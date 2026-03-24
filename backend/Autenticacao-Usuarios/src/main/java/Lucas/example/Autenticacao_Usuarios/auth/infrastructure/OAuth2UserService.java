package Lucas.example.Autenticacao_Usuarios.auth.infrastructure;

import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import Lucas.example.Autenticacao_Usuarios.user.domain.repository.UserRepository;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) {
        OAuth2User oAuth2User = super.loadUser(request);

        String registrationId = request.getClientRegistration().getRegistrationId();
        String name = oAuth2User.getAttribute("name");
        String emailStr = oAuth2User.getAttribute("email");

        User.AuthProvider provider = registrationId.equalsIgnoreCase("google")
                ? User.AuthProvider.GOOGLE
                : User.AuthProvider.GITHUB;

        Email email = new Email(emailStr);

        userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = User.createOAuth(name, email, provider);
                    return userRepository.save(newUser);
                });

        return oAuth2User;
    }
}
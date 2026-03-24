package Lucas.example.Autenticacao_Usuarios.auth.infrastructure;

import Lucas.example.Autenticacao_Usuarios.auth.domain.TokenService;
import Lucas.example.Autenticacao_Usuarios.user.domain.repository.UserRepository;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String emailStr = oAuth2User.getAttribute("email");

        userRepository.findByEmail(new Email(emailStr))
                .ifPresent(user -> {
                    String token = tokenService.generateToken(user);
                    try {
                        // Redireciona pro frontend com o token na URL
                        response.sendRedirect("http://localhost:5173/oauth2/callback?token=" + token);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
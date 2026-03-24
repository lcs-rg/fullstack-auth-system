package Lucas.example.Autenticacao_Usuarios.auth.application.usecase;

import Lucas.example.Autenticacao_Usuarios.auth.application.dto.AuthResponse;
import Lucas.example.Autenticacao_Usuarios.auth.application.dto.LoginRequest;
import Lucas.example.Autenticacao_Usuarios.auth.application.mapper.AuthMapper;
import Lucas.example.Autenticacao_Usuarios.auth.domain.TokenService;
import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import Lucas.example.Autenticacao_Usuarios.user.domain.repository.UserRepository;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthMapper authMapper;

    public AuthResponse execute(LoginRequest request) {

        Email email = new Email(request.email());

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (user.isOAuthUser()) {
            throw new IllegalArgumentException(
                    "Esse email está vinculado ao " + user.getAuthProvider().name()
            );
        }

        boolean senhaCorreta = passwordEncoder.matches(
                request.password(),
                user.getPassword().value()
        );

        if (!senhaCorreta) {
            throw new IllegalArgumentException("Senha incorreta");
        }

        String token = tokenService.generateToken(user);

        return authMapper.toResponse(user, token);
    }
}
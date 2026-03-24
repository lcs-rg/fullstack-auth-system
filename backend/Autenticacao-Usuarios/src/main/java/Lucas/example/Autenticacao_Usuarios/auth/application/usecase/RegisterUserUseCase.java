package Lucas.example.Autenticacao_Usuarios.auth.application.usecase;

import Lucas.example.Autenticacao_Usuarios.auth.application.dto.AuthResponse;
import Lucas.example.Autenticacao_Usuarios.auth.application.mapper.AuthMapper;
import Lucas.example.Autenticacao_Usuarios.auth.domain.TokenService;
import Lucas.example.Autenticacao_Usuarios.auth.application.dto.RegisterRequest;
import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import Lucas.example.Autenticacao_Usuarios.user.domain.repository.UserRepository;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Password;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.RawPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final AuthMapper authMapper;

    @Transactional
    public AuthResponse execute(RegisterRequest request) {

        Email email = new Email(request.email());

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        new RawPassword(request.password());

        String hash = passwordEncoder.encode(request.password());
        Password password = new Password(hash);

        User user = User.createLocal(request.name(), email, password);

        userRepository.save(user);

        String token = tokenService.generateToken(user);

        return authMapper.toResponse(user, token);
    }
}
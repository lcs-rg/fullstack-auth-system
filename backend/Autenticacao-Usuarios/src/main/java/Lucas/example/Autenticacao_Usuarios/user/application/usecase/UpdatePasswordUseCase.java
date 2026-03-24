package Lucas.example.Autenticacao_Usuarios.user.application.usecase;

import Lucas.example.Autenticacao_Usuarios.user.application.dto.UpdatePasswordRequest;
import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import Lucas.example.Autenticacao_Usuarios.user.domain.repository.UserRepository;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Password;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.RawPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdatePasswordUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void execute(UUID userId, UpdatePasswordRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (user.isOAuthUser()) {
            throw new IllegalArgumentException("Usuários OAuth não possuem senha");
        }

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new IllegalArgumentException("Senhas não coincidem");
        }

        boolean senhaCorreta = passwordEncoder.matches(
                request.currentPassword(),
                user.getPassword().value()
        );

        if (!senhaCorreta) {
            throw new IllegalArgumentException("Senha atual incorreta");
        }

        new RawPassword(request.newPassword());

        String hash = passwordEncoder.encode(request.newPassword());

        user.updatePassword(new Password(hash));

        userRepository.save(user);
    }
}
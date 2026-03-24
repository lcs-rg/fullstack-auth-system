package Lucas.example.Autenticacao_Usuarios.user.application.usecase;

import Lucas.example.Autenticacao_Usuarios.user.application.dto.UpdateEmailRequest;
import Lucas.example.Autenticacao_Usuarios.user.application.dto.UserResponse;
import Lucas.example.Autenticacao_Usuarios.user.infrastructure.mapper.UserMapper;
import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import Lucas.example.Autenticacao_Usuarios.user.domain.repository.UserRepository;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UpdateEmailUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserResponse execute(UUID userId, UpdateEmailRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        if (user.isOAuthUser()) {
            throw new IllegalArgumentException("Usuários OAuth não podem alterar o email");
        }

        Email newEmail = new Email(request.newEmail());

        if (userRepository.existsByEmail(newEmail)) {
            throw new IllegalArgumentException("Email já em uso");
        }

        user.updateEmail(newEmail);

        userRepository.save(user);

        return userMapper.toResponse(user);
    }
}
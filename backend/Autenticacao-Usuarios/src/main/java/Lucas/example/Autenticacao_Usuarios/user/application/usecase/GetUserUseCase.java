package Lucas.example.Autenticacao_Usuarios.user.application.usecase;

import Lucas.example.Autenticacao_Usuarios.user.application.dto.UserResponse;
import Lucas.example.Autenticacao_Usuarios.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@RequiredArgsConstructor
public class GetUserUseCase {

    private final UserRepository userRepository;

    public UserResponse execute(UUID userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UserResponse(
                user.getId().toString(),
                user.getName(),
                user.getEmail().value(),
                user.getAuthProvider().name()
        );
    }
}
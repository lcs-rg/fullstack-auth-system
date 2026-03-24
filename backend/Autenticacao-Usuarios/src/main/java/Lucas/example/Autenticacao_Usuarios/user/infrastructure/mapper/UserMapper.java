package Lucas.example.Autenticacao_Usuarios.user.infrastructure.mapper;

import Lucas.example.Autenticacao_Usuarios.user.application.dto.UserResponse;
import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId().toString(),
                user.getName(),
                user.getEmail().value(),
                user.getAuthProvider().name()
        );
    }
}
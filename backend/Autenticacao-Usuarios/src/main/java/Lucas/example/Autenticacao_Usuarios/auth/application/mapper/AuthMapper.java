package Lucas.example.Autenticacao_Usuarios.auth.application.mapper;

import Lucas.example.Autenticacao_Usuarios.auth.application.dto.AuthResponse;
import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthResponse toResponse(User user, String token) {
        return new AuthResponse(
                token,
                user.getName(),
                user.getEmail().value(),
                user.getAuthProvider().name()
        );
    }
}
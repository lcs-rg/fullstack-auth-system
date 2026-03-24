package Lucas.example.Autenticacao_Usuarios.user.application.dto;

public record UserResponse(
        String id,
        String name,
        String email,
        String authProvider
) {}
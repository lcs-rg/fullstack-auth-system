package Lucas.example.Autenticacao_Usuarios.auth.application.dto;

public record AuthResponse(
        String token,
        String name,
        String email,
        String authProvider
) {}
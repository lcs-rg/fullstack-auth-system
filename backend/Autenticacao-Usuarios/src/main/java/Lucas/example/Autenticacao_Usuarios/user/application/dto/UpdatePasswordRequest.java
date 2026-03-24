package Lucas.example.Autenticacao_Usuarios.user.application.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordRequest(

        @NotBlank(message = "Senha atual é obrigatória")
        String currentPassword,

        @NotBlank(message = "Nova senha é obrigatória")
        String newPassword,

        @NotBlank(message = "Confirmação de senha é obrigatória")
        String confirmPassword
) {}
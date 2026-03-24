package Lucas.example.Autenticacao_Usuarios.user.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateEmailRequest(

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String newEmail
) {}
package Lucas.example.Autenticacao_Usuarios.interfaces.http;

import Lucas.example.Autenticacao_Usuarios.user.application.dto.UpdateEmailRequest;
import Lucas.example.Autenticacao_Usuarios.user.application.dto.UpdatePasswordRequest;
import Lucas.example.Autenticacao_Usuarios.user.application.dto.UserResponse;
import Lucas.example.Autenticacao_Usuarios.user.application.usecase.GetUserUseCase;
import Lucas.example.Autenticacao_Usuarios.user.application.usecase.UpdateEmailUseCase;
import Lucas.example.Autenticacao_Usuarios.user.application.usecase.UpdatePasswordUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Endpoints de gerenciamento de usuário")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final GetUserUseCase getUserUseCase;
    private final UpdateEmailUseCase updateEmailUseCase;
    private final UpdatePasswordUseCase updatePasswordUseCase;


    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails) {

        UUID userId = UUID.fromString(userDetails.getUsername());

        return ResponseEntity.ok(getUserUseCase.execute(userId));
    }

    @PutMapping("/email")
    @Operation(summary = "Atualizar email do usuário")
    public ResponseEntity<UserResponse> updateEmail(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid UpdateEmailRequest request) {

        UUID userId = UUID.fromString(userDetails.getUsername());
        UserResponse response = updateEmailUseCase.execute(userId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/password")
    @Operation(summary = "Atualizar senha do usuário")
    public ResponseEntity<Void> updatePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody @Valid UpdatePasswordRequest request) {

        UUID userId = UUID.fromString(userDetails.getUsername());
        updatePasswordUseCase.execute(userId, request);
        return ResponseEntity.noContent().build();
    }
}
package Lucas.example.Autenticacao_Usuarios.interfaces.http;

import Lucas.example.Autenticacao_Usuarios.auth.application.dto.AuthResponse;
import Lucas.example.Autenticacao_Usuarios.auth.application.dto.LoginRequest;
import Lucas.example.Autenticacao_Usuarios.auth.application.dto.RegisterRequest;
import Lucas.example.Autenticacao_Usuarios.auth.application.usecase.LoginUseCase;
import Lucas.example.Autenticacao_Usuarios.auth.application.usecase.RegisterUserUseCase;
import Lucas.example.Autenticacao_Usuarios.user.application.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints de registro e login")
public class    AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;



    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registrar novo usuário")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        System.out.println("=== register chamado: " + request.email());
        AuthResponse response = registerUserUseCase.execute(request);
        System.out.println("=== register concluído");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Login com email e senha")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        AuthResponse response = loginUseCase.execute(request);
        return ResponseEntity.ok(response);
    }
}
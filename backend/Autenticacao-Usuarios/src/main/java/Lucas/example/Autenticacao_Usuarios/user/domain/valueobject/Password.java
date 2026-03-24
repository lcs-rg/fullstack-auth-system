package Lucas.example.Autenticacao_Usuarios.user.domain.valueobject;

public record Password(String value) {
    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
        if (!value.startsWith("$2a$") && !value.startsWith("$2b$") && !value.startsWith("$2y$")) {
            throw new IllegalArgumentException("A senha deve ser criptografada com BCrypt antes de ser armazenada");
        }
    }
}
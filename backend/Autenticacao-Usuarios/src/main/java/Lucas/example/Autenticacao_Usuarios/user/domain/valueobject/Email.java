package Lucas.example.Autenticacao_Usuarios.user.domain.valueobject;

public record Email(String value) {
    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email não pode ser vazio");
        }
        value = value.toLowerCase().trim();
    }
}
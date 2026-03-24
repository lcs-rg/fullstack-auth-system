package Lucas.example.Autenticacao_Usuarios.user.domain.valueobject;

public record RawPassword(String value) {
    public RawPassword {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
        if (value.length() < 8) {
            throw new IllegalArgumentException("Senha deve ter no mínimo 8 caracteres");
        }
        if (!value.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos 1 letra maiúscula");
        }
        if (!value.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos 1 número");
        }
        if (!value.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            throw new IllegalArgumentException("Senha deve conter pelo menos 1 caractere especial");
        }
    }
}
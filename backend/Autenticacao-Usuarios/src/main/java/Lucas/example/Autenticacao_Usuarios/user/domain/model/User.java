package Lucas.example.Autenticacao_Usuarios.user.domain.model;


import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Password;

import java.util.UUID;

public class User {

    private final UUID id;
    private Email email;
    private Password password;
    private String name;
    private AuthProvider authProvider;

    public enum AuthProvider {
        LOCAL, GOOGLE, GITHUB
    }

    public User(UUID id, String name, Email email, Password password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authProvider = AuthProvider.LOCAL;
    }

    public User(UUID id, String name, Email email, AuthProvider authProvider) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = null;
        this.authProvider = authProvider;
    }

    public static User createLocal(String name, Email email, Password password) {
        return new User(null, name, email, password);
    }

    public static User createOAuth(String name, Email email, AuthProvider provider) {
        return new User(null, name, email, provider);
    }

    public boolean isOAuthUser() {
        return this.authProvider != AuthProvider.LOCAL;
    }

    public void updateEmail(Email newEmail) {
        this.email = newEmail;
    }

    public void updatePassword(Password newPassword) {
        this.password = newPassword;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public Email getEmail() { return email; }
    public Password getPassword() { return password; }
    public AuthProvider getAuthProvider() { return authProvider; }
}

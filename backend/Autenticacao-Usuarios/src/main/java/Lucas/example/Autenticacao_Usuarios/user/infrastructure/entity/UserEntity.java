package Lucas.example.Autenticacao_Usuarios.user.infrastructure.entity;

import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Password;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private User.AuthProvider authProvider;

    public static UserEntity fromDomain(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail().value())
                .password(user.getPassword() != null ? user.getPassword().value() : null)
                .authProvider(user.getAuthProvider())
                .build();
    }

    public User toDomain() {
        System.out.println("=== toDomain() chamado ===");
        System.out.println("id: " + this.id);
        System.out.println("email: " + this.email);
        System.out.println("password: " + this.password);
        System.out.println("authProvider: " + this.authProvider);

        if (this.authProvider == User.AuthProvider.LOCAL && this.password != null) {
            return new User(this.id, this.name, new Email(this.email), new Password(this.password));
        }
        return new User(this.id, this.name, new Email(this.email), this.authProvider);
    }

    public UUID getId() {
        return id;
    }

}
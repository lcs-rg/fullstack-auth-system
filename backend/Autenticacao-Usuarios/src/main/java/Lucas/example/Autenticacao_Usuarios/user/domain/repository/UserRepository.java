package Lucas.example.Autenticacao_Usuarios.user.domain.repository;

import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(Email email);

    boolean existsByEmail(Email email);
}

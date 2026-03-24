package Lucas.example.Autenticacao_Usuarios.user.infrastructure.repository;

import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import Lucas.example.Autenticacao_Usuarios.user.domain.repository.UserRepository;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;
import Lucas.example.Autenticacao_Usuarios.user.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Override
    @Transactional
    public User save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        UserEntity saved = jpaRepository.saveAndFlush(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id)
                .map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return jpaRepository.findByEmail(email.value())
                .map(UserEntity::toDomain);
    }

    @Override
    public boolean existsByEmail(Email email) {
        System.out.println("=== existsByEmail chamado: " + email.value());
        boolean exists = jpaRepository.existsByEmail(email.value());
        System.out.println("=== resultado: " + exists);
        return exists;
    }
}
package Lucas.example.Autenticacao_Usuarios.shared.security;

import Lucas.example.Autenticacao_Usuarios.user.domain.repository.UserRepository;
import Lucas.example.Autenticacao_Usuarios.user.domain.valueobject.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Lazy
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("=== loadUserByUsername chamado com: " + email);
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            System.out.println("  at " + element);
        }
        return userRepository.findByEmail(new Email(email))
                .map(user -> User.builder()
                        .username(user.getId().toString())
                        .password(user.getPassword() != null ? user.getPassword().value() : "")
                        .roles("USER")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
}
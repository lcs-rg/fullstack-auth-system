package Lucas.example.Autenticacao_Usuarios.auth.domain;

import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;

public interface TokenService {

    String generateToken(User user);

    String extractEmail(String token);

    boolean isTokenValid(String token);


}
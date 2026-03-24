package Lucas.example.Autenticacao_Usuarios.auth.infrastructure;

import Lucas.example.Autenticacao_Usuarios.auth.domain.TokenService;
import Lucas.example.Autenticacao_Usuarios.user.domain.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenService implements TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail().value())
                .claim("name", user.getName())
                .claim("authProvider", user.getAuthProvider().name())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public String extractEmail(String token) {
        return parseClaims(token).getSubject();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            extractEmail(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
package learn.digipet.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import learn.digipet.models.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtConverter {

    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final String ISSUER = "Tama-auth-i";

    private final int EXPIRATION_MINUTES = 60;
    private final int EXPIRATION_MILLIS = EXPIRATION_MINUTES * 60 * 1000;

    public String getTokenFromUser(User user) {
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(key)
                .compact();
    }

    public User getUserFromAuthHeader(String header) {
        if (header == null || !header.startsWith("bearer ")) {
            return null;
        }

        String token = header.substring(7);

        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .requireIssuer(ISSUER)
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            Claims claims = jws.getBody();
            String username = claims.getSubject();
            // String role = (String) claims.get("roles");

            return new User(username);

        } catch (JwtException ex) {
            System.out.println(ex);
        }

        return null;
    }

}

package peaksoft.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import peaksoft.entities.LoginDetails;
import peaksoft.exceptions.RoleNotFoundException;
import peaksoft.exceptions.UserNotFoundException;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${app.jwt.secret}")
    private String secretKey;

    //    create token jwt / encode token

    public String createToken(LoginDetails loginDetails) throws UserNotFoundException, RoleNotFoundException {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        return JWT.create()
                .withClaim("email", loginDetails.getUsername())
                .withClaim("role", loginDetails.getRole().name())
                .withIssuedAt(ZonedDateTime.now().toInstant())
                .withExpiresAt(ZonedDateTime.now().plusHours(4).toInstant())
                .sign(algorithm);
    }

    //    verify token jwt / decode token
    public String verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        return decodedJWT.getClaim("email").asString();
    }
}

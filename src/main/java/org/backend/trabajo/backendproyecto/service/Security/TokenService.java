package org.backend.trabajo.backendproyecto.service.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.backend.trabajo.backendproyecto.model.Admin;
import org.backend.trabajo.backendproyecto.model.Cliente;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String generarToken(Admin usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            return JWT.create()
                    .withIssuer("voll med")
                    .withSubject(usuario.getUsuario())
                    .withClaim("id",usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        }
        catch (JWTCreationException exception){
            throw new RuntimeException();
        }

    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusDays(2).toInstant(ZoneOffset.of("-05:00"));
    }
}

package modules.login.resource;

import core.encoder.PBKDF2Encoder;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modules.login.dtos.AuthRequest;
import modules.login.dtos.AuthResponse;
import modules.login.usecases.TokenUtils;
import modules.users.enumerations.UserStatus;
import modules.users.structure.entities.User;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/11/23
 */
@Path("/auth")
public class AuthResource {

    @Inject
    PBKDF2Encoder passwordEncoder;

    @ConfigProperty(name = "com.ard333.quarkusjwt.jwt.duration")
    public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String issuer;

    @Transactional
    @PermitAll
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = AuthRequest.class)))
    @Operation(summary = "Login", description = "Realiza o login do usuário")
    public Response login(AuthRequest authRequest) {
        final User u = User.find("email", authRequest.email).firstResult();
        if (u == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Usuário inexistente")
                .build();
        }
        if (!u.getPassword().equals(passwordEncoder.encode(authRequest.senha))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Senha invalida").build();
        }
        if (Objects.equals(u.getStatus(), UserStatus.INACTIVE)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário inativo").build();
        }
        if (u != null && u.getPassword().equals(passwordEncoder.encode(authRequest.senha))) {
            try {
                Response resp = Response.ok(new AuthResponse(
                    TokenUtils.generateToken(u.getName(), u.getEmail(), u.getRole().toString(),
                        duration, issuer, u.getId().toString()))).build();
                u.setLastLogin(new Timestamp(System.currentTimeMillis()));
                User.persist(u);
                return resp;
            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
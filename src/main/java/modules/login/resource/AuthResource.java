package modules.login.resource;

import core.emailservice.MessageOperation;
import core.emailservice.SendEmailService;
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
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.enumerations.StatusUsuario;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.usecases.GenerateRandomCode;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

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

    @Inject
    GenerateRandomCode generateRandomCode;

    @Inject
    SendEmailService sendEmailService;

    @Inject
    UsuarioConverter usuarioConverter;

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
        final Usuario u = Usuario.find("email", authRequest.email).firstResult();
        if (u == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Usuario inexistente")
                .build();
        }
        if (!u.getSenha().equals(passwordEncoder.encode(authRequest.password))) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Senha invalida").build();
        }
        if(Objects.equals(u.getStatus(), StatusUsuario.BLOQUEADO)){
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario bloqueado, entre em contato com o suporte").build();
        }
        if (Objects.equals(u.getStatus(), StatusUsuario.INATIVO)) {
            if (Objects.isNull(u.getCodigo())) {
                u.setCodigo(generateRandomCode.execute());
                Usuario.persist(u);
                sendEmailService.sendMail(usuarioConverter.toDTO(u), "Ativação de conta",
                    MessageOperation.ATIVACAO);
            }
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario inativo").build();
        }
        if (u != null && u.getSenha().equals(passwordEncoder.encode(authRequest.password))) {
            try {
                return Response.ok(new AuthResponse(
                    TokenUtils.generateToken(u.getNome(), u.getEmail(), u.getRole(),
                        duration, issuer, u.getId().toString()))).build();

            } catch (Exception e) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
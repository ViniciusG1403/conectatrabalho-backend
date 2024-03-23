package modules.login.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import modules.login.dtos.AuthResponse;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 22/03/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class AtualizarToken {

    private final UsuarioRepository usuarioRepository;

    @ConfigProperty(name = "com.ard333.quarkusjwt.jwt.duration")
    public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String issuer;

    public Response execute(UUID idUsuario) throws Exception {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(
            UsuarioNotFoundException::new);

        return Response.ok(new AuthResponse(
            TokenUtils.generateToken(usuario.getNome(), usuario.getEmail(),
                usuario.getRole(),
                duration, issuer, usuario.getId().toString()))).build();
    }
}


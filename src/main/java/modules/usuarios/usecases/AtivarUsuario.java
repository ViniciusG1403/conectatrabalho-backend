package modules.usuarios.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import modules.usuarios.enumerations.StatusUsuario;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 14/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class AtivarUsuario {

    private final UsuarioRepository usuarioRepository;

    public void execute(String codigo) {
        Usuario usuario = usuarioRepository.findOne("codigo", codigo).orElseThrow(UsuarioNotFoundException::new);

        if (Objects.equals(usuario.getStatus(), StatusUsuario.ATIVO)) {
            throw new ValidationException("Usuário já está ativo");
        }

        usuario.setStatus(StatusUsuario.ATIVO);
        usuario.setCodigo(null);
        usuarioRepository.update(usuario);
    }

}

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
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 14/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BloquearUsuario {

    private final UsuarioRepository usuarioRepository;

    public void execute(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);

        if (Objects.equals(usuario.getStatus(), StatusUsuario.BLOQUEADO)) {
            throw new ValidationException("Usuário já está bloqueado");
        }

        usuario.setStatus(StatusUsuario.BLOQUEADO);
        usuario.setCodigo(null);
        usuarioRepository.update(usuario);
    }

}

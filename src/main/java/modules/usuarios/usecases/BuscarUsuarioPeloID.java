package modules.usuarios.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.infra.entities.Usuario;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 14/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarUsuarioPeloID {

    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO execute(UUID id) {
        return usuarioConverter.toDTO(Usuario.findById(id));
    }

}

package modules.usuarios.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.dtos.UsuarioResponseDTO;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 14/02/24
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarUsuarioPeloID {

    private final UsuarioConverter usuarioConverter;

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO execute(UUID id) {
        return usuarioConverter.toResponse(usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new));
    }

}

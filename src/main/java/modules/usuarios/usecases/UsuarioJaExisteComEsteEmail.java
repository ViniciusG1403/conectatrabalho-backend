package modules.usuarios.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.repositories.UsuarioRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 15/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class UsuarioJaExisteComEsteEmail {

    private final UsuarioRepository usuarioRepository;

    public boolean execute(UsuarioDTO usuarioDTO) {
        return usuarioRepository.findOne("email", usuarioDTO.getEmail()).map(usuario -> !usuario.getId().equals(usuarioDTO.getId())).orElse(false);
    }


}

package modules.usuarios.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarTodosOsUsuarios {

    private final UsuarioConverter usuarioConverter;

    private final UsuarioRepository usuarioRepository;


    public List<UsuarioDTO> execute() {
       return usuarioRepository.findAll().stream().map(usuarioConverter::toDTO).collect(Collectors.toList());
    }


}

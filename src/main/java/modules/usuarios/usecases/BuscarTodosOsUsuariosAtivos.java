package modules.usuarios.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioResponseDTO;
import modules.usuarios.enumerations.StatusUsuario;
import modules.usuarios.repositories.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarTodosOsUsuariosAtivos {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioConverter usuarioConverter;

    public List<UsuarioResponseDTO> execute() {
        CondicaoPesquisa condicaoPesquisa = new CondicaoPesquisa();
        condicaoPesquisa.setChave("status");
        condicaoPesquisa.setValor(StatusUsuario.ATIVO.ordinal());

        return usuarioRepository.findAll(List.of(condicaoPesquisa)).stream().map(usuarioConverter::toResponse).collect(Collectors.toList());
    }


}

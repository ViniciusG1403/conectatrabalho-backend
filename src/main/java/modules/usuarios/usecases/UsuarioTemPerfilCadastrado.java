package modules.usuarios.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.repositories.CandidatoRepository;
import modules.empresa.repositories.EmpresaRepository;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 09/03/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class UsuarioTemPerfilCadastrado {


    private final CandidatoRepository candidatoRepository;

    private final EmpresaRepository empresaRepository;

    public boolean execute(UUID idUsuario) {
        CondicaoPesquisa condicaoPesquisa = new CondicaoPesquisa("usuario.id", idUsuario);
        return candidatoRepository.findOne(condicaoPesquisa).isPresent() ||
            empresaRepository.findOne(condicaoPesquisa).isPresent();
    }


}

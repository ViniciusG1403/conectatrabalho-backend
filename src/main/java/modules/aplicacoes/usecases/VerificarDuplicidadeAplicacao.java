package modules.aplicacoes.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.repositories.AplicacaoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class VerificarDuplicidadeAplicacao {

    private final AplicacaoRepository aplicacaoRepository;

    public boolean execute(UUID idVaga, UUID idCandidato){
        List<CondicaoPesquisa> condicaoPesquisaList = new ArrayList<>();
        condicaoPesquisaList.add(new CondicaoPesquisa("vaga.id", idVaga));
        condicaoPesquisaList.add(new CondicaoPesquisa("candidato.id", idCandidato));

        return !aplicacaoRepository.findAll(condicaoPesquisaList).isEmpty();



    }

}
package modules.aplicacoes.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.converters.AplicacaoConverter;
import modules.aplicacoes.dtos.AplicacaoResponseDTO;
import modules.aplicacoes.repositories.AplicacaoRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 02/03/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarTodasAplicacoesCandidato {

    private final AplicacaoRepository aplicacaoRepository;

    private final AplicacaoConverter aplicacaoConverter;

    public List<AplicacaoResponseDTO> execute(UUID idCandidato){
        CondicaoPesquisa condicaoPesquisa = new CondicaoPesquisa();
        condicaoPesquisa.setChave("candidato.id");
        condicaoPesquisa.setValor(idCandidato);
        return aplicacaoRepository.findAll(List.of(condicaoPesquisa)).stream().map(aplicacaoConverter::toRespondeDTO).toList();
    }

}

package modules.aplicacoes.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.converters.AplicacaoConverter;
import modules.aplicacoes.dtos.AplicacaoResponseDTO;
import modules.aplicacoes.repositories.AplicacaoRepository;
import modules.empresa.repositories.EmpresaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 02/03/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarTodasAplicacoesVaga {

    private final AplicacaoRepository aplicacaoRepository;

    private final AplicacaoConverter aplicacaoConverter;

    public List<AplicacaoResponseDTO> execute(UUID idVaga, UUID idEmpresa){
        CondicaoPesquisa condicaoPesquisa = new CondicaoPesquisa();
        condicaoPesquisa.setChave("vaga.id");
        condicaoPesquisa.setValor(idVaga);

        CondicaoPesquisa condicaoPesquisaEmpresa = new CondicaoPesquisa();
        condicaoPesquisaEmpresa.setChave("vaga.empresa.id");
        condicaoPesquisaEmpresa.setValor(idEmpresa);

        List<CondicaoPesquisa> condicaoPesquisaList = List.of(condicaoPesquisa, condicaoPesquisaEmpresa);
        return aplicacaoRepository.findAll(condicaoPesquisaList).stream().map(aplicacaoConverter::toRespondeDTO).toList();
    }


}

package modules.aplicacoes.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.dtos.AplicacaoCadastroDTO;
import modules.aplicacoes.enumerations.StatusAplicacao;
import modules.aplicacoes.exceptions.AplicacaoNaoEncontradaExceptions;
import modules.aplicacoes.infra.entities.Aplicacao;
import modules.aplicacoes.repositories.AplicacaoRepository;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */

@ApplicationScoped
@RequiredArgsConstructor
public class CancelarAplicacao {

    private final AplicacaoRepository aplicacaoRepository;

    public void execute(AplicacaoCadastroDTO dto) {

        CondicaoPesquisa condicaoAplicacao = new CondicaoPesquisa();
        condicaoAplicacao.setChave("vaga.id");
        condicaoAplicacao.setValor(dto.getIdVaga());

        CondicaoPesquisa condicaoCandidato = new CondicaoPesquisa();
        condicaoCandidato.setChave("candidato.id");
        condicaoCandidato.setValor(dto.getCandidato());

        CondicaoPesquisa condicaoStatus = new CondicaoPesquisa();
        condicaoStatus.setChave("status");
        condicaoStatus.setOperacao("!=");
        condicaoStatus.setValor(StatusAplicacao.CANCELADO.name());



        Aplicacao aplicacao = aplicacaoRepository.findOne(List.of(condicaoAplicacao, condicaoCandidato, condicaoStatus)).orElseThrow(AplicacaoNaoEncontradaExceptions::new);

        aplicacao.setStatus(StatusAplicacao.CANCELADO);

        aplicacaoRepository.update(aplicacao);
    }

}

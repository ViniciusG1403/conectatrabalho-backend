package modules.aplicacoes.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.enumerations.StatusAplicacao;
import modules.aplicacoes.exceptions.AplicacaoNaoEncontradaExceptions;
import modules.aplicacoes.infra.entities.Aplicacao;
import modules.aplicacoes.repositories.AplicacaoRepository;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class ReprovarCandidato {

    private final AplicacaoRepository aplicacaoRepository;

    public void execute(UUID idAplicacao) {
        Aplicacao aplicacao = aplicacaoRepository.findById(idAplicacao).orElseThrow(
            AplicacaoNaoEncontradaExceptions::new);
        aplicacao.setStatus(StatusAplicacao.REPROVADO);
        aplicacaoRepository.update(aplicacao);
    }


}

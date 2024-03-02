package modules.aplicacoes.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.dtos.AplicacaoCadastroDTO;
import modules.aplicacoes.dtos.AplicacaoFeedbackDTO;
import modules.aplicacoes.usecases.*;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class AplicacaoService {


    private final AplicarParaVaga aplicarParaVaga;

    private final AprovarCandidato aprovarCandidato;

    private final ReprovarCandidato reprovarCandidato;

    private final DarFeedbackAplicacao darFeedbackAplicacao;

    public void aplicarParaVaga(AplicacaoCadastroDTO dto) {
        aplicarParaVaga.execute(dto);
    }

    public void aprovarCandidato(UUID idAplicacao) {
        aprovarCandidato.execute(idAplicacao);
    }

    public void reprovarCandidato(UUID idAplicacao) {
        reprovarCandidato.execute(idAplicacao);
    }

    public void darFeedbackAplicacao(AplicacaoFeedbackDTO feedback) {
        darFeedbackAplicacao.execute(feedback);
    }


}

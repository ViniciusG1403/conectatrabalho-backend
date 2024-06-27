package modules.aplicacoes.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.dtos.AplicacaoCadastroDTO;
import modules.aplicacoes.dtos.AplicacaoFeedbackDTO;
import modules.aplicacoes.dtos.AplicacaoResponseDTO;
import modules.aplicacoes.usecases.*;

import java.util.List;
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

    private final BuscarTodasAplicacoesCandidato buscarTodasAplicacoesCandidato;

    private final BuscarTodasAplicacoesVaga buscarTodasAplicacoesVaga;

    private final CancelarAplicacao cancelarAplicacao;

    public void aplicarParaVaga(AplicacaoCadastroDTO dto) {
        aplicarParaVaga.execute(dto);
    }

    public void cancelarAplicacao(AplicacaoCadastroDTO dto) {
        cancelarAplicacao.execute(dto);
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

    public List<AplicacaoResponseDTO> buscarTodasAplicacoesCandidato(UUID idCandidato){
        return buscarTodasAplicacoesCandidato.execute(idCandidato);
    }

    public List<AplicacaoResponseDTO> buscarTodasAplicacoesVaga(UUID idVaga, UUID idEmpresa){
        return buscarTodasAplicacoesVaga.execute(idVaga, idEmpresa);
    }


}

package modules.aplicacoes.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.dtos.AplicacaoFeedbackDTO;
import modules.aplicacoes.exceptions.AplicacaoNaoEncontradaExceptions;
import modules.aplicacoes.infra.entities.Aplicacao;
import modules.aplicacoes.repositories.AplicacaoRepository;

import java.util.Objects;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class DarFeedbackAplicacao {

    private final AplicacaoRepository aplicacaoRepository;

    public void execute(AplicacaoFeedbackDTO dto){
        Aplicacao aplicacao = aplicacaoRepository.findById(dto.getIdAplicacao()).orElseThrow(
            AplicacaoNaoEncontradaExceptions::new);

        if(Objects.equals(dto.getUsuario(), aplicacao.getVaga().getEmpresa().getId())) {
            aplicacao.setFeedbackEmpresa(dto.getFeedback());
            aplicacaoRepository.update(aplicacao);
        }

        if(Objects.equals(dto.getUsuario(), aplicacao.getCandidato().getId())) {
            aplicacao.setFeedbackCandidato(dto.getFeedback());
            aplicacaoRepository.update(aplicacao);
        }

        throw new ValidationException("Usuário não tem permissão para dar feedback nesta aplicação");
    }



}

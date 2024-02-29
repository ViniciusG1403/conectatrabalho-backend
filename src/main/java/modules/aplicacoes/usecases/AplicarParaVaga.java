package modules.aplicacoes.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.dtos.AplicacaoCadastroDTO;
import modules.aplicacoes.enumerations.StatusAplicacao;
import modules.aplicacoes.infra.entities.Aplicacao;
import modules.aplicacoes.repositories.AplicacaoRepository;
import modules.candidatos.exceptions.CandidatoNaoEncontradoException;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;
import modules.vagas.enumerations.StatusVaga;
import modules.vagas.exceptions.VagaNaoEncontradaException;
import modules.vagas.infra.entities.Vagas;
import modules.vagas.repositories.VagasRepository;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class AplicarParaVaga {

    private final AplicacaoRepository aplicacaoRepository;

    private final VerificarDuplicidadeAplicacao verificarDuplicidadeAplicacao;

    private final CandidatoRepository candidatoRepository;

    private final VagasRepository vagasRepository;

    public void execute(AplicacaoCadastroDTO dto) {
        if (verificarDuplicidadeAplicacao.execute(dto.getIdVaga(), dto.getCandidato())) {
            throw new ValidationException("Candidato já aplicou para essa vaga");
        }

        Candidato candidato = candidatoRepository.findById(dto.getCandidato())
            .orElseThrow(CandidatoNaoEncontradoException::new);
        Vagas vaga = vagasRepository.findById(dto.getIdVaga()).orElseThrow(
            VagaNaoEncontradaException::new);

        if(Objects.equals(vaga.getStatus(), StatusVaga.ENCERRADA)){
            throw new ValidationException("Vaga fechada");
        }

        if(Objects.equals(vaga.getStatus(), StatusVaga.INATIVA)){
            throw new ValidationException("Vaga inativa");
        }

        if(Objects.equals(vaga.getStatus(), StatusVaga.PAUSADA)){
            throw new ValidationException("Vaga pausada");
        }

        Aplicacao aplicacao = new Aplicacao();
        aplicacao.setVaga(vaga);
        aplicacao.setCandidato(candidato);
        aplicacao.setDataAplicacao(new Timestamp(System.currentTimeMillis()));
        aplicacao.setStatus(StatusAplicacao.PENDENTE);

        aplicacaoRepository.save(aplicacao);
    }

}

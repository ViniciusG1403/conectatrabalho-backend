package modules.candidatos.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoUpdateDTO;
import modules.candidatos.exceptions.CandidatoNaoEncontradoException;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class AtualizarCandidato {

    private final CandidatoConverter converter;

    private final CandidatoRepository repository;

    public void execute(CandidatoUpdateDTO dto) {
        Candidato candidato = repository.findById(dto.getId()).orElseThrow(
            CandidatoNaoEncontradoException::new);

        candidato.setPretensaoSalarial(dto.getPretensaoSalarial());
        candidato.setDisponibilidade(dto.getDisponibilidade());
        candidato.setUrlFotoPerfil(dto.getUrlFotoPerfil());
        candidato.setHabilidades(dto.getHabilidades());
        candidato.setLinkedin(dto.getLinkedin());
        candidato.setGithub(dto.getGithub());
        candidato.setPortfolio(dto.getPortfolio());
        candidato.setUrlCurriculum(dto.getUrlCurriculum());

        repository.update(candidato);
    }

}

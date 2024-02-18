package modules.candidatos.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.dtos.CandidatoResponseDTO;
import modules.candidatos.exceptions.CandidatoNaoEncontradoException;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/24
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarCandidatoPeloID {

    private final CandidatoRepository repository;

    private final CandidatoConverter converter;

    public CandidatoResponseDTO execute(UUID id) {
        Candidato candidato = repository.findById(id).orElseThrow(CandidatoNaoEncontradoException::new);
        return converter.toResponse(candidato);
    }


}

package modules.candidatos.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoResponseDTO;
import modules.candidatos.dtos.CandidatoResumidoDTO;
import modules.candidatos.exceptions.CandidatoNaoEncontradoException;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarCandidatoResumidoPeloID {

    private final CandidatoRepository repository;

    private final CandidatoConverter converter;

    public CandidatoResumidoDTO execute(UUID id) {
        Candidato candidato = repository.findById(id).orElseThrow(CandidatoNaoEncontradoException::new);
        return converter.toResumido(candidato);
    }


}

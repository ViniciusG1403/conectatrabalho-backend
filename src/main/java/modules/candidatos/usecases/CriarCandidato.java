package modules.candidatos.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;

import java.util.Objects;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class CriarCandidato {

    private final CandidatoConverter converter;

    private final CandidatoRepository repository;

    public CandidatoDTO execute(CandidatoDTO dto){
        Candidato entity = converter.toEntity(dto);
        repository.save(entity);
        return converter.toDTO(entity);
    }







}

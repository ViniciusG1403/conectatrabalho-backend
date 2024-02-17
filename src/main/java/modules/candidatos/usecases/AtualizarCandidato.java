package modules.candidatos.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoDTO;
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

    public void execute(CandidatoDTO dto){
        repository.update(converter.toEntity(dto));
    }

}

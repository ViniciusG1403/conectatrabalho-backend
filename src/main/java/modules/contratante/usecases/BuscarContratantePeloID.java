package modules.contratante.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.contratante.converters.ContratanteConverter;
import modules.contratante.dtos.ContratanteResponseDTO;
import modules.contratante.exceptions.ContratanteNaoEncontradoException;
import modules.contratante.infra.entities.Contratante;
import modules.contratante.repositories.ContratanteRepository;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarContratantePeloID {

    private final ContratanteRepository repository;

    private final ContratanteConverter converter;

    public ContratanteResponseDTO execute(UUID id) {
        Contratante contratante = repository.findById(id).orElseThrow(ContratanteNaoEncontradoException::new);
        return converter.toResponse(contratante);
    }


}

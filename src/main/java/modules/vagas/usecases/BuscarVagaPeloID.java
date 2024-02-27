package modules.vagas.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.vagas.converters.VagasConverter;
import modules.vagas.dtos.VagasDTO;
import modules.vagas.exceptions.VagaNaoEncontradaException;
import modules.vagas.repositories.VagasRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 27/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarVagaPeloID {

    private final VagasRepository vagasRepository;

    private final VagasConverter vagasConverter;
    public VagasDTO execute(Long id) {
        return vagasRepository.findById(id).map(vagasConverter::toDTO).orElseThrow(VagaNaoEncontradaException::new);
    }


}

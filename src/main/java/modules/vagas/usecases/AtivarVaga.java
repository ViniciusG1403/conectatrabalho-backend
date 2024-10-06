package modules.vagas.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import modules.vagas.dtos.FinalizarPausarVagaDTO;
import modules.vagas.enumerations.StatusVaga;
import modules.vagas.exceptions.VagaNaoEncontradaException;
import modules.vagas.infra.entities.Vagas;
import modules.vagas.repositories.VagasRepository;

import java.util.Objects;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class AtivarVaga {

    private final VagasRepository vagasRepository;

    public void execute(FinalizarPausarVagaDTO dto) {
        Vagas vaga = vagasRepository.findById(dto.getIdVaga()).orElseThrow(
            VagaNaoEncontradaException::new);

        if(!Objects.equals(vaga.getEmpresa().getId(), dto.getIdEmpresa()))
            throw new ValidationException("Vaga não pertence a empresa");

        vaga.setStatus(StatusVaga.ATIVA);
        vagasRepository.update(vaga);
    }
}

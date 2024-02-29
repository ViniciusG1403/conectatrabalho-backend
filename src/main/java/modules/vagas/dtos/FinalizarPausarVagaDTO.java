package modules.vagas.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@Getter
@Setter
@RegisterForReflection
public class FinalizarPausarVagaDTO {

    private UUID idVaga;

    private UUID idEmpresa;

}

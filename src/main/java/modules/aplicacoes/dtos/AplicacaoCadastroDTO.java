package modules.aplicacoes.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class AplicacaoCadastroDTO {

    @Schema(description = "Vaga que o candidato se aplicou", required = true)
    @NotNull(message = "A vaga é obrigatória")
    private UUID idVaga;

    @Schema(description = "Candidato que aplicou na vaga", required = true)
    @NotNull(message = "O candidato é obrigatório")
    private UUID candidato;

}

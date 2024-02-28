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
public class AplicacaoFeedbackDTO {

    @Schema(description = "Vaga que o candidato se aplicou", required = true)
    @NotNull(message = "A vaga é obrigatória")
    private UUID idVaga;

    @Schema(description = "Usuario - Candidato ou Empresa", required = true)
    private UUID usuario;

    @Schema(description = "Feedback")
    @NotNull(message = "O feedback é obrigatório")
    private String feedback;

}

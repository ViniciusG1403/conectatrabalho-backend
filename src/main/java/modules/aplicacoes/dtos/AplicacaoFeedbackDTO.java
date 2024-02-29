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

    @Schema(description = "Id da aplicação", required = true)
    @NotNull(message = "O id da aplicação é obrigatório")
    private UUID idAplicacao;

    @Schema(description = "Usuario - Candidato ou Empresa", required = true)
    private UUID usuario;

    @Schema(description = "Feedback")
    @NotNull(message = "O feedback é obrigatório")
    private String feedback;

}

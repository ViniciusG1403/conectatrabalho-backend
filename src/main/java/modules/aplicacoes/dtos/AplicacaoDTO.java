package modules.aplicacoes.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import modules.aplicacoes.enumerations.StatusAplicacao;
import modules.candidatos.dtos.CandidatoDTO;
import modules.empresa.dtos.EmpresaDTO;
import modules.empresa.dtos.EmpresaResumidoDTO;
import modules.vagas.dtos.VagasResumidoDTO;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class AplicacaoDTO {

    @Schema(description = "Identificador da aplicação", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Vaga que o candidato se aplicou", required = true)
    @NotNull(message = "A vaga é obrigatória")
    private VagasResumidoDTO vaga;

    @Schema(description = "Candidato que aplicou na vaga", required = true)
    @NotNull(message = "O candidato é obrigatório")
    private CandidatoDTO candidato;

    @Schema(description = "Data e Hora que o candidato que se aplicou")
    private Timestamp dataAplicacao;

    @Schema(description = "Feedback do candidato")
    private String feedbackCandidato;

    @Schema(description = "Feedback da empresa")
    private String feedbackEmpresa;

    @Schema(description = "Status da aplicação", required = true, example = "1")
    @NotNull(message = "O status é obrigatório")
    @Min(value = 0, message = "O status deve ser 0 - Pendente, 1 - Aprovado ou 2 - Reprovado")
    @Max(value = 2, message = "O status deve ser 0 - Pendente, 1 - Aprovado ou 2 - Reprovado")
    private Integer status;


}

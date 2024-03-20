package modules.vagas.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import modules.empresa.dtos.EmpresaDTO;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
@Getter
@Setter
@RegisterForReflection
public class VagasDTO {

    @Schema(description = "Identificador da vaga", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Empresa que criou a vaga", required = true)
    @NotNull(message = "A empresa é obrigatória")
    private EmpresaDTO empresa;

    @Schema(description = "Título da vaga", required = true, example = "Desenvolvedor Java")
    @NotNull(message = "O título é obrigatório")
    @Size(min = 1, max = 100, message = "O título deve ter entre 1 e 100 caracteres")
    private String titulo;

    @Schema(description = "Descrição da vaga", required = true, example = "Desenvolvimento de aplicações Java")
    @NotNull(message = "A descrição é obrigatória")
    private String descricao;

    @Schema(description = "Remuneração da vaga", required = true, example = "5000.00")
    @NotNull(message = "A remuneração é obrigatória")
    private BigDecimal remuneracao;

    @Schema(description = "Tipo da vaga", required = true, example = "1")
    @NotNull(message = "O tipo é obrigatório")
    @Min(value = 0, message = "O tipo deve ser 0 - CLT, 1 - PJ, 2 - Estágio ou 3 - Trainee")
    @Max(value = 3, message = "O tipo deve ser 0 - CLT, 1 - PJ, 2 - Estágio ou 3 - Trainee")
    private Integer tipo;

    @Schema(description = "Nível da vaga", required = true, example = "Pleno")
    @NotNull(message = "O nível é obrigatório")
    private String nivel;

    @Schema(description = "Status da vaga", required = true, example = "1")
    @NotNull(message = "O status é obrigatório")
    @Min(value = 0, message = "O status deve ser 0 - Inativa, 1 - Ativa, 2 - Pausada ou 3 - Encerrada")
    @Max(value = 3, message = "O status deve ser 0 - Inativa, 1 - Ativa, 2 - Pausada ou 3 - Encerrada")
    private Integer status;

    @Schema(description = "Data de criação da vaga", required = true, example = "2024-02-25 00:00:00")
    private Timestamp dataCriacao;

    @Schema(description = "Cargo da vaga", required = true, example = "Desenvolvedor Java")
    @NotNull(message = "O cargo é obrigatório")
    private String cargo;

}

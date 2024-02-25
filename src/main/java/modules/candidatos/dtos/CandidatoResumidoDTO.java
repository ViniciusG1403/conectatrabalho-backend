package modules.candidatos.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 22/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class CandidatoResumidoDTO {

    @Schema(name = "nome", required = true, title = "Nome do candidato", example = "Vinicius Gabriel")
    private String nome;

    @Schema(name = "habilidades", title = "Habilidades do candidato", example = "Java, Spring Boot, Angular")
    private String habilidades;

    @Schema(name = "cidadeEstado", title = "Cidade e estado do candidato", example = "São Paulo - SP")
    private String cidadeEstado;


}

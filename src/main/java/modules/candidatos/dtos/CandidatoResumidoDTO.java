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

    @Schema(name = "Nome do candidato", required = true, title = "Nome", example = "Vinicius Gabriel")
    private String nome;

    @Schema(name = "Habilidades do candidato", title = "Habilidades", example = "Java, Spring Boot, Angular")
    private String habilidades;

    @Schema(name = "URL da foto de perfil do candidato", title = "URL da foto de perfil", example = "www.viniciusgabriel.com.br/foto-perfil")
    private String urlFotoPerfil;

    @Schema(name = "Cidade e estado do candidato", title = "Cidade e estado", example = "São Paulo - SP")
    private String cidadeEstado;

}

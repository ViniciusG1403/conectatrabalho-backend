package modules.candidatos.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.dtos.EnderecoDTO;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 18/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class CandidatoResponseDTO {

    @Schema(name = "nome", required = true, title = "Nome do candidato", example = "Vinicius Gabriel")
    private String nome;

    @Schema(name = "email", required = true, title = "E-mail do candidato", example = "email@mail.com")
    private String email;

    @Schema(name = "telefone", title = "Telefone do candidato", example = "(11) 99999-9999")
    private String telefone;

    @Schema(name = "habilidades", title = "Habilidades do candidato", example = "Java, Spring Boot, Angular")
    private String habilidades;

    @Schema(name = "linkedin", title = "LinkedIn do candidato", example = "www.linkedin.com/in/viniciusgabriel")
    private String linkedin;

    @Schema(name = "github", title = "GitHub do candidato", example = "www.github.com/viniciusgabriel")
    private String github;

    @Schema(name = "portfolio", title = "Portfolio do candidato", example = "www.viniciusgabriel.com.br")
    private String portfolio;

    @Schema(name = "disponibilidade", title = "Disponibilidade do candidato", example = "Disponível para viagens")
    private String disponibilidade;

    @Schema(name = "pretensaoSalarial", title = "Pretensão salarial do candidato", example = "5000.00")
    private BigDecimal pretensaoSalarial;

    @Schema(name = "endereco", title = "Endereço do candidato")
    private EnderecoDTO endereco;


}

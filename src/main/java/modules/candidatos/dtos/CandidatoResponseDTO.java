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

    @Schema(name = "Nome do candidato", required = true, title = "Nome", example = "Vinicius Gabriel")
    private String nome;

    @Schema(name = "E-mail do candidato", required = true, title = "E-mail", example = "email@mail.com")
    private String email;

    @Schema(name = "Telefone do candidato", title = "Telefone", example = "(11) 99999-9999")
    private String telefone;

    @Schema(name = "Habilidades do candidato", title = "Habilidades", example = "Java, Spring Boot, Angular")
    private String habilidades;

    @Schema(name = "LinkedIn do candidato", title = "LinkedIn", example = "www.linkedin.com/in/viniciusgabriel")
    private String linkedin;

    @Schema(name = "GitHub do candidato", title = "GitHub", example = "www.github.com/viniciusgabriel")
    private String github;

    @Schema(name = "Portfolio do candidato", title = "Portfolio", example = "www.viniciusgabriel.com.br")
    private String portfolio;

    @Schema(name = "Disponibilidade do candidato", title = "Disponibilidade", example = "Disponível para viagens")
    private String disponibilidade;

    @Schema(name = "Pretensão salarial do candidato", title = "Pretensão salarial", example = "5000.00")
    private BigDecimal pretensaoSalarial;

    @Schema(name = "URL do currículo do candidato", title = "URL do currículo", example = "www.viniciusgabriel.com.br/curriculo")
    private String urlCurriculum;

    @Schema(name = "URL da foto de perfil do candidato", title = "URL da foto de perfil", example = "www.viniciusgabriel.com.br/foto-perfil")
    private String urlFotoPerfil;

    @Schema(name = "Endereço do candidato", title = "Endereço")
    private EnderecoDTO endereco;

}

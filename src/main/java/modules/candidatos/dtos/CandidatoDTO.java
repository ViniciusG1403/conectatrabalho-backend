package modules.candidatos.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.infra.entities.Usuario;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class CandidatoDTO {

    @Schema(name = "id", required = true, title = "ID do candidato", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @NotNull(message = "O candidato deve estar associado a um usuário")
    @Schema(name = "usuario", required = true, title = "Usuário referenciado ao candidato")
    private UsuarioDTO usuario;

    @Schema(name = "habilidades", title = "Habilidades do candidato", example = "Java, Spring Boot, Angular")
    private String habilidades;

    @Schema(name = "linkedin", title = "LinkedIn do candidato", example = "www.linkedin.com/in/viniciusgabriel")
    private String linkedin;

    @Schema(name = "github", title = "GitHub do candidato", example = "www.github.com/viniciusgabriel")
    private String github;

    @Schema(name = "portfolio", title = "Portfolio do candidato", example = "www.viniciusgabriel.com.br")
    private String portfolio;

    @NotNull(message = "A disponibilidade do candidato deve ser informada")
    @Size(max = 40, message = "A disponibilidade do candidato deve ter no máximo 40 caracteres")
    @Schema(name = "disponibilidade", required = true, title = "Disponibilidade do candidato", example = "Disponível para viagens")
    private String disponibilidade;

    @NotNull(message = "A pretensão salarial do candidato deve ser informada")
    @Schema(name = "pretensaoSalarial", required = true, title = "Pretensão salarial do candidato", example = "5000.00")
    private BigDecimal pretensaoSalarial;

    @Schema(name = "urlCurriculum", title = "URL do currículo do candidato", example = "www.viniciusgabriel.com.br/curriculo")
    private String urlCurriculum;

    @Schema(name = "urlFotoPerfil", title = "URL da foto de perfil do candidato", example = "www.viniciusgabriel.com.br/foto-perfil")
    private String urlFotoPerfil;


}

package modules.candidatos.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import modules.usuarios.dtos.UsuarioDTO;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 23/02/24
 */
@RegisterForReflection
public class CandidatoCadastroDTO {

    @NotNull(message = "O candidato deve estar associado a um usuário")
    @Schema(name = "ID do usuário", required = true, title = "ID usuário referenciado ao candidato", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID idUsuario;

    @Schema(name = "Habilidades do candidato", title = "Habilidades do candidato", example = "Java, Spring Boot, Angular")
    private String habilidades;

    @Schema(name = "LinkedIn do candidato", title = "LinkedIn do candidato", example = "www.linkedin.com/in/viniciusgabriel")
    private String linkedin;

    @Schema(name = "GitHub do candidato", title = "GitHub do candidato", example = "www.github.com/viniciusgabriel")
    private String github;

    @Schema(name = "Portfolio do candidato", title = "Portfolio do candidato", example = "www.viniciusgabriel.com.br")
    private String portfolio;

    @NotNull(message = "A disponibilidade do candidato deve ser informada")
    @Size(max = 40, message = "A disponibilidade do candidato deve ter no máximo 40 caracteres")
    @Schema(name = "Disponibilidade do candidato", required = true, title = "Disponibilidade do candidato", example = "Disponível para viagens")
    private String disponibilidade;

    @NotNull(message = "A pretensão salarial do candidato deve ser informada")
    @Schema(name = "Pretensão salarial do candidato", required = true, title = "Pretensão salarial do candidato", example = "5000.00")
    private BigDecimal pretensaoSalarial;

    @Schema(name = "URL do currículo do candidato", title = "URL do currículo do candidato", example = "www.viniciusgabriel.com.br/curriculo")
    private String urlCurriculum;

    @Schema(name = "URL da foto de perfil do candidato", title = "URL da foto de perfil do candidato", example = "www.viniciusgabriel.com.br/foto-perfil")
    private String urlFotoPerfil;

}

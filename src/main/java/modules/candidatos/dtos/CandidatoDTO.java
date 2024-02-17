package modules.candidatos.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.infra.entities.Usuario;

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

    private UUID id;

    @NotNull(message = "O candidato deve estar associado a um usuário")
    private UsuarioDTO usuario;

    private String habilidades;

    private String linkedin;

    private String github;

    private String portfolio;

    @NotNull(message = "A disponibilidade do candidato deve ser informada")
    @Size(max = 40, message = "A disponibilidade do candidato deve ter no máximo 40 caracteres")
    private String disponibilidade;

    @NotNull(message = "A pretensão salarial do candidato deve ser informada")
    private BigDecimal pretensaoSalarial;

    private String urlCurriculum;

    private String urlFotoPerfil;

}

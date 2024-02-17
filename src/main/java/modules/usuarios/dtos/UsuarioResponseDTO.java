package modules.usuarios.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class UsuarioResponseDTO {

    private UUID id;

    private String nome;

    private String email;

    private String telefone;

    private Integer tipo;

    private Integer status;

    private Timestamp registro;

    private EnderecoResponseDTO endereco;

}

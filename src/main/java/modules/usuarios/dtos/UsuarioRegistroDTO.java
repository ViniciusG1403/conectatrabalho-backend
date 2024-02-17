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
 * @since 15/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class UsuarioRegistroDTO {

    @NotNull(message = "O nome não pode ser nulo")
    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @Size(min = 3, max = 60, message = "O email deve ter entre 3 e 60 caracteres")
    private String email;

    @NotNull(message = "O telefone não pode ser nulo")
    @Size(min = 3, max = 20, message = "O telefone deve ter entre 3 e 20 caracteres")
    private String telefone;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 3, max = 20, message = "A senha deve ter entre 3 e 20 caracteres")
    private String senha;

    @NotNull(message = "O tipo não pode ser nulo")
    @Min(value = 0, message = "O tipo deve ser 0 - CANDIDATO ou 1 - EMPRESA")
    @Max(value = 1, message = "O tipo deve ser 0 - CANDIDATO ou 1 - EMPRESA")
    private Integer tipo;

}

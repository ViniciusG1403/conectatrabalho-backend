package modules.usuarios.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

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
    @Schema(name = "nome", required = true, title = "Nome do usuário", example = "Vinicius Gabriel")
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @Size(min = 3, max = 60, message = "O email deve ter entre 3 e 60 caracteres")
    @Schema(name = "email", required = true, title = "Email do usuário", example = "vinicius@email.com")
    private String email;

    @NotNull(message = "O telefone não pode ser nulo")
    @Size(min = 3, max = 20, message = "O telefone deve ter entre 3 e 20 caracteres")
    @Schema(name = "telefone", required = true, title = "Telefone do usuário", example = "11999999999")
    private String telefone;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 3, max = 20, message = "A senha deve ter entre 3 e 20 caracteres")
    @Schema(name = "senha", required = true, title = "Senha do usuário", example = "123456")
    private String senha;

    @NotNull(message = "O tipo não pode ser nulo")
    @Min(value = 0, message = "O tipo deve ser 0 - CANDIDATO ou 1 - EMPRESA")
    @Max(value = 1, message = "O tipo deve ser 0 - CANDIDATO ou 1 - EMPRESA")
    @Schema(name = "tipo", required = true, title = "Tipo do usuário", example = "0 - CANDIDATO, 1 - EMPRESA")
    private Integer tipo;

    @NotNull(message = "O endereço não pode ser nulo")
    @Schema(name = "endereco", required = true, title = "Endereço do usuário")
    private EnderecoDTO endereco;


}

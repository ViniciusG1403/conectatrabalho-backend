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
 * @since 13/02/24
 */
@Getter
@Setter
@RegisterForReflection
public class UsuarioDTO {

    @Schema(name = "ID", required = true, title = "ID do usuário", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @NotNull(message = "O nome não pode ser nulo")
    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
    @Schema(name = "Nome do usuário", required = true, title = "Nome do usuário", example = "Vinicius Gabriel")
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @Size(min = 3, max = 60, message = "O email deve ter entre 3 e 60 caracteres")
    @Schema(name = "Email do usuário", required = true, title = "Email do usuário", example = "vinicius@email.com")
    private String email;

    @NotNull(message = "O telefone não pode ser nulo")
    @Size(min = 3, max = 20, message = "O telefone deve ter entre 3 e 20 caracteres")
    @Schema(name = "Telefone do usuário", required = true, title = "Telefone do usuário", example = "11999999999")
    private String telefone;

    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 3, max = 20, message = "A senha deve ter entre 3 e 20 caracteres")
    @Schema(name = "Senha do usuário", required = true, title = "Senha do usuário", example = "123456")
    private String senha;

    @NotNull(message = "O tipo não pode ser nulo")
    @Min(value = 0, message = "O tipo deve ser 0 - CANDIDATO ou 1 - EMPRESA")
    @Max(value = 1, message = "O tipo deve ser 0 - CANDIDATO ou 1 - EMPRESA")
    @Schema(name = "Tipo do usuário", required = true, title = "Tipo do usuário", example = "0 - CANDIDATO, 1 - EMPRESA")
    private Integer tipo;

    @NotNull(message = "O status não pode ser nulo")
    @Min(value = 0, message = "O status deve ser 0 - INATIVO, 1 - ATIVO, 2 - BLOQUEADO ou 3 - EXCLUIDO")
    @Max(value = 3, message = "O status deve ser 0 - INATIVO, 1 - ATIVO, 2 - BLOQUEADO ou 3 - EXCLUIDO")
    @Schema(name = "Status do usuário", required = true, title = "Status do usuário", example = "0 - INATIVO, 1 - ATIVO, 2 - BLOQUEADO, 3 - EXCLUIDO")
    private Integer status;

    @Schema(name = "Data de registro", required = true, title = "Data de registro do usuário", example = "2024-02-13 00:00:00")
    private Timestamp registro;

    @Schema(name = "Última atualização", required = true, title = "Última atualização do usuário", example = "2024-02-13 00:00:00")
    private Timestamp ultimaAtualizacao;

    @Size(min = 6, max = 6, message = "O código deve ter 6 caracteres")
    @Schema(name = "Código de verificação", title = "Código de verificação do usuário", example = "123456")
    private String codigo;

    @NotNull(message = "O endereço não pode ser nulo")
    @Schema(name = "Endereço do usuário", required = true, title = "Endereço do usuário")
    private EnderecoDTO endereco;

}

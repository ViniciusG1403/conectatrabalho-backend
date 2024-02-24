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
 * @since 17/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class UsuarioResponseDTO {

    @Schema(name = "ID", required = true, title = "ID do usuário", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(name = "Nome do usuário", required = true, title = "Nome do usuário", example = "Vinicius Gabriel")
    private String nome;

    @Schema(name = "Email do usuário", required = true, title = "Email do usuário", example = "vinicius@email.com")
    private String email;

    @Schema(name = "Telefone do usuário", required = true, title = "Telefone do usuário", example = "11999999999")
    private String telefone;

    @Schema(name = "Tipo do usuário", required = true, title = "Tipo do usuário", example = "0 - CANDIDATO, 1 - EMPRESA")
    private Integer tipo;

    @Schema(name = "Status do usuário", required = true, title = "Status do usuário", example = "0 - INATIVO, 1 - ATIVO, 2 - BLOQUEADO, 3 - EXCLUIDO")
    private Integer status;

    @Schema(name = "Data de registro", required = true, title = "Data de registro do usuário", example = "2024-02-13 00:00:00")
    private Timestamp registro;

    @Schema(name = "Endereço do usuário", required = true, title = "Endereço do usuário")
    private EnderecoResponseDTO endereco;

}

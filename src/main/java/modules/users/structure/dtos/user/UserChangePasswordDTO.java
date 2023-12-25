package modules.users.structure.dtos.user;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
@Getter
@Setter
@RegisterForReflection
public class UserChangePasswordDTO {

    private String id;

    @NotNull(message = "A senha é obrigatória")
    @Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
    @Schema(description = "Senha do usuário", required = true, example = "123456@JOAO")
    private String password;

    @NotNull(message = "A senha antiga é obrigatória")
    @Size(min = 6, max = 20, message = "A senha antiga deve ter entre 6 e 20 caracteres")
    @Schema(description = "Senha antiga do usuário", required = true, example = "123456@JOAO")
    private String oldPassword;

    @NotNull(message = "O código é obrigatório")
    @Size(min = 6, max = 6, message = "O código deve ter 6 caracteres")
    @Schema(description = "Código de verificação do usuário", required = true, example = "12A45B")
    private String code;
}

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
 * @since 27/12/23
 */
@Getter
@Setter
@RegisterForReflection
public class UserActiveDTO {
    @NotNull(message = "O código é obrigatório")
    @Size(min = 6, max = 6, message = "O código deve ter 6 caracteres")
    @Schema(description = "Código de verificação do usuário", required = true, example = "12A45B")
    private String code;
}

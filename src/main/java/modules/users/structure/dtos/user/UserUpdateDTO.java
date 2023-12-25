package modules.users.structure.dtos.user;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDate;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
@Getter
@Setter
@RegisterForReflection
public class UserUpdateDTO {

    private String id;

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
    @Schema(description = "Nome do usuário", required = true, example = "João dos Santos")
    private String name;

    @NotNull(message = "O email é obrigatório")
    @Size(min = 5, max = 60, message = "O email deve ter entre 5 e 60 caracteres")
    @Schema(description = "Email do usuário", required = true, example = "joaosantos@email.com")
    private String email;
}

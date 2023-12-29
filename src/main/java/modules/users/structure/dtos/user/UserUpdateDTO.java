package modules.users.structure.dtos.user;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import modules.users.structure.dtos.localization.LocalizationDTO;
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

    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
    @Schema(description = "Nome do usuário", required = true, example = "João dos Santos")
    private String name;

    @Size(min = 5, max = 60, message = "O email deve ter entre 5 e 60 caracteres")
    @Schema(description = "Email do usuário", required = true, example = "joaosantos@email.com")
    private String email;

    @Schema(description = "Endereço do usuário ", required = true)
    private LocalizationDTO localization;

    @NotNull(message = "O código é obrigatório")
    @Size(min = 6, max = 6, message = "O código deve ter 6 caracteres")
    @Schema(description = "Código de verificação do usuário", required = true, example = "12A45B")
    private String code;
}

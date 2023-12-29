package modules.users.structure.dtos.localization;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import modules.users.structure.dtos.user.UserDTO;
import modules.users.structure.entities.User;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/12/23
 */
@Getter
@Setter
@RegisterForReflection
public class LocalizationDTO {

    private String id;

    private UserDTO user;

    @NotNull(message = "O cep é obrigatório")
    private String cep;

    @NotNull(message = "A rua é obrigatória")
    private String street;

    @NotNull(message = "O número é obrigatório")
    private String number;

    private String complement;

    @NotNull(message = "O bairro é obrigatório")
    private String neighborhood;

    @NotNull(message = "A cidade é obrigatória")
    private String city;

    @NotNull(message = "O estado é obrigatório")
    private String state;

}

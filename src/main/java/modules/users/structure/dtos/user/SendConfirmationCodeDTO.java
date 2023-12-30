package modules.users.structure.dtos.user;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 29/12/23
 */
@Getter
@Setter
@RegisterForReflection
public class SendConfirmationCodeDTO {

    String id;

    @NotNull(message = "O assunto é obrigatório")
    String subject;

}

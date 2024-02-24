package modules.login.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/11/23
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthRequest {

    @Schema(name = "email", required = true, title = "Email do usuário", example = "mail@email.com")
    public String email;

    @Schema(name = "senha", required = true, title = "Senha do usuário", example = "123456")
    public String senha;

}

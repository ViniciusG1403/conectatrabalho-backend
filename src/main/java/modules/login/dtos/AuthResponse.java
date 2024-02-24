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
public class AuthResponse {

    @Schema(name = "token", required = true, title = "Token de autenticação", example = "token")
    public String token;
}

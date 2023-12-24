package modules.login.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/11/23
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResponse {

    public String token;
}

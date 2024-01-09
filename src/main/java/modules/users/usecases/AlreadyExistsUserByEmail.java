package modules.users.usecases;

import jakarta.enterprise.context.RequestScoped;
import lombok.RequiredArgsConstructor;
import modules.users.structure.entities.User;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 05/01/24
 */
@RequestScoped
public class AlreadyExistsUserByEmail {

    public boolean execute(final String email) {
        return User.find("email", email).stream().anyMatch(user -> true);
    }

}

package modules.users.usecases;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Random;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/12/23
 */
@ApplicationScoped
public class GenerateRandomCode {

    public String execute() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(characters.length());

            code.append(characters.charAt(randomIndex));
        }

        return code.toString();
    }

}

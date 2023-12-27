package core.emailservice;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import modules.users.structure.dtos.user.UserDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/12/23
 */
@QuarkusTest
@DisplayName("Teste unitário da classe para envio de email")
class SendEmailServiceTest {

    @Inject
    SendEmailService service;

    @Test
    @DisplayName("Deve enviar um email")
    void shouldBeSendAnEmail() {
        UserDTO dto = new UserDTO();
        dto.setName("User Test");
        dto.setEmail("tobeho9832@ubinert.com");
        dto.setCode("123134");

        service.sendMail(dto, "Ativação de usuário", MessageOperation.ATIVACAO);
    }

}
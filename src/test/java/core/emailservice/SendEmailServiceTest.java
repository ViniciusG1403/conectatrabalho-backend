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
    @DisplayName("Deve enviar um email de ativação")
    void shouldBeSendAnActivationEmail() {
        UserDTO dto = new UserDTO();
        dto.setName("User Test");
        dto.setEmail("nofog91872@vasteron.com");
        dto.setCode("123134");

        service.sendMail(dto, "Ativação de usuário", MessageOperation.ATIVACAO);
    }

    @Test
    @DisplayName("Deve enviar um email de troca de senha")
    void shouldBeSendAnChangePasswordEmail() {
        UserDTO dto = new UserDTO();
        dto.setName("User Test");
        dto.setEmail("nofog91872@vasteron.com");
        dto.setCode("123134");

        service.sendMail(dto, "Alteração de senha", MessageOperation.TROCA_SENHA);
    }

    @Test
    @DisplayName("Deve enviar um email de atualização de usuário")
    void shouldBeSendAnUserUpdateEmail() {
        UserDTO dto = new UserDTO();
        dto.setName("User Test");
        dto.setEmail("nofog91872@vasteron.com");
        dto.setCode("123134");

        service.sendMail(dto, "Atualização de dados", MessageOperation.ATUALIZACAO);
    }

}
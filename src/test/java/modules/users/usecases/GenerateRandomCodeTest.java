package modules.users.usecases;

import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/12/23
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Teste unitário da classe para gerar código aleatório")
class GenerateRandomCodeTest {

    @InjectMocks
    GenerateRandomCode generateRandomCode;

    @Test
    @DisplayName("Deve retornar um código aleatório")
    void shouldBeReturnARandomCode() {
        final String code = generateRandomCode.execute();

        assertNotNull(code);
        assertEquals(6, code.length());
    }

}
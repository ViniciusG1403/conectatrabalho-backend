package core.encoder;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
@QuarkusTest
@DisplayName("Teste unitário da classe para criptografar string")
class PBKDF2EncoderTest {

    @Inject
    PBKDF2Encoder encoder;

    @Test
    @DisplayName("Deve retornar uma string criptografada")
    void shouldBeReturnAEncodedString() {
        final var string = "123456";

        final String encodedString = encoder.encode(string);

        assertNotNull(encodedString);
        assertNotEquals(string, encodedString);
    }

}
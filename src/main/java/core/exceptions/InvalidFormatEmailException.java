package core.exceptions;

import jakarta.validation.ValidationException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/12/23
 */
public class InvalidFormatEmailException extends ValidationException {

    public InvalidFormatEmailException() {
        super("O campo email não está em um formato válido");
    }

}

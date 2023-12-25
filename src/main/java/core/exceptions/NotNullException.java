package core.exceptions;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/12/23
 */
public class NotNullException extends NullPointerException {

    public NotNullException(final String field) {
        super("O campo " + field + " não pode ser nulo");
    }


}

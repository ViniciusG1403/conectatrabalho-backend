package modules.vagas.exceptions;

import jakarta.ws.rs.NotFoundException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 27/02/2024
 */
public class VagaNaoEncontradaException extends NotFoundException {

    public VagaNaoEncontradaException() {
        super("Vaga não encontrada");
    }

    public VagaNaoEncontradaException(String message) {
        super(message);
    }

    public VagaNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

    public VagaNaoEncontradaException(Throwable cause) {
        super(cause);
    }

}

package modules.empresa.exceptions;

import jakarta.ws.rs.NotFoundException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
public class EmpresaNaoEncontradoException extends NotFoundException {

    public EmpresaNaoEncontradoException() {
        super("Empresa não encontrado");
    }

}

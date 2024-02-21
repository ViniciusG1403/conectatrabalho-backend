package modules.contratante.exceptions;

import jakarta.ws.rs.NotFoundException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
public class ContratanteNaoEncontradoException extends NotFoundException {

    public ContratanteNaoEncontradoException() {
        super("Contratante não encontrado");
    }

}

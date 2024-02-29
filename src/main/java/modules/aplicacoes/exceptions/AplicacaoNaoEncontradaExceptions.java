package modules.aplicacoes.exceptions;

import jakarta.ws.rs.NotFoundException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
public class AplicacaoNaoEncontradaExceptions extends NotFoundException {

    public AplicacaoNaoEncontradaExceptions() {
        super("Aplicação não encontrada");
    }


}

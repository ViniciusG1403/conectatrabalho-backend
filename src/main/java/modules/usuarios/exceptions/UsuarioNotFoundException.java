package modules.usuarios.exceptions;

import jakarta.ws.rs.NotFoundException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 13/02/24
 */
public class UsuarioNotFoundException extends NotFoundException {

    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }

}

package modules.usuarios.exceptions;

import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 13/02/24
 */
public class UsuarioExistenteComMesmoEmail extends ValidationException {

    public UsuarioExistenteComMesmoEmail() {
        super("Usuário já existente com o mesmo email");
    }

}

package modules.candidatos.usecases;

import core.shared.RecuperarImagemPerfil;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarImagemPerfilCandidato {

    private final RecuperarImagemPerfil recuperarImagemPerfil;

    public InputStream execute(String id) {
        return recuperarImagemPerfil.execute(id);
    }


}

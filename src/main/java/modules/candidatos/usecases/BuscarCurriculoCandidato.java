package modules.candidatos.usecases;

import core.shared.RecuperarCurriculoService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarCurriculoCandidato {

    private final RecuperarCurriculoService recuperarCurriculoService;

    public ResponseInputStream<GetObjectResponse> execute(String id) {
        return recuperarCurriculoService.execute(id);
    }

}

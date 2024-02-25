package modules.candidatos.usecases;

import core.shared.SalvarCurriculoService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class SalvarCurriculoCandidato {

    final private SalvarCurriculoService salvarCurriculoService;

    public void execute(MultipartFormDataInput input) {
        try {
            final String id = getBodyAsString(input);
            salvarCurriculoService.execute(input, id);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar curriculo do candidato");
        }
    }

    private String getBodyAsString(MultipartFormDataInput input) throws IOException {
        return input.getFormDataMap().get("id").get(0).getBodyAsString();
    }
}

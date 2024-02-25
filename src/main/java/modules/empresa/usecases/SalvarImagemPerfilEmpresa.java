package modules.empresa.usecases;

import core.shared.ProcessarESalvarImageService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class SalvarImagemPerfilEmpresa {

    private final ProcessarESalvarImageService processarESalvarImageService;

    public void execute(MultipartFormDataInput input) {
        try {
            final String id = getBodyAsString(input);

            processarESalvarImageService.execute(input, id);

        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem de perfil da empresa");
        }
    }

    private String getBodyAsString(MultipartFormDataInput input) throws IOException {
        return input.getFormDataMap().get("id").get(0).getBodyAsString();
    }

}

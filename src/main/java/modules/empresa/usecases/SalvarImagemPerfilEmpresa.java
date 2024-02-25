package modules.empresa.usecases;

import core.shared.ProcessImageService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.empresa.exceptions.EmpresaNaoEncontradoException;
import modules.empresa.infra.entities.Empresa;
import modules.empresa.repositories.EmpresaRepository;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class SalvarImagemPerfilEmpresa {

    private final ProcessImageService processImageService;

    private final EmpresaRepository empresaRepository;

    public void execute(MultipartFormDataInput input) {
        try {
            final String id = getBodyAsString(input, "id");

            final String urlImageSaved = processImageService.execute(input, id);

            Empresa empresa = empresaRepository.findById(UUID.fromString(id))
                .orElseThrow(EmpresaNaoEncontradoException::new);

            empresa.setUrlFotoPerfil(urlImageSaved);

            empresaRepository.update(empresa);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem de perfil da empresa");
        }
    }

    private String getBodyAsString(MultipartFormDataInput input, String field) throws IOException {
        return input.getFormDataMap().get(field).get(0).getBodyAsString();
    }

}

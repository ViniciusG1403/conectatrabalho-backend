package modules.candidatos.usecases;

import core.shared.ProcessImageService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.exceptions.CandidatoNaoEncontradoException;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class SalvarImagemPerfilCandidato {

    final private CandidatoRepository candidatoRepository;

    final private ProcessImageService processImageService;

    public void execute(MultipartFormDataInput input) {
        try {
            final String id = getBodyAsString(input);

            final String urlImageSaved = processImageService.execute(input, id);

            Candidato candidato = candidatoRepository.findById(UUID.fromString(id))
                .orElseThrow(CandidatoNaoEncontradoException::new);

            candidato.setUrlFotoPerfil(urlImageSaved);

            candidatoRepository.update(candidato);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem de perfil do candidato");
        }
    }

    private String getBodyAsString(MultipartFormDataInput input) throws IOException {
        return input.getFormDataMap().get("id").get(0).getBodyAsString();
    }

}

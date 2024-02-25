package core.shared;

import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/02/24
 */
@ApplicationScoped
public class SalvarCurriculoService {

    public void execute(MultipartFormDataInput input, String id) {
        try {
            Map<String, java.util.List<InputPart>> uploadForm = input.getFormDataMap();
            List<InputPart> inputParts = uploadForm.get("file");

            for (InputPart inputPart : inputParts) {
                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                S3Client s3Client = S3Client.builder()
                    .region(Region.SA_EAST_1)
                    .build();

                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket("curriculosperfil-conectatrabalho")
                    .key(id + "-curriculo.pdf")
                    .build();

                s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(inputStream, inputStream.available()));
            }
        } catch (Exception e){
            throw new RuntimeException("Falha ao enviar o arquivo para o S3.");
        }
    }
}

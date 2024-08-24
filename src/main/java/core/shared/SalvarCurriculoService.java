package core.shared;

import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
            Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
            List<InputPart> inputParts = uploadForm.get("file");

            for (InputPart inputPart : inputParts) {
                InputStream inputStream = inputPart.getBody(InputStream.class, null);

                byte[] fileBytes = readInputStream(inputStream);

                S3Client s3Client = S3Client.builder()
                        .region(Region.US_EAST_1)
                        .build();

                PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                        .bucket("conecta-trabalho")
                        .key(id + "-curriculo.pdf")
                        .build();

                s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileBytes));
            }
        } catch (Exception e){
            throw new RuntimeException("Falha ao enviar o arquivo para o S3.", e);
        }
    }

    private byte[] readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

}

package core.shared;

import jakarta.enterprise.context.ApplicationScoped;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
@ApplicationScoped
public class RecuperarCurriculoService {

    public ResponseInputStream<GetObjectResponse> execute(String id) {
        S3Client s3Client = S3Client.builder()
            .region(Region.US_EAST_1)
            .build();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket("conecta-trabalho")
            .key(id + "-curriculo.pdf")
            .build();

        return s3Client.getObject(getObjectRequest);
    }

}

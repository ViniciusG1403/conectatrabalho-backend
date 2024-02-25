package core.shared;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.InputStream;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
@ApplicationScoped
@Unremovable
public class RecuperarImagemPerfil {
    private final String bucketName = "imagensperfil-conectatrabalho";

    public InputStream execute(String id) {
        S3Client s3Client = S3Client.builder()
            .region(Region.SA_EAST_1)
            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
            .build();

        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
            .bucket(bucketName)
            .key(id + "imagemperfil.jpg")
            .build();

        try {
            ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(
                getObjectRequest);
            return objectBytes.asInputStream();
        } catch (Exception e) {
            throw new WebApplicationException("Falha ao baixar o arquivo do S3.",
                Response.Status.INTERNAL_SERVER_ERROR);
        } finally {
            s3Client.close();
        }
    }
}

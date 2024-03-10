package core.shared;

import io.quarkus.arc.Unremovable;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;

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
            return null;
        } finally {
            s3Client.close();
        }
    }

    public String buscarUrl(String id) {
        S3Client s3Client = S3Client.builder()
                .region(Region.SA_EAST_1)
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();

        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(id + "imagemperfil.jpg")
                    .build();

            HeadObjectResponse headObjectResponse = s3Client.headObject(headObjectRequest);

            GetObjectRequest objectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(id + "imagemperfil.jpg")
                    .build();

            try (S3Presigner presigner = S3Presigner.create()) {
                GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                        .signatureDuration(Duration.ofMinutes(60))
                        .getObjectRequest(objectRequest)
                        .build();

                PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
                return presignedRequest.url().toString();
            }

        } catch (NoSuchKeyException e) {
            return null;
        } catch (S3Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            s3Client.close();
        }
    }
}

package core.shared;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ValidationException;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/02/24
 */
@ApplicationScoped
public class ProcessarESalvarImageService {

    public String execute(MultipartFormDataInput data, String id) {

        try {
            InputPart inputPart = data.getFormDataMap().get("file").get(0);


            InputStream inputStream = inputPart.getBody(InputStream.class, null);
            BufferedImage originalImage = ImageIO.read(inputStream);

            Image scaledImage = originalImage.getScaledInstance(600, 600, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(600, 600, BufferedImage.TYPE_INT_RGB);
            resizedImage.getGraphics().drawImage(scaledImage, 0, 0, null);

            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "png", os);
            byte[] buffer = os.toByteArray();

            S3Client s3 = S3Client.builder().region(Region.SA_EAST_1).build();

            String bucketName = "imagensperfil-conectatrabalho";
            String key = id + "imagemperfil.png";
            PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(key).build();
            s3.putObject(putObjectRequest, RequestBody.fromBytes(buffer));

            S3Presigner presigner = S3Presigner.builder().region(Region.US_EAST_1).build();
            GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(key).build();
            GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(getObjectRequest)
                .build();
            URL url = presigner.presignGetObject(getObjectPresignRequest).url();

            return url.toString();
        } catch (IOException e) {
            throw new ValidationException("Invalid image data", e);
        }
    }


}

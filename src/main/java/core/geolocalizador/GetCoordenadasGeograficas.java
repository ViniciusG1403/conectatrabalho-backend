package core.geolocalizador;

import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageLatLng;
import com.opencagedata.jopencage.model.JOpenCageResponse;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 12/03/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class GetCoordenadasGeograficas {
    public CoordenadasGeograficasDTO execute(String endereco) {
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("292f183f3ba14202894adc3efbc1ad1c");
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(endereco);
        request.setRestrictToCountryCode("br");

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        JOpenCageLatLng firstResultLatLng = response.getFirstPosition();
        return CoordenadasGeograficasDTO.builder().latitude(firstResultLatLng.getLat().toString()).longitude(firstResultLatLng.getLng().toString()).build();
    }

}

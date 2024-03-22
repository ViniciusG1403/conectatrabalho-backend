package core.geolocalizador;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 12/03/24
 */
@Getter
@Builder
public class CoordenadasGeograficasDTO {

    private Double latitude;

    private Double longitude;

}

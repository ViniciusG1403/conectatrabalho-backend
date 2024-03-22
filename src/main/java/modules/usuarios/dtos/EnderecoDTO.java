package modules.usuarios.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class EnderecoDTO {

    @Schema(name = "idUsuario", required = true, title = "ID do usuário", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID idUsuario;

    @Schema(name = "cep", required = true, title = "CEP do endereço", example = "12345678")
    private String cep;

    @Schema(name = "estado", required = true, title = "Estado do endereço", example = "SP")
    private String estado;

    @Schema(name = "pais", required = true, title = "País do endereço", example = "Brasil")
    private String pais;

    @Schema(name = "municipio", required = true, title = "Município do endereço", example = "São Paulo")
    private String municipio;

    @Schema(name = "bairro", required = true, title = "Bairro do endereço", example = "Vila Mariana")
    private String bairro;

    @Schema(name = "logradouro", required = true, title = "Logradouro do endereço", example = "Rua Domingos de Morais")
    private String logradouro;

    @Schema(name = "numero", required = true, title = "Número do endereço", example = "123")
    private String numero;

    @Schema(name = "complemento", title = "Complemento do endereço", example = "Apto 123")
    private String complemento;

    @Schema(name = "latitude", title = "Latitude do endereço", example = "-23.567")
    private Double latitude;

    @Schema(name = "longitude", title = "Longitude do endereço", example = "-46.789")
    private Double longitude;

}

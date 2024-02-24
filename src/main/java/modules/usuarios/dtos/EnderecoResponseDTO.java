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
public class EnderecoResponseDTO {

    @Schema(name = "ID", required = true, title = "ID do endereço", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(name = "CEP", required = true, title = "CEP do endereço", example = "12345-678")
    private String cep;

    @Schema(name = "Estado", required = true, title = "Estado do endereço", example = "São Paulo")
    private String estado;

    @Schema(name = "País", required = true, title = "País do endereço", example = "Brasil")
    private String pais;

    @Schema(name = "Município", required = true, title = "Município do endereço", example = "São Paulo")
    private String municipio;

    @Schema(name = "Bairro", required = true, title = "Bairro do endereço", example = "Vila Mariana")
    private String bairro;

    @Schema(name = "Logradouro", required = true, title = "Logradouro do endereço", example = "Rua Domingos de Morais")
    private String logradouro;

    @Schema(name = "Número", required = true, title = "Número do endereço", example = "123")
    private String numero;

    @Schema(name = "Complemento", title = "Complemento do endereço", example = "Apto 123")
    private String complemento;
}

package modules.usuarios.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

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

    private UUID id;

    private String cep;

    private String estado;

    private String pais;

    private String municipio;

    private String bairro;

    private String logradouro;

    private String numero;

    private String complemento;
}

package modules.vagas.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class VagasResumidoDTO {

    public UUID id;

    public String titulo;

    public String descricao;

    public String nivel;

    public Integer status;

    public String empresa;


}

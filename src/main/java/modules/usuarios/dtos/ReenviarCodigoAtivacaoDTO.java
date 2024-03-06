package modules.usuarios.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 05/03/2024
 */
@Getter
@Setter
@RegisterForReflection
public class ReenviarCodigoAtivacaoDTO {


    private String email;

}

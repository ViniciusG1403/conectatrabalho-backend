package modules.empresa.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 21/02/24
 */
@Getter
@Setter
@RegisterForReflection
public class EmpresaResumidoDTO {

    private String empresa;

    private String descricao;

    private String setor;

    private String urlFotoPerfil;

    private String cidadeEstado;
}

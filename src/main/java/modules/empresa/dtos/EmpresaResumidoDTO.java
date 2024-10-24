package modules.empresa.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 21/02/24
 */
@Getter
@Setter
@RegisterForReflection
public class EmpresaResumidoDTO {

    @Schema(name = "id", required = true, title = "Identificador")
    private String id;

    @Schema(name = "nome", required = true, title = "nome", example = "Prado Softwares LTDA")
    private String nome;

    @Schema(name = "descricao", required = true, title = "descricao", example = "Empresa de desenvolvimento de software")
    private String descricao;

    @Schema(name = "setor", required = true, title = "setor", example = "Tecnologia")
    private String setor;

    @Schema(name = "cidadeEstado", required = true, title = "Cidade e estado da empresa", example = "São Paulo - SP")
    private String cidadeEstado;

}

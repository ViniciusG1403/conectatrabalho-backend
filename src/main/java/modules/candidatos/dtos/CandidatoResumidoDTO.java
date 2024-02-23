package modules.candidatos.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 22/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class CandidatoResumidoDTO {


    private String nome;

    private String habilidades;

    private String urlFotoPerfil;

    private String cidadeEstado;

}

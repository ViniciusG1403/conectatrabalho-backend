package modules.candidatos.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.dtos.EnderecoDTO;

import java.math.BigDecimal;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 18/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class CandidatoResponseDTO {

    private String nome;

    private String email;

    private String telefone;

    private String habilidades;

    private String linkedin;

    private String github;

    private String portfolio;

    private String disponibilidade;

    private BigDecimal pretensaoSalarial;

    private String urlCurriculum;

    private String urlFotoPerfil;

    private EnderecoDTO endereco;

}

package modules.contratante.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.dtos.EnderecoDTO;
import modules.usuarios.dtos.EnderecoResponseDTO;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class ContratanteResponseDTO {

    private UUID id;

    private String nome;

    private String email;

    private String telefone;

    private String empresa;

    private String setor;

    private String descricao;

    private String website;

    private String linkedin;

    private String urlFotoPerfil;

    private EnderecoResponseDTO endereco;

}

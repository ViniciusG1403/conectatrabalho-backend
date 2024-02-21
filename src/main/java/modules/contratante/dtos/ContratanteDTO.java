package modules.contratante.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.dtos.UsuarioDTO;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@Getter
@Setter
@RegisterForReflection
public class ContratanteDTO {

    private UUID id;

    @NotNull(message = "Usuário não pode ser nulo")
    private UsuarioDTO usuario;

    @NotNull(message = "Nome da empresa não pode ser nula")
    @Size(min = 0, max = 100, message = "Nome da empresa deve ter entre 0 e 100 caracteres")
    private String empresa;

    @NotNull(message = "Setor não pode ser nulo")
    @Size(min = 0, max = 100, message = "Setor deve ter entre 0 e 100 caracteres")
    private String setor;

    @NotNull(message = "Descrição não pode ser nula")
    private String descricao;

    private String website;

    private String linkedin;

    private String urlFotoPerfil;

}

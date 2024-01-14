package modules.users.structure.dtos.contractorprofile;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import modules.users.structure.dtos.user.UserDTO;
import modules.users.structure.entities.User;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
@Getter
@Setter
@RegisterForReflection
public class ContractorProfileDTO {

    private UUID id;

    @NotNull(message = "Usuário é obrigatório")
    @Schema(description = "Usuário do perfil", required = true)
    private UserDTO user;

    @NotNull(message = "A empresa é obrigatória")
    @Schema(description = "Empresa do usuário", required = true, example = "Construtora LTDA")
    private String enterprise;

    @NotNull(message = "O setor é obrigatório")
    @Schema(description = "Setor do usuário", required = true, example = "Construção Civil")
    private String sector;

    @NotNull(message = "A descrição é obrigatória")
    @Schema(description = "Descrição do usuário", required = true, example = "Construtora LTDA")
    private String description;

    @Schema(description = "Website do usuário", required = true, example = "www.construtora.com.br")
    private String website;

    @Schema(description = "Imagem de perfil do usuário", required = true, example = "www.construtora.com.br")
    private String imgProfileUrl;
}

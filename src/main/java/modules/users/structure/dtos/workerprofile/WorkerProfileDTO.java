package modules.users.structure.dtos.workerprofile;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
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
public class WorkerProfileDTO {

    private UUID id;

    @NotNull(message = "Usuário é obrigatório")
    @Schema(description = "Usuário do perfil", required = true)
    private User user;

    @NotNull(message = "A profissão é obrigatória")
    @Schema(description = "Profissão do usuário", required = true, example = "Pedreiro")
    private String profession;

    @NotNull(message = "O setor é obrigatório")
    @Schema(description = "Setor do usuário", required = true, example = "Construção Civil")
    private String sector;

    @NotNull(message = "A localização é obrigatória")
    @Schema(description = "Localização do usuário", required = true, example = "Rua dos Bobos, 0 - Centro")
    private String localization;

    @NotNull(message = "As habilidades são obrigatórias")
    @Schema(description = "Habilidades do usuário", required = true, example = "Pedreiro, Pintor, Eletricista")
    private String habilities;

    @Schema(description = "Currículo do usuário", required = true, example = "www.construtora.com.br")
    private String curriculumUrl;

    @Schema(description = "Portfólio do usuário", required = true, example = "www.construtora.com.br")
    private String portfolioUrl;
}

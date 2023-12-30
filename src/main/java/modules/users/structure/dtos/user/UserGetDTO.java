package modules.users.structure.dtos.user;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import modules.users.structure.dtos.localization.LocalizationDTO;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.sql.Timestamp;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
@Getter
@Setter
@RegisterForReflection
public class UserGetDTO {
    private String id;

    @NotNull(message = "O nome é obrigatório")
    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres")
    @Schema(description = "Nome do usuário", required = true, example = "João dos Santos")
    private String name;

    @NotNull(message = "O email é obrigatório")
    @Size(min = 5, max = 60, message = "O email deve ter entre 5 e 60 caracteres")
    @Schema(description = "Email do usuário", required = true, example = "joaosantos@email.com")
    private String email;

    @NotNull(message = "A data de registro é obrigatória")
    @Schema(description = "Data de registro do usuário", required = true, example = "2024-01-01")
    private Timestamp dhRegister;

    @NotNull(message = "O status é obrigatório")
    @Min(value = 0, message = "O Status do usuário deve ser 0 - Inativo ou 1 - Ativo")
    @Max(value = 1, message = "O Status do usuário deve ser 0 - Inativo ou 1 - Ativo")
    @Schema(description = "Status do usuário", required = true, example = "1 - ATIVO")
    private Integer status;

    @Schema(description = "Data do último login do usuário", required = true, example = "2024-01-01")
    private Timestamp lastLogin;

    @Schema(description = "Data da última atualização do usuário", required = true, example = "2024-01-01")
    private Timestamp lastUpdate;

    @Schema(description = "Regra do usuário", required = true, example = "0 - BASICO")
    private Integer role;

    @NotNull(message = "O tipo de usuário é obrigatório")
    @Min(value = 0, message = "O Tipo do usuário deve ser 0 - Contratante, 1 - Prestador, 2 - Ambos, 3 - Administrador")
    @Max(value = 3, message = "O Tipo do usuário deve ser 0 - Contratante, 1 - Prestador, 2 - Ambos, 3 - Administrador")
    @Schema(description = "Tipo do usuário", required = true, example = "0 - CONTRATANTE")
    private Integer type;

    private LocalizationDTO localization;
}

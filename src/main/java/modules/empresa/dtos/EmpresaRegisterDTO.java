package modules.empresa.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */

@Getter
@Setter
@RegisterForReflection
public class EmpresaRegisterDTO {

    @Schema(name = "idUsuario", required = true, title = "ID do usuário", example = "123e4567-e89b-12d3-a456-426614174000")
    @NotNull(message = "Usuário não pode ser nulo")
    private UUID idUsuario;

    @NotNull(message = "Setor não pode ser nulo")
    @Size(min = 0, max = 100, message = "Setor deve ter entre 0 e 100 caracteres")
    @Schema(name = "setor", required = true, title = "Setor da empresa", example = "Tecnologia")
    private String setor;

    @NotNull(message = "Descrição não pode ser nula")
    @Schema(name = "descricao", required = true, title = "Descrição da empresa", example = "Empresa de desenvolvimento de software")
    private String descricao;

    @Schema(name = "website", title = "Website da empresa", example = "www.pradosoftwares.com.br")
    private String website;

    @Schema(name = "linkedin", title = "LinkedIn da empresa", example = "www.linkedin.com/company/pradosoftwares")
    private String linkedin;

}

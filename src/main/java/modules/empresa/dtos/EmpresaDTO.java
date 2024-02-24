package modules.empresa.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.dtos.UsuarioDTO;
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
public class EmpresaDTO {

    private UUID id;

    @NotNull(message = "Usuário não pode ser nulo")
    @Schema(name = "Usuário", required = true, title = "Usuário referenciado a empresa")
    private UsuarioDTO usuario;

    @NotNull(message = "Nome da empresa não pode ser nula")
    @Size(min = 0, max = 100, message = "Nome da empresa deve ter entre 0 e 100 caracteres")
    @Schema(name = "Nome da empresa", required = true, title = "Nome da empresa", example = "Prado Softwares")
    private String empresa;

    @NotNull(message = "Setor não pode ser nulo")
    @Size(min = 0, max = 100, message = "Setor deve ter entre 0 e 100 caracteres")
    @Schema(name = "Setor da empresa", required = true, title = "Setor da empresa", example = "Tecnologia")
    private String setor;

    @NotNull(message = "Descrição não pode ser nula")
    @Schema(name = "Descrição da empresa", required = true, title = "Descrição da empresa", example = "Empresa de desenvolvimento de software")
    private String descricao;

    @Schema(name = "Website da empresa", title = "Website da empresa", example = "www.pradosoftwares.com.br")
    private String website;

    @Schema(name = "LinkedIn da empresa", title = "LinkedIn da empresa", example = "www.linkedin.com/company/pradosoftwares")
    private String linkedin;

    @Schema(name = "URL da foto de perfil da empresa", title = "URL da foto de perfil da empresa", example = "www.pradosoftwares.com.br/foto-perfil")
    private String urlFotoPerfil;

}
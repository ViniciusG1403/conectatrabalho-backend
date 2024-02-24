package modules.empresa.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.dtos.EnderecoResponseDTO;
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
public class EmpresaResponseDTO {

    @Schema(name = "ID da empresa", required = true, title = "ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(name = "Nome da empresa", required = true, title = "Nome da empresa", example = "Prado Softwares LTDA")
    private String nome;

    @Schema(name = "E-mail da empresa", title = "E-mail da empresa", example = "empresa@empresamail.com")
    private String email;

    @Schema(name = "Telefone da empresa", title = "Telefone da empresa", example = "(11) 99999-9999")
    private String telefone;

    @Schema(name = "Setor da empresa", title = "Setor da empresa", example = "Tecnologia")
    private String setor;

    @Schema(name = "Descrição da empresa", title = "Descrição da empresa", example = "Empresa de desenvolvimento de software")
    private String descricao;

    @Schema(name = "Website da empresa", title = "Website da empresa", example = "www.pradosoftwares.com.br")
    private String website;

    @Schema(name = "LinkedIn da empresa", title = "LinkedIn da empresa", example = "www.linkedin.com/company/pradosoftwares")
    private String linkedin;

    @Schema(name = "URL da foto de perfil da empresa", title = "URL da foto de perfil da empresa", example = "www.pradosoftwares.com.br/foto-perfil")
    private String urlFotoPerfil;

    @Schema(name = "Endereço da empresa", title = "Endereço")
    private EnderecoResponseDTO endereco;

}

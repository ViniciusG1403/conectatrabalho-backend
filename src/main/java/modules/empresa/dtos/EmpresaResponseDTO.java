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

    @Schema(name = "id", required = true, title = "ID da empresa", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(name = "nome", required = true, title = "Nome da empresa", example = "Prado Softwares LTDA")
    private String nome;

    @Schema(name = "email", title = "E-mail da empresa", example = "empresa@empresamail.com")
    private String email;

    @Schema(name = "telefone", title = "Telefone da empresa", example = "(11) 99999-9999")
    private String telefone;

    @Schema(name = "setor", title = "Setor da empresa", example = "Tecnologia")
    private String setor;

    @Schema(name = "descricao", title = "Descrição da empresa", example = "Empresa de desenvolvimento de software")
    private String descricao;

    @Schema(name = "website", title = "Website da empresa", example = "www.pradosoftwares.com.br")
    private String website;

    @Schema(name = "linkedin", title = "LinkedIn da empresa", example = "www.linkedin.com/company/pradosoftwares")
    private String linkedin;

    @Schema(name = "endereco", title = "Endereço da empresa")
    private EnderecoResponseDTO endereco;


}

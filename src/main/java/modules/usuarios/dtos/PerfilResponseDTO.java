package modules.usuarios.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 09/03/2024
 */
@Getter
@Setter
@Builder
public class PerfilResponseDTO {

    private UUID id;

    private String nome;

    private String email;

    private String fotoPerfil;

    List<UsuarioResponseDTO> usuarios;

}

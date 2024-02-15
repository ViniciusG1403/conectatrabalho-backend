package modules.usuarios.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import modules.usuarios.enumerations.StatusUsuario;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;

import java.util.Objects;
import java.util.Optional;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 14/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class InativarUsuario {


    public void execute(String codigo) {
        Usuario usuario = (Usuario) Optional.ofNullable(
                Usuario.find("codigo", codigo).firstResult())
            .orElseThrow(UsuarioNotFoundException::new);

        if(Objects.equals(usuario.getStatus(), StatusUsuario.INATIVO)){
            throw new ValidationException("Usuário já está inativo");
        }

        usuario.setStatus(StatusUsuario.INATIVO);
        usuario.persist();
    }


}

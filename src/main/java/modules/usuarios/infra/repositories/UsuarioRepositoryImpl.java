package modules.usuarios.infra.repositories;

import core.repositories.GenericRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 15/02/2024
 */
@ApplicationScoped
public class UsuarioRepositoryImpl extends GenericRepositoryImpl<Usuario> implements UsuarioRepository {
}

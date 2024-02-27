package modules.vagas.infra.repositories;

import core.repositories.GenericRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import modules.vagas.infra.entities.Vagas;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/02/2024
 */
@ApplicationScoped
public class VagasRepositoryImpl extends GenericRepositoryImpl<Vagas> {
}

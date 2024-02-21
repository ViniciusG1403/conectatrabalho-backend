package modules.contratante.infra.repositories;

import core.repositories.GenericRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import modules.contratante.infra.entities.Contratante;
import modules.contratante.repositories.ContratanteRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
public class ContratanteRepositoryImpl extends GenericRepositoryImpl<Contratante> implements ContratanteRepository {
}

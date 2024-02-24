package modules.empresa.infra.repositories;

import core.repositories.GenericRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import modules.empresa.infra.entities.Empresa;
import modules.empresa.repositories.EmpresaRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
public class EmpresaRepositoryImpl extends GenericRepositoryImpl<Empresa> implements
                                                                          EmpresaRepository {
}

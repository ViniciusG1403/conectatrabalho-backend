package modules.aplicacoes.infra.repositories;

import core.repositories.GenericRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import modules.aplicacoes.infra.entities.Aplicacao;
import modules.aplicacoes.repositories.AplicacaoRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@ApplicationScoped
public class AplicacaoRepositoryImpl extends GenericRepositoryImpl<Aplicacao>
    implements AplicacaoRepository {
}

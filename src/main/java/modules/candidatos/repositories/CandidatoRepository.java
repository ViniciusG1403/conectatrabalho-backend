package modules.candidatos.repositories;

import core.repositories.GenericRepository;
import modules.candidatos.infra.entities.Candidato;

import java.util.Optional;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
public interface CandidatoRepository extends GenericRepository<Candidato> {

    public Optional<Candidato> alreadyExistsCandidatoByUser(final Object value);

}

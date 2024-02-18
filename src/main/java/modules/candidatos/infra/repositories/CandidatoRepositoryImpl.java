package modules.candidatos.infra.repositories;

import core.exceptions.ConectaTrabalhoException;
import core.repositories.GenericRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;
import modules.usuarios.infra.entities.Usuario;

import java.util.Optional;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@RequiredArgsConstructor
@ApplicationScoped
public class CandidatoRepositoryImpl extends GenericRepositoryImpl<Candidato> implements CandidatoRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Candidato> alreadyExistsCandidatoByUser(final Object value) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Candidato> query = criteriaBuilder.createQuery(Candidato.class);
            Root<Candidato> root = query.from(Candidato.class);

            Join<Candidato, Usuario> usuarioJoin = root.join("usuario", JoinType.INNER);

            Predicate predicate = criteriaBuilder.equal(usuarioJoin.get("id"), value);

            query.where(predicate);

            final Candidato result = entityManager.createQuery(query).setMaxResults(1).getSingleResult();

            return Optional.ofNullable(result);
        } catch (NoResultException ex) {
            return Optional.empty();
        } catch (Exception ex) {
            throw new ConectaTrabalhoException(ex);
        }
    }
}


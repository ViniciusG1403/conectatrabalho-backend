package core.repositories;

import core.exceptions.ConectaTrabalhoException;
import core.pesquisa.CondicaoPesquisa;
import io.quarkus.panache.common.Sort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.Getter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 15/02/2024
 */
@Transactional(dontRollbackOn = NoResultException.class)
public class GenericRepositoryImpl<T> implements GenericRepository<T> {

    public static final int DEFAULT_PAGE_SIZE = 20;

    private final Class<T> entityClass;

    @Getter
    @PersistenceContext
    EntityManager entityManager;

    public GenericRepositoryImpl() {
        this.entityClass = getEntityClass();
    }

    @Override
    public T save(final T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }

    @Override
    public Optional<T> findById(final Object id) {
        final T entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<T> findOne(final String field, final Object value) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            Predicate predicate = criteriaBuilder.equal(root.get(field), value);

            query.where(predicate);

            final T result = entityManager.createQuery(query).setMaxResults(1).getSingleResult();

            return Optional.ofNullable(result);
        } catch (NoResultException ex) {
            return Optional.empty();
        } catch (Exception ex) {
            throw new ConectaTrabalhoException(ex);
        }
    }

    @Override
    public Optional<T> findOne(final List<CondicaoPesquisa> condicaoPesquisaList){
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            List<Predicate> predicateList = condicaoPesquisaList.stream().map(condicao -> {
                return criteriaBuilder.equal(root.get(condicao.getChave()), condicao.getValor());
            }).toList();

            Predicate[] predicates = predicateList.toArray(new Predicate[0]);
            Predicate finalPredicate = criteriaBuilder.and(predicates);

            query.where(finalPredicate);

            final T result = entityManager.createQuery(query).setMaxResults(1).getSingleResult();

            return Optional.ofNullable(result);
        } catch (NoResultException ex) {
            return Optional.empty();
        } catch (Exception ex) {
            throw new ConectaTrabalhoException(ex);
        }
    }



    @Override
    public T update(final T entity) {
        entityManager.merge(entity);
        entityManager.flush();
        return entity;
    }

    private Class<T> getEntityClass() {
        try {
            Type type = getClass().getGenericSuperclass();

            while (!(type instanceof ParameterizedType)
                    || ((ParameterizedType) type).getRawType() != GenericRepositoryImpl.class) {
                if (type instanceof ParameterizedType) {
                    type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
                } else {
                    type = ((Class<?>) type).getGenericSuperclass();
                }
            }

            return (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
        } catch (Exception e) {
            throw new ConectaTrabalhoException(e);
        }
    }
}
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
import java.util.*;
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
    public List<T> findAll(){
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);


            return entityManager.createQuery(query).getResultList();

        } catch (NoResultException ex) {
            return Collections.emptyList();
        } catch (Exception ex) {
            throw new ConectaTrabalhoException(ex);
        }
    }

    @Override
    public List<T> findAll(final List<CondicaoPesquisa> condicaoPesquisaList) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            if (condicaoPesquisaList != null && !condicaoPesquisaList.isEmpty()) {
                List<Predicate> predicateList = condicaoPesquisaList.stream().map(condicao -> {
                    Path<String> path = root.get(condicao.getChave());
                    String valor = condicao.getValor().toString().toLowerCase();
                    return criteriaBuilder.like(criteriaBuilder.lower(path), "%" + valor + "%");
                }).collect(Collectors.toList());

                Predicate finalPredicate = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
                query.where(finalPredicate);
            }

            final List<T> result = entityManager.createQuery(query).getResultList();
            return result;
        } catch (NoResultException ex) {
            return Collections.emptyList();
        } catch (Exception ex) {
            throw new ConectaTrabalhoException(ex);
        }
    }

    @Override
    public List<T> findAll(final List<CondicaoPesquisa> condicaoPesquisaList, int pageNumber, int pageSize) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            if (condicaoPesquisaList != null && !condicaoPesquisaList.isEmpty()) {
                List<Predicate> predicateList = condicaoPesquisaList.stream().map(condicao -> {
                    Path<String> path = root.get(condicao.getChave());
                    String valor = condicao.getValor().toString().toLowerCase();
                    return criteriaBuilder.like(criteriaBuilder.lower(path), "%" + valor + "%");
                }).toList();

                Predicate finalPredicate = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
                query.where(finalPredicate);
            }

            return entityManager.createQuery(query)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
        } catch (NoResultException ex) {
            return Collections.emptyList();
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

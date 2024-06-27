package core.repositories;

import core.exceptions.ConectaTrabalhoException;
import core.geolocalizador.CoordenadasGeograficasDTO;
import core.pesquisa.CondicaoPesquisa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import modules.empresa.infra.entities.Empresa;
import modules.usuarios.infra.entities.Endereco;
import modules.usuarios.infra.entities.Usuario;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.BiFunction;
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
    public Optional<T> findOne(final String fieldPath, final Object value) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            String[] fields = fieldPath.split("\\.");

            Path<Object> path = root.get(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                path = path.get(fields[i]);
            }

            Predicate predicate = criteriaBuilder.equal(path, value);

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
    public Optional<T> findOne(final CondicaoPesquisa condicaoPesquisa) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            String fieldPath = condicaoPesquisa.getChave();
            String[] fields = fieldPath.split("\\.");

            Path<Object> path = root.get(fields[0]);
            for (int i = 1; i < fields.length; i++) {
                path = path.get(fields[i]);
            }

            Predicate predicate = criteriaBuilder.equal(path, condicaoPesquisa.getValor());

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
    public Optional<T> findOne(final List<CondicaoPesquisa> condicaoPesquisaList) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            List<Predicate> predicateList = new ArrayList<>();

            Map<String, BiFunction<Path<?>, Object, Predicate>> operationMap = new HashMap<>();
            operationMap.put("=", (path, value) -> criteriaBuilder.equal(path, value));
            operationMap.put("!=", (path, value) -> criteriaBuilder.notEqual(path, value));
            operationMap.put("<", (path, value) -> criteriaBuilder.lessThan((Path<Comparable>) path, (Comparable) value));
            operationMap.put("<=", (path, value) -> criteriaBuilder.lessThanOrEqualTo((Path<Comparable>) path, (Comparable) value));
            operationMap.put(">", (path, value) -> criteriaBuilder.greaterThan((Path<Comparable>) path, (Comparable) value));
            operationMap.put(">=", (path, value) -> criteriaBuilder.greaterThanOrEqualTo((Path<Comparable>) path, (Comparable) value));
            operationMap.put("like", (path, value) -> criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), "%" + value.toString().toLowerCase() + "%"));

            for (CondicaoPesquisa condicao : condicaoPesquisaList) {
                String fieldPath = condicao.getChave();
                String operacao = condicao.getOperacao() != null ? condicao.getOperacao() : "=";
                Object valor = condicao.getValor();
                String[] fields = fieldPath.split("\\.");

                Path<?> path = root.get(fields[0]);
                for (int i = 1; i < fields.length; i++) {
                    path = path.get(fields[i]);
                }

                if (path.getJavaType().isEnum()) {
                    Enum<?> enumValue = Enum.valueOf((Class<Enum>) path.getJavaType(), valor.toString());
                    if ("=".equals(operacao)) {
                        predicateList.add(criteriaBuilder.equal(path, enumValue));
                    } else if ("!=".equals(operacao)) {
                        predicateList.add(criteriaBuilder.notEqual(path, enumValue));
                    } else {
                        throw new IllegalArgumentException("Operação desconhecida para enum: " + operacao);
                    }
                } else if (path.getJavaType() == String.class) {
                    if ("=".equals(operacao)) {
                        predicateList.add(criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), "%" + valor.toString().toLowerCase() + "%"));
                    } else if ("!=".equals(operacao)) {
                        predicateList.add(criteriaBuilder.notLike(criteriaBuilder.lower((Path<String>) path), "%" + valor.toString().toLowerCase() + "%"));
                    } else {
                        throw new IllegalArgumentException("Operação desconhecida para String: " + operacao);
                    }
                } else {
                    BiFunction<Path<?>, Object, Predicate> operation = operationMap.get(operacao);
                    if (operation != null) {
                        predicateList.add(operation.apply(path, valor));
                    } else {
                        throw new IllegalArgumentException("Operação desconhecida: " + operacao);
                    }
                }
            }

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
                List<Predicate> predicateList = new ArrayList<>();

                Map<String, BiFunction<Path<?>, Object, Predicate>> operationMap = new HashMap<>();
                operationMap.put("=", (path, value) -> criteriaBuilder.equal(path, value));
                operationMap.put("!=", (path, value) -> criteriaBuilder.notEqual(path, value));
                operationMap.put("<", (path, value) -> criteriaBuilder.lessThan((Path<Comparable>) path, (Comparable) value));
                operationMap.put("<=", (path, value) -> criteriaBuilder.lessThanOrEqualTo((Path<Comparable>) path, (Comparable) value));
                operationMap.put(">", (path, value) -> criteriaBuilder.greaterThan((Path<Comparable>) path, (Comparable) value));
                operationMap.put(">=", (path, value) -> criteriaBuilder.greaterThanOrEqualTo((Path<Comparable>) path, (Comparable) value));
                operationMap.put("like", (path, value) -> criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), "%" + value.toString().toLowerCase() + "%"));

                for (CondicaoPesquisa condicao : condicaoPesquisaList) {
                    String chave = condicao.getChave();
                    String operacao = condicao.getOperacao() != null ? condicao.getOperacao() : "=";
                    Object valor = condicao.getValor();
                    String[] partesChave = chave.split("\\.");

                    Path<?> path = root;
                    for (String parteChave : partesChave) {
                        path = path.get(parteChave);
                    }

                    if (path.getJavaType().isEnum()) {
                        Enum<?> enumValue = Enum.valueOf((Class<Enum>) path.getJavaType(), valor.toString());
                        if ("=".equals(operacao)) {
                            predicateList.add(criteriaBuilder.equal(path, enumValue));
                        } else if ("!=".equals(operacao)) {
                            predicateList.add(criteriaBuilder.notEqual(path, enumValue));
                        } else {
                            throw new IllegalArgumentException("Operação desconhecida para enum: " + operacao);
                        }
                    } else if (valor instanceof String) {
                        if ("=".equals(operacao)) {
                            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), "%" + valor.toString().toLowerCase() + "%"));
                        } else if ("!=".equals(operacao)) {
                            predicateList.add(criteriaBuilder.notLike(criteriaBuilder.lower((Path<String>) path), "%" + valor.toString().toLowerCase() + "%"));
                        } else {
                            throw new IllegalArgumentException("Operação desconhecida para String: " + operacao);
                        }
                    } else {
                        BiFunction<Path<?>, Object, Predicate> operation = operationMap.get(operacao);
                        if (operation != null) {
                            predicateList.add(operation.apply(path, valor));
                        } else {
                            throw new IllegalArgumentException("Operação desconhecida: " + operacao);
                        }
                    }
                }

                Predicate finalPredicate = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
                query.where(finalPredicate);
            }

            return entityManager.createQuery(query).getResultList();
        } catch (NoResultException ex) {
            return Collections.emptyList();
        } catch (Exception ex) {
            throw new ConectaTrabalhoException(ex);
        }
    }


    @Override
    public List<T> findAll(List<CondicaoPesquisa> condicaoPesquisaList, String campoOrdenacao, String tipoOrdenacao, int pageNumber, int pageSize) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            if (condicaoPesquisaList != null && !condicaoPesquisaList.isEmpty()) {
                List<Predicate> predicateList = new ArrayList<>();

                for (CondicaoPesquisa condicao : condicaoPesquisaList) {
                    String chave = condicao.getChave();
                    String[] partesChave = chave.split("\\.");

                    Path<?> path = root;

                    for (String parteChave : partesChave) {
                        path = path.get(parteChave);
                    }

                    Object valor = condicao.getValor();
                    if (path.getJavaType().isEnum()) {
                        Enum<?> enumValue = Enum.valueOf((Class<Enum>) path.getJavaType(), valor.toString());
                        predicateList.add(criteriaBuilder.equal(path, enumValue));
                    } else {
                        if (valor instanceof String) {
                            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), "%" + valor.toString().toLowerCase() + "%"));
                        } else {
                            predicateList.add(criteriaBuilder.equal(path, valor));
                        }
                    }
                }

                Predicate finalPredicate = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
                query.where(finalPredicate);
            }

            if (campoOrdenacao != null && !campoOrdenacao.isEmpty()) {
                if (tipoOrdenacao != null && tipoOrdenacao.equalsIgnoreCase("ASC")) {
                    query.orderBy(criteriaBuilder.asc(root.get(campoOrdenacao)));
                } else {
                    query.orderBy(criteriaBuilder.desc(root.get(campoOrdenacao)));
                }
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
    public List<T> findAll(List<CondicaoPesquisa> condicaoPesquisaList, int pageNumber, int pageSize) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            if (condicaoPesquisaList != null && !condicaoPesquisaList.isEmpty()) {
                List<Predicate> predicateList = new ArrayList<>();

                for (CondicaoPesquisa condicao : condicaoPesquisaList) {
                    String chave = condicao.getChave();
                    String[] partesChave = chave.split("\\.");

                    Path<?> path = root;

                    for (String parteChave : partesChave) {
                        path = path.get(parteChave);
                    }

                    Object valor = condicao.getValor();
                    if (path.getJavaType().isEnum()) {
                        Enum<?> enumValue = Enum.valueOf((Class<Enum>) path.getJavaType(), valor.toString());
                        predicateList.add(criteriaBuilder.equal(path, enumValue));
                    } else {
                        if (valor instanceof String) {
                            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), "%" + valor.toString().toLowerCase() + "%"));
                        } else {
                            predicateList.add(criteriaBuilder.equal(path, valor));
                        }
                    }
                }

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
    public List<T> findAllProximidade(List<CondicaoPesquisa> condicaoPesquisaList, String campoOrdenacao, String tipoOrdenacao, CoordenadasGeograficasDTO coordenadas, double distanciaMaxima, int pageNumber, int pageSize) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<T> query = criteriaBuilder.createQuery(entityClass);
            Root<T> root = query.from(entityClass);

            // Navegação para acessar latitude e longitude da tabela de endereço
            Join<T, Empresa> empresaJoin = root.join("empresa", JoinType.INNER);
            Join<Empresa, Usuario> usuarioJoin = empresaJoin.join("usuario", JoinType.INNER);
            Join<Usuario, Endereco> enderecoJoin = usuarioJoin.join("endereco", JoinType.INNER);

            List<Predicate> predicateList = new ArrayList<>();

            // Adiciona outras condições de pesquisa, se houver
            if (condicaoPesquisaList != null && !condicaoPesquisaList.isEmpty()) {
                for (CondicaoPesquisa condicao : condicaoPesquisaList) {
                    String chave = condicao.getChave();
                    String[] partesChave = chave.split("\\.");

                    Path<?> path = root;

                    for (String parteChave : partesChave) {
                        path = path.get(parteChave);
                    }

                    Object valor = condicao.getValor();
                    if (path.getJavaType().isEnum()) {
                        Enum<?> enumValue = Enum.valueOf((Class<Enum>) path.getJavaType(), valor.toString());
                        predicateList.add(criteriaBuilder.equal(path, enumValue));
                    } else {
                        if (valor instanceof String) {
                            predicateList.add(criteriaBuilder.like(criteriaBuilder.lower((Path<String>) path), "%" + valor.toString().toLowerCase() + "%"));
                        } else {
                            predicateList.add(criteriaBuilder.equal(path, valor));
                        }
                    }
                }
            }

            // Adiciona condições para calcular a distância
            if (coordenadas.getLatitude() != 0.0 && coordenadas.getLongitude() != 0.0 && distanciaMaxima > 0) {
                final double RAIO_TERRA = 6371.0; // Raio médio da Terra em quilômetros

                // Conversão de graus para radianos
                double grausParaRadianos = Math.PI / 180.0;
                double latitudeOrigemRad = coordenadas.getLatitude() * grausParaRadianos;
                double longitudeOrigemRad = coordenadas.getLongitude() * grausParaRadianos;

                // Distância máxima convertida para radianos
                double distanciaMaximaRad = distanciaMaxima / RAIO_TERRA;

                // Cálculo das latitudes mínima e máxima
                double latitudeMinRad = latitudeOrigemRad - distanciaMaximaRad;
                double latitudeMaxRad = latitudeOrigemRad + distanciaMaximaRad;

                // Cálculo da variação de longitude para a latitude de origem
                double deltaLongitudeOrigem = Math.asin(Math.sin(distanciaMaximaRad) / Math.cos(latitudeOrigemRad));

                // Cálculo das longitudes mínima e máxima
                double longitudeMinRad = longitudeOrigemRad - deltaLongitudeOrigem;
                double longitudeMaxRad = longitudeOrigemRad + deltaLongitudeOrigem;

                // Conversão de radianos para graus
                double latitudeMin = latitudeMinRad / grausParaRadianos;
                double latitudeMax = latitudeMaxRad / grausParaRadianos;
                double longitudeMin = longitudeMinRad / grausParaRadianos;
                double longitudeMax = longitudeMaxRad / grausParaRadianos;

                // Adiciona as condições para latitude e longitude
                predicateList.add(criteriaBuilder.between(enderecoJoin.get("latitude"), latitudeMin, latitudeMax));
                predicateList.add(criteriaBuilder.between(enderecoJoin.get("longitude"), longitudeMin, longitudeMax));
            }

            Predicate finalPredicate = criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
            query.where(finalPredicate);

            // Adiciona ordenação, se especificada
            if (campoOrdenacao != null && !campoOrdenacao.isEmpty()) {
                if (tipoOrdenacao != null && tipoOrdenacao.equalsIgnoreCase("ASC")) {
                    query.orderBy(criteriaBuilder.asc(root.get(campoOrdenacao)));
                } else {
                    query.orderBy(criteriaBuilder.desc(root.get(campoOrdenacao)));
                }
            }

            // Executa a consulta paginada
            return entityManager.createQuery(query)
                    .setFirstResult((pageNumber - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        } catch (NoResultException ex) {
            // Se nenhum resultado for encontrado, retorna uma lista vazia
            return Collections.emptyList();
        } catch (Exception ex) {
            // Se ocorrer uma exceção, lança uma exceção personalizada
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

package core.repositories;

import core.pesquisa.CondicaoPesquisa;

import java.util.List;
import java.util.Optional;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 15/02/2024
 */
public interface GenericRepository<T> {

    T save(T object);

    Optional<T> findById(Object id);

    Optional<T> findOne(String field, Object value);

    Optional<T> findOne(final CondicaoPesquisa condicaoPesquisa);

    Optional<T> findOne(final List<CondicaoPesquisa> condicaoPesquisaList);

    List<T> findAll();

    List<T> findAll(final List<CondicaoPesquisa> condicaoPesquisaList);

    List<T> findAll(final List<CondicaoPesquisa> condicaoPesquisaList, int pageNumber, int pageSize);
    List<T> findAll(List<CondicaoPesquisa> condicaoPesquisaList, String campoOrdenacao, String tipoOrdenacao, int pageNumber, int pageSize);

    T update(T object);


}

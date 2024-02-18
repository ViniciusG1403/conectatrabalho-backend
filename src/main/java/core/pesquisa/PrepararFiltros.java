package core.pesquisa;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/24
 */
@ApplicationScoped
public class PrepararFiltros {

    public List<CondicaoPesquisa> execute(String search){
        final String[] filtros = search.split(",");
        final List<CondicaoPesquisa> condicaoPesquisaList = new ArrayList<>();
        for(int i = 0; i < filtros.length; i++){
            String field = filtros[i].split(":")[0];
            String value = filtros[i].split(":")[1];

            CondicaoPesquisa condicao = new CondicaoPesquisa(field, value);
            condicao.setChave(field);
            condicao.setValor(value);

            condicaoPesquisaList.add(condicao);
        }
        return condicaoPesquisaList;


    }


}

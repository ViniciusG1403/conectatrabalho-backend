package modules.empresa.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.empresa.converters.EmpresaConverter;
import modules.empresa.dtos.EmpresaResumidoDTO;
import modules.empresa.repositories.EmpresaRepository;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarEmpresas {

    private final EmpresaRepository repository;

    private final EmpresaConverter converter;

    public List<EmpresaResumidoDTO> execute(List<CondicaoPesquisa> condicaoPesquisaList, int page, int size) {

        return repository.findAll(condicaoPesquisaList, page, size).stream().map(converter::toResumido).toList();
    }


}

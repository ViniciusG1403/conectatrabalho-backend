package modules.vagas.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.vagas.converters.VagasConverter;
import modules.vagas.dtos.VagasResumidoDTO;
import modules.vagas.repositories.VagasRepository;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 27/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarTodasVagas {

    private final VagasRepository repository;

    private final VagasConverter vagasConverter;
    public List<VagasResumidoDTO> execute(List<CondicaoPesquisa> condicaoPesquisaList, int page, int size){
        return repository.findAll(condicaoPesquisaList, "dataCriacao", "DESC", page, size).stream().map(vagasConverter::toResumidoDTO).toList();
    }


}

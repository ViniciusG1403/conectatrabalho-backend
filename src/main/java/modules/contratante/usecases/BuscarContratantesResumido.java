package modules.contratante.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.contratante.converters.ContratanteConverter;
import modules.contratante.dtos.ContratanteResponseDTO;
import modules.contratante.dtos.ContratanteResumidoDTO;
import modules.contratante.repositories.ContratanteRepository;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarContratantesResumido {

    private final ContratanteRepository repository;

    private final ContratanteConverter converter;

    public List<ContratanteResumidoDTO> execute(List<CondicaoPesquisa> condicaoPesquisaList, int page, int size) {

        return repository.findAll(condicaoPesquisaList, page, size).stream().map(converter::toResumido).toList();
    }


}

package modules.candidatos.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoResumidoDTO;
import modules.candidatos.repositories.CandidatoRepository;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 22/02/2024
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarCandidatosResumido {

    private final CandidatoRepository repository;

    private final CandidatoConverter converter;

    public List<CandidatoResumidoDTO> execute(List<CondicaoPesquisa> condicaoPesquisaList, int page, int size) {

        return repository.findAll(condicaoPesquisaList, page, size).stream().map(converter::toResumido).toList();
    }
}

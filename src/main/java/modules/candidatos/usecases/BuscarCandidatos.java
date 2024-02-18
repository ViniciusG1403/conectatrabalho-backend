package modules.candidatos.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/24
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarCandidatos {

    private final CandidatoRepository repository;

    private final CandidatoConverter converter;

    public List<CandidatoDTO> execute(List<CondicaoPesquisa> condicoes) {
        final List<Candidato> candidatos =  repository.findAll(condicoes);
        return candidatos.stream().map(converter::toDTO).toList();
    }

}

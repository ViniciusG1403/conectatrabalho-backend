package modules.vagas.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.RequestScoped;
import lombok.RequiredArgsConstructor;
import modules.vagas.converters.VagasConverter;
import modules.vagas.dtos.VagasDTO;
import modules.vagas.dtos.VagasResumidoDTO;
import modules.vagas.repositories.VagasRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 27/02/2024
 */

@RequestScoped
@RequiredArgsConstructor
public class BuscarVagaPorEmpresa {

    private final VagasRepository repository;

    private final VagasConverter vagasConverter;

    public List<VagasResumidoDTO> execute(UUID idEmpresa) {

        List<CondicaoPesquisa> condicaoPesquisaList = List.of(new CondicaoPesquisa("empresa.id", idEmpresa));

        return repository.findAll(condicaoPesquisaList).stream().map(vagasConverter::toResumidoDTO).toList();

    }

}

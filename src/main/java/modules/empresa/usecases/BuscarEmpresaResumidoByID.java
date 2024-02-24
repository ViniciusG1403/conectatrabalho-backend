package modules.empresa.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.empresa.converters.EmpresaConverter;
import modules.empresa.dtos.EmpresaResumidoDTO;
import modules.empresa.repositories.EmpresaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarEmpresaResumidoByID {

    private final EmpresaRepository repository;

    private final EmpresaConverter converter;

    public List<EmpresaResumidoDTO> execute(UUID id) {
        return repository.findById(id).stream().map(converter::toResumido).toList();
    }


}

package modules.empresa.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.empresa.converters.EmpresaConverter;
import modules.empresa.dtos.EmpresaResponseDTO;
import modules.empresa.exceptions.EmpresaNaoEncontradoException;
import modules.empresa.infra.entities.Empresa;
import modules.empresa.repositories.EmpresaRepository;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarEmpresaPeloID {

    private final EmpresaRepository repository;

    private final EmpresaConverter converter;

    public EmpresaResponseDTO execute(UUID id) {
        Empresa empresa = repository.findById(id).orElseThrow(
            EmpresaNaoEncontradoException::new);
        return converter.toResponse(empresa);
    }


}

package modules.empresa.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import modules.empresa.dtos.EmpresaUpdateDTO;
import modules.empresa.exceptions.EmpresaNaoEncontradoException;
import modules.empresa.infra.entities.Empresa;
import modules.empresa.repositories.EmpresaRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class UpdateEmpresa {


    private final EmpresaRepository repository;

    public void execute(@Valid EmpresaUpdateDTO dto) {
        Empresa empresa = repository.findById(dto.getId()).orElseThrow(
            EmpresaNaoEncontradoException::new);

        empresa.setSetor(dto.getSetor());
        empresa.setDescricao(dto.getDescricao());
        empresa.setWebsite(dto.getWebsite());
        empresa.setLinkedin(dto.getLinkedin());
        empresa.setUrlFotoPerfil(dto.getUrlFotoPerfil());

        repository.update(empresa);
    }


}

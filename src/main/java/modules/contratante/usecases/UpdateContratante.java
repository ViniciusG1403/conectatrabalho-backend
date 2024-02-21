package modules.contratante.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import modules.contratante.dtos.ContratanteUpdateDTO;
import modules.contratante.exceptions.ContratanteNaoEncontradoException;
import modules.contratante.infra.entities.Contratante;
import modules.contratante.repositories.ContratanteRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class UpdateContratante {


    private final ContratanteRepository repository;

    public void execute(@Valid ContratanteUpdateDTO dto) {
        Contratante contratante = repository.findById(dto.getId()).orElseThrow(ContratanteNaoEncontradoException::new);

        contratante.setEmpresa(dto.getEmpresa());
        contratante.setSetor(dto.getSetor());
        contratante.setDescricao(dto.getDescricao());
        contratante.setWebsite(dto.getWebsite());
        contratante.setLinkedin(dto.getLinkedin());
        contratante.setUrlFotoPerfil(dto.getUrlFotoPerfil());

        repository.save(contratante);
    }


}

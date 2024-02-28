package modules.vagas.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.vagas.dtos.VagasCadastroDTO;
import modules.vagas.enumerations.StatusVaga;
import modules.vagas.enumerations.TipoVaga;
import modules.vagas.exceptions.VagaNaoEncontradaException;
import modules.vagas.infra.entities.Vagas;
import modules.vagas.repositories.VagasRepository;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 27/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class AtualizarVaga {

    private final VagasRepository repository;

    public void execute(VagasCadastroDTO vagasDTO){
        Vagas vagas = repository.findById(vagasDTO.getId()).orElseThrow(VagaNaoEncontradaException::new);

        vagas.setTitulo(vagasDTO.getTitulo());
        vagas.setDescricao(vagasDTO.getDescricao());
        vagas.setRemuneracao(vagasDTO.getRemuneracao());
        vagas.setTipo(TipoVaga.valueOf(vagasDTO.getTipo()));
        vagas.setNivel(vagasDTO.getNivel());
        vagas.setStatus(StatusVaga.valueOf(vagasDTO.getStatus()));


        repository.update(vagas);
    }


}

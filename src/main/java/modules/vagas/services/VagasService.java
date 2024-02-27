package modules.vagas.services;

import core.pesquisa.CondicaoPesquisa;
import core.validates.Validators;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.vagas.dtos.VagasCadastroDTO;
import modules.vagas.dtos.VagasDTO;
import modules.vagas.dtos.VagasResumidoDTO;
import modules.vagas.usecases.AtualizarVaga;
import modules.vagas.usecases.BuscarTodasVagas;
import modules.vagas.usecases.BuscarVagaPeloID;
import modules.vagas.usecases.CriarVaga;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 27/02/2024
 */
@Transactional(Transactional.TxType.REQUIRES_NEW)
@ApplicationScoped
@RequiredArgsConstructor
public class VagasService extends Validators {

    private final CriarVaga criarVaga;

    private final AtualizarVaga atualizarVaga;

    private final BuscarTodasVagas buscarTodasVagas;

    private final BuscarVagaPeloID buscarVagaPeloID;


    public VagasResumidoDTO criarVaga(VagasCadastroDTO dto){
        NonNullValidate(dto.getIdEmpresa(), "Empresa");
        NonNullValidate(dto.getTitulo(), "Título");
        NonNullValidate(dto.getDescricao(), "Descrição");
        NonNullValidate(dto.getRemuneracao(), "Remuneração");
        NonNullValidate(dto.getTipo(), "Tipo");
        NonNullValidate(dto.getNivel(), "Nível");
        NonNullValidate(dto.getStatus(), "Status");
        return criarVaga.execute(dto);
    }

    public void atualizarVaga(VagasCadastroDTO vagasDTO){
        NonNullValidate(vagasDTO.getId(), "ID");
        NonNullValidate(vagasDTO.getTitulo(), "Título");
        NonNullValidate(vagasDTO.getDescricao(), "Descrição");
        NonNullValidate(vagasDTO.getRemuneracao(), "Remuneração");
        NonNullValidate(vagasDTO.getTipo(), "Tipo");
        NonNullValidate(vagasDTO.getNivel(), "Nível");
        NonNullValidate(vagasDTO.getStatus(), "Status");
        atualizarVaga.execute(vagasDTO);
    }

    public VagasDTO buscarVagaPeloID(Long id){
        NonNullValidate(id, "ID");
        return buscarVagaPeloID.execute(id);
    }

    public List<VagasResumidoDTO> buscarTodasVagas(List<CondicaoPesquisa> condicaoPesquisaList, int page, int size){
        return buscarTodasVagas.execute(condicaoPesquisaList, page, size);
    }
}

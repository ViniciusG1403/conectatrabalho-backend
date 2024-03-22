package modules.vagas.services;

import core.pesquisa.CondicaoPesquisa;
import core.validates.Validators;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.vagas.dtos.FinalizarPausarVagaDTO;
import modules.vagas.dtos.VagasCadastroDTO;
import modules.vagas.dtos.VagasDTO;
import modules.vagas.dtos.VagasResumidoDTO;
import modules.vagas.usecases.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 27/02/2024
 */

@ApplicationScoped
@RequiredArgsConstructor
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class VagasService extends Validators {

    private final CriarVaga criarVaga;

    private final AtualizarVaga atualizarVaga;

    private final BuscarTodasVagas buscarTodasVagas;

    private final BuscarVagaPeloID buscarVagaPeloID;

    private final FinalizarVaga finalizarVaga;

    private final PausarVaga pausarVaga;

    private final BuscarVagasPorProximidade buscarVagasPorProximidade;


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

    public VagasDTO buscarVagaPeloID(String id){
        NonNullValidate(id, "ID");
        return buscarVagaPeloID.execute(UUID.fromString(id));
    }

    public List<VagasResumidoDTO> buscarTodasVagas(List<CondicaoPesquisa> condicaoPesquisaList, int page, int size){
        return buscarTodasVagas.execute(condicaoPesquisaList, page, size);
    }

    public void finalizarVaga(FinalizarPausarVagaDTO dto){
        NonNullValidate(dto.getIdVaga(), "Vaga");
        NonNullValidate(dto.getIdEmpresa(), "Empresa");
        finalizarVaga.execute(dto);
    }

    public void pausarVaga(FinalizarPausarVagaDTO dto){
        NonNullValidate(dto.getIdVaga(), "Vaga");
        NonNullValidate(dto.getIdEmpresa(), "Empresa");
        pausarVaga.execute(dto);
    }

    public List<VagasResumidoDTO> buscarVagasPorProximidade(String idUsuario, List<CondicaoPesquisa> condicaoPesquisaList, int page, int size, int distanciaMaxima){
        return buscarVagasPorProximidade.execute(UUID.fromString(idUsuario), condicaoPesquisaList, page, size, distanciaMaxima);
    }


}

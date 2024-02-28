package modules.vagas.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.empresa.exceptions.EmpresaNaoEncontradoException;
import modules.empresa.infra.entities.Empresa;
import modules.empresa.repositories.EmpresaRepository;
import modules.vagas.dtos.VagasCadastroDTO;
import modules.vagas.dtos.VagasResumidoDTO;
import modules.vagas.enumerations.StatusVaga;
import modules.vagas.enumerations.TipoVaga;
import modules.vagas.infra.entities.Vagas;
import modules.vagas.repositories.VagasRepository;

import java.sql.Timestamp;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class CriarVaga {

    private final VagasRepository vagasRepository;

    private final EmpresaRepository empresaRepository;

    public VagasResumidoDTO execute(VagasCadastroDTO dto) {
        Empresa empresa = empresaRepository.findById(dto.getIdEmpresa()).orElseThrow(EmpresaNaoEncontradoException::new);

        Vagas vagas = new Vagas();
        vagas.setTitulo(dto.getTitulo());
        vagas.setDescricao(dto.getDescricao());
        vagas.setRemuneracao(dto.getRemuneracao());
        vagas.setTipo(TipoVaga.valueOf(dto.getTipo()));
        vagas.setNivel(dto.getNivel());
        vagas.setStatus(StatusVaga.valueOf(dto.getStatus()));
        vagas.setEmpresa(empresa);
        vagas.setDataCriacao(new Timestamp(System.currentTimeMillis()));

        vagasRepository.save(vagas);

        VagasResumidoDTO resumidoDTO = new VagasResumidoDTO();
        resumidoDTO.setTitulo(vagas.getTitulo());
        resumidoDTO.setDescricao(vagas.getDescricao());
        resumidoDTO.setNivel(vagas.getNivel());
        resumidoDTO.setStatus(StatusVaga.valueOf(vagas.getStatus()));
        resumidoDTO.setEmpresa(vagas.getEmpresa().getUsuario().getNome());

        return resumidoDTO;

    }

}

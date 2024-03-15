package modules.vagas.converters;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.empresa.converters.EmpresaConverter;
import modules.vagas.dtos.VagasDTO;
import modules.vagas.dtos.VagasResumidoDTO;
import modules.vagas.enumerations.StatusVaga;
import modules.vagas.enumerations.TipoVaga;
import modules.vagas.infra.entities.Vagas;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class VagasConverter {

    private final EmpresaConverter empresaConverter;


    public Vagas toEntity(VagasDTO dto) {
        Vagas entity = new Vagas();
        entity.setId(dto.getId());
        entity.setEmpresa(empresaConverter.toEntity(dto.getEmpresa()));
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setRemuneracao(dto.getRemuneracao());
        entity.setTipo(TipoVaga.valueOf(dto.getTipo()));
        entity.setNivel(dto.getNivel());
        entity.setStatus(StatusVaga.valueOf(dto.getStatus()));
        return entity;
    }

    public VagasDTO toDTO(Vagas entity) {
        VagasDTO dto = new VagasDTO();
        dto.setId(entity.getId());
        dto.setEmpresa(empresaConverter.toDTO(entity.getEmpresa()));
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setRemuneracao(entity.getRemuneracao());
        dto.setTipo(TipoVaga.valueOf(entity.getTipo()));
        dto.setNivel(entity.getNivel());
        dto.setStatus(StatusVaga.valueOf(entity.getStatus()));
        return dto;
    }

    public VagasResumidoDTO toResumidoDTO(Vagas entity) {
        VagasResumidoDTO dto = new VagasResumidoDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setDescricao(entity.getDescricao());
        dto.setNivel(entity.getNivel());
        dto.setStatus(StatusVaga.valueOf(entity.getStatus()));
        dto.setEmpresa(entity.getEmpresa().getUsuario().getNome());
        return dto;
    }



}

package modules.usuarios.converters;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.dtos.EnderecoDTO;
import modules.usuarios.dtos.EnderecoResponseDTO;
import modules.usuarios.infra.entities.Endereco;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class EnderecoConverter {

    private final UsuarioConverter usuarioConverter;

    public Endereco toEntity(EnderecoDTO dto) {
        Endereco entity = new Endereco();
        entity.setCep(dto.getCep());
        entity.setEstado(dto.getEstado());
        entity.setPais(dto.getPais());
        entity.setMunicipio(dto.getMunicipio());
        entity.setBairro(dto.getBairro());
        entity.setLogradouro(dto.getLogradouro());
        entity.setNumero(dto.getNumero());
        entity.setComplemento(dto.getComplemento());
        return entity;
    }

    public EnderecoDTO toDto(Endereco entity) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setCep(entity.getCep());
        dto.setEstado(entity.getEstado());
        dto.setPais(entity.getPais());
        dto.setMunicipio(entity.getMunicipio());
        dto.setBairro(entity.getBairro());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        return dto;
    }

    public EnderecoResponseDTO toResponseDto(Endereco entity) {
        EnderecoResponseDTO dto = new EnderecoResponseDTO();
        dto.setId(entity.getId());
        dto.setCep(entity.getCep());
        dto.setEstado(entity.getEstado());
        dto.setPais(entity.getPais());
        dto.setMunicipio(entity.getMunicipio());
        dto.setBairro(entity.getBairro());
        dto.setLogradouro(entity.getLogradouro());
        dto.setNumero(entity.getNumero());
        dto.setComplemento(entity.getComplemento());
        return dto;
    }

}

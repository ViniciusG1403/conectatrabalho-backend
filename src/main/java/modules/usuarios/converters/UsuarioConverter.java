package modules.usuarios.converters;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.dtos.UsuarioRegistroDTO;
import modules.usuarios.dtos.UsuarioResponseDTO;
import modules.usuarios.enumerations.StatusUsuario;
import modules.usuarios.enumerations.TipoUsuario;
import modules.usuarios.infra.entities.Usuario;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 13/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class UsuarioConverter {

    private final EnderecoConverter enderecoConverter;

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setSenha(dto.getSenha());
        entity.setTipo(TipoUsuario.valueOf(dto.getTipo()));
        entity.setStatus(StatusUsuario.valueOf(dto.getStatus()));
        entity.setRegistro(dto.getRegistro());
        entity.setUltimaAtualizacao(dto.getUltimaAtualizacao());
        entity.setCodigo(dto.getCodigo());
        entity.setEndereco(enderecoConverter.toEntity(dto.getEndereco()));
        if(entity.getEndereco() != null){
            entity.getEndereco().setUsuario(entity);
        }
        return entity;
    }

    public UsuarioDTO toDTO(Usuario entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setTipo(TipoUsuario.valueOf(entity.getTipo()));
        dto.setStatus(StatusUsuario.valueOf(entity.getStatus()));
        dto.setRegistro(entity.getRegistro());
        dto.setUltimaAtualizacao(entity.getUltimaAtualizacao());
        dto.setCodigo(entity.getCodigo());
        dto.setEndereco(enderecoConverter.toDto(entity.getEndereco()));
        return dto;
    }

    public UsuarioDTO registroToDTO(UsuarioRegistroDTO dto){
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setTipo(dto.getTipo());
        usuario.setSenha(dto.getSenha());
        usuario.setStatus(StatusUsuario.INATIVO.ordinal());
        usuario.setEndereco(dto.getEndereco());
        return usuario;

    }

    public UsuarioResponseDTO toResponse(Usuario entity) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setTipo(TipoUsuario.valueOf(entity.getTipo()));
        dto.setStatus(StatusUsuario.valueOf(entity.getStatus()));
        dto.setRegistro(entity.getRegistro());
        dto.setEndereco(enderecoConverter.toResponseDto(entity.getEndereco()));
        return dto;
    }
}

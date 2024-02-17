package modules.usuarios.converters;

import jakarta.enterprise.context.ApplicationScoped;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.dtos.UsuarioRegistroDTO;
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
public class UsuarioConverter {

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
        return usuario;



    }
}

package modules.usuarios.usecases;

import core.encoder.PBKDF2Encoder;
import core.validates.Validators;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.enumerations.StatusUsuario;
import modules.usuarios.enumerations.TipoUsuario;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 13/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class CriarOuAtualizarUsuario extends Validators {

    private final UsuarioConverter converter;

    private final PBKDF2Encoder encoder;

    public void execute(UsuarioDTO dto) {

        if (Objects.nonNull(dto.getId())) {
            Usuario usuario = Optional.ofNullable((Usuario) Usuario.findById(dto.getId()))
                .orElseThrow(UsuarioNotFoundException::new);

            usuario.setNome(dto.getNome());
            usuario.setEmail(dto.getEmail());
            usuario.setTelefone(dto.getTelefone());
            usuario.setTipo(TipoUsuario.valueOf(dto.getTipo()));
            usuario.setStatus(StatusUsuario.valueOf(dto.getStatus()));
            usuario.setRegistro(dto.getRegistro());
            usuario.setUltimaAtualizacao(new Timestamp(System.currentTimeMillis()));
            usuario.setCodigo(dto.getCodigo());
            usuario.persist();
        } else {
            Usuario usuario = converter.toEntity(dto);
            usuario.setSenha(encoder.encode(dto.getSenha()));
            usuario.setRegistro(new Timestamp(System.currentTimeMillis()));

            Usuario.persist(usuario);
        }

    }

}

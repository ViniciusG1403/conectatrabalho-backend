package modules.usuarios.usecases;

import core.emailservice.MessageOperation;
import core.emailservice.SendEmailService;
import core.encoder.PBKDF2Encoder;
import core.exceptions.ConectaTrabalhoException;
import core.validates.Validators;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.dtos.UsuarioResponseDTO;
import modules.usuarios.enumerations.StatusUsuario;
import modules.usuarios.enumerations.TipoUsuario;
import modules.usuarios.exceptions.UsuarioExistenteComMesmoEmail;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

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

    private final UsuarioRepository usuarioRepository;

    private final UsuarioJaExisteComEsteEmail usuarioJaExisteComEsteEmail;

    private final SendEmailService sendEmailService;

    private final GenerateRandomCode generateRandomCode;

    public UsuarioResponseDTO execute(UsuarioDTO dto) {

        if (Objects.nonNull(dto.getId())) {
            Usuario usuario = usuarioRepository.findById(dto.getId())
                .orElseThrow(UsuarioNotFoundException::new);

            usuario.setNome(dto.getNome());
            usuario.setEmail(dto.getEmail());
            usuario.setTelefone(dto.getTelefone());
            usuario.setTipo(TipoUsuario.valueOf(dto.getTipo()));
            usuario.setStatus(StatusUsuario.valueOf(dto.getStatus()));
            usuario.setRegistro(dto.getRegistro());
            usuario.setUltimaAtualizacao(new Timestamp(System.currentTimeMillis()));
            usuario.setCodigo(dto.getCodigo());

            return converter.toResponse(usuarioRepository.update(usuario));
        } else {

            if(usuarioJaExisteComEsteEmail.execute(dto)) {
                throw new UsuarioExistenteComMesmoEmail();
            }

            Usuario usuario = converter.toEntity(dto);
            usuario.setRole("USER_ROLE");
            usuario.setSenha(encoder.encode(dto.getSenha()));
            usuario.setRegistro(new Timestamp(System.currentTimeMillis()));
            usuario.setCodigo(generateRandomCode.execute());

            Usuario usuarioSalvo = usuarioRepository.save(usuario);

            sendEmailService.sendMail(converter.toDTO(usuarioSalvo), "Ativação de conta", MessageOperation.ATIVACAO);

            return converter.toResponse(usuarioSalvo);
        }

    }

}

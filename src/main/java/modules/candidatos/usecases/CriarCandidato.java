package modules.candidatos.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

import java.util.Objects;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class CriarCandidato {

    private final CandidatoConverter converter;

    private final CandidatoRepository repository;

    private final UsuarioRepository usuarioRepository;

    private final UsuarioConverter usuarioConverter;
    @Transactional
    public CandidatoDTO execute(CandidatoDTO dto) {
        Boolean candidato = repository.alreadyExistsCandidatoByUser(dto.getUsuario().getId()).isPresent();
        if (candidato) {
            throw new RuntimeException("Candidato já cadastrado para esse usuário");
        }
        Usuario usuario = usuarioRepository.findById(dto.getUsuario().getId()).orElseThrow(
            UsuarioNotFoundException::new);

        dto.setUsuario(usuarioConverter.toDTO(usuario));

        Candidato entity = converter.toEntity(dto);
        repository.save(entity);
        return converter.toDTO(entity);
    }

}

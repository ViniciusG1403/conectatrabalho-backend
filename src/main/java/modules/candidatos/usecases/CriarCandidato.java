package modules.candidatos.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoCadastroDTO;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.dtos.CandidatoResponseDTO;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.enumerations.TipoUsuario;
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

    public CandidatoResponseDTO execute(CandidatoCadastroDTO dto) {
        boolean candidato = repository.alreadyExistsCandidatoByUser(dto.getIdUsuario()).isPresent();
        if (candidato) {
            throw new ValidationException("Candidato já cadastrado para esse usuário");
        }
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()).orElseThrow(
            UsuarioNotFoundException::new);

        if (Objects.equals(usuario.getTipo(), TipoUsuario.EMPRESA)) {
            throw new ValidationException(
                "Usuário do tipo empresa não pode ser cadastrado como candidato");
        }

        Candidato entity = new Candidato();
        entity.setUsuario(usuario);
        entity.setDisponibilidade(dto.getDisponibilidade());
        entity.setHabilidades(dto.getHabilidades());
        entity.setLinkedin(dto.getLinkedin());
        entity.setGithub(dto.getGithub());
        entity.setPortfolio(dto.getPortfolio());
        entity.setPretensaoSalarial(dto.getPretensaoSalarial());
        entity.setUrlCurriculum(dto.getUrlCurriculum());
        entity.setUrlFotoPerfil(dto.getUrlFotoPerfil());
        repository.save(entity);

        return converter.toResponse(entity);
    }

}

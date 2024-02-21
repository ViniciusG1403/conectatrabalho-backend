package modules.contratante.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import modules.contratante.converters.ContratanteConverter;
import modules.contratante.dtos.ContratanteRegisterDTO;
import modules.contratante.dtos.ContratanteResponseDTO;
import modules.contratante.infra.entities.Contratante;
import modules.contratante.repositories.ContratanteRepository;
import modules.usuarios.enumerations.TipoUsuario;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

import java.util.Objects;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class CriarContratante {

    private final UsuarioRepository usuarioRepository;

    private final ContratanteRepository repository;

    private final ContratanteConverter converter;

    public ContratanteResponseDTO execute(@Valid ContratanteRegisterDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuario()).orElseThrow(UsuarioNotFoundException::new);

        if(Objects.equals(usuario.getTipo(), TipoUsuario.CANDIDATO)){
            throw new ValidationException("Usuário do tipo candidato não pode ser cadastrado como contratante");
        }

        Contratante contratante = new Contratante();
        contratante.setUsuario(usuario);
        contratante.setEmpresa(dto.getEmpresa());
        contratante.setSetor(dto.getSetor());
        contratante.setDescricao(dto.getDescricao());
        contratante.setWebsite(dto.getWebsite());
        contratante.setLinkedin(dto.getLinkedin());
        contratante.setUrlFotoPerfil(dto.getUrlFotoPerfil());


        repository.save(contratante);

        return converter.toResponse(contratante);
    }




}

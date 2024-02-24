package modules.empresa.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import modules.empresa.converters.EmpresaConverter;
import modules.empresa.dtos.EmpresaRegisterDTO;
import modules.empresa.dtos.EmpresaResponseDTO;
import modules.empresa.infra.entities.Empresa;
import modules.empresa.repositories.EmpresaRepository;
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
public class CriarEmpresa {

    private final UsuarioRepository usuarioRepository;

    private final EmpresaRepository repository;

    private final EmpresaConverter converter;

    public EmpresaResponseDTO execute(@Valid EmpresaRegisterDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario()).orElseThrow(UsuarioNotFoundException::new);

        if(Objects.equals(usuario.getTipo(), TipoUsuario.CANDIDATO)){
            throw new ValidationException("Usuário do tipo candidato não pode ser cadastrado como empresa");
        }

        Empresa empresa = new Empresa();
        empresa.setUsuario(usuario);
        empresa.setEmpresa(dto.getEmpresa());
        empresa.setSetor(dto.getSetor());
        empresa.setDescricao(dto.getDescricao());
        empresa.setWebsite(dto.getWebsite());
        empresa.setLinkedin(dto.getLinkedin());
        empresa.setUrlFotoPerfil(dto.getUrlFotoPerfil());


        repository.save(empresa);

        return converter.toResponse(empresa);
    }




}

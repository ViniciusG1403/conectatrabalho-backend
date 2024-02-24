package modules.empresa.converters;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.empresa.dtos.EmpresaDTO;
import modules.empresa.dtos.EmpresaResponseDTO;
import modules.empresa.dtos.EmpresaResumidoDTO;
import modules.empresa.infra.entities.Empresa;
import modules.usuarios.converters.EnderecoConverter;
import modules.usuarios.converters.UsuarioConverter;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class EmpresaConverter {

    private final UsuarioConverter usuarioConverter;

    private final EnderecoConverter enderecoConverter;

    public Empresa toEntity(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setId(dto.getId());
        empresa.setUsuario(usuarioConverter.toEntity(dto.getUsuario()));
        empresa.setSetor(dto.getSetor());
        empresa.setDescricao(dto.getDescricao());
        empresa.setWebsite(dto.getWebsite());
        empresa.setLinkedin(dto.getLinkedin());
        empresa.setUrlFotoPerfil(dto.getUrlFotoPerfil());
        return empresa;
    }

    public EmpresaDTO toDTO(Empresa entity) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(entity.getId());
        empresaDTO.setUsuario(usuarioConverter.toDTO(entity.getUsuario()));
        empresaDTO.setSetor(entity.getSetor());
        empresaDTO.setDescricao(entity.getDescricao());
        empresaDTO.setWebsite(entity.getWebsite());
        empresaDTO.setLinkedin(entity.getLinkedin());
        empresaDTO.setUrlFotoPerfil(entity.getUrlFotoPerfil());
        return empresaDTO;
    }

    public EmpresaResponseDTO toResponse(Empresa entity) {
        EmpresaResponseDTO responseDTO = new EmpresaResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setNome(entity.getUsuario().getNome());
        responseDTO.setEmail(entity.getUsuario().getEmail());
        responseDTO.setTelefone(entity.getUsuario().getTelefone());
        responseDTO.setSetor(entity.getSetor());
        responseDTO.setDescricao(entity.getDescricao());
        responseDTO.setWebsite(entity.getWebsite());
        responseDTO.setLinkedin(entity.getLinkedin());
        responseDTO.setUrlFotoPerfil(entity.getUrlFotoPerfil());
        responseDTO.setEndereco(enderecoConverter.toResponseDto(entity.getUsuario().getEndereco()));
        return responseDTO;
    }

    public EmpresaResumidoDTO toResumido(Empresa entity) {
        EmpresaResumidoDTO resumidoDTO = new EmpresaResumidoDTO();
        resumidoDTO.setDescricao(entity.getDescricao());
        resumidoDTO.setSetor(entity.getSetor());
        resumidoDTO.setUrlFotoPerfil(entity.getUrlFotoPerfil());
        resumidoDTO.setCidadeEstado(
            entity.getUsuario().getEndereco().getMunicipio() + "/" + entity.getUsuario()
                .getEndereco()
                .getEstado());
        return resumidoDTO;
    }

}

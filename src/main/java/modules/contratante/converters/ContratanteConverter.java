package modules.contratante.converters;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.contratante.dtos.ContratanteDTO;
import modules.contratante.dtos.ContratanteResponseDTO;
import modules.contratante.dtos.ContratanteResumidoDTO;
import modules.contratante.infra.entities.Contratante;
import modules.usuarios.converters.EnderecoConverter;
import modules.usuarios.converters.UsuarioConverter;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class ContratanteConverter {

    private final UsuarioConverter usuarioConverter;

    private final EnderecoConverter enderecoConverter;

    public Contratante toEntity(ContratanteDTO dto){
        Contratante contratante = new Contratante();
        contratante.setId(dto.getId());
        contratante.setUsuario(usuarioConverter.toEntity(dto.getUsuario()));
        contratante.setEmpresa(dto.getEmpresa());
        contratante.setSetor(dto.getSetor());
        contratante.setDescricao(dto.getDescricao());
        contratante.setWebsite(dto.getWebsite());
        contratante.setLinkedin(dto.getLinkedin());
        contratante.setUrlFotoPerfil(dto.getUrlFotoPerfil());
        return contratante;
    }

    public ContratanteDTO toDTO(Contratante entity){
        ContratanteDTO contratanteDTO = new ContratanteDTO();
        contratanteDTO.setId(entity.getId());
        contratanteDTO.setUsuario(usuarioConverter.toDTO(entity.getUsuario()));
        contratanteDTO.setEmpresa(entity.getEmpresa());
        contratanteDTO.setSetor(entity.getSetor());
        contratanteDTO.setDescricao(entity.getDescricao());
        contratanteDTO.setWebsite(entity.getWebsite());
        contratanteDTO.setLinkedin(entity.getLinkedin());
        contratanteDTO.setUrlFotoPerfil(entity.getUrlFotoPerfil());
        return contratanteDTO;
    }

    public ContratanteResponseDTO toResponse(Contratante entity){
        ContratanteResponseDTO responseDTO = new ContratanteResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setNome(entity.getUsuario().getNome());
        responseDTO.setEmail(entity.getUsuario().getEmail());
        responseDTO.setTelefone(entity.getUsuario().getTelefone());
        responseDTO.setEmpresa(entity.getEmpresa());
        responseDTO.setSetor(entity.getSetor());
        responseDTO.setDescricao(entity.getDescricao());
        responseDTO.setWebsite(entity.getWebsite());
        responseDTO.setLinkedin(entity.getLinkedin());
        responseDTO.setUrlFotoPerfil(entity.getUrlFotoPerfil());
        responseDTO.setEndereco(enderecoConverter.toResponseDto(entity.getUsuario().getEndereco()));
        return responseDTO;
    }

    public ContratanteResumidoDTO toResumido(Contratante entity){
        ContratanteResumidoDTO resumidoDTO = new ContratanteResumidoDTO();
        resumidoDTO.setEmpresa(entity.getEmpresa());
        resumidoDTO.setDescricao(entity.getDescricao());
        resumidoDTO.setSetor(entity.getSetor());
        resumidoDTO.setUrlFotoPerfil(entity.getUrlFotoPerfil());
        resumidoDTO.setCidadeEstado(entity.getUsuario().getEndereco().getMunicipio() + "/" + entity.getUsuario().getEndereco().getEstado());
        return resumidoDTO;
    }


}

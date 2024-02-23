package modules.candidatos.converters;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.dtos.CandidatoResponseDTO;
import modules.candidatos.dtos.CandidatoResumidoDTO;
import modules.candidatos.infra.entities.Candidato;
import modules.usuarios.converters.EnderecoConverter;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.infra.entities.Usuario;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class CandidatoConverter {


    private final UsuarioConverter usuarioConverter;

    private final EnderecoConverter enderecoConverter;

    public Candidato toEntity(CandidatoDTO dto){
        Candidato candidato = new Candidato();
        candidato.setId(dto.getId());
        candidato.setUsuario(usuarioConverter.toEntity(dto.getUsuario()));
        candidato.setHabilidades(dto.getHabilidades());
        candidato.setLinkedin(dto.getLinkedin());
        candidato.setGithub(dto.getGithub());
        candidato.setPortfolio(dto.getPortfolio());
        candidato.setDisponibilidade(dto.getDisponibilidade());
        candidato.setPretensaoSalarial(dto.getPretensaoSalarial());
        candidato.setUrlCurriculum(dto.getUrlCurriculum());
        candidato.setUrlFotoPerfil(dto.getUrlFotoPerfil());
        return candidato;
    }

    public CandidatoDTO toDTO(Candidato entity){
        CandidatoDTO candidatoDTO = new CandidatoDTO();
        candidatoDTO.setId(entity.getId());
        candidatoDTO.setUsuario(usuarioConverter.toDTO(entity.getUsuario()));
        candidatoDTO.setHabilidades(entity.getHabilidades());
        candidatoDTO.setLinkedin(entity.getLinkedin());
        candidatoDTO.setGithub(entity.getGithub());
        candidatoDTO.setPortfolio(entity.getPortfolio());
        candidatoDTO.setDisponibilidade(entity.getDisponibilidade());
        candidatoDTO.setPretensaoSalarial(entity.getPretensaoSalarial());
        candidatoDTO.setUrlCurriculum(entity.getUrlCurriculum());
        candidatoDTO.setUrlFotoPerfil(entity.getUrlFotoPerfil());
        return candidatoDTO;
    }

    public CandidatoResponseDTO toResponse(Candidato entity){
        CandidatoResponseDTO responseDTO = new CandidatoResponseDTO();
        responseDTO.setNome(entity.getUsuario().getNome());
        responseDTO.setEmail(entity.getUsuario().getEmail());
        responseDTO.setTelefone(entity.getUsuario().getTelefone());
        responseDTO.setHabilidades(entity.getHabilidades());
        responseDTO.setLinkedin(entity.getLinkedin());
        responseDTO.setGithub(entity.getGithub());
        responseDTO.setPortfolio(entity.getPortfolio());
        responseDTO.setDisponibilidade(entity.getDisponibilidade());
        responseDTO.setPretensaoSalarial(entity.getPretensaoSalarial());
        responseDTO.setUrlCurriculum(entity.getUrlCurriculum());
        responseDTO.setUrlFotoPerfil(entity.getUrlFotoPerfil());
        responseDTO.setEndereco(enderecoConverter.toDto(entity.getUsuario().getEndereco()));

        return responseDTO;
    }

    public CandidatoResumidoDTO toResumido(Candidato entity){
        CandidatoResumidoDTO resumidoDTO = new CandidatoResumidoDTO();
        resumidoDTO.setNome(entity.getUsuario().getNome());
        resumidoDTO.setHabilidades(entity.getHabilidades());
        resumidoDTO.setUrlFotoPerfil(entity.getUrlFotoPerfil());
        resumidoDTO.setCidadeEstado(entity.getUsuario().getEndereco().getMunicipio() + "/" + entity.getUsuario().getEndereco().getEstado());
        return resumidoDTO;
    }

}

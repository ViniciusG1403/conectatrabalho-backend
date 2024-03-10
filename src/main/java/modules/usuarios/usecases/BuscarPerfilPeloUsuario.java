package modules.usuarios.usecases;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.converters.CandidatoConverter;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.dtos.CandidatoResponseDTO;
import modules.candidatos.exceptions.CandidatoNaoEncontradoException;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;
import modules.candidatos.usecases.BuscarImagemPerfilCandidato;
import modules.empresa.converters.EmpresaConverter;
import modules.empresa.dtos.EmpresaDTO;
import modules.empresa.dtos.EmpresaResponseDTO;
import modules.empresa.exceptions.EmpresaNaoEncontradoException;
import modules.empresa.infra.entities.Empresa;
import modules.empresa.repositories.EmpresaRepository;
import modules.empresa.usecases.BuscarImagemPerfilEmpresa;
import modules.usuarios.dtos.PerfilResponseDTO;
import modules.usuarios.enumerations.TipoUsuario;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 09/03/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarPerfilPeloUsuario {

    private final CandidatoRepository candidatoRepository;

    private final BuscarImagemPerfilCandidato buscarImagemPerfilCandidato;

    private final EmpresaRepository empresaRepository;

    private final BuscarImagemPerfilEmpresa buscarImagemPerfilEmpresa;

    private final UsuarioRepository usuarioRepository;


    public PerfilResponseDTO execute(UUID idUsuario){
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(UsuarioNotFoundException::new);

        if(Objects.equals(usuario.getTipo(), TipoUsuario.CANDIDATO)){
            return buscarPerfilCandidato(usuario);
        }

        return buscarPerfilEmpresa(usuario);

    }

    private PerfilResponseDTO buscarPerfilEmpresa(Usuario usuario){
        Empresa empresa = empresaRepository.findOne("usuario.id", usuario.getId()).orElseThrow(EmpresaNaoEncontradoException::new);
        String imagem = buscarImagemPerfilEmpresa.buscarUrl(empresa.getId().toString());

        return PerfilResponseDTO.builder()
                .id(empresa.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .fotoPerfil(imagem).build();
    }

    public PerfilResponseDTO buscarPerfilCandidato(Usuario usuario){
        Candidato candidato = candidatoRepository.findOne("usuario.id", usuario.getId()).orElseThrow(CandidatoNaoEncontradoException::new);
        String imagem = buscarImagemPerfilCandidato.buscarUrl(candidato.getId().toString());

        if(Objects.isNull(imagem)){
            imagem = buscarImagemPerfilCandidato.buscarUrl("foto-not-found");
        }

        return PerfilResponseDTO.builder()
                .id(candidato.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .fotoPerfil(imagem).build();
    }

}

package modules.usuarios.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.exceptions.CandidatoNaoEncontradoException;
import modules.candidatos.infra.entities.Candidato;
import modules.candidatos.repositories.CandidatoRepository;
import modules.candidatos.usecases.BuscarImagemPerfilCandidato;
import modules.empresa.exceptions.EmpresaNaoEncontradoException;
import modules.empresa.infra.entities.Empresa;
import modules.empresa.repositories.EmpresaRepository;
import modules.empresa.usecases.BuscarImagemPerfilEmpresa;
import modules.usuarios.dtos.PerfilResponseDTO;
import modules.usuarios.enumerations.TipoUsuario;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;

import java.util.ArrayList;
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

    private final BuscarTodosUsuarioProximidade buscarTodosUsuarioProximidade;

    public PerfilResponseDTO execute(UUID idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
            .orElseThrow(UsuarioNotFoundException::new);

        if (Objects.equals(usuario.getTipo(), TipoUsuario.CANDIDATO)) {
            PerfilResponseDTO dto = buscarPerfilCandidato(usuario);
            List<CondicaoPesquisa> condicaoPesquisaList = new ArrayList<>();
            CondicaoPesquisa condicaoPesquisa = new CondicaoPesquisa();
            condicaoPesquisa.setChave("tipo");
            condicaoPesquisa.setValor(TipoUsuario.CANDIDATO);
            condicaoPesquisaList.add(condicaoPesquisa);
            dto.setUsuarios(
                buscarTodosUsuarioProximidade.execute(usuario.getId(), condicaoPesquisaList, 1));

            return dto;
        }

        PerfilResponseDTO dto = buscarPerfilEmpresa(usuario);
        List<CondicaoPesquisa> condicaoPesquisaList = new ArrayList<>();
        CondicaoPesquisa condicaoPesquisa = new CondicaoPesquisa();
        condicaoPesquisa.setChave("tipo");
        condicaoPesquisa.setValor(TipoUsuario.EMPRESA);
        condicaoPesquisaList.add(condicaoPesquisa);
        dto.setUsuarios(
            buscarTodosUsuarioProximidade.execute(usuario.getId(), condicaoPesquisaList, 1));

        return dto;

    }

    private PerfilResponseDTO buscarPerfilEmpresa(Usuario usuario) {
        Empresa empresa = empresaRepository.findOne("usuario.id", usuario.getId())
            .orElseThrow(EmpresaNaoEncontradoException::new);
        String imagem = buscarImagemPerfilEmpresa.buscarUrl(empresa.getId().toString());

        if (Objects.isNull(imagem)) {
            imagem = buscarImagemPerfilEmpresa.buscarUrl("foto-not-found");
        }

        return PerfilResponseDTO.builder()
            .id(empresa.getId())
            .nome(usuario.getNome())
            .email(usuario.getEmail())
            .fotoPerfil(imagem).build();
    }

    public PerfilResponseDTO buscarPerfilCandidato(Usuario usuario) {
        Candidato candidato = candidatoRepository.findOne("usuario.id", usuario.getId())
            .orElseThrow(CandidatoNaoEncontradoException::new);
        String imagem = buscarImagemPerfilCandidato.buscarUrl(candidato.getId().toString());

        if (Objects.isNull(imagem)) {
            imagem = buscarImagemPerfilCandidato.buscarUrl("foto-not-found");
        }

        return PerfilResponseDTO.builder()
            .id(candidato.getId())
            .nome(usuario.getNome())
            .email(usuario.getEmail())
            .fotoPerfil(imagem).build();
    }

}

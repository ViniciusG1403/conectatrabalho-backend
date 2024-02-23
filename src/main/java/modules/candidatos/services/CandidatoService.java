package modules.candidatos.services;

import core.pesquisa.CondicaoPesquisa;
import core.validates.Validators;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.dtos.CandidatoResponseDTO;
import modules.candidatos.dtos.CandidatoResumidoDTO;
import modules.candidatos.usecases.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class CandidatoService extends Validators {

    private final AtualizarCandidato atualizarCandidato;

    private final CriarCandidato criarCandidato;

    private final BuscarCandidatoPeloID buscarCandidatoPeloID;

    private final BuscarCandidatos buscarCandidatos;

    private final BuscarCandidatosResumido buscarCandidatosResumido;

    public CandidatoResponseDTO criarCandidato(CandidatoDTO dto) {

        NonNullValidate(dto, "Candidato");
        NonNullValidate(dto.getUsuario(), "Usuario");
        NonNullValidate(dto.getDisponibilidade(), "Disponibilidade");
        NonNullValidate(dto.getPretensaoSalarial(), "Pretensão Salarial");

        if (Objects.isNull(dto.getUrlFotoPerfil())) {
            dto.setUrlFotoPerfil("");
        }
        return criarCandidato.execute(dto);


    }

    public void atualizarCandidato(CandidatoDTO dto) {

        NonNullValidate(dto, "Candidato");
        NonNullValidate(dto.getId(), "ID");
        NonNullValidate(dto.getUsuario(), "Usuario");
        NonNullValidate(dto.getDisponibilidade(), "Disponibilidade");
        NonNullValidate(dto.getPretensaoSalarial(), "Pretensão Salarial");

        if (Objects.isNull(dto.getUrlFotoPerfil())) {
            dto.setUrlFotoPerfil("");
        }

        atualizarCandidato.execute(dto);
    }

    public CandidatoResponseDTO buscarCandidatoPeloID(String id) {
        NonNullValidate(id, "ID");
        return buscarCandidatoPeloID.execute(UUID.fromString(id));

    }

    public List<CandidatoResumidoDTO> buscarCandidatos(List<CondicaoPesquisa> condicoes) {
        return buscarCandidatos.execute(condicoes);
    }

    public List<CandidatoResumidoDTO> buscarCandidatosResumido(List<CondicaoPesquisa> condicaoPesquisaList, int page, int size) {
        return buscarCandidatosResumido.execute(condicaoPesquisaList, page, size);
    }

}

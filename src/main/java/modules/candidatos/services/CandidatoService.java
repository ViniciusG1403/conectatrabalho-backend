package modules.candidatos.services;

import core.validates.Validators;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.usecases.AtualizarCandidato;
import modules.candidatos.usecases.BuscarCandidatoPeloID;
import modules.candidatos.usecases.CriarCandidato;

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

    public CandidatoDTO criarCandidato(CandidatoDTO dto) {

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
        try {
            NonNullValidate(dto, "Candidato");
            NonNullValidate(dto.getId(), "ID");
            NonNullValidate(dto.getUsuario(), "Usuario");
            NonNullValidate(dto.getDisponibilidade(), "Disponibilidade");
            NonNullValidate(dto.getPretensaoSalarial(), "Pretensão Salarial");

            if (Objects.isNull(dto.getUrlFotoPerfil())) {
                dto.setUrlFotoPerfil("");
            }

            atualizarCandidato.execute(dto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar candidato", e);
        }
    }

    public CandidatoDTO buscarCandidatoPeloID(String id) {
        try {
            NonNullValidate(id, "ID");
            return buscarCandidatoPeloID.execute(UUID.fromString(id));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
    }

}

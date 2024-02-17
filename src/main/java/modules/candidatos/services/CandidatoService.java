package modules.candidatos.services;

import core.validates.Validators;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.usecases.AtualizarCandidato;
import modules.candidatos.usecases.CriarCandidato;

import java.util.Objects;

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

    public CandidatoDTO criarCandidato(CandidatoDTO dto) {
        try {
            NonNullValidate(dto, "Candidato");
            NonNullValidate(dto.getUsuario(), "Usuario");
            NonNullValidate(dto.getDisponibilidade(), "Disponibilidade");
            NonNullValidate(dto.getPretensaoSalarial(), "Pretensão Salarial");

            if (Objects.isNull(dto.getUrlFotoPerfil())) {
                dto.setUrlFotoPerfil("");
            }
            return criarCandidato.execute(dto);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar candidato", e);
        }


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


}

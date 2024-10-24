package modules.candidatos.services;

import core.pesquisa.CondicaoPesquisa;
import core.validates.Validators;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.candidatos.dtos.*;
import modules.candidatos.usecases.*;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@Transactional
@ApplicationScoped
@RequiredArgsConstructor
public class CandidatoService extends Validators {

    private final AtualizarCandidato atualizarCandidato;

    private final CriarCandidato criarCandidato;

    private final BuscarCandidatoPeloID buscarCandidatoPeloID;

    private final BuscarCandidatos buscarCandidatos;

    private final BuscarCandidatoResumidoPeloID buscarCandidatoResumidoPeloID;

    private final SalvarImagemPerfilCandidato salvarImagemPerfilCandidato;

    private final BuscarImagemPerfilCandidato buscarImagemPerfilCandidato;

    private final SalvarCurriculoCandidato salvarCurriculoCandidato;

    private final BuscarCurriculoCandidato buscarCurriculoCandidato;

    public CandidatoResponseCadastroDTO criarCandidato(CandidatoCadastroDTO dto) {

        NonNullValidate(dto, "Candidato");
        NonNullValidate(dto.getIdUsuario(), "Usuario");
        NonNullValidate(dto.getDisponibilidade(), "Disponibilidade");
        NonNullValidate(dto.getPretensaoSalarial(), "Pretensão Salarial");

        return criarCandidato.execute(dto);


    }

    public void atualizarCandidato(CandidatoUpdateDTO dto) {

        NonNullValidate(dto, "Candidato");
        NonNullValidate(dto.getId(), "ID");
        NonNullValidate(dto.getDisponibilidade(), "Disponibilidade");
        NonNullValidate(dto.getPretensaoSalarial(), "Pretensão Salarial");

        atualizarCandidato.execute(dto);
    }

    public CandidatoResponseDTO buscarCandidatoPeloID(String id) {
        NonNullValidate(id, "ID");
        return buscarCandidatoPeloID.execute(UUID.fromString(id));

    }

    public List<CandidatoResumidoDTO> buscarCandidatos(List<CondicaoPesquisa> condicoes, int page, int size) {
        List<CandidatoResumidoDTO> execute = buscarCandidatos.execute(condicoes, page, size);
        return execute;
    }

    public CandidatoResumidoDTO buscarCandidatosResumido(String id) {
        return buscarCandidatoResumidoPeloID.execute(UUID.fromString(id));
    }

    public void salvarImagemPerfilCandidato(MultipartFormDataInput input) {
        salvarImagemPerfilCandidato.execute(input);
    }

    public InputStream buscarImagemPerfilCandidato(String id) {
        return buscarImagemPerfilCandidato.execute(id);
    }

    public void salvarCurriculoCandidato(MultipartFormDataInput input) {
        salvarCurriculoCandidato.execute(input);
    }

    public ResponseInputStream<GetObjectResponse> buscarCurriculoCandidato(String id) {
        return buscarCurriculoCandidato.execute(id);
    }

}

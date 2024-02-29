package modules.aplicacoes.converters;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.dtos.AplicacaoDTO;
import modules.aplicacoes.dtos.AplicacaoResponseDTO;
import modules.aplicacoes.enumerations.StatusAplicacao;
import modules.aplicacoes.infra.entities.Aplicacao;
import modules.candidatos.converters.CandidatoConverter;
import modules.vagas.converters.VagasConverter;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class AplicacaoConverter {

    private final VagasConverter vagasConverter;

    private final CandidatoConverter candidatoConverter;

    public AplicacaoDTO toDTO(Aplicacao orm){
        AplicacaoDTO dto = new AplicacaoDTO();
        dto.setId(orm.getId());
        dto.setCandidato(candidatoConverter.toDTO(orm.getCandidato()));
        dto.setVaga(vagasConverter.toDTO(orm.getVaga()));
        dto.setDataAplicacao(orm.getDataAplicacao());
        dto.setStatus(StatusAplicacao.valueOf(orm.getStatus()));
        dto.setFeedbackCandidato(orm.getFeedbackCandidato());
        dto.setFeedbackEmpresa(orm.getFeedbackEmpresa());

        return dto;
    }

    public Aplicacao toEntity(AplicacaoDTO dto){
        Aplicacao orm = new Aplicacao();
        orm.setId(dto.getId());
        orm.setCandidato(candidatoConverter.toEntity(dto.getCandidato()));
        orm.setVaga(vagasConverter.toEntity(dto.getVaga()));
        orm.setDataAplicacao(dto.getDataAplicacao());
        orm.setStatus(StatusAplicacao.valueOf(dto.getStatus()));
        orm.setFeedbackCandidato(dto.getFeedbackCandidato());
        orm.setFeedbackEmpresa(dto.getFeedbackEmpresa());

        return orm;
    }

    public AplicacaoResponseDTO toRespondeDTO(Aplicacao orm){
        AplicacaoResponseDTO dto = new AplicacaoResponseDTO();
        dto.setDescricaoVaga(orm.getVaga().getDescricao());
        dto.setDataAplicacao(orm.getDataAplicacao());
        dto.setNomeEmpresa(orm.getVaga().getEmpresa().getUsuario().getNome());
        dto.setStatusAplicacao(StatusAplicacao.valueOf(orm.getStatus()));

        return dto;
    }


}

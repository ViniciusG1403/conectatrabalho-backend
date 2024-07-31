package modules.aplicacoes.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/24
 */
@Getter
@Setter
@RequiredArgsConstructor
public class AplicacaoResponseDTO {

    private String id;

    private String descricaoVaga;

    private Timestamp dataAplicacao;

    private String nomeEmpresa;

    private Integer statusAplicacao;

    private String nomeCandidato;


}

package modules.vagas.dtos;

import jakarta.persistence.*;
import modules.empresa.dtos.EmpresaDTO;
import modules.empresa.infra.entities.Empresa;
import modules.vagas.enumerations.StatusVaga;
import modules.vagas.enumerations.TipoVaga;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
public class VagasDTO {

    @Schema(description = "Identificador da vaga", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Empresa que criou a vaga", required = true)
    private EmpresaDTO empresaPerfil;

    @Schema(description = "Título da vaga", required = true, example = "Desenvolvedor Java")
    private String titulo;

    @Schema(description = "Descrição da vaga", required = true, example = "Desenvolvimento de aplicações Java")
    private String descricao;

    @Schema(description = "Remuneração da vaga", required = true, example = "5000.00")
    private BigDecimal remuneracao;

    @Schema(description = "Tipo da vaga", required = true, example = "1")
    private Integer tipo;

    @Schema(description = "Nível da vaga", required = true, example = "Pleno")
    private String nivel;

    @Schema(description = "Status da vaga", required = true, example = "1")
    private Integer status;

    @Schema(description = "Data de criação da vaga", required = true, example = "2024-02-25 00:00:00")
    private Timestamp dataCriacao;

}

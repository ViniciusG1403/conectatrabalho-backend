package modules.vagas.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import modules.empresa.infra.entities.Empresa;
import modules.vagas.enumerations.StatusVaga;
import modules.vagas.enumerations.TipoVaga;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
@Getter
@Setter
@Entity
@Table(name = "vagas")
public class Vagas {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_perfilempresa", nullable = false)
    private Empresa empresa;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "remuneracao", nullable = false)
    private BigDecimal remuneracao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo", nullable = false)
    private TipoVaga tipo;

    @Column(name = "nivel", nullable = false)
    private String nivel;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", nullable = false)
    private StatusVaga status;

    @Column(name = "data_criacao", nullable = false)
    private Timestamp dataCriacao;

}

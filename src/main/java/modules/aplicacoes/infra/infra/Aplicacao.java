package modules.aplicacoes.infra.infra;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import modules.aplicacoes.enumerations.StatusAplicacao;
import modules.candidatos.infra.entities.Candidato;
import modules.vagas.infra.entities.Vagas;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/2024
 */
@Getter
@Setter
@Entity
@Table(name = "aplicacoes")
public class Aplicacao {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @JoinColumn(name = "id_vaga")
    @ManyToOne(fetch = FetchType.LAZY)
    private Vagas vaga;

    @JoinColumn(name = "id_candidato")
    @ManyToOne(fetch = FetchType.LAZY)
    private Candidato candidato;

    @Column(name = "data_aplicacao")
    private Timestamp dataAplicacao;

    @Column(name = "feedbackcandidato")
    private String feedbackCandidato;

    @Column(name = "feedbackempresa")
    private String feedbackEmpresa;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private StatusAplicacao status;

}

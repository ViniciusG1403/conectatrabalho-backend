package modules.candidatos.infra.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import modules.usuarios.infra.entities.Usuario;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@Getter
@Setter
@Entity
@Table(name = "candidato")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "habilidades")
    private String habilidades;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "github")
    private String github;

    @Column(name = "portfolio")
    private String portfolio;

    @Column(name = "disponibilidade")
    private String disponibilidade;

    @Column(name = "pretensao_salarial")
    private BigDecimal pretensaoSalarial;

}

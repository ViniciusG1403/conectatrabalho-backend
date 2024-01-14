package modules.users.structure.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
@Entity
@Getter
@Setter
@Table(name = "worker_profile")
public class WorkerProfile extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @JoinColumn(name = "id_user")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "profession", nullable = false)
    private String profession;

    @Column(name = "nr_telefone", nullable = false)
    private String nrTelefone;

    @Column(name = "sector", nullable = false)
    private String sector;

    @Column(name = "habilities", nullable = false)
    private String habilities;

    @Column(name = "curriculum_url")
    private String curriculumUrl;

    @Column(name = "portfolio_url")
    private String portfolioUrl;

    @Column(name = "img_profile_url", nullable = false)
    private String imgProfileUrl;
}

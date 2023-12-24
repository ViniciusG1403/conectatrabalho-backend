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
@Table(name = "contractor_profile")
public class ContractorProfile extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @JoinColumn(name = "id_user")
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "enterprise", nullable = false)
    private String enterprise;

    @Column(name = "sector", nullable = false)
    private String sector;

    @Column(name = "localization", nullable = false)
    private String localization;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "website", nullable = false)
    private String website;
}

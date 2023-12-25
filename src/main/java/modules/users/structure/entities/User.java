package modules.users.structure.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import modules.users.enumerations.UserRoles;
import modules.users.enumerations.UserStatus;
import modules.users.enumerations.UserType;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 23/12/23
 */
@Entity
@Getter
@Setter
@Table(name = "user")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "email", nullable = false, length = 60)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "dh_register", nullable = false)
    private Timestamp dhRegister;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @Column(name = "lastlogin")
    private Timestamp lastLogin;

    @Column(name = "lastUpdate")
    private Timestamp lastUpdate;

    @Column(name = "code", length = 6)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRoles role;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserType type;
}

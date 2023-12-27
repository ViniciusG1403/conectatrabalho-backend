package modules.users.structure.entities;

import core.shared.PostgreSQLEnumType;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import modules.users.enumerations.UserRoles;
import modules.users.enumerations.UserStatus;
import modules.users.enumerations.UserType;
import org.hibernate.annotations.Type;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 23/12/23
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "app_users")
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

    @Type(PostgreSQLEnumType.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    @Column(name = "lastlogin")
    private Timestamp lastLogin;

    @Column(name = "lastUpdate")
    private Timestamp lastUpdate;

    @Column(name = "code", length = 6)
    private String code;

    @Type(PostgreSQLEnumType.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRoles role;

    @Type(PostgreSQLEnumType.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserType type;
}

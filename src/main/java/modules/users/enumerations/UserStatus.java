package modules.users.enumerations;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
public enum UserStatus {

    INACTIVE, ACTIVE;

    public static UserStatus valueOf(Integer value) {
        return value == null ? null : values()[value];
    }

    public static Integer valueOf(final UserStatus value) {
        return value != null ? value.ordinal() : null;
    }
}


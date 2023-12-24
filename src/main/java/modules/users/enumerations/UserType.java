package modules.users.enumerations;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
public enum UserType {
    CONTRACTOR, PROVIDER, BOTH, ADMINISTRATOR;

    public static UserType valueOf(Integer value) {
        return value == null ? null : values()[value];
    }

    public static Integer valueOf(final UserType value) {
        return value != null ? value.ordinal() : null;
    }
}

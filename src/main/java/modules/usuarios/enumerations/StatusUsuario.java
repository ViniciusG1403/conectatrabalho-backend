package modules.usuarios.enumerations;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 13/02/24
 */
public enum StatusUsuario {
    INATIVO, ATIVO, BLOQUEADO, EXCLUIDO;

    public static StatusUsuario valueOf(Integer value) {
        return value == null ? null : values()[value];
    }

    public static Integer valueOf(final StatusUsuario value) {
        return value != null ? value.ordinal() : null;
    }

}

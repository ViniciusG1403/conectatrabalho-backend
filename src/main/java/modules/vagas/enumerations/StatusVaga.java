package modules.vagas.enumerations;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
public enum StatusVaga {

    INATIVA, ATIVA, PAUSADA, ENCERRADA;
    public static StatusVaga valueOf(Integer value) {
        return value == null ? null : values()[value];
    }

    public static Integer valueOf(final StatusVaga value) {
        return value != null ? value.ordinal() : null;
    }

}

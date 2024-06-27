package modules.aplicacoes.enumerations;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/02/2024
 */
public enum StatusAplicacao {


    PENDENTE, APROVADO, REPROVADO, CANCELADO;
    public static StatusAplicacao valueOf(Integer value) {
        return value == null ? null : values()[value];
    }

    public static Integer valueOf(final StatusAplicacao value) {
        return value != null ? value.ordinal() : null;
    }
}

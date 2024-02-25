package modules.vagas.enumerations;

import modules.usuarios.enumerations.StatusUsuario;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/02/24
 */
public enum TipoVaga {

    CLT, PJ, ESTAGIO, TRAINEE;
    public static TipoVaga valueOf(Integer value) {
        return value == null ? null : values()[value];
    }

    public static Integer valueOf(final TipoVaga value) {
        return value != null ? value.ordinal() : null;
    }

}

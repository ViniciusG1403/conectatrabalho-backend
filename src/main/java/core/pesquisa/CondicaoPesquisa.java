package core.pesquisa;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 15/02/2024
 */
public class CondicaoPesquisa {

    /**
     * Chave
     */
    private String chave;

    /**
     * Operação
     */
    private String operacao;

    /**
     * Valor
     */
    private Object valor;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    /**
     * Construtor.
     */
    public CondicaoPesquisa() {

    }

    /**
     * Construtor.
     * @param chave
     * @param operacao
     * @param valor
     */
    public CondicaoPesquisa(String chave, String operacao, Object valor) {
        super();
        this.chave = chave;
        this.operacao = operacao;
        this.valor = valor;
    }

    public CondicaoPesquisa(final String chave, final Object valor) {
        this.chave = chave;
        this.operacao = "=";
        this.valor = valor;
    }

    /**
     * hashcode.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((chave == null) ? 0 : chave.hashCode());
        result = prime * result + ((operacao == null) ? 0 : operacao.hashCode());
        result = prime * result + ((valor == null) ? 0 : valor.hashCode());
        return result;
    }

    /**
     * Equals.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CondicaoPesquisa other = (CondicaoPesquisa) obj;
        if (chave == null) {
            if (other.chave != null)
                return false;
        } else if (!chave.equals(other.chave))
            return false;
        if (operacao == null) {
            if (other.operacao != null)
                return false;
        } else if (!operacao.equals(other.operacao))
            return false;
        if (valor == null) {
            if (other.valor != null)
                return false;
        } else if (!valor.equals(other.valor))
            return false;
        return true;
    }
}
package core.exceptions;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 15/02/2024
 */
public class ConectaTrabalhoException extends RuntimeException{

    public ConectaTrabalhoException(Exception e)  {super("Ocorreu um erro: " + e); }
}

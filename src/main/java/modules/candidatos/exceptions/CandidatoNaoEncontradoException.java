package modules.candidatos.exceptions;

import jakarta.ws.rs.NotFoundException;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
public class CandidatoNaoEncontradoException extends NotFoundException {


    public CandidatoNaoEncontradoException() {super ("Candidato não encontrado!");}

}

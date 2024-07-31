package modules.aplicacoes.usecases;

import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.converters.AplicacaoConverter;
import modules.aplicacoes.dtos.AplicacaoDTO;
import modules.aplicacoes.dtos.AplicacaoResponseDTO;
import modules.aplicacoes.enumerations.StatusAplicacao;
import modules.aplicacoes.exceptions.AplicacaoNaoEncontradaExceptions;
import modules.aplicacoes.repositories.AplicacaoRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 02/03/24
 */
@ApplicationScoped
@RequiredArgsConstructor
public class BuscarAplicacaoPorId {

    private final AplicacaoRepository aplicacaoRepository;

    private final AplicacaoConverter aplicacaoConverter;

    public AplicacaoDTO execute(UUID idaplicacao){

        return aplicacaoRepository.findById(idaplicacao).stream().map(aplicacaoConverter::toDTO).findFirst().orElseThrow(AplicacaoNaoEncontradaExceptions::new);
    }

}

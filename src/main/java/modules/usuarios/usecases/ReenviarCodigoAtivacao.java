package modules.usuarios.usecases;

import core.emailservice.MessageOperation;
import core.emailservice.SendEmailService;
import core.pesquisa.CondicaoPesquisa;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.ReenviarCodigoAtivacaoDTO;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.exceptions.UsuarioNotFoundException;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.repositories.UsuarioRepository;
import modules.usuarios.services.UsuarioService;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 05/03/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
public class ReenviarCodigoAtivacao {

    private final UsuarioRepository repository;

    private final SendEmailService sendEmailService;

    private final GenerateRandomCode generateRandomCode;

    private final UsuarioConverter converter;

    public void execute(ReenviarCodigoAtivacaoDTO reenviar) {
        List<CondicaoPesquisa> condicaoPesquisaList = List.of(
            new CondicaoPesquisa("email", reenviar.getEmail())
        );
        Usuario usuario = repository.findOne(condicaoPesquisaList).orElseThrow(UsuarioNotFoundException::new);

        String codigo = generateRandomCode.execute();

        usuario.setCodigo(codigo);

        repository.update(usuario);

        sendEmailService.sendMail(converter.toDTO(usuario), "Reenvio de código de ativação", MessageOperation.ATIVACAO);
    }


}

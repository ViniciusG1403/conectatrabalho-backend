package modules.aplicacoes.infra.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.dtos.AplicacaoCadastroDTO;
import modules.aplicacoes.services.AplicacaoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 02/03/2024
 */
@Path("/aplicacao")
@RequiredArgsConstructor
public class AplicacaoResource {

    private final AplicacaoService aplicacaoService;


    @POST
    @Path("/aplicar")
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @APIResponse(responseCode = "201", description = "Aplicação realizada com sucesso")
    @Operation(summary = "Aplicar", description = "Aplica para uma vaga")
    public Response aplicarParaVaga(AplicacaoCadastroDTO dto) {
        aplicacaoService.aplicarParaVaga(dto);
        return Response.status(Response.Status.CREATED).entity("Aplicação realizada com sucesso, boa sorte").build();
    }


}

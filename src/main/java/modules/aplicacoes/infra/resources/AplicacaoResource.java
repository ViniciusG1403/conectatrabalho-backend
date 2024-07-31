package modules.aplicacoes.infra.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import modules.aplicacoes.dtos.AplicacaoCadastroDTO;
import modules.aplicacoes.dtos.AplicacaoFeedbackDTO;
import modules.aplicacoes.services.AplicacaoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.UUID;

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
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @APIResponse(responseCode = "201", description = "Aplicação realizada com sucesso")
    @Operation(summary = "Aplicar", description = "Aplica para uma vaga")
    public Response aplicarParaVaga(AplicacaoCadastroDTO dto) {
        aplicacaoService.aplicarParaVaga(dto);
        return Response.status(Response.Status.CREATED)
                .entity("Aplicação realizada com sucesso, boa sorte")
                .build();
    }

    @POST
    @Path("/cancelar")
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @APIResponse(responseCode = "200", description = "Cancelamento realizada")
    @Operation(summary = "Cancelar", description = "Cancela a aplicação de uma vaga")
    public Response cancelarParaVaga(AplicacaoCadastroDTO dto) {
        aplicacaoService.cancelarAplicacao(dto);
        return Response.status(Response.Status.OK).entity("Cancelamento realizado com sucesso.").build();
    }

    @PUT
    @Path("{id}/aprovar")
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @APIResponse(responseCode = "200", description = "Candidato aprovado")
    @Operation(summary = "Aprovar", description = "Aprova um candidato")
    public Response aprovarCandidato(@PathParam("id") String aplicacaoId) {
        aplicacaoService.aprovarCandidato(UUID.fromString(aplicacaoId));
        return Response.status(Response.Status.OK).entity("Candidato aprovado").build();
    }

    @PUT
    @Path("{id}/reprovar")
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @APIResponse(responseCode = "200", description = "Candidato reprovado")
    @Operation(summary = "Reprovar", description = "Reprova um candidato")
    public Response reprovarCandidato(@PathParam("id") String aplicacaoId) {
        aplicacaoService.reprovarCandidato(UUID.fromString(aplicacaoId));
        return Response.status(Response.Status.OK).entity("Candidato reprovado").build();
    }

    @PUT
    @Path("/feedback")
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @APIResponse(responseCode = "200", description = "Feedback realizado")
    @Operation(summary = "Feedback", description = "Realiza o feedback")
    public Response feedback(AplicacaoFeedbackDTO feedback) {
        aplicacaoService.darFeedbackAplicacao(feedback);
        return Response.status(Response.Status.OK).entity("Feedback realizado").build();
    }

    @GET
    @Path("/{id}/candidato")
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @APIResponse(responseCode = "200", description = "Aplicações do candidato")
    @Operation(summary = "Aplicações do candidato", description = "Retorna todas as aplicações do candidato")
    public Response buscarTodasAplicacoesCandidato(@PathParam("id") String idCandidato) {
        return Response.status(Response.Status.OK)
                .entity(aplicacaoService.buscarTodasAplicacoesCandidato(UUID.fromString(idCandidato)))
                .build();
    }

    @GET
    @Path("/{id}/{idEmpresa}/empresa")
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @APIResponse(responseCode = "200", description = "Aplicações da empresa")
    @Operation(summary = "Aplicações da empresa", description = "Retorna todas as aplicações da empresa")
    public Response buscarTodasAplicacoesVaga(@PathParam("id") String idVaga, @PathParam("idEmpresa") String idEmpresa) {
        return Response.status(Response.Status.OK)
                .entity(aplicacaoService.buscarTodasAplicacoesVaga(UUID.fromString(idVaga), UUID.fromString(idEmpresa)))
                .build();
    }


    @GET
    @Path("/{id}")
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @APIResponse(responseCode = "200", description = "Aplicações da empresa")
    @Operation(summary = "Aplicações da empresa", description = "Retorna todas as aplicações da empresa")
    public Response buscarAplicacaoId(@PathParam("id") String idAplicacao) {
        return Response.status(Response.Status.OK)
                .entity(aplicacaoService.findById(idAplicacao))
                .build();
    }
}

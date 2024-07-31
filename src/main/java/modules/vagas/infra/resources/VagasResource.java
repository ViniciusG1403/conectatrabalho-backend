package modules.vagas.infra.resources;

import core.pesquisa.CondicaoPesquisa;
import core.pesquisa.PrepararFiltros;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import modules.vagas.dtos.FinalizarPausarVagaDTO;
import modules.vagas.dtos.VagasCadastroDTO;
import modules.vagas.dtos.VagasDTO;
import modules.vagas.dtos.VagasResumidoDTO;
import modules.vagas.services.VagasService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 27/02/2024
 */
@ApplicationScoped
@RequiredArgsConstructor
@Path("/vagas")
public class VagasResource {

    private final VagasService vagasService;

    private final PrepararFiltros prepararFiltros;


    @POST
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "201", description = "Vaga criada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = VagasCadastroDTO.class)))
    @Operation(summary = "Criar", description = "Cria uma vaga")
    public Response criar(VagasCadastroDTO dto) {
        return Response.status(Response.Status.CREATED).entity(vagasService.criarVaga(dto)).build();
    }

    @PUT
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Vaga atualizada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = VagasCadastroDTO.class)))
    @Operation(summary = "Atualizar", description = "Atualiza uma vaga")
    public Response atualizar(VagasCadastroDTO dto) {
        vagasService.atualizarVaga(dto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Vaga encontrada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = VagasDTO.class)))
    @Operation(summary = "Buscar", description = "Busca uma vaga pelo ID")
    public Response buscarPeloID(@PathParam("id") String id) {
        return Response.status(Response.Status.OK).entity(vagasService.buscarVagaPeloID(id)).build();
    }

    @GET
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Vagas encontradas com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = VagasResumidoDTO.class)))
    @Operation(summary = "Buscar", description = "Busca todas as vagas")
    public Response buscarTodasVagas(@QueryParam("search") String search, @QueryParam("page") int page, @QueryParam("size") int size) {
        final List<CondicaoPesquisa> condicaoPesquisaList = prepararFiltros.execute(search);
        return Response.status(Response.Status.OK).entity(vagasService.buscarTodasVagas(condicaoPesquisaList, page, size)).build();
    }

    @PUT
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Path("/finalizar")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Vaga finalizada com sucesso")
    @Operation(summary = "Finalizar", description = "Finaliza uma vaga")
    public Response finalizarVaga(FinalizarPausarVagaDTO dto) {
        vagasService.finalizarVaga(dto);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Path("/pausar")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Vaga pausada com sucesso")
    @Operation(summary = "Pausar", description = "Pausa uma vaga")
    public Response pausarVaga(FinalizarPausarVagaDTO dto) {
        vagasService.pausarVaga(dto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Path("/{id}/proximidade")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Vagas encontradas com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = VagasResumidoDTO.class)))
    @Operation(summary = "Buscar", description = "Busca todas as vagas por proximidade")
    public Response buscarVagasPorProximidade(@PathParam("id") final String uuid, @QueryParam("search") String search, @QueryParam("page") int page, @QueryParam("size") int size, @QueryParam("distanciaMaxima") int distanciaMaxima) {
        final List<CondicaoPesquisa> condicaoPesquisaList = prepararFiltros.execute(search);
        return Response.status(Response.Status.OK).entity(vagasService.buscarVagasPorProximidade(uuid, condicaoPesquisaList, page, size, distanciaMaxima)).build();
    }

    @GET
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Path("/{idVaga}/{idUsuario}/verifica-duplicidade-aplicacao")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificarSeUsuarioJaAplicou(@PathParam("idVaga") final String idVaga, @PathParam("idUsuario") final String idUsuario){
        return Response.status(Response.Status.OK).entity(vagasService.verificarSeUsuarioJaAplicou(idVaga, idUsuario)).build();
    }

    @GET
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Path("/empresa/{idEmpresa}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarVagaPorEmpresa(@PathParam("idEmpresa") final String idEmpresa){
        return Response.status(Response.Status.OK).entity(vagasService.buscarVagaPorEmpresa(idEmpresa)).build();
    }
}

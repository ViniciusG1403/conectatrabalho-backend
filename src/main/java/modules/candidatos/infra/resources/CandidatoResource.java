package modules.candidatos.infra.resources;

import core.pesquisa.CondicaoPesquisa;
import core.pesquisa.PrepararFiltros;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import modules.candidatos.dtos.CandidatoCadastroDTO;
import modules.candidatos.dtos.CandidatoDTO;
import modules.candidatos.dtos.CandidatoResumidoDTO;
import modules.candidatos.dtos.CandidatoUpdateDTO;
import modules.candidatos.services.CandidatoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 17/02/2024
 */
@Path("/candidato")
@RequiredArgsConstructor
public class CandidatoResource {

    private final CandidatoService candidatoService;

    private final PrepararFiltros prepararFiltros;

    @POST
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "201", description = "Candidato criado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CandidatoCadastroDTO.class)))
    @Operation(summary = "Criar", description = "Cria um candidato")
    public Response criar(CandidatoCadastroDTO candidatoDTO) {
        return Response.status(Response.Status.CREATED)
            .entity(candidatoService.criarCandidato(candidatoDTO))
            .build();
    }

    @PUT
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Candidato atualizado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CandidatoUpdateDTO.class)))
    @Operation(summary = "Atualizar", description = "Atualiza um candidato")
    public Response atualizar(CandidatoUpdateDTO candidatoDTO) {
        candidatoService.atualizarCandidato(candidatoDTO);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Candidato encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CandidatoDTO.class)))
    @APIResponse(responseCode = "404", description = "Candidato não encontrado")
    @Operation(summary = "Buscar", description = "Busca um candidato pelo ID")
    public Response buscar(@PathParam("id") String id) {
        return Response.status(Response.Status.OK)
            .entity(candidatoService.buscarCandidatoPeloID(id))
            .build();
    }

    @GET
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Candidato encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CandidatoDTO.class)))
    @APIResponse(responseCode = "404", description = "Candidato não encontrado")
    @Operation(summary = "Buscar", description = "Busca uma lista de candidatos")
    public Response buscarTodos(@QueryParam("search") String search,
        @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<CondicaoPesquisa> condicaoPesquisaList = prepararFiltros.execute(search);
        return Response.status(Response.Status.OK)
            .entity(candidatoService.buscarCandidatos(condicaoPesquisaList, page, size))
            .build();
    }

    @GET
    @Path("/{id}/resumido")
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Candidato encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = CandidatoResumidoDTO.class)))
    @APIResponse(responseCode = "404", description = "Candidato não encontrado")
    @Operation(summary = "Buscar", description = "Busca uma lista de candidatos resumido")
    public Response buscarResumido(@PathParam("id") String id) {
        return Response.status(Response.Status.OK)
            .entity(candidatoService.buscarCandidatosResumido(id))
            .build();
    }

    @PUT
    @Path("/salvar-imagem")
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @APIResponse(responseCode = "200", description = "Imagem salva com sucesso")
    @Operation(summary = "Salvar imagem", description = "Salva a imagem de perfil do candidato")
    public Response salvarImagem(MultipartFormDataInput input) {
        candidatoService.salvarImagemPerfilCandidato(input);
        return Response.status(Response.Status.OK).entity("Imagem de perfil salva com sucesso.").build();
    }

    @GET
    @Path("/{id}/imagem-perfil")
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    public Response buscarImagemPerfil(@PathParam("id") String id) {
        return Response.status(Response.Status.OK)
            .entity(candidatoService.buscarImagemPerfilCandidato(id))
            .build();
    }


}

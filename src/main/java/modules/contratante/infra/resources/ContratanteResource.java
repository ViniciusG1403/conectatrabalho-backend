package modules.contratante.infra.resources;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import modules.contratante.dtos.ContratanteRegisterDTO;
import modules.contratante.dtos.ContratanteResponseDTO;
import modules.contratante.dtos.ContratanteUpdateDTO;
import modules.contratante.usecases.BuscarContratantePeloID;
import modules.contratante.usecases.BuscarContratantes;
import modules.contratante.usecases.CriarContratante;
import modules.contratante.usecases.UpdateContratante;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@Path("/contratante")
@RequiredArgsConstructor
public class ContratanteResource {

    private final CriarContratante criarContratante;

    private final UpdateContratante updateContratante;

    private final BuscarContratantePeloID buscarContratantePeloID;

    private final BuscarContratantes buscarContratantes;

    @POST
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "201", description = "Contratante criado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ContratanteRegisterDTO.class)))
    @Operation(summary = "Criar", description = "Cria um contratante")
    public Response criar(ContratanteRegisterDTO dto){
        return Response.status(Response.Status.CREATED).entity(criarContratante.execute(dto)).build();
    }

    @PUT
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Contratante atualizado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ContratanteUpdateDTO.class)))
    @Operation(summary = "Atualizar", description = "Atualiza um contratante")
    public Response atualizar(ContratanteUpdateDTO dto){
        updateContratante.execute(dto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Contratante encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ContratanteResponseDTO.class)))
    @Operation(summary = "Buscar", description = "Busca um contratante pelo ID")
    public Response buscarPeloID(@PathParam("id") String id){
        return Response.status(Response.Status.OK).entity(buscarContratantePeloID.execute(UUID.fromString(id))).build();
    }

    @GET
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Contratantes encontrados com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = ContratanteResponseDTO.class)))
    @Operation(summary = "Buscar", description = "Busca todos os contratantes")
    public Response buscarTodos(){
        return Response.status(Response.Status.OK).entity(buscarContratantes.execute()).build();
    }


}

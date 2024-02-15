package modules.usuarios.infra.resources;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.services.UsuarioService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 14/02/24
 */
@Path("/usuario")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;

    @POST
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "201", description = "Usuário criado/atualizado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Usuario.class)))
    @Operation(summary = "Criar ou Atualizar", description = "Cria ou atualiza um usuário")
    public Response criarOuAtualizar(UsuarioDTO dto){
        return Response.status(Response.Status.CREATED).entity(usuarioService.criarOuAtualizar(dto)).build();
    }

    @GET
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Usuario.class)))
    @Operation(summary = "Buscar pelo ID", description = "Busca um usuário pelo ID")
    public Response buscarPeloId(@PathParam("id") String id){
        return Response.status(Response.Status.OK).entity(usuarioService.buscarPeloId(id)).build();
    }

    @PUT
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @Path("/ativar/{codigo}")
    @APIResponse(responseCode = "204", description = "Usuário ativado com sucesso")
    @Operation(summary = "Ativar", description = "Ativa um usuário")
    public Response ativarUsuario(@PathParam("codigo") String codigo){
        usuarioService.ativarUsuario(codigo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
    @Path("/inativar/{codigo}")
    @APIResponse(responseCode = "204", description = "Usuário inativado com sucesso")
    @Operation(summary = "Inativar", description = "Inativa um usuário")
    public Response inativarUsuario(@PathParam("codigo") String codigo){
        usuarioService.inativarUsuario(codigo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @RolesAllowed({"ROLE_ADMIN"})
    @Path("/bloquear/{id}")
    @APIResponse(responseCode = "204", description = "Usuário bloqueado com sucesso")
    @Operation(summary = "Bloquear", description = "Bloqueia um usuário")
    public Response bloquearUsuario(@PathParam("id") String id){
        usuarioService.bloquearUsuario(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
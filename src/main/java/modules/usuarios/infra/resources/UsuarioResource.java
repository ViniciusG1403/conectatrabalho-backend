package modules.usuarios.infra.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.geolocalizador.GetCoordenadasGeograficas;
import core.pesquisa.CondicaoPesquisa;
import core.pesquisa.PrepararFiltros;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import modules.usuarios.converters.UsuarioConverter;
import modules.usuarios.dtos.PerfilResponseDTO;
import modules.usuarios.dtos.ReenviarCodigoAtivacaoDTO;
import modules.usuarios.dtos.UsuarioDTO;
import modules.usuarios.dtos.UsuarioRegistroDTO;
import modules.usuarios.infra.entities.Usuario;
import modules.usuarios.services.UsuarioService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 14/02/24
 */
@Path("/usuario")
@RequiredArgsConstructor
public class UsuarioResource {

    private final UsuarioService usuarioService;

    private final UsuarioConverter converter;

    private final PrepararFiltros prepararFiltros;

    @POST
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "201", description = "Usuário criado/atualizado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UsuarioRegistroDTO.class)))
    @Operation(summary = "Criar ou Atualizar", description = "Cria ou atualiza um usuário")
    public Response criarOuAtualizar(UsuarioRegistroDTO registroDTO) throws JsonProcessingException {
        UsuarioDTO dto = converter.registroToDTO(registroDTO);
        return Response.status(Response.Status.CREATED).entity(usuarioService.criarOuAtualizar(dto)).build();
    }


    @POST
    @Path("/batch")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "201", description = "Usuário criado/atualizado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UsuarioRegistroDTO.class)))
    @Operation(summary = "Criar ou Atualizar", description = "Cria ou atualiza um usuário")
    public Response criarOuAtualizarBatch(List<UsuarioRegistroDTO> registroDTO) {
        registroDTO.forEach(dto -> {
            usuarioService.criarOuAtualizar(converter.registroToDTO(dto));
        });
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @RolesAllowed({"ADMIN_ROLE"})
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Usuários encontrados com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Usuario.class)))
    @Operation(summary = "Buscar todos", description = "Busca todos os usuários")
    public Response buscarTodos(){
        return Response.status(Response.Status.OK).entity(usuarioService.buscarTodosUsuarios()).build();
    }

    @GET
    @Path("/ativos")
    @RolesAllowed({"ADMIN_ROLE"})
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Usuários encontrados com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Usuario.class)))
    @Operation(summary = "Buscar todos", description = "Busca todos os usuários")
    public Response buscarTodosAtivos(){
        return Response.status(Response.Status.OK).entity(usuarioService.buscarTodosUsuariosAtivos()).build();
    }

    @GET
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Usuario.class)))
    @Operation(summary = "Buscar pelo ID", description = "Busca um usuário pelo ID")
    public Response buscarPeloId(@PathParam("id") String id){
        return Response.status(Response.Status.OK).entity(usuarioService.buscarPeloId(id)).build();
    }

    @PUT
    @PermitAll
    @Path("/ativar/{codigo}")
    @APIResponse(responseCode = "204", description = "Usuário ativado com sucesso")
    @Operation(summary = "Ativar", description = "Ativa um usuário")
    public Response ativarUsuario(@PathParam("codigo") String codigo){
        usuarioService.ativarUsuario(codigo);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Path("/inativar/{codigo}")
    @APIResponse(responseCode = "204", description = "Usuário inativado com sucesso")
    @Operation(summary = "Inativar", description = "Inativa um usuário")
    public Response inativarUsuario(@PathParam("codigo") String codigo){
        usuarioService.inativarUsuario(codigo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @RolesAllowed({"ADMIN_ROLE"})
    @Path("/bloquear/{id}")
    @APIResponse(responseCode = "204", description = "Usuário bloqueado com sucesso")
    @Operation(summary = "Bloquear", description = "Bloqueia um usuário")
    public Response bloquearUsuario(@PathParam("id") String id){
        usuarioService.bloquearUsuario(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    @PermitAll
    @Path("/reenviar-codigo")
    @APIResponse(responseCode = "200", description = "Código reenviado com sucesso")
    @Operation(summary = "Reenviar código de ativação", description = "Reenvia o código de ativação para o usuário")
    public Response reenviarCodigo(ReenviarCodigoAtivacaoDTO reenviarDTO){
        usuarioService.reenviarCodigoAtivacao(reenviarDTO);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Path("/{id}/tem-perfil-cadastrado")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Usuário tem perfil cadastrado", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Boolean.class)))
    @Operation(summary = "Tem perfil cadastrado", description = "Verifica se o usuário tem perfil cadastrado")
    public Response temPerfilCadastrado(@PathParam("id") String id){
        return Response.status(Response.Status.OK).entity(usuarioService.usuarioTemPerfilCadastrado(UUID.fromString(id))).build();
    }

    @GET
    @RolesAllowed({"USER_ROLE", "ADMIN_ROLE"})
    @Path("/{id}/perfil")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Perfil encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PerfilResponseDTO.class)))
    @Operation(summary = "Buscar perfil", description = "Busca o perfil do usuário")
    public Response buscarPerfilPeloUsuario(@PathParam("id") String id){
        return Response.status(Response.Status.OK).entity(usuarioService.buscarPerfilPeloUsuario(UUID.fromString(id))).build();
    }

    @GET
    @PermitAll
    @Path("/buscar-todos/localizacao/{id}")
    public Response buscarUsuariosPelaLocalizacao(@PathParam("id") String id, @QueryParam("search") String search, @QueryParam("page") Integer page){
        List<CondicaoPesquisa> condicaoPesquisaList = prepararFiltros.execute(search);
        return Response.status(Response.Status.OK).entity(usuarioService.buscarTodosUsuariosProximidade(id, condicaoPesquisaList, page)).build();
    }
}

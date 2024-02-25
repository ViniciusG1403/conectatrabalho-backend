package modules.empresa.infra.resources;

import core.pesquisa.CondicaoPesquisa;
import core.pesquisa.PrepararFiltros;
import core.shared.ProcessImageService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import modules.empresa.dtos.EmpresaRegisterDTO;
import modules.empresa.dtos.EmpresaResponseDTO;
import modules.empresa.dtos.EmpresaResumidoDTO;
import modules.empresa.dtos.EmpresaUpdateDTO;
import modules.empresa.services.EmpresaService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.util.List;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 20/02/2024
 */
@Path("/empresa")
@RequiredArgsConstructor
public class EmpresaResource {

    private final EmpresaService service;

    private final PrepararFiltros prepararFiltros;

    @POST
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "201", description = "Empresa criado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EmpresaRegisterDTO.class)))
    @Operation(summary = "Criar", description = "Cria uma empresa")
    public Response criar(EmpresaRegisterDTO dto) {
        return Response.status(Response.Status.CREATED)
            .entity(service.criar(dto))
            .build();
    }

    @PUT
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Empresa atualizado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EmpresaUpdateDTO.class)))
    @Operation(summary = "Atualizar", description = "Atualiza uma empresa")
    public Response atualizar(EmpresaUpdateDTO dto) {
        service.atualizar(dto);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Empresa encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EmpresaResponseDTO.class)))
    @Operation(summary = "Buscar", description = "Busca uma empresa pelo ID")
    public Response buscarPeloID(@PathParam("id") String id) {
        return Response.status(Response.Status.OK)
            .entity(service.buscarPeloID(id))
            .build();
    }

    @GET
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Empresas encontrados com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EmpresaResponseDTO.class)))
    @Operation(summary = "Buscar", description = "Busca todas as empresas")
    public Response buscarTodos(@QueryParam("search") String search,
        @QueryParam("page") Integer page, @QueryParam("size") Integer size) {
        List<CondicaoPesquisa> condicaoPesquisa = prepararFiltros.execute(search);

        return Response.status(Response.Status.OK)
            .entity(service.buscarEmpresas(condicaoPesquisa, page, size))
            .build();

    }

    @GET
    @Path("/{id}/resumido")
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Empresa encontrada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = EmpresaResumidoDTO.class)))
    @Operation(summary = "Buscar resumido", description = "Busca empresa resumida")
    public Response buscarResumidoPeloID(@PathParam("id") String id) {
        return Response.status(Response.Status.OK)
            .entity(service.buscarEmpresaResumidoByID(id))
            .build();
    }

    @PUT
    @RolesAllowed({ "USER_ROLE", "ADMIN_ROLE" })
    @APIResponse(responseCode = "200", description = "Imagem salva com sucesso")
    @Operation(summary = "Salvar imagem", description = "Salva a imagem de perfil da empresa")
    @Path("/salvar-imagem")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(MultipartFormDataInput input) {
        service.salvarImagemPerfilEmpresa(input);
        return Response.status(Response.Status.OK).entity("Imagem de perfil salva com sucesso").build();
    }
}

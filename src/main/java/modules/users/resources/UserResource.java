package modules.users.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modules.users.services.user.UserService;
import modules.users.structure.dtos.user.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 26/12/23
 */
@Path("/user")
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Path("/{id}")
    @Operation(summary = "Buscar", description = "Buscar usuário pelo ID")
    @APIResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserGetDTO.class)), headers = @Header(schema = @Schema(example = "/user/61afb078-46b3-4140-9051-3adb295dc4b9", implementation = String.class)))
    @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    @APIResponse(responseCode = "401", description = "Não autorizado")
    @APIResponse(responseCode = "500", description = "Erro no servidor")
    public Response getUserById(@PathParam("id") final String id) {
        return Response.ok(userService.getById(id)).build();
    }

    @GET
    @Path("/email/{email}")
    @Operation(summary = "Buscar", description = "Buscar um usuário pelo email")
    @APIResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserGetDTO.class)), headers = @Header(schema = @Schema(example = "/user/joao@email.com", implementation = String.class)))
    @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    @APIResponse(responseCode = "401", description = "Não autorizado")
    @APIResponse(responseCode = "500", description = "Erro no servidor")
    public Response getUserByEmail(@PathParam("email") final String email) {
        return Response.ok(userService.getByEmail(email)).build();
    }

    @POST
    @Operation(summary = "Criar", description = "Criar um novo usuário")
    @APIResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserDTO.class)))
    @APIResponse(responseCode = "500", description = "Erro no servidor")
    @APIResponse(responseCode = "400", description = "Erro de validação")
    public Response createUser(UserDTO dto) {
        return Response.ok(userService.save(dto)).build();
    }

    @PUT
    @Operation(summary = "Atualizar", description = "Atualizar um usuário")
    @APIResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserUpdateDTO.class)))
    @APIResponse(responseCode = "500", description = "Erro no servidor")
    @APIResponse(responseCode = "400", description = "Erro de validação")
    @APIResponse(responseCode = "406", description = "Código inválido")
    @APIResponse(responseCode = "401", description = "Não autorizado")
    @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    public Response updateUser(UserUpdateDTO dto) {
        return Response.ok(userService.update(dto)).build();
    }

    @PUT
    @Path("/inative")
    @Operation(summary = "Inativar", description = "Inativar um usuário")
    @APIResponse(responseCode = "200", description = "Usuário inativo com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserInativeDTO.class)))
    @APIResponse(responseCode = "500", description = "Erro no servidor")
    @APIResponse(responseCode = "406", description = "Código inválido")
    @APIResponse(responseCode = "401", description = "Não autorizado")
    @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    public Response inativeUser(UserInativeDTO dto) {
        userService.inativeUser(dto);
        return Response.ok().build();
    }

    @PUT
    @Path("/active")
    @Operation(summary = "Ativar", description = "Ativar um usuário")
    @APIResponse(responseCode = "200", description = "Usuário ativado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserActiveDTO.class)))
    @APIResponse(responseCode = "500", description = "Erro no servidor")
    @APIResponse(responseCode = "406", description = "Código inválido")
    @APIResponse(responseCode = "401", description = "Não autorizado")
    @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    public Response activeUser(UserActiveDTO dto) {
        userService.activeUser(dto);
        return Response.ok().build();
    }

    @PUT
    @Path("/change-password")
    @Operation(summary = "Alterar", description = "Alterar senha de um usuário")
    @APIResponse(responseCode = "200", description = "Senha alterada com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = UserChangePasswordDTO.class)))
    @APIResponse(responseCode = "500", description = "Erro no servidor")
    @APIResponse(responseCode = "406", description = "Código inválido")
    @APIResponse(responseCode = "401", description = "Não autorizado")
    @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    public Response changePassword(UserChangePasswordDTO dto) {
        return Response.ok(userService.changePassword(dto)).build();
    }

    @PUT
    @Path("/send-confirmation-code")
    @Operation(summary = "Enviar", description = "Enviar código de confirmação de email")
    @APIResponse(responseCode = "200", description = "Código enviado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SendConfirmationCodeDTO.class)))
    @APIResponse(responseCode = "500", description = "Erro no servidor")
    @APIResponse(responseCode = "404", description = "Usuário não encontrado")
    public Response sendConfirmationCodeInEmail(SendConfirmationCodeDTO dto) {
        userService.sendConfirmationCode(dto);
        return Response.ok().build();
    }

}

package modules.users.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import modules.users.services.UserService;
import modules.users.structure.dtos.user.*;

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
    public Response getUserById(@PathParam("id") final String id) {
        return Response.ok(userService.getById(id)).build();
    }

    @GET
    @Path("/email/{email}")
    public Response getUserByEmail(@PathParam("email") final String email) {
        return Response.ok(userService.getByEmail(email)).build();
    }

    @POST
    public Response createUser(UserDTO dto) {
        return Response.ok(userService.save(dto)).build();
    }

    @PUT
    public Response updateUser(UserUpdateDTO dto) {
        return Response.ok(userService.update(dto)).build();
    }

    @PUT
    @Path("/inative")
    public Response inativeUser(UserInativeDTO dto) {
        userService.inativeUser(dto);
        return Response.ok().build();
    }

    @PUT
    @Path("/active")
    public Response activeUser(UserActiveDTO dto) {
        userService.activeUser(dto);
        return Response.ok().build();
    }

    @PUT
    @Path("/change-password")
    public Response changePassword(UserChangePasswordDTO dto) {
        return Response.ok(userService.changePassword(dto)).build();
    }

}

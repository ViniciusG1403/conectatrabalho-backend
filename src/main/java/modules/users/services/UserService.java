package modules.users.services;

import jakarta.enterprise.context.RequestScoped;
import lombok.RequiredArgsConstructor;
import modules.users.converters.UserConverter;
import modules.users.structure.dtos.user.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
@RequestScoped
@RequiredArgsConstructor
@Schema(name = "Service de usuários", description = "Service responsável pelos usuários")
public class UserService {

    private final UserConverter userConverter;

    public UserDTO save(UserDTO dto){

    }

    public UserUpdateDTO update(UserUpdateDTO dto){
        return null;
    }

    public UserGetDTO getById(String id){
        return null;
    }

    public void inativeUser(UserInativeDTO id){
    }

    public UserChangePasswordDTO changePassword(UserChangePasswordDTO dto){
        return null;
    }


}

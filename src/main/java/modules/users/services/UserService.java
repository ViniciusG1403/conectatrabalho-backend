package modules.users.services;

import core.encoder.PBKDF2Encoder;
import core.validates.Validators;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.users.converters.UserConverter;
import modules.users.enumerations.UserStatus;
import modules.users.structure.dtos.user.*;
import modules.users.structure.entities.User;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.sql.Timestamp;

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

    private final Validators validators;

    private final PBKDF2Encoder encoder;

    @Transactional
    public UserDTO save(UserDTO dto) {
        validators.NonNullValidate(dto.getName(), "Nome");
        validators.NonNullValidate(dto.getEmail(), "Email");
        validators.EmailFormatValidate(dto.getEmail());
        validators.NonNullValidate(dto.getPassword(), "Senha");
        validators.NonNullValidate(dto.getType(), "Tipo de usuário");

        dto.setDhRegister(new Timestamp(System.currentTimeMillis()));
        dto.setStatus(UserStatus.ACTIVE.ordinal());
        dto.setPassword(encoder.encode(dto.getPassword()));

        User userSave = userConverter.dtoToOrm(dto);
        User.persist(userSave);

        return userConverter.ormToDto(userSave);
    }

    public UserUpdateDTO update(UserUpdateDTO dto) {
        return null;
    }

    public UserGetDTO getById(String id) {
        return null;
    }

    public UserGetDTO getByEmail(String email) {
        return null;
    }

    public void inativeUser(UserInativeDTO id) {
    }

    public UserChangePasswordDTO changePassword(UserChangePasswordDTO dto) {
        return null;
    }

}

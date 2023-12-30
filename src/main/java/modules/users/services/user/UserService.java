package modules.users.services.user;

import core.emailservice.MessageOperation;
import core.emailservice.SendEmailService;
import core.encoder.PBKDF2Encoder;
import core.validates.Validators;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import modules.users.converters.user.UserConverter;
import modules.users.converters.user.UserGetConverter;
import modules.users.enumerations.UserRoles;
import modules.users.enumerations.UserStatus;
import modules.users.services.localization.LocalizationService;
import modules.users.structure.dtos.localization.LocalizationDTO;
import modules.users.structure.dtos.user.*;
import modules.users.structure.entities.User;
import modules.users.usecases.GenerateRandomCode;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 24/12/23
 */
@RequestScoped
@RequiredArgsConstructor
@Schema(name = "Service de usuários", description = "Service responsável pelos usuários")
public class UserService extends Validators {

    private final UserConverter userConverter;

    private final UserGetConverter userGetConverter;

    private final GenerateRandomCode generateRandomCode;

    private final PBKDF2Encoder encoder;

    private final SendEmailService sendEmailService;

    private final LocalizationService localizationService;

    @Transactional
    public UserDTO save(UserDTO dto) {
        NonNullValidate(dto.getName(), "Nome");
        NonNullValidate(dto.getEmail(), "Email");
        EmailFormatValidate(dto.getEmail());
        NonNullValidate(dto.getPassword(), "Senha");
        NonNullValidate(dto.getType(), "Tipo de usuário");

        dto.setDhRegister(new Timestamp(System.currentTimeMillis()));
        dto.setStatus(UserStatus.INACTIVE.ordinal());
        dto.setRole(UserRoles.BASIC.ordinal());
        dto.setPassword(encoder.encode(dto.getPassword()));
        dto.setCode(generateRandomCode.execute());

        sendEmailService.sendMail(dto, "Ativação de usuário", MessageOperation.ATIVACAO);

        User userSave = userConverter.dtoToOrm(dto);
        LocalizationDTO localizationDTO = localizationService.save(dto.getLocalization());
        userSave.getLocalization().setId(UUID.fromString(localizationDTO.getId()));
        User.persist(userSave);


        return userConverter.ormToDto(userSave);
    }

    public UserUpdateDTO update(UserUpdateDTO dto) {
        User user = User.findById(UUID.fromString(dto.getId()));
        if (user == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        if (!Objects.equals(user.getCode(), dto.getCode())) {
            throw new ValidationException("Código de atualização é inválido");
        }
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if(dto.getLocalization() != null){
            localizationService.update(dto.getLocalization());
        }
        User.persist(user);
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }

    public UserGetDTO getById(String id) {
        User user = User.findById(UUID.fromString(id));
        if (user == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        UserGetDTO userGetDTO = userGetConverter.ormToDto(user);
        return userGetDTO;
    }

    public UserGetDTO getByEmail(String email) {
        User user = User.find("email", email).firstResult();
        if (user == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return userGetConverter.ormToDto(user);
    }

    @Transactional
    public void inativeUser(UserInativeDTO dto) {
        User user = User.findById(UUID.fromString(dto.getId()));
        if (user == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        if (!Objects.equals(user.getCode(), dto.getCode())) {
            throw new ValidationException("Código de inativação inválido");
        }
        user.setStatus(UserStatus.INACTIVE);
        user.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        user.setCode(null);
        User.persist(user);
    }

    @Transactional
    public void activeUser(UserActiveDTO dto){
        User user = User.findById(UUID.fromString(dto.getId()));
        if (user == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        if (!Objects.equals(user.getCode(), dto.getCode())) {
            throw new ValidationException("Código de ativação inválido");
        }
        user.setStatus(UserStatus.ACTIVE);
        user.setCode(null);
        User.persist(user);
    }

    @Transactional
    public UserChangePasswordDTO changePassword(UserChangePasswordDTO dto) {
        User user = User.findById(UUID.fromString(dto.getId()));
        if (user == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        if (!Objects.equals(dto.getCode(), user.getCode())) {
            throw new ValidationException("Código de alteração de senha inválido");
        }
        if (!Objects.equals(encoder.encode(dto.getOldPassword()), user.getPassword())) {
            throw new ValidationException("Senha atual inválida");
        }
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        user.setCode(null);
        User.persist(user);
        dto.setPassword(user.getPassword());
        return dto;
    }

}

package modules.users.converters.user;

import jakarta.enterprise.context.RequestScoped;
import lombok.RequiredArgsConstructor;
import modules.users.converters.localization.LocalizationConverter;
import modules.users.enumerations.UserRoles;
import modules.users.enumerations.UserStatus;
import modules.users.enumerations.UserType;
import modules.users.structure.dtos.user.UserDTO;
import modules.users.structure.dtos.user.UserGetDTO;
import modules.users.structure.entities.User;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/12/23
 */
@RequestScoped
@RequiredArgsConstructor
public class UserGetConverter {

    private final LocalizationConverter localizationConverter;

    public UserGetDTO ormToDto(final User userDTO) {
        UserGetDTO dto = new UserGetDTO();
        dto.setId(String.valueOf(userDTO.getId()));
        dto.setName(userDTO.getName());
        dto.setEmail(userDTO.getEmail());
        dto.setDhRegister(userDTO.getDhRegister());
        dto.setStatus(UserStatus.valueOf(userDTO.getStatus()));
        dto.setLastLogin(userDTO.getLastLogin());
        dto.setLastUpdate(userDTO.getLastUpdate());
        dto.setRole(UserRoles.valueOf(userDTO.getRole()));
        dto.setType(UserType.valueOf(userDTO.getType()));
        dto.setLocalization(localizationConverter.ormToDto(userDTO.getLocalization()));
        return dto;
    }

}
package modules.users.converters.user;

import jakarta.enterprise.context.RequestScoped;
import lombok.RequiredArgsConstructor;
import modules.users.converters.localization.LocalizationConverter;
import modules.users.enumerations.UserRoles;
import modules.users.enumerations.UserStatus;
import modules.users.enumerations.UserType;
import modules.users.structure.dtos.user.UserDTO;
import modules.users.structure.entities.User;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 25/12/23
 */
@RequestScoped
@RequiredArgsConstructor
public class UserConverter {

    private final LocalizationConverter localizationConverter;

    public UserDTO ormToDto(final User orm){
        UserDTO dto = new UserDTO();
        dto.setId(orm.getId().toString());
        dto.setName(orm.getName());
        dto.setEmail(orm.getEmail());
        dto.setPassword(orm.getPassword());
        dto.setDhRegister(orm.getDhRegister());
        dto.setStatus(UserStatus.valueOf(orm.getStatus()));
        dto.setLastLogin(orm.getLastLogin());
        dto.setLastUpdate(orm.getLastUpdate());
        dto.setCode(orm.getCode());
        dto.setRole(UserRoles.valueOf(orm.getRole()));
        dto.setType(UserType.valueOf(orm.getType()));
        dto.setLocalization(localizationConverter.ormToDto(orm.getLocalization()));
        return dto;
    }

    public User dtoToOrm(final UserDTO dto){
        User orm = new User();
        orm.setId(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null);
        orm.setName(dto.getName());
        orm.setEmail(dto.getEmail());
        orm.setPassword(dto.getPassword());
        orm.setDhRegister(dto.getDhRegister());
        orm.setStatus(UserStatus.valueOf(dto.getStatus()));
        orm.setLastLogin(dto.getLastLogin());
        orm.setLastUpdate(dto.getLastUpdate());
        orm.setCode(dto.getCode());
        orm.setRole(UserRoles.valueOf(dto.getRole()));
        orm.setType(UserType.valueOf(dto.getType()));
        orm.setLocalization(localizationConverter.dtoToOrm(dto.getLocalization()));
        return orm;
    }


}

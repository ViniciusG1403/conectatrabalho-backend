package modules.users.converters.localization;

import jakarta.enterprise.context.RequestScoped;
import lombok.RequiredArgsConstructor;
import modules.users.converters.user.UserConverter;
import modules.users.structure.dtos.localization.LocalizationDTO;
import modules.users.structure.entities.Localization;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/12/23
 */
@RequestScoped
@RequiredArgsConstructor
public class LocalizationConverter {

    private final UserConverter userConverter;

    public Localization dtoToOrm(final LocalizationDTO dto){
        Localization orm = new Localization();
        orm.setId(dto.getId() != null ? java.util.UUID.fromString(dto.getId()) : null);
        orm.setUser(userConverter.dtoToOrm(dto.getUser()));
        orm.setCep(dto.getCep());
        orm.setStreet(dto.getStreet());
        orm.setNumber(dto.getNumber());
        orm.setComplement(dto.getComplement());
        orm.setNeighborhood(dto.getNeighborhood());
        orm.setCity(dto.getCity());
        orm.setState(dto.getState());
        return orm;
    }

    public LocalizationDTO ormToDto(final Localization orm) {
        LocalizationDTO dto = new LocalizationDTO();
        dto.setId(String.valueOf(orm.getId()));
        dto.setCep(orm.getCep());
        dto.setStreet(orm.getStreet());
        dto.setNumber(orm.getNumber());
        dto.setComplement(orm.getComplement());
        dto.setNeighborhood(orm.getNeighborhood());
        dto.setCity(orm.getCity());
        dto.setState(orm.getState());
        return dto;
    }

}

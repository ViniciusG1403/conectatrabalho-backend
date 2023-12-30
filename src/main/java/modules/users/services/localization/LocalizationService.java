package modules.users.services.localization;

import core.validates.Validators;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.users.converters.localization.LocalizationConverter;
import modules.users.structure.dtos.localization.LocalizationDTO;
import modules.users.structure.entities.Localization;

import java.util.UUID;

/**
 * @author Vinicius Gabriel <vinicius.prado@nexuscloud.com.br>
 * @version 1.0
 * @since 28/12/23
 */
@RequestScoped
@RequiredArgsConstructor
public class LocalizationService extends Validators {

    private final LocalizationConverter converter;

    @Transactional
    public LocalizationDTO save(LocalizationDTO dto) {
        validate(dto);
        Localization orm = converter.dtoToOrm(dto);
        Localization.persist(orm);

        return converter.ormToDto(orm);
    }

    @Transactional
    public void update(LocalizationDTO dto) {
        validate(dto);

        Localization localization = Localization.findById(UUID.fromString(dto.getId()));
        if(dto.getCep() != null){
            localization.setCep(dto.getCep());
        }
        if(dto.getStreet() != null){
            localization.setStreet(dto.getStreet());
        }
        if(dto.getNumber() != null){
            localization.setNumber(dto.getNumber());
        }
        if(dto.getComplement() != null){
            localization.setComplement(dto.getComplement());
        }
        if(dto.getNeighborhood() != null){
            localization.setNeighborhood(dto.getNeighborhood());
        }
        if(dto.getCity() != null){
            localization.setCity(dto.getCity());
        }
        if(dto.getState() != null){
            localization.setState(dto.getState());
        }

        Localization.persist(localization);
    }

    public LocalizationDTO findByUser(String id) {
        Localization orm = Localization.find("user.id", UUID.fromString(id)).firstResult();
        return converter.ormToDto(orm);
    }

    private void validate(LocalizationDTO dto) {
        NonNullValidate(dto.getCep(), "Cep");
        NonNullValidate(dto.getStreet(), "Rua");
        NonNullValidate(dto.getNumber(), "Número");
        NonNullValidate(dto.getNeighborhood(), "Bairro");
        NonNullValidate(dto.getCity(), "Cidade");
        NonNullValidate(dto.getState(), "Estado");
    }

}

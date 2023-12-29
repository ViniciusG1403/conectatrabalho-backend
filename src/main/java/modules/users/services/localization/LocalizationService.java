package modules.users.services.localization;

import core.validates.Validators;
import jakarta.enterprise.context.RequestScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import modules.users.converters.localization.LocalizationConverter;
import modules.users.structure.dtos.localization.LocalizationDTO;
import modules.users.structure.entities.Localization;

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
    public void save(LocalizationDTO dto) {
        validate(dto);
        Localization orm = converter.dtoToOrm(dto);
        Localization.persist(orm);
    }

    @Transactional
    public void update(LocalizationDTO dto) {
        validate(dto);
        Localization orm = converter.dtoToOrm(dto);
        Localization.persist(orm);
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

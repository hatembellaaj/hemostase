package org.adhes.hemostase.service.mapper;

import org.adhes.hemostase.domain.FicheHemophilie;
import org.adhes.hemostase.domain.Patient;
import org.adhes.hemostase.service.dto.FicheHemophilieDTO;
import org.adhes.hemostase.service.dto.PatientDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FicheHemophilie} and its DTO {@link FicheHemophilieDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheHemophilieMapper extends EntityMapper<FicheHemophilieDTO, FicheHemophilie> {
    @Mapping(target = "patient", source = "patient", qualifiedByName = "patientId")
    FicheHemophilieDTO toDto(FicheHemophilie s);

    @Named("patientId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PatientDTO toDtoPatientId(Patient patient);
}

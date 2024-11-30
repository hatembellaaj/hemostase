package tn.med.adhes.service.mapper;

import org.mapstruct.*;
import tn.med.adhes.domain.FicheHemophilie;
import tn.med.adhes.domain.Patient;
import tn.med.adhes.service.dto.FicheHemophilieDTO;
import tn.med.adhes.service.dto.PatientDTO;

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

package tn.med.adhes.service.mapper;

import org.mapstruct.*;
import tn.med.adhes.domain.Patient;
import tn.med.adhes.service.dto.PatientDTO;

/**
 * Mapper for the entity {@link Patient} and its DTO {@link PatientDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientMapper extends EntityMapper<PatientDTO, Patient> {}

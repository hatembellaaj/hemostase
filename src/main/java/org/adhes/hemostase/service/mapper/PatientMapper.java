package org.adhes.hemostase.service.mapper;

import org.adhes.hemostase.domain.Patient;
import org.adhes.hemostase.service.dto.PatientDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Patient} and its DTO {@link PatientDTO}.
 */
@Mapper(componentModel = "spring")
public interface PatientMapper extends EntityMapper<PatientDTO, Patient> {}

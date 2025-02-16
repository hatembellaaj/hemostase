package tn.adhes.hemostase.service.mapper;

import org.mapstruct.*;
import tn.adhes.hemostase.domain.FicheThrombopathie;
import tn.adhes.hemostase.service.dto.FicheThrombopathieDTO;

/**
 * Mapper for the entity {@link FicheThrombopathie} and its DTO {@link FicheThrombopathieDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheThrombopathieMapper extends EntityMapper<FicheThrombopathieDTO, FicheThrombopathie> {}

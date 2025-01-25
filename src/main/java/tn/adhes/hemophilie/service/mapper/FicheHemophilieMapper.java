package tn.adhes.hemophilie.service.mapper;

import org.mapstruct.*;
import tn.adhes.hemophilie.domain.FicheHemophilie;
import tn.adhes.hemophilie.service.dto.FicheHemophilieDTO;

/**
 * Mapper for the entity {@link FicheHemophilie} and its DTO {@link FicheHemophilieDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheHemophilieMapper extends EntityMapper<FicheHemophilieDTO, FicheHemophilie> {}

package org.adhes.hemostase.service.mapper;

import org.adhes.hemostase.domain.FicheHemophilie;
import org.adhes.hemostase.service.dto.FicheHemophilieDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FicheHemophilie} and its DTO {@link FicheHemophilieDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheHemophilieMapper extends EntityMapper<FicheHemophilieDTO, FicheHemophilie> {}

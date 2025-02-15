package tn.adhes.hemostase.service.mapper;

import org.mapstruct.*;
import tn.adhes.hemostase.domain.FicheHemophilie;
import tn.adhes.hemostase.service.dto.FicheHemophilieDTO;

/**
 * Mapper for the entity {@link FicheHemophilie} and its DTO {@link FicheHemophilieDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheHemophilieMapper extends EntityMapper<FicheHemophilieDTO, FicheHemophilie> {}

package tn.adhes.hemostase.service.mapper;

import org.mapstruct.*;
import tn.adhes.hemostase.domain.FicheFacteurVII;
import tn.adhes.hemostase.service.dto.FicheFacteurVIIDTO;

/**
 * Mapper for the entity {@link FicheFacteurVII} and its DTO {@link FicheFacteurVIIDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheFacteurVIIMapper extends EntityMapper<FicheFacteurVIIDTO, FicheFacteurVII> {}

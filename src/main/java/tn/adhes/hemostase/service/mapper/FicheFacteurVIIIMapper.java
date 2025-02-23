package tn.adhes.hemostase.service.mapper;

import org.mapstruct.*;
import tn.adhes.hemostase.domain.FicheFacteurVIII;
import tn.adhes.hemostase.service.dto.FicheFacteurVIIIDTO;

/**
 * Mapper for the entity {@link FicheFacteurVIII} and its DTO {@link FicheFacteurVIIIDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheFacteurVIIIMapper extends EntityMapper<FicheFacteurVIIIDTO, FicheFacteurVIII> {}

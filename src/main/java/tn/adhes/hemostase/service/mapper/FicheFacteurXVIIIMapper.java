package tn.adhes.hemostase.service.mapper;

import org.mapstruct.*;
import tn.adhes.hemostase.domain.FicheFacteurXVIII;
import tn.adhes.hemostase.service.dto.FicheFacteurXVIIIDTO;

/**
 * Mapper for the entity {@link FicheFacteurXVIII} and its DTO {@link FicheFacteurXVIIIDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheFacteurXVIIIMapper extends EntityMapper<FicheFacteurXVIIIDTO, FicheFacteurXVIII> {}

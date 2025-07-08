package tn.adhes.hemostase.service.mapper;

import org.mapstruct.*;
import tn.adhes.hemostase.domain.FicheFacteurXIII;
import tn.adhes.hemostase.service.dto.FicheFacteurXIIIDTO;

/**
 * Mapper for the entity {@link FicheFacteurXIII} and its DTO {@link FicheFacteurXIIIDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheFacteurXIIIMapper extends EntityMapper<FicheFacteurXIIIDTO, FicheFacteurXIII> {}

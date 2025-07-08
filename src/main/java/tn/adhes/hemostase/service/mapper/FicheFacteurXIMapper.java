package tn.adhes.hemostase.service.mapper;

import org.mapstruct.*;
import tn.adhes.hemostase.domain.FicheFacteurXI;
import tn.adhes.hemostase.service.dto.FicheFacteurXIDTO;

/**
 * Mapper for the entity {@link FicheFacteurXI} and its DTO {@link FicheFacteurXIDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheFacteurXIMapper extends EntityMapper<FicheFacteurXIDTO, FicheFacteurXI> {}

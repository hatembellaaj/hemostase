package tn.adhes.hemostase.service.mapper;

import org.mapstruct.*;
import tn.adhes.hemostase.domain.FicheVonWillebrand;
import tn.adhes.hemostase.service.dto.FicheVonWillebrandDTO;

/**
 * Mapper for the entity {@link FicheVonWillebrand} and its DTO {@link FicheVonWillebrandDTO}.
 */
@Mapper(componentModel = "spring")
public interface FicheVonWillebrandMapper extends EntityMapper<FicheVonWillebrandDTO, FicheVonWillebrand> {}

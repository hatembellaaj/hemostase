package tn.adhes.hemostase.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.adhes.hemostase.domain.FicheHemophilie;

/**
 * Spring Data JPA repository for the FicheHemophilie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheHemophilieRepository extends JpaRepository<FicheHemophilie, Long> {}

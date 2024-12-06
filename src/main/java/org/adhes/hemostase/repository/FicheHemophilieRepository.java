package org.adhes.hemostase.repository;

import org.adhes.hemostase.domain.FicheHemophilie;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FicheHemophilie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheHemophilieRepository extends JpaRepository<FicheHemophilie, Long> {}

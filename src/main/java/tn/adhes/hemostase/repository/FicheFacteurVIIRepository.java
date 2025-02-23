package tn.adhes.hemostase.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.adhes.hemostase.domain.FicheFacteurVII;

/**
 * Spring Data JPA repository for the FicheFacteurVII entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheFacteurVIIRepository extends JpaRepository<FicheFacteurVII, Long> {}

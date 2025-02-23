package tn.adhes.hemostase.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.adhes.hemostase.domain.FicheFacteurVIII;

/**
 * Spring Data JPA repository for the FicheFacteurVIII entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheFacteurVIIIRepository extends JpaRepository<FicheFacteurVIII, Long> {}

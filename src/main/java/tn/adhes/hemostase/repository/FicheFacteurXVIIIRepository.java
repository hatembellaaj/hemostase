package tn.adhes.hemostase.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.adhes.hemostase.domain.FicheFacteurXVIII;

/**
 * Spring Data JPA repository for the FicheFacteurXVIII entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheFacteurXVIIIRepository extends JpaRepository<FicheFacteurXVIII, Long> {}

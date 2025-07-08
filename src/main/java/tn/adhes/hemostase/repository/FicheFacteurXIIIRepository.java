package tn.adhes.hemostase.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.adhes.hemostase.domain.FicheFacteurXIII;

/**
 * Spring Data JPA repository for the FicheFacteurXIII entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheFacteurXIIIRepository extends JpaRepository<FicheFacteurXIII, Long> {}

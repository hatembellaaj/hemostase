package tn.adhes.hemostase.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.adhes.hemostase.domain.FicheFacteurXI;

/**
 * Spring Data JPA repository for the FicheFacteurXI entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheFacteurXIRepository extends JpaRepository<FicheFacteurXI, Long> {}

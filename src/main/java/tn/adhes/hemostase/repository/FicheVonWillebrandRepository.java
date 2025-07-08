package tn.adhes.hemostase.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.adhes.hemostase.domain.FicheVonWillebrand;

/**
 * Spring Data JPA repository for the FicheVonWillebrand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheVonWillebrandRepository extends JpaRepository<FicheVonWillebrand, Long> {}

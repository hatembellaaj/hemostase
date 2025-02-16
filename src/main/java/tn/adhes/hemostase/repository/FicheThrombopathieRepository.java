package tn.adhes.hemostase.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.adhes.hemostase.domain.FicheThrombopathie;

/**
 * Spring Data JPA repository for the FicheThrombopathie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FicheThrombopathieRepository extends JpaRepository<FicheThrombopathie, Long> {}

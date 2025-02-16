package tn.adhes.hemostase.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.adhes.hemostase.domain.FicheThrombopathie;
import tn.adhes.hemostase.repository.FicheThrombopathieRepository;
import tn.adhes.hemostase.service.dto.FicheThrombopathieDTO;
import tn.adhes.hemostase.service.mapper.FicheThrombopathieMapper;

/**
 * Service Implementation for managing {@link tn.adhes.hemostase.domain.FicheThrombopathie}.
 */
@Service
@Transactional
public class FicheThrombopathieService {

    private static final Logger LOG = LoggerFactory.getLogger(FicheThrombopathieService.class);

    private final FicheThrombopathieRepository ficheThrombopathieRepository;

    private final FicheThrombopathieMapper ficheThrombopathieMapper;

    public FicheThrombopathieService(
        FicheThrombopathieRepository ficheThrombopathieRepository,
        FicheThrombopathieMapper ficheThrombopathieMapper
    ) {
        this.ficheThrombopathieRepository = ficheThrombopathieRepository;
        this.ficheThrombopathieMapper = ficheThrombopathieMapper;
    }

    /**
     * Save a ficheThrombopathie.
     *
     * @param ficheThrombopathieDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheThrombopathieDTO save(FicheThrombopathieDTO ficheThrombopathieDTO) {
        LOG.debug("Request to save FicheThrombopathie : {}", ficheThrombopathieDTO);
        FicheThrombopathie ficheThrombopathie = ficheThrombopathieMapper.toEntity(ficheThrombopathieDTO);
        ficheThrombopathie = ficheThrombopathieRepository.save(ficheThrombopathie);
        return ficheThrombopathieMapper.toDto(ficheThrombopathie);
    }

    /**
     * Update a ficheThrombopathie.
     *
     * @param ficheThrombopathieDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheThrombopathieDTO update(FicheThrombopathieDTO ficheThrombopathieDTO) {
        LOG.debug("Request to update FicheThrombopathie : {}", ficheThrombopathieDTO);
        FicheThrombopathie ficheThrombopathie = ficheThrombopathieMapper.toEntity(ficheThrombopathieDTO);
        ficheThrombopathie = ficheThrombopathieRepository.save(ficheThrombopathie);
        return ficheThrombopathieMapper.toDto(ficheThrombopathie);
    }

    /**
     * Partially update a ficheThrombopathie.
     *
     * @param ficheThrombopathieDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FicheThrombopathieDTO> partialUpdate(FicheThrombopathieDTO ficheThrombopathieDTO) {
        LOG.debug("Request to partially update FicheThrombopathie : {}", ficheThrombopathieDTO);

        return ficheThrombopathieRepository
            .findById(ficheThrombopathieDTO.getId())
            .map(existingFicheThrombopathie -> {
                ficheThrombopathieMapper.partialUpdate(existingFicheThrombopathie, ficheThrombopathieDTO);

                return existingFicheThrombopathie;
            })
            .map(ficheThrombopathieRepository::save)
            .map(ficheThrombopathieMapper::toDto);
    }

    /**
     * Get all the ficheThrombopathies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FicheThrombopathieDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all FicheThrombopathies");
        return ficheThrombopathieRepository.findAll(pageable).map(ficheThrombopathieMapper::toDto);
    }

    /**
     * Get one ficheThrombopathie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FicheThrombopathieDTO> findOne(Long id) {
        LOG.debug("Request to get FicheThrombopathie : {}", id);
        return ficheThrombopathieRepository.findById(id).map(ficheThrombopathieMapper::toDto);
    }

    /**
     * Delete the ficheThrombopathie by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete FicheThrombopathie : {}", id);
        ficheThrombopathieRepository.deleteById(id);
    }
}

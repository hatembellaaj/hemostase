package tn.adhes.hemostase.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.adhes.hemostase.domain.FicheFacteurVIII;
import tn.adhes.hemostase.repository.FicheFacteurVIIIRepository;
import tn.adhes.hemostase.service.dto.FicheFacteurVIIIDTO;
import tn.adhes.hemostase.service.mapper.FicheFacteurVIIIMapper;

/**
 * Service Implementation for managing {@link tn.adhes.hemostase.domain.FicheFacteurVIII}.
 */
@Service
@Transactional
public class FicheFacteurVIIIService {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurVIIIService.class);

    private final FicheFacteurVIIIRepository ficheFacteurVIIIRepository;

    private final FicheFacteurVIIIMapper ficheFacteurVIIIMapper;

    public FicheFacteurVIIIService(FicheFacteurVIIIRepository ficheFacteurVIIIRepository, FicheFacteurVIIIMapper ficheFacteurVIIIMapper) {
        this.ficheFacteurVIIIRepository = ficheFacteurVIIIRepository;
        this.ficheFacteurVIIIMapper = ficheFacteurVIIIMapper;
    }

    /**
     * Save a ficheFacteurVIII.
     *
     * @param ficheFacteurVIIIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurVIIIDTO save(FicheFacteurVIIIDTO ficheFacteurVIIIDTO) {
        LOG.debug("Request to save FicheFacteurVIII : {}", ficheFacteurVIIIDTO);
        FicheFacteurVIII ficheFacteurVIII = ficheFacteurVIIIMapper.toEntity(ficheFacteurVIIIDTO);
        ficheFacteurVIII = ficheFacteurVIIIRepository.save(ficheFacteurVIII);
        return ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);
    }

    /**
     * Update a ficheFacteurVIII.
     *
     * @param ficheFacteurVIIIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurVIIIDTO update(FicheFacteurVIIIDTO ficheFacteurVIIIDTO) {
        LOG.debug("Request to update FicheFacteurVIII : {}", ficheFacteurVIIIDTO);
        FicheFacteurVIII ficheFacteurVIII = ficheFacteurVIIIMapper.toEntity(ficheFacteurVIIIDTO);
        ficheFacteurVIII = ficheFacteurVIIIRepository.save(ficheFacteurVIII);
        return ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);
    }

    /**
     * Partially update a ficheFacteurVIII.
     *
     * @param ficheFacteurVIIIDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FicheFacteurVIIIDTO> partialUpdate(FicheFacteurVIIIDTO ficheFacteurVIIIDTO) {
        LOG.debug("Request to partially update FicheFacteurVIII : {}", ficheFacteurVIIIDTO);

        return ficheFacteurVIIIRepository
            .findById(ficheFacteurVIIIDTO.getId())
            .map(existingFicheFacteurVIII -> {
                ficheFacteurVIIIMapper.partialUpdate(existingFicheFacteurVIII, ficheFacteurVIIIDTO);

                return existingFicheFacteurVIII;
            })
            .map(ficheFacteurVIIIRepository::save)
            .map(ficheFacteurVIIIMapper::toDto);
    }

    /**
     * Get all the ficheFacteurVIIIS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FicheFacteurVIIIDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all FicheFacteurVIIIS");
        return ficheFacteurVIIIRepository.findAll(pageable).map(ficheFacteurVIIIMapper::toDto);
    }

    /**
     * Get one ficheFacteurVIII by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FicheFacteurVIIIDTO> findOne(Long id) {
        LOG.debug("Request to get FicheFacteurVIII : {}", id);
        return ficheFacteurVIIIRepository.findById(id).map(ficheFacteurVIIIMapper::toDto);
    }

    /**
     * Delete the ficheFacteurVIII by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete FicheFacteurVIII : {}", id);
        ficheFacteurVIIIRepository.deleteById(id);
    }
}

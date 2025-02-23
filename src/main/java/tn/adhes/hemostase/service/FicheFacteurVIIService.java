package tn.adhes.hemostase.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.adhes.hemostase.domain.FicheFacteurVII;
import tn.adhes.hemostase.repository.FicheFacteurVIIRepository;
import tn.adhes.hemostase.service.dto.FicheFacteurVIIDTO;
import tn.adhes.hemostase.service.mapper.FicheFacteurVIIMapper;

/**
 * Service Implementation for managing {@link tn.adhes.hemostase.domain.FicheFacteurVII}.
 */
@Service
@Transactional
public class FicheFacteurVIIService {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurVIIService.class);

    private final FicheFacteurVIIRepository ficheFacteurVIIRepository;

    private final FicheFacteurVIIMapper ficheFacteurVIIMapper;

    public FicheFacteurVIIService(FicheFacteurVIIRepository ficheFacteurVIIRepository, FicheFacteurVIIMapper ficheFacteurVIIMapper) {
        this.ficheFacteurVIIRepository = ficheFacteurVIIRepository;
        this.ficheFacteurVIIMapper = ficheFacteurVIIMapper;
    }

    /**
     * Save a ficheFacteurVII.
     *
     * @param ficheFacteurVIIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurVIIDTO save(FicheFacteurVIIDTO ficheFacteurVIIDTO) {
        LOG.debug("Request to save FicheFacteurVII : {}", ficheFacteurVIIDTO);
        FicheFacteurVII ficheFacteurVII = ficheFacteurVIIMapper.toEntity(ficheFacteurVIIDTO);
        ficheFacteurVII = ficheFacteurVIIRepository.save(ficheFacteurVII);
        return ficheFacteurVIIMapper.toDto(ficheFacteurVII);
    }

    /**
     * Update a ficheFacteurVII.
     *
     * @param ficheFacteurVIIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurVIIDTO update(FicheFacteurVIIDTO ficheFacteurVIIDTO) {
        LOG.debug("Request to update FicheFacteurVII : {}", ficheFacteurVIIDTO);
        FicheFacteurVII ficheFacteurVII = ficheFacteurVIIMapper.toEntity(ficheFacteurVIIDTO);
        ficheFacteurVII = ficheFacteurVIIRepository.save(ficheFacteurVII);
        return ficheFacteurVIIMapper.toDto(ficheFacteurVII);
    }

    /**
     * Partially update a ficheFacteurVII.
     *
     * @param ficheFacteurVIIDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FicheFacteurVIIDTO> partialUpdate(FicheFacteurVIIDTO ficheFacteurVIIDTO) {
        LOG.debug("Request to partially update FicheFacteurVII : {}", ficheFacteurVIIDTO);

        return ficheFacteurVIIRepository
            .findById(ficheFacteurVIIDTO.getId())
            .map(existingFicheFacteurVII -> {
                ficheFacteurVIIMapper.partialUpdate(existingFicheFacteurVII, ficheFacteurVIIDTO);

                return existingFicheFacteurVII;
            })
            .map(ficheFacteurVIIRepository::save)
            .map(ficheFacteurVIIMapper::toDto);
    }

    /**
     * Get all the ficheFacteurVIIS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FicheFacteurVIIDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all FicheFacteurVIIS");
        return ficheFacteurVIIRepository.findAll(pageable).map(ficheFacteurVIIMapper::toDto);
    }

    /**
     * Get one ficheFacteurVII by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FicheFacteurVIIDTO> findOne(Long id) {
        LOG.debug("Request to get FicheFacteurVII : {}", id);
        return ficheFacteurVIIRepository.findById(id).map(ficheFacteurVIIMapper::toDto);
    }

    /**
     * Delete the ficheFacteurVII by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete FicheFacteurVII : {}", id);
        ficheFacteurVIIRepository.deleteById(id);
    }
}

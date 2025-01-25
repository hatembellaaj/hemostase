package tn.adhes.hemophilie.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.adhes.hemophilie.domain.FicheHemophilie;
import tn.adhes.hemophilie.repository.FicheHemophilieRepository;
import tn.adhes.hemophilie.service.dto.FicheHemophilieDTO;
import tn.adhes.hemophilie.service.mapper.FicheHemophilieMapper;

/**
 * Service Implementation for managing {@link tn.adhes.hemophilie.domain.FicheHemophilie}.
 */
@Service
@Transactional
public class FicheHemophilieService {

    private static final Logger LOG = LoggerFactory.getLogger(FicheHemophilieService.class);

    private final FicheHemophilieRepository ficheHemophilieRepository;

    private final FicheHemophilieMapper ficheHemophilieMapper;

    public FicheHemophilieService(FicheHemophilieRepository ficheHemophilieRepository, FicheHemophilieMapper ficheHemophilieMapper) {
        this.ficheHemophilieRepository = ficheHemophilieRepository;
        this.ficheHemophilieMapper = ficheHemophilieMapper;
    }

    /**
     * Save a ficheHemophilie.
     *
     * @param ficheHemophilieDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheHemophilieDTO save(FicheHemophilieDTO ficheHemophilieDTO) {
        LOG.debug("Request to save FicheHemophilie : {}", ficheHemophilieDTO);
        FicheHemophilie ficheHemophilie = ficheHemophilieMapper.toEntity(ficheHemophilieDTO);
        ficheHemophilie = ficheHemophilieRepository.save(ficheHemophilie);
        return ficheHemophilieMapper.toDto(ficheHemophilie);
    }

    /**
     * Update a ficheHemophilie.
     *
     * @param ficheHemophilieDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheHemophilieDTO update(FicheHemophilieDTO ficheHemophilieDTO) {
        LOG.debug("Request to update FicheHemophilie : {}", ficheHemophilieDTO);
        FicheHemophilie ficheHemophilie = ficheHemophilieMapper.toEntity(ficheHemophilieDTO);
        ficheHemophilie = ficheHemophilieRepository.save(ficheHemophilie);
        return ficheHemophilieMapper.toDto(ficheHemophilie);
    }

    /**
     * Partially update a ficheHemophilie.
     *
     * @param ficheHemophilieDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FicheHemophilieDTO> partialUpdate(FicheHemophilieDTO ficheHemophilieDTO) {
        LOG.debug("Request to partially update FicheHemophilie : {}", ficheHemophilieDTO);

        return ficheHemophilieRepository
            .findById(ficheHemophilieDTO.getId())
            .map(existingFicheHemophilie -> {
                ficheHemophilieMapper.partialUpdate(existingFicheHemophilie, ficheHemophilieDTO);

                return existingFicheHemophilie;
            })
            .map(ficheHemophilieRepository::save)
            .map(ficheHemophilieMapper::toDto);
    }

    /**
     * Get all the ficheHemophilies.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FicheHemophilieDTO> findAll(Pageable pageable) {
        LOG.debug("Request to get all FicheHemophilies");
        return ficheHemophilieRepository.findAll(pageable).map(ficheHemophilieMapper::toDto);
    }

    /**
     * Get one ficheHemophilie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FicheHemophilieDTO> findOne(Long id) {
        LOG.debug("Request to get FicheHemophilie : {}", id);
        return ficheHemophilieRepository.findById(id).map(ficheHemophilieMapper::toDto);
    }

    /**
     * Delete the ficheHemophilie by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete FicheHemophilie : {}", id);
        ficheHemophilieRepository.deleteById(id);
    }
}

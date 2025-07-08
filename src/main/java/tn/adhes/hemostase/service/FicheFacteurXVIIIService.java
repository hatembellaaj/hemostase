package tn.adhes.hemostase.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.adhes.hemostase.domain.FicheFacteurXVIII;
import tn.adhes.hemostase.repository.FicheFacteurXVIIIRepository;
import tn.adhes.hemostase.service.dto.FicheFacteurXVIIIDTO;
import tn.adhes.hemostase.service.mapper.FicheFacteurXVIIIMapper;

/**
 * Service Implementation for managing {@link tn.adhes.hemostase.domain.FicheFacteurXVIII}.
 */
@Service
@Transactional
public class FicheFacteurXVIIIService {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurXVIIIService.class);

    private final FicheFacteurXVIIIRepository ficheFacteurXVIIIRepository;

    private final FicheFacteurXVIIIMapper ficheFacteurXVIIIMapper;

    public FicheFacteurXVIIIService(
        FicheFacteurXVIIIRepository ficheFacteurXVIIIRepository,
        FicheFacteurXVIIIMapper ficheFacteurXVIIIMapper
    ) {
        this.ficheFacteurXVIIIRepository = ficheFacteurXVIIIRepository;
        this.ficheFacteurXVIIIMapper = ficheFacteurXVIIIMapper;
    }

    /**
     * Save a ficheFacteurXVIII.
     *
     * @param ficheFacteurXVIIIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurXVIIIDTO save(FicheFacteurXVIIIDTO ficheFacteurXVIIIDTO) {
        LOG.debug("Request to save FicheFacteurXVIII : {}", ficheFacteurXVIIIDTO);
        FicheFacteurXVIII ficheFacteurXVIII = ficheFacteurXVIIIMapper.toEntity(ficheFacteurXVIIIDTO);
        ficheFacteurXVIII = ficheFacteurXVIIIRepository.save(ficheFacteurXVIII);
        return ficheFacteurXVIIIMapper.toDto(ficheFacteurXVIII);
    }

    /**
     * Update a ficheFacteurXVIII.
     *
     * @param ficheFacteurXVIIIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurXVIIIDTO update(FicheFacteurXVIIIDTO ficheFacteurXVIIIDTO) {
        LOG.debug("Request to update FicheFacteurXVIII : {}", ficheFacteurXVIIIDTO);
        FicheFacteurXVIII ficheFacteurXVIII = ficheFacteurXVIIIMapper.toEntity(ficheFacteurXVIIIDTO);
        ficheFacteurXVIII = ficheFacteurXVIIIRepository.save(ficheFacteurXVIII);
        return ficheFacteurXVIIIMapper.toDto(ficheFacteurXVIII);
    }

    /**
     * Partially update a ficheFacteurXVIII.
     *
     * @param ficheFacteurXVIIIDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FicheFacteurXVIIIDTO> partialUpdate(FicheFacteurXVIIIDTO ficheFacteurXVIIIDTO) {
        LOG.debug("Request to partially update FicheFacteurXVIII : {}", ficheFacteurXVIIIDTO);

        return ficheFacteurXVIIIRepository
            .findById(ficheFacteurXVIIIDTO.getId())
            .map(existingFicheFacteurXVIII -> {
                ficheFacteurXVIIIMapper.partialUpdate(existingFicheFacteurXVIII, ficheFacteurXVIIIDTO);

                return existingFicheFacteurXVIII;
            })
            .map(ficheFacteurXVIIIRepository::save)
            .map(ficheFacteurXVIIIMapper::toDto);
    }

    /**
     * Get all the ficheFacteurXVIIIS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FicheFacteurXVIIIDTO> findAll() {
        LOG.debug("Request to get all FicheFacteurXVIIIS");
        return ficheFacteurXVIIIRepository
            .findAll()
            .stream()
            .map(ficheFacteurXVIIIMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ficheFacteurXVIII by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FicheFacteurXVIIIDTO> findOne(Long id) {
        LOG.debug("Request to get FicheFacteurXVIII : {}", id);
        return ficheFacteurXVIIIRepository.findById(id).map(ficheFacteurXVIIIMapper::toDto);
    }

    /**
     * Delete the ficheFacteurXVIII by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete FicheFacteurXVIII : {}", id);
        ficheFacteurXVIIIRepository.deleteById(id);
    }
}

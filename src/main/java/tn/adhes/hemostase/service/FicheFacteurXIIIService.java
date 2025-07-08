package tn.adhes.hemostase.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.adhes.hemostase.domain.FicheFacteurXIII;
import tn.adhes.hemostase.repository.FicheFacteurXIIIRepository;
import tn.adhes.hemostase.service.dto.FicheFacteurXIIIDTO;
import tn.adhes.hemostase.service.mapper.FicheFacteurXIIIMapper;

/**
 * Service Implementation for managing {@link tn.adhes.hemostase.domain.FicheFacteurXIII}.
 */
@Service
@Transactional
public class FicheFacteurXIIIService {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurXIIIService.class);

    private final FicheFacteurXIIIRepository ficheFacteurXIIIRepository;

    private final FicheFacteurXIIIMapper ficheFacteurXIIIMapper;

    public FicheFacteurXIIIService(FicheFacteurXIIIRepository ficheFacteurXIIIRepository, FicheFacteurXIIIMapper ficheFacteurXIIIMapper) {
        this.ficheFacteurXIIIRepository = ficheFacteurXIIIRepository;
        this.ficheFacteurXIIIMapper = ficheFacteurXIIIMapper;
    }

    /**
     * Save a ficheFacteurXIII.
     *
     * @param ficheFacteurXIIIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurXIIIDTO save(FicheFacteurXIIIDTO ficheFacteurXIIIDTO) {
        LOG.debug("Request to save FicheFacteurXIII : {}", ficheFacteurXIIIDTO);
        FicheFacteurXIII ficheFacteurXIII = ficheFacteurXIIIMapper.toEntity(ficheFacteurXIIIDTO);
        ficheFacteurXIII = ficheFacteurXIIIRepository.save(ficheFacteurXIII);
        return ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);
    }

    /**
     * Update a ficheFacteurXIII.
     *
     * @param ficheFacteurXIIIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurXIIIDTO update(FicheFacteurXIIIDTO ficheFacteurXIIIDTO) {
        LOG.debug("Request to update FicheFacteurXIII : {}", ficheFacteurXIIIDTO);
        FicheFacteurXIII ficheFacteurXIII = ficheFacteurXIIIMapper.toEntity(ficheFacteurXIIIDTO);
        ficheFacteurXIII = ficheFacteurXIIIRepository.save(ficheFacteurXIII);
        return ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);
    }

    /**
     * Partially update a ficheFacteurXIII.
     *
     * @param ficheFacteurXIIIDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FicheFacteurXIIIDTO> partialUpdate(FicheFacteurXIIIDTO ficheFacteurXIIIDTO) {
        LOG.debug("Request to partially update FicheFacteurXIII : {}", ficheFacteurXIIIDTO);

        return ficheFacteurXIIIRepository
            .findById(ficheFacteurXIIIDTO.getId())
            .map(existingFicheFacteurXIII -> {
                ficheFacteurXIIIMapper.partialUpdate(existingFicheFacteurXIII, ficheFacteurXIIIDTO);

                return existingFicheFacteurXIII;
            })
            .map(ficheFacteurXIIIRepository::save)
            .map(ficheFacteurXIIIMapper::toDto);
    }

    /**
     * Get all the ficheFacteurXIIIS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FicheFacteurXIIIDTO> findAll() {
        LOG.debug("Request to get all FicheFacteurXIIIS");
        return ficheFacteurXIIIRepository
            .findAll()
            .stream()
            .map(ficheFacteurXIIIMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ficheFacteurXIII by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FicheFacteurXIIIDTO> findOne(Long id) {
        LOG.debug("Request to get FicheFacteurXIII : {}", id);
        return ficheFacteurXIIIRepository.findById(id).map(ficheFacteurXIIIMapper::toDto);
    }

    /**
     * Delete the ficheFacteurXIII by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete FicheFacteurXIII : {}", id);
        ficheFacteurXIIIRepository.deleteById(id);
    }
}

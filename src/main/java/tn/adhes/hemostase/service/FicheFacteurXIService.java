package tn.adhes.hemostase.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.adhes.hemostase.domain.FicheFacteurXI;
import tn.adhes.hemostase.repository.FicheFacteurXIRepository;
import tn.adhes.hemostase.service.dto.FicheFacteurXIDTO;
import tn.adhes.hemostase.service.mapper.FicheFacteurXIMapper;

/**
 * Service Implementation for managing {@link tn.adhes.hemostase.domain.FicheFacteurXI}.
 */
@Service
@Transactional
public class FicheFacteurXIService {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurXIService.class);

    private final FicheFacteurXIRepository ficheFacteurXIRepository;

    private final FicheFacteurXIMapper ficheFacteurXIMapper;

    public FicheFacteurXIService(FicheFacteurXIRepository ficheFacteurXIRepository, FicheFacteurXIMapper ficheFacteurXIMapper) {
        this.ficheFacteurXIRepository = ficheFacteurXIRepository;
        this.ficheFacteurXIMapper = ficheFacteurXIMapper;
    }

    /**
     * Save a ficheFacteurXI.
     *
     * @param ficheFacteurXIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurXIDTO save(FicheFacteurXIDTO ficheFacteurXIDTO) {
        LOG.debug("Request to save FicheFacteurXI : {}", ficheFacteurXIDTO);
        FicheFacteurXI ficheFacteurXI = ficheFacteurXIMapper.toEntity(ficheFacteurXIDTO);
        ficheFacteurXI = ficheFacteurXIRepository.save(ficheFacteurXI);
        return ficheFacteurXIMapper.toDto(ficheFacteurXI);
    }

    /**
     * Update a ficheFacteurXI.
     *
     * @param ficheFacteurXIDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheFacteurXIDTO update(FicheFacteurXIDTO ficheFacteurXIDTO) {
        LOG.debug("Request to update FicheFacteurXI : {}", ficheFacteurXIDTO);
        FicheFacteurXI ficheFacteurXI = ficheFacteurXIMapper.toEntity(ficheFacteurXIDTO);
        ficheFacteurXI = ficheFacteurXIRepository.save(ficheFacteurXI);
        return ficheFacteurXIMapper.toDto(ficheFacteurXI);
    }

    /**
     * Partially update a ficheFacteurXI.
     *
     * @param ficheFacteurXIDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FicheFacteurXIDTO> partialUpdate(FicheFacteurXIDTO ficheFacteurXIDTO) {
        LOG.debug("Request to partially update FicheFacteurXI : {}", ficheFacteurXIDTO);

        return ficheFacteurXIRepository
            .findById(ficheFacteurXIDTO.getId())
            .map(existingFicheFacteurXI -> {
                ficheFacteurXIMapper.partialUpdate(existingFicheFacteurXI, ficheFacteurXIDTO);

                return existingFicheFacteurXI;
            })
            .map(ficheFacteurXIRepository::save)
            .map(ficheFacteurXIMapper::toDto);
    }

    /**
     * Get all the ficheFacteurXIS.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FicheFacteurXIDTO> findAll() {
        LOG.debug("Request to get all FicheFacteurXIS");
        return ficheFacteurXIRepository
            .findAll()
            .stream()
            .map(ficheFacteurXIMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ficheFacteurXI by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FicheFacteurXIDTO> findOne(Long id) {
        LOG.debug("Request to get FicheFacteurXI : {}", id);
        return ficheFacteurXIRepository.findById(id).map(ficheFacteurXIMapper::toDto);
    }

    /**
     * Delete the ficheFacteurXI by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete FicheFacteurXI : {}", id);
        ficheFacteurXIRepository.deleteById(id);
    }
}

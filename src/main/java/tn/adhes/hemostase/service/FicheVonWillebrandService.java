package tn.adhes.hemostase.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.adhes.hemostase.domain.FicheVonWillebrand;
import tn.adhes.hemostase.repository.FicheVonWillebrandRepository;
import tn.adhes.hemostase.service.dto.FicheVonWillebrandDTO;
import tn.adhes.hemostase.service.mapper.FicheVonWillebrandMapper;

/**
 * Service Implementation for managing {@link tn.adhes.hemostase.domain.FicheVonWillebrand}.
 */
@Service
@Transactional
public class FicheVonWillebrandService {

    private static final Logger LOG = LoggerFactory.getLogger(FicheVonWillebrandService.class);

    private final FicheVonWillebrandRepository ficheVonWillebrandRepository;

    private final FicheVonWillebrandMapper ficheVonWillebrandMapper;

    public FicheVonWillebrandService(
        FicheVonWillebrandRepository ficheVonWillebrandRepository,
        FicheVonWillebrandMapper ficheVonWillebrandMapper
    ) {
        this.ficheVonWillebrandRepository = ficheVonWillebrandRepository;
        this.ficheVonWillebrandMapper = ficheVonWillebrandMapper;
    }

    /**
     * Save a ficheVonWillebrand.
     *
     * @param ficheVonWillebrandDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheVonWillebrandDTO save(FicheVonWillebrandDTO ficheVonWillebrandDTO) {
        LOG.debug("Request to save FicheVonWillebrand : {}", ficheVonWillebrandDTO);
        FicheVonWillebrand ficheVonWillebrand = ficheVonWillebrandMapper.toEntity(ficheVonWillebrandDTO);
        ficheVonWillebrand = ficheVonWillebrandRepository.save(ficheVonWillebrand);
        return ficheVonWillebrandMapper.toDto(ficheVonWillebrand);
    }

    /**
     * Update a ficheVonWillebrand.
     *
     * @param ficheVonWillebrandDTO the entity to save.
     * @return the persisted entity.
     */
    public FicheVonWillebrandDTO update(FicheVonWillebrandDTO ficheVonWillebrandDTO) {
        LOG.debug("Request to update FicheVonWillebrand : {}", ficheVonWillebrandDTO);
        FicheVonWillebrand ficheVonWillebrand = ficheVonWillebrandMapper.toEntity(ficheVonWillebrandDTO);
        ficheVonWillebrand = ficheVonWillebrandRepository.save(ficheVonWillebrand);
        return ficheVonWillebrandMapper.toDto(ficheVonWillebrand);
    }

    /**
     * Partially update a ficheVonWillebrand.
     *
     * @param ficheVonWillebrandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FicheVonWillebrandDTO> partialUpdate(FicheVonWillebrandDTO ficheVonWillebrandDTO) {
        LOG.debug("Request to partially update FicheVonWillebrand : {}", ficheVonWillebrandDTO);

        return ficheVonWillebrandRepository
            .findById(ficheVonWillebrandDTO.getId())
            .map(existingFicheVonWillebrand -> {
                ficheVonWillebrandMapper.partialUpdate(existingFicheVonWillebrand, ficheVonWillebrandDTO);

                return existingFicheVonWillebrand;
            })
            .map(ficheVonWillebrandRepository::save)
            .map(ficheVonWillebrandMapper::toDto);
    }

    /**
     * Get all the ficheVonWillebrands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FicheVonWillebrandDTO> findAll() {
        LOG.debug("Request to get all FicheVonWillebrands");
        return ficheVonWillebrandRepository
            .findAll()
            .stream()
            .map(ficheVonWillebrandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ficheVonWillebrand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FicheVonWillebrandDTO> findOne(Long id) {
        LOG.debug("Request to get FicheVonWillebrand : {}", id);
        return ficheVonWillebrandRepository.findById(id).map(ficheVonWillebrandMapper::toDto);
    }

    /**
     * Delete the ficheVonWillebrand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete FicheVonWillebrand : {}", id);
        ficheVonWillebrandRepository.deleteById(id);
    }
}

package tn.adhes.hemostase.web.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.adhes.hemostase.repository.FicheVonWillebrandRepository;
import tn.adhes.hemostase.service.FicheVonWillebrandService;
import tn.adhes.hemostase.service.dto.FicheVonWillebrandDTO;
import tn.adhes.hemostase.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.adhes.hemostase.domain.FicheVonWillebrand}.
 */
@RestController
@RequestMapping("/api/fiche-von-willebrands")
public class FicheVonWillebrandResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheVonWillebrandResource.class);

    private static final String ENTITY_NAME = "ficheVonWillebrand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheVonWillebrandService ficheVonWillebrandService;

    private final FicheVonWillebrandRepository ficheVonWillebrandRepository;

    public FicheVonWillebrandResource(
        FicheVonWillebrandService ficheVonWillebrandService,
        FicheVonWillebrandRepository ficheVonWillebrandRepository
    ) {
        this.ficheVonWillebrandService = ficheVonWillebrandService;
        this.ficheVonWillebrandRepository = ficheVonWillebrandRepository;
    }

    /**
     * {@code POST  /fiche-von-willebrands} : Create a new ficheVonWillebrand.
     *
     * @param ficheVonWillebrandDTO the ficheVonWillebrandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheVonWillebrandDTO, or with status {@code 400 (Bad Request)} if the ficheVonWillebrand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FicheVonWillebrandDTO> createFicheVonWillebrand(@Valid @RequestBody FicheVonWillebrandDTO ficheVonWillebrandDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save FicheVonWillebrand : {}", ficheVonWillebrandDTO);
        if (ficheVonWillebrandDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheVonWillebrand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ficheVonWillebrandDTO = ficheVonWillebrandService.save(ficheVonWillebrandDTO);
        return ResponseEntity.created(new URI("/api/fiche-von-willebrands/" + ficheVonWillebrandDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ficheVonWillebrandDTO.getId().toString()))
            .body(ficheVonWillebrandDTO);
    }

    /**
     * {@code PUT  /fiche-von-willebrands/:id} : Updates an existing ficheVonWillebrand.
     *
     * @param id the id of the ficheVonWillebrandDTO to save.
     * @param ficheVonWillebrandDTO the ficheVonWillebrandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheVonWillebrandDTO,
     * or with status {@code 400 (Bad Request)} if the ficheVonWillebrandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheVonWillebrandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FicheVonWillebrandDTO> updateFicheVonWillebrand(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FicheVonWillebrandDTO ficheVonWillebrandDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update FicheVonWillebrand : {}, {}", id, ficheVonWillebrandDTO);
        if (ficheVonWillebrandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheVonWillebrandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheVonWillebrandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ficheVonWillebrandDTO = ficheVonWillebrandService.update(ficheVonWillebrandDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheVonWillebrandDTO.getId().toString()))
            .body(ficheVonWillebrandDTO);
    }

    /**
     * {@code PATCH  /fiche-von-willebrands/:id} : Partial updates given fields of an existing ficheVonWillebrand, field will ignore if it is null
     *
     * @param id the id of the ficheVonWillebrandDTO to save.
     * @param ficheVonWillebrandDTO the ficheVonWillebrandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheVonWillebrandDTO,
     * or with status {@code 400 (Bad Request)} if the ficheVonWillebrandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ficheVonWillebrandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ficheVonWillebrandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FicheVonWillebrandDTO> partialUpdateFicheVonWillebrand(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FicheVonWillebrandDTO ficheVonWillebrandDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FicheVonWillebrand partially : {}, {}", id, ficheVonWillebrandDTO);
        if (ficheVonWillebrandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheVonWillebrandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheVonWillebrandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FicheVonWillebrandDTO> result = ficheVonWillebrandService.partialUpdate(ficheVonWillebrandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheVonWillebrandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fiche-von-willebrands} : get all the ficheVonWillebrands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheVonWillebrands in body.
     */
    @GetMapping("")
    public List<FicheVonWillebrandDTO> getAllFicheVonWillebrands() {
        LOG.debug("REST request to get all FicheVonWillebrands");
        return ficheVonWillebrandService.findAll();
    }

    /**
     * {@code GET  /fiche-von-willebrands/:id} : get the "id" ficheVonWillebrand.
     *
     * @param id the id of the ficheVonWillebrandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheVonWillebrandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FicheVonWillebrandDTO> getFicheVonWillebrand(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FicheVonWillebrand : {}", id);
        Optional<FicheVonWillebrandDTO> ficheVonWillebrandDTO = ficheVonWillebrandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheVonWillebrandDTO);
    }

    /**
     * {@code DELETE  /fiche-von-willebrands/:id} : delete the "id" ficheVonWillebrand.
     *
     * @param id the id of the ficheVonWillebrandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheVonWillebrand(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FicheVonWillebrand : {}", id);
        ficheVonWillebrandService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

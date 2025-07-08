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
import tn.adhes.hemostase.repository.FicheFacteurXIRepository;
import tn.adhes.hemostase.service.FicheFacteurXIService;
import tn.adhes.hemostase.service.dto.FicheFacteurXIDTO;
import tn.adhes.hemostase.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.adhes.hemostase.domain.FicheFacteurXI}.
 */
@RestController
@RequestMapping("/api/fiche-facteur-xis")
public class FicheFacteurXIResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurXIResource.class);

    private static final String ENTITY_NAME = "ficheFacteurXI";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheFacteurXIService ficheFacteurXIService;

    private final FicheFacteurXIRepository ficheFacteurXIRepository;

    public FicheFacteurXIResource(FicheFacteurXIService ficheFacteurXIService, FicheFacteurXIRepository ficheFacteurXIRepository) {
        this.ficheFacteurXIService = ficheFacteurXIService;
        this.ficheFacteurXIRepository = ficheFacteurXIRepository;
    }

    /**
     * {@code POST  /fiche-facteur-xis} : Create a new ficheFacteurXI.
     *
     * @param ficheFacteurXIDTO the ficheFacteurXIDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheFacteurXIDTO, or with status {@code 400 (Bad Request)} if the ficheFacteurXI has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FicheFacteurXIDTO> createFicheFacteurXI(@Valid @RequestBody FicheFacteurXIDTO ficheFacteurXIDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save FicheFacteurXI : {}", ficheFacteurXIDTO);
        if (ficheFacteurXIDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheFacteurXI cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ficheFacteurXIDTO = ficheFacteurXIService.save(ficheFacteurXIDTO);
        return ResponseEntity.created(new URI("/api/fiche-facteur-xis/" + ficheFacteurXIDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ficheFacteurXIDTO.getId().toString()))
            .body(ficheFacteurXIDTO);
    }

    /**
     * {@code PUT  /fiche-facteur-xis/:id} : Updates an existing ficheFacteurXI.
     *
     * @param id the id of the ficheFacteurXIDTO to save.
     * @param ficheFacteurXIDTO the ficheFacteurXIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurXIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurXIDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurXIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FicheFacteurXIDTO> updateFicheFacteurXI(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FicheFacteurXIDTO ficheFacteurXIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update FicheFacteurXI : {}, {}", id, ficheFacteurXIDTO);
        if (ficheFacteurXIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurXIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurXIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ficheFacteurXIDTO = ficheFacteurXIService.update(ficheFacteurXIDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurXIDTO.getId().toString()))
            .body(ficheFacteurXIDTO);
    }

    /**
     * {@code PATCH  /fiche-facteur-xis/:id} : Partial updates given fields of an existing ficheFacteurXI, field will ignore if it is null
     *
     * @param id the id of the ficheFacteurXIDTO to save.
     * @param ficheFacteurXIDTO the ficheFacteurXIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurXIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurXIDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ficheFacteurXIDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurXIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FicheFacteurXIDTO> partialUpdateFicheFacteurXI(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FicheFacteurXIDTO ficheFacteurXIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FicheFacteurXI partially : {}, {}", id, ficheFacteurXIDTO);
        if (ficheFacteurXIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurXIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurXIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FicheFacteurXIDTO> result = ficheFacteurXIService.partialUpdate(ficheFacteurXIDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurXIDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fiche-facteur-xis} : get all the ficheFacteurXIS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheFacteurXIS in body.
     */
    @GetMapping("")
    public List<FicheFacteurXIDTO> getAllFicheFacteurXIS() {
        LOG.debug("REST request to get all FicheFacteurXIS");
        return ficheFacteurXIService.findAll();
    }

    /**
     * {@code GET  /fiche-facteur-xis/:id} : get the "id" ficheFacteurXI.
     *
     * @param id the id of the ficheFacteurXIDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheFacteurXIDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FicheFacteurXIDTO> getFicheFacteurXI(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FicheFacteurXI : {}", id);
        Optional<FicheFacteurXIDTO> ficheFacteurXIDTO = ficheFacteurXIService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheFacteurXIDTO);
    }

    /**
     * {@code DELETE  /fiche-facteur-xis/:id} : delete the "id" ficheFacteurXI.
     *
     * @param id the id of the ficheFacteurXIDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheFacteurXI(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FicheFacteurXI : {}", id);
        ficheFacteurXIService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

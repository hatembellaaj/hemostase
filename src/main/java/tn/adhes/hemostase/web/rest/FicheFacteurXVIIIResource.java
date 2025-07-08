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
import tn.adhes.hemostase.repository.FicheFacteurXVIIIRepository;
import tn.adhes.hemostase.service.FicheFacteurXVIIIService;
import tn.adhes.hemostase.service.dto.FicheFacteurXVIIIDTO;
import tn.adhes.hemostase.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.adhes.hemostase.domain.FicheFacteurXVIII}.
 */
@RestController
@RequestMapping("/api/fiche-facteur-xviiis")
public class FicheFacteurXVIIIResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurXVIIIResource.class);

    private static final String ENTITY_NAME = "ficheFacteurXVIII";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheFacteurXVIIIService ficheFacteurXVIIIService;

    private final FicheFacteurXVIIIRepository ficheFacteurXVIIIRepository;

    public FicheFacteurXVIIIResource(
        FicheFacteurXVIIIService ficheFacteurXVIIIService,
        FicheFacteurXVIIIRepository ficheFacteurXVIIIRepository
    ) {
        this.ficheFacteurXVIIIService = ficheFacteurXVIIIService;
        this.ficheFacteurXVIIIRepository = ficheFacteurXVIIIRepository;
    }

    /**
     * {@code POST  /fiche-facteur-xviiis} : Create a new ficheFacteurXVIII.
     *
     * @param ficheFacteurXVIIIDTO the ficheFacteurXVIIIDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheFacteurXVIIIDTO, or with status {@code 400 (Bad Request)} if the ficheFacteurXVIII has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FicheFacteurXVIIIDTO> createFicheFacteurXVIII(@Valid @RequestBody FicheFacteurXVIIIDTO ficheFacteurXVIIIDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save FicheFacteurXVIII : {}", ficheFacteurXVIIIDTO);
        if (ficheFacteurXVIIIDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheFacteurXVIII cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ficheFacteurXVIIIDTO = ficheFacteurXVIIIService.save(ficheFacteurXVIIIDTO);
        return ResponseEntity.created(new URI("/api/fiche-facteur-xviiis/" + ficheFacteurXVIIIDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ficheFacteurXVIIIDTO.getId().toString()))
            .body(ficheFacteurXVIIIDTO);
    }

    /**
     * {@code PUT  /fiche-facteur-xviiis/:id} : Updates an existing ficheFacteurXVIII.
     *
     * @param id the id of the ficheFacteurXVIIIDTO to save.
     * @param ficheFacteurXVIIIDTO the ficheFacteurXVIIIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurXVIIIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurXVIIIDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurXVIIIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FicheFacteurXVIIIDTO> updateFicheFacteurXVIII(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FicheFacteurXVIIIDTO ficheFacteurXVIIIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update FicheFacteurXVIII : {}, {}", id, ficheFacteurXVIIIDTO);
        if (ficheFacteurXVIIIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurXVIIIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurXVIIIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ficheFacteurXVIIIDTO = ficheFacteurXVIIIService.update(ficheFacteurXVIIIDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurXVIIIDTO.getId().toString()))
            .body(ficheFacteurXVIIIDTO);
    }

    /**
     * {@code PATCH  /fiche-facteur-xviiis/:id} : Partial updates given fields of an existing ficheFacteurXVIII, field will ignore if it is null
     *
     * @param id the id of the ficheFacteurXVIIIDTO to save.
     * @param ficheFacteurXVIIIDTO the ficheFacteurXVIIIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurXVIIIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurXVIIIDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ficheFacteurXVIIIDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurXVIIIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FicheFacteurXVIIIDTO> partialUpdateFicheFacteurXVIII(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FicheFacteurXVIIIDTO ficheFacteurXVIIIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FicheFacteurXVIII partially : {}, {}", id, ficheFacteurXVIIIDTO);
        if (ficheFacteurXVIIIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurXVIIIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurXVIIIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FicheFacteurXVIIIDTO> result = ficheFacteurXVIIIService.partialUpdate(ficheFacteurXVIIIDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurXVIIIDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fiche-facteur-xviiis} : get all the ficheFacteurXVIIIS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheFacteurXVIIIS in body.
     */
    @GetMapping("")
    public List<FicheFacteurXVIIIDTO> getAllFicheFacteurXVIIIS() {
        LOG.debug("REST request to get all FicheFacteurXVIIIS");
        return ficheFacteurXVIIIService.findAll();
    }

    /**
     * {@code GET  /fiche-facteur-xviiis/:id} : get the "id" ficheFacteurXVIII.
     *
     * @param id the id of the ficheFacteurXVIIIDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheFacteurXVIIIDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FicheFacteurXVIIIDTO> getFicheFacteurXVIII(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FicheFacteurXVIII : {}", id);
        Optional<FicheFacteurXVIIIDTO> ficheFacteurXVIIIDTO = ficheFacteurXVIIIService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheFacteurXVIIIDTO);
    }

    /**
     * {@code DELETE  /fiche-facteur-xviiis/:id} : delete the "id" ficheFacteurXVIII.
     *
     * @param id the id of the ficheFacteurXVIIIDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheFacteurXVIII(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FicheFacteurXVIII : {}", id);
        ficheFacteurXVIIIService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

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
import tn.adhes.hemostase.repository.FicheFacteurXIIIRepository;
import tn.adhes.hemostase.service.FicheFacteurXIIIService;
import tn.adhes.hemostase.service.dto.FicheFacteurXIIIDTO;
import tn.adhes.hemostase.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.adhes.hemostase.domain.FicheFacteurXIII}.
 */
@RestController
@RequestMapping("/api/fiche-facteur-xiiis")
public class FicheFacteurXIIIResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurXIIIResource.class);

    private static final String ENTITY_NAME = "ficheFacteurXIII";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheFacteurXIIIService ficheFacteurXIIIService;

    private final FicheFacteurXIIIRepository ficheFacteurXIIIRepository;

    public FicheFacteurXIIIResource(
        FicheFacteurXIIIService ficheFacteurXIIIService,
        FicheFacteurXIIIRepository ficheFacteurXIIIRepository
    ) {
        this.ficheFacteurXIIIService = ficheFacteurXIIIService;
        this.ficheFacteurXIIIRepository = ficheFacteurXIIIRepository;
    }

    /**
     * {@code POST  /fiche-facteur-xiiis} : Create a new ficheFacteurXIII.
     *
     * @param ficheFacteurXIIIDTO the ficheFacteurXIIIDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheFacteurXIIIDTO, or with status {@code 400 (Bad Request)} if the ficheFacteurXIII has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FicheFacteurXIIIDTO> createFicheFacteurXIII(@Valid @RequestBody FicheFacteurXIIIDTO ficheFacteurXIIIDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save FicheFacteurXIII : {}", ficheFacteurXIIIDTO);
        if (ficheFacteurXIIIDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheFacteurXIII cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ficheFacteurXIIIDTO = ficheFacteurXIIIService.save(ficheFacteurXIIIDTO);
        return ResponseEntity.created(new URI("/api/fiche-facteur-xiiis/" + ficheFacteurXIIIDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ficheFacteurXIIIDTO.getId().toString()))
            .body(ficheFacteurXIIIDTO);
    }

    /**
     * {@code PUT  /fiche-facteur-xiiis/:id} : Updates an existing ficheFacteurXIII.
     *
     * @param id the id of the ficheFacteurXIIIDTO to save.
     * @param ficheFacteurXIIIDTO the ficheFacteurXIIIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurXIIIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurXIIIDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurXIIIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FicheFacteurXIIIDTO> updateFicheFacteurXIII(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FicheFacteurXIIIDTO ficheFacteurXIIIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update FicheFacteurXIII : {}, {}", id, ficheFacteurXIIIDTO);
        if (ficheFacteurXIIIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurXIIIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurXIIIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ficheFacteurXIIIDTO = ficheFacteurXIIIService.update(ficheFacteurXIIIDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurXIIIDTO.getId().toString()))
            .body(ficheFacteurXIIIDTO);
    }

    /**
     * {@code PATCH  /fiche-facteur-xiiis/:id} : Partial updates given fields of an existing ficheFacteurXIII, field will ignore if it is null
     *
     * @param id the id of the ficheFacteurXIIIDTO to save.
     * @param ficheFacteurXIIIDTO the ficheFacteurXIIIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurXIIIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurXIIIDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ficheFacteurXIIIDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurXIIIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FicheFacteurXIIIDTO> partialUpdateFicheFacteurXIII(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FicheFacteurXIIIDTO ficheFacteurXIIIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FicheFacteurXIII partially : {}, {}", id, ficheFacteurXIIIDTO);
        if (ficheFacteurXIIIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurXIIIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurXIIIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FicheFacteurXIIIDTO> result = ficheFacteurXIIIService.partialUpdate(ficheFacteurXIIIDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurXIIIDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fiche-facteur-xiiis} : get all the ficheFacteurXIIIS.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheFacteurXIIIS in body.
     */
    @GetMapping("")
    public List<FicheFacteurXIIIDTO> getAllFicheFacteurXIIIS() {
        LOG.debug("REST request to get all FicheFacteurXIIIS");
        return ficheFacteurXIIIService.findAll();
    }

    /**
     * {@code GET  /fiche-facteur-xiiis/:id} : get the "id" ficheFacteurXIII.
     *
     * @param id the id of the ficheFacteurXIIIDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheFacteurXIIIDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FicheFacteurXIIIDTO> getFicheFacteurXIII(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FicheFacteurXIII : {}", id);
        Optional<FicheFacteurXIIIDTO> ficheFacteurXIIIDTO = ficheFacteurXIIIService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheFacteurXIIIDTO);
    }

    /**
     * {@code DELETE  /fiche-facteur-xiiis/:id} : delete the "id" ficheFacteurXIII.
     *
     * @param id the id of the ficheFacteurXIIIDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheFacteurXIII(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FicheFacteurXIII : {}", id);
        ficheFacteurXIIIService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

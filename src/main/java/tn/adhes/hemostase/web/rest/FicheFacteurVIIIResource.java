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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.adhes.hemostase.repository.FicheFacteurVIIIRepository;
import tn.adhes.hemostase.service.FicheFacteurVIIIService;
import tn.adhes.hemostase.service.dto.FicheFacteurVIIIDTO;
import tn.adhes.hemostase.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.adhes.hemostase.domain.FicheFacteurVIII}.
 */
@RestController
@RequestMapping("/api/fiche-facteur-viiis")
public class FicheFacteurVIIIResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurVIIIResource.class);

    private static final String ENTITY_NAME = "ficheFacteurVIII";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheFacteurVIIIService ficheFacteurVIIIService;

    private final FicheFacteurVIIIRepository ficheFacteurVIIIRepository;

    public FicheFacteurVIIIResource(
        FicheFacteurVIIIService ficheFacteurVIIIService,
        FicheFacteurVIIIRepository ficheFacteurVIIIRepository
    ) {
        this.ficheFacteurVIIIService = ficheFacteurVIIIService;
        this.ficheFacteurVIIIRepository = ficheFacteurVIIIRepository;
    }

    /**
     * {@code POST  /fiche-facteur-viiis} : Create a new ficheFacteurVIII.
     *
     * @param ficheFacteurVIIIDTO the ficheFacteurVIIIDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheFacteurVIIIDTO, or with status {@code 400 (Bad Request)} if the ficheFacteurVIII has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FicheFacteurVIIIDTO> createFicheFacteurVIII(@Valid @RequestBody FicheFacteurVIIIDTO ficheFacteurVIIIDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save FicheFacteurVIII : {}", ficheFacteurVIIIDTO);
        if (ficheFacteurVIIIDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheFacteurVIII cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ficheFacteurVIIIDTO = ficheFacteurVIIIService.save(ficheFacteurVIIIDTO);
        return ResponseEntity.created(new URI("/api/fiche-facteur-viiis/" + ficheFacteurVIIIDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ficheFacteurVIIIDTO.getId().toString()))
            .body(ficheFacteurVIIIDTO);
    }

    /**
     * {@code PUT  /fiche-facteur-viiis/:id} : Updates an existing ficheFacteurVIII.
     *
     * @param id the id of the ficheFacteurVIIIDTO to save.
     * @param ficheFacteurVIIIDTO the ficheFacteurVIIIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurVIIIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurVIIIDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurVIIIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FicheFacteurVIIIDTO> updateFicheFacteurVIII(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FicheFacteurVIIIDTO ficheFacteurVIIIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update FicheFacteurVIII : {}, {}", id, ficheFacteurVIIIDTO);
        if (ficheFacteurVIIIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurVIIIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurVIIIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ficheFacteurVIIIDTO = ficheFacteurVIIIService.update(ficheFacteurVIIIDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurVIIIDTO.getId().toString()))
            .body(ficheFacteurVIIIDTO);
    }

    /**
     * {@code PATCH  /fiche-facteur-viiis/:id} : Partial updates given fields of an existing ficheFacteurVIII, field will ignore if it is null
     *
     * @param id the id of the ficheFacteurVIIIDTO to save.
     * @param ficheFacteurVIIIDTO the ficheFacteurVIIIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurVIIIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurVIIIDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ficheFacteurVIIIDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurVIIIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FicheFacteurVIIIDTO> partialUpdateFicheFacteurVIII(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FicheFacteurVIIIDTO ficheFacteurVIIIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FicheFacteurVIII partially : {}, {}", id, ficheFacteurVIIIDTO);
        if (ficheFacteurVIIIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurVIIIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurVIIIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FicheFacteurVIIIDTO> result = ficheFacteurVIIIService.partialUpdate(ficheFacteurVIIIDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurVIIIDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fiche-facteur-viiis} : get all the ficheFacteurVIIIS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheFacteurVIIIS in body.
     */
    @GetMapping("")
    public ResponseEntity<List<FicheFacteurVIIIDTO>> getAllFicheFacteurVIIIS(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of FicheFacteurVIIIS");
        Page<FicheFacteurVIIIDTO> page = ficheFacteurVIIIService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fiche-facteur-viiis/:id} : get the "id" ficheFacteurVIII.
     *
     * @param id the id of the ficheFacteurVIIIDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheFacteurVIIIDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FicheFacteurVIIIDTO> getFicheFacteurVIII(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FicheFacteurVIII : {}", id);
        Optional<FicheFacteurVIIIDTO> ficheFacteurVIIIDTO = ficheFacteurVIIIService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheFacteurVIIIDTO);
    }

    /**
     * {@code DELETE  /fiche-facteur-viiis/:id} : delete the "id" ficheFacteurVIII.
     *
     * @param id the id of the ficheFacteurVIIIDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheFacteurVIII(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FicheFacteurVIII : {}", id);
        ficheFacteurVIIIService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

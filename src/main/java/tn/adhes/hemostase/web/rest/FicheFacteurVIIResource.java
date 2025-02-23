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
import tn.adhes.hemostase.repository.FicheFacteurVIIRepository;
import tn.adhes.hemostase.service.FicheFacteurVIIService;
import tn.adhes.hemostase.service.dto.FicheFacteurVIIDTO;
import tn.adhes.hemostase.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.adhes.hemostase.domain.FicheFacteurVII}.
 */
@RestController
@RequestMapping("/api/fiche-facteur-viis")
public class FicheFacteurVIIResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheFacteurVIIResource.class);

    private static final String ENTITY_NAME = "ficheFacteurVII";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheFacteurVIIService ficheFacteurVIIService;

    private final FicheFacteurVIIRepository ficheFacteurVIIRepository;

    public FicheFacteurVIIResource(FicheFacteurVIIService ficheFacteurVIIService, FicheFacteurVIIRepository ficheFacteurVIIRepository) {
        this.ficheFacteurVIIService = ficheFacteurVIIService;
        this.ficheFacteurVIIRepository = ficheFacteurVIIRepository;
    }

    /**
     * {@code POST  /fiche-facteur-viis} : Create a new ficheFacteurVII.
     *
     * @param ficheFacteurVIIDTO the ficheFacteurVIIDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheFacteurVIIDTO, or with status {@code 400 (Bad Request)} if the ficheFacteurVII has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FicheFacteurVIIDTO> createFicheFacteurVII(@Valid @RequestBody FicheFacteurVIIDTO ficheFacteurVIIDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save FicheFacteurVII : {}", ficheFacteurVIIDTO);
        if (ficheFacteurVIIDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheFacteurVII cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ficheFacteurVIIDTO = ficheFacteurVIIService.save(ficheFacteurVIIDTO);
        return ResponseEntity.created(new URI("/api/fiche-facteur-viis/" + ficheFacteurVIIDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ficheFacteurVIIDTO.getId().toString()))
            .body(ficheFacteurVIIDTO);
    }

    /**
     * {@code PUT  /fiche-facteur-viis/:id} : Updates an existing ficheFacteurVII.
     *
     * @param id the id of the ficheFacteurVIIDTO to save.
     * @param ficheFacteurVIIDTO the ficheFacteurVIIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurVIIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurVIIDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurVIIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FicheFacteurVIIDTO> updateFicheFacteurVII(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FicheFacteurVIIDTO ficheFacteurVIIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update FicheFacteurVII : {}, {}", id, ficheFacteurVIIDTO);
        if (ficheFacteurVIIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurVIIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurVIIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ficheFacteurVIIDTO = ficheFacteurVIIService.update(ficheFacteurVIIDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurVIIDTO.getId().toString()))
            .body(ficheFacteurVIIDTO);
    }

    /**
     * {@code PATCH  /fiche-facteur-viis/:id} : Partial updates given fields of an existing ficheFacteurVII, field will ignore if it is null
     *
     * @param id the id of the ficheFacteurVIIDTO to save.
     * @param ficheFacteurVIIDTO the ficheFacteurVIIDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheFacteurVIIDTO,
     * or with status {@code 400 (Bad Request)} if the ficheFacteurVIIDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ficheFacteurVIIDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ficheFacteurVIIDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FicheFacteurVIIDTO> partialUpdateFicheFacteurVII(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FicheFacteurVIIDTO ficheFacteurVIIDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FicheFacteurVII partially : {}, {}", id, ficheFacteurVIIDTO);
        if (ficheFacteurVIIDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheFacteurVIIDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheFacteurVIIRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FicheFacteurVIIDTO> result = ficheFacteurVIIService.partialUpdate(ficheFacteurVIIDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheFacteurVIIDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fiche-facteur-viis} : get all the ficheFacteurVIIS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheFacteurVIIS in body.
     */
    @GetMapping("")
    public ResponseEntity<List<FicheFacteurVIIDTO>> getAllFicheFacteurVIIS(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of FicheFacteurVIIS");
        Page<FicheFacteurVIIDTO> page = ficheFacteurVIIService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fiche-facteur-viis/:id} : get the "id" ficheFacteurVII.
     *
     * @param id the id of the ficheFacteurVIIDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheFacteurVIIDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FicheFacteurVIIDTO> getFicheFacteurVII(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FicheFacteurVII : {}", id);
        Optional<FicheFacteurVIIDTO> ficheFacteurVIIDTO = ficheFacteurVIIService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheFacteurVIIDTO);
    }

    /**
     * {@code DELETE  /fiche-facteur-viis/:id} : delete the "id" ficheFacteurVII.
     *
     * @param id the id of the ficheFacteurVIIDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheFacteurVII(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FicheFacteurVII : {}", id);
        ficheFacteurVIIService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

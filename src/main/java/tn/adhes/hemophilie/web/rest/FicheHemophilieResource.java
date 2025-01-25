package tn.adhes.hemophilie.web.rest;

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
import tn.adhes.hemophilie.repository.FicheHemophilieRepository;
import tn.adhes.hemophilie.service.FicheHemophilieService;
import tn.adhes.hemophilie.service.dto.FicheHemophilieDTO;
import tn.adhes.hemophilie.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.adhes.hemophilie.domain.FicheHemophilie}.
 */
@RestController
@RequestMapping("/api/fiche-hemophilies")
public class FicheHemophilieResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheHemophilieResource.class);

    private static final String ENTITY_NAME = "ficheHemophilie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheHemophilieService ficheHemophilieService;

    private final FicheHemophilieRepository ficheHemophilieRepository;

    public FicheHemophilieResource(FicheHemophilieService ficheHemophilieService, FicheHemophilieRepository ficheHemophilieRepository) {
        this.ficheHemophilieService = ficheHemophilieService;
        this.ficheHemophilieRepository = ficheHemophilieRepository;
    }

    /**
     * {@code POST  /fiche-hemophilies} : Create a new ficheHemophilie.
     *
     * @param ficheHemophilieDTO the ficheHemophilieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheHemophilieDTO, or with status {@code 400 (Bad Request)} if the ficheHemophilie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FicheHemophilieDTO> createFicheHemophilie(@Valid @RequestBody FicheHemophilieDTO ficheHemophilieDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save FicheHemophilie : {}", ficheHemophilieDTO);
        if (ficheHemophilieDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheHemophilie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ficheHemophilieDTO = ficheHemophilieService.save(ficheHemophilieDTO);
        return ResponseEntity.created(new URI("/api/fiche-hemophilies/" + ficheHemophilieDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ficheHemophilieDTO.getId().toString()))
            .body(ficheHemophilieDTO);
    }

    /**
     * {@code PUT  /fiche-hemophilies/:id} : Updates an existing ficheHemophilie.
     *
     * @param id the id of the ficheHemophilieDTO to save.
     * @param ficheHemophilieDTO the ficheHemophilieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheHemophilieDTO,
     * or with status {@code 400 (Bad Request)} if the ficheHemophilieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheHemophilieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FicheHemophilieDTO> updateFicheHemophilie(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FicheHemophilieDTO ficheHemophilieDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update FicheHemophilie : {}, {}", id, ficheHemophilieDTO);
        if (ficheHemophilieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheHemophilieDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheHemophilieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ficheHemophilieDTO = ficheHemophilieService.update(ficheHemophilieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheHemophilieDTO.getId().toString()))
            .body(ficheHemophilieDTO);
    }

    /**
     * {@code PATCH  /fiche-hemophilies/:id} : Partial updates given fields of an existing ficheHemophilie, field will ignore if it is null
     *
     * @param id the id of the ficheHemophilieDTO to save.
     * @param ficheHemophilieDTO the ficheHemophilieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheHemophilieDTO,
     * or with status {@code 400 (Bad Request)} if the ficheHemophilieDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ficheHemophilieDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ficheHemophilieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FicheHemophilieDTO> partialUpdateFicheHemophilie(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FicheHemophilieDTO ficheHemophilieDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FicheHemophilie partially : {}, {}", id, ficheHemophilieDTO);
        if (ficheHemophilieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheHemophilieDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheHemophilieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FicheHemophilieDTO> result = ficheHemophilieService.partialUpdate(ficheHemophilieDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheHemophilieDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fiche-hemophilies} : get all the ficheHemophilies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheHemophilies in body.
     */
    @GetMapping("")
    public ResponseEntity<List<FicheHemophilieDTO>> getAllFicheHemophilies(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of FicheHemophilies");
        Page<FicheHemophilieDTO> page = ficheHemophilieService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fiche-hemophilies/:id} : get the "id" ficheHemophilie.
     *
     * @param id the id of the ficheHemophilieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheHemophilieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FicheHemophilieDTO> getFicheHemophilie(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FicheHemophilie : {}", id);
        Optional<FicheHemophilieDTO> ficheHemophilieDTO = ficheHemophilieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheHemophilieDTO);
    }

    /**
     * {@code DELETE  /fiche-hemophilies/:id} : delete the "id" ficheHemophilie.
     *
     * @param id the id of the ficheHemophilieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheHemophilie(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FicheHemophilie : {}", id);
        ficheHemophilieService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

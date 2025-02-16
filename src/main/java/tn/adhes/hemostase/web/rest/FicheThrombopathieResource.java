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
import tn.adhes.hemostase.repository.FicheThrombopathieRepository;
import tn.adhes.hemostase.service.FicheThrombopathieService;
import tn.adhes.hemostase.service.dto.FicheThrombopathieDTO;
import tn.adhes.hemostase.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.adhes.hemostase.domain.FicheThrombopathie}.
 */
@RestController
@RequestMapping("/api/fiche-thrombopathies")
public class FicheThrombopathieResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheThrombopathieResource.class);

    private static final String ENTITY_NAME = "ficheThrombopathie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheThrombopathieService ficheThrombopathieService;

    private final FicheThrombopathieRepository ficheThrombopathieRepository;

    public FicheThrombopathieResource(
        FicheThrombopathieService ficheThrombopathieService,
        FicheThrombopathieRepository ficheThrombopathieRepository
    ) {
        this.ficheThrombopathieService = ficheThrombopathieService;
        this.ficheThrombopathieRepository = ficheThrombopathieRepository;
    }

    /**
     * {@code POST  /fiche-thrombopathies} : Create a new ficheThrombopathie.
     *
     * @param ficheThrombopathieDTO the ficheThrombopathieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ficheThrombopathieDTO, or with status {@code 400 (Bad Request)} if the ficheThrombopathie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FicheThrombopathieDTO> createFicheThrombopathie(@Valid @RequestBody FicheThrombopathieDTO ficheThrombopathieDTO)
        throws URISyntaxException {
        LOG.debug("REST request to save FicheThrombopathie : {}", ficheThrombopathieDTO);
        if (ficheThrombopathieDTO.getId() != null) {
            throw new BadRequestAlertException("A new ficheThrombopathie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ficheThrombopathieDTO = ficheThrombopathieService.save(ficheThrombopathieDTO);
        return ResponseEntity.created(new URI("/api/fiche-thrombopathies/" + ficheThrombopathieDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, ficheThrombopathieDTO.getId().toString()))
            .body(ficheThrombopathieDTO);
    }

    /**
     * {@code PUT  /fiche-thrombopathies/:id} : Updates an existing ficheThrombopathie.
     *
     * @param id the id of the ficheThrombopathieDTO to save.
     * @param ficheThrombopathieDTO the ficheThrombopathieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheThrombopathieDTO,
     * or with status {@code 400 (Bad Request)} if the ficheThrombopathieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ficheThrombopathieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FicheThrombopathieDTO> updateFicheThrombopathie(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FicheThrombopathieDTO ficheThrombopathieDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update FicheThrombopathie : {}, {}", id, ficheThrombopathieDTO);
        if (ficheThrombopathieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheThrombopathieDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheThrombopathieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ficheThrombopathieDTO = ficheThrombopathieService.update(ficheThrombopathieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheThrombopathieDTO.getId().toString()))
            .body(ficheThrombopathieDTO);
    }

    /**
     * {@code PATCH  /fiche-thrombopathies/:id} : Partial updates given fields of an existing ficheThrombopathie, field will ignore if it is null
     *
     * @param id the id of the ficheThrombopathieDTO to save.
     * @param ficheThrombopathieDTO the ficheThrombopathieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ficheThrombopathieDTO,
     * or with status {@code 400 (Bad Request)} if the ficheThrombopathieDTO is not valid,
     * or with status {@code 404 (Not Found)} if the ficheThrombopathieDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the ficheThrombopathieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FicheThrombopathieDTO> partialUpdateFicheThrombopathie(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FicheThrombopathieDTO ficheThrombopathieDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update FicheThrombopathie partially : {}, {}", id, ficheThrombopathieDTO);
        if (ficheThrombopathieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ficheThrombopathieDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheThrombopathieRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FicheThrombopathieDTO> result = ficheThrombopathieService.partialUpdate(ficheThrombopathieDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ficheThrombopathieDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fiche-thrombopathies} : get all the ficheThrombopathies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ficheThrombopathies in body.
     */
    @GetMapping("")
    public ResponseEntity<List<FicheThrombopathieDTO>> getAllFicheThrombopathies(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get a page of FicheThrombopathies");
        Page<FicheThrombopathieDTO> page = ficheThrombopathieService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fiche-thrombopathies/:id} : get the "id" ficheThrombopathie.
     *
     * @param id the id of the ficheThrombopathieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ficheThrombopathieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FicheThrombopathieDTO> getFicheThrombopathie(@PathVariable("id") Long id) {
        LOG.debug("REST request to get FicheThrombopathie : {}", id);
        Optional<FicheThrombopathieDTO> ficheThrombopathieDTO = ficheThrombopathieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ficheThrombopathieDTO);
    }

    /**
     * {@code DELETE  /fiche-thrombopathies/:id} : delete the "id" ficheThrombopathie.
     *
     * @param id the id of the ficheThrombopathieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFicheThrombopathie(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete FicheThrombopathie : {}", id);
        ficheThrombopathieService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

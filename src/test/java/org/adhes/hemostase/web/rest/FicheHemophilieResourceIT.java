package org.adhes.hemostase.web.rest;

import static org.adhes.hemostase.domain.FicheHemophilieAsserts.*;
import static org.adhes.hemostase.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.adhes.hemostase.IntegrationTest;
import org.adhes.hemostase.domain.FicheHemophilie;
import org.adhes.hemostase.domain.enumeration.CivilStatus;
import org.adhes.hemostase.domain.enumeration.CouvertureSociale;
import org.adhes.hemostase.domain.enumeration.DiagnosticType;
import org.adhes.hemostase.domain.enumeration.Gender;
import org.adhes.hemostase.domain.enumeration.OuiNonNP;
import org.adhes.hemostase.domain.enumeration.Region;
import org.adhes.hemostase.repository.FicheHemophilieRepository;
import org.adhes.hemostase.service.dto.FicheHemophilieDTO;
import org.adhes.hemostase.service.mapper.FicheHemophilieMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link FicheHemophilieResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheHemophilieResourceIT {

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_JEUNE_FILLE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_JEUNE_FILLE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    private static final Gender DEFAULT_SEXE = Gender.MASCULIN;
    private static final Gender UPDATED_SEXE = Gender.FEMININ;

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGE_ACTUEL = 1;
    private static final Integer UPDATED_AGE_ACTUEL = 2;

    private static final Region DEFAULT_ORIGINE = Region.SFAX;
    private static final Region UPDATED_ORIGINE = Region.GABES;

    private static final String DEFAULT_AUTRE_ORIGINE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_ORIGINE = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final CivilStatus DEFAULT_ETAT_CIVIL = CivilStatus.CELIBATAIRE;
    private static final CivilStatus UPDATED_ETAT_CIVIL = CivilStatus.MARIE;

    private static final CouvertureSociale DEFAULT_COUVERTURE_SOCIALE = CouvertureSociale.CNAM;
    private static final CouvertureSociale UPDATED_COUVERTURE_SOCIALE = CouvertureSociale.INDIGENT;

    private static final String DEFAULT_PRENOM_PERE = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_PERE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION_PERE = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION_PERE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_PRENOM_MERE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_PRENOM_MERE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION_MERE = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION_MERE = "BBBBBBBBBB";

    private static final String DEFAULT_MEDECIN_TRAITANT = "AAAAAAAAAA";
    private static final String UPDATED_MEDECIN_TRAITANT = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_ORIGINE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_ORIGINE = "BBBBBBBBBB";

    private static final String DEFAULT_BIOLOGISTE_RESPONSABLE_DG = "AAAAAAAAAA";
    private static final String UPDATED_BIOLOGISTE_RESPONSABLE_DG = "BBBBBBBBBB";

    private static final DiagnosticType DEFAULT_DIAGNOSTIC = DiagnosticType.HEMOPHILIE;
    private static final DiagnosticType UPDATED_DIAGNOSTIC = DiagnosticType.WILLEBRAND;

    private static final String DEFAULT_AUTRE_DIAGNOSTIC = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_DIAGNOSTIC = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANNEE_DIAGNOSTIC = 1;
    private static final Integer UPDATED_ANNEE_DIAGNOSTIC = 2;

    private static final OuiNonNP DEFAULT_CONSENTEMENT_REGISTRE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CONSENTEMENT_REGISTRE = OuiNonNP.NON;

    private static final LocalDate DEFAULT_DATE_ENREGISTREMENT_REGISTRE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ENREGISTREMENT_REGISTRE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DOSSIER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOSSIER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ORDRE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ORDRE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_INDEX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INDEX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DEGRE_PARENTE_CONSANGUINS = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CONSANGUINS = "BBBBBBBBBB";

    private static final String DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES = "BBBBBBBBBB";

    private static final String DEFAULT_CIRCONSTANCE_HEMATOME_SUPERFICIEL = "AAAAAAAAAA";
    private static final String UPDATED_CIRCONSTANCE_HEMATOME_SUPERFICIEL = "BBBBBBBBBB";

    private static final String DEFAULT_SIEGE_HEMATOME_SUPERFICIEL = "AAAAAAAAAA";
    private static final String UPDATED_SIEGE_HEMATOME_SUPERFICIEL = "BBBBBBBBBB";

    private static final String DEFAULT_CIRCONSTANCE_HEMATOME_PSOAS = "AAAAAAAAAA";
    private static final String UPDATED_CIRCONSTANCE_HEMATOME_PSOAS = "BBBBBBBBBB";

    private static final String DEFAULT_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES = "AAAAAAAAAA";
    private static final String UPDATED_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_INTERVENTION_CHIRURGICALE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_INTERVENTION_CHIRURGICALE = "BBBBBBBBBB";

    private static final String DEFAULT_CAUSE_PRISE_EN_CHARGE = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_PRISE_EN_CHARGE = "BBBBBBBBBB";

    private static final String DEFAULT_POSOLOGIE_ITI = "AAAAAAAAAA";
    private static final String UPDATED_POSOLOGIE_ITI = "BBBBBBBBBB";

    private static final String DEFAULT_DUREE_ITI = "AAAAAAAAAA";
    private static final String UPDATED_DUREE_ITI = "BBBBBBBBBB";

    private static final String DEFAULT_CAUSE_NON_OBSERVANCE_ITI = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_NON_OBSERVANCE_ITI = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_HANDICAP = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HANDICAP = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_ACTIVITE_SPORTIVE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ACTIVITE_SPORTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_CAUSE_DATE_DECES = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_DATE_DECES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fiche-hemophilies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FicheHemophilieRepository ficheHemophilieRepository;

    @Autowired
    private FicheHemophilieMapper ficheHemophilieMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheHemophilieMockMvc;

    private FicheHemophilie ficheHemophilie;

    private FicheHemophilie insertedFicheHemophilie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheHemophilie createEntity() {
        return new FicheHemophilie()
            .prenom(DEFAULT_PRENOM)
            .nom(DEFAULT_NOM)
            .nomJeuneFille(DEFAULT_NOM_JEUNE_FILLE)
            .profession(DEFAULT_PROFESSION)
            .sexe(DEFAULT_SEXE)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .ageActuel(DEFAULT_AGE_ACTUEL)
            .origine(DEFAULT_ORIGINE)
            .autreOrigine(DEFAULT_AUTRE_ORIGINE)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
            .etatCivil(DEFAULT_ETAT_CIVIL)
            .couvertureSociale(DEFAULT_COUVERTURE_SOCIALE)
            .prenomPere(DEFAULT_PRENOM_PERE)
            .professionPere(DEFAULT_PROFESSION_PERE)
            .nomPrenomMere(DEFAULT_NOM_PRENOM_MERE)
            .professionMere(DEFAULT_PROFESSION_MERE)
            .medecinTraitant(DEFAULT_MEDECIN_TRAITANT)
            .serviceOrigine(DEFAULT_SERVICE_ORIGINE)
            .biologisteResponsableDg(DEFAULT_BIOLOGISTE_RESPONSABLE_DG)
            .diagnostic(DEFAULT_DIAGNOSTIC)
            .autreDiagnostic(DEFAULT_AUTRE_DIAGNOSTIC)
            .anneeDiagnostic(DEFAULT_ANNEE_DIAGNOSTIC)
            .consentementRegistre(DEFAULT_CONSENTEMENT_REGISTRE)
            .dateEnregistrementRegistre(DEFAULT_DATE_ENREGISTREMENT_REGISTRE)
            .dossierNumber(DEFAULT_DOSSIER_NUMBER)
            .ordreNumber(DEFAULT_ORDRE_NUMBER)
            .indexNumber(DEFAULT_INDEX_NUMBER)
            .degreParenteConsanguins(DEFAULT_DEGRE_PARENTE_CONSANGUINS)
            .degreParenteCasSimilaires(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES)
            .typeHemorragiesCutaneoMuqueuses(DEFAULT_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .circonstanceHematomeSuperficiel(DEFAULT_CIRCONSTANCE_HEMATOME_SUPERFICIEL)
            .siegeHematomeSuperficiel(DEFAULT_SIEGE_HEMATOME_SUPERFICIEL)
            .circonstanceHematomePsoas(DEFAULT_CIRCONSTANCE_HEMATOME_PSOAS)
            .circonstanceAccidentsHemorragiquesGraves(DEFAULT_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .typeInterventionChirurgicale(DEFAULT_TYPE_INTERVENTION_CHIRURGICALE)
            .causePriseEnCharge(DEFAULT_CAUSE_PRISE_EN_CHARGE)
            .posologieITI(DEFAULT_POSOLOGIE_ITI)
            .dureeITI(DEFAULT_DUREE_ITI)
            .causeNonObservanceITI(DEFAULT_CAUSE_NON_OBSERVANCE_ITI)
            .typeHandicap(DEFAULT_TYPE_HANDICAP)
            .typeActiviteSportive(DEFAULT_TYPE_ACTIVITE_SPORTIVE)
            .causeDateDeces(DEFAULT_CAUSE_DATE_DECES);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheHemophilie createUpdatedEntity() {
        return new FicheHemophilie()
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .profession(UPDATED_PROFESSION)
            .sexe(UPDATED_SEXE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .ageActuel(UPDATED_AGE_ACTUEL)
            .origine(UPDATED_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .prenomPere(UPDATED_PRENOM_PERE)
            .professionPere(UPDATED_PROFESSION_PERE)
            .nomPrenomMere(UPDATED_NOM_PRENOM_MERE)
            .professionMere(UPDATED_PROFESSION_MERE)
            .medecinTraitant(UPDATED_MEDECIN_TRAITANT)
            .serviceOrigine(UPDATED_SERVICE_ORIGINE)
            .biologisteResponsableDg(UPDATED_BIOLOGISTE_RESPONSABLE_DG)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .autreDiagnostic(UPDATED_AUTRE_DIAGNOSTIC)
            .anneeDiagnostic(UPDATED_ANNEE_DIAGNOSTIC)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .typeHemorragiesCutaneoMuqueuses(UPDATED_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .circonstanceHematomeSuperficiel(UPDATED_CIRCONSTANCE_HEMATOME_SUPERFICIEL)
            .siegeHematomeSuperficiel(UPDATED_SIEGE_HEMATOME_SUPERFICIEL)
            .circonstanceHematomePsoas(UPDATED_CIRCONSTANCE_HEMATOME_PSOAS)
            .circonstanceAccidentsHemorragiquesGraves(UPDATED_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .typeInterventionChirurgicale(UPDATED_TYPE_INTERVENTION_CHIRURGICALE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .causeNonObservanceITI(UPDATED_CAUSE_NON_OBSERVANCE_ITI)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .causeDateDeces(UPDATED_CAUSE_DATE_DECES);
    }

    @BeforeEach
    public void initTest() {
        ficheHemophilie = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedFicheHemophilie != null) {
            ficheHemophilieRepository.delete(insertedFicheHemophilie);
            insertedFicheHemophilie = null;
        }
    }

    @Test
    @Transactional
    void createFicheHemophilie() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FicheHemophilie
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);
        var returnedFicheHemophilieDTO = om.readValue(
            restFicheHemophilieMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FicheHemophilieDTO.class
        );

        // Validate the FicheHemophilie in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFicheHemophilie = ficheHemophilieMapper.toEntity(returnedFicheHemophilieDTO);
        assertFicheHemophilieUpdatableFieldsEquals(returnedFicheHemophilie, getPersistedFicheHemophilie(returnedFicheHemophilie));

        insertedFicheHemophilie = returnedFicheHemophilie;
    }

    @Test
    @Transactional
    void createFicheHemophilieWithExistingId() throws Exception {
        // Create the FicheHemophilie with an existing ID
        ficheHemophilie.setId(1L);
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FicheHemophilie in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkPrenomIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setPrenom(null);

        // Create the FicheHemophilie, which fails.
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNomIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setNom(null);

        // Create the FicheHemophilie, which fails.
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSexeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setSexe(null);

        // Create the FicheHemophilie, which fails.
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEtatCivilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setEtatCivil(null);

        // Create the FicheHemophilie, which fails.
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCouvertureSocialeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setCouvertureSociale(null);

        // Create the FicheHemophilie, which fails.
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiagnosticIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setDiagnostic(null);

        // Create the FicheHemophilie, which fails.
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDossierNumberIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setDossierNumber(null);

        // Create the FicheHemophilie, which fails.
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFicheHemophilies() throws Exception {
        // Initialize the database
        insertedFicheHemophilie = ficheHemophilieRepository.saveAndFlush(ficheHemophilie);

        // Get all the ficheHemophilieList
        restFicheHemophilieMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheHemophilie.getId().intValue())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].nomJeuneFille").value(hasItem(DEFAULT_NOM_JEUNE_FILLE)))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION)))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].ageActuel").value(hasItem(DEFAULT_AGE_ACTUEL)))
            .andExpect(jsonPath("$.[*].origine").value(hasItem(DEFAULT_ORIGINE.toString())))
            .andExpect(jsonPath("$.[*].autreOrigine").value(hasItem(DEFAULT_AUTRE_ORIGINE)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].etatCivil").value(hasItem(DEFAULT_ETAT_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].couvertureSociale").value(hasItem(DEFAULT_COUVERTURE_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].prenomPere").value(hasItem(DEFAULT_PRENOM_PERE)))
            .andExpect(jsonPath("$.[*].professionPere").value(hasItem(DEFAULT_PROFESSION_PERE)))
            .andExpect(jsonPath("$.[*].nomPrenomMere").value(hasItem(DEFAULT_NOM_PRENOM_MERE)))
            .andExpect(jsonPath("$.[*].professionMere").value(hasItem(DEFAULT_PROFESSION_MERE)))
            .andExpect(jsonPath("$.[*].medecinTraitant").value(hasItem(DEFAULT_MEDECIN_TRAITANT)))
            .andExpect(jsonPath("$.[*].serviceOrigine").value(hasItem(DEFAULT_SERVICE_ORIGINE)))
            .andExpect(jsonPath("$.[*].biologisteResponsableDg").value(hasItem(DEFAULT_BIOLOGISTE_RESPONSABLE_DG)))
            .andExpect(jsonPath("$.[*].diagnostic").value(hasItem(DEFAULT_DIAGNOSTIC.toString())))
            .andExpect(jsonPath("$.[*].autreDiagnostic").value(hasItem(DEFAULT_AUTRE_DIAGNOSTIC)))
            .andExpect(jsonPath("$.[*].anneeDiagnostic").value(hasItem(DEFAULT_ANNEE_DIAGNOSTIC)))
            .andExpect(jsonPath("$.[*].consentementRegistre").value(hasItem(DEFAULT_CONSENTEMENT_REGISTRE.toString())))
            .andExpect(jsonPath("$.[*].dateEnregistrementRegistre").value(hasItem(DEFAULT_DATE_ENREGISTREMENT_REGISTRE.toString())))
            .andExpect(jsonPath("$.[*].dossierNumber").value(hasItem(DEFAULT_DOSSIER_NUMBER)))
            .andExpect(jsonPath("$.[*].ordreNumber").value(hasItem(DEFAULT_ORDRE_NUMBER)))
            .andExpect(jsonPath("$.[*].indexNumber").value(hasItem(DEFAULT_INDEX_NUMBER)))
            .andExpect(jsonPath("$.[*].degreParenteConsanguins").value(hasItem(DEFAULT_DEGRE_PARENTE_CONSANGUINS)))
            .andExpect(jsonPath("$.[*].degreParenteCasSimilaires").value(hasItem(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES)))
            .andExpect(jsonPath("$.[*].typeHemorragiesCutaneoMuqueuses").value(hasItem(DEFAULT_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)))
            .andExpect(jsonPath("$.[*].circonstanceHematomeSuperficiel").value(hasItem(DEFAULT_CIRCONSTANCE_HEMATOME_SUPERFICIEL)))
            .andExpect(jsonPath("$.[*].siegeHematomeSuperficiel").value(hasItem(DEFAULT_SIEGE_HEMATOME_SUPERFICIEL)))
            .andExpect(jsonPath("$.[*].circonstanceHematomePsoas").value(hasItem(DEFAULT_CIRCONSTANCE_HEMATOME_PSOAS)))
            .andExpect(
                jsonPath("$.[*].circonstanceAccidentsHemorragiquesGraves").value(
                    hasItem(DEFAULT_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
                )
            )
            .andExpect(jsonPath("$.[*].typeInterventionChirurgicale").value(hasItem(DEFAULT_TYPE_INTERVENTION_CHIRURGICALE)))
            .andExpect(jsonPath("$.[*].causePriseEnCharge").value(hasItem(DEFAULT_CAUSE_PRISE_EN_CHARGE)))
            .andExpect(jsonPath("$.[*].posologieITI").value(hasItem(DEFAULT_POSOLOGIE_ITI)))
            .andExpect(jsonPath("$.[*].dureeITI").value(hasItem(DEFAULT_DUREE_ITI)))
            .andExpect(jsonPath("$.[*].causeNonObservanceITI").value(hasItem(DEFAULT_CAUSE_NON_OBSERVANCE_ITI)))
            .andExpect(jsonPath("$.[*].typeHandicap").value(hasItem(DEFAULT_TYPE_HANDICAP)))
            .andExpect(jsonPath("$.[*].typeActiviteSportive").value(hasItem(DEFAULT_TYPE_ACTIVITE_SPORTIVE)))
            .andExpect(jsonPath("$.[*].causeDateDeces").value(hasItem(DEFAULT_CAUSE_DATE_DECES)));
    }

    @Test
    @Transactional
    void getFicheHemophilie() throws Exception {
        // Initialize the database
        insertedFicheHemophilie = ficheHemophilieRepository.saveAndFlush(ficheHemophilie);

        // Get the ficheHemophilie
        restFicheHemophilieMockMvc
            .perform(get(ENTITY_API_URL_ID, ficheHemophilie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ficheHemophilie.getId().intValue()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.nomJeuneFille").value(DEFAULT_NOM_JEUNE_FILLE))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.ageActuel").value(DEFAULT_AGE_ACTUEL))
            .andExpect(jsonPath("$.origine").value(DEFAULT_ORIGINE.toString()))
            .andExpect(jsonPath("$.autreOrigine").value(DEFAULT_AUTRE_ORIGINE))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.etatCivil").value(DEFAULT_ETAT_CIVIL.toString()))
            .andExpect(jsonPath("$.couvertureSociale").value(DEFAULT_COUVERTURE_SOCIALE.toString()))
            .andExpect(jsonPath("$.prenomPere").value(DEFAULT_PRENOM_PERE))
            .andExpect(jsonPath("$.professionPere").value(DEFAULT_PROFESSION_PERE))
            .andExpect(jsonPath("$.nomPrenomMere").value(DEFAULT_NOM_PRENOM_MERE))
            .andExpect(jsonPath("$.professionMere").value(DEFAULT_PROFESSION_MERE))
            .andExpect(jsonPath("$.medecinTraitant").value(DEFAULT_MEDECIN_TRAITANT))
            .andExpect(jsonPath("$.serviceOrigine").value(DEFAULT_SERVICE_ORIGINE))
            .andExpect(jsonPath("$.biologisteResponsableDg").value(DEFAULT_BIOLOGISTE_RESPONSABLE_DG))
            .andExpect(jsonPath("$.diagnostic").value(DEFAULT_DIAGNOSTIC.toString()))
            .andExpect(jsonPath("$.autreDiagnostic").value(DEFAULT_AUTRE_DIAGNOSTIC))
            .andExpect(jsonPath("$.anneeDiagnostic").value(DEFAULT_ANNEE_DIAGNOSTIC))
            .andExpect(jsonPath("$.consentementRegistre").value(DEFAULT_CONSENTEMENT_REGISTRE.toString()))
            .andExpect(jsonPath("$.dateEnregistrementRegistre").value(DEFAULT_DATE_ENREGISTREMENT_REGISTRE.toString()))
            .andExpect(jsonPath("$.dossierNumber").value(DEFAULT_DOSSIER_NUMBER))
            .andExpect(jsonPath("$.ordreNumber").value(DEFAULT_ORDRE_NUMBER))
            .andExpect(jsonPath("$.indexNumber").value(DEFAULT_INDEX_NUMBER))
            .andExpect(jsonPath("$.degreParenteConsanguins").value(DEFAULT_DEGRE_PARENTE_CONSANGUINS))
            .andExpect(jsonPath("$.degreParenteCasSimilaires").value(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES))
            .andExpect(jsonPath("$.typeHemorragiesCutaneoMuqueuses").value(DEFAULT_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES))
            .andExpect(jsonPath("$.circonstanceHematomeSuperficiel").value(DEFAULT_CIRCONSTANCE_HEMATOME_SUPERFICIEL))
            .andExpect(jsonPath("$.siegeHematomeSuperficiel").value(DEFAULT_SIEGE_HEMATOME_SUPERFICIEL))
            .andExpect(jsonPath("$.circonstanceHematomePsoas").value(DEFAULT_CIRCONSTANCE_HEMATOME_PSOAS))
            .andExpect(jsonPath("$.circonstanceAccidentsHemorragiquesGraves").value(DEFAULT_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES))
            .andExpect(jsonPath("$.typeInterventionChirurgicale").value(DEFAULT_TYPE_INTERVENTION_CHIRURGICALE))
            .andExpect(jsonPath("$.causePriseEnCharge").value(DEFAULT_CAUSE_PRISE_EN_CHARGE))
            .andExpect(jsonPath("$.posologieITI").value(DEFAULT_POSOLOGIE_ITI))
            .andExpect(jsonPath("$.dureeITI").value(DEFAULT_DUREE_ITI))
            .andExpect(jsonPath("$.causeNonObservanceITI").value(DEFAULT_CAUSE_NON_OBSERVANCE_ITI))
            .andExpect(jsonPath("$.typeHandicap").value(DEFAULT_TYPE_HANDICAP))
            .andExpect(jsonPath("$.typeActiviteSportive").value(DEFAULT_TYPE_ACTIVITE_SPORTIVE))
            .andExpect(jsonPath("$.causeDateDeces").value(DEFAULT_CAUSE_DATE_DECES));
    }

    @Test
    @Transactional
    void getNonExistingFicheHemophilie() throws Exception {
        // Get the ficheHemophilie
        restFicheHemophilieMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFicheHemophilie() throws Exception {
        // Initialize the database
        insertedFicheHemophilie = ficheHemophilieRepository.saveAndFlush(ficheHemophilie);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheHemophilie
        FicheHemophilie updatedFicheHemophilie = ficheHemophilieRepository.findById(ficheHemophilie.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFicheHemophilie are not directly saved in db
        em.detach(updatedFicheHemophilie);
        updatedFicheHemophilie
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .profession(UPDATED_PROFESSION)
            .sexe(UPDATED_SEXE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .ageActuel(UPDATED_AGE_ACTUEL)
            .origine(UPDATED_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .prenomPere(UPDATED_PRENOM_PERE)
            .professionPere(UPDATED_PROFESSION_PERE)
            .nomPrenomMere(UPDATED_NOM_PRENOM_MERE)
            .professionMere(UPDATED_PROFESSION_MERE)
            .medecinTraitant(UPDATED_MEDECIN_TRAITANT)
            .serviceOrigine(UPDATED_SERVICE_ORIGINE)
            .biologisteResponsableDg(UPDATED_BIOLOGISTE_RESPONSABLE_DG)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .autreDiagnostic(UPDATED_AUTRE_DIAGNOSTIC)
            .anneeDiagnostic(UPDATED_ANNEE_DIAGNOSTIC)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .typeHemorragiesCutaneoMuqueuses(UPDATED_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .circonstanceHematomeSuperficiel(UPDATED_CIRCONSTANCE_HEMATOME_SUPERFICIEL)
            .siegeHematomeSuperficiel(UPDATED_SIEGE_HEMATOME_SUPERFICIEL)
            .circonstanceHematomePsoas(UPDATED_CIRCONSTANCE_HEMATOME_PSOAS)
            .circonstanceAccidentsHemorragiquesGraves(UPDATED_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .typeInterventionChirurgicale(UPDATED_TYPE_INTERVENTION_CHIRURGICALE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .causeNonObservanceITI(UPDATED_CAUSE_NON_OBSERVANCE_ITI)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .causeDateDeces(UPDATED_CAUSE_DATE_DECES);
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(updatedFicheHemophilie);

        restFicheHemophilieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheHemophilieDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheHemophilieDTO))
            )
            .andExpect(status().isOk());

        // Validate the FicheHemophilie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFicheHemophilieToMatchAllProperties(updatedFicheHemophilie);
    }

    @Test
    @Transactional
    void putNonExistingFicheHemophilie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheHemophilie.setId(longCount.incrementAndGet());

        // Create the FicheHemophilie
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheHemophilieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheHemophilieDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheHemophilieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheHemophilie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFicheHemophilie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheHemophilie.setId(longCount.incrementAndGet());

        // Create the FicheHemophilie
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheHemophilieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheHemophilieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheHemophilie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFicheHemophilie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheHemophilie.setId(longCount.incrementAndGet());

        // Create the FicheHemophilie
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheHemophilieMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheHemophilie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFicheHemophilieWithPatch() throws Exception {
        // Initialize the database
        insertedFicheHemophilie = ficheHemophilieRepository.saveAndFlush(ficheHemophilie);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheHemophilie using partial update
        FicheHemophilie partialUpdatedFicheHemophilie = new FicheHemophilie();
        partialUpdatedFicheHemophilie.setId(ficheHemophilie.getId());

        partialUpdatedFicheHemophilie
            .prenom(UPDATED_PRENOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .ageActuel(UPDATED_AGE_ACTUEL)
            .origine(UPDATED_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .prenomPere(UPDATED_PRENOM_PERE)
            .professionPere(UPDATED_PROFESSION_PERE)
            .professionMere(UPDATED_PROFESSION_MERE)
            .medecinTraitant(UPDATED_MEDECIN_TRAITANT)
            .biologisteResponsableDg(UPDATED_BIOLOGISTE_RESPONSABLE_DG)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .siegeHematomeSuperficiel(UPDATED_SIEGE_HEMATOME_SUPERFICIEL)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .causeNonObservanceITI(UPDATED_CAUSE_NON_OBSERVANCE_ITI)
            .causeDateDeces(UPDATED_CAUSE_DATE_DECES);

        restFicheHemophilieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheHemophilie.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheHemophilie))
            )
            .andExpect(status().isOk());

        // Validate the FicheHemophilie in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheHemophilieUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFicheHemophilie, ficheHemophilie),
            getPersistedFicheHemophilie(ficheHemophilie)
        );
    }

    @Test
    @Transactional
    void fullUpdateFicheHemophilieWithPatch() throws Exception {
        // Initialize the database
        insertedFicheHemophilie = ficheHemophilieRepository.saveAndFlush(ficheHemophilie);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheHemophilie using partial update
        FicheHemophilie partialUpdatedFicheHemophilie = new FicheHemophilie();
        partialUpdatedFicheHemophilie.setId(ficheHemophilie.getId());

        partialUpdatedFicheHemophilie
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .profession(UPDATED_PROFESSION)
            .sexe(UPDATED_SEXE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .ageActuel(UPDATED_AGE_ACTUEL)
            .origine(UPDATED_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .prenomPere(UPDATED_PRENOM_PERE)
            .professionPere(UPDATED_PROFESSION_PERE)
            .nomPrenomMere(UPDATED_NOM_PRENOM_MERE)
            .professionMere(UPDATED_PROFESSION_MERE)
            .medecinTraitant(UPDATED_MEDECIN_TRAITANT)
            .serviceOrigine(UPDATED_SERVICE_ORIGINE)
            .biologisteResponsableDg(UPDATED_BIOLOGISTE_RESPONSABLE_DG)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .autreDiagnostic(UPDATED_AUTRE_DIAGNOSTIC)
            .anneeDiagnostic(UPDATED_ANNEE_DIAGNOSTIC)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .typeHemorragiesCutaneoMuqueuses(UPDATED_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .circonstanceHematomeSuperficiel(UPDATED_CIRCONSTANCE_HEMATOME_SUPERFICIEL)
            .siegeHematomeSuperficiel(UPDATED_SIEGE_HEMATOME_SUPERFICIEL)
            .circonstanceHematomePsoas(UPDATED_CIRCONSTANCE_HEMATOME_PSOAS)
            .circonstanceAccidentsHemorragiquesGraves(UPDATED_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .typeInterventionChirurgicale(UPDATED_TYPE_INTERVENTION_CHIRURGICALE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .causeNonObservanceITI(UPDATED_CAUSE_NON_OBSERVANCE_ITI)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .causeDateDeces(UPDATED_CAUSE_DATE_DECES);

        restFicheHemophilieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheHemophilie.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheHemophilie))
            )
            .andExpect(status().isOk());

        // Validate the FicheHemophilie in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheHemophilieUpdatableFieldsEquals(
            partialUpdatedFicheHemophilie,
            getPersistedFicheHemophilie(partialUpdatedFicheHemophilie)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFicheHemophilie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheHemophilie.setId(longCount.incrementAndGet());

        // Create the FicheHemophilie
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheHemophilieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ficheHemophilieDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheHemophilieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheHemophilie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFicheHemophilie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheHemophilie.setId(longCount.incrementAndGet());

        // Create the FicheHemophilie
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheHemophilieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheHemophilieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheHemophilie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFicheHemophilie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheHemophilie.setId(longCount.incrementAndGet());

        // Create the FicheHemophilie
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheHemophilieMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheHemophilie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFicheHemophilie() throws Exception {
        // Initialize the database
        insertedFicheHemophilie = ficheHemophilieRepository.saveAndFlush(ficheHemophilie);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the ficheHemophilie
        restFicheHemophilieMockMvc
            .perform(delete(ENTITY_API_URL_ID, ficheHemophilie.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return ficheHemophilieRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected FicheHemophilie getPersistedFicheHemophilie(FicheHemophilie ficheHemophilie) {
        return ficheHemophilieRepository.findById(ficheHemophilie.getId()).orElseThrow();
    }

    protected void assertPersistedFicheHemophilieToMatchAllProperties(FicheHemophilie expectedFicheHemophilie) {
        assertFicheHemophilieAllPropertiesEquals(expectedFicheHemophilie, getPersistedFicheHemophilie(expectedFicheHemophilie));
    }

    protected void assertPersistedFicheHemophilieToMatchUpdatableProperties(FicheHemophilie expectedFicheHemophilie) {
        assertFicheHemophilieAllUpdatablePropertiesEquals(expectedFicheHemophilie, getPersistedFicheHemophilie(expectedFicheHemophilie));
    }
}

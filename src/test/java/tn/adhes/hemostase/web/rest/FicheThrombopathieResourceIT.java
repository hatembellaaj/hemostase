package tn.adhes.hemostase.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.adhes.hemostase.domain.FicheThrombopathieAsserts.*;
import static tn.adhes.hemostase.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tn.adhes.hemostase.IntegrationTest;
import tn.adhes.hemostase.domain.FicheThrombopathie;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.Region;
import tn.adhes.hemostase.domain.enumeration.ServiceCliniqueType;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.repository.FicheThrombopathieRepository;
import tn.adhes.hemostase.service.dto.FicheThrombopathieDTO;
import tn.adhes.hemostase.service.mapper.FicheThrombopathieMapper;

/**
 * Integration tests for the {@link FicheThrombopathieResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheThrombopathieResourceIT {

    private static final String DEFAULT_DOSSIER_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DOSSIER_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ORDRE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ORDRE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_INDEX_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INDEX_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM_JEUNE_FILLE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_JEUNE_FILLE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSION = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSION = "BBBBBBBBBB";

    private static final CivilStatus DEFAULT_ETAT_CIVIL = CivilStatus.CELIBATAIRE;
    private static final CivilStatus UPDATED_ETAT_CIVIL = CivilStatus.MARIE;

    private static final ServiceCliniqueType DEFAULT_SERVICE_CLINIQUE = ServiceCliniqueType.HTO;
    private static final ServiceCliniqueType UPDATED_SERVICE_CLINIQUE = ServiceCliniqueType.PRIVE;

    private static final String DEFAULT_AUTRE_SERVICE_CLINIQUE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_SERVICE_CLINIQUE = "BBBBBBBBBB";

    private static final CouvertureSociale DEFAULT_COUVERTURE_SOCIALE = CouvertureSociale.CNAM;
    private static final CouvertureSociale UPDATED_COUVERTURE_SOCIALE = CouvertureSociale.INDIGENT;

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

    private static final OuiNonNP DEFAULT_PARENTS_CONSANGUINS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PARENTS_CONSANGUINS = OuiNonNP.NON;

    private static final String DEFAULT_DEGRE_PARENTE_CONSANGUINS = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CONSANGUINS = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CAS_SIMILAIRES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CAS_SIMILAIRES = OuiNonNP.NON;

    private static final Integer DEFAULT_NOMBRE_CAS = 1;
    private static final Integer UPDATED_NOMBRE_CAS = 2;

    private static final String DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.NON;

    private static final Integer DEFAULT_NOMBRE_DECES = 1;
    private static final Integer UPDATED_NOMBRE_DECES = 2;

    private static final String DEFAULT_TYPE_HEMORRAGIE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HEMORRAGIE = "BBBBBBBBBB";

    private static final String DEFAULT_LIEN_PARENTE_DECES = "AAAAAAAAAA";
    private static final String UPDATED_LIEN_PARENTE_DECES = "BBBBBBBBBB";

    private static final FaiteNonFaite DEFAULT_ENQUETE_FAMILIALE = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_ENQUETE_FAMILIALE = FaiteNonFaite.NONFAITE;

    private static final String DEFAULT_ANTECEDENTS_MEDICAUX = "AAAAAAAAAA";
    private static final String UPDATED_ANTECEDENTS_MEDICAUX = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_ANTECEDENTS_CHIRURGICAUX = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ANTECEDENTS_CHIRURGICAUX = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_CHIRURGIE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CHIRURGIE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_COMPLICATION_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_COMPLICATION_HEMORRAGIQUE = OuiNonNP.NON;

    private static final Integer DEFAULT_AGE_PREMIER_SIGNE_HEMORRAGIQUE = 1;
    private static final Integer UPDATED_AGE_PREMIER_SIGNE_HEMORRAGIQUE = 2;

    private static final String DEFAULT_TYPE_SYNDROME_HEMORRAGIQUE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_SYNDROME_HEMORRAGIQUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE_DIAGNOSTIC = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC = 2;

    private static final Boolean DEFAULT_DECOUVERTE_FORTUITE = false;
    private static final Boolean UPDATED_DECOUVERTE_FORTUITE = true;

    private static final Boolean DEFAULT_ENQUETE_FAMILIALE_BOOL = false;
    private static final Boolean UPDATED_ENQUETE_FAMILIALE_BOOL = true;

    private static final Boolean DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE = false;
    private static final Boolean UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE = true;

    private static final String DEFAULT_TYPE_CIRCONSTANCE_DIAGNOSTIC = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CIRCONSTANCE_DIAGNOSTIC = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_ECZEMA = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ECZEMA = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYNDROME_DYSMORPHIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYNDROME_DYSMORPHIQUE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ALBINISME = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ALBINISME = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ARTHROGRYPOSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ARTHROGRYPOSE = OuiNonNP.NON;

    private static final Integer DEFAULT_GB = 1;
    private static final Integer UPDATED_GB = 2;

    private static final Float DEFAULT_HB = 1F;
    private static final Float UPDATED_HB = 2F;

    private static final Float DEFAULT_VGM = 1F;
    private static final Float UPDATED_VGM = 2F;

    private static final Float DEFAULT_TCMH = 1F;
    private static final Float UPDATED_TCMH = 2F;

    private static final Integer DEFAULT_PLAQUETTES = 1;
    private static final Integer UPDATED_PLAQUETTES = 2;

    private static final String DEFAULT_FROTTIS_SANGUIN = "AAAAAAAAAA";
    private static final String UPDATED_FROTTIS_SANGUIN = "BBBBBBBBBB";

    private static final Float DEFAULT_TS = 1F;
    private static final Float UPDATED_TS = 2F;

    private static final Float DEFAULT_TO_PFA = 1F;
    private static final Float UPDATED_TO_PFA = 2F;

    private static final Float DEFAULT_TO_PFA_100_COL_EPI = 1F;
    private static final Float UPDATED_TO_PFA_100_COL_EPI = 2F;

    private static final Float DEFAULT_TO_PFA_100_COL_ADP = 1F;
    private static final Float UPDATED_TO_PFA_100_COL_ADP = 2F;

    private static final Float DEFAULT_TP = 1F;
    private static final Float UPDATED_TP = 2F;

    private static final Float DEFAULT_TCA = 1F;
    private static final Float UPDATED_TCA = 2F;

    private static final OuiNonNP DEFAULT_AGGREGOMETRIE_PLAQUETTAIRE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_AGGREGOMETRIE_PLAQUETTAIRE = OuiNonNP.NON;

    private static final String DEFAULT_RESULTAT_AGREGOMETRIE = "AAAAAAAAAA";
    private static final String UPDATED_RESULTAT_AGREGOMETRIE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CMF = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CMF = OuiNonNP.NON;

    private static final String DEFAULT_RESULTAT_CMF = "AAAAAAAAAA";
    private static final String UPDATED_RESULTAT_CMF = "BBBBBBBBBB";

    private static final Float DEFAULT_DOSAGE_FVW_AG = 1F;
    private static final Float UPDATED_DOSAGE_FVW_AG = 2F;

    private static final Float DEFAULT_DOSAGE_FVW_RCO = 1F;
    private static final Float UPDATED_DOSAGE_FVW_RCO = 2F;

    private static final OuiNonNP DEFAULT_ETUDE_GENETIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ETUDE_GENETIQUE = OuiNonNP.NON;

    private static final String DEFAULT_RESULTAT_ETUDE_GENETIQUE = "AAAAAAAAAA";
    private static final String UPDATED_RESULTAT_ETUDE_GENETIQUE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_THROMBASTHENIE_GLANZMANN = false;
    private static final Boolean UPDATED_THROMBASTHENIE_GLANZMANN = true;

    private static final Boolean DEFAULT_MALADIE_BERNARD_SOULIER = false;
    private static final Boolean UPDATED_MALADIE_BERNARD_SOULIER = true;

    private static final Boolean DEFAULT_AUTRE_THROMBOPATHIE = false;
    private static final Boolean UPDATED_AUTRE_THROMBOPATHIE = true;

    private static final String DEFAULT_AUTRE_THROMBOPATHIE_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_THROMBOPATHIE_DETAILS = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_ECCHYMOSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ECCHYMOSE = OuiNonNP.NON;

    private static final TypeManifestation DEFAULT_TYPE_ECCHYMOSE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_ECCHYMOSE = TypeManifestation.PROVOQUE;

    private static final OuiNonNP DEFAULT_GINGIVORRAGIES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_GINGIVORRAGIES = OuiNonNP.NON;

    private static final TypeManifestation DEFAULT_TYPE_GINGIVORRAGIES = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_GINGIVORRAGIES = TypeManifestation.PROVOQUE;

    private static final OuiNonNP DEFAULT_EPISTAXIS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_EPISTAXIS = OuiNonNP.NON;

    private static final TypeManifestation DEFAULT_TYPE_EPISTAXIS = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_EPISTAXIS = TypeManifestation.PROVOQUE;

    private static final OuiNonNP DEFAULT_HEMATOME = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMATOME = OuiNonNP.NON;

    private static final TypeManifestation DEFAULT_TYPE_HEMATOME = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_HEMATOME = TypeManifestation.PROVOQUE;

    private static final String DEFAULT_SIEGEL_HEMATOME = "AAAAAAAAAA";
    private static final String UPDATED_SIEGEL_HEMATOME = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_MENO_METRORRAGIES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_MENO_METRORRAGIES = OuiNonNP.NON;

    private static final Integer DEFAULT_FREQUENCE_MENO_METRORRAGIES = 1;
    private static final Integer UPDATED_FREQUENCE_MENO_METRORRAGIES = 2;

    private static final String DEFAULT_SEVERITE_MENO_METRORRAGIES = "AAAAAAAAAA";
    private static final String UPDATED_SEVERITE_MENO_METRORRAGIES = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_EXPLORATION_MENO_METRORRAGIES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_EXPLORATION_MENO_METRORRAGIES = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_LESION_SOUS_JACENTE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_LESION_SOUS_JACENTE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_HEMORRAGIE_VISCERES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIE_VISCERES = OuiNonNP.NON;

    private static final Boolean DEFAULT_HEMORRAGIE_DIGESTIVE_HAUTE = false;
    private static final Boolean UPDATED_HEMORRAGIE_DIGESTIVE_HAUTE = true;

    private static final Boolean DEFAULT_HEMORRAGIE_DIGESTIVE_BASSE = false;
    private static final Boolean UPDATED_HEMORRAGIE_DIGESTIVE_BASSE = true;

    private static final Boolean DEFAULT_HEMATURIE = false;
    private static final Boolean UPDATED_HEMATURIE = true;

    private static final OuiNonNP DEFAULT_EXPLORATION_HEMORRAGIE_VISCERES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_EXPLORATION_HEMORRAGIE_VISCERES = OuiNonNP.NON;

    private static final String DEFAULT_LESION_SOUS_JACENTE_VISCERES = "AAAAAAAAAA";
    private static final String UPDATED_LESION_SOUS_JACENTE_VISCERES = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_INTERVENTION_CHIRURGICALE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INTERVENTION_CHIRURGICALE = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_INTERVENTION_CHIRURGICALE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_INTERVENTION_CHIRURGICALE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HEMATOMES_CEREBRALES = false;
    private static final Boolean UPDATED_HEMATOMES_CEREBRALES = true;

    private static final Boolean DEFAULT_EDC_HEMORRAGIQUE = false;
    private static final Boolean UPDATED_EDC_HEMORRAGIQUE = true;

    private static final OuiNonNP DEFAULT_TRANSFUSION_CPS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRANSFUSION_CPS = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_FACTEUR_VII_RECOMBINANT = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_FACTEUR_VII_RECOMBINANT = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_DICYNONE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_DICYNONE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_EXACYL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_EXACYL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_OESTROPROGESTATIF = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_OESTROPROGESTATIF = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_MECHAGE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_MECHAGE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRANSFUSION_CGR = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRANSFUSION_CGR = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_MARTIAL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_MARTIAL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_THROMBOSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_THROMBOSE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ALLO_IMMUNISATION_HLA = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ALLO_IMMUNISATION_HLA = OuiNonNP.NON;

    private static final String ENTITY_API_URL = "/api/fiche-thrombopathies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FicheThrombopathieRepository ficheThrombopathieRepository;

    @Autowired
    private FicheThrombopathieMapper ficheThrombopathieMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheThrombopathieMockMvc;

    private FicheThrombopathie ficheThrombopathie;

    private FicheThrombopathie insertedFicheThrombopathie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheThrombopathie createEntity() {
        return new FicheThrombopathie()
            .dossierNumber(DEFAULT_DOSSIER_NUMBER)
            .ordreNumber(DEFAULT_ORDRE_NUMBER)
            .indexNumber(DEFAULT_INDEX_NUMBER)
            .prenom(DEFAULT_PRENOM)
            .nom(DEFAULT_NOM)
            .nomJeuneFille(DEFAULT_NOM_JEUNE_FILLE)
            .profession(DEFAULT_PROFESSION)
            .etatCivil(DEFAULT_ETAT_CIVIL)
            .serviceClinique(DEFAULT_SERVICE_CLINIQUE)
            .autreServiceClinique(DEFAULT_AUTRE_SERVICE_CLINIQUE)
            .couvertureSociale(DEFAULT_COUVERTURE_SOCIALE)
            .sexe(DEFAULT_SEXE)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .ageActuel(DEFAULT_AGE_ACTUEL)
            .origine(DEFAULT_ORIGINE)
            .autreOrigine(DEFAULT_AUTRE_ORIGINE)
            .adresse(DEFAULT_ADRESSE)
            .telephone(DEFAULT_TELEPHONE)
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
            .parentsConsanguins(DEFAULT_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(DEFAULT_DEGRE_PARENTE_CONSANGUINS)
            .casSimilaires(DEFAULT_CAS_SIMILAIRES)
            .nombreCas(DEFAULT_NOMBRE_CAS)
            .degreParenteCasSimilaires(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES)
            .decesSyndromeHemorragique(DEFAULT_DECES_SYNDROME_HEMORRAGIQUE)
            .nombreDeces(DEFAULT_NOMBRE_DECES)
            .typeHemorragie(DEFAULT_TYPE_HEMORRAGIE)
            .lienParenteDeces(DEFAULT_LIEN_PARENTE_DECES)
            .enqueteFamiliale(DEFAULT_ENQUETE_FAMILIALE)
            .antecedentsMedicaux(DEFAULT_ANTECEDENTS_MEDICAUX)
            .antecedentsChirurgicaux(DEFAULT_ANTECEDENTS_CHIRURGICAUX)
            .typeChirurgie(DEFAULT_TYPE_CHIRURGIE)
            .complicationHemorragique(DEFAULT_COMPLICATION_HEMORRAGIQUE)
            .agePremierSigneHemorragique(DEFAULT_AGE_PREMIER_SIGNE_HEMORRAGIQUE)
            .typeSyndromeHemorragique(DEFAULT_TYPE_SYNDROME_HEMORRAGIQUE)
            .ageDiagnostic(DEFAULT_AGE_DIAGNOSTIC)
            .decouverteFortuite(DEFAULT_DECOUVERTE_FORTUITE)
            .enqueteFamilialeBool(DEFAULT_ENQUETE_FAMILIALE_BOOL)
            .explorationSyndromeHemorragique(DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .typeCirconstanceDiagnostic(DEFAULT_TYPE_CIRCONSTANCE_DIAGNOSTIC)
            .eczema(DEFAULT_ECZEMA)
            .syndromeDysmorphique(DEFAULT_SYNDROME_DYSMORPHIQUE)
            .albinisme(DEFAULT_ALBINISME)
            .arthrogrypose(DEFAULT_ARTHROGRYPOSE)
            .gb(DEFAULT_GB)
            .hb(DEFAULT_HB)
            .vgm(DEFAULT_VGM)
            .tcmh(DEFAULT_TCMH)
            .plaquettes(DEFAULT_PLAQUETTES)
            .frottisSanguin(DEFAULT_FROTTIS_SANGUIN)
            .ts(DEFAULT_TS)
            .toPfa(DEFAULT_TO_PFA)
            .toPfa100ColEpi(DEFAULT_TO_PFA_100_COL_EPI)
            .toPfa100ColAdp(DEFAULT_TO_PFA_100_COL_ADP)
            .tp(DEFAULT_TP)
            .tca(DEFAULT_TCA)
            .aggregometriePlaquettaire(DEFAULT_AGGREGOMETRIE_PLAQUETTAIRE)
            .resultatAgregometrie(DEFAULT_RESULTAT_AGREGOMETRIE)
            .cmf(DEFAULT_CMF)
            .resultatCmf(DEFAULT_RESULTAT_CMF)
            .dosageFvwAg(DEFAULT_DOSAGE_FVW_AG)
            .dosageFvwRco(DEFAULT_DOSAGE_FVW_RCO)
            .etudeGenetique(DEFAULT_ETUDE_GENETIQUE)
            .resultatEtudeGenetique(DEFAULT_RESULTAT_ETUDE_GENETIQUE)
            .thrombasthenieGlanzmann(DEFAULT_THROMBASTHENIE_GLANZMANN)
            .maladieBernardSoulier(DEFAULT_MALADIE_BERNARD_SOULIER)
            .autreThrombopathie(DEFAULT_AUTRE_THROMBOPATHIE)
            .autreThrombopathieDetails(DEFAULT_AUTRE_THROMBOPATHIE_DETAILS)
            .ecchymose(DEFAULT_ECCHYMOSE)
            .typeEcchymose(DEFAULT_TYPE_ECCHYMOSE)
            .gingivorragies(DEFAULT_GINGIVORRAGIES)
            .typeGingivorragies(DEFAULT_TYPE_GINGIVORRAGIES)
            .epistaxis(DEFAULT_EPISTAXIS)
            .typeEpistaxis(DEFAULT_TYPE_EPISTAXIS)
            .hematome(DEFAULT_HEMATOME)
            .typeHematome(DEFAULT_TYPE_HEMATOME)
            .siegelHematome(DEFAULT_SIEGEL_HEMATOME)
            .menoMetrorragies(DEFAULT_MENO_METRORRAGIES)
            .frequenceMenoMetrorragies(DEFAULT_FREQUENCE_MENO_METRORRAGIES)
            .severiteMenoMetrorragies(DEFAULT_SEVERITE_MENO_METRORRAGIES)
            .explorationMenoMetrorragies(DEFAULT_EXPLORATION_MENO_METRORRAGIES)
            .typeLesionSousJacente(DEFAULT_TYPE_LESION_SOUS_JACENTE)
            .hemorragieVisceres(DEFAULT_HEMORRAGIE_VISCERES)
            .hemorragieDigestiveHaute(DEFAULT_HEMORRAGIE_DIGESTIVE_HAUTE)
            .hemorragieDigestiveBasse(DEFAULT_HEMORRAGIE_DIGESTIVE_BASSE)
            .hematurie(DEFAULT_HEMATURIE)
            .explorationHemorragieVisceres(DEFAULT_EXPLORATION_HEMORRAGIE_VISCERES)
            .lesionSousJacenteVisceres(DEFAULT_LESION_SOUS_JACENTE_VISCERES)
            .interventionChirurgicale(DEFAULT_INTERVENTION_CHIRURGICALE)
            .typeInterventionChirurgicale(DEFAULT_TYPE_INTERVENTION_CHIRURGICALE)
            .hematomesCerebrales(DEFAULT_HEMATOMES_CEREBRALES)
            .edcHemorragique(DEFAULT_EDC_HEMORRAGIQUE)
            .transfusionCps(DEFAULT_TRANSFUSION_CPS)
            .facteurVIIRecombinant(DEFAULT_FACTEUR_VII_RECOMBINANT)
            .dicynone(DEFAULT_DICYNONE)
            .exacyl(DEFAULT_EXACYL)
            .traitementOestroprogestatif(DEFAULT_TRAITEMENT_OESTROPROGESTATIF)
            .mechage(DEFAULT_MECHAGE)
            .transfusionCgr(DEFAULT_TRANSFUSION_CGR)
            .traitementMartial(DEFAULT_TRAITEMENT_MARTIAL)
            .thrombose(DEFAULT_THROMBOSE)
            .alloImmunisationHLA(DEFAULT_ALLO_IMMUNISATION_HLA);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheThrombopathie createUpdatedEntity() {
        return new FicheThrombopathie()
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .profession(UPDATED_PROFESSION)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .serviceClinique(UPDATED_SERVICE_CLINIQUE)
            .autreServiceClinique(UPDATED_AUTRE_SERVICE_CLINIQUE)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .sexe(UPDATED_SEXE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .ageActuel(UPDATED_AGE_ACTUEL)
            .origine(UPDATED_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
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
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilaires(UPDATED_CAS_SIMILAIRES)
            .nombreCas(UPDATED_NOMBRE_CAS)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .decesSyndromeHemorragique(UPDATED_DECES_SYNDROME_HEMORRAGIQUE)
            .nombreDeces(UPDATED_NOMBRE_DECES)
            .typeHemorragie(UPDATED_TYPE_HEMORRAGIE)
            .lienParenteDeces(UPDATED_LIEN_PARENTE_DECES)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .antecedentsMedicaux(UPDATED_ANTECEDENTS_MEDICAUX)
            .antecedentsChirurgicaux(UPDATED_ANTECEDENTS_CHIRURGICAUX)
            .typeChirurgie(UPDATED_TYPE_CHIRURGIE)
            .complicationHemorragique(UPDATED_COMPLICATION_HEMORRAGIQUE)
            .agePremierSigneHemorragique(UPDATED_AGE_PREMIER_SIGNE_HEMORRAGIQUE)
            .typeSyndromeHemorragique(UPDATED_TYPE_SYNDROME_HEMORRAGIQUE)
            .ageDiagnostic(UPDATED_AGE_DIAGNOSTIC)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .enqueteFamilialeBool(UPDATED_ENQUETE_FAMILIALE_BOOL)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .typeCirconstanceDiagnostic(UPDATED_TYPE_CIRCONSTANCE_DIAGNOSTIC)
            .eczema(UPDATED_ECZEMA)
            .syndromeDysmorphique(UPDATED_SYNDROME_DYSMORPHIQUE)
            .albinisme(UPDATED_ALBINISME)
            .arthrogrypose(UPDATED_ARTHROGRYPOSE)
            .gb(UPDATED_GB)
            .hb(UPDATED_HB)
            .vgm(UPDATED_VGM)
            .tcmh(UPDATED_TCMH)
            .plaquettes(UPDATED_PLAQUETTES)
            .frottisSanguin(UPDATED_FROTTIS_SANGUIN)
            .ts(UPDATED_TS)
            .toPfa(UPDATED_TO_PFA)
            .toPfa100ColEpi(UPDATED_TO_PFA_100_COL_EPI)
            .toPfa100ColAdp(UPDATED_TO_PFA_100_COL_ADP)
            .tp(UPDATED_TP)
            .tca(UPDATED_TCA)
            .aggregometriePlaquettaire(UPDATED_AGGREGOMETRIE_PLAQUETTAIRE)
            .resultatAgregometrie(UPDATED_RESULTAT_AGREGOMETRIE)
            .cmf(UPDATED_CMF)
            .resultatCmf(UPDATED_RESULTAT_CMF)
            .dosageFvwAg(UPDATED_DOSAGE_FVW_AG)
            .dosageFvwRco(UPDATED_DOSAGE_FVW_RCO)
            .etudeGenetique(UPDATED_ETUDE_GENETIQUE)
            .resultatEtudeGenetique(UPDATED_RESULTAT_ETUDE_GENETIQUE)
            .thrombasthenieGlanzmann(UPDATED_THROMBASTHENIE_GLANZMANN)
            .maladieBernardSoulier(UPDATED_MALADIE_BERNARD_SOULIER)
            .autreThrombopathie(UPDATED_AUTRE_THROMBOPATHIE)
            .autreThrombopathieDetails(UPDATED_AUTRE_THROMBOPATHIE_DETAILS)
            .ecchymose(UPDATED_ECCHYMOSE)
            .typeEcchymose(UPDATED_TYPE_ECCHYMOSE)
            .gingivorragies(UPDATED_GINGIVORRAGIES)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .epistaxis(UPDATED_EPISTAXIS)
            .typeEpistaxis(UPDATED_TYPE_EPISTAXIS)
            .hematome(UPDATED_HEMATOME)
            .typeHematome(UPDATED_TYPE_HEMATOME)
            .siegelHematome(UPDATED_SIEGEL_HEMATOME)
            .menoMetrorragies(UPDATED_MENO_METRORRAGIES)
            .frequenceMenoMetrorragies(UPDATED_FREQUENCE_MENO_METRORRAGIES)
            .severiteMenoMetrorragies(UPDATED_SEVERITE_MENO_METRORRAGIES)
            .explorationMenoMetrorragies(UPDATED_EXPLORATION_MENO_METRORRAGIES)
            .typeLesionSousJacente(UPDATED_TYPE_LESION_SOUS_JACENTE)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hemorragieDigestiveHaute(UPDATED_HEMORRAGIE_DIGESTIVE_HAUTE)
            .hemorragieDigestiveBasse(UPDATED_HEMORRAGIE_DIGESTIVE_BASSE)
            .hematurie(UPDATED_HEMATURIE)
            .explorationHemorragieVisceres(UPDATED_EXPLORATION_HEMORRAGIE_VISCERES)
            .lesionSousJacenteVisceres(UPDATED_LESION_SOUS_JACENTE_VISCERES)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .typeInterventionChirurgicale(UPDATED_TYPE_INTERVENTION_CHIRURGICALE)
            .hematomesCerebrales(UPDATED_HEMATOMES_CEREBRALES)
            .edcHemorragique(UPDATED_EDC_HEMORRAGIQUE)
            .transfusionCps(UPDATED_TRANSFUSION_CPS)
            .facteurVIIRecombinant(UPDATED_FACTEUR_VII_RECOMBINANT)
            .dicynone(UPDATED_DICYNONE)
            .exacyl(UPDATED_EXACYL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .mechage(UPDATED_MECHAGE)
            .transfusionCgr(UPDATED_TRANSFUSION_CGR)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .thrombose(UPDATED_THROMBOSE)
            .alloImmunisationHLA(UPDATED_ALLO_IMMUNISATION_HLA);
    }

    @BeforeEach
    public void initTest() {
        ficheThrombopathie = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedFicheThrombopathie != null) {
            ficheThrombopathieRepository.delete(insertedFicheThrombopathie);
            insertedFicheThrombopathie = null;
        }
    }

    @Test
    @Transactional
    void createFicheThrombopathie() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FicheThrombopathie
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);
        var returnedFicheThrombopathieDTO = om.readValue(
            restFicheThrombopathieMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheThrombopathieDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FicheThrombopathieDTO.class
        );

        // Validate the FicheThrombopathie in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFicheThrombopathie = ficheThrombopathieMapper.toEntity(returnedFicheThrombopathieDTO);
        assertFicheThrombopathieUpdatableFieldsEquals(
            returnedFicheThrombopathie,
            getPersistedFicheThrombopathie(returnedFicheThrombopathie)
        );

        insertedFicheThrombopathie = returnedFicheThrombopathie;
    }

    @Test
    @Transactional
    void createFicheThrombopathieWithExistingId() throws Exception {
        // Create the FicheThrombopathie with an existing ID
        ficheThrombopathie.setId(1L);
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheThrombopathieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheThrombopathieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FicheThrombopathie in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEtatCivilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheThrombopathie.setEtatCivil(null);

        // Create the FicheThrombopathie, which fails.
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        restFicheThrombopathieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheThrombopathieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkServiceCliniqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheThrombopathie.setServiceClinique(null);

        // Create the FicheThrombopathie, which fails.
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        restFicheThrombopathieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheThrombopathieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCouvertureSocialeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheThrombopathie.setCouvertureSociale(null);

        // Create the FicheThrombopathie, which fails.
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        restFicheThrombopathieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheThrombopathieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSexeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheThrombopathie.setSexe(null);

        // Create the FicheThrombopathie, which fails.
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        restFicheThrombopathieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheThrombopathieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiagnosticIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheThrombopathie.setDiagnostic(null);

        // Create the FicheThrombopathie, which fails.
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        restFicheThrombopathieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheThrombopathieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFicheThrombopathies() throws Exception {
        // Initialize the database
        insertedFicheThrombopathie = ficheThrombopathieRepository.saveAndFlush(ficheThrombopathie);

        // Get all the ficheThrombopathieList
        restFicheThrombopathieMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheThrombopathie.getId().intValue())))
            .andExpect(jsonPath("$.[*].dossierNumber").value(hasItem(DEFAULT_DOSSIER_NUMBER)))
            .andExpect(jsonPath("$.[*].ordreNumber").value(hasItem(DEFAULT_ORDRE_NUMBER)))
            .andExpect(jsonPath("$.[*].indexNumber").value(hasItem(DEFAULT_INDEX_NUMBER)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].nomJeuneFille").value(hasItem(DEFAULT_NOM_JEUNE_FILLE)))
            .andExpect(jsonPath("$.[*].profession").value(hasItem(DEFAULT_PROFESSION)))
            .andExpect(jsonPath("$.[*].etatCivil").value(hasItem(DEFAULT_ETAT_CIVIL.toString())))
            .andExpect(jsonPath("$.[*].serviceClinique").value(hasItem(DEFAULT_SERVICE_CLINIQUE.toString())))
            .andExpect(jsonPath("$.[*].autreServiceClinique").value(hasItem(DEFAULT_AUTRE_SERVICE_CLINIQUE)))
            .andExpect(jsonPath("$.[*].couvertureSociale").value(hasItem(DEFAULT_COUVERTURE_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE.toString())))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].ageActuel").value(hasItem(DEFAULT_AGE_ACTUEL)))
            .andExpect(jsonPath("$.[*].origine").value(hasItem(DEFAULT_ORIGINE.toString())))
            .andExpect(jsonPath("$.[*].autreOrigine").value(hasItem(DEFAULT_AUTRE_ORIGINE)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
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
            .andExpect(jsonPath("$.[*].parentsConsanguins").value(hasItem(DEFAULT_PARENTS_CONSANGUINS.toString())))
            .andExpect(jsonPath("$.[*].degreParenteConsanguins").value(hasItem(DEFAULT_DEGRE_PARENTE_CONSANGUINS)))
            .andExpect(jsonPath("$.[*].casSimilaires").value(hasItem(DEFAULT_CAS_SIMILAIRES.toString())))
            .andExpect(jsonPath("$.[*].nombreCas").value(hasItem(DEFAULT_NOMBRE_CAS)))
            .andExpect(jsonPath("$.[*].degreParenteCasSimilaires").value(hasItem(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES)))
            .andExpect(jsonPath("$.[*].decesSyndromeHemorragique").value(hasItem(DEFAULT_DECES_SYNDROME_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].nombreDeces").value(hasItem(DEFAULT_NOMBRE_DECES)))
            .andExpect(jsonPath("$.[*].typeHemorragie").value(hasItem(DEFAULT_TYPE_HEMORRAGIE)))
            .andExpect(jsonPath("$.[*].lienParenteDeces").value(hasItem(DEFAULT_LIEN_PARENTE_DECES)))
            .andExpect(jsonPath("$.[*].enqueteFamiliale").value(hasItem(DEFAULT_ENQUETE_FAMILIALE.toString())))
            .andExpect(jsonPath("$.[*].antecedentsMedicaux").value(hasItem(DEFAULT_ANTECEDENTS_MEDICAUX)))
            .andExpect(jsonPath("$.[*].antecedentsChirurgicaux").value(hasItem(DEFAULT_ANTECEDENTS_CHIRURGICAUX.toString())))
            .andExpect(jsonPath("$.[*].typeChirurgie").value(hasItem(DEFAULT_TYPE_CHIRURGIE)))
            .andExpect(jsonPath("$.[*].complicationHemorragique").value(hasItem(DEFAULT_COMPLICATION_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].agePremierSigneHemorragique").value(hasItem(DEFAULT_AGE_PREMIER_SIGNE_HEMORRAGIQUE)))
            .andExpect(jsonPath("$.[*].typeSyndromeHemorragique").value(hasItem(DEFAULT_TYPE_SYNDROME_HEMORRAGIQUE)))
            .andExpect(jsonPath("$.[*].ageDiagnostic").value(hasItem(DEFAULT_AGE_DIAGNOSTIC)))
            .andExpect(jsonPath("$.[*].decouverteFortuite").value(hasItem(DEFAULT_DECOUVERTE_FORTUITE.booleanValue())))
            .andExpect(jsonPath("$.[*].enqueteFamilialeBool").value(hasItem(DEFAULT_ENQUETE_FAMILIALE_BOOL.booleanValue())))
            .andExpect(
                jsonPath("$.[*].explorationSyndromeHemorragique").value(hasItem(DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].typeCirconstanceDiagnostic").value(hasItem(DEFAULT_TYPE_CIRCONSTANCE_DIAGNOSTIC)))
            .andExpect(jsonPath("$.[*].eczema").value(hasItem(DEFAULT_ECZEMA.toString())))
            .andExpect(jsonPath("$.[*].syndromeDysmorphique").value(hasItem(DEFAULT_SYNDROME_DYSMORPHIQUE.toString())))
            .andExpect(jsonPath("$.[*].albinisme").value(hasItem(DEFAULT_ALBINISME.toString())))
            .andExpect(jsonPath("$.[*].arthrogrypose").value(hasItem(DEFAULT_ARTHROGRYPOSE.toString())))
            .andExpect(jsonPath("$.[*].gb").value(hasItem(DEFAULT_GB)))
            .andExpect(jsonPath("$.[*].hb").value(hasItem(DEFAULT_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].vgm").value(hasItem(DEFAULT_VGM.doubleValue())))
            .andExpect(jsonPath("$.[*].tcmh").value(hasItem(DEFAULT_TCMH.doubleValue())))
            .andExpect(jsonPath("$.[*].plaquettes").value(hasItem(DEFAULT_PLAQUETTES)))
            .andExpect(jsonPath("$.[*].frottisSanguin").value(hasItem(DEFAULT_FROTTIS_SANGUIN)))
            .andExpect(jsonPath("$.[*].ts").value(hasItem(DEFAULT_TS.doubleValue())))
            .andExpect(jsonPath("$.[*].toPfa").value(hasItem(DEFAULT_TO_PFA.doubleValue())))
            .andExpect(jsonPath("$.[*].toPfa100ColEpi").value(hasItem(DEFAULT_TO_PFA_100_COL_EPI.doubleValue())))
            .andExpect(jsonPath("$.[*].toPfa100ColAdp").value(hasItem(DEFAULT_TO_PFA_100_COL_ADP.doubleValue())))
            .andExpect(jsonPath("$.[*].tp").value(hasItem(DEFAULT_TP.doubleValue())))
            .andExpect(jsonPath("$.[*].tca").value(hasItem(DEFAULT_TCA.doubleValue())))
            .andExpect(jsonPath("$.[*].aggregometriePlaquettaire").value(hasItem(DEFAULT_AGGREGOMETRIE_PLAQUETTAIRE.toString())))
            .andExpect(jsonPath("$.[*].resultatAgregometrie").value(hasItem(DEFAULT_RESULTAT_AGREGOMETRIE)))
            .andExpect(jsonPath("$.[*].cmf").value(hasItem(DEFAULT_CMF.toString())))
            .andExpect(jsonPath("$.[*].resultatCmf").value(hasItem(DEFAULT_RESULTAT_CMF)))
            .andExpect(jsonPath("$.[*].dosageFvwAg").value(hasItem(DEFAULT_DOSAGE_FVW_AG.doubleValue())))
            .andExpect(jsonPath("$.[*].dosageFvwRco").value(hasItem(DEFAULT_DOSAGE_FVW_RCO.doubleValue())))
            .andExpect(jsonPath("$.[*].etudeGenetique").value(hasItem(DEFAULT_ETUDE_GENETIQUE.toString())))
            .andExpect(jsonPath("$.[*].resultatEtudeGenetique").value(hasItem(DEFAULT_RESULTAT_ETUDE_GENETIQUE)))
            .andExpect(jsonPath("$.[*].thrombasthenieGlanzmann").value(hasItem(DEFAULT_THROMBASTHENIE_GLANZMANN.booleanValue())))
            .andExpect(jsonPath("$.[*].maladieBernardSoulier").value(hasItem(DEFAULT_MALADIE_BERNARD_SOULIER.booleanValue())))
            .andExpect(jsonPath("$.[*].autreThrombopathie").value(hasItem(DEFAULT_AUTRE_THROMBOPATHIE.booleanValue())))
            .andExpect(jsonPath("$.[*].autreThrombopathieDetails").value(hasItem(DEFAULT_AUTRE_THROMBOPATHIE_DETAILS)))
            .andExpect(jsonPath("$.[*].ecchymose").value(hasItem(DEFAULT_ECCHYMOSE.toString())))
            .andExpect(jsonPath("$.[*].typeEcchymose").value(hasItem(DEFAULT_TYPE_ECCHYMOSE.toString())))
            .andExpect(jsonPath("$.[*].gingivorragies").value(hasItem(DEFAULT_GINGIVORRAGIES.toString())))
            .andExpect(jsonPath("$.[*].typeGingivorragies").value(hasItem(DEFAULT_TYPE_GINGIVORRAGIES.toString())))
            .andExpect(jsonPath("$.[*].epistaxis").value(hasItem(DEFAULT_EPISTAXIS.toString())))
            .andExpect(jsonPath("$.[*].typeEpistaxis").value(hasItem(DEFAULT_TYPE_EPISTAXIS.toString())))
            .andExpect(jsonPath("$.[*].hematome").value(hasItem(DEFAULT_HEMATOME.toString())))
            .andExpect(jsonPath("$.[*].typeHematome").value(hasItem(DEFAULT_TYPE_HEMATOME.toString())))
            .andExpect(jsonPath("$.[*].siegelHematome").value(hasItem(DEFAULT_SIEGEL_HEMATOME)))
            .andExpect(jsonPath("$.[*].menoMetrorragies").value(hasItem(DEFAULT_MENO_METRORRAGIES.toString())))
            .andExpect(jsonPath("$.[*].frequenceMenoMetrorragies").value(hasItem(DEFAULT_FREQUENCE_MENO_METRORRAGIES)))
            .andExpect(jsonPath("$.[*].severiteMenoMetrorragies").value(hasItem(DEFAULT_SEVERITE_MENO_METRORRAGIES)))
            .andExpect(jsonPath("$.[*].explorationMenoMetrorragies").value(hasItem(DEFAULT_EXPLORATION_MENO_METRORRAGIES.toString())))
            .andExpect(jsonPath("$.[*].typeLesionSousJacente").value(hasItem(DEFAULT_TYPE_LESION_SOUS_JACENTE)))
            .andExpect(jsonPath("$.[*].hemorragieVisceres").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES.toString())))
            .andExpect(jsonPath("$.[*].hemorragieDigestiveHaute").value(hasItem(DEFAULT_HEMORRAGIE_DIGESTIVE_HAUTE.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragieDigestiveBasse").value(hasItem(DEFAULT_HEMORRAGIE_DIGESTIVE_BASSE.booleanValue())))
            .andExpect(jsonPath("$.[*].hematurie").value(hasItem(DEFAULT_HEMATURIE.booleanValue())))
            .andExpect(jsonPath("$.[*].explorationHemorragieVisceres").value(hasItem(DEFAULT_EXPLORATION_HEMORRAGIE_VISCERES.toString())))
            .andExpect(jsonPath("$.[*].lesionSousJacenteVisceres").value(hasItem(DEFAULT_LESION_SOUS_JACENTE_VISCERES)))
            .andExpect(jsonPath("$.[*].interventionChirurgicale").value(hasItem(DEFAULT_INTERVENTION_CHIRURGICALE.toString())))
            .andExpect(jsonPath("$.[*].typeInterventionChirurgicale").value(hasItem(DEFAULT_TYPE_INTERVENTION_CHIRURGICALE)))
            .andExpect(jsonPath("$.[*].hematomesCerebrales").value(hasItem(DEFAULT_HEMATOMES_CEREBRALES.booleanValue())))
            .andExpect(jsonPath("$.[*].edcHemorragique").value(hasItem(DEFAULT_EDC_HEMORRAGIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].transfusionCps").value(hasItem(DEFAULT_TRANSFUSION_CPS.toString())))
            .andExpect(jsonPath("$.[*].facteurVIIRecombinant").value(hasItem(DEFAULT_FACTEUR_VII_RECOMBINANT.toString())))
            .andExpect(jsonPath("$.[*].dicynone").value(hasItem(DEFAULT_DICYNONE.toString())))
            .andExpect(jsonPath("$.[*].exacyl").value(hasItem(DEFAULT_EXACYL.toString())))
            .andExpect(jsonPath("$.[*].traitementOestroprogestatif").value(hasItem(DEFAULT_TRAITEMENT_OESTROPROGESTATIF.toString())))
            .andExpect(jsonPath("$.[*].mechage").value(hasItem(DEFAULT_MECHAGE.toString())))
            .andExpect(jsonPath("$.[*].transfusionCgr").value(hasItem(DEFAULT_TRANSFUSION_CGR.toString())))
            .andExpect(jsonPath("$.[*].traitementMartial").value(hasItem(DEFAULT_TRAITEMENT_MARTIAL.toString())))
            .andExpect(jsonPath("$.[*].thrombose").value(hasItem(DEFAULT_THROMBOSE.toString())))
            .andExpect(jsonPath("$.[*].alloImmunisationHLA").value(hasItem(DEFAULT_ALLO_IMMUNISATION_HLA.toString())));
    }

    @Test
    @Transactional
    void getFicheThrombopathie() throws Exception {
        // Initialize the database
        insertedFicheThrombopathie = ficheThrombopathieRepository.saveAndFlush(ficheThrombopathie);

        // Get the ficheThrombopathie
        restFicheThrombopathieMockMvc
            .perform(get(ENTITY_API_URL_ID, ficheThrombopathie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ficheThrombopathie.getId().intValue()))
            .andExpect(jsonPath("$.dossierNumber").value(DEFAULT_DOSSIER_NUMBER))
            .andExpect(jsonPath("$.ordreNumber").value(DEFAULT_ORDRE_NUMBER))
            .andExpect(jsonPath("$.indexNumber").value(DEFAULT_INDEX_NUMBER))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.nomJeuneFille").value(DEFAULT_NOM_JEUNE_FILLE))
            .andExpect(jsonPath("$.profession").value(DEFAULT_PROFESSION))
            .andExpect(jsonPath("$.etatCivil").value(DEFAULT_ETAT_CIVIL.toString()))
            .andExpect(jsonPath("$.serviceClinique").value(DEFAULT_SERVICE_CLINIQUE.toString()))
            .andExpect(jsonPath("$.autreServiceClinique").value(DEFAULT_AUTRE_SERVICE_CLINIQUE))
            .andExpect(jsonPath("$.couvertureSociale").value(DEFAULT_COUVERTURE_SOCIALE.toString()))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE.toString()))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.ageActuel").value(DEFAULT_AGE_ACTUEL))
            .andExpect(jsonPath("$.origine").value(DEFAULT_ORIGINE.toString()))
            .andExpect(jsonPath("$.autreOrigine").value(DEFAULT_AUTRE_ORIGINE))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
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
            .andExpect(jsonPath("$.parentsConsanguins").value(DEFAULT_PARENTS_CONSANGUINS.toString()))
            .andExpect(jsonPath("$.degreParenteConsanguins").value(DEFAULT_DEGRE_PARENTE_CONSANGUINS))
            .andExpect(jsonPath("$.casSimilaires").value(DEFAULT_CAS_SIMILAIRES.toString()))
            .andExpect(jsonPath("$.nombreCas").value(DEFAULT_NOMBRE_CAS))
            .andExpect(jsonPath("$.degreParenteCasSimilaires").value(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES))
            .andExpect(jsonPath("$.decesSyndromeHemorragique").value(DEFAULT_DECES_SYNDROME_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.nombreDeces").value(DEFAULT_NOMBRE_DECES))
            .andExpect(jsonPath("$.typeHemorragie").value(DEFAULT_TYPE_HEMORRAGIE))
            .andExpect(jsonPath("$.lienParenteDeces").value(DEFAULT_LIEN_PARENTE_DECES))
            .andExpect(jsonPath("$.enqueteFamiliale").value(DEFAULT_ENQUETE_FAMILIALE.toString()))
            .andExpect(jsonPath("$.antecedentsMedicaux").value(DEFAULT_ANTECEDENTS_MEDICAUX))
            .andExpect(jsonPath("$.antecedentsChirurgicaux").value(DEFAULT_ANTECEDENTS_CHIRURGICAUX.toString()))
            .andExpect(jsonPath("$.typeChirurgie").value(DEFAULT_TYPE_CHIRURGIE))
            .andExpect(jsonPath("$.complicationHemorragique").value(DEFAULT_COMPLICATION_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.agePremierSigneHemorragique").value(DEFAULT_AGE_PREMIER_SIGNE_HEMORRAGIQUE))
            .andExpect(jsonPath("$.typeSyndromeHemorragique").value(DEFAULT_TYPE_SYNDROME_HEMORRAGIQUE))
            .andExpect(jsonPath("$.ageDiagnostic").value(DEFAULT_AGE_DIAGNOSTIC))
            .andExpect(jsonPath("$.decouverteFortuite").value(DEFAULT_DECOUVERTE_FORTUITE.booleanValue()))
            .andExpect(jsonPath("$.enqueteFamilialeBool").value(DEFAULT_ENQUETE_FAMILIALE_BOOL.booleanValue()))
            .andExpect(jsonPath("$.explorationSyndromeHemorragique").value(DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE.booleanValue()))
            .andExpect(jsonPath("$.typeCirconstanceDiagnostic").value(DEFAULT_TYPE_CIRCONSTANCE_DIAGNOSTIC))
            .andExpect(jsonPath("$.eczema").value(DEFAULT_ECZEMA.toString()))
            .andExpect(jsonPath("$.syndromeDysmorphique").value(DEFAULT_SYNDROME_DYSMORPHIQUE.toString()))
            .andExpect(jsonPath("$.albinisme").value(DEFAULT_ALBINISME.toString()))
            .andExpect(jsonPath("$.arthrogrypose").value(DEFAULT_ARTHROGRYPOSE.toString()))
            .andExpect(jsonPath("$.gb").value(DEFAULT_GB))
            .andExpect(jsonPath("$.hb").value(DEFAULT_HB.doubleValue()))
            .andExpect(jsonPath("$.vgm").value(DEFAULT_VGM.doubleValue()))
            .andExpect(jsonPath("$.tcmh").value(DEFAULT_TCMH.doubleValue()))
            .andExpect(jsonPath("$.plaquettes").value(DEFAULT_PLAQUETTES))
            .andExpect(jsonPath("$.frottisSanguin").value(DEFAULT_FROTTIS_SANGUIN))
            .andExpect(jsonPath("$.ts").value(DEFAULT_TS.doubleValue()))
            .andExpect(jsonPath("$.toPfa").value(DEFAULT_TO_PFA.doubleValue()))
            .andExpect(jsonPath("$.toPfa100ColEpi").value(DEFAULT_TO_PFA_100_COL_EPI.doubleValue()))
            .andExpect(jsonPath("$.toPfa100ColAdp").value(DEFAULT_TO_PFA_100_COL_ADP.doubleValue()))
            .andExpect(jsonPath("$.tp").value(DEFAULT_TP.doubleValue()))
            .andExpect(jsonPath("$.tca").value(DEFAULT_TCA.doubleValue()))
            .andExpect(jsonPath("$.aggregometriePlaquettaire").value(DEFAULT_AGGREGOMETRIE_PLAQUETTAIRE.toString()))
            .andExpect(jsonPath("$.resultatAgregometrie").value(DEFAULT_RESULTAT_AGREGOMETRIE))
            .andExpect(jsonPath("$.cmf").value(DEFAULT_CMF.toString()))
            .andExpect(jsonPath("$.resultatCmf").value(DEFAULT_RESULTAT_CMF))
            .andExpect(jsonPath("$.dosageFvwAg").value(DEFAULT_DOSAGE_FVW_AG.doubleValue()))
            .andExpect(jsonPath("$.dosageFvwRco").value(DEFAULT_DOSAGE_FVW_RCO.doubleValue()))
            .andExpect(jsonPath("$.etudeGenetique").value(DEFAULT_ETUDE_GENETIQUE.toString()))
            .andExpect(jsonPath("$.resultatEtudeGenetique").value(DEFAULT_RESULTAT_ETUDE_GENETIQUE))
            .andExpect(jsonPath("$.thrombasthenieGlanzmann").value(DEFAULT_THROMBASTHENIE_GLANZMANN.booleanValue()))
            .andExpect(jsonPath("$.maladieBernardSoulier").value(DEFAULT_MALADIE_BERNARD_SOULIER.booleanValue()))
            .andExpect(jsonPath("$.autreThrombopathie").value(DEFAULT_AUTRE_THROMBOPATHIE.booleanValue()))
            .andExpect(jsonPath("$.autreThrombopathieDetails").value(DEFAULT_AUTRE_THROMBOPATHIE_DETAILS))
            .andExpect(jsonPath("$.ecchymose").value(DEFAULT_ECCHYMOSE.toString()))
            .andExpect(jsonPath("$.typeEcchymose").value(DEFAULT_TYPE_ECCHYMOSE.toString()))
            .andExpect(jsonPath("$.gingivorragies").value(DEFAULT_GINGIVORRAGIES.toString()))
            .andExpect(jsonPath("$.typeGingivorragies").value(DEFAULT_TYPE_GINGIVORRAGIES.toString()))
            .andExpect(jsonPath("$.epistaxis").value(DEFAULT_EPISTAXIS.toString()))
            .andExpect(jsonPath("$.typeEpistaxis").value(DEFAULT_TYPE_EPISTAXIS.toString()))
            .andExpect(jsonPath("$.hematome").value(DEFAULT_HEMATOME.toString()))
            .andExpect(jsonPath("$.typeHematome").value(DEFAULT_TYPE_HEMATOME.toString()))
            .andExpect(jsonPath("$.siegelHematome").value(DEFAULT_SIEGEL_HEMATOME))
            .andExpect(jsonPath("$.menoMetrorragies").value(DEFAULT_MENO_METRORRAGIES.toString()))
            .andExpect(jsonPath("$.frequenceMenoMetrorragies").value(DEFAULT_FREQUENCE_MENO_METRORRAGIES))
            .andExpect(jsonPath("$.severiteMenoMetrorragies").value(DEFAULT_SEVERITE_MENO_METRORRAGIES))
            .andExpect(jsonPath("$.explorationMenoMetrorragies").value(DEFAULT_EXPLORATION_MENO_METRORRAGIES.toString()))
            .andExpect(jsonPath("$.typeLesionSousJacente").value(DEFAULT_TYPE_LESION_SOUS_JACENTE))
            .andExpect(jsonPath("$.hemorragieVisceres").value(DEFAULT_HEMORRAGIE_VISCERES.toString()))
            .andExpect(jsonPath("$.hemorragieDigestiveHaute").value(DEFAULT_HEMORRAGIE_DIGESTIVE_HAUTE.booleanValue()))
            .andExpect(jsonPath("$.hemorragieDigestiveBasse").value(DEFAULT_HEMORRAGIE_DIGESTIVE_BASSE.booleanValue()))
            .andExpect(jsonPath("$.hematurie").value(DEFAULT_HEMATURIE.booleanValue()))
            .andExpect(jsonPath("$.explorationHemorragieVisceres").value(DEFAULT_EXPLORATION_HEMORRAGIE_VISCERES.toString()))
            .andExpect(jsonPath("$.lesionSousJacenteVisceres").value(DEFAULT_LESION_SOUS_JACENTE_VISCERES))
            .andExpect(jsonPath("$.interventionChirurgicale").value(DEFAULT_INTERVENTION_CHIRURGICALE.toString()))
            .andExpect(jsonPath("$.typeInterventionChirurgicale").value(DEFAULT_TYPE_INTERVENTION_CHIRURGICALE))
            .andExpect(jsonPath("$.hematomesCerebrales").value(DEFAULT_HEMATOMES_CEREBRALES.booleanValue()))
            .andExpect(jsonPath("$.edcHemorragique").value(DEFAULT_EDC_HEMORRAGIQUE.booleanValue()))
            .andExpect(jsonPath("$.transfusionCps").value(DEFAULT_TRANSFUSION_CPS.toString()))
            .andExpect(jsonPath("$.facteurVIIRecombinant").value(DEFAULT_FACTEUR_VII_RECOMBINANT.toString()))
            .andExpect(jsonPath("$.dicynone").value(DEFAULT_DICYNONE.toString()))
            .andExpect(jsonPath("$.exacyl").value(DEFAULT_EXACYL.toString()))
            .andExpect(jsonPath("$.traitementOestroprogestatif").value(DEFAULT_TRAITEMENT_OESTROPROGESTATIF.toString()))
            .andExpect(jsonPath("$.mechage").value(DEFAULT_MECHAGE.toString()))
            .andExpect(jsonPath("$.transfusionCgr").value(DEFAULT_TRANSFUSION_CGR.toString()))
            .andExpect(jsonPath("$.traitementMartial").value(DEFAULT_TRAITEMENT_MARTIAL.toString()))
            .andExpect(jsonPath("$.thrombose").value(DEFAULT_THROMBOSE.toString()))
            .andExpect(jsonPath("$.alloImmunisationHLA").value(DEFAULT_ALLO_IMMUNISATION_HLA.toString()));
    }

    @Test
    @Transactional
    void getNonExistingFicheThrombopathie() throws Exception {
        // Get the ficheThrombopathie
        restFicheThrombopathieMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFicheThrombopathie() throws Exception {
        // Initialize the database
        insertedFicheThrombopathie = ficheThrombopathieRepository.saveAndFlush(ficheThrombopathie);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheThrombopathie
        FicheThrombopathie updatedFicheThrombopathie = ficheThrombopathieRepository.findById(ficheThrombopathie.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFicheThrombopathie are not directly saved in db
        em.detach(updatedFicheThrombopathie);
        updatedFicheThrombopathie
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .profession(UPDATED_PROFESSION)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .serviceClinique(UPDATED_SERVICE_CLINIQUE)
            .autreServiceClinique(UPDATED_AUTRE_SERVICE_CLINIQUE)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .sexe(UPDATED_SEXE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .ageActuel(UPDATED_AGE_ACTUEL)
            .origine(UPDATED_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
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
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilaires(UPDATED_CAS_SIMILAIRES)
            .nombreCas(UPDATED_NOMBRE_CAS)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .decesSyndromeHemorragique(UPDATED_DECES_SYNDROME_HEMORRAGIQUE)
            .nombreDeces(UPDATED_NOMBRE_DECES)
            .typeHemorragie(UPDATED_TYPE_HEMORRAGIE)
            .lienParenteDeces(UPDATED_LIEN_PARENTE_DECES)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .antecedentsMedicaux(UPDATED_ANTECEDENTS_MEDICAUX)
            .antecedentsChirurgicaux(UPDATED_ANTECEDENTS_CHIRURGICAUX)
            .typeChirurgie(UPDATED_TYPE_CHIRURGIE)
            .complicationHemorragique(UPDATED_COMPLICATION_HEMORRAGIQUE)
            .agePremierSigneHemorragique(UPDATED_AGE_PREMIER_SIGNE_HEMORRAGIQUE)
            .typeSyndromeHemorragique(UPDATED_TYPE_SYNDROME_HEMORRAGIQUE)
            .ageDiagnostic(UPDATED_AGE_DIAGNOSTIC)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .enqueteFamilialeBool(UPDATED_ENQUETE_FAMILIALE_BOOL)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .typeCirconstanceDiagnostic(UPDATED_TYPE_CIRCONSTANCE_DIAGNOSTIC)
            .eczema(UPDATED_ECZEMA)
            .syndromeDysmorphique(UPDATED_SYNDROME_DYSMORPHIQUE)
            .albinisme(UPDATED_ALBINISME)
            .arthrogrypose(UPDATED_ARTHROGRYPOSE)
            .gb(UPDATED_GB)
            .hb(UPDATED_HB)
            .vgm(UPDATED_VGM)
            .tcmh(UPDATED_TCMH)
            .plaquettes(UPDATED_PLAQUETTES)
            .frottisSanguin(UPDATED_FROTTIS_SANGUIN)
            .ts(UPDATED_TS)
            .toPfa(UPDATED_TO_PFA)
            .toPfa100ColEpi(UPDATED_TO_PFA_100_COL_EPI)
            .toPfa100ColAdp(UPDATED_TO_PFA_100_COL_ADP)
            .tp(UPDATED_TP)
            .tca(UPDATED_TCA)
            .aggregometriePlaquettaire(UPDATED_AGGREGOMETRIE_PLAQUETTAIRE)
            .resultatAgregometrie(UPDATED_RESULTAT_AGREGOMETRIE)
            .cmf(UPDATED_CMF)
            .resultatCmf(UPDATED_RESULTAT_CMF)
            .dosageFvwAg(UPDATED_DOSAGE_FVW_AG)
            .dosageFvwRco(UPDATED_DOSAGE_FVW_RCO)
            .etudeGenetique(UPDATED_ETUDE_GENETIQUE)
            .resultatEtudeGenetique(UPDATED_RESULTAT_ETUDE_GENETIQUE)
            .thrombasthenieGlanzmann(UPDATED_THROMBASTHENIE_GLANZMANN)
            .maladieBernardSoulier(UPDATED_MALADIE_BERNARD_SOULIER)
            .autreThrombopathie(UPDATED_AUTRE_THROMBOPATHIE)
            .autreThrombopathieDetails(UPDATED_AUTRE_THROMBOPATHIE_DETAILS)
            .ecchymose(UPDATED_ECCHYMOSE)
            .typeEcchymose(UPDATED_TYPE_ECCHYMOSE)
            .gingivorragies(UPDATED_GINGIVORRAGIES)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .epistaxis(UPDATED_EPISTAXIS)
            .typeEpistaxis(UPDATED_TYPE_EPISTAXIS)
            .hematome(UPDATED_HEMATOME)
            .typeHematome(UPDATED_TYPE_HEMATOME)
            .siegelHematome(UPDATED_SIEGEL_HEMATOME)
            .menoMetrorragies(UPDATED_MENO_METRORRAGIES)
            .frequenceMenoMetrorragies(UPDATED_FREQUENCE_MENO_METRORRAGIES)
            .severiteMenoMetrorragies(UPDATED_SEVERITE_MENO_METRORRAGIES)
            .explorationMenoMetrorragies(UPDATED_EXPLORATION_MENO_METRORRAGIES)
            .typeLesionSousJacente(UPDATED_TYPE_LESION_SOUS_JACENTE)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hemorragieDigestiveHaute(UPDATED_HEMORRAGIE_DIGESTIVE_HAUTE)
            .hemorragieDigestiveBasse(UPDATED_HEMORRAGIE_DIGESTIVE_BASSE)
            .hematurie(UPDATED_HEMATURIE)
            .explorationHemorragieVisceres(UPDATED_EXPLORATION_HEMORRAGIE_VISCERES)
            .lesionSousJacenteVisceres(UPDATED_LESION_SOUS_JACENTE_VISCERES)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .typeInterventionChirurgicale(UPDATED_TYPE_INTERVENTION_CHIRURGICALE)
            .hematomesCerebrales(UPDATED_HEMATOMES_CEREBRALES)
            .edcHemorragique(UPDATED_EDC_HEMORRAGIQUE)
            .transfusionCps(UPDATED_TRANSFUSION_CPS)
            .facteurVIIRecombinant(UPDATED_FACTEUR_VII_RECOMBINANT)
            .dicynone(UPDATED_DICYNONE)
            .exacyl(UPDATED_EXACYL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .mechage(UPDATED_MECHAGE)
            .transfusionCgr(UPDATED_TRANSFUSION_CGR)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .thrombose(UPDATED_THROMBOSE)
            .alloImmunisationHLA(UPDATED_ALLO_IMMUNISATION_HLA);
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(updatedFicheThrombopathie);

        restFicheThrombopathieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheThrombopathieDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheThrombopathieDTO))
            )
            .andExpect(status().isOk());

        // Validate the FicheThrombopathie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFicheThrombopathieToMatchAllProperties(updatedFicheThrombopathie);
    }

    @Test
    @Transactional
    void putNonExistingFicheThrombopathie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheThrombopathie.setId(longCount.incrementAndGet());

        // Create the FicheThrombopathie
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheThrombopathieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheThrombopathieDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheThrombopathieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheThrombopathie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFicheThrombopathie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheThrombopathie.setId(longCount.incrementAndGet());

        // Create the FicheThrombopathie
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheThrombopathieMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheThrombopathieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheThrombopathie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFicheThrombopathie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheThrombopathie.setId(longCount.incrementAndGet());

        // Create the FicheThrombopathie
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheThrombopathieMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheThrombopathieDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheThrombopathie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFicheThrombopathieWithPatch() throws Exception {
        // Initialize the database
        insertedFicheThrombopathie = ficheThrombopathieRepository.saveAndFlush(ficheThrombopathie);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheThrombopathie using partial update
        FicheThrombopathie partialUpdatedFicheThrombopathie = new FicheThrombopathie();
        partialUpdatedFicheThrombopathie.setId(ficheThrombopathie.getId());

        partialUpdatedFicheThrombopathie
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .profession(UPDATED_PROFESSION)
            .serviceClinique(UPDATED_SERVICE_CLINIQUE)
            .autreServiceClinique(UPDATED_AUTRE_SERVICE_CLINIQUE)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .professionPere(UPDATED_PROFESSION_PERE)
            .professionMere(UPDATED_PROFESSION_MERE)
            .medecinTraitant(UPDATED_MEDECIN_TRAITANT)
            .serviceOrigine(UPDATED_SERVICE_ORIGINE)
            .biologisteResponsableDg(UPDATED_BIOLOGISTE_RESPONSABLE_DG)
            .autreDiagnostic(UPDATED_AUTRE_DIAGNOSTIC)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilaires(UPDATED_CAS_SIMILAIRES)
            .nombreCas(UPDATED_NOMBRE_CAS)
            .typeHemorragie(UPDATED_TYPE_HEMORRAGIE)
            .lienParenteDeces(UPDATED_LIEN_PARENTE_DECES)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .antecedentsMedicaux(UPDATED_ANTECEDENTS_MEDICAUX)
            .agePremierSigneHemorragique(UPDATED_AGE_PREMIER_SIGNE_HEMORRAGIQUE)
            .typeCirconstanceDiagnostic(UPDATED_TYPE_CIRCONSTANCE_DIAGNOSTIC)
            .syndromeDysmorphique(UPDATED_SYNDROME_DYSMORPHIQUE)
            .albinisme(UPDATED_ALBINISME)
            .arthrogrypose(UPDATED_ARTHROGRYPOSE)
            .plaquettes(UPDATED_PLAQUETTES)
            .toPfa100ColAdp(UPDATED_TO_PFA_100_COL_ADP)
            .aggregometriePlaquettaire(UPDATED_AGGREGOMETRIE_PLAQUETTAIRE)
            .resultatAgregometrie(UPDATED_RESULTAT_AGREGOMETRIE)
            .cmf(UPDATED_CMF)
            .resultatCmf(UPDATED_RESULTAT_CMF)
            .resultatEtudeGenetique(UPDATED_RESULTAT_ETUDE_GENETIQUE)
            .thrombasthenieGlanzmann(UPDATED_THROMBASTHENIE_GLANZMANN)
            .autreThrombopathie(UPDATED_AUTRE_THROMBOPATHIE)
            .ecchymose(UPDATED_ECCHYMOSE)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .epistaxis(UPDATED_EPISTAXIS)
            .typeEpistaxis(UPDATED_TYPE_EPISTAXIS)
            .hematome(UPDATED_HEMATOME)
            .siegelHematome(UPDATED_SIEGEL_HEMATOME)
            .explorationMenoMetrorragies(UPDATED_EXPLORATION_MENO_METRORRAGIES)
            .typeLesionSousJacente(UPDATED_TYPE_LESION_SOUS_JACENTE)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hematurie(UPDATED_HEMATURIE)
            .lesionSousJacenteVisceres(UPDATED_LESION_SOUS_JACENTE_VISCERES)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .hematomesCerebrales(UPDATED_HEMATOMES_CEREBRALES)
            .edcHemorragique(UPDATED_EDC_HEMORRAGIQUE)
            .facteurVIIRecombinant(UPDATED_FACTEUR_VII_RECOMBINANT)
            .dicynone(UPDATED_DICYNONE)
            .thrombose(UPDATED_THROMBOSE);

        restFicheThrombopathieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheThrombopathie.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheThrombopathie))
            )
            .andExpect(status().isOk());

        // Validate the FicheThrombopathie in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheThrombopathieUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFicheThrombopathie, ficheThrombopathie),
            getPersistedFicheThrombopathie(ficheThrombopathie)
        );
    }

    @Test
    @Transactional
    void fullUpdateFicheThrombopathieWithPatch() throws Exception {
        // Initialize the database
        insertedFicheThrombopathie = ficheThrombopathieRepository.saveAndFlush(ficheThrombopathie);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheThrombopathie using partial update
        FicheThrombopathie partialUpdatedFicheThrombopathie = new FicheThrombopathie();
        partialUpdatedFicheThrombopathie.setId(ficheThrombopathie.getId());

        partialUpdatedFicheThrombopathie
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .profession(UPDATED_PROFESSION)
            .etatCivil(UPDATED_ETAT_CIVIL)
            .serviceClinique(UPDATED_SERVICE_CLINIQUE)
            .autreServiceClinique(UPDATED_AUTRE_SERVICE_CLINIQUE)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .sexe(UPDATED_SEXE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .ageActuel(UPDATED_AGE_ACTUEL)
            .origine(UPDATED_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .adresse(UPDATED_ADRESSE)
            .telephone(UPDATED_TELEPHONE)
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
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilaires(UPDATED_CAS_SIMILAIRES)
            .nombreCas(UPDATED_NOMBRE_CAS)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .decesSyndromeHemorragique(UPDATED_DECES_SYNDROME_HEMORRAGIQUE)
            .nombreDeces(UPDATED_NOMBRE_DECES)
            .typeHemorragie(UPDATED_TYPE_HEMORRAGIE)
            .lienParenteDeces(UPDATED_LIEN_PARENTE_DECES)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .antecedentsMedicaux(UPDATED_ANTECEDENTS_MEDICAUX)
            .antecedentsChirurgicaux(UPDATED_ANTECEDENTS_CHIRURGICAUX)
            .typeChirurgie(UPDATED_TYPE_CHIRURGIE)
            .complicationHemorragique(UPDATED_COMPLICATION_HEMORRAGIQUE)
            .agePremierSigneHemorragique(UPDATED_AGE_PREMIER_SIGNE_HEMORRAGIQUE)
            .typeSyndromeHemorragique(UPDATED_TYPE_SYNDROME_HEMORRAGIQUE)
            .ageDiagnostic(UPDATED_AGE_DIAGNOSTIC)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .enqueteFamilialeBool(UPDATED_ENQUETE_FAMILIALE_BOOL)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .typeCirconstanceDiagnostic(UPDATED_TYPE_CIRCONSTANCE_DIAGNOSTIC)
            .eczema(UPDATED_ECZEMA)
            .syndromeDysmorphique(UPDATED_SYNDROME_DYSMORPHIQUE)
            .albinisme(UPDATED_ALBINISME)
            .arthrogrypose(UPDATED_ARTHROGRYPOSE)
            .gb(UPDATED_GB)
            .hb(UPDATED_HB)
            .vgm(UPDATED_VGM)
            .tcmh(UPDATED_TCMH)
            .plaquettes(UPDATED_PLAQUETTES)
            .frottisSanguin(UPDATED_FROTTIS_SANGUIN)
            .ts(UPDATED_TS)
            .toPfa(UPDATED_TO_PFA)
            .toPfa100ColEpi(UPDATED_TO_PFA_100_COL_EPI)
            .toPfa100ColAdp(UPDATED_TO_PFA_100_COL_ADP)
            .tp(UPDATED_TP)
            .tca(UPDATED_TCA)
            .aggregometriePlaquettaire(UPDATED_AGGREGOMETRIE_PLAQUETTAIRE)
            .resultatAgregometrie(UPDATED_RESULTAT_AGREGOMETRIE)
            .cmf(UPDATED_CMF)
            .resultatCmf(UPDATED_RESULTAT_CMF)
            .dosageFvwAg(UPDATED_DOSAGE_FVW_AG)
            .dosageFvwRco(UPDATED_DOSAGE_FVW_RCO)
            .etudeGenetique(UPDATED_ETUDE_GENETIQUE)
            .resultatEtudeGenetique(UPDATED_RESULTAT_ETUDE_GENETIQUE)
            .thrombasthenieGlanzmann(UPDATED_THROMBASTHENIE_GLANZMANN)
            .maladieBernardSoulier(UPDATED_MALADIE_BERNARD_SOULIER)
            .autreThrombopathie(UPDATED_AUTRE_THROMBOPATHIE)
            .autreThrombopathieDetails(UPDATED_AUTRE_THROMBOPATHIE_DETAILS)
            .ecchymose(UPDATED_ECCHYMOSE)
            .typeEcchymose(UPDATED_TYPE_ECCHYMOSE)
            .gingivorragies(UPDATED_GINGIVORRAGIES)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .epistaxis(UPDATED_EPISTAXIS)
            .typeEpistaxis(UPDATED_TYPE_EPISTAXIS)
            .hematome(UPDATED_HEMATOME)
            .typeHematome(UPDATED_TYPE_HEMATOME)
            .siegelHematome(UPDATED_SIEGEL_HEMATOME)
            .menoMetrorragies(UPDATED_MENO_METRORRAGIES)
            .frequenceMenoMetrorragies(UPDATED_FREQUENCE_MENO_METRORRAGIES)
            .severiteMenoMetrorragies(UPDATED_SEVERITE_MENO_METRORRAGIES)
            .explorationMenoMetrorragies(UPDATED_EXPLORATION_MENO_METRORRAGIES)
            .typeLesionSousJacente(UPDATED_TYPE_LESION_SOUS_JACENTE)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hemorragieDigestiveHaute(UPDATED_HEMORRAGIE_DIGESTIVE_HAUTE)
            .hemorragieDigestiveBasse(UPDATED_HEMORRAGIE_DIGESTIVE_BASSE)
            .hematurie(UPDATED_HEMATURIE)
            .explorationHemorragieVisceres(UPDATED_EXPLORATION_HEMORRAGIE_VISCERES)
            .lesionSousJacenteVisceres(UPDATED_LESION_SOUS_JACENTE_VISCERES)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .typeInterventionChirurgicale(UPDATED_TYPE_INTERVENTION_CHIRURGICALE)
            .hematomesCerebrales(UPDATED_HEMATOMES_CEREBRALES)
            .edcHemorragique(UPDATED_EDC_HEMORRAGIQUE)
            .transfusionCps(UPDATED_TRANSFUSION_CPS)
            .facteurVIIRecombinant(UPDATED_FACTEUR_VII_RECOMBINANT)
            .dicynone(UPDATED_DICYNONE)
            .exacyl(UPDATED_EXACYL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .mechage(UPDATED_MECHAGE)
            .transfusionCgr(UPDATED_TRANSFUSION_CGR)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .thrombose(UPDATED_THROMBOSE)
            .alloImmunisationHLA(UPDATED_ALLO_IMMUNISATION_HLA);

        restFicheThrombopathieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheThrombopathie.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheThrombopathie))
            )
            .andExpect(status().isOk());

        // Validate the FicheThrombopathie in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheThrombopathieUpdatableFieldsEquals(
            partialUpdatedFicheThrombopathie,
            getPersistedFicheThrombopathie(partialUpdatedFicheThrombopathie)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFicheThrombopathie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheThrombopathie.setId(longCount.incrementAndGet());

        // Create the FicheThrombopathie
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheThrombopathieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ficheThrombopathieDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheThrombopathieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheThrombopathie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFicheThrombopathie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheThrombopathie.setId(longCount.incrementAndGet());

        // Create the FicheThrombopathie
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheThrombopathieMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheThrombopathieDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheThrombopathie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFicheThrombopathie() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheThrombopathie.setId(longCount.incrementAndGet());

        // Create the FicheThrombopathie
        FicheThrombopathieDTO ficheThrombopathieDTO = ficheThrombopathieMapper.toDto(ficheThrombopathie);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheThrombopathieMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(ficheThrombopathieDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheThrombopathie in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFicheThrombopathie() throws Exception {
        // Initialize the database
        insertedFicheThrombopathie = ficheThrombopathieRepository.saveAndFlush(ficheThrombopathie);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the ficheThrombopathie
        restFicheThrombopathieMockMvc
            .perform(delete(ENTITY_API_URL_ID, ficheThrombopathie.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return ficheThrombopathieRepository.count();
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

    protected FicheThrombopathie getPersistedFicheThrombopathie(FicheThrombopathie ficheThrombopathie) {
        return ficheThrombopathieRepository.findById(ficheThrombopathie.getId()).orElseThrow();
    }

    protected void assertPersistedFicheThrombopathieToMatchAllProperties(FicheThrombopathie expectedFicheThrombopathie) {
        assertFicheThrombopathieAllPropertiesEquals(expectedFicheThrombopathie, getPersistedFicheThrombopathie(expectedFicheThrombopathie));
    }

    protected void assertPersistedFicheThrombopathieToMatchUpdatableProperties(FicheThrombopathie expectedFicheThrombopathie) {
        assertFicheThrombopathieAllUpdatablePropertiesEquals(
            expectedFicheThrombopathie,
            getPersistedFicheThrombopathie(expectedFicheThrombopathie)
        );
    }
}

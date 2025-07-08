package tn.adhes.hemostase.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.adhes.hemostase.domain.FicheFacteurXIIIAsserts.*;
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
import tn.adhes.hemostase.domain.FicheFacteurXIII;
import tn.adhes.hemostase.domain.enumeration.AccidentsThrombotiquesType;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.MutationType;
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
import tn.adhes.hemostase.domain.enumeration.TypeFaitOuNonFaitEtResultat;
import tn.adhes.hemostase.domain.enumeration.TypeFaitOuNonFaitEtResultat;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.VieSociale;
import tn.adhes.hemostase.repository.FicheFacteurXIIIRepository;
import tn.adhes.hemostase.service.dto.FicheFacteurXIIIDTO;
import tn.adhes.hemostase.service.mapper.FicheFacteurXIIIMapper;

/**
 * Integration tests for the {@link FicheFacteurXIIIResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheFacteurXIIIResourceIT {

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

    private static final DegreParente DEFAULT_DEGRE_PARENTE_CONSANGUINS = DegreParente.TROISIEME_DEGRE;
    private static final DegreParente UPDATED_DEGRE_PARENTE_CONSANGUINS = DegreParente.QUATRIEME_DEGRE;

    private static final OuiNonNP DEFAULT_CAS_SIMILAIRES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CAS_SIMILAIRES = OuiNonNP.NON;

    private static final Integer DEFAULT_NOMBRE_CAS = 1;
    private static final Integer UPDATED_NOMBRE_CAS = 2;

    private static final Integer DEFAULT_CAS_1_NUMERO = 1;
    private static final Integer UPDATED_CAS_1_NUMERO = 2;

    private static final LienParente DEFAULT_LIEN_PARENTE_CAS_1 = LienParente.FRERE;
    private static final LienParente UPDATED_LIEN_PARENTE_CAS_1 = LienParente.COUSIN;

    private static final String DEFAULT_DEGRE_PARENTE_CAS_1 = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CAS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_HEMORRAGIE_CAS_1 = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HEMORRAGIE_CAS_1 = "BBBBBBBBBB";

    private static final Integer DEFAULT_CAS_2_NUMERO = 1;
    private static final Integer UPDATED_CAS_2_NUMERO = 2;

    private static final LienParente DEFAULT_LIEN_PARENTE_CAS_2 = LienParente.FRERE;
    private static final LienParente UPDATED_LIEN_PARENTE_CAS_2 = LienParente.COUSIN;

    private static final String DEFAULT_DEGRE_PARENTE_CAS_2 = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CAS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_HEMORRAGIE_CAS_2 = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HEMORRAGIE_CAS_2 = "BBBBBBBBBB";

    private static final Integer DEFAULT_CAS_3_NUMERO = 1;
    private static final Integer UPDATED_CAS_3_NUMERO = 2;

    private static final LienParente DEFAULT_LIEN_PARENTE_CAS_3 = LienParente.FRERE;
    private static final LienParente UPDATED_LIEN_PARENTE_CAS_3 = LienParente.COUSIN;

    private static final String DEFAULT_DEGRE_PARENTE_CAS_3 = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CAS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_HEMORRAGIE_CAS_3 = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HEMORRAGIE_CAS_3 = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.NON;

    private static final Integer DEFAULT_NOMBRE_DECES = 1;
    private static final Integer UPDATED_NOMBRE_DECES = 2;

    private static final Integer DEFAULT_DECES_1_NUMERO = 1;
    private static final Integer UPDATED_DECES_1_NUMERO = 2;

    private static final LienParente DEFAULT_LIEN_PARENTE_DECES_1 = LienParente.FRERE;
    private static final LienParente UPDATED_LIEN_PARENTE_DECES_1 = LienParente.COUSIN;

    private static final String DEFAULT_CAUSE_DECES_1 = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_DECES_1 = "BBBBBBBBBB";

    private static final Integer DEFAULT_DECES_2_NUMERO = 1;
    private static final Integer UPDATED_DECES_2_NUMERO = 2;

    private static final LienParente DEFAULT_LIEN_PARENTE_DECES_2 = LienParente.FRERE;
    private static final LienParente UPDATED_LIEN_PARENTE_DECES_2 = LienParente.COUSIN;

    private static final String DEFAULT_CAUSE_DECES_2 = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_DECES_2 = "BBBBBBBBBB";

    private static final Integer DEFAULT_DECES_3_NUMERO = 1;
    private static final Integer UPDATED_DECES_3_NUMERO = 2;

    private static final LienParente DEFAULT_LIEN_PARENTE_DECES_3 = LienParente.FRERE;
    private static final LienParente UPDATED_LIEN_PARENTE_DECES_3 = LienParente.COUSIN;

    private static final String DEFAULT_CAUSE_DECES_3 = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_DECES_3 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENQUETE_FAMILIALE = false;
    private static final Boolean UPDATED_ENQUETE_FAMILIALE = true;

    private static final Boolean DEFAULT_DECOUVERTE_FORTUITE = false;
    private static final Boolean UPDATED_DECOUVERTE_FORTUITE = true;

    private static final Boolean DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE = false;
    private static final Boolean UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE = true;

    private static final Boolean DEFAULT_HEMORRAGIE_CHUTE_CORDON = false;
    private static final Boolean UPDATED_HEMORRAGIE_CHUTE_CORDON = true;

    private static final Boolean DEFAULT_METRORAGIE_MENARCHE = false;
    private static final Boolean UPDATED_METRORAGIE_MENARCHE = true;

    private static final Boolean DEFAULT_HEMORRAGIE_CEREBRALE_NN = false;
    private static final Boolean UPDATED_HEMORRAGIE_CEREBRALE_NN = true;

    private static final Boolean DEFAULT_HEMORRAGIE_POST_PARTUM = false;
    private static final Boolean UPDATED_HEMORRAGIE_POST_PARTUM = true;

    private static final String DEFAULT_AUTRES_CIRCONSTANCES = "AAAAAAAAAA";
    private static final String UPDATED_AUTRES_CIRCONSTANCES = "BBBBBBBBBB";

    private static final Integer DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_JOUR = 1;
    private static final Integer UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_JOUR = 2;

    private static final Integer DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_MOIS = 1;
    private static final Integer UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_MOIS = 2;

    private static final Integer DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_ANNEE = 1;
    private static final Integer UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_ANNEE = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_JOUR = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_JOUR = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_MOIS = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_MOIS = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_ANNEE = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_ANNEE = 2;

    private static final Double DEFAULT_HB = 1D;
    private static final Double UPDATED_HB = 2D;

    private static final Double DEFAULT_HT = 1D;
    private static final Double UPDATED_HT = 2D;

    private static final Integer DEFAULT_PLAQUETTES = 1;
    private static final Integer UPDATED_PLAQUETTES = 2;

    private static final Double DEFAULT_TP = 1D;
    private static final Double UPDATED_TP = 2D;

    private static final Double DEFAULT_TP_MT = 1D;
    private static final Double UPDATED_TP_MT = 2D;

    private static final Double DEFAULT_TCA = 1D;
    private static final Double UPDATED_TCA = 2D;

    private static final Double DEFAULT_TCA_MT = 1D;
    private static final Double UPDATED_TCA_MT = 2D;

    private static final Double DEFAULT_FIBRINOGENE = 1D;
    private static final Double UPDATED_FIBRINOGENE = 2D;

    private static final TypeFaitOuNonFaitEtResultat DEFAULT_TS = TypeFaitOuNonFaitEtResultat.NON_FAIT;
    private static final TypeFaitOuNonFaitEtResultat UPDATED_TS = TypeFaitOuNonFaitEtResultat.FAIT_NORMAL;

    private static final TypeFaitOuNonFaitEtResultat DEFAULT_TO_PFA_200 = TypeFaitOuNonFaitEtResultat.NON_FAIT;
    private static final TypeFaitOuNonFaitEtResultat UPDATED_TO_PFA_200 = TypeFaitOuNonFaitEtResultat.FAIT_NORMAL;

    private static final Double DEFAULT_COL_EPI = 1D;
    private static final Double UPDATED_COL_EPI = 2D;

    private static final Double DEFAULT_COL_ADP = 1D;
    private static final Double UPDATED_COL_ADP = 2D;

    private static final Double DEFAULT_FACTEUR_XIII = 1D;
    private static final Double UPDATED_FACTEUR_XIII = 2D;

    private static final Boolean DEFAULT_TECHNIQUE_CHROMOGENIQUE = false;
    private static final Boolean UPDATED_TECHNIQUE_CHROMOGENIQUE = true;

    private static final Double DEFAULT_TAUX_CHROMOGENIQUE = 1D;
    private static final Double UPDATED_TAUX_CHROMOGENIQUE = 2D;

    private static final Boolean DEFAULT_TECHNIQUE_SOLUBILITE_CAILLOT = false;
    private static final Boolean UPDATED_TECHNIQUE_SOLUBILITE_CAILLOT = true;

    private static final Double DEFAULT_TAUX_SOLUBILITE_CAILLOT = 1D;
    private static final Double UPDATED_TAUX_SOLUBILITE_CAILLOT = 2D;

    private static final Boolean DEFAULT_CONFIRMATION_MOLECULAIRE = false;
    private static final Boolean UPDATED_CONFIRMATION_MOLECULAIRE = true;

    private static final Boolean DEFAULT_MUTATION_PRESENTE = false;
    private static final Boolean UPDATED_MUTATION_PRESENTE = true;

    private static final String DEFAULT_MUTATION_NOM = "AAAAAAAAAA";
    private static final String UPDATED_MUTATION_NOM = "BBBBBBBBBB";

    private static final MutationType DEFAULT_MUTATION_TYPE = MutationType.HETEROZYGOTE;
    private static final MutationType UPDATED_MUTATION_TYPE = MutationType.HOMOZYGOTE;

    private static final String DEFAULT_MUTATION_2_NOM = "AAAAAAAAAA";
    private static final String UPDATED_MUTATION_2_NOM = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_SAIGNEMENT_CHUTE_CORDON = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SAIGNEMENT_CHUTE_CORDON = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CICATRICES_KELOIDES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CICATRICES_KELOIDES = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_RETARD_CICATRISATION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_RETARD_CICATRISATION = OuiNonNP.NON;

    private static final Boolean DEFAULT_EPISTAXIS = false;
    private static final Boolean UPDATED_EPISTAXIS = true;

    private static final Integer DEFAULT_FREQUENCE_EPISTAXIS = 1;
    private static final Integer UPDATED_FREQUENCE_EPISTAXIS = 2;

    private static final Boolean DEFAULT_GINGIVORRAGIES = false;
    private static final Boolean UPDATED_GINGIVORRAGIES = true;

    private static final TypeManifestation DEFAULT_TYPE_GINGIVORRAGIES = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_GINGIVORRAGIES = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_GINGIVORRAGIES = 1;
    private static final Integer UPDATED_FREQUENCE_GINGIVORRAGIES = 2;

    private static final Boolean DEFAULT_ECCHYMOSE = false;
    private static final Boolean UPDATED_ECCHYMOSE = true;

    private static final TypeManifestation DEFAULT_TYPE_ECCHYMOSE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_ECCHYMOSE = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_ECCHYMOSE = 1;
    private static final Integer UPDATED_FREQUENCE_ECCHYMOSE = 2;

    private static final Boolean DEFAULT_MENORRAGIE = false;
    private static final Boolean UPDATED_MENORRAGIE = true;

    private static final Integer DEFAULT_FREQUENCE_MENORRAGIE = 1;
    private static final Integer UPDATED_FREQUENCE_MENORRAGIE = 2;

    private static final Boolean DEFAULT_HEMATEMESE = false;
    private static final Boolean UPDATED_HEMATEMESE = true;

    private static final Integer DEFAULT_FREQUENCE_HEMATEMESE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMATEMESE = 2;

    private static final Boolean DEFAULT_RECTORRAGIE = false;
    private static final Boolean UPDATED_RECTORRAGIE = true;

    private static final Integer DEFAULT_FREQUENCE_RECTORRAGIE = 1;
    private static final Integer UPDATED_FREQUENCE_RECTORRAGIE = 2;

    private static final Boolean DEFAULT_HEMATURIE = false;
    private static final Boolean UPDATED_HEMATURIE = true;

    private static final Integer DEFAULT_FREQUENCE_HEMATURIE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMATURIE = 2;

    private static final Boolean DEFAULT_HEMATOME_SOUS_CUTANE = false;
    private static final Boolean UPDATED_HEMATOME_SOUS_CUTANE = true;

    private static final TypeManifestation DEFAULT_TYPE_HEMATOME_SOUS_CUTANE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_HEMATOME_SOUS_CUTANE = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_HEMATOME_SOUS_CUTANE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE = 2;

    private static final Boolean DEFAULT_HEMATOME_MUSCULAIRE = false;
    private static final Boolean UPDATED_HEMATOME_MUSCULAIRE = true;

    private static final String DEFAULT_SIEGE_HEMATOME_MUSCULAIRE = "AAAAAAAAAA";
    private static final String UPDATED_SIEGE_HEMATOME_MUSCULAIRE = "BBBBBBBBBB";

    private static final TypeManifestation DEFAULT_TYPE_HEMATOME_MUSCULAIRE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_HEMATOME_MUSCULAIRE = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_HEMATOME_MUSCULAIRE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMATOME_MUSCULAIRE = 2;

    private static final Boolean DEFAULT_HEMARTHROSE = false;
    private static final Boolean UPDATED_HEMARTHROSE = true;

    private static final TypeManifestation DEFAULT_TYPE_HEMARTHROSE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_HEMARTHROSE = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_HEMARTHROSE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMARTHROSE = 2;

    private static final Boolean DEFAULT_SAIGNEMENT_SNC = false;
    private static final Boolean UPDATED_SAIGNEMENT_SNC = true;

    private static final TypeManifestation DEFAULT_TYPE_SAIGNEMENT_SNC = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_SAIGNEMENT_SNC = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_SAIGNEMENT_SNC = 1;
    private static final Integer UPDATED_FREQUENCE_SAIGNEMENT_SNC = 2;

    private static final Boolean DEFAULT_RUPTURE_KYSTES_HEMORRAGIQUES = false;
    private static final Boolean UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES = true;

    private static final Boolean DEFAULT_HEMORRAGIES_POST_CHIRURGICALES = false;
    private static final Boolean UPDATED_HEMORRAGIES_POST_CHIRURGICALES = true;

    private static final Boolean DEFAULT_HEMOPERITONE = false;
    private static final Boolean UPDATED_HEMOPERITONE = true;

    private static final Boolean DEFAULT_ACCIDENTS_THROMBOTIQUES = false;
    private static final Boolean UPDATED_ACCIDENTS_THROMBOTIQUES = true;

    private static final AccidentsThrombotiquesType DEFAULT_TYPE_ACCIDENTS_THROMBOTIQUES = AccidentsThrombotiquesType.VEINEUX;
    private static final AccidentsThrombotiquesType UPDATED_TYPE_ACCIDENTS_THROMBOTIQUES = AccidentsThrombotiquesType.ARTERIEL;

    private static final OuiNonNP DEFAULT_HEMORRAGIE_GRAVE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIE_GRAVE = OuiNonNP.NON;

    private static final Boolean DEFAULT_GRAVE_DEGLOBULISATION = false;
    private static final Boolean UPDATED_GRAVE_DEGLOBULISATION = true;

    private static final Boolean DEFAULT_GRAVE_ETAT_DE_CHOC = false;
    private static final Boolean UPDATED_GRAVE_ETAT_DE_CHOC = true;

    private static final Boolean DEFAULT_GRAVE_PAR_LOCALISATION = false;
    private static final Boolean UPDATED_GRAVE_PAR_LOCALISATION = true;

    private static final String DEFAULT_SIEGE_GRAVITE_LOCALISATION = "AAAAAAAAAA";
    private static final String UPDATED_SIEGE_GRAVITE_LOCALISATION = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CHIRURGIE_PATIENT_PREPARE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CHIRURGIE_PATIENT_PREPARE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CHIRURGIE_SUITE_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CHIRURGIE_SUITE_HEMORRAGIQUE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CIRCONCISION_PATIENT_PREPARE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION_PATIENT_PREPARE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CIRCONCISION_SUITE_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION_SUITE_HEMORRAGIQUE = OuiNonNP.NON;

    private static final Integer DEFAULT_AGE_CIRCONCISION = 1;
    private static final Integer UPDATED_AGE_CIRCONCISION = 2;

    private static final OuiNonNP DEFAULT_CONCENTRE_FACTEUR_XIII = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CONCENTRE_FACTEUR_XIII = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_PLASMA_FRAIS_CONGELE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PLASMA_FRAIS_CONGELE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CRYOPRECIPITE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CRYOPRECIPITE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CULOTS_PLAQUETTAIRES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CULOTS_PLAQUETTAIRES = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_DICYNONE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_DICYNONE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_EXACYL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_EXACYL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_MARTIAL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_MARTIAL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_OESTROPROGESTATIF = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_OESTROPROGESTATIF = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRANSFUSION_CGR = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRANSFUSION_CGR = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_INHIBITEURS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INHIBITEURS = OuiNonNP.NON;

    private static final Boolean DEFAULT_INHIBITEUR_NEUTRALISANT = false;
    private static final Boolean UPDATED_INHIBITEUR_NEUTRALISANT = true;

    private static final Double DEFAULT_TITRE_INHIBITEUR_NEUTRALISANT = 1D;
    private static final Double UPDATED_TITRE_INHIBITEUR_NEUTRALISANT = 2D;

    private static final Boolean DEFAULT_INHIBITEUR_NON_NEUTRALISANT = false;
    private static final Boolean UPDATED_INHIBITEUR_NON_NEUTRALISANT = true;

    private static final OuiNonNP DEFAULT_INFECTIONS_VIRALES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INFECTIONS_VIRALES = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_HEPATITE_B = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEPATITE_B = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_HEPATITE_C = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEPATITE_C = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_VIH = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_VIH = OuiNonNP.NON;

    private static final Boolean DEFAULT_THROMBOSE = false;
    private static final Boolean UPDATED_THROMBOSE = true;

    private static final Boolean DEFAULT_REACTION_ALLERGIQUE = false;
    private static final Boolean UPDATED_REACTION_ALLERGIQUE = true;

    private static final Boolean DEFAULT_ALLOIMMUNISATION = false;
    private static final Boolean UPDATED_ALLOIMMUNISATION = true;

    private static final String DEFAULT_AUTRES_COMPLICATIONS = "AAAAAAAAAA";
    private static final String UPDATED_AUTRES_COMPLICATIONS = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_ADHERENCE_THERAPEUTIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ADHERENCE_THERAPEUTIQUE = OuiNonNP.NON;

    private static final String DEFAULT_CAUSE_NON_ADHERENCE = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_NON_ADHERENCE = "BBBBBBBBBB";

    private static final VieSociale DEFAULT_VIE_SOCIALE = VieSociale.PRESCOLAIRE;
    private static final VieSociale UPDATED_VIE_SOCIALE = VieSociale.SCOLARISE;

    private static final OuiNonNP DEFAULT_MARIE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_MARIE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ENFANTS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ENFANTS = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ACTIVITE_PHYSIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ACTIVITE_PHYSIQUE = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_ACTIVITE_PHYSIQUE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ACTIVITE_PHYSIQUE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_SEQUELLES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SEQUELLES = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_SEQUELLES = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_SEQUELLES = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_PATIENT_DECEDE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PATIENT_DECEDE = OuiNonNP.NON;

    private static final LocalDate DEFAULT_DATE_DECES = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DECES = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGE_DECES = 1;
    private static final Integer UPDATED_AGE_DECES = 2;

    private static final Boolean DEFAULT_CAUSE_DECES_SNC = false;
    private static final Boolean UPDATED_CAUSE_DECES_SNC = true;

    private static final Boolean DEFAULT_CAUSE_DECES_AUTRE_HEMORRAGIE = false;
    private static final Boolean UPDATED_CAUSE_DECES_AUTRE_HEMORRAGIE = true;

    private static final Boolean DEFAULT_CAUSE_DECES_VIH = false;
    private static final Boolean UPDATED_CAUSE_DECES_VIH = true;

    private static final Boolean DEFAULT_CAUSE_DECES_AUTRE_INFECTION = false;
    private static final Boolean UPDATED_CAUSE_DECES_AUTRE_INFECTION = true;

    private static final Boolean DEFAULT_CAUSE_DECES_THROMBOEMBOLIQUE = false;
    private static final Boolean UPDATED_CAUSE_DECES_THROMBOEMBOLIQUE = true;

    private static final Boolean DEFAULT_CAUSE_DECES_CANCER = false;
    private static final Boolean UPDATED_CAUSE_DECES_CANCER = true;

    private static final Boolean DEFAULT_CAUSE_DECES_COVID_19 = false;
    private static final Boolean UPDATED_CAUSE_DECES_COVID_19 = true;

    private static final Boolean DEFAULT_CAUSE_DECES_INCONNUE = false;
    private static final Boolean UPDATED_CAUSE_DECES_INCONNUE = true;

    private static final String DEFAULT_AUTRE_CAUSE_DECES = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_CAUSE_DECES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fiche-facteur-xiiis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FicheFacteurXIIIRepository ficheFacteurXIIIRepository;

    @Autowired
    private FicheFacteurXIIIMapper ficheFacteurXIIIMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheFacteurXIIIMockMvc;

    private FicheFacteurXIII ficheFacteurXIII;

    private FicheFacteurXIII insertedFicheFacteurXIII;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheFacteurXIII createEntity() {
        return new FicheFacteurXIII()
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
            .cas1Numero(DEFAULT_CAS_1_NUMERO)
            .lienParenteCas1(DEFAULT_LIEN_PARENTE_CAS_1)
            .degreParenteCas1(DEFAULT_DEGRE_PARENTE_CAS_1)
            .typeHemorragieCas1(DEFAULT_TYPE_HEMORRAGIE_CAS_1)
            .cas2Numero(DEFAULT_CAS_2_NUMERO)
            .lienParenteCas2(DEFAULT_LIEN_PARENTE_CAS_2)
            .degreParenteCas2(DEFAULT_DEGRE_PARENTE_CAS_2)
            .typeHemorragieCas2(DEFAULT_TYPE_HEMORRAGIE_CAS_2)
            .cas3Numero(DEFAULT_CAS_3_NUMERO)
            .lienParenteCas3(DEFAULT_LIEN_PARENTE_CAS_3)
            .degreParenteCas3(DEFAULT_DEGRE_PARENTE_CAS_3)
            .typeHemorragieCas3(DEFAULT_TYPE_HEMORRAGIE_CAS_3)
            .decesSyndromeHemorragique(DEFAULT_DECES_SYNDROME_HEMORRAGIQUE)
            .nombreDeces(DEFAULT_NOMBRE_DECES)
            .deces1Numero(DEFAULT_DECES_1_NUMERO)
            .lienParenteDeces1(DEFAULT_LIEN_PARENTE_DECES_1)
            .causeDeces1(DEFAULT_CAUSE_DECES_1)
            .deces2Numero(DEFAULT_DECES_2_NUMERO)
            .lienParenteDeces2(DEFAULT_LIEN_PARENTE_DECES_2)
            .causeDeces2(DEFAULT_CAUSE_DECES_2)
            .deces3Numero(DEFAULT_DECES_3_NUMERO)
            .lienParenteDeces3(DEFAULT_LIEN_PARENTE_DECES_3)
            .causeDeces3(DEFAULT_CAUSE_DECES_3)
            .enqueteFamiliale(DEFAULT_ENQUETE_FAMILIALE)
            .decouverteFortuite(DEFAULT_DECOUVERTE_FORTUITE)
            .explorationSyndromeHemorragique(DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .hemorragieChuteCordon(DEFAULT_HEMORRAGIE_CHUTE_CORDON)
            .metroragieMenarche(DEFAULT_METRORAGIE_MENARCHE)
            .hemorragieCerebraleNN(DEFAULT_HEMORRAGIE_CEREBRALE_NN)
            .hemorragiePostPartum(DEFAULT_HEMORRAGIE_POST_PARTUM)
            .autresCirconstances(DEFAULT_AUTRES_CIRCONSTANCES)
            .datePremierSigneCliniqueEvocateurJour(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_JOUR)
            .datePremierSigneCliniqueEvocateurMois(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_MOIS)
            .datePremierSigneCliniqueEvocateurAnnee(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_ANNEE)
            .ageDiagnosticJour(DEFAULT_AGE_DIAGNOSTIC_JOUR)
            .ageDiagnosticMois(DEFAULT_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnee(DEFAULT_AGE_DIAGNOSTIC_ANNEE)
            .hb(DEFAULT_HB)
            .ht(DEFAULT_HT)
            .plaquettes(DEFAULT_PLAQUETTES)
            .tp(DEFAULT_TP)
            .tpMT(DEFAULT_TP_MT)
            .tca(DEFAULT_TCA)
            .tcaMT(DEFAULT_TCA_MT)
            .fibrinogene(DEFAULT_FIBRINOGENE)
            .ts(DEFAULT_TS)
            .toPfa200(DEFAULT_TO_PFA_200)
            .colEpi(DEFAULT_COL_EPI)
            .colAdp(DEFAULT_COL_ADP)
            .facteurXIII(DEFAULT_FACTEUR_XIII)
            .techniqueChromogenique(DEFAULT_TECHNIQUE_CHROMOGENIQUE)
            .tauxChromogenique(DEFAULT_TAUX_CHROMOGENIQUE)
            .techniqueSolubiliteCaillot(DEFAULT_TECHNIQUE_SOLUBILITE_CAILLOT)
            .tauxSolubiliteCaillot(DEFAULT_TAUX_SOLUBILITE_CAILLOT)
            .confirmationMoleculaire(DEFAULT_CONFIRMATION_MOLECULAIRE)
            .mutationPresente(DEFAULT_MUTATION_PRESENTE)
            .mutationNom(DEFAULT_MUTATION_NOM)
            .mutationType(DEFAULT_MUTATION_TYPE)
            .mutation2Nom(DEFAULT_MUTATION_2_NOM)
            .saignementChuteCordon(DEFAULT_SAIGNEMENT_CHUTE_CORDON)
            .cicatricesKeloides(DEFAULT_CICATRICES_KELOIDES)
            .retardCicatrisation(DEFAULT_RETARD_CICATRISATION)
            .epistaxis(DEFAULT_EPISTAXIS)
            .frequenceEpistaxis(DEFAULT_FREQUENCE_EPISTAXIS)
            .gingivorragies(DEFAULT_GINGIVORRAGIES)
            .typeGingivorragies(DEFAULT_TYPE_GINGIVORRAGIES)
            .frequenceGingivorragies(DEFAULT_FREQUENCE_GINGIVORRAGIES)
            .ecchymose(DEFAULT_ECCHYMOSE)
            .typeEcchymose(DEFAULT_TYPE_ECCHYMOSE)
            .frequenceEcchymose(DEFAULT_FREQUENCE_ECCHYMOSE)
            .menorragie(DEFAULT_MENORRAGIE)
            .frequenceMenorragie(DEFAULT_FREQUENCE_MENORRAGIE)
            .hematemese(DEFAULT_HEMATEMESE)
            .frequenceHematemese(DEFAULT_FREQUENCE_HEMATEMESE)
            .rectorragie(DEFAULT_RECTORRAGIE)
            .frequenceRectorragie(DEFAULT_FREQUENCE_RECTORRAGIE)
            .hematurie(DEFAULT_HEMATURIE)
            .frequenceHematurie(DEFAULT_FREQUENCE_HEMATURIE)
            .hematomeSousCutane(DEFAULT_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(DEFAULT_TYPE_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(DEFAULT_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(DEFAULT_HEMATOME_MUSCULAIRE)
            .siegeHematomeMusculaire(DEFAULT_SIEGE_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(DEFAULT_TYPE_HEMATOME_MUSCULAIRE)
            .frequenceHematomeMusculaire(DEFAULT_FREQUENCE_HEMATOME_MUSCULAIRE)
            .hemarthrose(DEFAULT_HEMARTHROSE)
            .typeHemarthrose(DEFAULT_TYPE_HEMARTHROSE)
            .frequenceHemarthrose(DEFAULT_FREQUENCE_HEMARTHROSE)
            .saignementSNC(DEFAULT_SAIGNEMENT_SNC)
            .typeSaignementSNC(DEFAULT_TYPE_SAIGNEMENT_SNC)
            .frequenceSaignementSNC(DEFAULT_FREQUENCE_SAIGNEMENT_SNC)
            .ruptureKystesHemorragiques(DEFAULT_RUPTURE_KYSTES_HEMORRAGIQUES)
            .hemorragiesPostChirurgicales(DEFAULT_HEMORRAGIES_POST_CHIRURGICALES)
            .hemoperitone(DEFAULT_HEMOPERITONE)
            .accidentsThrombotiques(DEFAULT_ACCIDENTS_THROMBOTIQUES)
            .typeAccidentsThrombotiques(DEFAULT_TYPE_ACCIDENTS_THROMBOTIQUES)
            .hemorragieGrave(DEFAULT_HEMORRAGIE_GRAVE)
            .graveDeglobulisation(DEFAULT_GRAVE_DEGLOBULISATION)
            .graveEtatDeChoc(DEFAULT_GRAVE_ETAT_DE_CHOC)
            .graveParLocalisation(DEFAULT_GRAVE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(DEFAULT_SIEGE_GRAVITE_LOCALISATION)
            .chirurgiePatientPrepare(DEFAULT_CHIRURGIE_PATIENT_PREPARE)
            .chirurgieSuiteHemorragique(DEFAULT_CHIRURGIE_SUITE_HEMORRAGIQUE)
            .circoncisionPatientPrepare(DEFAULT_CIRCONCISION_PATIENT_PREPARE)
            .circoncisionSuiteHemorragique(DEFAULT_CIRCONCISION_SUITE_HEMORRAGIQUE)
            .ageCirconcision(DEFAULT_AGE_CIRCONCISION)
            .concentreFacteurXIII(DEFAULT_CONCENTRE_FACTEUR_XIII)
            .plasmaFraisCongele(DEFAULT_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(DEFAULT_CRYOPRECIPITE)
            .culotsPlaquettaires(DEFAULT_CULOTS_PLAQUETTAIRES)
            .dicynone(DEFAULT_DICYNONE)
            .exacyl(DEFAULT_EXACYL)
            .traitementMartial(DEFAULT_TRAITEMENT_MARTIAL)
            .traitementOestroprogestatif(DEFAULT_TRAITEMENT_OESTROPROGESTATIF)
            .transfusionCGR(DEFAULT_TRANSFUSION_CGR)
            .inhibiteurs(DEFAULT_INHIBITEURS)
            .inhibiteurNeutralisant(DEFAULT_INHIBITEUR_NEUTRALISANT)
            .titreInhibiteurNeutralisant(DEFAULT_TITRE_INHIBITEUR_NEUTRALISANT)
            .inhibiteurNonNeutralisant(DEFAULT_INHIBITEUR_NON_NEUTRALISANT)
            .infectionsVirales(DEFAULT_INFECTIONS_VIRALES)
            .hepatiteB(DEFAULT_HEPATITE_B)
            .hepatiteC(DEFAULT_HEPATITE_C)
            .vih(DEFAULT_VIH)
            .thrombose(DEFAULT_THROMBOSE)
            .reactionAllergique(DEFAULT_REACTION_ALLERGIQUE)
            .alloimmunisation(DEFAULT_ALLOIMMUNISATION)
            .autresComplications(DEFAULT_AUTRES_COMPLICATIONS)
            .adherenceTherapeutique(DEFAULT_ADHERENCE_THERAPEUTIQUE)
            .causeNonAdherence(DEFAULT_CAUSE_NON_ADHERENCE)
            .vieSociale(DEFAULT_VIE_SOCIALE)
            .marie(DEFAULT_MARIE)
            .enfants(DEFAULT_ENFANTS)
            .activitePhysique(DEFAULT_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(DEFAULT_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(DEFAULT_SEQUELLES)
            .typeSequelles(DEFAULT_TYPE_SEQUELLES)
            .patientDecede(DEFAULT_PATIENT_DECEDE)
            .dateDeces(DEFAULT_DATE_DECES)
            .ageDeces(DEFAULT_AGE_DECES)
            .causeDecesSNC(DEFAULT_CAUSE_DECES_SNC)
            .causeDecesAutreHemorragie(DEFAULT_CAUSE_DECES_AUTRE_HEMORRAGIE)
            .causeDecesVIH(DEFAULT_CAUSE_DECES_VIH)
            .causeDecesAutreInfection(DEFAULT_CAUSE_DECES_AUTRE_INFECTION)
            .causeDecesThromboembolique(DEFAULT_CAUSE_DECES_THROMBOEMBOLIQUE)
            .causeDecesCancer(DEFAULT_CAUSE_DECES_CANCER)
            .causeDecesCovid19(DEFAULT_CAUSE_DECES_COVID_19)
            .causeDecesInconnue(DEFAULT_CAUSE_DECES_INCONNUE)
            .autreCauseDeces(DEFAULT_AUTRE_CAUSE_DECES);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheFacteurXIII createUpdatedEntity() {
        return new FicheFacteurXIII()
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
            .cas1Numero(UPDATED_CAS_1_NUMERO)
            .lienParenteCas1(UPDATED_LIEN_PARENTE_CAS_1)
            .degreParenteCas1(UPDATED_DEGRE_PARENTE_CAS_1)
            .typeHemorragieCas1(UPDATED_TYPE_HEMORRAGIE_CAS_1)
            .cas2Numero(UPDATED_CAS_2_NUMERO)
            .lienParenteCas2(UPDATED_LIEN_PARENTE_CAS_2)
            .degreParenteCas2(UPDATED_DEGRE_PARENTE_CAS_2)
            .typeHemorragieCas2(UPDATED_TYPE_HEMORRAGIE_CAS_2)
            .cas3Numero(UPDATED_CAS_3_NUMERO)
            .lienParenteCas3(UPDATED_LIEN_PARENTE_CAS_3)
            .degreParenteCas3(UPDATED_DEGRE_PARENTE_CAS_3)
            .typeHemorragieCas3(UPDATED_TYPE_HEMORRAGIE_CAS_3)
            .decesSyndromeHemorragique(UPDATED_DECES_SYNDROME_HEMORRAGIQUE)
            .nombreDeces(UPDATED_NOMBRE_DECES)
            .deces1Numero(UPDATED_DECES_1_NUMERO)
            .lienParenteDeces1(UPDATED_LIEN_PARENTE_DECES_1)
            .causeDeces1(UPDATED_CAUSE_DECES_1)
            .deces2Numero(UPDATED_DECES_2_NUMERO)
            .lienParenteDeces2(UPDATED_LIEN_PARENTE_DECES_2)
            .causeDeces2(UPDATED_CAUSE_DECES_2)
            .deces3Numero(UPDATED_DECES_3_NUMERO)
            .lienParenteDeces3(UPDATED_LIEN_PARENTE_DECES_3)
            .causeDeces3(UPDATED_CAUSE_DECES_3)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .hemorragieChuteCordon(UPDATED_HEMORRAGIE_CHUTE_CORDON)
            .metroragieMenarche(UPDATED_METRORAGIE_MENARCHE)
            .hemorragieCerebraleNN(UPDATED_HEMORRAGIE_CEREBRALE_NN)
            .hemorragiePostPartum(UPDATED_HEMORRAGIE_POST_PARTUM)
            .autresCirconstances(UPDATED_AUTRES_CIRCONSTANCES)
            .datePremierSigneCliniqueEvocateurJour(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_JOUR)
            .datePremierSigneCliniqueEvocateurMois(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_MOIS)
            .datePremierSigneCliniqueEvocateurAnnee(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_ANNEE)
            .ageDiagnosticJour(UPDATED_AGE_DIAGNOSTIC_JOUR)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnee(UPDATED_AGE_DIAGNOSTIC_ANNEE)
            .hb(UPDATED_HB)
            .ht(UPDATED_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .tpMT(UPDATED_TP_MT)
            .tca(UPDATED_TCA)
            .tcaMT(UPDATED_TCA_MT)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .ts(UPDATED_TS)
            .toPfa200(UPDATED_TO_PFA_200)
            .colEpi(UPDATED_COL_EPI)
            .colAdp(UPDATED_COL_ADP)
            .facteurXIII(UPDATED_FACTEUR_XIII)
            .techniqueChromogenique(UPDATED_TECHNIQUE_CHROMOGENIQUE)
            .tauxChromogenique(UPDATED_TAUX_CHROMOGENIQUE)
            .techniqueSolubiliteCaillot(UPDATED_TECHNIQUE_SOLUBILITE_CAILLOT)
            .tauxSolubiliteCaillot(UPDATED_TAUX_SOLUBILITE_CAILLOT)
            .confirmationMoleculaire(UPDATED_CONFIRMATION_MOLECULAIRE)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationType(UPDATED_MUTATION_TYPE)
            .mutation2Nom(UPDATED_MUTATION_2_NOM)
            .saignementChuteCordon(UPDATED_SAIGNEMENT_CHUTE_CORDON)
            .cicatricesKeloides(UPDATED_CICATRICES_KELOIDES)
            .retardCicatrisation(UPDATED_RETARD_CICATRISATION)
            .epistaxis(UPDATED_EPISTAXIS)
            .frequenceEpistaxis(UPDATED_FREQUENCE_EPISTAXIS)
            .gingivorragies(UPDATED_GINGIVORRAGIES)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .frequenceGingivorragies(UPDATED_FREQUENCE_GINGIVORRAGIES)
            .ecchymose(UPDATED_ECCHYMOSE)
            .typeEcchymose(UPDATED_TYPE_ECCHYMOSE)
            .frequenceEcchymose(UPDATED_FREQUENCE_ECCHYMOSE)
            .menorragie(UPDATED_MENORRAGIE)
            .frequenceMenorragie(UPDATED_FREQUENCE_MENORRAGIE)
            .hematemese(UPDATED_HEMATEMESE)
            .frequenceHematemese(UPDATED_FREQUENCE_HEMATEMESE)
            .rectorragie(UPDATED_RECTORRAGIE)
            .frequenceRectorragie(UPDATED_FREQUENCE_RECTORRAGIE)
            .hematurie(UPDATED_HEMATURIE)
            .frequenceHematurie(UPDATED_FREQUENCE_HEMATURIE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(UPDATED_TYPE_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .siegeHematomeMusculaire(UPDATED_SIEGE_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(UPDATED_TYPE_HEMATOME_MUSCULAIRE)
            .frequenceHematomeMusculaire(UPDATED_FREQUENCE_HEMATOME_MUSCULAIRE)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .typeHemarthrose(UPDATED_TYPE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .typeSaignementSNC(UPDATED_TYPE_SAIGNEMENT_SNC)
            .frequenceSaignementSNC(UPDATED_FREQUENCE_SAIGNEMENT_SNC)
            .ruptureKystesHemorragiques(UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES)
            .hemorragiesPostChirurgicales(UPDATED_HEMORRAGIES_POST_CHIRURGICALES)
            .hemoperitone(UPDATED_HEMOPERITONE)
            .accidentsThrombotiques(UPDATED_ACCIDENTS_THROMBOTIQUES)
            .typeAccidentsThrombotiques(UPDATED_TYPE_ACCIDENTS_THROMBOTIQUES)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .graveDeglobulisation(UPDATED_GRAVE_DEGLOBULISATION)
            .graveEtatDeChoc(UPDATED_GRAVE_ETAT_DE_CHOC)
            .graveParLocalisation(UPDATED_GRAVE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .chirurgiePatientPrepare(UPDATED_CHIRURGIE_PATIENT_PREPARE)
            .chirurgieSuiteHemorragique(UPDATED_CHIRURGIE_SUITE_HEMORRAGIQUE)
            .circoncisionPatientPrepare(UPDATED_CIRCONCISION_PATIENT_PREPARE)
            .circoncisionSuiteHemorragique(UPDATED_CIRCONCISION_SUITE_HEMORRAGIQUE)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .concentreFacteurXIII(UPDATED_CONCENTRE_FACTEUR_XIII)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .culotsPlaquettaires(UPDATED_CULOTS_PLAQUETTAIRES)
            .dicynone(UPDATED_DICYNONE)
            .exacyl(UPDATED_EXACYL)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .transfusionCGR(UPDATED_TRANSFUSION_CGR)
            .inhibiteurs(UPDATED_INHIBITEURS)
            .inhibiteurNeutralisant(UPDATED_INHIBITEUR_NEUTRALISANT)
            .titreInhibiteurNeutralisant(UPDATED_TITRE_INHIBITEUR_NEUTRALISANT)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .infectionsVirales(UPDATED_INFECTIONS_VIRALES)
            .hepatiteB(UPDATED_HEPATITE_B)
            .hepatiteC(UPDATED_HEPATITE_C)
            .vih(UPDATED_VIH)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .alloimmunisation(UPDATED_ALLOIMMUNISATION)
            .autresComplications(UPDATED_AUTRES_COMPLICATIONS)
            .adherenceTherapeutique(UPDATED_ADHERENCE_THERAPEUTIQUE)
            .causeNonAdherence(UPDATED_CAUSE_NON_ADHERENCE)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .causeDecesSNC(UPDATED_CAUSE_DECES_SNC)
            .causeDecesAutreHemorragie(UPDATED_CAUSE_DECES_AUTRE_HEMORRAGIE)
            .causeDecesVIH(UPDATED_CAUSE_DECES_VIH)
            .causeDecesAutreInfection(UPDATED_CAUSE_DECES_AUTRE_INFECTION)
            .causeDecesThromboembolique(UPDATED_CAUSE_DECES_THROMBOEMBOLIQUE)
            .causeDecesCancer(UPDATED_CAUSE_DECES_CANCER)
            .causeDecesCovid19(UPDATED_CAUSE_DECES_COVID_19)
            .causeDecesInconnue(UPDATED_CAUSE_DECES_INCONNUE)
            .autreCauseDeces(UPDATED_AUTRE_CAUSE_DECES);
    }

    @BeforeEach
    public void initTest() {
        ficheFacteurXIII = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedFicheFacteurXIII != null) {
            ficheFacteurXIIIRepository.delete(insertedFicheFacteurXIII);
            insertedFicheFacteurXIII = null;
        }
    }

    @Test
    @Transactional
    void createFicheFacteurXIII() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FicheFacteurXIII
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);
        var returnedFicheFacteurXIIIDTO = om.readValue(
            restFicheFacteurXIIIMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIIIDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FicheFacteurXIIIDTO.class
        );

        // Validate the FicheFacteurXIII in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFicheFacteurXIII = ficheFacteurXIIIMapper.toEntity(returnedFicheFacteurXIIIDTO);
        assertFicheFacteurXIIIUpdatableFieldsEquals(returnedFicheFacteurXIII, getPersistedFicheFacteurXIII(returnedFicheFacteurXIII));

        insertedFicheFacteurXIII = returnedFicheFacteurXIII;
    }

    @Test
    @Transactional
    void createFicheFacteurXIIIWithExistingId() throws Exception {
        // Create the FicheFacteurXIII with an existing ID
        ficheFacteurXIII.setId(1L);
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheFacteurXIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIIIDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXIII in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEtatCivilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXIII.setEtatCivil(null);

        // Create the FicheFacteurXIII, which fails.
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        restFicheFacteurXIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkServiceCliniqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXIII.setServiceClinique(null);

        // Create the FicheFacteurXIII, which fails.
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        restFicheFacteurXIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCouvertureSocialeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXIII.setCouvertureSociale(null);

        // Create the FicheFacteurXIII, which fails.
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        restFicheFacteurXIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSexeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXIII.setSexe(null);

        // Create the FicheFacteurXIII, which fails.
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        restFicheFacteurXIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiagnosticIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXIII.setDiagnostic(null);

        // Create the FicheFacteurXIII, which fails.
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        restFicheFacteurXIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFicheFacteurXIIIS() throws Exception {
        // Initialize the database
        insertedFicheFacteurXIII = ficheFacteurXIIIRepository.saveAndFlush(ficheFacteurXIII);

        // Get all the ficheFacteurXIIIList
        restFicheFacteurXIIIMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheFacteurXIII.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].degreParenteConsanguins").value(hasItem(DEFAULT_DEGRE_PARENTE_CONSANGUINS.toString())))
            .andExpect(jsonPath("$.[*].casSimilaires").value(hasItem(DEFAULT_CAS_SIMILAIRES.toString())))
            .andExpect(jsonPath("$.[*].nombreCas").value(hasItem(DEFAULT_NOMBRE_CAS)))
            .andExpect(jsonPath("$.[*].cas1Numero").value(hasItem(DEFAULT_CAS_1_NUMERO)))
            .andExpect(jsonPath("$.[*].lienParenteCas1").value(hasItem(DEFAULT_LIEN_PARENTE_CAS_1.toString())))
            .andExpect(jsonPath("$.[*].degreParenteCas1").value(hasItem(DEFAULT_DEGRE_PARENTE_CAS_1)))
            .andExpect(jsonPath("$.[*].typeHemorragieCas1").value(hasItem(DEFAULT_TYPE_HEMORRAGIE_CAS_1)))
            .andExpect(jsonPath("$.[*].cas2Numero").value(hasItem(DEFAULT_CAS_2_NUMERO)))
            .andExpect(jsonPath("$.[*].lienParenteCas2").value(hasItem(DEFAULT_LIEN_PARENTE_CAS_2.toString())))
            .andExpect(jsonPath("$.[*].degreParenteCas2").value(hasItem(DEFAULT_DEGRE_PARENTE_CAS_2)))
            .andExpect(jsonPath("$.[*].typeHemorragieCas2").value(hasItem(DEFAULT_TYPE_HEMORRAGIE_CAS_2)))
            .andExpect(jsonPath("$.[*].cas3Numero").value(hasItem(DEFAULT_CAS_3_NUMERO)))
            .andExpect(jsonPath("$.[*].lienParenteCas3").value(hasItem(DEFAULT_LIEN_PARENTE_CAS_3.toString())))
            .andExpect(jsonPath("$.[*].degreParenteCas3").value(hasItem(DEFAULT_DEGRE_PARENTE_CAS_3)))
            .andExpect(jsonPath("$.[*].typeHemorragieCas3").value(hasItem(DEFAULT_TYPE_HEMORRAGIE_CAS_3)))
            .andExpect(jsonPath("$.[*].decesSyndromeHemorragique").value(hasItem(DEFAULT_DECES_SYNDROME_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].nombreDeces").value(hasItem(DEFAULT_NOMBRE_DECES)))
            .andExpect(jsonPath("$.[*].deces1Numero").value(hasItem(DEFAULT_DECES_1_NUMERO)))
            .andExpect(jsonPath("$.[*].lienParenteDeces1").value(hasItem(DEFAULT_LIEN_PARENTE_DECES_1.toString())))
            .andExpect(jsonPath("$.[*].causeDeces1").value(hasItem(DEFAULT_CAUSE_DECES_1)))
            .andExpect(jsonPath("$.[*].deces2Numero").value(hasItem(DEFAULT_DECES_2_NUMERO)))
            .andExpect(jsonPath("$.[*].lienParenteDeces2").value(hasItem(DEFAULT_LIEN_PARENTE_DECES_2.toString())))
            .andExpect(jsonPath("$.[*].causeDeces2").value(hasItem(DEFAULT_CAUSE_DECES_2)))
            .andExpect(jsonPath("$.[*].deces3Numero").value(hasItem(DEFAULT_DECES_3_NUMERO)))
            .andExpect(jsonPath("$.[*].lienParenteDeces3").value(hasItem(DEFAULT_LIEN_PARENTE_DECES_3.toString())))
            .andExpect(jsonPath("$.[*].causeDeces3").value(hasItem(DEFAULT_CAUSE_DECES_3)))
            .andExpect(jsonPath("$.[*].enqueteFamiliale").value(hasItem(DEFAULT_ENQUETE_FAMILIALE.booleanValue())))
            .andExpect(jsonPath("$.[*].decouverteFortuite").value(hasItem(DEFAULT_DECOUVERTE_FORTUITE.booleanValue())))
            .andExpect(
                jsonPath("$.[*].explorationSyndromeHemorragique").value(hasItem(DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].hemorragieChuteCordon").value(hasItem(DEFAULT_HEMORRAGIE_CHUTE_CORDON.booleanValue())))
            .andExpect(jsonPath("$.[*].metroragieMenarche").value(hasItem(DEFAULT_METRORAGIE_MENARCHE.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragieCerebraleNN").value(hasItem(DEFAULT_HEMORRAGIE_CEREBRALE_NN.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragiePostPartum").value(hasItem(DEFAULT_HEMORRAGIE_POST_PARTUM.booleanValue())))
            .andExpect(jsonPath("$.[*].autresCirconstances").value(hasItem(DEFAULT_AUTRES_CIRCONSTANCES)))
            .andExpect(
                jsonPath("$.[*].datePremierSigneCliniqueEvocateurJour").value(hasItem(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_JOUR))
            )
            .andExpect(
                jsonPath("$.[*].datePremierSigneCliniqueEvocateurMois").value(hasItem(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_MOIS))
            )
            .andExpect(
                jsonPath("$.[*].datePremierSigneCliniqueEvocateurAnnee").value(hasItem(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_ANNEE))
            )
            .andExpect(jsonPath("$.[*].ageDiagnosticJour").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_JOUR)))
            .andExpect(jsonPath("$.[*].ageDiagnosticMois").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_MOIS)))
            .andExpect(jsonPath("$.[*].ageDiagnosticAnnee").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_ANNEE)))
            .andExpect(jsonPath("$.[*].hb").value(hasItem(DEFAULT_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].ht").value(hasItem(DEFAULT_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].plaquettes").value(hasItem(DEFAULT_PLAQUETTES)))
            .andExpect(jsonPath("$.[*].tp").value(hasItem(DEFAULT_TP.doubleValue())))
            .andExpect(jsonPath("$.[*].tpMT").value(hasItem(DEFAULT_TP_MT.doubleValue())))
            .andExpect(jsonPath("$.[*].tca").value(hasItem(DEFAULT_TCA.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMT").value(hasItem(DEFAULT_TCA_MT.doubleValue())))
            .andExpect(jsonPath("$.[*].fibrinogene").value(hasItem(DEFAULT_FIBRINOGENE.doubleValue())))
            .andExpect(jsonPath("$.[*].ts").value(hasItem(DEFAULT_TS.toString())))
            .andExpect(jsonPath("$.[*].toPfa200").value(hasItem(DEFAULT_TO_PFA_200.toString())))
            .andExpect(jsonPath("$.[*].colEpi").value(hasItem(DEFAULT_COL_EPI.doubleValue())))
            .andExpect(jsonPath("$.[*].colAdp").value(hasItem(DEFAULT_COL_ADP.doubleValue())))
            .andExpect(jsonPath("$.[*].facteurXIII").value(hasItem(DEFAULT_FACTEUR_XIII.doubleValue())))
            .andExpect(jsonPath("$.[*].techniqueChromogenique").value(hasItem(DEFAULT_TECHNIQUE_CHROMOGENIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].tauxChromogenique").value(hasItem(DEFAULT_TAUX_CHROMOGENIQUE.doubleValue())))
            .andExpect(jsonPath("$.[*].techniqueSolubiliteCaillot").value(hasItem(DEFAULT_TECHNIQUE_SOLUBILITE_CAILLOT.booleanValue())))
            .andExpect(jsonPath("$.[*].tauxSolubiliteCaillot").value(hasItem(DEFAULT_TAUX_SOLUBILITE_CAILLOT.doubleValue())))
            .andExpect(jsonPath("$.[*].confirmationMoleculaire").value(hasItem(DEFAULT_CONFIRMATION_MOLECULAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].mutationPresente").value(hasItem(DEFAULT_MUTATION_PRESENTE.booleanValue())))
            .andExpect(jsonPath("$.[*].mutationNom").value(hasItem(DEFAULT_MUTATION_NOM)))
            .andExpect(jsonPath("$.[*].mutationType").value(hasItem(DEFAULT_MUTATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].mutation2Nom").value(hasItem(DEFAULT_MUTATION_2_NOM)))
            .andExpect(jsonPath("$.[*].saignementChuteCordon").value(hasItem(DEFAULT_SAIGNEMENT_CHUTE_CORDON.toString())))
            .andExpect(jsonPath("$.[*].cicatricesKeloides").value(hasItem(DEFAULT_CICATRICES_KELOIDES.toString())))
            .andExpect(jsonPath("$.[*].retardCicatrisation").value(hasItem(DEFAULT_RETARD_CICATRISATION.toString())))
            .andExpect(jsonPath("$.[*].epistaxis").value(hasItem(DEFAULT_EPISTAXIS.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceEpistaxis").value(hasItem(DEFAULT_FREQUENCE_EPISTAXIS)))
            .andExpect(jsonPath("$.[*].gingivorragies").value(hasItem(DEFAULT_GINGIVORRAGIES.booleanValue())))
            .andExpect(jsonPath("$.[*].typeGingivorragies").value(hasItem(DEFAULT_TYPE_GINGIVORRAGIES.toString())))
            .andExpect(jsonPath("$.[*].frequenceGingivorragies").value(hasItem(DEFAULT_FREQUENCE_GINGIVORRAGIES)))
            .andExpect(jsonPath("$.[*].ecchymose").value(hasItem(DEFAULT_ECCHYMOSE.booleanValue())))
            .andExpect(jsonPath("$.[*].typeEcchymose").value(hasItem(DEFAULT_TYPE_ECCHYMOSE.toString())))
            .andExpect(jsonPath("$.[*].frequenceEcchymose").value(hasItem(DEFAULT_FREQUENCE_ECCHYMOSE)))
            .andExpect(jsonPath("$.[*].menorragie").value(hasItem(DEFAULT_MENORRAGIE.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceMenorragie").value(hasItem(DEFAULT_FREQUENCE_MENORRAGIE)))
            .andExpect(jsonPath("$.[*].hematemese").value(hasItem(DEFAULT_HEMATEMESE.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceHematemese").value(hasItem(DEFAULT_FREQUENCE_HEMATEMESE)))
            .andExpect(jsonPath("$.[*].rectorragie").value(hasItem(DEFAULT_RECTORRAGIE.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceRectorragie").value(hasItem(DEFAULT_FREQUENCE_RECTORRAGIE)))
            .andExpect(jsonPath("$.[*].hematurie").value(hasItem(DEFAULT_HEMATURIE.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceHematurie").value(hasItem(DEFAULT_FREQUENCE_HEMATURIE)))
            .andExpect(jsonPath("$.[*].hematomeSousCutane").value(hasItem(DEFAULT_HEMATOME_SOUS_CUTANE.booleanValue())))
            .andExpect(jsonPath("$.[*].typeHematomeSousCutane").value(hasItem(DEFAULT_TYPE_HEMATOME_SOUS_CUTANE.toString())))
            .andExpect(jsonPath("$.[*].frequenceHematomeSousCutane").value(hasItem(DEFAULT_FREQUENCE_HEMATOME_SOUS_CUTANE)))
            .andExpect(jsonPath("$.[*].hematomeMusculaire").value(hasItem(DEFAULT_HEMATOME_MUSCULAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].siegeHematomeMusculaire").value(hasItem(DEFAULT_SIEGE_HEMATOME_MUSCULAIRE)))
            .andExpect(jsonPath("$.[*].typeHematomeMusculaire").value(hasItem(DEFAULT_TYPE_HEMATOME_MUSCULAIRE.toString())))
            .andExpect(jsonPath("$.[*].frequenceHematomeMusculaire").value(hasItem(DEFAULT_FREQUENCE_HEMATOME_MUSCULAIRE)))
            .andExpect(jsonPath("$.[*].hemarthrose").value(hasItem(DEFAULT_HEMARTHROSE.booleanValue())))
            .andExpect(jsonPath("$.[*].typeHemarthrose").value(hasItem(DEFAULT_TYPE_HEMARTHROSE.toString())))
            .andExpect(jsonPath("$.[*].frequenceHemarthrose").value(hasItem(DEFAULT_FREQUENCE_HEMARTHROSE)))
            .andExpect(jsonPath("$.[*].saignementSNC").value(hasItem(DEFAULT_SAIGNEMENT_SNC.booleanValue())))
            .andExpect(jsonPath("$.[*].typeSaignementSNC").value(hasItem(DEFAULT_TYPE_SAIGNEMENT_SNC.toString())))
            .andExpect(jsonPath("$.[*].frequenceSaignementSNC").value(hasItem(DEFAULT_FREQUENCE_SAIGNEMENT_SNC)))
            .andExpect(jsonPath("$.[*].ruptureKystesHemorragiques").value(hasItem(DEFAULT_RUPTURE_KYSTES_HEMORRAGIQUES.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragiesPostChirurgicales").value(hasItem(DEFAULT_HEMORRAGIES_POST_CHIRURGICALES.booleanValue())))
            .andExpect(jsonPath("$.[*].hemoperitone").value(hasItem(DEFAULT_HEMOPERITONE.booleanValue())))
            .andExpect(jsonPath("$.[*].accidentsThrombotiques").value(hasItem(DEFAULT_ACCIDENTS_THROMBOTIQUES.booleanValue())))
            .andExpect(jsonPath("$.[*].typeAccidentsThrombotiques").value(hasItem(DEFAULT_TYPE_ACCIDENTS_THROMBOTIQUES.toString())))
            .andExpect(jsonPath("$.[*].hemorragieGrave").value(hasItem(DEFAULT_HEMORRAGIE_GRAVE.toString())))
            .andExpect(jsonPath("$.[*].graveDeglobulisation").value(hasItem(DEFAULT_GRAVE_DEGLOBULISATION.booleanValue())))
            .andExpect(jsonPath("$.[*].graveEtatDeChoc").value(hasItem(DEFAULT_GRAVE_ETAT_DE_CHOC.booleanValue())))
            .andExpect(jsonPath("$.[*].graveParLocalisation").value(hasItem(DEFAULT_GRAVE_PAR_LOCALISATION.booleanValue())))
            .andExpect(jsonPath("$.[*].siegeGraviteLocalisation").value(hasItem(DEFAULT_SIEGE_GRAVITE_LOCALISATION)))
            .andExpect(jsonPath("$.[*].chirurgiePatientPrepare").value(hasItem(DEFAULT_CHIRURGIE_PATIENT_PREPARE.toString())))
            .andExpect(jsonPath("$.[*].chirurgieSuiteHemorragique").value(hasItem(DEFAULT_CHIRURGIE_SUITE_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].circoncisionPatientPrepare").value(hasItem(DEFAULT_CIRCONCISION_PATIENT_PREPARE.toString())))
            .andExpect(jsonPath("$.[*].circoncisionSuiteHemorragique").value(hasItem(DEFAULT_CIRCONCISION_SUITE_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].ageCirconcision").value(hasItem(DEFAULT_AGE_CIRCONCISION)))
            .andExpect(jsonPath("$.[*].concentreFacteurXIII").value(hasItem(DEFAULT_CONCENTRE_FACTEUR_XIII.toString())))
            .andExpect(jsonPath("$.[*].plasmaFraisCongele").value(hasItem(DEFAULT_PLASMA_FRAIS_CONGELE.toString())))
            .andExpect(jsonPath("$.[*].cryoprecipite").value(hasItem(DEFAULT_CRYOPRECIPITE.toString())))
            .andExpect(jsonPath("$.[*].culotsPlaquettaires").value(hasItem(DEFAULT_CULOTS_PLAQUETTAIRES.toString())))
            .andExpect(jsonPath("$.[*].dicynone").value(hasItem(DEFAULT_DICYNONE.toString())))
            .andExpect(jsonPath("$.[*].exacyl").value(hasItem(DEFAULT_EXACYL.toString())))
            .andExpect(jsonPath("$.[*].traitementMartial").value(hasItem(DEFAULT_TRAITEMENT_MARTIAL.toString())))
            .andExpect(jsonPath("$.[*].traitementOestroprogestatif").value(hasItem(DEFAULT_TRAITEMENT_OESTROPROGESTATIF.toString())))
            .andExpect(jsonPath("$.[*].transfusionCGR").value(hasItem(DEFAULT_TRANSFUSION_CGR.toString())))
            .andExpect(jsonPath("$.[*].inhibiteurs").value(hasItem(DEFAULT_INHIBITEURS.toString())))
            .andExpect(jsonPath("$.[*].inhibiteurNeutralisant").value(hasItem(DEFAULT_INHIBITEUR_NEUTRALISANT.booleanValue())))
            .andExpect(jsonPath("$.[*].titreInhibiteurNeutralisant").value(hasItem(DEFAULT_TITRE_INHIBITEUR_NEUTRALISANT.doubleValue())))
            .andExpect(jsonPath("$.[*].inhibiteurNonNeutralisant").value(hasItem(DEFAULT_INHIBITEUR_NON_NEUTRALISANT.booleanValue())))
            .andExpect(jsonPath("$.[*].infectionsVirales").value(hasItem(DEFAULT_INFECTIONS_VIRALES.toString())))
            .andExpect(jsonPath("$.[*].hepatiteB").value(hasItem(DEFAULT_HEPATITE_B.toString())))
            .andExpect(jsonPath("$.[*].hepatiteC").value(hasItem(DEFAULT_HEPATITE_C.toString())))
            .andExpect(jsonPath("$.[*].vih").value(hasItem(DEFAULT_VIH.toString())))
            .andExpect(jsonPath("$.[*].thrombose").value(hasItem(DEFAULT_THROMBOSE.booleanValue())))
            .andExpect(jsonPath("$.[*].reactionAllergique").value(hasItem(DEFAULT_REACTION_ALLERGIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].alloimmunisation").value(hasItem(DEFAULT_ALLOIMMUNISATION.booleanValue())))
            .andExpect(jsonPath("$.[*].autresComplications").value(hasItem(DEFAULT_AUTRES_COMPLICATIONS)))
            .andExpect(jsonPath("$.[*].adherenceTherapeutique").value(hasItem(DEFAULT_ADHERENCE_THERAPEUTIQUE.toString())))
            .andExpect(jsonPath("$.[*].causeNonAdherence").value(hasItem(DEFAULT_CAUSE_NON_ADHERENCE)))
            .andExpect(jsonPath("$.[*].vieSociale").value(hasItem(DEFAULT_VIE_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].marie").value(hasItem(DEFAULT_MARIE.toString())))
            .andExpect(jsonPath("$.[*].enfants").value(hasItem(DEFAULT_ENFANTS.toString())))
            .andExpect(jsonPath("$.[*].activitePhysique").value(hasItem(DEFAULT_ACTIVITE_PHYSIQUE.toString())))
            .andExpect(jsonPath("$.[*].typeActivitePhysique").value(hasItem(DEFAULT_TYPE_ACTIVITE_PHYSIQUE)))
            .andExpect(jsonPath("$.[*].sequelles").value(hasItem(DEFAULT_SEQUELLES.toString())))
            .andExpect(jsonPath("$.[*].typeSequelles").value(hasItem(DEFAULT_TYPE_SEQUELLES)))
            .andExpect(jsonPath("$.[*].patientDecede").value(hasItem(DEFAULT_PATIENT_DECEDE.toString())))
            .andExpect(jsonPath("$.[*].dateDeces").value(hasItem(DEFAULT_DATE_DECES.toString())))
            .andExpect(jsonPath("$.[*].ageDeces").value(hasItem(DEFAULT_AGE_DECES)))
            .andExpect(jsonPath("$.[*].causeDecesSNC").value(hasItem(DEFAULT_CAUSE_DECES_SNC.booleanValue())))
            .andExpect(jsonPath("$.[*].causeDecesAutreHemorragie").value(hasItem(DEFAULT_CAUSE_DECES_AUTRE_HEMORRAGIE.booleanValue())))
            .andExpect(jsonPath("$.[*].causeDecesVIH").value(hasItem(DEFAULT_CAUSE_DECES_VIH.booleanValue())))
            .andExpect(jsonPath("$.[*].causeDecesAutreInfection").value(hasItem(DEFAULT_CAUSE_DECES_AUTRE_INFECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].causeDecesThromboembolique").value(hasItem(DEFAULT_CAUSE_DECES_THROMBOEMBOLIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].causeDecesCancer").value(hasItem(DEFAULT_CAUSE_DECES_CANCER.booleanValue())))
            .andExpect(jsonPath("$.[*].causeDecesCovid19").value(hasItem(DEFAULT_CAUSE_DECES_COVID_19.booleanValue())))
            .andExpect(jsonPath("$.[*].causeDecesInconnue").value(hasItem(DEFAULT_CAUSE_DECES_INCONNUE.booleanValue())))
            .andExpect(jsonPath("$.[*].autreCauseDeces").value(hasItem(DEFAULT_AUTRE_CAUSE_DECES)));
    }

    @Test
    @Transactional
    void getFicheFacteurXIII() throws Exception {
        // Initialize the database
        insertedFicheFacteurXIII = ficheFacteurXIIIRepository.saveAndFlush(ficheFacteurXIII);

        // Get the ficheFacteurXIII
        restFicheFacteurXIIIMockMvc
            .perform(get(ENTITY_API_URL_ID, ficheFacteurXIII.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ficheFacteurXIII.getId().intValue()))
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
            .andExpect(jsonPath("$.degreParenteConsanguins").value(DEFAULT_DEGRE_PARENTE_CONSANGUINS.toString()))
            .andExpect(jsonPath("$.casSimilaires").value(DEFAULT_CAS_SIMILAIRES.toString()))
            .andExpect(jsonPath("$.nombreCas").value(DEFAULT_NOMBRE_CAS))
            .andExpect(jsonPath("$.cas1Numero").value(DEFAULT_CAS_1_NUMERO))
            .andExpect(jsonPath("$.lienParenteCas1").value(DEFAULT_LIEN_PARENTE_CAS_1.toString()))
            .andExpect(jsonPath("$.degreParenteCas1").value(DEFAULT_DEGRE_PARENTE_CAS_1))
            .andExpect(jsonPath("$.typeHemorragieCas1").value(DEFAULT_TYPE_HEMORRAGIE_CAS_1))
            .andExpect(jsonPath("$.cas2Numero").value(DEFAULT_CAS_2_NUMERO))
            .andExpect(jsonPath("$.lienParenteCas2").value(DEFAULT_LIEN_PARENTE_CAS_2.toString()))
            .andExpect(jsonPath("$.degreParenteCas2").value(DEFAULT_DEGRE_PARENTE_CAS_2))
            .andExpect(jsonPath("$.typeHemorragieCas2").value(DEFAULT_TYPE_HEMORRAGIE_CAS_2))
            .andExpect(jsonPath("$.cas3Numero").value(DEFAULT_CAS_3_NUMERO))
            .andExpect(jsonPath("$.lienParenteCas3").value(DEFAULT_LIEN_PARENTE_CAS_3.toString()))
            .andExpect(jsonPath("$.degreParenteCas3").value(DEFAULT_DEGRE_PARENTE_CAS_3))
            .andExpect(jsonPath("$.typeHemorragieCas3").value(DEFAULT_TYPE_HEMORRAGIE_CAS_3))
            .andExpect(jsonPath("$.decesSyndromeHemorragique").value(DEFAULT_DECES_SYNDROME_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.nombreDeces").value(DEFAULT_NOMBRE_DECES))
            .andExpect(jsonPath("$.deces1Numero").value(DEFAULT_DECES_1_NUMERO))
            .andExpect(jsonPath("$.lienParenteDeces1").value(DEFAULT_LIEN_PARENTE_DECES_1.toString()))
            .andExpect(jsonPath("$.causeDeces1").value(DEFAULT_CAUSE_DECES_1))
            .andExpect(jsonPath("$.deces2Numero").value(DEFAULT_DECES_2_NUMERO))
            .andExpect(jsonPath("$.lienParenteDeces2").value(DEFAULT_LIEN_PARENTE_DECES_2.toString()))
            .andExpect(jsonPath("$.causeDeces2").value(DEFAULT_CAUSE_DECES_2))
            .andExpect(jsonPath("$.deces3Numero").value(DEFAULT_DECES_3_NUMERO))
            .andExpect(jsonPath("$.lienParenteDeces3").value(DEFAULT_LIEN_PARENTE_DECES_3.toString()))
            .andExpect(jsonPath("$.causeDeces3").value(DEFAULT_CAUSE_DECES_3))
            .andExpect(jsonPath("$.enqueteFamiliale").value(DEFAULT_ENQUETE_FAMILIALE.booleanValue()))
            .andExpect(jsonPath("$.decouverteFortuite").value(DEFAULT_DECOUVERTE_FORTUITE.booleanValue()))
            .andExpect(jsonPath("$.explorationSyndromeHemorragique").value(DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE.booleanValue()))
            .andExpect(jsonPath("$.hemorragieChuteCordon").value(DEFAULT_HEMORRAGIE_CHUTE_CORDON.booleanValue()))
            .andExpect(jsonPath("$.metroragieMenarche").value(DEFAULT_METRORAGIE_MENARCHE.booleanValue()))
            .andExpect(jsonPath("$.hemorragieCerebraleNN").value(DEFAULT_HEMORRAGIE_CEREBRALE_NN.booleanValue()))
            .andExpect(jsonPath("$.hemorragiePostPartum").value(DEFAULT_HEMORRAGIE_POST_PARTUM.booleanValue()))
            .andExpect(jsonPath("$.autresCirconstances").value(DEFAULT_AUTRES_CIRCONSTANCES))
            .andExpect(jsonPath("$.datePremierSigneCliniqueEvocateurJour").value(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_JOUR))
            .andExpect(jsonPath("$.datePremierSigneCliniqueEvocateurMois").value(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_MOIS))
            .andExpect(jsonPath("$.datePremierSigneCliniqueEvocateurAnnee").value(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_ANNEE))
            .andExpect(jsonPath("$.ageDiagnosticJour").value(DEFAULT_AGE_DIAGNOSTIC_JOUR))
            .andExpect(jsonPath("$.ageDiagnosticMois").value(DEFAULT_AGE_DIAGNOSTIC_MOIS))
            .andExpect(jsonPath("$.ageDiagnosticAnnee").value(DEFAULT_AGE_DIAGNOSTIC_ANNEE))
            .andExpect(jsonPath("$.hb").value(DEFAULT_HB.doubleValue()))
            .andExpect(jsonPath("$.ht").value(DEFAULT_HT.doubleValue()))
            .andExpect(jsonPath("$.plaquettes").value(DEFAULT_PLAQUETTES))
            .andExpect(jsonPath("$.tp").value(DEFAULT_TP.doubleValue()))
            .andExpect(jsonPath("$.tpMT").value(DEFAULT_TP_MT.doubleValue()))
            .andExpect(jsonPath("$.tca").value(DEFAULT_TCA.doubleValue()))
            .andExpect(jsonPath("$.tcaMT").value(DEFAULT_TCA_MT.doubleValue()))
            .andExpect(jsonPath("$.fibrinogene").value(DEFAULT_FIBRINOGENE.doubleValue()))
            .andExpect(jsonPath("$.ts").value(DEFAULT_TS.toString()))
            .andExpect(jsonPath("$.toPfa200").value(DEFAULT_TO_PFA_200.toString()))
            .andExpect(jsonPath("$.colEpi").value(DEFAULT_COL_EPI.doubleValue()))
            .andExpect(jsonPath("$.colAdp").value(DEFAULT_COL_ADP.doubleValue()))
            .andExpect(jsonPath("$.facteurXIII").value(DEFAULT_FACTEUR_XIII.doubleValue()))
            .andExpect(jsonPath("$.techniqueChromogenique").value(DEFAULT_TECHNIQUE_CHROMOGENIQUE.booleanValue()))
            .andExpect(jsonPath("$.tauxChromogenique").value(DEFAULT_TAUX_CHROMOGENIQUE.doubleValue()))
            .andExpect(jsonPath("$.techniqueSolubiliteCaillot").value(DEFAULT_TECHNIQUE_SOLUBILITE_CAILLOT.booleanValue()))
            .andExpect(jsonPath("$.tauxSolubiliteCaillot").value(DEFAULT_TAUX_SOLUBILITE_CAILLOT.doubleValue()))
            .andExpect(jsonPath("$.confirmationMoleculaire").value(DEFAULT_CONFIRMATION_MOLECULAIRE.booleanValue()))
            .andExpect(jsonPath("$.mutationPresente").value(DEFAULT_MUTATION_PRESENTE.booleanValue()))
            .andExpect(jsonPath("$.mutationNom").value(DEFAULT_MUTATION_NOM))
            .andExpect(jsonPath("$.mutationType").value(DEFAULT_MUTATION_TYPE.toString()))
            .andExpect(jsonPath("$.mutation2Nom").value(DEFAULT_MUTATION_2_NOM))
            .andExpect(jsonPath("$.saignementChuteCordon").value(DEFAULT_SAIGNEMENT_CHUTE_CORDON.toString()))
            .andExpect(jsonPath("$.cicatricesKeloides").value(DEFAULT_CICATRICES_KELOIDES.toString()))
            .andExpect(jsonPath("$.retardCicatrisation").value(DEFAULT_RETARD_CICATRISATION.toString()))
            .andExpect(jsonPath("$.epistaxis").value(DEFAULT_EPISTAXIS.booleanValue()))
            .andExpect(jsonPath("$.frequenceEpistaxis").value(DEFAULT_FREQUENCE_EPISTAXIS))
            .andExpect(jsonPath("$.gingivorragies").value(DEFAULT_GINGIVORRAGIES.booleanValue()))
            .andExpect(jsonPath("$.typeGingivorragies").value(DEFAULT_TYPE_GINGIVORRAGIES.toString()))
            .andExpect(jsonPath("$.frequenceGingivorragies").value(DEFAULT_FREQUENCE_GINGIVORRAGIES))
            .andExpect(jsonPath("$.ecchymose").value(DEFAULT_ECCHYMOSE.booleanValue()))
            .andExpect(jsonPath("$.typeEcchymose").value(DEFAULT_TYPE_ECCHYMOSE.toString()))
            .andExpect(jsonPath("$.frequenceEcchymose").value(DEFAULT_FREQUENCE_ECCHYMOSE))
            .andExpect(jsonPath("$.menorragie").value(DEFAULT_MENORRAGIE.booleanValue()))
            .andExpect(jsonPath("$.frequenceMenorragie").value(DEFAULT_FREQUENCE_MENORRAGIE))
            .andExpect(jsonPath("$.hematemese").value(DEFAULT_HEMATEMESE.booleanValue()))
            .andExpect(jsonPath("$.frequenceHematemese").value(DEFAULT_FREQUENCE_HEMATEMESE))
            .andExpect(jsonPath("$.rectorragie").value(DEFAULT_RECTORRAGIE.booleanValue()))
            .andExpect(jsonPath("$.frequenceRectorragie").value(DEFAULT_FREQUENCE_RECTORRAGIE))
            .andExpect(jsonPath("$.hematurie").value(DEFAULT_HEMATURIE.booleanValue()))
            .andExpect(jsonPath("$.frequenceHematurie").value(DEFAULT_FREQUENCE_HEMATURIE))
            .andExpect(jsonPath("$.hematomeSousCutane").value(DEFAULT_HEMATOME_SOUS_CUTANE.booleanValue()))
            .andExpect(jsonPath("$.typeHematomeSousCutane").value(DEFAULT_TYPE_HEMATOME_SOUS_CUTANE.toString()))
            .andExpect(jsonPath("$.frequenceHematomeSousCutane").value(DEFAULT_FREQUENCE_HEMATOME_SOUS_CUTANE))
            .andExpect(jsonPath("$.hematomeMusculaire").value(DEFAULT_HEMATOME_MUSCULAIRE.booleanValue()))
            .andExpect(jsonPath("$.siegeHematomeMusculaire").value(DEFAULT_SIEGE_HEMATOME_MUSCULAIRE))
            .andExpect(jsonPath("$.typeHematomeMusculaire").value(DEFAULT_TYPE_HEMATOME_MUSCULAIRE.toString()))
            .andExpect(jsonPath("$.frequenceHematomeMusculaire").value(DEFAULT_FREQUENCE_HEMATOME_MUSCULAIRE))
            .andExpect(jsonPath("$.hemarthrose").value(DEFAULT_HEMARTHROSE.booleanValue()))
            .andExpect(jsonPath("$.typeHemarthrose").value(DEFAULT_TYPE_HEMARTHROSE.toString()))
            .andExpect(jsonPath("$.frequenceHemarthrose").value(DEFAULT_FREQUENCE_HEMARTHROSE))
            .andExpect(jsonPath("$.saignementSNC").value(DEFAULT_SAIGNEMENT_SNC.booleanValue()))
            .andExpect(jsonPath("$.typeSaignementSNC").value(DEFAULT_TYPE_SAIGNEMENT_SNC.toString()))
            .andExpect(jsonPath("$.frequenceSaignementSNC").value(DEFAULT_FREQUENCE_SAIGNEMENT_SNC))
            .andExpect(jsonPath("$.ruptureKystesHemorragiques").value(DEFAULT_RUPTURE_KYSTES_HEMORRAGIQUES.booleanValue()))
            .andExpect(jsonPath("$.hemorragiesPostChirurgicales").value(DEFAULT_HEMORRAGIES_POST_CHIRURGICALES.booleanValue()))
            .andExpect(jsonPath("$.hemoperitone").value(DEFAULT_HEMOPERITONE.booleanValue()))
            .andExpect(jsonPath("$.accidentsThrombotiques").value(DEFAULT_ACCIDENTS_THROMBOTIQUES.booleanValue()))
            .andExpect(jsonPath("$.typeAccidentsThrombotiques").value(DEFAULT_TYPE_ACCIDENTS_THROMBOTIQUES.toString()))
            .andExpect(jsonPath("$.hemorragieGrave").value(DEFAULT_HEMORRAGIE_GRAVE.toString()))
            .andExpect(jsonPath("$.graveDeglobulisation").value(DEFAULT_GRAVE_DEGLOBULISATION.booleanValue()))
            .andExpect(jsonPath("$.graveEtatDeChoc").value(DEFAULT_GRAVE_ETAT_DE_CHOC.booleanValue()))
            .andExpect(jsonPath("$.graveParLocalisation").value(DEFAULT_GRAVE_PAR_LOCALISATION.booleanValue()))
            .andExpect(jsonPath("$.siegeGraviteLocalisation").value(DEFAULT_SIEGE_GRAVITE_LOCALISATION))
            .andExpect(jsonPath("$.chirurgiePatientPrepare").value(DEFAULT_CHIRURGIE_PATIENT_PREPARE.toString()))
            .andExpect(jsonPath("$.chirurgieSuiteHemorragique").value(DEFAULT_CHIRURGIE_SUITE_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.circoncisionPatientPrepare").value(DEFAULT_CIRCONCISION_PATIENT_PREPARE.toString()))
            .andExpect(jsonPath("$.circoncisionSuiteHemorragique").value(DEFAULT_CIRCONCISION_SUITE_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.ageCirconcision").value(DEFAULT_AGE_CIRCONCISION))
            .andExpect(jsonPath("$.concentreFacteurXIII").value(DEFAULT_CONCENTRE_FACTEUR_XIII.toString()))
            .andExpect(jsonPath("$.plasmaFraisCongele").value(DEFAULT_PLASMA_FRAIS_CONGELE.toString()))
            .andExpect(jsonPath("$.cryoprecipite").value(DEFAULT_CRYOPRECIPITE.toString()))
            .andExpect(jsonPath("$.culotsPlaquettaires").value(DEFAULT_CULOTS_PLAQUETTAIRES.toString()))
            .andExpect(jsonPath("$.dicynone").value(DEFAULT_DICYNONE.toString()))
            .andExpect(jsonPath("$.exacyl").value(DEFAULT_EXACYL.toString()))
            .andExpect(jsonPath("$.traitementMartial").value(DEFAULT_TRAITEMENT_MARTIAL.toString()))
            .andExpect(jsonPath("$.traitementOestroprogestatif").value(DEFAULT_TRAITEMENT_OESTROPROGESTATIF.toString()))
            .andExpect(jsonPath("$.transfusionCGR").value(DEFAULT_TRANSFUSION_CGR.toString()))
            .andExpect(jsonPath("$.inhibiteurs").value(DEFAULT_INHIBITEURS.toString()))
            .andExpect(jsonPath("$.inhibiteurNeutralisant").value(DEFAULT_INHIBITEUR_NEUTRALISANT.booleanValue()))
            .andExpect(jsonPath("$.titreInhibiteurNeutralisant").value(DEFAULT_TITRE_INHIBITEUR_NEUTRALISANT.doubleValue()))
            .andExpect(jsonPath("$.inhibiteurNonNeutralisant").value(DEFAULT_INHIBITEUR_NON_NEUTRALISANT.booleanValue()))
            .andExpect(jsonPath("$.infectionsVirales").value(DEFAULT_INFECTIONS_VIRALES.toString()))
            .andExpect(jsonPath("$.hepatiteB").value(DEFAULT_HEPATITE_B.toString()))
            .andExpect(jsonPath("$.hepatiteC").value(DEFAULT_HEPATITE_C.toString()))
            .andExpect(jsonPath("$.vih").value(DEFAULT_VIH.toString()))
            .andExpect(jsonPath("$.thrombose").value(DEFAULT_THROMBOSE.booleanValue()))
            .andExpect(jsonPath("$.reactionAllergique").value(DEFAULT_REACTION_ALLERGIQUE.booleanValue()))
            .andExpect(jsonPath("$.alloimmunisation").value(DEFAULT_ALLOIMMUNISATION.booleanValue()))
            .andExpect(jsonPath("$.autresComplications").value(DEFAULT_AUTRES_COMPLICATIONS))
            .andExpect(jsonPath("$.adherenceTherapeutique").value(DEFAULT_ADHERENCE_THERAPEUTIQUE.toString()))
            .andExpect(jsonPath("$.causeNonAdherence").value(DEFAULT_CAUSE_NON_ADHERENCE))
            .andExpect(jsonPath("$.vieSociale").value(DEFAULT_VIE_SOCIALE.toString()))
            .andExpect(jsonPath("$.marie").value(DEFAULT_MARIE.toString()))
            .andExpect(jsonPath("$.enfants").value(DEFAULT_ENFANTS.toString()))
            .andExpect(jsonPath("$.activitePhysique").value(DEFAULT_ACTIVITE_PHYSIQUE.toString()))
            .andExpect(jsonPath("$.typeActivitePhysique").value(DEFAULT_TYPE_ACTIVITE_PHYSIQUE))
            .andExpect(jsonPath("$.sequelles").value(DEFAULT_SEQUELLES.toString()))
            .andExpect(jsonPath("$.typeSequelles").value(DEFAULT_TYPE_SEQUELLES))
            .andExpect(jsonPath("$.patientDecede").value(DEFAULT_PATIENT_DECEDE.toString()))
            .andExpect(jsonPath("$.dateDeces").value(DEFAULT_DATE_DECES.toString()))
            .andExpect(jsonPath("$.ageDeces").value(DEFAULT_AGE_DECES))
            .andExpect(jsonPath("$.causeDecesSNC").value(DEFAULT_CAUSE_DECES_SNC.booleanValue()))
            .andExpect(jsonPath("$.causeDecesAutreHemorragie").value(DEFAULT_CAUSE_DECES_AUTRE_HEMORRAGIE.booleanValue()))
            .andExpect(jsonPath("$.causeDecesVIH").value(DEFAULT_CAUSE_DECES_VIH.booleanValue()))
            .andExpect(jsonPath("$.causeDecesAutreInfection").value(DEFAULT_CAUSE_DECES_AUTRE_INFECTION.booleanValue()))
            .andExpect(jsonPath("$.causeDecesThromboembolique").value(DEFAULT_CAUSE_DECES_THROMBOEMBOLIQUE.booleanValue()))
            .andExpect(jsonPath("$.causeDecesCancer").value(DEFAULT_CAUSE_DECES_CANCER.booleanValue()))
            .andExpect(jsonPath("$.causeDecesCovid19").value(DEFAULT_CAUSE_DECES_COVID_19.booleanValue()))
            .andExpect(jsonPath("$.causeDecesInconnue").value(DEFAULT_CAUSE_DECES_INCONNUE.booleanValue()))
            .andExpect(jsonPath("$.autreCauseDeces").value(DEFAULT_AUTRE_CAUSE_DECES));
    }

    @Test
    @Transactional
    void getNonExistingFicheFacteurXIII() throws Exception {
        // Get the ficheFacteurXIII
        restFicheFacteurXIIIMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFicheFacteurXIII() throws Exception {
        // Initialize the database
        insertedFicheFacteurXIII = ficheFacteurXIIIRepository.saveAndFlush(ficheFacteurXIII);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheFacteurXIII
        FicheFacteurXIII updatedFicheFacteurXIII = ficheFacteurXIIIRepository.findById(ficheFacteurXIII.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFicheFacteurXIII are not directly saved in db
        em.detach(updatedFicheFacteurXIII);
        updatedFicheFacteurXIII
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
            .cas1Numero(UPDATED_CAS_1_NUMERO)
            .lienParenteCas1(UPDATED_LIEN_PARENTE_CAS_1)
            .degreParenteCas1(UPDATED_DEGRE_PARENTE_CAS_1)
            .typeHemorragieCas1(UPDATED_TYPE_HEMORRAGIE_CAS_1)
            .cas2Numero(UPDATED_CAS_2_NUMERO)
            .lienParenteCas2(UPDATED_LIEN_PARENTE_CAS_2)
            .degreParenteCas2(UPDATED_DEGRE_PARENTE_CAS_2)
            .typeHemorragieCas2(UPDATED_TYPE_HEMORRAGIE_CAS_2)
            .cas3Numero(UPDATED_CAS_3_NUMERO)
            .lienParenteCas3(UPDATED_LIEN_PARENTE_CAS_3)
            .degreParenteCas3(UPDATED_DEGRE_PARENTE_CAS_3)
            .typeHemorragieCas3(UPDATED_TYPE_HEMORRAGIE_CAS_3)
            .decesSyndromeHemorragique(UPDATED_DECES_SYNDROME_HEMORRAGIQUE)
            .nombreDeces(UPDATED_NOMBRE_DECES)
            .deces1Numero(UPDATED_DECES_1_NUMERO)
            .lienParenteDeces1(UPDATED_LIEN_PARENTE_DECES_1)
            .causeDeces1(UPDATED_CAUSE_DECES_1)
            .deces2Numero(UPDATED_DECES_2_NUMERO)
            .lienParenteDeces2(UPDATED_LIEN_PARENTE_DECES_2)
            .causeDeces2(UPDATED_CAUSE_DECES_2)
            .deces3Numero(UPDATED_DECES_3_NUMERO)
            .lienParenteDeces3(UPDATED_LIEN_PARENTE_DECES_3)
            .causeDeces3(UPDATED_CAUSE_DECES_3)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .hemorragieChuteCordon(UPDATED_HEMORRAGIE_CHUTE_CORDON)
            .metroragieMenarche(UPDATED_METRORAGIE_MENARCHE)
            .hemorragieCerebraleNN(UPDATED_HEMORRAGIE_CEREBRALE_NN)
            .hemorragiePostPartum(UPDATED_HEMORRAGIE_POST_PARTUM)
            .autresCirconstances(UPDATED_AUTRES_CIRCONSTANCES)
            .datePremierSigneCliniqueEvocateurJour(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_JOUR)
            .datePremierSigneCliniqueEvocateurMois(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_MOIS)
            .datePremierSigneCliniqueEvocateurAnnee(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_ANNEE)
            .ageDiagnosticJour(UPDATED_AGE_DIAGNOSTIC_JOUR)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnee(UPDATED_AGE_DIAGNOSTIC_ANNEE)
            .hb(UPDATED_HB)
            .ht(UPDATED_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .tpMT(UPDATED_TP_MT)
            .tca(UPDATED_TCA)
            .tcaMT(UPDATED_TCA_MT)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .ts(UPDATED_TS)
            .toPfa200(UPDATED_TO_PFA_200)
            .colEpi(UPDATED_COL_EPI)
            .colAdp(UPDATED_COL_ADP)
            .facteurXIII(UPDATED_FACTEUR_XIII)
            .techniqueChromogenique(UPDATED_TECHNIQUE_CHROMOGENIQUE)
            .tauxChromogenique(UPDATED_TAUX_CHROMOGENIQUE)
            .techniqueSolubiliteCaillot(UPDATED_TECHNIQUE_SOLUBILITE_CAILLOT)
            .tauxSolubiliteCaillot(UPDATED_TAUX_SOLUBILITE_CAILLOT)
            .confirmationMoleculaire(UPDATED_CONFIRMATION_MOLECULAIRE)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationType(UPDATED_MUTATION_TYPE)
            .mutation2Nom(UPDATED_MUTATION_2_NOM)
            .saignementChuteCordon(UPDATED_SAIGNEMENT_CHUTE_CORDON)
            .cicatricesKeloides(UPDATED_CICATRICES_KELOIDES)
            .retardCicatrisation(UPDATED_RETARD_CICATRISATION)
            .epistaxis(UPDATED_EPISTAXIS)
            .frequenceEpistaxis(UPDATED_FREQUENCE_EPISTAXIS)
            .gingivorragies(UPDATED_GINGIVORRAGIES)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .frequenceGingivorragies(UPDATED_FREQUENCE_GINGIVORRAGIES)
            .ecchymose(UPDATED_ECCHYMOSE)
            .typeEcchymose(UPDATED_TYPE_ECCHYMOSE)
            .frequenceEcchymose(UPDATED_FREQUENCE_ECCHYMOSE)
            .menorragie(UPDATED_MENORRAGIE)
            .frequenceMenorragie(UPDATED_FREQUENCE_MENORRAGIE)
            .hematemese(UPDATED_HEMATEMESE)
            .frequenceHematemese(UPDATED_FREQUENCE_HEMATEMESE)
            .rectorragie(UPDATED_RECTORRAGIE)
            .frequenceRectorragie(UPDATED_FREQUENCE_RECTORRAGIE)
            .hematurie(UPDATED_HEMATURIE)
            .frequenceHematurie(UPDATED_FREQUENCE_HEMATURIE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(UPDATED_TYPE_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .siegeHematomeMusculaire(UPDATED_SIEGE_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(UPDATED_TYPE_HEMATOME_MUSCULAIRE)
            .frequenceHematomeMusculaire(UPDATED_FREQUENCE_HEMATOME_MUSCULAIRE)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .typeHemarthrose(UPDATED_TYPE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .typeSaignementSNC(UPDATED_TYPE_SAIGNEMENT_SNC)
            .frequenceSaignementSNC(UPDATED_FREQUENCE_SAIGNEMENT_SNC)
            .ruptureKystesHemorragiques(UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES)
            .hemorragiesPostChirurgicales(UPDATED_HEMORRAGIES_POST_CHIRURGICALES)
            .hemoperitone(UPDATED_HEMOPERITONE)
            .accidentsThrombotiques(UPDATED_ACCIDENTS_THROMBOTIQUES)
            .typeAccidentsThrombotiques(UPDATED_TYPE_ACCIDENTS_THROMBOTIQUES)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .graveDeglobulisation(UPDATED_GRAVE_DEGLOBULISATION)
            .graveEtatDeChoc(UPDATED_GRAVE_ETAT_DE_CHOC)
            .graveParLocalisation(UPDATED_GRAVE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .chirurgiePatientPrepare(UPDATED_CHIRURGIE_PATIENT_PREPARE)
            .chirurgieSuiteHemorragique(UPDATED_CHIRURGIE_SUITE_HEMORRAGIQUE)
            .circoncisionPatientPrepare(UPDATED_CIRCONCISION_PATIENT_PREPARE)
            .circoncisionSuiteHemorragique(UPDATED_CIRCONCISION_SUITE_HEMORRAGIQUE)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .concentreFacteurXIII(UPDATED_CONCENTRE_FACTEUR_XIII)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .culotsPlaquettaires(UPDATED_CULOTS_PLAQUETTAIRES)
            .dicynone(UPDATED_DICYNONE)
            .exacyl(UPDATED_EXACYL)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .transfusionCGR(UPDATED_TRANSFUSION_CGR)
            .inhibiteurs(UPDATED_INHIBITEURS)
            .inhibiteurNeutralisant(UPDATED_INHIBITEUR_NEUTRALISANT)
            .titreInhibiteurNeutralisant(UPDATED_TITRE_INHIBITEUR_NEUTRALISANT)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .infectionsVirales(UPDATED_INFECTIONS_VIRALES)
            .hepatiteB(UPDATED_HEPATITE_B)
            .hepatiteC(UPDATED_HEPATITE_C)
            .vih(UPDATED_VIH)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .alloimmunisation(UPDATED_ALLOIMMUNISATION)
            .autresComplications(UPDATED_AUTRES_COMPLICATIONS)
            .adherenceTherapeutique(UPDATED_ADHERENCE_THERAPEUTIQUE)
            .causeNonAdherence(UPDATED_CAUSE_NON_ADHERENCE)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .causeDecesSNC(UPDATED_CAUSE_DECES_SNC)
            .causeDecesAutreHemorragie(UPDATED_CAUSE_DECES_AUTRE_HEMORRAGIE)
            .causeDecesVIH(UPDATED_CAUSE_DECES_VIH)
            .causeDecesAutreInfection(UPDATED_CAUSE_DECES_AUTRE_INFECTION)
            .causeDecesThromboembolique(UPDATED_CAUSE_DECES_THROMBOEMBOLIQUE)
            .causeDecesCancer(UPDATED_CAUSE_DECES_CANCER)
            .causeDecesCovid19(UPDATED_CAUSE_DECES_COVID_19)
            .causeDecesInconnue(UPDATED_CAUSE_DECES_INCONNUE)
            .autreCauseDeces(UPDATED_AUTRE_CAUSE_DECES);
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(updatedFicheFacteurXIII);

        restFicheFacteurXIIIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheFacteurXIIIDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheFacteurXIIIDTO))
            )
            .andExpect(status().isOk());

        // Validate the FicheFacteurXIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFicheFacteurXIIIToMatchAllProperties(updatedFicheFacteurXIII);
    }

    @Test
    @Transactional
    void putNonExistingFicheFacteurXIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXIII
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheFacteurXIIIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheFacteurXIIIDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheFacteurXIIIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFicheFacteurXIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXIII
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurXIIIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheFacteurXIIIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFicheFacteurXIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXIII
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurXIIIMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIIIDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheFacteurXIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFicheFacteurXIIIWithPatch() throws Exception {
        // Initialize the database
        insertedFicheFacteurXIII = ficheFacteurXIIIRepository.saveAndFlush(ficheFacteurXIII);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheFacteurXIII using partial update
        FicheFacteurXIII partialUpdatedFicheFacteurXIII = new FicheFacteurXIII();
        partialUpdatedFicheFacteurXIII.setId(ficheFacteurXIII.getId());

        partialUpdatedFicheFacteurXIII
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .autreServiceClinique(UPDATED_AUTRE_SERVICE_CLINIQUE)
            .sexe(UPDATED_SEXE)
            .prenomPere(UPDATED_PRENOM_PERE)
            .nomPrenomMere(UPDATED_NOM_PRENOM_MERE)
            .professionMere(UPDATED_PROFESSION_MERE)
            .serviceOrigine(UPDATED_SERVICE_ORIGINE)
            .biologisteResponsableDg(UPDATED_BIOLOGISTE_RESPONSABLE_DG)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .nombreCas(UPDATED_NOMBRE_CAS)
            .lienParenteCas1(UPDATED_LIEN_PARENTE_CAS_1)
            .lienParenteCas2(UPDATED_LIEN_PARENTE_CAS_2)
            .degreParenteCas2(UPDATED_DEGRE_PARENTE_CAS_2)
            .cas3Numero(UPDATED_CAS_3_NUMERO)
            .degreParenteCas3(UPDATED_DEGRE_PARENTE_CAS_3)
            .typeHemorragieCas3(UPDATED_TYPE_HEMORRAGIE_CAS_3)
            .nombreDeces(UPDATED_NOMBRE_DECES)
            .causeDeces1(UPDATED_CAUSE_DECES_1)
            .causeDeces2(UPDATED_CAUSE_DECES_2)
            .deces3Numero(UPDATED_DECES_3_NUMERO)
            .causeDeces3(UPDATED_CAUSE_DECES_3)
            .hemorragieChuteCordon(UPDATED_HEMORRAGIE_CHUTE_CORDON)
            .metroragieMenarche(UPDATED_METRORAGIE_MENARCHE)
            .hemorragieCerebraleNN(UPDATED_HEMORRAGIE_CEREBRALE_NN)
            .autresCirconstances(UPDATED_AUTRES_CIRCONSTANCES)
            .datePremierSigneCliniqueEvocateurAnnee(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_ANNEE)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .hb(UPDATED_HB)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .tca(UPDATED_TCA)
            .tcaMT(UPDATED_TCA_MT)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .toPfa200(UPDATED_TO_PFA_200)
            .colEpi(UPDATED_COL_EPI)
            .colAdp(UPDATED_COL_ADP)
            .techniqueChromogenique(UPDATED_TECHNIQUE_CHROMOGENIQUE)
            .tauxSolubiliteCaillot(UPDATED_TAUX_SOLUBILITE_CAILLOT)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutation2Nom(UPDATED_MUTATION_2_NOM)
            .gingivorragies(UPDATED_GINGIVORRAGIES)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .frequenceGingivorragies(UPDATED_FREQUENCE_GINGIVORRAGIES)
            .ecchymose(UPDATED_ECCHYMOSE)
            .frequenceHematemese(UPDATED_FREQUENCE_HEMATEMESE)
            .frequenceHematurie(UPDATED_FREQUENCE_HEMATURIE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(UPDATED_TYPE_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .siegeHematomeMusculaire(UPDATED_SIEGE_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(UPDATED_TYPE_HEMATOME_MUSCULAIRE)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .typeHemarthrose(UPDATED_TYPE_HEMARTHROSE)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .typeSaignementSNC(UPDATED_TYPE_SAIGNEMENT_SNC)
            .frequenceSaignementSNC(UPDATED_FREQUENCE_SAIGNEMENT_SNC)
            .ruptureKystesHemorragiques(UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .chirurgieSuiteHemorragique(UPDATED_CHIRURGIE_SUITE_HEMORRAGIQUE)
            .circoncisionSuiteHemorragique(UPDATED_CIRCONCISION_SUITE_HEMORRAGIQUE)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .dicynone(UPDATED_DICYNONE)
            .inhibiteurs(UPDATED_INHIBITEURS)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .autresComplications(UPDATED_AUTRES_COMPLICATIONS)
            .marie(UPDATED_MARIE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .causeDecesThromboembolique(UPDATED_CAUSE_DECES_THROMBOEMBOLIQUE)
            .causeDecesCancer(UPDATED_CAUSE_DECES_CANCER);

        restFicheFacteurXIIIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheFacteurXIII.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheFacteurXIII))
            )
            .andExpect(status().isOk());

        // Validate the FicheFacteurXIII in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheFacteurXIIIUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFicheFacteurXIII, ficheFacteurXIII),
            getPersistedFicheFacteurXIII(ficheFacteurXIII)
        );
    }

    @Test
    @Transactional
    void fullUpdateFicheFacteurXIIIWithPatch() throws Exception {
        // Initialize the database
        insertedFicheFacteurXIII = ficheFacteurXIIIRepository.saveAndFlush(ficheFacteurXIII);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheFacteurXIII using partial update
        FicheFacteurXIII partialUpdatedFicheFacteurXIII = new FicheFacteurXIII();
        partialUpdatedFicheFacteurXIII.setId(ficheFacteurXIII.getId());

        partialUpdatedFicheFacteurXIII
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
            .cas1Numero(UPDATED_CAS_1_NUMERO)
            .lienParenteCas1(UPDATED_LIEN_PARENTE_CAS_1)
            .degreParenteCas1(UPDATED_DEGRE_PARENTE_CAS_1)
            .typeHemorragieCas1(UPDATED_TYPE_HEMORRAGIE_CAS_1)
            .cas2Numero(UPDATED_CAS_2_NUMERO)
            .lienParenteCas2(UPDATED_LIEN_PARENTE_CAS_2)
            .degreParenteCas2(UPDATED_DEGRE_PARENTE_CAS_2)
            .typeHemorragieCas2(UPDATED_TYPE_HEMORRAGIE_CAS_2)
            .cas3Numero(UPDATED_CAS_3_NUMERO)
            .lienParenteCas3(UPDATED_LIEN_PARENTE_CAS_3)
            .degreParenteCas3(UPDATED_DEGRE_PARENTE_CAS_3)
            .typeHemorragieCas3(UPDATED_TYPE_HEMORRAGIE_CAS_3)
            .decesSyndromeHemorragique(UPDATED_DECES_SYNDROME_HEMORRAGIQUE)
            .nombreDeces(UPDATED_NOMBRE_DECES)
            .deces1Numero(UPDATED_DECES_1_NUMERO)
            .lienParenteDeces1(UPDATED_LIEN_PARENTE_DECES_1)
            .causeDeces1(UPDATED_CAUSE_DECES_1)
            .deces2Numero(UPDATED_DECES_2_NUMERO)
            .lienParenteDeces2(UPDATED_LIEN_PARENTE_DECES_2)
            .causeDeces2(UPDATED_CAUSE_DECES_2)
            .deces3Numero(UPDATED_DECES_3_NUMERO)
            .lienParenteDeces3(UPDATED_LIEN_PARENTE_DECES_3)
            .causeDeces3(UPDATED_CAUSE_DECES_3)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .hemorragieChuteCordon(UPDATED_HEMORRAGIE_CHUTE_CORDON)
            .metroragieMenarche(UPDATED_METRORAGIE_MENARCHE)
            .hemorragieCerebraleNN(UPDATED_HEMORRAGIE_CEREBRALE_NN)
            .hemorragiePostPartum(UPDATED_HEMORRAGIE_POST_PARTUM)
            .autresCirconstances(UPDATED_AUTRES_CIRCONSTANCES)
            .datePremierSigneCliniqueEvocateurJour(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_JOUR)
            .datePremierSigneCliniqueEvocateurMois(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_MOIS)
            .datePremierSigneCliniqueEvocateurAnnee(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE_EVOCATEUR_ANNEE)
            .ageDiagnosticJour(UPDATED_AGE_DIAGNOSTIC_JOUR)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnee(UPDATED_AGE_DIAGNOSTIC_ANNEE)
            .hb(UPDATED_HB)
            .ht(UPDATED_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .tpMT(UPDATED_TP_MT)
            .tca(UPDATED_TCA)
            .tcaMT(UPDATED_TCA_MT)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .ts(UPDATED_TS)
            .toPfa200(UPDATED_TO_PFA_200)
            .colEpi(UPDATED_COL_EPI)
            .colAdp(UPDATED_COL_ADP)
            .facteurXIII(UPDATED_FACTEUR_XIII)
            .techniqueChromogenique(UPDATED_TECHNIQUE_CHROMOGENIQUE)
            .tauxChromogenique(UPDATED_TAUX_CHROMOGENIQUE)
            .techniqueSolubiliteCaillot(UPDATED_TECHNIQUE_SOLUBILITE_CAILLOT)
            .tauxSolubiliteCaillot(UPDATED_TAUX_SOLUBILITE_CAILLOT)
            .confirmationMoleculaire(UPDATED_CONFIRMATION_MOLECULAIRE)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationType(UPDATED_MUTATION_TYPE)
            .mutation2Nom(UPDATED_MUTATION_2_NOM)
            .saignementChuteCordon(UPDATED_SAIGNEMENT_CHUTE_CORDON)
            .cicatricesKeloides(UPDATED_CICATRICES_KELOIDES)
            .retardCicatrisation(UPDATED_RETARD_CICATRISATION)
            .epistaxis(UPDATED_EPISTAXIS)
            .frequenceEpistaxis(UPDATED_FREQUENCE_EPISTAXIS)
            .gingivorragies(UPDATED_GINGIVORRAGIES)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .frequenceGingivorragies(UPDATED_FREQUENCE_GINGIVORRAGIES)
            .ecchymose(UPDATED_ECCHYMOSE)
            .typeEcchymose(UPDATED_TYPE_ECCHYMOSE)
            .frequenceEcchymose(UPDATED_FREQUENCE_ECCHYMOSE)
            .menorragie(UPDATED_MENORRAGIE)
            .frequenceMenorragie(UPDATED_FREQUENCE_MENORRAGIE)
            .hematemese(UPDATED_HEMATEMESE)
            .frequenceHematemese(UPDATED_FREQUENCE_HEMATEMESE)
            .rectorragie(UPDATED_RECTORRAGIE)
            .frequenceRectorragie(UPDATED_FREQUENCE_RECTORRAGIE)
            .hematurie(UPDATED_HEMATURIE)
            .frequenceHematurie(UPDATED_FREQUENCE_HEMATURIE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(UPDATED_TYPE_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .siegeHematomeMusculaire(UPDATED_SIEGE_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(UPDATED_TYPE_HEMATOME_MUSCULAIRE)
            .frequenceHematomeMusculaire(UPDATED_FREQUENCE_HEMATOME_MUSCULAIRE)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .typeHemarthrose(UPDATED_TYPE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .typeSaignementSNC(UPDATED_TYPE_SAIGNEMENT_SNC)
            .frequenceSaignementSNC(UPDATED_FREQUENCE_SAIGNEMENT_SNC)
            .ruptureKystesHemorragiques(UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES)
            .hemorragiesPostChirurgicales(UPDATED_HEMORRAGIES_POST_CHIRURGICALES)
            .hemoperitone(UPDATED_HEMOPERITONE)
            .accidentsThrombotiques(UPDATED_ACCIDENTS_THROMBOTIQUES)
            .typeAccidentsThrombotiques(UPDATED_TYPE_ACCIDENTS_THROMBOTIQUES)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .graveDeglobulisation(UPDATED_GRAVE_DEGLOBULISATION)
            .graveEtatDeChoc(UPDATED_GRAVE_ETAT_DE_CHOC)
            .graveParLocalisation(UPDATED_GRAVE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .chirurgiePatientPrepare(UPDATED_CHIRURGIE_PATIENT_PREPARE)
            .chirurgieSuiteHemorragique(UPDATED_CHIRURGIE_SUITE_HEMORRAGIQUE)
            .circoncisionPatientPrepare(UPDATED_CIRCONCISION_PATIENT_PREPARE)
            .circoncisionSuiteHemorragique(UPDATED_CIRCONCISION_SUITE_HEMORRAGIQUE)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .concentreFacteurXIII(UPDATED_CONCENTRE_FACTEUR_XIII)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .culotsPlaquettaires(UPDATED_CULOTS_PLAQUETTAIRES)
            .dicynone(UPDATED_DICYNONE)
            .exacyl(UPDATED_EXACYL)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .transfusionCGR(UPDATED_TRANSFUSION_CGR)
            .inhibiteurs(UPDATED_INHIBITEURS)
            .inhibiteurNeutralisant(UPDATED_INHIBITEUR_NEUTRALISANT)
            .titreInhibiteurNeutralisant(UPDATED_TITRE_INHIBITEUR_NEUTRALISANT)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .infectionsVirales(UPDATED_INFECTIONS_VIRALES)
            .hepatiteB(UPDATED_HEPATITE_B)
            .hepatiteC(UPDATED_HEPATITE_C)
            .vih(UPDATED_VIH)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .alloimmunisation(UPDATED_ALLOIMMUNISATION)
            .autresComplications(UPDATED_AUTRES_COMPLICATIONS)
            .adherenceTherapeutique(UPDATED_ADHERENCE_THERAPEUTIQUE)
            .causeNonAdherence(UPDATED_CAUSE_NON_ADHERENCE)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .causeDecesSNC(UPDATED_CAUSE_DECES_SNC)
            .causeDecesAutreHemorragie(UPDATED_CAUSE_DECES_AUTRE_HEMORRAGIE)
            .causeDecesVIH(UPDATED_CAUSE_DECES_VIH)
            .causeDecesAutreInfection(UPDATED_CAUSE_DECES_AUTRE_INFECTION)
            .causeDecesThromboembolique(UPDATED_CAUSE_DECES_THROMBOEMBOLIQUE)
            .causeDecesCancer(UPDATED_CAUSE_DECES_CANCER)
            .causeDecesCovid19(UPDATED_CAUSE_DECES_COVID_19)
            .causeDecesInconnue(UPDATED_CAUSE_DECES_INCONNUE)
            .autreCauseDeces(UPDATED_AUTRE_CAUSE_DECES);

        restFicheFacteurXIIIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheFacteurXIII.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheFacteurXIII))
            )
            .andExpect(status().isOk());

        // Validate the FicheFacteurXIII in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheFacteurXIIIUpdatableFieldsEquals(
            partialUpdatedFicheFacteurXIII,
            getPersistedFicheFacteurXIII(partialUpdatedFicheFacteurXIII)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFicheFacteurXIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXIII
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheFacteurXIIIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ficheFacteurXIIIDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheFacteurXIIIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFicheFacteurXIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXIII
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurXIIIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheFacteurXIIIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFicheFacteurXIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXIII
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = ficheFacteurXIIIMapper.toDto(ficheFacteurXIII);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurXIIIMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(ficheFacteurXIIIDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheFacteurXIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFicheFacteurXIII() throws Exception {
        // Initialize the database
        insertedFicheFacteurXIII = ficheFacteurXIIIRepository.saveAndFlush(ficheFacteurXIII);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the ficheFacteurXIII
        restFicheFacteurXIIIMockMvc
            .perform(delete(ENTITY_API_URL_ID, ficheFacteurXIII.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return ficheFacteurXIIIRepository.count();
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

    protected FicheFacteurXIII getPersistedFicheFacteurXIII(FicheFacteurXIII ficheFacteurXIII) {
        return ficheFacteurXIIIRepository.findById(ficheFacteurXIII.getId()).orElseThrow();
    }

    protected void assertPersistedFicheFacteurXIIIToMatchAllProperties(FicheFacteurXIII expectedFicheFacteurXIII) {
        assertFicheFacteurXIIIAllPropertiesEquals(expectedFicheFacteurXIII, getPersistedFicheFacteurXIII(expectedFicheFacteurXIII));
    }

    protected void assertPersistedFicheFacteurXIIIToMatchUpdatableProperties(FicheFacteurXIII expectedFicheFacteurXIII) {
        assertFicheFacteurXIIIAllUpdatablePropertiesEquals(
            expectedFicheFacteurXIII,
            getPersistedFicheFacteurXIII(expectedFicheFacteurXIII)
        );
    }
}

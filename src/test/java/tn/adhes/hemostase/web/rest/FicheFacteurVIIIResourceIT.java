package tn.adhes.hemostase.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.adhes.hemostase.domain.FicheFacteurVIIIAsserts.*;
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
import tn.adhes.hemostase.domain.FicheFacteurVIII;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.ExplorationType;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.MutationType;
import tn.adhes.hemostase.domain.enumeration.NormalAllonge;
import tn.adhes.hemostase.domain.enumeration.NormalAllonge;
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
import tn.adhes.hemostase.domain.enumeration.TypeAccidentsThrombotiques;
import tn.adhes.hemostase.domain.enumeration.TypeHemorragieGrave;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.VieSocialeType;
import tn.adhes.hemostase.repository.FicheFacteurVIIIRepository;
import tn.adhes.hemostase.service.dto.FicheFacteurVIIIDTO;
import tn.adhes.hemostase.service.mapper.FicheFacteurVIIIMapper;

/**
 * Integration tests for the {@link FicheFacteurVIIIResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheFacteurVIIIResourceIT {

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

    private static final FaiteNonFaite DEFAULT_ENQUETE_FAMILIALE = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_ENQUETE_FAMILIALE = FaiteNonFaite.NONFAITE;

    private static final Boolean DEFAULT_DECOUVERTE_FORTUITE = false;
    private static final Boolean UPDATED_DECOUVERTE_FORTUITE = true;

    private static final Boolean DEFAULT_ENQUETE_FAMILIALE_BOOL = false;
    private static final Boolean UPDATED_ENQUETE_FAMILIALE_BOOL = true;

    private static final Boolean DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE = false;
    private static final Boolean UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE = true;

    private static final ExplorationType DEFAULT_TYPE_EXPLORATION_SYNDROME_HEMORRAGIQUE = ExplorationType.CUTANEO_MUQUEUX;
    private static final ExplorationType UPDATED_TYPE_EXPLORATION_SYNDROME_HEMORRAGIQUE = ExplorationType.METRORRAGIE;

    private static final LocalDate DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PREMIER_SIGNE_CLINIQUE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_JOUR = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_JOUR = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_MOIS = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_MOIS = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_ANNEE = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_ANNEE = 2;

    private static final Float DEFAULT_TP = 1F;
    private static final Float UPDATED_TP = 2F;

    private static final Float DEFAULT_TP_MT = 1F;
    private static final Float UPDATED_TP_MT = 2F;

    private static final Float DEFAULT_TCA = 1F;
    private static final Float UPDATED_TCA = 2F;

    private static final Float DEFAULT_TCA_MT = 1F;
    private static final Float UPDATED_TCA_MT = 2F;

    private static final Float DEFAULT_FIBRINOGENE = 1F;
    private static final Float UPDATED_FIBRINOGENE = 2F;

    private static final FaiteNonFaite DEFAULT_TS = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_TS = FaiteNonFaite.NONFAITE;

    private static final NormalAllonge DEFAULT_TS_FAIT = NormalAllonge.NORMAL;
    private static final NormalAllonge UPDATED_TS_FAIT = NormalAllonge.ALLONGE;

    private static final NormalAllonge DEFAULT_TO_PFA = NormalAllonge.NORMAL;
    private static final NormalAllonge UPDATED_TO_PFA = NormalAllonge.ALLONGE;

    private static final Float DEFAULT_TO_PFA_200_COL_EPI = 1F;
    private static final Float UPDATED_TO_PFA_200_COL_EPI = 2F;

    private static final Float DEFAULT_TO_PFA_200_COL_ADP = 1F;
    private static final Float UPDATED_TO_PFA_200_COL_ADP = 2F;

    private static final Float DEFAULT_TAUX_DOSAGE_FACTEUR_XIII = 1F;
    private static final Float UPDATED_TAUX_DOSAGE_FACTEUR_XIII = 2F;

    private static final Float DEFAULT_HB = 1F;
    private static final Float UPDATED_HB = 2F;

    private static final Float DEFAULT_HT = 1F;
    private static final Float UPDATED_HT = 2F;

    private static final Integer DEFAULT_PLAQUETTES = 1;
    private static final Integer UPDATED_PLAQUETTES = 2;

    private static final Boolean DEFAULT_TECHNIQUE_CHROMOGENIQUE = false;
    private static final Boolean UPDATED_TECHNIQUE_CHROMOGENIQUE = true;

    private static final Float DEFAULT_TAUX_TECHNIQUE_CHROMOGENIQUE = 1F;
    private static final Float UPDATED_TAUX_TECHNIQUE_CHROMOGENIQUE = 2F;

    private static final Boolean DEFAULT_TECHNIQUE_SOLUBILITE_CAILLOT = false;
    private static final Boolean UPDATED_TECHNIQUE_SOLUBILITE_CAILLOT = true;

    private static final Float DEFAULT_TAUX_TECHNIQUE_SOLUBILITE_CAILLOT = 1F;
    private static final Float UPDATED_TAUX_TECHNIQUE_SOLUBILITE_CAILLOT = 2F;

    private static final FaiteNonFaite DEFAULT_CONFIRMATION_MOLECULAIRE = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_CONFIRMATION_MOLECULAIRE = FaiteNonFaite.NONFAITE;

    private static final Boolean DEFAULT_MUTATION_PRESENCE = false;
    private static final Boolean UPDATED_MUTATION_PRESENCE = true;

    private static final MutationType DEFAULT_MUTATION_TYPE = MutationType.HETEROZYGOTE;
    private static final MutationType UPDATED_MUTATION_TYPE = MutationType.HOMOZYGOTE;

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

    private static final TypeAccidentsThrombotiques DEFAULT_TYPE_ACCIDENTS_THROMBOTIQUES = TypeAccidentsThrombotiques.VEINEUX;
    private static final TypeAccidentsThrombotiques UPDATED_TYPE_ACCIDENTS_THROMBOTIQUES = TypeAccidentsThrombotiques.ARTERIEL;

    private static final Boolean DEFAULT_HEMORRAGIE_GRAVE = false;
    private static final Boolean UPDATED_HEMORRAGIE_GRAVE = true;

    private static final TypeHemorragieGrave DEFAULT_TYPE_HEMORRAGIE_GRAVE = TypeHemorragieGrave.DEGLOBULISATION;
    private static final TypeHemorragieGrave UPDATED_TYPE_HEMORRAGIE_GRAVE = TypeHemorragieGrave.ETAT_DE_CHOC;

    private static final OuiNonNP DEFAULT_CHIRURGIE_PREPARE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CHIRURGIE_PREPARE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SUITE_OPERATOIRE_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SUITE_OPERATOIRE_HEMORRAGIQUE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CIRCONCISION_PREPARE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION_PREPARE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SUITE_OPERATOIRE_CIRCONCISION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SUITE_OPERATOIRE_CIRCONCISION = OuiNonNP.NON;

    private static final Integer DEFAULT_AGE_CIRCONCISION = 1;
    private static final Integer UPDATED_AGE_CIRCONCISION = 2;

    private static final OuiNonNP DEFAULT_FACTEUR_VIII_RECOMBINANT = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_FACTEUR_VIII_RECOMBINANT = OuiNonNP.NON;

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

    private static final Float DEFAULT_TITRE_UB = 1F;
    private static final Float UPDATED_TITRE_UB = 2F;

    private static final Boolean DEFAULT_INHIBITEUR_NON_NEUTRALISANT = false;
    private static final Boolean UPDATED_INHIBITEUR_NON_NEUTRALISANT = true;

    private static final OuiNonNP DEFAULT_INFECTIONS_VIRALES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INFECTIONS_VIRALES = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_THROMBOSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_THROMBOSE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_REACTION_ALLERGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_REACTION_ALLERGIQUE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ALLO_IMMUNISATION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ALLO_IMMUNISATION = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ADHESION_THERAPEUTIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ADHESION_THERAPEUTIQUE = OuiNonNP.NON;

    private static final String DEFAULT_CAUSE_NON_ADHESION = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_NON_ADHESION = "BBBBBBBBBB";

    private static final VieSocialeType DEFAULT_VIE_SOCIALE = VieSocialeType.CHOMEUR;
    private static final VieSocialeType UPDATED_VIE_SOCIALE = VieSocialeType.TRAVAILLE;

    private static final OuiNonNP DEFAULT_MARIAGE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_MARIAGE = OuiNonNP.NON;

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

    private static final Boolean DEFAULT_HEMORRAGIE_SNC = false;
    private static final Boolean UPDATED_HEMORRAGIE_SNC = true;

    private static final Boolean DEFAULT_HEMORRAGIE_AUTRE_QUE_SNC = false;
    private static final Boolean UPDATED_HEMORRAGIE_AUTRE_QUE_SNC = true;

    private static final Boolean DEFAULT_HIV = false;
    private static final Boolean UPDATED_HIV = true;

    private static final Boolean DEFAULT_AUTRE_INFECTION = false;
    private static final Boolean UPDATED_AUTRE_INFECTION = true;

    private static final Boolean DEFAULT_EVENEMENT_THROMBOEMBOLIQUE = false;
    private static final Boolean UPDATED_EVENEMENT_THROMBOEMBOLIQUE = true;

    private static final Boolean DEFAULT_CANCER = false;
    private static final Boolean UPDATED_CANCER = true;

    private static final Boolean DEFAULT_COVID_19 = false;
    private static final Boolean UPDATED_COVID_19 = true;

    private static final Boolean DEFAULT_INCONNUE = false;
    private static final Boolean UPDATED_INCONNUE = true;

    private static final Boolean DEFAULT_AUTRE_EVENEMENT = false;
    private static final Boolean UPDATED_AUTRE_EVENEMENT = true;

    private static final String DEFAULT_AUTRE_EVENEMENT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_EVENEMENT_DETAILS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/fiche-facteur-viiis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FicheFacteurVIIIRepository ficheFacteurVIIIRepository;

    @Autowired
    private FicheFacteurVIIIMapper ficheFacteurVIIIMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheFacteurVIIIMockMvc;

    private FicheFacteurVIII ficheFacteurVIII;

    private FicheFacteurVIII insertedFicheFacteurVIII;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheFacteurVIII createEntity() {
        return new FicheFacteurVIII()
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
            .enqueteFamilialeBool(DEFAULT_ENQUETE_FAMILIALE_BOOL)
            .explorationSyndromeHemorragique(DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .typeExplorationSyndromeHemorragique(DEFAULT_TYPE_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .datePremierSigneClinique(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE)
            .ageDiagnosticJour(DEFAULT_AGE_DIAGNOSTIC_JOUR)
            .ageDiagnosticMois(DEFAULT_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnee(DEFAULT_AGE_DIAGNOSTIC_ANNEE)
            .tp(DEFAULT_TP)
            .tpMT(DEFAULT_TP_MT)
            .tca(DEFAULT_TCA)
            .tcaMT(DEFAULT_TCA_MT)
            .fibrinogene(DEFAULT_FIBRINOGENE)
            .ts(DEFAULT_TS)
            .tsFait(DEFAULT_TS_FAIT)
            .toPfa(DEFAULT_TO_PFA)
            .toPfa200ColEpi(DEFAULT_TO_PFA_200_COL_EPI)
            .toPfa200ColAdp(DEFAULT_TO_PFA_200_COL_ADP)
            .tauxDosageFacteurXIII(DEFAULT_TAUX_DOSAGE_FACTEUR_XIII)
            .hb(DEFAULT_HB)
            .ht(DEFAULT_HT)
            .plaquettes(DEFAULT_PLAQUETTES)
            .techniqueChromogenique(DEFAULT_TECHNIQUE_CHROMOGENIQUE)
            .tauxTechniqueChromogenique(DEFAULT_TAUX_TECHNIQUE_CHROMOGENIQUE)
            .techniqueSolubiliteCaillot(DEFAULT_TECHNIQUE_SOLUBILITE_CAILLOT)
            .tauxTechniqueSolubiliteCaillot(DEFAULT_TAUX_TECHNIQUE_SOLUBILITE_CAILLOT)
            .confirmationMoleculaire(DEFAULT_CONFIRMATION_MOLECULAIRE)
            .mutationPresence(DEFAULT_MUTATION_PRESENCE)
            .mutationType(DEFAULT_MUTATION_TYPE)
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
            .typeHemorragieGrave(DEFAULT_TYPE_HEMORRAGIE_GRAVE)
            .chirurgiePrepare(DEFAULT_CHIRURGIE_PREPARE)
            .suiteOperatoireHemorragique(DEFAULT_SUITE_OPERATOIRE_HEMORRAGIQUE)
            .circoncisionPrepare(DEFAULT_CIRCONCISION_PREPARE)
            .suiteOperatoireCirconcision(DEFAULT_SUITE_OPERATOIRE_CIRCONCISION)
            .ageCirconcision(DEFAULT_AGE_CIRCONCISION)
            .facteurVIIIRecombinant(DEFAULT_FACTEUR_VIII_RECOMBINANT)
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
            .titreUB(DEFAULT_TITRE_UB)
            .inhibiteurNonNeutralisant(DEFAULT_INHIBITEUR_NON_NEUTRALISANT)
            .infectionsVirales(DEFAULT_INFECTIONS_VIRALES)
            .thrombose(DEFAULT_THROMBOSE)
            .reactionAllergique(DEFAULT_REACTION_ALLERGIQUE)
            .alloImmunisation(DEFAULT_ALLO_IMMUNISATION)
            .adhesionTherapeutique(DEFAULT_ADHESION_THERAPEUTIQUE)
            .causeNonAdhesion(DEFAULT_CAUSE_NON_ADHESION)
            .vieSociale(DEFAULT_VIE_SOCIALE)
            .mariage(DEFAULT_MARIAGE)
            .enfants(DEFAULT_ENFANTS)
            .activitePhysique(DEFAULT_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(DEFAULT_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(DEFAULT_SEQUELLES)
            .typeSequelles(DEFAULT_TYPE_SEQUELLES)
            .patientDecede(DEFAULT_PATIENT_DECEDE)
            .dateDeces(DEFAULT_DATE_DECES)
            .ageDeces(DEFAULT_AGE_DECES)
            .hemorragieSnc(DEFAULT_HEMORRAGIE_SNC)
            .hemorragieAutreQueSnc(DEFAULT_HEMORRAGIE_AUTRE_QUE_SNC)
            .hiv(DEFAULT_HIV)
            .autreInfection(DEFAULT_AUTRE_INFECTION)
            .evenementThromboembolique(DEFAULT_EVENEMENT_THROMBOEMBOLIQUE)
            .cancer(DEFAULT_CANCER)
            .covid19(DEFAULT_COVID_19)
            .inconnue(DEFAULT_INCONNUE)
            .autreEvenement(DEFAULT_AUTRE_EVENEMENT)
            .autreEvenementDetails(DEFAULT_AUTRE_EVENEMENT_DETAILS);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheFacteurVIII createUpdatedEntity() {
        return new FicheFacteurVIII()
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
            .enqueteFamilialeBool(UPDATED_ENQUETE_FAMILIALE_BOOL)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .typeExplorationSyndromeHemorragique(UPDATED_TYPE_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .datePremierSigneClinique(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE)
            .ageDiagnosticJour(UPDATED_AGE_DIAGNOSTIC_JOUR)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnee(UPDATED_AGE_DIAGNOSTIC_ANNEE)
            .tp(UPDATED_TP)
            .tpMT(UPDATED_TP_MT)
            .tca(UPDATED_TCA)
            .tcaMT(UPDATED_TCA_MT)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .ts(UPDATED_TS)
            .tsFait(UPDATED_TS_FAIT)
            .toPfa(UPDATED_TO_PFA)
            .toPfa200ColEpi(UPDATED_TO_PFA_200_COL_EPI)
            .toPfa200ColAdp(UPDATED_TO_PFA_200_COL_ADP)
            .tauxDosageFacteurXIII(UPDATED_TAUX_DOSAGE_FACTEUR_XIII)
            .hb(UPDATED_HB)
            .ht(UPDATED_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .techniqueChromogenique(UPDATED_TECHNIQUE_CHROMOGENIQUE)
            .tauxTechniqueChromogenique(UPDATED_TAUX_TECHNIQUE_CHROMOGENIQUE)
            .techniqueSolubiliteCaillot(UPDATED_TECHNIQUE_SOLUBILITE_CAILLOT)
            .tauxTechniqueSolubiliteCaillot(UPDATED_TAUX_TECHNIQUE_SOLUBILITE_CAILLOT)
            .confirmationMoleculaire(UPDATED_CONFIRMATION_MOLECULAIRE)
            .mutationPresence(UPDATED_MUTATION_PRESENCE)
            .mutationType(UPDATED_MUTATION_TYPE)
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
            .typeHemorragieGrave(UPDATED_TYPE_HEMORRAGIE_GRAVE)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .suiteOperatoireHemorragique(UPDATED_SUITE_OPERATOIRE_HEMORRAGIQUE)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .suiteOperatoireCirconcision(UPDATED_SUITE_OPERATOIRE_CIRCONCISION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .facteurVIIIRecombinant(UPDATED_FACTEUR_VIII_RECOMBINANT)
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
            .titreUB(UPDATED_TITRE_UB)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .infectionsVirales(UPDATED_INFECTIONS_VIRALES)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .alloImmunisation(UPDATED_ALLO_IMMUNISATION)
            .adhesionTherapeutique(UPDATED_ADHESION_THERAPEUTIQUE)
            .causeNonAdhesion(UPDATED_CAUSE_NON_ADHESION)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .mariage(UPDATED_MARIAGE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .hemorragieSnc(UPDATED_HEMORRAGIE_SNC)
            .hemorragieAutreQueSnc(UPDATED_HEMORRAGIE_AUTRE_QUE_SNC)
            .hiv(UPDATED_HIV)
            .autreInfection(UPDATED_AUTRE_INFECTION)
            .evenementThromboembolique(UPDATED_EVENEMENT_THROMBOEMBOLIQUE)
            .cancer(UPDATED_CANCER)
            .covid19(UPDATED_COVID_19)
            .inconnue(UPDATED_INCONNUE)
            .autreEvenement(UPDATED_AUTRE_EVENEMENT)
            .autreEvenementDetails(UPDATED_AUTRE_EVENEMENT_DETAILS);
    }

    @BeforeEach
    public void initTest() {
        ficheFacteurVIII = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedFicheFacteurVIII != null) {
            ficheFacteurVIIIRepository.delete(insertedFicheFacteurVIII);
            insertedFicheFacteurVIII = null;
        }
    }

    @Test
    @Transactional
    void createFicheFacteurVIII() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FicheFacteurVIII
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);
        var returnedFicheFacteurVIIIDTO = om.readValue(
            restFicheFacteurVIIIMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurVIIIDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FicheFacteurVIIIDTO.class
        );

        // Validate the FicheFacteurVIII in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFicheFacteurVIII = ficheFacteurVIIIMapper.toEntity(returnedFicheFacteurVIIIDTO);
        assertFicheFacteurVIIIUpdatableFieldsEquals(returnedFicheFacteurVIII, getPersistedFicheFacteurVIII(returnedFicheFacteurVIII));

        insertedFicheFacteurVIII = returnedFicheFacteurVIII;
    }

    @Test
    @Transactional
    void createFicheFacteurVIIIWithExistingId() throws Exception {
        // Create the FicheFacteurVIII with an existing ID
        ficheFacteurVIII.setId(1L);
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheFacteurVIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurVIIIDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurVIII in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEtatCivilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurVIII.setEtatCivil(null);

        // Create the FicheFacteurVIII, which fails.
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        restFicheFacteurVIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurVIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkServiceCliniqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurVIII.setServiceClinique(null);

        // Create the FicheFacteurVIII, which fails.
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        restFicheFacteurVIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurVIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCouvertureSocialeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurVIII.setCouvertureSociale(null);

        // Create the FicheFacteurVIII, which fails.
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        restFicheFacteurVIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurVIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSexeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurVIII.setSexe(null);

        // Create the FicheFacteurVIII, which fails.
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        restFicheFacteurVIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurVIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiagnosticIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurVIII.setDiagnostic(null);

        // Create the FicheFacteurVIII, which fails.
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        restFicheFacteurVIIIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurVIIIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFicheFacteurVIIIS() throws Exception {
        // Initialize the database
        insertedFicheFacteurVIII = ficheFacteurVIIIRepository.saveAndFlush(ficheFacteurVIII);

        // Get all the ficheFacteurVIIIList
        restFicheFacteurVIIIMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheFacteurVIII.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].enqueteFamiliale").value(hasItem(DEFAULT_ENQUETE_FAMILIALE.toString())))
            .andExpect(jsonPath("$.[*].decouverteFortuite").value(hasItem(DEFAULT_DECOUVERTE_FORTUITE.booleanValue())))
            .andExpect(jsonPath("$.[*].enqueteFamilialeBool").value(hasItem(DEFAULT_ENQUETE_FAMILIALE_BOOL.booleanValue())))
            .andExpect(
                jsonPath("$.[*].explorationSyndromeHemorragique").value(hasItem(DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE.booleanValue()))
            )
            .andExpect(
                jsonPath("$.[*].typeExplorationSyndromeHemorragique").value(
                    hasItem(DEFAULT_TYPE_EXPLORATION_SYNDROME_HEMORRAGIQUE.toString())
                )
            )
            .andExpect(jsonPath("$.[*].datePremierSigneClinique").value(hasItem(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE.toString())))
            .andExpect(jsonPath("$.[*].ageDiagnosticJour").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_JOUR)))
            .andExpect(jsonPath("$.[*].ageDiagnosticMois").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_MOIS)))
            .andExpect(jsonPath("$.[*].ageDiagnosticAnnee").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_ANNEE)))
            .andExpect(jsonPath("$.[*].tp").value(hasItem(DEFAULT_TP.doubleValue())))
            .andExpect(jsonPath("$.[*].tpMT").value(hasItem(DEFAULT_TP_MT.doubleValue())))
            .andExpect(jsonPath("$.[*].tca").value(hasItem(DEFAULT_TCA.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMT").value(hasItem(DEFAULT_TCA_MT.doubleValue())))
            .andExpect(jsonPath("$.[*].fibrinogene").value(hasItem(DEFAULT_FIBRINOGENE.doubleValue())))
            .andExpect(jsonPath("$.[*].ts").value(hasItem(DEFAULT_TS.toString())))
            .andExpect(jsonPath("$.[*].tsFait").value(hasItem(DEFAULT_TS_FAIT.toString())))
            .andExpect(jsonPath("$.[*].toPfa").value(hasItem(DEFAULT_TO_PFA.toString())))
            .andExpect(jsonPath("$.[*].toPfa200ColEpi").value(hasItem(DEFAULT_TO_PFA_200_COL_EPI.doubleValue())))
            .andExpect(jsonPath("$.[*].toPfa200ColAdp").value(hasItem(DEFAULT_TO_PFA_200_COL_ADP.doubleValue())))
            .andExpect(jsonPath("$.[*].tauxDosageFacteurXIII").value(hasItem(DEFAULT_TAUX_DOSAGE_FACTEUR_XIII.doubleValue())))
            .andExpect(jsonPath("$.[*].hb").value(hasItem(DEFAULT_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].ht").value(hasItem(DEFAULT_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].plaquettes").value(hasItem(DEFAULT_PLAQUETTES)))
            .andExpect(jsonPath("$.[*].techniqueChromogenique").value(hasItem(DEFAULT_TECHNIQUE_CHROMOGENIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].tauxTechniqueChromogenique").value(hasItem(DEFAULT_TAUX_TECHNIQUE_CHROMOGENIQUE.doubleValue())))
            .andExpect(jsonPath("$.[*].techniqueSolubiliteCaillot").value(hasItem(DEFAULT_TECHNIQUE_SOLUBILITE_CAILLOT.booleanValue())))
            .andExpect(
                jsonPath("$.[*].tauxTechniqueSolubiliteCaillot").value(hasItem(DEFAULT_TAUX_TECHNIQUE_SOLUBILITE_CAILLOT.doubleValue()))
            )
            .andExpect(jsonPath("$.[*].confirmationMoleculaire").value(hasItem(DEFAULT_CONFIRMATION_MOLECULAIRE.toString())))
            .andExpect(jsonPath("$.[*].mutationPresence").value(hasItem(DEFAULT_MUTATION_PRESENCE.booleanValue())))
            .andExpect(jsonPath("$.[*].mutationType").value(hasItem(DEFAULT_MUTATION_TYPE.toString())))
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
            .andExpect(jsonPath("$.[*].hemorragieGrave").value(hasItem(DEFAULT_HEMORRAGIE_GRAVE.booleanValue())))
            .andExpect(jsonPath("$.[*].typeHemorragieGrave").value(hasItem(DEFAULT_TYPE_HEMORRAGIE_GRAVE.toString())))
            .andExpect(jsonPath("$.[*].chirurgiePrepare").value(hasItem(DEFAULT_CHIRURGIE_PREPARE.toString())))
            .andExpect(jsonPath("$.[*].suiteOperatoireHemorragique").value(hasItem(DEFAULT_SUITE_OPERATOIRE_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].circoncisionPrepare").value(hasItem(DEFAULT_CIRCONCISION_PREPARE.toString())))
            .andExpect(jsonPath("$.[*].suiteOperatoireCirconcision").value(hasItem(DEFAULT_SUITE_OPERATOIRE_CIRCONCISION.toString())))
            .andExpect(jsonPath("$.[*].ageCirconcision").value(hasItem(DEFAULT_AGE_CIRCONCISION)))
            .andExpect(jsonPath("$.[*].facteurVIIIRecombinant").value(hasItem(DEFAULT_FACTEUR_VIII_RECOMBINANT.toString())))
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
            .andExpect(jsonPath("$.[*].titreUB").value(hasItem(DEFAULT_TITRE_UB.doubleValue())))
            .andExpect(jsonPath("$.[*].inhibiteurNonNeutralisant").value(hasItem(DEFAULT_INHIBITEUR_NON_NEUTRALISANT.booleanValue())))
            .andExpect(jsonPath("$.[*].infectionsVirales").value(hasItem(DEFAULT_INFECTIONS_VIRALES.toString())))
            .andExpect(jsonPath("$.[*].thrombose").value(hasItem(DEFAULT_THROMBOSE.toString())))
            .andExpect(jsonPath("$.[*].reactionAllergique").value(hasItem(DEFAULT_REACTION_ALLERGIQUE.toString())))
            .andExpect(jsonPath("$.[*].alloImmunisation").value(hasItem(DEFAULT_ALLO_IMMUNISATION.toString())))
            .andExpect(jsonPath("$.[*].adhesionTherapeutique").value(hasItem(DEFAULT_ADHESION_THERAPEUTIQUE.toString())))
            .andExpect(jsonPath("$.[*].causeNonAdhesion").value(hasItem(DEFAULT_CAUSE_NON_ADHESION)))
            .andExpect(jsonPath("$.[*].vieSociale").value(hasItem(DEFAULT_VIE_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].mariage").value(hasItem(DEFAULT_MARIAGE.toString())))
            .andExpect(jsonPath("$.[*].enfants").value(hasItem(DEFAULT_ENFANTS.toString())))
            .andExpect(jsonPath("$.[*].activitePhysique").value(hasItem(DEFAULT_ACTIVITE_PHYSIQUE.toString())))
            .andExpect(jsonPath("$.[*].typeActivitePhysique").value(hasItem(DEFAULT_TYPE_ACTIVITE_PHYSIQUE)))
            .andExpect(jsonPath("$.[*].sequelles").value(hasItem(DEFAULT_SEQUELLES.toString())))
            .andExpect(jsonPath("$.[*].typeSequelles").value(hasItem(DEFAULT_TYPE_SEQUELLES)))
            .andExpect(jsonPath("$.[*].patientDecede").value(hasItem(DEFAULT_PATIENT_DECEDE.toString())))
            .andExpect(jsonPath("$.[*].dateDeces").value(hasItem(DEFAULT_DATE_DECES.toString())))
            .andExpect(jsonPath("$.[*].ageDeces").value(hasItem(DEFAULT_AGE_DECES)))
            .andExpect(jsonPath("$.[*].hemorragieSnc").value(hasItem(DEFAULT_HEMORRAGIE_SNC.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragieAutreQueSnc").value(hasItem(DEFAULT_HEMORRAGIE_AUTRE_QUE_SNC.booleanValue())))
            .andExpect(jsonPath("$.[*].hiv").value(hasItem(DEFAULT_HIV.booleanValue())))
            .andExpect(jsonPath("$.[*].autreInfection").value(hasItem(DEFAULT_AUTRE_INFECTION.booleanValue())))
            .andExpect(jsonPath("$.[*].evenementThromboembolique").value(hasItem(DEFAULT_EVENEMENT_THROMBOEMBOLIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].cancer").value(hasItem(DEFAULT_CANCER.booleanValue())))
            .andExpect(jsonPath("$.[*].covid19").value(hasItem(DEFAULT_COVID_19.booleanValue())))
            .andExpect(jsonPath("$.[*].inconnue").value(hasItem(DEFAULT_INCONNUE.booleanValue())))
            .andExpect(jsonPath("$.[*].autreEvenement").value(hasItem(DEFAULT_AUTRE_EVENEMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].autreEvenementDetails").value(hasItem(DEFAULT_AUTRE_EVENEMENT_DETAILS)));
    }

    @Test
    @Transactional
    void getFicheFacteurVIII() throws Exception {
        // Initialize the database
        insertedFicheFacteurVIII = ficheFacteurVIIIRepository.saveAndFlush(ficheFacteurVIII);

        // Get the ficheFacteurVIII
        restFicheFacteurVIIIMockMvc
            .perform(get(ENTITY_API_URL_ID, ficheFacteurVIII.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ficheFacteurVIII.getId().intValue()))
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
            .andExpect(jsonPath("$.enqueteFamiliale").value(DEFAULT_ENQUETE_FAMILIALE.toString()))
            .andExpect(jsonPath("$.decouverteFortuite").value(DEFAULT_DECOUVERTE_FORTUITE.booleanValue()))
            .andExpect(jsonPath("$.enqueteFamilialeBool").value(DEFAULT_ENQUETE_FAMILIALE_BOOL.booleanValue()))
            .andExpect(jsonPath("$.explorationSyndromeHemorragique").value(DEFAULT_EXPLORATION_SYNDROME_HEMORRAGIQUE.booleanValue()))
            .andExpect(jsonPath("$.typeExplorationSyndromeHemorragique").value(DEFAULT_TYPE_EXPLORATION_SYNDROME_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.datePremierSigneClinique").value(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE.toString()))
            .andExpect(jsonPath("$.ageDiagnosticJour").value(DEFAULT_AGE_DIAGNOSTIC_JOUR))
            .andExpect(jsonPath("$.ageDiagnosticMois").value(DEFAULT_AGE_DIAGNOSTIC_MOIS))
            .andExpect(jsonPath("$.ageDiagnosticAnnee").value(DEFAULT_AGE_DIAGNOSTIC_ANNEE))
            .andExpect(jsonPath("$.tp").value(DEFAULT_TP.doubleValue()))
            .andExpect(jsonPath("$.tpMT").value(DEFAULT_TP_MT.doubleValue()))
            .andExpect(jsonPath("$.tca").value(DEFAULT_TCA.doubleValue()))
            .andExpect(jsonPath("$.tcaMT").value(DEFAULT_TCA_MT.doubleValue()))
            .andExpect(jsonPath("$.fibrinogene").value(DEFAULT_FIBRINOGENE.doubleValue()))
            .andExpect(jsonPath("$.ts").value(DEFAULT_TS.toString()))
            .andExpect(jsonPath("$.tsFait").value(DEFAULT_TS_FAIT.toString()))
            .andExpect(jsonPath("$.toPfa").value(DEFAULT_TO_PFA.toString()))
            .andExpect(jsonPath("$.toPfa200ColEpi").value(DEFAULT_TO_PFA_200_COL_EPI.doubleValue()))
            .andExpect(jsonPath("$.toPfa200ColAdp").value(DEFAULT_TO_PFA_200_COL_ADP.doubleValue()))
            .andExpect(jsonPath("$.tauxDosageFacteurXIII").value(DEFAULT_TAUX_DOSAGE_FACTEUR_XIII.doubleValue()))
            .andExpect(jsonPath("$.hb").value(DEFAULT_HB.doubleValue()))
            .andExpect(jsonPath("$.ht").value(DEFAULT_HT.doubleValue()))
            .andExpect(jsonPath("$.plaquettes").value(DEFAULT_PLAQUETTES))
            .andExpect(jsonPath("$.techniqueChromogenique").value(DEFAULT_TECHNIQUE_CHROMOGENIQUE.booleanValue()))
            .andExpect(jsonPath("$.tauxTechniqueChromogenique").value(DEFAULT_TAUX_TECHNIQUE_CHROMOGENIQUE.doubleValue()))
            .andExpect(jsonPath("$.techniqueSolubiliteCaillot").value(DEFAULT_TECHNIQUE_SOLUBILITE_CAILLOT.booleanValue()))
            .andExpect(jsonPath("$.tauxTechniqueSolubiliteCaillot").value(DEFAULT_TAUX_TECHNIQUE_SOLUBILITE_CAILLOT.doubleValue()))
            .andExpect(jsonPath("$.confirmationMoleculaire").value(DEFAULT_CONFIRMATION_MOLECULAIRE.toString()))
            .andExpect(jsonPath("$.mutationPresence").value(DEFAULT_MUTATION_PRESENCE.booleanValue()))
            .andExpect(jsonPath("$.mutationType").value(DEFAULT_MUTATION_TYPE.toString()))
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
            .andExpect(jsonPath("$.hemorragieGrave").value(DEFAULT_HEMORRAGIE_GRAVE.booleanValue()))
            .andExpect(jsonPath("$.typeHemorragieGrave").value(DEFAULT_TYPE_HEMORRAGIE_GRAVE.toString()))
            .andExpect(jsonPath("$.chirurgiePrepare").value(DEFAULT_CHIRURGIE_PREPARE.toString()))
            .andExpect(jsonPath("$.suiteOperatoireHemorragique").value(DEFAULT_SUITE_OPERATOIRE_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.circoncisionPrepare").value(DEFAULT_CIRCONCISION_PREPARE.toString()))
            .andExpect(jsonPath("$.suiteOperatoireCirconcision").value(DEFAULT_SUITE_OPERATOIRE_CIRCONCISION.toString()))
            .andExpect(jsonPath("$.ageCirconcision").value(DEFAULT_AGE_CIRCONCISION))
            .andExpect(jsonPath("$.facteurVIIIRecombinant").value(DEFAULT_FACTEUR_VIII_RECOMBINANT.toString()))
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
            .andExpect(jsonPath("$.titreUB").value(DEFAULT_TITRE_UB.doubleValue()))
            .andExpect(jsonPath("$.inhibiteurNonNeutralisant").value(DEFAULT_INHIBITEUR_NON_NEUTRALISANT.booleanValue()))
            .andExpect(jsonPath("$.infectionsVirales").value(DEFAULT_INFECTIONS_VIRALES.toString()))
            .andExpect(jsonPath("$.thrombose").value(DEFAULT_THROMBOSE.toString()))
            .andExpect(jsonPath("$.reactionAllergique").value(DEFAULT_REACTION_ALLERGIQUE.toString()))
            .andExpect(jsonPath("$.alloImmunisation").value(DEFAULT_ALLO_IMMUNISATION.toString()))
            .andExpect(jsonPath("$.adhesionTherapeutique").value(DEFAULT_ADHESION_THERAPEUTIQUE.toString()))
            .andExpect(jsonPath("$.causeNonAdhesion").value(DEFAULT_CAUSE_NON_ADHESION))
            .andExpect(jsonPath("$.vieSociale").value(DEFAULT_VIE_SOCIALE.toString()))
            .andExpect(jsonPath("$.mariage").value(DEFAULT_MARIAGE.toString()))
            .andExpect(jsonPath("$.enfants").value(DEFAULT_ENFANTS.toString()))
            .andExpect(jsonPath("$.activitePhysique").value(DEFAULT_ACTIVITE_PHYSIQUE.toString()))
            .andExpect(jsonPath("$.typeActivitePhysique").value(DEFAULT_TYPE_ACTIVITE_PHYSIQUE))
            .andExpect(jsonPath("$.sequelles").value(DEFAULT_SEQUELLES.toString()))
            .andExpect(jsonPath("$.typeSequelles").value(DEFAULT_TYPE_SEQUELLES))
            .andExpect(jsonPath("$.patientDecede").value(DEFAULT_PATIENT_DECEDE.toString()))
            .andExpect(jsonPath("$.dateDeces").value(DEFAULT_DATE_DECES.toString()))
            .andExpect(jsonPath("$.ageDeces").value(DEFAULT_AGE_DECES))
            .andExpect(jsonPath("$.hemorragieSnc").value(DEFAULT_HEMORRAGIE_SNC.booleanValue()))
            .andExpect(jsonPath("$.hemorragieAutreQueSnc").value(DEFAULT_HEMORRAGIE_AUTRE_QUE_SNC.booleanValue()))
            .andExpect(jsonPath("$.hiv").value(DEFAULT_HIV.booleanValue()))
            .andExpect(jsonPath("$.autreInfection").value(DEFAULT_AUTRE_INFECTION.booleanValue()))
            .andExpect(jsonPath("$.evenementThromboembolique").value(DEFAULT_EVENEMENT_THROMBOEMBOLIQUE.booleanValue()))
            .andExpect(jsonPath("$.cancer").value(DEFAULT_CANCER.booleanValue()))
            .andExpect(jsonPath("$.covid19").value(DEFAULT_COVID_19.booleanValue()))
            .andExpect(jsonPath("$.inconnue").value(DEFAULT_INCONNUE.booleanValue()))
            .andExpect(jsonPath("$.autreEvenement").value(DEFAULT_AUTRE_EVENEMENT.booleanValue()))
            .andExpect(jsonPath("$.autreEvenementDetails").value(DEFAULT_AUTRE_EVENEMENT_DETAILS));
    }

    @Test
    @Transactional
    void getNonExistingFicheFacteurVIII() throws Exception {
        // Get the ficheFacteurVIII
        restFicheFacteurVIIIMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFicheFacteurVIII() throws Exception {
        // Initialize the database
        insertedFicheFacteurVIII = ficheFacteurVIIIRepository.saveAndFlush(ficheFacteurVIII);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheFacteurVIII
        FicheFacteurVIII updatedFicheFacteurVIII = ficheFacteurVIIIRepository.findById(ficheFacteurVIII.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFicheFacteurVIII are not directly saved in db
        em.detach(updatedFicheFacteurVIII);
        updatedFicheFacteurVIII
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
            .enqueteFamilialeBool(UPDATED_ENQUETE_FAMILIALE_BOOL)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .typeExplorationSyndromeHemorragique(UPDATED_TYPE_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .datePremierSigneClinique(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE)
            .ageDiagnosticJour(UPDATED_AGE_DIAGNOSTIC_JOUR)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnee(UPDATED_AGE_DIAGNOSTIC_ANNEE)
            .tp(UPDATED_TP)
            .tpMT(UPDATED_TP_MT)
            .tca(UPDATED_TCA)
            .tcaMT(UPDATED_TCA_MT)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .ts(UPDATED_TS)
            .tsFait(UPDATED_TS_FAIT)
            .toPfa(UPDATED_TO_PFA)
            .toPfa200ColEpi(UPDATED_TO_PFA_200_COL_EPI)
            .toPfa200ColAdp(UPDATED_TO_PFA_200_COL_ADP)
            .tauxDosageFacteurXIII(UPDATED_TAUX_DOSAGE_FACTEUR_XIII)
            .hb(UPDATED_HB)
            .ht(UPDATED_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .techniqueChromogenique(UPDATED_TECHNIQUE_CHROMOGENIQUE)
            .tauxTechniqueChromogenique(UPDATED_TAUX_TECHNIQUE_CHROMOGENIQUE)
            .techniqueSolubiliteCaillot(UPDATED_TECHNIQUE_SOLUBILITE_CAILLOT)
            .tauxTechniqueSolubiliteCaillot(UPDATED_TAUX_TECHNIQUE_SOLUBILITE_CAILLOT)
            .confirmationMoleculaire(UPDATED_CONFIRMATION_MOLECULAIRE)
            .mutationPresence(UPDATED_MUTATION_PRESENCE)
            .mutationType(UPDATED_MUTATION_TYPE)
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
            .typeHemorragieGrave(UPDATED_TYPE_HEMORRAGIE_GRAVE)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .suiteOperatoireHemorragique(UPDATED_SUITE_OPERATOIRE_HEMORRAGIQUE)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .suiteOperatoireCirconcision(UPDATED_SUITE_OPERATOIRE_CIRCONCISION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .facteurVIIIRecombinant(UPDATED_FACTEUR_VIII_RECOMBINANT)
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
            .titreUB(UPDATED_TITRE_UB)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .infectionsVirales(UPDATED_INFECTIONS_VIRALES)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .alloImmunisation(UPDATED_ALLO_IMMUNISATION)
            .adhesionTherapeutique(UPDATED_ADHESION_THERAPEUTIQUE)
            .causeNonAdhesion(UPDATED_CAUSE_NON_ADHESION)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .mariage(UPDATED_MARIAGE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .hemorragieSnc(UPDATED_HEMORRAGIE_SNC)
            .hemorragieAutreQueSnc(UPDATED_HEMORRAGIE_AUTRE_QUE_SNC)
            .hiv(UPDATED_HIV)
            .autreInfection(UPDATED_AUTRE_INFECTION)
            .evenementThromboembolique(UPDATED_EVENEMENT_THROMBOEMBOLIQUE)
            .cancer(UPDATED_CANCER)
            .covid19(UPDATED_COVID_19)
            .inconnue(UPDATED_INCONNUE)
            .autreEvenement(UPDATED_AUTRE_EVENEMENT)
            .autreEvenementDetails(UPDATED_AUTRE_EVENEMENT_DETAILS);
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(updatedFicheFacteurVIII);

        restFicheFacteurVIIIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheFacteurVIIIDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheFacteurVIIIDTO))
            )
            .andExpect(status().isOk());

        // Validate the FicheFacteurVIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFicheFacteurVIIIToMatchAllProperties(updatedFicheFacteurVIII);
    }

    @Test
    @Transactional
    void putNonExistingFicheFacteurVIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurVIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurVIII
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheFacteurVIIIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheFacteurVIIIDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheFacteurVIIIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurVIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFicheFacteurVIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurVIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurVIII
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurVIIIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheFacteurVIIIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurVIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFicheFacteurVIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurVIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurVIII
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurVIIIMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurVIIIDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheFacteurVIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFicheFacteurVIIIWithPatch() throws Exception {
        // Initialize the database
        insertedFicheFacteurVIII = ficheFacteurVIIIRepository.saveAndFlush(ficheFacteurVIII);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheFacteurVIII using partial update
        FicheFacteurVIII partialUpdatedFicheFacteurVIII = new FicheFacteurVIII();
        partialUpdatedFicheFacteurVIII.setId(ficheFacteurVIII.getId());

        partialUpdatedFicheFacteurVIII
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .profession(UPDATED_PROFESSION)
            .sexe(UPDATED_SEXE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .origine(UPDATED_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .adresse(UPDATED_ADRESSE)
            .prenomPere(UPDATED_PRENOM_PERE)
            .nomPrenomMere(UPDATED_NOM_PRENOM_MERE)
            .medecinTraitant(UPDATED_MEDECIN_TRAITANT)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .casSimilaires(UPDATED_CAS_SIMILAIRES)
            .nombreCas(UPDATED_NOMBRE_CAS)
            .cas1Numero(UPDATED_CAS_1_NUMERO)
            .degreParenteCas1(UPDATED_DEGRE_PARENTE_CAS_1)
            .lienParenteCas2(UPDATED_LIEN_PARENTE_CAS_2)
            .decesSyndromeHemorragique(UPDATED_DECES_SYNDROME_HEMORRAGIQUE)
            .causeDeces1(UPDATED_CAUSE_DECES_1)
            .causeDeces2(UPDATED_CAUSE_DECES_2)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .enqueteFamilialeBool(UPDATED_ENQUETE_FAMILIALE_BOOL)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .typeExplorationSyndromeHemorragique(UPDATED_TYPE_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .ageDiagnosticAnnee(UPDATED_AGE_DIAGNOSTIC_ANNEE)
            .tca(UPDATED_TCA)
            .tcaMT(UPDATED_TCA_MT)
            .ts(UPDATED_TS)
            .toPfa(UPDATED_TO_PFA)
            .toPfa200ColAdp(UPDATED_TO_PFA_200_COL_ADP)
            .tauxDosageFacteurXIII(UPDATED_TAUX_DOSAGE_FACTEUR_XIII)
            .ht(UPDATED_HT)
            .techniqueSolubiliteCaillot(UPDATED_TECHNIQUE_SOLUBILITE_CAILLOT)
            .tauxTechniqueSolubiliteCaillot(UPDATED_TAUX_TECHNIQUE_SOLUBILITE_CAILLOT)
            .mutationPresence(UPDATED_MUTATION_PRESENCE)
            .gingivorragies(UPDATED_GINGIVORRAGIES)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .ecchymose(UPDATED_ECCHYMOSE)
            .typeEcchymose(UPDATED_TYPE_ECCHYMOSE)
            .frequenceEcchymose(UPDATED_FREQUENCE_ECCHYMOSE)
            .menorragie(UPDATED_MENORRAGIE)
            .frequenceMenorragie(UPDATED_FREQUENCE_MENORRAGIE)
            .rectorragie(UPDATED_RECTORRAGIE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .typeHematomeMusculaire(UPDATED_TYPE_HEMATOME_MUSCULAIRE)
            .frequenceHematomeMusculaire(UPDATED_FREQUENCE_HEMATOME_MUSCULAIRE)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .ruptureKystesHemorragiques(UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES)
            .hemorragiesPostChirurgicales(UPDATED_HEMORRAGIES_POST_CHIRURGICALES)
            .hemoperitone(UPDATED_HEMOPERITONE)
            .typeAccidentsThrombotiques(UPDATED_TYPE_ACCIDENTS_THROMBOTIQUES)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .suiteOperatoireHemorragique(UPDATED_SUITE_OPERATOIRE_HEMORRAGIQUE)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .facteurVIIIRecombinant(UPDATED_FACTEUR_VIII_RECOMBINANT)
            .culotsPlaquettaires(UPDATED_CULOTS_PLAQUETTAIRES)
            .dicynone(UPDATED_DICYNONE)
            .exacyl(UPDATED_EXACYL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .transfusionCGR(UPDATED_TRANSFUSION_CGR)
            .inhibiteurs(UPDATED_INHIBITEURS)
            .titreUB(UPDATED_TITRE_UB)
            .infectionsVirales(UPDATED_INFECTIONS_VIRALES)
            .adhesionTherapeutique(UPDATED_ADHESION_THERAPEUTIQUE)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .hemorragieSnc(UPDATED_HEMORRAGIE_SNC)
            .hemorragieAutreQueSnc(UPDATED_HEMORRAGIE_AUTRE_QUE_SNC)
            .hiv(UPDATED_HIV)
            .cancer(UPDATED_CANCER)
            .covid19(UPDATED_COVID_19)
            .inconnue(UPDATED_INCONNUE)
            .autreEvenement(UPDATED_AUTRE_EVENEMENT)
            .autreEvenementDetails(UPDATED_AUTRE_EVENEMENT_DETAILS);

        restFicheFacteurVIIIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheFacteurVIII.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheFacteurVIII))
            )
            .andExpect(status().isOk());

        // Validate the FicheFacteurVIII in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheFacteurVIIIUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFicheFacteurVIII, ficheFacteurVIII),
            getPersistedFicheFacteurVIII(ficheFacteurVIII)
        );
    }

    @Test
    @Transactional
    void fullUpdateFicheFacteurVIIIWithPatch() throws Exception {
        // Initialize the database
        insertedFicheFacteurVIII = ficheFacteurVIIIRepository.saveAndFlush(ficheFacteurVIII);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheFacteurVIII using partial update
        FicheFacteurVIII partialUpdatedFicheFacteurVIII = new FicheFacteurVIII();
        partialUpdatedFicheFacteurVIII.setId(ficheFacteurVIII.getId());

        partialUpdatedFicheFacteurVIII
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
            .enqueteFamilialeBool(UPDATED_ENQUETE_FAMILIALE_BOOL)
            .explorationSyndromeHemorragique(UPDATED_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .typeExplorationSyndromeHemorragique(UPDATED_TYPE_EXPLORATION_SYNDROME_HEMORRAGIQUE)
            .datePremierSigneClinique(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE)
            .ageDiagnosticJour(UPDATED_AGE_DIAGNOSTIC_JOUR)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnee(UPDATED_AGE_DIAGNOSTIC_ANNEE)
            .tp(UPDATED_TP)
            .tpMT(UPDATED_TP_MT)
            .tca(UPDATED_TCA)
            .tcaMT(UPDATED_TCA_MT)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .ts(UPDATED_TS)
            .tsFait(UPDATED_TS_FAIT)
            .toPfa(UPDATED_TO_PFA)
            .toPfa200ColEpi(UPDATED_TO_PFA_200_COL_EPI)
            .toPfa200ColAdp(UPDATED_TO_PFA_200_COL_ADP)
            .tauxDosageFacteurXIII(UPDATED_TAUX_DOSAGE_FACTEUR_XIII)
            .hb(UPDATED_HB)
            .ht(UPDATED_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .techniqueChromogenique(UPDATED_TECHNIQUE_CHROMOGENIQUE)
            .tauxTechniqueChromogenique(UPDATED_TAUX_TECHNIQUE_CHROMOGENIQUE)
            .techniqueSolubiliteCaillot(UPDATED_TECHNIQUE_SOLUBILITE_CAILLOT)
            .tauxTechniqueSolubiliteCaillot(UPDATED_TAUX_TECHNIQUE_SOLUBILITE_CAILLOT)
            .confirmationMoleculaire(UPDATED_CONFIRMATION_MOLECULAIRE)
            .mutationPresence(UPDATED_MUTATION_PRESENCE)
            .mutationType(UPDATED_MUTATION_TYPE)
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
            .typeHemorragieGrave(UPDATED_TYPE_HEMORRAGIE_GRAVE)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .suiteOperatoireHemorragique(UPDATED_SUITE_OPERATOIRE_HEMORRAGIQUE)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .suiteOperatoireCirconcision(UPDATED_SUITE_OPERATOIRE_CIRCONCISION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .facteurVIIIRecombinant(UPDATED_FACTEUR_VIII_RECOMBINANT)
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
            .titreUB(UPDATED_TITRE_UB)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .infectionsVirales(UPDATED_INFECTIONS_VIRALES)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .alloImmunisation(UPDATED_ALLO_IMMUNISATION)
            .adhesionTherapeutique(UPDATED_ADHESION_THERAPEUTIQUE)
            .causeNonAdhesion(UPDATED_CAUSE_NON_ADHESION)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .mariage(UPDATED_MARIAGE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .hemorragieSnc(UPDATED_HEMORRAGIE_SNC)
            .hemorragieAutreQueSnc(UPDATED_HEMORRAGIE_AUTRE_QUE_SNC)
            .hiv(UPDATED_HIV)
            .autreInfection(UPDATED_AUTRE_INFECTION)
            .evenementThromboembolique(UPDATED_EVENEMENT_THROMBOEMBOLIQUE)
            .cancer(UPDATED_CANCER)
            .covid19(UPDATED_COVID_19)
            .inconnue(UPDATED_INCONNUE)
            .autreEvenement(UPDATED_AUTRE_EVENEMENT)
            .autreEvenementDetails(UPDATED_AUTRE_EVENEMENT_DETAILS);

        restFicheFacteurVIIIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheFacteurVIII.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheFacteurVIII))
            )
            .andExpect(status().isOk());

        // Validate the FicheFacteurVIII in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheFacteurVIIIUpdatableFieldsEquals(
            partialUpdatedFicheFacteurVIII,
            getPersistedFicheFacteurVIII(partialUpdatedFicheFacteurVIII)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFicheFacteurVIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurVIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurVIII
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheFacteurVIIIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ficheFacteurVIIIDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheFacteurVIIIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurVIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFicheFacteurVIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurVIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurVIII
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurVIIIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheFacteurVIIIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurVIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFicheFacteurVIII() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurVIII.setId(longCount.incrementAndGet());

        // Create the FicheFacteurVIII
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = ficheFacteurVIIIMapper.toDto(ficheFacteurVIII);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurVIIIMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(ficheFacteurVIIIDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheFacteurVIII in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFicheFacteurVIII() throws Exception {
        // Initialize the database
        insertedFicheFacteurVIII = ficheFacteurVIIIRepository.saveAndFlush(ficheFacteurVIII);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the ficheFacteurVIII
        restFicheFacteurVIIIMockMvc
            .perform(delete(ENTITY_API_URL_ID, ficheFacteurVIII.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return ficheFacteurVIIIRepository.count();
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

    protected FicheFacteurVIII getPersistedFicheFacteurVIII(FicheFacteurVIII ficheFacteurVIII) {
        return ficheFacteurVIIIRepository.findById(ficheFacteurVIII.getId()).orElseThrow();
    }

    protected void assertPersistedFicheFacteurVIIIToMatchAllProperties(FicheFacteurVIII expectedFicheFacteurVIII) {
        assertFicheFacteurVIIIAllPropertiesEquals(expectedFicheFacteurVIII, getPersistedFicheFacteurVIII(expectedFicheFacteurVIII));
    }

    protected void assertPersistedFicheFacteurVIIIToMatchUpdatableProperties(FicheFacteurVIII expectedFicheFacteurVIII) {
        assertFicheFacteurVIIIAllUpdatablePropertiesEquals(
            expectedFicheFacteurVIII,
            getPersistedFicheFacteurVIII(expectedFicheFacteurVIII)
        );
    }
}

package tn.adhes.hemostase.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.adhes.hemostase.domain.FicheFacteurXIAsserts.*;
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
import tn.adhes.hemostase.domain.FicheFacteurXI;
import tn.adhes.hemostase.domain.enumeration.CauseDeces;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.LienParente;
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
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.Region;
import tn.adhes.hemostase.domain.enumeration.ServiceCliniqueType;
import tn.adhes.hemostase.domain.enumeration.StatutSocial;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeZygote;
import tn.adhes.hemostase.repository.FicheFacteurXIRepository;
import tn.adhes.hemostase.service.dto.FicheFacteurXIDTO;
import tn.adhes.hemostase.service.mapper.FicheFacteurXIMapper;

/**
 * Integration tests for the {@link FicheFacteurXIResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheFacteurXIResourceIT {

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

    private static final OuiNonNP DEFAULT_ENQUETE_FAMILIALE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ENQUETE_FAMILIALE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYNDROME_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYNDROME_HEMORRAGIQUE = OuiNonNP.NON;

    private static final String DEFAULT_PRECISION_SYNDROME_HEMORRAGIQUE = "AAAAAAAAAA";
    private static final String UPDATED_PRECISION_SYNDROME_HEMORRAGIQUE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_DECOUVERTE_FORTUITE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_DECOUVERTE_FORTUITE = OuiNonNP.NON;

    private static final String DEFAULT_PRECISION_DECOUVERTE_FORTUITE = "AAAAAAAAAA";
    private static final String UPDATED_PRECISION_DECOUVERTE_FORTUITE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_JAMAIS_SAIGNE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_JAMAIS_SAIGNE = OuiNonNP.NON;

    private static final Integer DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_JOURS = 1;
    private static final Integer UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_JOURS = 2;

    private static final Integer DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_MOIS = 1;
    private static final Integer UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_MOIS = 2;

    private static final Integer DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES = 1;
    private static final Integer UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES = 2;

    private static final Boolean DEFAULT_SYMPTOME_ECCHYMOSE = false;
    private static final Boolean UPDATED_SYMPTOME_ECCHYMOSE = true;

    private static final Boolean DEFAULT_SYMPTOME_EPISTAXIS = false;
    private static final Boolean UPDATED_SYMPTOME_EPISTAXIS = true;

    private static final Boolean DEFAULT_SYMPTOME_GINGIVORRAGIES = false;
    private static final Boolean UPDATED_SYMPTOME_GINGIVORRAGIES = true;

    private static final Boolean DEFAULT_SYMPTOME_METRORRAGIES = false;
    private static final Boolean UPDATED_SYMPTOME_METRORRAGIES = true;

    private static final Boolean DEFAULT_SYMPTOME_MELENA = false;
    private static final Boolean UPDATED_SYMPTOME_MELENA = true;

    private static final Boolean DEFAULT_SYMPTOME_HEMOPTYSIE = false;
    private static final Boolean UPDATED_SYMPTOME_HEMOPTYSIE = true;

    private static final Boolean DEFAULT_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE = false;
    private static final Boolean UPDATED_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE = true;

    private static final Boolean DEFAULT_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE = false;
    private static final Boolean UPDATED_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE = true;

    private static final Boolean DEFAULT_SYMPTOME_HEMORRAGIE_CHUTE_CORDON = false;
    private static final Boolean UPDATED_SYMPTOME_HEMORRAGIE_CHUTE_CORDON = true;

    private static final Boolean DEFAULT_SYMPTOME_AUTRE = false;
    private static final Boolean UPDATED_SYMPTOME_AUTRE = true;

    private static final String DEFAULT_SYMPTOME_AUTRE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SYMPTOME_AUTRE_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_PREMIERE_CONSULTATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PREMIERE_CONSULTATION = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGE_PREMIERE_CONSULTATION_JOURS = 1;
    private static final Integer UPDATED_AGE_PREMIERE_CONSULTATION_JOURS = 2;

    private static final Integer DEFAULT_AGE_PREMIERE_CONSULTATION_MOIS = 1;
    private static final Integer UPDATED_AGE_PREMIERE_CONSULTATION_MOIS = 2;

    private static final Integer DEFAULT_AGE_PREMIERE_CONSULTATION_ANNEES = 1;
    private static final Integer UPDATED_AGE_PREMIERE_CONSULTATION_ANNEES = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_JOURS = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_JOURS = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_MOIS = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_MOIS = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_ANNEES = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_ANNEES = 2;

    private static final Double DEFAULT_HB = 1D;
    private static final Double UPDATED_HB = 2D;

    private static final Double DEFAULT_HTE = 1D;
    private static final Double UPDATED_HTE = 2D;

    private static final Integer DEFAULT_PLAQUETTES = 1;
    private static final Integer UPDATED_PLAQUETTES = 2;

    private static final Double DEFAULT_PFA_COL_EPI = 1D;
    private static final Double UPDATED_PFA_COL_EPI = 2D;

    private static final Double DEFAULT_PFA_COL_ADP = 1D;
    private static final Double UPDATED_PFA_COL_ADP = 2D;

    private static final Double DEFAULT_TP = 1D;
    private static final Double UPDATED_TP = 2D;

    private static final Double DEFAULT_TP_M_PLUS_T = 1D;
    private static final Double UPDATED_TP_M_PLUS_T = 2D;

    private static final Double DEFAULT_FIBRINOGENE = 1D;
    private static final Double UPDATED_FIBRINOGENE = 2D;

    private static final Double DEFAULT_TCA_MT = 1D;
    private static final Double UPDATED_TCA_MT = 2D;

    private static final Double DEFAULT_TCA_M_PLUS_T = 1D;
    private static final Double UPDATED_TCA_M_PLUS_T = 2D;

    private static final Double DEFAULT_TCA_TEMOIN = 1D;
    private static final Double UPDATED_TCA_TEMOIN = 2D;

    private static final Double DEFAULT_FACTEUR_XI = 1D;
    private static final Double UPDATED_FACTEUR_XI = 2D;

    private static final String DEFAULT_AUTRES_FACTEURS_DOSAGE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_AUTRES_FACTEURS_DOSAGE_DESCRIPTION = "BBBBBBBBBB";

    private static final Double DEFAULT_AUTRES_FACTEURS_TAUX_1 = 1D;
    private static final Double UPDATED_AUTRES_FACTEURS_TAUX_1 = 2D;

    private static final Double DEFAULT_AUTRES_FACTEURS_TAUX_2 = 1D;
    private static final Double UPDATED_AUTRES_FACTEURS_TAUX_2 = 2D;

    private static final Double DEFAULT_AUTRES_FACTEURS_TAUX_3 = 1D;
    private static final Double UPDATED_AUTRES_FACTEURS_TAUX_3 = 2D;

    private static final FaiteNonFaite DEFAULT_CONFIRMATION_MOLECULAIRE = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_CONFIRMATION_MOLECULAIRE = FaiteNonFaite.NONFAITE;

    private static final Boolean DEFAULT_MUTATION_PRESENTE = false;
    private static final Boolean UPDATED_MUTATION_PRESENTE = true;

    private static final String DEFAULT_MUTATION_NOM = "AAAAAAAAAA";
    private static final String UPDATED_MUTATION_NOM = "BBBBBBBBBB";

    private static final TypeZygote DEFAULT_MUTATION_ZYGOTE = TypeZygote.HETEROZYGOTE;
    private static final TypeZygote UPDATED_MUTATION_ZYGOTE = TypeZygote.HOMOZYGOTE;

    private static final String DEFAULT_MUTATION_2_NOM = "AAAAAAAAAA";
    private static final String UPDATED_MUTATION_2_NOM = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_SEROLOGIE_VHB = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SEROLOGIE_VHB = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SEROLOGIE_VHC = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SEROLOGIE_VHC = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SEROLOGIE_VIH = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SEROLOGIE_VIH = OuiNonNP.NON;

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

    private static final Boolean DEFAULT_HEMORRAGIE_POST_TRAUMATIQUE = false;
    private static final Boolean UPDATED_HEMORRAGIE_POST_TRAUMATIQUE = true;

    private static final Integer DEFAULT_FREQUENCE_HEMORRAGIE_POST_TRAUMATIQUE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMORRAGIE_POST_TRAUMATIQUE = 2;

    private static final Boolean DEFAULT_HEMATOME_SOUS_CUTANE = false;
    private static final Boolean UPDATED_HEMATOME_SOUS_CUTANE = true;

    private static final TypeManifestation DEFAULT_TYPE_HEMATOME_SOUS_CUTANE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_HEMATOME_SOUS_CUTANE = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_HEMATOME_SOUS_CUTANE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE = 2;

    private static final Boolean DEFAULT_HEMATOME_MUSCULAIRE = false;
    private static final Boolean UPDATED_HEMATOME_MUSCULAIRE = true;

    private static final TypeManifestation DEFAULT_TYPE_HEMATOME_MUSCULAIRE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_HEMATOME_MUSCULAIRE = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_HEMATOME_MUSCULAIRE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMATOME_MUSCULAIRE = 2;

    private static final Boolean DEFAULT_HEMATOME_TISSUS_MOUS_PROFONDS = false;
    private static final Boolean UPDATED_HEMATOME_TISSUS_MOUS_PROFONDS = true;

    private static final TypeManifestation DEFAULT_TYPE_HEMATOME_TISSUS_MOUS_PROFONDS = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_TYPE_HEMATOME_TISSUS_MOUS_PROFONDS = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_HEMATOME_TISSUS_MOUS_PROFONDS = 1;
    private static final Integer UPDATED_FREQUENCE_HEMATOME_TISSUS_MOUS_PROFONDS = 2;

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

    private static final Boolean DEFAULT_SAIGNEMENT_CHUTE_CORDON = false;
    private static final Boolean UPDATED_SAIGNEMENT_CHUTE_CORDON = true;

    private static final Integer DEFAULT_FREQUENCE_SAIGNEMENT_CHUTE_CORDON = 1;
    private static final Integer UPDATED_FREQUENCE_SAIGNEMENT_CHUTE_CORDON = 2;

    private static final Boolean DEFAULT_RETARD_CICATRISATION_PLAIES = false;
    private static final Boolean UPDATED_RETARD_CICATRISATION_PLAIES = true;

    private static final Integer DEFAULT_FREQUENCE_RETARD_CICATRISATION_PLAIES = 1;
    private static final Integer UPDATED_FREQUENCE_RETARD_CICATRISATION_PLAIES = 2;

    private static final Boolean DEFAULT_AUTRES_MANIFESTATIONS = false;
    private static final Boolean UPDATED_AUTRES_MANIFESTATIONS = true;

    private static final Boolean DEFAULT_FAUSSES_COUCHES_SPONTANEES_RECURRENCE = false;
    private static final Boolean UPDATED_FAUSSES_COUCHES_SPONTANEES_RECURRENCE = true;

    private static final Integer DEFAULT_FREQUENCE_FAUSSES_COUCHES = 1;
    private static final Integer UPDATED_FREQUENCE_FAUSSES_COUCHES = 2;

    private static final Boolean DEFAULT_ACCIDENTS_THROMBOTIQUES = false;
    private static final Boolean UPDATED_ACCIDENTS_THROMBOTIQUES = true;

    private static final Boolean DEFAULT_THROMBOSE_VEINEUSE = false;
    private static final Boolean UPDATED_THROMBOSE_VEINEUSE = true;

    private static final Boolean DEFAULT_THROMBOSE_ARTERIELLE = false;
    private static final Boolean UPDATED_THROMBOSE_ARTERIELLE = true;

    private static final Integer DEFAULT_FREQUENCE_ACCIDENTS_THROMBOTIQUES = 1;
    private static final Integer UPDATED_FREQUENCE_ACCIDENTS_THROMBOTIQUES = 2;

    private static final Boolean DEFAULT_HEMOPERITOINE = false;
    private static final Boolean UPDATED_HEMOPERITOINE = true;

    private static final Integer DEFAULT_FREQUENCE_HEMOPERITOINE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMOPERITOINE = 2;

    private static final Boolean DEFAULT_RUPTURE_KYSTES_HEMORRAGIQUES = false;
    private static final Boolean UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES = true;

    private static final Integer DEFAULT_FREQUENCE_RUPTURE_KYSTES = 1;
    private static final Integer UPDATED_FREQUENCE_RUPTURE_KYSTES = 2;

    private static final Boolean DEFAULT_HEMORRAGIE_GRAVE = false;
    private static final Boolean UPDATED_HEMORRAGIE_GRAVE = true;

    private static final Integer DEFAULT_FREQUENCE_HEMORRAGIE_GRAVE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMORRAGIE_GRAVE = 2;

    private static final Boolean DEFAULT_GRAVITE_DEGLOBULISATION = false;
    private static final Boolean UPDATED_GRAVITE_DEGLOBULISATION = true;

    private static final Boolean DEFAULT_GRAVITE_ETAT_DE_CHOC = false;
    private static final Boolean UPDATED_GRAVITE_ETAT_DE_CHOC = true;

    private static final Boolean DEFAULT_GRAVITE_PAR_LOCALISATION = false;
    private static final Boolean UPDATED_GRAVITE_PAR_LOCALISATION = true;

    private static final String DEFAULT_SIEGE_GRAVITE_LOCALISATION = "AAAAAAAAAA";
    private static final String UPDATED_SIEGE_GRAVITE_LOCALISATION = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CHIRURGIE_PREPARE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CHIRURGIE_PREPARE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CHIRURGIE_SUITE_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CHIRURGIE_SUITE_HEMORRAGIQUE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CIRCONCISION_PREPARE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION_PREPARE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CIRCONCISION_SUITE_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION_SUITE_HEMORRAGIQUE = OuiNonNP.NON;

    private static final Integer DEFAULT_AGE_CIRCONCISION = 1;
    private static final Integer UPDATED_AGE_CIRCONCISION = 2;

    private static final OuiNonNP DEFAULT_TRAITEMENT_PFC = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_PFC = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_CRYOPRECIPITE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_CRYOPRECIPITE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_CONCENTRE_FACTEUR_XI = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_CONCENTRE_FACTEUR_XI = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_CULOTS_PLAQUETTAIRES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_CULOTS_PLAQUETTAIRES = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_DICYNONE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_DICYNONE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_EXACYL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_EXACYL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_MARTIAL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_MARTIAL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_OESTROPROGESTATIF = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_OESTROPROGESTATIF = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRANSFUSION_CGR = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRANSFUSION_CGR = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ALLO_IMMUNISATION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ALLO_IMMUNISATION = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_INHIBITEUR_NEUTRALISANT = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INHIBITEUR_NEUTRALISANT = OuiNonNP.NON;

    private static final Double DEFAULT_TITRE_INHIBITEUR_UB = 1D;
    private static final Double UPDATED_TITRE_INHIBITEUR_UB = 2D;

    private static final OuiNonNP DEFAULT_INHIBITEUR_NON_NEUTRALISANT = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INHIBITEUR_NON_NEUTRALISANT = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_INFECTION_VIH = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INFECTION_VIH = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_INFECTION_VHC = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INFECTION_VHC = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_INFECTION_VHB = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INFECTION_VHB = OuiNonNP.NON;

    private static final Boolean DEFAULT_THROMBOSE = false;
    private static final Boolean UPDATED_THROMBOSE = true;

    private static final Boolean DEFAULT_REACTION_ALLERGIQUE = false;
    private static final Boolean UPDATED_REACTION_ALLERGIQUE = true;

    private static final String DEFAULT_AUTRES_COMPLICATIONS = "AAAAAAAAAA";
    private static final String UPDATED_AUTRES_COMPLICATIONS = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_ADHERENCE_THERAPEUTIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ADHERENCE_THERAPEUTIQUE = OuiNonNP.NON;

    private static final String DEFAULT_CAUSE_NON_ADHERENCE = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_NON_ADHERENCE = "BBBBBBBBBB";

    private static final StatutSocial DEFAULT_STATUT_SOCIAL = StatutSocial.CHOMEUR;
    private static final StatutSocial UPDATED_STATUT_SOCIAL = StatutSocial.TRAVAILLE;

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

    private static final CauseDeces DEFAULT_CAUSE_DECES = CauseDeces.HEMORRAGIE_SNC;
    private static final CauseDeces UPDATED_CAUSE_DECES = CauseDeces.HEMORRAGIE_AUTRE;

    private static final String ENTITY_API_URL = "/api/fiche-facteur-xis";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FicheFacteurXIRepository ficheFacteurXIRepository;

    @Autowired
    private FicheFacteurXIMapper ficheFacteurXIMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheFacteurXIMockMvc;

    private FicheFacteurXI ficheFacteurXI;

    private FicheFacteurXI insertedFicheFacteurXI;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheFacteurXI createEntity() {
        return new FicheFacteurXI()
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
            .syndromeHemorragique(DEFAULT_SYNDROME_HEMORRAGIQUE)
            .precisionSyndromeHemorragique(DEFAULT_PRECISION_SYNDROME_HEMORRAGIQUE)
            .decouverteFortuite(DEFAULT_DECOUVERTE_FORTUITE)
            .precisionDecouverteFortuite(DEFAULT_PRECISION_DECOUVERTE_FORTUITE)
            .jamaisSaigne(DEFAULT_JAMAIS_SAIGNE)
            .agePremierSigneCliniqueJours(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_JOURS)
            .agePremierSigneCliniqueMois(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_MOIS)
            .agePremierSigneCliniqueAnnees(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES)
            .symptomeEcchymose(DEFAULT_SYMPTOME_ECCHYMOSE)
            .symptomeEpistaxis(DEFAULT_SYMPTOME_EPISTAXIS)
            .symptomeGingivorragies(DEFAULT_SYMPTOME_GINGIVORRAGIES)
            .symptomeMetrorragies(DEFAULT_SYMPTOME_METRORRAGIES)
            .symptomeMelena(DEFAULT_SYMPTOME_MELENA)
            .symptomeHemoptysie(DEFAULT_SYMPTOME_HEMOPTYSIE)
            .symptomeHemorragiePostAmygdalectomie(DEFAULT_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE)
            .symptomeHemorragiePostAvulsionDentaire(DEFAULT_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE)
            .symptomeHemorragieChuteCordon(DEFAULT_SYMPTOME_HEMORRAGIE_CHUTE_CORDON)
            .symptomeAutre(DEFAULT_SYMPTOME_AUTRE)
            .symptomeAutreDescription(DEFAULT_SYMPTOME_AUTRE_DESCRIPTION)
            .datePremiereConsultation(DEFAULT_DATE_PREMIERE_CONSULTATION)
            .agePremiereConsultationJours(DEFAULT_AGE_PREMIERE_CONSULTATION_JOURS)
            .agePremiereConsultationMois(DEFAULT_AGE_PREMIERE_CONSULTATION_MOIS)
            .agePremiereConsultationAnnees(DEFAULT_AGE_PREMIERE_CONSULTATION_ANNEES)
            .ageDiagnosticJours(DEFAULT_AGE_DIAGNOSTIC_JOURS)
            .ageDiagnosticMois(DEFAULT_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnees(DEFAULT_AGE_DIAGNOSTIC_ANNEES)
            .hb(DEFAULT_HB)
            .hte(DEFAULT_HTE)
            .plaquettes(DEFAULT_PLAQUETTES)
            .pfaColEpi(DEFAULT_PFA_COL_EPI)
            .pfaColADP(DEFAULT_PFA_COL_ADP)
            .tp(DEFAULT_TP)
            .tpMPlusT(DEFAULT_TP_M_PLUS_T)
            .fibrinogene(DEFAULT_FIBRINOGENE)
            .tcaMT(DEFAULT_TCA_MT)
            .tcaMPlusT(DEFAULT_TCA_M_PLUS_T)
            .tcaTemoin(DEFAULT_TCA_TEMOIN)
            .facteurXI(DEFAULT_FACTEUR_XI)
            .autresFacteursDosageDescription(DEFAULT_AUTRES_FACTEURS_DOSAGE_DESCRIPTION)
            .autresFacteursTaux1(DEFAULT_AUTRES_FACTEURS_TAUX_1)
            .autresFacteursTaux2(DEFAULT_AUTRES_FACTEURS_TAUX_2)
            .autresFacteursTaux3(DEFAULT_AUTRES_FACTEURS_TAUX_3)
            .confirmationMoleculaire(DEFAULT_CONFIRMATION_MOLECULAIRE)
            .mutationPresente(DEFAULT_MUTATION_PRESENTE)
            .mutationNom(DEFAULT_MUTATION_NOM)
            .mutationZygote(DEFAULT_MUTATION_ZYGOTE)
            .mutation2Nom(DEFAULT_MUTATION_2_NOM)
            .serologieVHB(DEFAULT_SEROLOGIE_VHB)
            .serologieVHC(DEFAULT_SEROLOGIE_VHC)
            .serologieVIH(DEFAULT_SEROLOGIE_VIH)
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
            .hemorragiePostTraumatique(DEFAULT_HEMORRAGIE_POST_TRAUMATIQUE)
            .frequenceHemorragiePostTraumatique(DEFAULT_FREQUENCE_HEMORRAGIE_POST_TRAUMATIQUE)
            .hematomeSousCutane(DEFAULT_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(DEFAULT_TYPE_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(DEFAULT_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(DEFAULT_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(DEFAULT_TYPE_HEMATOME_MUSCULAIRE)
            .frequenceHematomeMusculaire(DEFAULT_FREQUENCE_HEMATOME_MUSCULAIRE)
            .hematomeTissusMousProfonds(DEFAULT_HEMATOME_TISSUS_MOUS_PROFONDS)
            .typeHematomeTissusMousProfonds(DEFAULT_TYPE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .frequenceHematomeTissusMousProfonds(DEFAULT_FREQUENCE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .hemarthrose(DEFAULT_HEMARTHROSE)
            .typeHemarthrose(DEFAULT_TYPE_HEMARTHROSE)
            .frequenceHemarthrose(DEFAULT_FREQUENCE_HEMARTHROSE)
            .saignementSNC(DEFAULT_SAIGNEMENT_SNC)
            .typeSaignementSNC(DEFAULT_TYPE_SAIGNEMENT_SNC)
            .frequenceSaignementSNC(DEFAULT_FREQUENCE_SAIGNEMENT_SNC)
            .saignementChuteCordon(DEFAULT_SAIGNEMENT_CHUTE_CORDON)
            .frequenceSaignementChuteCordon(DEFAULT_FREQUENCE_SAIGNEMENT_CHUTE_CORDON)
            .retardCicatrisationPlaies(DEFAULT_RETARD_CICATRISATION_PLAIES)
            .frequenceRetardCicatrisationPlaies(DEFAULT_FREQUENCE_RETARD_CICATRISATION_PLAIES)
            .autresManifestations(DEFAULT_AUTRES_MANIFESTATIONS)
            .faussesCouchesSpontaneesRecurrence(DEFAULT_FAUSSES_COUCHES_SPONTANEES_RECURRENCE)
            .frequenceFaussesCouches(DEFAULT_FREQUENCE_FAUSSES_COUCHES)
            .accidentsThrombotiques(DEFAULT_ACCIDENTS_THROMBOTIQUES)
            .thromboseVeineuse(DEFAULT_THROMBOSE_VEINEUSE)
            .thromboseArterielle(DEFAULT_THROMBOSE_ARTERIELLE)
            .frequenceAccidentsThrombotiques(DEFAULT_FREQUENCE_ACCIDENTS_THROMBOTIQUES)
            .hemoperitoine(DEFAULT_HEMOPERITOINE)
            .frequenceHemoperitoine(DEFAULT_FREQUENCE_HEMOPERITOINE)
            .ruptureKystesHemorragiques(DEFAULT_RUPTURE_KYSTES_HEMORRAGIQUES)
            .frequenceRuptureKystes(DEFAULT_FREQUENCE_RUPTURE_KYSTES)
            .hemorragieGrave(DEFAULT_HEMORRAGIE_GRAVE)
            .frequenceHemorragieGrave(DEFAULT_FREQUENCE_HEMORRAGIE_GRAVE)
            .graviteDeglobulisation(DEFAULT_GRAVITE_DEGLOBULISATION)
            .graviteEtatDeChoc(DEFAULT_GRAVITE_ETAT_DE_CHOC)
            .graviteParLocalisation(DEFAULT_GRAVITE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(DEFAULT_SIEGE_GRAVITE_LOCALISATION)
            .chirurgiePrepare(DEFAULT_CHIRURGIE_PREPARE)
            .chirurgieSuiteHemorragique(DEFAULT_CHIRURGIE_SUITE_HEMORRAGIQUE)
            .circoncisionPrepare(DEFAULT_CIRCONCISION_PREPARE)
            .circoncisionSuiteHemorragique(DEFAULT_CIRCONCISION_SUITE_HEMORRAGIQUE)
            .ageCirconcision(DEFAULT_AGE_CIRCONCISION)
            .traitementPFC(DEFAULT_TRAITEMENT_PFC)
            .traitementCryoprecipite(DEFAULT_TRAITEMENT_CRYOPRECIPITE)
            .traitementConcentreFacteurXI(DEFAULT_TRAITEMENT_CONCENTRE_FACTEUR_XI)
            .traitementCulotsPlaquettaires(DEFAULT_TRAITEMENT_CULOTS_PLAQUETTAIRES)
            .traitementDicynone(DEFAULT_TRAITEMENT_DICYNONE)
            .traitementExacyl(DEFAULT_TRAITEMENT_EXACYL)
            .traitementMartial(DEFAULT_TRAITEMENT_MARTIAL)
            .traitementOestroprogestatif(DEFAULT_TRAITEMENT_OESTROPROGESTATIF)
            .transfusionCGR(DEFAULT_TRANSFUSION_CGR)
            .alloImmunisation(DEFAULT_ALLO_IMMUNISATION)
            .inhibiteurNeutralisant(DEFAULT_INHIBITEUR_NEUTRALISANT)
            .titreInhibiteurUB(DEFAULT_TITRE_INHIBITEUR_UB)
            .inhibiteurNonNeutralisant(DEFAULT_INHIBITEUR_NON_NEUTRALISANT)
            .infectionVIH(DEFAULT_INFECTION_VIH)
            .infectionVHC(DEFAULT_INFECTION_VHC)
            .infectionVHB(DEFAULT_INFECTION_VHB)
            .thrombose(DEFAULT_THROMBOSE)
            .reactionAllergique(DEFAULT_REACTION_ALLERGIQUE)
            .autresComplications(DEFAULT_AUTRES_COMPLICATIONS)
            .adherenceTherapeutique(DEFAULT_ADHERENCE_THERAPEUTIQUE)
            .causeNonAdherence(DEFAULT_CAUSE_NON_ADHERENCE)
            .statutSocial(DEFAULT_STATUT_SOCIAL)
            .marie(DEFAULT_MARIE)
            .enfants(DEFAULT_ENFANTS)
            .activitePhysique(DEFAULT_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(DEFAULT_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(DEFAULT_SEQUELLES)
            .typeSequelles(DEFAULT_TYPE_SEQUELLES)
            .patientDecede(DEFAULT_PATIENT_DECEDE)
            .dateDeces(DEFAULT_DATE_DECES)
            .ageDeces(DEFAULT_AGE_DECES)
            .causeDeces(DEFAULT_CAUSE_DECES);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheFacteurXI createUpdatedEntity() {
        return new FicheFacteurXI()
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
            .syndromeHemorragique(UPDATED_SYNDROME_HEMORRAGIQUE)
            .precisionSyndromeHemorragique(UPDATED_PRECISION_SYNDROME_HEMORRAGIQUE)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .precisionDecouverteFortuite(UPDATED_PRECISION_DECOUVERTE_FORTUITE)
            .jamaisSaigne(UPDATED_JAMAIS_SAIGNE)
            .agePremierSigneCliniqueJours(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_JOURS)
            .agePremierSigneCliniqueMois(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_MOIS)
            .agePremierSigneCliniqueAnnees(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES)
            .symptomeEcchymose(UPDATED_SYMPTOME_ECCHYMOSE)
            .symptomeEpistaxis(UPDATED_SYMPTOME_EPISTAXIS)
            .symptomeGingivorragies(UPDATED_SYMPTOME_GINGIVORRAGIES)
            .symptomeMetrorragies(UPDATED_SYMPTOME_METRORRAGIES)
            .symptomeMelena(UPDATED_SYMPTOME_MELENA)
            .symptomeHemoptysie(UPDATED_SYMPTOME_HEMOPTYSIE)
            .symptomeHemorragiePostAmygdalectomie(UPDATED_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE)
            .symptomeHemorragiePostAvulsionDentaire(UPDATED_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE)
            .symptomeHemorragieChuteCordon(UPDATED_SYMPTOME_HEMORRAGIE_CHUTE_CORDON)
            .symptomeAutre(UPDATED_SYMPTOME_AUTRE)
            .symptomeAutreDescription(UPDATED_SYMPTOME_AUTRE_DESCRIPTION)
            .datePremiereConsultation(UPDATED_DATE_PREMIERE_CONSULTATION)
            .agePremiereConsultationJours(UPDATED_AGE_PREMIERE_CONSULTATION_JOURS)
            .agePremiereConsultationMois(UPDATED_AGE_PREMIERE_CONSULTATION_MOIS)
            .agePremiereConsultationAnnees(UPDATED_AGE_PREMIERE_CONSULTATION_ANNEES)
            .ageDiagnosticJours(UPDATED_AGE_DIAGNOSTIC_JOURS)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnees(UPDATED_AGE_DIAGNOSTIC_ANNEES)
            .hb(UPDATED_HB)
            .hte(UPDATED_HTE)
            .plaquettes(UPDATED_PLAQUETTES)
            .pfaColEpi(UPDATED_PFA_COL_EPI)
            .pfaColADP(UPDATED_PFA_COL_ADP)
            .tp(UPDATED_TP)
            .tpMPlusT(UPDATED_TP_M_PLUS_T)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMPlusT(UPDATED_TCA_M_PLUS_T)
            .tcaTemoin(UPDATED_TCA_TEMOIN)
            .facteurXI(UPDATED_FACTEUR_XI)
            .autresFacteursDosageDescription(UPDATED_AUTRES_FACTEURS_DOSAGE_DESCRIPTION)
            .autresFacteursTaux1(UPDATED_AUTRES_FACTEURS_TAUX_1)
            .autresFacteursTaux2(UPDATED_AUTRES_FACTEURS_TAUX_2)
            .autresFacteursTaux3(UPDATED_AUTRES_FACTEURS_TAUX_3)
            .confirmationMoleculaire(UPDATED_CONFIRMATION_MOLECULAIRE)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationZygote(UPDATED_MUTATION_ZYGOTE)
            .mutation2Nom(UPDATED_MUTATION_2_NOM)
            .serologieVHB(UPDATED_SEROLOGIE_VHB)
            .serologieVHC(UPDATED_SEROLOGIE_VHC)
            .serologieVIH(UPDATED_SEROLOGIE_VIH)
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
            .hemorragiePostTraumatique(UPDATED_HEMORRAGIE_POST_TRAUMATIQUE)
            .frequenceHemorragiePostTraumatique(UPDATED_FREQUENCE_HEMORRAGIE_POST_TRAUMATIQUE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(UPDATED_TYPE_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(UPDATED_TYPE_HEMATOME_MUSCULAIRE)
            .frequenceHematomeMusculaire(UPDATED_FREQUENCE_HEMATOME_MUSCULAIRE)
            .hematomeTissusMousProfonds(UPDATED_HEMATOME_TISSUS_MOUS_PROFONDS)
            .typeHematomeTissusMousProfonds(UPDATED_TYPE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .frequenceHematomeTissusMousProfonds(UPDATED_FREQUENCE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .typeHemarthrose(UPDATED_TYPE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .typeSaignementSNC(UPDATED_TYPE_SAIGNEMENT_SNC)
            .frequenceSaignementSNC(UPDATED_FREQUENCE_SAIGNEMENT_SNC)
            .saignementChuteCordon(UPDATED_SAIGNEMENT_CHUTE_CORDON)
            .frequenceSaignementChuteCordon(UPDATED_FREQUENCE_SAIGNEMENT_CHUTE_CORDON)
            .retardCicatrisationPlaies(UPDATED_RETARD_CICATRISATION_PLAIES)
            .frequenceRetardCicatrisationPlaies(UPDATED_FREQUENCE_RETARD_CICATRISATION_PLAIES)
            .autresManifestations(UPDATED_AUTRES_MANIFESTATIONS)
            .faussesCouchesSpontaneesRecurrence(UPDATED_FAUSSES_COUCHES_SPONTANEES_RECURRENCE)
            .frequenceFaussesCouches(UPDATED_FREQUENCE_FAUSSES_COUCHES)
            .accidentsThrombotiques(UPDATED_ACCIDENTS_THROMBOTIQUES)
            .thromboseVeineuse(UPDATED_THROMBOSE_VEINEUSE)
            .thromboseArterielle(UPDATED_THROMBOSE_ARTERIELLE)
            .frequenceAccidentsThrombotiques(UPDATED_FREQUENCE_ACCIDENTS_THROMBOTIQUES)
            .hemoperitoine(UPDATED_HEMOPERITOINE)
            .frequenceHemoperitoine(UPDATED_FREQUENCE_HEMOPERITOINE)
            .ruptureKystesHemorragiques(UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES)
            .frequenceRuptureKystes(UPDATED_FREQUENCE_RUPTURE_KYSTES)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .frequenceHemorragieGrave(UPDATED_FREQUENCE_HEMORRAGIE_GRAVE)
            .graviteDeglobulisation(UPDATED_GRAVITE_DEGLOBULISATION)
            .graviteEtatDeChoc(UPDATED_GRAVITE_ETAT_DE_CHOC)
            .graviteParLocalisation(UPDATED_GRAVITE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .chirurgieSuiteHemorragique(UPDATED_CHIRURGIE_SUITE_HEMORRAGIQUE)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .circoncisionSuiteHemorragique(UPDATED_CIRCONCISION_SUITE_HEMORRAGIQUE)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .traitementPFC(UPDATED_TRAITEMENT_PFC)
            .traitementCryoprecipite(UPDATED_TRAITEMENT_CRYOPRECIPITE)
            .traitementConcentreFacteurXI(UPDATED_TRAITEMENT_CONCENTRE_FACTEUR_XI)
            .traitementCulotsPlaquettaires(UPDATED_TRAITEMENT_CULOTS_PLAQUETTAIRES)
            .traitementDicynone(UPDATED_TRAITEMENT_DICYNONE)
            .traitementExacyl(UPDATED_TRAITEMENT_EXACYL)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .transfusionCGR(UPDATED_TRANSFUSION_CGR)
            .alloImmunisation(UPDATED_ALLO_IMMUNISATION)
            .inhibiteurNeutralisant(UPDATED_INHIBITEUR_NEUTRALISANT)
            .titreInhibiteurUB(UPDATED_TITRE_INHIBITEUR_UB)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .infectionVIH(UPDATED_INFECTION_VIH)
            .infectionVHC(UPDATED_INFECTION_VHC)
            .infectionVHB(UPDATED_INFECTION_VHB)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .autresComplications(UPDATED_AUTRES_COMPLICATIONS)
            .adherenceTherapeutique(UPDATED_ADHERENCE_THERAPEUTIQUE)
            .causeNonAdherence(UPDATED_CAUSE_NON_ADHERENCE)
            .statutSocial(UPDATED_STATUT_SOCIAL)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .causeDeces(UPDATED_CAUSE_DECES);
    }

    @BeforeEach
    public void initTest() {
        ficheFacteurXI = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedFicheFacteurXI != null) {
            ficheFacteurXIRepository.delete(insertedFicheFacteurXI);
            insertedFicheFacteurXI = null;
        }
    }

    @Test
    @Transactional
    void createFicheFacteurXI() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FicheFacteurXI
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);
        var returnedFicheFacteurXIDTO = om.readValue(
            restFicheFacteurXIMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FicheFacteurXIDTO.class
        );

        // Validate the FicheFacteurXI in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFicheFacteurXI = ficheFacteurXIMapper.toEntity(returnedFicheFacteurXIDTO);
        assertFicheFacteurXIUpdatableFieldsEquals(returnedFicheFacteurXI, getPersistedFicheFacteurXI(returnedFicheFacteurXI));

        insertedFicheFacteurXI = returnedFicheFacteurXI;
    }

    @Test
    @Transactional
    void createFicheFacteurXIWithExistingId() throws Exception {
        // Create the FicheFacteurXI with an existing ID
        ficheFacteurXI.setId(1L);
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheFacteurXIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXI in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEtatCivilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXI.setEtatCivil(null);

        // Create the FicheFacteurXI, which fails.
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        restFicheFacteurXIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkServiceCliniqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXI.setServiceClinique(null);

        // Create the FicheFacteurXI, which fails.
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        restFicheFacteurXIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCouvertureSocialeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXI.setCouvertureSociale(null);

        // Create the FicheFacteurXI, which fails.
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        restFicheFacteurXIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSexeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXI.setSexe(null);

        // Create the FicheFacteurXI, which fails.
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        restFicheFacteurXIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiagnosticIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheFacteurXI.setDiagnostic(null);

        // Create the FicheFacteurXI, which fails.
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        restFicheFacteurXIMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFicheFacteurXIS() throws Exception {
        // Initialize the database
        insertedFicheFacteurXI = ficheFacteurXIRepository.saveAndFlush(ficheFacteurXI);

        // Get all the ficheFacteurXIList
        restFicheFacteurXIMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheFacteurXI.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].syndromeHemorragique").value(hasItem(DEFAULT_SYNDROME_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].precisionSyndromeHemorragique").value(hasItem(DEFAULT_PRECISION_SYNDROME_HEMORRAGIQUE)))
            .andExpect(jsonPath("$.[*].decouverteFortuite").value(hasItem(DEFAULT_DECOUVERTE_FORTUITE.toString())))
            .andExpect(jsonPath("$.[*].precisionDecouverteFortuite").value(hasItem(DEFAULT_PRECISION_DECOUVERTE_FORTUITE)))
            .andExpect(jsonPath("$.[*].jamaisSaigne").value(hasItem(DEFAULT_JAMAIS_SAIGNE.toString())))
            .andExpect(jsonPath("$.[*].agePremierSigneCliniqueJours").value(hasItem(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_JOURS)))
            .andExpect(jsonPath("$.[*].agePremierSigneCliniqueMois").value(hasItem(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_MOIS)))
            .andExpect(jsonPath("$.[*].agePremierSigneCliniqueAnnees").value(hasItem(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES)))
            .andExpect(jsonPath("$.[*].symptomeEcchymose").value(hasItem(DEFAULT_SYMPTOME_ECCHYMOSE.booleanValue())))
            .andExpect(jsonPath("$.[*].symptomeEpistaxis").value(hasItem(DEFAULT_SYMPTOME_EPISTAXIS.booleanValue())))
            .andExpect(jsonPath("$.[*].symptomeGingivorragies").value(hasItem(DEFAULT_SYMPTOME_GINGIVORRAGIES.booleanValue())))
            .andExpect(jsonPath("$.[*].symptomeMetrorragies").value(hasItem(DEFAULT_SYMPTOME_METRORRAGIES.booleanValue())))
            .andExpect(jsonPath("$.[*].symptomeMelena").value(hasItem(DEFAULT_SYMPTOME_MELENA.booleanValue())))
            .andExpect(jsonPath("$.[*].symptomeHemoptysie").value(hasItem(DEFAULT_SYMPTOME_HEMOPTYSIE.booleanValue())))
            .andExpect(
                jsonPath("$.[*].symptomeHemorragiePostAmygdalectomie").value(
                    hasItem(DEFAULT_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE.booleanValue())
                )
            )
            .andExpect(
                jsonPath("$.[*].symptomeHemorragiePostAvulsionDentaire").value(
                    hasItem(DEFAULT_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE.booleanValue())
                )
            )
            .andExpect(
                jsonPath("$.[*].symptomeHemorragieChuteCordon").value(hasItem(DEFAULT_SYMPTOME_HEMORRAGIE_CHUTE_CORDON.booleanValue()))
            )
            .andExpect(jsonPath("$.[*].symptomeAutre").value(hasItem(DEFAULT_SYMPTOME_AUTRE.booleanValue())))
            .andExpect(jsonPath("$.[*].symptomeAutreDescription").value(hasItem(DEFAULT_SYMPTOME_AUTRE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].datePremiereConsultation").value(hasItem(DEFAULT_DATE_PREMIERE_CONSULTATION.toString())))
            .andExpect(jsonPath("$.[*].agePremiereConsultationJours").value(hasItem(DEFAULT_AGE_PREMIERE_CONSULTATION_JOURS)))
            .andExpect(jsonPath("$.[*].agePremiereConsultationMois").value(hasItem(DEFAULT_AGE_PREMIERE_CONSULTATION_MOIS)))
            .andExpect(jsonPath("$.[*].agePremiereConsultationAnnees").value(hasItem(DEFAULT_AGE_PREMIERE_CONSULTATION_ANNEES)))
            .andExpect(jsonPath("$.[*].ageDiagnosticJours").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_JOURS)))
            .andExpect(jsonPath("$.[*].ageDiagnosticMois").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_MOIS)))
            .andExpect(jsonPath("$.[*].ageDiagnosticAnnees").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_ANNEES)))
            .andExpect(jsonPath("$.[*].hb").value(hasItem(DEFAULT_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].hte").value(hasItem(DEFAULT_HTE.doubleValue())))
            .andExpect(jsonPath("$.[*].plaquettes").value(hasItem(DEFAULT_PLAQUETTES)))
            .andExpect(jsonPath("$.[*].pfaColEpi").value(hasItem(DEFAULT_PFA_COL_EPI.doubleValue())))
            .andExpect(jsonPath("$.[*].pfaColADP").value(hasItem(DEFAULT_PFA_COL_ADP.doubleValue())))
            .andExpect(jsonPath("$.[*].tp").value(hasItem(DEFAULT_TP.doubleValue())))
            .andExpect(jsonPath("$.[*].tpMPlusT").value(hasItem(DEFAULT_TP_M_PLUS_T.doubleValue())))
            .andExpect(jsonPath("$.[*].fibrinogene").value(hasItem(DEFAULT_FIBRINOGENE.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMT").value(hasItem(DEFAULT_TCA_MT.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMPlusT").value(hasItem(DEFAULT_TCA_M_PLUS_T.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaTemoin").value(hasItem(DEFAULT_TCA_TEMOIN.doubleValue())))
            .andExpect(jsonPath("$.[*].facteurXI").value(hasItem(DEFAULT_FACTEUR_XI.doubleValue())))
            .andExpect(jsonPath("$.[*].autresFacteursDosageDescription").value(hasItem(DEFAULT_AUTRES_FACTEURS_DOSAGE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].autresFacteursTaux1").value(hasItem(DEFAULT_AUTRES_FACTEURS_TAUX_1.doubleValue())))
            .andExpect(jsonPath("$.[*].autresFacteursTaux2").value(hasItem(DEFAULT_AUTRES_FACTEURS_TAUX_2.doubleValue())))
            .andExpect(jsonPath("$.[*].autresFacteursTaux3").value(hasItem(DEFAULT_AUTRES_FACTEURS_TAUX_3.doubleValue())))
            .andExpect(jsonPath("$.[*].confirmationMoleculaire").value(hasItem(DEFAULT_CONFIRMATION_MOLECULAIRE.toString())))
            .andExpect(jsonPath("$.[*].mutationPresente").value(hasItem(DEFAULT_MUTATION_PRESENTE.booleanValue())))
            .andExpect(jsonPath("$.[*].mutationNom").value(hasItem(DEFAULT_MUTATION_NOM)))
            .andExpect(jsonPath("$.[*].mutationZygote").value(hasItem(DEFAULT_MUTATION_ZYGOTE.toString())))
            .andExpect(jsonPath("$.[*].mutation2Nom").value(hasItem(DEFAULT_MUTATION_2_NOM)))
            .andExpect(jsonPath("$.[*].serologieVHB").value(hasItem(DEFAULT_SEROLOGIE_VHB.toString())))
            .andExpect(jsonPath("$.[*].serologieVHC").value(hasItem(DEFAULT_SEROLOGIE_VHC.toString())))
            .andExpect(jsonPath("$.[*].serologieVIH").value(hasItem(DEFAULT_SEROLOGIE_VIH.toString())))
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
            .andExpect(jsonPath("$.[*].hemorragiePostTraumatique").value(hasItem(DEFAULT_HEMORRAGIE_POST_TRAUMATIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceHemorragiePostTraumatique").value(hasItem(DEFAULT_FREQUENCE_HEMORRAGIE_POST_TRAUMATIQUE)))
            .andExpect(jsonPath("$.[*].hematomeSousCutane").value(hasItem(DEFAULT_HEMATOME_SOUS_CUTANE.booleanValue())))
            .andExpect(jsonPath("$.[*].typeHematomeSousCutane").value(hasItem(DEFAULT_TYPE_HEMATOME_SOUS_CUTANE.toString())))
            .andExpect(jsonPath("$.[*].frequenceHematomeSousCutane").value(hasItem(DEFAULT_FREQUENCE_HEMATOME_SOUS_CUTANE)))
            .andExpect(jsonPath("$.[*].hematomeMusculaire").value(hasItem(DEFAULT_HEMATOME_MUSCULAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].typeHematomeMusculaire").value(hasItem(DEFAULT_TYPE_HEMATOME_MUSCULAIRE.toString())))
            .andExpect(jsonPath("$.[*].frequenceHematomeMusculaire").value(hasItem(DEFAULT_FREQUENCE_HEMATOME_MUSCULAIRE)))
            .andExpect(jsonPath("$.[*].hematomeTissusMousProfonds").value(hasItem(DEFAULT_HEMATOME_TISSUS_MOUS_PROFONDS.booleanValue())))
            .andExpect(
                jsonPath("$.[*].typeHematomeTissusMousProfonds").value(hasItem(DEFAULT_TYPE_HEMATOME_TISSUS_MOUS_PROFONDS.toString()))
            )
            .andExpect(
                jsonPath("$.[*].frequenceHematomeTissusMousProfonds").value(hasItem(DEFAULT_FREQUENCE_HEMATOME_TISSUS_MOUS_PROFONDS))
            )
            .andExpect(jsonPath("$.[*].hemarthrose").value(hasItem(DEFAULT_HEMARTHROSE.booleanValue())))
            .andExpect(jsonPath("$.[*].typeHemarthrose").value(hasItem(DEFAULT_TYPE_HEMARTHROSE.toString())))
            .andExpect(jsonPath("$.[*].frequenceHemarthrose").value(hasItem(DEFAULT_FREQUENCE_HEMARTHROSE)))
            .andExpect(jsonPath("$.[*].saignementSNC").value(hasItem(DEFAULT_SAIGNEMENT_SNC.booleanValue())))
            .andExpect(jsonPath("$.[*].typeSaignementSNC").value(hasItem(DEFAULT_TYPE_SAIGNEMENT_SNC.toString())))
            .andExpect(jsonPath("$.[*].frequenceSaignementSNC").value(hasItem(DEFAULT_FREQUENCE_SAIGNEMENT_SNC)))
            .andExpect(jsonPath("$.[*].saignementChuteCordon").value(hasItem(DEFAULT_SAIGNEMENT_CHUTE_CORDON.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceSaignementChuteCordon").value(hasItem(DEFAULT_FREQUENCE_SAIGNEMENT_CHUTE_CORDON)))
            .andExpect(jsonPath("$.[*].retardCicatrisationPlaies").value(hasItem(DEFAULT_RETARD_CICATRISATION_PLAIES.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceRetardCicatrisationPlaies").value(hasItem(DEFAULT_FREQUENCE_RETARD_CICATRISATION_PLAIES)))
            .andExpect(jsonPath("$.[*].autresManifestations").value(hasItem(DEFAULT_AUTRES_MANIFESTATIONS.booleanValue())))
            .andExpect(
                jsonPath("$.[*].faussesCouchesSpontaneesRecurrence").value(
                    hasItem(DEFAULT_FAUSSES_COUCHES_SPONTANEES_RECURRENCE.booleanValue())
                )
            )
            .andExpect(jsonPath("$.[*].frequenceFaussesCouches").value(hasItem(DEFAULT_FREQUENCE_FAUSSES_COUCHES)))
            .andExpect(jsonPath("$.[*].accidentsThrombotiques").value(hasItem(DEFAULT_ACCIDENTS_THROMBOTIQUES.booleanValue())))
            .andExpect(jsonPath("$.[*].thromboseVeineuse").value(hasItem(DEFAULT_THROMBOSE_VEINEUSE.booleanValue())))
            .andExpect(jsonPath("$.[*].thromboseArterielle").value(hasItem(DEFAULT_THROMBOSE_ARTERIELLE.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceAccidentsThrombotiques").value(hasItem(DEFAULT_FREQUENCE_ACCIDENTS_THROMBOTIQUES)))
            .andExpect(jsonPath("$.[*].hemoperitoine").value(hasItem(DEFAULT_HEMOPERITOINE.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceHemoperitoine").value(hasItem(DEFAULT_FREQUENCE_HEMOPERITOINE)))
            .andExpect(jsonPath("$.[*].ruptureKystesHemorragiques").value(hasItem(DEFAULT_RUPTURE_KYSTES_HEMORRAGIQUES.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceRuptureKystes").value(hasItem(DEFAULT_FREQUENCE_RUPTURE_KYSTES)))
            .andExpect(jsonPath("$.[*].hemorragieGrave").value(hasItem(DEFAULT_HEMORRAGIE_GRAVE.booleanValue())))
            .andExpect(jsonPath("$.[*].frequenceHemorragieGrave").value(hasItem(DEFAULT_FREQUENCE_HEMORRAGIE_GRAVE)))
            .andExpect(jsonPath("$.[*].graviteDeglobulisation").value(hasItem(DEFAULT_GRAVITE_DEGLOBULISATION.booleanValue())))
            .andExpect(jsonPath("$.[*].graviteEtatDeChoc").value(hasItem(DEFAULT_GRAVITE_ETAT_DE_CHOC.booleanValue())))
            .andExpect(jsonPath("$.[*].graviteParLocalisation").value(hasItem(DEFAULT_GRAVITE_PAR_LOCALISATION.booleanValue())))
            .andExpect(jsonPath("$.[*].siegeGraviteLocalisation").value(hasItem(DEFAULT_SIEGE_GRAVITE_LOCALISATION)))
            .andExpect(jsonPath("$.[*].chirurgiePrepare").value(hasItem(DEFAULT_CHIRURGIE_PREPARE.toString())))
            .andExpect(jsonPath("$.[*].chirurgieSuiteHemorragique").value(hasItem(DEFAULT_CHIRURGIE_SUITE_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].circoncisionPrepare").value(hasItem(DEFAULT_CIRCONCISION_PREPARE.toString())))
            .andExpect(jsonPath("$.[*].circoncisionSuiteHemorragique").value(hasItem(DEFAULT_CIRCONCISION_SUITE_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].ageCirconcision").value(hasItem(DEFAULT_AGE_CIRCONCISION)))
            .andExpect(jsonPath("$.[*].traitementPFC").value(hasItem(DEFAULT_TRAITEMENT_PFC.toString())))
            .andExpect(jsonPath("$.[*].traitementCryoprecipite").value(hasItem(DEFAULT_TRAITEMENT_CRYOPRECIPITE.toString())))
            .andExpect(jsonPath("$.[*].traitementConcentreFacteurXI").value(hasItem(DEFAULT_TRAITEMENT_CONCENTRE_FACTEUR_XI.toString())))
            .andExpect(jsonPath("$.[*].traitementCulotsPlaquettaires").value(hasItem(DEFAULT_TRAITEMENT_CULOTS_PLAQUETTAIRES.toString())))
            .andExpect(jsonPath("$.[*].traitementDicynone").value(hasItem(DEFAULT_TRAITEMENT_DICYNONE.toString())))
            .andExpect(jsonPath("$.[*].traitementExacyl").value(hasItem(DEFAULT_TRAITEMENT_EXACYL.toString())))
            .andExpect(jsonPath("$.[*].traitementMartial").value(hasItem(DEFAULT_TRAITEMENT_MARTIAL.toString())))
            .andExpect(jsonPath("$.[*].traitementOestroprogestatif").value(hasItem(DEFAULT_TRAITEMENT_OESTROPROGESTATIF.toString())))
            .andExpect(jsonPath("$.[*].transfusionCGR").value(hasItem(DEFAULT_TRANSFUSION_CGR.toString())))
            .andExpect(jsonPath("$.[*].alloImmunisation").value(hasItem(DEFAULT_ALLO_IMMUNISATION.toString())))
            .andExpect(jsonPath("$.[*].inhibiteurNeutralisant").value(hasItem(DEFAULT_INHIBITEUR_NEUTRALISANT.toString())))
            .andExpect(jsonPath("$.[*].titreInhibiteurUB").value(hasItem(DEFAULT_TITRE_INHIBITEUR_UB.doubleValue())))
            .andExpect(jsonPath("$.[*].inhibiteurNonNeutralisant").value(hasItem(DEFAULT_INHIBITEUR_NON_NEUTRALISANT.toString())))
            .andExpect(jsonPath("$.[*].infectionVIH").value(hasItem(DEFAULT_INFECTION_VIH.toString())))
            .andExpect(jsonPath("$.[*].infectionVHC").value(hasItem(DEFAULT_INFECTION_VHC.toString())))
            .andExpect(jsonPath("$.[*].infectionVHB").value(hasItem(DEFAULT_INFECTION_VHB.toString())))
            .andExpect(jsonPath("$.[*].thrombose").value(hasItem(DEFAULT_THROMBOSE.booleanValue())))
            .andExpect(jsonPath("$.[*].reactionAllergique").value(hasItem(DEFAULT_REACTION_ALLERGIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].autresComplications").value(hasItem(DEFAULT_AUTRES_COMPLICATIONS)))
            .andExpect(jsonPath("$.[*].adherenceTherapeutique").value(hasItem(DEFAULT_ADHERENCE_THERAPEUTIQUE.toString())))
            .andExpect(jsonPath("$.[*].causeNonAdherence").value(hasItem(DEFAULT_CAUSE_NON_ADHERENCE)))
            .andExpect(jsonPath("$.[*].statutSocial").value(hasItem(DEFAULT_STATUT_SOCIAL.toString())))
            .andExpect(jsonPath("$.[*].marie").value(hasItem(DEFAULT_MARIE.toString())))
            .andExpect(jsonPath("$.[*].enfants").value(hasItem(DEFAULT_ENFANTS.toString())))
            .andExpect(jsonPath("$.[*].activitePhysique").value(hasItem(DEFAULT_ACTIVITE_PHYSIQUE.toString())))
            .andExpect(jsonPath("$.[*].typeActivitePhysique").value(hasItem(DEFAULT_TYPE_ACTIVITE_PHYSIQUE)))
            .andExpect(jsonPath("$.[*].sequelles").value(hasItem(DEFAULT_SEQUELLES.toString())))
            .andExpect(jsonPath("$.[*].typeSequelles").value(hasItem(DEFAULT_TYPE_SEQUELLES)))
            .andExpect(jsonPath("$.[*].patientDecede").value(hasItem(DEFAULT_PATIENT_DECEDE.toString())))
            .andExpect(jsonPath("$.[*].dateDeces").value(hasItem(DEFAULT_DATE_DECES.toString())))
            .andExpect(jsonPath("$.[*].ageDeces").value(hasItem(DEFAULT_AGE_DECES)))
            .andExpect(jsonPath("$.[*].causeDeces").value(hasItem(DEFAULT_CAUSE_DECES.toString())));
    }

    @Test
    @Transactional
    void getFicheFacteurXI() throws Exception {
        // Initialize the database
        insertedFicheFacteurXI = ficheFacteurXIRepository.saveAndFlush(ficheFacteurXI);

        // Get the ficheFacteurXI
        restFicheFacteurXIMockMvc
            .perform(get(ENTITY_API_URL_ID, ficheFacteurXI.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ficheFacteurXI.getId().intValue()))
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
            .andExpect(jsonPath("$.syndromeHemorragique").value(DEFAULT_SYNDROME_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.precisionSyndromeHemorragique").value(DEFAULT_PRECISION_SYNDROME_HEMORRAGIQUE))
            .andExpect(jsonPath("$.decouverteFortuite").value(DEFAULT_DECOUVERTE_FORTUITE.toString()))
            .andExpect(jsonPath("$.precisionDecouverteFortuite").value(DEFAULT_PRECISION_DECOUVERTE_FORTUITE))
            .andExpect(jsonPath("$.jamaisSaigne").value(DEFAULT_JAMAIS_SAIGNE.toString()))
            .andExpect(jsonPath("$.agePremierSigneCliniqueJours").value(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_JOURS))
            .andExpect(jsonPath("$.agePremierSigneCliniqueMois").value(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_MOIS))
            .andExpect(jsonPath("$.agePremierSigneCliniqueAnnees").value(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES))
            .andExpect(jsonPath("$.symptomeEcchymose").value(DEFAULT_SYMPTOME_ECCHYMOSE.booleanValue()))
            .andExpect(jsonPath("$.symptomeEpistaxis").value(DEFAULT_SYMPTOME_EPISTAXIS.booleanValue()))
            .andExpect(jsonPath("$.symptomeGingivorragies").value(DEFAULT_SYMPTOME_GINGIVORRAGIES.booleanValue()))
            .andExpect(jsonPath("$.symptomeMetrorragies").value(DEFAULT_SYMPTOME_METRORRAGIES.booleanValue()))
            .andExpect(jsonPath("$.symptomeMelena").value(DEFAULT_SYMPTOME_MELENA.booleanValue()))
            .andExpect(jsonPath("$.symptomeHemoptysie").value(DEFAULT_SYMPTOME_HEMOPTYSIE.booleanValue()))
            .andExpect(
                jsonPath("$.symptomeHemorragiePostAmygdalectomie").value(DEFAULT_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE.booleanValue())
            )
            .andExpect(
                jsonPath("$.symptomeHemorragiePostAvulsionDentaire").value(
                    DEFAULT_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE.booleanValue()
                )
            )
            .andExpect(jsonPath("$.symptomeHemorragieChuteCordon").value(DEFAULT_SYMPTOME_HEMORRAGIE_CHUTE_CORDON.booleanValue()))
            .andExpect(jsonPath("$.symptomeAutre").value(DEFAULT_SYMPTOME_AUTRE.booleanValue()))
            .andExpect(jsonPath("$.symptomeAutreDescription").value(DEFAULT_SYMPTOME_AUTRE_DESCRIPTION))
            .andExpect(jsonPath("$.datePremiereConsultation").value(DEFAULT_DATE_PREMIERE_CONSULTATION.toString()))
            .andExpect(jsonPath("$.agePremiereConsultationJours").value(DEFAULT_AGE_PREMIERE_CONSULTATION_JOURS))
            .andExpect(jsonPath("$.agePremiereConsultationMois").value(DEFAULT_AGE_PREMIERE_CONSULTATION_MOIS))
            .andExpect(jsonPath("$.agePremiereConsultationAnnees").value(DEFAULT_AGE_PREMIERE_CONSULTATION_ANNEES))
            .andExpect(jsonPath("$.ageDiagnosticJours").value(DEFAULT_AGE_DIAGNOSTIC_JOURS))
            .andExpect(jsonPath("$.ageDiagnosticMois").value(DEFAULT_AGE_DIAGNOSTIC_MOIS))
            .andExpect(jsonPath("$.ageDiagnosticAnnees").value(DEFAULT_AGE_DIAGNOSTIC_ANNEES))
            .andExpect(jsonPath("$.hb").value(DEFAULT_HB.doubleValue()))
            .andExpect(jsonPath("$.hte").value(DEFAULT_HTE.doubleValue()))
            .andExpect(jsonPath("$.plaquettes").value(DEFAULT_PLAQUETTES))
            .andExpect(jsonPath("$.pfaColEpi").value(DEFAULT_PFA_COL_EPI.doubleValue()))
            .andExpect(jsonPath("$.pfaColADP").value(DEFAULT_PFA_COL_ADP.doubleValue()))
            .andExpect(jsonPath("$.tp").value(DEFAULT_TP.doubleValue()))
            .andExpect(jsonPath("$.tpMPlusT").value(DEFAULT_TP_M_PLUS_T.doubleValue()))
            .andExpect(jsonPath("$.fibrinogene").value(DEFAULT_FIBRINOGENE.doubleValue()))
            .andExpect(jsonPath("$.tcaMT").value(DEFAULT_TCA_MT.doubleValue()))
            .andExpect(jsonPath("$.tcaMPlusT").value(DEFAULT_TCA_M_PLUS_T.doubleValue()))
            .andExpect(jsonPath("$.tcaTemoin").value(DEFAULT_TCA_TEMOIN.doubleValue()))
            .andExpect(jsonPath("$.facteurXI").value(DEFAULT_FACTEUR_XI.doubleValue()))
            .andExpect(jsonPath("$.autresFacteursDosageDescription").value(DEFAULT_AUTRES_FACTEURS_DOSAGE_DESCRIPTION))
            .andExpect(jsonPath("$.autresFacteursTaux1").value(DEFAULT_AUTRES_FACTEURS_TAUX_1.doubleValue()))
            .andExpect(jsonPath("$.autresFacteursTaux2").value(DEFAULT_AUTRES_FACTEURS_TAUX_2.doubleValue()))
            .andExpect(jsonPath("$.autresFacteursTaux3").value(DEFAULT_AUTRES_FACTEURS_TAUX_3.doubleValue()))
            .andExpect(jsonPath("$.confirmationMoleculaire").value(DEFAULT_CONFIRMATION_MOLECULAIRE.toString()))
            .andExpect(jsonPath("$.mutationPresente").value(DEFAULT_MUTATION_PRESENTE.booleanValue()))
            .andExpect(jsonPath("$.mutationNom").value(DEFAULT_MUTATION_NOM))
            .andExpect(jsonPath("$.mutationZygote").value(DEFAULT_MUTATION_ZYGOTE.toString()))
            .andExpect(jsonPath("$.mutation2Nom").value(DEFAULT_MUTATION_2_NOM))
            .andExpect(jsonPath("$.serologieVHB").value(DEFAULT_SEROLOGIE_VHB.toString()))
            .andExpect(jsonPath("$.serologieVHC").value(DEFAULT_SEROLOGIE_VHC.toString()))
            .andExpect(jsonPath("$.serologieVIH").value(DEFAULT_SEROLOGIE_VIH.toString()))
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
            .andExpect(jsonPath("$.hemorragiePostTraumatique").value(DEFAULT_HEMORRAGIE_POST_TRAUMATIQUE.booleanValue()))
            .andExpect(jsonPath("$.frequenceHemorragiePostTraumatique").value(DEFAULT_FREQUENCE_HEMORRAGIE_POST_TRAUMATIQUE))
            .andExpect(jsonPath("$.hematomeSousCutane").value(DEFAULT_HEMATOME_SOUS_CUTANE.booleanValue()))
            .andExpect(jsonPath("$.typeHematomeSousCutane").value(DEFAULT_TYPE_HEMATOME_SOUS_CUTANE.toString()))
            .andExpect(jsonPath("$.frequenceHematomeSousCutane").value(DEFAULT_FREQUENCE_HEMATOME_SOUS_CUTANE))
            .andExpect(jsonPath("$.hematomeMusculaire").value(DEFAULT_HEMATOME_MUSCULAIRE.booleanValue()))
            .andExpect(jsonPath("$.typeHematomeMusculaire").value(DEFAULT_TYPE_HEMATOME_MUSCULAIRE.toString()))
            .andExpect(jsonPath("$.frequenceHematomeMusculaire").value(DEFAULT_FREQUENCE_HEMATOME_MUSCULAIRE))
            .andExpect(jsonPath("$.hematomeTissusMousProfonds").value(DEFAULT_HEMATOME_TISSUS_MOUS_PROFONDS.booleanValue()))
            .andExpect(jsonPath("$.typeHematomeTissusMousProfonds").value(DEFAULT_TYPE_HEMATOME_TISSUS_MOUS_PROFONDS.toString()))
            .andExpect(jsonPath("$.frequenceHematomeTissusMousProfonds").value(DEFAULT_FREQUENCE_HEMATOME_TISSUS_MOUS_PROFONDS))
            .andExpect(jsonPath("$.hemarthrose").value(DEFAULT_HEMARTHROSE.booleanValue()))
            .andExpect(jsonPath("$.typeHemarthrose").value(DEFAULT_TYPE_HEMARTHROSE.toString()))
            .andExpect(jsonPath("$.frequenceHemarthrose").value(DEFAULT_FREQUENCE_HEMARTHROSE))
            .andExpect(jsonPath("$.saignementSNC").value(DEFAULT_SAIGNEMENT_SNC.booleanValue()))
            .andExpect(jsonPath("$.typeSaignementSNC").value(DEFAULT_TYPE_SAIGNEMENT_SNC.toString()))
            .andExpect(jsonPath("$.frequenceSaignementSNC").value(DEFAULT_FREQUENCE_SAIGNEMENT_SNC))
            .andExpect(jsonPath("$.saignementChuteCordon").value(DEFAULT_SAIGNEMENT_CHUTE_CORDON.booleanValue()))
            .andExpect(jsonPath("$.frequenceSaignementChuteCordon").value(DEFAULT_FREQUENCE_SAIGNEMENT_CHUTE_CORDON))
            .andExpect(jsonPath("$.retardCicatrisationPlaies").value(DEFAULT_RETARD_CICATRISATION_PLAIES.booleanValue()))
            .andExpect(jsonPath("$.frequenceRetardCicatrisationPlaies").value(DEFAULT_FREQUENCE_RETARD_CICATRISATION_PLAIES))
            .andExpect(jsonPath("$.autresManifestations").value(DEFAULT_AUTRES_MANIFESTATIONS.booleanValue()))
            .andExpect(jsonPath("$.faussesCouchesSpontaneesRecurrence").value(DEFAULT_FAUSSES_COUCHES_SPONTANEES_RECURRENCE.booleanValue()))
            .andExpect(jsonPath("$.frequenceFaussesCouches").value(DEFAULT_FREQUENCE_FAUSSES_COUCHES))
            .andExpect(jsonPath("$.accidentsThrombotiques").value(DEFAULT_ACCIDENTS_THROMBOTIQUES.booleanValue()))
            .andExpect(jsonPath("$.thromboseVeineuse").value(DEFAULT_THROMBOSE_VEINEUSE.booleanValue()))
            .andExpect(jsonPath("$.thromboseArterielle").value(DEFAULT_THROMBOSE_ARTERIELLE.booleanValue()))
            .andExpect(jsonPath("$.frequenceAccidentsThrombotiques").value(DEFAULT_FREQUENCE_ACCIDENTS_THROMBOTIQUES))
            .andExpect(jsonPath("$.hemoperitoine").value(DEFAULT_HEMOPERITOINE.booleanValue()))
            .andExpect(jsonPath("$.frequenceHemoperitoine").value(DEFAULT_FREQUENCE_HEMOPERITOINE))
            .andExpect(jsonPath("$.ruptureKystesHemorragiques").value(DEFAULT_RUPTURE_KYSTES_HEMORRAGIQUES.booleanValue()))
            .andExpect(jsonPath("$.frequenceRuptureKystes").value(DEFAULT_FREQUENCE_RUPTURE_KYSTES))
            .andExpect(jsonPath("$.hemorragieGrave").value(DEFAULT_HEMORRAGIE_GRAVE.booleanValue()))
            .andExpect(jsonPath("$.frequenceHemorragieGrave").value(DEFAULT_FREQUENCE_HEMORRAGIE_GRAVE))
            .andExpect(jsonPath("$.graviteDeglobulisation").value(DEFAULT_GRAVITE_DEGLOBULISATION.booleanValue()))
            .andExpect(jsonPath("$.graviteEtatDeChoc").value(DEFAULT_GRAVITE_ETAT_DE_CHOC.booleanValue()))
            .andExpect(jsonPath("$.graviteParLocalisation").value(DEFAULT_GRAVITE_PAR_LOCALISATION.booleanValue()))
            .andExpect(jsonPath("$.siegeGraviteLocalisation").value(DEFAULT_SIEGE_GRAVITE_LOCALISATION))
            .andExpect(jsonPath("$.chirurgiePrepare").value(DEFAULT_CHIRURGIE_PREPARE.toString()))
            .andExpect(jsonPath("$.chirurgieSuiteHemorragique").value(DEFAULT_CHIRURGIE_SUITE_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.circoncisionPrepare").value(DEFAULT_CIRCONCISION_PREPARE.toString()))
            .andExpect(jsonPath("$.circoncisionSuiteHemorragique").value(DEFAULT_CIRCONCISION_SUITE_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.ageCirconcision").value(DEFAULT_AGE_CIRCONCISION))
            .andExpect(jsonPath("$.traitementPFC").value(DEFAULT_TRAITEMENT_PFC.toString()))
            .andExpect(jsonPath("$.traitementCryoprecipite").value(DEFAULT_TRAITEMENT_CRYOPRECIPITE.toString()))
            .andExpect(jsonPath("$.traitementConcentreFacteurXI").value(DEFAULT_TRAITEMENT_CONCENTRE_FACTEUR_XI.toString()))
            .andExpect(jsonPath("$.traitementCulotsPlaquettaires").value(DEFAULT_TRAITEMENT_CULOTS_PLAQUETTAIRES.toString()))
            .andExpect(jsonPath("$.traitementDicynone").value(DEFAULT_TRAITEMENT_DICYNONE.toString()))
            .andExpect(jsonPath("$.traitementExacyl").value(DEFAULT_TRAITEMENT_EXACYL.toString()))
            .andExpect(jsonPath("$.traitementMartial").value(DEFAULT_TRAITEMENT_MARTIAL.toString()))
            .andExpect(jsonPath("$.traitementOestroprogestatif").value(DEFAULT_TRAITEMENT_OESTROPROGESTATIF.toString()))
            .andExpect(jsonPath("$.transfusionCGR").value(DEFAULT_TRANSFUSION_CGR.toString()))
            .andExpect(jsonPath("$.alloImmunisation").value(DEFAULT_ALLO_IMMUNISATION.toString()))
            .andExpect(jsonPath("$.inhibiteurNeutralisant").value(DEFAULT_INHIBITEUR_NEUTRALISANT.toString()))
            .andExpect(jsonPath("$.titreInhibiteurUB").value(DEFAULT_TITRE_INHIBITEUR_UB.doubleValue()))
            .andExpect(jsonPath("$.inhibiteurNonNeutralisant").value(DEFAULT_INHIBITEUR_NON_NEUTRALISANT.toString()))
            .andExpect(jsonPath("$.infectionVIH").value(DEFAULT_INFECTION_VIH.toString()))
            .andExpect(jsonPath("$.infectionVHC").value(DEFAULT_INFECTION_VHC.toString()))
            .andExpect(jsonPath("$.infectionVHB").value(DEFAULT_INFECTION_VHB.toString()))
            .andExpect(jsonPath("$.thrombose").value(DEFAULT_THROMBOSE.booleanValue()))
            .andExpect(jsonPath("$.reactionAllergique").value(DEFAULT_REACTION_ALLERGIQUE.booleanValue()))
            .andExpect(jsonPath("$.autresComplications").value(DEFAULT_AUTRES_COMPLICATIONS))
            .andExpect(jsonPath("$.adherenceTherapeutique").value(DEFAULT_ADHERENCE_THERAPEUTIQUE.toString()))
            .andExpect(jsonPath("$.causeNonAdherence").value(DEFAULT_CAUSE_NON_ADHERENCE))
            .andExpect(jsonPath("$.statutSocial").value(DEFAULT_STATUT_SOCIAL.toString()))
            .andExpect(jsonPath("$.marie").value(DEFAULT_MARIE.toString()))
            .andExpect(jsonPath("$.enfants").value(DEFAULT_ENFANTS.toString()))
            .andExpect(jsonPath("$.activitePhysique").value(DEFAULT_ACTIVITE_PHYSIQUE.toString()))
            .andExpect(jsonPath("$.typeActivitePhysique").value(DEFAULT_TYPE_ACTIVITE_PHYSIQUE))
            .andExpect(jsonPath("$.sequelles").value(DEFAULT_SEQUELLES.toString()))
            .andExpect(jsonPath("$.typeSequelles").value(DEFAULT_TYPE_SEQUELLES))
            .andExpect(jsonPath("$.patientDecede").value(DEFAULT_PATIENT_DECEDE.toString()))
            .andExpect(jsonPath("$.dateDeces").value(DEFAULT_DATE_DECES.toString()))
            .andExpect(jsonPath("$.ageDeces").value(DEFAULT_AGE_DECES))
            .andExpect(jsonPath("$.causeDeces").value(DEFAULT_CAUSE_DECES.toString()));
    }

    @Test
    @Transactional
    void getNonExistingFicheFacteurXI() throws Exception {
        // Get the ficheFacteurXI
        restFicheFacteurXIMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFicheFacteurXI() throws Exception {
        // Initialize the database
        insertedFicheFacteurXI = ficheFacteurXIRepository.saveAndFlush(ficheFacteurXI);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheFacteurXI
        FicheFacteurXI updatedFicheFacteurXI = ficheFacteurXIRepository.findById(ficheFacteurXI.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFicheFacteurXI are not directly saved in db
        em.detach(updatedFicheFacteurXI);
        updatedFicheFacteurXI
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
            .syndromeHemorragique(UPDATED_SYNDROME_HEMORRAGIQUE)
            .precisionSyndromeHemorragique(UPDATED_PRECISION_SYNDROME_HEMORRAGIQUE)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .precisionDecouverteFortuite(UPDATED_PRECISION_DECOUVERTE_FORTUITE)
            .jamaisSaigne(UPDATED_JAMAIS_SAIGNE)
            .agePremierSigneCliniqueJours(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_JOURS)
            .agePremierSigneCliniqueMois(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_MOIS)
            .agePremierSigneCliniqueAnnees(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES)
            .symptomeEcchymose(UPDATED_SYMPTOME_ECCHYMOSE)
            .symptomeEpistaxis(UPDATED_SYMPTOME_EPISTAXIS)
            .symptomeGingivorragies(UPDATED_SYMPTOME_GINGIVORRAGIES)
            .symptomeMetrorragies(UPDATED_SYMPTOME_METRORRAGIES)
            .symptomeMelena(UPDATED_SYMPTOME_MELENA)
            .symptomeHemoptysie(UPDATED_SYMPTOME_HEMOPTYSIE)
            .symptomeHemorragiePostAmygdalectomie(UPDATED_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE)
            .symptomeHemorragiePostAvulsionDentaire(UPDATED_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE)
            .symptomeHemorragieChuteCordon(UPDATED_SYMPTOME_HEMORRAGIE_CHUTE_CORDON)
            .symptomeAutre(UPDATED_SYMPTOME_AUTRE)
            .symptomeAutreDescription(UPDATED_SYMPTOME_AUTRE_DESCRIPTION)
            .datePremiereConsultation(UPDATED_DATE_PREMIERE_CONSULTATION)
            .agePremiereConsultationJours(UPDATED_AGE_PREMIERE_CONSULTATION_JOURS)
            .agePremiereConsultationMois(UPDATED_AGE_PREMIERE_CONSULTATION_MOIS)
            .agePremiereConsultationAnnees(UPDATED_AGE_PREMIERE_CONSULTATION_ANNEES)
            .ageDiagnosticJours(UPDATED_AGE_DIAGNOSTIC_JOURS)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnees(UPDATED_AGE_DIAGNOSTIC_ANNEES)
            .hb(UPDATED_HB)
            .hte(UPDATED_HTE)
            .plaquettes(UPDATED_PLAQUETTES)
            .pfaColEpi(UPDATED_PFA_COL_EPI)
            .pfaColADP(UPDATED_PFA_COL_ADP)
            .tp(UPDATED_TP)
            .tpMPlusT(UPDATED_TP_M_PLUS_T)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMPlusT(UPDATED_TCA_M_PLUS_T)
            .tcaTemoin(UPDATED_TCA_TEMOIN)
            .facteurXI(UPDATED_FACTEUR_XI)
            .autresFacteursDosageDescription(UPDATED_AUTRES_FACTEURS_DOSAGE_DESCRIPTION)
            .autresFacteursTaux1(UPDATED_AUTRES_FACTEURS_TAUX_1)
            .autresFacteursTaux2(UPDATED_AUTRES_FACTEURS_TAUX_2)
            .autresFacteursTaux3(UPDATED_AUTRES_FACTEURS_TAUX_3)
            .confirmationMoleculaire(UPDATED_CONFIRMATION_MOLECULAIRE)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationZygote(UPDATED_MUTATION_ZYGOTE)
            .mutation2Nom(UPDATED_MUTATION_2_NOM)
            .serologieVHB(UPDATED_SEROLOGIE_VHB)
            .serologieVHC(UPDATED_SEROLOGIE_VHC)
            .serologieVIH(UPDATED_SEROLOGIE_VIH)
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
            .hemorragiePostTraumatique(UPDATED_HEMORRAGIE_POST_TRAUMATIQUE)
            .frequenceHemorragiePostTraumatique(UPDATED_FREQUENCE_HEMORRAGIE_POST_TRAUMATIQUE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(UPDATED_TYPE_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(UPDATED_TYPE_HEMATOME_MUSCULAIRE)
            .frequenceHematomeMusculaire(UPDATED_FREQUENCE_HEMATOME_MUSCULAIRE)
            .hematomeTissusMousProfonds(UPDATED_HEMATOME_TISSUS_MOUS_PROFONDS)
            .typeHematomeTissusMousProfonds(UPDATED_TYPE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .frequenceHematomeTissusMousProfonds(UPDATED_FREQUENCE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .typeHemarthrose(UPDATED_TYPE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .typeSaignementSNC(UPDATED_TYPE_SAIGNEMENT_SNC)
            .frequenceSaignementSNC(UPDATED_FREQUENCE_SAIGNEMENT_SNC)
            .saignementChuteCordon(UPDATED_SAIGNEMENT_CHUTE_CORDON)
            .frequenceSaignementChuteCordon(UPDATED_FREQUENCE_SAIGNEMENT_CHUTE_CORDON)
            .retardCicatrisationPlaies(UPDATED_RETARD_CICATRISATION_PLAIES)
            .frequenceRetardCicatrisationPlaies(UPDATED_FREQUENCE_RETARD_CICATRISATION_PLAIES)
            .autresManifestations(UPDATED_AUTRES_MANIFESTATIONS)
            .faussesCouchesSpontaneesRecurrence(UPDATED_FAUSSES_COUCHES_SPONTANEES_RECURRENCE)
            .frequenceFaussesCouches(UPDATED_FREQUENCE_FAUSSES_COUCHES)
            .accidentsThrombotiques(UPDATED_ACCIDENTS_THROMBOTIQUES)
            .thromboseVeineuse(UPDATED_THROMBOSE_VEINEUSE)
            .thromboseArterielle(UPDATED_THROMBOSE_ARTERIELLE)
            .frequenceAccidentsThrombotiques(UPDATED_FREQUENCE_ACCIDENTS_THROMBOTIQUES)
            .hemoperitoine(UPDATED_HEMOPERITOINE)
            .frequenceHemoperitoine(UPDATED_FREQUENCE_HEMOPERITOINE)
            .ruptureKystesHemorragiques(UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES)
            .frequenceRuptureKystes(UPDATED_FREQUENCE_RUPTURE_KYSTES)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .frequenceHemorragieGrave(UPDATED_FREQUENCE_HEMORRAGIE_GRAVE)
            .graviteDeglobulisation(UPDATED_GRAVITE_DEGLOBULISATION)
            .graviteEtatDeChoc(UPDATED_GRAVITE_ETAT_DE_CHOC)
            .graviteParLocalisation(UPDATED_GRAVITE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .chirurgieSuiteHemorragique(UPDATED_CHIRURGIE_SUITE_HEMORRAGIQUE)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .circoncisionSuiteHemorragique(UPDATED_CIRCONCISION_SUITE_HEMORRAGIQUE)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .traitementPFC(UPDATED_TRAITEMENT_PFC)
            .traitementCryoprecipite(UPDATED_TRAITEMENT_CRYOPRECIPITE)
            .traitementConcentreFacteurXI(UPDATED_TRAITEMENT_CONCENTRE_FACTEUR_XI)
            .traitementCulotsPlaquettaires(UPDATED_TRAITEMENT_CULOTS_PLAQUETTAIRES)
            .traitementDicynone(UPDATED_TRAITEMENT_DICYNONE)
            .traitementExacyl(UPDATED_TRAITEMENT_EXACYL)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .transfusionCGR(UPDATED_TRANSFUSION_CGR)
            .alloImmunisation(UPDATED_ALLO_IMMUNISATION)
            .inhibiteurNeutralisant(UPDATED_INHIBITEUR_NEUTRALISANT)
            .titreInhibiteurUB(UPDATED_TITRE_INHIBITEUR_UB)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .infectionVIH(UPDATED_INFECTION_VIH)
            .infectionVHC(UPDATED_INFECTION_VHC)
            .infectionVHB(UPDATED_INFECTION_VHB)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .autresComplications(UPDATED_AUTRES_COMPLICATIONS)
            .adherenceTherapeutique(UPDATED_ADHERENCE_THERAPEUTIQUE)
            .causeNonAdherence(UPDATED_CAUSE_NON_ADHERENCE)
            .statutSocial(UPDATED_STATUT_SOCIAL)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .causeDeces(UPDATED_CAUSE_DECES);
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(updatedFicheFacteurXI);

        restFicheFacteurXIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheFacteurXIDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheFacteurXIDTO))
            )
            .andExpect(status().isOk());

        // Validate the FicheFacteurXI in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFicheFacteurXIToMatchAllProperties(updatedFicheFacteurXI);
    }

    @Test
    @Transactional
    void putNonExistingFicheFacteurXI() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXI.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXI
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheFacteurXIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheFacteurXIDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheFacteurXIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXI in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFicheFacteurXI() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXI.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXI
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurXIMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheFacteurXIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXI in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFicheFacteurXI() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXI.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXI
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurXIMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheFacteurXIDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheFacteurXI in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFicheFacteurXIWithPatch() throws Exception {
        // Initialize the database
        insertedFicheFacteurXI = ficheFacteurXIRepository.saveAndFlush(ficheFacteurXI);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheFacteurXI using partial update
        FicheFacteurXI partialUpdatedFicheFacteurXI = new FicheFacteurXI();
        partialUpdatedFicheFacteurXI.setId(ficheFacteurXI.getId());

        partialUpdatedFicheFacteurXI
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .sexe(UPDATED_SEXE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .ageActuel(UPDATED_AGE_ACTUEL)
            .professionPere(UPDATED_PROFESSION_PERE)
            .nomPrenomMere(UPDATED_NOM_PRENOM_MERE)
            .biologisteResponsableDg(UPDATED_BIOLOGISTE_RESPONSABLE_DG)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilaires(UPDATED_CAS_SIMILAIRES)
            .nombreCas(UPDATED_NOMBRE_CAS)
            .degreParenteCas1(UPDATED_DEGRE_PARENTE_CAS_1)
            .typeHemorragieCas1(UPDATED_TYPE_HEMORRAGIE_CAS_1)
            .cas2Numero(UPDATED_CAS_2_NUMERO)
            .degreParenteCas2(UPDATED_DEGRE_PARENTE_CAS_2)
            .cas3Numero(UPDATED_CAS_3_NUMERO)
            .degreParenteCas3(UPDATED_DEGRE_PARENTE_CAS_3)
            .nombreDeces(UPDATED_NOMBRE_DECES)
            .lienParenteDeces2(UPDATED_LIEN_PARENTE_DECES_2)
            .lienParenteDeces3(UPDATED_LIEN_PARENTE_DECES_3)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .precisionDecouverteFortuite(UPDATED_PRECISION_DECOUVERTE_FORTUITE)
            .agePremierSigneCliniqueJours(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_JOURS)
            .agePremierSigneCliniqueAnnees(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES)
            .symptomeEpistaxis(UPDATED_SYMPTOME_EPISTAXIS)
            .symptomeMetrorragies(UPDATED_SYMPTOME_METRORRAGIES)
            .symptomeMelena(UPDATED_SYMPTOME_MELENA)
            .symptomeHemoptysie(UPDATED_SYMPTOME_HEMOPTYSIE)
            .symptomeAutreDescription(UPDATED_SYMPTOME_AUTRE_DESCRIPTION)
            .agePremiereConsultationJours(UPDATED_AGE_PREMIERE_CONSULTATION_JOURS)
            .agePremiereConsultationAnnees(UPDATED_AGE_PREMIERE_CONSULTATION_ANNEES)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .plaquettes(UPDATED_PLAQUETTES)
            .tpMPlusT(UPDATED_TP_M_PLUS_T)
            .tcaMT(UPDATED_TCA_MT)
            .tcaTemoin(UPDATED_TCA_TEMOIN)
            .facteurXI(UPDATED_FACTEUR_XI)
            .autresFacteursTaux3(UPDATED_AUTRES_FACTEURS_TAUX_3)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationZygote(UPDATED_MUTATION_ZYGOTE)
            .serologieVHB(UPDATED_SEROLOGIE_VHB)
            .serologieVHC(UPDATED_SEROLOGIE_VHC)
            .gingivorragies(UPDATED_GINGIVORRAGIES)
            .typeGingivorragies(UPDATED_TYPE_GINGIVORRAGIES)
            .typeEcchymose(UPDATED_TYPE_ECCHYMOSE)
            .frequenceEcchymose(UPDATED_FREQUENCE_ECCHYMOSE)
            .frequenceMenorragie(UPDATED_FREQUENCE_MENORRAGIE)
            .hematemese(UPDATED_HEMATEMESE)
            .frequenceHematemese(UPDATED_FREQUENCE_HEMATEMESE)
            .frequenceRectorragie(UPDATED_FREQUENCE_RECTORRAGIE)
            .hemorragiePostTraumatique(UPDATED_HEMORRAGIE_POST_TRAUMATIQUE)
            .frequenceHemorragiePostTraumatique(UPDATED_FREQUENCE_HEMORRAGIE_POST_TRAUMATIQUE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(UPDATED_TYPE_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(UPDATED_TYPE_HEMATOME_MUSCULAIRE)
            .hematomeTissusMousProfonds(UPDATED_HEMATOME_TISSUS_MOUS_PROFONDS)
            .typeHematomeTissusMousProfonds(UPDATED_TYPE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .frequenceHematomeTissusMousProfonds(UPDATED_FREQUENCE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .typeHemarthrose(UPDATED_TYPE_HEMARTHROSE)
            .typeSaignementSNC(UPDATED_TYPE_SAIGNEMENT_SNC)
            .saignementChuteCordon(UPDATED_SAIGNEMENT_CHUTE_CORDON)
            .frequenceSaignementChuteCordon(UPDATED_FREQUENCE_SAIGNEMENT_CHUTE_CORDON)
            .faussesCouchesSpontaneesRecurrence(UPDATED_FAUSSES_COUCHES_SPONTANEES_RECURRENCE)
            .frequenceFaussesCouches(UPDATED_FREQUENCE_FAUSSES_COUCHES)
            .accidentsThrombotiques(UPDATED_ACCIDENTS_THROMBOTIQUES)
            .thromboseVeineuse(UPDATED_THROMBOSE_VEINEUSE)
            .frequenceAccidentsThrombotiques(UPDATED_FREQUENCE_ACCIDENTS_THROMBOTIQUES)
            .hemoperitoine(UPDATED_HEMOPERITOINE)
            .frequenceHemoperitoine(UPDATED_FREQUENCE_HEMOPERITOINE)
            .frequenceRuptureKystes(UPDATED_FREQUENCE_RUPTURE_KYSTES)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .graviteEtatDeChoc(UPDATED_GRAVITE_ETAT_DE_CHOC)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .traitementCryoprecipite(UPDATED_TRAITEMENT_CRYOPRECIPITE)
            .traitementConcentreFacteurXI(UPDATED_TRAITEMENT_CONCENTRE_FACTEUR_XI)
            .traitementExacyl(UPDATED_TRAITEMENT_EXACYL)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .transfusionCGR(UPDATED_TRANSFUSION_CGR)
            .alloImmunisation(UPDATED_ALLO_IMMUNISATION)
            .inhibiteurNeutralisant(UPDATED_INHIBITEUR_NEUTRALISANT)
            .infectionVHC(UPDATED_INFECTION_VHC)
            .infectionVHB(UPDATED_INFECTION_VHB)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .causeNonAdherence(UPDATED_CAUSE_NON_ADHERENCE)
            .marie(UPDATED_MARIE)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES);

        restFicheFacteurXIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheFacteurXI.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheFacteurXI))
            )
            .andExpect(status().isOk());

        // Validate the FicheFacteurXI in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheFacteurXIUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFicheFacteurXI, ficheFacteurXI),
            getPersistedFicheFacteurXI(ficheFacteurXI)
        );
    }

    @Test
    @Transactional
    void fullUpdateFicheFacteurXIWithPatch() throws Exception {
        // Initialize the database
        insertedFicheFacteurXI = ficheFacteurXIRepository.saveAndFlush(ficheFacteurXI);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheFacteurXI using partial update
        FicheFacteurXI partialUpdatedFicheFacteurXI = new FicheFacteurXI();
        partialUpdatedFicheFacteurXI.setId(ficheFacteurXI.getId());

        partialUpdatedFicheFacteurXI
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
            .syndromeHemorragique(UPDATED_SYNDROME_HEMORRAGIQUE)
            .precisionSyndromeHemorragique(UPDATED_PRECISION_SYNDROME_HEMORRAGIQUE)
            .decouverteFortuite(UPDATED_DECOUVERTE_FORTUITE)
            .precisionDecouverteFortuite(UPDATED_PRECISION_DECOUVERTE_FORTUITE)
            .jamaisSaigne(UPDATED_JAMAIS_SAIGNE)
            .agePremierSigneCliniqueJours(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_JOURS)
            .agePremierSigneCliniqueMois(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_MOIS)
            .agePremierSigneCliniqueAnnees(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES)
            .symptomeEcchymose(UPDATED_SYMPTOME_ECCHYMOSE)
            .symptomeEpistaxis(UPDATED_SYMPTOME_EPISTAXIS)
            .symptomeGingivorragies(UPDATED_SYMPTOME_GINGIVORRAGIES)
            .symptomeMetrorragies(UPDATED_SYMPTOME_METRORRAGIES)
            .symptomeMelena(UPDATED_SYMPTOME_MELENA)
            .symptomeHemoptysie(UPDATED_SYMPTOME_HEMOPTYSIE)
            .symptomeHemorragiePostAmygdalectomie(UPDATED_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE)
            .symptomeHemorragiePostAvulsionDentaire(UPDATED_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE)
            .symptomeHemorragieChuteCordon(UPDATED_SYMPTOME_HEMORRAGIE_CHUTE_CORDON)
            .symptomeAutre(UPDATED_SYMPTOME_AUTRE)
            .symptomeAutreDescription(UPDATED_SYMPTOME_AUTRE_DESCRIPTION)
            .datePremiereConsultation(UPDATED_DATE_PREMIERE_CONSULTATION)
            .agePremiereConsultationJours(UPDATED_AGE_PREMIERE_CONSULTATION_JOURS)
            .agePremiereConsultationMois(UPDATED_AGE_PREMIERE_CONSULTATION_MOIS)
            .agePremiereConsultationAnnees(UPDATED_AGE_PREMIERE_CONSULTATION_ANNEES)
            .ageDiagnosticJours(UPDATED_AGE_DIAGNOSTIC_JOURS)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnees(UPDATED_AGE_DIAGNOSTIC_ANNEES)
            .hb(UPDATED_HB)
            .hte(UPDATED_HTE)
            .plaquettes(UPDATED_PLAQUETTES)
            .pfaColEpi(UPDATED_PFA_COL_EPI)
            .pfaColADP(UPDATED_PFA_COL_ADP)
            .tp(UPDATED_TP)
            .tpMPlusT(UPDATED_TP_M_PLUS_T)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMPlusT(UPDATED_TCA_M_PLUS_T)
            .tcaTemoin(UPDATED_TCA_TEMOIN)
            .facteurXI(UPDATED_FACTEUR_XI)
            .autresFacteursDosageDescription(UPDATED_AUTRES_FACTEURS_DOSAGE_DESCRIPTION)
            .autresFacteursTaux1(UPDATED_AUTRES_FACTEURS_TAUX_1)
            .autresFacteursTaux2(UPDATED_AUTRES_FACTEURS_TAUX_2)
            .autresFacteursTaux3(UPDATED_AUTRES_FACTEURS_TAUX_3)
            .confirmationMoleculaire(UPDATED_CONFIRMATION_MOLECULAIRE)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationZygote(UPDATED_MUTATION_ZYGOTE)
            .mutation2Nom(UPDATED_MUTATION_2_NOM)
            .serologieVHB(UPDATED_SEROLOGIE_VHB)
            .serologieVHC(UPDATED_SEROLOGIE_VHC)
            .serologieVIH(UPDATED_SEROLOGIE_VIH)
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
            .hemorragiePostTraumatique(UPDATED_HEMORRAGIE_POST_TRAUMATIQUE)
            .frequenceHemorragiePostTraumatique(UPDATED_FREQUENCE_HEMORRAGIE_POST_TRAUMATIQUE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .typeHematomeSousCutane(UPDATED_TYPE_HEMATOME_SOUS_CUTANE)
            .frequenceHematomeSousCutane(UPDATED_FREQUENCE_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .typeHematomeMusculaire(UPDATED_TYPE_HEMATOME_MUSCULAIRE)
            .frequenceHematomeMusculaire(UPDATED_FREQUENCE_HEMATOME_MUSCULAIRE)
            .hematomeTissusMousProfonds(UPDATED_HEMATOME_TISSUS_MOUS_PROFONDS)
            .typeHematomeTissusMousProfonds(UPDATED_TYPE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .frequenceHematomeTissusMousProfonds(UPDATED_FREQUENCE_HEMATOME_TISSUS_MOUS_PROFONDS)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .typeHemarthrose(UPDATED_TYPE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .typeSaignementSNC(UPDATED_TYPE_SAIGNEMENT_SNC)
            .frequenceSaignementSNC(UPDATED_FREQUENCE_SAIGNEMENT_SNC)
            .saignementChuteCordon(UPDATED_SAIGNEMENT_CHUTE_CORDON)
            .frequenceSaignementChuteCordon(UPDATED_FREQUENCE_SAIGNEMENT_CHUTE_CORDON)
            .retardCicatrisationPlaies(UPDATED_RETARD_CICATRISATION_PLAIES)
            .frequenceRetardCicatrisationPlaies(UPDATED_FREQUENCE_RETARD_CICATRISATION_PLAIES)
            .autresManifestations(UPDATED_AUTRES_MANIFESTATIONS)
            .faussesCouchesSpontaneesRecurrence(UPDATED_FAUSSES_COUCHES_SPONTANEES_RECURRENCE)
            .frequenceFaussesCouches(UPDATED_FREQUENCE_FAUSSES_COUCHES)
            .accidentsThrombotiques(UPDATED_ACCIDENTS_THROMBOTIQUES)
            .thromboseVeineuse(UPDATED_THROMBOSE_VEINEUSE)
            .thromboseArterielle(UPDATED_THROMBOSE_ARTERIELLE)
            .frequenceAccidentsThrombotiques(UPDATED_FREQUENCE_ACCIDENTS_THROMBOTIQUES)
            .hemoperitoine(UPDATED_HEMOPERITOINE)
            .frequenceHemoperitoine(UPDATED_FREQUENCE_HEMOPERITOINE)
            .ruptureKystesHemorragiques(UPDATED_RUPTURE_KYSTES_HEMORRAGIQUES)
            .frequenceRuptureKystes(UPDATED_FREQUENCE_RUPTURE_KYSTES)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .frequenceHemorragieGrave(UPDATED_FREQUENCE_HEMORRAGIE_GRAVE)
            .graviteDeglobulisation(UPDATED_GRAVITE_DEGLOBULISATION)
            .graviteEtatDeChoc(UPDATED_GRAVITE_ETAT_DE_CHOC)
            .graviteParLocalisation(UPDATED_GRAVITE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .chirurgieSuiteHemorragique(UPDATED_CHIRURGIE_SUITE_HEMORRAGIQUE)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .circoncisionSuiteHemorragique(UPDATED_CIRCONCISION_SUITE_HEMORRAGIQUE)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .traitementPFC(UPDATED_TRAITEMENT_PFC)
            .traitementCryoprecipite(UPDATED_TRAITEMENT_CRYOPRECIPITE)
            .traitementConcentreFacteurXI(UPDATED_TRAITEMENT_CONCENTRE_FACTEUR_XI)
            .traitementCulotsPlaquettaires(UPDATED_TRAITEMENT_CULOTS_PLAQUETTAIRES)
            .traitementDicynone(UPDATED_TRAITEMENT_DICYNONE)
            .traitementExacyl(UPDATED_TRAITEMENT_EXACYL)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .traitementOestroprogestatif(UPDATED_TRAITEMENT_OESTROPROGESTATIF)
            .transfusionCGR(UPDATED_TRANSFUSION_CGR)
            .alloImmunisation(UPDATED_ALLO_IMMUNISATION)
            .inhibiteurNeutralisant(UPDATED_INHIBITEUR_NEUTRALISANT)
            .titreInhibiteurUB(UPDATED_TITRE_INHIBITEUR_UB)
            .inhibiteurNonNeutralisant(UPDATED_INHIBITEUR_NON_NEUTRALISANT)
            .infectionVIH(UPDATED_INFECTION_VIH)
            .infectionVHC(UPDATED_INFECTION_VHC)
            .infectionVHB(UPDATED_INFECTION_VHB)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .autresComplications(UPDATED_AUTRES_COMPLICATIONS)
            .adherenceTherapeutique(UPDATED_ADHERENCE_THERAPEUTIQUE)
            .causeNonAdherence(UPDATED_CAUSE_NON_ADHERENCE)
            .statutSocial(UPDATED_STATUT_SOCIAL)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES)
            .patientDecede(UPDATED_PATIENT_DECEDE)
            .dateDeces(UPDATED_DATE_DECES)
            .ageDeces(UPDATED_AGE_DECES)
            .causeDeces(UPDATED_CAUSE_DECES);

        restFicheFacteurXIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheFacteurXI.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheFacteurXI))
            )
            .andExpect(status().isOk());

        // Validate the FicheFacteurXI in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheFacteurXIUpdatableFieldsEquals(partialUpdatedFicheFacteurXI, getPersistedFicheFacteurXI(partialUpdatedFicheFacteurXI));
    }

    @Test
    @Transactional
    void patchNonExistingFicheFacteurXI() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXI.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXI
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheFacteurXIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ficheFacteurXIDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheFacteurXIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXI in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFicheFacteurXI() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXI.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXI
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurXIMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheFacteurXIDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheFacteurXI in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFicheFacteurXI() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheFacteurXI.setId(longCount.incrementAndGet());

        // Create the FicheFacteurXI
        FicheFacteurXIDTO ficheFacteurXIDTO = ficheFacteurXIMapper.toDto(ficheFacteurXI);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheFacteurXIMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(ficheFacteurXIDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheFacteurXI in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFicheFacteurXI() throws Exception {
        // Initialize the database
        insertedFicheFacteurXI = ficheFacteurXIRepository.saveAndFlush(ficheFacteurXI);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the ficheFacteurXI
        restFicheFacteurXIMockMvc
            .perform(delete(ENTITY_API_URL_ID, ficheFacteurXI.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return ficheFacteurXIRepository.count();
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

    protected FicheFacteurXI getPersistedFicheFacteurXI(FicheFacteurXI ficheFacteurXI) {
        return ficheFacteurXIRepository.findById(ficheFacteurXI.getId()).orElseThrow();
    }

    protected void assertPersistedFicheFacteurXIToMatchAllProperties(FicheFacteurXI expectedFicheFacteurXI) {
        assertFicheFacteurXIAllPropertiesEquals(expectedFicheFacteurXI, getPersistedFicheFacteurXI(expectedFicheFacteurXI));
    }

    protected void assertPersistedFicheFacteurXIToMatchUpdatableProperties(FicheFacteurXI expectedFicheFacteurXI) {
        assertFicheFacteurXIAllUpdatablePropertiesEquals(expectedFicheFacteurXI, getPersistedFicheFacteurXI(expectedFicheFacteurXI));
    }
}

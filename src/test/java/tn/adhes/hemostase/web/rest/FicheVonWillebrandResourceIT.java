package tn.adhes.hemostase.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.adhes.hemostase.domain.FicheVonWillebrandAsserts.*;
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
import tn.adhes.hemostase.domain.FicheVonWillebrand;
import tn.adhes.hemostase.domain.enumeration.CauseDeces;
import tn.adhes.hemostase.domain.enumeration.CirconstanceDecouverteMvW;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.GroupeSanguin;
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
import tn.adhes.hemostase.domain.enumeration.ResultatSerologie;
import tn.adhes.hemostase.domain.enumeration.ResultatSerologie;
import tn.adhes.hemostase.domain.enumeration.ResultatSerologie;
import tn.adhes.hemostase.domain.enumeration.ResultatSerologie;
import tn.adhes.hemostase.domain.enumeration.ResultatSerologie;
import tn.adhes.hemostase.domain.enumeration.ServiceCliniqueType;
import tn.adhes.hemostase.domain.enumeration.SousTypeMvW;
import tn.adhes.hemostase.domain.enumeration.StatutSocial;
import tn.adhes.hemostase.domain.enumeration.TypeFvWppRatio;
import tn.adhes.hemostase.domain.enumeration.TypeHemorragieVisceres;
import tn.adhes.hemostase.domain.enumeration.TypeMultimere;
import tn.adhes.hemostase.domain.enumeration.TypeMvW;
import tn.adhes.hemostase.domain.enumeration.TypeOcclusion;
import tn.adhes.hemostase.domain.enumeration.TypeResultatPatho;
import tn.adhes.hemostase.domain.enumeration.TypeRipaResultat;
import tn.adhes.hemostase.domain.enumeration.TypeTS;
import tn.adhes.hemostase.domain.enumeration.TypeZygote;
import tn.adhes.hemostase.repository.FicheVonWillebrandRepository;
import tn.adhes.hemostase.service.dto.FicheVonWillebrandDTO;
import tn.adhes.hemostase.service.mapper.FicheVonWillebrandMapper;

/**
 * Integration tests for the {@link FicheVonWillebrandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheVonWillebrandResourceIT {

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

    private static final LocalDate DEFAULT_DATE_ENREGISTREMENT_REGISTRE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ENREGISTREMENT_REGISTRE = LocalDate.now(ZoneId.systemDefault());

    private static final OuiNonNP DEFAULT_CONSENTEMENT_REGISTRE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CONSENTEMENT_REGISTRE = OuiNonNP.NON;

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

    private static final CirconstanceDecouverteMvW DEFAULT_CIRCONSTANCES_DECOUVERTE = CirconstanceDecouverteMvW.SYNDROME_HEMORRAGIQUE;
    private static final CirconstanceDecouverteMvW UPDATED_CIRCONSTANCES_DECOUVERTE = CirconstanceDecouverteMvW.ENQUETE_FAMILIALE;

    private static final String DEFAULT_CIRCONSTANCES_DECOUVERTE_PRECISION = "AAAAAAAAAA";
    private static final String UPDATED_CIRCONSTANCES_DECOUVERTE_PRECISION = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_JAMAIS_SAIGNE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_JAMAIS_SAIGNE = OuiNonNP.NON;

    private static final Integer DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_JOURS = 1;
    private static final Integer UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_JOURS = 2;

    private static final Integer DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_MOIS = 1;
    private static final Integer UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_MOIS = 2;

    private static final Integer DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES = 1;
    private static final Integer UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES = 2;

    private static final OuiNonNP DEFAULT_SYMPTOME_ECCHYMOSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_ECCHYMOSE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYMPTOME_EPISTAXIS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_EPISTAXIS = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYMPTOME_GINGIVORRAGIES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_GINGIVORRAGIES = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYMPTOME_METRORRAGIES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_METRORRAGIES = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYMPTOME_MELENA = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_MELENA = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYMPTOME_HEMOPTYSIE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_HEMOPTYSIE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYMPTOME_HEMORRAGIE_CHUTE_CORDON = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_HEMORRAGIE_CHUTE_CORDON = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_SYMPTOME_AUTRE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SYMPTOME_AUTRE = OuiNonNP.NON;

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

    private static final TypeMvW DEFAULT_TYPE_MV_W = TypeMvW.TYPE1;
    private static final TypeMvW UPDATED_TYPE_MV_W = TypeMvW.TYPE2;

    private static final SousTypeMvW DEFAULT_SOUS_TYPE_MV_W = SousTypeMvW.TYPE2A;
    private static final SousTypeMvW UPDATED_SOUS_TYPE_MV_W = SousTypeMvW.TYPE2B;

    private static final String DEFAULT_PATHOLOGIES_ASSOCIEES = "AAAAAAAAAA";
    private static final String UPDATED_PATHOLOGIES_ASSOCIEES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_TEST_CONFIRMATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TEST_CONFIRMATION = LocalDate.now(ZoneId.systemDefault());

    private static final GroupeSanguin DEFAULT_GS = GroupeSanguin.O;
    private static final GroupeSanguin UPDATED_GS = GroupeSanguin.NON_O;

    private static final Double DEFAULT_HB = 1D;
    private static final Double UPDATED_HB = 2D;

    private static final Double DEFAULT_HTE = 1D;
    private static final Double UPDATED_HTE = 2D;

    private static final Integer DEFAULT_PLAQUETTES = 1;
    private static final Integer UPDATED_PLAQUETTES = 2;

    private static final Double DEFAULT_TP = 1D;
    private static final Double UPDATED_TP = 2D;

    private static final Double DEFAULT_TCA_MT = 1D;
    private static final Double UPDATED_TCA_MT = 2D;

    private static final Double DEFAULT_TCA_M_PLUS_T = 1D;
    private static final Double UPDATED_TCA_M_PLUS_T = 2D;

    private static final Double DEFAULT_TCA_M_PLUS_T_2_H = 1D;
    private static final Double UPDATED_TCA_M_PLUS_T_2_H = 2D;

    private static final Double DEFAULT_FIBRINOGENE = 1D;
    private static final Double UPDATED_FIBRINOGENE = 2D;

    private static final TypeTS DEFAULT_TS_RESULTAT = TypeTS.NORMAL;
    private static final TypeTS UPDATED_TS_RESULTAT = TypeTS.ALLONGE;

    private static final Double DEFAULT_TS_VALEUR = 1D;
    private static final Double UPDATED_TS_VALEUR = 2D;

    private static final TypeOcclusion DEFAULT_OCCLUSION_PLAQUETTAIRE = TypeOcclusion.NORMAL;
    private static final TypeOcclusion UPDATED_OCCLUSION_PLAQUETTAIRE = TypeOcclusion.ALLONGE;

    private static final Double DEFAULT_COLLAGENE_EPINEPHRINE = 1D;
    private static final Double UPDATED_COLLAGENE_EPINEPHRINE = 2D;

    private static final Double DEFAULT_COLLAGENE_ADP = 1D;
    private static final Double UPDATED_COLLAGENE_ADP = 2D;

    private static final Double DEFAULT_FVWAG = 1D;
    private static final Double UPDATED_FVWAG = 2D;

    private static final Double DEFAULT_FVWACT = 1D;
    private static final Double UPDATED_FVWACT = 2D;

    private static final Double DEFAULT_F_8_C = 1D;
    private static final Double UPDATED_F_8_C = 2D;

    private static final Double DEFAULT_RATIO_FVWACT_FVWAG = 1D;
    private static final Double UPDATED_RATIO_FVWACT_FVWAG = 2D;

    private static final Double DEFAULT_RATIO_F_8_C_FVWAG = 1D;
    private static final Double UPDATED_RATIO_F_8_C_FVWAG = 2D;

    private static final FaiteNonFaite DEFAULT_AUTRES_TESTS_SPECIFIQUES = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_AUTRES_TESTS_SPECIFIQUES = FaiteNonFaite.NONFAITE;

    private static final FaiteNonFaite DEFAULT_VWF_CB = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_VWF_CB = FaiteNonFaite.NONFAITE;

    private static final Double DEFAULT_VWF_CB_RESULTAT = 1D;
    private static final Double UPDATED_VWF_CB_RESULTAT = 2D;

    private static final FaiteNonFaite DEFAULT_RIPA = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_RIPA = FaiteNonFaite.NONFAITE;

    private static final TypeRipaResultat DEFAULT_RIPA_RESULTAT = TypeRipaResultat.DIMINUE;
    private static final TypeRipaResultat UPDATED_RIPA_RESULTAT = TypeRipaResultat.PARADOXALE;

    private static final TypeMultimere DEFAULT_MULTIMERE_HPM = TypeMultimere.PRESENTS;
    private static final TypeMultimere UPDATED_MULTIMERE_HPM = TypeMultimere.ABSENTS;

    private static final FaiteNonFaite DEFAULT_LIAISON_FVW_F_8 = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_LIAISON_FVW_F_8 = FaiteNonFaite.NONFAITE;

    private static final TypeResultatPatho DEFAULT_LIAISON_FVW_F_8_RESULTAT = TypeResultatPatho.PATHOLOGIQUE;
    private static final TypeResultatPatho UPDATED_LIAISON_FVW_F_8_RESULTAT = TypeResultatPatho.NORMAL;

    private static final FaiteNonFaite DEFAULT_FVWPP = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_FVWPP = FaiteNonFaite.NONFAITE;

    private static final TypeFvWppRatio DEFAULT_FVWPP_RESULTAT = TypeFvWppRatio.PATHOLOGIQUE;
    private static final TypeFvWppRatio UPDATED_FVWPP_RESULTAT = TypeFvWppRatio.NORMAL;

    private static final FaiteNonFaite DEFAULT_MUTATION_GENETIQUE = FaiteNonFaite.FAITE;
    private static final FaiteNonFaite UPDATED_MUTATION_GENETIQUE = FaiteNonFaite.NONFAITE;

    private static final Boolean DEFAULT_MUTATION_PRESENTE = false;
    private static final Boolean UPDATED_MUTATION_PRESENTE = true;

    private static final String DEFAULT_MUTATION_NOM = "AAAAAAAAAA";
    private static final String UPDATED_MUTATION_NOM = "BBBBBBBBBB";

    private static final TypeZygote DEFAULT_MUTATION_ZYGOTE = TypeZygote.HETEROZYGOTE;
    private static final TypeZygote UPDATED_MUTATION_ZYGOTE = TypeZygote.HOMOZYGOTE;

    private static final OuiNonNP DEFAULT_ECCHYMOSES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ECCHYMOSES = OuiNonNP.NON;

    private static final Boolean DEFAULT_ECCHYMOSES_SPONTANEES = false;
    private static final Boolean UPDATED_ECCHYMOSES_SPONTANEES = true;

    private static final Boolean DEFAULT_ECCHYMOSES_PROVOQUEES = false;
    private static final Boolean UPDATED_ECCHYMOSES_PROVOQUEES = true;

    private static final String DEFAULT_ECCHYMOSES_SIEGE = "AAAAAAAAAA";
    private static final String UPDATED_ECCHYMOSES_SIEGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ECCHYMOSES_FREQUENCE = 1;
    private static final Integer UPDATED_ECCHYMOSES_FREQUENCE = 2;

    private static final OuiNonNP DEFAULT_HEMORRAGIE_MUQUEUSES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIE_MUQUEUSES = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_EPISTAXIS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_EPISTAXIS = OuiNonNP.NON;

    private static final Integer DEFAULT_EPISTAXIS_FREQUENCE = 1;
    private static final Integer UPDATED_EPISTAXIS_FREQUENCE = 2;

    private static final OuiNonNP DEFAULT_HEMORRAGIES_INTRABUCCALES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIES_INTRABUCCALES = OuiNonNP.NON;

    private static final Integer DEFAULT_HEMORRAGIES_INTRABUCCALES_FREQUENCE = 1;
    private static final Integer UPDATED_HEMORRAGIES_INTRABUCCALES_FREQUENCE = 2;

    private static final OuiNonNP DEFAULT_MENORAGIE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_MENORAGIE = OuiNonNP.NON;

    private static final Integer DEFAULT_MENORAGIE_FREQUENCE = 1;
    private static final Integer UPDATED_MENORAGIE_FREQUENCE = 2;

    private static final Boolean DEFAULT_SCORE_HIGHAM_FAIT = false;
    private static final Boolean UPDATED_SCORE_HIGHAM_FAIT = true;

    private static final Integer DEFAULT_SCORE_HIGHAM_VALEUR = 1;
    private static final Integer UPDATED_SCORE_HIGHAM_VALEUR = 2;

    private static final OuiNonNP DEFAULT_HEMATOME = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMATOME = OuiNonNP.NON;

    private static final Boolean DEFAULT_HEMATOME_SPONTANE = false;
    private static final Boolean UPDATED_HEMATOME_SPONTANE = true;

    private static final Boolean DEFAULT_HEMATOME_PROVOQUE = false;
    private static final Boolean UPDATED_HEMATOME_PROVOQUE = true;

    private static final Boolean DEFAULT_HEMATOME_SOUS_CUTANE = false;
    private static final Boolean UPDATED_HEMATOME_SOUS_CUTANE = true;

    private static final Boolean DEFAULT_HEMATOME_MUSCULAIRE = false;
    private static final Boolean UPDATED_HEMATOME_MUSCULAIRE = true;

    private static final Boolean DEFAULT_HEMATOME_TISSUS_MOUS = false;
    private static final Boolean UPDATED_HEMATOME_TISSUS_MOUS = true;

    private static final Integer DEFAULT_HEMATOME_FREQUENCE = 1;
    private static final Integer UPDATED_HEMATOME_FREQUENCE = 2;

    private static final OuiNonNP DEFAULT_HEMARTHROSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMARTHROSE = OuiNonNP.NON;

    private static final Boolean DEFAULT_HEMARTHROSE_SPONTANE = false;
    private static final Boolean UPDATED_HEMARTHROSE_SPONTANE = true;

    private static final Boolean DEFAULT_HEMARTHROSE_PROVOQUE = false;
    private static final Boolean UPDATED_HEMARTHROSE_PROVOQUE = true;

    private static final String DEFAULT_HEMARTHROSE_SIEGE = "AAAAAAAAAA";
    private static final String UPDATED_HEMARTHROSE_SIEGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_HEMARTHROSE_FREQUENCE = 1;
    private static final Integer UPDATED_HEMARTHROSE_FREQUENCE = 2;

    private static final OuiNonNP DEFAULT_HEMORRAGIE_AMYGDALIENNE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIE_AMYGDALIENNE = OuiNonNP.NON;

    private static final Integer DEFAULT_HEMORRAGIE_AMYGDALIENNE_FREQUENCE = 1;
    private static final Integer UPDATED_HEMORRAGIE_AMYGDALIENNE_FREQUENCE = 2;

    private static final OuiNonNP DEFAULT_AVULSION_DENTAIRE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_AVULSION_DENTAIRE = OuiNonNP.NON;

    private static final Boolean DEFAULT_AVULSION_PREPARE = false;
    private static final Boolean UPDATED_AVULSION_PREPARE = true;

    private static final OuiNonNP DEFAULT_AVULSION_BIEN_DEROULE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_AVULSION_BIEN_DEROULE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAUMATISME = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAUMATISME = OuiNonNP.NON;

    private static final String DEFAULT_TRAUMATISME_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRAUMATISME_TYPE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CHIRURGIE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CHIRURGIE = OuiNonNP.NON;

    private static final String DEFAULT_CHIRURGIE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CHIRURGIE_TYPE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CHIRURGIE_PREPARE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CHIRURGIE_PREPARE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CHIRURGIE_BIEN_DEROULE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CHIRURGIE_BIEN_DEROULE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ACCOUCHEMENT = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ACCOUCHEMENT = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_HEMORRAGIE_POSTPARTUM = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIE_POSTPARTUM = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CIRCONCISION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION = OuiNonNP.NON;

    private static final Integer DEFAULT_AGE_CIRCONCISION = 1;
    private static final Integer UPDATED_AGE_CIRCONCISION = 2;

    private static final OuiNonNP DEFAULT_CIRCONCISION_PREPARE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION_PREPARE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CIRCONCISION_BIEN_DEROULE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION_BIEN_DEROULE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_HEMORRAGIE_VISCERES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIE_VISCERES = OuiNonNP.NON;

    private static final Boolean DEFAULT_HEMORRAGIE_VISCERES_SPONTANE = false;
    private static final Boolean UPDATED_HEMORRAGIE_VISCERES_SPONTANE = true;

    private static final Boolean DEFAULT_HEMORRAGIE_VISCERES_PROVOQUE = false;
    private static final Boolean UPDATED_HEMORRAGIE_VISCERES_PROVOQUE = true;

    private static final TypeHemorragieVisceres DEFAULT_HEMORRAGIE_VISCERES_TYPE = TypeHemorragieVisceres.HDH;
    private static final TypeHemorragieVisceres UPDATED_HEMORRAGIE_VISCERES_TYPE = TypeHemorragieVisceres.HDB;

    private static final String DEFAULT_AUTRE_HEMORRAGIE_VISCERES = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_HEMORRAGIE_VISCERES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXPLORATION_VISCERES = false;
    private static final Boolean UPDATED_EXPLORATION_VISCERES = true;

    private static final String DEFAULT_LESION_SOUS_JACENTE = "AAAAAAAAAA";
    private static final String UPDATED_LESION_SOUS_JACENTE = "BBBBBBBBBB";

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

    private static final Integer DEFAULT_NOMBRE_HOSPITALISATIONS = 1;
    private static final Integer UPDATED_NOMBRE_HOSPITALISATIONS = 2;

    private static final Integer DEFAULT_NOMBRE_TRANSFUSIONS = 1;
    private static final Integer UPDATED_NOMBRE_TRANSFUSIONS = 2;

    private static final Integer DEFAULT_SCORE_ISTHBAT = 1;
    private static final Integer UPDATED_SCORE_ISTHBAT = 2;

    private static final OuiNonNP DEFAULT_DESMOPRESSINE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_DESMOPRESSINE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_SUBSTITUTIF_PREVENTION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_SUBSTITUTIF_PREVENTION = OuiNonNP.NON;

    private static final String DEFAULT_TRAITEMENT_PREPARATION = "AAAAAAAAAA";
    private static final String UPDATED_TRAITEMENT_PREPARATION = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_TRAITEMENT_SUBSTITUTIF_CURATIF = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_SUBSTITUTIF_CURATIF = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CRYOPRECIPITE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CRYOPRECIPITE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_PFC = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PFC = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CONCENTRE_FVIIIFVW = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CONCENTRE_FVIIIFVW = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CONCENTRE_FVW = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CONCENTRE_FVW = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CONCENTRE_FVIII = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CONCENTRE_FVIII = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_AGENT_BYPASSANT = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_AGENT_BYPASSANT = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ANTIFIBRINOLYTIQUES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ANTIFIBRINOLYTIQUES = OuiNonNP.NON;

    private static final Boolean DEFAULT_VOIE_ORALE = false;
    private static final Boolean UPDATED_VOIE_ORALE = true;

    private static final Boolean DEFAULT_VOIE_IV = false;
    private static final Boolean UPDATED_VOIE_IV = true;

    private static final Boolean DEFAULT_VOIE_LOCALE = false;
    private static final Boolean UPDATED_VOIE_LOCALE = true;

    private static final OuiNonNP DEFAULT_OESTROPROGESTATIFS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_OESTROPROGESTATIFS = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_ANTALGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_ANTALGIQUE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CORTICOTHERAPIE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CORTICOTHERAPIE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_KINESITHERAPIE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_KINESITHERAPIE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_TRAITEMENT_MARTIAL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_MARTIAL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CULOTS_GLOBULAIRES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CULOTS_GLOBULAIRES = OuiNonNP.NON;

    private static final Integer DEFAULT_NOMBRE_CULOTS_GLOBULAIRES = 1;
    private static final Integer UPDATED_NOMBRE_CULOTS_GLOBULAIRES = 2;

    private static final OuiNonNP DEFAULT_IMMUNOSUPPRESSEUR = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_IMMUNOSUPPRESSEUR = OuiNonNP.NON;

    private static final String DEFAULT_NOM_IMMUNOSUPPRESSEUR = "AAAAAAAAAA";
    private static final String UPDATED_NOM_IMMUNOSUPPRESSEUR = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_ANEMIE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ANEMIE = OuiNonNP.NON;

    private static final Boolean DEFAULT_ANEMIE_SEVERE = false;
    private static final Boolean UPDATED_ANEMIE_SEVERE = true;

    private static final Double DEFAULT_TAUX_HB = 1D;
    private static final Double UPDATED_TAUX_HB = 2D;

    private static final ResultatSerologie DEFAULT_ANTICORPS_ANTI_FVW = ResultatSerologie.POSITIF;
    private static final ResultatSerologie UPDATED_ANTICORPS_ANTI_FVW = ResultatSerologie.NEGATIF;

    private static final ResultatSerologie DEFAULT_ANTICORPS_ANTI_FVIII = ResultatSerologie.POSITIF;
    private static final ResultatSerologie UPDATED_ANTICORPS_ANTI_FVIII = ResultatSerologie.NEGATIF;

    private static final ResultatSerologie DEFAULT_HEPATITE_B = ResultatSerologie.POSITIF;
    private static final ResultatSerologie UPDATED_HEPATITE_B = ResultatSerologie.NEGATIF;

    private static final ResultatSerologie DEFAULT_HEPATITE_C = ResultatSerologie.POSITIF;
    private static final ResultatSerologie UPDATED_HEPATITE_C = ResultatSerologie.NEGATIF;

    private static final ResultatSerologie DEFAULT_HIV = ResultatSerologie.POSITIF;
    private static final ResultatSerologie UPDATED_HIV = ResultatSerologie.NEGATIF;

    private static final Boolean DEFAULT_THROMBOSE = false;
    private static final Boolean UPDATED_THROMBOSE = true;

    private static final Boolean DEFAULT_REACTION_ALLERGIQUE = false;
    private static final Boolean UPDATED_REACTION_ALLERGIQUE = true;

    private static final Boolean DEFAULT_AUTRE_COMPLICATION = false;
    private static final Boolean UPDATED_AUTRE_COMPLICATION = true;

    private static final String DEFAULT_AUTRE_COMPLICATION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_COMPLICATION_DESCRIPTION = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/fiche-von-willebrands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FicheVonWillebrandRepository ficheVonWillebrandRepository;

    @Autowired
    private FicheVonWillebrandMapper ficheVonWillebrandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheVonWillebrandMockMvc;

    private FicheVonWillebrand ficheVonWillebrand;

    private FicheVonWillebrand insertedFicheVonWillebrand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheVonWillebrand createEntity() {
        return new FicheVonWillebrand()
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
            .dateEnregistrementRegistre(DEFAULT_DATE_ENREGISTREMENT_REGISTRE)
            .consentementRegistre(DEFAULT_CONSENTEMENT_REGISTRE)
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
            .circonstancesDecouverte(DEFAULT_CIRCONSTANCES_DECOUVERTE)
            .circonstancesDecouvertePrecision(DEFAULT_CIRCONSTANCES_DECOUVERTE_PRECISION)
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
            .typeMvW(DEFAULT_TYPE_MV_W)
            .sousTypeMvW(DEFAULT_SOUS_TYPE_MV_W)
            .pathologiesAssociees(DEFAULT_PATHOLOGIES_ASSOCIEES)
            .dateTestConfirmation(DEFAULT_DATE_TEST_CONFIRMATION)
            .gs(DEFAULT_GS)
            .hb(DEFAULT_HB)
            .hte(DEFAULT_HTE)
            .plaquettes(DEFAULT_PLAQUETTES)
            .tp(DEFAULT_TP)
            .tcaMT(DEFAULT_TCA_MT)
            .tcaMPlusT(DEFAULT_TCA_M_PLUS_T)
            .tcaMPlusT2H(DEFAULT_TCA_M_PLUS_T_2_H)
            .fibrinogene(DEFAULT_FIBRINOGENE)
            .tsResultat(DEFAULT_TS_RESULTAT)
            .tsValeur(DEFAULT_TS_VALEUR)
            .occlusionPlaquettaire(DEFAULT_OCCLUSION_PLAQUETTAIRE)
            .collageneEpinephrine(DEFAULT_COLLAGENE_EPINEPHRINE)
            .collageneADP(DEFAULT_COLLAGENE_ADP)
            .fvwag(DEFAULT_FVWAG)
            .fvwact(DEFAULT_FVWACT)
            .f8c(DEFAULT_F_8_C)
            .ratioFvwactFvwag(DEFAULT_RATIO_FVWACT_FVWAG)
            .ratioF8cFvwag(DEFAULT_RATIO_F_8_C_FVWAG)
            .autresTestsSpecifiques(DEFAULT_AUTRES_TESTS_SPECIFIQUES)
            .vwfCB(DEFAULT_VWF_CB)
            .vwfCBResultat(DEFAULT_VWF_CB_RESULTAT)
            .ripa(DEFAULT_RIPA)
            .ripaResultat(DEFAULT_RIPA_RESULTAT)
            .multimereHPM(DEFAULT_MULTIMERE_HPM)
            .liaisonFvwF8(DEFAULT_LIAISON_FVW_F_8)
            .liaisonFvwF8Resultat(DEFAULT_LIAISON_FVW_F_8_RESULTAT)
            .fvwpp(DEFAULT_FVWPP)
            .fvwppResultat(DEFAULT_FVWPP_RESULTAT)
            .mutationGenetique(DEFAULT_MUTATION_GENETIQUE)
            .mutationPresente(DEFAULT_MUTATION_PRESENTE)
            .mutationNom(DEFAULT_MUTATION_NOM)
            .mutationZygote(DEFAULT_MUTATION_ZYGOTE)
            .ecchymoses(DEFAULT_ECCHYMOSES)
            .ecchymosesSpontanees(DEFAULT_ECCHYMOSES_SPONTANEES)
            .ecchymosesProvoquees(DEFAULT_ECCHYMOSES_PROVOQUEES)
            .ecchymosesSiege(DEFAULT_ECCHYMOSES_SIEGE)
            .ecchymosesFrequence(DEFAULT_ECCHYMOSES_FREQUENCE)
            .hemorragieMuqueuses(DEFAULT_HEMORRAGIE_MUQUEUSES)
            .epistaxis(DEFAULT_EPISTAXIS)
            .epistaxisFrequence(DEFAULT_EPISTAXIS_FREQUENCE)
            .hemorragiesIntrabuccales(DEFAULT_HEMORRAGIES_INTRABUCCALES)
            .hemorragiesIntrabuccalesFrequence(DEFAULT_HEMORRAGIES_INTRABUCCALES_FREQUENCE)
            .menoragie(DEFAULT_MENORAGIE)
            .menoragieFrequence(DEFAULT_MENORAGIE_FREQUENCE)
            .scoreHighamFait(DEFAULT_SCORE_HIGHAM_FAIT)
            .scoreHighamValeur(DEFAULT_SCORE_HIGHAM_VALEUR)
            .hematome(DEFAULT_HEMATOME)
            .hematomeSpontane(DEFAULT_HEMATOME_SPONTANE)
            .hematomeProvoque(DEFAULT_HEMATOME_PROVOQUE)
            .hematomeSousCutane(DEFAULT_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(DEFAULT_HEMATOME_MUSCULAIRE)
            .hematomeTissusMous(DEFAULT_HEMATOME_TISSUS_MOUS)
            .hematomeFrequence(DEFAULT_HEMATOME_FREQUENCE)
            .hemarthrose(DEFAULT_HEMARTHROSE)
            .hemarthroseSpontane(DEFAULT_HEMARTHROSE_SPONTANE)
            .hemarthroseProvoque(DEFAULT_HEMARTHROSE_PROVOQUE)
            .hemarthroseSiege(DEFAULT_HEMARTHROSE_SIEGE)
            .hemarthroseFrequence(DEFAULT_HEMARTHROSE_FREQUENCE)
            .hemorragieAmygdalienne(DEFAULT_HEMORRAGIE_AMYGDALIENNE)
            .hemorragieAmygdalienneFrequence(DEFAULT_HEMORRAGIE_AMYGDALIENNE_FREQUENCE)
            .avulsionDentaire(DEFAULT_AVULSION_DENTAIRE)
            .avulsionPrepare(DEFAULT_AVULSION_PREPARE)
            .avulsionBienDeroule(DEFAULT_AVULSION_BIEN_DEROULE)
            .traumatisme(DEFAULT_TRAUMATISME)
            .traumatismeType(DEFAULT_TRAUMATISME_TYPE)
            .chirurgie(DEFAULT_CHIRURGIE)
            .chirurgieType(DEFAULT_CHIRURGIE_TYPE)
            .chirurgiePrepare(DEFAULT_CHIRURGIE_PREPARE)
            .chirurgieBienDeroule(DEFAULT_CHIRURGIE_BIEN_DEROULE)
            .accouchement(DEFAULT_ACCOUCHEMENT)
            .hemorragiePostpartum(DEFAULT_HEMORRAGIE_POSTPARTUM)
            .circoncision(DEFAULT_CIRCONCISION)
            .ageCirconcision(DEFAULT_AGE_CIRCONCISION)
            .circoncisionPrepare(DEFAULT_CIRCONCISION_PREPARE)
            .circoncisionBienDeroule(DEFAULT_CIRCONCISION_BIEN_DEROULE)
            .hemorragieVisceres(DEFAULT_HEMORRAGIE_VISCERES)
            .hemorragieVisceresSpontane(DEFAULT_HEMORRAGIE_VISCERES_SPONTANE)
            .hemorragieVisceresProvoque(DEFAULT_HEMORRAGIE_VISCERES_PROVOQUE)
            .hemorragieVisceresType(DEFAULT_HEMORRAGIE_VISCERES_TYPE)
            .autreHemorragieVisceres(DEFAULT_AUTRE_HEMORRAGIE_VISCERES)
            .explorationVisceres(DEFAULT_EXPLORATION_VISCERES)
            .lesionSousJacente(DEFAULT_LESION_SOUS_JACENTE)
            .hemorragieGrave(DEFAULT_HEMORRAGIE_GRAVE)
            .graveDeglobulisation(DEFAULT_GRAVE_DEGLOBULISATION)
            .graveEtatDeChoc(DEFAULT_GRAVE_ETAT_DE_CHOC)
            .graveParLocalisation(DEFAULT_GRAVE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(DEFAULT_SIEGE_GRAVITE_LOCALISATION)
            .nombreHospitalisations(DEFAULT_NOMBRE_HOSPITALISATIONS)
            .nombreTransfusions(DEFAULT_NOMBRE_TRANSFUSIONS)
            .scoreISTHBAT(DEFAULT_SCORE_ISTHBAT)
            .desmopressine(DEFAULT_DESMOPRESSINE)
            .traitementSubstitutifPrevention(DEFAULT_TRAITEMENT_SUBSTITUTIF_PREVENTION)
            .traitementPreparation(DEFAULT_TRAITEMENT_PREPARATION)
            .traitementSubstitutifCuratif(DEFAULT_TRAITEMENT_SUBSTITUTIF_CURATIF)
            .cryoprecipite(DEFAULT_CRYOPRECIPITE)
            .pfc(DEFAULT_PFC)
            .concentreFVIIIFVW(DEFAULT_CONCENTRE_FVIIIFVW)
            .concentreFVW(DEFAULT_CONCENTRE_FVW)
            .concentreFVIII(DEFAULT_CONCENTRE_FVIII)
            .agentBypassant(DEFAULT_AGENT_BYPASSANT)
            .antifibrinolytiques(DEFAULT_ANTIFIBRINOLYTIQUES)
            .voieOrale(DEFAULT_VOIE_ORALE)
            .voieIV(DEFAULT_VOIE_IV)
            .voieLocale(DEFAULT_VOIE_LOCALE)
            .oestroprogestatifs(DEFAULT_OESTROPROGESTATIFS)
            .traitementAntalgique(DEFAULT_TRAITEMENT_ANTALGIQUE)
            .corticotherapie(DEFAULT_CORTICOTHERAPIE)
            .kinesitherapie(DEFAULT_KINESITHERAPIE)
            .traitementMartial(DEFAULT_TRAITEMENT_MARTIAL)
            .culotsGlobulaires(DEFAULT_CULOTS_GLOBULAIRES)
            .nombreCulotsGlobulaires(DEFAULT_NOMBRE_CULOTS_GLOBULAIRES)
            .immunosuppresseur(DEFAULT_IMMUNOSUPPRESSEUR)
            .nomImmunosuppresseur(DEFAULT_NOM_IMMUNOSUPPRESSEUR)
            .anemie(DEFAULT_ANEMIE)
            .anemieSevere(DEFAULT_ANEMIE_SEVERE)
            .tauxHb(DEFAULT_TAUX_HB)
            .anticorpsAntiFVW(DEFAULT_ANTICORPS_ANTI_FVW)
            .anticorpsAntiFVIII(DEFAULT_ANTICORPS_ANTI_FVIII)
            .hepatiteB(DEFAULT_HEPATITE_B)
            .hepatiteC(DEFAULT_HEPATITE_C)
            .hiv(DEFAULT_HIV)
            .thrombose(DEFAULT_THROMBOSE)
            .reactionAllergique(DEFAULT_REACTION_ALLERGIQUE)
            .autreComplication(DEFAULT_AUTRE_COMPLICATION)
            .autreComplicationDescription(DEFAULT_AUTRE_COMPLICATION_DESCRIPTION)
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
    public static FicheVonWillebrand createUpdatedEntity() {
        return new FicheVonWillebrand()
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
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
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
            .circonstancesDecouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .circonstancesDecouvertePrecision(UPDATED_CIRCONSTANCES_DECOUVERTE_PRECISION)
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
            .typeMvW(UPDATED_TYPE_MV_W)
            .sousTypeMvW(UPDATED_SOUS_TYPE_MV_W)
            .pathologiesAssociees(UPDATED_PATHOLOGIES_ASSOCIEES)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .gs(UPDATED_GS)
            .hb(UPDATED_HB)
            .hte(UPDATED_HTE)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMPlusT(UPDATED_TCA_M_PLUS_T)
            .tcaMPlusT2H(UPDATED_TCA_M_PLUS_T_2_H)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tsResultat(UPDATED_TS_RESULTAT)
            .tsValeur(UPDATED_TS_VALEUR)
            .occlusionPlaquettaire(UPDATED_OCCLUSION_PLAQUETTAIRE)
            .collageneEpinephrine(UPDATED_COLLAGENE_EPINEPHRINE)
            .collageneADP(UPDATED_COLLAGENE_ADP)
            .fvwag(UPDATED_FVWAG)
            .fvwact(UPDATED_FVWACT)
            .f8c(UPDATED_F_8_C)
            .ratioFvwactFvwag(UPDATED_RATIO_FVWACT_FVWAG)
            .ratioF8cFvwag(UPDATED_RATIO_F_8_C_FVWAG)
            .autresTestsSpecifiques(UPDATED_AUTRES_TESTS_SPECIFIQUES)
            .vwfCB(UPDATED_VWF_CB)
            .vwfCBResultat(UPDATED_VWF_CB_RESULTAT)
            .ripa(UPDATED_RIPA)
            .ripaResultat(UPDATED_RIPA_RESULTAT)
            .multimereHPM(UPDATED_MULTIMERE_HPM)
            .liaisonFvwF8(UPDATED_LIAISON_FVW_F_8)
            .liaisonFvwF8Resultat(UPDATED_LIAISON_FVW_F_8_RESULTAT)
            .fvwpp(UPDATED_FVWPP)
            .fvwppResultat(UPDATED_FVWPP_RESULTAT)
            .mutationGenetique(UPDATED_MUTATION_GENETIQUE)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationZygote(UPDATED_MUTATION_ZYGOTE)
            .ecchymoses(UPDATED_ECCHYMOSES)
            .ecchymosesSpontanees(UPDATED_ECCHYMOSES_SPONTANEES)
            .ecchymosesProvoquees(UPDATED_ECCHYMOSES_PROVOQUEES)
            .ecchymosesSiege(UPDATED_ECCHYMOSES_SIEGE)
            .ecchymosesFrequence(UPDATED_ECCHYMOSES_FREQUENCE)
            .hemorragieMuqueuses(UPDATED_HEMORRAGIE_MUQUEUSES)
            .epistaxis(UPDATED_EPISTAXIS)
            .epistaxisFrequence(UPDATED_EPISTAXIS_FREQUENCE)
            .hemorragiesIntrabuccales(UPDATED_HEMORRAGIES_INTRABUCCALES)
            .hemorragiesIntrabuccalesFrequence(UPDATED_HEMORRAGIES_INTRABUCCALES_FREQUENCE)
            .menoragie(UPDATED_MENORAGIE)
            .menoragieFrequence(UPDATED_MENORAGIE_FREQUENCE)
            .scoreHighamFait(UPDATED_SCORE_HIGHAM_FAIT)
            .scoreHighamValeur(UPDATED_SCORE_HIGHAM_VALEUR)
            .hematome(UPDATED_HEMATOME)
            .hematomeSpontane(UPDATED_HEMATOME_SPONTANE)
            .hematomeProvoque(UPDATED_HEMATOME_PROVOQUE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .hematomeTissusMous(UPDATED_HEMATOME_TISSUS_MOUS)
            .hematomeFrequence(UPDATED_HEMATOME_FREQUENCE)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .hemarthroseSpontane(UPDATED_HEMARTHROSE_SPONTANE)
            .hemarthroseProvoque(UPDATED_HEMARTHROSE_PROVOQUE)
            .hemarthroseSiege(UPDATED_HEMARTHROSE_SIEGE)
            .hemarthroseFrequence(UPDATED_HEMARTHROSE_FREQUENCE)
            .hemorragieAmygdalienne(UPDATED_HEMORRAGIE_AMYGDALIENNE)
            .hemorragieAmygdalienneFrequence(UPDATED_HEMORRAGIE_AMYGDALIENNE_FREQUENCE)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .avulsionPrepare(UPDATED_AVULSION_PREPARE)
            .avulsionBienDeroule(UPDATED_AVULSION_BIEN_DEROULE)
            .traumatisme(UPDATED_TRAUMATISME)
            .traumatismeType(UPDATED_TRAUMATISME_TYPE)
            .chirurgie(UPDATED_CHIRURGIE)
            .chirurgieType(UPDATED_CHIRURGIE_TYPE)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .chirurgieBienDeroule(UPDATED_CHIRURGIE_BIEN_DEROULE)
            .accouchement(UPDATED_ACCOUCHEMENT)
            .hemorragiePostpartum(UPDATED_HEMORRAGIE_POSTPARTUM)
            .circoncision(UPDATED_CIRCONCISION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .circoncisionBienDeroule(UPDATED_CIRCONCISION_BIEN_DEROULE)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hemorragieVisceresSpontane(UPDATED_HEMORRAGIE_VISCERES_SPONTANE)
            .hemorragieVisceresProvoque(UPDATED_HEMORRAGIE_VISCERES_PROVOQUE)
            .hemorragieVisceresType(UPDATED_HEMORRAGIE_VISCERES_TYPE)
            .autreHemorragieVisceres(UPDATED_AUTRE_HEMORRAGIE_VISCERES)
            .explorationVisceres(UPDATED_EXPLORATION_VISCERES)
            .lesionSousJacente(UPDATED_LESION_SOUS_JACENTE)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .graveDeglobulisation(UPDATED_GRAVE_DEGLOBULISATION)
            .graveEtatDeChoc(UPDATED_GRAVE_ETAT_DE_CHOC)
            .graveParLocalisation(UPDATED_GRAVE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .nombreHospitalisations(UPDATED_NOMBRE_HOSPITALISATIONS)
            .nombreTransfusions(UPDATED_NOMBRE_TRANSFUSIONS)
            .scoreISTHBAT(UPDATED_SCORE_ISTHBAT)
            .desmopressine(UPDATED_DESMOPRESSINE)
            .traitementSubstitutifPrevention(UPDATED_TRAITEMENT_SUBSTITUTIF_PREVENTION)
            .traitementPreparation(UPDATED_TRAITEMENT_PREPARATION)
            .traitementSubstitutifCuratif(UPDATED_TRAITEMENT_SUBSTITUTIF_CURATIF)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .pfc(UPDATED_PFC)
            .concentreFVIIIFVW(UPDATED_CONCENTRE_FVIIIFVW)
            .concentreFVW(UPDATED_CONCENTRE_FVW)
            .concentreFVIII(UPDATED_CONCENTRE_FVIII)
            .agentBypassant(UPDATED_AGENT_BYPASSANT)
            .antifibrinolytiques(UPDATED_ANTIFIBRINOLYTIQUES)
            .voieOrale(UPDATED_VOIE_ORALE)
            .voieIV(UPDATED_VOIE_IV)
            .voieLocale(UPDATED_VOIE_LOCALE)
            .oestroprogestatifs(UPDATED_OESTROPROGESTATIFS)
            .traitementAntalgique(UPDATED_TRAITEMENT_ANTALGIQUE)
            .corticotherapie(UPDATED_CORTICOTHERAPIE)
            .kinesitherapie(UPDATED_KINESITHERAPIE)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .culotsGlobulaires(UPDATED_CULOTS_GLOBULAIRES)
            .nombreCulotsGlobulaires(UPDATED_NOMBRE_CULOTS_GLOBULAIRES)
            .immunosuppresseur(UPDATED_IMMUNOSUPPRESSEUR)
            .nomImmunosuppresseur(UPDATED_NOM_IMMUNOSUPPRESSEUR)
            .anemie(UPDATED_ANEMIE)
            .anemieSevere(UPDATED_ANEMIE_SEVERE)
            .tauxHb(UPDATED_TAUX_HB)
            .anticorpsAntiFVW(UPDATED_ANTICORPS_ANTI_FVW)
            .anticorpsAntiFVIII(UPDATED_ANTICORPS_ANTI_FVIII)
            .hepatiteB(UPDATED_HEPATITE_B)
            .hepatiteC(UPDATED_HEPATITE_C)
            .hiv(UPDATED_HIV)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .autreComplication(UPDATED_AUTRE_COMPLICATION)
            .autreComplicationDescription(UPDATED_AUTRE_COMPLICATION_DESCRIPTION)
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
        ficheVonWillebrand = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedFicheVonWillebrand != null) {
            ficheVonWillebrandRepository.delete(insertedFicheVonWillebrand);
            insertedFicheVonWillebrand = null;
        }
    }

    @Test
    @Transactional
    void createFicheVonWillebrand() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FicheVonWillebrand
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);
        var returnedFicheVonWillebrandDTO = om.readValue(
            restFicheVonWillebrandMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FicheVonWillebrandDTO.class
        );

        // Validate the FicheVonWillebrand in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedFicheVonWillebrand = ficheVonWillebrandMapper.toEntity(returnedFicheVonWillebrandDTO);
        assertFicheVonWillebrandUpdatableFieldsEquals(
            returnedFicheVonWillebrand,
            getPersistedFicheVonWillebrand(returnedFicheVonWillebrand)
        );

        insertedFicheVonWillebrand = returnedFicheVonWillebrand;
    }

    @Test
    @Transactional
    void createFicheVonWillebrandWithExistingId() throws Exception {
        // Create the FicheVonWillebrand with an existing ID
        ficheVonWillebrand.setId(1L);
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheVonWillebrandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FicheVonWillebrand in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkEtatCivilIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheVonWillebrand.setEtatCivil(null);

        // Create the FicheVonWillebrand, which fails.
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        restFicheVonWillebrandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkServiceCliniqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheVonWillebrand.setServiceClinique(null);

        // Create the FicheVonWillebrand, which fails.
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        restFicheVonWillebrandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCouvertureSocialeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheVonWillebrand.setCouvertureSociale(null);

        // Create the FicheVonWillebrand, which fails.
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        restFicheVonWillebrandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSexeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheVonWillebrand.setSexe(null);

        // Create the FicheVonWillebrand, which fails.
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        restFicheVonWillebrandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkDiagnosticIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheVonWillebrand.setDiagnostic(null);

        // Create the FicheVonWillebrand, which fails.
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        restFicheVonWillebrandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeMvWIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheVonWillebrand.setTypeMvW(null);

        // Create the FicheVonWillebrand, which fails.
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        restFicheVonWillebrandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllFicheVonWillebrands() throws Exception {
        // Initialize the database
        insertedFicheVonWillebrand = ficheVonWillebrandRepository.saveAndFlush(ficheVonWillebrand);

        // Get all the ficheVonWillebrandList
        restFicheVonWillebrandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ficheVonWillebrand.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].dateEnregistrementRegistre").value(hasItem(DEFAULT_DATE_ENREGISTREMENT_REGISTRE.toString())))
            .andExpect(jsonPath("$.[*].consentementRegistre").value(hasItem(DEFAULT_CONSENTEMENT_REGISTRE.toString())))
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
            .andExpect(jsonPath("$.[*].circonstancesDecouverte").value(hasItem(DEFAULT_CIRCONSTANCES_DECOUVERTE.toString())))
            .andExpect(jsonPath("$.[*].circonstancesDecouvertePrecision").value(hasItem(DEFAULT_CIRCONSTANCES_DECOUVERTE_PRECISION)))
            .andExpect(jsonPath("$.[*].jamaisSaigne").value(hasItem(DEFAULT_JAMAIS_SAIGNE.toString())))
            .andExpect(jsonPath("$.[*].agePremierSigneCliniqueJours").value(hasItem(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_JOURS)))
            .andExpect(jsonPath("$.[*].agePremierSigneCliniqueMois").value(hasItem(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_MOIS)))
            .andExpect(jsonPath("$.[*].agePremierSigneCliniqueAnnees").value(hasItem(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES)))
            .andExpect(jsonPath("$.[*].symptomeEcchymose").value(hasItem(DEFAULT_SYMPTOME_ECCHYMOSE.toString())))
            .andExpect(jsonPath("$.[*].symptomeEpistaxis").value(hasItem(DEFAULT_SYMPTOME_EPISTAXIS.toString())))
            .andExpect(jsonPath("$.[*].symptomeGingivorragies").value(hasItem(DEFAULT_SYMPTOME_GINGIVORRAGIES.toString())))
            .andExpect(jsonPath("$.[*].symptomeMetrorragies").value(hasItem(DEFAULT_SYMPTOME_METRORRAGIES.toString())))
            .andExpect(jsonPath("$.[*].symptomeMelena").value(hasItem(DEFAULT_SYMPTOME_MELENA.toString())))
            .andExpect(jsonPath("$.[*].symptomeHemoptysie").value(hasItem(DEFAULT_SYMPTOME_HEMOPTYSIE.toString())))
            .andExpect(
                jsonPath("$.[*].symptomeHemorragiePostAmygdalectomie").value(
                    hasItem(DEFAULT_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE.toString())
                )
            )
            .andExpect(
                jsonPath("$.[*].symptomeHemorragiePostAvulsionDentaire").value(
                    hasItem(DEFAULT_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE.toString())
                )
            )
            .andExpect(jsonPath("$.[*].symptomeHemorragieChuteCordon").value(hasItem(DEFAULT_SYMPTOME_HEMORRAGIE_CHUTE_CORDON.toString())))
            .andExpect(jsonPath("$.[*].symptomeAutre").value(hasItem(DEFAULT_SYMPTOME_AUTRE.toString())))
            .andExpect(jsonPath("$.[*].symptomeAutreDescription").value(hasItem(DEFAULT_SYMPTOME_AUTRE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].datePremiereConsultation").value(hasItem(DEFAULT_DATE_PREMIERE_CONSULTATION.toString())))
            .andExpect(jsonPath("$.[*].agePremiereConsultationJours").value(hasItem(DEFAULT_AGE_PREMIERE_CONSULTATION_JOURS)))
            .andExpect(jsonPath("$.[*].agePremiereConsultationMois").value(hasItem(DEFAULT_AGE_PREMIERE_CONSULTATION_MOIS)))
            .andExpect(jsonPath("$.[*].agePremiereConsultationAnnees").value(hasItem(DEFAULT_AGE_PREMIERE_CONSULTATION_ANNEES)))
            .andExpect(jsonPath("$.[*].ageDiagnosticJours").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_JOURS)))
            .andExpect(jsonPath("$.[*].ageDiagnosticMois").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_MOIS)))
            .andExpect(jsonPath("$.[*].ageDiagnosticAnnees").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_ANNEES)))
            .andExpect(jsonPath("$.[*].typeMvW").value(hasItem(DEFAULT_TYPE_MV_W.toString())))
            .andExpect(jsonPath("$.[*].sousTypeMvW").value(hasItem(DEFAULT_SOUS_TYPE_MV_W.toString())))
            .andExpect(jsonPath("$.[*].pathologiesAssociees").value(hasItem(DEFAULT_PATHOLOGIES_ASSOCIEES)))
            .andExpect(jsonPath("$.[*].dateTestConfirmation").value(hasItem(DEFAULT_DATE_TEST_CONFIRMATION.toString())))
            .andExpect(jsonPath("$.[*].gs").value(hasItem(DEFAULT_GS.toString())))
            .andExpect(jsonPath("$.[*].hb").value(hasItem(DEFAULT_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].hte").value(hasItem(DEFAULT_HTE.doubleValue())))
            .andExpect(jsonPath("$.[*].plaquettes").value(hasItem(DEFAULT_PLAQUETTES)))
            .andExpect(jsonPath("$.[*].tp").value(hasItem(DEFAULT_TP.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMT").value(hasItem(DEFAULT_TCA_MT.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMPlusT").value(hasItem(DEFAULT_TCA_M_PLUS_T.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMPlusT2H").value(hasItem(DEFAULT_TCA_M_PLUS_T_2_H.doubleValue())))
            .andExpect(jsonPath("$.[*].fibrinogene").value(hasItem(DEFAULT_FIBRINOGENE.doubleValue())))
            .andExpect(jsonPath("$.[*].tsResultat").value(hasItem(DEFAULT_TS_RESULTAT.toString())))
            .andExpect(jsonPath("$.[*].tsValeur").value(hasItem(DEFAULT_TS_VALEUR.doubleValue())))
            .andExpect(jsonPath("$.[*].occlusionPlaquettaire").value(hasItem(DEFAULT_OCCLUSION_PLAQUETTAIRE.toString())))
            .andExpect(jsonPath("$.[*].collageneEpinephrine").value(hasItem(DEFAULT_COLLAGENE_EPINEPHRINE.doubleValue())))
            .andExpect(jsonPath("$.[*].collageneADP").value(hasItem(DEFAULT_COLLAGENE_ADP.doubleValue())))
            .andExpect(jsonPath("$.[*].fvwag").value(hasItem(DEFAULT_FVWAG.doubleValue())))
            .andExpect(jsonPath("$.[*].fvwact").value(hasItem(DEFAULT_FVWACT.doubleValue())))
            .andExpect(jsonPath("$.[*].f8c").value(hasItem(DEFAULT_F_8_C.doubleValue())))
            .andExpect(jsonPath("$.[*].ratioFvwactFvwag").value(hasItem(DEFAULT_RATIO_FVWACT_FVWAG.doubleValue())))
            .andExpect(jsonPath("$.[*].ratioF8cFvwag").value(hasItem(DEFAULT_RATIO_F_8_C_FVWAG.doubleValue())))
            .andExpect(jsonPath("$.[*].autresTestsSpecifiques").value(hasItem(DEFAULT_AUTRES_TESTS_SPECIFIQUES.toString())))
            .andExpect(jsonPath("$.[*].vwfCB").value(hasItem(DEFAULT_VWF_CB.toString())))
            .andExpect(jsonPath("$.[*].vwfCBResultat").value(hasItem(DEFAULT_VWF_CB_RESULTAT.doubleValue())))
            .andExpect(jsonPath("$.[*].ripa").value(hasItem(DEFAULT_RIPA.toString())))
            .andExpect(jsonPath("$.[*].ripaResultat").value(hasItem(DEFAULT_RIPA_RESULTAT.toString())))
            .andExpect(jsonPath("$.[*].multimereHPM").value(hasItem(DEFAULT_MULTIMERE_HPM.toString())))
            .andExpect(jsonPath("$.[*].liaisonFvwF8").value(hasItem(DEFAULT_LIAISON_FVW_F_8.toString())))
            .andExpect(jsonPath("$.[*].liaisonFvwF8Resultat").value(hasItem(DEFAULT_LIAISON_FVW_F_8_RESULTAT.toString())))
            .andExpect(jsonPath("$.[*].fvwpp").value(hasItem(DEFAULT_FVWPP.toString())))
            .andExpect(jsonPath("$.[*].fvwppResultat").value(hasItem(DEFAULT_FVWPP_RESULTAT.toString())))
            .andExpect(jsonPath("$.[*].mutationGenetique").value(hasItem(DEFAULT_MUTATION_GENETIQUE.toString())))
            .andExpect(jsonPath("$.[*].mutationPresente").value(hasItem(DEFAULT_MUTATION_PRESENTE.booleanValue())))
            .andExpect(jsonPath("$.[*].mutationNom").value(hasItem(DEFAULT_MUTATION_NOM)))
            .andExpect(jsonPath("$.[*].mutationZygote").value(hasItem(DEFAULT_MUTATION_ZYGOTE.toString())))
            .andExpect(jsonPath("$.[*].ecchymoses").value(hasItem(DEFAULT_ECCHYMOSES.toString())))
            .andExpect(jsonPath("$.[*].ecchymosesSpontanees").value(hasItem(DEFAULT_ECCHYMOSES_SPONTANEES.booleanValue())))
            .andExpect(jsonPath("$.[*].ecchymosesProvoquees").value(hasItem(DEFAULT_ECCHYMOSES_PROVOQUEES.booleanValue())))
            .andExpect(jsonPath("$.[*].ecchymosesSiege").value(hasItem(DEFAULT_ECCHYMOSES_SIEGE)))
            .andExpect(jsonPath("$.[*].ecchymosesFrequence").value(hasItem(DEFAULT_ECCHYMOSES_FREQUENCE)))
            .andExpect(jsonPath("$.[*].hemorragieMuqueuses").value(hasItem(DEFAULT_HEMORRAGIE_MUQUEUSES.toString())))
            .andExpect(jsonPath("$.[*].epistaxis").value(hasItem(DEFAULT_EPISTAXIS.toString())))
            .andExpect(jsonPath("$.[*].epistaxisFrequence").value(hasItem(DEFAULT_EPISTAXIS_FREQUENCE)))
            .andExpect(jsonPath("$.[*].hemorragiesIntrabuccales").value(hasItem(DEFAULT_HEMORRAGIES_INTRABUCCALES.toString())))
            .andExpect(jsonPath("$.[*].hemorragiesIntrabuccalesFrequence").value(hasItem(DEFAULT_HEMORRAGIES_INTRABUCCALES_FREQUENCE)))
            .andExpect(jsonPath("$.[*].menoragie").value(hasItem(DEFAULT_MENORAGIE.toString())))
            .andExpect(jsonPath("$.[*].menoragieFrequence").value(hasItem(DEFAULT_MENORAGIE_FREQUENCE)))
            .andExpect(jsonPath("$.[*].scoreHighamFait").value(hasItem(DEFAULT_SCORE_HIGHAM_FAIT.booleanValue())))
            .andExpect(jsonPath("$.[*].scoreHighamValeur").value(hasItem(DEFAULT_SCORE_HIGHAM_VALEUR)))
            .andExpect(jsonPath("$.[*].hematome").value(hasItem(DEFAULT_HEMATOME.toString())))
            .andExpect(jsonPath("$.[*].hematomeSpontane").value(hasItem(DEFAULT_HEMATOME_SPONTANE.booleanValue())))
            .andExpect(jsonPath("$.[*].hematomeProvoque").value(hasItem(DEFAULT_HEMATOME_PROVOQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].hematomeSousCutane").value(hasItem(DEFAULT_HEMATOME_SOUS_CUTANE.booleanValue())))
            .andExpect(jsonPath("$.[*].hematomeMusculaire").value(hasItem(DEFAULT_HEMATOME_MUSCULAIRE.booleanValue())))
            .andExpect(jsonPath("$.[*].hematomeTissusMous").value(hasItem(DEFAULT_HEMATOME_TISSUS_MOUS.booleanValue())))
            .andExpect(jsonPath("$.[*].hematomeFrequence").value(hasItem(DEFAULT_HEMATOME_FREQUENCE)))
            .andExpect(jsonPath("$.[*].hemarthrose").value(hasItem(DEFAULT_HEMARTHROSE.toString())))
            .andExpect(jsonPath("$.[*].hemarthroseSpontane").value(hasItem(DEFAULT_HEMARTHROSE_SPONTANE.booleanValue())))
            .andExpect(jsonPath("$.[*].hemarthroseProvoque").value(hasItem(DEFAULT_HEMARTHROSE_PROVOQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].hemarthroseSiege").value(hasItem(DEFAULT_HEMARTHROSE_SIEGE)))
            .andExpect(jsonPath("$.[*].hemarthroseFrequence").value(hasItem(DEFAULT_HEMARTHROSE_FREQUENCE)))
            .andExpect(jsonPath("$.[*].hemorragieAmygdalienne").value(hasItem(DEFAULT_HEMORRAGIE_AMYGDALIENNE.toString())))
            .andExpect(jsonPath("$.[*].hemorragieAmygdalienneFrequence").value(hasItem(DEFAULT_HEMORRAGIE_AMYGDALIENNE_FREQUENCE)))
            .andExpect(jsonPath("$.[*].avulsionDentaire").value(hasItem(DEFAULT_AVULSION_DENTAIRE.toString())))
            .andExpect(jsonPath("$.[*].avulsionPrepare").value(hasItem(DEFAULT_AVULSION_PREPARE.booleanValue())))
            .andExpect(jsonPath("$.[*].avulsionBienDeroule").value(hasItem(DEFAULT_AVULSION_BIEN_DEROULE.toString())))
            .andExpect(jsonPath("$.[*].traumatisme").value(hasItem(DEFAULT_TRAUMATISME.toString())))
            .andExpect(jsonPath("$.[*].traumatismeType").value(hasItem(DEFAULT_TRAUMATISME_TYPE)))
            .andExpect(jsonPath("$.[*].chirurgie").value(hasItem(DEFAULT_CHIRURGIE.toString())))
            .andExpect(jsonPath("$.[*].chirurgieType").value(hasItem(DEFAULT_CHIRURGIE_TYPE)))
            .andExpect(jsonPath("$.[*].chirurgiePrepare").value(hasItem(DEFAULT_CHIRURGIE_PREPARE.toString())))
            .andExpect(jsonPath("$.[*].chirurgieBienDeroule").value(hasItem(DEFAULT_CHIRURGIE_BIEN_DEROULE.toString())))
            .andExpect(jsonPath("$.[*].accouchement").value(hasItem(DEFAULT_ACCOUCHEMENT.toString())))
            .andExpect(jsonPath("$.[*].hemorragiePostpartum").value(hasItem(DEFAULT_HEMORRAGIE_POSTPARTUM.toString())))
            .andExpect(jsonPath("$.[*].circoncision").value(hasItem(DEFAULT_CIRCONCISION.toString())))
            .andExpect(jsonPath("$.[*].ageCirconcision").value(hasItem(DEFAULT_AGE_CIRCONCISION)))
            .andExpect(jsonPath("$.[*].circoncisionPrepare").value(hasItem(DEFAULT_CIRCONCISION_PREPARE.toString())))
            .andExpect(jsonPath("$.[*].circoncisionBienDeroule").value(hasItem(DEFAULT_CIRCONCISION_BIEN_DEROULE.toString())))
            .andExpect(jsonPath("$.[*].hemorragieVisceres").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES.toString())))
            .andExpect(jsonPath("$.[*].hemorragieVisceresSpontane").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES_SPONTANE.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragieVisceresProvoque").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES_PROVOQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragieVisceresType").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES_TYPE.toString())))
            .andExpect(jsonPath("$.[*].autreHemorragieVisceres").value(hasItem(DEFAULT_AUTRE_HEMORRAGIE_VISCERES)))
            .andExpect(jsonPath("$.[*].explorationVisceres").value(hasItem(DEFAULT_EXPLORATION_VISCERES.booleanValue())))
            .andExpect(jsonPath("$.[*].lesionSousJacente").value(hasItem(DEFAULT_LESION_SOUS_JACENTE)))
            .andExpect(jsonPath("$.[*].hemorragieGrave").value(hasItem(DEFAULT_HEMORRAGIE_GRAVE.toString())))
            .andExpect(jsonPath("$.[*].graveDeglobulisation").value(hasItem(DEFAULT_GRAVE_DEGLOBULISATION.booleanValue())))
            .andExpect(jsonPath("$.[*].graveEtatDeChoc").value(hasItem(DEFAULT_GRAVE_ETAT_DE_CHOC.booleanValue())))
            .andExpect(jsonPath("$.[*].graveParLocalisation").value(hasItem(DEFAULT_GRAVE_PAR_LOCALISATION.booleanValue())))
            .andExpect(jsonPath("$.[*].siegeGraviteLocalisation").value(hasItem(DEFAULT_SIEGE_GRAVITE_LOCALISATION)))
            .andExpect(jsonPath("$.[*].nombreHospitalisations").value(hasItem(DEFAULT_NOMBRE_HOSPITALISATIONS)))
            .andExpect(jsonPath("$.[*].nombreTransfusions").value(hasItem(DEFAULT_NOMBRE_TRANSFUSIONS)))
            .andExpect(jsonPath("$.[*].scoreISTHBAT").value(hasItem(DEFAULT_SCORE_ISTHBAT)))
            .andExpect(jsonPath("$.[*].desmopressine").value(hasItem(DEFAULT_DESMOPRESSINE.toString())))
            .andExpect(
                jsonPath("$.[*].traitementSubstitutifPrevention").value(hasItem(DEFAULT_TRAITEMENT_SUBSTITUTIF_PREVENTION.toString()))
            )
            .andExpect(jsonPath("$.[*].traitementPreparation").value(hasItem(DEFAULT_TRAITEMENT_PREPARATION)))
            .andExpect(jsonPath("$.[*].traitementSubstitutifCuratif").value(hasItem(DEFAULT_TRAITEMENT_SUBSTITUTIF_CURATIF.toString())))
            .andExpect(jsonPath("$.[*].cryoprecipite").value(hasItem(DEFAULT_CRYOPRECIPITE.toString())))
            .andExpect(jsonPath("$.[*].pfc").value(hasItem(DEFAULT_PFC.toString())))
            .andExpect(jsonPath("$.[*].concentreFVIIIFVW").value(hasItem(DEFAULT_CONCENTRE_FVIIIFVW.toString())))
            .andExpect(jsonPath("$.[*].concentreFVW").value(hasItem(DEFAULT_CONCENTRE_FVW.toString())))
            .andExpect(jsonPath("$.[*].concentreFVIII").value(hasItem(DEFAULT_CONCENTRE_FVIII.toString())))
            .andExpect(jsonPath("$.[*].agentBypassant").value(hasItem(DEFAULT_AGENT_BYPASSANT.toString())))
            .andExpect(jsonPath("$.[*].antifibrinolytiques").value(hasItem(DEFAULT_ANTIFIBRINOLYTIQUES.toString())))
            .andExpect(jsonPath("$.[*].voieOrale").value(hasItem(DEFAULT_VOIE_ORALE.booleanValue())))
            .andExpect(jsonPath("$.[*].voieIV").value(hasItem(DEFAULT_VOIE_IV.booleanValue())))
            .andExpect(jsonPath("$.[*].voieLocale").value(hasItem(DEFAULT_VOIE_LOCALE.booleanValue())))
            .andExpect(jsonPath("$.[*].oestroprogestatifs").value(hasItem(DEFAULT_OESTROPROGESTATIFS.toString())))
            .andExpect(jsonPath("$.[*].traitementAntalgique").value(hasItem(DEFAULT_TRAITEMENT_ANTALGIQUE.toString())))
            .andExpect(jsonPath("$.[*].corticotherapie").value(hasItem(DEFAULT_CORTICOTHERAPIE.toString())))
            .andExpect(jsonPath("$.[*].kinesitherapie").value(hasItem(DEFAULT_KINESITHERAPIE.toString())))
            .andExpect(jsonPath("$.[*].traitementMartial").value(hasItem(DEFAULT_TRAITEMENT_MARTIAL.toString())))
            .andExpect(jsonPath("$.[*].culotsGlobulaires").value(hasItem(DEFAULT_CULOTS_GLOBULAIRES.toString())))
            .andExpect(jsonPath("$.[*].nombreCulotsGlobulaires").value(hasItem(DEFAULT_NOMBRE_CULOTS_GLOBULAIRES)))
            .andExpect(jsonPath("$.[*].immunosuppresseur").value(hasItem(DEFAULT_IMMUNOSUPPRESSEUR.toString())))
            .andExpect(jsonPath("$.[*].nomImmunosuppresseur").value(hasItem(DEFAULT_NOM_IMMUNOSUPPRESSEUR)))
            .andExpect(jsonPath("$.[*].anemie").value(hasItem(DEFAULT_ANEMIE.toString())))
            .andExpect(jsonPath("$.[*].anemieSevere").value(hasItem(DEFAULT_ANEMIE_SEVERE.booleanValue())))
            .andExpect(jsonPath("$.[*].tauxHb").value(hasItem(DEFAULT_TAUX_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].anticorpsAntiFVW").value(hasItem(DEFAULT_ANTICORPS_ANTI_FVW.toString())))
            .andExpect(jsonPath("$.[*].anticorpsAntiFVIII").value(hasItem(DEFAULT_ANTICORPS_ANTI_FVIII.toString())))
            .andExpect(jsonPath("$.[*].hepatiteB").value(hasItem(DEFAULT_HEPATITE_B.toString())))
            .andExpect(jsonPath("$.[*].hepatiteC").value(hasItem(DEFAULT_HEPATITE_C.toString())))
            .andExpect(jsonPath("$.[*].hiv").value(hasItem(DEFAULT_HIV.toString())))
            .andExpect(jsonPath("$.[*].thrombose").value(hasItem(DEFAULT_THROMBOSE.booleanValue())))
            .andExpect(jsonPath("$.[*].reactionAllergique").value(hasItem(DEFAULT_REACTION_ALLERGIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].autreComplication").value(hasItem(DEFAULT_AUTRE_COMPLICATION.booleanValue())))
            .andExpect(jsonPath("$.[*].autreComplicationDescription").value(hasItem(DEFAULT_AUTRE_COMPLICATION_DESCRIPTION)))
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
    void getFicheVonWillebrand() throws Exception {
        // Initialize the database
        insertedFicheVonWillebrand = ficheVonWillebrandRepository.saveAndFlush(ficheVonWillebrand);

        // Get the ficheVonWillebrand
        restFicheVonWillebrandMockMvc
            .perform(get(ENTITY_API_URL_ID, ficheVonWillebrand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ficheVonWillebrand.getId().intValue()))
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
            .andExpect(jsonPath("$.dateEnregistrementRegistre").value(DEFAULT_DATE_ENREGISTREMENT_REGISTRE.toString()))
            .andExpect(jsonPath("$.consentementRegistre").value(DEFAULT_CONSENTEMENT_REGISTRE.toString()))
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
            .andExpect(jsonPath("$.circonstancesDecouverte").value(DEFAULT_CIRCONSTANCES_DECOUVERTE.toString()))
            .andExpect(jsonPath("$.circonstancesDecouvertePrecision").value(DEFAULT_CIRCONSTANCES_DECOUVERTE_PRECISION))
            .andExpect(jsonPath("$.jamaisSaigne").value(DEFAULT_JAMAIS_SAIGNE.toString()))
            .andExpect(jsonPath("$.agePremierSigneCliniqueJours").value(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_JOURS))
            .andExpect(jsonPath("$.agePremierSigneCliniqueMois").value(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_MOIS))
            .andExpect(jsonPath("$.agePremierSigneCliniqueAnnees").value(DEFAULT_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES))
            .andExpect(jsonPath("$.symptomeEcchymose").value(DEFAULT_SYMPTOME_ECCHYMOSE.toString()))
            .andExpect(jsonPath("$.symptomeEpistaxis").value(DEFAULT_SYMPTOME_EPISTAXIS.toString()))
            .andExpect(jsonPath("$.symptomeGingivorragies").value(DEFAULT_SYMPTOME_GINGIVORRAGIES.toString()))
            .andExpect(jsonPath("$.symptomeMetrorragies").value(DEFAULT_SYMPTOME_METRORRAGIES.toString()))
            .andExpect(jsonPath("$.symptomeMelena").value(DEFAULT_SYMPTOME_MELENA.toString()))
            .andExpect(jsonPath("$.symptomeHemoptysie").value(DEFAULT_SYMPTOME_HEMOPTYSIE.toString()))
            .andExpect(jsonPath("$.symptomeHemorragiePostAmygdalectomie").value(DEFAULT_SYMPTOME_HEMORRAGIE_POST_AMYGDALECTOMIE.toString()))
            .andExpect(
                jsonPath("$.symptomeHemorragiePostAvulsionDentaire").value(DEFAULT_SYMPTOME_HEMORRAGIE_POST_AVULSION_DENTAIRE.toString())
            )
            .andExpect(jsonPath("$.symptomeHemorragieChuteCordon").value(DEFAULT_SYMPTOME_HEMORRAGIE_CHUTE_CORDON.toString()))
            .andExpect(jsonPath("$.symptomeAutre").value(DEFAULT_SYMPTOME_AUTRE.toString()))
            .andExpect(jsonPath("$.symptomeAutreDescription").value(DEFAULT_SYMPTOME_AUTRE_DESCRIPTION))
            .andExpect(jsonPath("$.datePremiereConsultation").value(DEFAULT_DATE_PREMIERE_CONSULTATION.toString()))
            .andExpect(jsonPath("$.agePremiereConsultationJours").value(DEFAULT_AGE_PREMIERE_CONSULTATION_JOURS))
            .andExpect(jsonPath("$.agePremiereConsultationMois").value(DEFAULT_AGE_PREMIERE_CONSULTATION_MOIS))
            .andExpect(jsonPath("$.agePremiereConsultationAnnees").value(DEFAULT_AGE_PREMIERE_CONSULTATION_ANNEES))
            .andExpect(jsonPath("$.ageDiagnosticJours").value(DEFAULT_AGE_DIAGNOSTIC_JOURS))
            .andExpect(jsonPath("$.ageDiagnosticMois").value(DEFAULT_AGE_DIAGNOSTIC_MOIS))
            .andExpect(jsonPath("$.ageDiagnosticAnnees").value(DEFAULT_AGE_DIAGNOSTIC_ANNEES))
            .andExpect(jsonPath("$.typeMvW").value(DEFAULT_TYPE_MV_W.toString()))
            .andExpect(jsonPath("$.sousTypeMvW").value(DEFAULT_SOUS_TYPE_MV_W.toString()))
            .andExpect(jsonPath("$.pathologiesAssociees").value(DEFAULT_PATHOLOGIES_ASSOCIEES))
            .andExpect(jsonPath("$.dateTestConfirmation").value(DEFAULT_DATE_TEST_CONFIRMATION.toString()))
            .andExpect(jsonPath("$.gs").value(DEFAULT_GS.toString()))
            .andExpect(jsonPath("$.hb").value(DEFAULT_HB.doubleValue()))
            .andExpect(jsonPath("$.hte").value(DEFAULT_HTE.doubleValue()))
            .andExpect(jsonPath("$.plaquettes").value(DEFAULT_PLAQUETTES))
            .andExpect(jsonPath("$.tp").value(DEFAULT_TP.doubleValue()))
            .andExpect(jsonPath("$.tcaMT").value(DEFAULT_TCA_MT.doubleValue()))
            .andExpect(jsonPath("$.tcaMPlusT").value(DEFAULT_TCA_M_PLUS_T.doubleValue()))
            .andExpect(jsonPath("$.tcaMPlusT2H").value(DEFAULT_TCA_M_PLUS_T_2_H.doubleValue()))
            .andExpect(jsonPath("$.fibrinogene").value(DEFAULT_FIBRINOGENE.doubleValue()))
            .andExpect(jsonPath("$.tsResultat").value(DEFAULT_TS_RESULTAT.toString()))
            .andExpect(jsonPath("$.tsValeur").value(DEFAULT_TS_VALEUR.doubleValue()))
            .andExpect(jsonPath("$.occlusionPlaquettaire").value(DEFAULT_OCCLUSION_PLAQUETTAIRE.toString()))
            .andExpect(jsonPath("$.collageneEpinephrine").value(DEFAULT_COLLAGENE_EPINEPHRINE.doubleValue()))
            .andExpect(jsonPath("$.collageneADP").value(DEFAULT_COLLAGENE_ADP.doubleValue()))
            .andExpect(jsonPath("$.fvwag").value(DEFAULT_FVWAG.doubleValue()))
            .andExpect(jsonPath("$.fvwact").value(DEFAULT_FVWACT.doubleValue()))
            .andExpect(jsonPath("$.f8c").value(DEFAULT_F_8_C.doubleValue()))
            .andExpect(jsonPath("$.ratioFvwactFvwag").value(DEFAULT_RATIO_FVWACT_FVWAG.doubleValue()))
            .andExpect(jsonPath("$.ratioF8cFvwag").value(DEFAULT_RATIO_F_8_C_FVWAG.doubleValue()))
            .andExpect(jsonPath("$.autresTestsSpecifiques").value(DEFAULT_AUTRES_TESTS_SPECIFIQUES.toString()))
            .andExpect(jsonPath("$.vwfCB").value(DEFAULT_VWF_CB.toString()))
            .andExpect(jsonPath("$.vwfCBResultat").value(DEFAULT_VWF_CB_RESULTAT.doubleValue()))
            .andExpect(jsonPath("$.ripa").value(DEFAULT_RIPA.toString()))
            .andExpect(jsonPath("$.ripaResultat").value(DEFAULT_RIPA_RESULTAT.toString()))
            .andExpect(jsonPath("$.multimereHPM").value(DEFAULT_MULTIMERE_HPM.toString()))
            .andExpect(jsonPath("$.liaisonFvwF8").value(DEFAULT_LIAISON_FVW_F_8.toString()))
            .andExpect(jsonPath("$.liaisonFvwF8Resultat").value(DEFAULT_LIAISON_FVW_F_8_RESULTAT.toString()))
            .andExpect(jsonPath("$.fvwpp").value(DEFAULT_FVWPP.toString()))
            .andExpect(jsonPath("$.fvwppResultat").value(DEFAULT_FVWPP_RESULTAT.toString()))
            .andExpect(jsonPath("$.mutationGenetique").value(DEFAULT_MUTATION_GENETIQUE.toString()))
            .andExpect(jsonPath("$.mutationPresente").value(DEFAULT_MUTATION_PRESENTE.booleanValue()))
            .andExpect(jsonPath("$.mutationNom").value(DEFAULT_MUTATION_NOM))
            .andExpect(jsonPath("$.mutationZygote").value(DEFAULT_MUTATION_ZYGOTE.toString()))
            .andExpect(jsonPath("$.ecchymoses").value(DEFAULT_ECCHYMOSES.toString()))
            .andExpect(jsonPath("$.ecchymosesSpontanees").value(DEFAULT_ECCHYMOSES_SPONTANEES.booleanValue()))
            .andExpect(jsonPath("$.ecchymosesProvoquees").value(DEFAULT_ECCHYMOSES_PROVOQUEES.booleanValue()))
            .andExpect(jsonPath("$.ecchymosesSiege").value(DEFAULT_ECCHYMOSES_SIEGE))
            .andExpect(jsonPath("$.ecchymosesFrequence").value(DEFAULT_ECCHYMOSES_FREQUENCE))
            .andExpect(jsonPath("$.hemorragieMuqueuses").value(DEFAULT_HEMORRAGIE_MUQUEUSES.toString()))
            .andExpect(jsonPath("$.epistaxis").value(DEFAULT_EPISTAXIS.toString()))
            .andExpect(jsonPath("$.epistaxisFrequence").value(DEFAULT_EPISTAXIS_FREQUENCE))
            .andExpect(jsonPath("$.hemorragiesIntrabuccales").value(DEFAULT_HEMORRAGIES_INTRABUCCALES.toString()))
            .andExpect(jsonPath("$.hemorragiesIntrabuccalesFrequence").value(DEFAULT_HEMORRAGIES_INTRABUCCALES_FREQUENCE))
            .andExpect(jsonPath("$.menoragie").value(DEFAULT_MENORAGIE.toString()))
            .andExpect(jsonPath("$.menoragieFrequence").value(DEFAULT_MENORAGIE_FREQUENCE))
            .andExpect(jsonPath("$.scoreHighamFait").value(DEFAULT_SCORE_HIGHAM_FAIT.booleanValue()))
            .andExpect(jsonPath("$.scoreHighamValeur").value(DEFAULT_SCORE_HIGHAM_VALEUR))
            .andExpect(jsonPath("$.hematome").value(DEFAULT_HEMATOME.toString()))
            .andExpect(jsonPath("$.hematomeSpontane").value(DEFAULT_HEMATOME_SPONTANE.booleanValue()))
            .andExpect(jsonPath("$.hematomeProvoque").value(DEFAULT_HEMATOME_PROVOQUE.booleanValue()))
            .andExpect(jsonPath("$.hematomeSousCutane").value(DEFAULT_HEMATOME_SOUS_CUTANE.booleanValue()))
            .andExpect(jsonPath("$.hematomeMusculaire").value(DEFAULT_HEMATOME_MUSCULAIRE.booleanValue()))
            .andExpect(jsonPath("$.hematomeTissusMous").value(DEFAULT_HEMATOME_TISSUS_MOUS.booleanValue()))
            .andExpect(jsonPath("$.hematomeFrequence").value(DEFAULT_HEMATOME_FREQUENCE))
            .andExpect(jsonPath("$.hemarthrose").value(DEFAULT_HEMARTHROSE.toString()))
            .andExpect(jsonPath("$.hemarthroseSpontane").value(DEFAULT_HEMARTHROSE_SPONTANE.booleanValue()))
            .andExpect(jsonPath("$.hemarthroseProvoque").value(DEFAULT_HEMARTHROSE_PROVOQUE.booleanValue()))
            .andExpect(jsonPath("$.hemarthroseSiege").value(DEFAULT_HEMARTHROSE_SIEGE))
            .andExpect(jsonPath("$.hemarthroseFrequence").value(DEFAULT_HEMARTHROSE_FREQUENCE))
            .andExpect(jsonPath("$.hemorragieAmygdalienne").value(DEFAULT_HEMORRAGIE_AMYGDALIENNE.toString()))
            .andExpect(jsonPath("$.hemorragieAmygdalienneFrequence").value(DEFAULT_HEMORRAGIE_AMYGDALIENNE_FREQUENCE))
            .andExpect(jsonPath("$.avulsionDentaire").value(DEFAULT_AVULSION_DENTAIRE.toString()))
            .andExpect(jsonPath("$.avulsionPrepare").value(DEFAULT_AVULSION_PREPARE.booleanValue()))
            .andExpect(jsonPath("$.avulsionBienDeroule").value(DEFAULT_AVULSION_BIEN_DEROULE.toString()))
            .andExpect(jsonPath("$.traumatisme").value(DEFAULT_TRAUMATISME.toString()))
            .andExpect(jsonPath("$.traumatismeType").value(DEFAULT_TRAUMATISME_TYPE))
            .andExpect(jsonPath("$.chirurgie").value(DEFAULT_CHIRURGIE.toString()))
            .andExpect(jsonPath("$.chirurgieType").value(DEFAULT_CHIRURGIE_TYPE))
            .andExpect(jsonPath("$.chirurgiePrepare").value(DEFAULT_CHIRURGIE_PREPARE.toString()))
            .andExpect(jsonPath("$.chirurgieBienDeroule").value(DEFAULT_CHIRURGIE_BIEN_DEROULE.toString()))
            .andExpect(jsonPath("$.accouchement").value(DEFAULT_ACCOUCHEMENT.toString()))
            .andExpect(jsonPath("$.hemorragiePostpartum").value(DEFAULT_HEMORRAGIE_POSTPARTUM.toString()))
            .andExpect(jsonPath("$.circoncision").value(DEFAULT_CIRCONCISION.toString()))
            .andExpect(jsonPath("$.ageCirconcision").value(DEFAULT_AGE_CIRCONCISION))
            .andExpect(jsonPath("$.circoncisionPrepare").value(DEFAULT_CIRCONCISION_PREPARE.toString()))
            .andExpect(jsonPath("$.circoncisionBienDeroule").value(DEFAULT_CIRCONCISION_BIEN_DEROULE.toString()))
            .andExpect(jsonPath("$.hemorragieVisceres").value(DEFAULT_HEMORRAGIE_VISCERES.toString()))
            .andExpect(jsonPath("$.hemorragieVisceresSpontane").value(DEFAULT_HEMORRAGIE_VISCERES_SPONTANE.booleanValue()))
            .andExpect(jsonPath("$.hemorragieVisceresProvoque").value(DEFAULT_HEMORRAGIE_VISCERES_PROVOQUE.booleanValue()))
            .andExpect(jsonPath("$.hemorragieVisceresType").value(DEFAULT_HEMORRAGIE_VISCERES_TYPE.toString()))
            .andExpect(jsonPath("$.autreHemorragieVisceres").value(DEFAULT_AUTRE_HEMORRAGIE_VISCERES))
            .andExpect(jsonPath("$.explorationVisceres").value(DEFAULT_EXPLORATION_VISCERES.booleanValue()))
            .andExpect(jsonPath("$.lesionSousJacente").value(DEFAULT_LESION_SOUS_JACENTE))
            .andExpect(jsonPath("$.hemorragieGrave").value(DEFAULT_HEMORRAGIE_GRAVE.toString()))
            .andExpect(jsonPath("$.graveDeglobulisation").value(DEFAULT_GRAVE_DEGLOBULISATION.booleanValue()))
            .andExpect(jsonPath("$.graveEtatDeChoc").value(DEFAULT_GRAVE_ETAT_DE_CHOC.booleanValue()))
            .andExpect(jsonPath("$.graveParLocalisation").value(DEFAULT_GRAVE_PAR_LOCALISATION.booleanValue()))
            .andExpect(jsonPath("$.siegeGraviteLocalisation").value(DEFAULT_SIEGE_GRAVITE_LOCALISATION))
            .andExpect(jsonPath("$.nombreHospitalisations").value(DEFAULT_NOMBRE_HOSPITALISATIONS))
            .andExpect(jsonPath("$.nombreTransfusions").value(DEFAULT_NOMBRE_TRANSFUSIONS))
            .andExpect(jsonPath("$.scoreISTHBAT").value(DEFAULT_SCORE_ISTHBAT))
            .andExpect(jsonPath("$.desmopressine").value(DEFAULT_DESMOPRESSINE.toString()))
            .andExpect(jsonPath("$.traitementSubstitutifPrevention").value(DEFAULT_TRAITEMENT_SUBSTITUTIF_PREVENTION.toString()))
            .andExpect(jsonPath("$.traitementPreparation").value(DEFAULT_TRAITEMENT_PREPARATION))
            .andExpect(jsonPath("$.traitementSubstitutifCuratif").value(DEFAULT_TRAITEMENT_SUBSTITUTIF_CURATIF.toString()))
            .andExpect(jsonPath("$.cryoprecipite").value(DEFAULT_CRYOPRECIPITE.toString()))
            .andExpect(jsonPath("$.pfc").value(DEFAULT_PFC.toString()))
            .andExpect(jsonPath("$.concentreFVIIIFVW").value(DEFAULT_CONCENTRE_FVIIIFVW.toString()))
            .andExpect(jsonPath("$.concentreFVW").value(DEFAULT_CONCENTRE_FVW.toString()))
            .andExpect(jsonPath("$.concentreFVIII").value(DEFAULT_CONCENTRE_FVIII.toString()))
            .andExpect(jsonPath("$.agentBypassant").value(DEFAULT_AGENT_BYPASSANT.toString()))
            .andExpect(jsonPath("$.antifibrinolytiques").value(DEFAULT_ANTIFIBRINOLYTIQUES.toString()))
            .andExpect(jsonPath("$.voieOrale").value(DEFAULT_VOIE_ORALE.booleanValue()))
            .andExpect(jsonPath("$.voieIV").value(DEFAULT_VOIE_IV.booleanValue()))
            .andExpect(jsonPath("$.voieLocale").value(DEFAULT_VOIE_LOCALE.booleanValue()))
            .andExpect(jsonPath("$.oestroprogestatifs").value(DEFAULT_OESTROPROGESTATIFS.toString()))
            .andExpect(jsonPath("$.traitementAntalgique").value(DEFAULT_TRAITEMENT_ANTALGIQUE.toString()))
            .andExpect(jsonPath("$.corticotherapie").value(DEFAULT_CORTICOTHERAPIE.toString()))
            .andExpect(jsonPath("$.kinesitherapie").value(DEFAULT_KINESITHERAPIE.toString()))
            .andExpect(jsonPath("$.traitementMartial").value(DEFAULT_TRAITEMENT_MARTIAL.toString()))
            .andExpect(jsonPath("$.culotsGlobulaires").value(DEFAULT_CULOTS_GLOBULAIRES.toString()))
            .andExpect(jsonPath("$.nombreCulotsGlobulaires").value(DEFAULT_NOMBRE_CULOTS_GLOBULAIRES))
            .andExpect(jsonPath("$.immunosuppresseur").value(DEFAULT_IMMUNOSUPPRESSEUR.toString()))
            .andExpect(jsonPath("$.nomImmunosuppresseur").value(DEFAULT_NOM_IMMUNOSUPPRESSEUR))
            .andExpect(jsonPath("$.anemie").value(DEFAULT_ANEMIE.toString()))
            .andExpect(jsonPath("$.anemieSevere").value(DEFAULT_ANEMIE_SEVERE.booleanValue()))
            .andExpect(jsonPath("$.tauxHb").value(DEFAULT_TAUX_HB.doubleValue()))
            .andExpect(jsonPath("$.anticorpsAntiFVW").value(DEFAULT_ANTICORPS_ANTI_FVW.toString()))
            .andExpect(jsonPath("$.anticorpsAntiFVIII").value(DEFAULT_ANTICORPS_ANTI_FVIII.toString()))
            .andExpect(jsonPath("$.hepatiteB").value(DEFAULT_HEPATITE_B.toString()))
            .andExpect(jsonPath("$.hepatiteC").value(DEFAULT_HEPATITE_C.toString()))
            .andExpect(jsonPath("$.hiv").value(DEFAULT_HIV.toString()))
            .andExpect(jsonPath("$.thrombose").value(DEFAULT_THROMBOSE.booleanValue()))
            .andExpect(jsonPath("$.reactionAllergique").value(DEFAULT_REACTION_ALLERGIQUE.booleanValue()))
            .andExpect(jsonPath("$.autreComplication").value(DEFAULT_AUTRE_COMPLICATION.booleanValue()))
            .andExpect(jsonPath("$.autreComplicationDescription").value(DEFAULT_AUTRE_COMPLICATION_DESCRIPTION))
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
    void getNonExistingFicheVonWillebrand() throws Exception {
        // Get the ficheVonWillebrand
        restFicheVonWillebrandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFicheVonWillebrand() throws Exception {
        // Initialize the database
        insertedFicheVonWillebrand = ficheVonWillebrandRepository.saveAndFlush(ficheVonWillebrand);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheVonWillebrand
        FicheVonWillebrand updatedFicheVonWillebrand = ficheVonWillebrandRepository.findById(ficheVonWillebrand.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFicheVonWillebrand are not directly saved in db
        em.detach(updatedFicheVonWillebrand);
        updatedFicheVonWillebrand
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
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
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
            .circonstancesDecouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .circonstancesDecouvertePrecision(UPDATED_CIRCONSTANCES_DECOUVERTE_PRECISION)
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
            .typeMvW(UPDATED_TYPE_MV_W)
            .sousTypeMvW(UPDATED_SOUS_TYPE_MV_W)
            .pathologiesAssociees(UPDATED_PATHOLOGIES_ASSOCIEES)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .gs(UPDATED_GS)
            .hb(UPDATED_HB)
            .hte(UPDATED_HTE)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMPlusT(UPDATED_TCA_M_PLUS_T)
            .tcaMPlusT2H(UPDATED_TCA_M_PLUS_T_2_H)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tsResultat(UPDATED_TS_RESULTAT)
            .tsValeur(UPDATED_TS_VALEUR)
            .occlusionPlaquettaire(UPDATED_OCCLUSION_PLAQUETTAIRE)
            .collageneEpinephrine(UPDATED_COLLAGENE_EPINEPHRINE)
            .collageneADP(UPDATED_COLLAGENE_ADP)
            .fvwag(UPDATED_FVWAG)
            .fvwact(UPDATED_FVWACT)
            .f8c(UPDATED_F_8_C)
            .ratioFvwactFvwag(UPDATED_RATIO_FVWACT_FVWAG)
            .ratioF8cFvwag(UPDATED_RATIO_F_8_C_FVWAG)
            .autresTestsSpecifiques(UPDATED_AUTRES_TESTS_SPECIFIQUES)
            .vwfCB(UPDATED_VWF_CB)
            .vwfCBResultat(UPDATED_VWF_CB_RESULTAT)
            .ripa(UPDATED_RIPA)
            .ripaResultat(UPDATED_RIPA_RESULTAT)
            .multimereHPM(UPDATED_MULTIMERE_HPM)
            .liaisonFvwF8(UPDATED_LIAISON_FVW_F_8)
            .liaisonFvwF8Resultat(UPDATED_LIAISON_FVW_F_8_RESULTAT)
            .fvwpp(UPDATED_FVWPP)
            .fvwppResultat(UPDATED_FVWPP_RESULTAT)
            .mutationGenetique(UPDATED_MUTATION_GENETIQUE)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationZygote(UPDATED_MUTATION_ZYGOTE)
            .ecchymoses(UPDATED_ECCHYMOSES)
            .ecchymosesSpontanees(UPDATED_ECCHYMOSES_SPONTANEES)
            .ecchymosesProvoquees(UPDATED_ECCHYMOSES_PROVOQUEES)
            .ecchymosesSiege(UPDATED_ECCHYMOSES_SIEGE)
            .ecchymosesFrequence(UPDATED_ECCHYMOSES_FREQUENCE)
            .hemorragieMuqueuses(UPDATED_HEMORRAGIE_MUQUEUSES)
            .epistaxis(UPDATED_EPISTAXIS)
            .epistaxisFrequence(UPDATED_EPISTAXIS_FREQUENCE)
            .hemorragiesIntrabuccales(UPDATED_HEMORRAGIES_INTRABUCCALES)
            .hemorragiesIntrabuccalesFrequence(UPDATED_HEMORRAGIES_INTRABUCCALES_FREQUENCE)
            .menoragie(UPDATED_MENORAGIE)
            .menoragieFrequence(UPDATED_MENORAGIE_FREQUENCE)
            .scoreHighamFait(UPDATED_SCORE_HIGHAM_FAIT)
            .scoreHighamValeur(UPDATED_SCORE_HIGHAM_VALEUR)
            .hematome(UPDATED_HEMATOME)
            .hematomeSpontane(UPDATED_HEMATOME_SPONTANE)
            .hematomeProvoque(UPDATED_HEMATOME_PROVOQUE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .hematomeTissusMous(UPDATED_HEMATOME_TISSUS_MOUS)
            .hematomeFrequence(UPDATED_HEMATOME_FREQUENCE)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .hemarthroseSpontane(UPDATED_HEMARTHROSE_SPONTANE)
            .hemarthroseProvoque(UPDATED_HEMARTHROSE_PROVOQUE)
            .hemarthroseSiege(UPDATED_HEMARTHROSE_SIEGE)
            .hemarthroseFrequence(UPDATED_HEMARTHROSE_FREQUENCE)
            .hemorragieAmygdalienne(UPDATED_HEMORRAGIE_AMYGDALIENNE)
            .hemorragieAmygdalienneFrequence(UPDATED_HEMORRAGIE_AMYGDALIENNE_FREQUENCE)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .avulsionPrepare(UPDATED_AVULSION_PREPARE)
            .avulsionBienDeroule(UPDATED_AVULSION_BIEN_DEROULE)
            .traumatisme(UPDATED_TRAUMATISME)
            .traumatismeType(UPDATED_TRAUMATISME_TYPE)
            .chirurgie(UPDATED_CHIRURGIE)
            .chirurgieType(UPDATED_CHIRURGIE_TYPE)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .chirurgieBienDeroule(UPDATED_CHIRURGIE_BIEN_DEROULE)
            .accouchement(UPDATED_ACCOUCHEMENT)
            .hemorragiePostpartum(UPDATED_HEMORRAGIE_POSTPARTUM)
            .circoncision(UPDATED_CIRCONCISION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .circoncisionBienDeroule(UPDATED_CIRCONCISION_BIEN_DEROULE)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hemorragieVisceresSpontane(UPDATED_HEMORRAGIE_VISCERES_SPONTANE)
            .hemorragieVisceresProvoque(UPDATED_HEMORRAGIE_VISCERES_PROVOQUE)
            .hemorragieVisceresType(UPDATED_HEMORRAGIE_VISCERES_TYPE)
            .autreHemorragieVisceres(UPDATED_AUTRE_HEMORRAGIE_VISCERES)
            .explorationVisceres(UPDATED_EXPLORATION_VISCERES)
            .lesionSousJacente(UPDATED_LESION_SOUS_JACENTE)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .graveDeglobulisation(UPDATED_GRAVE_DEGLOBULISATION)
            .graveEtatDeChoc(UPDATED_GRAVE_ETAT_DE_CHOC)
            .graveParLocalisation(UPDATED_GRAVE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .nombreHospitalisations(UPDATED_NOMBRE_HOSPITALISATIONS)
            .nombreTransfusions(UPDATED_NOMBRE_TRANSFUSIONS)
            .scoreISTHBAT(UPDATED_SCORE_ISTHBAT)
            .desmopressine(UPDATED_DESMOPRESSINE)
            .traitementSubstitutifPrevention(UPDATED_TRAITEMENT_SUBSTITUTIF_PREVENTION)
            .traitementPreparation(UPDATED_TRAITEMENT_PREPARATION)
            .traitementSubstitutifCuratif(UPDATED_TRAITEMENT_SUBSTITUTIF_CURATIF)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .pfc(UPDATED_PFC)
            .concentreFVIIIFVW(UPDATED_CONCENTRE_FVIIIFVW)
            .concentreFVW(UPDATED_CONCENTRE_FVW)
            .concentreFVIII(UPDATED_CONCENTRE_FVIII)
            .agentBypassant(UPDATED_AGENT_BYPASSANT)
            .antifibrinolytiques(UPDATED_ANTIFIBRINOLYTIQUES)
            .voieOrale(UPDATED_VOIE_ORALE)
            .voieIV(UPDATED_VOIE_IV)
            .voieLocale(UPDATED_VOIE_LOCALE)
            .oestroprogestatifs(UPDATED_OESTROPROGESTATIFS)
            .traitementAntalgique(UPDATED_TRAITEMENT_ANTALGIQUE)
            .corticotherapie(UPDATED_CORTICOTHERAPIE)
            .kinesitherapie(UPDATED_KINESITHERAPIE)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .culotsGlobulaires(UPDATED_CULOTS_GLOBULAIRES)
            .nombreCulotsGlobulaires(UPDATED_NOMBRE_CULOTS_GLOBULAIRES)
            .immunosuppresseur(UPDATED_IMMUNOSUPPRESSEUR)
            .nomImmunosuppresseur(UPDATED_NOM_IMMUNOSUPPRESSEUR)
            .anemie(UPDATED_ANEMIE)
            .anemieSevere(UPDATED_ANEMIE_SEVERE)
            .tauxHb(UPDATED_TAUX_HB)
            .anticorpsAntiFVW(UPDATED_ANTICORPS_ANTI_FVW)
            .anticorpsAntiFVIII(UPDATED_ANTICORPS_ANTI_FVIII)
            .hepatiteB(UPDATED_HEPATITE_B)
            .hepatiteC(UPDATED_HEPATITE_C)
            .hiv(UPDATED_HIV)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .autreComplication(UPDATED_AUTRE_COMPLICATION)
            .autreComplicationDescription(UPDATED_AUTRE_COMPLICATION_DESCRIPTION)
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
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(updatedFicheVonWillebrand);

        restFicheVonWillebrandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheVonWillebrandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheVonWillebrandDTO))
            )
            .andExpect(status().isOk());

        // Validate the FicheVonWillebrand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFicheVonWillebrandToMatchAllProperties(updatedFicheVonWillebrand);
    }

    @Test
    @Transactional
    void putNonExistingFicheVonWillebrand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheVonWillebrand.setId(longCount.incrementAndGet());

        // Create the FicheVonWillebrand
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheVonWillebrandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ficheVonWillebrandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheVonWillebrandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheVonWillebrand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFicheVonWillebrand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheVonWillebrand.setId(longCount.incrementAndGet());

        // Create the FicheVonWillebrand
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheVonWillebrandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(ficheVonWillebrandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheVonWillebrand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFicheVonWillebrand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheVonWillebrand.setId(longCount.incrementAndGet());

        // Create the FicheVonWillebrand
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheVonWillebrandMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheVonWillebrand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFicheVonWillebrandWithPatch() throws Exception {
        // Initialize the database
        insertedFicheVonWillebrand = ficheVonWillebrandRepository.saveAndFlush(ficheVonWillebrand);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheVonWillebrand using partial update
        FicheVonWillebrand partialUpdatedFicheVonWillebrand = new FicheVonWillebrand();
        partialUpdatedFicheVonWillebrand.setId(ficheVonWillebrand.getId());

        partialUpdatedFicheVonWillebrand
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .profession(UPDATED_PROFESSION)
            .autreServiceClinique(UPDATED_AUTRE_SERVICE_CLINIQUE)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .adresse(UPDATED_ADRESSE)
            .prenomPere(UPDATED_PRENOM_PERE)
            .professionPere(UPDATED_PROFESSION_PERE)
            .professionMere(UPDATED_PROFESSION_MERE)
            .medecinTraitant(UPDATED_MEDECIN_TRAITANT)
            .biologisteResponsableDg(UPDATED_BIOLOGISTE_RESPONSABLE_DG)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .autreDiagnostic(UPDATED_AUTRE_DIAGNOSTIC)
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .casSimilaires(UPDATED_CAS_SIMILAIRES)
            .nombreCas(UPDATED_NOMBRE_CAS)
            .cas1Numero(UPDATED_CAS_1_NUMERO)
            .lienParenteCas1(UPDATED_LIEN_PARENTE_CAS_1)
            .degreParenteCas1(UPDATED_DEGRE_PARENTE_CAS_1)
            .typeHemorragieCas1(UPDATED_TYPE_HEMORRAGIE_CAS_1)
            .cas2Numero(UPDATED_CAS_2_NUMERO)
            .lienParenteCas3(UPDATED_LIEN_PARENTE_CAS_3)
            .deces1Numero(UPDATED_DECES_1_NUMERO)
            .causeDeces1(UPDATED_CAUSE_DECES_1)
            .deces2Numero(UPDATED_DECES_2_NUMERO)
            .causeDeces2(UPDATED_CAUSE_DECES_2)
            .causeDeces3(UPDATED_CAUSE_DECES_3)
            .enqueteFamiliale(UPDATED_ENQUETE_FAMILIALE)
            .circonstancesDecouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .circonstancesDecouvertePrecision(UPDATED_CIRCONSTANCES_DECOUVERTE_PRECISION)
            .agePremierSigneCliniqueMois(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_MOIS)
            .agePremierSigneCliniqueAnnees(UPDATED_AGE_PREMIER_SIGNE_CLINIQUE_ANNEES)
            .symptomeEpistaxis(UPDATED_SYMPTOME_EPISTAXIS)
            .symptomeAutre(UPDATED_SYMPTOME_AUTRE)
            .datePremiereConsultation(UPDATED_DATE_PREMIERE_CONSULTATION)
            .agePremiereConsultationMois(UPDATED_AGE_PREMIERE_CONSULTATION_MOIS)
            .agePremiereConsultationAnnees(UPDATED_AGE_PREMIERE_CONSULTATION_ANNEES)
            .ageDiagnosticAnnees(UPDATED_AGE_DIAGNOSTIC_ANNEES)
            .sousTypeMvW(UPDATED_SOUS_TYPE_MV_W)
            .pathologiesAssociees(UPDATED_PATHOLOGIES_ASSOCIEES)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .tp(UPDATED_TP)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMPlusT(UPDATED_TCA_M_PLUS_T)
            .occlusionPlaquettaire(UPDATED_OCCLUSION_PLAQUETTAIRE)
            .fvwact(UPDATED_FVWACT)
            .f8c(UPDATED_F_8_C)
            .ratioF8cFvwag(UPDATED_RATIO_F_8_C_FVWAG)
            .autresTestsSpecifiques(UPDATED_AUTRES_TESTS_SPECIFIQUES)
            .vwfCBResultat(UPDATED_VWF_CB_RESULTAT)
            .ripa(UPDATED_RIPA)
            .ripaResultat(UPDATED_RIPA_RESULTAT)
            .fvwpp(UPDATED_FVWPP)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationZygote(UPDATED_MUTATION_ZYGOTE)
            .ecchymosesSpontanees(UPDATED_ECCHYMOSES_SPONTANEES)
            .ecchymosesProvoquees(UPDATED_ECCHYMOSES_PROVOQUEES)
            .ecchymosesSiege(UPDATED_ECCHYMOSES_SIEGE)
            .epistaxis(UPDATED_EPISTAXIS)
            .hemorragiesIntrabuccalesFrequence(UPDATED_HEMORRAGIES_INTRABUCCALES_FREQUENCE)
            .menoragie(UPDATED_MENORAGIE)
            .menoragieFrequence(UPDATED_MENORAGIE_FREQUENCE)
            .scoreHighamFait(UPDATED_SCORE_HIGHAM_FAIT)
            .hematomeFrequence(UPDATED_HEMATOME_FREQUENCE)
            .hemarthroseSiege(UPDATED_HEMARTHROSE_SIEGE)
            .hemorragieAmygdalienne(UPDATED_HEMORRAGIE_AMYGDALIENNE)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .traumatismeType(UPDATED_TRAUMATISME_TYPE)
            .chirurgie(UPDATED_CHIRURGIE)
            .chirurgieType(UPDATED_CHIRURGIE_TYPE)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .circoncision(UPDATED_CIRCONCISION)
            .circoncisionBienDeroule(UPDATED_CIRCONCISION_BIEN_DEROULE)
            .hemorragieVisceresSpontane(UPDATED_HEMORRAGIE_VISCERES_SPONTANE)
            .hemorragieVisceresProvoque(UPDATED_HEMORRAGIE_VISCERES_PROVOQUE)
            .hemorragieVisceresType(UPDATED_HEMORRAGIE_VISCERES_TYPE)
            .autreHemorragieVisceres(UPDATED_AUTRE_HEMORRAGIE_VISCERES)
            .explorationVisceres(UPDATED_EXPLORATION_VISCERES)
            .lesionSousJacente(UPDATED_LESION_SOUS_JACENTE)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .nombreHospitalisations(UPDATED_NOMBRE_HOSPITALISATIONS)
            .nombreTransfusions(UPDATED_NOMBRE_TRANSFUSIONS)
            .scoreISTHBAT(UPDATED_SCORE_ISTHBAT)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .pfc(UPDATED_PFC)
            .concentreFVIIIFVW(UPDATED_CONCENTRE_FVIIIFVW)
            .concentreFVW(UPDATED_CONCENTRE_FVW)
            .concentreFVIII(UPDATED_CONCENTRE_FVIII)
            .agentBypassant(UPDATED_AGENT_BYPASSANT)
            .antifibrinolytiques(UPDATED_ANTIFIBRINOLYTIQUES)
            .voieOrale(UPDATED_VOIE_ORALE)
            .voieIV(UPDATED_VOIE_IV)
            .voieLocale(UPDATED_VOIE_LOCALE)
            .oestroprogestatifs(UPDATED_OESTROPROGESTATIFS)
            .traitementAntalgique(UPDATED_TRAITEMENT_ANTALGIQUE)
            .kinesitherapie(UPDATED_KINESITHERAPIE)
            .culotsGlobulaires(UPDATED_CULOTS_GLOBULAIRES)
            .nombreCulotsGlobulaires(UPDATED_NOMBRE_CULOTS_GLOBULAIRES)
            .anemie(UPDATED_ANEMIE)
            .anticorpsAntiFVW(UPDATED_ANTICORPS_ANTI_FVW)
            .anticorpsAntiFVIII(UPDATED_ANTICORPS_ANTI_FVIII)
            .hepatiteB(UPDATED_HEPATITE_B)
            .hepatiteC(UPDATED_HEPATITE_C)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .autreComplication(UPDATED_AUTRE_COMPLICATION)
            .adherenceTherapeutique(UPDATED_ADHERENCE_THERAPEUTIQUE)
            .statutSocial(UPDATED_STATUT_SOCIAL)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .activitePhysique(UPDATED_ACTIVITE_PHYSIQUE)
            .typeActivitePhysique(UPDATED_TYPE_ACTIVITE_PHYSIQUE)
            .sequelles(UPDATED_SEQUELLES)
            .typeSequelles(UPDATED_TYPE_SEQUELLES);

        restFicheVonWillebrandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheVonWillebrand.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheVonWillebrand))
            )
            .andExpect(status().isOk());

        // Validate the FicheVonWillebrand in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheVonWillebrandUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFicheVonWillebrand, ficheVonWillebrand),
            getPersistedFicheVonWillebrand(ficheVonWillebrand)
        );
    }

    @Test
    @Transactional
    void fullUpdateFicheVonWillebrandWithPatch() throws Exception {
        // Initialize the database
        insertedFicheVonWillebrand = ficheVonWillebrandRepository.saveAndFlush(ficheVonWillebrand);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the ficheVonWillebrand using partial update
        FicheVonWillebrand partialUpdatedFicheVonWillebrand = new FicheVonWillebrand();
        partialUpdatedFicheVonWillebrand.setId(ficheVonWillebrand.getId());

        partialUpdatedFicheVonWillebrand
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
            .dateEnregistrementRegistre(UPDATED_DATE_ENREGISTREMENT_REGISTRE)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
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
            .circonstancesDecouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .circonstancesDecouvertePrecision(UPDATED_CIRCONSTANCES_DECOUVERTE_PRECISION)
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
            .typeMvW(UPDATED_TYPE_MV_W)
            .sousTypeMvW(UPDATED_SOUS_TYPE_MV_W)
            .pathologiesAssociees(UPDATED_PATHOLOGIES_ASSOCIEES)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .gs(UPDATED_GS)
            .hb(UPDATED_HB)
            .hte(UPDATED_HTE)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMPlusT(UPDATED_TCA_M_PLUS_T)
            .tcaMPlusT2H(UPDATED_TCA_M_PLUS_T_2_H)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tsResultat(UPDATED_TS_RESULTAT)
            .tsValeur(UPDATED_TS_VALEUR)
            .occlusionPlaquettaire(UPDATED_OCCLUSION_PLAQUETTAIRE)
            .collageneEpinephrine(UPDATED_COLLAGENE_EPINEPHRINE)
            .collageneADP(UPDATED_COLLAGENE_ADP)
            .fvwag(UPDATED_FVWAG)
            .fvwact(UPDATED_FVWACT)
            .f8c(UPDATED_F_8_C)
            .ratioFvwactFvwag(UPDATED_RATIO_FVWACT_FVWAG)
            .ratioF8cFvwag(UPDATED_RATIO_F_8_C_FVWAG)
            .autresTestsSpecifiques(UPDATED_AUTRES_TESTS_SPECIFIQUES)
            .vwfCB(UPDATED_VWF_CB)
            .vwfCBResultat(UPDATED_VWF_CB_RESULTAT)
            .ripa(UPDATED_RIPA)
            .ripaResultat(UPDATED_RIPA_RESULTAT)
            .multimereHPM(UPDATED_MULTIMERE_HPM)
            .liaisonFvwF8(UPDATED_LIAISON_FVW_F_8)
            .liaisonFvwF8Resultat(UPDATED_LIAISON_FVW_F_8_RESULTAT)
            .fvwpp(UPDATED_FVWPP)
            .fvwppResultat(UPDATED_FVWPP_RESULTAT)
            .mutationGenetique(UPDATED_MUTATION_GENETIQUE)
            .mutationPresente(UPDATED_MUTATION_PRESENTE)
            .mutationNom(UPDATED_MUTATION_NOM)
            .mutationZygote(UPDATED_MUTATION_ZYGOTE)
            .ecchymoses(UPDATED_ECCHYMOSES)
            .ecchymosesSpontanees(UPDATED_ECCHYMOSES_SPONTANEES)
            .ecchymosesProvoquees(UPDATED_ECCHYMOSES_PROVOQUEES)
            .ecchymosesSiege(UPDATED_ECCHYMOSES_SIEGE)
            .ecchymosesFrequence(UPDATED_ECCHYMOSES_FREQUENCE)
            .hemorragieMuqueuses(UPDATED_HEMORRAGIE_MUQUEUSES)
            .epistaxis(UPDATED_EPISTAXIS)
            .epistaxisFrequence(UPDATED_EPISTAXIS_FREQUENCE)
            .hemorragiesIntrabuccales(UPDATED_HEMORRAGIES_INTRABUCCALES)
            .hemorragiesIntrabuccalesFrequence(UPDATED_HEMORRAGIES_INTRABUCCALES_FREQUENCE)
            .menoragie(UPDATED_MENORAGIE)
            .menoragieFrequence(UPDATED_MENORAGIE_FREQUENCE)
            .scoreHighamFait(UPDATED_SCORE_HIGHAM_FAIT)
            .scoreHighamValeur(UPDATED_SCORE_HIGHAM_VALEUR)
            .hematome(UPDATED_HEMATOME)
            .hematomeSpontane(UPDATED_HEMATOME_SPONTANE)
            .hematomeProvoque(UPDATED_HEMATOME_PROVOQUE)
            .hematomeSousCutane(UPDATED_HEMATOME_SOUS_CUTANE)
            .hematomeMusculaire(UPDATED_HEMATOME_MUSCULAIRE)
            .hematomeTissusMous(UPDATED_HEMATOME_TISSUS_MOUS)
            .hematomeFrequence(UPDATED_HEMATOME_FREQUENCE)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .hemarthroseSpontane(UPDATED_HEMARTHROSE_SPONTANE)
            .hemarthroseProvoque(UPDATED_HEMARTHROSE_PROVOQUE)
            .hemarthroseSiege(UPDATED_HEMARTHROSE_SIEGE)
            .hemarthroseFrequence(UPDATED_HEMARTHROSE_FREQUENCE)
            .hemorragieAmygdalienne(UPDATED_HEMORRAGIE_AMYGDALIENNE)
            .hemorragieAmygdalienneFrequence(UPDATED_HEMORRAGIE_AMYGDALIENNE_FREQUENCE)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .avulsionPrepare(UPDATED_AVULSION_PREPARE)
            .avulsionBienDeroule(UPDATED_AVULSION_BIEN_DEROULE)
            .traumatisme(UPDATED_TRAUMATISME)
            .traumatismeType(UPDATED_TRAUMATISME_TYPE)
            .chirurgie(UPDATED_CHIRURGIE)
            .chirurgieType(UPDATED_CHIRURGIE_TYPE)
            .chirurgiePrepare(UPDATED_CHIRURGIE_PREPARE)
            .chirurgieBienDeroule(UPDATED_CHIRURGIE_BIEN_DEROULE)
            .accouchement(UPDATED_ACCOUCHEMENT)
            .hemorragiePostpartum(UPDATED_HEMORRAGIE_POSTPARTUM)
            .circoncision(UPDATED_CIRCONCISION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .circoncisionPrepare(UPDATED_CIRCONCISION_PREPARE)
            .circoncisionBienDeroule(UPDATED_CIRCONCISION_BIEN_DEROULE)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hemorragieVisceresSpontane(UPDATED_HEMORRAGIE_VISCERES_SPONTANE)
            .hemorragieVisceresProvoque(UPDATED_HEMORRAGIE_VISCERES_PROVOQUE)
            .hemorragieVisceresType(UPDATED_HEMORRAGIE_VISCERES_TYPE)
            .autreHemorragieVisceres(UPDATED_AUTRE_HEMORRAGIE_VISCERES)
            .explorationVisceres(UPDATED_EXPLORATION_VISCERES)
            .lesionSousJacente(UPDATED_LESION_SOUS_JACENTE)
            .hemorragieGrave(UPDATED_HEMORRAGIE_GRAVE)
            .graveDeglobulisation(UPDATED_GRAVE_DEGLOBULISATION)
            .graveEtatDeChoc(UPDATED_GRAVE_ETAT_DE_CHOC)
            .graveParLocalisation(UPDATED_GRAVE_PAR_LOCALISATION)
            .siegeGraviteLocalisation(UPDATED_SIEGE_GRAVITE_LOCALISATION)
            .nombreHospitalisations(UPDATED_NOMBRE_HOSPITALISATIONS)
            .nombreTransfusions(UPDATED_NOMBRE_TRANSFUSIONS)
            .scoreISTHBAT(UPDATED_SCORE_ISTHBAT)
            .desmopressine(UPDATED_DESMOPRESSINE)
            .traitementSubstitutifPrevention(UPDATED_TRAITEMENT_SUBSTITUTIF_PREVENTION)
            .traitementPreparation(UPDATED_TRAITEMENT_PREPARATION)
            .traitementSubstitutifCuratif(UPDATED_TRAITEMENT_SUBSTITUTIF_CURATIF)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .pfc(UPDATED_PFC)
            .concentreFVIIIFVW(UPDATED_CONCENTRE_FVIIIFVW)
            .concentreFVW(UPDATED_CONCENTRE_FVW)
            .concentreFVIII(UPDATED_CONCENTRE_FVIII)
            .agentBypassant(UPDATED_AGENT_BYPASSANT)
            .antifibrinolytiques(UPDATED_ANTIFIBRINOLYTIQUES)
            .voieOrale(UPDATED_VOIE_ORALE)
            .voieIV(UPDATED_VOIE_IV)
            .voieLocale(UPDATED_VOIE_LOCALE)
            .oestroprogestatifs(UPDATED_OESTROPROGESTATIFS)
            .traitementAntalgique(UPDATED_TRAITEMENT_ANTALGIQUE)
            .corticotherapie(UPDATED_CORTICOTHERAPIE)
            .kinesitherapie(UPDATED_KINESITHERAPIE)
            .traitementMartial(UPDATED_TRAITEMENT_MARTIAL)
            .culotsGlobulaires(UPDATED_CULOTS_GLOBULAIRES)
            .nombreCulotsGlobulaires(UPDATED_NOMBRE_CULOTS_GLOBULAIRES)
            .immunosuppresseur(UPDATED_IMMUNOSUPPRESSEUR)
            .nomImmunosuppresseur(UPDATED_NOM_IMMUNOSUPPRESSEUR)
            .anemie(UPDATED_ANEMIE)
            .anemieSevere(UPDATED_ANEMIE_SEVERE)
            .tauxHb(UPDATED_TAUX_HB)
            .anticorpsAntiFVW(UPDATED_ANTICORPS_ANTI_FVW)
            .anticorpsAntiFVIII(UPDATED_ANTICORPS_ANTI_FVIII)
            .hepatiteB(UPDATED_HEPATITE_B)
            .hepatiteC(UPDATED_HEPATITE_C)
            .hiv(UPDATED_HIV)
            .thrombose(UPDATED_THROMBOSE)
            .reactionAllergique(UPDATED_REACTION_ALLERGIQUE)
            .autreComplication(UPDATED_AUTRE_COMPLICATION)
            .autreComplicationDescription(UPDATED_AUTRE_COMPLICATION_DESCRIPTION)
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

        restFicheVonWillebrandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFicheVonWillebrand.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFicheVonWillebrand))
            )
            .andExpect(status().isOk());

        // Validate the FicheVonWillebrand in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheVonWillebrandUpdatableFieldsEquals(
            partialUpdatedFicheVonWillebrand,
            getPersistedFicheVonWillebrand(partialUpdatedFicheVonWillebrand)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFicheVonWillebrand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheVonWillebrand.setId(longCount.incrementAndGet());

        // Create the FicheVonWillebrand
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheVonWillebrandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ficheVonWillebrandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheVonWillebrandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheVonWillebrand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFicheVonWillebrand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheVonWillebrand.setId(longCount.incrementAndGet());

        // Create the FicheVonWillebrand
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheVonWillebrandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(ficheVonWillebrandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the FicheVonWillebrand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFicheVonWillebrand() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        ficheVonWillebrand.setId(longCount.incrementAndGet());

        // Create the FicheVonWillebrand
        FicheVonWillebrandDTO ficheVonWillebrandDTO = ficheVonWillebrandMapper.toDto(ficheVonWillebrand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheVonWillebrandMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(ficheVonWillebrandDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FicheVonWillebrand in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFicheVonWillebrand() throws Exception {
        // Initialize the database
        insertedFicheVonWillebrand = ficheVonWillebrandRepository.saveAndFlush(ficheVonWillebrand);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the ficheVonWillebrand
        restFicheVonWillebrandMockMvc
            .perform(delete(ENTITY_API_URL_ID, ficheVonWillebrand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return ficheVonWillebrandRepository.count();
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

    protected FicheVonWillebrand getPersistedFicheVonWillebrand(FicheVonWillebrand ficheVonWillebrand) {
        return ficheVonWillebrandRepository.findById(ficheVonWillebrand.getId()).orElseThrow();
    }

    protected void assertPersistedFicheVonWillebrandToMatchAllProperties(FicheVonWillebrand expectedFicheVonWillebrand) {
        assertFicheVonWillebrandAllPropertiesEquals(expectedFicheVonWillebrand, getPersistedFicheVonWillebrand(expectedFicheVonWillebrand));
    }

    protected void assertPersistedFicheVonWillebrandToMatchUpdatableProperties(FicheVonWillebrand expectedFicheVonWillebrand) {
        assertFicheVonWillebrandAllUpdatablePropertiesEquals(
            expectedFicheVonWillebrand,
            getPersistedFicheVonWillebrand(expectedFicheVonWillebrand)
        );
    }
}

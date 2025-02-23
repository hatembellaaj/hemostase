package tn.adhes.hemostase.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.adhes.hemostase.domain.FicheHemophilieAsserts.*;
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
import tn.adhes.hemostase.domain.FicheHemophilie;
import tn.adhes.hemostase.domain.enumeration.CircumstanceDecouverte;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreeParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.EvolutionInhibiteur;
import tn.adhes.hemostase.domain.enumeration.EvolutionSNC;
import tn.adhes.hemostase.domain.enumeration.EvolutionVHC;
import tn.adhes.hemostase.domain.enumeration.FormeClinique;
import tn.adhes.hemostase.domain.enumeration.FormeHemophilie;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.InjectionType;
import tn.adhes.hemostase.domain.enumeration.ModaliteTraitement;
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
import tn.adhes.hemostase.domain.enumeration.PECTherapeutique;
import tn.adhes.hemostase.domain.enumeration.RechercheACC;
import tn.adhes.hemostase.domain.enumeration.Region;
import tn.adhes.hemostase.domain.enumeration.SchemaTherapeutique;
import tn.adhes.hemostase.domain.enumeration.Serologie;
import tn.adhes.hemostase.domain.enumeration.Serologie;
import tn.adhes.hemostase.domain.enumeration.Serologie;
import tn.adhes.hemostase.domain.enumeration.ServiceCliniqueType;
import tn.adhes.hemostase.domain.enumeration.Severite;
import tn.adhes.hemostase.domain.enumeration.SuiteOperatoire;
import tn.adhes.hemostase.domain.enumeration.TauxInhibiteur;
import tn.adhes.hemostase.domain.enumeration.TauxRecuperation;
import tn.adhes.hemostase.domain.enumeration.TestStatus;
import tn.adhes.hemostase.domain.enumeration.TestStatus;
import tn.adhes.hemostase.domain.enumeration.TypeAccidentHemorragiqueGrave;
import tn.adhes.hemostase.domain.enumeration.TypeHemorragie;
import tn.adhes.hemostase.domain.enumeration.TypeHemorragieCutaneoMuqueuse;
import tn.adhes.hemostase.domain.enumeration.TypeHemorragieViscere;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypePriseEnCharge;
import tn.adhes.hemostase.domain.enumeration.VieSociale;
import tn.adhes.hemostase.repository.FicheHemophilieRepository;
import tn.adhes.hemostase.service.dto.FicheHemophilieDTO;
import tn.adhes.hemostase.service.mapper.FicheHemophilieMapper;

/**
 * Integration tests for the {@link FicheHemophilieResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheHemophilieResourceIT {

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

    private static final DegreeParente DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES = DegreeParente.FRERE;
    private static final DegreeParente UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES = DegreeParente.COUSIN;

    private static final OuiNonNP DEFAULT_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.NON;

    private static final Integer DEFAULT_NOMBRE_DECES = 1;
    private static final Integer UPDATED_NOMBRE_DECES = 2;

    private static final FormeHemophilie DEFAULT_FORME_HEMOPHILIE = FormeHemophilie.SPORADIQUE;
    private static final FormeHemophilie UPDATED_FORME_HEMOPHILIE = FormeHemophilie.HEREDITAIRE;

    private static final Integer DEFAULT_FRATRIE_NB_FRERES = 1;
    private static final Integer UPDATED_FRATRIE_NB_FRERES = 2;

    private static final Integer DEFAULT_FRATRIE_NB_SOEURS = 1;
    private static final Integer UPDATED_FRATRIE_NB_SOEURS = 2;

    private static final CircumstanceDecouverte DEFAULT_CIRCONSTANCES_DECOUVERTE = CircumstanceDecouverte.FORTUITE;
    private static final CircumstanceDecouverte UPDATED_CIRCONSTANCES_DECOUVERTE = CircumstanceDecouverte.FAMILIALE;

    private static final Boolean DEFAULT_CUTANEO_MUQUEUX = false;
    private static final Boolean UPDATED_CUTANEO_MUQUEUX = true;

    private static final Boolean DEFAULT_HEMARTHROSE = false;
    private static final Boolean UPDATED_HEMARTHROSE = true;

    private static final Boolean DEFAULT_HEMATOME_POST_TRAUMATIQUE = false;
    private static final Boolean UPDATED_HEMATOME_POST_TRAUMATIQUE = true;

    private static final Boolean DEFAULT_PLAIE_POST_TRAUMATIQUE = false;
    private static final Boolean UPDATED_PLAIE_POST_TRAUMATIQUE = true;

    private static final Boolean DEFAULT_HEMORRAGIE_POST_CIRCONCISION = false;
    private static final Boolean UPDATED_HEMORRAGIE_POST_CIRCONCISION = true;

    private static final Boolean DEFAULT_HEMORRAGIE_POST_CHIRURGIE = false;
    private static final Boolean UPDATED_HEMORRAGIE_POST_CHIRURGIE = true;

    private static final Boolean DEFAULT_HEMORRAGIE_CEREBRALE = false;
    private static final Boolean UPDATED_HEMORRAGIE_CEREBRALE = true;

    private static final Boolean DEFAULT_HEMORRAGIE_DIGESTIVE = false;
    private static final Boolean UPDATED_HEMORRAGIE_DIGESTIVE = true;

    private static final LocalDate DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_PREMIER_SIGNE_CLINIQUE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_JOURS = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_JOURS = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_MOIS = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_MOIS = 2;

    private static final Integer DEFAULT_AGE_DIAGNOSTIC_ANNEES = 1;
    private static final Integer UPDATED_AGE_DIAGNOSTIC_ANNEES = 2;

    private static final LocalDate DEFAULT_DATE_TEST_CONFIRMATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TEST_CONFIRMATION = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_HB = 1F;
    private static final Float UPDATED_HB = 2F;

    private static final Float DEFAULT_HT = 1F;
    private static final Float UPDATED_HT = 2F;

    private static final Integer DEFAULT_PLAQUETTES = 1;
    private static final Integer UPDATED_PLAQUETTES = 2;

    private static final Float DEFAULT_TP = 1F;
    private static final Float UPDATED_TP = 2F;

    private static final Float DEFAULT_FIBRINOGENE = 1F;
    private static final Float UPDATED_FIBRINOGENE = 2F;

    private static final Float DEFAULT_TCA_MT = 1F;
    private static final Float UPDATED_TCA_MT = 2F;

    private static final Float DEFAULT_TCA_MT_TOTAL = 1F;
    private static final Float UPDATED_TCA_MT_TOTAL = 2F;

    private static final Float DEFAULT_TCA_MT_2_H = 1F;
    private static final Float UPDATED_TCA_MT_2_H = 2F;

    private static final Float DEFAULT_TCA_TEMOIN_2_H = 1F;
    private static final Float UPDATED_TCA_TEMOIN_2_H = 2F;

    private static final TestStatus DEFAULT_TS = TestStatus.FAIT;
    private static final TestStatus UPDATED_TS = TestStatus.NON_FAIT;

    private static final TestStatus DEFAULT_PFA = TestStatus.FAIT;
    private static final TestStatus UPDATED_PFA = TestStatus.NON_FAIT;

    private static final Float DEFAULT_DOSAGE_FACTEUR_VIII = 1F;
    private static final Float UPDATED_DOSAGE_FACTEUR_VIII = 2F;

    private static final Float DEFAULT_DOSAGE_FACTEUR_IX = 1F;
    private static final Float UPDATED_DOSAGE_FACTEUR_IX = 2F;

    private static final Float DEFAULT_DOSAGE_FACTEUR_VW_ACTIVITE = 1F;
    private static final Float UPDATED_DOSAGE_FACTEUR_VW_ACTIVITE = 2F;

    private static final Float DEFAULT_DOSAGE_FACTEUR_VW_ANTIGENE = 1F;
    private static final Float UPDATED_DOSAGE_FACTEUR_VW_ANTIGENE = 2F;

    private static final FormeClinique DEFAULT_FORME_CLINIQUE = FormeClinique.A;
    private static final FormeClinique UPDATED_FORME_CLINIQUE = FormeClinique.B;

    private static final Severite DEFAULT_SEVERITE = Severite.SEVERE;
    private static final Severite UPDATED_SEVERITE = Severite.MODEREE;

    private static final OuiNonNP DEFAULT_EXPLORATION_MOLECULAIRE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_EXPLORATION_MOLECULAIRE = OuiNonNP.NON;

    private static final Boolean DEFAULT_INVERSION_INTRON_22 = false;
    private static final Boolean UPDATED_INVERSION_INTRON_22 = true;

    private static final String DEFAULT_AUTRE_EXPLORATION_MOLECULAIRE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_EXPLORATION_MOLECULAIRE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_HEMARTHROSE_MC = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMARTHROSE_MC = OuiNonNP.NON;

    private static final String DEFAULT_HEMARTHROSE_SIEGE = "AAAAAAAAAA";
    private static final String UPDATED_HEMARTHROSE_SIEGE = "BBBBBBBBBB";

    private static final TypeManifestation DEFAULT_HEMARTHROSE_TYPE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_HEMARTHROSE_TYPE = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_HEMARTHROSE_FREQUENCE_AN = 1;
    private static final Integer UPDATED_HEMARTHROSE_FREQUENCE_AN = 2;

    private static final OuiNonNP DEFAULT_HEMATOME_SUPERFICIEL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMATOME_SUPERFICIEL = OuiNonNP.NON;

    private static final TypeManifestation DEFAULT_HEMATOME_SUPERFICIEL_TYPE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_HEMATOME_SUPERFICIEL_TYPE = TypeManifestation.PROVOQUE;

    private static final String DEFAULT_HEMATOME_SUPERFICIEL_SIEGE = "AAAAAAAAAA";
    private static final String UPDATED_HEMATOME_SUPERFICIEL_SIEGE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_HEMATOME_PSOAS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMATOME_PSOAS = OuiNonNP.NON;

    private static final TypeHemorragie DEFAULT_HEMATOME_PSOAS_TYPE = TypeHemorragie.CHUTE_DENTAIRE;
    private static final TypeHemorragie UPDATED_HEMATOME_PSOAS_TYPE = TypeHemorragie.PLAIE_LANGUE;

    private static final OuiNonNP DEFAULT_HEMATOME_PSOAS_RECIDIVE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMATOME_PSOAS_RECIDIVE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES = OuiNonNP.NON;

    private static final TypeHemorragieCutaneoMuqueuse DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES_TYPES =
        TypeHemorragieCutaneoMuqueuse.CHUTE_DENTAIRE;
    private static final TypeHemorragieCutaneoMuqueuse UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES_TYPES =
        TypeHemorragieCutaneoMuqueuse.PLAIE_LANGUE;

    private static final Integer DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES_FREQUENCE_AN = 1;
    private static final Integer UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES_FREQUENCE_AN = 2;

    private static final OuiNonNP DEFAULT_HEMORRAGIE_VISCERES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIE_VISCERES = OuiNonNP.NON;

    private static final TypeHemorragieViscere DEFAULT_HEMORRAGIE_VISCERES_TYPE = TypeHemorragieViscere.HEMATEMESE;
    private static final TypeHemorragieViscere UPDATED_HEMORRAGIE_VISCERES_TYPE = TypeHemorragieViscere.RECTORRAGIE;

    private static final OuiNonNP DEFAULT_HEMORRAGIE_VISCERES_EXPLORATION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIE_VISCERES_EXPLORATION = OuiNonNP.NON;

    private static final String DEFAULT_HEMORRAGIE_VISCERES_EXAMEN = "AAAAAAAAAA";
    private static final String UPDATED_HEMORRAGIE_VISCERES_EXAMEN = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_SAIGNEMENT_SNC = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SAIGNEMENT_SNC = OuiNonNP.NON;

    private static final EvolutionSNC DEFAULT_SAIGNEMENT_SNC_EVOLUTION = EvolutionSNC.REGRESSION;
    private static final EvolutionSNC UPDATED_SAIGNEMENT_SNC_EVOLUTION = EvolutionSNC.DECES;

    private static final OuiNonNP DEFAULT_CIRCONCISION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CIRCONCISION_PREPARATION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION_PREPARATION = OuiNonNP.NON;

    private static final SuiteOperatoire DEFAULT_CIRCONCISION_SUITE_OPERATOIRE = SuiteOperatoire.SIMPLE;
    private static final SuiteOperatoire UPDATED_CIRCONCISION_SUITE_OPERATOIRE = SuiteOperatoire.HEMORRAGIQUE;

    private static final Integer DEFAULT_CIRCONCISION_AGE = 1;
    private static final Integer UPDATED_CIRCONCISION_AGE = 2;

    private static final OuiNonNP DEFAULT_AVULSION_DENTAIRE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_AVULSION_DENTAIRE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_AVULSION_DENTAIRE_PREPARATION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_AVULSION_DENTAIRE_PREPARATION = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_INTERVENTION_CHIRURGICALE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INTERVENTION_CHIRURGICALE = OuiNonNP.NON;

    private static final String DEFAULT_INTERVENTION_CHIRURGICALE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INTERVENTION_CHIRURGICALE_TYPE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES = OuiNonNP.NON;

    private static final TypeAccidentHemorragiqueGrave DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES_TYPES =
        TypeAccidentHemorragiqueGrave.HEMATOMES_CEREBRALES;
    private static final TypeAccidentHemorragiqueGrave UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES_TYPES =
        TypeAccidentHemorragiqueGrave.HEMOTHORAX;

    private static final TypePriseEnCharge DEFAULT_PRISE_EN_CHARGE_THERAPEUTIQUE = TypePriseEnCharge.A_LA_DEMANDE;
    private static final TypePriseEnCharge UPDATED_PRISE_EN_CHARGE_THERAPEUTIQUE = TypePriseEnCharge.PROPHYLAXIE;

    private static final String DEFAULT_CAUSE_PRISE_EN_CHARGE = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_PRISE_EN_CHARGE = "BBBBBBBBBB";

    private static final String DEFAULT_PROPHYLAXIE_DOSE_FREQUENCE = "AAAAAAAAAA";
    private static final String UPDATED_PROPHYLAXIE_DOSE_FREQUENCE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEBUT_PROPHYLAXIE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT_PROPHYLAXIE = LocalDate.now(ZoneId.systemDefault());

    private static final InjectionType DEFAULT_INJECTION_TYPE = InjectionType.AUTO_INJECTION;
    private static final InjectionType UPDATED_INJECTION_TYPE = InjectionType.INJECTION_PARENT;

    private static final ModaliteTraitement DEFAULT_MODALITE_TRAITEMENT = ModaliteTraitement.PLASMATIQUE;
    private static final ModaliteTraitement UPDATED_MODALITE_TRAITEMENT = ModaliteTraitement.RECOMBINANT;

    private static final String DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE_PREMIERE_SUBSTITUTION = 1;
    private static final Integer UPDATED_AGE_PREMIERE_SUBSTITUTION = 2;

    private static final OuiNonNP DEFAULT_ANTECEDENTS_TRAITEMENT_PSL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ANTECEDENTS_TRAITEMENT_PSL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_PLASMA_FRAIS_CONGELE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PLASMA_FRAIS_CONGELE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CRYOPRECIPITE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CRYOPRECIPITE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ORTHOPEDIQUES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ORTHOPEDIQUES = OuiNonNP.NON;

    private static final Boolean DEFAULT_SYNOVITE = false;
    private static final Boolean UPDATED_SYNOVITE = true;

    private static final Boolean DEFAULT_ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE = false;
    private static final Boolean UPDATED_ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE = true;

    private static final Boolean DEFAULT_PSEUDOTUMEURS = false;
    private static final Boolean UPDATED_PSEUDOTUMEURS = true;

    private static final Boolean DEFAULT_FRACTURES = false;
    private static final Boolean UPDATED_FRACTURES = true;

    private static final Boolean DEFAULT_COUDE_DROIT = false;
    private static final Boolean UPDATED_COUDE_DROIT = true;

    private static final Boolean DEFAULT_COUDE_GAUCHE = false;
    private static final Boolean UPDATED_COUDE_GAUCHE = true;

    private static final Boolean DEFAULT_GENOU_DROIT = false;
    private static final Boolean UPDATED_GENOU_DROIT = true;

    private static final Boolean DEFAULT_GENOU_GAUCHE = false;
    private static final Boolean UPDATED_GENOU_GAUCHE = true;

    private static final Boolean DEFAULT_CHEVILLE_DROITE = false;
    private static final Boolean UPDATED_CHEVILLE_DROITE = true;

    private static final Boolean DEFAULT_CHEVILLE_GAUCHE = false;
    private static final Boolean UPDATED_CHEVILLE_GAUCHE = true;

    private static final Boolean DEFAULT_EPAULE_DROITE = false;
    private static final Boolean UPDATED_EPAULE_DROITE = true;

    private static final Boolean DEFAULT_EPAULE_GAUCHE = false;
    private static final Boolean UPDATED_EPAULE_GAUCHE = true;

    private static final Boolean DEFAULT_POIGNET_DROIT = false;
    private static final Boolean UPDATED_POIGNET_DROIT = true;

    private static final Boolean DEFAULT_POIGNET_GAUCHE = false;
    private static final Boolean UPDATED_POIGNET_GAUCHE = true;

    private static final Boolean DEFAULT_HANCHE_DROITE = false;
    private static final Boolean UPDATED_HANCHE_DROITE = true;

    private static final Boolean DEFAULT_HANCHE_GAUCHE = false;
    private static final Boolean UPDATED_HANCHE_GAUCHE = true;

    private static final PECTherapeutique DEFAULT_PEC_THERAPEUTIQUE = PECTherapeutique.RAS;
    private static final PECTherapeutique UPDATED_PEC_THERAPEUTIQUE = PECTherapeutique.SYNOVIORTHOSE;

    private static final OuiNonNP DEFAULT_INHIBITEURS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INHIBITEURS = OuiNonNP.NON;

    private static final TauxInhibiteur DEFAULT_TAUX_INHIBITEUR = TauxInhibiteur.FAIBLE_REPONDEUR;
    private static final TauxInhibiteur UPDATED_TAUX_INHIBITEUR = TauxInhibiteur.FORT_REPONDEUR;

    private static final Boolean DEFAULT_TEST_RECUPERATION_FAH = false;
    private static final Boolean UPDATED_TEST_RECUPERATION_FAH = true;

    private static final LocalDate DEFAULT_DATE_TEST_RECUPERATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TEST_RECUPERATION = LocalDate.now(ZoneId.systemDefault());

    private static final TauxRecuperation DEFAULT_TAUX_RECUPERATION = TauxRecuperation.NORMAL;
    private static final TauxRecuperation UPDATED_TAUX_RECUPERATION = TauxRecuperation.DIMINUE;

    private static final Boolean DEFAULT_ANTI_FAH_NON_NEUTRALISANT = false;
    private static final Boolean UPDATED_ANTI_FAH_NON_NEUTRALISANT = true;

    private static final Boolean DEFAULT_ANTI_FAH_NEGATIF = false;
    private static final Boolean UPDATED_ANTI_FAH_NEGATIF = true;

    private static final RechercheACC DEFAULT_RECHERCHE_ACC = RechercheACC.NON_FAITE;
    private static final RechercheACC UPDATED_RECHERCHE_ACC = RechercheACC.FAITE;

    private static final Boolean DEFAULT_AUGMENTER_DOSE_FACTEUR = false;
    private static final Boolean UPDATED_AUGMENTER_DOSE_FACTEUR = true;

    private static final Boolean DEFAULT_NOVOSEVEN = false;
    private static final Boolean UPDATED_NOVOSEVEN = true;

    private static final Boolean DEFAULT_FEIBA = false;
    private static final Boolean UPDATED_FEIBA = true;

    private static final Boolean DEFAULT_CORTICOTHERAPIE = false;
    private static final Boolean UPDATED_CORTICOTHERAPIE = true;

    private static final Boolean DEFAULT_IMMUREL = false;
    private static final Boolean UPDATED_IMMUREL = true;

    private static final Boolean DEFAULT_ITI = false;
    private static final Boolean UPDATED_ITI = true;

    private static final String DEFAULT_POSOLOGIE_ITI = "AAAAAAAAAA";
    private static final String UPDATED_POSOLOGIE_ITI = "BBBBBBBBBB";

    private static final String DEFAULT_DUREE_ITI = "AAAAAAAAAA";
    private static final String UPDATED_DUREE_ITI = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_OBSERVANCE_ITI = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_OBSERVANCE_ITI = OuiNonNP.NON;

    private static final String DEFAULT_CAUSE_NON_OBSERVANCE = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_NON_OBSERVANCE = "BBBBBBBBBB";

    private static final EvolutionInhibiteur DEFAULT_EVOLUTION_INHIBITEUR = EvolutionInhibiteur.DISPARITION;
    private static final EvolutionInhibiteur UPDATED_EVOLUTION_INHIBITEUR = EvolutionInhibiteur.PERSISTANCE;

    private static final Boolean DEFAULT_PROPHYLAXIE_HEMLIBRA = false;
    private static final Boolean UPDATED_PROPHYLAXIE_HEMLIBRA = true;

    private static final LocalDate DEFAULT_DATE_DEBUT_HEMLIBRA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT_HEMLIBRA = LocalDate.now(ZoneId.systemDefault());

    private static final SchemaTherapeutique DEFAULT_SCHEMA_THERAPEUTIQUE = SchemaTherapeutique.HEBDOMADAIRE;
    private static final SchemaTherapeutique UPDATED_SCHEMA_THERAPEUTIQUE = SchemaTherapeutique.MENSUEL;

    private static final Integer DEFAULT_EFFICACITE_HEMLIBRA = 1;
    private static final Integer UPDATED_EFFICACITE_HEMLIBRA = 2;

    private static final Serologie DEFAULT_SEROLOGIE_HBC = Serologie.POSITIVE;
    private static final Serologie UPDATED_SEROLOGIE_HBC = Serologie.NEGATIVE;

    private static final Serologie DEFAULT_SEROLOGIE_HVC = Serologie.POSITIVE;
    private static final Serologie UPDATED_SEROLOGIE_HVC = Serologie.NEGATIVE;

    private static final LocalDate DEFAULT_DATE_DECOUVERTE_VHC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DECOUVERTE_VHC = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CHARGE_VIRALE_VHC = "AAAAAAAAAA";
    private static final String UPDATED_CHARGE_VIRALE_VHC = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_TRAITEMENT_VHC = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_TRAITEMENT_VHC = OuiNonNP.NON;

    private static final EvolutionVHC DEFAULT_EVOLUTION_VHC = EvolutionVHC.DISPARITION;
    private static final EvolutionVHC UPDATED_EVOLUTION_VHC = EvolutionVHC.PERSISTANCE;

    private static final Serologie DEFAULT_SEROLOGIE_HIV = Serologie.POSITIVE;
    private static final Serologie UPDATED_SEROLOGIE_HIV = Serologie.NEGATIVE;

    private static final VieSociale DEFAULT_VIE_SOCIALE = VieSociale.PRESCOLAIRE;
    private static final VieSociale UPDATED_VIE_SOCIALE = VieSociale.SCOLARISE;

    private static final OuiNonNP DEFAULT_MARIE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_MARIE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_ENFANTS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ENFANTS = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_HANDICAP = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HANDICAP = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_HANDICAP = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HANDICAP = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACTIVITE_SPORTIVE = false;
    private static final Boolean UPDATED_ACTIVITE_SPORTIVE = true;

    private static final String DEFAULT_TYPE_ACTIVITE_SPORTIVE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ACTIVITE_SPORTIVE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_DECEDE = false;
    private static final Boolean UPDATED_DECEDE = true;

    private static final String DEFAULT_CAUSE_DECES = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_DECES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DECES = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DECES = LocalDate.now(ZoneId.systemDefault());

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
            .formeHemophilie(DEFAULT_FORME_HEMOPHILIE)
            .fratrieNbFreres(DEFAULT_FRATRIE_NB_FRERES)
            .fratrieNbSoeurs(DEFAULT_FRATRIE_NB_SOEURS)
            .circonstancesDecouverte(DEFAULT_CIRCONSTANCES_DECOUVERTE)
            .cutaneoMuqueux(DEFAULT_CUTANEO_MUQUEUX)
            .hemarthrose(DEFAULT_HEMARTHROSE)
            .hematomePostTraumatique(DEFAULT_HEMATOME_POST_TRAUMATIQUE)
            .plaiePostTraumatique(DEFAULT_PLAIE_POST_TRAUMATIQUE)
            .hemorragiePostCirconcision(DEFAULT_HEMORRAGIE_POST_CIRCONCISION)
            .hemorragiePostChirurgie(DEFAULT_HEMORRAGIE_POST_CHIRURGIE)
            .hemorragieCerebrale(DEFAULT_HEMORRAGIE_CEREBRALE)
            .hemorragieDigestive(DEFAULT_HEMORRAGIE_DIGESTIVE)
            .datePremierSigneClinique(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE)
            .ageDiagnosticJours(DEFAULT_AGE_DIAGNOSTIC_JOURS)
            .ageDiagnosticMois(DEFAULT_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnees(DEFAULT_AGE_DIAGNOSTIC_ANNEES)
            .dateTestConfirmation(DEFAULT_DATE_TEST_CONFIRMATION)
            .hb(DEFAULT_HB)
            .ht(DEFAULT_HT)
            .plaquettes(DEFAULT_PLAQUETTES)
            .tp(DEFAULT_TP)
            .fibrinogene(DEFAULT_FIBRINOGENE)
            .tcaMT(DEFAULT_TCA_MT)
            .tcaMTTotal(DEFAULT_TCA_MT_TOTAL)
            .tcaMT2h(DEFAULT_TCA_MT_2_H)
            .tcaTemoin2h(DEFAULT_TCA_TEMOIN_2_H)
            .ts(DEFAULT_TS)
            .pfa(DEFAULT_PFA)
            .dosageFacteurVIII(DEFAULT_DOSAGE_FACTEUR_VIII)
            .dosageFacteurIX(DEFAULT_DOSAGE_FACTEUR_IX)
            .dosageFacteurVWActivite(DEFAULT_DOSAGE_FACTEUR_VW_ACTIVITE)
            .dosageFacteurVWAntigene(DEFAULT_DOSAGE_FACTEUR_VW_ANTIGENE)
            .formeClinique(DEFAULT_FORME_CLINIQUE)
            .severite(DEFAULT_SEVERITE)
            .explorationMoleculaire(DEFAULT_EXPLORATION_MOLECULAIRE)
            .inversionIntron22(DEFAULT_INVERSION_INTRON_22)
            .autreExplorationMoleculaire(DEFAULT_AUTRE_EXPLORATION_MOLECULAIRE)
            .hemarthroseMC(DEFAULT_HEMARTHROSE_MC)
            .hemarthroseSiege(DEFAULT_HEMARTHROSE_SIEGE)
            .hemarthroseType(DEFAULT_HEMARTHROSE_TYPE)
            .hemarthroseFrequenceAn(DEFAULT_HEMARTHROSE_FREQUENCE_AN)
            .hematomeSuperficiel(DEFAULT_HEMATOME_SUPERFICIEL)
            .hematomeSuperficielType(DEFAULT_HEMATOME_SUPERFICIEL_TYPE)
            .hematomeSuperficielSiege(DEFAULT_HEMATOME_SUPERFICIEL_SIEGE)
            .hematomePsoas(DEFAULT_HEMATOME_PSOAS)
            .hematomePsoasType(DEFAULT_HEMATOME_PSOAS_TYPE)
            .hematomePsoasRecidive(DEFAULT_HEMATOME_PSOAS_RECIDIVE)
            .hemorragiesCutaneoMuqueuses(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .hemorragiesCutaneoMuqueusesTypes(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES_TYPES)
            .hemorragiesCutaneoMuqueusesFrequenceAn(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES_FREQUENCE_AN)
            .hemorragieVisceres(DEFAULT_HEMORRAGIE_VISCERES)
            .hemorragieVisceresType(DEFAULT_HEMORRAGIE_VISCERES_TYPE)
            .hemorragieVisceresExploration(DEFAULT_HEMORRAGIE_VISCERES_EXPLORATION)
            .hemorragieVisceresExamen(DEFAULT_HEMORRAGIE_VISCERES_EXAMEN)
            .saignementSNC(DEFAULT_SAIGNEMENT_SNC)
            .saignementSNCEvolution(DEFAULT_SAIGNEMENT_SNC_EVOLUTION)
            .circoncision(DEFAULT_CIRCONCISION)
            .circoncisionPreparation(DEFAULT_CIRCONCISION_PREPARATION)
            .circoncisionSuiteOperatoire(DEFAULT_CIRCONCISION_SUITE_OPERATOIRE)
            .circoncisionAge(DEFAULT_CIRCONCISION_AGE)
            .avulsionDentaire(DEFAULT_AVULSION_DENTAIRE)
            .avulsionDentairePreparation(DEFAULT_AVULSION_DENTAIRE_PREPARATION)
            .interventionChirurgicale(DEFAULT_INTERVENTION_CHIRURGICALE)
            .interventionChirurgicaleType(DEFAULT_INTERVENTION_CHIRURGICALE_TYPE)
            .accidentsHemorragiquesGraves(DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .accidentsHemorragiquesGravesTypes(DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES_TYPES)
            .priseEnChargeTherapeutique(DEFAULT_PRISE_EN_CHARGE_THERAPEUTIQUE)
            .causePriseEnCharge(DEFAULT_CAUSE_PRISE_EN_CHARGE)
            .prophylaxieDoseFrequence(DEFAULT_PROPHYLAXIE_DOSE_FREQUENCE)
            .dateDebutProphylaxie(DEFAULT_DATE_DEBUT_PROPHYLAXIE)
            .injectionType(DEFAULT_INJECTION_TYPE)
            .modaliteTraitement(DEFAULT_MODALITE_TRAITEMENT)
            .typeTraitementSubstitutif(DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF)
            .agePremiereSubstitution(DEFAULT_AGE_PREMIERE_SUBSTITUTION)
            .antecedentsTraitementPSL(DEFAULT_ANTECEDENTS_TRAITEMENT_PSL)
            .plasmaFraisCongele(DEFAULT_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(DEFAULT_CRYOPRECIPITE)
            .orthopediques(DEFAULT_ORTHOPEDIQUES)
            .synovite(DEFAULT_SYNOVITE)
            .arthropathieHemophiliqueChronique(DEFAULT_ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE)
            .pseudotumeurs(DEFAULT_PSEUDOTUMEURS)
            .fractures(DEFAULT_FRACTURES)
            .coudeDroit(DEFAULT_COUDE_DROIT)
            .coudeGauche(DEFAULT_COUDE_GAUCHE)
            .genouDroit(DEFAULT_GENOU_DROIT)
            .genouGauche(DEFAULT_GENOU_GAUCHE)
            .chevilleDroite(DEFAULT_CHEVILLE_DROITE)
            .chevilleGauche(DEFAULT_CHEVILLE_GAUCHE)
            .epauleDroite(DEFAULT_EPAULE_DROITE)
            .epauleGauche(DEFAULT_EPAULE_GAUCHE)
            .poignetDroit(DEFAULT_POIGNET_DROIT)
            .poignetGauche(DEFAULT_POIGNET_GAUCHE)
            .hancheDroite(DEFAULT_HANCHE_DROITE)
            .hancheGauche(DEFAULT_HANCHE_GAUCHE)
            .pecTherapeutique(DEFAULT_PEC_THERAPEUTIQUE)
            .inhibiteurs(DEFAULT_INHIBITEURS)
            .tauxInhibiteur(DEFAULT_TAUX_INHIBITEUR)
            .testRecuperationFAH(DEFAULT_TEST_RECUPERATION_FAH)
            .dateTestRecuperation(DEFAULT_DATE_TEST_RECUPERATION)
            .tauxRecuperation(DEFAULT_TAUX_RECUPERATION)
            .antiFAHNonNeutralisant(DEFAULT_ANTI_FAH_NON_NEUTRALISANT)
            .antiFAHNegatif(DEFAULT_ANTI_FAH_NEGATIF)
            .rechercheACC(DEFAULT_RECHERCHE_ACC)
            .augmenterDoseFacteur(DEFAULT_AUGMENTER_DOSE_FACTEUR)
            .novoseven(DEFAULT_NOVOSEVEN)
            .feiba(DEFAULT_FEIBA)
            .corticotherapie(DEFAULT_CORTICOTHERAPIE)
            .immurel(DEFAULT_IMMUREL)
            .iti(DEFAULT_ITI)
            .posologieITI(DEFAULT_POSOLOGIE_ITI)
            .dureeITI(DEFAULT_DUREE_ITI)
            .observanceITI(DEFAULT_OBSERVANCE_ITI)
            .causeNonObservance(DEFAULT_CAUSE_NON_OBSERVANCE)
            .evolutionInhibiteur(DEFAULT_EVOLUTION_INHIBITEUR)
            .prophylaxieHemlibra(DEFAULT_PROPHYLAXIE_HEMLIBRA)
            .dateDebutHemlibra(DEFAULT_DATE_DEBUT_HEMLIBRA)
            .schemaTherapeutique(DEFAULT_SCHEMA_THERAPEUTIQUE)
            .efficaciteHemlibra(DEFAULT_EFFICACITE_HEMLIBRA)
            .serologieHBC(DEFAULT_SEROLOGIE_HBC)
            .serologieHVC(DEFAULT_SEROLOGIE_HVC)
            .dateDecouverteVHC(DEFAULT_DATE_DECOUVERTE_VHC)
            .chargeViraleVHC(DEFAULT_CHARGE_VIRALE_VHC)
            .traitementVHC(DEFAULT_TRAITEMENT_VHC)
            .evolutionVHC(DEFAULT_EVOLUTION_VHC)
            .serologieHIV(DEFAULT_SEROLOGIE_HIV)
            .vieSociale(DEFAULT_VIE_SOCIALE)
            .marie(DEFAULT_MARIE)
            .enfants(DEFAULT_ENFANTS)
            .handicap(DEFAULT_HANDICAP)
            .typeHandicap(DEFAULT_TYPE_HANDICAP)
            .activiteSportive(DEFAULT_ACTIVITE_SPORTIVE)
            .typeActiviteSportive(DEFAULT_TYPE_ACTIVITE_SPORTIVE)
            .decede(DEFAULT_DECEDE)
            .causeDeces(DEFAULT_CAUSE_DECES)
            .dateDeces(DEFAULT_DATE_DECES);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FicheHemophilie createUpdatedEntity() {
        return new FicheHemophilie()
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
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .fratrieNbFreres(UPDATED_FRATRIE_NB_FRERES)
            .fratrieNbSoeurs(UPDATED_FRATRIE_NB_SOEURS)
            .circonstancesDecouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .cutaneoMuqueux(UPDATED_CUTANEO_MUQUEUX)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .hematomePostTraumatique(UPDATED_HEMATOME_POST_TRAUMATIQUE)
            .plaiePostTraumatique(UPDATED_PLAIE_POST_TRAUMATIQUE)
            .hemorragiePostCirconcision(UPDATED_HEMORRAGIE_POST_CIRCONCISION)
            .hemorragiePostChirurgie(UPDATED_HEMORRAGIE_POST_CHIRURGIE)
            .hemorragieCerebrale(UPDATED_HEMORRAGIE_CEREBRALE)
            .hemorragieDigestive(UPDATED_HEMORRAGIE_DIGESTIVE)
            .datePremierSigneClinique(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE)
            .ageDiagnosticJours(UPDATED_AGE_DIAGNOSTIC_JOURS)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnees(UPDATED_AGE_DIAGNOSTIC_ANNEES)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hb(UPDATED_HB)
            .ht(UPDATED_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMTTotal(UPDATED_TCA_MT_TOTAL)
            .tcaMT2h(UPDATED_TCA_MT_2_H)
            .tcaTemoin2h(UPDATED_TCA_TEMOIN_2_H)
            .ts(UPDATED_TS)
            .pfa(UPDATED_PFA)
            .dosageFacteurVIII(UPDATED_DOSAGE_FACTEUR_VIII)
            .dosageFacteurIX(UPDATED_DOSAGE_FACTEUR_IX)
            .dosageFacteurVWActivite(UPDATED_DOSAGE_FACTEUR_VW_ACTIVITE)
            .dosageFacteurVWAntigene(UPDATED_DOSAGE_FACTEUR_VW_ANTIGENE)
            .formeClinique(UPDATED_FORME_CLINIQUE)
            .severite(UPDATED_SEVERITE)
            .explorationMoleculaire(UPDATED_EXPLORATION_MOLECULAIRE)
            .inversionIntron22(UPDATED_INVERSION_INTRON_22)
            .autreExplorationMoleculaire(UPDATED_AUTRE_EXPLORATION_MOLECULAIRE)
            .hemarthroseMC(UPDATED_HEMARTHROSE_MC)
            .hemarthroseSiege(UPDATED_HEMARTHROSE_SIEGE)
            .hemarthroseType(UPDATED_HEMARTHROSE_TYPE)
            .hemarthroseFrequenceAn(UPDATED_HEMARTHROSE_FREQUENCE_AN)
            .hematomeSuperficiel(UPDATED_HEMATOME_SUPERFICIEL)
            .hematomeSuperficielType(UPDATED_HEMATOME_SUPERFICIEL_TYPE)
            .hematomeSuperficielSiege(UPDATED_HEMATOME_SUPERFICIEL_SIEGE)
            .hematomePsoas(UPDATED_HEMATOME_PSOAS)
            .hematomePsoasType(UPDATED_HEMATOME_PSOAS_TYPE)
            .hematomePsoasRecidive(UPDATED_HEMATOME_PSOAS_RECIDIVE)
            .hemorragiesCutaneoMuqueuses(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .hemorragiesCutaneoMuqueusesTypes(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES_TYPES)
            .hemorragiesCutaneoMuqueusesFrequenceAn(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES_FREQUENCE_AN)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hemorragieVisceresType(UPDATED_HEMORRAGIE_VISCERES_TYPE)
            .hemorragieVisceresExploration(UPDATED_HEMORRAGIE_VISCERES_EXPLORATION)
            .hemorragieVisceresExamen(UPDATED_HEMORRAGIE_VISCERES_EXAMEN)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .saignementSNCEvolution(UPDATED_SAIGNEMENT_SNC_EVOLUTION)
            .circoncision(UPDATED_CIRCONCISION)
            .circoncisionPreparation(UPDATED_CIRCONCISION_PREPARATION)
            .circoncisionSuiteOperatoire(UPDATED_CIRCONCISION_SUITE_OPERATOIRE)
            .circoncisionAge(UPDATED_CIRCONCISION_AGE)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .avulsionDentairePreparation(UPDATED_AVULSION_DENTAIRE_PREPARATION)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .interventionChirurgicaleType(UPDATED_INTERVENTION_CHIRURGICALE_TYPE)
            .accidentsHemorragiquesGraves(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .accidentsHemorragiquesGravesTypes(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES_TYPES)
            .priseEnChargeTherapeutique(UPDATED_PRISE_EN_CHARGE_THERAPEUTIQUE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .prophylaxieDoseFrequence(UPDATED_PROPHYLAXIE_DOSE_FREQUENCE)
            .dateDebutProphylaxie(UPDATED_DATE_DEBUT_PROPHYLAXIE)
            .injectionType(UPDATED_INJECTION_TYPE)
            .modaliteTraitement(UPDATED_MODALITE_TRAITEMENT)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .agePremiereSubstitution(UPDATED_AGE_PREMIERE_SUBSTITUTION)
            .antecedentsTraitementPSL(UPDATED_ANTECEDENTS_TRAITEMENT_PSL)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .orthopediques(UPDATED_ORTHOPEDIQUES)
            .synovite(UPDATED_SYNOVITE)
            .arthropathieHemophiliqueChronique(UPDATED_ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE)
            .pseudotumeurs(UPDATED_PSEUDOTUMEURS)
            .fractures(UPDATED_FRACTURES)
            .coudeDroit(UPDATED_COUDE_DROIT)
            .coudeGauche(UPDATED_COUDE_GAUCHE)
            .genouDroit(UPDATED_GENOU_DROIT)
            .genouGauche(UPDATED_GENOU_GAUCHE)
            .chevilleDroite(UPDATED_CHEVILLE_DROITE)
            .chevilleGauche(UPDATED_CHEVILLE_GAUCHE)
            .epauleDroite(UPDATED_EPAULE_DROITE)
            .epauleGauche(UPDATED_EPAULE_GAUCHE)
            .poignetDroit(UPDATED_POIGNET_DROIT)
            .poignetGauche(UPDATED_POIGNET_GAUCHE)
            .hancheDroite(UPDATED_HANCHE_DROITE)
            .hancheGauche(UPDATED_HANCHE_GAUCHE)
            .pecTherapeutique(UPDATED_PEC_THERAPEUTIQUE)
            .inhibiteurs(UPDATED_INHIBITEURS)
            .tauxInhibiteur(UPDATED_TAUX_INHIBITEUR)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .dateTestRecuperation(UPDATED_DATE_TEST_RECUPERATION)
            .tauxRecuperation(UPDATED_TAUX_RECUPERATION)
            .antiFAHNonNeutralisant(UPDATED_ANTI_FAH_NON_NEUTRALISANT)
            .antiFAHNegatif(UPDATED_ANTI_FAH_NEGATIF)
            .rechercheACC(UPDATED_RECHERCHE_ACC)
            .augmenterDoseFacteur(UPDATED_AUGMENTER_DOSE_FACTEUR)
            .novoseven(UPDATED_NOVOSEVEN)
            .feiba(UPDATED_FEIBA)
            .corticotherapie(UPDATED_CORTICOTHERAPIE)
            .immurel(UPDATED_IMMUREL)
            .iti(UPDATED_ITI)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .observanceITI(UPDATED_OBSERVANCE_ITI)
            .causeNonObservance(UPDATED_CAUSE_NON_OBSERVANCE)
            .evolutionInhibiteur(UPDATED_EVOLUTION_INHIBITEUR)
            .prophylaxieHemlibra(UPDATED_PROPHYLAXIE_HEMLIBRA)
            .dateDebutHemlibra(UPDATED_DATE_DEBUT_HEMLIBRA)
            .schemaTherapeutique(UPDATED_SCHEMA_THERAPEUTIQUE)
            .efficaciteHemlibra(UPDATED_EFFICACITE_HEMLIBRA)
            .serologieHBC(UPDATED_SEROLOGIE_HBC)
            .serologieHVC(UPDATED_SEROLOGIE_HVC)
            .dateDecouverteVHC(UPDATED_DATE_DECOUVERTE_VHC)
            .chargeViraleVHC(UPDATED_CHARGE_VIRALE_VHC)
            .traitementVHC(UPDATED_TRAITEMENT_VHC)
            .evolutionVHC(UPDATED_EVOLUTION_VHC)
            .serologieHIV(UPDATED_SEROLOGIE_HIV)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .handicap(UPDATED_HANDICAP)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .activiteSportive(UPDATED_ACTIVITE_SPORTIVE)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .decede(UPDATED_DECEDE)
            .causeDeces(UPDATED_CAUSE_DECES)
            .dateDeces(UPDATED_DATE_DECES);
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
    void checkServiceCliniqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setServiceClinique(null);

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
    void checkCirconstancesDecouverteIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setCirconstancesDecouverte(null);

        // Create the FicheHemophilie, which fails.
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFormeCliniqueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setFormeClinique(null);

        // Create the FicheHemophilie, which fails.
        FicheHemophilieDTO ficheHemophilieDTO = ficheHemophilieMapper.toDto(ficheHemophilie);

        restFicheHemophilieMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(ficheHemophilieDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSeveriteIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setSeverite(null);

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
            .andExpect(jsonPath("$.[*].degreParenteCasSimilaires").value(hasItem(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES.toString())))
            .andExpect(jsonPath("$.[*].decesSyndromeHemorragique").value(hasItem(DEFAULT_DECES_SYNDROME_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].nombreDeces").value(hasItem(DEFAULT_NOMBRE_DECES)))
            .andExpect(jsonPath("$.[*].formeHemophilie").value(hasItem(DEFAULT_FORME_HEMOPHILIE.toString())))
            .andExpect(jsonPath("$.[*].fratrieNbFreres").value(hasItem(DEFAULT_FRATRIE_NB_FRERES)))
            .andExpect(jsonPath("$.[*].fratrieNbSoeurs").value(hasItem(DEFAULT_FRATRIE_NB_SOEURS)))
            .andExpect(jsonPath("$.[*].circonstancesDecouverte").value(hasItem(DEFAULT_CIRCONSTANCES_DECOUVERTE.toString())))
            .andExpect(jsonPath("$.[*].cutaneoMuqueux").value(hasItem(DEFAULT_CUTANEO_MUQUEUX.booleanValue())))
            .andExpect(jsonPath("$.[*].hemarthrose").value(hasItem(DEFAULT_HEMARTHROSE.booleanValue())))
            .andExpect(jsonPath("$.[*].hematomePostTraumatique").value(hasItem(DEFAULT_HEMATOME_POST_TRAUMATIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].plaiePostTraumatique").value(hasItem(DEFAULT_PLAIE_POST_TRAUMATIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragiePostCirconcision").value(hasItem(DEFAULT_HEMORRAGIE_POST_CIRCONCISION.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragiePostChirurgie").value(hasItem(DEFAULT_HEMORRAGIE_POST_CHIRURGIE.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragieCerebrale").value(hasItem(DEFAULT_HEMORRAGIE_CEREBRALE.booleanValue())))
            .andExpect(jsonPath("$.[*].hemorragieDigestive").value(hasItem(DEFAULT_HEMORRAGIE_DIGESTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].datePremierSigneClinique").value(hasItem(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE.toString())))
            .andExpect(jsonPath("$.[*].ageDiagnosticJours").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_JOURS)))
            .andExpect(jsonPath("$.[*].ageDiagnosticMois").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_MOIS)))
            .andExpect(jsonPath("$.[*].ageDiagnosticAnnees").value(hasItem(DEFAULT_AGE_DIAGNOSTIC_ANNEES)))
            .andExpect(jsonPath("$.[*].dateTestConfirmation").value(hasItem(DEFAULT_DATE_TEST_CONFIRMATION.toString())))
            .andExpect(jsonPath("$.[*].hb").value(hasItem(DEFAULT_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].ht").value(hasItem(DEFAULT_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].plaquettes").value(hasItem(DEFAULT_PLAQUETTES)))
            .andExpect(jsonPath("$.[*].tp").value(hasItem(DEFAULT_TP.doubleValue())))
            .andExpect(jsonPath("$.[*].fibrinogene").value(hasItem(DEFAULT_FIBRINOGENE.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMT").value(hasItem(DEFAULT_TCA_MT.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMTTotal").value(hasItem(DEFAULT_TCA_MT_TOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMT2h").value(hasItem(DEFAULT_TCA_MT_2_H.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaTemoin2h").value(hasItem(DEFAULT_TCA_TEMOIN_2_H.doubleValue())))
            .andExpect(jsonPath("$.[*].ts").value(hasItem(DEFAULT_TS.toString())))
            .andExpect(jsonPath("$.[*].pfa").value(hasItem(DEFAULT_PFA.toString())))
            .andExpect(jsonPath("$.[*].dosageFacteurVIII").value(hasItem(DEFAULT_DOSAGE_FACTEUR_VIII.doubleValue())))
            .andExpect(jsonPath("$.[*].dosageFacteurIX").value(hasItem(DEFAULT_DOSAGE_FACTEUR_IX.doubleValue())))
            .andExpect(jsonPath("$.[*].dosageFacteurVWActivite").value(hasItem(DEFAULT_DOSAGE_FACTEUR_VW_ACTIVITE.doubleValue())))
            .andExpect(jsonPath("$.[*].dosageFacteurVWAntigene").value(hasItem(DEFAULT_DOSAGE_FACTEUR_VW_ANTIGENE.doubleValue())))
            .andExpect(jsonPath("$.[*].formeClinique").value(hasItem(DEFAULT_FORME_CLINIQUE.toString())))
            .andExpect(jsonPath("$.[*].severite").value(hasItem(DEFAULT_SEVERITE.toString())))
            .andExpect(jsonPath("$.[*].explorationMoleculaire").value(hasItem(DEFAULT_EXPLORATION_MOLECULAIRE.toString())))
            .andExpect(jsonPath("$.[*].inversionIntron22").value(hasItem(DEFAULT_INVERSION_INTRON_22.booleanValue())))
            .andExpect(jsonPath("$.[*].autreExplorationMoleculaire").value(hasItem(DEFAULT_AUTRE_EXPLORATION_MOLECULAIRE)))
            .andExpect(jsonPath("$.[*].hemarthroseMC").value(hasItem(DEFAULT_HEMARTHROSE_MC.toString())))
            .andExpect(jsonPath("$.[*].hemarthroseSiege").value(hasItem(DEFAULT_HEMARTHROSE_SIEGE)))
            .andExpect(jsonPath("$.[*].hemarthroseType").value(hasItem(DEFAULT_HEMARTHROSE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].hemarthroseFrequenceAn").value(hasItem(DEFAULT_HEMARTHROSE_FREQUENCE_AN)))
            .andExpect(jsonPath("$.[*].hematomeSuperficiel").value(hasItem(DEFAULT_HEMATOME_SUPERFICIEL.toString())))
            .andExpect(jsonPath("$.[*].hematomeSuperficielType").value(hasItem(DEFAULT_HEMATOME_SUPERFICIEL_TYPE.toString())))
            .andExpect(jsonPath("$.[*].hematomeSuperficielSiege").value(hasItem(DEFAULT_HEMATOME_SUPERFICIEL_SIEGE)))
            .andExpect(jsonPath("$.[*].hematomePsoas").value(hasItem(DEFAULT_HEMATOME_PSOAS.toString())))
            .andExpect(jsonPath("$.[*].hematomePsoasType").value(hasItem(DEFAULT_HEMATOME_PSOAS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].hematomePsoasRecidive").value(hasItem(DEFAULT_HEMATOME_PSOAS_RECIDIVE.toString())))
            .andExpect(jsonPath("$.[*].hemorragiesCutaneoMuqueuses").value(hasItem(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES.toString())))
            .andExpect(
                jsonPath("$.[*].hemorragiesCutaneoMuqueusesTypes").value(hasItem(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES_TYPES.toString()))
            )
            .andExpect(
                jsonPath("$.[*].hemorragiesCutaneoMuqueusesFrequenceAn").value(hasItem(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES_FREQUENCE_AN))
            )
            .andExpect(jsonPath("$.[*].hemorragieVisceres").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES.toString())))
            .andExpect(jsonPath("$.[*].hemorragieVisceresType").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES_TYPE.toString())))
            .andExpect(jsonPath("$.[*].hemorragieVisceresExploration").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES_EXPLORATION.toString())))
            .andExpect(jsonPath("$.[*].hemorragieVisceresExamen").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES_EXAMEN)))
            .andExpect(jsonPath("$.[*].saignementSNC").value(hasItem(DEFAULT_SAIGNEMENT_SNC.toString())))
            .andExpect(jsonPath("$.[*].saignementSNCEvolution").value(hasItem(DEFAULT_SAIGNEMENT_SNC_EVOLUTION.toString())))
            .andExpect(jsonPath("$.[*].circoncision").value(hasItem(DEFAULT_CIRCONCISION.toString())))
            .andExpect(jsonPath("$.[*].circoncisionPreparation").value(hasItem(DEFAULT_CIRCONCISION_PREPARATION.toString())))
            .andExpect(jsonPath("$.[*].circoncisionSuiteOperatoire").value(hasItem(DEFAULT_CIRCONCISION_SUITE_OPERATOIRE.toString())))
            .andExpect(jsonPath("$.[*].circoncisionAge").value(hasItem(DEFAULT_CIRCONCISION_AGE)))
            .andExpect(jsonPath("$.[*].avulsionDentaire").value(hasItem(DEFAULT_AVULSION_DENTAIRE.toString())))
            .andExpect(jsonPath("$.[*].avulsionDentairePreparation").value(hasItem(DEFAULT_AVULSION_DENTAIRE_PREPARATION.toString())))
            .andExpect(jsonPath("$.[*].interventionChirurgicale").value(hasItem(DEFAULT_INTERVENTION_CHIRURGICALE.toString())))
            .andExpect(jsonPath("$.[*].interventionChirurgicaleType").value(hasItem(DEFAULT_INTERVENTION_CHIRURGICALE_TYPE)))
            .andExpect(jsonPath("$.[*].accidentsHemorragiquesGraves").value(hasItem(DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES.toString())))
            .andExpect(
                jsonPath("$.[*].accidentsHemorragiquesGravesTypes").value(hasItem(DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES_TYPES.toString()))
            )
            .andExpect(jsonPath("$.[*].priseEnChargeTherapeutique").value(hasItem(DEFAULT_PRISE_EN_CHARGE_THERAPEUTIQUE.toString())))
            .andExpect(jsonPath("$.[*].causePriseEnCharge").value(hasItem(DEFAULT_CAUSE_PRISE_EN_CHARGE)))
            .andExpect(jsonPath("$.[*].prophylaxieDoseFrequence").value(hasItem(DEFAULT_PROPHYLAXIE_DOSE_FREQUENCE)))
            .andExpect(jsonPath("$.[*].dateDebutProphylaxie").value(hasItem(DEFAULT_DATE_DEBUT_PROPHYLAXIE.toString())))
            .andExpect(jsonPath("$.[*].injectionType").value(hasItem(DEFAULT_INJECTION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].modaliteTraitement").value(hasItem(DEFAULT_MODALITE_TRAITEMENT.toString())))
            .andExpect(jsonPath("$.[*].typeTraitementSubstitutif").value(hasItem(DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF)))
            .andExpect(jsonPath("$.[*].agePremiereSubstitution").value(hasItem(DEFAULT_AGE_PREMIERE_SUBSTITUTION)))
            .andExpect(jsonPath("$.[*].antecedentsTraitementPSL").value(hasItem(DEFAULT_ANTECEDENTS_TRAITEMENT_PSL.toString())))
            .andExpect(jsonPath("$.[*].plasmaFraisCongele").value(hasItem(DEFAULT_PLASMA_FRAIS_CONGELE.toString())))
            .andExpect(jsonPath("$.[*].cryoprecipite").value(hasItem(DEFAULT_CRYOPRECIPITE.toString())))
            .andExpect(jsonPath("$.[*].orthopediques").value(hasItem(DEFAULT_ORTHOPEDIQUES.toString())))
            .andExpect(jsonPath("$.[*].synovite").value(hasItem(DEFAULT_SYNOVITE.booleanValue())))
            .andExpect(
                jsonPath("$.[*].arthropathieHemophiliqueChronique").value(
                    hasItem(DEFAULT_ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE.booleanValue())
                )
            )
            .andExpect(jsonPath("$.[*].pseudotumeurs").value(hasItem(DEFAULT_PSEUDOTUMEURS.booleanValue())))
            .andExpect(jsonPath("$.[*].fractures").value(hasItem(DEFAULT_FRACTURES.booleanValue())))
            .andExpect(jsonPath("$.[*].coudeDroit").value(hasItem(DEFAULT_COUDE_DROIT.booleanValue())))
            .andExpect(jsonPath("$.[*].coudeGauche").value(hasItem(DEFAULT_COUDE_GAUCHE.booleanValue())))
            .andExpect(jsonPath("$.[*].genouDroit").value(hasItem(DEFAULT_GENOU_DROIT.booleanValue())))
            .andExpect(jsonPath("$.[*].genouGauche").value(hasItem(DEFAULT_GENOU_GAUCHE.booleanValue())))
            .andExpect(jsonPath("$.[*].chevilleDroite").value(hasItem(DEFAULT_CHEVILLE_DROITE.booleanValue())))
            .andExpect(jsonPath("$.[*].chevilleGauche").value(hasItem(DEFAULT_CHEVILLE_GAUCHE.booleanValue())))
            .andExpect(jsonPath("$.[*].epauleDroite").value(hasItem(DEFAULT_EPAULE_DROITE.booleanValue())))
            .andExpect(jsonPath("$.[*].epauleGauche").value(hasItem(DEFAULT_EPAULE_GAUCHE.booleanValue())))
            .andExpect(jsonPath("$.[*].poignetDroit").value(hasItem(DEFAULT_POIGNET_DROIT.booleanValue())))
            .andExpect(jsonPath("$.[*].poignetGauche").value(hasItem(DEFAULT_POIGNET_GAUCHE.booleanValue())))
            .andExpect(jsonPath("$.[*].hancheDroite").value(hasItem(DEFAULT_HANCHE_DROITE.booleanValue())))
            .andExpect(jsonPath("$.[*].hancheGauche").value(hasItem(DEFAULT_HANCHE_GAUCHE.booleanValue())))
            .andExpect(jsonPath("$.[*].pecTherapeutique").value(hasItem(DEFAULT_PEC_THERAPEUTIQUE.toString())))
            .andExpect(jsonPath("$.[*].inhibiteurs").value(hasItem(DEFAULT_INHIBITEURS.toString())))
            .andExpect(jsonPath("$.[*].tauxInhibiteur").value(hasItem(DEFAULT_TAUX_INHIBITEUR.toString())))
            .andExpect(jsonPath("$.[*].testRecuperationFAH").value(hasItem(DEFAULT_TEST_RECUPERATION_FAH.booleanValue())))
            .andExpect(jsonPath("$.[*].dateTestRecuperation").value(hasItem(DEFAULT_DATE_TEST_RECUPERATION.toString())))
            .andExpect(jsonPath("$.[*].tauxRecuperation").value(hasItem(DEFAULT_TAUX_RECUPERATION.toString())))
            .andExpect(jsonPath("$.[*].antiFAHNonNeutralisant").value(hasItem(DEFAULT_ANTI_FAH_NON_NEUTRALISANT.booleanValue())))
            .andExpect(jsonPath("$.[*].antiFAHNegatif").value(hasItem(DEFAULT_ANTI_FAH_NEGATIF.booleanValue())))
            .andExpect(jsonPath("$.[*].rechercheACC").value(hasItem(DEFAULT_RECHERCHE_ACC.toString())))
            .andExpect(jsonPath("$.[*].augmenterDoseFacteur").value(hasItem(DEFAULT_AUGMENTER_DOSE_FACTEUR.booleanValue())))
            .andExpect(jsonPath("$.[*].novoseven").value(hasItem(DEFAULT_NOVOSEVEN.booleanValue())))
            .andExpect(jsonPath("$.[*].feiba").value(hasItem(DEFAULT_FEIBA.booleanValue())))
            .andExpect(jsonPath("$.[*].corticotherapie").value(hasItem(DEFAULT_CORTICOTHERAPIE.booleanValue())))
            .andExpect(jsonPath("$.[*].immurel").value(hasItem(DEFAULT_IMMUREL.booleanValue())))
            .andExpect(jsonPath("$.[*].iti").value(hasItem(DEFAULT_ITI.booleanValue())))
            .andExpect(jsonPath("$.[*].posologieITI").value(hasItem(DEFAULT_POSOLOGIE_ITI)))
            .andExpect(jsonPath("$.[*].dureeITI").value(hasItem(DEFAULT_DUREE_ITI)))
            .andExpect(jsonPath("$.[*].observanceITI").value(hasItem(DEFAULT_OBSERVANCE_ITI.toString())))
            .andExpect(jsonPath("$.[*].causeNonObservance").value(hasItem(DEFAULT_CAUSE_NON_OBSERVANCE)))
            .andExpect(jsonPath("$.[*].evolutionInhibiteur").value(hasItem(DEFAULT_EVOLUTION_INHIBITEUR.toString())))
            .andExpect(jsonPath("$.[*].prophylaxieHemlibra").value(hasItem(DEFAULT_PROPHYLAXIE_HEMLIBRA.booleanValue())))
            .andExpect(jsonPath("$.[*].dateDebutHemlibra").value(hasItem(DEFAULT_DATE_DEBUT_HEMLIBRA.toString())))
            .andExpect(jsonPath("$.[*].schemaTherapeutique").value(hasItem(DEFAULT_SCHEMA_THERAPEUTIQUE.toString())))
            .andExpect(jsonPath("$.[*].efficaciteHemlibra").value(hasItem(DEFAULT_EFFICACITE_HEMLIBRA)))
            .andExpect(jsonPath("$.[*].serologieHBC").value(hasItem(DEFAULT_SEROLOGIE_HBC.toString())))
            .andExpect(jsonPath("$.[*].serologieHVC").value(hasItem(DEFAULT_SEROLOGIE_HVC.toString())))
            .andExpect(jsonPath("$.[*].dateDecouverteVHC").value(hasItem(DEFAULT_DATE_DECOUVERTE_VHC.toString())))
            .andExpect(jsonPath("$.[*].chargeViraleVHC").value(hasItem(DEFAULT_CHARGE_VIRALE_VHC)))
            .andExpect(jsonPath("$.[*].traitementVHC").value(hasItem(DEFAULT_TRAITEMENT_VHC.toString())))
            .andExpect(jsonPath("$.[*].evolutionVHC").value(hasItem(DEFAULT_EVOLUTION_VHC.toString())))
            .andExpect(jsonPath("$.[*].serologieHIV").value(hasItem(DEFAULT_SEROLOGIE_HIV.toString())))
            .andExpect(jsonPath("$.[*].vieSociale").value(hasItem(DEFAULT_VIE_SOCIALE.toString())))
            .andExpect(jsonPath("$.[*].marie").value(hasItem(DEFAULT_MARIE.toString())))
            .andExpect(jsonPath("$.[*].enfants").value(hasItem(DEFAULT_ENFANTS.toString())))
            .andExpect(jsonPath("$.[*].handicap").value(hasItem(DEFAULT_HANDICAP.toString())))
            .andExpect(jsonPath("$.[*].typeHandicap").value(hasItem(DEFAULT_TYPE_HANDICAP)))
            .andExpect(jsonPath("$.[*].activiteSportive").value(hasItem(DEFAULT_ACTIVITE_SPORTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].typeActiviteSportive").value(hasItem(DEFAULT_TYPE_ACTIVITE_SPORTIVE)))
            .andExpect(jsonPath("$.[*].decede").value(hasItem(DEFAULT_DECEDE.booleanValue())))
            .andExpect(jsonPath("$.[*].causeDeces").value(hasItem(DEFAULT_CAUSE_DECES)))
            .andExpect(jsonPath("$.[*].dateDeces").value(hasItem(DEFAULT_DATE_DECES.toString())));
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
            .andExpect(jsonPath("$.degreParenteCasSimilaires").value(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES.toString()))
            .andExpect(jsonPath("$.decesSyndromeHemorragique").value(DEFAULT_DECES_SYNDROME_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.nombreDeces").value(DEFAULT_NOMBRE_DECES))
            .andExpect(jsonPath("$.formeHemophilie").value(DEFAULT_FORME_HEMOPHILIE.toString()))
            .andExpect(jsonPath("$.fratrieNbFreres").value(DEFAULT_FRATRIE_NB_FRERES))
            .andExpect(jsonPath("$.fratrieNbSoeurs").value(DEFAULT_FRATRIE_NB_SOEURS))
            .andExpect(jsonPath("$.circonstancesDecouverte").value(DEFAULT_CIRCONSTANCES_DECOUVERTE.toString()))
            .andExpect(jsonPath("$.cutaneoMuqueux").value(DEFAULT_CUTANEO_MUQUEUX.booleanValue()))
            .andExpect(jsonPath("$.hemarthrose").value(DEFAULT_HEMARTHROSE.booleanValue()))
            .andExpect(jsonPath("$.hematomePostTraumatique").value(DEFAULT_HEMATOME_POST_TRAUMATIQUE.booleanValue()))
            .andExpect(jsonPath("$.plaiePostTraumatique").value(DEFAULT_PLAIE_POST_TRAUMATIQUE.booleanValue()))
            .andExpect(jsonPath("$.hemorragiePostCirconcision").value(DEFAULT_HEMORRAGIE_POST_CIRCONCISION.booleanValue()))
            .andExpect(jsonPath("$.hemorragiePostChirurgie").value(DEFAULT_HEMORRAGIE_POST_CHIRURGIE.booleanValue()))
            .andExpect(jsonPath("$.hemorragieCerebrale").value(DEFAULT_HEMORRAGIE_CEREBRALE.booleanValue()))
            .andExpect(jsonPath("$.hemorragieDigestive").value(DEFAULT_HEMORRAGIE_DIGESTIVE.booleanValue()))
            .andExpect(jsonPath("$.datePremierSigneClinique").value(DEFAULT_DATE_PREMIER_SIGNE_CLINIQUE.toString()))
            .andExpect(jsonPath("$.ageDiagnosticJours").value(DEFAULT_AGE_DIAGNOSTIC_JOURS))
            .andExpect(jsonPath("$.ageDiagnosticMois").value(DEFAULT_AGE_DIAGNOSTIC_MOIS))
            .andExpect(jsonPath("$.ageDiagnosticAnnees").value(DEFAULT_AGE_DIAGNOSTIC_ANNEES))
            .andExpect(jsonPath("$.dateTestConfirmation").value(DEFAULT_DATE_TEST_CONFIRMATION.toString()))
            .andExpect(jsonPath("$.hb").value(DEFAULT_HB.doubleValue()))
            .andExpect(jsonPath("$.ht").value(DEFAULT_HT.doubleValue()))
            .andExpect(jsonPath("$.plaquettes").value(DEFAULT_PLAQUETTES))
            .andExpect(jsonPath("$.tp").value(DEFAULT_TP.doubleValue()))
            .andExpect(jsonPath("$.fibrinogene").value(DEFAULT_FIBRINOGENE.doubleValue()))
            .andExpect(jsonPath("$.tcaMT").value(DEFAULT_TCA_MT.doubleValue()))
            .andExpect(jsonPath("$.tcaMTTotal").value(DEFAULT_TCA_MT_TOTAL.doubleValue()))
            .andExpect(jsonPath("$.tcaMT2h").value(DEFAULT_TCA_MT_2_H.doubleValue()))
            .andExpect(jsonPath("$.tcaTemoin2h").value(DEFAULT_TCA_TEMOIN_2_H.doubleValue()))
            .andExpect(jsonPath("$.ts").value(DEFAULT_TS.toString()))
            .andExpect(jsonPath("$.pfa").value(DEFAULT_PFA.toString()))
            .andExpect(jsonPath("$.dosageFacteurVIII").value(DEFAULT_DOSAGE_FACTEUR_VIII.doubleValue()))
            .andExpect(jsonPath("$.dosageFacteurIX").value(DEFAULT_DOSAGE_FACTEUR_IX.doubleValue()))
            .andExpect(jsonPath("$.dosageFacteurVWActivite").value(DEFAULT_DOSAGE_FACTEUR_VW_ACTIVITE.doubleValue()))
            .andExpect(jsonPath("$.dosageFacteurVWAntigene").value(DEFAULT_DOSAGE_FACTEUR_VW_ANTIGENE.doubleValue()))
            .andExpect(jsonPath("$.formeClinique").value(DEFAULT_FORME_CLINIQUE.toString()))
            .andExpect(jsonPath("$.severite").value(DEFAULT_SEVERITE.toString()))
            .andExpect(jsonPath("$.explorationMoleculaire").value(DEFAULT_EXPLORATION_MOLECULAIRE.toString()))
            .andExpect(jsonPath("$.inversionIntron22").value(DEFAULT_INVERSION_INTRON_22.booleanValue()))
            .andExpect(jsonPath("$.autreExplorationMoleculaire").value(DEFAULT_AUTRE_EXPLORATION_MOLECULAIRE))
            .andExpect(jsonPath("$.hemarthroseMC").value(DEFAULT_HEMARTHROSE_MC.toString()))
            .andExpect(jsonPath("$.hemarthroseSiege").value(DEFAULT_HEMARTHROSE_SIEGE))
            .andExpect(jsonPath("$.hemarthroseType").value(DEFAULT_HEMARTHROSE_TYPE.toString()))
            .andExpect(jsonPath("$.hemarthroseFrequenceAn").value(DEFAULT_HEMARTHROSE_FREQUENCE_AN))
            .andExpect(jsonPath("$.hematomeSuperficiel").value(DEFAULT_HEMATOME_SUPERFICIEL.toString()))
            .andExpect(jsonPath("$.hematomeSuperficielType").value(DEFAULT_HEMATOME_SUPERFICIEL_TYPE.toString()))
            .andExpect(jsonPath("$.hematomeSuperficielSiege").value(DEFAULT_HEMATOME_SUPERFICIEL_SIEGE))
            .andExpect(jsonPath("$.hematomePsoas").value(DEFAULT_HEMATOME_PSOAS.toString()))
            .andExpect(jsonPath("$.hematomePsoasType").value(DEFAULT_HEMATOME_PSOAS_TYPE.toString()))
            .andExpect(jsonPath("$.hematomePsoasRecidive").value(DEFAULT_HEMATOME_PSOAS_RECIDIVE.toString()))
            .andExpect(jsonPath("$.hemorragiesCutaneoMuqueuses").value(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES.toString()))
            .andExpect(jsonPath("$.hemorragiesCutaneoMuqueusesTypes").value(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES_TYPES.toString()))
            .andExpect(jsonPath("$.hemorragiesCutaneoMuqueusesFrequenceAn").value(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES_FREQUENCE_AN))
            .andExpect(jsonPath("$.hemorragieVisceres").value(DEFAULT_HEMORRAGIE_VISCERES.toString()))
            .andExpect(jsonPath("$.hemorragieVisceresType").value(DEFAULT_HEMORRAGIE_VISCERES_TYPE.toString()))
            .andExpect(jsonPath("$.hemorragieVisceresExploration").value(DEFAULT_HEMORRAGIE_VISCERES_EXPLORATION.toString()))
            .andExpect(jsonPath("$.hemorragieVisceresExamen").value(DEFAULT_HEMORRAGIE_VISCERES_EXAMEN))
            .andExpect(jsonPath("$.saignementSNC").value(DEFAULT_SAIGNEMENT_SNC.toString()))
            .andExpect(jsonPath("$.saignementSNCEvolution").value(DEFAULT_SAIGNEMENT_SNC_EVOLUTION.toString()))
            .andExpect(jsonPath("$.circoncision").value(DEFAULT_CIRCONCISION.toString()))
            .andExpect(jsonPath("$.circoncisionPreparation").value(DEFAULT_CIRCONCISION_PREPARATION.toString()))
            .andExpect(jsonPath("$.circoncisionSuiteOperatoire").value(DEFAULT_CIRCONCISION_SUITE_OPERATOIRE.toString()))
            .andExpect(jsonPath("$.circoncisionAge").value(DEFAULT_CIRCONCISION_AGE))
            .andExpect(jsonPath("$.avulsionDentaire").value(DEFAULT_AVULSION_DENTAIRE.toString()))
            .andExpect(jsonPath("$.avulsionDentairePreparation").value(DEFAULT_AVULSION_DENTAIRE_PREPARATION.toString()))
            .andExpect(jsonPath("$.interventionChirurgicale").value(DEFAULT_INTERVENTION_CHIRURGICALE.toString()))
            .andExpect(jsonPath("$.interventionChirurgicaleType").value(DEFAULT_INTERVENTION_CHIRURGICALE_TYPE))
            .andExpect(jsonPath("$.accidentsHemorragiquesGraves").value(DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES.toString()))
            .andExpect(jsonPath("$.accidentsHemorragiquesGravesTypes").value(DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES_TYPES.toString()))
            .andExpect(jsonPath("$.priseEnChargeTherapeutique").value(DEFAULT_PRISE_EN_CHARGE_THERAPEUTIQUE.toString()))
            .andExpect(jsonPath("$.causePriseEnCharge").value(DEFAULT_CAUSE_PRISE_EN_CHARGE))
            .andExpect(jsonPath("$.prophylaxieDoseFrequence").value(DEFAULT_PROPHYLAXIE_DOSE_FREQUENCE))
            .andExpect(jsonPath("$.dateDebutProphylaxie").value(DEFAULT_DATE_DEBUT_PROPHYLAXIE.toString()))
            .andExpect(jsonPath("$.injectionType").value(DEFAULT_INJECTION_TYPE.toString()))
            .andExpect(jsonPath("$.modaliteTraitement").value(DEFAULT_MODALITE_TRAITEMENT.toString()))
            .andExpect(jsonPath("$.typeTraitementSubstitutif").value(DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF))
            .andExpect(jsonPath("$.agePremiereSubstitution").value(DEFAULT_AGE_PREMIERE_SUBSTITUTION))
            .andExpect(jsonPath("$.antecedentsTraitementPSL").value(DEFAULT_ANTECEDENTS_TRAITEMENT_PSL.toString()))
            .andExpect(jsonPath("$.plasmaFraisCongele").value(DEFAULT_PLASMA_FRAIS_CONGELE.toString()))
            .andExpect(jsonPath("$.cryoprecipite").value(DEFAULT_CRYOPRECIPITE.toString()))
            .andExpect(jsonPath("$.orthopediques").value(DEFAULT_ORTHOPEDIQUES.toString()))
            .andExpect(jsonPath("$.synovite").value(DEFAULT_SYNOVITE.booleanValue()))
            .andExpect(jsonPath("$.arthropathieHemophiliqueChronique").value(DEFAULT_ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE.booleanValue()))
            .andExpect(jsonPath("$.pseudotumeurs").value(DEFAULT_PSEUDOTUMEURS.booleanValue()))
            .andExpect(jsonPath("$.fractures").value(DEFAULT_FRACTURES.booleanValue()))
            .andExpect(jsonPath("$.coudeDroit").value(DEFAULT_COUDE_DROIT.booleanValue()))
            .andExpect(jsonPath("$.coudeGauche").value(DEFAULT_COUDE_GAUCHE.booleanValue()))
            .andExpect(jsonPath("$.genouDroit").value(DEFAULT_GENOU_DROIT.booleanValue()))
            .andExpect(jsonPath("$.genouGauche").value(DEFAULT_GENOU_GAUCHE.booleanValue()))
            .andExpect(jsonPath("$.chevilleDroite").value(DEFAULT_CHEVILLE_DROITE.booleanValue()))
            .andExpect(jsonPath("$.chevilleGauche").value(DEFAULT_CHEVILLE_GAUCHE.booleanValue()))
            .andExpect(jsonPath("$.epauleDroite").value(DEFAULT_EPAULE_DROITE.booleanValue()))
            .andExpect(jsonPath("$.epauleGauche").value(DEFAULT_EPAULE_GAUCHE.booleanValue()))
            .andExpect(jsonPath("$.poignetDroit").value(DEFAULT_POIGNET_DROIT.booleanValue()))
            .andExpect(jsonPath("$.poignetGauche").value(DEFAULT_POIGNET_GAUCHE.booleanValue()))
            .andExpect(jsonPath("$.hancheDroite").value(DEFAULT_HANCHE_DROITE.booleanValue()))
            .andExpect(jsonPath("$.hancheGauche").value(DEFAULT_HANCHE_GAUCHE.booleanValue()))
            .andExpect(jsonPath("$.pecTherapeutique").value(DEFAULT_PEC_THERAPEUTIQUE.toString()))
            .andExpect(jsonPath("$.inhibiteurs").value(DEFAULT_INHIBITEURS.toString()))
            .andExpect(jsonPath("$.tauxInhibiteur").value(DEFAULT_TAUX_INHIBITEUR.toString()))
            .andExpect(jsonPath("$.testRecuperationFAH").value(DEFAULT_TEST_RECUPERATION_FAH.booleanValue()))
            .andExpect(jsonPath("$.dateTestRecuperation").value(DEFAULT_DATE_TEST_RECUPERATION.toString()))
            .andExpect(jsonPath("$.tauxRecuperation").value(DEFAULT_TAUX_RECUPERATION.toString()))
            .andExpect(jsonPath("$.antiFAHNonNeutralisant").value(DEFAULT_ANTI_FAH_NON_NEUTRALISANT.booleanValue()))
            .andExpect(jsonPath("$.antiFAHNegatif").value(DEFAULT_ANTI_FAH_NEGATIF.booleanValue()))
            .andExpect(jsonPath("$.rechercheACC").value(DEFAULT_RECHERCHE_ACC.toString()))
            .andExpect(jsonPath("$.augmenterDoseFacteur").value(DEFAULT_AUGMENTER_DOSE_FACTEUR.booleanValue()))
            .andExpect(jsonPath("$.novoseven").value(DEFAULT_NOVOSEVEN.booleanValue()))
            .andExpect(jsonPath("$.feiba").value(DEFAULT_FEIBA.booleanValue()))
            .andExpect(jsonPath("$.corticotherapie").value(DEFAULT_CORTICOTHERAPIE.booleanValue()))
            .andExpect(jsonPath("$.immurel").value(DEFAULT_IMMUREL.booleanValue()))
            .andExpect(jsonPath("$.iti").value(DEFAULT_ITI.booleanValue()))
            .andExpect(jsonPath("$.posologieITI").value(DEFAULT_POSOLOGIE_ITI))
            .andExpect(jsonPath("$.dureeITI").value(DEFAULT_DUREE_ITI))
            .andExpect(jsonPath("$.observanceITI").value(DEFAULT_OBSERVANCE_ITI.toString()))
            .andExpect(jsonPath("$.causeNonObservance").value(DEFAULT_CAUSE_NON_OBSERVANCE))
            .andExpect(jsonPath("$.evolutionInhibiteur").value(DEFAULT_EVOLUTION_INHIBITEUR.toString()))
            .andExpect(jsonPath("$.prophylaxieHemlibra").value(DEFAULT_PROPHYLAXIE_HEMLIBRA.booleanValue()))
            .andExpect(jsonPath("$.dateDebutHemlibra").value(DEFAULT_DATE_DEBUT_HEMLIBRA.toString()))
            .andExpect(jsonPath("$.schemaTherapeutique").value(DEFAULT_SCHEMA_THERAPEUTIQUE.toString()))
            .andExpect(jsonPath("$.efficaciteHemlibra").value(DEFAULT_EFFICACITE_HEMLIBRA))
            .andExpect(jsonPath("$.serologieHBC").value(DEFAULT_SEROLOGIE_HBC.toString()))
            .andExpect(jsonPath("$.serologieHVC").value(DEFAULT_SEROLOGIE_HVC.toString()))
            .andExpect(jsonPath("$.dateDecouverteVHC").value(DEFAULT_DATE_DECOUVERTE_VHC.toString()))
            .andExpect(jsonPath("$.chargeViraleVHC").value(DEFAULT_CHARGE_VIRALE_VHC))
            .andExpect(jsonPath("$.traitementVHC").value(DEFAULT_TRAITEMENT_VHC.toString()))
            .andExpect(jsonPath("$.evolutionVHC").value(DEFAULT_EVOLUTION_VHC.toString()))
            .andExpect(jsonPath("$.serologieHIV").value(DEFAULT_SEROLOGIE_HIV.toString()))
            .andExpect(jsonPath("$.vieSociale").value(DEFAULT_VIE_SOCIALE.toString()))
            .andExpect(jsonPath("$.marie").value(DEFAULT_MARIE.toString()))
            .andExpect(jsonPath("$.enfants").value(DEFAULT_ENFANTS.toString()))
            .andExpect(jsonPath("$.handicap").value(DEFAULT_HANDICAP.toString()))
            .andExpect(jsonPath("$.typeHandicap").value(DEFAULT_TYPE_HANDICAP))
            .andExpect(jsonPath("$.activiteSportive").value(DEFAULT_ACTIVITE_SPORTIVE.booleanValue()))
            .andExpect(jsonPath("$.typeActiviteSportive").value(DEFAULT_TYPE_ACTIVITE_SPORTIVE))
            .andExpect(jsonPath("$.decede").value(DEFAULT_DECEDE.booleanValue()))
            .andExpect(jsonPath("$.causeDeces").value(DEFAULT_CAUSE_DECES))
            .andExpect(jsonPath("$.dateDeces").value(DEFAULT_DATE_DECES.toString()));
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
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .fratrieNbFreres(UPDATED_FRATRIE_NB_FRERES)
            .fratrieNbSoeurs(UPDATED_FRATRIE_NB_SOEURS)
            .circonstancesDecouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .cutaneoMuqueux(UPDATED_CUTANEO_MUQUEUX)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .hematomePostTraumatique(UPDATED_HEMATOME_POST_TRAUMATIQUE)
            .plaiePostTraumatique(UPDATED_PLAIE_POST_TRAUMATIQUE)
            .hemorragiePostCirconcision(UPDATED_HEMORRAGIE_POST_CIRCONCISION)
            .hemorragiePostChirurgie(UPDATED_HEMORRAGIE_POST_CHIRURGIE)
            .hemorragieCerebrale(UPDATED_HEMORRAGIE_CEREBRALE)
            .hemorragieDigestive(UPDATED_HEMORRAGIE_DIGESTIVE)
            .datePremierSigneClinique(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE)
            .ageDiagnosticJours(UPDATED_AGE_DIAGNOSTIC_JOURS)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnees(UPDATED_AGE_DIAGNOSTIC_ANNEES)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hb(UPDATED_HB)
            .ht(UPDATED_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMTTotal(UPDATED_TCA_MT_TOTAL)
            .tcaMT2h(UPDATED_TCA_MT_2_H)
            .tcaTemoin2h(UPDATED_TCA_TEMOIN_2_H)
            .ts(UPDATED_TS)
            .pfa(UPDATED_PFA)
            .dosageFacteurVIII(UPDATED_DOSAGE_FACTEUR_VIII)
            .dosageFacteurIX(UPDATED_DOSAGE_FACTEUR_IX)
            .dosageFacteurVWActivite(UPDATED_DOSAGE_FACTEUR_VW_ACTIVITE)
            .dosageFacteurVWAntigene(UPDATED_DOSAGE_FACTEUR_VW_ANTIGENE)
            .formeClinique(UPDATED_FORME_CLINIQUE)
            .severite(UPDATED_SEVERITE)
            .explorationMoleculaire(UPDATED_EXPLORATION_MOLECULAIRE)
            .inversionIntron22(UPDATED_INVERSION_INTRON_22)
            .autreExplorationMoleculaire(UPDATED_AUTRE_EXPLORATION_MOLECULAIRE)
            .hemarthroseMC(UPDATED_HEMARTHROSE_MC)
            .hemarthroseSiege(UPDATED_HEMARTHROSE_SIEGE)
            .hemarthroseType(UPDATED_HEMARTHROSE_TYPE)
            .hemarthroseFrequenceAn(UPDATED_HEMARTHROSE_FREQUENCE_AN)
            .hematomeSuperficiel(UPDATED_HEMATOME_SUPERFICIEL)
            .hematomeSuperficielType(UPDATED_HEMATOME_SUPERFICIEL_TYPE)
            .hematomeSuperficielSiege(UPDATED_HEMATOME_SUPERFICIEL_SIEGE)
            .hematomePsoas(UPDATED_HEMATOME_PSOAS)
            .hematomePsoasType(UPDATED_HEMATOME_PSOAS_TYPE)
            .hematomePsoasRecidive(UPDATED_HEMATOME_PSOAS_RECIDIVE)
            .hemorragiesCutaneoMuqueuses(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .hemorragiesCutaneoMuqueusesTypes(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES_TYPES)
            .hemorragiesCutaneoMuqueusesFrequenceAn(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES_FREQUENCE_AN)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hemorragieVisceresType(UPDATED_HEMORRAGIE_VISCERES_TYPE)
            .hemorragieVisceresExploration(UPDATED_HEMORRAGIE_VISCERES_EXPLORATION)
            .hemorragieVisceresExamen(UPDATED_HEMORRAGIE_VISCERES_EXAMEN)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .saignementSNCEvolution(UPDATED_SAIGNEMENT_SNC_EVOLUTION)
            .circoncision(UPDATED_CIRCONCISION)
            .circoncisionPreparation(UPDATED_CIRCONCISION_PREPARATION)
            .circoncisionSuiteOperatoire(UPDATED_CIRCONCISION_SUITE_OPERATOIRE)
            .circoncisionAge(UPDATED_CIRCONCISION_AGE)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .avulsionDentairePreparation(UPDATED_AVULSION_DENTAIRE_PREPARATION)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .interventionChirurgicaleType(UPDATED_INTERVENTION_CHIRURGICALE_TYPE)
            .accidentsHemorragiquesGraves(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .accidentsHemorragiquesGravesTypes(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES_TYPES)
            .priseEnChargeTherapeutique(UPDATED_PRISE_EN_CHARGE_THERAPEUTIQUE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .prophylaxieDoseFrequence(UPDATED_PROPHYLAXIE_DOSE_FREQUENCE)
            .dateDebutProphylaxie(UPDATED_DATE_DEBUT_PROPHYLAXIE)
            .injectionType(UPDATED_INJECTION_TYPE)
            .modaliteTraitement(UPDATED_MODALITE_TRAITEMENT)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .agePremiereSubstitution(UPDATED_AGE_PREMIERE_SUBSTITUTION)
            .antecedentsTraitementPSL(UPDATED_ANTECEDENTS_TRAITEMENT_PSL)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .orthopediques(UPDATED_ORTHOPEDIQUES)
            .synovite(UPDATED_SYNOVITE)
            .arthropathieHemophiliqueChronique(UPDATED_ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE)
            .pseudotumeurs(UPDATED_PSEUDOTUMEURS)
            .fractures(UPDATED_FRACTURES)
            .coudeDroit(UPDATED_COUDE_DROIT)
            .coudeGauche(UPDATED_COUDE_GAUCHE)
            .genouDroit(UPDATED_GENOU_DROIT)
            .genouGauche(UPDATED_GENOU_GAUCHE)
            .chevilleDroite(UPDATED_CHEVILLE_DROITE)
            .chevilleGauche(UPDATED_CHEVILLE_GAUCHE)
            .epauleDroite(UPDATED_EPAULE_DROITE)
            .epauleGauche(UPDATED_EPAULE_GAUCHE)
            .poignetDroit(UPDATED_POIGNET_DROIT)
            .poignetGauche(UPDATED_POIGNET_GAUCHE)
            .hancheDroite(UPDATED_HANCHE_DROITE)
            .hancheGauche(UPDATED_HANCHE_GAUCHE)
            .pecTherapeutique(UPDATED_PEC_THERAPEUTIQUE)
            .inhibiteurs(UPDATED_INHIBITEURS)
            .tauxInhibiteur(UPDATED_TAUX_INHIBITEUR)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .dateTestRecuperation(UPDATED_DATE_TEST_RECUPERATION)
            .tauxRecuperation(UPDATED_TAUX_RECUPERATION)
            .antiFAHNonNeutralisant(UPDATED_ANTI_FAH_NON_NEUTRALISANT)
            .antiFAHNegatif(UPDATED_ANTI_FAH_NEGATIF)
            .rechercheACC(UPDATED_RECHERCHE_ACC)
            .augmenterDoseFacteur(UPDATED_AUGMENTER_DOSE_FACTEUR)
            .novoseven(UPDATED_NOVOSEVEN)
            .feiba(UPDATED_FEIBA)
            .corticotherapie(UPDATED_CORTICOTHERAPIE)
            .immurel(UPDATED_IMMUREL)
            .iti(UPDATED_ITI)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .observanceITI(UPDATED_OBSERVANCE_ITI)
            .causeNonObservance(UPDATED_CAUSE_NON_OBSERVANCE)
            .evolutionInhibiteur(UPDATED_EVOLUTION_INHIBITEUR)
            .prophylaxieHemlibra(UPDATED_PROPHYLAXIE_HEMLIBRA)
            .dateDebutHemlibra(UPDATED_DATE_DEBUT_HEMLIBRA)
            .schemaTherapeutique(UPDATED_SCHEMA_THERAPEUTIQUE)
            .efficaciteHemlibra(UPDATED_EFFICACITE_HEMLIBRA)
            .serologieHBC(UPDATED_SEROLOGIE_HBC)
            .serologieHVC(UPDATED_SEROLOGIE_HVC)
            .dateDecouverteVHC(UPDATED_DATE_DECOUVERTE_VHC)
            .chargeViraleVHC(UPDATED_CHARGE_VIRALE_VHC)
            .traitementVHC(UPDATED_TRAITEMENT_VHC)
            .evolutionVHC(UPDATED_EVOLUTION_VHC)
            .serologieHIV(UPDATED_SEROLOGIE_HIV)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .handicap(UPDATED_HANDICAP)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .activiteSportive(UPDATED_ACTIVITE_SPORTIVE)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .decede(UPDATED_DECEDE)
            .causeDeces(UPDATED_CAUSE_DECES)
            .dateDeces(UPDATED_DATE_DECES);
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
            .nom(UPDATED_NOM)
            .nomJeuneFille(UPDATED_NOM_JEUNE_FILLE)
            .serviceClinique(UPDATED_SERVICE_CLINIQUE)
            .autreServiceClinique(UPDATED_AUTRE_SERVICE_CLINIQUE)
            .couvertureSociale(UPDATED_COUVERTURE_SOCIALE)
            .sexe(UPDATED_SEXE)
            .origine(UPDATED_ORIGINE)
            .autreOrigine(UPDATED_AUTRE_ORIGINE)
            .nomPrenomMere(UPDATED_NOM_PRENOM_MERE)
            .medecinTraitant(UPDATED_MEDECIN_TRAITANT)
            .serviceOrigine(UPDATED_SERVICE_ORIGINE)
            .biologisteResponsableDg(UPDATED_BIOLOGISTE_RESPONSABLE_DG)
            .diagnostic(UPDATED_DIAGNOSTIC)
            .autreDiagnostic(UPDATED_AUTRE_DIAGNOSTIC)
            .consentementRegistre(UPDATED_CONSENTEMENT_REGISTRE)
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .nombreDeces(UPDATED_NOMBRE_DECES)
            .circonstancesDecouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .hemorragiePostChirurgie(UPDATED_HEMORRAGIE_POST_CHIRURGIE)
            .datePremierSigneClinique(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnees(UPDATED_AGE_DIAGNOSTIC_ANNEES)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .plaquettes(UPDATED_PLAQUETTES)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaTemoin2h(UPDATED_TCA_TEMOIN_2_H)
            .ts(UPDATED_TS)
            .dosageFacteurIX(UPDATED_DOSAGE_FACTEUR_IX)
            .dosageFacteurVWAntigene(UPDATED_DOSAGE_FACTEUR_VW_ANTIGENE)
            .severite(UPDATED_SEVERITE)
            .explorationMoleculaire(UPDATED_EXPLORATION_MOLECULAIRE)
            .hemarthroseMC(UPDATED_HEMARTHROSE_MC)
            .hemarthroseType(UPDATED_HEMARTHROSE_TYPE)
            .hemarthroseFrequenceAn(UPDATED_HEMARTHROSE_FREQUENCE_AN)
            .hematomeSuperficiel(UPDATED_HEMATOME_SUPERFICIEL)
            .hematomePsoas(UPDATED_HEMATOME_PSOAS)
            .hematomePsoasType(UPDATED_HEMATOME_PSOAS_TYPE)
            .hematomePsoasRecidive(UPDATED_HEMATOME_PSOAS_RECIDIVE)
            .hemorragiesCutaneoMuqueuses(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .hemorragiesCutaneoMuqueusesFrequenceAn(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES_FREQUENCE_AN)
            .hemorragieVisceresType(UPDATED_HEMORRAGIE_VISCERES_TYPE)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .saignementSNCEvolution(UPDATED_SAIGNEMENT_SNC_EVOLUTION)
            .circoncisionPreparation(UPDATED_CIRCONCISION_PREPARATION)
            .circoncisionSuiteOperatoire(UPDATED_CIRCONCISION_SUITE_OPERATOIRE)
            .circoncisionAge(UPDATED_CIRCONCISION_AGE)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .avulsionDentairePreparation(UPDATED_AVULSION_DENTAIRE_PREPARATION)
            .interventionChirurgicaleType(UPDATED_INTERVENTION_CHIRURGICALE_TYPE)
            .accidentsHemorragiquesGraves(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .prophylaxieDoseFrequence(UPDATED_PROPHYLAXIE_DOSE_FREQUENCE)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .agePremiereSubstitution(UPDATED_AGE_PREMIERE_SUBSTITUTION)
            .antecedentsTraitementPSL(UPDATED_ANTECEDENTS_TRAITEMENT_PSL)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .arthropathieHemophiliqueChronique(UPDATED_ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE)
            .fractures(UPDATED_FRACTURES)
            .coudeDroit(UPDATED_COUDE_DROIT)
            .genouDroit(UPDATED_GENOU_DROIT)
            .genouGauche(UPDATED_GENOU_GAUCHE)
            .chevilleGauche(UPDATED_CHEVILLE_GAUCHE)
            .poignetDroit(UPDATED_POIGNET_DROIT)
            .poignetGauche(UPDATED_POIGNET_GAUCHE)
            .hancheGauche(UPDATED_HANCHE_GAUCHE)
            .pecTherapeutique(UPDATED_PEC_THERAPEUTIQUE)
            .tauxInhibiteur(UPDATED_TAUX_INHIBITEUR)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .feiba(UPDATED_FEIBA)
            .corticotherapie(UPDATED_CORTICOTHERAPIE)
            .immurel(UPDATED_IMMUREL)
            .iti(UPDATED_ITI)
            .observanceITI(UPDATED_OBSERVANCE_ITI)
            .evolutionInhibiteur(UPDATED_EVOLUTION_INHIBITEUR)
            .dateDebutHemlibra(UPDATED_DATE_DEBUT_HEMLIBRA)
            .serologieHVC(UPDATED_SEROLOGIE_HVC)
            .dateDecouverteVHC(UPDATED_DATE_DECOUVERTE_VHC)
            .traitementVHC(UPDATED_TRAITEMENT_VHC)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .marie(UPDATED_MARIE)
            .handicap(UPDATED_HANDICAP)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .decede(UPDATED_DECEDE)
            .causeDeces(UPDATED_CAUSE_DECES)
            .dateDeces(UPDATED_DATE_DECES);

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
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .fratrieNbFreres(UPDATED_FRATRIE_NB_FRERES)
            .fratrieNbSoeurs(UPDATED_FRATRIE_NB_SOEURS)
            .circonstancesDecouverte(UPDATED_CIRCONSTANCES_DECOUVERTE)
            .cutaneoMuqueux(UPDATED_CUTANEO_MUQUEUX)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .hematomePostTraumatique(UPDATED_HEMATOME_POST_TRAUMATIQUE)
            .plaiePostTraumatique(UPDATED_PLAIE_POST_TRAUMATIQUE)
            .hemorragiePostCirconcision(UPDATED_HEMORRAGIE_POST_CIRCONCISION)
            .hemorragiePostChirurgie(UPDATED_HEMORRAGIE_POST_CHIRURGIE)
            .hemorragieCerebrale(UPDATED_HEMORRAGIE_CEREBRALE)
            .hemorragieDigestive(UPDATED_HEMORRAGIE_DIGESTIVE)
            .datePremierSigneClinique(UPDATED_DATE_PREMIER_SIGNE_CLINIQUE)
            .ageDiagnosticJours(UPDATED_AGE_DIAGNOSTIC_JOURS)
            .ageDiagnosticMois(UPDATED_AGE_DIAGNOSTIC_MOIS)
            .ageDiagnosticAnnees(UPDATED_AGE_DIAGNOSTIC_ANNEES)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hb(UPDATED_HB)
            .ht(UPDATED_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMTTotal(UPDATED_TCA_MT_TOTAL)
            .tcaMT2h(UPDATED_TCA_MT_2_H)
            .tcaTemoin2h(UPDATED_TCA_TEMOIN_2_H)
            .ts(UPDATED_TS)
            .pfa(UPDATED_PFA)
            .dosageFacteurVIII(UPDATED_DOSAGE_FACTEUR_VIII)
            .dosageFacteurIX(UPDATED_DOSAGE_FACTEUR_IX)
            .dosageFacteurVWActivite(UPDATED_DOSAGE_FACTEUR_VW_ACTIVITE)
            .dosageFacteurVWAntigene(UPDATED_DOSAGE_FACTEUR_VW_ANTIGENE)
            .formeClinique(UPDATED_FORME_CLINIQUE)
            .severite(UPDATED_SEVERITE)
            .explorationMoleculaire(UPDATED_EXPLORATION_MOLECULAIRE)
            .inversionIntron22(UPDATED_INVERSION_INTRON_22)
            .autreExplorationMoleculaire(UPDATED_AUTRE_EXPLORATION_MOLECULAIRE)
            .hemarthroseMC(UPDATED_HEMARTHROSE_MC)
            .hemarthroseSiege(UPDATED_HEMARTHROSE_SIEGE)
            .hemarthroseType(UPDATED_HEMARTHROSE_TYPE)
            .hemarthroseFrequenceAn(UPDATED_HEMARTHROSE_FREQUENCE_AN)
            .hematomeSuperficiel(UPDATED_HEMATOME_SUPERFICIEL)
            .hematomeSuperficielType(UPDATED_HEMATOME_SUPERFICIEL_TYPE)
            .hematomeSuperficielSiege(UPDATED_HEMATOME_SUPERFICIEL_SIEGE)
            .hematomePsoas(UPDATED_HEMATOME_PSOAS)
            .hematomePsoasType(UPDATED_HEMATOME_PSOAS_TYPE)
            .hematomePsoasRecidive(UPDATED_HEMATOME_PSOAS_RECIDIVE)
            .hemorragiesCutaneoMuqueuses(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .hemorragiesCutaneoMuqueusesTypes(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES_TYPES)
            .hemorragiesCutaneoMuqueusesFrequenceAn(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES_FREQUENCE_AN)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .hemorragieVisceresType(UPDATED_HEMORRAGIE_VISCERES_TYPE)
            .hemorragieVisceresExploration(UPDATED_HEMORRAGIE_VISCERES_EXPLORATION)
            .hemorragieVisceresExamen(UPDATED_HEMORRAGIE_VISCERES_EXAMEN)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .saignementSNCEvolution(UPDATED_SAIGNEMENT_SNC_EVOLUTION)
            .circoncision(UPDATED_CIRCONCISION)
            .circoncisionPreparation(UPDATED_CIRCONCISION_PREPARATION)
            .circoncisionSuiteOperatoire(UPDATED_CIRCONCISION_SUITE_OPERATOIRE)
            .circoncisionAge(UPDATED_CIRCONCISION_AGE)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .avulsionDentairePreparation(UPDATED_AVULSION_DENTAIRE_PREPARATION)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .interventionChirurgicaleType(UPDATED_INTERVENTION_CHIRURGICALE_TYPE)
            .accidentsHemorragiquesGraves(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .accidentsHemorragiquesGravesTypes(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES_TYPES)
            .priseEnChargeTherapeutique(UPDATED_PRISE_EN_CHARGE_THERAPEUTIQUE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .prophylaxieDoseFrequence(UPDATED_PROPHYLAXIE_DOSE_FREQUENCE)
            .dateDebutProphylaxie(UPDATED_DATE_DEBUT_PROPHYLAXIE)
            .injectionType(UPDATED_INJECTION_TYPE)
            .modaliteTraitement(UPDATED_MODALITE_TRAITEMENT)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .agePremiereSubstitution(UPDATED_AGE_PREMIERE_SUBSTITUTION)
            .antecedentsTraitementPSL(UPDATED_ANTECEDENTS_TRAITEMENT_PSL)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .orthopediques(UPDATED_ORTHOPEDIQUES)
            .synovite(UPDATED_SYNOVITE)
            .arthropathieHemophiliqueChronique(UPDATED_ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE)
            .pseudotumeurs(UPDATED_PSEUDOTUMEURS)
            .fractures(UPDATED_FRACTURES)
            .coudeDroit(UPDATED_COUDE_DROIT)
            .coudeGauche(UPDATED_COUDE_GAUCHE)
            .genouDroit(UPDATED_GENOU_DROIT)
            .genouGauche(UPDATED_GENOU_GAUCHE)
            .chevilleDroite(UPDATED_CHEVILLE_DROITE)
            .chevilleGauche(UPDATED_CHEVILLE_GAUCHE)
            .epauleDroite(UPDATED_EPAULE_DROITE)
            .epauleGauche(UPDATED_EPAULE_GAUCHE)
            .poignetDroit(UPDATED_POIGNET_DROIT)
            .poignetGauche(UPDATED_POIGNET_GAUCHE)
            .hancheDroite(UPDATED_HANCHE_DROITE)
            .hancheGauche(UPDATED_HANCHE_GAUCHE)
            .pecTherapeutique(UPDATED_PEC_THERAPEUTIQUE)
            .inhibiteurs(UPDATED_INHIBITEURS)
            .tauxInhibiteur(UPDATED_TAUX_INHIBITEUR)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .dateTestRecuperation(UPDATED_DATE_TEST_RECUPERATION)
            .tauxRecuperation(UPDATED_TAUX_RECUPERATION)
            .antiFAHNonNeutralisant(UPDATED_ANTI_FAH_NON_NEUTRALISANT)
            .antiFAHNegatif(UPDATED_ANTI_FAH_NEGATIF)
            .rechercheACC(UPDATED_RECHERCHE_ACC)
            .augmenterDoseFacteur(UPDATED_AUGMENTER_DOSE_FACTEUR)
            .novoseven(UPDATED_NOVOSEVEN)
            .feiba(UPDATED_FEIBA)
            .corticotherapie(UPDATED_CORTICOTHERAPIE)
            .immurel(UPDATED_IMMUREL)
            .iti(UPDATED_ITI)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .observanceITI(UPDATED_OBSERVANCE_ITI)
            .causeNonObservance(UPDATED_CAUSE_NON_OBSERVANCE)
            .evolutionInhibiteur(UPDATED_EVOLUTION_INHIBITEUR)
            .prophylaxieHemlibra(UPDATED_PROPHYLAXIE_HEMLIBRA)
            .dateDebutHemlibra(UPDATED_DATE_DEBUT_HEMLIBRA)
            .schemaTherapeutique(UPDATED_SCHEMA_THERAPEUTIQUE)
            .efficaciteHemlibra(UPDATED_EFFICACITE_HEMLIBRA)
            .serologieHBC(UPDATED_SEROLOGIE_HBC)
            .serologieHVC(UPDATED_SEROLOGIE_HVC)
            .dateDecouverteVHC(UPDATED_DATE_DECOUVERTE_VHC)
            .chargeViraleVHC(UPDATED_CHARGE_VIRALE_VHC)
            .traitementVHC(UPDATED_TRAITEMENT_VHC)
            .evolutionVHC(UPDATED_EVOLUTION_VHC)
            .serologieHIV(UPDATED_SEROLOGIE_HIV)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .handicap(UPDATED_HANDICAP)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .activiteSportive(UPDATED_ACTIVITE_SPORTIVE)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .decede(UPDATED_DECEDE)
            .causeDeces(UPDATED_CAUSE_DECES)
            .dateDeces(UPDATED_DATE_DECES);

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

package tn.med.adhes.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.med.adhes.domain.FicheHemophilieAsserts.*;
import static tn.med.adhes.web.rest.TestUtil.createUpdateProxyForBean;

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
import tn.med.adhes.IntegrationTest;
import tn.med.adhes.domain.FicheHemophilie;
import tn.med.adhes.domain.enumeration.ArticulationCible;
import tn.med.adhes.domain.enumeration.ArticulationCible;
import tn.med.adhes.domain.enumeration.CATTherapeutique;
import tn.med.adhes.domain.enumeration.CircumstanceDecouverte;
import tn.med.adhes.domain.enumeration.EvolutionInhibiteur;
import tn.med.adhes.domain.enumeration.EvolutionSNC;
import tn.med.adhes.domain.enumeration.EvolutionVHC;
import tn.med.adhes.domain.enumeration.FormeClinique;
import tn.med.adhes.domain.enumeration.FormeHemophilie;
import tn.med.adhes.domain.enumeration.InjectionType;
import tn.med.adhes.domain.enumeration.Observance;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.OuiNonNP;
import tn.med.adhes.domain.enumeration.PECTherapeutique;
import tn.med.adhes.domain.enumeration.PriseEnChargeType;
import tn.med.adhes.domain.enumeration.RechercheACC;
import tn.med.adhes.domain.enumeration.SchemaTherapeutique;
import tn.med.adhes.domain.enumeration.Serologie;
import tn.med.adhes.domain.enumeration.Serologie;
import tn.med.adhes.domain.enumeration.Serologie;
import tn.med.adhes.domain.enumeration.Severite;
import tn.med.adhes.domain.enumeration.SuiteOperatoire;
import tn.med.adhes.domain.enumeration.TauxInhibiteur;
import tn.med.adhes.domain.enumeration.TauxRecuperation;
import tn.med.adhes.domain.enumeration.TestStatus;
import tn.med.adhes.domain.enumeration.TestStatus;
import tn.med.adhes.domain.enumeration.TestStatus;
import tn.med.adhes.domain.enumeration.TraitementType;
import tn.med.adhes.domain.enumeration.TypeComplicationsOrthopediques;
import tn.med.adhes.domain.enumeration.TypeManifestation;
import tn.med.adhes.domain.enumeration.TypePreparation;
import tn.med.adhes.domain.enumeration.TypePreparation;
import tn.med.adhes.domain.enumeration.VieSociale;
import tn.med.adhes.repository.FicheHemophilieRepository;
import tn.med.adhes.service.dto.FicheHemophilieDTO;
import tn.med.adhes.service.mapper.FicheHemophilieMapper;

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

    private static final OuiNonNP DEFAULT_PARENTS_CONSANGUINS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PARENTS_CONSANGUINS = OuiNonNP.NON;

    private static final String DEFAULT_DEGRE_PARENTE_CONSANGUINS = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CONSANGUINS = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CAS_SIMILAIRES_FAMILLE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CAS_SIMILAIRES_FAMILLE = OuiNonNP.NON;

    private static final Integer DEFAULT_NBRE_CAS_SIMILAIRES = 1;
    private static final Integer UPDATED_NBRE_CAS_SIMILAIRES = 2;

    private static final String DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES = "AAAAAAAAAA";
    private static final String UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_CAS_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CAS_DECES_SYNDROME_HEMORRAGIQUE = OuiNonNP.NON;

    private static final Integer DEFAULT_NBRE_CAS_DECES = 1;
    private static final Integer UPDATED_NBRE_CAS_DECES = 2;

    private static final FormeHemophilie DEFAULT_FORME_HEMOPHILIE = FormeHemophilie.SPORADIQUE;
    private static final FormeHemophilie UPDATED_FORME_HEMOPHILIE = FormeHemophilie.HEREDITAIRE;

    private static final Integer DEFAULT_NBRE_FRERES = 1;
    private static final Integer UPDATED_NBRE_FRERES = 2;

    private static final Integer DEFAULT_NBRE_SOEURS = 1;
    private static final Integer UPDATED_NBRE_SOEURS = 2;

    private static final CircumstanceDecouverte DEFAULT_CIRCONSTANCE_DECOUVERTE = CircumstanceDecouverte.FORTUITE;
    private static final CircumstanceDecouverte UPDATED_CIRCONSTANCE_DECOUVERTE = CircumstanceDecouverte.FAMILIALE;

    private static final LocalDate DEFAULT_DATE_TEST_CONFIRMATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_TEST_CONFIRMATION = LocalDate.now(ZoneId.systemDefault());

    private static final Double DEFAULT_HEMOGRAMME_HB = 1D;
    private static final Double UPDATED_HEMOGRAMME_HB = 2D;

    private static final Double DEFAULT_HEMOGRAMME_HT = 1D;
    private static final Double UPDATED_HEMOGRAMME_HT = 2D;

    private static final Integer DEFAULT_PLAQUETTES = 1;
    private static final Integer UPDATED_PLAQUETTES = 2;

    private static final Double DEFAULT_TP = 1D;
    private static final Double UPDATED_TP = 2D;

    private static final Double DEFAULT_FIBRINOGENE = 1D;
    private static final Double UPDATED_FIBRINOGENE = 2D;

    private static final Double DEFAULT_TCA_MT = 1D;
    private static final Double UPDATED_TCA_MT = 2D;

    private static final Double DEFAULT_TCA_MT_2_H = 1D;
    private static final Double UPDATED_TCA_MT_2_H = 2D;

    private static final Double DEFAULT_TCA_TEMOIN_2_H = 1D;
    private static final Double UPDATED_TCA_TEMOIN_2_H = 2D;

    private static final TestStatus DEFAULT_TS = TestStatus.FAIT;
    private static final TestStatus UPDATED_TS = TestStatus.NON_FAIT;

    private static final TestStatus DEFAULT_PFA = TestStatus.FAIT;
    private static final TestStatus UPDATED_PFA = TestStatus.NON_FAIT;

    private static final Double DEFAULT_DOSAGE_FACTEUR_VIII = 1D;
    private static final Double UPDATED_DOSAGE_FACTEUR_VIII = 2D;

    private static final Double DEFAULT_DOSAGE_FACTEUR_IX = 1D;
    private static final Double UPDATED_DOSAGE_FACTEUR_IX = 2D;

    private static final Double DEFAULT_DOSAGE_FACTEUR_VW_ACTIVITE = 1D;
    private static final Double UPDATED_DOSAGE_FACTEUR_VW_ACTIVITE = 2D;

    private static final Double DEFAULT_DOSAGE_FACTEUR_VW_ANTIGENE = 1D;
    private static final Double UPDATED_DOSAGE_FACTEUR_VW_ANTIGENE = 2D;

    private static final FormeClinique DEFAULT_FORME_CLINIQUE = FormeClinique.A;
    private static final FormeClinique UPDATED_FORME_CLINIQUE = FormeClinique.B;

    private static final Severite DEFAULT_SEVERITE = Severite.SEVERE;
    private static final Severite UPDATED_SEVERITE = Severite.MODEREE;

    private static final String DEFAULT_EXPLORATION_MOLECULAIRE = "AAAAAAAAAA";
    private static final String UPDATED_EXPLORATION_MOLECULAIRE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_HEMARTHROSE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMARTHROSE = OuiNonNP.NON;

    private static final ArticulationCible DEFAULT_SIEGE_HEMARTHROSE = ArticulationCible.GENOU_GAUCHE;
    private static final ArticulationCible UPDATED_SIEGE_HEMARTHROSE = ArticulationCible.GENOU_DROIT;

    private static final TypeManifestation DEFAULT_CIRCONSTANCE_HEMARTHROSE = TypeManifestation.SPONTANE;
    private static final TypeManifestation UPDATED_CIRCONSTANCE_HEMARTHROSE = TypeManifestation.PROVOQUE;

    private static final Integer DEFAULT_FREQUENCE_HEMARTHROSE = 1;
    private static final Integer UPDATED_FREQUENCE_HEMARTHROSE = 2;

    private static final OuiNonNP DEFAULT_HEMATOME_SUPERFICIEL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMATOME_SUPERFICIEL = OuiNonNP.NON;

    private static final String DEFAULT_CIRCONSTANCE_HEMATOME_SUPERFICIEL = "AAAAAAAAAA";
    private static final String UPDATED_CIRCONSTANCE_HEMATOME_SUPERFICIEL = "BBBBBBBBBB";

    private static final String DEFAULT_SIEGE_HEMATOME_SUPERFICIEL = "AAAAAAAAAA";
    private static final String UPDATED_SIEGE_HEMATOME_SUPERFICIEL = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_HEMATOME_PSOAS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMATOME_PSOAS = OuiNonNP.NON;

    private static final String DEFAULT_CIRCONSTANCE_HEMATOME_PSOAS = "AAAAAAAAAA";
    private static final String UPDATED_CIRCONSTANCE_HEMATOME_PSOAS = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_RECIDIVE_HEMATOME_PSOAS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_RECIDIVE_HEMATOME_PSOAS = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES = "BBBBBBBBBB";

    private static final Integer DEFAULT_FREQUENCE_HEMORRAGIES_CUTANEO_MUQUEUSES = 1;
    private static final Integer UPDATED_FREQUENCE_HEMORRAGIES_CUTANEO_MUQUEUSES = 2;

    private static final OuiNonNP DEFAULT_HEMORRAGIE_VISCERES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_HEMORRAGIE_VISCERES = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_HEMORRAGIE_VISCERES = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HEMORRAGIE_VISCERES = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_EXPLORATION_HEMORRAGIE_VISCERES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_EXPLORATION_HEMORRAGIE_VISCERES = OuiNonNP.NON;

    private static final String DEFAULT_EXAMEN_LESION_HEMORRAGIE_VISCERES = "AAAAAAAAAA";
    private static final String UPDATED_EXAMEN_LESION_HEMORRAGIE_VISCERES = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_SAIGNEMENT_SNC = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_SAIGNEMENT_SNC = OuiNonNP.NON;

    private static final EvolutionSNC DEFAULT_EVOLUTION_SNC = EvolutionSNC.REGRESSION;
    private static final EvolutionSNC UPDATED_EVOLUTION_SNC = EvolutionSNC.DECES;

    private static final OuiNonNP DEFAULT_CIRCONCISION = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CIRCONCISION = OuiNonNP.NON;

    private static final TypePreparation DEFAULT_TYPE_PREPARATION_CIRCONCISION = TypePreparation.PREPAREE;
    private static final TypePreparation UPDATED_TYPE_PREPARATION_CIRCONCISION = TypePreparation.NON_PREPAREE;

    private static final SuiteOperatoire DEFAULT_SUITE_OPERATOIRE_CIRCONCISION = SuiteOperatoire.SIMPLE;
    private static final SuiteOperatoire UPDATED_SUITE_OPERATOIRE_CIRCONCISION = SuiteOperatoire.HEMORRAGIQUE;

    private static final Integer DEFAULT_AGE_CIRCONCISION = 1;
    private static final Integer UPDATED_AGE_CIRCONCISION = 2;

    private static final OuiNonNP DEFAULT_AVULSION_DENTAIRE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_AVULSION_DENTAIRE = OuiNonNP.NON;

    private static final TypePreparation DEFAULT_TYPE_PREPARATION_AVULSION_DENTAIRE = TypePreparation.PREPAREE;
    private static final TypePreparation UPDATED_TYPE_PREPARATION_AVULSION_DENTAIRE = TypePreparation.NON_PREPAREE;

    private static final OuiNonNP DEFAULT_INTERVENTION_CHIRURGICALE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_INTERVENTION_CHIRURGICALE = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_INTERVENTION_CHIRURGICALE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_INTERVENTION_CHIRURGICALE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES = OuiNonNP.NON;

    private static final String DEFAULT_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES = "AAAAAAAAAA";
    private static final String UPDATED_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES = "BBBBBBBBBB";

    private static final PriseEnChargeType DEFAULT_PRISE_EN_CHARGE = PriseEnChargeType.A_LA_DEMANDE;
    private static final PriseEnChargeType UPDATED_PRISE_EN_CHARGE = PriseEnChargeType.PROPHYLAXIE;

    private static final String DEFAULT_CAUSE_PRISE_EN_CHARGE = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_PRISE_EN_CHARGE = "BBBBBBBBBB";

    private static final String DEFAULT_DOSE_PROPHYLAXIE = "AAAAAAAAAA";
    private static final String UPDATED_DOSE_PROPHYLAXIE = "BBBBBBBBBB";

    private static final Integer DEFAULT_FREQUENCE_PROPHYLAXIE = 1;
    private static final Integer UPDATED_FREQUENCE_PROPHYLAXIE = 2;

    private static final LocalDate DEFAULT_DEBUT_PROPHYLAXIE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DEBUT_PROPHYLAXIE = LocalDate.now(ZoneId.systemDefault());

    private static final InjectionType DEFAULT_MODALITE_INJECTION = InjectionType.AUTO_INJECTION;
    private static final InjectionType UPDATED_MODALITE_INJECTION = InjectionType.INJECTION_PARENT;

    private static final TraitementType DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF = TraitementType.PLASMATIQUE;
    private static final TraitementType UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF = TraitementType.RECOMBINANT;

    private static final Integer DEFAULT_AGE_1_ERE_SUBSTITUTION = 1;
    private static final Integer UPDATED_AGE_1_ERE_SUBSTITUTION = 2;

    private static final OuiNonNP DEFAULT_ANTECEDENTS_PSL = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ANTECEDENTS_PSL = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_PLASMA_FRAIS_CONGELE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PLASMA_FRAIS_CONGELE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_CRYOPRECIPITE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_CRYOPRECIPITE = OuiNonNP.NON;

    private static final OuiNonNP DEFAULT_COMPLICATIONS_ORTHOPEDIQUES = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_COMPLICATIONS_ORTHOPEDIQUES = OuiNonNP.NON;

    private static final TypeComplicationsOrthopediques DEFAULT_TYPE_COMPLICATIONS_ORTHOPEDIQUES = TypeComplicationsOrthopediques.SYNOVITE;
    private static final TypeComplicationsOrthopediques UPDATED_TYPE_COMPLICATIONS_ORTHOPEDIQUES =
        TypeComplicationsOrthopediques.ARTHROPATHIE_HEMOPHILIQUE_CHRONIQUE;

    private static final ArticulationCible DEFAULT_ARTICULATION_CIBLE = ArticulationCible.GENOU_GAUCHE;
    private static final ArticulationCible UPDATED_ARTICULATION_CIBLE = ArticulationCible.GENOU_DROIT;

    private static final PECTherapeutique DEFAULT_PEC_THERAPEUTIQUE = PECTherapeutique.RAS;
    private static final PECTherapeutique UPDATED_PEC_THERAPEUTIQUE = PECTherapeutique.SYNOVIORTHESE;

    private static final OuiNonNP DEFAULT_COMPLICATIONS_INHIBITEURS = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_COMPLICATIONS_INHIBITEURS = OuiNonNP.NON;

    private static final TauxInhibiteur DEFAULT_TAUX_INHIBITEUR = TauxInhibiteur.FAIBLE_REPONDEUR;
    private static final TauxInhibiteur UPDATED_TAUX_INHIBITEUR = TauxInhibiteur.FORT_REPONDEUR;

    private static final TestStatus DEFAULT_TEST_RECUPERATION_FAH = TestStatus.FAIT;
    private static final TestStatus UPDATED_TEST_RECUPERATION_FAH = TestStatus.NON_FAIT;

    private static final LocalDate DEFAULT_DATE_RECUPERATION_FAH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_RECUPERATION_FAH = LocalDate.now(ZoneId.systemDefault());

    private static final TauxRecuperation DEFAULT_TAUX_RECUPERATION = TauxRecuperation.NORMAL;
    private static final TauxRecuperation UPDATED_TAUX_RECUPERATION = TauxRecuperation.DIMINUE;

    private static final OuiNonNP DEFAULT_ANTI_FAH_NON_NEUTRALISANT = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ANTI_FAH_NON_NEUTRALISANT = OuiNonNP.NON;

    private static final RechercheACC DEFAULT_RECHERCHE_ACC = RechercheACC.NON_FAITE;
    private static final RechercheACC UPDATED_RECHERCHE_ACC = RechercheACC.FAITE;

    private static final CATTherapeutique DEFAULT_CAT_THERAPEUTIQUE = CATTherapeutique.AUGMENTER_DOSE_FACTEUR;
    private static final CATTherapeutique UPDATED_CAT_THERAPEUTIQUE = CATTherapeutique.NOVOSEVEN;

    private static final OuiNonNP DEFAULT_ITI = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ITI = OuiNonNP.NON;

    private static final String DEFAULT_POSOLOGIE_ITI = "AAAAAAAAAA";
    private static final String UPDATED_POSOLOGIE_ITI = "BBBBBBBBBB";

    private static final String DEFAULT_DUREE_ITI = "AAAAAAAAAA";
    private static final String UPDATED_DUREE_ITI = "BBBBBBBBBB";

    private static final Observance DEFAULT_OBSERVANCE_ITI = Observance.OUI;
    private static final Observance UPDATED_OBSERVANCE_ITI = Observance.NON;

    private static final String DEFAULT_CAUSE_NON_OBSERVANCE_ITI = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_NON_OBSERVANCE_ITI = "BBBBBBBBBB";

    private static final EvolutionInhibiteur DEFAULT_EVOLUTION_INHIBITEUR = EvolutionInhibiteur.DISPARITION;
    private static final EvolutionInhibiteur UPDATED_EVOLUTION_INHIBITEUR = EvolutionInhibiteur.PERSISTANCE;

    private static final OuiNonNP DEFAULT_PROPHYLAXIE_HEMLIBRA = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_PROPHYLAXIE_HEMLIBRA = OuiNonNP.NON;

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

    private static final OuiNonNP DEFAULT_ACTIVITE_SPORTIVE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_ACTIVITE_SPORTIVE = OuiNonNP.NON;

    private static final String DEFAULT_TYPE_ACTIVITE_SPORTIVE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ACTIVITE_SPORTIVE = "BBBBBBBBBB";

    private static final OuiNonNP DEFAULT_DECEDE = OuiNonNP.OUI;
    private static final OuiNonNP UPDATED_DECEDE = OuiNonNP.NON;

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
            .dossierNumber(DEFAULT_DOSSIER_NUMBER)
            .ordreNumber(DEFAULT_ORDRE_NUMBER)
            .indexNumber(DEFAULT_INDEX_NUMBER)
            .parentsConsanguins(DEFAULT_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(DEFAULT_DEGRE_PARENTE_CONSANGUINS)
            .casSimilairesFamille(DEFAULT_CAS_SIMILAIRES_FAMILLE)
            .nbreCasSimilaires(DEFAULT_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES)
            .casDecesSyndromeHemorragique(DEFAULT_CAS_DECES_SYNDROME_HEMORRAGIQUE)
            .nbreCasDeces(DEFAULT_NBRE_CAS_DECES)
            .formeHemophilie(DEFAULT_FORME_HEMOPHILIE)
            .nbreFreres(DEFAULT_NBRE_FRERES)
            .nbreSoeurs(DEFAULT_NBRE_SOEURS)
            .circonstanceDecouverte(DEFAULT_CIRCONSTANCE_DECOUVERTE)
            .dateTestConfirmation(DEFAULT_DATE_TEST_CONFIRMATION)
            .hemogrammeHb(DEFAULT_HEMOGRAMME_HB)
            .hemogrammeHt(DEFAULT_HEMOGRAMME_HT)
            .plaquettes(DEFAULT_PLAQUETTES)
            .tp(DEFAULT_TP)
            .fibrinogene(DEFAULT_FIBRINOGENE)
            .tcaMT(DEFAULT_TCA_MT)
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
            .hemarthrose(DEFAULT_HEMARTHROSE)
            .siegeHemarthrose(DEFAULT_SIEGE_HEMARTHROSE)
            .circonstanceHemarthrose(DEFAULT_CIRCONSTANCE_HEMARTHROSE)
            .frequenceHemarthrose(DEFAULT_FREQUENCE_HEMARTHROSE)
            .hematomeSuperficiel(DEFAULT_HEMATOME_SUPERFICIEL)
            .circonstanceHematomeSuperficiel(DEFAULT_CIRCONSTANCE_HEMATOME_SUPERFICIEL)
            .siegeHematomeSuperficiel(DEFAULT_SIEGE_HEMATOME_SUPERFICIEL)
            .hematomePsoas(DEFAULT_HEMATOME_PSOAS)
            .circonstanceHematomePsoas(DEFAULT_CIRCONSTANCE_HEMATOME_PSOAS)
            .recidiveHematomePsoas(DEFAULT_RECIDIVE_HEMATOME_PSOAS)
            .hemorragiesCutaneoMuqueuses(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .typeHemorragiesCutaneoMuqueuses(DEFAULT_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .frequenceHemorragiesCutaneoMuqueuses(DEFAULT_FREQUENCE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .hemorragieVisceres(DEFAULT_HEMORRAGIE_VISCERES)
            .typeHemorragieVisceres(DEFAULT_TYPE_HEMORRAGIE_VISCERES)
            .explorationHemorragieVisceres(DEFAULT_EXPLORATION_HEMORRAGIE_VISCERES)
            .examenLesionHemorragieVisceres(DEFAULT_EXAMEN_LESION_HEMORRAGIE_VISCERES)
            .saignementSNC(DEFAULT_SAIGNEMENT_SNC)
            .evolutionSNC(DEFAULT_EVOLUTION_SNC)
            .circoncision(DEFAULT_CIRCONCISION)
            .typePreparationCirconcision(DEFAULT_TYPE_PREPARATION_CIRCONCISION)
            .suiteOperatoireCirconcision(DEFAULT_SUITE_OPERATOIRE_CIRCONCISION)
            .ageCirconcision(DEFAULT_AGE_CIRCONCISION)
            .avulsionDentaire(DEFAULT_AVULSION_DENTAIRE)
            .typePreparationAvulsionDentaire(DEFAULT_TYPE_PREPARATION_AVULSION_DENTAIRE)
            .interventionChirurgicale(DEFAULT_INTERVENTION_CHIRURGICALE)
            .typeInterventionChirurgicale(DEFAULT_TYPE_INTERVENTION_CHIRURGICALE)
            .accidentsHemorragiquesGraves(DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .circonstanceAccidentsHemorragiquesGraves(DEFAULT_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .priseEnCharge(DEFAULT_PRISE_EN_CHARGE)
            .causePriseEnCharge(DEFAULT_CAUSE_PRISE_EN_CHARGE)
            .doseProphylaxie(DEFAULT_DOSE_PROPHYLAXIE)
            .frequenceProphylaxie(DEFAULT_FREQUENCE_PROPHYLAXIE)
            .debutProphylaxie(DEFAULT_DEBUT_PROPHYLAXIE)
            .modaliteInjection(DEFAULT_MODALITE_INJECTION)
            .typeTraitementSubstitutif(DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF)
            .age1ereSubstitution(DEFAULT_AGE_1_ERE_SUBSTITUTION)
            .antecedentsPSL(DEFAULT_ANTECEDENTS_PSL)
            .plasmaFraisCongele(DEFAULT_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(DEFAULT_CRYOPRECIPITE)
            .complicationsOrthopediques(DEFAULT_COMPLICATIONS_ORTHOPEDIQUES)
            .typeComplicationsOrthopediques(DEFAULT_TYPE_COMPLICATIONS_ORTHOPEDIQUES)
            .articulationCible(DEFAULT_ARTICULATION_CIBLE)
            .pecTherapeutique(DEFAULT_PEC_THERAPEUTIQUE)
            .complicationsInhibiteurs(DEFAULT_COMPLICATIONS_INHIBITEURS)
            .tauxInhibiteur(DEFAULT_TAUX_INHIBITEUR)
            .testRecuperationFAH(DEFAULT_TEST_RECUPERATION_FAH)
            .dateRecuperationFAH(DEFAULT_DATE_RECUPERATION_FAH)
            .tauxRecuperation(DEFAULT_TAUX_RECUPERATION)
            .antiFAHNonNeutralisant(DEFAULT_ANTI_FAH_NON_NEUTRALISANT)
            .rechercheACC(DEFAULT_RECHERCHE_ACC)
            .catTherapeutique(DEFAULT_CAT_THERAPEUTIQUE)
            .iti(DEFAULT_ITI)
            .posologieITI(DEFAULT_POSOLOGIE_ITI)
            .dureeITI(DEFAULT_DUREE_ITI)
            .observanceITI(DEFAULT_OBSERVANCE_ITI)
            .causeNonObservanceITI(DEFAULT_CAUSE_NON_OBSERVANCE_ITI)
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
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilairesFamille(UPDATED_CAS_SIMILAIRES_FAMILLE)
            .nbreCasSimilaires(UPDATED_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .casDecesSyndromeHemorragique(UPDATED_CAS_DECES_SYNDROME_HEMORRAGIQUE)
            .nbreCasDeces(UPDATED_NBRE_CAS_DECES)
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .nbreFreres(UPDATED_NBRE_FRERES)
            .nbreSoeurs(UPDATED_NBRE_SOEURS)
            .circonstanceDecouverte(UPDATED_CIRCONSTANCE_DECOUVERTE)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hemogrammeHb(UPDATED_HEMOGRAMME_HB)
            .hemogrammeHt(UPDATED_HEMOGRAMME_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
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
            .hemarthrose(UPDATED_HEMARTHROSE)
            .siegeHemarthrose(UPDATED_SIEGE_HEMARTHROSE)
            .circonstanceHemarthrose(UPDATED_CIRCONSTANCE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .hematomeSuperficiel(UPDATED_HEMATOME_SUPERFICIEL)
            .circonstanceHematomeSuperficiel(UPDATED_CIRCONSTANCE_HEMATOME_SUPERFICIEL)
            .siegeHematomeSuperficiel(UPDATED_SIEGE_HEMATOME_SUPERFICIEL)
            .hematomePsoas(UPDATED_HEMATOME_PSOAS)
            .circonstanceHematomePsoas(UPDATED_CIRCONSTANCE_HEMATOME_PSOAS)
            .recidiveHematomePsoas(UPDATED_RECIDIVE_HEMATOME_PSOAS)
            .hemorragiesCutaneoMuqueuses(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .typeHemorragiesCutaneoMuqueuses(UPDATED_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .frequenceHemorragiesCutaneoMuqueuses(UPDATED_FREQUENCE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .typeHemorragieVisceres(UPDATED_TYPE_HEMORRAGIE_VISCERES)
            .explorationHemorragieVisceres(UPDATED_EXPLORATION_HEMORRAGIE_VISCERES)
            .examenLesionHemorragieVisceres(UPDATED_EXAMEN_LESION_HEMORRAGIE_VISCERES)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .evolutionSNC(UPDATED_EVOLUTION_SNC)
            .circoncision(UPDATED_CIRCONCISION)
            .typePreparationCirconcision(UPDATED_TYPE_PREPARATION_CIRCONCISION)
            .suiteOperatoireCirconcision(UPDATED_SUITE_OPERATOIRE_CIRCONCISION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .typePreparationAvulsionDentaire(UPDATED_TYPE_PREPARATION_AVULSION_DENTAIRE)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .typeInterventionChirurgicale(UPDATED_TYPE_INTERVENTION_CHIRURGICALE)
            .accidentsHemorragiquesGraves(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .circonstanceAccidentsHemorragiquesGraves(UPDATED_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .priseEnCharge(UPDATED_PRISE_EN_CHARGE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .doseProphylaxie(UPDATED_DOSE_PROPHYLAXIE)
            .frequenceProphylaxie(UPDATED_FREQUENCE_PROPHYLAXIE)
            .debutProphylaxie(UPDATED_DEBUT_PROPHYLAXIE)
            .modaliteInjection(UPDATED_MODALITE_INJECTION)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .age1ereSubstitution(UPDATED_AGE_1_ERE_SUBSTITUTION)
            .antecedentsPSL(UPDATED_ANTECEDENTS_PSL)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .complicationsOrthopediques(UPDATED_COMPLICATIONS_ORTHOPEDIQUES)
            .typeComplicationsOrthopediques(UPDATED_TYPE_COMPLICATIONS_ORTHOPEDIQUES)
            .articulationCible(UPDATED_ARTICULATION_CIBLE)
            .pecTherapeutique(UPDATED_PEC_THERAPEUTIQUE)
            .complicationsInhibiteurs(UPDATED_COMPLICATIONS_INHIBITEURS)
            .tauxInhibiteur(UPDATED_TAUX_INHIBITEUR)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .dateRecuperationFAH(UPDATED_DATE_RECUPERATION_FAH)
            .tauxRecuperation(UPDATED_TAUX_RECUPERATION)
            .antiFAHNonNeutralisant(UPDATED_ANTI_FAH_NON_NEUTRALISANT)
            .rechercheACC(UPDATED_RECHERCHE_ACC)
            .catTherapeutique(UPDATED_CAT_THERAPEUTIQUE)
            .iti(UPDATED_ITI)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .observanceITI(UPDATED_OBSERVANCE_ITI)
            .causeNonObservanceITI(UPDATED_CAUSE_NON_OBSERVANCE_ITI)
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
    void checkPriseEnChargeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        ficheHemophilie.setPriseEnCharge(null);

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
            .andExpect(jsonPath("$.[*].parentsConsanguins").value(hasItem(DEFAULT_PARENTS_CONSANGUINS.toString())))
            .andExpect(jsonPath("$.[*].degreParenteConsanguins").value(hasItem(DEFAULT_DEGRE_PARENTE_CONSANGUINS)))
            .andExpect(jsonPath("$.[*].casSimilairesFamille").value(hasItem(DEFAULT_CAS_SIMILAIRES_FAMILLE.toString())))
            .andExpect(jsonPath("$.[*].nbreCasSimilaires").value(hasItem(DEFAULT_NBRE_CAS_SIMILAIRES)))
            .andExpect(jsonPath("$.[*].degreParenteCasSimilaires").value(hasItem(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES)))
            .andExpect(jsonPath("$.[*].casDecesSyndromeHemorragique").value(hasItem(DEFAULT_CAS_DECES_SYNDROME_HEMORRAGIQUE.toString())))
            .andExpect(jsonPath("$.[*].nbreCasDeces").value(hasItem(DEFAULT_NBRE_CAS_DECES)))
            .andExpect(jsonPath("$.[*].formeHemophilie").value(hasItem(DEFAULT_FORME_HEMOPHILIE.toString())))
            .andExpect(jsonPath("$.[*].nbreFreres").value(hasItem(DEFAULT_NBRE_FRERES)))
            .andExpect(jsonPath("$.[*].nbreSoeurs").value(hasItem(DEFAULT_NBRE_SOEURS)))
            .andExpect(jsonPath("$.[*].circonstanceDecouverte").value(hasItem(DEFAULT_CIRCONSTANCE_DECOUVERTE.toString())))
            .andExpect(jsonPath("$.[*].dateTestConfirmation").value(hasItem(DEFAULT_DATE_TEST_CONFIRMATION.toString())))
            .andExpect(jsonPath("$.[*].hemogrammeHb").value(hasItem(DEFAULT_HEMOGRAMME_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].hemogrammeHt").value(hasItem(DEFAULT_HEMOGRAMME_HT.doubleValue())))
            .andExpect(jsonPath("$.[*].plaquettes").value(hasItem(DEFAULT_PLAQUETTES)))
            .andExpect(jsonPath("$.[*].tp").value(hasItem(DEFAULT_TP.doubleValue())))
            .andExpect(jsonPath("$.[*].fibrinogene").value(hasItem(DEFAULT_FIBRINOGENE.doubleValue())))
            .andExpect(jsonPath("$.[*].tcaMT").value(hasItem(DEFAULT_TCA_MT.doubleValue())))
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
            .andExpect(jsonPath("$.[*].explorationMoleculaire").value(hasItem(DEFAULT_EXPLORATION_MOLECULAIRE)))
            .andExpect(jsonPath("$.[*].hemarthrose").value(hasItem(DEFAULT_HEMARTHROSE.toString())))
            .andExpect(jsonPath("$.[*].siegeHemarthrose").value(hasItem(DEFAULT_SIEGE_HEMARTHROSE.toString())))
            .andExpect(jsonPath("$.[*].circonstanceHemarthrose").value(hasItem(DEFAULT_CIRCONSTANCE_HEMARTHROSE.toString())))
            .andExpect(jsonPath("$.[*].frequenceHemarthrose").value(hasItem(DEFAULT_FREQUENCE_HEMARTHROSE)))
            .andExpect(jsonPath("$.[*].hematomeSuperficiel").value(hasItem(DEFAULT_HEMATOME_SUPERFICIEL.toString())))
            .andExpect(jsonPath("$.[*].circonstanceHematomeSuperficiel").value(hasItem(DEFAULT_CIRCONSTANCE_HEMATOME_SUPERFICIEL)))
            .andExpect(jsonPath("$.[*].siegeHematomeSuperficiel").value(hasItem(DEFAULT_SIEGE_HEMATOME_SUPERFICIEL)))
            .andExpect(jsonPath("$.[*].hematomePsoas").value(hasItem(DEFAULT_HEMATOME_PSOAS.toString())))
            .andExpect(jsonPath("$.[*].circonstanceHematomePsoas").value(hasItem(DEFAULT_CIRCONSTANCE_HEMATOME_PSOAS)))
            .andExpect(jsonPath("$.[*].recidiveHematomePsoas").value(hasItem(DEFAULT_RECIDIVE_HEMATOME_PSOAS.toString())))
            .andExpect(jsonPath("$.[*].hemorragiesCutaneoMuqueuses").value(hasItem(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES.toString())))
            .andExpect(jsonPath("$.[*].typeHemorragiesCutaneoMuqueuses").value(hasItem(DEFAULT_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)))
            .andExpect(
                jsonPath("$.[*].frequenceHemorragiesCutaneoMuqueuses").value(hasItem(DEFAULT_FREQUENCE_HEMORRAGIES_CUTANEO_MUQUEUSES))
            )
            .andExpect(jsonPath("$.[*].hemorragieVisceres").value(hasItem(DEFAULT_HEMORRAGIE_VISCERES.toString())))
            .andExpect(jsonPath("$.[*].typeHemorragieVisceres").value(hasItem(DEFAULT_TYPE_HEMORRAGIE_VISCERES)))
            .andExpect(jsonPath("$.[*].explorationHemorragieVisceres").value(hasItem(DEFAULT_EXPLORATION_HEMORRAGIE_VISCERES.toString())))
            .andExpect(jsonPath("$.[*].examenLesionHemorragieVisceres").value(hasItem(DEFAULT_EXAMEN_LESION_HEMORRAGIE_VISCERES)))
            .andExpect(jsonPath("$.[*].saignementSNC").value(hasItem(DEFAULT_SAIGNEMENT_SNC.toString())))
            .andExpect(jsonPath("$.[*].evolutionSNC").value(hasItem(DEFAULT_EVOLUTION_SNC.toString())))
            .andExpect(jsonPath("$.[*].circoncision").value(hasItem(DEFAULT_CIRCONCISION.toString())))
            .andExpect(jsonPath("$.[*].typePreparationCirconcision").value(hasItem(DEFAULT_TYPE_PREPARATION_CIRCONCISION.toString())))
            .andExpect(jsonPath("$.[*].suiteOperatoireCirconcision").value(hasItem(DEFAULT_SUITE_OPERATOIRE_CIRCONCISION.toString())))
            .andExpect(jsonPath("$.[*].ageCirconcision").value(hasItem(DEFAULT_AGE_CIRCONCISION)))
            .andExpect(jsonPath("$.[*].avulsionDentaire").value(hasItem(DEFAULT_AVULSION_DENTAIRE.toString())))
            .andExpect(
                jsonPath("$.[*].typePreparationAvulsionDentaire").value(hasItem(DEFAULT_TYPE_PREPARATION_AVULSION_DENTAIRE.toString()))
            )
            .andExpect(jsonPath("$.[*].interventionChirurgicale").value(hasItem(DEFAULT_INTERVENTION_CHIRURGICALE.toString())))
            .andExpect(jsonPath("$.[*].typeInterventionChirurgicale").value(hasItem(DEFAULT_TYPE_INTERVENTION_CHIRURGICALE)))
            .andExpect(jsonPath("$.[*].accidentsHemorragiquesGraves").value(hasItem(DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES.toString())))
            .andExpect(
                jsonPath("$.[*].circonstanceAccidentsHemorragiquesGraves").value(
                    hasItem(DEFAULT_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
                )
            )
            .andExpect(jsonPath("$.[*].priseEnCharge").value(hasItem(DEFAULT_PRISE_EN_CHARGE.toString())))
            .andExpect(jsonPath("$.[*].causePriseEnCharge").value(hasItem(DEFAULT_CAUSE_PRISE_EN_CHARGE)))
            .andExpect(jsonPath("$.[*].doseProphylaxie").value(hasItem(DEFAULT_DOSE_PROPHYLAXIE)))
            .andExpect(jsonPath("$.[*].frequenceProphylaxie").value(hasItem(DEFAULT_FREQUENCE_PROPHYLAXIE)))
            .andExpect(jsonPath("$.[*].debutProphylaxie").value(hasItem(DEFAULT_DEBUT_PROPHYLAXIE.toString())))
            .andExpect(jsonPath("$.[*].modaliteInjection").value(hasItem(DEFAULT_MODALITE_INJECTION.toString())))
            .andExpect(jsonPath("$.[*].typeTraitementSubstitutif").value(hasItem(DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF.toString())))
            .andExpect(jsonPath("$.[*].age1ereSubstitution").value(hasItem(DEFAULT_AGE_1_ERE_SUBSTITUTION)))
            .andExpect(jsonPath("$.[*].antecedentsPSL").value(hasItem(DEFAULT_ANTECEDENTS_PSL.toString())))
            .andExpect(jsonPath("$.[*].plasmaFraisCongele").value(hasItem(DEFAULT_PLASMA_FRAIS_CONGELE.toString())))
            .andExpect(jsonPath("$.[*].cryoprecipite").value(hasItem(DEFAULT_CRYOPRECIPITE.toString())))
            .andExpect(jsonPath("$.[*].complicationsOrthopediques").value(hasItem(DEFAULT_COMPLICATIONS_ORTHOPEDIQUES.toString())))
            .andExpect(jsonPath("$.[*].typeComplicationsOrthopediques").value(hasItem(DEFAULT_TYPE_COMPLICATIONS_ORTHOPEDIQUES.toString())))
            .andExpect(jsonPath("$.[*].articulationCible").value(hasItem(DEFAULT_ARTICULATION_CIBLE.toString())))
            .andExpect(jsonPath("$.[*].pecTherapeutique").value(hasItem(DEFAULT_PEC_THERAPEUTIQUE.toString())))
            .andExpect(jsonPath("$.[*].complicationsInhibiteurs").value(hasItem(DEFAULT_COMPLICATIONS_INHIBITEURS.toString())))
            .andExpect(jsonPath("$.[*].tauxInhibiteur").value(hasItem(DEFAULT_TAUX_INHIBITEUR.toString())))
            .andExpect(jsonPath("$.[*].testRecuperationFAH").value(hasItem(DEFAULT_TEST_RECUPERATION_FAH.toString())))
            .andExpect(jsonPath("$.[*].dateRecuperationFAH").value(hasItem(DEFAULT_DATE_RECUPERATION_FAH.toString())))
            .andExpect(jsonPath("$.[*].tauxRecuperation").value(hasItem(DEFAULT_TAUX_RECUPERATION.toString())))
            .andExpect(jsonPath("$.[*].antiFAHNonNeutralisant").value(hasItem(DEFAULT_ANTI_FAH_NON_NEUTRALISANT.toString())))
            .andExpect(jsonPath("$.[*].rechercheACC").value(hasItem(DEFAULT_RECHERCHE_ACC.toString())))
            .andExpect(jsonPath("$.[*].catTherapeutique").value(hasItem(DEFAULT_CAT_THERAPEUTIQUE.toString())))
            .andExpect(jsonPath("$.[*].iti").value(hasItem(DEFAULT_ITI.toString())))
            .andExpect(jsonPath("$.[*].posologieITI").value(hasItem(DEFAULT_POSOLOGIE_ITI)))
            .andExpect(jsonPath("$.[*].dureeITI").value(hasItem(DEFAULT_DUREE_ITI)))
            .andExpect(jsonPath("$.[*].observanceITI").value(hasItem(DEFAULT_OBSERVANCE_ITI.toString())))
            .andExpect(jsonPath("$.[*].causeNonObservanceITI").value(hasItem(DEFAULT_CAUSE_NON_OBSERVANCE_ITI)))
            .andExpect(jsonPath("$.[*].evolutionInhibiteur").value(hasItem(DEFAULT_EVOLUTION_INHIBITEUR.toString())))
            .andExpect(jsonPath("$.[*].prophylaxieHemlibra").value(hasItem(DEFAULT_PROPHYLAXIE_HEMLIBRA.toString())))
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
            .andExpect(jsonPath("$.[*].activiteSportive").value(hasItem(DEFAULT_ACTIVITE_SPORTIVE.toString())))
            .andExpect(jsonPath("$.[*].typeActiviteSportive").value(hasItem(DEFAULT_TYPE_ACTIVITE_SPORTIVE)))
            .andExpect(jsonPath("$.[*].decede").value(hasItem(DEFAULT_DECEDE.toString())))
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
            .andExpect(jsonPath("$.dossierNumber").value(DEFAULT_DOSSIER_NUMBER))
            .andExpect(jsonPath("$.ordreNumber").value(DEFAULT_ORDRE_NUMBER))
            .andExpect(jsonPath("$.indexNumber").value(DEFAULT_INDEX_NUMBER))
            .andExpect(jsonPath("$.parentsConsanguins").value(DEFAULT_PARENTS_CONSANGUINS.toString()))
            .andExpect(jsonPath("$.degreParenteConsanguins").value(DEFAULT_DEGRE_PARENTE_CONSANGUINS))
            .andExpect(jsonPath("$.casSimilairesFamille").value(DEFAULT_CAS_SIMILAIRES_FAMILLE.toString()))
            .andExpect(jsonPath("$.nbreCasSimilaires").value(DEFAULT_NBRE_CAS_SIMILAIRES))
            .andExpect(jsonPath("$.degreParenteCasSimilaires").value(DEFAULT_DEGRE_PARENTE_CAS_SIMILAIRES))
            .andExpect(jsonPath("$.casDecesSyndromeHemorragique").value(DEFAULT_CAS_DECES_SYNDROME_HEMORRAGIQUE.toString()))
            .andExpect(jsonPath("$.nbreCasDeces").value(DEFAULT_NBRE_CAS_DECES))
            .andExpect(jsonPath("$.formeHemophilie").value(DEFAULT_FORME_HEMOPHILIE.toString()))
            .andExpect(jsonPath("$.nbreFreres").value(DEFAULT_NBRE_FRERES))
            .andExpect(jsonPath("$.nbreSoeurs").value(DEFAULT_NBRE_SOEURS))
            .andExpect(jsonPath("$.circonstanceDecouverte").value(DEFAULT_CIRCONSTANCE_DECOUVERTE.toString()))
            .andExpect(jsonPath("$.dateTestConfirmation").value(DEFAULT_DATE_TEST_CONFIRMATION.toString()))
            .andExpect(jsonPath("$.hemogrammeHb").value(DEFAULT_HEMOGRAMME_HB.doubleValue()))
            .andExpect(jsonPath("$.hemogrammeHt").value(DEFAULT_HEMOGRAMME_HT.doubleValue()))
            .andExpect(jsonPath("$.plaquettes").value(DEFAULT_PLAQUETTES))
            .andExpect(jsonPath("$.tp").value(DEFAULT_TP.doubleValue()))
            .andExpect(jsonPath("$.fibrinogene").value(DEFAULT_FIBRINOGENE.doubleValue()))
            .andExpect(jsonPath("$.tcaMT").value(DEFAULT_TCA_MT.doubleValue()))
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
            .andExpect(jsonPath("$.explorationMoleculaire").value(DEFAULT_EXPLORATION_MOLECULAIRE))
            .andExpect(jsonPath("$.hemarthrose").value(DEFAULT_HEMARTHROSE.toString()))
            .andExpect(jsonPath("$.siegeHemarthrose").value(DEFAULT_SIEGE_HEMARTHROSE.toString()))
            .andExpect(jsonPath("$.circonstanceHemarthrose").value(DEFAULT_CIRCONSTANCE_HEMARTHROSE.toString()))
            .andExpect(jsonPath("$.frequenceHemarthrose").value(DEFAULT_FREQUENCE_HEMARTHROSE))
            .andExpect(jsonPath("$.hematomeSuperficiel").value(DEFAULT_HEMATOME_SUPERFICIEL.toString()))
            .andExpect(jsonPath("$.circonstanceHematomeSuperficiel").value(DEFAULT_CIRCONSTANCE_HEMATOME_SUPERFICIEL))
            .andExpect(jsonPath("$.siegeHematomeSuperficiel").value(DEFAULT_SIEGE_HEMATOME_SUPERFICIEL))
            .andExpect(jsonPath("$.hematomePsoas").value(DEFAULT_HEMATOME_PSOAS.toString()))
            .andExpect(jsonPath("$.circonstanceHematomePsoas").value(DEFAULT_CIRCONSTANCE_HEMATOME_PSOAS))
            .andExpect(jsonPath("$.recidiveHematomePsoas").value(DEFAULT_RECIDIVE_HEMATOME_PSOAS.toString()))
            .andExpect(jsonPath("$.hemorragiesCutaneoMuqueuses").value(DEFAULT_HEMORRAGIES_CUTANEO_MUQUEUSES.toString()))
            .andExpect(jsonPath("$.typeHemorragiesCutaneoMuqueuses").value(DEFAULT_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES))
            .andExpect(jsonPath("$.frequenceHemorragiesCutaneoMuqueuses").value(DEFAULT_FREQUENCE_HEMORRAGIES_CUTANEO_MUQUEUSES))
            .andExpect(jsonPath("$.hemorragieVisceres").value(DEFAULT_HEMORRAGIE_VISCERES.toString()))
            .andExpect(jsonPath("$.typeHemorragieVisceres").value(DEFAULT_TYPE_HEMORRAGIE_VISCERES))
            .andExpect(jsonPath("$.explorationHemorragieVisceres").value(DEFAULT_EXPLORATION_HEMORRAGIE_VISCERES.toString()))
            .andExpect(jsonPath("$.examenLesionHemorragieVisceres").value(DEFAULT_EXAMEN_LESION_HEMORRAGIE_VISCERES))
            .andExpect(jsonPath("$.saignementSNC").value(DEFAULT_SAIGNEMENT_SNC.toString()))
            .andExpect(jsonPath("$.evolutionSNC").value(DEFAULT_EVOLUTION_SNC.toString()))
            .andExpect(jsonPath("$.circoncision").value(DEFAULT_CIRCONCISION.toString()))
            .andExpect(jsonPath("$.typePreparationCirconcision").value(DEFAULT_TYPE_PREPARATION_CIRCONCISION.toString()))
            .andExpect(jsonPath("$.suiteOperatoireCirconcision").value(DEFAULT_SUITE_OPERATOIRE_CIRCONCISION.toString()))
            .andExpect(jsonPath("$.ageCirconcision").value(DEFAULT_AGE_CIRCONCISION))
            .andExpect(jsonPath("$.avulsionDentaire").value(DEFAULT_AVULSION_DENTAIRE.toString()))
            .andExpect(jsonPath("$.typePreparationAvulsionDentaire").value(DEFAULT_TYPE_PREPARATION_AVULSION_DENTAIRE.toString()))
            .andExpect(jsonPath("$.interventionChirurgicale").value(DEFAULT_INTERVENTION_CHIRURGICALE.toString()))
            .andExpect(jsonPath("$.typeInterventionChirurgicale").value(DEFAULT_TYPE_INTERVENTION_CHIRURGICALE))
            .andExpect(jsonPath("$.accidentsHemorragiquesGraves").value(DEFAULT_ACCIDENTS_HEMORRAGIQUES_GRAVES.toString()))
            .andExpect(jsonPath("$.circonstanceAccidentsHemorragiquesGraves").value(DEFAULT_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES))
            .andExpect(jsonPath("$.priseEnCharge").value(DEFAULT_PRISE_EN_CHARGE.toString()))
            .andExpect(jsonPath("$.causePriseEnCharge").value(DEFAULT_CAUSE_PRISE_EN_CHARGE))
            .andExpect(jsonPath("$.doseProphylaxie").value(DEFAULT_DOSE_PROPHYLAXIE))
            .andExpect(jsonPath("$.frequenceProphylaxie").value(DEFAULT_FREQUENCE_PROPHYLAXIE))
            .andExpect(jsonPath("$.debutProphylaxie").value(DEFAULT_DEBUT_PROPHYLAXIE.toString()))
            .andExpect(jsonPath("$.modaliteInjection").value(DEFAULT_MODALITE_INJECTION.toString()))
            .andExpect(jsonPath("$.typeTraitementSubstitutif").value(DEFAULT_TYPE_TRAITEMENT_SUBSTITUTIF.toString()))
            .andExpect(jsonPath("$.age1ereSubstitution").value(DEFAULT_AGE_1_ERE_SUBSTITUTION))
            .andExpect(jsonPath("$.antecedentsPSL").value(DEFAULT_ANTECEDENTS_PSL.toString()))
            .andExpect(jsonPath("$.plasmaFraisCongele").value(DEFAULT_PLASMA_FRAIS_CONGELE.toString()))
            .andExpect(jsonPath("$.cryoprecipite").value(DEFAULT_CRYOPRECIPITE.toString()))
            .andExpect(jsonPath("$.complicationsOrthopediques").value(DEFAULT_COMPLICATIONS_ORTHOPEDIQUES.toString()))
            .andExpect(jsonPath("$.typeComplicationsOrthopediques").value(DEFAULT_TYPE_COMPLICATIONS_ORTHOPEDIQUES.toString()))
            .andExpect(jsonPath("$.articulationCible").value(DEFAULT_ARTICULATION_CIBLE.toString()))
            .andExpect(jsonPath("$.pecTherapeutique").value(DEFAULT_PEC_THERAPEUTIQUE.toString()))
            .andExpect(jsonPath("$.complicationsInhibiteurs").value(DEFAULT_COMPLICATIONS_INHIBITEURS.toString()))
            .andExpect(jsonPath("$.tauxInhibiteur").value(DEFAULT_TAUX_INHIBITEUR.toString()))
            .andExpect(jsonPath("$.testRecuperationFAH").value(DEFAULT_TEST_RECUPERATION_FAH.toString()))
            .andExpect(jsonPath("$.dateRecuperationFAH").value(DEFAULT_DATE_RECUPERATION_FAH.toString()))
            .andExpect(jsonPath("$.tauxRecuperation").value(DEFAULT_TAUX_RECUPERATION.toString()))
            .andExpect(jsonPath("$.antiFAHNonNeutralisant").value(DEFAULT_ANTI_FAH_NON_NEUTRALISANT.toString()))
            .andExpect(jsonPath("$.rechercheACC").value(DEFAULT_RECHERCHE_ACC.toString()))
            .andExpect(jsonPath("$.catTherapeutique").value(DEFAULT_CAT_THERAPEUTIQUE.toString()))
            .andExpect(jsonPath("$.iti").value(DEFAULT_ITI.toString()))
            .andExpect(jsonPath("$.posologieITI").value(DEFAULT_POSOLOGIE_ITI))
            .andExpect(jsonPath("$.dureeITI").value(DEFAULT_DUREE_ITI))
            .andExpect(jsonPath("$.observanceITI").value(DEFAULT_OBSERVANCE_ITI.toString()))
            .andExpect(jsonPath("$.causeNonObservanceITI").value(DEFAULT_CAUSE_NON_OBSERVANCE_ITI))
            .andExpect(jsonPath("$.evolutionInhibiteur").value(DEFAULT_EVOLUTION_INHIBITEUR.toString()))
            .andExpect(jsonPath("$.prophylaxieHemlibra").value(DEFAULT_PROPHYLAXIE_HEMLIBRA.toString()))
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
            .andExpect(jsonPath("$.activiteSportive").value(DEFAULT_ACTIVITE_SPORTIVE.toString()))
            .andExpect(jsonPath("$.typeActiviteSportive").value(DEFAULT_TYPE_ACTIVITE_SPORTIVE))
            .andExpect(jsonPath("$.decede").value(DEFAULT_DECEDE.toString()))
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
            .dossierNumber(UPDATED_DOSSIER_NUMBER)
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilairesFamille(UPDATED_CAS_SIMILAIRES_FAMILLE)
            .nbreCasSimilaires(UPDATED_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .casDecesSyndromeHemorragique(UPDATED_CAS_DECES_SYNDROME_HEMORRAGIQUE)
            .nbreCasDeces(UPDATED_NBRE_CAS_DECES)
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .nbreFreres(UPDATED_NBRE_FRERES)
            .nbreSoeurs(UPDATED_NBRE_SOEURS)
            .circonstanceDecouverte(UPDATED_CIRCONSTANCE_DECOUVERTE)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hemogrammeHb(UPDATED_HEMOGRAMME_HB)
            .hemogrammeHt(UPDATED_HEMOGRAMME_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
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
            .hemarthrose(UPDATED_HEMARTHROSE)
            .siegeHemarthrose(UPDATED_SIEGE_HEMARTHROSE)
            .circonstanceHemarthrose(UPDATED_CIRCONSTANCE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .hematomeSuperficiel(UPDATED_HEMATOME_SUPERFICIEL)
            .circonstanceHematomeSuperficiel(UPDATED_CIRCONSTANCE_HEMATOME_SUPERFICIEL)
            .siegeHematomeSuperficiel(UPDATED_SIEGE_HEMATOME_SUPERFICIEL)
            .hematomePsoas(UPDATED_HEMATOME_PSOAS)
            .circonstanceHematomePsoas(UPDATED_CIRCONSTANCE_HEMATOME_PSOAS)
            .recidiveHematomePsoas(UPDATED_RECIDIVE_HEMATOME_PSOAS)
            .hemorragiesCutaneoMuqueuses(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .typeHemorragiesCutaneoMuqueuses(UPDATED_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .frequenceHemorragiesCutaneoMuqueuses(UPDATED_FREQUENCE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .typeHemorragieVisceres(UPDATED_TYPE_HEMORRAGIE_VISCERES)
            .explorationHemorragieVisceres(UPDATED_EXPLORATION_HEMORRAGIE_VISCERES)
            .examenLesionHemorragieVisceres(UPDATED_EXAMEN_LESION_HEMORRAGIE_VISCERES)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .evolutionSNC(UPDATED_EVOLUTION_SNC)
            .circoncision(UPDATED_CIRCONCISION)
            .typePreparationCirconcision(UPDATED_TYPE_PREPARATION_CIRCONCISION)
            .suiteOperatoireCirconcision(UPDATED_SUITE_OPERATOIRE_CIRCONCISION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .typePreparationAvulsionDentaire(UPDATED_TYPE_PREPARATION_AVULSION_DENTAIRE)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .typeInterventionChirurgicale(UPDATED_TYPE_INTERVENTION_CHIRURGICALE)
            .accidentsHemorragiquesGraves(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .circonstanceAccidentsHemorragiquesGraves(UPDATED_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .priseEnCharge(UPDATED_PRISE_EN_CHARGE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .doseProphylaxie(UPDATED_DOSE_PROPHYLAXIE)
            .frequenceProphylaxie(UPDATED_FREQUENCE_PROPHYLAXIE)
            .debutProphylaxie(UPDATED_DEBUT_PROPHYLAXIE)
            .modaliteInjection(UPDATED_MODALITE_INJECTION)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .age1ereSubstitution(UPDATED_AGE_1_ERE_SUBSTITUTION)
            .antecedentsPSL(UPDATED_ANTECEDENTS_PSL)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .complicationsOrthopediques(UPDATED_COMPLICATIONS_ORTHOPEDIQUES)
            .typeComplicationsOrthopediques(UPDATED_TYPE_COMPLICATIONS_ORTHOPEDIQUES)
            .articulationCible(UPDATED_ARTICULATION_CIBLE)
            .pecTherapeutique(UPDATED_PEC_THERAPEUTIQUE)
            .complicationsInhibiteurs(UPDATED_COMPLICATIONS_INHIBITEURS)
            .tauxInhibiteur(UPDATED_TAUX_INHIBITEUR)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .dateRecuperationFAH(UPDATED_DATE_RECUPERATION_FAH)
            .tauxRecuperation(UPDATED_TAUX_RECUPERATION)
            .antiFAHNonNeutralisant(UPDATED_ANTI_FAH_NON_NEUTRALISANT)
            .rechercheACC(UPDATED_RECHERCHE_ACC)
            .catTherapeutique(UPDATED_CAT_THERAPEUTIQUE)
            .iti(UPDATED_ITI)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .observanceITI(UPDATED_OBSERVANCE_ITI)
            .causeNonObservanceITI(UPDATED_CAUSE_NON_OBSERVANCE_ITI)
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
            .ordreNumber(UPDATED_ORDRE_NUMBER)
            .indexNumber(UPDATED_INDEX_NUMBER)
            .nbreCasSimilaires(UPDATED_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .casDecesSyndromeHemorragique(UPDATED_CAS_DECES_SYNDROME_HEMORRAGIQUE)
            .nbreCasDeces(UPDATED_NBRE_CAS_DECES)
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .nbreFreres(UPDATED_NBRE_FRERES)
            .nbreSoeurs(UPDATED_NBRE_SOEURS)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hemogrammeHt(UPDATED_HEMOGRAMME_HT)
            .tp(UPDATED_TP)
            .tcaMT(UPDATED_TCA_MT)
            .tcaMT2h(UPDATED_TCA_MT_2_H)
            .tcaTemoin2h(UPDATED_TCA_TEMOIN_2_H)
            .explorationMoleculaire(UPDATED_EXPLORATION_MOLECULAIRE)
            .hemarthrose(UPDATED_HEMARTHROSE)
            .siegeHemarthrose(UPDATED_SIEGE_HEMARTHROSE)
            .circonstanceHemarthrose(UPDATED_CIRCONSTANCE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .hematomeSuperficiel(UPDATED_HEMATOME_SUPERFICIEL)
            .circonstanceHematomeSuperficiel(UPDATED_CIRCONSTANCE_HEMATOME_SUPERFICIEL)
            .siegeHematomeSuperficiel(UPDATED_SIEGE_HEMATOME_SUPERFICIEL)
            .hematomePsoas(UPDATED_HEMATOME_PSOAS)
            .recidiveHematomePsoas(UPDATED_RECIDIVE_HEMATOME_PSOAS)
            .hemorragiesCutaneoMuqueuses(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .frequenceHemorragiesCutaneoMuqueuses(UPDATED_FREQUENCE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .typeHemorragieVisceres(UPDATED_TYPE_HEMORRAGIE_VISCERES)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .suiteOperatoireCirconcision(UPDATED_SUITE_OPERATOIRE_CIRCONCISION)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .accidentsHemorragiquesGraves(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .circonstanceAccidentsHemorragiquesGraves(UPDATED_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .priseEnCharge(UPDATED_PRISE_EN_CHARGE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .doseProphylaxie(UPDATED_DOSE_PROPHYLAXIE)
            .frequenceProphylaxie(UPDATED_FREQUENCE_PROPHYLAXIE)
            .debutProphylaxie(UPDATED_DEBUT_PROPHYLAXIE)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .antecedentsPSL(UPDATED_ANTECEDENTS_PSL)
            .articulationCible(UPDATED_ARTICULATION_CIBLE)
            .pecTherapeutique(UPDATED_PEC_THERAPEUTIQUE)
            .tauxInhibiteur(UPDATED_TAUX_INHIBITEUR)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .dateRecuperationFAH(UPDATED_DATE_RECUPERATION_FAH)
            .tauxRecuperation(UPDATED_TAUX_RECUPERATION)
            .iti(UPDATED_ITI)
            .causeNonObservanceITI(UPDATED_CAUSE_NON_OBSERVANCE_ITI)
            .prophylaxieHemlibra(UPDATED_PROPHYLAXIE_HEMLIBRA)
            .schemaTherapeutique(UPDATED_SCHEMA_THERAPEUTIQUE)
            .serologieHBC(UPDATED_SEROLOGIE_HBC)
            .serologieHVC(UPDATED_SEROLOGIE_HVC)
            .chargeViraleVHC(UPDATED_CHARGE_VIRALE_VHC)
            .serologieHIV(UPDATED_SEROLOGIE_HIV)
            .vieSociale(UPDATED_VIE_SOCIALE)
            .marie(UPDATED_MARIE)
            .enfants(UPDATED_ENFANTS)
            .handicap(UPDATED_HANDICAP)
            .typeHandicap(UPDATED_TYPE_HANDICAP)
            .activiteSportive(UPDATED_ACTIVITE_SPORTIVE)
            .typeActiviteSportive(UPDATED_TYPE_ACTIVITE_SPORTIVE)
            .decede(UPDATED_DECEDE);

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
            .parentsConsanguins(UPDATED_PARENTS_CONSANGUINS)
            .degreParenteConsanguins(UPDATED_DEGRE_PARENTE_CONSANGUINS)
            .casSimilairesFamille(UPDATED_CAS_SIMILAIRES_FAMILLE)
            .nbreCasSimilaires(UPDATED_NBRE_CAS_SIMILAIRES)
            .degreParenteCasSimilaires(UPDATED_DEGRE_PARENTE_CAS_SIMILAIRES)
            .casDecesSyndromeHemorragique(UPDATED_CAS_DECES_SYNDROME_HEMORRAGIQUE)
            .nbreCasDeces(UPDATED_NBRE_CAS_DECES)
            .formeHemophilie(UPDATED_FORME_HEMOPHILIE)
            .nbreFreres(UPDATED_NBRE_FRERES)
            .nbreSoeurs(UPDATED_NBRE_SOEURS)
            .circonstanceDecouverte(UPDATED_CIRCONSTANCE_DECOUVERTE)
            .dateTestConfirmation(UPDATED_DATE_TEST_CONFIRMATION)
            .hemogrammeHb(UPDATED_HEMOGRAMME_HB)
            .hemogrammeHt(UPDATED_HEMOGRAMME_HT)
            .plaquettes(UPDATED_PLAQUETTES)
            .tp(UPDATED_TP)
            .fibrinogene(UPDATED_FIBRINOGENE)
            .tcaMT(UPDATED_TCA_MT)
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
            .hemarthrose(UPDATED_HEMARTHROSE)
            .siegeHemarthrose(UPDATED_SIEGE_HEMARTHROSE)
            .circonstanceHemarthrose(UPDATED_CIRCONSTANCE_HEMARTHROSE)
            .frequenceHemarthrose(UPDATED_FREQUENCE_HEMARTHROSE)
            .hematomeSuperficiel(UPDATED_HEMATOME_SUPERFICIEL)
            .circonstanceHematomeSuperficiel(UPDATED_CIRCONSTANCE_HEMATOME_SUPERFICIEL)
            .siegeHematomeSuperficiel(UPDATED_SIEGE_HEMATOME_SUPERFICIEL)
            .hematomePsoas(UPDATED_HEMATOME_PSOAS)
            .circonstanceHematomePsoas(UPDATED_CIRCONSTANCE_HEMATOME_PSOAS)
            .recidiveHematomePsoas(UPDATED_RECIDIVE_HEMATOME_PSOAS)
            .hemorragiesCutaneoMuqueuses(UPDATED_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .typeHemorragiesCutaneoMuqueuses(UPDATED_TYPE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .frequenceHemorragiesCutaneoMuqueuses(UPDATED_FREQUENCE_HEMORRAGIES_CUTANEO_MUQUEUSES)
            .hemorragieVisceres(UPDATED_HEMORRAGIE_VISCERES)
            .typeHemorragieVisceres(UPDATED_TYPE_HEMORRAGIE_VISCERES)
            .explorationHemorragieVisceres(UPDATED_EXPLORATION_HEMORRAGIE_VISCERES)
            .examenLesionHemorragieVisceres(UPDATED_EXAMEN_LESION_HEMORRAGIE_VISCERES)
            .saignementSNC(UPDATED_SAIGNEMENT_SNC)
            .evolutionSNC(UPDATED_EVOLUTION_SNC)
            .circoncision(UPDATED_CIRCONCISION)
            .typePreparationCirconcision(UPDATED_TYPE_PREPARATION_CIRCONCISION)
            .suiteOperatoireCirconcision(UPDATED_SUITE_OPERATOIRE_CIRCONCISION)
            .ageCirconcision(UPDATED_AGE_CIRCONCISION)
            .avulsionDentaire(UPDATED_AVULSION_DENTAIRE)
            .typePreparationAvulsionDentaire(UPDATED_TYPE_PREPARATION_AVULSION_DENTAIRE)
            .interventionChirurgicale(UPDATED_INTERVENTION_CHIRURGICALE)
            .typeInterventionChirurgicale(UPDATED_TYPE_INTERVENTION_CHIRURGICALE)
            .accidentsHemorragiquesGraves(UPDATED_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .circonstanceAccidentsHemorragiquesGraves(UPDATED_CIRCONSTANCE_ACCIDENTS_HEMORRAGIQUES_GRAVES)
            .priseEnCharge(UPDATED_PRISE_EN_CHARGE)
            .causePriseEnCharge(UPDATED_CAUSE_PRISE_EN_CHARGE)
            .doseProphylaxie(UPDATED_DOSE_PROPHYLAXIE)
            .frequenceProphylaxie(UPDATED_FREQUENCE_PROPHYLAXIE)
            .debutProphylaxie(UPDATED_DEBUT_PROPHYLAXIE)
            .modaliteInjection(UPDATED_MODALITE_INJECTION)
            .typeTraitementSubstitutif(UPDATED_TYPE_TRAITEMENT_SUBSTITUTIF)
            .age1ereSubstitution(UPDATED_AGE_1_ERE_SUBSTITUTION)
            .antecedentsPSL(UPDATED_ANTECEDENTS_PSL)
            .plasmaFraisCongele(UPDATED_PLASMA_FRAIS_CONGELE)
            .cryoprecipite(UPDATED_CRYOPRECIPITE)
            .complicationsOrthopediques(UPDATED_COMPLICATIONS_ORTHOPEDIQUES)
            .typeComplicationsOrthopediques(UPDATED_TYPE_COMPLICATIONS_ORTHOPEDIQUES)
            .articulationCible(UPDATED_ARTICULATION_CIBLE)
            .pecTherapeutique(UPDATED_PEC_THERAPEUTIQUE)
            .complicationsInhibiteurs(UPDATED_COMPLICATIONS_INHIBITEURS)
            .tauxInhibiteur(UPDATED_TAUX_INHIBITEUR)
            .testRecuperationFAH(UPDATED_TEST_RECUPERATION_FAH)
            .dateRecuperationFAH(UPDATED_DATE_RECUPERATION_FAH)
            .tauxRecuperation(UPDATED_TAUX_RECUPERATION)
            .antiFAHNonNeutralisant(UPDATED_ANTI_FAH_NON_NEUTRALISANT)
            .rechercheACC(UPDATED_RECHERCHE_ACC)
            .catTherapeutique(UPDATED_CAT_THERAPEUTIQUE)
            .iti(UPDATED_ITI)
            .posologieITI(UPDATED_POSOLOGIE_ITI)
            .dureeITI(UPDATED_DUREE_ITI)
            .observanceITI(UPDATED_OBSERVANCE_ITI)
            .causeNonObservanceITI(UPDATED_CAUSE_NON_OBSERVANCE_ITI)
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

package tn.med.adhes.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
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
import tn.med.adhes.domain.enumeration.PECTherapeutique;
import tn.med.adhes.domain.enumeration.PriseEnChargeType;
import tn.med.adhes.domain.enumeration.RechercheACC;
import tn.med.adhes.domain.enumeration.SchemaTherapeutique;
import tn.med.adhes.domain.enumeration.Serologie;
import tn.med.adhes.domain.enumeration.Severite;
import tn.med.adhes.domain.enumeration.SuiteOperatoire;
import tn.med.adhes.domain.enumeration.TauxInhibiteur;
import tn.med.adhes.domain.enumeration.TauxRecuperation;
import tn.med.adhes.domain.enumeration.TestStatus;
import tn.med.adhes.domain.enumeration.TraitementType;
import tn.med.adhes.domain.enumeration.TypeComplicationsOrthopediques;
import tn.med.adhes.domain.enumeration.TypeManifestation;
import tn.med.adhes.domain.enumeration.TypePreparation;
import tn.med.adhes.domain.enumeration.VieSociale;

/**
 * A FicheHemophilie.
 */
@Entity
@Table(name = "fiche_hemophilie")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheHemophilie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "dossier_number", length = 30, nullable = false)
    private String dossierNumber;

    @Size(max = 30)
    @Column(name = "ordre_number", length = 30)
    private String ordreNumber;

    @Size(max = 30)
    @Column(name = "index_number", length = 30)
    private String indexNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "parents_consanguins")
    private OuiNonNP parentsConsanguins;

    @Size(max = 30)
    @Column(name = "degre_parente_consanguins", length = 30)
    private String degreParenteConsanguins;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_similaires_famille")
    private OuiNonNP casSimilairesFamille;

    @Column(name = "nbre_cas_similaires")
    private Integer nbreCasSimilaires;

    @Size(max = 30)
    @Column(name = "degre_parente_cas_similaires", length = 30)
    private String degreParenteCasSimilaires;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_deces_syndrome_hemorragique")
    private OuiNonNP casDecesSyndromeHemorragique;

    @Column(name = "nbre_cas_deces")
    private Integer nbreCasDeces;

    @Enumerated(EnumType.STRING)
    @Column(name = "forme_hemophilie")
    private FormeHemophilie formeHemophilie;

    @Column(name = "nbre_freres")
    private Integer nbreFreres;

    @Column(name = "nbre_soeurs")
    private Integer nbreSoeurs;

    @Enumerated(EnumType.STRING)
    @Column(name = "circonstance_decouverte")
    private CircumstanceDecouverte circonstanceDecouverte;

    @Column(name = "date_test_confirmation")
    private LocalDate dateTestConfirmation;

    @Column(name = "hemogramme_hb")
    private Double hemogrammeHb;

    @Column(name = "hemogramme_ht")
    private Double hemogrammeHt;

    @Column(name = "plaquettes")
    private Integer plaquettes;

    @Column(name = "tp")
    private Double tp;

    @Column(name = "fibrinogene")
    private Double fibrinogene;

    @Column(name = "tca_mt")
    private Double tcaMT;

    @Column(name = "tca_mt_2_h")
    private Double tcaMT2h;

    @Column(name = "tca_temoin_2_h")
    private Double tcaTemoin2h;

    @Enumerated(EnumType.STRING)
    @Column(name = "ts")
    private TestStatus ts;

    @Enumerated(EnumType.STRING)
    @Column(name = "pfa")
    private TestStatus pfa;

    @Column(name = "dosage_facteur_viii")
    private Double dosageFacteurVIII;

    @Column(name = "dosage_facteur_ix")
    private Double dosageFacteurIX;

    @Column(name = "dosage_facteur_vw_activite")
    private Double dosageFacteurVWActivite;

    @Column(name = "dosage_facteur_vw_antigene")
    private Double dosageFacteurVWAntigene;

    @Enumerated(EnumType.STRING)
    @Column(name = "forme_clinique")
    private FormeClinique formeClinique;

    @Enumerated(EnumType.STRING)
    @Column(name = "severite")
    private Severite severite;

    @Size(max = 30)
    @Column(name = "exploration_moleculaire", length = 30)
    private String explorationMoleculaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemarthrose")
    private OuiNonNP hemarthrose;

    @Enumerated(EnumType.STRING)
    @Column(name = "siege_hemarthrose")
    private ArticulationCible siegeHemarthrose;

    @Enumerated(EnumType.STRING)
    @Column(name = "circonstance_hemarthrose")
    private TypeManifestation circonstanceHemarthrose;

    @Column(name = "frequence_hemarthrose")
    private Integer frequenceHemarthrose;

    @Enumerated(EnumType.STRING)
    @Column(name = "hematome_superficiel")
    private OuiNonNP hematomeSuperficiel;

    @Size(max = 30)
    @Column(name = "circonstance_hematome_superficiel", length = 30)
    private String circonstanceHematomeSuperficiel;

    @Size(max = 30)
    @Column(name = "siege_hematome_superficiel", length = 30)
    private String siegeHematomeSuperficiel;

    @Enumerated(EnumType.STRING)
    @Column(name = "hematome_psoas")
    private OuiNonNP hematomePsoas;

    @Size(max = 30)
    @Column(name = "circonstance_hematome_psoas", length = 30)
    private String circonstanceHematomePsoas;

    @Enumerated(EnumType.STRING)
    @Column(name = "recidive_hematome_psoas")
    private OuiNonNP recidiveHematomePsoas;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragies_cutaneo_muqueuses")
    private OuiNonNP hemorragiesCutaneoMuqueuses;

    @Size(max = 30)
    @Column(name = "type_hemorragies_cutaneo_muqueuses", length = 30)
    private String typeHemorragiesCutaneoMuqueuses;

    @Column(name = "frequence_hemorragies_cutaneo_muqueuses")
    private Integer frequenceHemorragiesCutaneoMuqueuses;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_visceres")
    private OuiNonNP hemorragieVisceres;

    @Size(max = 30)
    @Column(name = "type_hemorragie_visceres", length = 30)
    private String typeHemorragieVisceres;

    @Enumerated(EnumType.STRING)
    @Column(name = "exploration_hemorragie_visceres")
    private OuiNonNP explorationHemorragieVisceres;

    @Size(max = 30)
    @Column(name = "examen_lesion_hemorragie_visceres", length = 30)
    private String examenLesionHemorragieVisceres;

    @Enumerated(EnumType.STRING)
    @Column(name = "saignement_snc")
    private OuiNonNP saignementSNC;

    @Enumerated(EnumType.STRING)
    @Column(name = "evolution_snc")
    private EvolutionSNC evolutionSNC;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision")
    private OuiNonNP circoncision;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_preparation_circoncision")
    private TypePreparation typePreparationCirconcision;

    @Enumerated(EnumType.STRING)
    @Column(name = "suite_operatoire_circoncision")
    private SuiteOperatoire suiteOperatoireCirconcision;

    @Column(name = "age_circoncision")
    private Integer ageCirconcision;

    @Enumerated(EnumType.STRING)
    @Column(name = "avulsion_dentaire")
    private OuiNonNP avulsionDentaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_preparation_avulsion_dentaire")
    private TypePreparation typePreparationAvulsionDentaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "intervention_chirurgicale")
    private OuiNonNP interventionChirurgicale;

    @Size(max = 30)
    @Column(name = "type_intervention_chirurgicale", length = 30)
    private String typeInterventionChirurgicale;

    @Enumerated(EnumType.STRING)
    @Column(name = "accidents_hemorragiques_graves")
    private OuiNonNP accidentsHemorragiquesGraves;

    @Size(max = 30)
    @Column(name = "circonstance_accidents_hemorragiques_graves", length = 30)
    private String circonstanceAccidentsHemorragiquesGraves;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "prise_en_charge", nullable = false)
    private PriseEnChargeType priseEnCharge;

    @Size(max = 30)
    @Column(name = "cause_prise_en_charge", length = 30)
    private String causePriseEnCharge;

    @Size(max = 20)
    @Column(name = "dose_prophylaxie", length = 20)
    private String doseProphylaxie;

    @Column(name = "frequence_prophylaxie")
    private Integer frequenceProphylaxie;

    @Column(name = "debut_prophylaxie")
    private LocalDate debutProphylaxie;

    @Enumerated(EnumType.STRING)
    @Column(name = "modalite_injection")
    private InjectionType modaliteInjection;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_traitement_substitutif")
    private TraitementType typeTraitementSubstitutif;

    @Column(name = "age_1_ere_substitution")
    private Integer age1ereSubstitution;

    @Enumerated(EnumType.STRING)
    @Column(name = "antecedents_psl")
    private OuiNonNP antecedentsPSL;

    @Enumerated(EnumType.STRING)
    @Column(name = "plasma_frais_congele")
    private OuiNonNP plasmaFraisCongele;

    @Enumerated(EnumType.STRING)
    @Column(name = "cryoprecipite")
    private OuiNonNP cryoprecipite;

    @Enumerated(EnumType.STRING)
    @Column(name = "complications_orthopediques")
    private OuiNonNP complicationsOrthopediques;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_complications_orthopediques")
    private TypeComplicationsOrthopediques typeComplicationsOrthopediques;

    @Enumerated(EnumType.STRING)
    @Column(name = "articulation_cible")
    private ArticulationCible articulationCible;

    @Enumerated(EnumType.STRING)
    @Column(name = "pec_therapeutique")
    private PECTherapeutique pecTherapeutique;

    @Enumerated(EnumType.STRING)
    @Column(name = "complications_inhibiteurs")
    private OuiNonNP complicationsInhibiteurs;

    @Enumerated(EnumType.STRING)
    @Column(name = "taux_inhibiteur")
    private TauxInhibiteur tauxInhibiteur;

    @Enumerated(EnumType.STRING)
    @Column(name = "test_recuperation_fah")
    private TestStatus testRecuperationFAH;

    @Column(name = "date_recuperation_fah")
    private LocalDate dateRecuperationFAH;

    @Enumerated(EnumType.STRING)
    @Column(name = "taux_recuperation")
    private TauxRecuperation tauxRecuperation;

    @Enumerated(EnumType.STRING)
    @Column(name = "anti_fah_non_neutralisant")
    private OuiNonNP antiFAHNonNeutralisant;

    @Enumerated(EnumType.STRING)
    @Column(name = "recherche_acc")
    private RechercheACC rechercheACC;

    @Enumerated(EnumType.STRING)
    @Column(name = "cat_therapeutique")
    private CATTherapeutique catTherapeutique;

    @Enumerated(EnumType.STRING)
    @Column(name = "iti")
    private OuiNonNP iti;

    @Size(max = 30)
    @Column(name = "posologie_iti", length = 30)
    private String posologieITI;

    @Size(max = 30)
    @Column(name = "duree_iti", length = 30)
    private String dureeITI;

    @Enumerated(EnumType.STRING)
    @Column(name = "observance_iti")
    private Observance observanceITI;

    @Size(max = 30)
    @Column(name = "cause_non_observance_iti", length = 30)
    private String causeNonObservanceITI;

    @Enumerated(EnumType.STRING)
    @Column(name = "evolution_inhibiteur")
    private EvolutionInhibiteur evolutionInhibiteur;

    @Enumerated(EnumType.STRING)
    @Column(name = "prophylaxie_hemlibra")
    private OuiNonNP prophylaxieHemlibra;

    @Column(name = "date_debut_hemlibra")
    private LocalDate dateDebutHemlibra;

    @Enumerated(EnumType.STRING)
    @Column(name = "schema_therapeutique")
    private SchemaTherapeutique schemaTherapeutique;

    @Column(name = "efficacite_hemlibra")
    private Integer efficaciteHemlibra;

    @Enumerated(EnumType.STRING)
    @Column(name = "serologie_hbc")
    private Serologie serologieHBC;

    @Enumerated(EnumType.STRING)
    @Column(name = "serologie_hvc")
    private Serologie serologieHVC;

    @Column(name = "date_decouverte_vhc")
    private LocalDate dateDecouverteVHC;

    @Size(max = 30)
    @Column(name = "charge_virale_vhc", length = 30)
    private String chargeViraleVHC;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_vhc")
    private OuiNonNP traitementVHC;

    @Enumerated(EnumType.STRING)
    @Column(name = "evolution_vhc")
    private EvolutionVHC evolutionVHC;

    @Enumerated(EnumType.STRING)
    @Column(name = "serologie_hiv")
    private Serologie serologieHIV;

    @Enumerated(EnumType.STRING)
    @Column(name = "vie_sociale")
    private VieSociale vieSociale;

    @Enumerated(EnumType.STRING)
    @Column(name = "marie")
    private OuiNonNP marie;

    @Enumerated(EnumType.STRING)
    @Column(name = "enfants")
    private OuiNonNP enfants;

    @Enumerated(EnumType.STRING)
    @Column(name = "handicap")
    private OuiNonNP handicap;

    @Size(max = 30)
    @Column(name = "type_handicap", length = 30)
    private String typeHandicap;

    @Enumerated(EnumType.STRING)
    @Column(name = "activite_sportive")
    private OuiNonNP activiteSportive;

    @Size(max = 30)
    @Column(name = "type_activite_sportive", length = 30)
    private String typeActiviteSportive;

    @Enumerated(EnumType.STRING)
    @Column(name = "decede")
    private OuiNonNP decede;

    @Size(max = 30)
    @Column(name = "cause_date_deces", length = 30)
    private String causeDateDeces;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FicheHemophilie id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDossierNumber() {
        return this.dossierNumber;
    }

    public FicheHemophilie dossierNumber(String dossierNumber) {
        this.setDossierNumber(dossierNumber);
        return this;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return this.ordreNumber;
    }

    public FicheHemophilie ordreNumber(String ordreNumber) {
        this.setOrdreNumber(ordreNumber);
        return this;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return this.indexNumber;
    }

    public FicheHemophilie indexNumber(String indexNumber) {
        this.setIndexNumber(indexNumber);
        return this;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public OuiNonNP getParentsConsanguins() {
        return this.parentsConsanguins;
    }

    public FicheHemophilie parentsConsanguins(OuiNonNP parentsConsanguins) {
        this.setParentsConsanguins(parentsConsanguins);
        return this;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public String getDegreParenteConsanguins() {
        return this.degreParenteConsanguins;
    }

    public FicheHemophilie degreParenteConsanguins(String degreParenteConsanguins) {
        this.setDegreParenteConsanguins(degreParenteConsanguins);
        return this;
    }

    public void setDegreParenteConsanguins(String degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public OuiNonNP getCasSimilairesFamille() {
        return this.casSimilairesFamille;
    }

    public FicheHemophilie casSimilairesFamille(OuiNonNP casSimilairesFamille) {
        this.setCasSimilairesFamille(casSimilairesFamille);
        return this;
    }

    public void setCasSimilairesFamille(OuiNonNP casSimilairesFamille) {
        this.casSimilairesFamille = casSimilairesFamille;
    }

    public Integer getNbreCasSimilaires() {
        return this.nbreCasSimilaires;
    }

    public FicheHemophilie nbreCasSimilaires(Integer nbreCasSimilaires) {
        this.setNbreCasSimilaires(nbreCasSimilaires);
        return this;
    }

    public void setNbreCasSimilaires(Integer nbreCasSimilaires) {
        this.nbreCasSimilaires = nbreCasSimilaires;
    }

    public String getDegreParenteCasSimilaires() {
        return this.degreParenteCasSimilaires;
    }

    public FicheHemophilie degreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.setDegreParenteCasSimilaires(degreParenteCasSimilaires);
        return this;
    }

    public void setDegreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.degreParenteCasSimilaires = degreParenteCasSimilaires;
    }

    public OuiNonNP getCasDecesSyndromeHemorragique() {
        return this.casDecesSyndromeHemorragique;
    }

    public FicheHemophilie casDecesSyndromeHemorragique(OuiNonNP casDecesSyndromeHemorragique) {
        this.setCasDecesSyndromeHemorragique(casDecesSyndromeHemorragique);
        return this;
    }

    public void setCasDecesSyndromeHemorragique(OuiNonNP casDecesSyndromeHemorragique) {
        this.casDecesSyndromeHemorragique = casDecesSyndromeHemorragique;
    }

    public Integer getNbreCasDeces() {
        return this.nbreCasDeces;
    }

    public FicheHemophilie nbreCasDeces(Integer nbreCasDeces) {
        this.setNbreCasDeces(nbreCasDeces);
        return this;
    }

    public void setNbreCasDeces(Integer nbreCasDeces) {
        this.nbreCasDeces = nbreCasDeces;
    }

    public FormeHemophilie getFormeHemophilie() {
        return this.formeHemophilie;
    }

    public FicheHemophilie formeHemophilie(FormeHemophilie formeHemophilie) {
        this.setFormeHemophilie(formeHemophilie);
        return this;
    }

    public void setFormeHemophilie(FormeHemophilie formeHemophilie) {
        this.formeHemophilie = formeHemophilie;
    }

    public Integer getNbreFreres() {
        return this.nbreFreres;
    }

    public FicheHemophilie nbreFreres(Integer nbreFreres) {
        this.setNbreFreres(nbreFreres);
        return this;
    }

    public void setNbreFreres(Integer nbreFreres) {
        this.nbreFreres = nbreFreres;
    }

    public Integer getNbreSoeurs() {
        return this.nbreSoeurs;
    }

    public FicheHemophilie nbreSoeurs(Integer nbreSoeurs) {
        this.setNbreSoeurs(nbreSoeurs);
        return this;
    }

    public void setNbreSoeurs(Integer nbreSoeurs) {
        this.nbreSoeurs = nbreSoeurs;
    }

    public CircumstanceDecouverte getCirconstanceDecouverte() {
        return this.circonstanceDecouverte;
    }

    public FicheHemophilie circonstanceDecouverte(CircumstanceDecouverte circonstanceDecouverte) {
        this.setCirconstanceDecouverte(circonstanceDecouverte);
        return this;
    }

    public void setCirconstanceDecouverte(CircumstanceDecouverte circonstanceDecouverte) {
        this.circonstanceDecouverte = circonstanceDecouverte;
    }

    public LocalDate getDateTestConfirmation() {
        return this.dateTestConfirmation;
    }

    public FicheHemophilie dateTestConfirmation(LocalDate dateTestConfirmation) {
        this.setDateTestConfirmation(dateTestConfirmation);
        return this;
    }

    public void setDateTestConfirmation(LocalDate dateTestConfirmation) {
        this.dateTestConfirmation = dateTestConfirmation;
    }

    public Double getHemogrammeHb() {
        return this.hemogrammeHb;
    }

    public FicheHemophilie hemogrammeHb(Double hemogrammeHb) {
        this.setHemogrammeHb(hemogrammeHb);
        return this;
    }

    public void setHemogrammeHb(Double hemogrammeHb) {
        this.hemogrammeHb = hemogrammeHb;
    }

    public Double getHemogrammeHt() {
        return this.hemogrammeHt;
    }

    public FicheHemophilie hemogrammeHt(Double hemogrammeHt) {
        this.setHemogrammeHt(hemogrammeHt);
        return this;
    }

    public void setHemogrammeHt(Double hemogrammeHt) {
        this.hemogrammeHt = hemogrammeHt;
    }

    public Integer getPlaquettes() {
        return this.plaquettes;
    }

    public FicheHemophilie plaquettes(Integer plaquettes) {
        this.setPlaquettes(plaquettes);
        return this;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Double getTp() {
        return this.tp;
    }

    public FicheHemophilie tp(Double tp) {
        this.setTp(tp);
        return this;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getFibrinogene() {
        return this.fibrinogene;
    }

    public FicheHemophilie fibrinogene(Double fibrinogene) {
        this.setFibrinogene(fibrinogene);
        return this;
    }

    public void setFibrinogene(Double fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public Double getTcaMT() {
        return this.tcaMT;
    }

    public FicheHemophilie tcaMT(Double tcaMT) {
        this.setTcaMT(tcaMT);
        return this;
    }

    public void setTcaMT(Double tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Double getTcaMT2h() {
        return this.tcaMT2h;
    }

    public FicheHemophilie tcaMT2h(Double tcaMT2h) {
        this.setTcaMT2h(tcaMT2h);
        return this;
    }

    public void setTcaMT2h(Double tcaMT2h) {
        this.tcaMT2h = tcaMT2h;
    }

    public Double getTcaTemoin2h() {
        return this.tcaTemoin2h;
    }

    public FicheHemophilie tcaTemoin2h(Double tcaTemoin2h) {
        this.setTcaTemoin2h(tcaTemoin2h);
        return this;
    }

    public void setTcaTemoin2h(Double tcaTemoin2h) {
        this.tcaTemoin2h = tcaTemoin2h;
    }

    public TestStatus getTs() {
        return this.ts;
    }

    public FicheHemophilie ts(TestStatus ts) {
        this.setTs(ts);
        return this;
    }

    public void setTs(TestStatus ts) {
        this.ts = ts;
    }

    public TestStatus getPfa() {
        return this.pfa;
    }

    public FicheHemophilie pfa(TestStatus pfa) {
        this.setPfa(pfa);
        return this;
    }

    public void setPfa(TestStatus pfa) {
        this.pfa = pfa;
    }

    public Double getDosageFacteurVIII() {
        return this.dosageFacteurVIII;
    }

    public FicheHemophilie dosageFacteurVIII(Double dosageFacteurVIII) {
        this.setDosageFacteurVIII(dosageFacteurVIII);
        return this;
    }

    public void setDosageFacteurVIII(Double dosageFacteurVIII) {
        this.dosageFacteurVIII = dosageFacteurVIII;
    }

    public Double getDosageFacteurIX() {
        return this.dosageFacteurIX;
    }

    public FicheHemophilie dosageFacteurIX(Double dosageFacteurIX) {
        this.setDosageFacteurIX(dosageFacteurIX);
        return this;
    }

    public void setDosageFacteurIX(Double dosageFacteurIX) {
        this.dosageFacteurIX = dosageFacteurIX;
    }

    public Double getDosageFacteurVWActivite() {
        return this.dosageFacteurVWActivite;
    }

    public FicheHemophilie dosageFacteurVWActivite(Double dosageFacteurVWActivite) {
        this.setDosageFacteurVWActivite(dosageFacteurVWActivite);
        return this;
    }

    public void setDosageFacteurVWActivite(Double dosageFacteurVWActivite) {
        this.dosageFacteurVWActivite = dosageFacteurVWActivite;
    }

    public Double getDosageFacteurVWAntigene() {
        return this.dosageFacteurVWAntigene;
    }

    public FicheHemophilie dosageFacteurVWAntigene(Double dosageFacteurVWAntigene) {
        this.setDosageFacteurVWAntigene(dosageFacteurVWAntigene);
        return this;
    }

    public void setDosageFacteurVWAntigene(Double dosageFacteurVWAntigene) {
        this.dosageFacteurVWAntigene = dosageFacteurVWAntigene;
    }

    public FormeClinique getFormeClinique() {
        return this.formeClinique;
    }

    public FicheHemophilie formeClinique(FormeClinique formeClinique) {
        this.setFormeClinique(formeClinique);
        return this;
    }

    public void setFormeClinique(FormeClinique formeClinique) {
        this.formeClinique = formeClinique;
    }

    public Severite getSeverite() {
        return this.severite;
    }

    public FicheHemophilie severite(Severite severite) {
        this.setSeverite(severite);
        return this;
    }

    public void setSeverite(Severite severite) {
        this.severite = severite;
    }

    public String getExplorationMoleculaire() {
        return this.explorationMoleculaire;
    }

    public FicheHemophilie explorationMoleculaire(String explorationMoleculaire) {
        this.setExplorationMoleculaire(explorationMoleculaire);
        return this;
    }

    public void setExplorationMoleculaire(String explorationMoleculaire) {
        this.explorationMoleculaire = explorationMoleculaire;
    }

    public OuiNonNP getHemarthrose() {
        return this.hemarthrose;
    }

    public FicheHemophilie hemarthrose(OuiNonNP hemarthrose) {
        this.setHemarthrose(hemarthrose);
        return this;
    }

    public void setHemarthrose(OuiNonNP hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public ArticulationCible getSiegeHemarthrose() {
        return this.siegeHemarthrose;
    }

    public FicheHemophilie siegeHemarthrose(ArticulationCible siegeHemarthrose) {
        this.setSiegeHemarthrose(siegeHemarthrose);
        return this;
    }

    public void setSiegeHemarthrose(ArticulationCible siegeHemarthrose) {
        this.siegeHemarthrose = siegeHemarthrose;
    }

    public TypeManifestation getCirconstanceHemarthrose() {
        return this.circonstanceHemarthrose;
    }

    public FicheHemophilie circonstanceHemarthrose(TypeManifestation circonstanceHemarthrose) {
        this.setCirconstanceHemarthrose(circonstanceHemarthrose);
        return this;
    }

    public void setCirconstanceHemarthrose(TypeManifestation circonstanceHemarthrose) {
        this.circonstanceHemarthrose = circonstanceHemarthrose;
    }

    public Integer getFrequenceHemarthrose() {
        return this.frequenceHemarthrose;
    }

    public FicheHemophilie frequenceHemarthrose(Integer frequenceHemarthrose) {
        this.setFrequenceHemarthrose(frequenceHemarthrose);
        return this;
    }

    public void setFrequenceHemarthrose(Integer frequenceHemarthrose) {
        this.frequenceHemarthrose = frequenceHemarthrose;
    }

    public OuiNonNP getHematomeSuperficiel() {
        return this.hematomeSuperficiel;
    }

    public FicheHemophilie hematomeSuperficiel(OuiNonNP hematomeSuperficiel) {
        this.setHematomeSuperficiel(hematomeSuperficiel);
        return this;
    }

    public void setHematomeSuperficiel(OuiNonNP hematomeSuperficiel) {
        this.hematomeSuperficiel = hematomeSuperficiel;
    }

    public String getCirconstanceHematomeSuperficiel() {
        return this.circonstanceHematomeSuperficiel;
    }

    public FicheHemophilie circonstanceHematomeSuperficiel(String circonstanceHematomeSuperficiel) {
        this.setCirconstanceHematomeSuperficiel(circonstanceHematomeSuperficiel);
        return this;
    }

    public void setCirconstanceHematomeSuperficiel(String circonstanceHematomeSuperficiel) {
        this.circonstanceHematomeSuperficiel = circonstanceHematomeSuperficiel;
    }

    public String getSiegeHematomeSuperficiel() {
        return this.siegeHematomeSuperficiel;
    }

    public FicheHemophilie siegeHematomeSuperficiel(String siegeHematomeSuperficiel) {
        this.setSiegeHematomeSuperficiel(siegeHematomeSuperficiel);
        return this;
    }

    public void setSiegeHematomeSuperficiel(String siegeHematomeSuperficiel) {
        this.siegeHematomeSuperficiel = siegeHematomeSuperficiel;
    }

    public OuiNonNP getHematomePsoas() {
        return this.hematomePsoas;
    }

    public FicheHemophilie hematomePsoas(OuiNonNP hematomePsoas) {
        this.setHematomePsoas(hematomePsoas);
        return this;
    }

    public void setHematomePsoas(OuiNonNP hematomePsoas) {
        this.hematomePsoas = hematomePsoas;
    }

    public String getCirconstanceHematomePsoas() {
        return this.circonstanceHematomePsoas;
    }

    public FicheHemophilie circonstanceHematomePsoas(String circonstanceHematomePsoas) {
        this.setCirconstanceHematomePsoas(circonstanceHematomePsoas);
        return this;
    }

    public void setCirconstanceHematomePsoas(String circonstanceHematomePsoas) {
        this.circonstanceHematomePsoas = circonstanceHematomePsoas;
    }

    public OuiNonNP getRecidiveHematomePsoas() {
        return this.recidiveHematomePsoas;
    }

    public FicheHemophilie recidiveHematomePsoas(OuiNonNP recidiveHematomePsoas) {
        this.setRecidiveHematomePsoas(recidiveHematomePsoas);
        return this;
    }

    public void setRecidiveHematomePsoas(OuiNonNP recidiveHematomePsoas) {
        this.recidiveHematomePsoas = recidiveHematomePsoas;
    }

    public OuiNonNP getHemorragiesCutaneoMuqueuses() {
        return this.hemorragiesCutaneoMuqueuses;
    }

    public FicheHemophilie hemorragiesCutaneoMuqueuses(OuiNonNP hemorragiesCutaneoMuqueuses) {
        this.setHemorragiesCutaneoMuqueuses(hemorragiesCutaneoMuqueuses);
        return this;
    }

    public void setHemorragiesCutaneoMuqueuses(OuiNonNP hemorragiesCutaneoMuqueuses) {
        this.hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueuses;
    }

    public String getTypeHemorragiesCutaneoMuqueuses() {
        return this.typeHemorragiesCutaneoMuqueuses;
    }

    public FicheHemophilie typeHemorragiesCutaneoMuqueuses(String typeHemorragiesCutaneoMuqueuses) {
        this.setTypeHemorragiesCutaneoMuqueuses(typeHemorragiesCutaneoMuqueuses);
        return this;
    }

    public void setTypeHemorragiesCutaneoMuqueuses(String typeHemorragiesCutaneoMuqueuses) {
        this.typeHemorragiesCutaneoMuqueuses = typeHemorragiesCutaneoMuqueuses;
    }

    public Integer getFrequenceHemorragiesCutaneoMuqueuses() {
        return this.frequenceHemorragiesCutaneoMuqueuses;
    }

    public FicheHemophilie frequenceHemorragiesCutaneoMuqueuses(Integer frequenceHemorragiesCutaneoMuqueuses) {
        this.setFrequenceHemorragiesCutaneoMuqueuses(frequenceHemorragiesCutaneoMuqueuses);
        return this;
    }

    public void setFrequenceHemorragiesCutaneoMuqueuses(Integer frequenceHemorragiesCutaneoMuqueuses) {
        this.frequenceHemorragiesCutaneoMuqueuses = frequenceHemorragiesCutaneoMuqueuses;
    }

    public OuiNonNP getHemorragieVisceres() {
        return this.hemorragieVisceres;
    }

    public FicheHemophilie hemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.setHemorragieVisceres(hemorragieVisceres);
        return this;
    }

    public void setHemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.hemorragieVisceres = hemorragieVisceres;
    }

    public String getTypeHemorragieVisceres() {
        return this.typeHemorragieVisceres;
    }

    public FicheHemophilie typeHemorragieVisceres(String typeHemorragieVisceres) {
        this.setTypeHemorragieVisceres(typeHemorragieVisceres);
        return this;
    }

    public void setTypeHemorragieVisceres(String typeHemorragieVisceres) {
        this.typeHemorragieVisceres = typeHemorragieVisceres;
    }

    public OuiNonNP getExplorationHemorragieVisceres() {
        return this.explorationHemorragieVisceres;
    }

    public FicheHemophilie explorationHemorragieVisceres(OuiNonNP explorationHemorragieVisceres) {
        this.setExplorationHemorragieVisceres(explorationHemorragieVisceres);
        return this;
    }

    public void setExplorationHemorragieVisceres(OuiNonNP explorationHemorragieVisceres) {
        this.explorationHemorragieVisceres = explorationHemorragieVisceres;
    }

    public String getExamenLesionHemorragieVisceres() {
        return this.examenLesionHemorragieVisceres;
    }

    public FicheHemophilie examenLesionHemorragieVisceres(String examenLesionHemorragieVisceres) {
        this.setExamenLesionHemorragieVisceres(examenLesionHemorragieVisceres);
        return this;
    }

    public void setExamenLesionHemorragieVisceres(String examenLesionHemorragieVisceres) {
        this.examenLesionHemorragieVisceres = examenLesionHemorragieVisceres;
    }

    public OuiNonNP getSaignementSNC() {
        return this.saignementSNC;
    }

    public FicheHemophilie saignementSNC(OuiNonNP saignementSNC) {
        this.setSaignementSNC(saignementSNC);
        return this;
    }

    public void setSaignementSNC(OuiNonNP saignementSNC) {
        this.saignementSNC = saignementSNC;
    }

    public EvolutionSNC getEvolutionSNC() {
        return this.evolutionSNC;
    }

    public FicheHemophilie evolutionSNC(EvolutionSNC evolutionSNC) {
        this.setEvolutionSNC(evolutionSNC);
        return this;
    }

    public void setEvolutionSNC(EvolutionSNC evolutionSNC) {
        this.evolutionSNC = evolutionSNC;
    }

    public OuiNonNP getCirconcision() {
        return this.circoncision;
    }

    public FicheHemophilie circoncision(OuiNonNP circoncision) {
        this.setCirconcision(circoncision);
        return this;
    }

    public void setCirconcision(OuiNonNP circoncision) {
        this.circoncision = circoncision;
    }

    public TypePreparation getTypePreparationCirconcision() {
        return this.typePreparationCirconcision;
    }

    public FicheHemophilie typePreparationCirconcision(TypePreparation typePreparationCirconcision) {
        this.setTypePreparationCirconcision(typePreparationCirconcision);
        return this;
    }

    public void setTypePreparationCirconcision(TypePreparation typePreparationCirconcision) {
        this.typePreparationCirconcision = typePreparationCirconcision;
    }

    public SuiteOperatoire getSuiteOperatoireCirconcision() {
        return this.suiteOperatoireCirconcision;
    }

    public FicheHemophilie suiteOperatoireCirconcision(SuiteOperatoire suiteOperatoireCirconcision) {
        this.setSuiteOperatoireCirconcision(suiteOperatoireCirconcision);
        return this;
    }

    public void setSuiteOperatoireCirconcision(SuiteOperatoire suiteOperatoireCirconcision) {
        this.suiteOperatoireCirconcision = suiteOperatoireCirconcision;
    }

    public Integer getAgeCirconcision() {
        return this.ageCirconcision;
    }

    public FicheHemophilie ageCirconcision(Integer ageCirconcision) {
        this.setAgeCirconcision(ageCirconcision);
        return this;
    }

    public void setAgeCirconcision(Integer ageCirconcision) {
        this.ageCirconcision = ageCirconcision;
    }

    public OuiNonNP getAvulsionDentaire() {
        return this.avulsionDentaire;
    }

    public FicheHemophilie avulsionDentaire(OuiNonNP avulsionDentaire) {
        this.setAvulsionDentaire(avulsionDentaire);
        return this;
    }

    public void setAvulsionDentaire(OuiNonNP avulsionDentaire) {
        this.avulsionDentaire = avulsionDentaire;
    }

    public TypePreparation getTypePreparationAvulsionDentaire() {
        return this.typePreparationAvulsionDentaire;
    }

    public FicheHemophilie typePreparationAvulsionDentaire(TypePreparation typePreparationAvulsionDentaire) {
        this.setTypePreparationAvulsionDentaire(typePreparationAvulsionDentaire);
        return this;
    }

    public void setTypePreparationAvulsionDentaire(TypePreparation typePreparationAvulsionDentaire) {
        this.typePreparationAvulsionDentaire = typePreparationAvulsionDentaire;
    }

    public OuiNonNP getInterventionChirurgicale() {
        return this.interventionChirurgicale;
    }

    public FicheHemophilie interventionChirurgicale(OuiNonNP interventionChirurgicale) {
        this.setInterventionChirurgicale(interventionChirurgicale);
        return this;
    }

    public void setInterventionChirurgicale(OuiNonNP interventionChirurgicale) {
        this.interventionChirurgicale = interventionChirurgicale;
    }

    public String getTypeInterventionChirurgicale() {
        return this.typeInterventionChirurgicale;
    }

    public FicheHemophilie typeInterventionChirurgicale(String typeInterventionChirurgicale) {
        this.setTypeInterventionChirurgicale(typeInterventionChirurgicale);
        return this;
    }

    public void setTypeInterventionChirurgicale(String typeInterventionChirurgicale) {
        this.typeInterventionChirurgicale = typeInterventionChirurgicale;
    }

    public OuiNonNP getAccidentsHemorragiquesGraves() {
        return this.accidentsHemorragiquesGraves;
    }

    public FicheHemophilie accidentsHemorragiquesGraves(OuiNonNP accidentsHemorragiquesGraves) {
        this.setAccidentsHemorragiquesGraves(accidentsHemorragiquesGraves);
        return this;
    }

    public void setAccidentsHemorragiquesGraves(OuiNonNP accidentsHemorragiquesGraves) {
        this.accidentsHemorragiquesGraves = accidentsHemorragiquesGraves;
    }

    public String getCirconstanceAccidentsHemorragiquesGraves() {
        return this.circonstanceAccidentsHemorragiquesGraves;
    }

    public FicheHemophilie circonstanceAccidentsHemorragiquesGraves(String circonstanceAccidentsHemorragiquesGraves) {
        this.setCirconstanceAccidentsHemorragiquesGraves(circonstanceAccidentsHemorragiquesGraves);
        return this;
    }

    public void setCirconstanceAccidentsHemorragiquesGraves(String circonstanceAccidentsHemorragiquesGraves) {
        this.circonstanceAccidentsHemorragiquesGraves = circonstanceAccidentsHemorragiquesGraves;
    }

    public PriseEnChargeType getPriseEnCharge() {
        return this.priseEnCharge;
    }

    public FicheHemophilie priseEnCharge(PriseEnChargeType priseEnCharge) {
        this.setPriseEnCharge(priseEnCharge);
        return this;
    }

    public void setPriseEnCharge(PriseEnChargeType priseEnCharge) {
        this.priseEnCharge = priseEnCharge;
    }

    public String getCausePriseEnCharge() {
        return this.causePriseEnCharge;
    }

    public FicheHemophilie causePriseEnCharge(String causePriseEnCharge) {
        this.setCausePriseEnCharge(causePriseEnCharge);
        return this;
    }

    public void setCausePriseEnCharge(String causePriseEnCharge) {
        this.causePriseEnCharge = causePriseEnCharge;
    }

    public String getDoseProphylaxie() {
        return this.doseProphylaxie;
    }

    public FicheHemophilie doseProphylaxie(String doseProphylaxie) {
        this.setDoseProphylaxie(doseProphylaxie);
        return this;
    }

    public void setDoseProphylaxie(String doseProphylaxie) {
        this.doseProphylaxie = doseProphylaxie;
    }

    public Integer getFrequenceProphylaxie() {
        return this.frequenceProphylaxie;
    }

    public FicheHemophilie frequenceProphylaxie(Integer frequenceProphylaxie) {
        this.setFrequenceProphylaxie(frequenceProphylaxie);
        return this;
    }

    public void setFrequenceProphylaxie(Integer frequenceProphylaxie) {
        this.frequenceProphylaxie = frequenceProphylaxie;
    }

    public LocalDate getDebutProphylaxie() {
        return this.debutProphylaxie;
    }

    public FicheHemophilie debutProphylaxie(LocalDate debutProphylaxie) {
        this.setDebutProphylaxie(debutProphylaxie);
        return this;
    }

    public void setDebutProphylaxie(LocalDate debutProphylaxie) {
        this.debutProphylaxie = debutProphylaxie;
    }

    public InjectionType getModaliteInjection() {
        return this.modaliteInjection;
    }

    public FicheHemophilie modaliteInjection(InjectionType modaliteInjection) {
        this.setModaliteInjection(modaliteInjection);
        return this;
    }

    public void setModaliteInjection(InjectionType modaliteInjection) {
        this.modaliteInjection = modaliteInjection;
    }

    public TraitementType getTypeTraitementSubstitutif() {
        return this.typeTraitementSubstitutif;
    }

    public FicheHemophilie typeTraitementSubstitutif(TraitementType typeTraitementSubstitutif) {
        this.setTypeTraitementSubstitutif(typeTraitementSubstitutif);
        return this;
    }

    public void setTypeTraitementSubstitutif(TraitementType typeTraitementSubstitutif) {
        this.typeTraitementSubstitutif = typeTraitementSubstitutif;
    }

    public Integer getAge1ereSubstitution() {
        return this.age1ereSubstitution;
    }

    public FicheHemophilie age1ereSubstitution(Integer age1ereSubstitution) {
        this.setAge1ereSubstitution(age1ereSubstitution);
        return this;
    }

    public void setAge1ereSubstitution(Integer age1ereSubstitution) {
        this.age1ereSubstitution = age1ereSubstitution;
    }

    public OuiNonNP getAntecedentsPSL() {
        return this.antecedentsPSL;
    }

    public FicheHemophilie antecedentsPSL(OuiNonNP antecedentsPSL) {
        this.setAntecedentsPSL(antecedentsPSL);
        return this;
    }

    public void setAntecedentsPSL(OuiNonNP antecedentsPSL) {
        this.antecedentsPSL = antecedentsPSL;
    }

    public OuiNonNP getPlasmaFraisCongele() {
        return this.plasmaFraisCongele;
    }

    public FicheHemophilie plasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.setPlasmaFraisCongele(plasmaFraisCongele);
        return this;
    }

    public void setPlasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.plasmaFraisCongele = plasmaFraisCongele;
    }

    public OuiNonNP getCryoprecipite() {
        return this.cryoprecipite;
    }

    public FicheHemophilie cryoprecipite(OuiNonNP cryoprecipite) {
        this.setCryoprecipite(cryoprecipite);
        return this;
    }

    public void setCryoprecipite(OuiNonNP cryoprecipite) {
        this.cryoprecipite = cryoprecipite;
    }

    public OuiNonNP getComplicationsOrthopediques() {
        return this.complicationsOrthopediques;
    }

    public FicheHemophilie complicationsOrthopediques(OuiNonNP complicationsOrthopediques) {
        this.setComplicationsOrthopediques(complicationsOrthopediques);
        return this;
    }

    public void setComplicationsOrthopediques(OuiNonNP complicationsOrthopediques) {
        this.complicationsOrthopediques = complicationsOrthopediques;
    }

    public TypeComplicationsOrthopediques getTypeComplicationsOrthopediques() {
        return this.typeComplicationsOrthopediques;
    }

    public FicheHemophilie typeComplicationsOrthopediques(TypeComplicationsOrthopediques typeComplicationsOrthopediques) {
        this.setTypeComplicationsOrthopediques(typeComplicationsOrthopediques);
        return this;
    }

    public void setTypeComplicationsOrthopediques(TypeComplicationsOrthopediques typeComplicationsOrthopediques) {
        this.typeComplicationsOrthopediques = typeComplicationsOrthopediques;
    }

    public ArticulationCible getArticulationCible() {
        return this.articulationCible;
    }

    public FicheHemophilie articulationCible(ArticulationCible articulationCible) {
        this.setArticulationCible(articulationCible);
        return this;
    }

    public void setArticulationCible(ArticulationCible articulationCible) {
        this.articulationCible = articulationCible;
    }

    public PECTherapeutique getPecTherapeutique() {
        return this.pecTherapeutique;
    }

    public FicheHemophilie pecTherapeutique(PECTherapeutique pecTherapeutique) {
        this.setPecTherapeutique(pecTherapeutique);
        return this;
    }

    public void setPecTherapeutique(PECTherapeutique pecTherapeutique) {
        this.pecTherapeutique = pecTherapeutique;
    }

    public OuiNonNP getComplicationsInhibiteurs() {
        return this.complicationsInhibiteurs;
    }

    public FicheHemophilie complicationsInhibiteurs(OuiNonNP complicationsInhibiteurs) {
        this.setComplicationsInhibiteurs(complicationsInhibiteurs);
        return this;
    }

    public void setComplicationsInhibiteurs(OuiNonNP complicationsInhibiteurs) {
        this.complicationsInhibiteurs = complicationsInhibiteurs;
    }

    public TauxInhibiteur getTauxInhibiteur() {
        return this.tauxInhibiteur;
    }

    public FicheHemophilie tauxInhibiteur(TauxInhibiteur tauxInhibiteur) {
        this.setTauxInhibiteur(tauxInhibiteur);
        return this;
    }

    public void setTauxInhibiteur(TauxInhibiteur tauxInhibiteur) {
        this.tauxInhibiteur = tauxInhibiteur;
    }

    public TestStatus getTestRecuperationFAH() {
        return this.testRecuperationFAH;
    }

    public FicheHemophilie testRecuperationFAH(TestStatus testRecuperationFAH) {
        this.setTestRecuperationFAH(testRecuperationFAH);
        return this;
    }

    public void setTestRecuperationFAH(TestStatus testRecuperationFAH) {
        this.testRecuperationFAH = testRecuperationFAH;
    }

    public LocalDate getDateRecuperationFAH() {
        return this.dateRecuperationFAH;
    }

    public FicheHemophilie dateRecuperationFAH(LocalDate dateRecuperationFAH) {
        this.setDateRecuperationFAH(dateRecuperationFAH);
        return this;
    }

    public void setDateRecuperationFAH(LocalDate dateRecuperationFAH) {
        this.dateRecuperationFAH = dateRecuperationFAH;
    }

    public TauxRecuperation getTauxRecuperation() {
        return this.tauxRecuperation;
    }

    public FicheHemophilie tauxRecuperation(TauxRecuperation tauxRecuperation) {
        this.setTauxRecuperation(tauxRecuperation);
        return this;
    }

    public void setTauxRecuperation(TauxRecuperation tauxRecuperation) {
        this.tauxRecuperation = tauxRecuperation;
    }

    public OuiNonNP getAntiFAHNonNeutralisant() {
        return this.antiFAHNonNeutralisant;
    }

    public FicheHemophilie antiFAHNonNeutralisant(OuiNonNP antiFAHNonNeutralisant) {
        this.setAntiFAHNonNeutralisant(antiFAHNonNeutralisant);
        return this;
    }

    public void setAntiFAHNonNeutralisant(OuiNonNP antiFAHNonNeutralisant) {
        this.antiFAHNonNeutralisant = antiFAHNonNeutralisant;
    }

    public RechercheACC getRechercheACC() {
        return this.rechercheACC;
    }

    public FicheHemophilie rechercheACC(RechercheACC rechercheACC) {
        this.setRechercheACC(rechercheACC);
        return this;
    }

    public void setRechercheACC(RechercheACC rechercheACC) {
        this.rechercheACC = rechercheACC;
    }

    public CATTherapeutique getCatTherapeutique() {
        return this.catTherapeutique;
    }

    public FicheHemophilie catTherapeutique(CATTherapeutique catTherapeutique) {
        this.setCatTherapeutique(catTherapeutique);
        return this;
    }

    public void setCatTherapeutique(CATTherapeutique catTherapeutique) {
        this.catTherapeutique = catTherapeutique;
    }

    public OuiNonNP getIti() {
        return this.iti;
    }

    public FicheHemophilie iti(OuiNonNP iti) {
        this.setIti(iti);
        return this;
    }

    public void setIti(OuiNonNP iti) {
        this.iti = iti;
    }

    public String getPosologieITI() {
        return this.posologieITI;
    }

    public FicheHemophilie posologieITI(String posologieITI) {
        this.setPosologieITI(posologieITI);
        return this;
    }

    public void setPosologieITI(String posologieITI) {
        this.posologieITI = posologieITI;
    }

    public String getDureeITI() {
        return this.dureeITI;
    }

    public FicheHemophilie dureeITI(String dureeITI) {
        this.setDureeITI(dureeITI);
        return this;
    }

    public void setDureeITI(String dureeITI) {
        this.dureeITI = dureeITI;
    }

    public Observance getObservanceITI() {
        return this.observanceITI;
    }

    public FicheHemophilie observanceITI(Observance observanceITI) {
        this.setObservanceITI(observanceITI);
        return this;
    }

    public void setObservanceITI(Observance observanceITI) {
        this.observanceITI = observanceITI;
    }

    public String getCauseNonObservanceITI() {
        return this.causeNonObservanceITI;
    }

    public FicheHemophilie causeNonObservanceITI(String causeNonObservanceITI) {
        this.setCauseNonObservanceITI(causeNonObservanceITI);
        return this;
    }

    public void setCauseNonObservanceITI(String causeNonObservanceITI) {
        this.causeNonObservanceITI = causeNonObservanceITI;
    }

    public EvolutionInhibiteur getEvolutionInhibiteur() {
        return this.evolutionInhibiteur;
    }

    public FicheHemophilie evolutionInhibiteur(EvolutionInhibiteur evolutionInhibiteur) {
        this.setEvolutionInhibiteur(evolutionInhibiteur);
        return this;
    }

    public void setEvolutionInhibiteur(EvolutionInhibiteur evolutionInhibiteur) {
        this.evolutionInhibiteur = evolutionInhibiteur;
    }

    public OuiNonNP getProphylaxieHemlibra() {
        return this.prophylaxieHemlibra;
    }

    public FicheHemophilie prophylaxieHemlibra(OuiNonNP prophylaxieHemlibra) {
        this.setProphylaxieHemlibra(prophylaxieHemlibra);
        return this;
    }

    public void setProphylaxieHemlibra(OuiNonNP prophylaxieHemlibra) {
        this.prophylaxieHemlibra = prophylaxieHemlibra;
    }

    public LocalDate getDateDebutHemlibra() {
        return this.dateDebutHemlibra;
    }

    public FicheHemophilie dateDebutHemlibra(LocalDate dateDebutHemlibra) {
        this.setDateDebutHemlibra(dateDebutHemlibra);
        return this;
    }

    public void setDateDebutHemlibra(LocalDate dateDebutHemlibra) {
        this.dateDebutHemlibra = dateDebutHemlibra;
    }

    public SchemaTherapeutique getSchemaTherapeutique() {
        return this.schemaTherapeutique;
    }

    public FicheHemophilie schemaTherapeutique(SchemaTherapeutique schemaTherapeutique) {
        this.setSchemaTherapeutique(schemaTherapeutique);
        return this;
    }

    public void setSchemaTherapeutique(SchemaTherapeutique schemaTherapeutique) {
        this.schemaTherapeutique = schemaTherapeutique;
    }

    public Integer getEfficaciteHemlibra() {
        return this.efficaciteHemlibra;
    }

    public FicheHemophilie efficaciteHemlibra(Integer efficaciteHemlibra) {
        this.setEfficaciteHemlibra(efficaciteHemlibra);
        return this;
    }

    public void setEfficaciteHemlibra(Integer efficaciteHemlibra) {
        this.efficaciteHemlibra = efficaciteHemlibra;
    }

    public Serologie getSerologieHBC() {
        return this.serologieHBC;
    }

    public FicheHemophilie serologieHBC(Serologie serologieHBC) {
        this.setSerologieHBC(serologieHBC);
        return this;
    }

    public void setSerologieHBC(Serologie serologieHBC) {
        this.serologieHBC = serologieHBC;
    }

    public Serologie getSerologieHVC() {
        return this.serologieHVC;
    }

    public FicheHemophilie serologieHVC(Serologie serologieHVC) {
        this.setSerologieHVC(serologieHVC);
        return this;
    }

    public void setSerologieHVC(Serologie serologieHVC) {
        this.serologieHVC = serologieHVC;
    }

    public LocalDate getDateDecouverteVHC() {
        return this.dateDecouverteVHC;
    }

    public FicheHemophilie dateDecouverteVHC(LocalDate dateDecouverteVHC) {
        this.setDateDecouverteVHC(dateDecouverteVHC);
        return this;
    }

    public void setDateDecouverteVHC(LocalDate dateDecouverteVHC) {
        this.dateDecouverteVHC = dateDecouverteVHC;
    }

    public String getChargeViraleVHC() {
        return this.chargeViraleVHC;
    }

    public FicheHemophilie chargeViraleVHC(String chargeViraleVHC) {
        this.setChargeViraleVHC(chargeViraleVHC);
        return this;
    }

    public void setChargeViraleVHC(String chargeViraleVHC) {
        this.chargeViraleVHC = chargeViraleVHC;
    }

    public OuiNonNP getTraitementVHC() {
        return this.traitementVHC;
    }

    public FicheHemophilie traitementVHC(OuiNonNP traitementVHC) {
        this.setTraitementVHC(traitementVHC);
        return this;
    }

    public void setTraitementVHC(OuiNonNP traitementVHC) {
        this.traitementVHC = traitementVHC;
    }

    public EvolutionVHC getEvolutionVHC() {
        return this.evolutionVHC;
    }

    public FicheHemophilie evolutionVHC(EvolutionVHC evolutionVHC) {
        this.setEvolutionVHC(evolutionVHC);
        return this;
    }

    public void setEvolutionVHC(EvolutionVHC evolutionVHC) {
        this.evolutionVHC = evolutionVHC;
    }

    public Serologie getSerologieHIV() {
        return this.serologieHIV;
    }

    public FicheHemophilie serologieHIV(Serologie serologieHIV) {
        this.setSerologieHIV(serologieHIV);
        return this;
    }

    public void setSerologieHIV(Serologie serologieHIV) {
        this.serologieHIV = serologieHIV;
    }

    public VieSociale getVieSociale() {
        return this.vieSociale;
    }

    public FicheHemophilie vieSociale(VieSociale vieSociale) {
        this.setVieSociale(vieSociale);
        return this;
    }

    public void setVieSociale(VieSociale vieSociale) {
        this.vieSociale = vieSociale;
    }

    public OuiNonNP getMarie() {
        return this.marie;
    }

    public FicheHemophilie marie(OuiNonNP marie) {
        this.setMarie(marie);
        return this;
    }

    public void setMarie(OuiNonNP marie) {
        this.marie = marie;
    }

    public OuiNonNP getEnfants() {
        return this.enfants;
    }

    public FicheHemophilie enfants(OuiNonNP enfants) {
        this.setEnfants(enfants);
        return this;
    }

    public void setEnfants(OuiNonNP enfants) {
        this.enfants = enfants;
    }

    public OuiNonNP getHandicap() {
        return this.handicap;
    }

    public FicheHemophilie handicap(OuiNonNP handicap) {
        this.setHandicap(handicap);
        return this;
    }

    public void setHandicap(OuiNonNP handicap) {
        this.handicap = handicap;
    }

    public String getTypeHandicap() {
        return this.typeHandicap;
    }

    public FicheHemophilie typeHandicap(String typeHandicap) {
        this.setTypeHandicap(typeHandicap);
        return this;
    }

    public void setTypeHandicap(String typeHandicap) {
        this.typeHandicap = typeHandicap;
    }

    public OuiNonNP getActiviteSportive() {
        return this.activiteSportive;
    }

    public FicheHemophilie activiteSportive(OuiNonNP activiteSportive) {
        this.setActiviteSportive(activiteSportive);
        return this;
    }

    public void setActiviteSportive(OuiNonNP activiteSportive) {
        this.activiteSportive = activiteSportive;
    }

    public String getTypeActiviteSportive() {
        return this.typeActiviteSportive;
    }

    public FicheHemophilie typeActiviteSportive(String typeActiviteSportive) {
        this.setTypeActiviteSportive(typeActiviteSportive);
        return this;
    }

    public void setTypeActiviteSportive(String typeActiviteSportive) {
        this.typeActiviteSportive = typeActiviteSportive;
    }

    public OuiNonNP getDecede() {
        return this.decede;
    }

    public FicheHemophilie decede(OuiNonNP decede) {
        this.setDecede(decede);
        return this;
    }

    public void setDecede(OuiNonNP decede) {
        this.decede = decede;
    }

    public String getCauseDateDeces() {
        return this.causeDateDeces;
    }

    public FicheHemophilie causeDateDeces(String causeDateDeces) {
        this.setCauseDateDeces(causeDateDeces);
        return this;
    }

    public void setCauseDateDeces(String causeDateDeces) {
        this.causeDateDeces = causeDateDeces;
    }

    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public FicheHemophilie patient(Patient patient) {
        this.setPatient(patient);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheHemophilie)) {
            return false;
        }
        return getId() != null && getId().equals(((FicheHemophilie) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheHemophilie{" +
            "id=" + getId() +
            ", dossierNumber='" + getDossierNumber() + "'" +
            ", ordreNumber='" + getOrdreNumber() + "'" +
            ", indexNumber='" + getIndexNumber() + "'" +
            ", parentsConsanguins='" + getParentsConsanguins() + "'" +
            ", degreParenteConsanguins='" + getDegreParenteConsanguins() + "'" +
            ", casSimilairesFamille='" + getCasSimilairesFamille() + "'" +
            ", nbreCasSimilaires=" + getNbreCasSimilaires() +
            ", degreParenteCasSimilaires='" + getDegreParenteCasSimilaires() + "'" +
            ", casDecesSyndromeHemorragique='" + getCasDecesSyndromeHemorragique() + "'" +
            ", nbreCasDeces=" + getNbreCasDeces() +
            ", formeHemophilie='" + getFormeHemophilie() + "'" +
            ", nbreFreres=" + getNbreFreres() +
            ", nbreSoeurs=" + getNbreSoeurs() +
            ", circonstanceDecouverte='" + getCirconstanceDecouverte() + "'" +
            ", dateTestConfirmation='" + getDateTestConfirmation() + "'" +
            ", hemogrammeHb=" + getHemogrammeHb() +
            ", hemogrammeHt=" + getHemogrammeHt() +
            ", plaquettes=" + getPlaquettes() +
            ", tp=" + getTp() +
            ", fibrinogene=" + getFibrinogene() +
            ", tcaMT=" + getTcaMT() +
            ", tcaMT2h=" + getTcaMT2h() +
            ", tcaTemoin2h=" + getTcaTemoin2h() +
            ", ts='" + getTs() + "'" +
            ", pfa='" + getPfa() + "'" +
            ", dosageFacteurVIII=" + getDosageFacteurVIII() +
            ", dosageFacteurIX=" + getDosageFacteurIX() +
            ", dosageFacteurVWActivite=" + getDosageFacteurVWActivite() +
            ", dosageFacteurVWAntigene=" + getDosageFacteurVWAntigene() +
            ", formeClinique='" + getFormeClinique() + "'" +
            ", severite='" + getSeverite() + "'" +
            ", explorationMoleculaire='" + getExplorationMoleculaire() + "'" +
            ", hemarthrose='" + getHemarthrose() + "'" +
            ", siegeHemarthrose='" + getSiegeHemarthrose() + "'" +
            ", circonstanceHemarthrose='" + getCirconstanceHemarthrose() + "'" +
            ", frequenceHemarthrose=" + getFrequenceHemarthrose() +
            ", hematomeSuperficiel='" + getHematomeSuperficiel() + "'" +
            ", circonstanceHematomeSuperficiel='" + getCirconstanceHematomeSuperficiel() + "'" +
            ", siegeHematomeSuperficiel='" + getSiegeHematomeSuperficiel() + "'" +
            ", hematomePsoas='" + getHematomePsoas() + "'" +
            ", circonstanceHematomePsoas='" + getCirconstanceHematomePsoas() + "'" +
            ", recidiveHematomePsoas='" + getRecidiveHematomePsoas() + "'" +
            ", hemorragiesCutaneoMuqueuses='" + getHemorragiesCutaneoMuqueuses() + "'" +
            ", typeHemorragiesCutaneoMuqueuses='" + getTypeHemorragiesCutaneoMuqueuses() + "'" +
            ", frequenceHemorragiesCutaneoMuqueuses=" + getFrequenceHemorragiesCutaneoMuqueuses() +
            ", hemorragieVisceres='" + getHemorragieVisceres() + "'" +
            ", typeHemorragieVisceres='" + getTypeHemorragieVisceres() + "'" +
            ", explorationHemorragieVisceres='" + getExplorationHemorragieVisceres() + "'" +
            ", examenLesionHemorragieVisceres='" + getExamenLesionHemorragieVisceres() + "'" +
            ", saignementSNC='" + getSaignementSNC() + "'" +
            ", evolutionSNC='" + getEvolutionSNC() + "'" +
            ", circoncision='" + getCirconcision() + "'" +
            ", typePreparationCirconcision='" + getTypePreparationCirconcision() + "'" +
            ", suiteOperatoireCirconcision='" + getSuiteOperatoireCirconcision() + "'" +
            ", ageCirconcision=" + getAgeCirconcision() +
            ", avulsionDentaire='" + getAvulsionDentaire() + "'" +
            ", typePreparationAvulsionDentaire='" + getTypePreparationAvulsionDentaire() + "'" +
            ", interventionChirurgicale='" + getInterventionChirurgicale() + "'" +
            ", typeInterventionChirurgicale='" + getTypeInterventionChirurgicale() + "'" +
            ", accidentsHemorragiquesGraves='" + getAccidentsHemorragiquesGraves() + "'" +
            ", circonstanceAccidentsHemorragiquesGraves='" + getCirconstanceAccidentsHemorragiquesGraves() + "'" +
            ", priseEnCharge='" + getPriseEnCharge() + "'" +
            ", causePriseEnCharge='" + getCausePriseEnCharge() + "'" +
            ", doseProphylaxie='" + getDoseProphylaxie() + "'" +
            ", frequenceProphylaxie=" + getFrequenceProphylaxie() +
            ", debutProphylaxie='" + getDebutProphylaxie() + "'" +
            ", modaliteInjection='" + getModaliteInjection() + "'" +
            ", typeTraitementSubstitutif='" + getTypeTraitementSubstitutif() + "'" +
            ", age1ereSubstitution=" + getAge1ereSubstitution() +
            ", antecedentsPSL='" + getAntecedentsPSL() + "'" +
            ", plasmaFraisCongele='" + getPlasmaFraisCongele() + "'" +
            ", cryoprecipite='" + getCryoprecipite() + "'" +
            ", complicationsOrthopediques='" + getComplicationsOrthopediques() + "'" +
            ", typeComplicationsOrthopediques='" + getTypeComplicationsOrthopediques() + "'" +
            ", articulationCible='" + getArticulationCible() + "'" +
            ", pecTherapeutique='" + getPecTherapeutique() + "'" +
            ", complicationsInhibiteurs='" + getComplicationsInhibiteurs() + "'" +
            ", tauxInhibiteur='" + getTauxInhibiteur() + "'" +
            ", testRecuperationFAH='" + getTestRecuperationFAH() + "'" +
            ", dateRecuperationFAH='" + getDateRecuperationFAH() + "'" +
            ", tauxRecuperation='" + getTauxRecuperation() + "'" +
            ", antiFAHNonNeutralisant='" + getAntiFAHNonNeutralisant() + "'" +
            ", rechercheACC='" + getRechercheACC() + "'" +
            ", catTherapeutique='" + getCatTherapeutique() + "'" +
            ", iti='" + getIti() + "'" +
            ", posologieITI='" + getPosologieITI() + "'" +
            ", dureeITI='" + getDureeITI() + "'" +
            ", observanceITI='" + getObservanceITI() + "'" +
            ", causeNonObservanceITI='" + getCauseNonObservanceITI() + "'" +
            ", evolutionInhibiteur='" + getEvolutionInhibiteur() + "'" +
            ", prophylaxieHemlibra='" + getProphylaxieHemlibra() + "'" +
            ", dateDebutHemlibra='" + getDateDebutHemlibra() + "'" +
            ", schemaTherapeutique='" + getSchemaTherapeutique() + "'" +
            ", efficaciteHemlibra=" + getEfficaciteHemlibra() +
            ", serologieHBC='" + getSerologieHBC() + "'" +
            ", serologieHVC='" + getSerologieHVC() + "'" +
            ", dateDecouverteVHC='" + getDateDecouverteVHC() + "'" +
            ", chargeViraleVHC='" + getChargeViraleVHC() + "'" +
            ", traitementVHC='" + getTraitementVHC() + "'" +
            ", evolutionVHC='" + getEvolutionVHC() + "'" +
            ", serologieHIV='" + getSerologieHIV() + "'" +
            ", vieSociale='" + getVieSociale() + "'" +
            ", marie='" + getMarie() + "'" +
            ", enfants='" + getEnfants() + "'" +
            ", handicap='" + getHandicap() + "'" +
            ", typeHandicap='" + getTypeHandicap() + "'" +
            ", activiteSportive='" + getActiviteSportive() + "'" +
            ", typeActiviteSportive='" + getTypeActiviteSportive() + "'" +
            ", decede='" + getDecede() + "'" +
            ", causeDateDeces='" + getCauseDateDeces() + "'" +
            "}";
    }
}

package tn.adhes.hemophilie.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import tn.adhes.hemophilie.domain.enumeration.ArticulationCible;
import tn.adhes.hemophilie.domain.enumeration.CivilStatus;
import tn.adhes.hemophilie.domain.enumeration.CouvertureSociale;
import tn.adhes.hemophilie.domain.enumeration.DegreeParente;
import tn.adhes.hemophilie.domain.enumeration.DiagnosticType;
import tn.adhes.hemophilie.domain.enumeration.EvolutionInhibiteur;
import tn.adhes.hemophilie.domain.enumeration.EvolutionSNC;
import tn.adhes.hemophilie.domain.enumeration.EvolutionVHC;
import tn.adhes.hemophilie.domain.enumeration.FormeClinique;
import tn.adhes.hemophilie.domain.enumeration.FormeHemophilie;
import tn.adhes.hemophilie.domain.enumeration.Gender;
import tn.adhes.hemophilie.domain.enumeration.InjectionType;
import tn.adhes.hemophilie.domain.enumeration.ModaliteTraitement;
import tn.adhes.hemophilie.domain.enumeration.OuiNonNP;
import tn.adhes.hemophilie.domain.enumeration.PECTherapeutique;
import tn.adhes.hemophilie.domain.enumeration.RechercheACC;
import tn.adhes.hemophilie.domain.enumeration.Region;
import tn.adhes.hemophilie.domain.enumeration.SchemaTherapeutique;
import tn.adhes.hemophilie.domain.enumeration.Serologie;
import tn.adhes.hemophilie.domain.enumeration.Severite;
import tn.adhes.hemophilie.domain.enumeration.SuiteOperatoire;
import tn.adhes.hemophilie.domain.enumeration.TauxInhibiteur;
import tn.adhes.hemophilie.domain.enumeration.TauxRecuperation;
import tn.adhes.hemophilie.domain.enumeration.TypeAccidentHemorragiqueGrave;
import tn.adhes.hemophilie.domain.enumeration.TypeHemorragie;
import tn.adhes.hemophilie.domain.enumeration.TypeHemorragieCutaneoMuqueuse;
import tn.adhes.hemophilie.domain.enumeration.TypeHemorragieViscere;
import tn.adhes.hemophilie.domain.enumeration.TypePriseEnCharge;
import tn.adhes.hemophilie.domain.enumeration.VieSociale;

/**
 * A FicheHemophilie.
 */
@Entity
@Table(name = "fiche_hemophilie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheHemophilie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Size(max = 25)
    @Column(name = "nom_jeune_fille", length = 25)
    private String nomJeuneFille;

    @Size(max = 25)
    @Column(name = "profession", length = 25)
    private String profession;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexe", nullable = false)
    private Gender sexe;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "age_actuel")
    private Integer ageActuel;

    @Enumerated(EnumType.STRING)
    @Column(name = "origine")
    private Region origine;

    @Size(max = 25)
    @Column(name = "autre_origine", length = 25)
    private String autreOrigine;

    @Size(max = 25)
    @Column(name = "adresse", length = 25)
    private String adresse;

    @Size(max = 20)
    @Column(name = "telephone", length = 20)
    private String telephone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "etat_civil", nullable = false)
    private CivilStatus etatCivil;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "couverture_sociale", nullable = false)
    private CouvertureSociale couvertureSociale;

    @Size(max = 25)
    @Column(name = "prenom_pere", length = 25)
    private String prenomPere;

    @Size(max = 25)
    @Column(name = "profession_pere", length = 25)
    private String professionPere;

    @Size(max = 25)
    @Column(name = "nom_prenom_mere", length = 25)
    private String nomPrenomMere;

    @Size(max = 25)
    @Column(name = "profession_mere", length = 25)
    private String professionMere;

    @Size(max = 25)
    @Column(name = "medecin_traitant", length = 25)
    private String medecinTraitant;

    @Size(max = 25)
    @Column(name = "service_origine", length = 25)
    private String serviceOrigine;

    @Size(max = 25)
    @Column(name = "biologiste_responsable_dg", length = 25)
    private String biologisteResponsableDg;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "diagnostic", nullable = false)
    private DiagnosticType diagnostic;

    @Size(max = 25)
    @Column(name = "autre_diagnostic", length = 25)
    private String autreDiagnostic;

    @Column(name = "annee_diagnostic")
    private Integer anneeDiagnostic;

    @Enumerated(EnumType.STRING)
    @Column(name = "consentement_registre")
    private OuiNonNP consentementRegistre;

    @Column(name = "date_enregistrement_registre")
    private LocalDate dateEnregistrementRegistre;

    @Column(name = "dossier_number")
    private String dossierNumber;

    @Size(max = 25)
    @Column(name = "ordre_number", length = 25)
    private String ordreNumber;

    @Size(max = 25)
    @Column(name = "index_number", length = 25)
    private String indexNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "parents_consanguins")
    private OuiNonNP parentsConsanguins;

    @Size(max = 25)
    @Column(name = "degre_parente_consanguins", length = 25)
    private String degreParenteConsanguins;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_similaires")
    private OuiNonNP casSimilaires;

    @Column(name = "nombre_cas")
    private Integer nombreCas;

    @Enumerated(EnumType.STRING)
    @Column(name = "degre_parente_cas_similaires")
    private DegreeParente degreParenteCasSimilaires;

    @Enumerated(EnumType.STRING)
    @Column(name = "deces_syndrome_hemorragique")
    private OuiNonNP decesSyndromeHemorragique;

    @Column(name = "nombre_deces")
    private Integer nombreDeces;

    @Enumerated(EnumType.STRING)
    @Column(name = "forme_hemophilie")
    private FormeHemophilie formeHemophilie;

    @Column(name = "fratrie_nb_freres")
    private Integer fratrieNbFreres;

    @Column(name = "fratrie_nb_soeurs")
    private Integer fratrieNbSoeurs;

    @Enumerated(EnumType.STRING)
    @Column(name = "enquete_familiale")
    private OuiNonNP enqueteFamiliale;

    @Enumerated(EnumType.STRING)
    @Column(name = "exploration_syndrome")
    private OuiNonNP explorationSyndrome;

    @Column(name = "cutaneo_muqueux")
    private Boolean cutaneoMuqueux;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemarthrose")
    private OuiNonNP hemarthrose;

    @Column(name = "hematome_post_traumatique")
    private Boolean hematomePostTraumatique;

    @Column(name = "plaie_post_traumatique")
    private Boolean plaiePostTraumatique;

    @Column(name = "hemorragie_post_circoncision")
    private Boolean hemorragiePostCirconcision;

    @Column(name = "hemorragie_post_chirurgie")
    private Boolean hemorragiePostChirurgie;

    @Column(name = "hemorragie_cerebrale")
    private Boolean hemorragieCerebrale;

    @Column(name = "hemorragie_digestive")
    private Boolean hemorragieDigestive;

    @Column(name = "date_premier_signe_clinique")
    private LocalDate datePremierSigneClinique;

    @Column(name = "age_diagnostic_jours")
    private Integer ageDiagnosticJours;

    @Column(name = "age_diagnostic_mois")
    private Integer ageDiagnosticMois;

    @Column(name = "age_diagnostic_annees")
    private Integer ageDiagnosticAnnees;

    @Column(name = "date_test_confirmation")
    private LocalDate dateTestConfirmation;

    @Column(name = "hb")
    private Float hb;

    @Column(name = "ht")
    private Float ht;

    @Column(name = "plaquettes")
    private Integer plaquettes;

    @Column(name = "tp")
    private Float tp;

    @Column(name = "fibrinogene")
    private Float fibrinogene;

    @Column(name = "tca_mt")
    private Float tcaMT;

    @Column(name = "tca_mt_total")
    private Float tcaMTTotal;

    @Column(name = "tca_mt_2_h")
    private Float tcaMT2h;

    @Column(name = "tca_temoin_2_h")
    private Float tcaTemoin2h;

    @Enumerated(EnumType.STRING)
    @Column(name = "ts")
    private OuiNonNP ts;

    @Enumerated(EnumType.STRING)
    @Column(name = "pfa")
    private OuiNonNP pfa;

    @Column(name = "dosage_facteur_viii")
    private Float dosageFacteurVIII;

    @Column(name = "dosage_facteur_ix")
    private Float dosageFacteurIX;

    @Column(name = "dosage_facteur_vw_activite")
    private Float dosageFacteurVWActivite;

    @Column(name = "dosage_facteur_vw_antigene")
    private Float dosageFacteurVWAntigene;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "forme_clinique", nullable = false)
    private FormeClinique formeClinique;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "severite", nullable = false)
    private Severite severite;

    @Enumerated(EnumType.STRING)
    @Column(name = "exploration_moleculaire")
    private OuiNonNP explorationMoleculaire;

    @Column(name = "inversion_intron_22")
    private Boolean inversionIntron22;

    @Size(max = 25)
    @Column(name = "autre_exploration_moleculaire", length = 25)
    private String autreExplorationMoleculaire;

    @Size(max = 25)
    @Column(name = "hemarthrose_siege", length = 25)
    private String hemarthroseSiege;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemarthrose_type")
    private TypeHemorragie hemarthroseType;

    @Column(name = "hemarthrose_frequence_an")
    private Integer hemarthroseFrequenceAn;

    @Enumerated(EnumType.STRING)
    @Column(name = "hematome_superficiel")
    private OuiNonNP hematomeSuperficiel;

    @Enumerated(EnumType.STRING)
    @Column(name = "hematome_superficiel_type")
    private TypeHemorragie hematomeSuperficielType;

    @Size(max = 25)
    @Column(name = "hematome_superficiel_siege", length = 25)
    private String hematomeSuperficielSiege;

    @Enumerated(EnumType.STRING)
    @Column(name = "hematome_psoas")
    private OuiNonNP hematomePsoas;

    @Enumerated(EnumType.STRING)
    @Column(name = "hematome_psoas_type")
    private TypeHemorragie hematomePsoasType;

    @Enumerated(EnumType.STRING)
    @Column(name = "hematome_psoas_recidive")
    private OuiNonNP hematomePsoasRecidive;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragies_cutaneo_muqueuses")
    private OuiNonNP hemorragiesCutaneoMuqueuses;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragies_cutaneo_muqueuses_types")
    private TypeHemorragieCutaneoMuqueuse hemorragiesCutaneoMuqueusesTypes;

    @Column(name = "hemorragies_cutaneo_muqueuses_frequence_an")
    private Integer hemorragiesCutaneoMuqueusesFrequenceAn;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_visceres")
    private OuiNonNP hemorragieVisceres;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_visceres_type")
    private TypeHemorragieViscere hemorragieVisceresType;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_visceres_exploration")
    private OuiNonNP hemorragieVisceresExploration;

    @Size(max = 25)
    @Column(name = "hemorragie_visceres_examen", length = 25)
    private String hemorragieVisceresExamen;

    @Enumerated(EnumType.STRING)
    @Column(name = "saignement_snc")
    private OuiNonNP saignementSNC;

    @Enumerated(EnumType.STRING)
    @Column(name = "saignement_snc_evolution")
    private EvolutionSNC saignementSNCEvolution;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision")
    private OuiNonNP circoncision;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision_preparation")
    private OuiNonNP circoncisionPreparation;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision_suite_operatoire")
    private SuiteOperatoire circoncisionSuiteOperatoire;

    @Column(name = "circoncision_age")
    private Integer circoncisionAge;

    @Enumerated(EnumType.STRING)
    @Column(name = "avulsion_dentaire")
    private OuiNonNP avulsionDentaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "avulsion_dentaire_preparation")
    private OuiNonNP avulsionDentairePreparation;

    @Enumerated(EnumType.STRING)
    @Column(name = "intervention_chirurgicale")
    private OuiNonNP interventionChirurgicale;

    @Size(max = 25)
    @Column(name = "intervention_chirurgicale_type", length = 25)
    private String interventionChirurgicaleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "accidents_hemorragiques_graves")
    private OuiNonNP accidentsHemorragiquesGraves;

    @Enumerated(EnumType.STRING)
    @Column(name = "accidents_hemorragiques_graves_types")
    private TypeAccidentHemorragiqueGrave accidentsHemorragiquesGravesTypes;

    @Enumerated(EnumType.STRING)
    @Column(name = "prise_en_charge_therapeutique")
    private TypePriseEnCharge priseEnChargeTherapeutique;

    @Size(max = 25)
    @Column(name = "cause_prise_en_charge", length = 25)
    private String causePriseEnCharge;

    @Size(max = 25)
    @Column(name = "prophylaxie_dose_frequence", length = 25)
    private String prophylaxieDoseFrequence;

    @Column(name = "date_debut_prophylaxie")
    private LocalDate dateDebutProphylaxie;

    @Enumerated(EnumType.STRING)
    @Column(name = "injection_type")
    private InjectionType injectionType;

    @Enumerated(EnumType.STRING)
    @Column(name = "modalite_traitement")
    private ModaliteTraitement modaliteTraitement;

    @Size(max = 25)
    @Column(name = "type_traitement_substitutif", length = 25)
    private String typeTraitementSubstitutif;

    @Column(name = "age_premiere_substitution")
    private Integer agePremiereSubstitution;

    @Enumerated(EnumType.STRING)
    @Column(name = "antecedents_traitement_psl")
    private OuiNonNP antecedentsTraitementPSL;

    @Enumerated(EnumType.STRING)
    @Column(name = "plasma_frais_congele")
    private OuiNonNP plasmaFraisCongele;

    @Enumerated(EnumType.STRING)
    @Column(name = "cryoprecipite")
    private OuiNonNP cryoprecipite;

    @Column(name = "synovite")
    private Boolean synovite;

    @Column(name = "arthropathie_hemophilique_chronique")
    private Boolean arthropathieHemophiliqueChronique;

    @Column(name = "pseudotumeurs")
    private Boolean pseudotumeurs;

    @Column(name = "fractures")
    private Boolean fractures;

    @Enumerated(EnumType.STRING)
    @Column(name = "articulation_cible")
    private ArticulationCible articulationCible;

    @Enumerated(EnumType.STRING)
    @Column(name = "pec_therapeutique")
    private PECTherapeutique pecTherapeutique;

    @Enumerated(EnumType.STRING)
    @Column(name = "inhibiteurs")
    private OuiNonNP inhibiteurs;

    @Enumerated(EnumType.STRING)
    @Column(name = "taux_inhibiteur")
    private TauxInhibiteur tauxInhibiteur;

    @Column(name = "test_recuperation_fah")
    private Boolean testRecuperationFAH;

    @Column(name = "date_test_recuperation")
    private LocalDate dateTestRecuperation;

    @Enumerated(EnumType.STRING)
    @Column(name = "taux_recuperation")
    private TauxRecuperation tauxRecuperation;

    @Column(name = "anti_fah_non_neutralisant")
    private Boolean antiFAHNonNeutralisant;

    @Column(name = "anti_fah_negatif")
    private Boolean antiFAHNegatif;

    @Enumerated(EnumType.STRING)
    @Column(name = "recherche_acc")
    private RechercheACC rechercheACC;

    @Column(name = "augmenter_dose_facteur")
    private Boolean augmenterDoseFacteur;

    @Column(name = "novoseven")
    private Boolean novoseven;

    @Column(name = "feiba")
    private Boolean feiba;

    @Column(name = "corticotherapie")
    private Boolean corticotherapie;

    @Column(name = "immurel")
    private Boolean immurel;

    @Column(name = "iti")
    private Boolean iti;

    @Size(max = 25)
    @Column(name = "posologie_iti", length = 25)
    private String posologieITI;

    @Size(max = 25)
    @Column(name = "duree_iti", length = 25)
    private String dureeITI;

    @Enumerated(EnumType.STRING)
    @Column(name = "observance_iti")
    private OuiNonNP observanceITI;

    @Size(max = 25)
    @Column(name = "cause_non_observance", length = 25)
    private String causeNonObservance;

    @Enumerated(EnumType.STRING)
    @Column(name = "evolution_inhibiteur")
    private EvolutionInhibiteur evolutionInhibiteur;

    @Column(name = "prophylaxie_hemlibra")
    private Boolean prophylaxieHemlibra;

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

    @Size(max = 25)
    @Column(name = "charge_virale_vhc", length = 25)
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

    @Column(name = "marie")
    private Boolean marie;

    @Column(name = "enfants")
    private Boolean enfants;

    @Column(name = "handicap")
    private Boolean handicap;

    @Size(max = 25)
    @Column(name = "type_handicap", length = 25)
    private String typeHandicap;

    @Column(name = "activite_sportive")
    private Boolean activiteSportive;

    @Size(max = 25)
    @Column(name = "type_activite_sportive", length = 25)
    private String typeActiviteSportive;

    @Column(name = "decede")
    private Boolean decede;

    @Size(max = 25)
    @Column(name = "cause_deces", length = 25)
    private String causeDeces;

    @Column(name = "date_deces")
    private LocalDate dateDeces;

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

    public String getPrenom() {
        return this.prenom;
    }

    public FicheHemophilie prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public FicheHemophilie nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomJeuneFille() {
        return this.nomJeuneFille;
    }

    public FicheHemophilie nomJeuneFille(String nomJeuneFille) {
        this.setNomJeuneFille(nomJeuneFille);
        return this;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public String getProfession() {
        return this.profession;
    }

    public FicheHemophilie profession(String profession) {
        this.setProfession(profession);
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Gender getSexe() {
        return this.sexe;
    }

    public FicheHemophilie sexe(Gender sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Gender sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public FicheHemophilie dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Integer getAgeActuel() {
        return this.ageActuel;
    }

    public FicheHemophilie ageActuel(Integer ageActuel) {
        this.setAgeActuel(ageActuel);
        return this;
    }

    public void setAgeActuel(Integer ageActuel) {
        this.ageActuel = ageActuel;
    }

    public Region getOrigine() {
        return this.origine;
    }

    public FicheHemophilie origine(Region origine) {
        this.setOrigine(origine);
        return this;
    }

    public void setOrigine(Region origine) {
        this.origine = origine;
    }

    public String getAutreOrigine() {
        return this.autreOrigine;
    }

    public FicheHemophilie autreOrigine(String autreOrigine) {
        this.setAutreOrigine(autreOrigine);
        return this;
    }

    public void setAutreOrigine(String autreOrigine) {
        this.autreOrigine = autreOrigine;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public FicheHemophilie adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public FicheHemophilie telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public CivilStatus getEtatCivil() {
        return this.etatCivil;
    }

    public FicheHemophilie etatCivil(CivilStatus etatCivil) {
        this.setEtatCivil(etatCivil);
        return this;
    }

    public void setEtatCivil(CivilStatus etatCivil) {
        this.etatCivil = etatCivil;
    }

    public CouvertureSociale getCouvertureSociale() {
        return this.couvertureSociale;
    }

    public FicheHemophilie couvertureSociale(CouvertureSociale couvertureSociale) {
        this.setCouvertureSociale(couvertureSociale);
        return this;
    }

    public void setCouvertureSociale(CouvertureSociale couvertureSociale) {
        this.couvertureSociale = couvertureSociale;
    }

    public String getPrenomPere() {
        return this.prenomPere;
    }

    public FicheHemophilie prenomPere(String prenomPere) {
        this.setPrenomPere(prenomPere);
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getProfessionPere() {
        return this.professionPere;
    }

    public FicheHemophilie professionPere(String professionPere) {
        this.setProfessionPere(professionPere);
        return this;
    }

    public void setProfessionPere(String professionPere) {
        this.professionPere = professionPere;
    }

    public String getNomPrenomMere() {
        return this.nomPrenomMere;
    }

    public FicheHemophilie nomPrenomMere(String nomPrenomMere) {
        this.setNomPrenomMere(nomPrenomMere);
        return this;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }

    public String getProfessionMere() {
        return this.professionMere;
    }

    public FicheHemophilie professionMere(String professionMere) {
        this.setProfessionMere(professionMere);
        return this;
    }

    public void setProfessionMere(String professionMere) {
        this.professionMere = professionMere;
    }

    public String getMedecinTraitant() {
        return this.medecinTraitant;
    }

    public FicheHemophilie medecinTraitant(String medecinTraitant) {
        this.setMedecinTraitant(medecinTraitant);
        return this;
    }

    public void setMedecinTraitant(String medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }

    public String getServiceOrigine() {
        return this.serviceOrigine;
    }

    public FicheHemophilie serviceOrigine(String serviceOrigine) {
        this.setServiceOrigine(serviceOrigine);
        return this;
    }

    public void setServiceOrigine(String serviceOrigine) {
        this.serviceOrigine = serviceOrigine;
    }

    public String getBiologisteResponsableDg() {
        return this.biologisteResponsableDg;
    }

    public FicheHemophilie biologisteResponsableDg(String biologisteResponsableDg) {
        this.setBiologisteResponsableDg(biologisteResponsableDg);
        return this;
    }

    public void setBiologisteResponsableDg(String biologisteResponsableDg) {
        this.biologisteResponsableDg = biologisteResponsableDg;
    }

    public DiagnosticType getDiagnostic() {
        return this.diagnostic;
    }

    public FicheHemophilie diagnostic(DiagnosticType diagnostic) {
        this.setDiagnostic(diagnostic);
        return this;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return this.autreDiagnostic;
    }

    public FicheHemophilie autreDiagnostic(String autreDiagnostic) {
        this.setAutreDiagnostic(autreDiagnostic);
        return this;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public Integer getAnneeDiagnostic() {
        return this.anneeDiagnostic;
    }

    public FicheHemophilie anneeDiagnostic(Integer anneeDiagnostic) {
        this.setAnneeDiagnostic(anneeDiagnostic);
        return this;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public OuiNonNP getConsentementRegistre() {
        return this.consentementRegistre;
    }

    public FicheHemophilie consentementRegistre(OuiNonNP consentementRegistre) {
        this.setConsentementRegistre(consentementRegistre);
        return this;
    }

    public void setConsentementRegistre(OuiNonNP consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return this.dateEnregistrementRegistre;
    }

    public FicheHemophilie dateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.setDateEnregistrementRegistre(dateEnregistrementRegistre);
        return this;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
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

    public OuiNonNP getCasSimilaires() {
        return this.casSimilaires;
    }

    public FicheHemophilie casSimilaires(OuiNonNP casSimilaires) {
        this.setCasSimilaires(casSimilaires);
        return this;
    }

    public void setCasSimilaires(OuiNonNP casSimilaires) {
        this.casSimilaires = casSimilaires;
    }

    public Integer getNombreCas() {
        return this.nombreCas;
    }

    public FicheHemophilie nombreCas(Integer nombreCas) {
        this.setNombreCas(nombreCas);
        return this;
    }

    public void setNombreCas(Integer nombreCas) {
        this.nombreCas = nombreCas;
    }

    public DegreeParente getDegreParenteCasSimilaires() {
        return this.degreParenteCasSimilaires;
    }

    public FicheHemophilie degreParenteCasSimilaires(DegreeParente degreParenteCasSimilaires) {
        this.setDegreParenteCasSimilaires(degreParenteCasSimilaires);
        return this;
    }

    public void setDegreParenteCasSimilaires(DegreeParente degreParenteCasSimilaires) {
        this.degreParenteCasSimilaires = degreParenteCasSimilaires;
    }

    public OuiNonNP getDecesSyndromeHemorragique() {
        return this.decesSyndromeHemorragique;
    }

    public FicheHemophilie decesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.setDecesSyndromeHemorragique(decesSyndromeHemorragique);
        return this;
    }

    public void setDecesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.decesSyndromeHemorragique = decesSyndromeHemorragique;
    }

    public Integer getNombreDeces() {
        return this.nombreDeces;
    }

    public FicheHemophilie nombreDeces(Integer nombreDeces) {
        this.setNombreDeces(nombreDeces);
        return this;
    }

    public void setNombreDeces(Integer nombreDeces) {
        this.nombreDeces = nombreDeces;
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

    public Integer getFratrieNbFreres() {
        return this.fratrieNbFreres;
    }

    public FicheHemophilie fratrieNbFreres(Integer fratrieNbFreres) {
        this.setFratrieNbFreres(fratrieNbFreres);
        return this;
    }

    public void setFratrieNbFreres(Integer fratrieNbFreres) {
        this.fratrieNbFreres = fratrieNbFreres;
    }

    public Integer getFratrieNbSoeurs() {
        return this.fratrieNbSoeurs;
    }

    public FicheHemophilie fratrieNbSoeurs(Integer fratrieNbSoeurs) {
        this.setFratrieNbSoeurs(fratrieNbSoeurs);
        return this;
    }

    public void setFratrieNbSoeurs(Integer fratrieNbSoeurs) {
        this.fratrieNbSoeurs = fratrieNbSoeurs;
    }


    public void setDecouverteFortuite(OuiNonNP decouverteFortuite) {
        this.decouverteFortuite = decouverteFortuite;
    }

    public OuiNonNP getEnqueteFamiliale() {
        return this.enqueteFamiliale;
    }

    public FicheHemophilie enqueteFamiliale(OuiNonNP enqueteFamiliale) {
        this.setEnqueteFamiliale(enqueteFamiliale);
        return this;
    }

    public void setEnqueteFamiliale(OuiNonNP enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public OuiNonNP getExplorationSyndrome() {
        return this.explorationSyndrome;
    }

    public FicheHemophilie explorationSyndrome(OuiNonNP explorationSyndrome) {
        this.setExplorationSyndrome(explorationSyndrome);
        return this;
    }

    public void setExplorationSyndrome(OuiNonNP explorationSyndrome) {
        this.explorationSyndrome = explorationSyndrome;
    }

    public Boolean getCutaneoMuqueux() {
        return this.cutaneoMuqueux;
    }

    public FicheHemophilie cutaneoMuqueux(Boolean cutaneoMuqueux) {
        this.setCutaneoMuqueux(cutaneoMuqueux);
        return this;
    }

    public void setCutaneoMuqueux(Boolean cutaneoMuqueux) {
        this.cutaneoMuqueux = cutaneoMuqueux;
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

    public Boolean getHematomePostTraumatique() {
        return this.hematomePostTraumatique;
    }

    public FicheHemophilie hematomePostTraumatique(Boolean hematomePostTraumatique) {
        this.setHematomePostTraumatique(hematomePostTraumatique);
        return this;
    }

    public void setHematomePostTraumatique(Boolean hematomePostTraumatique) {
        this.hematomePostTraumatique = hematomePostTraumatique;
    }

    public Boolean getPlaiePostTraumatique() {
        return this.plaiePostTraumatique;
    }

    public FicheHemophilie plaiePostTraumatique(Boolean plaiePostTraumatique) {
        this.setPlaiePostTraumatique(plaiePostTraumatique);
        return this;
    }

    public void setPlaiePostTraumatique(Boolean plaiePostTraumatique) {
        this.plaiePostTraumatique = plaiePostTraumatique;
    }

    public Boolean getHemorragiePostCirconcision() {
        return this.hemorragiePostCirconcision;
    }

    public FicheHemophilie hemorragiePostCirconcision(Boolean hemorragiePostCirconcision) {
        this.setHemorragiePostCirconcision(hemorragiePostCirconcision);
        return this;
    }

    public void setHemorragiePostCirconcision(Boolean hemorragiePostCirconcision) {
        this.hemorragiePostCirconcision = hemorragiePostCirconcision;
    }

    public Boolean getHemorragiePostChirurgie() {
        return this.hemorragiePostChirurgie;
    }

    public FicheHemophilie hemorragiePostChirurgie(Boolean hemorragiePostChirurgie) {
        this.setHemorragiePostChirurgie(hemorragiePostChirurgie);
        return this;
    }

    public void setHemorragiePostChirurgie(Boolean hemorragiePostChirurgie) {
        this.hemorragiePostChirurgie = hemorragiePostChirurgie;
    }

    public Boolean getHemorragieCerebrale() {
        return this.hemorragieCerebrale;
    }

    public FicheHemophilie hemorragieCerebrale(Boolean hemorragieCerebrale) {
        this.setHemorragieCerebrale(hemorragieCerebrale);
        return this;
    }

    public void setHemorragieCerebrale(Boolean hemorragieCerebrale) {
        this.hemorragieCerebrale = hemorragieCerebrale;
    }

    public Boolean getHemorragieDigestive() {
        return this.hemorragieDigestive;
    }

    public FicheHemophilie hemorragieDigestive(Boolean hemorragieDigestive) {
        this.setHemorragieDigestive(hemorragieDigestive);
        return this;
    }

    public void setHemorragieDigestive(Boolean hemorragieDigestive) {
        this.hemorragieDigestive = hemorragieDigestive;
    }

    public LocalDate getDatePremierSigneClinique() {
        return this.datePremierSigneClinique;
    }

    public FicheHemophilie datePremierSigneClinique(LocalDate datePremierSigneClinique) {
        this.setDatePremierSigneClinique(datePremierSigneClinique);
        return this;
    }

    public void setDatePremierSigneClinique(LocalDate datePremierSigneClinique) {
        this.datePremierSigneClinique = datePremierSigneClinique;
    }

    public Integer getAgeDiagnosticJours() {
        return this.ageDiagnosticJours;
    }

    public FicheHemophilie ageDiagnosticJours(Integer ageDiagnosticJours) {
        this.setAgeDiagnosticJours(ageDiagnosticJours);
        return this;
    }

    public void setAgeDiagnosticJours(Integer ageDiagnosticJours) {
        this.ageDiagnosticJours = ageDiagnosticJours;
    }

    public Integer getAgeDiagnosticMois() {
        return this.ageDiagnosticMois;
    }

    public FicheHemophilie ageDiagnosticMois(Integer ageDiagnosticMois) {
        this.setAgeDiagnosticMois(ageDiagnosticMois);
        return this;
    }

    public void setAgeDiagnosticMois(Integer ageDiagnosticMois) {
        this.ageDiagnosticMois = ageDiagnosticMois;
    }

    public Integer getAgeDiagnosticAnnees() {
        return this.ageDiagnosticAnnees;
    }

    public FicheHemophilie ageDiagnosticAnnees(Integer ageDiagnosticAnnees) {
        this.setAgeDiagnosticAnnees(ageDiagnosticAnnees);
        return this;
    }

    public void setAgeDiagnosticAnnees(Integer ageDiagnosticAnnees) {
        this.ageDiagnosticAnnees = ageDiagnosticAnnees;
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

    public Float getHb() {
        return this.hb;
    }

    public FicheHemophilie hb(Float hb) {
        this.setHb(hb);
        return this;
    }

    public void setHb(Float hb) {
        this.hb = hb;
    }

    public Float getHt() {
        return this.ht;
    }

    public FicheHemophilie ht(Float ht) {
        this.setHt(ht);
        return this;
    }

    public void setHt(Float ht) {
        this.ht = ht;
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

    public Float getTp() {
        return this.tp;
    }

    public FicheHemophilie tp(Float tp) {
        this.setTp(tp);
        return this;
    }

    public void setTp(Float tp) {
        this.tp = tp;
    }

    public Float getFibrinogene() {
        return this.fibrinogene;
    }

    public FicheHemophilie fibrinogene(Float fibrinogene) {
        this.setFibrinogene(fibrinogene);
        return this;
    }

    public void setFibrinogene(Float fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public Float getTcaMT() {
        return this.tcaMT;
    }

    public FicheHemophilie tcaMT(Float tcaMT) {
        this.setTcaMT(tcaMT);
        return this;
    }

    public void setTcaMT(Float tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Float getTcaMTTotal() {
        return this.tcaMTTotal;
    }

    public FicheHemophilie tcaMTTotal(Float tcaMTTotal) {
        this.setTcaMTTotal(tcaMTTotal);
        return this;
    }

    public void setTcaMTTotal(Float tcaMTTotal) {
        this.tcaMTTotal = tcaMTTotal;
    }

    public Float getTcaMT2h() {
        return this.tcaMT2h;
    }

    public FicheHemophilie tcaMT2h(Float tcaMT2h) {
        this.setTcaMT2h(tcaMT2h);
        return this;
    }

    public void setTcaMT2h(Float tcaMT2h) {
        this.tcaMT2h = tcaMT2h;
    }

    public Float getTcaTemoin2h() {
        return this.tcaTemoin2h;
    }

    public FicheHemophilie tcaTemoin2h(Float tcaTemoin2h) {
        this.setTcaTemoin2h(tcaTemoin2h);
        return this;
    }

    public void setTcaTemoin2h(Float tcaTemoin2h) {
        this.tcaTemoin2h = tcaTemoin2h;
    }

    public OuiNonNP getTs() {
        return this.ts;
    }

    public FicheHemophilie ts(OuiNonNP ts) {
        this.setTs(ts);
        return this;
    }

    public void setTs(OuiNonNP ts) {
        this.ts = ts;
    }

    public OuiNonNP getPfa() {
        return this.pfa;
    }

    public FicheHemophilie pfa(OuiNonNP pfa) {
        this.setPfa(pfa);
        return this;
    }

    public void setPfa(OuiNonNP pfa) {
        this.pfa = pfa;
    }

    public Float getDosageFacteurVIII() {
        return this.dosageFacteurVIII;
    }

    public FicheHemophilie dosageFacteurVIII(Float dosageFacteurVIII) {
        this.setDosageFacteurVIII(dosageFacteurVIII);
        return this;
    }

    public void setDosageFacteurVIII(Float dosageFacteurVIII) {
        this.dosageFacteurVIII = dosageFacteurVIII;
    }

    public Float getDosageFacteurIX() {
        return this.dosageFacteurIX;
    }

    public FicheHemophilie dosageFacteurIX(Float dosageFacteurIX) {
        this.setDosageFacteurIX(dosageFacteurIX);
        return this;
    }

    public void setDosageFacteurIX(Float dosageFacteurIX) {
        this.dosageFacteurIX = dosageFacteurIX;
    }

    public Float getDosageFacteurVWActivite() {
        return this.dosageFacteurVWActivite;
    }

    public FicheHemophilie dosageFacteurVWActivite(Float dosageFacteurVWActivite) {
        this.setDosageFacteurVWActivite(dosageFacteurVWActivite);
        return this;
    }

    public void setDosageFacteurVWActivite(Float dosageFacteurVWActivite) {
        this.dosageFacteurVWActivite = dosageFacteurVWActivite;
    }

    public Float getDosageFacteurVWAntigene() {
        return this.dosageFacteurVWAntigene;
    }

    public FicheHemophilie dosageFacteurVWAntigene(Float dosageFacteurVWAntigene) {
        this.setDosageFacteurVWAntigene(dosageFacteurVWAntigene);
        return this;
    }

    public void setDosageFacteurVWAntigene(Float dosageFacteurVWAntigene) {
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

    public OuiNonNP getExplorationMoleculaire() {
        return this.explorationMoleculaire;
    }

    public FicheHemophilie explorationMoleculaire(OuiNonNP explorationMoleculaire) {
        this.setExplorationMoleculaire(explorationMoleculaire);
        return this;
    }

    public void setExplorationMoleculaire(OuiNonNP explorationMoleculaire) {
        this.explorationMoleculaire = explorationMoleculaire;
    }

    public Boolean getInversionIntron22() {
        return this.inversionIntron22;
    }

    public FicheHemophilie inversionIntron22(Boolean inversionIntron22) {
        this.setInversionIntron22(inversionIntron22);
        return this;
    }

    public void setInversionIntron22(Boolean inversionIntron22) {
        this.inversionIntron22 = inversionIntron22;
    }

    public String getAutreExplorationMoleculaire() {
        return this.autreExplorationMoleculaire;
    }

    public FicheHemophilie autreExplorationMoleculaire(String autreExplorationMoleculaire) {
        this.setAutreExplorationMoleculaire(autreExplorationMoleculaire);
        return this;
    }

    public void setAutreExplorationMoleculaire(String autreExplorationMoleculaire) {
        this.autreExplorationMoleculaire = autreExplorationMoleculaire;
    }

    public String getHemarthroseSiege() {
        return this.hemarthroseSiege;
    }

    public FicheHemophilie hemarthroseSiege(String hemarthroseSiege) {
        this.setHemarthroseSiege(hemarthroseSiege);
        return this;
    }

    public void setHemarthroseSiege(String hemarthroseSiege) {
        this.hemarthroseSiege = hemarthroseSiege;
    }

    public TypeHemorragie getHemarthroseType() {
        return this.hemarthroseType;
    }

    public FicheHemophilie hemarthroseType(TypeHemorragie hemarthroseType) {
        this.setHemarthroseType(hemarthroseType);
        return this;
    }

    public void setHemarthroseType(TypeHemorragie hemarthroseType) {
        this.hemarthroseType = hemarthroseType;
    }

    public Integer getHemarthroseFrequenceAn() {
        return this.hemarthroseFrequenceAn;
    }

    public FicheHemophilie hemarthroseFrequenceAn(Integer hemarthroseFrequenceAn) {
        this.setHemarthroseFrequenceAn(hemarthroseFrequenceAn);
        return this;
    }

    public void setHemarthroseFrequenceAn(Integer hemarthroseFrequenceAn) {
        this.hemarthroseFrequenceAn = hemarthroseFrequenceAn;
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

    public TypeHemorragie getHematomeSuperficielType() {
        return this.hematomeSuperficielType;
    }

    public FicheHemophilie hematomeSuperficielType(TypeHemorragie hematomeSuperficielType) {
        this.setHematomeSuperficielType(hematomeSuperficielType);
        return this;
    }

    public void setHematomeSuperficielType(TypeHemorragie hematomeSuperficielType) {
        this.hematomeSuperficielType = hematomeSuperficielType;
    }

    public String getHematomeSuperficielSiege() {
        return this.hematomeSuperficielSiege;
    }

    public FicheHemophilie hematomeSuperficielSiege(String hematomeSuperficielSiege) {
        this.setHematomeSuperficielSiege(hematomeSuperficielSiege);
        return this;
    }

    public void setHematomeSuperficielSiege(String hematomeSuperficielSiege) {
        this.hematomeSuperficielSiege = hematomeSuperficielSiege;
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

    public TypeHemorragie getHematomePsoasType() {
        return this.hematomePsoasType;
    }

    public FicheHemophilie hematomePsoasType(TypeHemorragie hematomePsoasType) {
        this.setHematomePsoasType(hematomePsoasType);
        return this;
    }

    public void setHematomePsoasType(TypeHemorragie hematomePsoasType) {
        this.hematomePsoasType = hematomePsoasType;
    }

    public OuiNonNP getHematomePsoasRecidive() {
        return this.hematomePsoasRecidive;
    }

    public FicheHemophilie hematomePsoasRecidive(OuiNonNP hematomePsoasRecidive) {
        this.setHematomePsoasRecidive(hematomePsoasRecidive);
        return this;
    }

    public void setHematomePsoasRecidive(OuiNonNP hematomePsoasRecidive) {
        this.hematomePsoasRecidive = hematomePsoasRecidive;
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

    public TypeHemorragieCutaneoMuqueuse getHemorragiesCutaneoMuqueusesTypes() {
        return this.hemorragiesCutaneoMuqueusesTypes;
    }

    public FicheHemophilie hemorragiesCutaneoMuqueusesTypes(TypeHemorragieCutaneoMuqueuse hemorragiesCutaneoMuqueusesTypes) {
        this.setHemorragiesCutaneoMuqueusesTypes(hemorragiesCutaneoMuqueusesTypes);
        return this;
    }

    public void setHemorragiesCutaneoMuqueusesTypes(TypeHemorragieCutaneoMuqueuse hemorragiesCutaneoMuqueusesTypes) {
        this.hemorragiesCutaneoMuqueusesTypes = hemorragiesCutaneoMuqueusesTypes;
    }

    public Integer getHemorragiesCutaneoMuqueusesFrequenceAn() {
        return this.hemorragiesCutaneoMuqueusesFrequenceAn;
    }

    public FicheHemophilie hemorragiesCutaneoMuqueusesFrequenceAn(Integer hemorragiesCutaneoMuqueusesFrequenceAn) {
        this.setHemorragiesCutaneoMuqueusesFrequenceAn(hemorragiesCutaneoMuqueusesFrequenceAn);
        return this;
    }

    public void setHemorragiesCutaneoMuqueusesFrequenceAn(Integer hemorragiesCutaneoMuqueusesFrequenceAn) {
        this.hemorragiesCutaneoMuqueusesFrequenceAn = hemorragiesCutaneoMuqueusesFrequenceAn;
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

    public TypeHemorragieViscere getHemorragieVisceresType() {
        return this.hemorragieVisceresType;
    }

    public FicheHemophilie hemorragieVisceresType(TypeHemorragieViscere hemorragieVisceresType) {
        this.setHemorragieVisceresType(hemorragieVisceresType);
        return this;
    }

    public void setHemorragieVisceresType(TypeHemorragieViscere hemorragieVisceresType) {
        this.hemorragieVisceresType = hemorragieVisceresType;
    }

    public OuiNonNP getHemorragieVisceresExploration() {
        return this.hemorragieVisceresExploration;
    }

    public FicheHemophilie hemorragieVisceresExploration(OuiNonNP hemorragieVisceresExploration) {
        this.setHemorragieVisceresExploration(hemorragieVisceresExploration);
        return this;
    }

    public void setHemorragieVisceresExploration(OuiNonNP hemorragieVisceresExploration) {
        this.hemorragieVisceresExploration = hemorragieVisceresExploration;
    }

    public String getHemorragieVisceresExamen() {
        return this.hemorragieVisceresExamen;
    }

    public FicheHemophilie hemorragieVisceresExamen(String hemorragieVisceresExamen) {
        this.setHemorragieVisceresExamen(hemorragieVisceresExamen);
        return this;
    }

    public void setHemorragieVisceresExamen(String hemorragieVisceresExamen) {
        this.hemorragieVisceresExamen = hemorragieVisceresExamen;
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

    public EvolutionSNC getSaignementSNCEvolution() {
        return this.saignementSNCEvolution;
    }

    public FicheHemophilie saignementSNCEvolution(EvolutionSNC saignementSNCEvolution) {
        this.setSaignementSNCEvolution(saignementSNCEvolution);
        return this;
    }

    public void setSaignementSNCEvolution(EvolutionSNC saignementSNCEvolution) {
        this.saignementSNCEvolution = saignementSNCEvolution;
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

    public OuiNonNP getCirconcisionPreparation() {
        return this.circoncisionPreparation;
    }

    public FicheHemophilie circoncisionPreparation(OuiNonNP circoncisionPreparation) {
        this.setCirconcisionPreparation(circoncisionPreparation);
        return this;
    }

    public void setCirconcisionPreparation(OuiNonNP circoncisionPreparation) {
        this.circoncisionPreparation = circoncisionPreparation;
    }

    public SuiteOperatoire getCirconcisionSuiteOperatoire() {
        return this.circoncisionSuiteOperatoire;
    }

    public FicheHemophilie circoncisionSuiteOperatoire(SuiteOperatoire circoncisionSuiteOperatoire) {
        this.setCirconcisionSuiteOperatoire(circoncisionSuiteOperatoire);
        return this;
    }

    public void setCirconcisionSuiteOperatoire(SuiteOperatoire circoncisionSuiteOperatoire) {
        this.circoncisionSuiteOperatoire = circoncisionSuiteOperatoire;
    }

    public Integer getCirconcisionAge() {
        return this.circoncisionAge;
    }

    public FicheHemophilie circoncisionAge(Integer circoncisionAge) {
        this.setCirconcisionAge(circoncisionAge);
        return this;
    }

    public void setCirconcisionAge(Integer circoncisionAge) {
        this.circoncisionAge = circoncisionAge;
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

    public OuiNonNP getAvulsionDentairePreparation() {
        return this.avulsionDentairePreparation;
    }

    public FicheHemophilie avulsionDentairePreparation(OuiNonNP avulsionDentairePreparation) {
        this.setAvulsionDentairePreparation(avulsionDentairePreparation);
        return this;
    }

    public void setAvulsionDentairePreparation(OuiNonNP avulsionDentairePreparation) {
        this.avulsionDentairePreparation = avulsionDentairePreparation;
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

    public String getInterventionChirurgicaleType() {
        return this.interventionChirurgicaleType;
    }

    public FicheHemophilie interventionChirurgicaleType(String interventionChirurgicaleType) {
        this.setInterventionChirurgicaleType(interventionChirurgicaleType);
        return this;
    }

    public void setInterventionChirurgicaleType(String interventionChirurgicaleType) {
        this.interventionChirurgicaleType = interventionChirurgicaleType;
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

    public TypeAccidentHemorragiqueGrave getAccidentsHemorragiquesGravesTypes() {
        return this.accidentsHemorragiquesGravesTypes;
    }

    public FicheHemophilie accidentsHemorragiquesGravesTypes(TypeAccidentHemorragiqueGrave accidentsHemorragiquesGravesTypes) {
        this.setAccidentsHemorragiquesGravesTypes(accidentsHemorragiquesGravesTypes);
        return this;
    }

    public void setAccidentsHemorragiquesGravesTypes(TypeAccidentHemorragiqueGrave accidentsHemorragiquesGravesTypes) {
        this.accidentsHemorragiquesGravesTypes = accidentsHemorragiquesGravesTypes;
    }

    public TypePriseEnCharge getPriseEnChargeTherapeutique() {
        return this.priseEnChargeTherapeutique;
    }

    public FicheHemophilie priseEnChargeTherapeutique(TypePriseEnCharge priseEnChargeTherapeutique) {
        this.setPriseEnChargeTherapeutique(priseEnChargeTherapeutique);
        return this;
    }

    public void setPriseEnChargeTherapeutique(TypePriseEnCharge priseEnChargeTherapeutique) {
        this.priseEnChargeTherapeutique = priseEnChargeTherapeutique;
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

    public String getProphylaxieDoseFrequence() {
        return this.prophylaxieDoseFrequence;
    }

    public FicheHemophilie prophylaxieDoseFrequence(String prophylaxieDoseFrequence) {
        this.setProphylaxieDoseFrequence(prophylaxieDoseFrequence);
        return this;
    }

    public void setProphylaxieDoseFrequence(String prophylaxieDoseFrequence) {
        this.prophylaxieDoseFrequence = prophylaxieDoseFrequence;
    }

    public LocalDate getDateDebutProphylaxie() {
        return this.dateDebutProphylaxie;
    }

    public FicheHemophilie dateDebutProphylaxie(LocalDate dateDebutProphylaxie) {
        this.setDateDebutProphylaxie(dateDebutProphylaxie);
        return this;
    }

    public void setDateDebutProphylaxie(LocalDate dateDebutProphylaxie) {
        this.dateDebutProphylaxie = dateDebutProphylaxie;
    }

    public InjectionType getInjectionType() {
        return this.injectionType;
    }

    public FicheHemophilie injectionType(InjectionType injectionType) {
        this.setInjectionType(injectionType);
        return this;
    }

    public void setInjectionType(InjectionType injectionType) {
        this.injectionType = injectionType;
    }

    public ModaliteTraitement getModaliteTraitement() {
        return this.modaliteTraitement;
    }

    public FicheHemophilie modaliteTraitement(ModaliteTraitement modaliteTraitement) {
        this.setModaliteTraitement(modaliteTraitement);
        return this;
    }

    public void setModaliteTraitement(ModaliteTraitement modaliteTraitement) {
        this.modaliteTraitement = modaliteTraitement;
    }

    public String getTypeTraitementSubstitutif() {
        return this.typeTraitementSubstitutif;
    }

    public FicheHemophilie typeTraitementSubstitutif(String typeTraitementSubstitutif) {
        this.setTypeTraitementSubstitutif(typeTraitementSubstitutif);
        return this;
    }

    public void setTypeTraitementSubstitutif(String typeTraitementSubstitutif) {
        this.typeTraitementSubstitutif = typeTraitementSubstitutif;
    }

    public Integer getAgePremiereSubstitution() {
        return this.agePremiereSubstitution;
    }

    public FicheHemophilie agePremiereSubstitution(Integer agePremiereSubstitution) {
        this.setAgePremiereSubstitution(agePremiereSubstitution);
        return this;
    }

    public void setAgePremiereSubstitution(Integer agePremiereSubstitution) {
        this.agePremiereSubstitution = agePremiereSubstitution;
    }

    public OuiNonNP getAntecedentsTraitementPSL() {
        return this.antecedentsTraitementPSL;
    }

    public FicheHemophilie antecedentsTraitementPSL(OuiNonNP antecedentsTraitementPSL) {
        this.setAntecedentsTraitementPSL(antecedentsTraitementPSL);
        return this;
    }

    public void setAntecedentsTraitementPSL(OuiNonNP antecedentsTraitementPSL) {
        this.antecedentsTraitementPSL = antecedentsTraitementPSL;
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

    public Boolean getSynovite() {
        return this.synovite;
    }

    public FicheHemophilie synovite(Boolean synovite) {
        this.setSynovite(synovite);
        return this;
    }

    public void setSynovite(Boolean synovite) {
        this.synovite = synovite;
    }

    public Boolean getArthropathieHemophiliqueChronique() {
        return this.arthropathieHemophiliqueChronique;
    }

    public FicheHemophilie arthropathieHemophiliqueChronique(Boolean arthropathieHemophiliqueChronique) {
        this.setArthropathieHemophiliqueChronique(arthropathieHemophiliqueChronique);
        return this;
    }

    public void setArthropathieHemophiliqueChronique(Boolean arthropathieHemophiliqueChronique) {
        this.arthropathieHemophiliqueChronique = arthropathieHemophiliqueChronique;
    }

    public Boolean getPseudotumeurs() {
        return this.pseudotumeurs;
    }

    public FicheHemophilie pseudotumeurs(Boolean pseudotumeurs) {
        this.setPseudotumeurs(pseudotumeurs);
        return this;
    }

    public void setPseudotumeurs(Boolean pseudotumeurs) {
        this.pseudotumeurs = pseudotumeurs;
    }

    public Boolean getFractures() {
        return this.fractures;
    }

    public FicheHemophilie fractures(Boolean fractures) {
        this.setFractures(fractures);
        return this;
    }

    public void setFractures(Boolean fractures) {
        this.fractures = fractures;
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

    public OuiNonNP getInhibiteurs() {
        return this.inhibiteurs;
    }

    public FicheHemophilie inhibiteurs(OuiNonNP inhibiteurs) {
        this.setInhibiteurs(inhibiteurs);
        return this;
    }

    public void setInhibiteurs(OuiNonNP inhibiteurs) {
        this.inhibiteurs = inhibiteurs;
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

    public Boolean getTestRecuperationFAH() {
        return this.testRecuperationFAH;
    }

    public FicheHemophilie testRecuperationFAH(Boolean testRecuperationFAH) {
        this.setTestRecuperationFAH(testRecuperationFAH);
        return this;
    }

    public void setTestRecuperationFAH(Boolean testRecuperationFAH) {
        this.testRecuperationFAH = testRecuperationFAH;
    }

    public LocalDate getDateTestRecuperation() {
        return this.dateTestRecuperation;
    }

    public FicheHemophilie dateTestRecuperation(LocalDate dateTestRecuperation) {
        this.setDateTestRecuperation(dateTestRecuperation);
        return this;
    }

    public void setDateTestRecuperation(LocalDate dateTestRecuperation) {
        this.dateTestRecuperation = dateTestRecuperation;
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

    public Boolean getAntiFAHNonNeutralisant() {
        return this.antiFAHNonNeutralisant;
    }

    public FicheHemophilie antiFAHNonNeutralisant(Boolean antiFAHNonNeutralisant) {
        this.setAntiFAHNonNeutralisant(antiFAHNonNeutralisant);
        return this;
    }

    public void setAntiFAHNonNeutralisant(Boolean antiFAHNonNeutralisant) {
        this.antiFAHNonNeutralisant = antiFAHNonNeutralisant;
    }

    public Boolean getAntiFAHNegatif() {
        return this.antiFAHNegatif;
    }

    public FicheHemophilie antiFAHNegatif(Boolean antiFAHNegatif) {
        this.setAntiFAHNegatif(antiFAHNegatif);
        return this;
    }

    public void setAntiFAHNegatif(Boolean antiFAHNegatif) {
        this.antiFAHNegatif = antiFAHNegatif;
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

    public Boolean getAugmenterDoseFacteur() {
        return this.augmenterDoseFacteur;
    }

    public FicheHemophilie augmenterDoseFacteur(Boolean augmenterDoseFacteur) {
        this.setAugmenterDoseFacteur(augmenterDoseFacteur);
        return this;
    }

    public void setAugmenterDoseFacteur(Boolean augmenterDoseFacteur) {
        this.augmenterDoseFacteur = augmenterDoseFacteur;
    }

    public Boolean getNovoseven() {
        return this.novoseven;
    }

    public FicheHemophilie novoseven(Boolean novoseven) {
        this.setNovoseven(novoseven);
        return this;
    }

    public void setNovoseven(Boolean novoseven) {
        this.novoseven = novoseven;
    }

    public Boolean getFeiba() {
        return this.feiba;
    }

    public FicheHemophilie feiba(Boolean feiba) {
        this.setFeiba(feiba);
        return this;
    }

    public void setFeiba(Boolean feiba) {
        this.feiba = feiba;
    }

    public Boolean getCorticotherapie() {
        return this.corticotherapie;
    }

    public FicheHemophilie corticotherapie(Boolean corticotherapie) {
        this.setCorticotherapie(corticotherapie);
        return this;
    }

    public void setCorticotherapie(Boolean corticotherapie) {
        this.corticotherapie = corticotherapie;
    }

    public Boolean getImmurel() {
        return this.immurel;
    }

    public FicheHemophilie immurel(Boolean immurel) {
        this.setImmurel(immurel);
        return this;
    }

    public void setImmurel(Boolean immurel) {
        this.immurel = immurel;
    }

    public Boolean getIti() {
        return this.iti;
    }

    public FicheHemophilie iti(Boolean iti) {
        this.setIti(iti);
        return this;
    }

    public void setIti(Boolean iti) {
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

    public OuiNonNP getObservanceITI() {
        return this.observanceITI;
    }

    public FicheHemophilie observanceITI(OuiNonNP observanceITI) {
        this.setObservanceITI(observanceITI);
        return this;
    }

    public void setObservanceITI(OuiNonNP observanceITI) {
        this.observanceITI = observanceITI;
    }

    public String getCauseNonObservance() {
        return this.causeNonObservance;
    }

    public FicheHemophilie causeNonObservance(String causeNonObservance) {
        this.setCauseNonObservance(causeNonObservance);
        return this;
    }

    public void setCauseNonObservance(String causeNonObservance) {
        this.causeNonObservance = causeNonObservance;
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

    public Boolean getProphylaxieHemlibra() {
        return this.prophylaxieHemlibra;
    }

    public FicheHemophilie prophylaxieHemlibra(Boolean prophylaxieHemlibra) {
        this.setProphylaxieHemlibra(prophylaxieHemlibra);
        return this;
    }

    public void setProphylaxieHemlibra(Boolean prophylaxieHemlibra) {
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

    public Boolean getMarie() {
        return this.marie;
    }

    public FicheHemophilie marie(Boolean marie) {
        this.setMarie(marie);
        return this;
    }

    public void setMarie(Boolean marie) {
        this.marie = marie;
    }

    public Boolean getEnfants() {
        return this.enfants;
    }

    public FicheHemophilie enfants(Boolean enfants) {
        this.setEnfants(enfants);
        return this;
    }

    public void setEnfants(Boolean enfants) {
        this.enfants = enfants;
    }

    public Boolean getHandicap() {
        return this.handicap;
    }

    public FicheHemophilie handicap(Boolean handicap) {
        this.setHandicap(handicap);
        return this;
    }

    public void setHandicap(Boolean handicap) {
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

    public Boolean getActiviteSportive() {
        return this.activiteSportive;
    }

    public FicheHemophilie activiteSportive(Boolean activiteSportive) {
        this.setActiviteSportive(activiteSportive);
        return this;
    }

    public void setActiviteSportive(Boolean activiteSportive) {
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

    public Boolean getDecede() {
        return this.decede;
    }

    public FicheHemophilie decede(Boolean decede) {
        this.setDecede(decede);
        return this;
    }

    public void setDecede(Boolean decede) {
        this.decede = decede;
    }

    public String getCauseDeces() {
        return this.causeDeces;
    }

    public FicheHemophilie causeDeces(String causeDeces) {
        this.setCauseDeces(causeDeces);
        return this;
    }

    public void setCauseDeces(String causeDeces) {
        this.causeDeces = causeDeces;
    }

    public LocalDate getDateDeces() {
        return this.dateDeces;
    }

    public FicheHemophilie dateDeces(LocalDate dateDeces) {
        this.setDateDeces(dateDeces);
        return this;
    }

    public void setDateDeces(LocalDate dateDeces) {
        this.dateDeces = dateDeces;
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
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", nomJeuneFille='" + getNomJeuneFille() + "'" +
            ", profession='" + getProfession() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", ageActuel=" + getAgeActuel() +
            ", origine='" + getOrigine() + "'" +
            ", autreOrigine='" + getAutreOrigine() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", etatCivil='" + getEtatCivil() + "'" +
            ", couvertureSociale='" + getCouvertureSociale() + "'" +
            ", prenomPere='" + getPrenomPere() + "'" +
            ", professionPere='" + getProfessionPere() + "'" +
            ", nomPrenomMere='" + getNomPrenomMere() + "'" +
            ", professionMere='" + getProfessionMere() + "'" +
            ", medecinTraitant='" + getMedecinTraitant() + "'" +
            ", serviceOrigine='" + getServiceOrigine() + "'" +
            ", biologisteResponsableDg='" + getBiologisteResponsableDg() + "'" +
            ", diagnostic='" + getDiagnostic() + "'" +
            ", autreDiagnostic='" + getAutreDiagnostic() + "'" +
            ", anneeDiagnostic=" + getAnneeDiagnostic() +
            ", consentementRegistre='" + getConsentementRegistre() + "'" +
            ", dateEnregistrementRegistre='" + getDateEnregistrementRegistre() + "'" +
            ", dossierNumber='" + getDossierNumber() + "'" +
            ", ordreNumber='" + getOrdreNumber() + "'" +
            ", indexNumber='" + getIndexNumber() + "'" +
            ", parentsConsanguins='" + getParentsConsanguins() + "'" +
            ", degreParenteConsanguins='" + getDegreParenteConsanguins() + "'" +
            ", casSimilaires='" + getCasSimilaires() + "'" +
            ", nombreCas=" + getNombreCas() +
            ", degreParenteCasSimilaires='" + getDegreParenteCasSimilaires() + "'" +
            ", decesSyndromeHemorragique='" + getDecesSyndromeHemorragique() + "'" +
            ", nombreDeces=" + getNombreDeces() +
            ", formeHemophilie='" + getFormeHemophilie() + "'" +
            ", fratrieNbFreres=" + getFratrieNbFreres() +
            ", fratrieNbSoeurs=" + getFratrieNbSoeurs() +
            ", decouverteFortuite='" + getDecouverteFortuite() + "'" +
            ", enqueteFamiliale='" + getEnqueteFamiliale() + "'" +
            ", explorationSyndrome='" + getExplorationSyndrome() + "'" +
            ", cutaneoMuqueux='" + getCutaneoMuqueux() + "'" +
            ", hemarthrose='" + getHemarthrose() + "'" +
            ", hematomePostTraumatique='" + getHematomePostTraumatique() + "'" +
            ", plaiePostTraumatique='" + getPlaiePostTraumatique() + "'" +
            ", hemorragiePostCirconcision='" + getHemorragiePostCirconcision() + "'" +
            ", hemorragiePostChirurgie='" + getHemorragiePostChirurgie() + "'" +
            ", hemorragieCerebrale='" + getHemorragieCerebrale() + "'" +
            ", hemorragieDigestive='" + getHemorragieDigestive() + "'" +
            ", datePremierSigneClinique='" + getDatePremierSigneClinique() + "'" +
            ", ageDiagnosticJours=" + getAgeDiagnosticJours() +
            ", ageDiagnosticMois=" + getAgeDiagnosticMois() +
            ", ageDiagnosticAnnees=" + getAgeDiagnosticAnnees() +
            ", dateTestConfirmation='" + getDateTestConfirmation() + "'" +
            ", hb=" + getHb() +
            ", ht=" + getHt() +
            ", plaquettes=" + getPlaquettes() +
            ", tp=" + getTp() +
            ", fibrinogene=" + getFibrinogene() +
            ", tcaMT=" + getTcaMT() +
            ", tcaMTTotal=" + getTcaMTTotal() +
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
            ", inversionIntron22='" + getInversionIntron22() + "'" +
            ", autreExplorationMoleculaire='" + getAutreExplorationMoleculaire() + "'" +
            ", hemarthroseSiege='" + getHemarthroseSiege() + "'" +
            ", hemarthroseType='" + getHemarthroseType() + "'" +
            ", hemarthroseFrequenceAn=" + getHemarthroseFrequenceAn() +
            ", hematomeSuperficiel='" + getHematomeSuperficiel() + "'" +
            ", hematomeSuperficielType='" + getHematomeSuperficielType() + "'" +
            ", hematomeSuperficielSiege='" + getHematomeSuperficielSiege() + "'" +
            ", hematomePsoas='" + getHematomePsoas() + "'" +
            ", hematomePsoasType='" + getHematomePsoasType() + "'" +
            ", hematomePsoasRecidive='" + getHematomePsoasRecidive() + "'" +
            ", hemorragiesCutaneoMuqueuses='" + getHemorragiesCutaneoMuqueuses() + "'" +
            ", hemorragiesCutaneoMuqueusesTypes='" + getHemorragiesCutaneoMuqueusesTypes() + "'" +
            ", hemorragiesCutaneoMuqueusesFrequenceAn=" + getHemorragiesCutaneoMuqueusesFrequenceAn() +
            ", hemorragieVisceres='" + getHemorragieVisceres() + "'" +
            ", hemorragieVisceresType='" + getHemorragieVisceresType() + "'" +
            ", hemorragieVisceresExploration='" + getHemorragieVisceresExploration() + "'" +
            ", hemorragieVisceresExamen='" + getHemorragieVisceresExamen() + "'" +
            ", saignementSNC='" + getSaignementSNC() + "'" +
            ", saignementSNCEvolution='" + getSaignementSNCEvolution() + "'" +
            ", circoncision='" + getCirconcision() + "'" +
            ", circoncisionPreparation='" + getCirconcisionPreparation() + "'" +
            ", circoncisionSuiteOperatoire='" + getCirconcisionSuiteOperatoire() + "'" +
            ", circoncisionAge=" + getCirconcisionAge() +
            ", avulsionDentaire='" + getAvulsionDentaire() + "'" +
            ", avulsionDentairePreparation='" + getAvulsionDentairePreparation() + "'" +
            ", interventionChirurgicale='" + getInterventionChirurgicale() + "'" +
            ", interventionChirurgicaleType='" + getInterventionChirurgicaleType() + "'" +
            ", accidentsHemorragiquesGraves='" + getAccidentsHemorragiquesGraves() + "'" +
            ", accidentsHemorragiquesGravesTypes='" + getAccidentsHemorragiquesGravesTypes() + "'" +
            ", priseEnChargeTherapeutique='" + getPriseEnChargeTherapeutique() + "'" +
            ", causePriseEnCharge='" + getCausePriseEnCharge() + "'" +
            ", prophylaxieDoseFrequence='" + getProphylaxieDoseFrequence() + "'" +
            ", dateDebutProphylaxie='" + getDateDebutProphylaxie() + "'" +
            ", injectionType='" + getInjectionType() + "'" +
            ", modaliteTraitement='" + getModaliteTraitement() + "'" +
            ", typeTraitementSubstitutif='" + getTypeTraitementSubstitutif() + "'" +
            ", agePremiereSubstitution=" + getAgePremiereSubstitution() +
            ", antecedentsTraitementPSL='" + getAntecedentsTraitementPSL() + "'" +
            ", plasmaFraisCongele='" + getPlasmaFraisCongele() + "'" +
            ", cryoprecipite='" + getCryoprecipite() + "'" +
            ", synovite='" + getSynovite() + "'" +
            ", arthropathieHemophiliqueChronique='" + getArthropathieHemophiliqueChronique() + "'" +
            ", pseudotumeurs='" + getPseudotumeurs() + "'" +
            ", fractures='" + getFractures() + "'" +
            ", articulationCible='" + getArticulationCible() + "'" +
            ", pecTherapeutique='" + getPecTherapeutique() + "'" +
            ", inhibiteurs='" + getInhibiteurs() + "'" +
            ", tauxInhibiteur='" + getTauxInhibiteur() + "'" +
            ", testRecuperationFAH='" + getTestRecuperationFAH() + "'" +
            ", dateTestRecuperation='" + getDateTestRecuperation() + "'" +
            ", tauxRecuperation='" + getTauxRecuperation() + "'" +
            ", antiFAHNonNeutralisant='" + getAntiFAHNonNeutralisant() + "'" +
            ", antiFAHNegatif='" + getAntiFAHNegatif() + "'" +
            ", rechercheACC='" + getRechercheACC() + "'" +
            ", augmenterDoseFacteur='" + getAugmenterDoseFacteur() + "'" +
            ", novoseven='" + getNovoseven() + "'" +
            ", feiba='" + getFeiba() + "'" +
            ", corticotherapie='" + getCorticotherapie() + "'" +
            ", immurel='" + getImmurel() + "'" +
            ", iti='" + getIti() + "'" +
            ", posologieITI='" + getPosologieITI() + "'" +
            ", dureeITI='" + getDureeITI() + "'" +
            ", observanceITI='" + getObservanceITI() + "'" +
            ", causeNonObservance='" + getCauseNonObservance() + "'" +
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
            ", causeDeces='" + getCauseDeces() + "'" +
            ", dateDeces='" + getDateDeces() + "'" +
            "}";
    }
}

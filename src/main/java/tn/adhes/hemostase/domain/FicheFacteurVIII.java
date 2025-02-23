package tn.adhes.hemostase.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.ExplorationType;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.MutationType;
import tn.adhes.hemostase.domain.enumeration.NormalAllonge;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.Region;
import tn.adhes.hemostase.domain.enumeration.ServiceCliniqueType;
import tn.adhes.hemostase.domain.enumeration.TypeAccidentsThrombotiques;
import tn.adhes.hemostase.domain.enumeration.TypeHemorragieGrave;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.VieSocialeType;

/**
 * A FicheFacteurVIII.
 */
@Entity
@Table(name = "fiche_facteur_viii")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheFacteurVIII implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "dossier_number")
    private String dossierNumber;

    @Size(max = 25)
    @Column(name = "ordre_number", length = 25)
    private String ordreNumber;

    @Size(max = 25)
    @Column(name = "index_number", length = 25)
    private String indexNumber;

    @Size(max = 25)
    @Column(name = "prenom", length = 25)
    private String prenom;

    @Size(max = 25)
    @Column(name = "nom", length = 25)
    private String nom;

    @Size(max = 25)
    @Column(name = "nom_jeune_fille", length = 25)
    private String nomJeuneFille;

    @Size(max = 25)
    @Column(name = "profession", length = 25)
    private String profession;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "etat_civil", nullable = false)
    private CivilStatus etatCivil;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "service_clinique", nullable = false)
    private ServiceCliniqueType serviceClinique;

    @Size(max = 255)
    @Column(name = "autre_service_clinique", length = 255)
    private String autreServiceClinique;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "couverture_sociale", nullable = false)
    private CouvertureSociale couvertureSociale;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "parents_consanguins")
    private OuiNonNP parentsConsanguins;

    @Enumerated(EnumType.STRING)
    @Column(name = "degre_parente_consanguins")
    private DegreParente degreParenteConsanguins;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_similaires")
    private OuiNonNP casSimilaires;

    @Column(name = "nombre_cas")
    private Integer nombreCas;

    @Column(name = "cas_1_numero")
    private Integer cas1Numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_parente_cas_1")
    private LienParente lienParenteCas1;

    @Size(max = 25)
    @Column(name = "degre_parente_cas_1", length = 25)
    private String degreParenteCas1;

    @Size(max = 255)
    @Column(name = "type_hemorragie_cas_1", length = 255)
    private String typeHemorragieCas1;

    @Column(name = "cas_2_numero")
    private Integer cas2Numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_parente_cas_2")
    private LienParente lienParenteCas2;

    @Size(max = 25)
    @Column(name = "degre_parente_cas_2", length = 25)
    private String degreParenteCas2;

    @Size(max = 255)
    @Column(name = "type_hemorragie_cas_2", length = 255)
    private String typeHemorragieCas2;

    @Column(name = "cas_3_numero")
    private Integer cas3Numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_parente_cas_3")
    private LienParente lienParenteCas3;

    @Size(max = 25)
    @Column(name = "degre_parente_cas_3", length = 25)
    private String degreParenteCas3;

    @Size(max = 255)
    @Column(name = "type_hemorragie_cas_3", length = 255)
    private String typeHemorragieCas3;

    @Enumerated(EnumType.STRING)
    @Column(name = "deces_syndrome_hemorragique")
    private OuiNonNP decesSyndromeHemorragique;

    @Column(name = "nombre_deces")
    private Integer nombreDeces;

    @Column(name = "deces_1_numero")
    private Integer deces1Numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_parente_deces_1")
    private LienParente lienParenteDeces1;

    @Size(max = 255)
    @Column(name = "cause_deces_1", length = 255)
    private String causeDeces1;

    @Column(name = "deces_2_numero")
    private Integer deces2Numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_parente_deces_2")
    private LienParente lienParenteDeces2;

    @Size(max = 255)
    @Column(name = "cause_deces_2", length = 255)
    private String causeDeces2;

    @Column(name = "deces_3_numero")
    private Integer deces3Numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "lien_parente_deces_3")
    private LienParente lienParenteDeces3;

    @Size(max = 255)
    @Column(name = "cause_deces_3", length = 255)
    private String causeDeces3;

    @Enumerated(EnumType.STRING)
    @Column(name = "enquete_familiale")
    private FaiteNonFaite enqueteFamiliale;

    @Column(name = "decouverte_fortuite")
    private Boolean decouverteFortuite;

    @Column(name = "enquete_familiale_bool")
    private Boolean enqueteFamilialeBool;

    @Column(name = "exploration_syndrome_hemorragique")
    private Boolean explorationSyndromeHemorragique;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_exploration_syndrome_hemorragique")
    private ExplorationType typeExplorationSyndromeHemorragique;

    @Column(name = "date_premier_signe_clinique")
    private LocalDate datePremierSigneClinique;

    @Column(name = "age_diagnostic_jour")
    private Integer ageDiagnosticJour;

    @Column(name = "age_diagnostic_mois")
    private Integer ageDiagnosticMois;

    @Column(name = "age_diagnostic_annee")
    private Integer ageDiagnosticAnnee;

    @Column(name = "tp")
    private Float tp;

    @Column(name = "tp_mt")
    private Float tpMT;

    @Column(name = "tca")
    private Float tca;

    @Column(name = "tca_mt")
    private Float tcaMT;

    @Column(name = "fibrinogene")
    private Float fibrinogene;

    @Enumerated(EnumType.STRING)
    @Column(name = "ts")
    private FaiteNonFaite ts;

    @Enumerated(EnumType.STRING)
    @Column(name = "ts_fait")
    private NormalAllonge tsFait;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_pfa")
    private NormalAllonge toPfa;

    @Column(name = "to_pfa_200_col_epi")
    private Float toPfa200ColEpi;

    @Column(name = "to_pfa_200_col_adp")
    private Float toPfa200ColAdp;

    @Column(name = "taux_dosage_facteur_xiii")
    private Float tauxDosageFacteurXIII;

    @Column(name = "hb")
    private Float hb;

    @Column(name = "ht")
    private Float ht;

    @Column(name = "plaquettes")
    private Integer plaquettes;

    @Column(name = "technique_chromogenique")
    private Boolean techniqueChromogenique;

    @Column(name = "taux_technique_chromogenique")
    private Float tauxTechniqueChromogenique;

    @Column(name = "technique_solubilite_caillot")
    private Boolean techniqueSolubiliteCaillot;

    @Column(name = "taux_technique_solubilite_caillot")
    private Float tauxTechniqueSolubiliteCaillot;

    @Enumerated(EnumType.STRING)
    @Column(name = "confirmation_moleculaire")
    private FaiteNonFaite confirmationMoleculaire;

    @Column(name = "mutation_presence")
    private Boolean mutationPresence;

    @Enumerated(EnumType.STRING)
    @Column(name = "mutation_type")
    private MutationType mutationType;

    @Column(name = "epistaxis")
    private Boolean epistaxis;

    @Column(name = "frequence_epistaxis")
    private Integer frequenceEpistaxis;

    @Column(name = "gingivorragies")
    private Boolean gingivorragies;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_gingivorragies")
    private TypeManifestation typeGingivorragies;

    @Column(name = "frequence_gingivorragies")
    private Integer frequenceGingivorragies;

    @Column(name = "ecchymose")
    private Boolean ecchymose;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_ecchymose")
    private TypeManifestation typeEcchymose;

    @Column(name = "frequence_ecchymose")
    private Integer frequenceEcchymose;

    @Column(name = "menorragie")
    private Boolean menorragie;

    @Column(name = "frequence_menorragie")
    private Integer frequenceMenorragie;

    @Column(name = "hematemese")
    private Boolean hematemese;

    @Column(name = "frequence_hematemese")
    private Integer frequenceHematemese;

    @Column(name = "rectorragie")
    private Boolean rectorragie;

    @Column(name = "frequence_rectorragie")
    private Integer frequenceRectorragie;

    @Column(name = "hematurie")
    private Boolean hematurie;

    @Column(name = "frequence_hematurie")
    private Integer frequenceHematurie;

    @Column(name = "hematome_sous_cutane")
    private Boolean hematomeSousCutane;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_hematome_sous_cutane")
    private TypeManifestation typeHematomeSousCutane;

    @Column(name = "frequence_hematome_sous_cutane")
    private Integer frequenceHematomeSousCutane;

    @Column(name = "hematome_musculaire")
    private Boolean hematomeMusculaire;

    @Size(max = 255)
    @Column(name = "siege_hematome_musculaire", length = 255)
    private String siegeHematomeMusculaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_hematome_musculaire")
    private TypeManifestation typeHematomeMusculaire;

    @Column(name = "frequence_hematome_musculaire")
    private Integer frequenceHematomeMusculaire;

    @Column(name = "hemarthrose")
    private Boolean hemarthrose;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_hemarthrose")
    private TypeManifestation typeHemarthrose;

    @Column(name = "frequence_hemarthrose")
    private Integer frequenceHemarthrose;

    @Column(name = "saignement_snc")
    private Boolean saignementSNC;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_saignement_snc")
    private TypeManifestation typeSaignementSNC;

    @Column(name = "frequence_saignement_snc")
    private Integer frequenceSaignementSNC;

    @Column(name = "rupture_kystes_hemorragiques")
    private Boolean ruptureKystesHemorragiques;

    @Column(name = "hemorragies_post_chirurgicales")
    private Boolean hemorragiesPostChirurgicales;

    @Column(name = "hemoperitone")
    private Boolean hemoperitone;

    @Column(name = "accidents_thrombotiques")
    private Boolean accidentsThrombotiques;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_accidents_thrombotiques")
    private TypeAccidentsThrombotiques typeAccidentsThrombotiques;

    @Column(name = "hemorragie_grave")
    private Boolean hemorragieGrave;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_hemorragie_grave")
    private TypeHemorragieGrave typeHemorragieGrave;

    @Enumerated(EnumType.STRING)
    @Column(name = "chirurgie_prepare")
    private OuiNonNP chirurgiePrepare;

    @Enumerated(EnumType.STRING)
    @Column(name = "suite_operatoire_hemorragique")
    private OuiNonNP suiteOperatoireHemorragique;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision_prepare")
    private OuiNonNP circoncisionPrepare;

    @Enumerated(EnumType.STRING)
    @Column(name = "suite_operatoire_circoncision")
    private OuiNonNP suiteOperatoireCirconcision;

    @Column(name = "age_circoncision")
    private Integer ageCirconcision;

    @Enumerated(EnumType.STRING)
    @Column(name = "facteur_viii_recombinant")
    private OuiNonNP facteurVIIIRecombinant;

    @Enumerated(EnumType.STRING)
    @Column(name = "plasma_frais_congele")
    private OuiNonNP plasmaFraisCongele;

    @Enumerated(EnumType.STRING)
    @Column(name = "cryoprecipite")
    private OuiNonNP cryoprecipite;

    @Enumerated(EnumType.STRING)
    @Column(name = "culots_plaquettaires")
    private OuiNonNP culotsPlaquettaires;

    @Enumerated(EnumType.STRING)
    @Column(name = "dicynone")
    private OuiNonNP dicynone;

    @Enumerated(EnumType.STRING)
    @Column(name = "exacyl")
    private OuiNonNP exacyl;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_martial")
    private OuiNonNP traitementMartial;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_oestroprogestatif")
    private OuiNonNP traitementOestroprogestatif;

    @Enumerated(EnumType.STRING)
    @Column(name = "transfusion_cgr")
    private OuiNonNP transfusionCGR;

    @Enumerated(EnumType.STRING)
    @Column(name = "inhibiteurs")
    private OuiNonNP inhibiteurs;

    @Column(name = "inhibiteur_neutralisant")
    private Boolean inhibiteurNeutralisant;

    @Column(name = "titre_ub")
    private Float titreUB;

    @Column(name = "inhibiteur_non_neutralisant")
    private Boolean inhibiteurNonNeutralisant;

    @Enumerated(EnumType.STRING)
    @Column(name = "infections_virales")
    private OuiNonNP infectionsVirales;

    @Enumerated(EnumType.STRING)
    @Column(name = "thrombose")
    private OuiNonNP thrombose;

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction_allergique")
    private OuiNonNP reactionAllergique;

    @Enumerated(EnumType.STRING)
    @Column(name = "allo_immunisation")
    private OuiNonNP alloImmunisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "adhesion_therapeutique")
    private OuiNonNP adhesionTherapeutique;

    @Size(max = 255)
    @Column(name = "cause_non_adhesion", length = 255)
    private String causeNonAdhesion;

    @Enumerated(EnumType.STRING)
    @Column(name = "vie_sociale")
    private VieSocialeType vieSociale;

    @Enumerated(EnumType.STRING)
    @Column(name = "mariage")
    private OuiNonNP mariage;

    @Enumerated(EnumType.STRING)
    @Column(name = "enfants")
    private OuiNonNP enfants;

    @Enumerated(EnumType.STRING)
    @Column(name = "activite_physique")
    private OuiNonNP activitePhysique;

    @Size(max = 255)
    @Column(name = "type_activite_physique", length = 255)
    private String typeActivitePhysique;

    @Enumerated(EnumType.STRING)
    @Column(name = "sequelles")
    private OuiNonNP sequelles;

    @Size(max = 255)
    @Column(name = "type_sequelles", length = 255)
    private String typeSequelles;

    @Enumerated(EnumType.STRING)
    @Column(name = "patient_decede")
    private OuiNonNP patientDecede;

    @Column(name = "date_deces")
    private LocalDate dateDeces;

    @Column(name = "age_deces")
    private Integer ageDeces;

    @Column(name = "hemorragie_snc")
    private Boolean hemorragieSnc;

    @Column(name = "hemorragie_autre_que_snc")
    private Boolean hemorragieAutreQueSnc;

    @Column(name = "hiv")
    private Boolean hiv;

    @Column(name = "autre_infection")
    private Boolean autreInfection;

    @Column(name = "evenement_thromboembolique")
    private Boolean evenementThromboembolique;

    @Column(name = "cancer")
    private Boolean cancer;

    @Column(name = "covid_19")
    private Boolean covid19;

    @Column(name = "inconnue")
    private Boolean inconnue;

    @Column(name = "autre_evenement")
    private Boolean autreEvenement;

    @Size(max = 255)
    @Column(name = "autre_evenement_details", length = 255)
    private String autreEvenementDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FicheFacteurVIII id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDossierNumber() {
        return this.dossierNumber;
    }

    public FicheFacteurVIII dossierNumber(String dossierNumber) {
        this.setDossierNumber(dossierNumber);
        return this;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return this.ordreNumber;
    }

    public FicheFacteurVIII ordreNumber(String ordreNumber) {
        this.setOrdreNumber(ordreNumber);
        return this;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return this.indexNumber;
    }

    public FicheFacteurVIII indexNumber(String indexNumber) {
        this.setIndexNumber(indexNumber);
        return this;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public FicheFacteurVIII prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public FicheFacteurVIII nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomJeuneFille() {
        return this.nomJeuneFille;
    }

    public FicheFacteurVIII nomJeuneFille(String nomJeuneFille) {
        this.setNomJeuneFille(nomJeuneFille);
        return this;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public String getProfession() {
        return this.profession;
    }

    public FicheFacteurVIII profession(String profession) {
        this.setProfession(profession);
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public CivilStatus getEtatCivil() {
        return this.etatCivil;
    }

    public FicheFacteurVIII etatCivil(CivilStatus etatCivil) {
        this.setEtatCivil(etatCivil);
        return this;
    }

    public void setEtatCivil(CivilStatus etatCivil) {
        this.etatCivil = etatCivil;
    }

    public ServiceCliniqueType getServiceClinique() {
        return this.serviceClinique;
    }

    public FicheFacteurVIII serviceClinique(ServiceCliniqueType serviceClinique) {
        this.setServiceClinique(serviceClinique);
        return this;
    }

    public void setServiceClinique(ServiceCliniqueType serviceClinique) {
        this.serviceClinique = serviceClinique;
    }

    public String getAutreServiceClinique() {
        return this.autreServiceClinique;
    }

    public FicheFacteurVIII autreServiceClinique(String autreServiceClinique) {
        this.setAutreServiceClinique(autreServiceClinique);
        return this;
    }

    public void setAutreServiceClinique(String autreServiceClinique) {
        this.autreServiceClinique = autreServiceClinique;
    }

    public CouvertureSociale getCouvertureSociale() {
        return this.couvertureSociale;
    }

    public FicheFacteurVIII couvertureSociale(CouvertureSociale couvertureSociale) {
        this.setCouvertureSociale(couvertureSociale);
        return this;
    }

    public void setCouvertureSociale(CouvertureSociale couvertureSociale) {
        this.couvertureSociale = couvertureSociale;
    }

    public Gender getSexe() {
        return this.sexe;
    }

    public FicheFacteurVIII sexe(Gender sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Gender sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public FicheFacteurVIII dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Integer getAgeActuel() {
        return this.ageActuel;
    }

    public FicheFacteurVIII ageActuel(Integer ageActuel) {
        this.setAgeActuel(ageActuel);
        return this;
    }

    public void setAgeActuel(Integer ageActuel) {
        this.ageActuel = ageActuel;
    }

    public Region getOrigine() {
        return this.origine;
    }

    public FicheFacteurVIII origine(Region origine) {
        this.setOrigine(origine);
        return this;
    }

    public void setOrigine(Region origine) {
        this.origine = origine;
    }

    public String getAutreOrigine() {
        return this.autreOrigine;
    }

    public FicheFacteurVIII autreOrigine(String autreOrigine) {
        this.setAutreOrigine(autreOrigine);
        return this;
    }

    public void setAutreOrigine(String autreOrigine) {
        this.autreOrigine = autreOrigine;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public FicheFacteurVIII adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public FicheFacteurVIII telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrenomPere() {
        return this.prenomPere;
    }

    public FicheFacteurVIII prenomPere(String prenomPere) {
        this.setPrenomPere(prenomPere);
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getProfessionPere() {
        return this.professionPere;
    }

    public FicheFacteurVIII professionPere(String professionPere) {
        this.setProfessionPere(professionPere);
        return this;
    }

    public void setProfessionPere(String professionPere) {
        this.professionPere = professionPere;
    }

    public String getNomPrenomMere() {
        return this.nomPrenomMere;
    }

    public FicheFacteurVIII nomPrenomMere(String nomPrenomMere) {
        this.setNomPrenomMere(nomPrenomMere);
        return this;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }

    public String getProfessionMere() {
        return this.professionMere;
    }

    public FicheFacteurVIII professionMere(String professionMere) {
        this.setProfessionMere(professionMere);
        return this;
    }

    public void setProfessionMere(String professionMere) {
        this.professionMere = professionMere;
    }

    public String getMedecinTraitant() {
        return this.medecinTraitant;
    }

    public FicheFacteurVIII medecinTraitant(String medecinTraitant) {
        this.setMedecinTraitant(medecinTraitant);
        return this;
    }

    public void setMedecinTraitant(String medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }

    public String getServiceOrigine() {
        return this.serviceOrigine;
    }

    public FicheFacteurVIII serviceOrigine(String serviceOrigine) {
        this.setServiceOrigine(serviceOrigine);
        return this;
    }

    public void setServiceOrigine(String serviceOrigine) {
        this.serviceOrigine = serviceOrigine;
    }

    public String getBiologisteResponsableDg() {
        return this.biologisteResponsableDg;
    }

    public FicheFacteurVIII biologisteResponsableDg(String biologisteResponsableDg) {
        this.setBiologisteResponsableDg(biologisteResponsableDg);
        return this;
    }

    public void setBiologisteResponsableDg(String biologisteResponsableDg) {
        this.biologisteResponsableDg = biologisteResponsableDg;
    }

    public DiagnosticType getDiagnostic() {
        return this.diagnostic;
    }

    public FicheFacteurVIII diagnostic(DiagnosticType diagnostic) {
        this.setDiagnostic(diagnostic);
        return this;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return this.autreDiagnostic;
    }

    public FicheFacteurVIII autreDiagnostic(String autreDiagnostic) {
        this.setAutreDiagnostic(autreDiagnostic);
        return this;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public Integer getAnneeDiagnostic() {
        return this.anneeDiagnostic;
    }

    public FicheFacteurVIII anneeDiagnostic(Integer anneeDiagnostic) {
        this.setAnneeDiagnostic(anneeDiagnostic);
        return this;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public OuiNonNP getConsentementRegistre() {
        return this.consentementRegistre;
    }

    public FicheFacteurVIII consentementRegistre(OuiNonNP consentementRegistre) {
        this.setConsentementRegistre(consentementRegistre);
        return this;
    }

    public void setConsentementRegistre(OuiNonNP consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return this.dateEnregistrementRegistre;
    }

    public FicheFacteurVIII dateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.setDateEnregistrementRegistre(dateEnregistrementRegistre);
        return this;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
    }

    public OuiNonNP getParentsConsanguins() {
        return this.parentsConsanguins;
    }

    public FicheFacteurVIII parentsConsanguins(OuiNonNP parentsConsanguins) {
        this.setParentsConsanguins(parentsConsanguins);
        return this;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public DegreParente getDegreParenteConsanguins() {
        return this.degreParenteConsanguins;
    }

    public FicheFacteurVIII degreParenteConsanguins(DegreParente degreParenteConsanguins) {
        this.setDegreParenteConsanguins(degreParenteConsanguins);
        return this;
    }

    public void setDegreParenteConsanguins(DegreParente degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public OuiNonNP getCasSimilaires() {
        return this.casSimilaires;
    }

    public FicheFacteurVIII casSimilaires(OuiNonNP casSimilaires) {
        this.setCasSimilaires(casSimilaires);
        return this;
    }

    public void setCasSimilaires(OuiNonNP casSimilaires) {
        this.casSimilaires = casSimilaires;
    }

    public Integer getNombreCas() {
        return this.nombreCas;
    }

    public FicheFacteurVIII nombreCas(Integer nombreCas) {
        this.setNombreCas(nombreCas);
        return this;
    }

    public void setNombreCas(Integer nombreCas) {
        this.nombreCas = nombreCas;
    }

    public Integer getCas1Numero() {
        return this.cas1Numero;
    }

    public FicheFacteurVIII cas1Numero(Integer cas1Numero) {
        this.setCas1Numero(cas1Numero);
        return this;
    }

    public void setCas1Numero(Integer cas1Numero) {
        this.cas1Numero = cas1Numero;
    }

    public LienParente getLienParenteCas1() {
        return this.lienParenteCas1;
    }

    public FicheFacteurVIII lienParenteCas1(LienParente lienParenteCas1) {
        this.setLienParenteCas1(lienParenteCas1);
        return this;
    }

    public void setLienParenteCas1(LienParente lienParenteCas1) {
        this.lienParenteCas1 = lienParenteCas1;
    }

    public String getDegreParenteCas1() {
        return this.degreParenteCas1;
    }

    public FicheFacteurVIII degreParenteCas1(String degreParenteCas1) {
        this.setDegreParenteCas1(degreParenteCas1);
        return this;
    }

    public void setDegreParenteCas1(String degreParenteCas1) {
        this.degreParenteCas1 = degreParenteCas1;
    }

    public String getTypeHemorragieCas1() {
        return this.typeHemorragieCas1;
    }

    public FicheFacteurVIII typeHemorragieCas1(String typeHemorragieCas1) {
        this.setTypeHemorragieCas1(typeHemorragieCas1);
        return this;
    }

    public void setTypeHemorragieCas1(String typeHemorragieCas1) {
        this.typeHemorragieCas1 = typeHemorragieCas1;
    }

    public Integer getCas2Numero() {
        return this.cas2Numero;
    }

    public FicheFacteurVIII cas2Numero(Integer cas2Numero) {
        this.setCas2Numero(cas2Numero);
        return this;
    }

    public void setCas2Numero(Integer cas2Numero) {
        this.cas2Numero = cas2Numero;
    }

    public LienParente getLienParenteCas2() {
        return this.lienParenteCas2;
    }

    public FicheFacteurVIII lienParenteCas2(LienParente lienParenteCas2) {
        this.setLienParenteCas2(lienParenteCas2);
        return this;
    }

    public void setLienParenteCas2(LienParente lienParenteCas2) {
        this.lienParenteCas2 = lienParenteCas2;
    }

    public String getDegreParenteCas2() {
        return this.degreParenteCas2;
    }

    public FicheFacteurVIII degreParenteCas2(String degreParenteCas2) {
        this.setDegreParenteCas2(degreParenteCas2);
        return this;
    }

    public void setDegreParenteCas2(String degreParenteCas2) {
        this.degreParenteCas2 = degreParenteCas2;
    }

    public String getTypeHemorragieCas2() {
        return this.typeHemorragieCas2;
    }

    public FicheFacteurVIII typeHemorragieCas2(String typeHemorragieCas2) {
        this.setTypeHemorragieCas2(typeHemorragieCas2);
        return this;
    }

    public void setTypeHemorragieCas2(String typeHemorragieCas2) {
        this.typeHemorragieCas2 = typeHemorragieCas2;
    }

    public Integer getCas3Numero() {
        return this.cas3Numero;
    }

    public FicheFacteurVIII cas3Numero(Integer cas3Numero) {
        this.setCas3Numero(cas3Numero);
        return this;
    }

    public void setCas3Numero(Integer cas3Numero) {
        this.cas3Numero = cas3Numero;
    }

    public LienParente getLienParenteCas3() {
        return this.lienParenteCas3;
    }

    public FicheFacteurVIII lienParenteCas3(LienParente lienParenteCas3) {
        this.setLienParenteCas3(lienParenteCas3);
        return this;
    }

    public void setLienParenteCas3(LienParente lienParenteCas3) {
        this.lienParenteCas3 = lienParenteCas3;
    }

    public String getDegreParenteCas3() {
        return this.degreParenteCas3;
    }

    public FicheFacteurVIII degreParenteCas3(String degreParenteCas3) {
        this.setDegreParenteCas3(degreParenteCas3);
        return this;
    }

    public void setDegreParenteCas3(String degreParenteCas3) {
        this.degreParenteCas3 = degreParenteCas3;
    }

    public String getTypeHemorragieCas3() {
        return this.typeHemorragieCas3;
    }

    public FicheFacteurVIII typeHemorragieCas3(String typeHemorragieCas3) {
        this.setTypeHemorragieCas3(typeHemorragieCas3);
        return this;
    }

    public void setTypeHemorragieCas3(String typeHemorragieCas3) {
        this.typeHemorragieCas3 = typeHemorragieCas3;
    }

    public OuiNonNP getDecesSyndromeHemorragique() {
        return this.decesSyndromeHemorragique;
    }

    public FicheFacteurVIII decesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.setDecesSyndromeHemorragique(decesSyndromeHemorragique);
        return this;
    }

    public void setDecesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.decesSyndromeHemorragique = decesSyndromeHemorragique;
    }

    public Integer getNombreDeces() {
        return this.nombreDeces;
    }

    public FicheFacteurVIII nombreDeces(Integer nombreDeces) {
        this.setNombreDeces(nombreDeces);
        return this;
    }

    public void setNombreDeces(Integer nombreDeces) {
        this.nombreDeces = nombreDeces;
    }

    public Integer getDeces1Numero() {
        return this.deces1Numero;
    }

    public FicheFacteurVIII deces1Numero(Integer deces1Numero) {
        this.setDeces1Numero(deces1Numero);
        return this;
    }

    public void setDeces1Numero(Integer deces1Numero) {
        this.deces1Numero = deces1Numero;
    }

    public LienParente getLienParenteDeces1() {
        return this.lienParenteDeces1;
    }

    public FicheFacteurVIII lienParenteDeces1(LienParente lienParenteDeces1) {
        this.setLienParenteDeces1(lienParenteDeces1);
        return this;
    }

    public void setLienParenteDeces1(LienParente lienParenteDeces1) {
        this.lienParenteDeces1 = lienParenteDeces1;
    }

    public String getCauseDeces1() {
        return this.causeDeces1;
    }

    public FicheFacteurVIII causeDeces1(String causeDeces1) {
        this.setCauseDeces1(causeDeces1);
        return this;
    }

    public void setCauseDeces1(String causeDeces1) {
        this.causeDeces1 = causeDeces1;
    }

    public Integer getDeces2Numero() {
        return this.deces2Numero;
    }

    public FicheFacteurVIII deces2Numero(Integer deces2Numero) {
        this.setDeces2Numero(deces2Numero);
        return this;
    }

    public void setDeces2Numero(Integer deces2Numero) {
        this.deces2Numero = deces2Numero;
    }

    public LienParente getLienParenteDeces2() {
        return this.lienParenteDeces2;
    }

    public FicheFacteurVIII lienParenteDeces2(LienParente lienParenteDeces2) {
        this.setLienParenteDeces2(lienParenteDeces2);
        return this;
    }

    public void setLienParenteDeces2(LienParente lienParenteDeces2) {
        this.lienParenteDeces2 = lienParenteDeces2;
    }

    public String getCauseDeces2() {
        return this.causeDeces2;
    }

    public FicheFacteurVIII causeDeces2(String causeDeces2) {
        this.setCauseDeces2(causeDeces2);
        return this;
    }

    public void setCauseDeces2(String causeDeces2) {
        this.causeDeces2 = causeDeces2;
    }

    public Integer getDeces3Numero() {
        return this.deces3Numero;
    }

    public FicheFacteurVIII deces3Numero(Integer deces3Numero) {
        this.setDeces3Numero(deces3Numero);
        return this;
    }

    public void setDeces3Numero(Integer deces3Numero) {
        this.deces3Numero = deces3Numero;
    }

    public LienParente getLienParenteDeces3() {
        return this.lienParenteDeces3;
    }

    public FicheFacteurVIII lienParenteDeces3(LienParente lienParenteDeces3) {
        this.setLienParenteDeces3(lienParenteDeces3);
        return this;
    }

    public void setLienParenteDeces3(LienParente lienParenteDeces3) {
        this.lienParenteDeces3 = lienParenteDeces3;
    }

    public String getCauseDeces3() {
        return this.causeDeces3;
    }

    public FicheFacteurVIII causeDeces3(String causeDeces3) {
        this.setCauseDeces3(causeDeces3);
        return this;
    }

    public void setCauseDeces3(String causeDeces3) {
        this.causeDeces3 = causeDeces3;
    }

    public FaiteNonFaite getEnqueteFamiliale() {
        return this.enqueteFamiliale;
    }

    public FicheFacteurVIII enqueteFamiliale(FaiteNonFaite enqueteFamiliale) {
        this.setEnqueteFamiliale(enqueteFamiliale);
        return this;
    }

    public void setEnqueteFamiliale(FaiteNonFaite enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public Boolean getDecouverteFortuite() {
        return this.decouverteFortuite;
    }

    public FicheFacteurVIII decouverteFortuite(Boolean decouverteFortuite) {
        this.setDecouverteFortuite(decouverteFortuite);
        return this;
    }

    public void setDecouverteFortuite(Boolean decouverteFortuite) {
        this.decouverteFortuite = decouverteFortuite;
    }

    public Boolean getEnqueteFamilialeBool() {
        return this.enqueteFamilialeBool;
    }

    public FicheFacteurVIII enqueteFamilialeBool(Boolean enqueteFamilialeBool) {
        this.setEnqueteFamilialeBool(enqueteFamilialeBool);
        return this;
    }

    public void setEnqueteFamilialeBool(Boolean enqueteFamilialeBool) {
        this.enqueteFamilialeBool = enqueteFamilialeBool;
    }

    public Boolean getExplorationSyndromeHemorragique() {
        return this.explorationSyndromeHemorragique;
    }

    public FicheFacteurVIII explorationSyndromeHemorragique(Boolean explorationSyndromeHemorragique) {
        this.setExplorationSyndromeHemorragique(explorationSyndromeHemorragique);
        return this;
    }

    public void setExplorationSyndromeHemorragique(Boolean explorationSyndromeHemorragique) {
        this.explorationSyndromeHemorragique = explorationSyndromeHemorragique;
    }

    public ExplorationType getTypeExplorationSyndromeHemorragique() {
        return this.typeExplorationSyndromeHemorragique;
    }

    public FicheFacteurVIII typeExplorationSyndromeHemorragique(ExplorationType typeExplorationSyndromeHemorragique) {
        this.setTypeExplorationSyndromeHemorragique(typeExplorationSyndromeHemorragique);
        return this;
    }

    public void setTypeExplorationSyndromeHemorragique(ExplorationType typeExplorationSyndromeHemorragique) {
        this.typeExplorationSyndromeHemorragique = typeExplorationSyndromeHemorragique;
    }

    public LocalDate getDatePremierSigneClinique() {
        return this.datePremierSigneClinique;
    }

    public FicheFacteurVIII datePremierSigneClinique(LocalDate datePremierSigneClinique) {
        this.setDatePremierSigneClinique(datePremierSigneClinique);
        return this;
    }

    public void setDatePremierSigneClinique(LocalDate datePremierSigneClinique) {
        this.datePremierSigneClinique = datePremierSigneClinique;
    }

    public Integer getAgeDiagnosticJour() {
        return this.ageDiagnosticJour;
    }

    public FicheFacteurVIII ageDiagnosticJour(Integer ageDiagnosticJour) {
        this.setAgeDiagnosticJour(ageDiagnosticJour);
        return this;
    }

    public void setAgeDiagnosticJour(Integer ageDiagnosticJour) {
        this.ageDiagnosticJour = ageDiagnosticJour;
    }

    public Integer getAgeDiagnosticMois() {
        return this.ageDiagnosticMois;
    }

    public FicheFacteurVIII ageDiagnosticMois(Integer ageDiagnosticMois) {
        this.setAgeDiagnosticMois(ageDiagnosticMois);
        return this;
    }

    public void setAgeDiagnosticMois(Integer ageDiagnosticMois) {
        this.ageDiagnosticMois = ageDiagnosticMois;
    }

    public Integer getAgeDiagnosticAnnee() {
        return this.ageDiagnosticAnnee;
    }

    public FicheFacteurVIII ageDiagnosticAnnee(Integer ageDiagnosticAnnee) {
        this.setAgeDiagnosticAnnee(ageDiagnosticAnnee);
        return this;
    }

    public void setAgeDiagnosticAnnee(Integer ageDiagnosticAnnee) {
        this.ageDiagnosticAnnee = ageDiagnosticAnnee;
    }

    public Float getTp() {
        return this.tp;
    }

    public FicheFacteurVIII tp(Float tp) {
        this.setTp(tp);
        return this;
    }

    public void setTp(Float tp) {
        this.tp = tp;
    }

    public Float getTpMT() {
        return this.tpMT;
    }

    public FicheFacteurVIII tpMT(Float tpMT) {
        this.setTpMT(tpMT);
        return this;
    }

    public void setTpMT(Float tpMT) {
        this.tpMT = tpMT;
    }

    public Float getTca() {
        return this.tca;
    }

    public FicheFacteurVIII tca(Float tca) {
        this.setTca(tca);
        return this;
    }

    public void setTca(Float tca) {
        this.tca = tca;
    }

    public Float getTcaMT() {
        return this.tcaMT;
    }

    public FicheFacteurVIII tcaMT(Float tcaMT) {
        this.setTcaMT(tcaMT);
        return this;
    }

    public void setTcaMT(Float tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Float getFibrinogene() {
        return this.fibrinogene;
    }

    public FicheFacteurVIII fibrinogene(Float fibrinogene) {
        this.setFibrinogene(fibrinogene);
        return this;
    }

    public void setFibrinogene(Float fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public FaiteNonFaite getTs() {
        return this.ts;
    }

    public FicheFacteurVIII ts(FaiteNonFaite ts) {
        this.setTs(ts);
        return this;
    }

    public void setTs(FaiteNonFaite ts) {
        this.ts = ts;
    }

    public NormalAllonge getTsFait() {
        return this.tsFait;
    }

    public FicheFacteurVIII tsFait(NormalAllonge tsFait) {
        this.setTsFait(tsFait);
        return this;
    }

    public void setTsFait(NormalAllonge tsFait) {
        this.tsFait = tsFait;
    }

    public NormalAllonge getToPfa() {
        return this.toPfa;
    }

    public FicheFacteurVIII toPfa(NormalAllonge toPfa) {
        this.setToPfa(toPfa);
        return this;
    }

    public void setToPfa(NormalAllonge toPfa) {
        this.toPfa = toPfa;
    }

    public Float getToPfa200ColEpi() {
        return this.toPfa200ColEpi;
    }

    public FicheFacteurVIII toPfa200ColEpi(Float toPfa200ColEpi) {
        this.setToPfa200ColEpi(toPfa200ColEpi);
        return this;
    }

    public void setToPfa200ColEpi(Float toPfa200ColEpi) {
        this.toPfa200ColEpi = toPfa200ColEpi;
    }

    public Float getToPfa200ColAdp() {
        return this.toPfa200ColAdp;
    }

    public FicheFacteurVIII toPfa200ColAdp(Float toPfa200ColAdp) {
        this.setToPfa200ColAdp(toPfa200ColAdp);
        return this;
    }

    public void setToPfa200ColAdp(Float toPfa200ColAdp) {
        this.toPfa200ColAdp = toPfa200ColAdp;
    }

    public Float getTauxDosageFacteurXIII() {
        return this.tauxDosageFacteurXIII;
    }

    public FicheFacteurVIII tauxDosageFacteurXIII(Float tauxDosageFacteurXIII) {
        this.setTauxDosageFacteurXIII(tauxDosageFacteurXIII);
        return this;
    }

    public void setTauxDosageFacteurXIII(Float tauxDosageFacteurXIII) {
        this.tauxDosageFacteurXIII = tauxDosageFacteurXIII;
    }

    public Float getHb() {
        return this.hb;
    }

    public FicheFacteurVIII hb(Float hb) {
        this.setHb(hb);
        return this;
    }

    public void setHb(Float hb) {
        this.hb = hb;
    }

    public Float getHt() {
        return this.ht;
    }

    public FicheFacteurVIII ht(Float ht) {
        this.setHt(ht);
        return this;
    }

    public void setHt(Float ht) {
        this.ht = ht;
    }

    public Integer getPlaquettes() {
        return this.plaquettes;
    }

    public FicheFacteurVIII plaquettes(Integer plaquettes) {
        this.setPlaquettes(plaquettes);
        return this;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Boolean getTechniqueChromogenique() {
        return this.techniqueChromogenique;
    }

    public FicheFacteurVIII techniqueChromogenique(Boolean techniqueChromogenique) {
        this.setTechniqueChromogenique(techniqueChromogenique);
        return this;
    }

    public void setTechniqueChromogenique(Boolean techniqueChromogenique) {
        this.techniqueChromogenique = techniqueChromogenique;
    }

    public Float getTauxTechniqueChromogenique() {
        return this.tauxTechniqueChromogenique;
    }

    public FicheFacteurVIII tauxTechniqueChromogenique(Float tauxTechniqueChromogenique) {
        this.setTauxTechniqueChromogenique(tauxTechniqueChromogenique);
        return this;
    }

    public void setTauxTechniqueChromogenique(Float tauxTechniqueChromogenique) {
        this.tauxTechniqueChromogenique = tauxTechniqueChromogenique;
    }

    public Boolean getTechniqueSolubiliteCaillot() {
        return this.techniqueSolubiliteCaillot;
    }

    public FicheFacteurVIII techniqueSolubiliteCaillot(Boolean techniqueSolubiliteCaillot) {
        this.setTechniqueSolubiliteCaillot(techniqueSolubiliteCaillot);
        return this;
    }

    public void setTechniqueSolubiliteCaillot(Boolean techniqueSolubiliteCaillot) {
        this.techniqueSolubiliteCaillot = techniqueSolubiliteCaillot;
    }

    public Float getTauxTechniqueSolubiliteCaillot() {
        return this.tauxTechniqueSolubiliteCaillot;
    }

    public FicheFacteurVIII tauxTechniqueSolubiliteCaillot(Float tauxTechniqueSolubiliteCaillot) {
        this.setTauxTechniqueSolubiliteCaillot(tauxTechniqueSolubiliteCaillot);
        return this;
    }

    public void setTauxTechniqueSolubiliteCaillot(Float tauxTechniqueSolubiliteCaillot) {
        this.tauxTechniqueSolubiliteCaillot = tauxTechniqueSolubiliteCaillot;
    }

    public FaiteNonFaite getConfirmationMoleculaire() {
        return this.confirmationMoleculaire;
    }

    public FicheFacteurVIII confirmationMoleculaire(FaiteNonFaite confirmationMoleculaire) {
        this.setConfirmationMoleculaire(confirmationMoleculaire);
        return this;
    }

    public void setConfirmationMoleculaire(FaiteNonFaite confirmationMoleculaire) {
        this.confirmationMoleculaire = confirmationMoleculaire;
    }

    public Boolean getMutationPresence() {
        return this.mutationPresence;
    }

    public FicheFacteurVIII mutationPresence(Boolean mutationPresence) {
        this.setMutationPresence(mutationPresence);
        return this;
    }

    public void setMutationPresence(Boolean mutationPresence) {
        this.mutationPresence = mutationPresence;
    }

    public MutationType getMutationType() {
        return this.mutationType;
    }

    public FicheFacteurVIII mutationType(MutationType mutationType) {
        this.setMutationType(mutationType);
        return this;
    }

    public void setMutationType(MutationType mutationType) {
        this.mutationType = mutationType;
    }

    public Boolean getEpistaxis() {
        return this.epistaxis;
    }

    public FicheFacteurVIII epistaxis(Boolean epistaxis) {
        this.setEpistaxis(epistaxis);
        return this;
    }

    public void setEpistaxis(Boolean epistaxis) {
        this.epistaxis = epistaxis;
    }

    public Integer getFrequenceEpistaxis() {
        return this.frequenceEpistaxis;
    }

    public FicheFacteurVIII frequenceEpistaxis(Integer frequenceEpistaxis) {
        this.setFrequenceEpistaxis(frequenceEpistaxis);
        return this;
    }

    public void setFrequenceEpistaxis(Integer frequenceEpistaxis) {
        this.frequenceEpistaxis = frequenceEpistaxis;
    }

    public Boolean getGingivorragies() {
        return this.gingivorragies;
    }

    public FicheFacteurVIII gingivorragies(Boolean gingivorragies) {
        this.setGingivorragies(gingivorragies);
        return this;
    }

    public void setGingivorragies(Boolean gingivorragies) {
        this.gingivorragies = gingivorragies;
    }

    public TypeManifestation getTypeGingivorragies() {
        return this.typeGingivorragies;
    }

    public FicheFacteurVIII typeGingivorragies(TypeManifestation typeGingivorragies) {
        this.setTypeGingivorragies(typeGingivorragies);
        return this;
    }

    public void setTypeGingivorragies(TypeManifestation typeGingivorragies) {
        this.typeGingivorragies = typeGingivorragies;
    }

    public Integer getFrequenceGingivorragies() {
        return this.frequenceGingivorragies;
    }

    public FicheFacteurVIII frequenceGingivorragies(Integer frequenceGingivorragies) {
        this.setFrequenceGingivorragies(frequenceGingivorragies);
        return this;
    }

    public void setFrequenceGingivorragies(Integer frequenceGingivorragies) {
        this.frequenceGingivorragies = frequenceGingivorragies;
    }

    public Boolean getEcchymose() {
        return this.ecchymose;
    }

    public FicheFacteurVIII ecchymose(Boolean ecchymose) {
        this.setEcchymose(ecchymose);
        return this;
    }

    public void setEcchymose(Boolean ecchymose) {
        this.ecchymose = ecchymose;
    }

    public TypeManifestation getTypeEcchymose() {
        return this.typeEcchymose;
    }

    public FicheFacteurVIII typeEcchymose(TypeManifestation typeEcchymose) {
        this.setTypeEcchymose(typeEcchymose);
        return this;
    }

    public void setTypeEcchymose(TypeManifestation typeEcchymose) {
        this.typeEcchymose = typeEcchymose;
    }

    public Integer getFrequenceEcchymose() {
        return this.frequenceEcchymose;
    }

    public FicheFacteurVIII frequenceEcchymose(Integer frequenceEcchymose) {
        this.setFrequenceEcchymose(frequenceEcchymose);
        return this;
    }

    public void setFrequenceEcchymose(Integer frequenceEcchymose) {
        this.frequenceEcchymose = frequenceEcchymose;
    }

    public Boolean getMenorragie() {
        return this.menorragie;
    }

    public FicheFacteurVIII menorragie(Boolean menorragie) {
        this.setMenorragie(menorragie);
        return this;
    }

    public void setMenorragie(Boolean menorragie) {
        this.menorragie = menorragie;
    }

    public Integer getFrequenceMenorragie() {
        return this.frequenceMenorragie;
    }

    public FicheFacteurVIII frequenceMenorragie(Integer frequenceMenorragie) {
        this.setFrequenceMenorragie(frequenceMenorragie);
        return this;
    }

    public void setFrequenceMenorragie(Integer frequenceMenorragie) {
        this.frequenceMenorragie = frequenceMenorragie;
    }

    public Boolean getHematemese() {
        return this.hematemese;
    }

    public FicheFacteurVIII hematemese(Boolean hematemese) {
        this.setHematemese(hematemese);
        return this;
    }

    public void setHematemese(Boolean hematemese) {
        this.hematemese = hematemese;
    }

    public Integer getFrequenceHematemese() {
        return this.frequenceHematemese;
    }

    public FicheFacteurVIII frequenceHematemese(Integer frequenceHematemese) {
        this.setFrequenceHematemese(frequenceHematemese);
        return this;
    }

    public void setFrequenceHematemese(Integer frequenceHematemese) {
        this.frequenceHematemese = frequenceHematemese;
    }

    public Boolean getRectorragie() {
        return this.rectorragie;
    }

    public FicheFacteurVIII rectorragie(Boolean rectorragie) {
        this.setRectorragie(rectorragie);
        return this;
    }

    public void setRectorragie(Boolean rectorragie) {
        this.rectorragie = rectorragie;
    }

    public Integer getFrequenceRectorragie() {
        return this.frequenceRectorragie;
    }

    public FicheFacteurVIII frequenceRectorragie(Integer frequenceRectorragie) {
        this.setFrequenceRectorragie(frequenceRectorragie);
        return this;
    }

    public void setFrequenceRectorragie(Integer frequenceRectorragie) {
        this.frequenceRectorragie = frequenceRectorragie;
    }

    public Boolean getHematurie() {
        return this.hematurie;
    }

    public FicheFacteurVIII hematurie(Boolean hematurie) {
        this.setHematurie(hematurie);
        return this;
    }

    public void setHematurie(Boolean hematurie) {
        this.hematurie = hematurie;
    }

    public Integer getFrequenceHematurie() {
        return this.frequenceHematurie;
    }

    public FicheFacteurVIII frequenceHematurie(Integer frequenceHematurie) {
        this.setFrequenceHematurie(frequenceHematurie);
        return this;
    }

    public void setFrequenceHematurie(Integer frequenceHematurie) {
        this.frequenceHematurie = frequenceHematurie;
    }

    public Boolean getHematomeSousCutane() {
        return this.hematomeSousCutane;
    }

    public FicheFacteurVIII hematomeSousCutane(Boolean hematomeSousCutane) {
        this.setHematomeSousCutane(hematomeSousCutane);
        return this;
    }

    public void setHematomeSousCutane(Boolean hematomeSousCutane) {
        this.hematomeSousCutane = hematomeSousCutane;
    }

    public TypeManifestation getTypeHematomeSousCutane() {
        return this.typeHematomeSousCutane;
    }

    public FicheFacteurVIII typeHematomeSousCutane(TypeManifestation typeHematomeSousCutane) {
        this.setTypeHematomeSousCutane(typeHematomeSousCutane);
        return this;
    }

    public void setTypeHematomeSousCutane(TypeManifestation typeHematomeSousCutane) {
        this.typeHematomeSousCutane = typeHematomeSousCutane;
    }

    public Integer getFrequenceHematomeSousCutane() {
        return this.frequenceHematomeSousCutane;
    }

    public FicheFacteurVIII frequenceHematomeSousCutane(Integer frequenceHematomeSousCutane) {
        this.setFrequenceHematomeSousCutane(frequenceHematomeSousCutane);
        return this;
    }

    public void setFrequenceHematomeSousCutane(Integer frequenceHematomeSousCutane) {
        this.frequenceHematomeSousCutane = frequenceHematomeSousCutane;
    }

    public Boolean getHematomeMusculaire() {
        return this.hematomeMusculaire;
    }

    public FicheFacteurVIII hematomeMusculaire(Boolean hematomeMusculaire) {
        this.setHematomeMusculaire(hematomeMusculaire);
        return this;
    }

    public void setHematomeMusculaire(Boolean hematomeMusculaire) {
        this.hematomeMusculaire = hematomeMusculaire;
    }

    public String getSiegeHematomeMusculaire() {
        return this.siegeHematomeMusculaire;
    }

    public FicheFacteurVIII siegeHematomeMusculaire(String siegeHematomeMusculaire) {
        this.setSiegeHematomeMusculaire(siegeHematomeMusculaire);
        return this;
    }

    public void setSiegeHematomeMusculaire(String siegeHematomeMusculaire) {
        this.siegeHematomeMusculaire = siegeHematomeMusculaire;
    }

    public TypeManifestation getTypeHematomeMusculaire() {
        return this.typeHematomeMusculaire;
    }

    public FicheFacteurVIII typeHematomeMusculaire(TypeManifestation typeHematomeMusculaire) {
        this.setTypeHematomeMusculaire(typeHematomeMusculaire);
        return this;
    }

    public void setTypeHematomeMusculaire(TypeManifestation typeHematomeMusculaire) {
        this.typeHematomeMusculaire = typeHematomeMusculaire;
    }

    public Integer getFrequenceHematomeMusculaire() {
        return this.frequenceHematomeMusculaire;
    }

    public FicheFacteurVIII frequenceHematomeMusculaire(Integer frequenceHematomeMusculaire) {
        this.setFrequenceHematomeMusculaire(frequenceHematomeMusculaire);
        return this;
    }

    public void setFrequenceHematomeMusculaire(Integer frequenceHematomeMusculaire) {
        this.frequenceHematomeMusculaire = frequenceHematomeMusculaire;
    }

    public Boolean getHemarthrose() {
        return this.hemarthrose;
    }

    public FicheFacteurVIII hemarthrose(Boolean hemarthrose) {
        this.setHemarthrose(hemarthrose);
        return this;
    }

    public void setHemarthrose(Boolean hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public TypeManifestation getTypeHemarthrose() {
        return this.typeHemarthrose;
    }

    public FicheFacteurVIII typeHemarthrose(TypeManifestation typeHemarthrose) {
        this.setTypeHemarthrose(typeHemarthrose);
        return this;
    }

    public void setTypeHemarthrose(TypeManifestation typeHemarthrose) {
        this.typeHemarthrose = typeHemarthrose;
    }

    public Integer getFrequenceHemarthrose() {
        return this.frequenceHemarthrose;
    }

    public FicheFacteurVIII frequenceHemarthrose(Integer frequenceHemarthrose) {
        this.setFrequenceHemarthrose(frequenceHemarthrose);
        return this;
    }

    public void setFrequenceHemarthrose(Integer frequenceHemarthrose) {
        this.frequenceHemarthrose = frequenceHemarthrose;
    }

    public Boolean getSaignementSNC() {
        return this.saignementSNC;
    }

    public FicheFacteurVIII saignementSNC(Boolean saignementSNC) {
        this.setSaignementSNC(saignementSNC);
        return this;
    }

    public void setSaignementSNC(Boolean saignementSNC) {
        this.saignementSNC = saignementSNC;
    }

    public TypeManifestation getTypeSaignementSNC() {
        return this.typeSaignementSNC;
    }

    public FicheFacteurVIII typeSaignementSNC(TypeManifestation typeSaignementSNC) {
        this.setTypeSaignementSNC(typeSaignementSNC);
        return this;
    }

    public void setTypeSaignementSNC(TypeManifestation typeSaignementSNC) {
        this.typeSaignementSNC = typeSaignementSNC;
    }

    public Integer getFrequenceSaignementSNC() {
        return this.frequenceSaignementSNC;
    }

    public FicheFacteurVIII frequenceSaignementSNC(Integer frequenceSaignementSNC) {
        this.setFrequenceSaignementSNC(frequenceSaignementSNC);
        return this;
    }

    public void setFrequenceSaignementSNC(Integer frequenceSaignementSNC) {
        this.frequenceSaignementSNC = frequenceSaignementSNC;
    }

    public Boolean getRuptureKystesHemorragiques() {
        return this.ruptureKystesHemorragiques;
    }

    public FicheFacteurVIII ruptureKystesHemorragiques(Boolean ruptureKystesHemorragiques) {
        this.setRuptureKystesHemorragiques(ruptureKystesHemorragiques);
        return this;
    }

    public void setRuptureKystesHemorragiques(Boolean ruptureKystesHemorragiques) {
        this.ruptureKystesHemorragiques = ruptureKystesHemorragiques;
    }

    public Boolean getHemorragiesPostChirurgicales() {
        return this.hemorragiesPostChirurgicales;
    }

    public FicheFacteurVIII hemorragiesPostChirurgicales(Boolean hemorragiesPostChirurgicales) {
        this.setHemorragiesPostChirurgicales(hemorragiesPostChirurgicales);
        return this;
    }

    public void setHemorragiesPostChirurgicales(Boolean hemorragiesPostChirurgicales) {
        this.hemorragiesPostChirurgicales = hemorragiesPostChirurgicales;
    }

    public Boolean getHemoperitone() {
        return this.hemoperitone;
    }

    public FicheFacteurVIII hemoperitone(Boolean hemoperitone) {
        this.setHemoperitone(hemoperitone);
        return this;
    }

    public void setHemoperitone(Boolean hemoperitone) {
        this.hemoperitone = hemoperitone;
    }

    public Boolean getAccidentsThrombotiques() {
        return this.accidentsThrombotiques;
    }

    public FicheFacteurVIII accidentsThrombotiques(Boolean accidentsThrombotiques) {
        this.setAccidentsThrombotiques(accidentsThrombotiques);
        return this;
    }

    public void setAccidentsThrombotiques(Boolean accidentsThrombotiques) {
        this.accidentsThrombotiques = accidentsThrombotiques;
    }

    public TypeAccidentsThrombotiques getTypeAccidentsThrombotiques() {
        return this.typeAccidentsThrombotiques;
    }

    public FicheFacteurVIII typeAccidentsThrombotiques(TypeAccidentsThrombotiques typeAccidentsThrombotiques) {
        this.setTypeAccidentsThrombotiques(typeAccidentsThrombotiques);
        return this;
    }

    public void setTypeAccidentsThrombotiques(TypeAccidentsThrombotiques typeAccidentsThrombotiques) {
        this.typeAccidentsThrombotiques = typeAccidentsThrombotiques;
    }

    public Boolean getHemorragieGrave() {
        return this.hemorragieGrave;
    }

    public FicheFacteurVIII hemorragieGrave(Boolean hemorragieGrave) {
        this.setHemorragieGrave(hemorragieGrave);
        return this;
    }

    public void setHemorragieGrave(Boolean hemorragieGrave) {
        this.hemorragieGrave = hemorragieGrave;
    }

    public TypeHemorragieGrave getTypeHemorragieGrave() {
        return this.typeHemorragieGrave;
    }

    public FicheFacteurVIII typeHemorragieGrave(TypeHemorragieGrave typeHemorragieGrave) {
        this.setTypeHemorragieGrave(typeHemorragieGrave);
        return this;
    }

    public void setTypeHemorragieGrave(TypeHemorragieGrave typeHemorragieGrave) {
        this.typeHemorragieGrave = typeHemorragieGrave;
    }

    public OuiNonNP getChirurgiePrepare() {
        return this.chirurgiePrepare;
    }

    public FicheFacteurVIII chirurgiePrepare(OuiNonNP chirurgiePrepare) {
        this.setChirurgiePrepare(chirurgiePrepare);
        return this;
    }

    public void setChirurgiePrepare(OuiNonNP chirurgiePrepare) {
        this.chirurgiePrepare = chirurgiePrepare;
    }

    public OuiNonNP getSuiteOperatoireHemorragique() {
        return this.suiteOperatoireHemorragique;
    }

    public FicheFacteurVIII suiteOperatoireHemorragique(OuiNonNP suiteOperatoireHemorragique) {
        this.setSuiteOperatoireHemorragique(suiteOperatoireHemorragique);
        return this;
    }

    public void setSuiteOperatoireHemorragique(OuiNonNP suiteOperatoireHemorragique) {
        this.suiteOperatoireHemorragique = suiteOperatoireHemorragique;
    }

    public OuiNonNP getCirconcisionPrepare() {
        return this.circoncisionPrepare;
    }

    public FicheFacteurVIII circoncisionPrepare(OuiNonNP circoncisionPrepare) {
        this.setCirconcisionPrepare(circoncisionPrepare);
        return this;
    }

    public void setCirconcisionPrepare(OuiNonNP circoncisionPrepare) {
        this.circoncisionPrepare = circoncisionPrepare;
    }

    public OuiNonNP getSuiteOperatoireCirconcision() {
        return this.suiteOperatoireCirconcision;
    }

    public FicheFacteurVIII suiteOperatoireCirconcision(OuiNonNP suiteOperatoireCirconcision) {
        this.setSuiteOperatoireCirconcision(suiteOperatoireCirconcision);
        return this;
    }

    public void setSuiteOperatoireCirconcision(OuiNonNP suiteOperatoireCirconcision) {
        this.suiteOperatoireCirconcision = suiteOperatoireCirconcision;
    }

    public Integer getAgeCirconcision() {
        return this.ageCirconcision;
    }

    public FicheFacteurVIII ageCirconcision(Integer ageCirconcision) {
        this.setAgeCirconcision(ageCirconcision);
        return this;
    }

    public void setAgeCirconcision(Integer ageCirconcision) {
        this.ageCirconcision = ageCirconcision;
    }

    public OuiNonNP getFacteurVIIIRecombinant() {
        return this.facteurVIIIRecombinant;
    }

    public FicheFacteurVIII facteurVIIIRecombinant(OuiNonNP facteurVIIIRecombinant) {
        this.setFacteurVIIIRecombinant(facteurVIIIRecombinant);
        return this;
    }

    public void setFacteurVIIIRecombinant(OuiNonNP facteurVIIIRecombinant) {
        this.facteurVIIIRecombinant = facteurVIIIRecombinant;
    }

    public OuiNonNP getPlasmaFraisCongele() {
        return this.plasmaFraisCongele;
    }

    public FicheFacteurVIII plasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.setPlasmaFraisCongele(plasmaFraisCongele);
        return this;
    }

    public void setPlasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.plasmaFraisCongele = plasmaFraisCongele;
    }

    public OuiNonNP getCryoprecipite() {
        return this.cryoprecipite;
    }

    public FicheFacteurVIII cryoprecipite(OuiNonNP cryoprecipite) {
        this.setCryoprecipite(cryoprecipite);
        return this;
    }

    public void setCryoprecipite(OuiNonNP cryoprecipite) {
        this.cryoprecipite = cryoprecipite;
    }

    public OuiNonNP getCulotsPlaquettaires() {
        return this.culotsPlaquettaires;
    }

    public FicheFacteurVIII culotsPlaquettaires(OuiNonNP culotsPlaquettaires) {
        this.setCulotsPlaquettaires(culotsPlaquettaires);
        return this;
    }

    public void setCulotsPlaquettaires(OuiNonNP culotsPlaquettaires) {
        this.culotsPlaquettaires = culotsPlaquettaires;
    }

    public OuiNonNP getDicynone() {
        return this.dicynone;
    }

    public FicheFacteurVIII dicynone(OuiNonNP dicynone) {
        this.setDicynone(dicynone);
        return this;
    }

    public void setDicynone(OuiNonNP dicynone) {
        this.dicynone = dicynone;
    }

    public OuiNonNP getExacyl() {
        return this.exacyl;
    }

    public FicheFacteurVIII exacyl(OuiNonNP exacyl) {
        this.setExacyl(exacyl);
        return this;
    }

    public void setExacyl(OuiNonNP exacyl) {
        this.exacyl = exacyl;
    }

    public OuiNonNP getTraitementMartial() {
        return this.traitementMartial;
    }

    public FicheFacteurVIII traitementMartial(OuiNonNP traitementMartial) {
        this.setTraitementMartial(traitementMartial);
        return this;
    }

    public void setTraitementMartial(OuiNonNP traitementMartial) {
        this.traitementMartial = traitementMartial;
    }

    public OuiNonNP getTraitementOestroprogestatif() {
        return this.traitementOestroprogestatif;
    }

    public FicheFacteurVIII traitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.setTraitementOestroprogestatif(traitementOestroprogestatif);
        return this;
    }

    public void setTraitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.traitementOestroprogestatif = traitementOestroprogestatif;
    }

    public OuiNonNP getTransfusionCGR() {
        return this.transfusionCGR;
    }

    public FicheFacteurVIII transfusionCGR(OuiNonNP transfusionCGR) {
        this.setTransfusionCGR(transfusionCGR);
        return this;
    }

    public void setTransfusionCGR(OuiNonNP transfusionCGR) {
        this.transfusionCGR = transfusionCGR;
    }

    public OuiNonNP getInhibiteurs() {
        return this.inhibiteurs;
    }

    public FicheFacteurVIII inhibiteurs(OuiNonNP inhibiteurs) {
        this.setInhibiteurs(inhibiteurs);
        return this;
    }

    public void setInhibiteurs(OuiNonNP inhibiteurs) {
        this.inhibiteurs = inhibiteurs;
    }

    public Boolean getInhibiteurNeutralisant() {
        return this.inhibiteurNeutralisant;
    }

    public FicheFacteurVIII inhibiteurNeutralisant(Boolean inhibiteurNeutralisant) {
        this.setInhibiteurNeutralisant(inhibiteurNeutralisant);
        return this;
    }

    public void setInhibiteurNeutralisant(Boolean inhibiteurNeutralisant) {
        this.inhibiteurNeutralisant = inhibiteurNeutralisant;
    }

    public Float getTitreUB() {
        return this.titreUB;
    }

    public FicheFacteurVIII titreUB(Float titreUB) {
        this.setTitreUB(titreUB);
        return this;
    }

    public void setTitreUB(Float titreUB) {
        this.titreUB = titreUB;
    }

    public Boolean getInhibiteurNonNeutralisant() {
        return this.inhibiteurNonNeutralisant;
    }

    public FicheFacteurVIII inhibiteurNonNeutralisant(Boolean inhibiteurNonNeutralisant) {
        this.setInhibiteurNonNeutralisant(inhibiteurNonNeutralisant);
        return this;
    }

    public void setInhibiteurNonNeutralisant(Boolean inhibiteurNonNeutralisant) {
        this.inhibiteurNonNeutralisant = inhibiteurNonNeutralisant;
    }

    public OuiNonNP getInfectionsVirales() {
        return this.infectionsVirales;
    }

    public FicheFacteurVIII infectionsVirales(OuiNonNP infectionsVirales) {
        this.setInfectionsVirales(infectionsVirales);
        return this;
    }

    public void setInfectionsVirales(OuiNonNP infectionsVirales) {
        this.infectionsVirales = infectionsVirales;
    }

    public OuiNonNP getThrombose() {
        return this.thrombose;
    }

    public FicheFacteurVIII thrombose(OuiNonNP thrombose) {
        this.setThrombose(thrombose);
        return this;
    }

    public void setThrombose(OuiNonNP thrombose) {
        this.thrombose = thrombose;
    }

    public OuiNonNP getReactionAllergique() {
        return this.reactionAllergique;
    }

    public FicheFacteurVIII reactionAllergique(OuiNonNP reactionAllergique) {
        this.setReactionAllergique(reactionAllergique);
        return this;
    }

    public void setReactionAllergique(OuiNonNP reactionAllergique) {
        this.reactionAllergique = reactionAllergique;
    }

    public OuiNonNP getAlloImmunisation() {
        return this.alloImmunisation;
    }

    public FicheFacteurVIII alloImmunisation(OuiNonNP alloImmunisation) {
        this.setAlloImmunisation(alloImmunisation);
        return this;
    }

    public void setAlloImmunisation(OuiNonNP alloImmunisation) {
        this.alloImmunisation = alloImmunisation;
    }

    public OuiNonNP getAdhesionTherapeutique() {
        return this.adhesionTherapeutique;
    }

    public FicheFacteurVIII adhesionTherapeutique(OuiNonNP adhesionTherapeutique) {
        this.setAdhesionTherapeutique(adhesionTherapeutique);
        return this;
    }

    public void setAdhesionTherapeutique(OuiNonNP adhesionTherapeutique) {
        this.adhesionTherapeutique = adhesionTherapeutique;
    }

    public String getCauseNonAdhesion() {
        return this.causeNonAdhesion;
    }

    public FicheFacteurVIII causeNonAdhesion(String causeNonAdhesion) {
        this.setCauseNonAdhesion(causeNonAdhesion);
        return this;
    }

    public void setCauseNonAdhesion(String causeNonAdhesion) {
        this.causeNonAdhesion = causeNonAdhesion;
    }

    public VieSocialeType getVieSociale() {
        return this.vieSociale;
    }

    public FicheFacteurVIII vieSociale(VieSocialeType vieSociale) {
        this.setVieSociale(vieSociale);
        return this;
    }

    public void setVieSociale(VieSocialeType vieSociale) {
        this.vieSociale = vieSociale;
    }

    public OuiNonNP getMariage() {
        return this.mariage;
    }

    public FicheFacteurVIII mariage(OuiNonNP mariage) {
        this.setMariage(mariage);
        return this;
    }

    public void setMariage(OuiNonNP mariage) {
        this.mariage = mariage;
    }

    public OuiNonNP getEnfants() {
        return this.enfants;
    }

    public FicheFacteurVIII enfants(OuiNonNP enfants) {
        this.setEnfants(enfants);
        return this;
    }

    public void setEnfants(OuiNonNP enfants) {
        this.enfants = enfants;
    }

    public OuiNonNP getActivitePhysique() {
        return this.activitePhysique;
    }

    public FicheFacteurVIII activitePhysique(OuiNonNP activitePhysique) {
        this.setActivitePhysique(activitePhysique);
        return this;
    }

    public void setActivitePhysique(OuiNonNP activitePhysique) {
        this.activitePhysique = activitePhysique;
    }

    public String getTypeActivitePhysique() {
        return this.typeActivitePhysique;
    }

    public FicheFacteurVIII typeActivitePhysique(String typeActivitePhysique) {
        this.setTypeActivitePhysique(typeActivitePhysique);
        return this;
    }

    public void setTypeActivitePhysique(String typeActivitePhysique) {
        this.typeActivitePhysique = typeActivitePhysique;
    }

    public OuiNonNP getSequelles() {
        return this.sequelles;
    }

    public FicheFacteurVIII sequelles(OuiNonNP sequelles) {
        this.setSequelles(sequelles);
        return this;
    }

    public void setSequelles(OuiNonNP sequelles) {
        this.sequelles = sequelles;
    }

    public String getTypeSequelles() {
        return this.typeSequelles;
    }

    public FicheFacteurVIII typeSequelles(String typeSequelles) {
        this.setTypeSequelles(typeSequelles);
        return this;
    }

    public void setTypeSequelles(String typeSequelles) {
        this.typeSequelles = typeSequelles;
    }

    public OuiNonNP getPatientDecede() {
        return this.patientDecede;
    }

    public FicheFacteurVIII patientDecede(OuiNonNP patientDecede) {
        this.setPatientDecede(patientDecede);
        return this;
    }

    public void setPatientDecede(OuiNonNP patientDecede) {
        this.patientDecede = patientDecede;
    }

    public LocalDate getDateDeces() {
        return this.dateDeces;
    }

    public FicheFacteurVIII dateDeces(LocalDate dateDeces) {
        this.setDateDeces(dateDeces);
        return this;
    }

    public void setDateDeces(LocalDate dateDeces) {
        this.dateDeces = dateDeces;
    }

    public Integer getAgeDeces() {
        return this.ageDeces;
    }

    public FicheFacteurVIII ageDeces(Integer ageDeces) {
        this.setAgeDeces(ageDeces);
        return this;
    }

    public void setAgeDeces(Integer ageDeces) {
        this.ageDeces = ageDeces;
    }

    public Boolean getHemorragieSnc() {
        return this.hemorragieSnc;
    }

    public FicheFacteurVIII hemorragieSnc(Boolean hemorragieSnc) {
        this.setHemorragieSnc(hemorragieSnc);
        return this;
    }

    public void setHemorragieSnc(Boolean hemorragieSnc) {
        this.hemorragieSnc = hemorragieSnc;
    }

    public Boolean getHemorragieAutreQueSnc() {
        return this.hemorragieAutreQueSnc;
    }

    public FicheFacteurVIII hemorragieAutreQueSnc(Boolean hemorragieAutreQueSnc) {
        this.setHemorragieAutreQueSnc(hemorragieAutreQueSnc);
        return this;
    }

    public void setHemorragieAutreQueSnc(Boolean hemorragieAutreQueSnc) {
        this.hemorragieAutreQueSnc = hemorragieAutreQueSnc;
    }

    public Boolean getHiv() {
        return this.hiv;
    }

    public FicheFacteurVIII hiv(Boolean hiv) {
        this.setHiv(hiv);
        return this;
    }

    public void setHiv(Boolean hiv) {
        this.hiv = hiv;
    }

    public Boolean getAutreInfection() {
        return this.autreInfection;
    }

    public FicheFacteurVIII autreInfection(Boolean autreInfection) {
        this.setAutreInfection(autreInfection);
        return this;
    }

    public void setAutreInfection(Boolean autreInfection) {
        this.autreInfection = autreInfection;
    }

    public Boolean getEvenementThromboembolique() {
        return this.evenementThromboembolique;
    }

    public FicheFacteurVIII evenementThromboembolique(Boolean evenementThromboembolique) {
        this.setEvenementThromboembolique(evenementThromboembolique);
        return this;
    }

    public void setEvenementThromboembolique(Boolean evenementThromboembolique) {
        this.evenementThromboembolique = evenementThromboembolique;
    }

    public Boolean getCancer() {
        return this.cancer;
    }

    public FicheFacteurVIII cancer(Boolean cancer) {
        this.setCancer(cancer);
        return this;
    }

    public void setCancer(Boolean cancer) {
        this.cancer = cancer;
    }

    public Boolean getCovid19() {
        return this.covid19;
    }

    public FicheFacteurVIII covid19(Boolean covid19) {
        this.setCovid19(covid19);
        return this;
    }

    public void setCovid19(Boolean covid19) {
        this.covid19 = covid19;
    }

    public Boolean getInconnue() {
        return this.inconnue;
    }

    public FicheFacteurVIII inconnue(Boolean inconnue) {
        this.setInconnue(inconnue);
        return this;
    }

    public void setInconnue(Boolean inconnue) {
        this.inconnue = inconnue;
    }

    public Boolean getAutreEvenement() {
        return this.autreEvenement;
    }

    public FicheFacteurVIII autreEvenement(Boolean autreEvenement) {
        this.setAutreEvenement(autreEvenement);
        return this;
    }

    public void setAutreEvenement(Boolean autreEvenement) {
        this.autreEvenement = autreEvenement;
    }

    public String getAutreEvenementDetails() {
        return this.autreEvenementDetails;
    }

    public FicheFacteurVIII autreEvenementDetails(String autreEvenementDetails) {
        this.setAutreEvenementDetails(autreEvenementDetails);
        return this;
    }

    public void setAutreEvenementDetails(String autreEvenementDetails) {
        this.autreEvenementDetails = autreEvenementDetails;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheFacteurVIII)) {
            return false;
        }
        return getId() != null && getId().equals(((FicheFacteurVIII) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheFacteurVIII{" +
            "id=" + getId() +
            ", dossierNumber='" + getDossierNumber() + "'" +
            ", ordreNumber='" + getOrdreNumber() + "'" +
            ", indexNumber='" + getIndexNumber() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", nomJeuneFille='" + getNomJeuneFille() + "'" +
            ", profession='" + getProfession() + "'" +
            ", etatCivil='" + getEtatCivil() + "'" +
            ", serviceClinique='" + getServiceClinique() + "'" +
            ", autreServiceClinique='" + getAutreServiceClinique() + "'" +
            ", couvertureSociale='" + getCouvertureSociale() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", ageActuel=" + getAgeActuel() +
            ", origine='" + getOrigine() + "'" +
            ", autreOrigine='" + getAutreOrigine() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", telephone='" + getTelephone() + "'" +
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
            ", parentsConsanguins='" + getParentsConsanguins() + "'" +
            ", degreParenteConsanguins='" + getDegreParenteConsanguins() + "'" +
            ", casSimilaires='" + getCasSimilaires() + "'" +
            ", nombreCas=" + getNombreCas() +
            ", cas1Numero=" + getCas1Numero() +
            ", lienParenteCas1='" + getLienParenteCas1() + "'" +
            ", degreParenteCas1='" + getDegreParenteCas1() + "'" +
            ", typeHemorragieCas1='" + getTypeHemorragieCas1() + "'" +
            ", cas2Numero=" + getCas2Numero() +
            ", lienParenteCas2='" + getLienParenteCas2() + "'" +
            ", degreParenteCas2='" + getDegreParenteCas2() + "'" +
            ", typeHemorragieCas2='" + getTypeHemorragieCas2() + "'" +
            ", cas3Numero=" + getCas3Numero() +
            ", lienParenteCas3='" + getLienParenteCas3() + "'" +
            ", degreParenteCas3='" + getDegreParenteCas3() + "'" +
            ", typeHemorragieCas3='" + getTypeHemorragieCas3() + "'" +
            ", decesSyndromeHemorragique='" + getDecesSyndromeHemorragique() + "'" +
            ", nombreDeces=" + getNombreDeces() +
            ", deces1Numero=" + getDeces1Numero() +
            ", lienParenteDeces1='" + getLienParenteDeces1() + "'" +
            ", causeDeces1='" + getCauseDeces1() + "'" +
            ", deces2Numero=" + getDeces2Numero() +
            ", lienParenteDeces2='" + getLienParenteDeces2() + "'" +
            ", causeDeces2='" + getCauseDeces2() + "'" +
            ", deces3Numero=" + getDeces3Numero() +
            ", lienParenteDeces3='" + getLienParenteDeces3() + "'" +
            ", causeDeces3='" + getCauseDeces3() + "'" +
            ", enqueteFamiliale='" + getEnqueteFamiliale() + "'" +
            ", decouverteFortuite='" + getDecouverteFortuite() + "'" +
            ", enqueteFamilialeBool='" + getEnqueteFamilialeBool() + "'" +
            ", explorationSyndromeHemorragique='" + getExplorationSyndromeHemorragique() + "'" +
            ", typeExplorationSyndromeHemorragique='" + getTypeExplorationSyndromeHemorragique() + "'" +
            ", datePremierSigneClinique='" + getDatePremierSigneClinique() + "'" +
            ", ageDiagnosticJour=" + getAgeDiagnosticJour() +
            ", ageDiagnosticMois=" + getAgeDiagnosticMois() +
            ", ageDiagnosticAnnee=" + getAgeDiagnosticAnnee() +
            ", tp=" + getTp() +
            ", tpMT=" + getTpMT() +
            ", tca=" + getTca() +
            ", tcaMT=" + getTcaMT() +
            ", fibrinogene=" + getFibrinogene() +
            ", ts='" + getTs() + "'" +
            ", tsFait='" + getTsFait() + "'" +
            ", toPfa='" + getToPfa() + "'" +
            ", toPfa200ColEpi=" + getToPfa200ColEpi() +
            ", toPfa200ColAdp=" + getToPfa200ColAdp() +
            ", tauxDosageFacteurXIII=" + getTauxDosageFacteurXIII() +
            ", hb=" + getHb() +
            ", ht=" + getHt() +
            ", plaquettes=" + getPlaquettes() +
            ", techniqueChromogenique='" + getTechniqueChromogenique() + "'" +
            ", tauxTechniqueChromogenique=" + getTauxTechniqueChromogenique() +
            ", techniqueSolubiliteCaillot='" + getTechniqueSolubiliteCaillot() + "'" +
            ", tauxTechniqueSolubiliteCaillot=" + getTauxTechniqueSolubiliteCaillot() +
            ", confirmationMoleculaire='" + getConfirmationMoleculaire() + "'" +
            ", mutationPresence='" + getMutationPresence() + "'" +
            ", mutationType='" + getMutationType() + "'" +
            ", epistaxis='" + getEpistaxis() + "'" +
            ", frequenceEpistaxis=" + getFrequenceEpistaxis() +
            ", gingivorragies='" + getGingivorragies() + "'" +
            ", typeGingivorragies='" + getTypeGingivorragies() + "'" +
            ", frequenceGingivorragies=" + getFrequenceGingivorragies() +
            ", ecchymose='" + getEcchymose() + "'" +
            ", typeEcchymose='" + getTypeEcchymose() + "'" +
            ", frequenceEcchymose=" + getFrequenceEcchymose() +
            ", menorragie='" + getMenorragie() + "'" +
            ", frequenceMenorragie=" + getFrequenceMenorragie() +
            ", hematemese='" + getHematemese() + "'" +
            ", frequenceHematemese=" + getFrequenceHematemese() +
            ", rectorragie='" + getRectorragie() + "'" +
            ", frequenceRectorragie=" + getFrequenceRectorragie() +
            ", hematurie='" + getHematurie() + "'" +
            ", frequenceHematurie=" + getFrequenceHematurie() +
            ", hematomeSousCutane='" + getHematomeSousCutane() + "'" +
            ", typeHematomeSousCutane='" + getTypeHematomeSousCutane() + "'" +
            ", frequenceHematomeSousCutane=" + getFrequenceHematomeSousCutane() +
            ", hematomeMusculaire='" + getHematomeMusculaire() + "'" +
            ", siegeHematomeMusculaire='" + getSiegeHematomeMusculaire() + "'" +
            ", typeHematomeMusculaire='" + getTypeHematomeMusculaire() + "'" +
            ", frequenceHematomeMusculaire=" + getFrequenceHematomeMusculaire() +
            ", hemarthrose='" + getHemarthrose() + "'" +
            ", typeHemarthrose='" + getTypeHemarthrose() + "'" +
            ", frequenceHemarthrose=" + getFrequenceHemarthrose() +
            ", saignementSNC='" + getSaignementSNC() + "'" +
            ", typeSaignementSNC='" + getTypeSaignementSNC() + "'" +
            ", frequenceSaignementSNC=" + getFrequenceSaignementSNC() +
            ", ruptureKystesHemorragiques='" + getRuptureKystesHemorragiques() + "'" +
            ", hemorragiesPostChirurgicales='" + getHemorragiesPostChirurgicales() + "'" +
            ", hemoperitone='" + getHemoperitone() + "'" +
            ", accidentsThrombotiques='" + getAccidentsThrombotiques() + "'" +
            ", typeAccidentsThrombotiques='" + getTypeAccidentsThrombotiques() + "'" +
            ", hemorragieGrave='" + getHemorragieGrave() + "'" +
            ", typeHemorragieGrave='" + getTypeHemorragieGrave() + "'" +
            ", chirurgiePrepare='" + getChirurgiePrepare() + "'" +
            ", suiteOperatoireHemorragique='" + getSuiteOperatoireHemorragique() + "'" +
            ", circoncisionPrepare='" + getCirconcisionPrepare() + "'" +
            ", suiteOperatoireCirconcision='" + getSuiteOperatoireCirconcision() + "'" +
            ", ageCirconcision=" + getAgeCirconcision() +
            ", facteurVIIIRecombinant='" + getFacteurVIIIRecombinant() + "'" +
            ", plasmaFraisCongele='" + getPlasmaFraisCongele() + "'" +
            ", cryoprecipite='" + getCryoprecipite() + "'" +
            ", culotsPlaquettaires='" + getCulotsPlaquettaires() + "'" +
            ", dicynone='" + getDicynone() + "'" +
            ", exacyl='" + getExacyl() + "'" +
            ", traitementMartial='" + getTraitementMartial() + "'" +
            ", traitementOestroprogestatif='" + getTraitementOestroprogestatif() + "'" +
            ", transfusionCGR='" + getTransfusionCGR() + "'" +
            ", inhibiteurs='" + getInhibiteurs() + "'" +
            ", inhibiteurNeutralisant='" + getInhibiteurNeutralisant() + "'" +
            ", titreUB=" + getTitreUB() +
            ", inhibiteurNonNeutralisant='" + getInhibiteurNonNeutralisant() + "'" +
            ", infectionsVirales='" + getInfectionsVirales() + "'" +
            ", thrombose='" + getThrombose() + "'" +
            ", reactionAllergique='" + getReactionAllergique() + "'" +
            ", alloImmunisation='" + getAlloImmunisation() + "'" +
            ", adhesionTherapeutique='" + getAdhesionTherapeutique() + "'" +
            ", causeNonAdhesion='" + getCauseNonAdhesion() + "'" +
            ", vieSociale='" + getVieSociale() + "'" +
            ", mariage='" + getMariage() + "'" +
            ", enfants='" + getEnfants() + "'" +
            ", activitePhysique='" + getActivitePhysique() + "'" +
            ", typeActivitePhysique='" + getTypeActivitePhysique() + "'" +
            ", sequelles='" + getSequelles() + "'" +
            ", typeSequelles='" + getTypeSequelles() + "'" +
            ", patientDecede='" + getPatientDecede() + "'" +
            ", dateDeces='" + getDateDeces() + "'" +
            ", ageDeces=" + getAgeDeces() +
            ", hemorragieSnc='" + getHemorragieSnc() + "'" +
            ", hemorragieAutreQueSnc='" + getHemorragieAutreQueSnc() + "'" +
            ", hiv='" + getHiv() + "'" +
            ", autreInfection='" + getAutreInfection() + "'" +
            ", evenementThromboembolique='" + getEvenementThromboembolique() + "'" +
            ", cancer='" + getCancer() + "'" +
            ", covid19='" + getCovid19() + "'" +
            ", inconnue='" + getInconnue() + "'" +
            ", autreEvenement='" + getAutreEvenement() + "'" +
            ", autreEvenementDetails='" + getAutreEvenementDetails() + "'" +
            "}";
    }
}

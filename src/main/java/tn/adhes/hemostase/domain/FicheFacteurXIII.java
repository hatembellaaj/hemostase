package tn.adhes.hemostase.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import tn.adhes.hemostase.domain.enumeration.AccidentsThrombotiquesType;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.MutationType;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.Region;
import tn.adhes.hemostase.domain.enumeration.ServiceCliniqueType;
import tn.adhes.hemostase.domain.enumeration.TypeFaitOuNonFaitEtResultat;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.VieSociale;

/**
 * A FicheFacteurXIII.
 */
@Entity
@Table(name = "fiche_facteur_xiii")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheFacteurXIII implements Serializable {

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

    @Column(name = "enquete_familiale")
    private Boolean enqueteFamiliale;

    @Column(name = "decouverte_fortuite")
    private Boolean decouverteFortuite;

    @Column(name = "exploration_syndrome_hemorragique")
    private Boolean explorationSyndromeHemorragique;

    @Column(name = "hemorragie_chute_cordon")
    private Boolean hemorragieChuteCordon;

    @Column(name = "metroragie_menarche")
    private Boolean metroragieMenarche;

    @Column(name = "hemorragie_cerebrale_nn")
    private Boolean hemorragieCerebraleNN;

    @Column(name = "hemorragie_post_partum")
    private Boolean hemorragiePostPartum;

    @Size(max = 255)
    @Column(name = "autres_circonstances", length = 255)
    private String autresCirconstances;

    @Column(name = "date_premier_signe_clinique_evocateur_jour")
    private Integer datePremierSigneCliniqueEvocateurJour;

    @Column(name = "date_premier_signe_clinique_evocateur_mois")
    private Integer datePremierSigneCliniqueEvocateurMois;

    @Column(name = "date_premier_signe_clinique_evocateur_annee")
    private Integer datePremierSigneCliniqueEvocateurAnnee;

    @Column(name = "age_diagnostic_jour")
    private Integer ageDiagnosticJour;

    @Column(name = "age_diagnostic_mois")
    private Integer ageDiagnosticMois;

    @Column(name = "age_diagnostic_annee")
    private Integer ageDiagnosticAnnee;

    @Column(name = "hb")
    private Double hb;

    @Column(name = "ht")
    private Double ht;

    @Column(name = "plaquettes")
    private Integer plaquettes;

    @Column(name = "tp")
    private Double tp;

    @Column(name = "tp_mt")
    private Double tpMT;

    @Column(name = "tca")
    private Double tca;

    @Column(name = "tca_mt")
    private Double tcaMT;

    @Column(name = "fibrinogene")
    private Double fibrinogene;

    @Enumerated(EnumType.STRING)
    @Column(name = "ts")
    private TypeFaitOuNonFaitEtResultat ts;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_pfa_200")
    private TypeFaitOuNonFaitEtResultat toPfa200;

    @Column(name = "col_epi")
    private Double colEpi;

    @Column(name = "col_adp")
    private Double colAdp;

    @Column(name = "facteur_xiii")
    private Double facteurXIII;

    @Column(name = "technique_chromogenique")
    private Boolean techniqueChromogenique;

    @Column(name = "taux_chromogenique")
    private Double tauxChromogenique;

    @Column(name = "technique_solubilite_caillot")
    private Boolean techniqueSolubiliteCaillot;

    @Column(name = "taux_solubilite_caillot")
    private Double tauxSolubiliteCaillot;

    @Column(name = "confirmation_moleculaire")
    private Boolean confirmationMoleculaire;

    @Column(name = "mutation_presente")
    private Boolean mutationPresente;

    @Size(max = 255)
    @Column(name = "mutation_nom", length = 255)
    private String mutationNom;

    @Enumerated(EnumType.STRING)
    @Column(name = "mutation_type")
    private MutationType mutationType;

    @Size(max = 255)
    @Column(name = "mutation_2_nom", length = 255)
    private String mutation2Nom;

    @Enumerated(EnumType.STRING)
    @Column(name = "saignement_chute_cordon")
    private OuiNonNP saignementChuteCordon;

    @Enumerated(EnumType.STRING)
    @Column(name = "cicatrices_keloides")
    private OuiNonNP cicatricesKeloides;

    @Enumerated(EnumType.STRING)
    @Column(name = "retard_cicatrisation")
    private OuiNonNP retardCicatrisation;

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
    private AccidentsThrombotiquesType typeAccidentsThrombotiques;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_grave")
    private OuiNonNP hemorragieGrave;

    @Column(name = "grave_deglobulisation")
    private Boolean graveDeglobulisation;

    @Column(name = "grave_etat_de_choc")
    private Boolean graveEtatDeChoc;

    @Column(name = "grave_par_localisation")
    private Boolean graveParLocalisation;

    @Size(max = 255)
    @Column(name = "siege_gravite_localisation", length = 255)
    private String siegeGraviteLocalisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "chirurgie_patient_prepare")
    private OuiNonNP chirurgiePatientPrepare;

    @Enumerated(EnumType.STRING)
    @Column(name = "chirurgie_suite_hemorragique")
    private OuiNonNP chirurgieSuiteHemorragique;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision_patient_prepare")
    private OuiNonNP circoncisionPatientPrepare;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision_suite_hemorragique")
    private OuiNonNP circoncisionSuiteHemorragique;

    @Column(name = "age_circoncision")
    private Integer ageCirconcision;

    @Enumerated(EnumType.STRING)
    @Column(name = "concentre_facteur_xiii")
    private OuiNonNP concentreFacteurXIII;

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

    @Column(name = "titre_inhibiteur_neutralisant")
    private Double titreInhibiteurNeutralisant;

    @Column(name = "inhibiteur_non_neutralisant")
    private Boolean inhibiteurNonNeutralisant;

    @Enumerated(EnumType.STRING)
    @Column(name = "infections_virales")
    private OuiNonNP infectionsVirales;

    @Enumerated(EnumType.STRING)
    @Column(name = "hepatite_b")
    private OuiNonNP hepatiteB;

    @Enumerated(EnumType.STRING)
    @Column(name = "hepatite_c")
    private OuiNonNP hepatiteC;

    @Enumerated(EnumType.STRING)
    @Column(name = "vih")
    private OuiNonNP vih;

    @Column(name = "thrombose")
    private Boolean thrombose;

    @Column(name = "reaction_allergique")
    private Boolean reactionAllergique;

    @Column(name = "alloimmunisation")
    private Boolean alloimmunisation;

    @Size(max = 255)
    @Column(name = "autres_complications", length = 255)
    private String autresComplications;

    @Enumerated(EnumType.STRING)
    @Column(name = "adherence_therapeutique")
    private OuiNonNP adherenceTherapeutique;

    @Size(max = 255)
    @Column(name = "cause_non_adherence", length = 255)
    private String causeNonAdherence;

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

    @Column(name = "cause_deces_snc")
    private Boolean causeDecesSNC;

    @Column(name = "cause_deces_autre_hemorragie")
    private Boolean causeDecesAutreHemorragie;

    @Column(name = "cause_deces_vih")
    private Boolean causeDecesVIH;

    @Column(name = "cause_deces_autre_infection")
    private Boolean causeDecesAutreInfection;

    @Column(name = "cause_deces_thromboembolique")
    private Boolean causeDecesThromboembolique;

    @Column(name = "cause_deces_cancer")
    private Boolean causeDecesCancer;

    @Column(name = "cause_deces_covid_19")
    private Boolean causeDecesCovid19;

    @Column(name = "cause_deces_inconnue")
    private Boolean causeDecesInconnue;

    @Size(max = 255)
    @Column(name = "autre_cause_deces", length = 255)
    private String autreCauseDeces;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FicheFacteurXIII id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDossierNumber() {
        return this.dossierNumber;
    }

    public FicheFacteurXIII dossierNumber(String dossierNumber) {
        this.setDossierNumber(dossierNumber);
        return this;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return this.ordreNumber;
    }

    public FicheFacteurXIII ordreNumber(String ordreNumber) {
        this.setOrdreNumber(ordreNumber);
        return this;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return this.indexNumber;
    }

    public FicheFacteurXIII indexNumber(String indexNumber) {
        this.setIndexNumber(indexNumber);
        return this;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public FicheFacteurXIII prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public FicheFacteurXIII nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomJeuneFille() {
        return this.nomJeuneFille;
    }

    public FicheFacteurXIII nomJeuneFille(String nomJeuneFille) {
        this.setNomJeuneFille(nomJeuneFille);
        return this;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public String getProfession() {
        return this.profession;
    }

    public FicheFacteurXIII profession(String profession) {
        this.setProfession(profession);
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public CivilStatus getEtatCivil() {
        return this.etatCivil;
    }

    public FicheFacteurXIII etatCivil(CivilStatus etatCivil) {
        this.setEtatCivil(etatCivil);
        return this;
    }

    public void setEtatCivil(CivilStatus etatCivil) {
        this.etatCivil = etatCivil;
    }

    public ServiceCliniqueType getServiceClinique() {
        return this.serviceClinique;
    }

    public FicheFacteurXIII serviceClinique(ServiceCliniqueType serviceClinique) {
        this.setServiceClinique(serviceClinique);
        return this;
    }

    public void setServiceClinique(ServiceCliniqueType serviceClinique) {
        this.serviceClinique = serviceClinique;
    }

    public String getAutreServiceClinique() {
        return this.autreServiceClinique;
    }

    public FicheFacteurXIII autreServiceClinique(String autreServiceClinique) {
        this.setAutreServiceClinique(autreServiceClinique);
        return this;
    }

    public void setAutreServiceClinique(String autreServiceClinique) {
        this.autreServiceClinique = autreServiceClinique;
    }

    public CouvertureSociale getCouvertureSociale() {
        return this.couvertureSociale;
    }

    public FicheFacteurXIII couvertureSociale(CouvertureSociale couvertureSociale) {
        this.setCouvertureSociale(couvertureSociale);
        return this;
    }

    public void setCouvertureSociale(CouvertureSociale couvertureSociale) {
        this.couvertureSociale = couvertureSociale;
    }

    public Gender getSexe() {
        return this.sexe;
    }

    public FicheFacteurXIII sexe(Gender sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Gender sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public FicheFacteurXIII dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Integer getAgeActuel() {
        return this.ageActuel;
    }

    public FicheFacteurXIII ageActuel(Integer ageActuel) {
        this.setAgeActuel(ageActuel);
        return this;
    }

    public void setAgeActuel(Integer ageActuel) {
        this.ageActuel = ageActuel;
    }

    public Region getOrigine() {
        return this.origine;
    }

    public FicheFacteurXIII origine(Region origine) {
        this.setOrigine(origine);
        return this;
    }

    public void setOrigine(Region origine) {
        this.origine = origine;
    }

    public String getAutreOrigine() {
        return this.autreOrigine;
    }

    public FicheFacteurXIII autreOrigine(String autreOrigine) {
        this.setAutreOrigine(autreOrigine);
        return this;
    }

    public void setAutreOrigine(String autreOrigine) {
        this.autreOrigine = autreOrigine;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public FicheFacteurXIII adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public FicheFacteurXIII telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrenomPere() {
        return this.prenomPere;
    }

    public FicheFacteurXIII prenomPere(String prenomPere) {
        this.setPrenomPere(prenomPere);
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getProfessionPere() {
        return this.professionPere;
    }

    public FicheFacteurXIII professionPere(String professionPere) {
        this.setProfessionPere(professionPere);
        return this;
    }

    public void setProfessionPere(String professionPere) {
        this.professionPere = professionPere;
    }

    public String getNomPrenomMere() {
        return this.nomPrenomMere;
    }

    public FicheFacteurXIII nomPrenomMere(String nomPrenomMere) {
        this.setNomPrenomMere(nomPrenomMere);
        return this;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }

    public String getProfessionMere() {
        return this.professionMere;
    }

    public FicheFacteurXIII professionMere(String professionMere) {
        this.setProfessionMere(professionMere);
        return this;
    }

    public void setProfessionMere(String professionMere) {
        this.professionMere = professionMere;
    }

    public String getMedecinTraitant() {
        return this.medecinTraitant;
    }

    public FicheFacteurXIII medecinTraitant(String medecinTraitant) {
        this.setMedecinTraitant(medecinTraitant);
        return this;
    }

    public void setMedecinTraitant(String medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }

    public String getServiceOrigine() {
        return this.serviceOrigine;
    }

    public FicheFacteurXIII serviceOrigine(String serviceOrigine) {
        this.setServiceOrigine(serviceOrigine);
        return this;
    }

    public void setServiceOrigine(String serviceOrigine) {
        this.serviceOrigine = serviceOrigine;
    }

    public String getBiologisteResponsableDg() {
        return this.biologisteResponsableDg;
    }

    public FicheFacteurXIII biologisteResponsableDg(String biologisteResponsableDg) {
        this.setBiologisteResponsableDg(biologisteResponsableDg);
        return this;
    }

    public void setBiologisteResponsableDg(String biologisteResponsableDg) {
        this.biologisteResponsableDg = biologisteResponsableDg;
    }

    public DiagnosticType getDiagnostic() {
        return this.diagnostic;
    }

    public FicheFacteurXIII diagnostic(DiagnosticType diagnostic) {
        this.setDiagnostic(diagnostic);
        return this;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return this.autreDiagnostic;
    }

    public FicheFacteurXIII autreDiagnostic(String autreDiagnostic) {
        this.setAutreDiagnostic(autreDiagnostic);
        return this;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public Integer getAnneeDiagnostic() {
        return this.anneeDiagnostic;
    }

    public FicheFacteurXIII anneeDiagnostic(Integer anneeDiagnostic) {
        this.setAnneeDiagnostic(anneeDiagnostic);
        return this;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public OuiNonNP getConsentementRegistre() {
        return this.consentementRegistre;
    }

    public FicheFacteurXIII consentementRegistre(OuiNonNP consentementRegistre) {
        this.setConsentementRegistre(consentementRegistre);
        return this;
    }

    public void setConsentementRegistre(OuiNonNP consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return this.dateEnregistrementRegistre;
    }

    public FicheFacteurXIII dateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.setDateEnregistrementRegistre(dateEnregistrementRegistre);
        return this;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
    }

    public OuiNonNP getParentsConsanguins() {
        return this.parentsConsanguins;
    }

    public FicheFacteurXIII parentsConsanguins(OuiNonNP parentsConsanguins) {
        this.setParentsConsanguins(parentsConsanguins);
        return this;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public DegreParente getDegreParenteConsanguins() {
        return this.degreParenteConsanguins;
    }

    public FicheFacteurXIII degreParenteConsanguins(DegreParente degreParenteConsanguins) {
        this.setDegreParenteConsanguins(degreParenteConsanguins);
        return this;
    }

    public void setDegreParenteConsanguins(DegreParente degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public OuiNonNP getCasSimilaires() {
        return this.casSimilaires;
    }

    public FicheFacteurXIII casSimilaires(OuiNonNP casSimilaires) {
        this.setCasSimilaires(casSimilaires);
        return this;
    }

    public void setCasSimilaires(OuiNonNP casSimilaires) {
        this.casSimilaires = casSimilaires;
    }

    public Integer getNombreCas() {
        return this.nombreCas;
    }

    public FicheFacteurXIII nombreCas(Integer nombreCas) {
        this.setNombreCas(nombreCas);
        return this;
    }

    public void setNombreCas(Integer nombreCas) {
        this.nombreCas = nombreCas;
    }

    public Integer getCas1Numero() {
        return this.cas1Numero;
    }

    public FicheFacteurXIII cas1Numero(Integer cas1Numero) {
        this.setCas1Numero(cas1Numero);
        return this;
    }

    public void setCas1Numero(Integer cas1Numero) {
        this.cas1Numero = cas1Numero;
    }

    public LienParente getLienParenteCas1() {
        return this.lienParenteCas1;
    }

    public FicheFacteurXIII lienParenteCas1(LienParente lienParenteCas1) {
        this.setLienParenteCas1(lienParenteCas1);
        return this;
    }

    public void setLienParenteCas1(LienParente lienParenteCas1) {
        this.lienParenteCas1 = lienParenteCas1;
    }

    public String getDegreParenteCas1() {
        return this.degreParenteCas1;
    }

    public FicheFacteurXIII degreParenteCas1(String degreParenteCas1) {
        this.setDegreParenteCas1(degreParenteCas1);
        return this;
    }

    public void setDegreParenteCas1(String degreParenteCas1) {
        this.degreParenteCas1 = degreParenteCas1;
    }

    public String getTypeHemorragieCas1() {
        return this.typeHemorragieCas1;
    }

    public FicheFacteurXIII typeHemorragieCas1(String typeHemorragieCas1) {
        this.setTypeHemorragieCas1(typeHemorragieCas1);
        return this;
    }

    public void setTypeHemorragieCas1(String typeHemorragieCas1) {
        this.typeHemorragieCas1 = typeHemorragieCas1;
    }

    public Integer getCas2Numero() {
        return this.cas2Numero;
    }

    public FicheFacteurXIII cas2Numero(Integer cas2Numero) {
        this.setCas2Numero(cas2Numero);
        return this;
    }

    public void setCas2Numero(Integer cas2Numero) {
        this.cas2Numero = cas2Numero;
    }

    public LienParente getLienParenteCas2() {
        return this.lienParenteCas2;
    }

    public FicheFacteurXIII lienParenteCas2(LienParente lienParenteCas2) {
        this.setLienParenteCas2(lienParenteCas2);
        return this;
    }

    public void setLienParenteCas2(LienParente lienParenteCas2) {
        this.lienParenteCas2 = lienParenteCas2;
    }

    public String getDegreParenteCas2() {
        return this.degreParenteCas2;
    }

    public FicheFacteurXIII degreParenteCas2(String degreParenteCas2) {
        this.setDegreParenteCas2(degreParenteCas2);
        return this;
    }

    public void setDegreParenteCas2(String degreParenteCas2) {
        this.degreParenteCas2 = degreParenteCas2;
    }

    public String getTypeHemorragieCas2() {
        return this.typeHemorragieCas2;
    }

    public FicheFacteurXIII typeHemorragieCas2(String typeHemorragieCas2) {
        this.setTypeHemorragieCas2(typeHemorragieCas2);
        return this;
    }

    public void setTypeHemorragieCas2(String typeHemorragieCas2) {
        this.typeHemorragieCas2 = typeHemorragieCas2;
    }

    public Integer getCas3Numero() {
        return this.cas3Numero;
    }

    public FicheFacteurXIII cas3Numero(Integer cas3Numero) {
        this.setCas3Numero(cas3Numero);
        return this;
    }

    public void setCas3Numero(Integer cas3Numero) {
        this.cas3Numero = cas3Numero;
    }

    public LienParente getLienParenteCas3() {
        return this.lienParenteCas3;
    }

    public FicheFacteurXIII lienParenteCas3(LienParente lienParenteCas3) {
        this.setLienParenteCas3(lienParenteCas3);
        return this;
    }

    public void setLienParenteCas3(LienParente lienParenteCas3) {
        this.lienParenteCas3 = lienParenteCas3;
    }

    public String getDegreParenteCas3() {
        return this.degreParenteCas3;
    }

    public FicheFacteurXIII degreParenteCas3(String degreParenteCas3) {
        this.setDegreParenteCas3(degreParenteCas3);
        return this;
    }

    public void setDegreParenteCas3(String degreParenteCas3) {
        this.degreParenteCas3 = degreParenteCas3;
    }

    public String getTypeHemorragieCas3() {
        return this.typeHemorragieCas3;
    }

    public FicheFacteurXIII typeHemorragieCas3(String typeHemorragieCas3) {
        this.setTypeHemorragieCas3(typeHemorragieCas3);
        return this;
    }

    public void setTypeHemorragieCas3(String typeHemorragieCas3) {
        this.typeHemorragieCas3 = typeHemorragieCas3;
    }

    public OuiNonNP getDecesSyndromeHemorragique() {
        return this.decesSyndromeHemorragique;
    }

    public FicheFacteurXIII decesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.setDecesSyndromeHemorragique(decesSyndromeHemorragique);
        return this;
    }

    public void setDecesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.decesSyndromeHemorragique = decesSyndromeHemorragique;
    }

    public Integer getNombreDeces() {
        return this.nombreDeces;
    }

    public FicheFacteurXIII nombreDeces(Integer nombreDeces) {
        this.setNombreDeces(nombreDeces);
        return this;
    }

    public void setNombreDeces(Integer nombreDeces) {
        this.nombreDeces = nombreDeces;
    }

    public Integer getDeces1Numero() {
        return this.deces1Numero;
    }

    public FicheFacteurXIII deces1Numero(Integer deces1Numero) {
        this.setDeces1Numero(deces1Numero);
        return this;
    }

    public void setDeces1Numero(Integer deces1Numero) {
        this.deces1Numero = deces1Numero;
    }

    public LienParente getLienParenteDeces1() {
        return this.lienParenteDeces1;
    }

    public FicheFacteurXIII lienParenteDeces1(LienParente lienParenteDeces1) {
        this.setLienParenteDeces1(lienParenteDeces1);
        return this;
    }

    public void setLienParenteDeces1(LienParente lienParenteDeces1) {
        this.lienParenteDeces1 = lienParenteDeces1;
    }

    public String getCauseDeces1() {
        return this.causeDeces1;
    }

    public FicheFacteurXIII causeDeces1(String causeDeces1) {
        this.setCauseDeces1(causeDeces1);
        return this;
    }

    public void setCauseDeces1(String causeDeces1) {
        this.causeDeces1 = causeDeces1;
    }

    public Integer getDeces2Numero() {
        return this.deces2Numero;
    }

    public FicheFacteurXIII deces2Numero(Integer deces2Numero) {
        this.setDeces2Numero(deces2Numero);
        return this;
    }

    public void setDeces2Numero(Integer deces2Numero) {
        this.deces2Numero = deces2Numero;
    }

    public LienParente getLienParenteDeces2() {
        return this.lienParenteDeces2;
    }

    public FicheFacteurXIII lienParenteDeces2(LienParente lienParenteDeces2) {
        this.setLienParenteDeces2(lienParenteDeces2);
        return this;
    }

    public void setLienParenteDeces2(LienParente lienParenteDeces2) {
        this.lienParenteDeces2 = lienParenteDeces2;
    }

    public String getCauseDeces2() {
        return this.causeDeces2;
    }

    public FicheFacteurXIII causeDeces2(String causeDeces2) {
        this.setCauseDeces2(causeDeces2);
        return this;
    }

    public void setCauseDeces2(String causeDeces2) {
        this.causeDeces2 = causeDeces2;
    }

    public Integer getDeces3Numero() {
        return this.deces3Numero;
    }

    public FicheFacteurXIII deces3Numero(Integer deces3Numero) {
        this.setDeces3Numero(deces3Numero);
        return this;
    }

    public void setDeces3Numero(Integer deces3Numero) {
        this.deces3Numero = deces3Numero;
    }

    public LienParente getLienParenteDeces3() {
        return this.lienParenteDeces3;
    }

    public FicheFacteurXIII lienParenteDeces3(LienParente lienParenteDeces3) {
        this.setLienParenteDeces3(lienParenteDeces3);
        return this;
    }

    public void setLienParenteDeces3(LienParente lienParenteDeces3) {
        this.lienParenteDeces3 = lienParenteDeces3;
    }

    public String getCauseDeces3() {
        return this.causeDeces3;
    }

    public FicheFacteurXIII causeDeces3(String causeDeces3) {
        this.setCauseDeces3(causeDeces3);
        return this;
    }

    public void setCauseDeces3(String causeDeces3) {
        this.causeDeces3 = causeDeces3;
    }

    public Boolean getEnqueteFamiliale() {
        return this.enqueteFamiliale;
    }

    public FicheFacteurXIII enqueteFamiliale(Boolean enqueteFamiliale) {
        this.setEnqueteFamiliale(enqueteFamiliale);
        return this;
    }

    public void setEnqueteFamiliale(Boolean enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public Boolean getDecouverteFortuite() {
        return this.decouverteFortuite;
    }

    public FicheFacteurXIII decouverteFortuite(Boolean decouverteFortuite) {
        this.setDecouverteFortuite(decouverteFortuite);
        return this;
    }

    public void setDecouverteFortuite(Boolean decouverteFortuite) {
        this.decouverteFortuite = decouverteFortuite;
    }

    public Boolean getExplorationSyndromeHemorragique() {
        return this.explorationSyndromeHemorragique;
    }

    public FicheFacteurXIII explorationSyndromeHemorragique(Boolean explorationSyndromeHemorragique) {
        this.setExplorationSyndromeHemorragique(explorationSyndromeHemorragique);
        return this;
    }

    public void setExplorationSyndromeHemorragique(Boolean explorationSyndromeHemorragique) {
        this.explorationSyndromeHemorragique = explorationSyndromeHemorragique;
    }

    public Boolean getHemorragieChuteCordon() {
        return this.hemorragieChuteCordon;
    }

    public FicheFacteurXIII hemorragieChuteCordon(Boolean hemorragieChuteCordon) {
        this.setHemorragieChuteCordon(hemorragieChuteCordon);
        return this;
    }

    public void setHemorragieChuteCordon(Boolean hemorragieChuteCordon) {
        this.hemorragieChuteCordon = hemorragieChuteCordon;
    }

    public Boolean getMetroragieMenarche() {
        return this.metroragieMenarche;
    }

    public FicheFacteurXIII metroragieMenarche(Boolean metroragieMenarche) {
        this.setMetroragieMenarche(metroragieMenarche);
        return this;
    }

    public void setMetroragieMenarche(Boolean metroragieMenarche) {
        this.metroragieMenarche = metroragieMenarche;
    }

    public Boolean getHemorragieCerebraleNN() {
        return this.hemorragieCerebraleNN;
    }

    public FicheFacteurXIII hemorragieCerebraleNN(Boolean hemorragieCerebraleNN) {
        this.setHemorragieCerebraleNN(hemorragieCerebraleNN);
        return this;
    }

    public void setHemorragieCerebraleNN(Boolean hemorragieCerebraleNN) {
        this.hemorragieCerebraleNN = hemorragieCerebraleNN;
    }

    public Boolean getHemorragiePostPartum() {
        return this.hemorragiePostPartum;
    }

    public FicheFacteurXIII hemorragiePostPartum(Boolean hemorragiePostPartum) {
        this.setHemorragiePostPartum(hemorragiePostPartum);
        return this;
    }

    public void setHemorragiePostPartum(Boolean hemorragiePostPartum) {
        this.hemorragiePostPartum = hemorragiePostPartum;
    }

    public String getAutresCirconstances() {
        return this.autresCirconstances;
    }

    public FicheFacteurXIII autresCirconstances(String autresCirconstances) {
        this.setAutresCirconstances(autresCirconstances);
        return this;
    }

    public void setAutresCirconstances(String autresCirconstances) {
        this.autresCirconstances = autresCirconstances;
    }

    public Integer getDatePremierSigneCliniqueEvocateurJour() {
        return this.datePremierSigneCliniqueEvocateurJour;
    }

    public FicheFacteurXIII datePremierSigneCliniqueEvocateurJour(Integer datePremierSigneCliniqueEvocateurJour) {
        this.setDatePremierSigneCliniqueEvocateurJour(datePremierSigneCliniqueEvocateurJour);
        return this;
    }

    public void setDatePremierSigneCliniqueEvocateurJour(Integer datePremierSigneCliniqueEvocateurJour) {
        this.datePremierSigneCliniqueEvocateurJour = datePremierSigneCliniqueEvocateurJour;
    }

    public Integer getDatePremierSigneCliniqueEvocateurMois() {
        return this.datePremierSigneCliniqueEvocateurMois;
    }

    public FicheFacteurXIII datePremierSigneCliniqueEvocateurMois(Integer datePremierSigneCliniqueEvocateurMois) {
        this.setDatePremierSigneCliniqueEvocateurMois(datePremierSigneCliniqueEvocateurMois);
        return this;
    }

    public void setDatePremierSigneCliniqueEvocateurMois(Integer datePremierSigneCliniqueEvocateurMois) {
        this.datePremierSigneCliniqueEvocateurMois = datePremierSigneCliniqueEvocateurMois;
    }

    public Integer getDatePremierSigneCliniqueEvocateurAnnee() {
        return this.datePremierSigneCliniqueEvocateurAnnee;
    }

    public FicheFacteurXIII datePremierSigneCliniqueEvocateurAnnee(Integer datePremierSigneCliniqueEvocateurAnnee) {
        this.setDatePremierSigneCliniqueEvocateurAnnee(datePremierSigneCliniqueEvocateurAnnee);
        return this;
    }

    public void setDatePremierSigneCliniqueEvocateurAnnee(Integer datePremierSigneCliniqueEvocateurAnnee) {
        this.datePremierSigneCliniqueEvocateurAnnee = datePremierSigneCliniqueEvocateurAnnee;
    }

    public Integer getAgeDiagnosticJour() {
        return this.ageDiagnosticJour;
    }

    public FicheFacteurXIII ageDiagnosticJour(Integer ageDiagnosticJour) {
        this.setAgeDiagnosticJour(ageDiagnosticJour);
        return this;
    }

    public void setAgeDiagnosticJour(Integer ageDiagnosticJour) {
        this.ageDiagnosticJour = ageDiagnosticJour;
    }

    public Integer getAgeDiagnosticMois() {
        return this.ageDiagnosticMois;
    }

    public FicheFacteurXIII ageDiagnosticMois(Integer ageDiagnosticMois) {
        this.setAgeDiagnosticMois(ageDiagnosticMois);
        return this;
    }

    public void setAgeDiagnosticMois(Integer ageDiagnosticMois) {
        this.ageDiagnosticMois = ageDiagnosticMois;
    }

    public Integer getAgeDiagnosticAnnee() {
        return this.ageDiagnosticAnnee;
    }

    public FicheFacteurXIII ageDiagnosticAnnee(Integer ageDiagnosticAnnee) {
        this.setAgeDiagnosticAnnee(ageDiagnosticAnnee);
        return this;
    }

    public void setAgeDiagnosticAnnee(Integer ageDiagnosticAnnee) {
        this.ageDiagnosticAnnee = ageDiagnosticAnnee;
    }

    public Double getHb() {
        return this.hb;
    }

    public FicheFacteurXIII hb(Double hb) {
        this.setHb(hb);
        return this;
    }

    public void setHb(Double hb) {
        this.hb = hb;
    }

    public Double getHt() {
        return this.ht;
    }

    public FicheFacteurXIII ht(Double ht) {
        this.setHt(ht);
        return this;
    }

    public void setHt(Double ht) {
        this.ht = ht;
    }

    public Integer getPlaquettes() {
        return this.plaquettes;
    }

    public FicheFacteurXIII plaquettes(Integer plaquettes) {
        this.setPlaquettes(plaquettes);
        return this;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Double getTp() {
        return this.tp;
    }

    public FicheFacteurXIII tp(Double tp) {
        this.setTp(tp);
        return this;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getTpMT() {
        return this.tpMT;
    }

    public FicheFacteurXIII tpMT(Double tpMT) {
        this.setTpMT(tpMT);
        return this;
    }

    public void setTpMT(Double tpMT) {
        this.tpMT = tpMT;
    }

    public Double getTca() {
        return this.tca;
    }

    public FicheFacteurXIII tca(Double tca) {
        this.setTca(tca);
        return this;
    }

    public void setTca(Double tca) {
        this.tca = tca;
    }

    public Double getTcaMT() {
        return this.tcaMT;
    }

    public FicheFacteurXIII tcaMT(Double tcaMT) {
        this.setTcaMT(tcaMT);
        return this;
    }

    public void setTcaMT(Double tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Double getFibrinogene() {
        return this.fibrinogene;
    }

    public FicheFacteurXIII fibrinogene(Double fibrinogene) {
        this.setFibrinogene(fibrinogene);
        return this;
    }

    public void setFibrinogene(Double fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public TypeFaitOuNonFaitEtResultat getTs() {
        return this.ts;
    }

    public FicheFacteurXIII ts(TypeFaitOuNonFaitEtResultat ts) {
        this.setTs(ts);
        return this;
    }

    public void setTs(TypeFaitOuNonFaitEtResultat ts) {
        this.ts = ts;
    }

    public TypeFaitOuNonFaitEtResultat getToPfa200() {
        return this.toPfa200;
    }

    public FicheFacteurXIII toPfa200(TypeFaitOuNonFaitEtResultat toPfa200) {
        this.setToPfa200(toPfa200);
        return this;
    }

    public void setToPfa200(TypeFaitOuNonFaitEtResultat toPfa200) {
        this.toPfa200 = toPfa200;
    }

    public Double getColEpi() {
        return this.colEpi;
    }

    public FicheFacteurXIII colEpi(Double colEpi) {
        this.setColEpi(colEpi);
        return this;
    }

    public void setColEpi(Double colEpi) {
        this.colEpi = colEpi;
    }

    public Double getColAdp() {
        return this.colAdp;
    }

    public FicheFacteurXIII colAdp(Double colAdp) {
        this.setColAdp(colAdp);
        return this;
    }

    public void setColAdp(Double colAdp) {
        this.colAdp = colAdp;
    }

    public Double getFacteurXIII() {
        return this.facteurXIII;
    }

    public FicheFacteurXIII facteurXIII(Double facteurXIII) {
        this.setFacteurXIII(facteurXIII);
        return this;
    }

    public void setFacteurXIII(Double facteurXIII) {
        this.facteurXIII = facteurXIII;
    }

    public Boolean getTechniqueChromogenique() {
        return this.techniqueChromogenique;
    }

    public FicheFacteurXIII techniqueChromogenique(Boolean techniqueChromogenique) {
        this.setTechniqueChromogenique(techniqueChromogenique);
        return this;
    }

    public void setTechniqueChromogenique(Boolean techniqueChromogenique) {
        this.techniqueChromogenique = techniqueChromogenique;
    }

    public Double getTauxChromogenique() {
        return this.tauxChromogenique;
    }

    public FicheFacteurXIII tauxChromogenique(Double tauxChromogenique) {
        this.setTauxChromogenique(tauxChromogenique);
        return this;
    }

    public void setTauxChromogenique(Double tauxChromogenique) {
        this.tauxChromogenique = tauxChromogenique;
    }

    public Boolean getTechniqueSolubiliteCaillot() {
        return this.techniqueSolubiliteCaillot;
    }

    public FicheFacteurXIII techniqueSolubiliteCaillot(Boolean techniqueSolubiliteCaillot) {
        this.setTechniqueSolubiliteCaillot(techniqueSolubiliteCaillot);
        return this;
    }

    public void setTechniqueSolubiliteCaillot(Boolean techniqueSolubiliteCaillot) {
        this.techniqueSolubiliteCaillot = techniqueSolubiliteCaillot;
    }

    public Double getTauxSolubiliteCaillot() {
        return this.tauxSolubiliteCaillot;
    }

    public FicheFacteurXIII tauxSolubiliteCaillot(Double tauxSolubiliteCaillot) {
        this.setTauxSolubiliteCaillot(tauxSolubiliteCaillot);
        return this;
    }

    public void setTauxSolubiliteCaillot(Double tauxSolubiliteCaillot) {
        this.tauxSolubiliteCaillot = tauxSolubiliteCaillot;
    }

    public Boolean getConfirmationMoleculaire() {
        return this.confirmationMoleculaire;
    }

    public FicheFacteurXIII confirmationMoleculaire(Boolean confirmationMoleculaire) {
        this.setConfirmationMoleculaire(confirmationMoleculaire);
        return this;
    }

    public void setConfirmationMoleculaire(Boolean confirmationMoleculaire) {
        this.confirmationMoleculaire = confirmationMoleculaire;
    }

    public Boolean getMutationPresente() {
        return this.mutationPresente;
    }

    public FicheFacteurXIII mutationPresente(Boolean mutationPresente) {
        this.setMutationPresente(mutationPresente);
        return this;
    }

    public void setMutationPresente(Boolean mutationPresente) {
        this.mutationPresente = mutationPresente;
    }

    public String getMutationNom() {
        return this.mutationNom;
    }

    public FicheFacteurXIII mutationNom(String mutationNom) {
        this.setMutationNom(mutationNom);
        return this;
    }

    public void setMutationNom(String mutationNom) {
        this.mutationNom = mutationNom;
    }

    public MutationType getMutationType() {
        return this.mutationType;
    }

    public FicheFacteurXIII mutationType(MutationType mutationType) {
        this.setMutationType(mutationType);
        return this;
    }

    public void setMutationType(MutationType mutationType) {
        this.mutationType = mutationType;
    }

    public String getMutation2Nom() {
        return this.mutation2Nom;
    }

    public FicheFacteurXIII mutation2Nom(String mutation2Nom) {
        this.setMutation2Nom(mutation2Nom);
        return this;
    }

    public void setMutation2Nom(String mutation2Nom) {
        this.mutation2Nom = mutation2Nom;
    }

    public OuiNonNP getSaignementChuteCordon() {
        return this.saignementChuteCordon;
    }

    public FicheFacteurXIII saignementChuteCordon(OuiNonNP saignementChuteCordon) {
        this.setSaignementChuteCordon(saignementChuteCordon);
        return this;
    }

    public void setSaignementChuteCordon(OuiNonNP saignementChuteCordon) {
        this.saignementChuteCordon = saignementChuteCordon;
    }

    public OuiNonNP getCicatricesKeloides() {
        return this.cicatricesKeloides;
    }

    public FicheFacteurXIII cicatricesKeloides(OuiNonNP cicatricesKeloides) {
        this.setCicatricesKeloides(cicatricesKeloides);
        return this;
    }

    public void setCicatricesKeloides(OuiNonNP cicatricesKeloides) {
        this.cicatricesKeloides = cicatricesKeloides;
    }

    public OuiNonNP getRetardCicatrisation() {
        return this.retardCicatrisation;
    }

    public FicheFacteurXIII retardCicatrisation(OuiNonNP retardCicatrisation) {
        this.setRetardCicatrisation(retardCicatrisation);
        return this;
    }

    public void setRetardCicatrisation(OuiNonNP retardCicatrisation) {
        this.retardCicatrisation = retardCicatrisation;
    }

    public Boolean getEpistaxis() {
        return this.epistaxis;
    }

    public FicheFacteurXIII epistaxis(Boolean epistaxis) {
        this.setEpistaxis(epistaxis);
        return this;
    }

    public void setEpistaxis(Boolean epistaxis) {
        this.epistaxis = epistaxis;
    }

    public Integer getFrequenceEpistaxis() {
        return this.frequenceEpistaxis;
    }

    public FicheFacteurXIII frequenceEpistaxis(Integer frequenceEpistaxis) {
        this.setFrequenceEpistaxis(frequenceEpistaxis);
        return this;
    }

    public void setFrequenceEpistaxis(Integer frequenceEpistaxis) {
        this.frequenceEpistaxis = frequenceEpistaxis;
    }

    public Boolean getGingivorragies() {
        return this.gingivorragies;
    }

    public FicheFacteurXIII gingivorragies(Boolean gingivorragies) {
        this.setGingivorragies(gingivorragies);
        return this;
    }

    public void setGingivorragies(Boolean gingivorragies) {
        this.gingivorragies = gingivorragies;
    }

    public TypeManifestation getTypeGingivorragies() {
        return this.typeGingivorragies;
    }

    public FicheFacteurXIII typeGingivorragies(TypeManifestation typeGingivorragies) {
        this.setTypeGingivorragies(typeGingivorragies);
        return this;
    }

    public void setTypeGingivorragies(TypeManifestation typeGingivorragies) {
        this.typeGingivorragies = typeGingivorragies;
    }

    public Integer getFrequenceGingivorragies() {
        return this.frequenceGingivorragies;
    }

    public FicheFacteurXIII frequenceGingivorragies(Integer frequenceGingivorragies) {
        this.setFrequenceGingivorragies(frequenceGingivorragies);
        return this;
    }

    public void setFrequenceGingivorragies(Integer frequenceGingivorragies) {
        this.frequenceGingivorragies = frequenceGingivorragies;
    }

    public Boolean getEcchymose() {
        return this.ecchymose;
    }

    public FicheFacteurXIII ecchymose(Boolean ecchymose) {
        this.setEcchymose(ecchymose);
        return this;
    }

    public void setEcchymose(Boolean ecchymose) {
        this.ecchymose = ecchymose;
    }

    public TypeManifestation getTypeEcchymose() {
        return this.typeEcchymose;
    }

    public FicheFacteurXIII typeEcchymose(TypeManifestation typeEcchymose) {
        this.setTypeEcchymose(typeEcchymose);
        return this;
    }

    public void setTypeEcchymose(TypeManifestation typeEcchymose) {
        this.typeEcchymose = typeEcchymose;
    }

    public Integer getFrequenceEcchymose() {
        return this.frequenceEcchymose;
    }

    public FicheFacteurXIII frequenceEcchymose(Integer frequenceEcchymose) {
        this.setFrequenceEcchymose(frequenceEcchymose);
        return this;
    }

    public void setFrequenceEcchymose(Integer frequenceEcchymose) {
        this.frequenceEcchymose = frequenceEcchymose;
    }

    public Boolean getMenorragie() {
        return this.menorragie;
    }

    public FicheFacteurXIII menorragie(Boolean menorragie) {
        this.setMenorragie(menorragie);
        return this;
    }

    public void setMenorragie(Boolean menorragie) {
        this.menorragie = menorragie;
    }

    public Integer getFrequenceMenorragie() {
        return this.frequenceMenorragie;
    }

    public FicheFacteurXIII frequenceMenorragie(Integer frequenceMenorragie) {
        this.setFrequenceMenorragie(frequenceMenorragie);
        return this;
    }

    public void setFrequenceMenorragie(Integer frequenceMenorragie) {
        this.frequenceMenorragie = frequenceMenorragie;
    }

    public Boolean getHematemese() {
        return this.hematemese;
    }

    public FicheFacteurXIII hematemese(Boolean hematemese) {
        this.setHematemese(hematemese);
        return this;
    }

    public void setHematemese(Boolean hematemese) {
        this.hematemese = hematemese;
    }

    public Integer getFrequenceHematemese() {
        return this.frequenceHematemese;
    }

    public FicheFacteurXIII frequenceHematemese(Integer frequenceHematemese) {
        this.setFrequenceHematemese(frequenceHematemese);
        return this;
    }

    public void setFrequenceHematemese(Integer frequenceHematemese) {
        this.frequenceHematemese = frequenceHematemese;
    }

    public Boolean getRectorragie() {
        return this.rectorragie;
    }

    public FicheFacteurXIII rectorragie(Boolean rectorragie) {
        this.setRectorragie(rectorragie);
        return this;
    }

    public void setRectorragie(Boolean rectorragie) {
        this.rectorragie = rectorragie;
    }

    public Integer getFrequenceRectorragie() {
        return this.frequenceRectorragie;
    }

    public FicheFacteurXIII frequenceRectorragie(Integer frequenceRectorragie) {
        this.setFrequenceRectorragie(frequenceRectorragie);
        return this;
    }

    public void setFrequenceRectorragie(Integer frequenceRectorragie) {
        this.frequenceRectorragie = frequenceRectorragie;
    }

    public Boolean getHematurie() {
        return this.hematurie;
    }

    public FicheFacteurXIII hematurie(Boolean hematurie) {
        this.setHematurie(hematurie);
        return this;
    }

    public void setHematurie(Boolean hematurie) {
        this.hematurie = hematurie;
    }

    public Integer getFrequenceHematurie() {
        return this.frequenceHematurie;
    }

    public FicheFacteurXIII frequenceHematurie(Integer frequenceHematurie) {
        this.setFrequenceHematurie(frequenceHematurie);
        return this;
    }

    public void setFrequenceHematurie(Integer frequenceHematurie) {
        this.frequenceHematurie = frequenceHematurie;
    }

    public Boolean getHematomeSousCutane() {
        return this.hematomeSousCutane;
    }

    public FicheFacteurXIII hematomeSousCutane(Boolean hematomeSousCutane) {
        this.setHematomeSousCutane(hematomeSousCutane);
        return this;
    }

    public void setHematomeSousCutane(Boolean hematomeSousCutane) {
        this.hematomeSousCutane = hematomeSousCutane;
    }

    public TypeManifestation getTypeHematomeSousCutane() {
        return this.typeHematomeSousCutane;
    }

    public FicheFacteurXIII typeHematomeSousCutane(TypeManifestation typeHematomeSousCutane) {
        this.setTypeHematomeSousCutane(typeHematomeSousCutane);
        return this;
    }

    public void setTypeHematomeSousCutane(TypeManifestation typeHematomeSousCutane) {
        this.typeHematomeSousCutane = typeHematomeSousCutane;
    }

    public Integer getFrequenceHematomeSousCutane() {
        return this.frequenceHematomeSousCutane;
    }

    public FicheFacteurXIII frequenceHematomeSousCutane(Integer frequenceHematomeSousCutane) {
        this.setFrequenceHematomeSousCutane(frequenceHematomeSousCutane);
        return this;
    }

    public void setFrequenceHematomeSousCutane(Integer frequenceHematomeSousCutane) {
        this.frequenceHematomeSousCutane = frequenceHematomeSousCutane;
    }

    public Boolean getHematomeMusculaire() {
        return this.hematomeMusculaire;
    }

    public FicheFacteurXIII hematomeMusculaire(Boolean hematomeMusculaire) {
        this.setHematomeMusculaire(hematomeMusculaire);
        return this;
    }

    public void setHematomeMusculaire(Boolean hematomeMusculaire) {
        this.hematomeMusculaire = hematomeMusculaire;
    }

    public String getSiegeHematomeMusculaire() {
        return this.siegeHematomeMusculaire;
    }

    public FicheFacteurXIII siegeHematomeMusculaire(String siegeHematomeMusculaire) {
        this.setSiegeHematomeMusculaire(siegeHematomeMusculaire);
        return this;
    }

    public void setSiegeHematomeMusculaire(String siegeHematomeMusculaire) {
        this.siegeHematomeMusculaire = siegeHematomeMusculaire;
    }

    public TypeManifestation getTypeHematomeMusculaire() {
        return this.typeHematomeMusculaire;
    }

    public FicheFacteurXIII typeHematomeMusculaire(TypeManifestation typeHematomeMusculaire) {
        this.setTypeHematomeMusculaire(typeHematomeMusculaire);
        return this;
    }

    public void setTypeHematomeMusculaire(TypeManifestation typeHematomeMusculaire) {
        this.typeHematomeMusculaire = typeHematomeMusculaire;
    }

    public Integer getFrequenceHematomeMusculaire() {
        return this.frequenceHematomeMusculaire;
    }

    public FicheFacteurXIII frequenceHematomeMusculaire(Integer frequenceHematomeMusculaire) {
        this.setFrequenceHematomeMusculaire(frequenceHematomeMusculaire);
        return this;
    }

    public void setFrequenceHematomeMusculaire(Integer frequenceHematomeMusculaire) {
        this.frequenceHematomeMusculaire = frequenceHematomeMusculaire;
    }

    public Boolean getHemarthrose() {
        return this.hemarthrose;
    }

    public FicheFacteurXIII hemarthrose(Boolean hemarthrose) {
        this.setHemarthrose(hemarthrose);
        return this;
    }

    public void setHemarthrose(Boolean hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public TypeManifestation getTypeHemarthrose() {
        return this.typeHemarthrose;
    }

    public FicheFacteurXIII typeHemarthrose(TypeManifestation typeHemarthrose) {
        this.setTypeHemarthrose(typeHemarthrose);
        return this;
    }

    public void setTypeHemarthrose(TypeManifestation typeHemarthrose) {
        this.typeHemarthrose = typeHemarthrose;
    }

    public Integer getFrequenceHemarthrose() {
        return this.frequenceHemarthrose;
    }

    public FicheFacteurXIII frequenceHemarthrose(Integer frequenceHemarthrose) {
        this.setFrequenceHemarthrose(frequenceHemarthrose);
        return this;
    }

    public void setFrequenceHemarthrose(Integer frequenceHemarthrose) {
        this.frequenceHemarthrose = frequenceHemarthrose;
    }

    public Boolean getSaignementSNC() {
        return this.saignementSNC;
    }

    public FicheFacteurXIII saignementSNC(Boolean saignementSNC) {
        this.setSaignementSNC(saignementSNC);
        return this;
    }

    public void setSaignementSNC(Boolean saignementSNC) {
        this.saignementSNC = saignementSNC;
    }

    public TypeManifestation getTypeSaignementSNC() {
        return this.typeSaignementSNC;
    }

    public FicheFacteurXIII typeSaignementSNC(TypeManifestation typeSaignementSNC) {
        this.setTypeSaignementSNC(typeSaignementSNC);
        return this;
    }

    public void setTypeSaignementSNC(TypeManifestation typeSaignementSNC) {
        this.typeSaignementSNC = typeSaignementSNC;
    }

    public Integer getFrequenceSaignementSNC() {
        return this.frequenceSaignementSNC;
    }

    public FicheFacteurXIII frequenceSaignementSNC(Integer frequenceSaignementSNC) {
        this.setFrequenceSaignementSNC(frequenceSaignementSNC);
        return this;
    }

    public void setFrequenceSaignementSNC(Integer frequenceSaignementSNC) {
        this.frequenceSaignementSNC = frequenceSaignementSNC;
    }

    public Boolean getRuptureKystesHemorragiques() {
        return this.ruptureKystesHemorragiques;
    }

    public FicheFacteurXIII ruptureKystesHemorragiques(Boolean ruptureKystesHemorragiques) {
        this.setRuptureKystesHemorragiques(ruptureKystesHemorragiques);
        return this;
    }

    public void setRuptureKystesHemorragiques(Boolean ruptureKystesHemorragiques) {
        this.ruptureKystesHemorragiques = ruptureKystesHemorragiques;
    }

    public Boolean getHemorragiesPostChirurgicales() {
        return this.hemorragiesPostChirurgicales;
    }

    public FicheFacteurXIII hemorragiesPostChirurgicales(Boolean hemorragiesPostChirurgicales) {
        this.setHemorragiesPostChirurgicales(hemorragiesPostChirurgicales);
        return this;
    }

    public void setHemorragiesPostChirurgicales(Boolean hemorragiesPostChirurgicales) {
        this.hemorragiesPostChirurgicales = hemorragiesPostChirurgicales;
    }

    public Boolean getHemoperitone() {
        return this.hemoperitone;
    }

    public FicheFacteurXIII hemoperitone(Boolean hemoperitone) {
        this.setHemoperitone(hemoperitone);
        return this;
    }

    public void setHemoperitone(Boolean hemoperitone) {
        this.hemoperitone = hemoperitone;
    }

    public Boolean getAccidentsThrombotiques() {
        return this.accidentsThrombotiques;
    }

    public FicheFacteurXIII accidentsThrombotiques(Boolean accidentsThrombotiques) {
        this.setAccidentsThrombotiques(accidentsThrombotiques);
        return this;
    }

    public void setAccidentsThrombotiques(Boolean accidentsThrombotiques) {
        this.accidentsThrombotiques = accidentsThrombotiques;
    }

    public AccidentsThrombotiquesType getTypeAccidentsThrombotiques() {
        return this.typeAccidentsThrombotiques;
    }

    public FicheFacteurXIII typeAccidentsThrombotiques(AccidentsThrombotiquesType typeAccidentsThrombotiques) {
        this.setTypeAccidentsThrombotiques(typeAccidentsThrombotiques);
        return this;
    }

    public void setTypeAccidentsThrombotiques(AccidentsThrombotiquesType typeAccidentsThrombotiques) {
        this.typeAccidentsThrombotiques = typeAccidentsThrombotiques;
    }

    public OuiNonNP getHemorragieGrave() {
        return this.hemorragieGrave;
    }

    public FicheFacteurXIII hemorragieGrave(OuiNonNP hemorragieGrave) {
        this.setHemorragieGrave(hemorragieGrave);
        return this;
    }

    public void setHemorragieGrave(OuiNonNP hemorragieGrave) {
        this.hemorragieGrave = hemorragieGrave;
    }

    public Boolean getGraveDeglobulisation() {
        return this.graveDeglobulisation;
    }

    public FicheFacteurXIII graveDeglobulisation(Boolean graveDeglobulisation) {
        this.setGraveDeglobulisation(graveDeglobulisation);
        return this;
    }

    public void setGraveDeglobulisation(Boolean graveDeglobulisation) {
        this.graveDeglobulisation = graveDeglobulisation;
    }

    public Boolean getGraveEtatDeChoc() {
        return this.graveEtatDeChoc;
    }

    public FicheFacteurXIII graveEtatDeChoc(Boolean graveEtatDeChoc) {
        this.setGraveEtatDeChoc(graveEtatDeChoc);
        return this;
    }

    public void setGraveEtatDeChoc(Boolean graveEtatDeChoc) {
        this.graveEtatDeChoc = graveEtatDeChoc;
    }

    public Boolean getGraveParLocalisation() {
        return this.graveParLocalisation;
    }

    public FicheFacteurXIII graveParLocalisation(Boolean graveParLocalisation) {
        this.setGraveParLocalisation(graveParLocalisation);
        return this;
    }

    public void setGraveParLocalisation(Boolean graveParLocalisation) {
        this.graveParLocalisation = graveParLocalisation;
    }

    public String getSiegeGraviteLocalisation() {
        return this.siegeGraviteLocalisation;
    }

    public FicheFacteurXIII siegeGraviteLocalisation(String siegeGraviteLocalisation) {
        this.setSiegeGraviteLocalisation(siegeGraviteLocalisation);
        return this;
    }

    public void setSiegeGraviteLocalisation(String siegeGraviteLocalisation) {
        this.siegeGraviteLocalisation = siegeGraviteLocalisation;
    }

    public OuiNonNP getChirurgiePatientPrepare() {
        return this.chirurgiePatientPrepare;
    }

    public FicheFacteurXIII chirurgiePatientPrepare(OuiNonNP chirurgiePatientPrepare) {
        this.setChirurgiePatientPrepare(chirurgiePatientPrepare);
        return this;
    }

    public void setChirurgiePatientPrepare(OuiNonNP chirurgiePatientPrepare) {
        this.chirurgiePatientPrepare = chirurgiePatientPrepare;
    }

    public OuiNonNP getChirurgieSuiteHemorragique() {
        return this.chirurgieSuiteHemorragique;
    }

    public FicheFacteurXIII chirurgieSuiteHemorragique(OuiNonNP chirurgieSuiteHemorragique) {
        this.setChirurgieSuiteHemorragique(chirurgieSuiteHemorragique);
        return this;
    }

    public void setChirurgieSuiteHemorragique(OuiNonNP chirurgieSuiteHemorragique) {
        this.chirurgieSuiteHemorragique = chirurgieSuiteHemorragique;
    }

    public OuiNonNP getCirconcisionPatientPrepare() {
        return this.circoncisionPatientPrepare;
    }

    public FicheFacteurXIII circoncisionPatientPrepare(OuiNonNP circoncisionPatientPrepare) {
        this.setCirconcisionPatientPrepare(circoncisionPatientPrepare);
        return this;
    }

    public void setCirconcisionPatientPrepare(OuiNonNP circoncisionPatientPrepare) {
        this.circoncisionPatientPrepare = circoncisionPatientPrepare;
    }

    public OuiNonNP getCirconcisionSuiteHemorragique() {
        return this.circoncisionSuiteHemorragique;
    }

    public FicheFacteurXIII circoncisionSuiteHemorragique(OuiNonNP circoncisionSuiteHemorragique) {
        this.setCirconcisionSuiteHemorragique(circoncisionSuiteHemorragique);
        return this;
    }

    public void setCirconcisionSuiteHemorragique(OuiNonNP circoncisionSuiteHemorragique) {
        this.circoncisionSuiteHemorragique = circoncisionSuiteHemorragique;
    }

    public Integer getAgeCirconcision() {
        return this.ageCirconcision;
    }

    public FicheFacteurXIII ageCirconcision(Integer ageCirconcision) {
        this.setAgeCirconcision(ageCirconcision);
        return this;
    }

    public void setAgeCirconcision(Integer ageCirconcision) {
        this.ageCirconcision = ageCirconcision;
    }

    public OuiNonNP getConcentreFacteurXIII() {
        return this.concentreFacteurXIII;
    }

    public FicheFacteurXIII concentreFacteurXIII(OuiNonNP concentreFacteurXIII) {
        this.setConcentreFacteurXIII(concentreFacteurXIII);
        return this;
    }

    public void setConcentreFacteurXIII(OuiNonNP concentreFacteurXIII) {
        this.concentreFacteurXIII = concentreFacteurXIII;
    }

    public OuiNonNP getPlasmaFraisCongele() {
        return this.plasmaFraisCongele;
    }

    public FicheFacteurXIII plasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.setPlasmaFraisCongele(plasmaFraisCongele);
        return this;
    }

    public void setPlasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.plasmaFraisCongele = plasmaFraisCongele;
    }

    public OuiNonNP getCryoprecipite() {
        return this.cryoprecipite;
    }

    public FicheFacteurXIII cryoprecipite(OuiNonNP cryoprecipite) {
        this.setCryoprecipite(cryoprecipite);
        return this;
    }

    public void setCryoprecipite(OuiNonNP cryoprecipite) {
        this.cryoprecipite = cryoprecipite;
    }

    public OuiNonNP getCulotsPlaquettaires() {
        return this.culotsPlaquettaires;
    }

    public FicheFacteurXIII culotsPlaquettaires(OuiNonNP culotsPlaquettaires) {
        this.setCulotsPlaquettaires(culotsPlaquettaires);
        return this;
    }

    public void setCulotsPlaquettaires(OuiNonNP culotsPlaquettaires) {
        this.culotsPlaquettaires = culotsPlaquettaires;
    }

    public OuiNonNP getDicynone() {
        return this.dicynone;
    }

    public FicheFacteurXIII dicynone(OuiNonNP dicynone) {
        this.setDicynone(dicynone);
        return this;
    }

    public void setDicynone(OuiNonNP dicynone) {
        this.dicynone = dicynone;
    }

    public OuiNonNP getExacyl() {
        return this.exacyl;
    }

    public FicheFacteurXIII exacyl(OuiNonNP exacyl) {
        this.setExacyl(exacyl);
        return this;
    }

    public void setExacyl(OuiNonNP exacyl) {
        this.exacyl = exacyl;
    }

    public OuiNonNP getTraitementMartial() {
        return this.traitementMartial;
    }

    public FicheFacteurXIII traitementMartial(OuiNonNP traitementMartial) {
        this.setTraitementMartial(traitementMartial);
        return this;
    }

    public void setTraitementMartial(OuiNonNP traitementMartial) {
        this.traitementMartial = traitementMartial;
    }

    public OuiNonNP getTraitementOestroprogestatif() {
        return this.traitementOestroprogestatif;
    }

    public FicheFacteurXIII traitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.setTraitementOestroprogestatif(traitementOestroprogestatif);
        return this;
    }

    public void setTraitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.traitementOestroprogestatif = traitementOestroprogestatif;
    }

    public OuiNonNP getTransfusionCGR() {
        return this.transfusionCGR;
    }

    public FicheFacteurXIII transfusionCGR(OuiNonNP transfusionCGR) {
        this.setTransfusionCGR(transfusionCGR);
        return this;
    }

    public void setTransfusionCGR(OuiNonNP transfusionCGR) {
        this.transfusionCGR = transfusionCGR;
    }

    public OuiNonNP getInhibiteurs() {
        return this.inhibiteurs;
    }

    public FicheFacteurXIII inhibiteurs(OuiNonNP inhibiteurs) {
        this.setInhibiteurs(inhibiteurs);
        return this;
    }

    public void setInhibiteurs(OuiNonNP inhibiteurs) {
        this.inhibiteurs = inhibiteurs;
    }

    public Boolean getInhibiteurNeutralisant() {
        return this.inhibiteurNeutralisant;
    }

    public FicheFacteurXIII inhibiteurNeutralisant(Boolean inhibiteurNeutralisant) {
        this.setInhibiteurNeutralisant(inhibiteurNeutralisant);
        return this;
    }

    public void setInhibiteurNeutralisant(Boolean inhibiteurNeutralisant) {
        this.inhibiteurNeutralisant = inhibiteurNeutralisant;
    }

    public Double getTitreInhibiteurNeutralisant() {
        return this.titreInhibiteurNeutralisant;
    }

    public FicheFacteurXIII titreInhibiteurNeutralisant(Double titreInhibiteurNeutralisant) {
        this.setTitreInhibiteurNeutralisant(titreInhibiteurNeutralisant);
        return this;
    }

    public void setTitreInhibiteurNeutralisant(Double titreInhibiteurNeutralisant) {
        this.titreInhibiteurNeutralisant = titreInhibiteurNeutralisant;
    }

    public Boolean getInhibiteurNonNeutralisant() {
        return this.inhibiteurNonNeutralisant;
    }

    public FicheFacteurXIII inhibiteurNonNeutralisant(Boolean inhibiteurNonNeutralisant) {
        this.setInhibiteurNonNeutralisant(inhibiteurNonNeutralisant);
        return this;
    }

    public void setInhibiteurNonNeutralisant(Boolean inhibiteurNonNeutralisant) {
        this.inhibiteurNonNeutralisant = inhibiteurNonNeutralisant;
    }

    public OuiNonNP getInfectionsVirales() {
        return this.infectionsVirales;
    }

    public FicheFacteurXIII infectionsVirales(OuiNonNP infectionsVirales) {
        this.setInfectionsVirales(infectionsVirales);
        return this;
    }

    public void setInfectionsVirales(OuiNonNP infectionsVirales) {
        this.infectionsVirales = infectionsVirales;
    }

    public OuiNonNP getHepatiteB() {
        return this.hepatiteB;
    }

    public FicheFacteurXIII hepatiteB(OuiNonNP hepatiteB) {
        this.setHepatiteB(hepatiteB);
        return this;
    }

    public void setHepatiteB(OuiNonNP hepatiteB) {
        this.hepatiteB = hepatiteB;
    }

    public OuiNonNP getHepatiteC() {
        return this.hepatiteC;
    }

    public FicheFacteurXIII hepatiteC(OuiNonNP hepatiteC) {
        this.setHepatiteC(hepatiteC);
        return this;
    }

    public void setHepatiteC(OuiNonNP hepatiteC) {
        this.hepatiteC = hepatiteC;
    }

    public OuiNonNP getVih() {
        return this.vih;
    }

    public FicheFacteurXIII vih(OuiNonNP vih) {
        this.setVih(vih);
        return this;
    }

    public void setVih(OuiNonNP vih) {
        this.vih = vih;
    }

    public Boolean getThrombose() {
        return this.thrombose;
    }

    public FicheFacteurXIII thrombose(Boolean thrombose) {
        this.setThrombose(thrombose);
        return this;
    }

    public void setThrombose(Boolean thrombose) {
        this.thrombose = thrombose;
    }

    public Boolean getReactionAllergique() {
        return this.reactionAllergique;
    }

    public FicheFacteurXIII reactionAllergique(Boolean reactionAllergique) {
        this.setReactionAllergique(reactionAllergique);
        return this;
    }

    public void setReactionAllergique(Boolean reactionAllergique) {
        this.reactionAllergique = reactionAllergique;
    }

    public Boolean getAlloimmunisation() {
        return this.alloimmunisation;
    }

    public FicheFacteurXIII alloimmunisation(Boolean alloimmunisation) {
        this.setAlloimmunisation(alloimmunisation);
        return this;
    }

    public void setAlloimmunisation(Boolean alloimmunisation) {
        this.alloimmunisation = alloimmunisation;
    }

    public String getAutresComplications() {
        return this.autresComplications;
    }

    public FicheFacteurXIII autresComplications(String autresComplications) {
        this.setAutresComplications(autresComplications);
        return this;
    }

    public void setAutresComplications(String autresComplications) {
        this.autresComplications = autresComplications;
    }

    public OuiNonNP getAdherenceTherapeutique() {
        return this.adherenceTherapeutique;
    }

    public FicheFacteurXIII adherenceTherapeutique(OuiNonNP adherenceTherapeutique) {
        this.setAdherenceTherapeutique(adherenceTherapeutique);
        return this;
    }

    public void setAdherenceTherapeutique(OuiNonNP adherenceTherapeutique) {
        this.adherenceTherapeutique = adherenceTherapeutique;
    }

    public String getCauseNonAdherence() {
        return this.causeNonAdherence;
    }

    public FicheFacteurXIII causeNonAdherence(String causeNonAdherence) {
        this.setCauseNonAdherence(causeNonAdherence);
        return this;
    }

    public void setCauseNonAdherence(String causeNonAdherence) {
        this.causeNonAdherence = causeNonAdherence;
    }

    public VieSociale getVieSociale() {
        return this.vieSociale;
    }

    public FicheFacteurXIII vieSociale(VieSociale vieSociale) {
        this.setVieSociale(vieSociale);
        return this;
    }

    public void setVieSociale(VieSociale vieSociale) {
        this.vieSociale = vieSociale;
    }

    public OuiNonNP getMarie() {
        return this.marie;
    }

    public FicheFacteurXIII marie(OuiNonNP marie) {
        this.setMarie(marie);
        return this;
    }

    public void setMarie(OuiNonNP marie) {
        this.marie = marie;
    }

    public OuiNonNP getEnfants() {
        return this.enfants;
    }

    public FicheFacteurXIII enfants(OuiNonNP enfants) {
        this.setEnfants(enfants);
        return this;
    }

    public void setEnfants(OuiNonNP enfants) {
        this.enfants = enfants;
    }

    public OuiNonNP getActivitePhysique() {
        return this.activitePhysique;
    }

    public FicheFacteurXIII activitePhysique(OuiNonNP activitePhysique) {
        this.setActivitePhysique(activitePhysique);
        return this;
    }

    public void setActivitePhysique(OuiNonNP activitePhysique) {
        this.activitePhysique = activitePhysique;
    }

    public String getTypeActivitePhysique() {
        return this.typeActivitePhysique;
    }

    public FicheFacteurXIII typeActivitePhysique(String typeActivitePhysique) {
        this.setTypeActivitePhysique(typeActivitePhysique);
        return this;
    }

    public void setTypeActivitePhysique(String typeActivitePhysique) {
        this.typeActivitePhysique = typeActivitePhysique;
    }

    public OuiNonNP getSequelles() {
        return this.sequelles;
    }

    public FicheFacteurXIII sequelles(OuiNonNP sequelles) {
        this.setSequelles(sequelles);
        return this;
    }

    public void setSequelles(OuiNonNP sequelles) {
        this.sequelles = sequelles;
    }

    public String getTypeSequelles() {
        return this.typeSequelles;
    }

    public FicheFacteurXIII typeSequelles(String typeSequelles) {
        this.setTypeSequelles(typeSequelles);
        return this;
    }

    public void setTypeSequelles(String typeSequelles) {
        this.typeSequelles = typeSequelles;
    }

    public OuiNonNP getPatientDecede() {
        return this.patientDecede;
    }

    public FicheFacteurXIII patientDecede(OuiNonNP patientDecede) {
        this.setPatientDecede(patientDecede);
        return this;
    }

    public void setPatientDecede(OuiNonNP patientDecede) {
        this.patientDecede = patientDecede;
    }

    public LocalDate getDateDeces() {
        return this.dateDeces;
    }

    public FicheFacteurXIII dateDeces(LocalDate dateDeces) {
        this.setDateDeces(dateDeces);
        return this;
    }

    public void setDateDeces(LocalDate dateDeces) {
        this.dateDeces = dateDeces;
    }

    public Integer getAgeDeces() {
        return this.ageDeces;
    }

    public FicheFacteurXIII ageDeces(Integer ageDeces) {
        this.setAgeDeces(ageDeces);
        return this;
    }

    public void setAgeDeces(Integer ageDeces) {
        this.ageDeces = ageDeces;
    }

    public Boolean getCauseDecesSNC() {
        return this.causeDecesSNC;
    }

    public FicheFacteurXIII causeDecesSNC(Boolean causeDecesSNC) {
        this.setCauseDecesSNC(causeDecesSNC);
        return this;
    }

    public void setCauseDecesSNC(Boolean causeDecesSNC) {
        this.causeDecesSNC = causeDecesSNC;
    }

    public Boolean getCauseDecesAutreHemorragie() {
        return this.causeDecesAutreHemorragie;
    }

    public FicheFacteurXIII causeDecesAutreHemorragie(Boolean causeDecesAutreHemorragie) {
        this.setCauseDecesAutreHemorragie(causeDecesAutreHemorragie);
        return this;
    }

    public void setCauseDecesAutreHemorragie(Boolean causeDecesAutreHemorragie) {
        this.causeDecesAutreHemorragie = causeDecesAutreHemorragie;
    }

    public Boolean getCauseDecesVIH() {
        return this.causeDecesVIH;
    }

    public FicheFacteurXIII causeDecesVIH(Boolean causeDecesVIH) {
        this.setCauseDecesVIH(causeDecesVIH);
        return this;
    }

    public void setCauseDecesVIH(Boolean causeDecesVIH) {
        this.causeDecesVIH = causeDecesVIH;
    }

    public Boolean getCauseDecesAutreInfection() {
        return this.causeDecesAutreInfection;
    }

    public FicheFacteurXIII causeDecesAutreInfection(Boolean causeDecesAutreInfection) {
        this.setCauseDecesAutreInfection(causeDecesAutreInfection);
        return this;
    }

    public void setCauseDecesAutreInfection(Boolean causeDecesAutreInfection) {
        this.causeDecesAutreInfection = causeDecesAutreInfection;
    }

    public Boolean getCauseDecesThromboembolique() {
        return this.causeDecesThromboembolique;
    }

    public FicheFacteurXIII causeDecesThromboembolique(Boolean causeDecesThromboembolique) {
        this.setCauseDecesThromboembolique(causeDecesThromboembolique);
        return this;
    }

    public void setCauseDecesThromboembolique(Boolean causeDecesThromboembolique) {
        this.causeDecesThromboembolique = causeDecesThromboembolique;
    }

    public Boolean getCauseDecesCancer() {
        return this.causeDecesCancer;
    }

    public FicheFacteurXIII causeDecesCancer(Boolean causeDecesCancer) {
        this.setCauseDecesCancer(causeDecesCancer);
        return this;
    }

    public void setCauseDecesCancer(Boolean causeDecesCancer) {
        this.causeDecesCancer = causeDecesCancer;
    }

    public Boolean getCauseDecesCovid19() {
        return this.causeDecesCovid19;
    }

    public FicheFacteurXIII causeDecesCovid19(Boolean causeDecesCovid19) {
        this.setCauseDecesCovid19(causeDecesCovid19);
        return this;
    }

    public void setCauseDecesCovid19(Boolean causeDecesCovid19) {
        this.causeDecesCovid19 = causeDecesCovid19;
    }

    public Boolean getCauseDecesInconnue() {
        return this.causeDecesInconnue;
    }

    public FicheFacteurXIII causeDecesInconnue(Boolean causeDecesInconnue) {
        this.setCauseDecesInconnue(causeDecesInconnue);
        return this;
    }

    public void setCauseDecesInconnue(Boolean causeDecesInconnue) {
        this.causeDecesInconnue = causeDecesInconnue;
    }

    public String getAutreCauseDeces() {
        return this.autreCauseDeces;
    }

    public FicheFacteurXIII autreCauseDeces(String autreCauseDeces) {
        this.setAutreCauseDeces(autreCauseDeces);
        return this;
    }

    public void setAutreCauseDeces(String autreCauseDeces) {
        this.autreCauseDeces = autreCauseDeces;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheFacteurXIII)) {
            return false;
        }
        return getId() != null && getId().equals(((FicheFacteurXIII) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheFacteurXIII{" +
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
            ", explorationSyndromeHemorragique='" + getExplorationSyndromeHemorragique() + "'" +
            ", hemorragieChuteCordon='" + getHemorragieChuteCordon() + "'" +
            ", metroragieMenarche='" + getMetroragieMenarche() + "'" +
            ", hemorragieCerebraleNN='" + getHemorragieCerebraleNN() + "'" +
            ", hemorragiePostPartum='" + getHemorragiePostPartum() + "'" +
            ", autresCirconstances='" + getAutresCirconstances() + "'" +
            ", datePremierSigneCliniqueEvocateurJour=" + getDatePremierSigneCliniqueEvocateurJour() +
            ", datePremierSigneCliniqueEvocateurMois=" + getDatePremierSigneCliniqueEvocateurMois() +
            ", datePremierSigneCliniqueEvocateurAnnee=" + getDatePremierSigneCliniqueEvocateurAnnee() +
            ", ageDiagnosticJour=" + getAgeDiagnosticJour() +
            ", ageDiagnosticMois=" + getAgeDiagnosticMois() +
            ", ageDiagnosticAnnee=" + getAgeDiagnosticAnnee() +
            ", hb=" + getHb() +
            ", ht=" + getHt() +
            ", plaquettes=" + getPlaquettes() +
            ", tp=" + getTp() +
            ", tpMT=" + getTpMT() +
            ", tca=" + getTca() +
            ", tcaMT=" + getTcaMT() +
            ", fibrinogene=" + getFibrinogene() +
            ", ts='" + getTs() + "'" +
            ", toPfa200='" + getToPfa200() + "'" +
            ", colEpi=" + getColEpi() +
            ", colAdp=" + getColAdp() +
            ", facteurXIII=" + getFacteurXIII() +
            ", techniqueChromogenique='" + getTechniqueChromogenique() + "'" +
            ", tauxChromogenique=" + getTauxChromogenique() +
            ", techniqueSolubiliteCaillot='" + getTechniqueSolubiliteCaillot() + "'" +
            ", tauxSolubiliteCaillot=" + getTauxSolubiliteCaillot() +
            ", confirmationMoleculaire='" + getConfirmationMoleculaire() + "'" +
            ", mutationPresente='" + getMutationPresente() + "'" +
            ", mutationNom='" + getMutationNom() + "'" +
            ", mutationType='" + getMutationType() + "'" +
            ", mutation2Nom='" + getMutation2Nom() + "'" +
            ", saignementChuteCordon='" + getSaignementChuteCordon() + "'" +
            ", cicatricesKeloides='" + getCicatricesKeloides() + "'" +
            ", retardCicatrisation='" + getRetardCicatrisation() + "'" +
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
            ", graveDeglobulisation='" + getGraveDeglobulisation() + "'" +
            ", graveEtatDeChoc='" + getGraveEtatDeChoc() + "'" +
            ", graveParLocalisation='" + getGraveParLocalisation() + "'" +
            ", siegeGraviteLocalisation='" + getSiegeGraviteLocalisation() + "'" +
            ", chirurgiePatientPrepare='" + getChirurgiePatientPrepare() + "'" +
            ", chirurgieSuiteHemorragique='" + getChirurgieSuiteHemorragique() + "'" +
            ", circoncisionPatientPrepare='" + getCirconcisionPatientPrepare() + "'" +
            ", circoncisionSuiteHemorragique='" + getCirconcisionSuiteHemorragique() + "'" +
            ", ageCirconcision=" + getAgeCirconcision() +
            ", concentreFacteurXIII='" + getConcentreFacteurXIII() + "'" +
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
            ", titreInhibiteurNeutralisant=" + getTitreInhibiteurNeutralisant() +
            ", inhibiteurNonNeutralisant='" + getInhibiteurNonNeutralisant() + "'" +
            ", infectionsVirales='" + getInfectionsVirales() + "'" +
            ", hepatiteB='" + getHepatiteB() + "'" +
            ", hepatiteC='" + getHepatiteC() + "'" +
            ", vih='" + getVih() + "'" +
            ", thrombose='" + getThrombose() + "'" +
            ", reactionAllergique='" + getReactionAllergique() + "'" +
            ", alloimmunisation='" + getAlloimmunisation() + "'" +
            ", autresComplications='" + getAutresComplications() + "'" +
            ", adherenceTherapeutique='" + getAdherenceTherapeutique() + "'" +
            ", causeNonAdherence='" + getCauseNonAdherence() + "'" +
            ", vieSociale='" + getVieSociale() + "'" +
            ", marie='" + getMarie() + "'" +
            ", enfants='" + getEnfants() + "'" +
            ", activitePhysique='" + getActivitePhysique() + "'" +
            ", typeActivitePhysique='" + getTypeActivitePhysique() + "'" +
            ", sequelles='" + getSequelles() + "'" +
            ", typeSequelles='" + getTypeSequelles() + "'" +
            ", patientDecede='" + getPatientDecede() + "'" +
            ", dateDeces='" + getDateDeces() + "'" +
            ", ageDeces=" + getAgeDeces() +
            ", causeDecesSNC='" + getCauseDecesSNC() + "'" +
            ", causeDecesAutreHemorragie='" + getCauseDecesAutreHemorragie() + "'" +
            ", causeDecesVIH='" + getCauseDecesVIH() + "'" +
            ", causeDecesAutreInfection='" + getCauseDecesAutreInfection() + "'" +
            ", causeDecesThromboembolique='" + getCauseDecesThromboembolique() + "'" +
            ", causeDecesCancer='" + getCauseDecesCancer() + "'" +
            ", causeDecesCovid19='" + getCauseDecesCovid19() + "'" +
            ", causeDecesInconnue='" + getCauseDecesInconnue() + "'" +
            ", autreCauseDeces='" + getAutreCauseDeces() + "'" +
            "}";
    }
}

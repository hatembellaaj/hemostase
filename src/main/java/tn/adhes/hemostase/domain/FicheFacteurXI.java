package tn.adhes.hemostase.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import tn.adhes.hemostase.domain.enumeration.CauseDeces;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.Region;
import tn.adhes.hemostase.domain.enumeration.ServiceCliniqueType;
import tn.adhes.hemostase.domain.enumeration.StatutSocial;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypeZygote;

/**
 * A FicheFacteurXI.
 */
@Entity
@Table(name = "fiche_facteur_xi")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheFacteurXI implements Serializable {

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
    private OuiNonNP enqueteFamiliale;

    @Enumerated(EnumType.STRING)
    @Column(name = "syndrome_hemorragique")
    private OuiNonNP syndromeHemorragique;

    @Column(name = "precision_syndrome_hemorragique")
    private String precisionSyndromeHemorragique;

    @Enumerated(EnumType.STRING)
    @Column(name = "decouverte_fortuite")
    private OuiNonNP decouverteFortuite;

    @Column(name = "precision_decouverte_fortuite")
    private String precisionDecouverteFortuite;

    @Enumerated(EnumType.STRING)
    @Column(name = "jamais_saigne")
    private OuiNonNP jamaisSaigne;

    @Column(name = "age_premier_signe_clinique_jours")
    private Integer agePremierSigneCliniqueJours;

    @Column(name = "age_premier_signe_clinique_mois")
    private Integer agePremierSigneCliniqueMois;

    @Column(name = "age_premier_signe_clinique_annees")
    private Integer agePremierSigneCliniqueAnnees;

    @Column(name = "symptome_ecchymose")
    private Boolean symptomeEcchymose;

    @Column(name = "symptome_epistaxis")
    private Boolean symptomeEpistaxis;

    @Column(name = "symptome_gingivorragies")
    private Boolean symptomeGingivorragies;

    @Column(name = "symptome_metrorragies")
    private Boolean symptomeMetrorragies;

    @Column(name = "symptome_melena")
    private Boolean symptomeMelena;

    @Column(name = "symptome_hemoptysie")
    private Boolean symptomeHemoptysie;

    @Column(name = "symptome_hemorragie_post_amygdalectomie")
    private Boolean symptomeHemorragiePostAmygdalectomie;

    @Column(name = "symptome_hemorragie_post_avulsion_dentaire")
    private Boolean symptomeHemorragiePostAvulsionDentaire;

    @Column(name = "symptome_hemorragie_chute_cordon")
    private Boolean symptomeHemorragieChuteCordon;

    @Column(name = "symptome_autre")
    private Boolean symptomeAutre;

    @Column(name = "symptome_autre_description")
    private String symptomeAutreDescription;

    @Column(name = "date_premiere_consultation")
    private LocalDate datePremiereConsultation;

    @Column(name = "age_premiere_consultation_jours")
    private Integer agePremiereConsultationJours;

    @Column(name = "age_premiere_consultation_mois")
    private Integer agePremiereConsultationMois;

    @Column(name = "age_premiere_consultation_annees")
    private Integer agePremiereConsultationAnnees;

    @Column(name = "age_diagnostic_jours")
    private Integer ageDiagnosticJours;

    @Column(name = "age_diagnostic_mois")
    private Integer ageDiagnosticMois;

    @Column(name = "age_diagnostic_annees")
    private Integer ageDiagnosticAnnees;

    @Column(name = "hb")
    private Double hb;

    @Column(name = "hte")
    private Double hte;

    @Column(name = "plaquettes")
    private Integer plaquettes;

    @Column(name = "pfa_col_epi")
    private Double pfaColEpi;

    @Column(name = "pfa_col_adp")
    private Double pfaColADP;

    @Column(name = "tp")
    private Double tp;

    @Column(name = "tp_m_plus_t")
    private Double tpMPlusT;

    @Column(name = "fibrinogene")
    private Double fibrinogene;

    @Column(name = "tca_mt")
    private Double tcaMT;

    @Column(name = "tca_m_plus_t")
    private Double tcaMPlusT;

    @Column(name = "tca_temoin")
    private Double tcaTemoin;

    @Column(name = "facteur_xi")
    private Double facteurXI;

    @Column(name = "autres_facteurs_dosage_description")
    private String autresFacteursDosageDescription;

    @Column(name = "autres_facteurs_taux_1")
    private Double autresFacteursTaux1;

    @Column(name = "autres_facteurs_taux_2")
    private Double autresFacteursTaux2;

    @Column(name = "autres_facteurs_taux_3")
    private Double autresFacteursTaux3;

    @Enumerated(EnumType.STRING)
    @Column(name = "confirmation_moleculaire")
    private FaiteNonFaite confirmationMoleculaire;

    @Column(name = "mutation_presente")
    private Boolean mutationPresente;

    @Column(name = "mutation_nom")
    private String mutationNom;

    @Enumerated(EnumType.STRING)
    @Column(name = "mutation_zygote")
    private TypeZygote mutationZygote;

    @Column(name = "mutation_2_nom")
    private String mutation2Nom;

    @Enumerated(EnumType.STRING)
    @Column(name = "serologie_vhb")
    private OuiNonNP serologieVHB;

    @Enumerated(EnumType.STRING)
    @Column(name = "serologie_vhc")
    private OuiNonNP serologieVHC;

    @Enumerated(EnumType.STRING)
    @Column(name = "serologie_vih")
    private OuiNonNP serologieVIH;

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

    @Column(name = "hemorragie_post_traumatique")
    private Boolean hemorragiePostTraumatique;

    @Column(name = "frequence_hemorragie_post_traumatique")
    private Integer frequenceHemorragiePostTraumatique;

    @Column(name = "hematome_sous_cutane")
    private Boolean hematomeSousCutane;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_hematome_sous_cutane")
    private TypeManifestation typeHematomeSousCutane;

    @Column(name = "frequence_hematome_sous_cutane")
    private Integer frequenceHematomeSousCutane;

    @Column(name = "hematome_musculaire")
    private Boolean hematomeMusculaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_hematome_musculaire")
    private TypeManifestation typeHematomeMusculaire;

    @Column(name = "frequence_hematome_musculaire")
    private Integer frequenceHematomeMusculaire;

    @Column(name = "hematome_tissus_mous_profonds")
    private Boolean hematomeTissusMousProfonds;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_hematome_tissus_mous_profonds")
    private TypeManifestation typeHematomeTissusMousProfonds;

    @Column(name = "frequence_hematome_tissus_mous_profonds")
    private Integer frequenceHematomeTissusMousProfonds;

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

    @Column(name = "saignement_chute_cordon")
    private Boolean saignementChuteCordon;

    @Column(name = "frequence_saignement_chute_cordon")
    private Integer frequenceSaignementChuteCordon;

    @Column(name = "retard_cicatrisation_plaies")
    private Boolean retardCicatrisationPlaies;

    @Column(name = "frequence_retard_cicatrisation_plaies")
    private Integer frequenceRetardCicatrisationPlaies;

    @Column(name = "autres_manifestations")
    private Boolean autresManifestations;

    @Column(name = "fausses_couches_spontanees_recurrence")
    private Boolean faussesCouchesSpontaneesRecurrence;

    @Column(name = "frequence_fausses_couches")
    private Integer frequenceFaussesCouches;

    @Column(name = "accidents_thrombotiques")
    private Boolean accidentsThrombotiques;

    @Column(name = "thrombose_veineuse")
    private Boolean thromboseVeineuse;

    @Column(name = "thrombose_arterielle")
    private Boolean thromboseArterielle;

    @Column(name = "frequence_accidents_thrombotiques")
    private Integer frequenceAccidentsThrombotiques;

    @Column(name = "hemoperitoine")
    private Boolean hemoperitoine;

    @Column(name = "frequence_hemoperitoine")
    private Integer frequenceHemoperitoine;

    @Column(name = "rupture_kystes_hemorragiques")
    private Boolean ruptureKystesHemorragiques;

    @Column(name = "frequence_rupture_kystes")
    private Integer frequenceRuptureKystes;

    @Column(name = "hemorragie_grave")
    private Boolean hemorragieGrave;

    @Column(name = "frequence_hemorragie_grave")
    private Integer frequenceHemorragieGrave;

    @Column(name = "gravite_deglobulisation")
    private Boolean graviteDeglobulisation;

    @Column(name = "gravite_etat_de_choc")
    private Boolean graviteEtatDeChoc;

    @Column(name = "gravite_par_localisation")
    private Boolean graviteParLocalisation;

    @Size(max = 255)
    @Column(name = "siege_gravite_localisation", length = 255)
    private String siegeGraviteLocalisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "chirurgie_prepare")
    private OuiNonNP chirurgiePrepare;

    @Enumerated(EnumType.STRING)
    @Column(name = "chirurgie_suite_hemorragique")
    private OuiNonNP chirurgieSuiteHemorragique;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision_prepare")
    private OuiNonNP circoncisionPrepare;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision_suite_hemorragique")
    private OuiNonNP circoncisionSuiteHemorragique;

    @Column(name = "age_circoncision")
    private Integer ageCirconcision;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_pfc")
    private OuiNonNP traitementPFC;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_cryoprecipite")
    private OuiNonNP traitementCryoprecipite;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_concentre_facteur_xi")
    private OuiNonNP traitementConcentreFacteurXI;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_culots_plaquettaires")
    private OuiNonNP traitementCulotsPlaquettaires;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_dicynone")
    private OuiNonNP traitementDicynone;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_exacyl")
    private OuiNonNP traitementExacyl;

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
    @Column(name = "allo_immunisation")
    private OuiNonNP alloImmunisation;

    @Enumerated(EnumType.STRING)
    @Column(name = "inhibiteur_neutralisant")
    private OuiNonNP inhibiteurNeutralisant;

    @Column(name = "titre_inhibiteur_ub")
    private Double titreInhibiteurUB;

    @Enumerated(EnumType.STRING)
    @Column(name = "inhibiteur_non_neutralisant")
    private OuiNonNP inhibiteurNonNeutralisant;

    @Enumerated(EnumType.STRING)
    @Column(name = "infection_vih")
    private OuiNonNP infectionVIH;

    @Enumerated(EnumType.STRING)
    @Column(name = "infection_vhc")
    private OuiNonNP infectionVHC;

    @Enumerated(EnumType.STRING)
    @Column(name = "infection_vhb")
    private OuiNonNP infectionVHB;

    @Column(name = "thrombose")
    private Boolean thrombose;

    @Column(name = "reaction_allergique")
    private Boolean reactionAllergique;

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
    @Column(name = "statut_social")
    private StatutSocial statutSocial;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "cause_deces")
    private CauseDeces causeDeces;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FicheFacteurXI id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDossierNumber() {
        return this.dossierNumber;
    }

    public FicheFacteurXI dossierNumber(String dossierNumber) {
        this.setDossierNumber(dossierNumber);
        return this;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return this.ordreNumber;
    }

    public FicheFacteurXI ordreNumber(String ordreNumber) {
        this.setOrdreNumber(ordreNumber);
        return this;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return this.indexNumber;
    }

    public FicheFacteurXI indexNumber(String indexNumber) {
        this.setIndexNumber(indexNumber);
        return this;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public FicheFacteurXI prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public FicheFacteurXI nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomJeuneFille() {
        return this.nomJeuneFille;
    }

    public FicheFacteurXI nomJeuneFille(String nomJeuneFille) {
        this.setNomJeuneFille(nomJeuneFille);
        return this;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public String getProfession() {
        return this.profession;
    }

    public FicheFacteurXI profession(String profession) {
        this.setProfession(profession);
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public CivilStatus getEtatCivil() {
        return this.etatCivil;
    }

    public FicheFacteurXI etatCivil(CivilStatus etatCivil) {
        this.setEtatCivil(etatCivil);
        return this;
    }

    public void setEtatCivil(CivilStatus etatCivil) {
        this.etatCivil = etatCivil;
    }

    public ServiceCliniqueType getServiceClinique() {
        return this.serviceClinique;
    }

    public FicheFacteurXI serviceClinique(ServiceCliniqueType serviceClinique) {
        this.setServiceClinique(serviceClinique);
        return this;
    }

    public void setServiceClinique(ServiceCliniqueType serviceClinique) {
        this.serviceClinique = serviceClinique;
    }

    public String getAutreServiceClinique() {
        return this.autreServiceClinique;
    }

    public FicheFacteurXI autreServiceClinique(String autreServiceClinique) {
        this.setAutreServiceClinique(autreServiceClinique);
        return this;
    }

    public void setAutreServiceClinique(String autreServiceClinique) {
        this.autreServiceClinique = autreServiceClinique;
    }

    public CouvertureSociale getCouvertureSociale() {
        return this.couvertureSociale;
    }

    public FicheFacteurXI couvertureSociale(CouvertureSociale couvertureSociale) {
        this.setCouvertureSociale(couvertureSociale);
        return this;
    }

    public void setCouvertureSociale(CouvertureSociale couvertureSociale) {
        this.couvertureSociale = couvertureSociale;
    }

    public Gender getSexe() {
        return this.sexe;
    }

    public FicheFacteurXI sexe(Gender sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Gender sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public FicheFacteurXI dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Integer getAgeActuel() {
        return this.ageActuel;
    }

    public FicheFacteurXI ageActuel(Integer ageActuel) {
        this.setAgeActuel(ageActuel);
        return this;
    }

    public void setAgeActuel(Integer ageActuel) {
        this.ageActuel = ageActuel;
    }

    public Region getOrigine() {
        return this.origine;
    }

    public FicheFacteurXI origine(Region origine) {
        this.setOrigine(origine);
        return this;
    }

    public void setOrigine(Region origine) {
        this.origine = origine;
    }

    public String getAutreOrigine() {
        return this.autreOrigine;
    }

    public FicheFacteurXI autreOrigine(String autreOrigine) {
        this.setAutreOrigine(autreOrigine);
        return this;
    }

    public void setAutreOrigine(String autreOrigine) {
        this.autreOrigine = autreOrigine;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public FicheFacteurXI adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public FicheFacteurXI telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrenomPere() {
        return this.prenomPere;
    }

    public FicheFacteurXI prenomPere(String prenomPere) {
        this.setPrenomPere(prenomPere);
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getProfessionPere() {
        return this.professionPere;
    }

    public FicheFacteurXI professionPere(String professionPere) {
        this.setProfessionPere(professionPere);
        return this;
    }

    public void setProfessionPere(String professionPere) {
        this.professionPere = professionPere;
    }

    public String getNomPrenomMere() {
        return this.nomPrenomMere;
    }

    public FicheFacteurXI nomPrenomMere(String nomPrenomMere) {
        this.setNomPrenomMere(nomPrenomMere);
        return this;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }

    public String getProfessionMere() {
        return this.professionMere;
    }

    public FicheFacteurXI professionMere(String professionMere) {
        this.setProfessionMere(professionMere);
        return this;
    }

    public void setProfessionMere(String professionMere) {
        this.professionMere = professionMere;
    }

    public String getMedecinTraitant() {
        return this.medecinTraitant;
    }

    public FicheFacteurXI medecinTraitant(String medecinTraitant) {
        this.setMedecinTraitant(medecinTraitant);
        return this;
    }

    public void setMedecinTraitant(String medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }

    public String getServiceOrigine() {
        return this.serviceOrigine;
    }

    public FicheFacteurXI serviceOrigine(String serviceOrigine) {
        this.setServiceOrigine(serviceOrigine);
        return this;
    }

    public void setServiceOrigine(String serviceOrigine) {
        this.serviceOrigine = serviceOrigine;
    }

    public String getBiologisteResponsableDg() {
        return this.biologisteResponsableDg;
    }

    public FicheFacteurXI biologisteResponsableDg(String biologisteResponsableDg) {
        this.setBiologisteResponsableDg(biologisteResponsableDg);
        return this;
    }

    public void setBiologisteResponsableDg(String biologisteResponsableDg) {
        this.biologisteResponsableDg = biologisteResponsableDg;
    }

    public DiagnosticType getDiagnostic() {
        return this.diagnostic;
    }

    public FicheFacteurXI diagnostic(DiagnosticType diagnostic) {
        this.setDiagnostic(diagnostic);
        return this;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return this.autreDiagnostic;
    }

    public FicheFacteurXI autreDiagnostic(String autreDiagnostic) {
        this.setAutreDiagnostic(autreDiagnostic);
        return this;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public Integer getAnneeDiagnostic() {
        return this.anneeDiagnostic;
    }

    public FicheFacteurXI anneeDiagnostic(Integer anneeDiagnostic) {
        this.setAnneeDiagnostic(anneeDiagnostic);
        return this;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public OuiNonNP getConsentementRegistre() {
        return this.consentementRegistre;
    }

    public FicheFacteurXI consentementRegistre(OuiNonNP consentementRegistre) {
        this.setConsentementRegistre(consentementRegistre);
        return this;
    }

    public void setConsentementRegistre(OuiNonNP consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return this.dateEnregistrementRegistre;
    }

    public FicheFacteurXI dateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.setDateEnregistrementRegistre(dateEnregistrementRegistre);
        return this;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
    }

    public OuiNonNP getParentsConsanguins() {
        return this.parentsConsanguins;
    }

    public FicheFacteurXI parentsConsanguins(OuiNonNP parentsConsanguins) {
        this.setParentsConsanguins(parentsConsanguins);
        return this;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public DegreParente getDegreParenteConsanguins() {
        return this.degreParenteConsanguins;
    }

    public FicheFacteurXI degreParenteConsanguins(DegreParente degreParenteConsanguins) {
        this.setDegreParenteConsanguins(degreParenteConsanguins);
        return this;
    }

    public void setDegreParenteConsanguins(DegreParente degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public OuiNonNP getCasSimilaires() {
        return this.casSimilaires;
    }

    public FicheFacteurXI casSimilaires(OuiNonNP casSimilaires) {
        this.setCasSimilaires(casSimilaires);
        return this;
    }

    public void setCasSimilaires(OuiNonNP casSimilaires) {
        this.casSimilaires = casSimilaires;
    }

    public Integer getNombreCas() {
        return this.nombreCas;
    }

    public FicheFacteurXI nombreCas(Integer nombreCas) {
        this.setNombreCas(nombreCas);
        return this;
    }

    public void setNombreCas(Integer nombreCas) {
        this.nombreCas = nombreCas;
    }

    public Integer getCas1Numero() {
        return this.cas1Numero;
    }

    public FicheFacteurXI cas1Numero(Integer cas1Numero) {
        this.setCas1Numero(cas1Numero);
        return this;
    }

    public void setCas1Numero(Integer cas1Numero) {
        this.cas1Numero = cas1Numero;
    }

    public LienParente getLienParenteCas1() {
        return this.lienParenteCas1;
    }

    public FicheFacteurXI lienParenteCas1(LienParente lienParenteCas1) {
        this.setLienParenteCas1(lienParenteCas1);
        return this;
    }

    public void setLienParenteCas1(LienParente lienParenteCas1) {
        this.lienParenteCas1 = lienParenteCas1;
    }

    public String getDegreParenteCas1() {
        return this.degreParenteCas1;
    }

    public FicheFacteurXI degreParenteCas1(String degreParenteCas1) {
        this.setDegreParenteCas1(degreParenteCas1);
        return this;
    }

    public void setDegreParenteCas1(String degreParenteCas1) {
        this.degreParenteCas1 = degreParenteCas1;
    }

    public String getTypeHemorragieCas1() {
        return this.typeHemorragieCas1;
    }

    public FicheFacteurXI typeHemorragieCas1(String typeHemorragieCas1) {
        this.setTypeHemorragieCas1(typeHemorragieCas1);
        return this;
    }

    public void setTypeHemorragieCas1(String typeHemorragieCas1) {
        this.typeHemorragieCas1 = typeHemorragieCas1;
    }

    public Integer getCas2Numero() {
        return this.cas2Numero;
    }

    public FicheFacteurXI cas2Numero(Integer cas2Numero) {
        this.setCas2Numero(cas2Numero);
        return this;
    }

    public void setCas2Numero(Integer cas2Numero) {
        this.cas2Numero = cas2Numero;
    }

    public LienParente getLienParenteCas2() {
        return this.lienParenteCas2;
    }

    public FicheFacteurXI lienParenteCas2(LienParente lienParenteCas2) {
        this.setLienParenteCas2(lienParenteCas2);
        return this;
    }

    public void setLienParenteCas2(LienParente lienParenteCas2) {
        this.lienParenteCas2 = lienParenteCas2;
    }

    public String getDegreParenteCas2() {
        return this.degreParenteCas2;
    }

    public FicheFacteurXI degreParenteCas2(String degreParenteCas2) {
        this.setDegreParenteCas2(degreParenteCas2);
        return this;
    }

    public void setDegreParenteCas2(String degreParenteCas2) {
        this.degreParenteCas2 = degreParenteCas2;
    }

    public String getTypeHemorragieCas2() {
        return this.typeHemorragieCas2;
    }

    public FicheFacteurXI typeHemorragieCas2(String typeHemorragieCas2) {
        this.setTypeHemorragieCas2(typeHemorragieCas2);
        return this;
    }

    public void setTypeHemorragieCas2(String typeHemorragieCas2) {
        this.typeHemorragieCas2 = typeHemorragieCas2;
    }

    public Integer getCas3Numero() {
        return this.cas3Numero;
    }

    public FicheFacteurXI cas3Numero(Integer cas3Numero) {
        this.setCas3Numero(cas3Numero);
        return this;
    }

    public void setCas3Numero(Integer cas3Numero) {
        this.cas3Numero = cas3Numero;
    }

    public LienParente getLienParenteCas3() {
        return this.lienParenteCas3;
    }

    public FicheFacteurXI lienParenteCas3(LienParente lienParenteCas3) {
        this.setLienParenteCas3(lienParenteCas3);
        return this;
    }

    public void setLienParenteCas3(LienParente lienParenteCas3) {
        this.lienParenteCas3 = lienParenteCas3;
    }

    public String getDegreParenteCas3() {
        return this.degreParenteCas3;
    }

    public FicheFacteurXI degreParenteCas3(String degreParenteCas3) {
        this.setDegreParenteCas3(degreParenteCas3);
        return this;
    }

    public void setDegreParenteCas3(String degreParenteCas3) {
        this.degreParenteCas3 = degreParenteCas3;
    }

    public String getTypeHemorragieCas3() {
        return this.typeHemorragieCas3;
    }

    public FicheFacteurXI typeHemorragieCas3(String typeHemorragieCas3) {
        this.setTypeHemorragieCas3(typeHemorragieCas3);
        return this;
    }

    public void setTypeHemorragieCas3(String typeHemorragieCas3) {
        this.typeHemorragieCas3 = typeHemorragieCas3;
    }

    public OuiNonNP getDecesSyndromeHemorragique() {
        return this.decesSyndromeHemorragique;
    }

    public FicheFacteurXI decesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.setDecesSyndromeHemorragique(decesSyndromeHemorragique);
        return this;
    }

    public void setDecesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.decesSyndromeHemorragique = decesSyndromeHemorragique;
    }

    public Integer getNombreDeces() {
        return this.nombreDeces;
    }

    public FicheFacteurXI nombreDeces(Integer nombreDeces) {
        this.setNombreDeces(nombreDeces);
        return this;
    }

    public void setNombreDeces(Integer nombreDeces) {
        this.nombreDeces = nombreDeces;
    }

    public Integer getDeces1Numero() {
        return this.deces1Numero;
    }

    public FicheFacteurXI deces1Numero(Integer deces1Numero) {
        this.setDeces1Numero(deces1Numero);
        return this;
    }

    public void setDeces1Numero(Integer deces1Numero) {
        this.deces1Numero = deces1Numero;
    }

    public LienParente getLienParenteDeces1() {
        return this.lienParenteDeces1;
    }

    public FicheFacteurXI lienParenteDeces1(LienParente lienParenteDeces1) {
        this.setLienParenteDeces1(lienParenteDeces1);
        return this;
    }

    public void setLienParenteDeces1(LienParente lienParenteDeces1) {
        this.lienParenteDeces1 = lienParenteDeces1;
    }

    public String getCauseDeces1() {
        return this.causeDeces1;
    }

    public FicheFacteurXI causeDeces1(String causeDeces1) {
        this.setCauseDeces1(causeDeces1);
        return this;
    }

    public void setCauseDeces1(String causeDeces1) {
        this.causeDeces1 = causeDeces1;
    }

    public Integer getDeces2Numero() {
        return this.deces2Numero;
    }

    public FicheFacteurXI deces2Numero(Integer deces2Numero) {
        this.setDeces2Numero(deces2Numero);
        return this;
    }

    public void setDeces2Numero(Integer deces2Numero) {
        this.deces2Numero = deces2Numero;
    }

    public LienParente getLienParenteDeces2() {
        return this.lienParenteDeces2;
    }

    public FicheFacteurXI lienParenteDeces2(LienParente lienParenteDeces2) {
        this.setLienParenteDeces2(lienParenteDeces2);
        return this;
    }

    public void setLienParenteDeces2(LienParente lienParenteDeces2) {
        this.lienParenteDeces2 = lienParenteDeces2;
    }

    public String getCauseDeces2() {
        return this.causeDeces2;
    }

    public FicheFacteurXI causeDeces2(String causeDeces2) {
        this.setCauseDeces2(causeDeces2);
        return this;
    }

    public void setCauseDeces2(String causeDeces2) {
        this.causeDeces2 = causeDeces2;
    }

    public Integer getDeces3Numero() {
        return this.deces3Numero;
    }

    public FicheFacteurXI deces3Numero(Integer deces3Numero) {
        this.setDeces3Numero(deces3Numero);
        return this;
    }

    public void setDeces3Numero(Integer deces3Numero) {
        this.deces3Numero = deces3Numero;
    }

    public LienParente getLienParenteDeces3() {
        return this.lienParenteDeces3;
    }

    public FicheFacteurXI lienParenteDeces3(LienParente lienParenteDeces3) {
        this.setLienParenteDeces3(lienParenteDeces3);
        return this;
    }

    public void setLienParenteDeces3(LienParente lienParenteDeces3) {
        this.lienParenteDeces3 = lienParenteDeces3;
    }

    public String getCauseDeces3() {
        return this.causeDeces3;
    }

    public FicheFacteurXI causeDeces3(String causeDeces3) {
        this.setCauseDeces3(causeDeces3);
        return this;
    }

    public void setCauseDeces3(String causeDeces3) {
        this.causeDeces3 = causeDeces3;
    }

    public OuiNonNP getEnqueteFamiliale() {
        return this.enqueteFamiliale;
    }

    public FicheFacteurXI enqueteFamiliale(OuiNonNP enqueteFamiliale) {
        this.setEnqueteFamiliale(enqueteFamiliale);
        return this;
    }

    public void setEnqueteFamiliale(OuiNonNP enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public OuiNonNP getSyndromeHemorragique() {
        return this.syndromeHemorragique;
    }

    public FicheFacteurXI syndromeHemorragique(OuiNonNP syndromeHemorragique) {
        this.setSyndromeHemorragique(syndromeHemorragique);
        return this;
    }

    public void setSyndromeHemorragique(OuiNonNP syndromeHemorragique) {
        this.syndromeHemorragique = syndromeHemorragique;
    }

    public String getPrecisionSyndromeHemorragique() {
        return this.precisionSyndromeHemorragique;
    }

    public FicheFacteurXI precisionSyndromeHemorragique(String precisionSyndromeHemorragique) {
        this.setPrecisionSyndromeHemorragique(precisionSyndromeHemorragique);
        return this;
    }

    public void setPrecisionSyndromeHemorragique(String precisionSyndromeHemorragique) {
        this.precisionSyndromeHemorragique = precisionSyndromeHemorragique;
    }

    public OuiNonNP getDecouverteFortuite() {
        return this.decouverteFortuite;
    }

    public FicheFacteurXI decouverteFortuite(OuiNonNP decouverteFortuite) {
        this.setDecouverteFortuite(decouverteFortuite);
        return this;
    }

    public void setDecouverteFortuite(OuiNonNP decouverteFortuite) {
        this.decouverteFortuite = decouverteFortuite;
    }

    public String getPrecisionDecouverteFortuite() {
        return this.precisionDecouverteFortuite;
    }

    public FicheFacteurXI precisionDecouverteFortuite(String precisionDecouverteFortuite) {
        this.setPrecisionDecouverteFortuite(precisionDecouverteFortuite);
        return this;
    }

    public void setPrecisionDecouverteFortuite(String precisionDecouverteFortuite) {
        this.precisionDecouverteFortuite = precisionDecouverteFortuite;
    }

    public OuiNonNP getJamaisSaigne() {
        return this.jamaisSaigne;
    }

    public FicheFacteurXI jamaisSaigne(OuiNonNP jamaisSaigne) {
        this.setJamaisSaigne(jamaisSaigne);
        return this;
    }

    public void setJamaisSaigne(OuiNonNP jamaisSaigne) {
        this.jamaisSaigne = jamaisSaigne;
    }

    public Integer getAgePremierSigneCliniqueJours() {
        return this.agePremierSigneCliniqueJours;
    }

    public FicheFacteurXI agePremierSigneCliniqueJours(Integer agePremierSigneCliniqueJours) {
        this.setAgePremierSigneCliniqueJours(agePremierSigneCliniqueJours);
        return this;
    }

    public void setAgePremierSigneCliniqueJours(Integer agePremierSigneCliniqueJours) {
        this.agePremierSigneCliniqueJours = agePremierSigneCliniqueJours;
    }

    public Integer getAgePremierSigneCliniqueMois() {
        return this.agePremierSigneCliniqueMois;
    }

    public FicheFacteurXI agePremierSigneCliniqueMois(Integer agePremierSigneCliniqueMois) {
        this.setAgePremierSigneCliniqueMois(agePremierSigneCliniqueMois);
        return this;
    }

    public void setAgePremierSigneCliniqueMois(Integer agePremierSigneCliniqueMois) {
        this.agePremierSigneCliniqueMois = agePremierSigneCliniqueMois;
    }

    public Integer getAgePremierSigneCliniqueAnnees() {
        return this.agePremierSigneCliniqueAnnees;
    }

    public FicheFacteurXI agePremierSigneCliniqueAnnees(Integer agePremierSigneCliniqueAnnees) {
        this.setAgePremierSigneCliniqueAnnees(agePremierSigneCliniqueAnnees);
        return this;
    }

    public void setAgePremierSigneCliniqueAnnees(Integer agePremierSigneCliniqueAnnees) {
        this.agePremierSigneCliniqueAnnees = agePremierSigneCliniqueAnnees;
    }

    public Boolean getSymptomeEcchymose() {
        return this.symptomeEcchymose;
    }

    public FicheFacteurXI symptomeEcchymose(Boolean symptomeEcchymose) {
        this.setSymptomeEcchymose(symptomeEcchymose);
        return this;
    }

    public void setSymptomeEcchymose(Boolean symptomeEcchymose) {
        this.symptomeEcchymose = symptomeEcchymose;
    }

    public Boolean getSymptomeEpistaxis() {
        return this.symptomeEpistaxis;
    }

    public FicheFacteurXI symptomeEpistaxis(Boolean symptomeEpistaxis) {
        this.setSymptomeEpistaxis(symptomeEpistaxis);
        return this;
    }

    public void setSymptomeEpistaxis(Boolean symptomeEpistaxis) {
        this.symptomeEpistaxis = symptomeEpistaxis;
    }

    public Boolean getSymptomeGingivorragies() {
        return this.symptomeGingivorragies;
    }

    public FicheFacteurXI symptomeGingivorragies(Boolean symptomeGingivorragies) {
        this.setSymptomeGingivorragies(symptomeGingivorragies);
        return this;
    }

    public void setSymptomeGingivorragies(Boolean symptomeGingivorragies) {
        this.symptomeGingivorragies = symptomeGingivorragies;
    }

    public Boolean getSymptomeMetrorragies() {
        return this.symptomeMetrorragies;
    }

    public FicheFacteurXI symptomeMetrorragies(Boolean symptomeMetrorragies) {
        this.setSymptomeMetrorragies(symptomeMetrorragies);
        return this;
    }

    public void setSymptomeMetrorragies(Boolean symptomeMetrorragies) {
        this.symptomeMetrorragies = symptomeMetrorragies;
    }

    public Boolean getSymptomeMelena() {
        return this.symptomeMelena;
    }

    public FicheFacteurXI symptomeMelena(Boolean symptomeMelena) {
        this.setSymptomeMelena(symptomeMelena);
        return this;
    }

    public void setSymptomeMelena(Boolean symptomeMelena) {
        this.symptomeMelena = symptomeMelena;
    }

    public Boolean getSymptomeHemoptysie() {
        return this.symptomeHemoptysie;
    }

    public FicheFacteurXI symptomeHemoptysie(Boolean symptomeHemoptysie) {
        this.setSymptomeHemoptysie(symptomeHemoptysie);
        return this;
    }

    public void setSymptomeHemoptysie(Boolean symptomeHemoptysie) {
        this.symptomeHemoptysie = symptomeHemoptysie;
    }

    public Boolean getSymptomeHemorragiePostAmygdalectomie() {
        return this.symptomeHemorragiePostAmygdalectomie;
    }

    public FicheFacteurXI symptomeHemorragiePostAmygdalectomie(Boolean symptomeHemorragiePostAmygdalectomie) {
        this.setSymptomeHemorragiePostAmygdalectomie(symptomeHemorragiePostAmygdalectomie);
        return this;
    }

    public void setSymptomeHemorragiePostAmygdalectomie(Boolean symptomeHemorragiePostAmygdalectomie) {
        this.symptomeHemorragiePostAmygdalectomie = symptomeHemorragiePostAmygdalectomie;
    }

    public Boolean getSymptomeHemorragiePostAvulsionDentaire() {
        return this.symptomeHemorragiePostAvulsionDentaire;
    }

    public FicheFacteurXI symptomeHemorragiePostAvulsionDentaire(Boolean symptomeHemorragiePostAvulsionDentaire) {
        this.setSymptomeHemorragiePostAvulsionDentaire(symptomeHemorragiePostAvulsionDentaire);
        return this;
    }

    public void setSymptomeHemorragiePostAvulsionDentaire(Boolean symptomeHemorragiePostAvulsionDentaire) {
        this.symptomeHemorragiePostAvulsionDentaire = symptomeHemorragiePostAvulsionDentaire;
    }

    public Boolean getSymptomeHemorragieChuteCordon() {
        return this.symptomeHemorragieChuteCordon;
    }

    public FicheFacteurXI symptomeHemorragieChuteCordon(Boolean symptomeHemorragieChuteCordon) {
        this.setSymptomeHemorragieChuteCordon(symptomeHemorragieChuteCordon);
        return this;
    }

    public void setSymptomeHemorragieChuteCordon(Boolean symptomeHemorragieChuteCordon) {
        this.symptomeHemorragieChuteCordon = symptomeHemorragieChuteCordon;
    }

    public Boolean getSymptomeAutre() {
        return this.symptomeAutre;
    }

    public FicheFacteurXI symptomeAutre(Boolean symptomeAutre) {
        this.setSymptomeAutre(symptomeAutre);
        return this;
    }

    public void setSymptomeAutre(Boolean symptomeAutre) {
        this.symptomeAutre = symptomeAutre;
    }

    public String getSymptomeAutreDescription() {
        return this.symptomeAutreDescription;
    }

    public FicheFacteurXI symptomeAutreDescription(String symptomeAutreDescription) {
        this.setSymptomeAutreDescription(symptomeAutreDescription);
        return this;
    }

    public void setSymptomeAutreDescription(String symptomeAutreDescription) {
        this.symptomeAutreDescription = symptomeAutreDescription;
    }

    public LocalDate getDatePremiereConsultation() {
        return this.datePremiereConsultation;
    }

    public FicheFacteurXI datePremiereConsultation(LocalDate datePremiereConsultation) {
        this.setDatePremiereConsultation(datePremiereConsultation);
        return this;
    }

    public void setDatePremiereConsultation(LocalDate datePremiereConsultation) {
        this.datePremiereConsultation = datePremiereConsultation;
    }

    public Integer getAgePremiereConsultationJours() {
        return this.agePremiereConsultationJours;
    }

    public FicheFacteurXI agePremiereConsultationJours(Integer agePremiereConsultationJours) {
        this.setAgePremiereConsultationJours(agePremiereConsultationJours);
        return this;
    }

    public void setAgePremiereConsultationJours(Integer agePremiereConsultationJours) {
        this.agePremiereConsultationJours = agePremiereConsultationJours;
    }

    public Integer getAgePremiereConsultationMois() {
        return this.agePremiereConsultationMois;
    }

    public FicheFacteurXI agePremiereConsultationMois(Integer agePremiereConsultationMois) {
        this.setAgePremiereConsultationMois(agePremiereConsultationMois);
        return this;
    }

    public void setAgePremiereConsultationMois(Integer agePremiereConsultationMois) {
        this.agePremiereConsultationMois = agePremiereConsultationMois;
    }

    public Integer getAgePremiereConsultationAnnees() {
        return this.agePremiereConsultationAnnees;
    }

    public FicheFacteurXI agePremiereConsultationAnnees(Integer agePremiereConsultationAnnees) {
        this.setAgePremiereConsultationAnnees(agePremiereConsultationAnnees);
        return this;
    }

    public void setAgePremiereConsultationAnnees(Integer agePremiereConsultationAnnees) {
        this.agePremiereConsultationAnnees = agePremiereConsultationAnnees;
    }

    public Integer getAgeDiagnosticJours() {
        return this.ageDiagnosticJours;
    }

    public FicheFacteurXI ageDiagnosticJours(Integer ageDiagnosticJours) {
        this.setAgeDiagnosticJours(ageDiagnosticJours);
        return this;
    }

    public void setAgeDiagnosticJours(Integer ageDiagnosticJours) {
        this.ageDiagnosticJours = ageDiagnosticJours;
    }

    public Integer getAgeDiagnosticMois() {
        return this.ageDiagnosticMois;
    }

    public FicheFacteurXI ageDiagnosticMois(Integer ageDiagnosticMois) {
        this.setAgeDiagnosticMois(ageDiagnosticMois);
        return this;
    }

    public void setAgeDiagnosticMois(Integer ageDiagnosticMois) {
        this.ageDiagnosticMois = ageDiagnosticMois;
    }

    public Integer getAgeDiagnosticAnnees() {
        return this.ageDiagnosticAnnees;
    }

    public FicheFacteurXI ageDiagnosticAnnees(Integer ageDiagnosticAnnees) {
        this.setAgeDiagnosticAnnees(ageDiagnosticAnnees);
        return this;
    }

    public void setAgeDiagnosticAnnees(Integer ageDiagnosticAnnees) {
        this.ageDiagnosticAnnees = ageDiagnosticAnnees;
    }

    public Double getHb() {
        return this.hb;
    }

    public FicheFacteurXI hb(Double hb) {
        this.setHb(hb);
        return this;
    }

    public void setHb(Double hb) {
        this.hb = hb;
    }

    public Double getHte() {
        return this.hte;
    }

    public FicheFacteurXI hte(Double hte) {
        this.setHte(hte);
        return this;
    }

    public void setHte(Double hte) {
        this.hte = hte;
    }

    public Integer getPlaquettes() {
        return this.plaquettes;
    }

    public FicheFacteurXI plaquettes(Integer plaquettes) {
        this.setPlaquettes(plaquettes);
        return this;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Double getPfaColEpi() {
        return this.pfaColEpi;
    }

    public FicheFacteurXI pfaColEpi(Double pfaColEpi) {
        this.setPfaColEpi(pfaColEpi);
        return this;
    }

    public void setPfaColEpi(Double pfaColEpi) {
        this.pfaColEpi = pfaColEpi;
    }

    public Double getPfaColADP() {
        return this.pfaColADP;
    }

    public FicheFacteurXI pfaColADP(Double pfaColADP) {
        this.setPfaColADP(pfaColADP);
        return this;
    }

    public void setPfaColADP(Double pfaColADP) {
        this.pfaColADP = pfaColADP;
    }

    public Double getTp() {
        return this.tp;
    }

    public FicheFacteurXI tp(Double tp) {
        this.setTp(tp);
        return this;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getTpMPlusT() {
        return this.tpMPlusT;
    }

    public FicheFacteurXI tpMPlusT(Double tpMPlusT) {
        this.setTpMPlusT(tpMPlusT);
        return this;
    }

    public void setTpMPlusT(Double tpMPlusT) {
        this.tpMPlusT = tpMPlusT;
    }

    public Double getFibrinogene() {
        return this.fibrinogene;
    }

    public FicheFacteurXI fibrinogene(Double fibrinogene) {
        this.setFibrinogene(fibrinogene);
        return this;
    }

    public void setFibrinogene(Double fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public Double getTcaMT() {
        return this.tcaMT;
    }

    public FicheFacteurXI tcaMT(Double tcaMT) {
        this.setTcaMT(tcaMT);
        return this;
    }

    public void setTcaMT(Double tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Double getTcaMPlusT() {
        return this.tcaMPlusT;
    }

    public FicheFacteurXI tcaMPlusT(Double tcaMPlusT) {
        this.setTcaMPlusT(tcaMPlusT);
        return this;
    }

    public void setTcaMPlusT(Double tcaMPlusT) {
        this.tcaMPlusT = tcaMPlusT;
    }

    public Double getTcaTemoin() {
        return this.tcaTemoin;
    }

    public FicheFacteurXI tcaTemoin(Double tcaTemoin) {
        this.setTcaTemoin(tcaTemoin);
        return this;
    }

    public void setTcaTemoin(Double tcaTemoin) {
        this.tcaTemoin = tcaTemoin;
    }

    public Double getFacteurXI() {
        return this.facteurXI;
    }

    public FicheFacteurXI facteurXI(Double facteurXI) {
        this.setFacteurXI(facteurXI);
        return this;
    }

    public void setFacteurXI(Double facteurXI) {
        this.facteurXI = facteurXI;
    }

    public String getAutresFacteursDosageDescription() {
        return this.autresFacteursDosageDescription;
    }

    public FicheFacteurXI autresFacteursDosageDescription(String autresFacteursDosageDescription) {
        this.setAutresFacteursDosageDescription(autresFacteursDosageDescription);
        return this;
    }

    public void setAutresFacteursDosageDescription(String autresFacteursDosageDescription) {
        this.autresFacteursDosageDescription = autresFacteursDosageDescription;
    }

    public Double getAutresFacteursTaux1() {
        return this.autresFacteursTaux1;
    }

    public FicheFacteurXI autresFacteursTaux1(Double autresFacteursTaux1) {
        this.setAutresFacteursTaux1(autresFacteursTaux1);
        return this;
    }

    public void setAutresFacteursTaux1(Double autresFacteursTaux1) {
        this.autresFacteursTaux1 = autresFacteursTaux1;
    }

    public Double getAutresFacteursTaux2() {
        return this.autresFacteursTaux2;
    }

    public FicheFacteurXI autresFacteursTaux2(Double autresFacteursTaux2) {
        this.setAutresFacteursTaux2(autresFacteursTaux2);
        return this;
    }

    public void setAutresFacteursTaux2(Double autresFacteursTaux2) {
        this.autresFacteursTaux2 = autresFacteursTaux2;
    }

    public Double getAutresFacteursTaux3() {
        return this.autresFacteursTaux3;
    }

    public FicheFacteurXI autresFacteursTaux3(Double autresFacteursTaux3) {
        this.setAutresFacteursTaux3(autresFacteursTaux3);
        return this;
    }

    public void setAutresFacteursTaux3(Double autresFacteursTaux3) {
        this.autresFacteursTaux3 = autresFacteursTaux3;
    }

    public FaiteNonFaite getConfirmationMoleculaire() {
        return this.confirmationMoleculaire;
    }

    public FicheFacteurXI confirmationMoleculaire(FaiteNonFaite confirmationMoleculaire) {
        this.setConfirmationMoleculaire(confirmationMoleculaire);
        return this;
    }

    public void setConfirmationMoleculaire(FaiteNonFaite confirmationMoleculaire) {
        this.confirmationMoleculaire = confirmationMoleculaire;
    }

    public Boolean getMutationPresente() {
        return this.mutationPresente;
    }

    public FicheFacteurXI mutationPresente(Boolean mutationPresente) {
        this.setMutationPresente(mutationPresente);
        return this;
    }

    public void setMutationPresente(Boolean mutationPresente) {
        this.mutationPresente = mutationPresente;
    }

    public String getMutationNom() {
        return this.mutationNom;
    }

    public FicheFacteurXI mutationNom(String mutationNom) {
        this.setMutationNom(mutationNom);
        return this;
    }

    public void setMutationNom(String mutationNom) {
        this.mutationNom = mutationNom;
    }

    public TypeZygote getMutationZygote() {
        return this.mutationZygote;
    }

    public FicheFacteurXI mutationZygote(TypeZygote mutationZygote) {
        this.setMutationZygote(mutationZygote);
        return this;
    }

    public void setMutationZygote(TypeZygote mutationZygote) {
        this.mutationZygote = mutationZygote;
    }

    public String getMutation2Nom() {
        return this.mutation2Nom;
    }

    public FicheFacteurXI mutation2Nom(String mutation2Nom) {
        this.setMutation2Nom(mutation2Nom);
        return this;
    }

    public void setMutation2Nom(String mutation2Nom) {
        this.mutation2Nom = mutation2Nom;
    }

    public OuiNonNP getSerologieVHB() {
        return this.serologieVHB;
    }

    public FicheFacteurXI serologieVHB(OuiNonNP serologieVHB) {
        this.setSerologieVHB(serologieVHB);
        return this;
    }

    public void setSerologieVHB(OuiNonNP serologieVHB) {
        this.serologieVHB = serologieVHB;
    }

    public OuiNonNP getSerologieVHC() {
        return this.serologieVHC;
    }

    public FicheFacteurXI serologieVHC(OuiNonNP serologieVHC) {
        this.setSerologieVHC(serologieVHC);
        return this;
    }

    public void setSerologieVHC(OuiNonNP serologieVHC) {
        this.serologieVHC = serologieVHC;
    }

    public OuiNonNP getSerologieVIH() {
        return this.serologieVIH;
    }

    public FicheFacteurXI serologieVIH(OuiNonNP serologieVIH) {
        this.setSerologieVIH(serologieVIH);
        return this;
    }

    public void setSerologieVIH(OuiNonNP serologieVIH) {
        this.serologieVIH = serologieVIH;
    }

    public Boolean getEpistaxis() {
        return this.epistaxis;
    }

    public FicheFacteurXI epistaxis(Boolean epistaxis) {
        this.setEpistaxis(epistaxis);
        return this;
    }

    public void setEpistaxis(Boolean epistaxis) {
        this.epistaxis = epistaxis;
    }

    public Integer getFrequenceEpistaxis() {
        return this.frequenceEpistaxis;
    }

    public FicheFacteurXI frequenceEpistaxis(Integer frequenceEpistaxis) {
        this.setFrequenceEpistaxis(frequenceEpistaxis);
        return this;
    }

    public void setFrequenceEpistaxis(Integer frequenceEpistaxis) {
        this.frequenceEpistaxis = frequenceEpistaxis;
    }

    public Boolean getGingivorragies() {
        return this.gingivorragies;
    }

    public FicheFacteurXI gingivorragies(Boolean gingivorragies) {
        this.setGingivorragies(gingivorragies);
        return this;
    }

    public void setGingivorragies(Boolean gingivorragies) {
        this.gingivorragies = gingivorragies;
    }

    public TypeManifestation getTypeGingivorragies() {
        return this.typeGingivorragies;
    }

    public FicheFacteurXI typeGingivorragies(TypeManifestation typeGingivorragies) {
        this.setTypeGingivorragies(typeGingivorragies);
        return this;
    }

    public void setTypeGingivorragies(TypeManifestation typeGingivorragies) {
        this.typeGingivorragies = typeGingivorragies;
    }

    public Integer getFrequenceGingivorragies() {
        return this.frequenceGingivorragies;
    }

    public FicheFacteurXI frequenceGingivorragies(Integer frequenceGingivorragies) {
        this.setFrequenceGingivorragies(frequenceGingivorragies);
        return this;
    }

    public void setFrequenceGingivorragies(Integer frequenceGingivorragies) {
        this.frequenceGingivorragies = frequenceGingivorragies;
    }

    public Boolean getEcchymose() {
        return this.ecchymose;
    }

    public FicheFacteurXI ecchymose(Boolean ecchymose) {
        this.setEcchymose(ecchymose);
        return this;
    }

    public void setEcchymose(Boolean ecchymose) {
        this.ecchymose = ecchymose;
    }

    public TypeManifestation getTypeEcchymose() {
        return this.typeEcchymose;
    }

    public FicheFacteurXI typeEcchymose(TypeManifestation typeEcchymose) {
        this.setTypeEcchymose(typeEcchymose);
        return this;
    }

    public void setTypeEcchymose(TypeManifestation typeEcchymose) {
        this.typeEcchymose = typeEcchymose;
    }

    public Integer getFrequenceEcchymose() {
        return this.frequenceEcchymose;
    }

    public FicheFacteurXI frequenceEcchymose(Integer frequenceEcchymose) {
        this.setFrequenceEcchymose(frequenceEcchymose);
        return this;
    }

    public void setFrequenceEcchymose(Integer frequenceEcchymose) {
        this.frequenceEcchymose = frequenceEcchymose;
    }

    public Boolean getMenorragie() {
        return this.menorragie;
    }

    public FicheFacteurXI menorragie(Boolean menorragie) {
        this.setMenorragie(menorragie);
        return this;
    }

    public void setMenorragie(Boolean menorragie) {
        this.menorragie = menorragie;
    }

    public Integer getFrequenceMenorragie() {
        return this.frequenceMenorragie;
    }

    public FicheFacteurXI frequenceMenorragie(Integer frequenceMenorragie) {
        this.setFrequenceMenorragie(frequenceMenorragie);
        return this;
    }

    public void setFrequenceMenorragie(Integer frequenceMenorragie) {
        this.frequenceMenorragie = frequenceMenorragie;
    }

    public Boolean getHematemese() {
        return this.hematemese;
    }

    public FicheFacteurXI hematemese(Boolean hematemese) {
        this.setHematemese(hematemese);
        return this;
    }

    public void setHematemese(Boolean hematemese) {
        this.hematemese = hematemese;
    }

    public Integer getFrequenceHematemese() {
        return this.frequenceHematemese;
    }

    public FicheFacteurXI frequenceHematemese(Integer frequenceHematemese) {
        this.setFrequenceHematemese(frequenceHematemese);
        return this;
    }

    public void setFrequenceHematemese(Integer frequenceHematemese) {
        this.frequenceHematemese = frequenceHematemese;
    }

    public Boolean getRectorragie() {
        return this.rectorragie;
    }

    public FicheFacteurXI rectorragie(Boolean rectorragie) {
        this.setRectorragie(rectorragie);
        return this;
    }

    public void setRectorragie(Boolean rectorragie) {
        this.rectorragie = rectorragie;
    }

    public Integer getFrequenceRectorragie() {
        return this.frequenceRectorragie;
    }

    public FicheFacteurXI frequenceRectorragie(Integer frequenceRectorragie) {
        this.setFrequenceRectorragie(frequenceRectorragie);
        return this;
    }

    public void setFrequenceRectorragie(Integer frequenceRectorragie) {
        this.frequenceRectorragie = frequenceRectorragie;
    }

    public Boolean getHematurie() {
        return this.hematurie;
    }

    public FicheFacteurXI hematurie(Boolean hematurie) {
        this.setHematurie(hematurie);
        return this;
    }

    public void setHematurie(Boolean hematurie) {
        this.hematurie = hematurie;
    }

    public Integer getFrequenceHematurie() {
        return this.frequenceHematurie;
    }

    public FicheFacteurXI frequenceHematurie(Integer frequenceHematurie) {
        this.setFrequenceHematurie(frequenceHematurie);
        return this;
    }

    public void setFrequenceHematurie(Integer frequenceHematurie) {
        this.frequenceHematurie = frequenceHematurie;
    }

    public Boolean getHemorragiePostTraumatique() {
        return this.hemorragiePostTraumatique;
    }

    public FicheFacteurXI hemorragiePostTraumatique(Boolean hemorragiePostTraumatique) {
        this.setHemorragiePostTraumatique(hemorragiePostTraumatique);
        return this;
    }

    public void setHemorragiePostTraumatique(Boolean hemorragiePostTraumatique) {
        this.hemorragiePostTraumatique = hemorragiePostTraumatique;
    }

    public Integer getFrequenceHemorragiePostTraumatique() {
        return this.frequenceHemorragiePostTraumatique;
    }

    public FicheFacteurXI frequenceHemorragiePostTraumatique(Integer frequenceHemorragiePostTraumatique) {
        this.setFrequenceHemorragiePostTraumatique(frequenceHemorragiePostTraumatique);
        return this;
    }

    public void setFrequenceHemorragiePostTraumatique(Integer frequenceHemorragiePostTraumatique) {
        this.frequenceHemorragiePostTraumatique = frequenceHemorragiePostTraumatique;
    }

    public Boolean getHematomeSousCutane() {
        return this.hematomeSousCutane;
    }

    public FicheFacteurXI hematomeSousCutane(Boolean hematomeSousCutane) {
        this.setHematomeSousCutane(hematomeSousCutane);
        return this;
    }

    public void setHematomeSousCutane(Boolean hematomeSousCutane) {
        this.hematomeSousCutane = hematomeSousCutane;
    }

    public TypeManifestation getTypeHematomeSousCutane() {
        return this.typeHematomeSousCutane;
    }

    public FicheFacteurXI typeHematomeSousCutane(TypeManifestation typeHematomeSousCutane) {
        this.setTypeHematomeSousCutane(typeHematomeSousCutane);
        return this;
    }

    public void setTypeHematomeSousCutane(TypeManifestation typeHematomeSousCutane) {
        this.typeHematomeSousCutane = typeHematomeSousCutane;
    }

    public Integer getFrequenceHematomeSousCutane() {
        return this.frequenceHematomeSousCutane;
    }

    public FicheFacteurXI frequenceHematomeSousCutane(Integer frequenceHematomeSousCutane) {
        this.setFrequenceHematomeSousCutane(frequenceHematomeSousCutane);
        return this;
    }

    public void setFrequenceHematomeSousCutane(Integer frequenceHematomeSousCutane) {
        this.frequenceHematomeSousCutane = frequenceHematomeSousCutane;
    }

    public Boolean getHematomeMusculaire() {
        return this.hematomeMusculaire;
    }

    public FicheFacteurXI hematomeMusculaire(Boolean hematomeMusculaire) {
        this.setHematomeMusculaire(hematomeMusculaire);
        return this;
    }

    public void setHematomeMusculaire(Boolean hematomeMusculaire) {
        this.hematomeMusculaire = hematomeMusculaire;
    }

    public TypeManifestation getTypeHematomeMusculaire() {
        return this.typeHematomeMusculaire;
    }

    public FicheFacteurXI typeHematomeMusculaire(TypeManifestation typeHematomeMusculaire) {
        this.setTypeHematomeMusculaire(typeHematomeMusculaire);
        return this;
    }

    public void setTypeHematomeMusculaire(TypeManifestation typeHematomeMusculaire) {
        this.typeHematomeMusculaire = typeHematomeMusculaire;
    }

    public Integer getFrequenceHematomeMusculaire() {
        return this.frequenceHematomeMusculaire;
    }

    public FicheFacteurXI frequenceHematomeMusculaire(Integer frequenceHematomeMusculaire) {
        this.setFrequenceHematomeMusculaire(frequenceHematomeMusculaire);
        return this;
    }

    public void setFrequenceHematomeMusculaire(Integer frequenceHematomeMusculaire) {
        this.frequenceHematomeMusculaire = frequenceHematomeMusculaire;
    }

    public Boolean getHematomeTissusMousProfonds() {
        return this.hematomeTissusMousProfonds;
    }

    public FicheFacteurXI hematomeTissusMousProfonds(Boolean hematomeTissusMousProfonds) {
        this.setHematomeTissusMousProfonds(hematomeTissusMousProfonds);
        return this;
    }

    public void setHematomeTissusMousProfonds(Boolean hematomeTissusMousProfonds) {
        this.hematomeTissusMousProfonds = hematomeTissusMousProfonds;
    }

    public TypeManifestation getTypeHematomeTissusMousProfonds() {
        return this.typeHematomeTissusMousProfonds;
    }

    public FicheFacteurXI typeHematomeTissusMousProfonds(TypeManifestation typeHematomeTissusMousProfonds) {
        this.setTypeHematomeTissusMousProfonds(typeHematomeTissusMousProfonds);
        return this;
    }

    public void setTypeHematomeTissusMousProfonds(TypeManifestation typeHematomeTissusMousProfonds) {
        this.typeHematomeTissusMousProfonds = typeHematomeTissusMousProfonds;
    }

    public Integer getFrequenceHematomeTissusMousProfonds() {
        return this.frequenceHematomeTissusMousProfonds;
    }

    public FicheFacteurXI frequenceHematomeTissusMousProfonds(Integer frequenceHematomeTissusMousProfonds) {
        this.setFrequenceHematomeTissusMousProfonds(frequenceHematomeTissusMousProfonds);
        return this;
    }

    public void setFrequenceHematomeTissusMousProfonds(Integer frequenceHematomeTissusMousProfonds) {
        this.frequenceHematomeTissusMousProfonds = frequenceHematomeTissusMousProfonds;
    }

    public Boolean getHemarthrose() {
        return this.hemarthrose;
    }

    public FicheFacteurXI hemarthrose(Boolean hemarthrose) {
        this.setHemarthrose(hemarthrose);
        return this;
    }

    public void setHemarthrose(Boolean hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public TypeManifestation getTypeHemarthrose() {
        return this.typeHemarthrose;
    }

    public FicheFacteurXI typeHemarthrose(TypeManifestation typeHemarthrose) {
        this.setTypeHemarthrose(typeHemarthrose);
        return this;
    }

    public void setTypeHemarthrose(TypeManifestation typeHemarthrose) {
        this.typeHemarthrose = typeHemarthrose;
    }

    public Integer getFrequenceHemarthrose() {
        return this.frequenceHemarthrose;
    }

    public FicheFacteurXI frequenceHemarthrose(Integer frequenceHemarthrose) {
        this.setFrequenceHemarthrose(frequenceHemarthrose);
        return this;
    }

    public void setFrequenceHemarthrose(Integer frequenceHemarthrose) {
        this.frequenceHemarthrose = frequenceHemarthrose;
    }

    public Boolean getSaignementSNC() {
        return this.saignementSNC;
    }

    public FicheFacteurXI saignementSNC(Boolean saignementSNC) {
        this.setSaignementSNC(saignementSNC);
        return this;
    }

    public void setSaignementSNC(Boolean saignementSNC) {
        this.saignementSNC = saignementSNC;
    }

    public TypeManifestation getTypeSaignementSNC() {
        return this.typeSaignementSNC;
    }

    public FicheFacteurXI typeSaignementSNC(TypeManifestation typeSaignementSNC) {
        this.setTypeSaignementSNC(typeSaignementSNC);
        return this;
    }

    public void setTypeSaignementSNC(TypeManifestation typeSaignementSNC) {
        this.typeSaignementSNC = typeSaignementSNC;
    }

    public Integer getFrequenceSaignementSNC() {
        return this.frequenceSaignementSNC;
    }

    public FicheFacteurXI frequenceSaignementSNC(Integer frequenceSaignementSNC) {
        this.setFrequenceSaignementSNC(frequenceSaignementSNC);
        return this;
    }

    public void setFrequenceSaignementSNC(Integer frequenceSaignementSNC) {
        this.frequenceSaignementSNC = frequenceSaignementSNC;
    }

    public Boolean getSaignementChuteCordon() {
        return this.saignementChuteCordon;
    }

    public FicheFacteurXI saignementChuteCordon(Boolean saignementChuteCordon) {
        this.setSaignementChuteCordon(saignementChuteCordon);
        return this;
    }

    public void setSaignementChuteCordon(Boolean saignementChuteCordon) {
        this.saignementChuteCordon = saignementChuteCordon;
    }

    public Integer getFrequenceSaignementChuteCordon() {
        return this.frequenceSaignementChuteCordon;
    }

    public FicheFacteurXI frequenceSaignementChuteCordon(Integer frequenceSaignementChuteCordon) {
        this.setFrequenceSaignementChuteCordon(frequenceSaignementChuteCordon);
        return this;
    }

    public void setFrequenceSaignementChuteCordon(Integer frequenceSaignementChuteCordon) {
        this.frequenceSaignementChuteCordon = frequenceSaignementChuteCordon;
    }

    public Boolean getRetardCicatrisationPlaies() {
        return this.retardCicatrisationPlaies;
    }

    public FicheFacteurXI retardCicatrisationPlaies(Boolean retardCicatrisationPlaies) {
        this.setRetardCicatrisationPlaies(retardCicatrisationPlaies);
        return this;
    }

    public void setRetardCicatrisationPlaies(Boolean retardCicatrisationPlaies) {
        this.retardCicatrisationPlaies = retardCicatrisationPlaies;
    }

    public Integer getFrequenceRetardCicatrisationPlaies() {
        return this.frequenceRetardCicatrisationPlaies;
    }

    public FicheFacteurXI frequenceRetardCicatrisationPlaies(Integer frequenceRetardCicatrisationPlaies) {
        this.setFrequenceRetardCicatrisationPlaies(frequenceRetardCicatrisationPlaies);
        return this;
    }

    public void setFrequenceRetardCicatrisationPlaies(Integer frequenceRetardCicatrisationPlaies) {
        this.frequenceRetardCicatrisationPlaies = frequenceRetardCicatrisationPlaies;
    }

    public Boolean getAutresManifestations() {
        return this.autresManifestations;
    }

    public FicheFacteurXI autresManifestations(Boolean autresManifestations) {
        this.setAutresManifestations(autresManifestations);
        return this;
    }

    public void setAutresManifestations(Boolean autresManifestations) {
        this.autresManifestations = autresManifestations;
    }

    public Boolean getFaussesCouchesSpontaneesRecurrence() {
        return this.faussesCouchesSpontaneesRecurrence;
    }

    public FicheFacteurXI faussesCouchesSpontaneesRecurrence(Boolean faussesCouchesSpontaneesRecurrence) {
        this.setFaussesCouchesSpontaneesRecurrence(faussesCouchesSpontaneesRecurrence);
        return this;
    }

    public void setFaussesCouchesSpontaneesRecurrence(Boolean faussesCouchesSpontaneesRecurrence) {
        this.faussesCouchesSpontaneesRecurrence = faussesCouchesSpontaneesRecurrence;
    }

    public Integer getFrequenceFaussesCouches() {
        return this.frequenceFaussesCouches;
    }

    public FicheFacteurXI frequenceFaussesCouches(Integer frequenceFaussesCouches) {
        this.setFrequenceFaussesCouches(frequenceFaussesCouches);
        return this;
    }

    public void setFrequenceFaussesCouches(Integer frequenceFaussesCouches) {
        this.frequenceFaussesCouches = frequenceFaussesCouches;
    }

    public Boolean getAccidentsThrombotiques() {
        return this.accidentsThrombotiques;
    }

    public FicheFacteurXI accidentsThrombotiques(Boolean accidentsThrombotiques) {
        this.setAccidentsThrombotiques(accidentsThrombotiques);
        return this;
    }

    public void setAccidentsThrombotiques(Boolean accidentsThrombotiques) {
        this.accidentsThrombotiques = accidentsThrombotiques;
    }

    public Boolean getThromboseVeineuse() {
        return this.thromboseVeineuse;
    }

    public FicheFacteurXI thromboseVeineuse(Boolean thromboseVeineuse) {
        this.setThromboseVeineuse(thromboseVeineuse);
        return this;
    }

    public void setThromboseVeineuse(Boolean thromboseVeineuse) {
        this.thromboseVeineuse = thromboseVeineuse;
    }

    public Boolean getThromboseArterielle() {
        return this.thromboseArterielle;
    }

    public FicheFacteurXI thromboseArterielle(Boolean thromboseArterielle) {
        this.setThromboseArterielle(thromboseArterielle);
        return this;
    }

    public void setThromboseArterielle(Boolean thromboseArterielle) {
        this.thromboseArterielle = thromboseArterielle;
    }

    public Integer getFrequenceAccidentsThrombotiques() {
        return this.frequenceAccidentsThrombotiques;
    }

    public FicheFacteurXI frequenceAccidentsThrombotiques(Integer frequenceAccidentsThrombotiques) {
        this.setFrequenceAccidentsThrombotiques(frequenceAccidentsThrombotiques);
        return this;
    }

    public void setFrequenceAccidentsThrombotiques(Integer frequenceAccidentsThrombotiques) {
        this.frequenceAccidentsThrombotiques = frequenceAccidentsThrombotiques;
    }

    public Boolean getHemoperitoine() {
        return this.hemoperitoine;
    }

    public FicheFacteurXI hemoperitoine(Boolean hemoperitoine) {
        this.setHemoperitoine(hemoperitoine);
        return this;
    }

    public void setHemoperitoine(Boolean hemoperitoine) {
        this.hemoperitoine = hemoperitoine;
    }

    public Integer getFrequenceHemoperitoine() {
        return this.frequenceHemoperitoine;
    }

    public FicheFacteurXI frequenceHemoperitoine(Integer frequenceHemoperitoine) {
        this.setFrequenceHemoperitoine(frequenceHemoperitoine);
        return this;
    }

    public void setFrequenceHemoperitoine(Integer frequenceHemoperitoine) {
        this.frequenceHemoperitoine = frequenceHemoperitoine;
    }

    public Boolean getRuptureKystesHemorragiques() {
        return this.ruptureKystesHemorragiques;
    }

    public FicheFacteurXI ruptureKystesHemorragiques(Boolean ruptureKystesHemorragiques) {
        this.setRuptureKystesHemorragiques(ruptureKystesHemorragiques);
        return this;
    }

    public void setRuptureKystesHemorragiques(Boolean ruptureKystesHemorragiques) {
        this.ruptureKystesHemorragiques = ruptureKystesHemorragiques;
    }

    public Integer getFrequenceRuptureKystes() {
        return this.frequenceRuptureKystes;
    }

    public FicheFacteurXI frequenceRuptureKystes(Integer frequenceRuptureKystes) {
        this.setFrequenceRuptureKystes(frequenceRuptureKystes);
        return this;
    }

    public void setFrequenceRuptureKystes(Integer frequenceRuptureKystes) {
        this.frequenceRuptureKystes = frequenceRuptureKystes;
    }

    public Boolean getHemorragieGrave() {
        return this.hemorragieGrave;
    }

    public FicheFacteurXI hemorragieGrave(Boolean hemorragieGrave) {
        this.setHemorragieGrave(hemorragieGrave);
        return this;
    }

    public void setHemorragieGrave(Boolean hemorragieGrave) {
        this.hemorragieGrave = hemorragieGrave;
    }

    public Integer getFrequenceHemorragieGrave() {
        return this.frequenceHemorragieGrave;
    }

    public FicheFacteurXI frequenceHemorragieGrave(Integer frequenceHemorragieGrave) {
        this.setFrequenceHemorragieGrave(frequenceHemorragieGrave);
        return this;
    }

    public void setFrequenceHemorragieGrave(Integer frequenceHemorragieGrave) {
        this.frequenceHemorragieGrave = frequenceHemorragieGrave;
    }

    public Boolean getGraviteDeglobulisation() {
        return this.graviteDeglobulisation;
    }

    public FicheFacteurXI graviteDeglobulisation(Boolean graviteDeglobulisation) {
        this.setGraviteDeglobulisation(graviteDeglobulisation);
        return this;
    }

    public void setGraviteDeglobulisation(Boolean graviteDeglobulisation) {
        this.graviteDeglobulisation = graviteDeglobulisation;
    }

    public Boolean getGraviteEtatDeChoc() {
        return this.graviteEtatDeChoc;
    }

    public FicheFacteurXI graviteEtatDeChoc(Boolean graviteEtatDeChoc) {
        this.setGraviteEtatDeChoc(graviteEtatDeChoc);
        return this;
    }

    public void setGraviteEtatDeChoc(Boolean graviteEtatDeChoc) {
        this.graviteEtatDeChoc = graviteEtatDeChoc;
    }

    public Boolean getGraviteParLocalisation() {
        return this.graviteParLocalisation;
    }

    public FicheFacteurXI graviteParLocalisation(Boolean graviteParLocalisation) {
        this.setGraviteParLocalisation(graviteParLocalisation);
        return this;
    }

    public void setGraviteParLocalisation(Boolean graviteParLocalisation) {
        this.graviteParLocalisation = graviteParLocalisation;
    }

    public String getSiegeGraviteLocalisation() {
        return this.siegeGraviteLocalisation;
    }

    public FicheFacteurXI siegeGraviteLocalisation(String siegeGraviteLocalisation) {
        this.setSiegeGraviteLocalisation(siegeGraviteLocalisation);
        return this;
    }

    public void setSiegeGraviteLocalisation(String siegeGraviteLocalisation) {
        this.siegeGraviteLocalisation = siegeGraviteLocalisation;
    }

    public OuiNonNP getChirurgiePrepare() {
        return this.chirurgiePrepare;
    }

    public FicheFacteurXI chirurgiePrepare(OuiNonNP chirurgiePrepare) {
        this.setChirurgiePrepare(chirurgiePrepare);
        return this;
    }

    public void setChirurgiePrepare(OuiNonNP chirurgiePrepare) {
        this.chirurgiePrepare = chirurgiePrepare;
    }

    public OuiNonNP getChirurgieSuiteHemorragique() {
        return this.chirurgieSuiteHemorragique;
    }

    public FicheFacteurXI chirurgieSuiteHemorragique(OuiNonNP chirurgieSuiteHemorragique) {
        this.setChirurgieSuiteHemorragique(chirurgieSuiteHemorragique);
        return this;
    }

    public void setChirurgieSuiteHemorragique(OuiNonNP chirurgieSuiteHemorragique) {
        this.chirurgieSuiteHemorragique = chirurgieSuiteHemorragique;
    }

    public OuiNonNP getCirconcisionPrepare() {
        return this.circoncisionPrepare;
    }

    public FicheFacteurXI circoncisionPrepare(OuiNonNP circoncisionPrepare) {
        this.setCirconcisionPrepare(circoncisionPrepare);
        return this;
    }

    public void setCirconcisionPrepare(OuiNonNP circoncisionPrepare) {
        this.circoncisionPrepare = circoncisionPrepare;
    }

    public OuiNonNP getCirconcisionSuiteHemorragique() {
        return this.circoncisionSuiteHemorragique;
    }

    public FicheFacteurXI circoncisionSuiteHemorragique(OuiNonNP circoncisionSuiteHemorragique) {
        this.setCirconcisionSuiteHemorragique(circoncisionSuiteHemorragique);
        return this;
    }

    public void setCirconcisionSuiteHemorragique(OuiNonNP circoncisionSuiteHemorragique) {
        this.circoncisionSuiteHemorragique = circoncisionSuiteHemorragique;
    }

    public Integer getAgeCirconcision() {
        return this.ageCirconcision;
    }

    public FicheFacteurXI ageCirconcision(Integer ageCirconcision) {
        this.setAgeCirconcision(ageCirconcision);
        return this;
    }

    public void setAgeCirconcision(Integer ageCirconcision) {
        this.ageCirconcision = ageCirconcision;
    }

    public OuiNonNP getTraitementPFC() {
        return this.traitementPFC;
    }

    public FicheFacteurXI traitementPFC(OuiNonNP traitementPFC) {
        this.setTraitementPFC(traitementPFC);
        return this;
    }

    public void setTraitementPFC(OuiNonNP traitementPFC) {
        this.traitementPFC = traitementPFC;
    }

    public OuiNonNP getTraitementCryoprecipite() {
        return this.traitementCryoprecipite;
    }

    public FicheFacteurXI traitementCryoprecipite(OuiNonNP traitementCryoprecipite) {
        this.setTraitementCryoprecipite(traitementCryoprecipite);
        return this;
    }

    public void setTraitementCryoprecipite(OuiNonNP traitementCryoprecipite) {
        this.traitementCryoprecipite = traitementCryoprecipite;
    }

    public OuiNonNP getTraitementConcentreFacteurXI() {
        return this.traitementConcentreFacteurXI;
    }

    public FicheFacteurXI traitementConcentreFacteurXI(OuiNonNP traitementConcentreFacteurXI) {
        this.setTraitementConcentreFacteurXI(traitementConcentreFacteurXI);
        return this;
    }

    public void setTraitementConcentreFacteurXI(OuiNonNP traitementConcentreFacteurXI) {
        this.traitementConcentreFacteurXI = traitementConcentreFacteurXI;
    }

    public OuiNonNP getTraitementCulotsPlaquettaires() {
        return this.traitementCulotsPlaquettaires;
    }

    public FicheFacteurXI traitementCulotsPlaquettaires(OuiNonNP traitementCulotsPlaquettaires) {
        this.setTraitementCulotsPlaquettaires(traitementCulotsPlaquettaires);
        return this;
    }

    public void setTraitementCulotsPlaquettaires(OuiNonNP traitementCulotsPlaquettaires) {
        this.traitementCulotsPlaquettaires = traitementCulotsPlaquettaires;
    }

    public OuiNonNP getTraitementDicynone() {
        return this.traitementDicynone;
    }

    public FicheFacteurXI traitementDicynone(OuiNonNP traitementDicynone) {
        this.setTraitementDicynone(traitementDicynone);
        return this;
    }

    public void setTraitementDicynone(OuiNonNP traitementDicynone) {
        this.traitementDicynone = traitementDicynone;
    }

    public OuiNonNP getTraitementExacyl() {
        return this.traitementExacyl;
    }

    public FicheFacteurXI traitementExacyl(OuiNonNP traitementExacyl) {
        this.setTraitementExacyl(traitementExacyl);
        return this;
    }

    public void setTraitementExacyl(OuiNonNP traitementExacyl) {
        this.traitementExacyl = traitementExacyl;
    }

    public OuiNonNP getTraitementMartial() {
        return this.traitementMartial;
    }

    public FicheFacteurXI traitementMartial(OuiNonNP traitementMartial) {
        this.setTraitementMartial(traitementMartial);
        return this;
    }

    public void setTraitementMartial(OuiNonNP traitementMartial) {
        this.traitementMartial = traitementMartial;
    }

    public OuiNonNP getTraitementOestroprogestatif() {
        return this.traitementOestroprogestatif;
    }

    public FicheFacteurXI traitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.setTraitementOestroprogestatif(traitementOestroprogestatif);
        return this;
    }

    public void setTraitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.traitementOestroprogestatif = traitementOestroprogestatif;
    }

    public OuiNonNP getTransfusionCGR() {
        return this.transfusionCGR;
    }

    public FicheFacteurXI transfusionCGR(OuiNonNP transfusionCGR) {
        this.setTransfusionCGR(transfusionCGR);
        return this;
    }

    public void setTransfusionCGR(OuiNonNP transfusionCGR) {
        this.transfusionCGR = transfusionCGR;
    }

    public OuiNonNP getAlloImmunisation() {
        return this.alloImmunisation;
    }

    public FicheFacteurXI alloImmunisation(OuiNonNP alloImmunisation) {
        this.setAlloImmunisation(alloImmunisation);
        return this;
    }

    public void setAlloImmunisation(OuiNonNP alloImmunisation) {
        this.alloImmunisation = alloImmunisation;
    }

    public OuiNonNP getInhibiteurNeutralisant() {
        return this.inhibiteurNeutralisant;
    }

    public FicheFacteurXI inhibiteurNeutralisant(OuiNonNP inhibiteurNeutralisant) {
        this.setInhibiteurNeutralisant(inhibiteurNeutralisant);
        return this;
    }

    public void setInhibiteurNeutralisant(OuiNonNP inhibiteurNeutralisant) {
        this.inhibiteurNeutralisant = inhibiteurNeutralisant;
    }

    public Double getTitreInhibiteurUB() {
        return this.titreInhibiteurUB;
    }

    public FicheFacteurXI titreInhibiteurUB(Double titreInhibiteurUB) {
        this.setTitreInhibiteurUB(titreInhibiteurUB);
        return this;
    }

    public void setTitreInhibiteurUB(Double titreInhibiteurUB) {
        this.titreInhibiteurUB = titreInhibiteurUB;
    }

    public OuiNonNP getInhibiteurNonNeutralisant() {
        return this.inhibiteurNonNeutralisant;
    }

    public FicheFacteurXI inhibiteurNonNeutralisant(OuiNonNP inhibiteurNonNeutralisant) {
        this.setInhibiteurNonNeutralisant(inhibiteurNonNeutralisant);
        return this;
    }

    public void setInhibiteurNonNeutralisant(OuiNonNP inhibiteurNonNeutralisant) {
        this.inhibiteurNonNeutralisant = inhibiteurNonNeutralisant;
    }

    public OuiNonNP getInfectionVIH() {
        return this.infectionVIH;
    }

    public FicheFacteurXI infectionVIH(OuiNonNP infectionVIH) {
        this.setInfectionVIH(infectionVIH);
        return this;
    }

    public void setInfectionVIH(OuiNonNP infectionVIH) {
        this.infectionVIH = infectionVIH;
    }

    public OuiNonNP getInfectionVHC() {
        return this.infectionVHC;
    }

    public FicheFacteurXI infectionVHC(OuiNonNP infectionVHC) {
        this.setInfectionVHC(infectionVHC);
        return this;
    }

    public void setInfectionVHC(OuiNonNP infectionVHC) {
        this.infectionVHC = infectionVHC;
    }

    public OuiNonNP getInfectionVHB() {
        return this.infectionVHB;
    }

    public FicheFacteurXI infectionVHB(OuiNonNP infectionVHB) {
        this.setInfectionVHB(infectionVHB);
        return this;
    }

    public void setInfectionVHB(OuiNonNP infectionVHB) {
        this.infectionVHB = infectionVHB;
    }

    public Boolean getThrombose() {
        return this.thrombose;
    }

    public FicheFacteurXI thrombose(Boolean thrombose) {
        this.setThrombose(thrombose);
        return this;
    }

    public void setThrombose(Boolean thrombose) {
        this.thrombose = thrombose;
    }

    public Boolean getReactionAllergique() {
        return this.reactionAllergique;
    }

    public FicheFacteurXI reactionAllergique(Boolean reactionAllergique) {
        this.setReactionAllergique(reactionAllergique);
        return this;
    }

    public void setReactionAllergique(Boolean reactionAllergique) {
        this.reactionAllergique = reactionAllergique;
    }

    public String getAutresComplications() {
        return this.autresComplications;
    }

    public FicheFacteurXI autresComplications(String autresComplications) {
        this.setAutresComplications(autresComplications);
        return this;
    }

    public void setAutresComplications(String autresComplications) {
        this.autresComplications = autresComplications;
    }

    public OuiNonNP getAdherenceTherapeutique() {
        return this.adherenceTherapeutique;
    }

    public FicheFacteurXI adherenceTherapeutique(OuiNonNP adherenceTherapeutique) {
        this.setAdherenceTherapeutique(adherenceTherapeutique);
        return this;
    }

    public void setAdherenceTherapeutique(OuiNonNP adherenceTherapeutique) {
        this.adherenceTherapeutique = adherenceTherapeutique;
    }

    public String getCauseNonAdherence() {
        return this.causeNonAdherence;
    }

    public FicheFacteurXI causeNonAdherence(String causeNonAdherence) {
        this.setCauseNonAdherence(causeNonAdherence);
        return this;
    }

    public void setCauseNonAdherence(String causeNonAdherence) {
        this.causeNonAdherence = causeNonAdherence;
    }

    public StatutSocial getStatutSocial() {
        return this.statutSocial;
    }

    public FicheFacteurXI statutSocial(StatutSocial statutSocial) {
        this.setStatutSocial(statutSocial);
        return this;
    }

    public void setStatutSocial(StatutSocial statutSocial) {
        this.statutSocial = statutSocial;
    }

    public OuiNonNP getMarie() {
        return this.marie;
    }

    public FicheFacteurXI marie(OuiNonNP marie) {
        this.setMarie(marie);
        return this;
    }

    public void setMarie(OuiNonNP marie) {
        this.marie = marie;
    }

    public OuiNonNP getEnfants() {
        return this.enfants;
    }

    public FicheFacteurXI enfants(OuiNonNP enfants) {
        this.setEnfants(enfants);
        return this;
    }

    public void setEnfants(OuiNonNP enfants) {
        this.enfants = enfants;
    }

    public OuiNonNP getActivitePhysique() {
        return this.activitePhysique;
    }

    public FicheFacteurXI activitePhysique(OuiNonNP activitePhysique) {
        this.setActivitePhysique(activitePhysique);
        return this;
    }

    public void setActivitePhysique(OuiNonNP activitePhysique) {
        this.activitePhysique = activitePhysique;
    }

    public String getTypeActivitePhysique() {
        return this.typeActivitePhysique;
    }

    public FicheFacteurXI typeActivitePhysique(String typeActivitePhysique) {
        this.setTypeActivitePhysique(typeActivitePhysique);
        return this;
    }

    public void setTypeActivitePhysique(String typeActivitePhysique) {
        this.typeActivitePhysique = typeActivitePhysique;
    }

    public OuiNonNP getSequelles() {
        return this.sequelles;
    }

    public FicheFacteurXI sequelles(OuiNonNP sequelles) {
        this.setSequelles(sequelles);
        return this;
    }

    public void setSequelles(OuiNonNP sequelles) {
        this.sequelles = sequelles;
    }

    public String getTypeSequelles() {
        return this.typeSequelles;
    }

    public FicheFacteurXI typeSequelles(String typeSequelles) {
        this.setTypeSequelles(typeSequelles);
        return this;
    }

    public void setTypeSequelles(String typeSequelles) {
        this.typeSequelles = typeSequelles;
    }

    public OuiNonNP getPatientDecede() {
        return this.patientDecede;
    }

    public FicheFacteurXI patientDecede(OuiNonNP patientDecede) {
        this.setPatientDecede(patientDecede);
        return this;
    }

    public void setPatientDecede(OuiNonNP patientDecede) {
        this.patientDecede = patientDecede;
    }

    public LocalDate getDateDeces() {
        return this.dateDeces;
    }

    public FicheFacteurXI dateDeces(LocalDate dateDeces) {
        this.setDateDeces(dateDeces);
        return this;
    }

    public void setDateDeces(LocalDate dateDeces) {
        this.dateDeces = dateDeces;
    }

    public Integer getAgeDeces() {
        return this.ageDeces;
    }

    public FicheFacteurXI ageDeces(Integer ageDeces) {
        this.setAgeDeces(ageDeces);
        return this;
    }

    public void setAgeDeces(Integer ageDeces) {
        this.ageDeces = ageDeces;
    }

    public CauseDeces getCauseDeces() {
        return this.causeDeces;
    }

    public FicheFacteurXI causeDeces(CauseDeces causeDeces) {
        this.setCauseDeces(causeDeces);
        return this;
    }

    public void setCauseDeces(CauseDeces causeDeces) {
        this.causeDeces = causeDeces;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheFacteurXI)) {
            return false;
        }
        return getId() != null && getId().equals(((FicheFacteurXI) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheFacteurXI{" +
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
            ", syndromeHemorragique='" + getSyndromeHemorragique() + "'" +
            ", precisionSyndromeHemorragique='" + getPrecisionSyndromeHemorragique() + "'" +
            ", decouverteFortuite='" + getDecouverteFortuite() + "'" +
            ", precisionDecouverteFortuite='" + getPrecisionDecouverteFortuite() + "'" +
            ", jamaisSaigne='" + getJamaisSaigne() + "'" +
            ", agePremierSigneCliniqueJours=" + getAgePremierSigneCliniqueJours() +
            ", agePremierSigneCliniqueMois=" + getAgePremierSigneCliniqueMois() +
            ", agePremierSigneCliniqueAnnees=" + getAgePremierSigneCliniqueAnnees() +
            ", symptomeEcchymose='" + getSymptomeEcchymose() + "'" +
            ", symptomeEpistaxis='" + getSymptomeEpistaxis() + "'" +
            ", symptomeGingivorragies='" + getSymptomeGingivorragies() + "'" +
            ", symptomeMetrorragies='" + getSymptomeMetrorragies() + "'" +
            ", symptomeMelena='" + getSymptomeMelena() + "'" +
            ", symptomeHemoptysie='" + getSymptomeHemoptysie() + "'" +
            ", symptomeHemorragiePostAmygdalectomie='" + getSymptomeHemorragiePostAmygdalectomie() + "'" +
            ", symptomeHemorragiePostAvulsionDentaire='" + getSymptomeHemorragiePostAvulsionDentaire() + "'" +
            ", symptomeHemorragieChuteCordon='" + getSymptomeHemorragieChuteCordon() + "'" +
            ", symptomeAutre='" + getSymptomeAutre() + "'" +
            ", symptomeAutreDescription='" + getSymptomeAutreDescription() + "'" +
            ", datePremiereConsultation='" + getDatePremiereConsultation() + "'" +
            ", agePremiereConsultationJours=" + getAgePremiereConsultationJours() +
            ", agePremiereConsultationMois=" + getAgePremiereConsultationMois() +
            ", agePremiereConsultationAnnees=" + getAgePremiereConsultationAnnees() +
            ", ageDiagnosticJours=" + getAgeDiagnosticJours() +
            ", ageDiagnosticMois=" + getAgeDiagnosticMois() +
            ", ageDiagnosticAnnees=" + getAgeDiagnosticAnnees() +
            ", hb=" + getHb() +
            ", hte=" + getHte() +
            ", plaquettes=" + getPlaquettes() +
            ", pfaColEpi=" + getPfaColEpi() +
            ", pfaColADP=" + getPfaColADP() +
            ", tp=" + getTp() +
            ", tpMPlusT=" + getTpMPlusT() +
            ", fibrinogene=" + getFibrinogene() +
            ", tcaMT=" + getTcaMT() +
            ", tcaMPlusT=" + getTcaMPlusT() +
            ", tcaTemoin=" + getTcaTemoin() +
            ", facteurXI=" + getFacteurXI() +
            ", autresFacteursDosageDescription='" + getAutresFacteursDosageDescription() + "'" +
            ", autresFacteursTaux1=" + getAutresFacteursTaux1() +
            ", autresFacteursTaux2=" + getAutresFacteursTaux2() +
            ", autresFacteursTaux3=" + getAutresFacteursTaux3() +
            ", confirmationMoleculaire='" + getConfirmationMoleculaire() + "'" +
            ", mutationPresente='" + getMutationPresente() + "'" +
            ", mutationNom='" + getMutationNom() + "'" +
            ", mutationZygote='" + getMutationZygote() + "'" +
            ", mutation2Nom='" + getMutation2Nom() + "'" +
            ", serologieVHB='" + getSerologieVHB() + "'" +
            ", serologieVHC='" + getSerologieVHC() + "'" +
            ", serologieVIH='" + getSerologieVIH() + "'" +
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
            ", hemorragiePostTraumatique='" + getHemorragiePostTraumatique() + "'" +
            ", frequenceHemorragiePostTraumatique=" + getFrequenceHemorragiePostTraumatique() +
            ", hematomeSousCutane='" + getHematomeSousCutane() + "'" +
            ", typeHematomeSousCutane='" + getTypeHematomeSousCutane() + "'" +
            ", frequenceHematomeSousCutane=" + getFrequenceHematomeSousCutane() +
            ", hematomeMusculaire='" + getHematomeMusculaire() + "'" +
            ", typeHematomeMusculaire='" + getTypeHematomeMusculaire() + "'" +
            ", frequenceHematomeMusculaire=" + getFrequenceHematomeMusculaire() +
            ", hematomeTissusMousProfonds='" + getHematomeTissusMousProfonds() + "'" +
            ", typeHematomeTissusMousProfonds='" + getTypeHematomeTissusMousProfonds() + "'" +
            ", frequenceHematomeTissusMousProfonds=" + getFrequenceHematomeTissusMousProfonds() +
            ", hemarthrose='" + getHemarthrose() + "'" +
            ", typeHemarthrose='" + getTypeHemarthrose() + "'" +
            ", frequenceHemarthrose=" + getFrequenceHemarthrose() +
            ", saignementSNC='" + getSaignementSNC() + "'" +
            ", typeSaignementSNC='" + getTypeSaignementSNC() + "'" +
            ", frequenceSaignementSNC=" + getFrequenceSaignementSNC() +
            ", saignementChuteCordon='" + getSaignementChuteCordon() + "'" +
            ", frequenceSaignementChuteCordon=" + getFrequenceSaignementChuteCordon() +
            ", retardCicatrisationPlaies='" + getRetardCicatrisationPlaies() + "'" +
            ", frequenceRetardCicatrisationPlaies=" + getFrequenceRetardCicatrisationPlaies() +
            ", autresManifestations='" + getAutresManifestations() + "'" +
            ", faussesCouchesSpontaneesRecurrence='" + getFaussesCouchesSpontaneesRecurrence() + "'" +
            ", frequenceFaussesCouches=" + getFrequenceFaussesCouches() +
            ", accidentsThrombotiques='" + getAccidentsThrombotiques() + "'" +
            ", thromboseVeineuse='" + getThromboseVeineuse() + "'" +
            ", thromboseArterielle='" + getThromboseArterielle() + "'" +
            ", frequenceAccidentsThrombotiques=" + getFrequenceAccidentsThrombotiques() +
            ", hemoperitoine='" + getHemoperitoine() + "'" +
            ", frequenceHemoperitoine=" + getFrequenceHemoperitoine() +
            ", ruptureKystesHemorragiques='" + getRuptureKystesHemorragiques() + "'" +
            ", frequenceRuptureKystes=" + getFrequenceRuptureKystes() +
            ", hemorragieGrave='" + getHemorragieGrave() + "'" +
            ", frequenceHemorragieGrave=" + getFrequenceHemorragieGrave() +
            ", graviteDeglobulisation='" + getGraviteDeglobulisation() + "'" +
            ", graviteEtatDeChoc='" + getGraviteEtatDeChoc() + "'" +
            ", graviteParLocalisation='" + getGraviteParLocalisation() + "'" +
            ", siegeGraviteLocalisation='" + getSiegeGraviteLocalisation() + "'" +
            ", chirurgiePrepare='" + getChirurgiePrepare() + "'" +
            ", chirurgieSuiteHemorragique='" + getChirurgieSuiteHemorragique() + "'" +
            ", circoncisionPrepare='" + getCirconcisionPrepare() + "'" +
            ", circoncisionSuiteHemorragique='" + getCirconcisionSuiteHemorragique() + "'" +
            ", ageCirconcision=" + getAgeCirconcision() +
            ", traitementPFC='" + getTraitementPFC() + "'" +
            ", traitementCryoprecipite='" + getTraitementCryoprecipite() + "'" +
            ", traitementConcentreFacteurXI='" + getTraitementConcentreFacteurXI() + "'" +
            ", traitementCulotsPlaquettaires='" + getTraitementCulotsPlaquettaires() + "'" +
            ", traitementDicynone='" + getTraitementDicynone() + "'" +
            ", traitementExacyl='" + getTraitementExacyl() + "'" +
            ", traitementMartial='" + getTraitementMartial() + "'" +
            ", traitementOestroprogestatif='" + getTraitementOestroprogestatif() + "'" +
            ", transfusionCGR='" + getTransfusionCGR() + "'" +
            ", alloImmunisation='" + getAlloImmunisation() + "'" +
            ", inhibiteurNeutralisant='" + getInhibiteurNeutralisant() + "'" +
            ", titreInhibiteurUB=" + getTitreInhibiteurUB() +
            ", inhibiteurNonNeutralisant='" + getInhibiteurNonNeutralisant() + "'" +
            ", infectionVIH='" + getInfectionVIH() + "'" +
            ", infectionVHC='" + getInfectionVHC() + "'" +
            ", infectionVHB='" + getInfectionVHB() + "'" +
            ", thrombose='" + getThrombose() + "'" +
            ", reactionAllergique='" + getReactionAllergique() + "'" +
            ", autresComplications='" + getAutresComplications() + "'" +
            ", adherenceTherapeutique='" + getAdherenceTherapeutique() + "'" +
            ", causeNonAdherence='" + getCauseNonAdherence() + "'" +
            ", statutSocial='" + getStatutSocial() + "'" +
            ", marie='" + getMarie() + "'" +
            ", enfants='" + getEnfants() + "'" +
            ", activitePhysique='" + getActivitePhysique() + "'" +
            ", typeActivitePhysique='" + getTypeActivitePhysique() + "'" +
            ", sequelles='" + getSequelles() + "'" +
            ", typeSequelles='" + getTypeSequelles() + "'" +
            ", patientDecede='" + getPatientDecede() + "'" +
            ", dateDeces='" + getDateDeces() + "'" +
            ", ageDeces=" + getAgeDeces() +
            ", causeDeces='" + getCauseDeces() + "'" +
            "}";
    }
}

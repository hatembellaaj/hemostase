package tn.adhes.hemostase.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import tn.adhes.hemostase.domain.enumeration.CauseDeces;
import tn.adhes.hemostase.domain.enumeration.CirconstanceDecouverteMvW;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.GroupeSanguin;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.Region;
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

/**
 * A FicheVonWillebrand.
 */
@Entity
@Table(name = "fiche_von_willebrand")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheVonWillebrand implements Serializable {

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

    @Column(name = "date_enregistrement_registre")
    private LocalDate dateEnregistrementRegistre;

    @Enumerated(EnumType.STRING)
    @Column(name = "consentement_registre")
    private OuiNonNP consentementRegistre;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "circonstances_decouverte")
    private CirconstanceDecouverteMvW circonstancesDecouverte;

    @Size(max = 100)
    @Column(name = "circonstances_decouverte_precision", length = 100)
    private String circonstancesDecouvertePrecision;

    @Enumerated(EnumType.STRING)
    @Column(name = "jamais_saigne")
    private OuiNonNP jamaisSaigne;

    @Column(name = "age_premier_signe_clinique_jours")
    private Integer agePremierSigneCliniqueJours;

    @Column(name = "age_premier_signe_clinique_mois")
    private Integer agePremierSigneCliniqueMois;

    @Column(name = "age_premier_signe_clinique_annees")
    private Integer agePremierSigneCliniqueAnnees;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_ecchymose")
    private OuiNonNP symptomeEcchymose;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_epistaxis")
    private OuiNonNP symptomeEpistaxis;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_gingivorragies")
    private OuiNonNP symptomeGingivorragies;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_metrorragies")
    private OuiNonNP symptomeMetrorragies;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_melena")
    private OuiNonNP symptomeMelena;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_hemoptysie")
    private OuiNonNP symptomeHemoptysie;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_hemorragie_post_amygdalectomie")
    private OuiNonNP symptomeHemorragiePostAmygdalectomie;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_hemorragie_post_avulsion_dentaire")
    private OuiNonNP symptomeHemorragiePostAvulsionDentaire;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_hemorragie_chute_cordon")
    private OuiNonNP symptomeHemorragieChuteCordon;

    @Enumerated(EnumType.STRING)
    @Column(name = "symptome_autre")
    private OuiNonNP symptomeAutre;

    @Size(max = 255)
    @Column(name = "symptome_autre_description", length = 255)
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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type_mv_w", nullable = false)
    private TypeMvW typeMvW;

    @Enumerated(EnumType.STRING)
    @Column(name = "sous_type_mv_w")
    private SousTypeMvW sousTypeMvW;

    @Column(name = "pathologies_associees")
    private String pathologiesAssociees;

    @Column(name = "date_test_confirmation")
    private LocalDate dateTestConfirmation;

    @Enumerated(EnumType.STRING)
    @Column(name = "gs")
    private GroupeSanguin gs;

    @Column(name = "hb")
    private Double hb;

    @Column(name = "hte")
    private Double hte;

    @Column(name = "plaquettes")
    private Integer plaquettes;

    @Column(name = "tp")
    private Double tp;

    @Column(name = "tca_mt")
    private Double tcaMT;

    @Column(name = "tca_m_plus_t")
    private Double tcaMPlusT;

    @Column(name = "tca_m_plus_t_2_h")
    private Double tcaMPlusT2H;

    @Column(name = "fibrinogene")
    private Double fibrinogene;

    @Enumerated(EnumType.STRING)
    @Column(name = "ts_resultat")
    private TypeTS tsResultat;

    @Column(name = "ts_valeur")
    private Double tsValeur;

    @Enumerated(EnumType.STRING)
    @Column(name = "occlusion_plaquettaire")
    private TypeOcclusion occlusionPlaquettaire;

    @Column(name = "collagene_epinephrine")
    private Double collageneEpinephrine;

    @Column(name = "collagene_adp")
    private Double collageneADP;

    @Column(name = "fvwag")
    private Double fvwag;

    @Column(name = "fvwact")
    private Double fvwact;

    @Column(name = "f_8_c")
    private Double f8c;

    @Column(name = "ratio_fvwact_fvwag")
    private Double ratioFvwactFvwag;

    @Column(name = "ratio_f_8_c_fvwag")
    private Double ratioF8cFvwag;

    @Enumerated(EnumType.STRING)
    @Column(name = "autres_tests_specifiques")
    private FaiteNonFaite autresTestsSpecifiques;

    @Enumerated(EnumType.STRING)
    @Column(name = "vwf_cb")
    private FaiteNonFaite vwfCB;

    @Column(name = "vwf_cb_resultat")
    private Double vwfCBResultat;

    @Enumerated(EnumType.STRING)
    @Column(name = "ripa")
    private FaiteNonFaite ripa;

    @Enumerated(EnumType.STRING)
    @Column(name = "ripa_resultat")
    private TypeRipaResultat ripaResultat;

    @Enumerated(EnumType.STRING)
    @Column(name = "multimere_hpm")
    private TypeMultimere multimereHPM;

    @Enumerated(EnumType.STRING)
    @Column(name = "liaison_fvw_f_8")
    private FaiteNonFaite liaisonFvwF8;

    @Enumerated(EnumType.STRING)
    @Column(name = "liaison_fvw_f_8_resultat")
    private TypeResultatPatho liaisonFvwF8Resultat;

    @Enumerated(EnumType.STRING)
    @Column(name = "fvwpp")
    private FaiteNonFaite fvwpp;

    @Enumerated(EnumType.STRING)
    @Column(name = "fvwpp_resultat")
    private TypeFvWppRatio fvwppResultat;

    @Enumerated(EnumType.STRING)
    @Column(name = "mutation_genetique")
    private FaiteNonFaite mutationGenetique;

    @Column(name = "mutation_presente")
    private Boolean mutationPresente;

    @Column(name = "mutation_nom")
    private String mutationNom;

    @Enumerated(EnumType.STRING)
    @Column(name = "mutation_zygote")
    private TypeZygote mutationZygote;

    @Enumerated(EnumType.STRING)
    @Column(name = "ecchymoses")
    private OuiNonNP ecchymoses;

    @Column(name = "ecchymoses_spontanees")
    private Boolean ecchymosesSpontanees;

    @Column(name = "ecchymoses_provoquees")
    private Boolean ecchymosesProvoquees;

    @Column(name = "ecchymoses_siege")
    private String ecchymosesSiege;

    @Column(name = "ecchymoses_frequence")
    private Integer ecchymosesFrequence;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_muqueuses")
    private OuiNonNP hemorragieMuqueuses;

    @Enumerated(EnumType.STRING)
    @Column(name = "epistaxis")
    private OuiNonNP epistaxis;

    @Column(name = "epistaxis_frequence")
    private Integer epistaxisFrequence;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragies_intrabuccales")
    private OuiNonNP hemorragiesIntrabuccales;

    @Column(name = "hemorragies_intrabuccales_frequence")
    private Integer hemorragiesIntrabuccalesFrequence;

    @Enumerated(EnumType.STRING)
    @Column(name = "menoragie")
    private OuiNonNP menoragie;

    @Column(name = "menoragie_frequence")
    private Integer menoragieFrequence;

    @Column(name = "score_higham_fait")
    private Boolean scoreHighamFait;

    @Column(name = "score_higham_valeur")
    private Integer scoreHighamValeur;

    @Enumerated(EnumType.STRING)
    @Column(name = "hematome")
    private OuiNonNP hematome;

    @Column(name = "hematome_spontane")
    private Boolean hematomeSpontane;

    @Column(name = "hematome_provoque")
    private Boolean hematomeProvoque;

    @Column(name = "hematome_sous_cutane")
    private Boolean hematomeSousCutane;

    @Column(name = "hematome_musculaire")
    private Boolean hematomeMusculaire;

    @Column(name = "hematome_tissus_mous")
    private Boolean hematomeTissusMous;

    @Column(name = "hematome_frequence")
    private Integer hematomeFrequence;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemarthrose")
    private OuiNonNP hemarthrose;

    @Column(name = "hemarthrose_spontane")
    private Boolean hemarthroseSpontane;

    @Column(name = "hemarthrose_provoque")
    private Boolean hemarthroseProvoque;

    @Column(name = "hemarthrose_siege")
    private String hemarthroseSiege;

    @Column(name = "hemarthrose_frequence")
    private Integer hemarthroseFrequence;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_amygdalienne")
    private OuiNonNP hemorragieAmygdalienne;

    @Column(name = "hemorragie_amygdalienne_frequence")
    private Integer hemorragieAmygdalienneFrequence;

    @Enumerated(EnumType.STRING)
    @Column(name = "avulsion_dentaire")
    private OuiNonNP avulsionDentaire;

    @Column(name = "avulsion_prepare")
    private Boolean avulsionPrepare;

    @Enumerated(EnumType.STRING)
    @Column(name = "avulsion_bien_deroule")
    private OuiNonNP avulsionBienDeroule;

    @Enumerated(EnumType.STRING)
    @Column(name = "traumatisme")
    private OuiNonNP traumatisme;

    @Column(name = "traumatisme_type")
    private String traumatismeType;

    @Enumerated(EnumType.STRING)
    @Column(name = "chirurgie")
    private OuiNonNP chirurgie;

    @Column(name = "chirurgie_type")
    private String chirurgieType;

    @Enumerated(EnumType.STRING)
    @Column(name = "chirurgie_prepare")
    private OuiNonNP chirurgiePrepare;

    @Enumerated(EnumType.STRING)
    @Column(name = "chirurgie_bien_deroule")
    private OuiNonNP chirurgieBienDeroule;

    @Enumerated(EnumType.STRING)
    @Column(name = "accouchement")
    private OuiNonNP accouchement;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_postpartum")
    private OuiNonNP hemorragiePostpartum;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision")
    private OuiNonNP circoncision;

    @Column(name = "age_circoncision")
    private Integer ageCirconcision;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision_prepare")
    private OuiNonNP circoncisionPrepare;

    @Enumerated(EnumType.STRING)
    @Column(name = "circoncision_bien_deroule")
    private OuiNonNP circoncisionBienDeroule;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_visceres")
    private OuiNonNP hemorragieVisceres;

    @Column(name = "hemorragie_visceres_spontane")
    private Boolean hemorragieVisceresSpontane;

    @Column(name = "hemorragie_visceres_provoque")
    private Boolean hemorragieVisceresProvoque;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_visceres_type")
    private TypeHemorragieVisceres hemorragieVisceresType;

    @Column(name = "autre_hemorragie_visceres")
    private String autreHemorragieVisceres;

    @Column(name = "exploration_visceres")
    private Boolean explorationVisceres;

    @Column(name = "lesion_sous_jacente")
    private String lesionSousJacente;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_grave")
    private OuiNonNP hemorragieGrave;

    @Column(name = "grave_deglobulisation")
    private Boolean graveDeglobulisation;

    @Column(name = "grave_etat_de_choc")
    private Boolean graveEtatDeChoc;

    @Column(name = "grave_par_localisation")
    private Boolean graveParLocalisation;

    @Column(name = "siege_gravite_localisation")
    private String siegeGraviteLocalisation;

    @Column(name = "nombre_hospitalisations")
    private Integer nombreHospitalisations;

    @Column(name = "nombre_transfusions")
    private Integer nombreTransfusions;

    @Column(name = "score_isthbat")
    private Integer scoreISTHBAT;

    @Enumerated(EnumType.STRING)
    @Column(name = "desmopressine")
    private OuiNonNP desmopressine;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_substitutif_prevention")
    private OuiNonNP traitementSubstitutifPrevention;

    @Column(name = "traitement_preparation")
    private String traitementPreparation;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_substitutif_curatif")
    private OuiNonNP traitementSubstitutifCuratif;

    @Enumerated(EnumType.STRING)
    @Column(name = "cryoprecipite")
    private OuiNonNP cryoprecipite;

    @Enumerated(EnumType.STRING)
    @Column(name = "pfc")
    private OuiNonNP pfc;

    @Enumerated(EnumType.STRING)
    @Column(name = "concentre_fviiifvw")
    private OuiNonNP concentreFVIIIFVW;

    @Enumerated(EnumType.STRING)
    @Column(name = "concentre_fvw")
    private OuiNonNP concentreFVW;

    @Enumerated(EnumType.STRING)
    @Column(name = "concentre_fviii")
    private OuiNonNP concentreFVIII;

    @Enumerated(EnumType.STRING)
    @Column(name = "agent_bypassant")
    private OuiNonNP agentBypassant;

    @Enumerated(EnumType.STRING)
    @Column(name = "antifibrinolytiques")
    private OuiNonNP antifibrinolytiques;

    @Column(name = "voie_orale")
    private Boolean voieOrale;

    @Column(name = "voie_iv")
    private Boolean voieIV;

    @Column(name = "voie_locale")
    private Boolean voieLocale;

    @Enumerated(EnumType.STRING)
    @Column(name = "oestroprogestatifs")
    private OuiNonNP oestroprogestatifs;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_antalgique")
    private OuiNonNP traitementAntalgique;

    @Enumerated(EnumType.STRING)
    @Column(name = "corticotherapie")
    private OuiNonNP corticotherapie;

    @Enumerated(EnumType.STRING)
    @Column(name = "kinesitherapie")
    private OuiNonNP kinesitherapie;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_martial")
    private OuiNonNP traitementMartial;

    @Enumerated(EnumType.STRING)
    @Column(name = "culots_globulaires")
    private OuiNonNP culotsGlobulaires;

    @Column(name = "nombre_culots_globulaires")
    private Integer nombreCulotsGlobulaires;

    @Enumerated(EnumType.STRING)
    @Column(name = "immunosuppresseur")
    private OuiNonNP immunosuppresseur;

    @Column(name = "nom_immunosuppresseur")
    private String nomImmunosuppresseur;

    @Enumerated(EnumType.STRING)
    @Column(name = "anemie")
    private OuiNonNP anemie;

    @Column(name = "anemie_severe")
    private Boolean anemieSevere;

    @Column(name = "taux_hb")
    private Double tauxHb;

    @Enumerated(EnumType.STRING)
    @Column(name = "anticorps_anti_fvw")
    private ResultatSerologie anticorpsAntiFVW;

    @Enumerated(EnumType.STRING)
    @Column(name = "anticorps_anti_fviii")
    private ResultatSerologie anticorpsAntiFVIII;

    @Enumerated(EnumType.STRING)
    @Column(name = "hepatite_b")
    private ResultatSerologie hepatiteB;

    @Enumerated(EnumType.STRING)
    @Column(name = "hepatite_c")
    private ResultatSerologie hepatiteC;

    @Enumerated(EnumType.STRING)
    @Column(name = "hiv")
    private ResultatSerologie hiv;

    @Column(name = "thrombose")
    private Boolean thrombose;

    @Column(name = "reaction_allergique")
    private Boolean reactionAllergique;

    @Column(name = "autre_complication")
    private Boolean autreComplication;

    @Column(name = "autre_complication_description")
    private String autreComplicationDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "adherence_therapeutique")
    private OuiNonNP adherenceTherapeutique;

    @Column(name = "cause_non_adherence")
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

    @Column(name = "type_activite_physique")
    private String typeActivitePhysique;

    @Enumerated(EnumType.STRING)
    @Column(name = "sequelles")
    private OuiNonNP sequelles;

    @Column(name = "type_sequelles")
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

    public FicheVonWillebrand id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDossierNumber() {
        return this.dossierNumber;
    }

    public FicheVonWillebrand dossierNumber(String dossierNumber) {
        this.setDossierNumber(dossierNumber);
        return this;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return this.ordreNumber;
    }

    public FicheVonWillebrand ordreNumber(String ordreNumber) {
        this.setOrdreNumber(ordreNumber);
        return this;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return this.indexNumber;
    }

    public FicheVonWillebrand indexNumber(String indexNumber) {
        this.setIndexNumber(indexNumber);
        return this;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public FicheVonWillebrand prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public FicheVonWillebrand nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomJeuneFille() {
        return this.nomJeuneFille;
    }

    public FicheVonWillebrand nomJeuneFille(String nomJeuneFille) {
        this.setNomJeuneFille(nomJeuneFille);
        return this;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public String getProfession() {
        return this.profession;
    }

    public FicheVonWillebrand profession(String profession) {
        this.setProfession(profession);
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public CivilStatus getEtatCivil() {
        return this.etatCivil;
    }

    public FicheVonWillebrand etatCivil(CivilStatus etatCivil) {
        this.setEtatCivil(etatCivil);
        return this;
    }

    public void setEtatCivil(CivilStatus etatCivil) {
        this.etatCivil = etatCivil;
    }

    public ServiceCliniqueType getServiceClinique() {
        return this.serviceClinique;
    }

    public FicheVonWillebrand serviceClinique(ServiceCliniqueType serviceClinique) {
        this.setServiceClinique(serviceClinique);
        return this;
    }

    public void setServiceClinique(ServiceCliniqueType serviceClinique) {
        this.serviceClinique = serviceClinique;
    }

    public String getAutreServiceClinique() {
        return this.autreServiceClinique;
    }

    public FicheVonWillebrand autreServiceClinique(String autreServiceClinique) {
        this.setAutreServiceClinique(autreServiceClinique);
        return this;
    }

    public void setAutreServiceClinique(String autreServiceClinique) {
        this.autreServiceClinique = autreServiceClinique;
    }

    public CouvertureSociale getCouvertureSociale() {
        return this.couvertureSociale;
    }

    public FicheVonWillebrand couvertureSociale(CouvertureSociale couvertureSociale) {
        this.setCouvertureSociale(couvertureSociale);
        return this;
    }

    public void setCouvertureSociale(CouvertureSociale couvertureSociale) {
        this.couvertureSociale = couvertureSociale;
    }

    public Gender getSexe() {
        return this.sexe;
    }

    public FicheVonWillebrand sexe(Gender sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Gender sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public FicheVonWillebrand dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Integer getAgeActuel() {
        return this.ageActuel;
    }

    public FicheVonWillebrand ageActuel(Integer ageActuel) {
        this.setAgeActuel(ageActuel);
        return this;
    }

    public void setAgeActuel(Integer ageActuel) {
        this.ageActuel = ageActuel;
    }

    public Region getOrigine() {
        return this.origine;
    }

    public FicheVonWillebrand origine(Region origine) {
        this.setOrigine(origine);
        return this;
    }

    public void setOrigine(Region origine) {
        this.origine = origine;
    }

    public String getAutreOrigine() {
        return this.autreOrigine;
    }

    public FicheVonWillebrand autreOrigine(String autreOrigine) {
        this.setAutreOrigine(autreOrigine);
        return this;
    }

    public void setAutreOrigine(String autreOrigine) {
        this.autreOrigine = autreOrigine;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public FicheVonWillebrand adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public FicheVonWillebrand telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrenomPere() {
        return this.prenomPere;
    }

    public FicheVonWillebrand prenomPere(String prenomPere) {
        this.setPrenomPere(prenomPere);
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getProfessionPere() {
        return this.professionPere;
    }

    public FicheVonWillebrand professionPere(String professionPere) {
        this.setProfessionPere(professionPere);
        return this;
    }

    public void setProfessionPere(String professionPere) {
        this.professionPere = professionPere;
    }

    public String getNomPrenomMere() {
        return this.nomPrenomMere;
    }

    public FicheVonWillebrand nomPrenomMere(String nomPrenomMere) {
        this.setNomPrenomMere(nomPrenomMere);
        return this;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }

    public String getProfessionMere() {
        return this.professionMere;
    }

    public FicheVonWillebrand professionMere(String professionMere) {
        this.setProfessionMere(professionMere);
        return this;
    }

    public void setProfessionMere(String professionMere) {
        this.professionMere = professionMere;
    }

    public String getMedecinTraitant() {
        return this.medecinTraitant;
    }

    public FicheVonWillebrand medecinTraitant(String medecinTraitant) {
        this.setMedecinTraitant(medecinTraitant);
        return this;
    }

    public void setMedecinTraitant(String medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }

    public String getServiceOrigine() {
        return this.serviceOrigine;
    }

    public FicheVonWillebrand serviceOrigine(String serviceOrigine) {
        this.setServiceOrigine(serviceOrigine);
        return this;
    }

    public void setServiceOrigine(String serviceOrigine) {
        this.serviceOrigine = serviceOrigine;
    }

    public String getBiologisteResponsableDg() {
        return this.biologisteResponsableDg;
    }

    public FicheVonWillebrand biologisteResponsableDg(String biologisteResponsableDg) {
        this.setBiologisteResponsableDg(biologisteResponsableDg);
        return this;
    }

    public void setBiologisteResponsableDg(String biologisteResponsableDg) {
        this.biologisteResponsableDg = biologisteResponsableDg;
    }

    public DiagnosticType getDiagnostic() {
        return this.diagnostic;
    }

    public FicheVonWillebrand diagnostic(DiagnosticType diagnostic) {
        this.setDiagnostic(diagnostic);
        return this;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return this.autreDiagnostic;
    }

    public FicheVonWillebrand autreDiagnostic(String autreDiagnostic) {
        this.setAutreDiagnostic(autreDiagnostic);
        return this;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public Integer getAnneeDiagnostic() {
        return this.anneeDiagnostic;
    }

    public FicheVonWillebrand anneeDiagnostic(Integer anneeDiagnostic) {
        this.setAnneeDiagnostic(anneeDiagnostic);
        return this;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return this.dateEnregistrementRegistre;
    }

    public FicheVonWillebrand dateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.setDateEnregistrementRegistre(dateEnregistrementRegistre);
        return this;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
    }

    public OuiNonNP getConsentementRegistre() {
        return this.consentementRegistre;
    }

    public FicheVonWillebrand consentementRegistre(OuiNonNP consentementRegistre) {
        this.setConsentementRegistre(consentementRegistre);
        return this;
    }

    public void setConsentementRegistre(OuiNonNP consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public OuiNonNP getParentsConsanguins() {
        return this.parentsConsanguins;
    }

    public FicheVonWillebrand parentsConsanguins(OuiNonNP parentsConsanguins) {
        this.setParentsConsanguins(parentsConsanguins);
        return this;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public DegreParente getDegreParenteConsanguins() {
        return this.degreParenteConsanguins;
    }

    public FicheVonWillebrand degreParenteConsanguins(DegreParente degreParenteConsanguins) {
        this.setDegreParenteConsanguins(degreParenteConsanguins);
        return this;
    }

    public void setDegreParenteConsanguins(DegreParente degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public OuiNonNP getCasSimilaires() {
        return this.casSimilaires;
    }

    public FicheVonWillebrand casSimilaires(OuiNonNP casSimilaires) {
        this.setCasSimilaires(casSimilaires);
        return this;
    }

    public void setCasSimilaires(OuiNonNP casSimilaires) {
        this.casSimilaires = casSimilaires;
    }

    public Integer getNombreCas() {
        return this.nombreCas;
    }

    public FicheVonWillebrand nombreCas(Integer nombreCas) {
        this.setNombreCas(nombreCas);
        return this;
    }

    public void setNombreCas(Integer nombreCas) {
        this.nombreCas = nombreCas;
    }

    public Integer getCas1Numero() {
        return this.cas1Numero;
    }

    public FicheVonWillebrand cas1Numero(Integer cas1Numero) {
        this.setCas1Numero(cas1Numero);
        return this;
    }

    public void setCas1Numero(Integer cas1Numero) {
        this.cas1Numero = cas1Numero;
    }

    public LienParente getLienParenteCas1() {
        return this.lienParenteCas1;
    }

    public FicheVonWillebrand lienParenteCas1(LienParente lienParenteCas1) {
        this.setLienParenteCas1(lienParenteCas1);
        return this;
    }

    public void setLienParenteCas1(LienParente lienParenteCas1) {
        this.lienParenteCas1 = lienParenteCas1;
    }

    public String getDegreParenteCas1() {
        return this.degreParenteCas1;
    }

    public FicheVonWillebrand degreParenteCas1(String degreParenteCas1) {
        this.setDegreParenteCas1(degreParenteCas1);
        return this;
    }

    public void setDegreParenteCas1(String degreParenteCas1) {
        this.degreParenteCas1 = degreParenteCas1;
    }

    public String getTypeHemorragieCas1() {
        return this.typeHemorragieCas1;
    }

    public FicheVonWillebrand typeHemorragieCas1(String typeHemorragieCas1) {
        this.setTypeHemorragieCas1(typeHemorragieCas1);
        return this;
    }

    public void setTypeHemorragieCas1(String typeHemorragieCas1) {
        this.typeHemorragieCas1 = typeHemorragieCas1;
    }

    public Integer getCas2Numero() {
        return this.cas2Numero;
    }

    public FicheVonWillebrand cas2Numero(Integer cas2Numero) {
        this.setCas2Numero(cas2Numero);
        return this;
    }

    public void setCas2Numero(Integer cas2Numero) {
        this.cas2Numero = cas2Numero;
    }

    public LienParente getLienParenteCas2() {
        return this.lienParenteCas2;
    }

    public FicheVonWillebrand lienParenteCas2(LienParente lienParenteCas2) {
        this.setLienParenteCas2(lienParenteCas2);
        return this;
    }

    public void setLienParenteCas2(LienParente lienParenteCas2) {
        this.lienParenteCas2 = lienParenteCas2;
    }

    public String getDegreParenteCas2() {
        return this.degreParenteCas2;
    }

    public FicheVonWillebrand degreParenteCas2(String degreParenteCas2) {
        this.setDegreParenteCas2(degreParenteCas2);
        return this;
    }

    public void setDegreParenteCas2(String degreParenteCas2) {
        this.degreParenteCas2 = degreParenteCas2;
    }

    public String getTypeHemorragieCas2() {
        return this.typeHemorragieCas2;
    }

    public FicheVonWillebrand typeHemorragieCas2(String typeHemorragieCas2) {
        this.setTypeHemorragieCas2(typeHemorragieCas2);
        return this;
    }

    public void setTypeHemorragieCas2(String typeHemorragieCas2) {
        this.typeHemorragieCas2 = typeHemorragieCas2;
    }

    public Integer getCas3Numero() {
        return this.cas3Numero;
    }

    public FicheVonWillebrand cas3Numero(Integer cas3Numero) {
        this.setCas3Numero(cas3Numero);
        return this;
    }

    public void setCas3Numero(Integer cas3Numero) {
        this.cas3Numero = cas3Numero;
    }

    public LienParente getLienParenteCas3() {
        return this.lienParenteCas3;
    }

    public FicheVonWillebrand lienParenteCas3(LienParente lienParenteCas3) {
        this.setLienParenteCas3(lienParenteCas3);
        return this;
    }

    public void setLienParenteCas3(LienParente lienParenteCas3) {
        this.lienParenteCas3 = lienParenteCas3;
    }

    public String getDegreParenteCas3() {
        return this.degreParenteCas3;
    }

    public FicheVonWillebrand degreParenteCas3(String degreParenteCas3) {
        this.setDegreParenteCas3(degreParenteCas3);
        return this;
    }

    public void setDegreParenteCas3(String degreParenteCas3) {
        this.degreParenteCas3 = degreParenteCas3;
    }

    public String getTypeHemorragieCas3() {
        return this.typeHemorragieCas3;
    }

    public FicheVonWillebrand typeHemorragieCas3(String typeHemorragieCas3) {
        this.setTypeHemorragieCas3(typeHemorragieCas3);
        return this;
    }

    public void setTypeHemorragieCas3(String typeHemorragieCas3) {
        this.typeHemorragieCas3 = typeHemorragieCas3;
    }

    public OuiNonNP getDecesSyndromeHemorragique() {
        return this.decesSyndromeHemorragique;
    }

    public FicheVonWillebrand decesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.setDecesSyndromeHemorragique(decesSyndromeHemorragique);
        return this;
    }

    public void setDecesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.decesSyndromeHemorragique = decesSyndromeHemorragique;
    }

    public Integer getNombreDeces() {
        return this.nombreDeces;
    }

    public FicheVonWillebrand nombreDeces(Integer nombreDeces) {
        this.setNombreDeces(nombreDeces);
        return this;
    }

    public void setNombreDeces(Integer nombreDeces) {
        this.nombreDeces = nombreDeces;
    }

    public Integer getDeces1Numero() {
        return this.deces1Numero;
    }

    public FicheVonWillebrand deces1Numero(Integer deces1Numero) {
        this.setDeces1Numero(deces1Numero);
        return this;
    }

    public void setDeces1Numero(Integer deces1Numero) {
        this.deces1Numero = deces1Numero;
    }

    public LienParente getLienParenteDeces1() {
        return this.lienParenteDeces1;
    }

    public FicheVonWillebrand lienParenteDeces1(LienParente lienParenteDeces1) {
        this.setLienParenteDeces1(lienParenteDeces1);
        return this;
    }

    public void setLienParenteDeces1(LienParente lienParenteDeces1) {
        this.lienParenteDeces1 = lienParenteDeces1;
    }

    public String getCauseDeces1() {
        return this.causeDeces1;
    }

    public FicheVonWillebrand causeDeces1(String causeDeces1) {
        this.setCauseDeces1(causeDeces1);
        return this;
    }

    public void setCauseDeces1(String causeDeces1) {
        this.causeDeces1 = causeDeces1;
    }

    public Integer getDeces2Numero() {
        return this.deces2Numero;
    }

    public FicheVonWillebrand deces2Numero(Integer deces2Numero) {
        this.setDeces2Numero(deces2Numero);
        return this;
    }

    public void setDeces2Numero(Integer deces2Numero) {
        this.deces2Numero = deces2Numero;
    }

    public LienParente getLienParenteDeces2() {
        return this.lienParenteDeces2;
    }

    public FicheVonWillebrand lienParenteDeces2(LienParente lienParenteDeces2) {
        this.setLienParenteDeces2(lienParenteDeces2);
        return this;
    }

    public void setLienParenteDeces2(LienParente lienParenteDeces2) {
        this.lienParenteDeces2 = lienParenteDeces2;
    }

    public String getCauseDeces2() {
        return this.causeDeces2;
    }

    public FicheVonWillebrand causeDeces2(String causeDeces2) {
        this.setCauseDeces2(causeDeces2);
        return this;
    }

    public void setCauseDeces2(String causeDeces2) {
        this.causeDeces2 = causeDeces2;
    }

    public Integer getDeces3Numero() {
        return this.deces3Numero;
    }

    public FicheVonWillebrand deces3Numero(Integer deces3Numero) {
        this.setDeces3Numero(deces3Numero);
        return this;
    }

    public void setDeces3Numero(Integer deces3Numero) {
        this.deces3Numero = deces3Numero;
    }

    public LienParente getLienParenteDeces3() {
        return this.lienParenteDeces3;
    }

    public FicheVonWillebrand lienParenteDeces3(LienParente lienParenteDeces3) {
        this.setLienParenteDeces3(lienParenteDeces3);
        return this;
    }

    public void setLienParenteDeces3(LienParente lienParenteDeces3) {
        this.lienParenteDeces3 = lienParenteDeces3;
    }

    public String getCauseDeces3() {
        return this.causeDeces3;
    }

    public FicheVonWillebrand causeDeces3(String causeDeces3) {
        this.setCauseDeces3(causeDeces3);
        return this;
    }

    public void setCauseDeces3(String causeDeces3) {
        this.causeDeces3 = causeDeces3;
    }

    public FaiteNonFaite getEnqueteFamiliale() {
        return this.enqueteFamiliale;
    }

    public FicheVonWillebrand enqueteFamiliale(FaiteNonFaite enqueteFamiliale) {
        this.setEnqueteFamiliale(enqueteFamiliale);
        return this;
    }

    public void setEnqueteFamiliale(FaiteNonFaite enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public CirconstanceDecouverteMvW getCirconstancesDecouverte() {
        return this.circonstancesDecouverte;
    }

    public FicheVonWillebrand circonstancesDecouverte(CirconstanceDecouverteMvW circonstancesDecouverte) {
        this.setCirconstancesDecouverte(circonstancesDecouverte);
        return this;
    }

    public void setCirconstancesDecouverte(CirconstanceDecouverteMvW circonstancesDecouverte) {
        this.circonstancesDecouverte = circonstancesDecouverte;
    }

    public String getCirconstancesDecouvertePrecision() {
        return this.circonstancesDecouvertePrecision;
    }

    public FicheVonWillebrand circonstancesDecouvertePrecision(String circonstancesDecouvertePrecision) {
        this.setCirconstancesDecouvertePrecision(circonstancesDecouvertePrecision);
        return this;
    }

    public void setCirconstancesDecouvertePrecision(String circonstancesDecouvertePrecision) {
        this.circonstancesDecouvertePrecision = circonstancesDecouvertePrecision;
    }

    public OuiNonNP getJamaisSaigne() {
        return this.jamaisSaigne;
    }

    public FicheVonWillebrand jamaisSaigne(OuiNonNP jamaisSaigne) {
        this.setJamaisSaigne(jamaisSaigne);
        return this;
    }

    public void setJamaisSaigne(OuiNonNP jamaisSaigne) {
        this.jamaisSaigne = jamaisSaigne;
    }

    public Integer getAgePremierSigneCliniqueJours() {
        return this.agePremierSigneCliniqueJours;
    }

    public FicheVonWillebrand agePremierSigneCliniqueJours(Integer agePremierSigneCliniqueJours) {
        this.setAgePremierSigneCliniqueJours(agePremierSigneCliniqueJours);
        return this;
    }

    public void setAgePremierSigneCliniqueJours(Integer agePremierSigneCliniqueJours) {
        this.agePremierSigneCliniqueJours = agePremierSigneCliniqueJours;
    }

    public Integer getAgePremierSigneCliniqueMois() {
        return this.agePremierSigneCliniqueMois;
    }

    public FicheVonWillebrand agePremierSigneCliniqueMois(Integer agePremierSigneCliniqueMois) {
        this.setAgePremierSigneCliniqueMois(agePremierSigneCliniqueMois);
        return this;
    }

    public void setAgePremierSigneCliniqueMois(Integer agePremierSigneCliniqueMois) {
        this.agePremierSigneCliniqueMois = agePremierSigneCliniqueMois;
    }

    public Integer getAgePremierSigneCliniqueAnnees() {
        return this.agePremierSigneCliniqueAnnees;
    }

    public FicheVonWillebrand agePremierSigneCliniqueAnnees(Integer agePremierSigneCliniqueAnnees) {
        this.setAgePremierSigneCliniqueAnnees(agePremierSigneCliniqueAnnees);
        return this;
    }

    public void setAgePremierSigneCliniqueAnnees(Integer agePremierSigneCliniqueAnnees) {
        this.agePremierSigneCliniqueAnnees = agePremierSigneCliniqueAnnees;
    }

    public OuiNonNP getSymptomeEcchymose() {
        return this.symptomeEcchymose;
    }

    public FicheVonWillebrand symptomeEcchymose(OuiNonNP symptomeEcchymose) {
        this.setSymptomeEcchymose(symptomeEcchymose);
        return this;
    }

    public void setSymptomeEcchymose(OuiNonNP symptomeEcchymose) {
        this.symptomeEcchymose = symptomeEcchymose;
    }

    public OuiNonNP getSymptomeEpistaxis() {
        return this.symptomeEpistaxis;
    }

    public FicheVonWillebrand symptomeEpistaxis(OuiNonNP symptomeEpistaxis) {
        this.setSymptomeEpistaxis(symptomeEpistaxis);
        return this;
    }

    public void setSymptomeEpistaxis(OuiNonNP symptomeEpistaxis) {
        this.symptomeEpistaxis = symptomeEpistaxis;
    }

    public OuiNonNP getSymptomeGingivorragies() {
        return this.symptomeGingivorragies;
    }

    public FicheVonWillebrand symptomeGingivorragies(OuiNonNP symptomeGingivorragies) {
        this.setSymptomeGingivorragies(symptomeGingivorragies);
        return this;
    }

    public void setSymptomeGingivorragies(OuiNonNP symptomeGingivorragies) {
        this.symptomeGingivorragies = symptomeGingivorragies;
    }

    public OuiNonNP getSymptomeMetrorragies() {
        return this.symptomeMetrorragies;
    }

    public FicheVonWillebrand symptomeMetrorragies(OuiNonNP symptomeMetrorragies) {
        this.setSymptomeMetrorragies(symptomeMetrorragies);
        return this;
    }

    public void setSymptomeMetrorragies(OuiNonNP symptomeMetrorragies) {
        this.symptomeMetrorragies = symptomeMetrorragies;
    }

    public OuiNonNP getSymptomeMelena() {
        return this.symptomeMelena;
    }

    public FicheVonWillebrand symptomeMelena(OuiNonNP symptomeMelena) {
        this.setSymptomeMelena(symptomeMelena);
        return this;
    }

    public void setSymptomeMelena(OuiNonNP symptomeMelena) {
        this.symptomeMelena = symptomeMelena;
    }

    public OuiNonNP getSymptomeHemoptysie() {
        return this.symptomeHemoptysie;
    }

    public FicheVonWillebrand symptomeHemoptysie(OuiNonNP symptomeHemoptysie) {
        this.setSymptomeHemoptysie(symptomeHemoptysie);
        return this;
    }

    public void setSymptomeHemoptysie(OuiNonNP symptomeHemoptysie) {
        this.symptomeHemoptysie = symptomeHemoptysie;
    }

    public OuiNonNP getSymptomeHemorragiePostAmygdalectomie() {
        return this.symptomeHemorragiePostAmygdalectomie;
    }

    public FicheVonWillebrand symptomeHemorragiePostAmygdalectomie(OuiNonNP symptomeHemorragiePostAmygdalectomie) {
        this.setSymptomeHemorragiePostAmygdalectomie(symptomeHemorragiePostAmygdalectomie);
        return this;
    }

    public void setSymptomeHemorragiePostAmygdalectomie(OuiNonNP symptomeHemorragiePostAmygdalectomie) {
        this.symptomeHemorragiePostAmygdalectomie = symptomeHemorragiePostAmygdalectomie;
    }

    public OuiNonNP getSymptomeHemorragiePostAvulsionDentaire() {
        return this.symptomeHemorragiePostAvulsionDentaire;
    }

    public FicheVonWillebrand symptomeHemorragiePostAvulsionDentaire(OuiNonNP symptomeHemorragiePostAvulsionDentaire) {
        this.setSymptomeHemorragiePostAvulsionDentaire(symptomeHemorragiePostAvulsionDentaire);
        return this;
    }

    public void setSymptomeHemorragiePostAvulsionDentaire(OuiNonNP symptomeHemorragiePostAvulsionDentaire) {
        this.symptomeHemorragiePostAvulsionDentaire = symptomeHemorragiePostAvulsionDentaire;
    }

    public OuiNonNP getSymptomeHemorragieChuteCordon() {
        return this.symptomeHemorragieChuteCordon;
    }

    public FicheVonWillebrand symptomeHemorragieChuteCordon(OuiNonNP symptomeHemorragieChuteCordon) {
        this.setSymptomeHemorragieChuteCordon(symptomeHemorragieChuteCordon);
        return this;
    }

    public void setSymptomeHemorragieChuteCordon(OuiNonNP symptomeHemorragieChuteCordon) {
        this.symptomeHemorragieChuteCordon = symptomeHemorragieChuteCordon;
    }

    public OuiNonNP getSymptomeAutre() {
        return this.symptomeAutre;
    }

    public FicheVonWillebrand symptomeAutre(OuiNonNP symptomeAutre) {
        this.setSymptomeAutre(symptomeAutre);
        return this;
    }

    public void setSymptomeAutre(OuiNonNP symptomeAutre) {
        this.symptomeAutre = symptomeAutre;
    }

    public String getSymptomeAutreDescription() {
        return this.symptomeAutreDescription;
    }

    public FicheVonWillebrand symptomeAutreDescription(String symptomeAutreDescription) {
        this.setSymptomeAutreDescription(symptomeAutreDescription);
        return this;
    }

    public void setSymptomeAutreDescription(String symptomeAutreDescription) {
        this.symptomeAutreDescription = symptomeAutreDescription;
    }

    public LocalDate getDatePremiereConsultation() {
        return this.datePremiereConsultation;
    }

    public FicheVonWillebrand datePremiereConsultation(LocalDate datePremiereConsultation) {
        this.setDatePremiereConsultation(datePremiereConsultation);
        return this;
    }

    public void setDatePremiereConsultation(LocalDate datePremiereConsultation) {
        this.datePremiereConsultation = datePremiereConsultation;
    }

    public Integer getAgePremiereConsultationJours() {
        return this.agePremiereConsultationJours;
    }

    public FicheVonWillebrand agePremiereConsultationJours(Integer agePremiereConsultationJours) {
        this.setAgePremiereConsultationJours(agePremiereConsultationJours);
        return this;
    }

    public void setAgePremiereConsultationJours(Integer agePremiereConsultationJours) {
        this.agePremiereConsultationJours = agePremiereConsultationJours;
    }

    public Integer getAgePremiereConsultationMois() {
        return this.agePremiereConsultationMois;
    }

    public FicheVonWillebrand agePremiereConsultationMois(Integer agePremiereConsultationMois) {
        this.setAgePremiereConsultationMois(agePremiereConsultationMois);
        return this;
    }

    public void setAgePremiereConsultationMois(Integer agePremiereConsultationMois) {
        this.agePremiereConsultationMois = agePremiereConsultationMois;
    }

    public Integer getAgePremiereConsultationAnnees() {
        return this.agePremiereConsultationAnnees;
    }

    public FicheVonWillebrand agePremiereConsultationAnnees(Integer agePremiereConsultationAnnees) {
        this.setAgePremiereConsultationAnnees(agePremiereConsultationAnnees);
        return this;
    }

    public void setAgePremiereConsultationAnnees(Integer agePremiereConsultationAnnees) {
        this.agePremiereConsultationAnnees = agePremiereConsultationAnnees;
    }

    public Integer getAgeDiagnosticJours() {
        return this.ageDiagnosticJours;
    }

    public FicheVonWillebrand ageDiagnosticJours(Integer ageDiagnosticJours) {
        this.setAgeDiagnosticJours(ageDiagnosticJours);
        return this;
    }

    public void setAgeDiagnosticJours(Integer ageDiagnosticJours) {
        this.ageDiagnosticJours = ageDiagnosticJours;
    }

    public Integer getAgeDiagnosticMois() {
        return this.ageDiagnosticMois;
    }

    public FicheVonWillebrand ageDiagnosticMois(Integer ageDiagnosticMois) {
        this.setAgeDiagnosticMois(ageDiagnosticMois);
        return this;
    }

    public void setAgeDiagnosticMois(Integer ageDiagnosticMois) {
        this.ageDiagnosticMois = ageDiagnosticMois;
    }

    public Integer getAgeDiagnosticAnnees() {
        return this.ageDiagnosticAnnees;
    }

    public FicheVonWillebrand ageDiagnosticAnnees(Integer ageDiagnosticAnnees) {
        this.setAgeDiagnosticAnnees(ageDiagnosticAnnees);
        return this;
    }

    public void setAgeDiagnosticAnnees(Integer ageDiagnosticAnnees) {
        this.ageDiagnosticAnnees = ageDiagnosticAnnees;
    }

    public TypeMvW getTypeMvW() {
        return this.typeMvW;
    }

    public FicheVonWillebrand typeMvW(TypeMvW typeMvW) {
        this.setTypeMvW(typeMvW);
        return this;
    }

    public void setTypeMvW(TypeMvW typeMvW) {
        this.typeMvW = typeMvW;
    }

    public SousTypeMvW getSousTypeMvW() {
        return this.sousTypeMvW;
    }

    public FicheVonWillebrand sousTypeMvW(SousTypeMvW sousTypeMvW) {
        this.setSousTypeMvW(sousTypeMvW);
        return this;
    }

    public void setSousTypeMvW(SousTypeMvW sousTypeMvW) {
        this.sousTypeMvW = sousTypeMvW;
    }

    public String getPathologiesAssociees() {
        return this.pathologiesAssociees;
    }

    public FicheVonWillebrand pathologiesAssociees(String pathologiesAssociees) {
        this.setPathologiesAssociees(pathologiesAssociees);
        return this;
    }

    public void setPathologiesAssociees(String pathologiesAssociees) {
        this.pathologiesAssociees = pathologiesAssociees;
    }

    public LocalDate getDateTestConfirmation() {
        return this.dateTestConfirmation;
    }

    public FicheVonWillebrand dateTestConfirmation(LocalDate dateTestConfirmation) {
        this.setDateTestConfirmation(dateTestConfirmation);
        return this;
    }

    public void setDateTestConfirmation(LocalDate dateTestConfirmation) {
        this.dateTestConfirmation = dateTestConfirmation;
    }

    public GroupeSanguin getGs() {
        return this.gs;
    }

    public FicheVonWillebrand gs(GroupeSanguin gs) {
        this.setGs(gs);
        return this;
    }

    public void setGs(GroupeSanguin gs) {
        this.gs = gs;
    }

    public Double getHb() {
        return this.hb;
    }

    public FicheVonWillebrand hb(Double hb) {
        this.setHb(hb);
        return this;
    }

    public void setHb(Double hb) {
        this.hb = hb;
    }

    public Double getHte() {
        return this.hte;
    }

    public FicheVonWillebrand hte(Double hte) {
        this.setHte(hte);
        return this;
    }

    public void setHte(Double hte) {
        this.hte = hte;
    }

    public Integer getPlaquettes() {
        return this.plaquettes;
    }

    public FicheVonWillebrand plaquettes(Integer plaquettes) {
        this.setPlaquettes(plaquettes);
        return this;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Double getTp() {
        return this.tp;
    }

    public FicheVonWillebrand tp(Double tp) {
        this.setTp(tp);
        return this;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getTcaMT() {
        return this.tcaMT;
    }

    public FicheVonWillebrand tcaMT(Double tcaMT) {
        this.setTcaMT(tcaMT);
        return this;
    }

    public void setTcaMT(Double tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Double getTcaMPlusT() {
        return this.tcaMPlusT;
    }

    public FicheVonWillebrand tcaMPlusT(Double tcaMPlusT) {
        this.setTcaMPlusT(tcaMPlusT);
        return this;
    }

    public void setTcaMPlusT(Double tcaMPlusT) {
        this.tcaMPlusT = tcaMPlusT;
    }

    public Double getTcaMPlusT2H() {
        return this.tcaMPlusT2H;
    }

    public FicheVonWillebrand tcaMPlusT2H(Double tcaMPlusT2H) {
        this.setTcaMPlusT2H(tcaMPlusT2H);
        return this;
    }

    public void setTcaMPlusT2H(Double tcaMPlusT2H) {
        this.tcaMPlusT2H = tcaMPlusT2H;
    }

    public Double getFibrinogene() {
        return this.fibrinogene;
    }

    public FicheVonWillebrand fibrinogene(Double fibrinogene) {
        this.setFibrinogene(fibrinogene);
        return this;
    }

    public void setFibrinogene(Double fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public TypeTS getTsResultat() {
        return this.tsResultat;
    }

    public FicheVonWillebrand tsResultat(TypeTS tsResultat) {
        this.setTsResultat(tsResultat);
        return this;
    }

    public void setTsResultat(TypeTS tsResultat) {
        this.tsResultat = tsResultat;
    }

    public Double getTsValeur() {
        return this.tsValeur;
    }

    public FicheVonWillebrand tsValeur(Double tsValeur) {
        this.setTsValeur(tsValeur);
        return this;
    }

    public void setTsValeur(Double tsValeur) {
        this.tsValeur = tsValeur;
    }

    public TypeOcclusion getOcclusionPlaquettaire() {
        return this.occlusionPlaquettaire;
    }

    public FicheVonWillebrand occlusionPlaquettaire(TypeOcclusion occlusionPlaquettaire) {
        this.setOcclusionPlaquettaire(occlusionPlaquettaire);
        return this;
    }

    public void setOcclusionPlaquettaire(TypeOcclusion occlusionPlaquettaire) {
        this.occlusionPlaquettaire = occlusionPlaquettaire;
    }

    public Double getCollageneEpinephrine() {
        return this.collageneEpinephrine;
    }

    public FicheVonWillebrand collageneEpinephrine(Double collageneEpinephrine) {
        this.setCollageneEpinephrine(collageneEpinephrine);
        return this;
    }

    public void setCollageneEpinephrine(Double collageneEpinephrine) {
        this.collageneEpinephrine = collageneEpinephrine;
    }

    public Double getCollageneADP() {
        return this.collageneADP;
    }

    public FicheVonWillebrand collageneADP(Double collageneADP) {
        this.setCollageneADP(collageneADP);
        return this;
    }

    public void setCollageneADP(Double collageneADP) {
        this.collageneADP = collageneADP;
    }

    public Double getFvwag() {
        return this.fvwag;
    }

    public FicheVonWillebrand fvwag(Double fvwag) {
        this.setFvwag(fvwag);
        return this;
    }

    public void setFvwag(Double fvwag) {
        this.fvwag = fvwag;
    }

    public Double getFvwact() {
        return this.fvwact;
    }

    public FicheVonWillebrand fvwact(Double fvwact) {
        this.setFvwact(fvwact);
        return this;
    }

    public void setFvwact(Double fvwact) {
        this.fvwact = fvwact;
    }

    public Double getf8c() {
        return this.f8c;
    }

    public FicheVonWillebrand f8c(Double f8c) {
        this.setf8c(f8c);
        return this;
    }

    public void setf8c(Double f8c) {
        this.f8c = f8c;
    }

    public Double getRatioFvwactFvwag() {
        return this.ratioFvwactFvwag;
    }

    public FicheVonWillebrand ratioFvwactFvwag(Double ratioFvwactFvwag) {
        this.setRatioFvwactFvwag(ratioFvwactFvwag);
        return this;
    }

    public void setRatioFvwactFvwag(Double ratioFvwactFvwag) {
        this.ratioFvwactFvwag = ratioFvwactFvwag;
    }

    public Double getRatioF8cFvwag() {
        return this.ratioF8cFvwag;
    }

    public FicheVonWillebrand ratioF8cFvwag(Double ratioF8cFvwag) {
        this.setRatioF8cFvwag(ratioF8cFvwag);
        return this;
    }

    public void setRatioF8cFvwag(Double ratioF8cFvwag) {
        this.ratioF8cFvwag = ratioF8cFvwag;
    }

    public FaiteNonFaite getAutresTestsSpecifiques() {
        return this.autresTestsSpecifiques;
    }

    public FicheVonWillebrand autresTestsSpecifiques(FaiteNonFaite autresTestsSpecifiques) {
        this.setAutresTestsSpecifiques(autresTestsSpecifiques);
        return this;
    }

    public void setAutresTestsSpecifiques(FaiteNonFaite autresTestsSpecifiques) {
        this.autresTestsSpecifiques = autresTestsSpecifiques;
    }

    public FaiteNonFaite getVwfCB() {
        return this.vwfCB;
    }

    public FicheVonWillebrand vwfCB(FaiteNonFaite vwfCB) {
        this.setVwfCB(vwfCB);
        return this;
    }

    public void setVwfCB(FaiteNonFaite vwfCB) {
        this.vwfCB = vwfCB;
    }

    public Double getVwfCBResultat() {
        return this.vwfCBResultat;
    }

    public FicheVonWillebrand vwfCBResultat(Double vwfCBResultat) {
        this.setVwfCBResultat(vwfCBResultat);
        return this;
    }

    public void setVwfCBResultat(Double vwfCBResultat) {
        this.vwfCBResultat = vwfCBResultat;
    }

    public FaiteNonFaite getRipa() {
        return this.ripa;
    }

    public FicheVonWillebrand ripa(FaiteNonFaite ripa) {
        this.setRipa(ripa);
        return this;
    }

    public void setRipa(FaiteNonFaite ripa) {
        this.ripa = ripa;
    }

    public TypeRipaResultat getRipaResultat() {
        return this.ripaResultat;
    }

    public FicheVonWillebrand ripaResultat(TypeRipaResultat ripaResultat) {
        this.setRipaResultat(ripaResultat);
        return this;
    }

    public void setRipaResultat(TypeRipaResultat ripaResultat) {
        this.ripaResultat = ripaResultat;
    }

    public TypeMultimere getMultimereHPM() {
        return this.multimereHPM;
    }

    public FicheVonWillebrand multimereHPM(TypeMultimere multimereHPM) {
        this.setMultimereHPM(multimereHPM);
        return this;
    }

    public void setMultimereHPM(TypeMultimere multimereHPM) {
        this.multimereHPM = multimereHPM;
    }

    public FaiteNonFaite getLiaisonFvwF8() {
        return this.liaisonFvwF8;
    }

    public FicheVonWillebrand liaisonFvwF8(FaiteNonFaite liaisonFvwF8) {
        this.setLiaisonFvwF8(liaisonFvwF8);
        return this;
    }

    public void setLiaisonFvwF8(FaiteNonFaite liaisonFvwF8) {
        this.liaisonFvwF8 = liaisonFvwF8;
    }

    public TypeResultatPatho getLiaisonFvwF8Resultat() {
        return this.liaisonFvwF8Resultat;
    }

    public FicheVonWillebrand liaisonFvwF8Resultat(TypeResultatPatho liaisonFvwF8Resultat) {
        this.setLiaisonFvwF8Resultat(liaisonFvwF8Resultat);
        return this;
    }

    public void setLiaisonFvwF8Resultat(TypeResultatPatho liaisonFvwF8Resultat) {
        this.liaisonFvwF8Resultat = liaisonFvwF8Resultat;
    }

    public FaiteNonFaite getFvwpp() {
        return this.fvwpp;
    }

    public FicheVonWillebrand fvwpp(FaiteNonFaite fvwpp) {
        this.setFvwpp(fvwpp);
        return this;
    }

    public void setFvwpp(FaiteNonFaite fvwpp) {
        this.fvwpp = fvwpp;
    }

    public TypeFvWppRatio getFvwppResultat() {
        return this.fvwppResultat;
    }

    public FicheVonWillebrand fvwppResultat(TypeFvWppRatio fvwppResultat) {
        this.setFvwppResultat(fvwppResultat);
        return this;
    }

    public void setFvwppResultat(TypeFvWppRatio fvwppResultat) {
        this.fvwppResultat = fvwppResultat;
    }

    public FaiteNonFaite getMutationGenetique() {
        return this.mutationGenetique;
    }

    public FicheVonWillebrand mutationGenetique(FaiteNonFaite mutationGenetique) {
        this.setMutationGenetique(mutationGenetique);
        return this;
    }

    public void setMutationGenetique(FaiteNonFaite mutationGenetique) {
        this.mutationGenetique = mutationGenetique;
    }

    public Boolean getMutationPresente() {
        return this.mutationPresente;
    }

    public FicheVonWillebrand mutationPresente(Boolean mutationPresente) {
        this.setMutationPresente(mutationPresente);
        return this;
    }

    public void setMutationPresente(Boolean mutationPresente) {
        this.mutationPresente = mutationPresente;
    }

    public String getMutationNom() {
        return this.mutationNom;
    }

    public FicheVonWillebrand mutationNom(String mutationNom) {
        this.setMutationNom(mutationNom);
        return this;
    }

    public void setMutationNom(String mutationNom) {
        this.mutationNom = mutationNom;
    }

    public TypeZygote getMutationZygote() {
        return this.mutationZygote;
    }

    public FicheVonWillebrand mutationZygote(TypeZygote mutationZygote) {
        this.setMutationZygote(mutationZygote);
        return this;
    }

    public void setMutationZygote(TypeZygote mutationZygote) {
        this.mutationZygote = mutationZygote;
    }

    public OuiNonNP getEcchymoses() {
        return this.ecchymoses;
    }

    public FicheVonWillebrand ecchymoses(OuiNonNP ecchymoses) {
        this.setEcchymoses(ecchymoses);
        return this;
    }

    public void setEcchymoses(OuiNonNP ecchymoses) {
        this.ecchymoses = ecchymoses;
    }

    public Boolean getEcchymosesSpontanees() {
        return this.ecchymosesSpontanees;
    }

    public FicheVonWillebrand ecchymosesSpontanees(Boolean ecchymosesSpontanees) {
        this.setEcchymosesSpontanees(ecchymosesSpontanees);
        return this;
    }

    public void setEcchymosesSpontanees(Boolean ecchymosesSpontanees) {
        this.ecchymosesSpontanees = ecchymosesSpontanees;
    }

    public Boolean getEcchymosesProvoquees() {
        return this.ecchymosesProvoquees;
    }

    public FicheVonWillebrand ecchymosesProvoquees(Boolean ecchymosesProvoquees) {
        this.setEcchymosesProvoquees(ecchymosesProvoquees);
        return this;
    }

    public void setEcchymosesProvoquees(Boolean ecchymosesProvoquees) {
        this.ecchymosesProvoquees = ecchymosesProvoquees;
    }

    public String getEcchymosesSiege() {
        return this.ecchymosesSiege;
    }

    public FicheVonWillebrand ecchymosesSiege(String ecchymosesSiege) {
        this.setEcchymosesSiege(ecchymosesSiege);
        return this;
    }

    public void setEcchymosesSiege(String ecchymosesSiege) {
        this.ecchymosesSiege = ecchymosesSiege;
    }

    public Integer getEcchymosesFrequence() {
        return this.ecchymosesFrequence;
    }

    public FicheVonWillebrand ecchymosesFrequence(Integer ecchymosesFrequence) {
        this.setEcchymosesFrequence(ecchymosesFrequence);
        return this;
    }

    public void setEcchymosesFrequence(Integer ecchymosesFrequence) {
        this.ecchymosesFrequence = ecchymosesFrequence;
    }

    public OuiNonNP getHemorragieMuqueuses() {
        return this.hemorragieMuqueuses;
    }

    public FicheVonWillebrand hemorragieMuqueuses(OuiNonNP hemorragieMuqueuses) {
        this.setHemorragieMuqueuses(hemorragieMuqueuses);
        return this;
    }

    public void setHemorragieMuqueuses(OuiNonNP hemorragieMuqueuses) {
        this.hemorragieMuqueuses = hemorragieMuqueuses;
    }

    public OuiNonNP getEpistaxis() {
        return this.epistaxis;
    }

    public FicheVonWillebrand epistaxis(OuiNonNP epistaxis) {
        this.setEpistaxis(epistaxis);
        return this;
    }

    public void setEpistaxis(OuiNonNP epistaxis) {
        this.epistaxis = epistaxis;
    }

    public Integer getEpistaxisFrequence() {
        return this.epistaxisFrequence;
    }

    public FicheVonWillebrand epistaxisFrequence(Integer epistaxisFrequence) {
        this.setEpistaxisFrequence(epistaxisFrequence);
        return this;
    }

    public void setEpistaxisFrequence(Integer epistaxisFrequence) {
        this.epistaxisFrequence = epistaxisFrequence;
    }

    public OuiNonNP getHemorragiesIntrabuccales() {
        return this.hemorragiesIntrabuccales;
    }

    public FicheVonWillebrand hemorragiesIntrabuccales(OuiNonNP hemorragiesIntrabuccales) {
        this.setHemorragiesIntrabuccales(hemorragiesIntrabuccales);
        return this;
    }

    public void setHemorragiesIntrabuccales(OuiNonNP hemorragiesIntrabuccales) {
        this.hemorragiesIntrabuccales = hemorragiesIntrabuccales;
    }

    public Integer getHemorragiesIntrabuccalesFrequence() {
        return this.hemorragiesIntrabuccalesFrequence;
    }

    public FicheVonWillebrand hemorragiesIntrabuccalesFrequence(Integer hemorragiesIntrabuccalesFrequence) {
        this.setHemorragiesIntrabuccalesFrequence(hemorragiesIntrabuccalesFrequence);
        return this;
    }

    public void setHemorragiesIntrabuccalesFrequence(Integer hemorragiesIntrabuccalesFrequence) {
        this.hemorragiesIntrabuccalesFrequence = hemorragiesIntrabuccalesFrequence;
    }

    public OuiNonNP getMenoragie() {
        return this.menoragie;
    }

    public FicheVonWillebrand menoragie(OuiNonNP menoragie) {
        this.setMenoragie(menoragie);
        return this;
    }

    public void setMenoragie(OuiNonNP menoragie) {
        this.menoragie = menoragie;
    }

    public Integer getMenoragieFrequence() {
        return this.menoragieFrequence;
    }

    public FicheVonWillebrand menoragieFrequence(Integer menoragieFrequence) {
        this.setMenoragieFrequence(menoragieFrequence);
        return this;
    }

    public void setMenoragieFrequence(Integer menoragieFrequence) {
        this.menoragieFrequence = menoragieFrequence;
    }

    public Boolean getScoreHighamFait() {
        return this.scoreHighamFait;
    }

    public FicheVonWillebrand scoreHighamFait(Boolean scoreHighamFait) {
        this.setScoreHighamFait(scoreHighamFait);
        return this;
    }

    public void setScoreHighamFait(Boolean scoreHighamFait) {
        this.scoreHighamFait = scoreHighamFait;
    }

    public Integer getScoreHighamValeur() {
        return this.scoreHighamValeur;
    }

    public FicheVonWillebrand scoreHighamValeur(Integer scoreHighamValeur) {
        this.setScoreHighamValeur(scoreHighamValeur);
        return this;
    }

    public void setScoreHighamValeur(Integer scoreHighamValeur) {
        this.scoreHighamValeur = scoreHighamValeur;
    }

    public OuiNonNP getHematome() {
        return this.hematome;
    }

    public FicheVonWillebrand hematome(OuiNonNP hematome) {
        this.setHematome(hematome);
        return this;
    }

    public void setHematome(OuiNonNP hematome) {
        this.hematome = hematome;
    }

    public Boolean getHematomeSpontane() {
        return this.hematomeSpontane;
    }

    public FicheVonWillebrand hematomeSpontane(Boolean hematomeSpontane) {
        this.setHematomeSpontane(hematomeSpontane);
        return this;
    }

    public void setHematomeSpontane(Boolean hematomeSpontane) {
        this.hematomeSpontane = hematomeSpontane;
    }

    public Boolean getHematomeProvoque() {
        return this.hematomeProvoque;
    }

    public FicheVonWillebrand hematomeProvoque(Boolean hematomeProvoque) {
        this.setHematomeProvoque(hematomeProvoque);
        return this;
    }

    public void setHematomeProvoque(Boolean hematomeProvoque) {
        this.hematomeProvoque = hematomeProvoque;
    }

    public Boolean getHematomeSousCutane() {
        return this.hematomeSousCutane;
    }

    public FicheVonWillebrand hematomeSousCutane(Boolean hematomeSousCutane) {
        this.setHematomeSousCutane(hematomeSousCutane);
        return this;
    }

    public void setHematomeSousCutane(Boolean hematomeSousCutane) {
        this.hematomeSousCutane = hematomeSousCutane;
    }

    public Boolean getHematomeMusculaire() {
        return this.hematomeMusculaire;
    }

    public FicheVonWillebrand hematomeMusculaire(Boolean hematomeMusculaire) {
        this.setHematomeMusculaire(hematomeMusculaire);
        return this;
    }

    public void setHematomeMusculaire(Boolean hematomeMusculaire) {
        this.hematomeMusculaire = hematomeMusculaire;
    }

    public Boolean getHematomeTissusMous() {
        return this.hematomeTissusMous;
    }

    public FicheVonWillebrand hematomeTissusMous(Boolean hematomeTissusMous) {
        this.setHematomeTissusMous(hematomeTissusMous);
        return this;
    }

    public void setHematomeTissusMous(Boolean hematomeTissusMous) {
        this.hematomeTissusMous = hematomeTissusMous;
    }

    public Integer getHematomeFrequence() {
        return this.hematomeFrequence;
    }

    public FicheVonWillebrand hematomeFrequence(Integer hematomeFrequence) {
        this.setHematomeFrequence(hematomeFrequence);
        return this;
    }

    public void setHematomeFrequence(Integer hematomeFrequence) {
        this.hematomeFrequence = hematomeFrequence;
    }

    public OuiNonNP getHemarthrose() {
        return this.hemarthrose;
    }

    public FicheVonWillebrand hemarthrose(OuiNonNP hemarthrose) {
        this.setHemarthrose(hemarthrose);
        return this;
    }

    public void setHemarthrose(OuiNonNP hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public Boolean getHemarthroseSpontane() {
        return this.hemarthroseSpontane;
    }

    public FicheVonWillebrand hemarthroseSpontane(Boolean hemarthroseSpontane) {
        this.setHemarthroseSpontane(hemarthroseSpontane);
        return this;
    }

    public void setHemarthroseSpontane(Boolean hemarthroseSpontane) {
        this.hemarthroseSpontane = hemarthroseSpontane;
    }

    public Boolean getHemarthroseProvoque() {
        return this.hemarthroseProvoque;
    }

    public FicheVonWillebrand hemarthroseProvoque(Boolean hemarthroseProvoque) {
        this.setHemarthroseProvoque(hemarthroseProvoque);
        return this;
    }

    public void setHemarthroseProvoque(Boolean hemarthroseProvoque) {
        this.hemarthroseProvoque = hemarthroseProvoque;
    }

    public String getHemarthroseSiege() {
        return this.hemarthroseSiege;
    }

    public FicheVonWillebrand hemarthroseSiege(String hemarthroseSiege) {
        this.setHemarthroseSiege(hemarthroseSiege);
        return this;
    }

    public void setHemarthroseSiege(String hemarthroseSiege) {
        this.hemarthroseSiege = hemarthroseSiege;
    }

    public Integer getHemarthroseFrequence() {
        return this.hemarthroseFrequence;
    }

    public FicheVonWillebrand hemarthroseFrequence(Integer hemarthroseFrequence) {
        this.setHemarthroseFrequence(hemarthroseFrequence);
        return this;
    }

    public void setHemarthroseFrequence(Integer hemarthroseFrequence) {
        this.hemarthroseFrequence = hemarthroseFrequence;
    }

    public OuiNonNP getHemorragieAmygdalienne() {
        return this.hemorragieAmygdalienne;
    }

    public FicheVonWillebrand hemorragieAmygdalienne(OuiNonNP hemorragieAmygdalienne) {
        this.setHemorragieAmygdalienne(hemorragieAmygdalienne);
        return this;
    }

    public void setHemorragieAmygdalienne(OuiNonNP hemorragieAmygdalienne) {
        this.hemorragieAmygdalienne = hemorragieAmygdalienne;
    }

    public Integer getHemorragieAmygdalienneFrequence() {
        return this.hemorragieAmygdalienneFrequence;
    }

    public FicheVonWillebrand hemorragieAmygdalienneFrequence(Integer hemorragieAmygdalienneFrequence) {
        this.setHemorragieAmygdalienneFrequence(hemorragieAmygdalienneFrequence);
        return this;
    }

    public void setHemorragieAmygdalienneFrequence(Integer hemorragieAmygdalienneFrequence) {
        this.hemorragieAmygdalienneFrequence = hemorragieAmygdalienneFrequence;
    }

    public OuiNonNP getAvulsionDentaire() {
        return this.avulsionDentaire;
    }

    public FicheVonWillebrand avulsionDentaire(OuiNonNP avulsionDentaire) {
        this.setAvulsionDentaire(avulsionDentaire);
        return this;
    }

    public void setAvulsionDentaire(OuiNonNP avulsionDentaire) {
        this.avulsionDentaire = avulsionDentaire;
    }

    public Boolean getAvulsionPrepare() {
        return this.avulsionPrepare;
    }

    public FicheVonWillebrand avulsionPrepare(Boolean avulsionPrepare) {
        this.setAvulsionPrepare(avulsionPrepare);
        return this;
    }

    public void setAvulsionPrepare(Boolean avulsionPrepare) {
        this.avulsionPrepare = avulsionPrepare;
    }

    public OuiNonNP getAvulsionBienDeroule() {
        return this.avulsionBienDeroule;
    }

    public FicheVonWillebrand avulsionBienDeroule(OuiNonNP avulsionBienDeroule) {
        this.setAvulsionBienDeroule(avulsionBienDeroule);
        return this;
    }

    public void setAvulsionBienDeroule(OuiNonNP avulsionBienDeroule) {
        this.avulsionBienDeroule = avulsionBienDeroule;
    }

    public OuiNonNP getTraumatisme() {
        return this.traumatisme;
    }

    public FicheVonWillebrand traumatisme(OuiNonNP traumatisme) {
        this.setTraumatisme(traumatisme);
        return this;
    }

    public void setTraumatisme(OuiNonNP traumatisme) {
        this.traumatisme = traumatisme;
    }

    public String getTraumatismeType() {
        return this.traumatismeType;
    }

    public FicheVonWillebrand traumatismeType(String traumatismeType) {
        this.setTraumatismeType(traumatismeType);
        return this;
    }

    public void setTraumatismeType(String traumatismeType) {
        this.traumatismeType = traumatismeType;
    }

    public OuiNonNP getChirurgie() {
        return this.chirurgie;
    }

    public FicheVonWillebrand chirurgie(OuiNonNP chirurgie) {
        this.setChirurgie(chirurgie);
        return this;
    }

    public void setChirurgie(OuiNonNP chirurgie) {
        this.chirurgie = chirurgie;
    }

    public String getChirurgieType() {
        return this.chirurgieType;
    }

    public FicheVonWillebrand chirurgieType(String chirurgieType) {
        this.setChirurgieType(chirurgieType);
        return this;
    }

    public void setChirurgieType(String chirurgieType) {
        this.chirurgieType = chirurgieType;
    }

    public OuiNonNP getChirurgiePrepare() {
        return this.chirurgiePrepare;
    }

    public FicheVonWillebrand chirurgiePrepare(OuiNonNP chirurgiePrepare) {
        this.setChirurgiePrepare(chirurgiePrepare);
        return this;
    }

    public void setChirurgiePrepare(OuiNonNP chirurgiePrepare) {
        this.chirurgiePrepare = chirurgiePrepare;
    }

    public OuiNonNP getChirurgieBienDeroule() {
        return this.chirurgieBienDeroule;
    }

    public FicheVonWillebrand chirurgieBienDeroule(OuiNonNP chirurgieBienDeroule) {
        this.setChirurgieBienDeroule(chirurgieBienDeroule);
        return this;
    }

    public void setChirurgieBienDeroule(OuiNonNP chirurgieBienDeroule) {
        this.chirurgieBienDeroule = chirurgieBienDeroule;
    }

    public OuiNonNP getAccouchement() {
        return this.accouchement;
    }

    public FicheVonWillebrand accouchement(OuiNonNP accouchement) {
        this.setAccouchement(accouchement);
        return this;
    }

    public void setAccouchement(OuiNonNP accouchement) {
        this.accouchement = accouchement;
    }

    public OuiNonNP getHemorragiePostpartum() {
        return this.hemorragiePostpartum;
    }

    public FicheVonWillebrand hemorragiePostpartum(OuiNonNP hemorragiePostpartum) {
        this.setHemorragiePostpartum(hemorragiePostpartum);
        return this;
    }

    public void setHemorragiePostpartum(OuiNonNP hemorragiePostpartum) {
        this.hemorragiePostpartum = hemorragiePostpartum;
    }

    public OuiNonNP getCirconcision() {
        return this.circoncision;
    }

    public FicheVonWillebrand circoncision(OuiNonNP circoncision) {
        this.setCirconcision(circoncision);
        return this;
    }

    public void setCirconcision(OuiNonNP circoncision) {
        this.circoncision = circoncision;
    }

    public Integer getAgeCirconcision() {
        return this.ageCirconcision;
    }

    public FicheVonWillebrand ageCirconcision(Integer ageCirconcision) {
        this.setAgeCirconcision(ageCirconcision);
        return this;
    }

    public void setAgeCirconcision(Integer ageCirconcision) {
        this.ageCirconcision = ageCirconcision;
    }

    public OuiNonNP getCirconcisionPrepare() {
        return this.circoncisionPrepare;
    }

    public FicheVonWillebrand circoncisionPrepare(OuiNonNP circoncisionPrepare) {
        this.setCirconcisionPrepare(circoncisionPrepare);
        return this;
    }

    public void setCirconcisionPrepare(OuiNonNP circoncisionPrepare) {
        this.circoncisionPrepare = circoncisionPrepare;
    }

    public OuiNonNP getCirconcisionBienDeroule() {
        return this.circoncisionBienDeroule;
    }

    public FicheVonWillebrand circoncisionBienDeroule(OuiNonNP circoncisionBienDeroule) {
        this.setCirconcisionBienDeroule(circoncisionBienDeroule);
        return this;
    }

    public void setCirconcisionBienDeroule(OuiNonNP circoncisionBienDeroule) {
        this.circoncisionBienDeroule = circoncisionBienDeroule;
    }

    public OuiNonNP getHemorragieVisceres() {
        return this.hemorragieVisceres;
    }

    public FicheVonWillebrand hemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.setHemorragieVisceres(hemorragieVisceres);
        return this;
    }

    public void setHemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.hemorragieVisceres = hemorragieVisceres;
    }

    public Boolean getHemorragieVisceresSpontane() {
        return this.hemorragieVisceresSpontane;
    }

    public FicheVonWillebrand hemorragieVisceresSpontane(Boolean hemorragieVisceresSpontane) {
        this.setHemorragieVisceresSpontane(hemorragieVisceresSpontane);
        return this;
    }

    public void setHemorragieVisceresSpontane(Boolean hemorragieVisceresSpontane) {
        this.hemorragieVisceresSpontane = hemorragieVisceresSpontane;
    }

    public Boolean getHemorragieVisceresProvoque() {
        return this.hemorragieVisceresProvoque;
    }

    public FicheVonWillebrand hemorragieVisceresProvoque(Boolean hemorragieVisceresProvoque) {
        this.setHemorragieVisceresProvoque(hemorragieVisceresProvoque);
        return this;
    }

    public void setHemorragieVisceresProvoque(Boolean hemorragieVisceresProvoque) {
        this.hemorragieVisceresProvoque = hemorragieVisceresProvoque;
    }

    public TypeHemorragieVisceres getHemorragieVisceresType() {
        return this.hemorragieVisceresType;
    }

    public FicheVonWillebrand hemorragieVisceresType(TypeHemorragieVisceres hemorragieVisceresType) {
        this.setHemorragieVisceresType(hemorragieVisceresType);
        return this;
    }

    public void setHemorragieVisceresType(TypeHemorragieVisceres hemorragieVisceresType) {
        this.hemorragieVisceresType = hemorragieVisceresType;
    }

    public String getAutreHemorragieVisceres() {
        return this.autreHemorragieVisceres;
    }

    public FicheVonWillebrand autreHemorragieVisceres(String autreHemorragieVisceres) {
        this.setAutreHemorragieVisceres(autreHemorragieVisceres);
        return this;
    }

    public void setAutreHemorragieVisceres(String autreHemorragieVisceres) {
        this.autreHemorragieVisceres = autreHemorragieVisceres;
    }

    public Boolean getExplorationVisceres() {
        return this.explorationVisceres;
    }

    public FicheVonWillebrand explorationVisceres(Boolean explorationVisceres) {
        this.setExplorationVisceres(explorationVisceres);
        return this;
    }

    public void setExplorationVisceres(Boolean explorationVisceres) {
        this.explorationVisceres = explorationVisceres;
    }

    public String getLesionSousJacente() {
        return this.lesionSousJacente;
    }

    public FicheVonWillebrand lesionSousJacente(String lesionSousJacente) {
        this.setLesionSousJacente(lesionSousJacente);
        return this;
    }

    public void setLesionSousJacente(String lesionSousJacente) {
        this.lesionSousJacente = lesionSousJacente;
    }

    public OuiNonNP getHemorragieGrave() {
        return this.hemorragieGrave;
    }

    public FicheVonWillebrand hemorragieGrave(OuiNonNP hemorragieGrave) {
        this.setHemorragieGrave(hemorragieGrave);
        return this;
    }

    public void setHemorragieGrave(OuiNonNP hemorragieGrave) {
        this.hemorragieGrave = hemorragieGrave;
    }

    public Boolean getGraveDeglobulisation() {
        return this.graveDeglobulisation;
    }

    public FicheVonWillebrand graveDeglobulisation(Boolean graveDeglobulisation) {
        this.setGraveDeglobulisation(graveDeglobulisation);
        return this;
    }

    public void setGraveDeglobulisation(Boolean graveDeglobulisation) {
        this.graveDeglobulisation = graveDeglobulisation;
    }

    public Boolean getGraveEtatDeChoc() {
        return this.graveEtatDeChoc;
    }

    public FicheVonWillebrand graveEtatDeChoc(Boolean graveEtatDeChoc) {
        this.setGraveEtatDeChoc(graveEtatDeChoc);
        return this;
    }

    public void setGraveEtatDeChoc(Boolean graveEtatDeChoc) {
        this.graveEtatDeChoc = graveEtatDeChoc;
    }

    public Boolean getGraveParLocalisation() {
        return this.graveParLocalisation;
    }

    public FicheVonWillebrand graveParLocalisation(Boolean graveParLocalisation) {
        this.setGraveParLocalisation(graveParLocalisation);
        return this;
    }

    public void setGraveParLocalisation(Boolean graveParLocalisation) {
        this.graveParLocalisation = graveParLocalisation;
    }

    public String getSiegeGraviteLocalisation() {
        return this.siegeGraviteLocalisation;
    }

    public FicheVonWillebrand siegeGraviteLocalisation(String siegeGraviteLocalisation) {
        this.setSiegeGraviteLocalisation(siegeGraviteLocalisation);
        return this;
    }

    public void setSiegeGraviteLocalisation(String siegeGraviteLocalisation) {
        this.siegeGraviteLocalisation = siegeGraviteLocalisation;
    }

    public Integer getNombreHospitalisations() {
        return this.nombreHospitalisations;
    }

    public FicheVonWillebrand nombreHospitalisations(Integer nombreHospitalisations) {
        this.setNombreHospitalisations(nombreHospitalisations);
        return this;
    }

    public void setNombreHospitalisations(Integer nombreHospitalisations) {
        this.nombreHospitalisations = nombreHospitalisations;
    }

    public Integer getNombreTransfusions() {
        return this.nombreTransfusions;
    }

    public FicheVonWillebrand nombreTransfusions(Integer nombreTransfusions) {
        this.setNombreTransfusions(nombreTransfusions);
        return this;
    }

    public void setNombreTransfusions(Integer nombreTransfusions) {
        this.nombreTransfusions = nombreTransfusions;
    }

    public Integer getScoreISTHBAT() {
        return this.scoreISTHBAT;
    }

    public FicheVonWillebrand scoreISTHBAT(Integer scoreISTHBAT) {
        this.setScoreISTHBAT(scoreISTHBAT);
        return this;
    }

    public void setScoreISTHBAT(Integer scoreISTHBAT) {
        this.scoreISTHBAT = scoreISTHBAT;
    }

    public OuiNonNP getDesmopressine() {
        return this.desmopressine;
    }

    public FicheVonWillebrand desmopressine(OuiNonNP desmopressine) {
        this.setDesmopressine(desmopressine);
        return this;
    }

    public void setDesmopressine(OuiNonNP desmopressine) {
        this.desmopressine = desmopressine;
    }

    public OuiNonNP getTraitementSubstitutifPrevention() {
        return this.traitementSubstitutifPrevention;
    }

    public FicheVonWillebrand traitementSubstitutifPrevention(OuiNonNP traitementSubstitutifPrevention) {
        this.setTraitementSubstitutifPrevention(traitementSubstitutifPrevention);
        return this;
    }

    public void setTraitementSubstitutifPrevention(OuiNonNP traitementSubstitutifPrevention) {
        this.traitementSubstitutifPrevention = traitementSubstitutifPrevention;
    }

    public String getTraitementPreparation() {
        return this.traitementPreparation;
    }

    public FicheVonWillebrand traitementPreparation(String traitementPreparation) {
        this.setTraitementPreparation(traitementPreparation);
        return this;
    }

    public void setTraitementPreparation(String traitementPreparation) {
        this.traitementPreparation = traitementPreparation;
    }

    public OuiNonNP getTraitementSubstitutifCuratif() {
        return this.traitementSubstitutifCuratif;
    }

    public FicheVonWillebrand traitementSubstitutifCuratif(OuiNonNP traitementSubstitutifCuratif) {
        this.setTraitementSubstitutifCuratif(traitementSubstitutifCuratif);
        return this;
    }

    public void setTraitementSubstitutifCuratif(OuiNonNP traitementSubstitutifCuratif) {
        this.traitementSubstitutifCuratif = traitementSubstitutifCuratif;
    }

    public OuiNonNP getCryoprecipite() {
        return this.cryoprecipite;
    }

    public FicheVonWillebrand cryoprecipite(OuiNonNP cryoprecipite) {
        this.setCryoprecipite(cryoprecipite);
        return this;
    }

    public void setCryoprecipite(OuiNonNP cryoprecipite) {
        this.cryoprecipite = cryoprecipite;
    }

    public OuiNonNP getPfc() {
        return this.pfc;
    }

    public FicheVonWillebrand pfc(OuiNonNP pfc) {
        this.setPfc(pfc);
        return this;
    }

    public void setPfc(OuiNonNP pfc) {
        this.pfc = pfc;
    }

    public OuiNonNP getConcentreFVIIIFVW() {
        return this.concentreFVIIIFVW;
    }

    public FicheVonWillebrand concentreFVIIIFVW(OuiNonNP concentreFVIIIFVW) {
        this.setConcentreFVIIIFVW(concentreFVIIIFVW);
        return this;
    }

    public void setConcentreFVIIIFVW(OuiNonNP concentreFVIIIFVW) {
        this.concentreFVIIIFVW = concentreFVIIIFVW;
    }

    public OuiNonNP getConcentreFVW() {
        return this.concentreFVW;
    }

    public FicheVonWillebrand concentreFVW(OuiNonNP concentreFVW) {
        this.setConcentreFVW(concentreFVW);
        return this;
    }

    public void setConcentreFVW(OuiNonNP concentreFVW) {
        this.concentreFVW = concentreFVW;
    }

    public OuiNonNP getConcentreFVIII() {
        return this.concentreFVIII;
    }

    public FicheVonWillebrand concentreFVIII(OuiNonNP concentreFVIII) {
        this.setConcentreFVIII(concentreFVIII);
        return this;
    }

    public void setConcentreFVIII(OuiNonNP concentreFVIII) {
        this.concentreFVIII = concentreFVIII;
    }

    public OuiNonNP getAgentBypassant() {
        return this.agentBypassant;
    }

    public FicheVonWillebrand agentBypassant(OuiNonNP agentBypassant) {
        this.setAgentBypassant(agentBypassant);
        return this;
    }

    public void setAgentBypassant(OuiNonNP agentBypassant) {
        this.agentBypassant = agentBypassant;
    }

    public OuiNonNP getAntifibrinolytiques() {
        return this.antifibrinolytiques;
    }

    public FicheVonWillebrand antifibrinolytiques(OuiNonNP antifibrinolytiques) {
        this.setAntifibrinolytiques(antifibrinolytiques);
        return this;
    }

    public void setAntifibrinolytiques(OuiNonNP antifibrinolytiques) {
        this.antifibrinolytiques = antifibrinolytiques;
    }

    public Boolean getVoieOrale() {
        return this.voieOrale;
    }

    public FicheVonWillebrand voieOrale(Boolean voieOrale) {
        this.setVoieOrale(voieOrale);
        return this;
    }

    public void setVoieOrale(Boolean voieOrale) {
        this.voieOrale = voieOrale;
    }

    public Boolean getVoieIV() {
        return this.voieIV;
    }

    public FicheVonWillebrand voieIV(Boolean voieIV) {
        this.setVoieIV(voieIV);
        return this;
    }

    public void setVoieIV(Boolean voieIV) {
        this.voieIV = voieIV;
    }

    public Boolean getVoieLocale() {
        return this.voieLocale;
    }

    public FicheVonWillebrand voieLocale(Boolean voieLocale) {
        this.setVoieLocale(voieLocale);
        return this;
    }

    public void setVoieLocale(Boolean voieLocale) {
        this.voieLocale = voieLocale;
    }

    public OuiNonNP getOestroprogestatifs() {
        return this.oestroprogestatifs;
    }

    public FicheVonWillebrand oestroprogestatifs(OuiNonNP oestroprogestatifs) {
        this.setOestroprogestatifs(oestroprogestatifs);
        return this;
    }

    public void setOestroprogestatifs(OuiNonNP oestroprogestatifs) {
        this.oestroprogestatifs = oestroprogestatifs;
    }

    public OuiNonNP getTraitementAntalgique() {
        return this.traitementAntalgique;
    }

    public FicheVonWillebrand traitementAntalgique(OuiNonNP traitementAntalgique) {
        this.setTraitementAntalgique(traitementAntalgique);
        return this;
    }

    public void setTraitementAntalgique(OuiNonNP traitementAntalgique) {
        this.traitementAntalgique = traitementAntalgique;
    }

    public OuiNonNP getCorticotherapie() {
        return this.corticotherapie;
    }

    public FicheVonWillebrand corticotherapie(OuiNonNP corticotherapie) {
        this.setCorticotherapie(corticotherapie);
        return this;
    }

    public void setCorticotherapie(OuiNonNP corticotherapie) {
        this.corticotherapie = corticotherapie;
    }

    public OuiNonNP getKinesitherapie() {
        return this.kinesitherapie;
    }

    public FicheVonWillebrand kinesitherapie(OuiNonNP kinesitherapie) {
        this.setKinesitherapie(kinesitherapie);
        return this;
    }

    public void setKinesitherapie(OuiNonNP kinesitherapie) {
        this.kinesitherapie = kinesitherapie;
    }

    public OuiNonNP getTraitementMartial() {
        return this.traitementMartial;
    }

    public FicheVonWillebrand traitementMartial(OuiNonNP traitementMartial) {
        this.setTraitementMartial(traitementMartial);
        return this;
    }

    public void setTraitementMartial(OuiNonNP traitementMartial) {
        this.traitementMartial = traitementMartial;
    }

    public OuiNonNP getCulotsGlobulaires() {
        return this.culotsGlobulaires;
    }

    public FicheVonWillebrand culotsGlobulaires(OuiNonNP culotsGlobulaires) {
        this.setCulotsGlobulaires(culotsGlobulaires);
        return this;
    }

    public void setCulotsGlobulaires(OuiNonNP culotsGlobulaires) {
        this.culotsGlobulaires = culotsGlobulaires;
    }

    public Integer getNombreCulotsGlobulaires() {
        return this.nombreCulotsGlobulaires;
    }

    public FicheVonWillebrand nombreCulotsGlobulaires(Integer nombreCulotsGlobulaires) {
        this.setNombreCulotsGlobulaires(nombreCulotsGlobulaires);
        return this;
    }

    public void setNombreCulotsGlobulaires(Integer nombreCulotsGlobulaires) {
        this.nombreCulotsGlobulaires = nombreCulotsGlobulaires;
    }

    public OuiNonNP getImmunosuppresseur() {
        return this.immunosuppresseur;
    }

    public FicheVonWillebrand immunosuppresseur(OuiNonNP immunosuppresseur) {
        this.setImmunosuppresseur(immunosuppresseur);
        return this;
    }

    public void setImmunosuppresseur(OuiNonNP immunosuppresseur) {
        this.immunosuppresseur = immunosuppresseur;
    }

    public String getNomImmunosuppresseur() {
        return this.nomImmunosuppresseur;
    }

    public FicheVonWillebrand nomImmunosuppresseur(String nomImmunosuppresseur) {
        this.setNomImmunosuppresseur(nomImmunosuppresseur);
        return this;
    }

    public void setNomImmunosuppresseur(String nomImmunosuppresseur) {
        this.nomImmunosuppresseur = nomImmunosuppresseur;
    }

    public OuiNonNP getAnemie() {
        return this.anemie;
    }

    public FicheVonWillebrand anemie(OuiNonNP anemie) {
        this.setAnemie(anemie);
        return this;
    }

    public void setAnemie(OuiNonNP anemie) {
        this.anemie = anemie;
    }

    public Boolean getAnemieSevere() {
        return this.anemieSevere;
    }

    public FicheVonWillebrand anemieSevere(Boolean anemieSevere) {
        this.setAnemieSevere(anemieSevere);
        return this;
    }

    public void setAnemieSevere(Boolean anemieSevere) {
        this.anemieSevere = anemieSevere;
    }

    public Double getTauxHb() {
        return this.tauxHb;
    }

    public FicheVonWillebrand tauxHb(Double tauxHb) {
        this.setTauxHb(tauxHb);
        return this;
    }

    public void setTauxHb(Double tauxHb) {
        this.tauxHb = tauxHb;
    }

    public ResultatSerologie getAnticorpsAntiFVW() {
        return this.anticorpsAntiFVW;
    }

    public FicheVonWillebrand anticorpsAntiFVW(ResultatSerologie anticorpsAntiFVW) {
        this.setAnticorpsAntiFVW(anticorpsAntiFVW);
        return this;
    }

    public void setAnticorpsAntiFVW(ResultatSerologie anticorpsAntiFVW) {
        this.anticorpsAntiFVW = anticorpsAntiFVW;
    }

    public ResultatSerologie getAnticorpsAntiFVIII() {
        return this.anticorpsAntiFVIII;
    }

    public FicheVonWillebrand anticorpsAntiFVIII(ResultatSerologie anticorpsAntiFVIII) {
        this.setAnticorpsAntiFVIII(anticorpsAntiFVIII);
        return this;
    }

    public void setAnticorpsAntiFVIII(ResultatSerologie anticorpsAntiFVIII) {
        this.anticorpsAntiFVIII = anticorpsAntiFVIII;
    }

    public ResultatSerologie getHepatiteB() {
        return this.hepatiteB;
    }

    public FicheVonWillebrand hepatiteB(ResultatSerologie hepatiteB) {
        this.setHepatiteB(hepatiteB);
        return this;
    }

    public void setHepatiteB(ResultatSerologie hepatiteB) {
        this.hepatiteB = hepatiteB;
    }

    public ResultatSerologie getHepatiteC() {
        return this.hepatiteC;
    }

    public FicheVonWillebrand hepatiteC(ResultatSerologie hepatiteC) {
        this.setHepatiteC(hepatiteC);
        return this;
    }

    public void setHepatiteC(ResultatSerologie hepatiteC) {
        this.hepatiteC = hepatiteC;
    }

    public ResultatSerologie getHiv() {
        return this.hiv;
    }

    public FicheVonWillebrand hiv(ResultatSerologie hiv) {
        this.setHiv(hiv);
        return this;
    }

    public void setHiv(ResultatSerologie hiv) {
        this.hiv = hiv;
    }

    public Boolean getThrombose() {
        return this.thrombose;
    }

    public FicheVonWillebrand thrombose(Boolean thrombose) {
        this.setThrombose(thrombose);
        return this;
    }

    public void setThrombose(Boolean thrombose) {
        this.thrombose = thrombose;
    }

    public Boolean getReactionAllergique() {
        return this.reactionAllergique;
    }

    public FicheVonWillebrand reactionAllergique(Boolean reactionAllergique) {
        this.setReactionAllergique(reactionAllergique);
        return this;
    }

    public void setReactionAllergique(Boolean reactionAllergique) {
        this.reactionAllergique = reactionAllergique;
    }

    public Boolean getAutreComplication() {
        return this.autreComplication;
    }

    public FicheVonWillebrand autreComplication(Boolean autreComplication) {
        this.setAutreComplication(autreComplication);
        return this;
    }

    public void setAutreComplication(Boolean autreComplication) {
        this.autreComplication = autreComplication;
    }

    public String getAutreComplicationDescription() {
        return this.autreComplicationDescription;
    }

    public FicheVonWillebrand autreComplicationDescription(String autreComplicationDescription) {
        this.setAutreComplicationDescription(autreComplicationDescription);
        return this;
    }

    public void setAutreComplicationDescription(String autreComplicationDescription) {
        this.autreComplicationDescription = autreComplicationDescription;
    }

    public OuiNonNP getAdherenceTherapeutique() {
        return this.adherenceTherapeutique;
    }

    public FicheVonWillebrand adherenceTherapeutique(OuiNonNP adherenceTherapeutique) {
        this.setAdherenceTherapeutique(adherenceTherapeutique);
        return this;
    }

    public void setAdherenceTherapeutique(OuiNonNP adherenceTherapeutique) {
        this.adherenceTherapeutique = adherenceTherapeutique;
    }

    public String getCauseNonAdherence() {
        return this.causeNonAdherence;
    }

    public FicheVonWillebrand causeNonAdherence(String causeNonAdherence) {
        this.setCauseNonAdherence(causeNonAdherence);
        return this;
    }

    public void setCauseNonAdherence(String causeNonAdherence) {
        this.causeNonAdherence = causeNonAdherence;
    }

    public StatutSocial getStatutSocial() {
        return this.statutSocial;
    }

    public FicheVonWillebrand statutSocial(StatutSocial statutSocial) {
        this.setStatutSocial(statutSocial);
        return this;
    }

    public void setStatutSocial(StatutSocial statutSocial) {
        this.statutSocial = statutSocial;
    }

    public OuiNonNP getMarie() {
        return this.marie;
    }

    public FicheVonWillebrand marie(OuiNonNP marie) {
        this.setMarie(marie);
        return this;
    }

    public void setMarie(OuiNonNP marie) {
        this.marie = marie;
    }

    public OuiNonNP getEnfants() {
        return this.enfants;
    }

    public FicheVonWillebrand enfants(OuiNonNP enfants) {
        this.setEnfants(enfants);
        return this;
    }

    public void setEnfants(OuiNonNP enfants) {
        this.enfants = enfants;
    }

    public OuiNonNP getActivitePhysique() {
        return this.activitePhysique;
    }

    public FicheVonWillebrand activitePhysique(OuiNonNP activitePhysique) {
        this.setActivitePhysique(activitePhysique);
        return this;
    }

    public void setActivitePhysique(OuiNonNP activitePhysique) {
        this.activitePhysique = activitePhysique;
    }

    public String getTypeActivitePhysique() {
        return this.typeActivitePhysique;
    }

    public FicheVonWillebrand typeActivitePhysique(String typeActivitePhysique) {
        this.setTypeActivitePhysique(typeActivitePhysique);
        return this;
    }

    public void setTypeActivitePhysique(String typeActivitePhysique) {
        this.typeActivitePhysique = typeActivitePhysique;
    }

    public OuiNonNP getSequelles() {
        return this.sequelles;
    }

    public FicheVonWillebrand sequelles(OuiNonNP sequelles) {
        this.setSequelles(sequelles);
        return this;
    }

    public void setSequelles(OuiNonNP sequelles) {
        this.sequelles = sequelles;
    }

    public String getTypeSequelles() {
        return this.typeSequelles;
    }

    public FicheVonWillebrand typeSequelles(String typeSequelles) {
        this.setTypeSequelles(typeSequelles);
        return this;
    }

    public void setTypeSequelles(String typeSequelles) {
        this.typeSequelles = typeSequelles;
    }

    public OuiNonNP getPatientDecede() {
        return this.patientDecede;
    }

    public FicheVonWillebrand patientDecede(OuiNonNP patientDecede) {
        this.setPatientDecede(patientDecede);
        return this;
    }

    public void setPatientDecede(OuiNonNP patientDecede) {
        this.patientDecede = patientDecede;
    }

    public LocalDate getDateDeces() {
        return this.dateDeces;
    }

    public FicheVonWillebrand dateDeces(LocalDate dateDeces) {
        this.setDateDeces(dateDeces);
        return this;
    }

    public void setDateDeces(LocalDate dateDeces) {
        this.dateDeces = dateDeces;
    }

    public Integer getAgeDeces() {
        return this.ageDeces;
    }

    public FicheVonWillebrand ageDeces(Integer ageDeces) {
        this.setAgeDeces(ageDeces);
        return this;
    }

    public void setAgeDeces(Integer ageDeces) {
        this.ageDeces = ageDeces;
    }

    public CauseDeces getCauseDeces() {
        return this.causeDeces;
    }

    public FicheVonWillebrand causeDeces(CauseDeces causeDeces) {
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
        if (!(o instanceof FicheVonWillebrand)) {
            return false;
        }
        return getId() != null && getId().equals(((FicheVonWillebrand) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheVonWillebrand{" +
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
            ", dateEnregistrementRegistre='" + getDateEnregistrementRegistre() + "'" +
            ", consentementRegistre='" + getConsentementRegistre() + "'" +
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
            ", circonstancesDecouverte='" + getCirconstancesDecouverte() + "'" +
            ", circonstancesDecouvertePrecision='" + getCirconstancesDecouvertePrecision() + "'" +
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
            ", typeMvW='" + getTypeMvW() + "'" +
            ", sousTypeMvW='" + getSousTypeMvW() + "'" +
            ", pathologiesAssociees='" + getPathologiesAssociees() + "'" +
            ", dateTestConfirmation='" + getDateTestConfirmation() + "'" +
            ", gs='" + getGs() + "'" +
            ", hb=" + getHb() +
            ", hte=" + getHte() +
            ", plaquettes=" + getPlaquettes() +
            ", tp=" + getTp() +
            ", tcaMT=" + getTcaMT() +
            ", tcaMPlusT=" + getTcaMPlusT() +
            ", tcaMPlusT2H=" + getTcaMPlusT2H() +
            ", fibrinogene=" + getFibrinogene() +
            ", tsResultat='" + getTsResultat() + "'" +
            ", tsValeur=" + getTsValeur() +
            ", occlusionPlaquettaire='" + getOcclusionPlaquettaire() + "'" +
            ", collageneEpinephrine=" + getCollageneEpinephrine() +
            ", collageneADP=" + getCollageneADP() +
            ", fvwag=" + getFvwag() +
            ", fvwact=" + getFvwact() +
            ", f8c=" + getf8c() +
            ", ratioFvwactFvwag=" + getRatioFvwactFvwag() +
            ", ratioF8cFvwag=" + getRatioF8cFvwag() +
            ", autresTestsSpecifiques='" + getAutresTestsSpecifiques() + "'" +
            ", vwfCB='" + getVwfCB() + "'" +
            ", vwfCBResultat=" + getVwfCBResultat() +
            ", ripa='" + getRipa() + "'" +
            ", ripaResultat='" + getRipaResultat() + "'" +
            ", multimereHPM='" + getMultimereHPM() + "'" +
            ", liaisonFvwF8='" + getLiaisonFvwF8() + "'" +
            ", liaisonFvwF8Resultat='" + getLiaisonFvwF8Resultat() + "'" +
            ", fvwpp='" + getFvwpp() + "'" +
            ", fvwppResultat='" + getFvwppResultat() + "'" +
            ", mutationGenetique='" + getMutationGenetique() + "'" +
            ", mutationPresente='" + getMutationPresente() + "'" +
            ", mutationNom='" + getMutationNom() + "'" +
            ", mutationZygote='" + getMutationZygote() + "'" +
            ", ecchymoses='" + getEcchymoses() + "'" +
            ", ecchymosesSpontanees='" + getEcchymosesSpontanees() + "'" +
            ", ecchymosesProvoquees='" + getEcchymosesProvoquees() + "'" +
            ", ecchymosesSiege='" + getEcchymosesSiege() + "'" +
            ", ecchymosesFrequence=" + getEcchymosesFrequence() +
            ", hemorragieMuqueuses='" + getHemorragieMuqueuses() + "'" +
            ", epistaxis='" + getEpistaxis() + "'" +
            ", epistaxisFrequence=" + getEpistaxisFrequence() +
            ", hemorragiesIntrabuccales='" + getHemorragiesIntrabuccales() + "'" +
            ", hemorragiesIntrabuccalesFrequence=" + getHemorragiesIntrabuccalesFrequence() +
            ", menoragie='" + getMenoragie() + "'" +
            ", menoragieFrequence=" + getMenoragieFrequence() +
            ", scoreHighamFait='" + getScoreHighamFait() + "'" +
            ", scoreHighamValeur=" + getScoreHighamValeur() +
            ", hematome='" + getHematome() + "'" +
            ", hematomeSpontane='" + getHematomeSpontane() + "'" +
            ", hematomeProvoque='" + getHematomeProvoque() + "'" +
            ", hematomeSousCutane='" + getHematomeSousCutane() + "'" +
            ", hematomeMusculaire='" + getHematomeMusculaire() + "'" +
            ", hematomeTissusMous='" + getHematomeTissusMous() + "'" +
            ", hematomeFrequence=" + getHematomeFrequence() +
            ", hemarthrose='" + getHemarthrose() + "'" +
            ", hemarthroseSpontane='" + getHemarthroseSpontane() + "'" +
            ", hemarthroseProvoque='" + getHemarthroseProvoque() + "'" +
            ", hemarthroseSiege='" + getHemarthroseSiege() + "'" +
            ", hemarthroseFrequence=" + getHemarthroseFrequence() +
            ", hemorragieAmygdalienne='" + getHemorragieAmygdalienne() + "'" +
            ", hemorragieAmygdalienneFrequence=" + getHemorragieAmygdalienneFrequence() +
            ", avulsionDentaire='" + getAvulsionDentaire() + "'" +
            ", avulsionPrepare='" + getAvulsionPrepare() + "'" +
            ", avulsionBienDeroule='" + getAvulsionBienDeroule() + "'" +
            ", traumatisme='" + getTraumatisme() + "'" +
            ", traumatismeType='" + getTraumatismeType() + "'" +
            ", chirurgie='" + getChirurgie() + "'" +
            ", chirurgieType='" + getChirurgieType() + "'" +
            ", chirurgiePrepare='" + getChirurgiePrepare() + "'" +
            ", chirurgieBienDeroule='" + getChirurgieBienDeroule() + "'" +
            ", accouchement='" + getAccouchement() + "'" +
            ", hemorragiePostpartum='" + getHemorragiePostpartum() + "'" +
            ", circoncision='" + getCirconcision() + "'" +
            ", ageCirconcision=" + getAgeCirconcision() +
            ", circoncisionPrepare='" + getCirconcisionPrepare() + "'" +
            ", circoncisionBienDeroule='" + getCirconcisionBienDeroule() + "'" +
            ", hemorragieVisceres='" + getHemorragieVisceres() + "'" +
            ", hemorragieVisceresSpontane='" + getHemorragieVisceresSpontane() + "'" +
            ", hemorragieVisceresProvoque='" + getHemorragieVisceresProvoque() + "'" +
            ", hemorragieVisceresType='" + getHemorragieVisceresType() + "'" +
            ", autreHemorragieVisceres='" + getAutreHemorragieVisceres() + "'" +
            ", explorationVisceres='" + getExplorationVisceres() + "'" +
            ", lesionSousJacente='" + getLesionSousJacente() + "'" +
            ", hemorragieGrave='" + getHemorragieGrave() + "'" +
            ", graveDeglobulisation='" + getGraveDeglobulisation() + "'" +
            ", graveEtatDeChoc='" + getGraveEtatDeChoc() + "'" +
            ", graveParLocalisation='" + getGraveParLocalisation() + "'" +
            ", siegeGraviteLocalisation='" + getSiegeGraviteLocalisation() + "'" +
            ", nombreHospitalisations=" + getNombreHospitalisations() +
            ", nombreTransfusions=" + getNombreTransfusions() +
            ", scoreISTHBAT=" + getScoreISTHBAT() +
            ", desmopressine='" + getDesmopressine() + "'" +
            ", traitementSubstitutifPrevention='" + getTraitementSubstitutifPrevention() + "'" +
            ", traitementPreparation='" + getTraitementPreparation() + "'" +
            ", traitementSubstitutifCuratif='" + getTraitementSubstitutifCuratif() + "'" +
            ", cryoprecipite='" + getCryoprecipite() + "'" +
            ", pfc='" + getPfc() + "'" +
            ", concentreFVIIIFVW='" + getConcentreFVIIIFVW() + "'" +
            ", concentreFVW='" + getConcentreFVW() + "'" +
            ", concentreFVIII='" + getConcentreFVIII() + "'" +
            ", agentBypassant='" + getAgentBypassant() + "'" +
            ", antifibrinolytiques='" + getAntifibrinolytiques() + "'" +
            ", voieOrale='" + getVoieOrale() + "'" +
            ", voieIV='" + getVoieIV() + "'" +
            ", voieLocale='" + getVoieLocale() + "'" +
            ", oestroprogestatifs='" + getOestroprogestatifs() + "'" +
            ", traitementAntalgique='" + getTraitementAntalgique() + "'" +
            ", corticotherapie='" + getCorticotherapie() + "'" +
            ", kinesitherapie='" + getKinesitherapie() + "'" +
            ", traitementMartial='" + getTraitementMartial() + "'" +
            ", culotsGlobulaires='" + getCulotsGlobulaires() + "'" +
            ", nombreCulotsGlobulaires=" + getNombreCulotsGlobulaires() +
            ", immunosuppresseur='" + getImmunosuppresseur() + "'" +
            ", nomImmunosuppresseur='" + getNomImmunosuppresseur() + "'" +
            ", anemie='" + getAnemie() + "'" +
            ", anemieSevere='" + getAnemieSevere() + "'" +
            ", tauxHb=" + getTauxHb() +
            ", anticorpsAntiFVW='" + getAnticorpsAntiFVW() + "'" +
            ", anticorpsAntiFVIII='" + getAnticorpsAntiFVIII() + "'" +
            ", hepatiteB='" + getHepatiteB() + "'" +
            ", hepatiteC='" + getHepatiteC() + "'" +
            ", hiv='" + getHiv() + "'" +
            ", thrombose='" + getThrombose() + "'" +
            ", reactionAllergique='" + getReactionAllergique() + "'" +
            ", autreComplication='" + getAutreComplication() + "'" +
            ", autreComplicationDescription='" + getAutreComplicationDescription() + "'" +
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

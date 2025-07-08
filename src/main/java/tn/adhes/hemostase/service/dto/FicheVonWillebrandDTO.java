package tn.adhes.hemostase.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
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
 * A DTO for the {@link tn.adhes.hemostase.domain.FicheVonWillebrand} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheVonWillebrandDTO implements Serializable {

    private Long id;

    private String dossierNumber;

    @Size(max = 25)
    private String ordreNumber;

    @Size(max = 25)
    private String indexNumber;

    @Size(max = 25)
    private String prenom;

    @Size(max = 25)
    private String nom;

    @Size(max = 25)
    private String nomJeuneFille;

    @Size(max = 25)
    private String profession;

    @NotNull
    private CivilStatus etatCivil;

    @NotNull
    private ServiceCliniqueType serviceClinique;

    @Size(max = 255)
    private String autreServiceClinique;

    @NotNull
    private CouvertureSociale couvertureSociale;

    @NotNull
    private Gender sexe;

    private LocalDate dateNaissance;

    private Integer ageActuel;

    private Region origine;

    @Size(max = 25)
    private String autreOrigine;

    @Size(max = 25)
    private String adresse;

    @Size(max = 20)
    private String telephone;

    @Size(max = 25)
    private String prenomPere;

    @Size(max = 25)
    private String professionPere;

    @Size(max = 25)
    private String nomPrenomMere;

    @Size(max = 25)
    private String professionMere;

    @Size(max = 25)
    private String medecinTraitant;

    @Size(max = 25)
    private String serviceOrigine;

    @Size(max = 25)
    private String biologisteResponsableDg;

    @NotNull
    private DiagnosticType diagnostic;

    @Size(max = 25)
    private String autreDiagnostic;

    private Integer anneeDiagnostic;

    private LocalDate dateEnregistrementRegistre;

    private OuiNonNP consentementRegistre;

    private OuiNonNP parentsConsanguins;

    private DegreParente degreParenteConsanguins;

    private OuiNonNP casSimilaires;

    private Integer nombreCas;

    private Integer cas1Numero;

    private LienParente lienParenteCas1;

    @Size(max = 25)
    private String degreParenteCas1;

    @Size(max = 255)
    private String typeHemorragieCas1;

    private Integer cas2Numero;

    private LienParente lienParenteCas2;

    @Size(max = 25)
    private String degreParenteCas2;

    @Size(max = 255)
    private String typeHemorragieCas2;

    private Integer cas3Numero;

    private LienParente lienParenteCas3;

    @Size(max = 25)
    private String degreParenteCas3;

    @Size(max = 255)
    private String typeHemorragieCas3;

    private OuiNonNP decesSyndromeHemorragique;

    private Integer nombreDeces;

    private Integer deces1Numero;

    private LienParente lienParenteDeces1;

    @Size(max = 255)
    private String causeDeces1;

    private Integer deces2Numero;

    private LienParente lienParenteDeces2;

    @Size(max = 255)
    private String causeDeces2;

    private Integer deces3Numero;

    private LienParente lienParenteDeces3;

    @Size(max = 255)
    private String causeDeces3;

    private FaiteNonFaite enqueteFamiliale;

    private CirconstanceDecouverteMvW circonstancesDecouverte;

    @Size(max = 100)
    private String circonstancesDecouvertePrecision;

    private OuiNonNP jamaisSaigne;

    private Integer agePremierSigneCliniqueJours;

    private Integer agePremierSigneCliniqueMois;

    private Integer agePremierSigneCliniqueAnnees;

    private OuiNonNP symptomeEcchymose;

    private OuiNonNP symptomeEpistaxis;

    private OuiNonNP symptomeGingivorragies;

    private OuiNonNP symptomeMetrorragies;

    private OuiNonNP symptomeMelena;

    private OuiNonNP symptomeHemoptysie;

    private OuiNonNP symptomeHemorragiePostAmygdalectomie;

    private OuiNonNP symptomeHemorragiePostAvulsionDentaire;

    private OuiNonNP symptomeHemorragieChuteCordon;

    private OuiNonNP symptomeAutre;

    @Size(max = 255)
    private String symptomeAutreDescription;

    private LocalDate datePremiereConsultation;

    private Integer agePremiereConsultationJours;

    private Integer agePremiereConsultationMois;

    private Integer agePremiereConsultationAnnees;

    private Integer ageDiagnosticJours;

    private Integer ageDiagnosticMois;

    private Integer ageDiagnosticAnnees;

    @NotNull
    private TypeMvW typeMvW;

    private SousTypeMvW sousTypeMvW;

    private String pathologiesAssociees;

    private LocalDate dateTestConfirmation;

    private GroupeSanguin gs;

    private Double hb;

    private Double hte;

    private Integer plaquettes;

    private Double tp;

    private Double tcaMT;

    private Double tcaMPlusT;

    private Double tcaMPlusT2H;

    private Double fibrinogene;

    private TypeTS tsResultat;

    private Double tsValeur;

    private TypeOcclusion occlusionPlaquettaire;

    private Double collageneEpinephrine;

    private Double collageneADP;

    private Double fvwag;

    private Double fvwact;

    private Double f8c;

    private Double ratioFvwactFvwag;

    private Double ratioF8cFvwag;

    private FaiteNonFaite autresTestsSpecifiques;

    private FaiteNonFaite vwfCB;

    private Double vwfCBResultat;

    private FaiteNonFaite ripa;

    private TypeRipaResultat ripaResultat;

    private TypeMultimere multimereHPM;

    private FaiteNonFaite liaisonFvwF8;

    private TypeResultatPatho liaisonFvwF8Resultat;

    private FaiteNonFaite fvwpp;

    private TypeFvWppRatio fvwppResultat;

    private FaiteNonFaite mutationGenetique;

    private Boolean mutationPresente;

    private String mutationNom;

    private TypeZygote mutationZygote;

    private OuiNonNP ecchymoses;

    private Boolean ecchymosesSpontanees;

    private Boolean ecchymosesProvoquees;

    private String ecchymosesSiege;

    private Integer ecchymosesFrequence;

    private OuiNonNP hemorragieMuqueuses;

    private OuiNonNP epistaxis;

    private Integer epistaxisFrequence;

    private OuiNonNP hemorragiesIntrabuccales;

    private Integer hemorragiesIntrabuccalesFrequence;

    private OuiNonNP menoragie;

    private Integer menoragieFrequence;

    private Boolean scoreHighamFait;

    private Integer scoreHighamValeur;

    private OuiNonNP hematome;

    private Boolean hematomeSpontane;

    private Boolean hematomeProvoque;

    private Boolean hematomeSousCutane;

    private Boolean hematomeMusculaire;

    private Boolean hematomeTissusMous;

    private Integer hematomeFrequence;

    private OuiNonNP hemarthrose;

    private Boolean hemarthroseSpontane;

    private Boolean hemarthroseProvoque;

    private String hemarthroseSiege;

    private Integer hemarthroseFrequence;

    private OuiNonNP hemorragieAmygdalienne;

    private Integer hemorragieAmygdalienneFrequence;

    private OuiNonNP avulsionDentaire;

    private Boolean avulsionPrepare;

    private OuiNonNP avulsionBienDeroule;

    private OuiNonNP traumatisme;

    private String traumatismeType;

    private OuiNonNP chirurgie;

    private String chirurgieType;

    private OuiNonNP chirurgiePrepare;

    private OuiNonNP chirurgieBienDeroule;

    private OuiNonNP accouchement;

    private OuiNonNP hemorragiePostpartum;

    private OuiNonNP circoncision;

    private Integer ageCirconcision;

    private OuiNonNP circoncisionPrepare;

    private OuiNonNP circoncisionBienDeroule;

    private OuiNonNP hemorragieVisceres;

    private Boolean hemorragieVisceresSpontane;

    private Boolean hemorragieVisceresProvoque;

    private TypeHemorragieVisceres hemorragieVisceresType;

    private String autreHemorragieVisceres;

    private Boolean explorationVisceres;

    private String lesionSousJacente;

    private OuiNonNP hemorragieGrave;

    private Boolean graveDeglobulisation;

    private Boolean graveEtatDeChoc;

    private Boolean graveParLocalisation;

    private String siegeGraviteLocalisation;

    private Integer nombreHospitalisations;

    private Integer nombreTransfusions;

    private Integer scoreISTHBAT;

    private OuiNonNP desmopressine;

    private OuiNonNP traitementSubstitutifPrevention;

    private String traitementPreparation;

    private OuiNonNP traitementSubstitutifCuratif;

    private OuiNonNP cryoprecipite;

    private OuiNonNP pfc;

    private OuiNonNP concentreFVIIIFVW;

    private OuiNonNP concentreFVW;

    private OuiNonNP concentreFVIII;

    private OuiNonNP agentBypassant;

    private OuiNonNP antifibrinolytiques;

    private Boolean voieOrale;

    private Boolean voieIV;

    private Boolean voieLocale;

    private OuiNonNP oestroprogestatifs;

    private OuiNonNP traitementAntalgique;

    private OuiNonNP corticotherapie;

    private OuiNonNP kinesitherapie;

    private OuiNonNP traitementMartial;

    private OuiNonNP culotsGlobulaires;

    private Integer nombreCulotsGlobulaires;

    private OuiNonNP immunosuppresseur;

    private String nomImmunosuppresseur;

    private OuiNonNP anemie;

    private Boolean anemieSevere;

    private Double tauxHb;

    private ResultatSerologie anticorpsAntiFVW;

    private ResultatSerologie anticorpsAntiFVIII;

    private ResultatSerologie hepatiteB;

    private ResultatSerologie hepatiteC;

    private ResultatSerologie hiv;

    private Boolean thrombose;

    private Boolean reactionAllergique;

    private Boolean autreComplication;

    private String autreComplicationDescription;

    private OuiNonNP adherenceTherapeutique;

    private String causeNonAdherence;

    private StatutSocial statutSocial;

    private OuiNonNP marie;

    private OuiNonNP enfants;

    private OuiNonNP activitePhysique;

    private String typeActivitePhysique;

    private OuiNonNP sequelles;

    private String typeSequelles;

    private OuiNonNP patientDecede;

    private LocalDate dateDeces;

    private Integer ageDeces;

    private CauseDeces causeDeces;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDossierNumber() {
        return dossierNumber;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return ordreNumber;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomJeuneFille() {
        return nomJeuneFille;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public CivilStatus getEtatCivil() {
        return etatCivil;
    }

    public void setEtatCivil(CivilStatus etatCivil) {
        this.etatCivil = etatCivil;
    }

    public ServiceCliniqueType getServiceClinique() {
        return serviceClinique;
    }

    public void setServiceClinique(ServiceCliniqueType serviceClinique) {
        this.serviceClinique = serviceClinique;
    }

    public String getAutreServiceClinique() {
        return autreServiceClinique;
    }

    public void setAutreServiceClinique(String autreServiceClinique) {
        this.autreServiceClinique = autreServiceClinique;
    }

    public CouvertureSociale getCouvertureSociale() {
        return couvertureSociale;
    }

    public void setCouvertureSociale(CouvertureSociale couvertureSociale) {
        this.couvertureSociale = couvertureSociale;
    }

    public Gender getSexe() {
        return sexe;
    }

    public void setSexe(Gender sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Integer getAgeActuel() {
        return ageActuel;
    }

    public void setAgeActuel(Integer ageActuel) {
        this.ageActuel = ageActuel;
    }

    public Region getOrigine() {
        return origine;
    }

    public void setOrigine(Region origine) {
        this.origine = origine;
    }

    public String getAutreOrigine() {
        return autreOrigine;
    }

    public void setAutreOrigine(String autreOrigine) {
        this.autreOrigine = autreOrigine;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrenomPere() {
        return prenomPere;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getProfessionPere() {
        return professionPere;
    }

    public void setProfessionPere(String professionPere) {
        this.professionPere = professionPere;
    }

    public String getNomPrenomMere() {
        return nomPrenomMere;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }

    public String getProfessionMere() {
        return professionMere;
    }

    public void setProfessionMere(String professionMere) {
        this.professionMere = professionMere;
    }

    public String getMedecinTraitant() {
        return medecinTraitant;
    }

    public void setMedecinTraitant(String medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }

    public String getServiceOrigine() {
        return serviceOrigine;
    }

    public void setServiceOrigine(String serviceOrigine) {
        this.serviceOrigine = serviceOrigine;
    }

    public String getBiologisteResponsableDg() {
        return biologisteResponsableDg;
    }

    public void setBiologisteResponsableDg(String biologisteResponsableDg) {
        this.biologisteResponsableDg = biologisteResponsableDg;
    }

    public DiagnosticType getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return autreDiagnostic;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public Integer getAnneeDiagnostic() {
        return anneeDiagnostic;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return dateEnregistrementRegistre;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
    }

    public OuiNonNP getConsentementRegistre() {
        return consentementRegistre;
    }

    public void setConsentementRegistre(OuiNonNP consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public OuiNonNP getParentsConsanguins() {
        return parentsConsanguins;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public DegreParente getDegreParenteConsanguins() {
        return degreParenteConsanguins;
    }

    public void setDegreParenteConsanguins(DegreParente degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public OuiNonNP getCasSimilaires() {
        return casSimilaires;
    }

    public void setCasSimilaires(OuiNonNP casSimilaires) {
        this.casSimilaires = casSimilaires;
    }

    public Integer getNombreCas() {
        return nombreCas;
    }

    public void setNombreCas(Integer nombreCas) {
        this.nombreCas = nombreCas;
    }

    public Integer getCas1Numero() {
        return cas1Numero;
    }

    public void setCas1Numero(Integer cas1Numero) {
        this.cas1Numero = cas1Numero;
    }

    public LienParente getLienParenteCas1() {
        return lienParenteCas1;
    }

    public void setLienParenteCas1(LienParente lienParenteCas1) {
        this.lienParenteCas1 = lienParenteCas1;
    }

    public String getDegreParenteCas1() {
        return degreParenteCas1;
    }

    public void setDegreParenteCas1(String degreParenteCas1) {
        this.degreParenteCas1 = degreParenteCas1;
    }

    public String getTypeHemorragieCas1() {
        return typeHemorragieCas1;
    }

    public void setTypeHemorragieCas1(String typeHemorragieCas1) {
        this.typeHemorragieCas1 = typeHemorragieCas1;
    }

    public Integer getCas2Numero() {
        return cas2Numero;
    }

    public void setCas2Numero(Integer cas2Numero) {
        this.cas2Numero = cas2Numero;
    }

    public LienParente getLienParenteCas2() {
        return lienParenteCas2;
    }

    public void setLienParenteCas2(LienParente lienParenteCas2) {
        this.lienParenteCas2 = lienParenteCas2;
    }

    public String getDegreParenteCas2() {
        return degreParenteCas2;
    }

    public void setDegreParenteCas2(String degreParenteCas2) {
        this.degreParenteCas2 = degreParenteCas2;
    }

    public String getTypeHemorragieCas2() {
        return typeHemorragieCas2;
    }

    public void setTypeHemorragieCas2(String typeHemorragieCas2) {
        this.typeHemorragieCas2 = typeHemorragieCas2;
    }

    public Integer getCas3Numero() {
        return cas3Numero;
    }

    public void setCas3Numero(Integer cas3Numero) {
        this.cas3Numero = cas3Numero;
    }

    public LienParente getLienParenteCas3() {
        return lienParenteCas3;
    }

    public void setLienParenteCas3(LienParente lienParenteCas3) {
        this.lienParenteCas3 = lienParenteCas3;
    }

    public String getDegreParenteCas3() {
        return degreParenteCas3;
    }

    public void setDegreParenteCas3(String degreParenteCas3) {
        this.degreParenteCas3 = degreParenteCas3;
    }

    public String getTypeHemorragieCas3() {
        return typeHemorragieCas3;
    }

    public void setTypeHemorragieCas3(String typeHemorragieCas3) {
        this.typeHemorragieCas3 = typeHemorragieCas3;
    }

    public OuiNonNP getDecesSyndromeHemorragique() {
        return decesSyndromeHemorragique;
    }

    public void setDecesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.decesSyndromeHemorragique = decesSyndromeHemorragique;
    }

    public Integer getNombreDeces() {
        return nombreDeces;
    }

    public void setNombreDeces(Integer nombreDeces) {
        this.nombreDeces = nombreDeces;
    }

    public Integer getDeces1Numero() {
        return deces1Numero;
    }

    public void setDeces1Numero(Integer deces1Numero) {
        this.deces1Numero = deces1Numero;
    }

    public LienParente getLienParenteDeces1() {
        return lienParenteDeces1;
    }

    public void setLienParenteDeces1(LienParente lienParenteDeces1) {
        this.lienParenteDeces1 = lienParenteDeces1;
    }

    public String getCauseDeces1() {
        return causeDeces1;
    }

    public void setCauseDeces1(String causeDeces1) {
        this.causeDeces1 = causeDeces1;
    }

    public Integer getDeces2Numero() {
        return deces2Numero;
    }

    public void setDeces2Numero(Integer deces2Numero) {
        this.deces2Numero = deces2Numero;
    }

    public LienParente getLienParenteDeces2() {
        return lienParenteDeces2;
    }

    public void setLienParenteDeces2(LienParente lienParenteDeces2) {
        this.lienParenteDeces2 = lienParenteDeces2;
    }

    public String getCauseDeces2() {
        return causeDeces2;
    }

    public void setCauseDeces2(String causeDeces2) {
        this.causeDeces2 = causeDeces2;
    }

    public Integer getDeces3Numero() {
        return deces3Numero;
    }

    public void setDeces3Numero(Integer deces3Numero) {
        this.deces3Numero = deces3Numero;
    }

    public LienParente getLienParenteDeces3() {
        return lienParenteDeces3;
    }

    public void setLienParenteDeces3(LienParente lienParenteDeces3) {
        this.lienParenteDeces3 = lienParenteDeces3;
    }

    public String getCauseDeces3() {
        return causeDeces3;
    }

    public void setCauseDeces3(String causeDeces3) {
        this.causeDeces3 = causeDeces3;
    }

    public FaiteNonFaite getEnqueteFamiliale() {
        return enqueteFamiliale;
    }

    public void setEnqueteFamiliale(FaiteNonFaite enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public CirconstanceDecouverteMvW getCirconstancesDecouverte() {
        return circonstancesDecouverte;
    }

    public void setCirconstancesDecouverte(CirconstanceDecouverteMvW circonstancesDecouverte) {
        this.circonstancesDecouverte = circonstancesDecouverte;
    }

    public String getCirconstancesDecouvertePrecision() {
        return circonstancesDecouvertePrecision;
    }

    public void setCirconstancesDecouvertePrecision(String circonstancesDecouvertePrecision) {
        this.circonstancesDecouvertePrecision = circonstancesDecouvertePrecision;
    }

    public OuiNonNP getJamaisSaigne() {
        return jamaisSaigne;
    }

    public void setJamaisSaigne(OuiNonNP jamaisSaigne) {
        this.jamaisSaigne = jamaisSaigne;
    }

    public Integer getAgePremierSigneCliniqueJours() {
        return agePremierSigneCliniqueJours;
    }

    public void setAgePremierSigneCliniqueJours(Integer agePremierSigneCliniqueJours) {
        this.agePremierSigneCliniqueJours = agePremierSigneCliniqueJours;
    }

    public Integer getAgePremierSigneCliniqueMois() {
        return agePremierSigneCliniqueMois;
    }

    public void setAgePremierSigneCliniqueMois(Integer agePremierSigneCliniqueMois) {
        this.agePremierSigneCliniqueMois = agePremierSigneCliniqueMois;
    }

    public Integer getAgePremierSigneCliniqueAnnees() {
        return agePremierSigneCliniqueAnnees;
    }

    public void setAgePremierSigneCliniqueAnnees(Integer agePremierSigneCliniqueAnnees) {
        this.agePremierSigneCliniqueAnnees = agePremierSigneCliniqueAnnees;
    }

    public OuiNonNP getSymptomeEcchymose() {
        return symptomeEcchymose;
    }

    public void setSymptomeEcchymose(OuiNonNP symptomeEcchymose) {
        this.symptomeEcchymose = symptomeEcchymose;
    }

    public OuiNonNP getSymptomeEpistaxis() {
        return symptomeEpistaxis;
    }

    public void setSymptomeEpistaxis(OuiNonNP symptomeEpistaxis) {
        this.symptomeEpistaxis = symptomeEpistaxis;
    }

    public OuiNonNP getSymptomeGingivorragies() {
        return symptomeGingivorragies;
    }

    public void setSymptomeGingivorragies(OuiNonNP symptomeGingivorragies) {
        this.symptomeGingivorragies = symptomeGingivorragies;
    }

    public OuiNonNP getSymptomeMetrorragies() {
        return symptomeMetrorragies;
    }

    public void setSymptomeMetrorragies(OuiNonNP symptomeMetrorragies) {
        this.symptomeMetrorragies = symptomeMetrorragies;
    }

    public OuiNonNP getSymptomeMelena() {
        return symptomeMelena;
    }

    public void setSymptomeMelena(OuiNonNP symptomeMelena) {
        this.symptomeMelena = symptomeMelena;
    }

    public OuiNonNP getSymptomeHemoptysie() {
        return symptomeHemoptysie;
    }

    public void setSymptomeHemoptysie(OuiNonNP symptomeHemoptysie) {
        this.symptomeHemoptysie = symptomeHemoptysie;
    }

    public OuiNonNP getSymptomeHemorragiePostAmygdalectomie() {
        return symptomeHemorragiePostAmygdalectomie;
    }

    public void setSymptomeHemorragiePostAmygdalectomie(OuiNonNP symptomeHemorragiePostAmygdalectomie) {
        this.symptomeHemorragiePostAmygdalectomie = symptomeHemorragiePostAmygdalectomie;
    }

    public OuiNonNP getSymptomeHemorragiePostAvulsionDentaire() {
        return symptomeHemorragiePostAvulsionDentaire;
    }

    public void setSymptomeHemorragiePostAvulsionDentaire(OuiNonNP symptomeHemorragiePostAvulsionDentaire) {
        this.symptomeHemorragiePostAvulsionDentaire = symptomeHemorragiePostAvulsionDentaire;
    }

    public OuiNonNP getSymptomeHemorragieChuteCordon() {
        return symptomeHemorragieChuteCordon;
    }

    public void setSymptomeHemorragieChuteCordon(OuiNonNP symptomeHemorragieChuteCordon) {
        this.symptomeHemorragieChuteCordon = symptomeHemorragieChuteCordon;
    }

    public OuiNonNP getSymptomeAutre() {
        return symptomeAutre;
    }

    public void setSymptomeAutre(OuiNonNP symptomeAutre) {
        this.symptomeAutre = symptomeAutre;
    }

    public String getSymptomeAutreDescription() {
        return symptomeAutreDescription;
    }

    public void setSymptomeAutreDescription(String symptomeAutreDescription) {
        this.symptomeAutreDescription = symptomeAutreDescription;
    }

    public LocalDate getDatePremiereConsultation() {
        return datePremiereConsultation;
    }

    public void setDatePremiereConsultation(LocalDate datePremiereConsultation) {
        this.datePremiereConsultation = datePremiereConsultation;
    }

    public Integer getAgePremiereConsultationJours() {
        return agePremiereConsultationJours;
    }

    public void setAgePremiereConsultationJours(Integer agePremiereConsultationJours) {
        this.agePremiereConsultationJours = agePremiereConsultationJours;
    }

    public Integer getAgePremiereConsultationMois() {
        return agePremiereConsultationMois;
    }

    public void setAgePremiereConsultationMois(Integer agePremiereConsultationMois) {
        this.agePremiereConsultationMois = agePremiereConsultationMois;
    }

    public Integer getAgePremiereConsultationAnnees() {
        return agePremiereConsultationAnnees;
    }

    public void setAgePremiereConsultationAnnees(Integer agePremiereConsultationAnnees) {
        this.agePremiereConsultationAnnees = agePremiereConsultationAnnees;
    }

    public Integer getAgeDiagnosticJours() {
        return ageDiagnosticJours;
    }

    public void setAgeDiagnosticJours(Integer ageDiagnosticJours) {
        this.ageDiagnosticJours = ageDiagnosticJours;
    }

    public Integer getAgeDiagnosticMois() {
        return ageDiagnosticMois;
    }

    public void setAgeDiagnosticMois(Integer ageDiagnosticMois) {
        this.ageDiagnosticMois = ageDiagnosticMois;
    }

    public Integer getAgeDiagnosticAnnees() {
        return ageDiagnosticAnnees;
    }

    public void setAgeDiagnosticAnnees(Integer ageDiagnosticAnnees) {
        this.ageDiagnosticAnnees = ageDiagnosticAnnees;
    }

    public TypeMvW getTypeMvW() {
        return typeMvW;
    }

    public void setTypeMvW(TypeMvW typeMvW) {
        this.typeMvW = typeMvW;
    }

    public SousTypeMvW getSousTypeMvW() {
        return sousTypeMvW;
    }

    public void setSousTypeMvW(SousTypeMvW sousTypeMvW) {
        this.sousTypeMvW = sousTypeMvW;
    }

    public String getPathologiesAssociees() {
        return pathologiesAssociees;
    }

    public void setPathologiesAssociees(String pathologiesAssociees) {
        this.pathologiesAssociees = pathologiesAssociees;
    }

    public LocalDate getDateTestConfirmation() {
        return dateTestConfirmation;
    }

    public void setDateTestConfirmation(LocalDate dateTestConfirmation) {
        this.dateTestConfirmation = dateTestConfirmation;
    }

    public GroupeSanguin getGs() {
        return gs;
    }

    public void setGs(GroupeSanguin gs) {
        this.gs = gs;
    }

    public Double getHb() {
        return hb;
    }

    public void setHb(Double hb) {
        this.hb = hb;
    }

    public Double getHte() {
        return hte;
    }

    public void setHte(Double hte) {
        this.hte = hte;
    }

    public Integer getPlaquettes() {
        return plaquettes;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getTcaMT() {
        return tcaMT;
    }

    public void setTcaMT(Double tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Double getTcaMPlusT() {
        return tcaMPlusT;
    }

    public void setTcaMPlusT(Double tcaMPlusT) {
        this.tcaMPlusT = tcaMPlusT;
    }

    public Double getTcaMPlusT2H() {
        return tcaMPlusT2H;
    }

    public void setTcaMPlusT2H(Double tcaMPlusT2H) {
        this.tcaMPlusT2H = tcaMPlusT2H;
    }

    public Double getFibrinogene() {
        return fibrinogene;
    }

    public void setFibrinogene(Double fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public TypeTS getTsResultat() {
        return tsResultat;
    }

    public void setTsResultat(TypeTS tsResultat) {
        this.tsResultat = tsResultat;
    }

    public Double getTsValeur() {
        return tsValeur;
    }

    public void setTsValeur(Double tsValeur) {
        this.tsValeur = tsValeur;
    }

    public TypeOcclusion getOcclusionPlaquettaire() {
        return occlusionPlaquettaire;
    }

    public void setOcclusionPlaquettaire(TypeOcclusion occlusionPlaquettaire) {
        this.occlusionPlaquettaire = occlusionPlaquettaire;
    }

    public Double getCollageneEpinephrine() {
        return collageneEpinephrine;
    }

    public void setCollageneEpinephrine(Double collageneEpinephrine) {
        this.collageneEpinephrine = collageneEpinephrine;
    }

    public Double getCollageneADP() {
        return collageneADP;
    }

    public void setCollageneADP(Double collageneADP) {
        this.collageneADP = collageneADP;
    }

    public Double getFvwag() {
        return fvwag;
    }

    public void setFvwag(Double fvwag) {
        this.fvwag = fvwag;
    }

    public Double getFvwact() {
        return fvwact;
    }

    public void setFvwact(Double fvwact) {
        this.fvwact = fvwact;
    }

    public Double getf8c() {
        return f8c;
    }

    public void setf8c(Double f8c) {
        this.f8c = f8c;
    }

    public Double getRatioFvwactFvwag() {
        return ratioFvwactFvwag;
    }

    public void setRatioFvwactFvwag(Double ratioFvwactFvwag) {
        this.ratioFvwactFvwag = ratioFvwactFvwag;
    }

    public Double getRatioF8cFvwag() {
        return ratioF8cFvwag;
    }

    public void setRatioF8cFvwag(Double ratioF8cFvwag) {
        this.ratioF8cFvwag = ratioF8cFvwag;
    }

    public FaiteNonFaite getAutresTestsSpecifiques() {
        return autresTestsSpecifiques;
    }

    public void setAutresTestsSpecifiques(FaiteNonFaite autresTestsSpecifiques) {
        this.autresTestsSpecifiques = autresTestsSpecifiques;
    }

    public FaiteNonFaite getVwfCB() {
        return vwfCB;
    }

    public void setVwfCB(FaiteNonFaite vwfCB) {
        this.vwfCB = vwfCB;
    }

    public Double getVwfCBResultat() {
        return vwfCBResultat;
    }

    public void setVwfCBResultat(Double vwfCBResultat) {
        this.vwfCBResultat = vwfCBResultat;
    }

    public FaiteNonFaite getRipa() {
        return ripa;
    }

    public void setRipa(FaiteNonFaite ripa) {
        this.ripa = ripa;
    }

    public TypeRipaResultat getRipaResultat() {
        return ripaResultat;
    }

    public void setRipaResultat(TypeRipaResultat ripaResultat) {
        this.ripaResultat = ripaResultat;
    }

    public TypeMultimere getMultimereHPM() {
        return multimereHPM;
    }

    public void setMultimereHPM(TypeMultimere multimereHPM) {
        this.multimereHPM = multimereHPM;
    }

    public FaiteNonFaite getLiaisonFvwF8() {
        return liaisonFvwF8;
    }

    public void setLiaisonFvwF8(FaiteNonFaite liaisonFvwF8) {
        this.liaisonFvwF8 = liaisonFvwF8;
    }

    public TypeResultatPatho getLiaisonFvwF8Resultat() {
        return liaisonFvwF8Resultat;
    }

    public void setLiaisonFvwF8Resultat(TypeResultatPatho liaisonFvwF8Resultat) {
        this.liaisonFvwF8Resultat = liaisonFvwF8Resultat;
    }

    public FaiteNonFaite getFvwpp() {
        return fvwpp;
    }

    public void setFvwpp(FaiteNonFaite fvwpp) {
        this.fvwpp = fvwpp;
    }

    public TypeFvWppRatio getFvwppResultat() {
        return fvwppResultat;
    }

    public void setFvwppResultat(TypeFvWppRatio fvwppResultat) {
        this.fvwppResultat = fvwppResultat;
    }

    public FaiteNonFaite getMutationGenetique() {
        return mutationGenetique;
    }

    public void setMutationGenetique(FaiteNonFaite mutationGenetique) {
        this.mutationGenetique = mutationGenetique;
    }

    public Boolean getMutationPresente() {
        return mutationPresente;
    }

    public void setMutationPresente(Boolean mutationPresente) {
        this.mutationPresente = mutationPresente;
    }

    public String getMutationNom() {
        return mutationNom;
    }

    public void setMutationNom(String mutationNom) {
        this.mutationNom = mutationNom;
    }

    public TypeZygote getMutationZygote() {
        return mutationZygote;
    }

    public void setMutationZygote(TypeZygote mutationZygote) {
        this.mutationZygote = mutationZygote;
    }

    public OuiNonNP getEcchymoses() {
        return ecchymoses;
    }

    public void setEcchymoses(OuiNonNP ecchymoses) {
        this.ecchymoses = ecchymoses;
    }

    public Boolean getEcchymosesSpontanees() {
        return ecchymosesSpontanees;
    }

    public void setEcchymosesSpontanees(Boolean ecchymosesSpontanees) {
        this.ecchymosesSpontanees = ecchymosesSpontanees;
    }

    public Boolean getEcchymosesProvoquees() {
        return ecchymosesProvoquees;
    }

    public void setEcchymosesProvoquees(Boolean ecchymosesProvoquees) {
        this.ecchymosesProvoquees = ecchymosesProvoquees;
    }

    public String getEcchymosesSiege() {
        return ecchymosesSiege;
    }

    public void setEcchymosesSiege(String ecchymosesSiege) {
        this.ecchymosesSiege = ecchymosesSiege;
    }

    public Integer getEcchymosesFrequence() {
        return ecchymosesFrequence;
    }

    public void setEcchymosesFrequence(Integer ecchymosesFrequence) {
        this.ecchymosesFrequence = ecchymosesFrequence;
    }

    public OuiNonNP getHemorragieMuqueuses() {
        return hemorragieMuqueuses;
    }

    public void setHemorragieMuqueuses(OuiNonNP hemorragieMuqueuses) {
        this.hemorragieMuqueuses = hemorragieMuqueuses;
    }

    public OuiNonNP getEpistaxis() {
        return epistaxis;
    }

    public void setEpistaxis(OuiNonNP epistaxis) {
        this.epistaxis = epistaxis;
    }

    public Integer getEpistaxisFrequence() {
        return epistaxisFrequence;
    }

    public void setEpistaxisFrequence(Integer epistaxisFrequence) {
        this.epistaxisFrequence = epistaxisFrequence;
    }

    public OuiNonNP getHemorragiesIntrabuccales() {
        return hemorragiesIntrabuccales;
    }

    public void setHemorragiesIntrabuccales(OuiNonNP hemorragiesIntrabuccales) {
        this.hemorragiesIntrabuccales = hemorragiesIntrabuccales;
    }

    public Integer getHemorragiesIntrabuccalesFrequence() {
        return hemorragiesIntrabuccalesFrequence;
    }

    public void setHemorragiesIntrabuccalesFrequence(Integer hemorragiesIntrabuccalesFrequence) {
        this.hemorragiesIntrabuccalesFrequence = hemorragiesIntrabuccalesFrequence;
    }

    public OuiNonNP getMenoragie() {
        return menoragie;
    }

    public void setMenoragie(OuiNonNP menoragie) {
        this.menoragie = menoragie;
    }

    public Integer getMenoragieFrequence() {
        return menoragieFrequence;
    }

    public void setMenoragieFrequence(Integer menoragieFrequence) {
        this.menoragieFrequence = menoragieFrequence;
    }

    public Boolean getScoreHighamFait() {
        return scoreHighamFait;
    }

    public void setScoreHighamFait(Boolean scoreHighamFait) {
        this.scoreHighamFait = scoreHighamFait;
    }

    public Integer getScoreHighamValeur() {
        return scoreHighamValeur;
    }

    public void setScoreHighamValeur(Integer scoreHighamValeur) {
        this.scoreHighamValeur = scoreHighamValeur;
    }

    public OuiNonNP getHematome() {
        return hematome;
    }

    public void setHematome(OuiNonNP hematome) {
        this.hematome = hematome;
    }

    public Boolean getHematomeSpontane() {
        return hematomeSpontane;
    }

    public void setHematomeSpontane(Boolean hematomeSpontane) {
        this.hematomeSpontane = hematomeSpontane;
    }

    public Boolean getHematomeProvoque() {
        return hematomeProvoque;
    }

    public void setHematomeProvoque(Boolean hematomeProvoque) {
        this.hematomeProvoque = hematomeProvoque;
    }

    public Boolean getHematomeSousCutane() {
        return hematomeSousCutane;
    }

    public void setHematomeSousCutane(Boolean hematomeSousCutane) {
        this.hematomeSousCutane = hematomeSousCutane;
    }

    public Boolean getHematomeMusculaire() {
        return hematomeMusculaire;
    }

    public void setHematomeMusculaire(Boolean hematomeMusculaire) {
        this.hematomeMusculaire = hematomeMusculaire;
    }

    public Boolean getHematomeTissusMous() {
        return hematomeTissusMous;
    }

    public void setHematomeTissusMous(Boolean hematomeTissusMous) {
        this.hematomeTissusMous = hematomeTissusMous;
    }

    public Integer getHematomeFrequence() {
        return hematomeFrequence;
    }

    public void setHematomeFrequence(Integer hematomeFrequence) {
        this.hematomeFrequence = hematomeFrequence;
    }

    public OuiNonNP getHemarthrose() {
        return hemarthrose;
    }

    public void setHemarthrose(OuiNonNP hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public Boolean getHemarthroseSpontane() {
        return hemarthroseSpontane;
    }

    public void setHemarthroseSpontane(Boolean hemarthroseSpontane) {
        this.hemarthroseSpontane = hemarthroseSpontane;
    }

    public Boolean getHemarthroseProvoque() {
        return hemarthroseProvoque;
    }

    public void setHemarthroseProvoque(Boolean hemarthroseProvoque) {
        this.hemarthroseProvoque = hemarthroseProvoque;
    }

    public String getHemarthroseSiege() {
        return hemarthroseSiege;
    }

    public void setHemarthroseSiege(String hemarthroseSiege) {
        this.hemarthroseSiege = hemarthroseSiege;
    }

    public Integer getHemarthroseFrequence() {
        return hemarthroseFrequence;
    }

    public void setHemarthroseFrequence(Integer hemarthroseFrequence) {
        this.hemarthroseFrequence = hemarthroseFrequence;
    }

    public OuiNonNP getHemorragieAmygdalienne() {
        return hemorragieAmygdalienne;
    }

    public void setHemorragieAmygdalienne(OuiNonNP hemorragieAmygdalienne) {
        this.hemorragieAmygdalienne = hemorragieAmygdalienne;
    }

    public Integer getHemorragieAmygdalienneFrequence() {
        return hemorragieAmygdalienneFrequence;
    }

    public void setHemorragieAmygdalienneFrequence(Integer hemorragieAmygdalienneFrequence) {
        this.hemorragieAmygdalienneFrequence = hemorragieAmygdalienneFrequence;
    }

    public OuiNonNP getAvulsionDentaire() {
        return avulsionDentaire;
    }

    public void setAvulsionDentaire(OuiNonNP avulsionDentaire) {
        this.avulsionDentaire = avulsionDentaire;
    }

    public Boolean getAvulsionPrepare() {
        return avulsionPrepare;
    }

    public void setAvulsionPrepare(Boolean avulsionPrepare) {
        this.avulsionPrepare = avulsionPrepare;
    }

    public OuiNonNP getAvulsionBienDeroule() {
        return avulsionBienDeroule;
    }

    public void setAvulsionBienDeroule(OuiNonNP avulsionBienDeroule) {
        this.avulsionBienDeroule = avulsionBienDeroule;
    }

    public OuiNonNP getTraumatisme() {
        return traumatisme;
    }

    public void setTraumatisme(OuiNonNP traumatisme) {
        this.traumatisme = traumatisme;
    }

    public String getTraumatismeType() {
        return traumatismeType;
    }

    public void setTraumatismeType(String traumatismeType) {
        this.traumatismeType = traumatismeType;
    }

    public OuiNonNP getChirurgie() {
        return chirurgie;
    }

    public void setChirurgie(OuiNonNP chirurgie) {
        this.chirurgie = chirurgie;
    }

    public String getChirurgieType() {
        return chirurgieType;
    }

    public void setChirurgieType(String chirurgieType) {
        this.chirurgieType = chirurgieType;
    }

    public OuiNonNP getChirurgiePrepare() {
        return chirurgiePrepare;
    }

    public void setChirurgiePrepare(OuiNonNP chirurgiePrepare) {
        this.chirurgiePrepare = chirurgiePrepare;
    }

    public OuiNonNP getChirurgieBienDeroule() {
        return chirurgieBienDeroule;
    }

    public void setChirurgieBienDeroule(OuiNonNP chirurgieBienDeroule) {
        this.chirurgieBienDeroule = chirurgieBienDeroule;
    }

    public OuiNonNP getAccouchement() {
        return accouchement;
    }

    public void setAccouchement(OuiNonNP accouchement) {
        this.accouchement = accouchement;
    }

    public OuiNonNP getHemorragiePostpartum() {
        return hemorragiePostpartum;
    }

    public void setHemorragiePostpartum(OuiNonNP hemorragiePostpartum) {
        this.hemorragiePostpartum = hemorragiePostpartum;
    }

    public OuiNonNP getCirconcision() {
        return circoncision;
    }

    public void setCirconcision(OuiNonNP circoncision) {
        this.circoncision = circoncision;
    }

    public Integer getAgeCirconcision() {
        return ageCirconcision;
    }

    public void setAgeCirconcision(Integer ageCirconcision) {
        this.ageCirconcision = ageCirconcision;
    }

    public OuiNonNP getCirconcisionPrepare() {
        return circoncisionPrepare;
    }

    public void setCirconcisionPrepare(OuiNonNP circoncisionPrepare) {
        this.circoncisionPrepare = circoncisionPrepare;
    }

    public OuiNonNP getCirconcisionBienDeroule() {
        return circoncisionBienDeroule;
    }

    public void setCirconcisionBienDeroule(OuiNonNP circoncisionBienDeroule) {
        this.circoncisionBienDeroule = circoncisionBienDeroule;
    }

    public OuiNonNP getHemorragieVisceres() {
        return hemorragieVisceres;
    }

    public void setHemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.hemorragieVisceres = hemorragieVisceres;
    }

    public Boolean getHemorragieVisceresSpontane() {
        return hemorragieVisceresSpontane;
    }

    public void setHemorragieVisceresSpontane(Boolean hemorragieVisceresSpontane) {
        this.hemorragieVisceresSpontane = hemorragieVisceresSpontane;
    }

    public Boolean getHemorragieVisceresProvoque() {
        return hemorragieVisceresProvoque;
    }

    public void setHemorragieVisceresProvoque(Boolean hemorragieVisceresProvoque) {
        this.hemorragieVisceresProvoque = hemorragieVisceresProvoque;
    }

    public TypeHemorragieVisceres getHemorragieVisceresType() {
        return hemorragieVisceresType;
    }

    public void setHemorragieVisceresType(TypeHemorragieVisceres hemorragieVisceresType) {
        this.hemorragieVisceresType = hemorragieVisceresType;
    }

    public String getAutreHemorragieVisceres() {
        return autreHemorragieVisceres;
    }

    public void setAutreHemorragieVisceres(String autreHemorragieVisceres) {
        this.autreHemorragieVisceres = autreHemorragieVisceres;
    }

    public Boolean getExplorationVisceres() {
        return explorationVisceres;
    }

    public void setExplorationVisceres(Boolean explorationVisceres) {
        this.explorationVisceres = explorationVisceres;
    }

    public String getLesionSousJacente() {
        return lesionSousJacente;
    }

    public void setLesionSousJacente(String lesionSousJacente) {
        this.lesionSousJacente = lesionSousJacente;
    }

    public OuiNonNP getHemorragieGrave() {
        return hemorragieGrave;
    }

    public void setHemorragieGrave(OuiNonNP hemorragieGrave) {
        this.hemorragieGrave = hemorragieGrave;
    }

    public Boolean getGraveDeglobulisation() {
        return graveDeglobulisation;
    }

    public void setGraveDeglobulisation(Boolean graveDeglobulisation) {
        this.graveDeglobulisation = graveDeglobulisation;
    }

    public Boolean getGraveEtatDeChoc() {
        return graveEtatDeChoc;
    }

    public void setGraveEtatDeChoc(Boolean graveEtatDeChoc) {
        this.graveEtatDeChoc = graveEtatDeChoc;
    }

    public Boolean getGraveParLocalisation() {
        return graveParLocalisation;
    }

    public void setGraveParLocalisation(Boolean graveParLocalisation) {
        this.graveParLocalisation = graveParLocalisation;
    }

    public String getSiegeGraviteLocalisation() {
        return siegeGraviteLocalisation;
    }

    public void setSiegeGraviteLocalisation(String siegeGraviteLocalisation) {
        this.siegeGraviteLocalisation = siegeGraviteLocalisation;
    }

    public Integer getNombreHospitalisations() {
        return nombreHospitalisations;
    }

    public void setNombreHospitalisations(Integer nombreHospitalisations) {
        this.nombreHospitalisations = nombreHospitalisations;
    }

    public Integer getNombreTransfusions() {
        return nombreTransfusions;
    }

    public void setNombreTransfusions(Integer nombreTransfusions) {
        this.nombreTransfusions = nombreTransfusions;
    }

    public Integer getScoreISTHBAT() {
        return scoreISTHBAT;
    }

    public void setScoreISTHBAT(Integer scoreISTHBAT) {
        this.scoreISTHBAT = scoreISTHBAT;
    }

    public OuiNonNP getDesmopressine() {
        return desmopressine;
    }

    public void setDesmopressine(OuiNonNP desmopressine) {
        this.desmopressine = desmopressine;
    }

    public OuiNonNP getTraitementSubstitutifPrevention() {
        return traitementSubstitutifPrevention;
    }

    public void setTraitementSubstitutifPrevention(OuiNonNP traitementSubstitutifPrevention) {
        this.traitementSubstitutifPrevention = traitementSubstitutifPrevention;
    }

    public String getTraitementPreparation() {
        return traitementPreparation;
    }

    public void setTraitementPreparation(String traitementPreparation) {
        this.traitementPreparation = traitementPreparation;
    }

    public OuiNonNP getTraitementSubstitutifCuratif() {
        return traitementSubstitutifCuratif;
    }

    public void setTraitementSubstitutifCuratif(OuiNonNP traitementSubstitutifCuratif) {
        this.traitementSubstitutifCuratif = traitementSubstitutifCuratif;
    }

    public OuiNonNP getCryoprecipite() {
        return cryoprecipite;
    }

    public void setCryoprecipite(OuiNonNP cryoprecipite) {
        this.cryoprecipite = cryoprecipite;
    }

    public OuiNonNP getPfc() {
        return pfc;
    }

    public void setPfc(OuiNonNP pfc) {
        this.pfc = pfc;
    }

    public OuiNonNP getConcentreFVIIIFVW() {
        return concentreFVIIIFVW;
    }

    public void setConcentreFVIIIFVW(OuiNonNP concentreFVIIIFVW) {
        this.concentreFVIIIFVW = concentreFVIIIFVW;
    }

    public OuiNonNP getConcentreFVW() {
        return concentreFVW;
    }

    public void setConcentreFVW(OuiNonNP concentreFVW) {
        this.concentreFVW = concentreFVW;
    }

    public OuiNonNP getConcentreFVIII() {
        return concentreFVIII;
    }

    public void setConcentreFVIII(OuiNonNP concentreFVIII) {
        this.concentreFVIII = concentreFVIII;
    }

    public OuiNonNP getAgentBypassant() {
        return agentBypassant;
    }

    public void setAgentBypassant(OuiNonNP agentBypassant) {
        this.agentBypassant = agentBypassant;
    }

    public OuiNonNP getAntifibrinolytiques() {
        return antifibrinolytiques;
    }

    public void setAntifibrinolytiques(OuiNonNP antifibrinolytiques) {
        this.antifibrinolytiques = antifibrinolytiques;
    }

    public Boolean getVoieOrale() {
        return voieOrale;
    }

    public void setVoieOrale(Boolean voieOrale) {
        this.voieOrale = voieOrale;
    }

    public Boolean getVoieIV() {
        return voieIV;
    }

    public void setVoieIV(Boolean voieIV) {
        this.voieIV = voieIV;
    }

    public Boolean getVoieLocale() {
        return voieLocale;
    }

    public void setVoieLocale(Boolean voieLocale) {
        this.voieLocale = voieLocale;
    }

    public OuiNonNP getOestroprogestatifs() {
        return oestroprogestatifs;
    }

    public void setOestroprogestatifs(OuiNonNP oestroprogestatifs) {
        this.oestroprogestatifs = oestroprogestatifs;
    }

    public OuiNonNP getTraitementAntalgique() {
        return traitementAntalgique;
    }

    public void setTraitementAntalgique(OuiNonNP traitementAntalgique) {
        this.traitementAntalgique = traitementAntalgique;
    }

    public OuiNonNP getCorticotherapie() {
        return corticotherapie;
    }

    public void setCorticotherapie(OuiNonNP corticotherapie) {
        this.corticotherapie = corticotherapie;
    }

    public OuiNonNP getKinesitherapie() {
        return kinesitherapie;
    }

    public void setKinesitherapie(OuiNonNP kinesitherapie) {
        this.kinesitherapie = kinesitherapie;
    }

    public OuiNonNP getTraitementMartial() {
        return traitementMartial;
    }

    public void setTraitementMartial(OuiNonNP traitementMartial) {
        this.traitementMartial = traitementMartial;
    }

    public OuiNonNP getCulotsGlobulaires() {
        return culotsGlobulaires;
    }

    public void setCulotsGlobulaires(OuiNonNP culotsGlobulaires) {
        this.culotsGlobulaires = culotsGlobulaires;
    }

    public Integer getNombreCulotsGlobulaires() {
        return nombreCulotsGlobulaires;
    }

    public void setNombreCulotsGlobulaires(Integer nombreCulotsGlobulaires) {
        this.nombreCulotsGlobulaires = nombreCulotsGlobulaires;
    }

    public OuiNonNP getImmunosuppresseur() {
        return immunosuppresseur;
    }

    public void setImmunosuppresseur(OuiNonNP immunosuppresseur) {
        this.immunosuppresseur = immunosuppresseur;
    }

    public String getNomImmunosuppresseur() {
        return nomImmunosuppresseur;
    }

    public void setNomImmunosuppresseur(String nomImmunosuppresseur) {
        this.nomImmunosuppresseur = nomImmunosuppresseur;
    }

    public OuiNonNP getAnemie() {
        return anemie;
    }

    public void setAnemie(OuiNonNP anemie) {
        this.anemie = anemie;
    }

    public Boolean getAnemieSevere() {
        return anemieSevere;
    }

    public void setAnemieSevere(Boolean anemieSevere) {
        this.anemieSevere = anemieSevere;
    }

    public Double getTauxHb() {
        return tauxHb;
    }

    public void setTauxHb(Double tauxHb) {
        this.tauxHb = tauxHb;
    }

    public ResultatSerologie getAnticorpsAntiFVW() {
        return anticorpsAntiFVW;
    }

    public void setAnticorpsAntiFVW(ResultatSerologie anticorpsAntiFVW) {
        this.anticorpsAntiFVW = anticorpsAntiFVW;
    }

    public ResultatSerologie getAnticorpsAntiFVIII() {
        return anticorpsAntiFVIII;
    }

    public void setAnticorpsAntiFVIII(ResultatSerologie anticorpsAntiFVIII) {
        this.anticorpsAntiFVIII = anticorpsAntiFVIII;
    }

    public ResultatSerologie getHepatiteB() {
        return hepatiteB;
    }

    public void setHepatiteB(ResultatSerologie hepatiteB) {
        this.hepatiteB = hepatiteB;
    }

    public ResultatSerologie getHepatiteC() {
        return hepatiteC;
    }

    public void setHepatiteC(ResultatSerologie hepatiteC) {
        this.hepatiteC = hepatiteC;
    }

    public ResultatSerologie getHiv() {
        return hiv;
    }

    public void setHiv(ResultatSerologie hiv) {
        this.hiv = hiv;
    }

    public Boolean getThrombose() {
        return thrombose;
    }

    public void setThrombose(Boolean thrombose) {
        this.thrombose = thrombose;
    }

    public Boolean getReactionAllergique() {
        return reactionAllergique;
    }

    public void setReactionAllergique(Boolean reactionAllergique) {
        this.reactionAllergique = reactionAllergique;
    }

    public Boolean getAutreComplication() {
        return autreComplication;
    }

    public void setAutreComplication(Boolean autreComplication) {
        this.autreComplication = autreComplication;
    }

    public String getAutreComplicationDescription() {
        return autreComplicationDescription;
    }

    public void setAutreComplicationDescription(String autreComplicationDescription) {
        this.autreComplicationDescription = autreComplicationDescription;
    }

    public OuiNonNP getAdherenceTherapeutique() {
        return adherenceTherapeutique;
    }

    public void setAdherenceTherapeutique(OuiNonNP adherenceTherapeutique) {
        this.adherenceTherapeutique = adherenceTherapeutique;
    }

    public String getCauseNonAdherence() {
        return causeNonAdherence;
    }

    public void setCauseNonAdherence(String causeNonAdherence) {
        this.causeNonAdherence = causeNonAdherence;
    }

    public StatutSocial getStatutSocial() {
        return statutSocial;
    }

    public void setStatutSocial(StatutSocial statutSocial) {
        this.statutSocial = statutSocial;
    }

    public OuiNonNP getMarie() {
        return marie;
    }

    public void setMarie(OuiNonNP marie) {
        this.marie = marie;
    }

    public OuiNonNP getEnfants() {
        return enfants;
    }

    public void setEnfants(OuiNonNP enfants) {
        this.enfants = enfants;
    }

    public OuiNonNP getActivitePhysique() {
        return activitePhysique;
    }

    public void setActivitePhysique(OuiNonNP activitePhysique) {
        this.activitePhysique = activitePhysique;
    }

    public String getTypeActivitePhysique() {
        return typeActivitePhysique;
    }

    public void setTypeActivitePhysique(String typeActivitePhysique) {
        this.typeActivitePhysique = typeActivitePhysique;
    }

    public OuiNonNP getSequelles() {
        return sequelles;
    }

    public void setSequelles(OuiNonNP sequelles) {
        this.sequelles = sequelles;
    }

    public String getTypeSequelles() {
        return typeSequelles;
    }

    public void setTypeSequelles(String typeSequelles) {
        this.typeSequelles = typeSequelles;
    }

    public OuiNonNP getPatientDecede() {
        return patientDecede;
    }

    public void setPatientDecede(OuiNonNP patientDecede) {
        this.patientDecede = patientDecede;
    }

    public LocalDate getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(LocalDate dateDeces) {
        this.dateDeces = dateDeces;
    }

    public Integer getAgeDeces() {
        return ageDeces;
    }

    public void setAgeDeces(Integer ageDeces) {
        this.ageDeces = ageDeces;
    }

    public CauseDeces getCauseDeces() {
        return causeDeces;
    }

    public void setCauseDeces(CauseDeces causeDeces) {
        this.causeDeces = causeDeces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheVonWillebrandDTO)) {
            return false;
        }

        FicheVonWillebrandDTO ficheVonWillebrandDTO = (FicheVonWillebrandDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ficheVonWillebrandDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheVonWillebrandDTO{" +
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

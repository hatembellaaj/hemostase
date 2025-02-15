package tn.adhes.hemostase.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
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
import tn.adhes.hemostase.domain.enumeration.PECTherapeutique;
import tn.adhes.hemostase.domain.enumeration.RechercheACC;
import tn.adhes.hemostase.domain.enumeration.Region;
import tn.adhes.hemostase.domain.enumeration.SchemaTherapeutique;
import tn.adhes.hemostase.domain.enumeration.Serologie;
import tn.adhes.hemostase.domain.enumeration.Severite;
import tn.adhes.hemostase.domain.enumeration.SuiteOperatoire;
import tn.adhes.hemostase.domain.enumeration.TauxInhibiteur;
import tn.adhes.hemostase.domain.enumeration.TauxRecuperation;
import tn.adhes.hemostase.domain.enumeration.TestStatus;
import tn.adhes.hemostase.domain.enumeration.TypeAccidentHemorragiqueGrave;
import tn.adhes.hemostase.domain.enumeration.TypeHemorragie;
import tn.adhes.hemostase.domain.enumeration.TypeHemorragieCutaneoMuqueuse;
import tn.adhes.hemostase.domain.enumeration.TypeHemorragieViscere;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;
import tn.adhes.hemostase.domain.enumeration.TypePriseEnCharge;
import tn.adhes.hemostase.domain.enumeration.VieSociale;

/**
 * A DTO for the {@link tn.adhes.hemostase.domain.FicheHemophilie} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheHemophilieDTO implements Serializable {

    private Long id;

    @Size(max = 25)
    private String prenom;

    @Size(max = 25)
    private String nom;

    @Size(max = 25)
    private String nomJeuneFille;

    @Size(max = 25)
    private String profession;

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

    @NotNull
    private CivilStatus etatCivil;

    @NotNull
    private CouvertureSociale couvertureSociale;

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

    private OuiNonNP consentementRegistre;

    private LocalDate dateEnregistrementRegistre;

    private String dossierNumber;

    @Size(max = 25)
    private String ordreNumber;

    @Size(max = 25)
    private String indexNumber;

    private OuiNonNP parentsConsanguins;

    @Size(max = 25)
    private String degreParenteConsanguins;

    private OuiNonNP casSimilaires;

    private Integer nombreCas;

    private DegreeParente degreParenteCasSimilaires;

    private OuiNonNP decesSyndromeHemorragique;

    private Integer nombreDeces;

    private FormeHemophilie formeHemophilie;

    private Integer fratrieNbFreres;

    private Integer fratrieNbSoeurs;

    @NotNull
    private CircumstanceDecouverte circonstancesDecouverte;

    private Boolean cutaneoMuqueux;

    private OuiNonNP hemarthrose;

    private Boolean hematomePostTraumatique;

    private Boolean plaiePostTraumatique;

    private Boolean hemorragiePostCirconcision;

    private Boolean hemorragiePostChirurgie;

    private Boolean hemorragieCerebrale;

    private Boolean hemorragieDigestive;

    private LocalDate datePremierSigneClinique;

    private Integer ageDiagnosticJours;

    private Integer ageDiagnosticMois;

    private Integer ageDiagnosticAnnees;

    private LocalDate dateTestConfirmation;

    private Float hb;

    private Float ht;

    private Integer plaquettes;

    private Float tp;

    private Float fibrinogene;

    private Float tcaMT;

    private Float tcaMTTotal;

    private Float tcaMT2h;

    private Float tcaTemoin2h;

    private TestStatus ts;

    private TestStatus pfa;

    private Float dosageFacteurVIII;

    private Float dosageFacteurIX;

    private Float dosageFacteurVWActivite;

    private Float dosageFacteurVWAntigene;

    @NotNull
    private FormeClinique formeClinique;

    @NotNull
    private Severite severite;

    private OuiNonNP explorationMoleculaire;

    private Boolean inversionIntron22;

    @Size(max = 25)
    private String autreExplorationMoleculaire;

    @Size(max = 25)
    private String hemarthroseSiege;

    private TypeManifestation hemarthroseType;

    private Integer hemarthroseFrequenceAn;

    private OuiNonNP hematomeSuperficiel;

    private TypeManifestation hematomeSuperficielType;

    @Size(max = 25)
    private String hematomeSuperficielSiege;

    private OuiNonNP hematomePsoas;

    private TypeHemorragie hematomePsoasType;

    private OuiNonNP hematomePsoasRecidive;

    private OuiNonNP hemorragiesCutaneoMuqueuses;

    private TypeHemorragieCutaneoMuqueuse hemorragiesCutaneoMuqueusesTypes;

    private Integer hemorragiesCutaneoMuqueusesFrequenceAn;

    private OuiNonNP hemorragieVisceres;

    private TypeHemorragieViscere hemorragieVisceresType;

    private OuiNonNP hemorragieVisceresExploration;

    @Size(max = 25)
    private String hemorragieVisceresExamen;

    private OuiNonNP saignementSNC;

    private EvolutionSNC saignementSNCEvolution;

    private OuiNonNP circoncision;

    private OuiNonNP circoncisionPreparation;

    private SuiteOperatoire circoncisionSuiteOperatoire;

    private Integer circoncisionAge;

    private OuiNonNP avulsionDentaire;

    private OuiNonNP avulsionDentairePreparation;

    private OuiNonNP interventionChirurgicale;

    @Size(max = 25)
    private String interventionChirurgicaleType;

    private OuiNonNP accidentsHemorragiquesGraves;

    private TypeAccidentHemorragiqueGrave accidentsHemorragiquesGravesTypes;

    private TypePriseEnCharge priseEnChargeTherapeutique;

    @Size(max = 25)
    private String causePriseEnCharge;

    @Size(max = 25)
    private String prophylaxieDoseFrequence;

    private LocalDate dateDebutProphylaxie;

    private InjectionType injectionType;

    private ModaliteTraitement modaliteTraitement;

    @Size(max = 25)
    private String typeTraitementSubstitutif;

    private Integer agePremiereSubstitution;

    private OuiNonNP antecedentsTraitementPSL;

    private OuiNonNP plasmaFraisCongele;

    private OuiNonNP cryoprecipite;

    private OuiNonNP orthopediques;

    private Boolean synovite;

    private Boolean arthropathieHemophiliqueChronique;

    private Boolean pseudotumeurs;

    private Boolean fractures;

    private Boolean coudeDroit;

    private Boolean coudeGauche;

    private Boolean genouDroit;

    private Boolean genouGauche;

    private Boolean chevilleDroite;

    private Boolean chevilleGauche;

    private Boolean epauleDroite;

    private Boolean epauleGauche;

    private Boolean poignetDroit;

    private Boolean poignetGauche;

    private Boolean hancheDroite;

    private Boolean hancheGauche;

    private PECTherapeutique pecTherapeutique;

    private OuiNonNP inhibiteurs;

    private TauxInhibiteur tauxInhibiteur;

    private Boolean testRecuperationFAH;

    private LocalDate dateTestRecuperation;

    private TauxRecuperation tauxRecuperation;

    private Boolean antiFAHNonNeutralisant;

    private Boolean antiFAHNegatif;

    private RechercheACC rechercheACC;

    private Boolean augmenterDoseFacteur;

    private Boolean novoseven;

    private Boolean feiba;

    private Boolean corticotherapie;

    private Boolean immurel;

    private Boolean iti;

    @Size(max = 25)
    private String posologieITI;

    @Size(max = 25)
    private String dureeITI;

    private OuiNonNP observanceITI;

    @Size(max = 25)
    private String causeNonObservance;

    private EvolutionInhibiteur evolutionInhibiteur;

    private Boolean prophylaxieHemlibra;

    private LocalDate dateDebutHemlibra;

    private SchemaTherapeutique schemaTherapeutique;

    private Integer efficaciteHemlibra;

    private Serologie serologieHBC;

    private Serologie serologieHVC;

    private LocalDate dateDecouverteVHC;

    @Size(max = 25)
    private String chargeViraleVHC;

    private OuiNonNP traitementVHC;

    private EvolutionVHC evolutionVHC;

    private Serologie serologieHIV;

    private VieSociale vieSociale;

    private OuiNonNP marie;

    private OuiNonNP enfants;

    private OuiNonNP handicap;

    @Size(max = 25)
    private String typeHandicap;

    private Boolean activiteSportive;

    @Size(max = 25)
    private String typeActiviteSportive;

    private Boolean decede;

    @Size(max = 25)
    private String causeDeces;

    private LocalDate dateDeces;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public CivilStatus getEtatCivil() {
        return etatCivil;
    }

    public void setEtatCivil(CivilStatus etatCivil) {
        this.etatCivil = etatCivil;
    }

    public CouvertureSociale getCouvertureSociale() {
        return couvertureSociale;
    }

    public void setCouvertureSociale(CouvertureSociale couvertureSociale) {
        this.couvertureSociale = couvertureSociale;
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

    public OuiNonNP getConsentementRegistre() {
        return consentementRegistre;
    }

    public void setConsentementRegistre(OuiNonNP consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return dateEnregistrementRegistre;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
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

    public OuiNonNP getParentsConsanguins() {
        return parentsConsanguins;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public String getDegreParenteConsanguins() {
        return degreParenteConsanguins;
    }

    public void setDegreParenteConsanguins(String degreParenteConsanguins) {
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

    public DegreeParente getDegreParenteCasSimilaires() {
        return degreParenteCasSimilaires;
    }

    public void setDegreParenteCasSimilaires(DegreeParente degreParenteCasSimilaires) {
        this.degreParenteCasSimilaires = degreParenteCasSimilaires;
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

    public FormeHemophilie getFormeHemophilie() {
        return formeHemophilie;
    }

    public void setFormeHemophilie(FormeHemophilie formeHemophilie) {
        this.formeHemophilie = formeHemophilie;
    }

    public Integer getFratrieNbFreres() {
        return fratrieNbFreres;
    }

    public void setFratrieNbFreres(Integer fratrieNbFreres) {
        this.fratrieNbFreres = fratrieNbFreres;
    }

    public Integer getFratrieNbSoeurs() {
        return fratrieNbSoeurs;
    }

    public void setFratrieNbSoeurs(Integer fratrieNbSoeurs) {
        this.fratrieNbSoeurs = fratrieNbSoeurs;
    }

    public CircumstanceDecouverte getCirconstancesDecouverte() {
        return circonstancesDecouverte;
    }

    public void setCirconstancesDecouverte(CircumstanceDecouverte circonstancesDecouverte) {
        this.circonstancesDecouverte = circonstancesDecouverte;
    }

    public Boolean getCutaneoMuqueux() {
        return cutaneoMuqueux;
    }

    public void setCutaneoMuqueux(Boolean cutaneoMuqueux) {
        this.cutaneoMuqueux = cutaneoMuqueux;
    }

    public OuiNonNP getHemarthrose() {
        return hemarthrose;
    }

    public void setHemarthrose(OuiNonNP hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public Boolean getHematomePostTraumatique() {
        return hematomePostTraumatique;
    }

    public void setHematomePostTraumatique(Boolean hematomePostTraumatique) {
        this.hematomePostTraumatique = hematomePostTraumatique;
    }

    public Boolean getPlaiePostTraumatique() {
        return plaiePostTraumatique;
    }

    public void setPlaiePostTraumatique(Boolean plaiePostTraumatique) {
        this.plaiePostTraumatique = plaiePostTraumatique;
    }

    public Boolean getHemorragiePostCirconcision() {
        return hemorragiePostCirconcision;
    }

    public void setHemorragiePostCirconcision(Boolean hemorragiePostCirconcision) {
        this.hemorragiePostCirconcision = hemorragiePostCirconcision;
    }

    public Boolean getHemorragiePostChirurgie() {
        return hemorragiePostChirurgie;
    }

    public void setHemorragiePostChirurgie(Boolean hemorragiePostChirurgie) {
        this.hemorragiePostChirurgie = hemorragiePostChirurgie;
    }

    public Boolean getHemorragieCerebrale() {
        return hemorragieCerebrale;
    }

    public void setHemorragieCerebrale(Boolean hemorragieCerebrale) {
        this.hemorragieCerebrale = hemorragieCerebrale;
    }

    public Boolean getHemorragieDigestive() {
        return hemorragieDigestive;
    }

    public void setHemorragieDigestive(Boolean hemorragieDigestive) {
        this.hemorragieDigestive = hemorragieDigestive;
    }

    public LocalDate getDatePremierSigneClinique() {
        return datePremierSigneClinique;
    }

    public void setDatePremierSigneClinique(LocalDate datePremierSigneClinique) {
        this.datePremierSigneClinique = datePremierSigneClinique;
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

    public LocalDate getDateTestConfirmation() {
        return dateTestConfirmation;
    }

    public void setDateTestConfirmation(LocalDate dateTestConfirmation) {
        this.dateTestConfirmation = dateTestConfirmation;
    }

    public Float getHb() {
        return hb;
    }

    public void setHb(Float hb) {
        this.hb = hb;
    }

    public Float getHt() {
        return ht;
    }

    public void setHt(Float ht) {
        this.ht = ht;
    }

    public Integer getPlaquettes() {
        return plaquettes;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public Float getTp() {
        return tp;
    }

    public void setTp(Float tp) {
        this.tp = tp;
    }

    public Float getFibrinogene() {
        return fibrinogene;
    }

    public void setFibrinogene(Float fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public Float getTcaMT() {
        return tcaMT;
    }

    public void setTcaMT(Float tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Float getTcaMTTotal() {
        return tcaMTTotal;
    }

    public void setTcaMTTotal(Float tcaMTTotal) {
        this.tcaMTTotal = tcaMTTotal;
    }

    public Float getTcaMT2h() {
        return tcaMT2h;
    }

    public void setTcaMT2h(Float tcaMT2h) {
        this.tcaMT2h = tcaMT2h;
    }

    public Float getTcaTemoin2h() {
        return tcaTemoin2h;
    }

    public void setTcaTemoin2h(Float tcaTemoin2h) {
        this.tcaTemoin2h = tcaTemoin2h;
    }

    public TestStatus getTs() {
        return ts;
    }

    public void setTs(TestStatus ts) {
        this.ts = ts;
    }

    public TestStatus getPfa() {
        return pfa;
    }

    public void setPfa(TestStatus pfa) {
        this.pfa = pfa;
    }

    public Float getDosageFacteurVIII() {
        return dosageFacteurVIII;
    }

    public void setDosageFacteurVIII(Float dosageFacteurVIII) {
        this.dosageFacteurVIII = dosageFacteurVIII;
    }

    public Float getDosageFacteurIX() {
        return dosageFacteurIX;
    }

    public void setDosageFacteurIX(Float dosageFacteurIX) {
        this.dosageFacteurIX = dosageFacteurIX;
    }

    public Float getDosageFacteurVWActivite() {
        return dosageFacteurVWActivite;
    }

    public void setDosageFacteurVWActivite(Float dosageFacteurVWActivite) {
        this.dosageFacteurVWActivite = dosageFacteurVWActivite;
    }

    public Float getDosageFacteurVWAntigene() {
        return dosageFacteurVWAntigene;
    }

    public void setDosageFacteurVWAntigene(Float dosageFacteurVWAntigene) {
        this.dosageFacteurVWAntigene = dosageFacteurVWAntigene;
    }

    public FormeClinique getFormeClinique() {
        return formeClinique;
    }

    public void setFormeClinique(FormeClinique formeClinique) {
        this.formeClinique = formeClinique;
    }

    public Severite getSeverite() {
        return severite;
    }

    public void setSeverite(Severite severite) {
        this.severite = severite;
    }

    public OuiNonNP getExplorationMoleculaire() {
        return explorationMoleculaire;
    }

    public void setExplorationMoleculaire(OuiNonNP explorationMoleculaire) {
        this.explorationMoleculaire = explorationMoleculaire;
    }

    public Boolean getInversionIntron22() {
        return inversionIntron22;
    }

    public void setInversionIntron22(Boolean inversionIntron22) {
        this.inversionIntron22 = inversionIntron22;
    }

    public String getAutreExplorationMoleculaire() {
        return autreExplorationMoleculaire;
    }

    public void setAutreExplorationMoleculaire(String autreExplorationMoleculaire) {
        this.autreExplorationMoleculaire = autreExplorationMoleculaire;
    }

    public String getHemarthroseSiege() {
        return hemarthroseSiege;
    }

    public void setHemarthroseSiege(String hemarthroseSiege) {
        this.hemarthroseSiege = hemarthroseSiege;
    }

    public TypeManifestation getHemarthroseType() {
        return hemarthroseType;
    }

    public void setHemarthroseType(TypeManifestation hemarthroseType) {
        this.hemarthroseType = hemarthroseType;
    }

    public Integer getHemarthroseFrequenceAn() {
        return hemarthroseFrequenceAn;
    }

    public void setHemarthroseFrequenceAn(Integer hemarthroseFrequenceAn) {
        this.hemarthroseFrequenceAn = hemarthroseFrequenceAn;
    }

    public OuiNonNP getHematomeSuperficiel() {
        return hematomeSuperficiel;
    }

    public void setHematomeSuperficiel(OuiNonNP hematomeSuperficiel) {
        this.hematomeSuperficiel = hematomeSuperficiel;
    }

    public TypeManifestation getHematomeSuperficielType() {
        return hematomeSuperficielType;
    }

    public void setHematomeSuperficielType(TypeManifestation hematomeSuperficielType) {
        this.hematomeSuperficielType = hematomeSuperficielType;
    }

    public String getHematomeSuperficielSiege() {
        return hematomeSuperficielSiege;
    }

    public void setHematomeSuperficielSiege(String hematomeSuperficielSiege) {
        this.hematomeSuperficielSiege = hematomeSuperficielSiege;
    }

    public OuiNonNP getHematomePsoas() {
        return hematomePsoas;
    }

    public void setHematomePsoas(OuiNonNP hematomePsoas) {
        this.hematomePsoas = hematomePsoas;
    }

    public TypeHemorragie getHematomePsoasType() {
        return hematomePsoasType;
    }

    public void setHematomePsoasType(TypeHemorragie hematomePsoasType) {
        this.hematomePsoasType = hematomePsoasType;
    }

    public OuiNonNP getHematomePsoasRecidive() {
        return hematomePsoasRecidive;
    }

    public void setHematomePsoasRecidive(OuiNonNP hematomePsoasRecidive) {
        this.hematomePsoasRecidive = hematomePsoasRecidive;
    }

    public OuiNonNP getHemorragiesCutaneoMuqueuses() {
        return hemorragiesCutaneoMuqueuses;
    }

    public void setHemorragiesCutaneoMuqueuses(OuiNonNP hemorragiesCutaneoMuqueuses) {
        this.hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueuses;
    }

    public TypeHemorragieCutaneoMuqueuse getHemorragiesCutaneoMuqueusesTypes() {
        return hemorragiesCutaneoMuqueusesTypes;
    }

    public void setHemorragiesCutaneoMuqueusesTypes(TypeHemorragieCutaneoMuqueuse hemorragiesCutaneoMuqueusesTypes) {
        this.hemorragiesCutaneoMuqueusesTypes = hemorragiesCutaneoMuqueusesTypes;
    }

    public Integer getHemorragiesCutaneoMuqueusesFrequenceAn() {
        return hemorragiesCutaneoMuqueusesFrequenceAn;
    }

    public void setHemorragiesCutaneoMuqueusesFrequenceAn(Integer hemorragiesCutaneoMuqueusesFrequenceAn) {
        this.hemorragiesCutaneoMuqueusesFrequenceAn = hemorragiesCutaneoMuqueusesFrequenceAn;
    }

    public OuiNonNP getHemorragieVisceres() {
        return hemorragieVisceres;
    }

    public void setHemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.hemorragieVisceres = hemorragieVisceres;
    }

    public TypeHemorragieViscere getHemorragieVisceresType() {
        return hemorragieVisceresType;
    }

    public void setHemorragieVisceresType(TypeHemorragieViscere hemorragieVisceresType) {
        this.hemorragieVisceresType = hemorragieVisceresType;
    }

    public OuiNonNP getHemorragieVisceresExploration() {
        return hemorragieVisceresExploration;
    }

    public void setHemorragieVisceresExploration(OuiNonNP hemorragieVisceresExploration) {
        this.hemorragieVisceresExploration = hemorragieVisceresExploration;
    }

    public String getHemorragieVisceresExamen() {
        return hemorragieVisceresExamen;
    }

    public void setHemorragieVisceresExamen(String hemorragieVisceresExamen) {
        this.hemorragieVisceresExamen = hemorragieVisceresExamen;
    }

    public OuiNonNP getSaignementSNC() {
        return saignementSNC;
    }

    public void setSaignementSNC(OuiNonNP saignementSNC) {
        this.saignementSNC = saignementSNC;
    }

    public EvolutionSNC getSaignementSNCEvolution() {
        return saignementSNCEvolution;
    }

    public void setSaignementSNCEvolution(EvolutionSNC saignementSNCEvolution) {
        this.saignementSNCEvolution = saignementSNCEvolution;
    }

    public OuiNonNP getCirconcision() {
        return circoncision;
    }

    public void setCirconcision(OuiNonNP circoncision) {
        this.circoncision = circoncision;
    }

    public OuiNonNP getCirconcisionPreparation() {
        return circoncisionPreparation;
    }

    public void setCirconcisionPreparation(OuiNonNP circoncisionPreparation) {
        this.circoncisionPreparation = circoncisionPreparation;
    }

    public SuiteOperatoire getCirconcisionSuiteOperatoire() {
        return circoncisionSuiteOperatoire;
    }

    public void setCirconcisionSuiteOperatoire(SuiteOperatoire circoncisionSuiteOperatoire) {
        this.circoncisionSuiteOperatoire = circoncisionSuiteOperatoire;
    }

    public Integer getCirconcisionAge() {
        return circoncisionAge;
    }

    public void setCirconcisionAge(Integer circoncisionAge) {
        this.circoncisionAge = circoncisionAge;
    }

    public OuiNonNP getAvulsionDentaire() {
        return avulsionDentaire;
    }

    public void setAvulsionDentaire(OuiNonNP avulsionDentaire) {
        this.avulsionDentaire = avulsionDentaire;
    }

    public OuiNonNP getAvulsionDentairePreparation() {
        return avulsionDentairePreparation;
    }

    public void setAvulsionDentairePreparation(OuiNonNP avulsionDentairePreparation) {
        this.avulsionDentairePreparation = avulsionDentairePreparation;
    }

    public OuiNonNP getInterventionChirurgicale() {
        return interventionChirurgicale;
    }

    public void setInterventionChirurgicale(OuiNonNP interventionChirurgicale) {
        this.interventionChirurgicale = interventionChirurgicale;
    }

    public String getInterventionChirurgicaleType() {
        return interventionChirurgicaleType;
    }

    public void setInterventionChirurgicaleType(String interventionChirurgicaleType) {
        this.interventionChirurgicaleType = interventionChirurgicaleType;
    }

    public OuiNonNP getAccidentsHemorragiquesGraves() {
        return accidentsHemorragiquesGraves;
    }

    public void setAccidentsHemorragiquesGraves(OuiNonNP accidentsHemorragiquesGraves) {
        this.accidentsHemorragiquesGraves = accidentsHemorragiquesGraves;
    }

    public TypeAccidentHemorragiqueGrave getAccidentsHemorragiquesGravesTypes() {
        return accidentsHemorragiquesGravesTypes;
    }

    public void setAccidentsHemorragiquesGravesTypes(TypeAccidentHemorragiqueGrave accidentsHemorragiquesGravesTypes) {
        this.accidentsHemorragiquesGravesTypes = accidentsHemorragiquesGravesTypes;
    }

    public TypePriseEnCharge getPriseEnChargeTherapeutique() {
        return priseEnChargeTherapeutique;
    }

    public void setPriseEnChargeTherapeutique(TypePriseEnCharge priseEnChargeTherapeutique) {
        this.priseEnChargeTherapeutique = priseEnChargeTherapeutique;
    }

    public String getCausePriseEnCharge() {
        return causePriseEnCharge;
    }

    public void setCausePriseEnCharge(String causePriseEnCharge) {
        this.causePriseEnCharge = causePriseEnCharge;
    }

    public String getProphylaxieDoseFrequence() {
        return prophylaxieDoseFrequence;
    }

    public void setProphylaxieDoseFrequence(String prophylaxieDoseFrequence) {
        this.prophylaxieDoseFrequence = prophylaxieDoseFrequence;
    }

    public LocalDate getDateDebutProphylaxie() {
        return dateDebutProphylaxie;
    }

    public void setDateDebutProphylaxie(LocalDate dateDebutProphylaxie) {
        this.dateDebutProphylaxie = dateDebutProphylaxie;
    }

    public InjectionType getInjectionType() {
        return injectionType;
    }

    public void setInjectionType(InjectionType injectionType) {
        this.injectionType = injectionType;
    }

    public ModaliteTraitement getModaliteTraitement() {
        return modaliteTraitement;
    }

    public void setModaliteTraitement(ModaliteTraitement modaliteTraitement) {
        this.modaliteTraitement = modaliteTraitement;
    }

    public String getTypeTraitementSubstitutif() {
        return typeTraitementSubstitutif;
    }

    public void setTypeTraitementSubstitutif(String typeTraitementSubstitutif) {
        this.typeTraitementSubstitutif = typeTraitementSubstitutif;
    }

    public Integer getAgePremiereSubstitution() {
        return agePremiereSubstitution;
    }

    public void setAgePremiereSubstitution(Integer agePremiereSubstitution) {
        this.agePremiereSubstitution = agePremiereSubstitution;
    }

    public OuiNonNP getAntecedentsTraitementPSL() {
        return antecedentsTraitementPSL;
    }

    public void setAntecedentsTraitementPSL(OuiNonNP antecedentsTraitementPSL) {
        this.antecedentsTraitementPSL = antecedentsTraitementPSL;
    }

    public OuiNonNP getPlasmaFraisCongele() {
        return plasmaFraisCongele;
    }

    public void setPlasmaFraisCongele(OuiNonNP plasmaFraisCongele) {
        this.plasmaFraisCongele = plasmaFraisCongele;
    }

    public OuiNonNP getCryoprecipite() {
        return cryoprecipite;
    }

    public void setCryoprecipite(OuiNonNP cryoprecipite) {
        this.cryoprecipite = cryoprecipite;
    }

    public OuiNonNP getOrthopediques() {
        return orthopediques;
    }

    public void setOrthopediques(OuiNonNP orthopediques) {
        this.orthopediques = orthopediques;
    }

    public Boolean getSynovite() {
        return synovite;
    }

    public void setSynovite(Boolean synovite) {
        this.synovite = synovite;
    }

    public Boolean getArthropathieHemophiliqueChronique() {
        return arthropathieHemophiliqueChronique;
    }

    public void setArthropathieHemophiliqueChronique(Boolean arthropathieHemophiliqueChronique) {
        this.arthropathieHemophiliqueChronique = arthropathieHemophiliqueChronique;
    }

    public Boolean getPseudotumeurs() {
        return pseudotumeurs;
    }

    public void setPseudotumeurs(Boolean pseudotumeurs) {
        this.pseudotumeurs = pseudotumeurs;
    }

    public Boolean getFractures() {
        return fractures;
    }

    public void setFractures(Boolean fractures) {
        this.fractures = fractures;
    }

    public Boolean getCoudeDroit() {
        return coudeDroit;
    }

    public void setCoudeDroit(Boolean coudeDroit) {
        this.coudeDroit = coudeDroit;
    }

    public Boolean getCoudeGauche() {
        return coudeGauche;
    }

    public void setCoudeGauche(Boolean coudeGauche) {
        this.coudeGauche = coudeGauche;
    }

    public Boolean getGenouDroit() {
        return genouDroit;
    }

    public void setGenouDroit(Boolean genouDroit) {
        this.genouDroit = genouDroit;
    }

    public Boolean getGenouGauche() {
        return genouGauche;
    }

    public void setGenouGauche(Boolean genouGauche) {
        this.genouGauche = genouGauche;
    }

    public Boolean getChevilleDroite() {
        return chevilleDroite;
    }

    public void setChevilleDroite(Boolean chevilleDroite) {
        this.chevilleDroite = chevilleDroite;
    }

    public Boolean getChevilleGauche() {
        return chevilleGauche;
    }

    public void setChevilleGauche(Boolean chevilleGauche) {
        this.chevilleGauche = chevilleGauche;
    }

    public Boolean getEpauleDroite() {
        return epauleDroite;
    }

    public void setEpauleDroite(Boolean epauleDroite) {
        this.epauleDroite = epauleDroite;
    }

    public Boolean getEpauleGauche() {
        return epauleGauche;
    }

    public void setEpauleGauche(Boolean epauleGauche) {
        this.epauleGauche = epauleGauche;
    }

    public Boolean getPoignetDroit() {
        return poignetDroit;
    }

    public void setPoignetDroit(Boolean poignetDroit) {
        this.poignetDroit = poignetDroit;
    }

    public Boolean getPoignetGauche() {
        return poignetGauche;
    }

    public void setPoignetGauche(Boolean poignetGauche) {
        this.poignetGauche = poignetGauche;
    }

    public Boolean getHancheDroite() {
        return hancheDroite;
    }

    public void setHancheDroite(Boolean hancheDroite) {
        this.hancheDroite = hancheDroite;
    }

    public Boolean getHancheGauche() {
        return hancheGauche;
    }

    public void setHancheGauche(Boolean hancheGauche) {
        this.hancheGauche = hancheGauche;
    }

    public PECTherapeutique getPecTherapeutique() {
        return pecTherapeutique;
    }

    public void setPecTherapeutique(PECTherapeutique pecTherapeutique) {
        this.pecTherapeutique = pecTherapeutique;
    }

    public OuiNonNP getInhibiteurs() {
        return inhibiteurs;
    }

    public void setInhibiteurs(OuiNonNP inhibiteurs) {
        this.inhibiteurs = inhibiteurs;
    }

    public TauxInhibiteur getTauxInhibiteur() {
        return tauxInhibiteur;
    }

    public void setTauxInhibiteur(TauxInhibiteur tauxInhibiteur) {
        this.tauxInhibiteur = tauxInhibiteur;
    }

    public Boolean getTestRecuperationFAH() {
        return testRecuperationFAH;
    }

    public void setTestRecuperationFAH(Boolean testRecuperationFAH) {
        this.testRecuperationFAH = testRecuperationFAH;
    }

    public LocalDate getDateTestRecuperation() {
        return dateTestRecuperation;
    }

    public void setDateTestRecuperation(LocalDate dateTestRecuperation) {
        this.dateTestRecuperation = dateTestRecuperation;
    }

    public TauxRecuperation getTauxRecuperation() {
        return tauxRecuperation;
    }

    public void setTauxRecuperation(TauxRecuperation tauxRecuperation) {
        this.tauxRecuperation = tauxRecuperation;
    }

    public Boolean getAntiFAHNonNeutralisant() {
        return antiFAHNonNeutralisant;
    }

    public void setAntiFAHNonNeutralisant(Boolean antiFAHNonNeutralisant) {
        this.antiFAHNonNeutralisant = antiFAHNonNeutralisant;
    }

    public Boolean getAntiFAHNegatif() {
        return antiFAHNegatif;
    }

    public void setAntiFAHNegatif(Boolean antiFAHNegatif) {
        this.antiFAHNegatif = antiFAHNegatif;
    }

    public RechercheACC getRechercheACC() {
        return rechercheACC;
    }

    public void setRechercheACC(RechercheACC rechercheACC) {
        this.rechercheACC = rechercheACC;
    }

    public Boolean getAugmenterDoseFacteur() {
        return augmenterDoseFacteur;
    }

    public void setAugmenterDoseFacteur(Boolean augmenterDoseFacteur) {
        this.augmenterDoseFacteur = augmenterDoseFacteur;
    }

    public Boolean getNovoseven() {
        return novoseven;
    }

    public void setNovoseven(Boolean novoseven) {
        this.novoseven = novoseven;
    }

    public Boolean getFeiba() {
        return feiba;
    }

    public void setFeiba(Boolean feiba) {
        this.feiba = feiba;
    }

    public Boolean getCorticotherapie() {
        return corticotherapie;
    }

    public void setCorticotherapie(Boolean corticotherapie) {
        this.corticotherapie = corticotherapie;
    }

    public Boolean getImmurel() {
        return immurel;
    }

    public void setImmurel(Boolean immurel) {
        this.immurel = immurel;
    }

    public Boolean getIti() {
        return iti;
    }

    public void setIti(Boolean iti) {
        this.iti = iti;
    }

    public String getPosologieITI() {
        return posologieITI;
    }

    public void setPosologieITI(String posologieITI) {
        this.posologieITI = posologieITI;
    }

    public String getDureeITI() {
        return dureeITI;
    }

    public void setDureeITI(String dureeITI) {
        this.dureeITI = dureeITI;
    }

    public OuiNonNP getObservanceITI() {
        return observanceITI;
    }

    public void setObservanceITI(OuiNonNP observanceITI) {
        this.observanceITI = observanceITI;
    }

    public String getCauseNonObservance() {
        return causeNonObservance;
    }

    public void setCauseNonObservance(String causeNonObservance) {
        this.causeNonObservance = causeNonObservance;
    }

    public EvolutionInhibiteur getEvolutionInhibiteur() {
        return evolutionInhibiteur;
    }

    public void setEvolutionInhibiteur(EvolutionInhibiteur evolutionInhibiteur) {
        this.evolutionInhibiteur = evolutionInhibiteur;
    }

    public Boolean getProphylaxieHemlibra() {
        return prophylaxieHemlibra;
    }

    public void setProphylaxieHemlibra(Boolean prophylaxieHemlibra) {
        this.prophylaxieHemlibra = prophylaxieHemlibra;
    }

    public LocalDate getDateDebutHemlibra() {
        return dateDebutHemlibra;
    }

    public void setDateDebutHemlibra(LocalDate dateDebutHemlibra) {
        this.dateDebutHemlibra = dateDebutHemlibra;
    }

    public SchemaTherapeutique getSchemaTherapeutique() {
        return schemaTherapeutique;
    }

    public void setSchemaTherapeutique(SchemaTherapeutique schemaTherapeutique) {
        this.schemaTherapeutique = schemaTherapeutique;
    }

    public Integer getEfficaciteHemlibra() {
        return efficaciteHemlibra;
    }

    public void setEfficaciteHemlibra(Integer efficaciteHemlibra) {
        this.efficaciteHemlibra = efficaciteHemlibra;
    }

    public Serologie getSerologieHBC() {
        return serologieHBC;
    }

    public void setSerologieHBC(Serologie serologieHBC) {
        this.serologieHBC = serologieHBC;
    }

    public Serologie getSerologieHVC() {
        return serologieHVC;
    }

    public void setSerologieHVC(Serologie serologieHVC) {
        this.serologieHVC = serologieHVC;
    }

    public LocalDate getDateDecouverteVHC() {
        return dateDecouverteVHC;
    }

    public void setDateDecouverteVHC(LocalDate dateDecouverteVHC) {
        this.dateDecouverteVHC = dateDecouverteVHC;
    }

    public String getChargeViraleVHC() {
        return chargeViraleVHC;
    }

    public void setChargeViraleVHC(String chargeViraleVHC) {
        this.chargeViraleVHC = chargeViraleVHC;
    }

    public OuiNonNP getTraitementVHC() {
        return traitementVHC;
    }

    public void setTraitementVHC(OuiNonNP traitementVHC) {
        this.traitementVHC = traitementVHC;
    }

    public EvolutionVHC getEvolutionVHC() {
        return evolutionVHC;
    }

    public void setEvolutionVHC(EvolutionVHC evolutionVHC) {
        this.evolutionVHC = evolutionVHC;
    }

    public Serologie getSerologieHIV() {
        return serologieHIV;
    }

    public void setSerologieHIV(Serologie serologieHIV) {
        this.serologieHIV = serologieHIV;
    }

    public VieSociale getVieSociale() {
        return vieSociale;
    }

    public void setVieSociale(VieSociale vieSociale) {
        this.vieSociale = vieSociale;
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

    public OuiNonNP getHandicap() {
        return handicap;
    }

    public void setHandicap(OuiNonNP handicap) {
        this.handicap = handicap;
    }

    public String getTypeHandicap() {
        return typeHandicap;
    }

    public void setTypeHandicap(String typeHandicap) {
        this.typeHandicap = typeHandicap;
    }

    public Boolean getActiviteSportive() {
        return activiteSportive;
    }

    public void setActiviteSportive(Boolean activiteSportive) {
        this.activiteSportive = activiteSportive;
    }

    public String getTypeActiviteSportive() {
        return typeActiviteSportive;
    }

    public void setTypeActiviteSportive(String typeActiviteSportive) {
        this.typeActiviteSportive = typeActiviteSportive;
    }

    public Boolean getDecede() {
        return decede;
    }

    public void setDecede(Boolean decede) {
        this.decede = decede;
    }

    public String getCauseDeces() {
        return causeDeces;
    }

    public void setCauseDeces(String causeDeces) {
        this.causeDeces = causeDeces;
    }

    public LocalDate getDateDeces() {
        return dateDeces;
    }

    public void setDateDeces(LocalDate dateDeces) {
        this.dateDeces = dateDeces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheHemophilieDTO)) {
            return false;
        }

        FicheHemophilieDTO ficheHemophilieDTO = (FicheHemophilieDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ficheHemophilieDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheHemophilieDTO{" +
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
            ", circonstancesDecouverte='" + getCirconstancesDecouverte() + "'" +
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
            ", orthopediques='" + getOrthopediques() + "'" +
            ", synovite='" + getSynovite() + "'" +
            ", arthropathieHemophiliqueChronique='" + getArthropathieHemophiliqueChronique() + "'" +
            ", pseudotumeurs='" + getPseudotumeurs() + "'" +
            ", fractures='" + getFractures() + "'" +
            ", coudeDroit='" + getCoudeDroit() + "'" +
            ", coudeGauche='" + getCoudeGauche() + "'" +
            ", genouDroit='" + getGenouDroit() + "'" +
            ", genouGauche='" + getGenouGauche() + "'" +
            ", chevilleDroite='" + getChevilleDroite() + "'" +
            ", chevilleGauche='" + getChevilleGauche() + "'" +
            ", epauleDroite='" + getEpauleDroite() + "'" +
            ", epauleGauche='" + getEpauleGauche() + "'" +
            ", poignetDroit='" + getPoignetDroit() + "'" +
            ", poignetGauche='" + getPoignetGauche() + "'" +
            ", hancheDroite='" + getHancheDroite() + "'" +
            ", hancheGauche='" + getHancheGauche() + "'" +
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

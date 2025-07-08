package tn.adhes.hemostase.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
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
 * A DTO for the {@link tn.adhes.hemostase.domain.FicheFacteurXIII} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheFacteurXIIIDTO implements Serializable {

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

    private OuiNonNP consentementRegistre;

    private LocalDate dateEnregistrementRegistre;

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

    private Boolean enqueteFamiliale;

    private Boolean decouverteFortuite;

    private Boolean explorationSyndromeHemorragique;

    private Boolean hemorragieChuteCordon;

    private Boolean metroragieMenarche;

    private Boolean hemorragieCerebraleNN;

    private Boolean hemorragiePostPartum;

    @Size(max = 255)
    private String autresCirconstances;

    private Integer datePremierSigneCliniqueEvocateurJour;

    private Integer datePremierSigneCliniqueEvocateurMois;

    private Integer datePremierSigneCliniqueEvocateurAnnee;

    private Integer ageDiagnosticJour;

    private Integer ageDiagnosticMois;

    private Integer ageDiagnosticAnnee;

    private Double hb;

    private Double ht;

    private Integer plaquettes;

    private Double tp;

    private Double tpMT;

    private Double tca;

    private Double tcaMT;

    private Double fibrinogene;

    private TypeFaitOuNonFaitEtResultat ts;

    private TypeFaitOuNonFaitEtResultat toPfa200;

    private Double colEpi;

    private Double colAdp;

    private Double facteurXIII;

    private Boolean techniqueChromogenique;

    private Double tauxChromogenique;

    private Boolean techniqueSolubiliteCaillot;

    private Double tauxSolubiliteCaillot;

    private Boolean confirmationMoleculaire;

    private Boolean mutationPresente;

    @Size(max = 255)
    private String mutationNom;

    private MutationType mutationType;

    @Size(max = 255)
    private String mutation2Nom;

    private OuiNonNP saignementChuteCordon;

    private OuiNonNP cicatricesKeloides;

    private OuiNonNP retardCicatrisation;

    private Boolean epistaxis;

    private Integer frequenceEpistaxis;

    private Boolean gingivorragies;

    private TypeManifestation typeGingivorragies;

    private Integer frequenceGingivorragies;

    private Boolean ecchymose;

    private TypeManifestation typeEcchymose;

    private Integer frequenceEcchymose;

    private Boolean menorragie;

    private Integer frequenceMenorragie;

    private Boolean hematemese;

    private Integer frequenceHematemese;

    private Boolean rectorragie;

    private Integer frequenceRectorragie;

    private Boolean hematurie;

    private Integer frequenceHematurie;

    private Boolean hematomeSousCutane;

    private TypeManifestation typeHematomeSousCutane;

    private Integer frequenceHematomeSousCutane;

    private Boolean hematomeMusculaire;

    @Size(max = 255)
    private String siegeHematomeMusculaire;

    private TypeManifestation typeHematomeMusculaire;

    private Integer frequenceHematomeMusculaire;

    private Boolean hemarthrose;

    private TypeManifestation typeHemarthrose;

    private Integer frequenceHemarthrose;

    private Boolean saignementSNC;

    private TypeManifestation typeSaignementSNC;

    private Integer frequenceSaignementSNC;

    private Boolean ruptureKystesHemorragiques;

    private Boolean hemorragiesPostChirurgicales;

    private Boolean hemoperitone;

    private Boolean accidentsThrombotiques;

    private AccidentsThrombotiquesType typeAccidentsThrombotiques;

    private OuiNonNP hemorragieGrave;

    private Boolean graveDeglobulisation;

    private Boolean graveEtatDeChoc;

    private Boolean graveParLocalisation;

    @Size(max = 255)
    private String siegeGraviteLocalisation;

    private OuiNonNP chirurgiePatientPrepare;

    private OuiNonNP chirurgieSuiteHemorragique;

    private OuiNonNP circoncisionPatientPrepare;

    private OuiNonNP circoncisionSuiteHemorragique;

    private Integer ageCirconcision;

    private OuiNonNP concentreFacteurXIII;

    private OuiNonNP plasmaFraisCongele;

    private OuiNonNP cryoprecipite;

    private OuiNonNP culotsPlaquettaires;

    private OuiNonNP dicynone;

    private OuiNonNP exacyl;

    private OuiNonNP traitementMartial;

    private OuiNonNP traitementOestroprogestatif;

    private OuiNonNP transfusionCGR;

    private OuiNonNP inhibiteurs;

    private Boolean inhibiteurNeutralisant;

    private Double titreInhibiteurNeutralisant;

    private Boolean inhibiteurNonNeutralisant;

    private OuiNonNP infectionsVirales;

    private OuiNonNP hepatiteB;

    private OuiNonNP hepatiteC;

    private OuiNonNP vih;

    private Boolean thrombose;

    private Boolean reactionAllergique;

    private Boolean alloimmunisation;

    @Size(max = 255)
    private String autresComplications;

    private OuiNonNP adherenceTherapeutique;

    @Size(max = 255)
    private String causeNonAdherence;

    private VieSociale vieSociale;

    private OuiNonNP marie;

    private OuiNonNP enfants;

    private OuiNonNP activitePhysique;

    @Size(max = 255)
    private String typeActivitePhysique;

    private OuiNonNP sequelles;

    @Size(max = 255)
    private String typeSequelles;

    private OuiNonNP patientDecede;

    private LocalDate dateDeces;

    private Integer ageDeces;

    private Boolean causeDecesSNC;

    private Boolean causeDecesAutreHemorragie;

    private Boolean causeDecesVIH;

    private Boolean causeDecesAutreInfection;

    private Boolean causeDecesThromboembolique;

    private Boolean causeDecesCancer;

    private Boolean causeDecesCovid19;

    private Boolean causeDecesInconnue;

    @Size(max = 255)
    private String autreCauseDeces;

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

    public Boolean getEnqueteFamiliale() {
        return enqueteFamiliale;
    }

    public void setEnqueteFamiliale(Boolean enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public Boolean getDecouverteFortuite() {
        return decouverteFortuite;
    }

    public void setDecouverteFortuite(Boolean decouverteFortuite) {
        this.decouverteFortuite = decouverteFortuite;
    }

    public Boolean getExplorationSyndromeHemorragique() {
        return explorationSyndromeHemorragique;
    }

    public void setExplorationSyndromeHemorragique(Boolean explorationSyndromeHemorragique) {
        this.explorationSyndromeHemorragique = explorationSyndromeHemorragique;
    }

    public Boolean getHemorragieChuteCordon() {
        return hemorragieChuteCordon;
    }

    public void setHemorragieChuteCordon(Boolean hemorragieChuteCordon) {
        this.hemorragieChuteCordon = hemorragieChuteCordon;
    }

    public Boolean getMetroragieMenarche() {
        return metroragieMenarche;
    }

    public void setMetroragieMenarche(Boolean metroragieMenarche) {
        this.metroragieMenarche = metroragieMenarche;
    }

    public Boolean getHemorragieCerebraleNN() {
        return hemorragieCerebraleNN;
    }

    public void setHemorragieCerebraleNN(Boolean hemorragieCerebraleNN) {
        this.hemorragieCerebraleNN = hemorragieCerebraleNN;
    }

    public Boolean getHemorragiePostPartum() {
        return hemorragiePostPartum;
    }

    public void setHemorragiePostPartum(Boolean hemorragiePostPartum) {
        this.hemorragiePostPartum = hemorragiePostPartum;
    }

    public String getAutresCirconstances() {
        return autresCirconstances;
    }

    public void setAutresCirconstances(String autresCirconstances) {
        this.autresCirconstances = autresCirconstances;
    }

    public Integer getDatePremierSigneCliniqueEvocateurJour() {
        return datePremierSigneCliniqueEvocateurJour;
    }

    public void setDatePremierSigneCliniqueEvocateurJour(Integer datePremierSigneCliniqueEvocateurJour) {
        this.datePremierSigneCliniqueEvocateurJour = datePremierSigneCliniqueEvocateurJour;
    }

    public Integer getDatePremierSigneCliniqueEvocateurMois() {
        return datePremierSigneCliniqueEvocateurMois;
    }

    public void setDatePremierSigneCliniqueEvocateurMois(Integer datePremierSigneCliniqueEvocateurMois) {
        this.datePremierSigneCliniqueEvocateurMois = datePremierSigneCliniqueEvocateurMois;
    }

    public Integer getDatePremierSigneCliniqueEvocateurAnnee() {
        return datePremierSigneCliniqueEvocateurAnnee;
    }

    public void setDatePremierSigneCliniqueEvocateurAnnee(Integer datePremierSigneCliniqueEvocateurAnnee) {
        this.datePremierSigneCliniqueEvocateurAnnee = datePremierSigneCliniqueEvocateurAnnee;
    }

    public Integer getAgeDiagnosticJour() {
        return ageDiagnosticJour;
    }

    public void setAgeDiagnosticJour(Integer ageDiagnosticJour) {
        this.ageDiagnosticJour = ageDiagnosticJour;
    }

    public Integer getAgeDiagnosticMois() {
        return ageDiagnosticMois;
    }

    public void setAgeDiagnosticMois(Integer ageDiagnosticMois) {
        this.ageDiagnosticMois = ageDiagnosticMois;
    }

    public Integer getAgeDiagnosticAnnee() {
        return ageDiagnosticAnnee;
    }

    public void setAgeDiagnosticAnnee(Integer ageDiagnosticAnnee) {
        this.ageDiagnosticAnnee = ageDiagnosticAnnee;
    }

    public Double getHb() {
        return hb;
    }

    public void setHb(Double hb) {
        this.hb = hb;
    }

    public Double getHt() {
        return ht;
    }

    public void setHt(Double ht) {
        this.ht = ht;
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

    public Double getTpMT() {
        return tpMT;
    }

    public void setTpMT(Double tpMT) {
        this.tpMT = tpMT;
    }

    public Double getTca() {
        return tca;
    }

    public void setTca(Double tca) {
        this.tca = tca;
    }

    public Double getTcaMT() {
        return tcaMT;
    }

    public void setTcaMT(Double tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Double getFibrinogene() {
        return fibrinogene;
    }

    public void setFibrinogene(Double fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public TypeFaitOuNonFaitEtResultat getTs() {
        return ts;
    }

    public void setTs(TypeFaitOuNonFaitEtResultat ts) {
        this.ts = ts;
    }

    public TypeFaitOuNonFaitEtResultat getToPfa200() {
        return toPfa200;
    }

    public void setToPfa200(TypeFaitOuNonFaitEtResultat toPfa200) {
        this.toPfa200 = toPfa200;
    }

    public Double getColEpi() {
        return colEpi;
    }

    public void setColEpi(Double colEpi) {
        this.colEpi = colEpi;
    }

    public Double getColAdp() {
        return colAdp;
    }

    public void setColAdp(Double colAdp) {
        this.colAdp = colAdp;
    }

    public Double getFacteurXIII() {
        return facteurXIII;
    }

    public void setFacteurXIII(Double facteurXIII) {
        this.facteurXIII = facteurXIII;
    }

    public Boolean getTechniqueChromogenique() {
        return techniqueChromogenique;
    }

    public void setTechniqueChromogenique(Boolean techniqueChromogenique) {
        this.techniqueChromogenique = techniqueChromogenique;
    }

    public Double getTauxChromogenique() {
        return tauxChromogenique;
    }

    public void setTauxChromogenique(Double tauxChromogenique) {
        this.tauxChromogenique = tauxChromogenique;
    }

    public Boolean getTechniqueSolubiliteCaillot() {
        return techniqueSolubiliteCaillot;
    }

    public void setTechniqueSolubiliteCaillot(Boolean techniqueSolubiliteCaillot) {
        this.techniqueSolubiliteCaillot = techniqueSolubiliteCaillot;
    }

    public Double getTauxSolubiliteCaillot() {
        return tauxSolubiliteCaillot;
    }

    public void setTauxSolubiliteCaillot(Double tauxSolubiliteCaillot) {
        this.tauxSolubiliteCaillot = tauxSolubiliteCaillot;
    }

    public Boolean getConfirmationMoleculaire() {
        return confirmationMoleculaire;
    }

    public void setConfirmationMoleculaire(Boolean confirmationMoleculaire) {
        this.confirmationMoleculaire = confirmationMoleculaire;
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

    public MutationType getMutationType() {
        return mutationType;
    }

    public void setMutationType(MutationType mutationType) {
        this.mutationType = mutationType;
    }

    public String getMutation2Nom() {
        return mutation2Nom;
    }

    public void setMutation2Nom(String mutation2Nom) {
        this.mutation2Nom = mutation2Nom;
    }

    public OuiNonNP getSaignementChuteCordon() {
        return saignementChuteCordon;
    }

    public void setSaignementChuteCordon(OuiNonNP saignementChuteCordon) {
        this.saignementChuteCordon = saignementChuteCordon;
    }

    public OuiNonNP getCicatricesKeloides() {
        return cicatricesKeloides;
    }

    public void setCicatricesKeloides(OuiNonNP cicatricesKeloides) {
        this.cicatricesKeloides = cicatricesKeloides;
    }

    public OuiNonNP getRetardCicatrisation() {
        return retardCicatrisation;
    }

    public void setRetardCicatrisation(OuiNonNP retardCicatrisation) {
        this.retardCicatrisation = retardCicatrisation;
    }

    public Boolean getEpistaxis() {
        return epistaxis;
    }

    public void setEpistaxis(Boolean epistaxis) {
        this.epistaxis = epistaxis;
    }

    public Integer getFrequenceEpistaxis() {
        return frequenceEpistaxis;
    }

    public void setFrequenceEpistaxis(Integer frequenceEpistaxis) {
        this.frequenceEpistaxis = frequenceEpistaxis;
    }

    public Boolean getGingivorragies() {
        return gingivorragies;
    }

    public void setGingivorragies(Boolean gingivorragies) {
        this.gingivorragies = gingivorragies;
    }

    public TypeManifestation getTypeGingivorragies() {
        return typeGingivorragies;
    }

    public void setTypeGingivorragies(TypeManifestation typeGingivorragies) {
        this.typeGingivorragies = typeGingivorragies;
    }

    public Integer getFrequenceGingivorragies() {
        return frequenceGingivorragies;
    }

    public void setFrequenceGingivorragies(Integer frequenceGingivorragies) {
        this.frequenceGingivorragies = frequenceGingivorragies;
    }

    public Boolean getEcchymose() {
        return ecchymose;
    }

    public void setEcchymose(Boolean ecchymose) {
        this.ecchymose = ecchymose;
    }

    public TypeManifestation getTypeEcchymose() {
        return typeEcchymose;
    }

    public void setTypeEcchymose(TypeManifestation typeEcchymose) {
        this.typeEcchymose = typeEcchymose;
    }

    public Integer getFrequenceEcchymose() {
        return frequenceEcchymose;
    }

    public void setFrequenceEcchymose(Integer frequenceEcchymose) {
        this.frequenceEcchymose = frequenceEcchymose;
    }

    public Boolean getMenorragie() {
        return menorragie;
    }

    public void setMenorragie(Boolean menorragie) {
        this.menorragie = menorragie;
    }

    public Integer getFrequenceMenorragie() {
        return frequenceMenorragie;
    }

    public void setFrequenceMenorragie(Integer frequenceMenorragie) {
        this.frequenceMenorragie = frequenceMenorragie;
    }

    public Boolean getHematemese() {
        return hematemese;
    }

    public void setHematemese(Boolean hematemese) {
        this.hematemese = hematemese;
    }

    public Integer getFrequenceHematemese() {
        return frequenceHematemese;
    }

    public void setFrequenceHematemese(Integer frequenceHematemese) {
        this.frequenceHematemese = frequenceHematemese;
    }

    public Boolean getRectorragie() {
        return rectorragie;
    }

    public void setRectorragie(Boolean rectorragie) {
        this.rectorragie = rectorragie;
    }

    public Integer getFrequenceRectorragie() {
        return frequenceRectorragie;
    }

    public void setFrequenceRectorragie(Integer frequenceRectorragie) {
        this.frequenceRectorragie = frequenceRectorragie;
    }

    public Boolean getHematurie() {
        return hematurie;
    }

    public void setHematurie(Boolean hematurie) {
        this.hematurie = hematurie;
    }

    public Integer getFrequenceHematurie() {
        return frequenceHematurie;
    }

    public void setFrequenceHematurie(Integer frequenceHematurie) {
        this.frequenceHematurie = frequenceHematurie;
    }

    public Boolean getHematomeSousCutane() {
        return hematomeSousCutane;
    }

    public void setHematomeSousCutane(Boolean hematomeSousCutane) {
        this.hematomeSousCutane = hematomeSousCutane;
    }

    public TypeManifestation getTypeHematomeSousCutane() {
        return typeHematomeSousCutane;
    }

    public void setTypeHematomeSousCutane(TypeManifestation typeHematomeSousCutane) {
        this.typeHematomeSousCutane = typeHematomeSousCutane;
    }

    public Integer getFrequenceHematomeSousCutane() {
        return frequenceHematomeSousCutane;
    }

    public void setFrequenceHematomeSousCutane(Integer frequenceHematomeSousCutane) {
        this.frequenceHematomeSousCutane = frequenceHematomeSousCutane;
    }

    public Boolean getHematomeMusculaire() {
        return hematomeMusculaire;
    }

    public void setHematomeMusculaire(Boolean hematomeMusculaire) {
        this.hematomeMusculaire = hematomeMusculaire;
    }

    public String getSiegeHematomeMusculaire() {
        return siegeHematomeMusculaire;
    }

    public void setSiegeHematomeMusculaire(String siegeHematomeMusculaire) {
        this.siegeHematomeMusculaire = siegeHematomeMusculaire;
    }

    public TypeManifestation getTypeHematomeMusculaire() {
        return typeHematomeMusculaire;
    }

    public void setTypeHematomeMusculaire(TypeManifestation typeHematomeMusculaire) {
        this.typeHematomeMusculaire = typeHematomeMusculaire;
    }

    public Integer getFrequenceHematomeMusculaire() {
        return frequenceHematomeMusculaire;
    }

    public void setFrequenceHematomeMusculaire(Integer frequenceHematomeMusculaire) {
        this.frequenceHematomeMusculaire = frequenceHematomeMusculaire;
    }

    public Boolean getHemarthrose() {
        return hemarthrose;
    }

    public void setHemarthrose(Boolean hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public TypeManifestation getTypeHemarthrose() {
        return typeHemarthrose;
    }

    public void setTypeHemarthrose(TypeManifestation typeHemarthrose) {
        this.typeHemarthrose = typeHemarthrose;
    }

    public Integer getFrequenceHemarthrose() {
        return frequenceHemarthrose;
    }

    public void setFrequenceHemarthrose(Integer frequenceHemarthrose) {
        this.frequenceHemarthrose = frequenceHemarthrose;
    }

    public Boolean getSaignementSNC() {
        return saignementSNC;
    }

    public void setSaignementSNC(Boolean saignementSNC) {
        this.saignementSNC = saignementSNC;
    }

    public TypeManifestation getTypeSaignementSNC() {
        return typeSaignementSNC;
    }

    public void setTypeSaignementSNC(TypeManifestation typeSaignementSNC) {
        this.typeSaignementSNC = typeSaignementSNC;
    }

    public Integer getFrequenceSaignementSNC() {
        return frequenceSaignementSNC;
    }

    public void setFrequenceSaignementSNC(Integer frequenceSaignementSNC) {
        this.frequenceSaignementSNC = frequenceSaignementSNC;
    }

    public Boolean getRuptureKystesHemorragiques() {
        return ruptureKystesHemorragiques;
    }

    public void setRuptureKystesHemorragiques(Boolean ruptureKystesHemorragiques) {
        this.ruptureKystesHemorragiques = ruptureKystesHemorragiques;
    }

    public Boolean getHemorragiesPostChirurgicales() {
        return hemorragiesPostChirurgicales;
    }

    public void setHemorragiesPostChirurgicales(Boolean hemorragiesPostChirurgicales) {
        this.hemorragiesPostChirurgicales = hemorragiesPostChirurgicales;
    }

    public Boolean getHemoperitone() {
        return hemoperitone;
    }

    public void setHemoperitone(Boolean hemoperitone) {
        this.hemoperitone = hemoperitone;
    }

    public Boolean getAccidentsThrombotiques() {
        return accidentsThrombotiques;
    }

    public void setAccidentsThrombotiques(Boolean accidentsThrombotiques) {
        this.accidentsThrombotiques = accidentsThrombotiques;
    }

    public AccidentsThrombotiquesType getTypeAccidentsThrombotiques() {
        return typeAccidentsThrombotiques;
    }

    public void setTypeAccidentsThrombotiques(AccidentsThrombotiquesType typeAccidentsThrombotiques) {
        this.typeAccidentsThrombotiques = typeAccidentsThrombotiques;
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

    public OuiNonNP getChirurgiePatientPrepare() {
        return chirurgiePatientPrepare;
    }

    public void setChirurgiePatientPrepare(OuiNonNP chirurgiePatientPrepare) {
        this.chirurgiePatientPrepare = chirurgiePatientPrepare;
    }

    public OuiNonNP getChirurgieSuiteHemorragique() {
        return chirurgieSuiteHemorragique;
    }

    public void setChirurgieSuiteHemorragique(OuiNonNP chirurgieSuiteHemorragique) {
        this.chirurgieSuiteHemorragique = chirurgieSuiteHemorragique;
    }

    public OuiNonNP getCirconcisionPatientPrepare() {
        return circoncisionPatientPrepare;
    }

    public void setCirconcisionPatientPrepare(OuiNonNP circoncisionPatientPrepare) {
        this.circoncisionPatientPrepare = circoncisionPatientPrepare;
    }

    public OuiNonNP getCirconcisionSuiteHemorragique() {
        return circoncisionSuiteHemorragique;
    }

    public void setCirconcisionSuiteHemorragique(OuiNonNP circoncisionSuiteHemorragique) {
        this.circoncisionSuiteHemorragique = circoncisionSuiteHemorragique;
    }

    public Integer getAgeCirconcision() {
        return ageCirconcision;
    }

    public void setAgeCirconcision(Integer ageCirconcision) {
        this.ageCirconcision = ageCirconcision;
    }

    public OuiNonNP getConcentreFacteurXIII() {
        return concentreFacteurXIII;
    }

    public void setConcentreFacteurXIII(OuiNonNP concentreFacteurXIII) {
        this.concentreFacteurXIII = concentreFacteurXIII;
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

    public OuiNonNP getCulotsPlaquettaires() {
        return culotsPlaquettaires;
    }

    public void setCulotsPlaquettaires(OuiNonNP culotsPlaquettaires) {
        this.culotsPlaquettaires = culotsPlaquettaires;
    }

    public OuiNonNP getDicynone() {
        return dicynone;
    }

    public void setDicynone(OuiNonNP dicynone) {
        this.dicynone = dicynone;
    }

    public OuiNonNP getExacyl() {
        return exacyl;
    }

    public void setExacyl(OuiNonNP exacyl) {
        this.exacyl = exacyl;
    }

    public OuiNonNP getTraitementMartial() {
        return traitementMartial;
    }

    public void setTraitementMartial(OuiNonNP traitementMartial) {
        this.traitementMartial = traitementMartial;
    }

    public OuiNonNP getTraitementOestroprogestatif() {
        return traitementOestroprogestatif;
    }

    public void setTraitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.traitementOestroprogestatif = traitementOestroprogestatif;
    }

    public OuiNonNP getTransfusionCGR() {
        return transfusionCGR;
    }

    public void setTransfusionCGR(OuiNonNP transfusionCGR) {
        this.transfusionCGR = transfusionCGR;
    }

    public OuiNonNP getInhibiteurs() {
        return inhibiteurs;
    }

    public void setInhibiteurs(OuiNonNP inhibiteurs) {
        this.inhibiteurs = inhibiteurs;
    }

    public Boolean getInhibiteurNeutralisant() {
        return inhibiteurNeutralisant;
    }

    public void setInhibiteurNeutralisant(Boolean inhibiteurNeutralisant) {
        this.inhibiteurNeutralisant = inhibiteurNeutralisant;
    }

    public Double getTitreInhibiteurNeutralisant() {
        return titreInhibiteurNeutralisant;
    }

    public void setTitreInhibiteurNeutralisant(Double titreInhibiteurNeutralisant) {
        this.titreInhibiteurNeutralisant = titreInhibiteurNeutralisant;
    }

    public Boolean getInhibiteurNonNeutralisant() {
        return inhibiteurNonNeutralisant;
    }

    public void setInhibiteurNonNeutralisant(Boolean inhibiteurNonNeutralisant) {
        this.inhibiteurNonNeutralisant = inhibiteurNonNeutralisant;
    }

    public OuiNonNP getInfectionsVirales() {
        return infectionsVirales;
    }

    public void setInfectionsVirales(OuiNonNP infectionsVirales) {
        this.infectionsVirales = infectionsVirales;
    }

    public OuiNonNP getHepatiteB() {
        return hepatiteB;
    }

    public void setHepatiteB(OuiNonNP hepatiteB) {
        this.hepatiteB = hepatiteB;
    }

    public OuiNonNP getHepatiteC() {
        return hepatiteC;
    }

    public void setHepatiteC(OuiNonNP hepatiteC) {
        this.hepatiteC = hepatiteC;
    }

    public OuiNonNP getVih() {
        return vih;
    }

    public void setVih(OuiNonNP vih) {
        this.vih = vih;
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

    public Boolean getAlloimmunisation() {
        return alloimmunisation;
    }

    public void setAlloimmunisation(Boolean alloimmunisation) {
        this.alloimmunisation = alloimmunisation;
    }

    public String getAutresComplications() {
        return autresComplications;
    }

    public void setAutresComplications(String autresComplications) {
        this.autresComplications = autresComplications;
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

    public Boolean getCauseDecesSNC() {
        return causeDecesSNC;
    }

    public void setCauseDecesSNC(Boolean causeDecesSNC) {
        this.causeDecesSNC = causeDecesSNC;
    }

    public Boolean getCauseDecesAutreHemorragie() {
        return causeDecesAutreHemorragie;
    }

    public void setCauseDecesAutreHemorragie(Boolean causeDecesAutreHemorragie) {
        this.causeDecesAutreHemorragie = causeDecesAutreHemorragie;
    }

    public Boolean getCauseDecesVIH() {
        return causeDecesVIH;
    }

    public void setCauseDecesVIH(Boolean causeDecesVIH) {
        this.causeDecesVIH = causeDecesVIH;
    }

    public Boolean getCauseDecesAutreInfection() {
        return causeDecesAutreInfection;
    }

    public void setCauseDecesAutreInfection(Boolean causeDecesAutreInfection) {
        this.causeDecesAutreInfection = causeDecesAutreInfection;
    }

    public Boolean getCauseDecesThromboembolique() {
        return causeDecesThromboembolique;
    }

    public void setCauseDecesThromboembolique(Boolean causeDecesThromboembolique) {
        this.causeDecesThromboembolique = causeDecesThromboembolique;
    }

    public Boolean getCauseDecesCancer() {
        return causeDecesCancer;
    }

    public void setCauseDecesCancer(Boolean causeDecesCancer) {
        this.causeDecesCancer = causeDecesCancer;
    }

    public Boolean getCauseDecesCovid19() {
        return causeDecesCovid19;
    }

    public void setCauseDecesCovid19(Boolean causeDecesCovid19) {
        this.causeDecesCovid19 = causeDecesCovid19;
    }

    public Boolean getCauseDecesInconnue() {
        return causeDecesInconnue;
    }

    public void setCauseDecesInconnue(Boolean causeDecesInconnue) {
        this.causeDecesInconnue = causeDecesInconnue;
    }

    public String getAutreCauseDeces() {
        return autreCauseDeces;
    }

    public void setAutreCauseDeces(String autreCauseDeces) {
        this.autreCauseDeces = autreCauseDeces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheFacteurXIIIDTO)) {
            return false;
        }

        FicheFacteurXIIIDTO ficheFacteurXIIIDTO = (FicheFacteurXIIIDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ficheFacteurXIIIDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheFacteurXIIIDTO{" +
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

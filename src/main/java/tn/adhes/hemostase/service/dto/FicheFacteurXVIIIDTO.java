package tn.adhes.hemostase.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import tn.adhes.hemostase.domain.enumeration.CauseDeces;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DegreParente;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.LienParente;
import tn.adhes.hemostase.domain.enumeration.MutationType;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.Region;
import tn.adhes.hemostase.domain.enumeration.SerologieStatut;
import tn.adhes.hemostase.domain.enumeration.ServiceCliniqueType;
import tn.adhes.hemostase.domain.enumeration.StatutSocial;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;

/**
 * A DTO for the {@link tn.adhes.hemostase.domain.FicheFacteurXVIII} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheFacteurXVIIIDTO implements Serializable {

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

    private Boolean circHemorragique;

    private String precisionsCircHemorragique;

    private Boolean decouverteFortuitePreop;

    private String precisionsDecouverteFortuite;

    private OuiNonNP jamaisSaignement;

    private Integer agePremierSigneJours;

    private Integer agePremierSigneMois;

    private Integer agePremierSigneAnnees;

    private Boolean symptomeEcchymose;

    private Boolean symptomeEpistaxis;

    private Boolean symptomeGingivorragies;

    private Boolean symptomeMetrorragies;

    private Boolean symptomeMelena;

    private Boolean symptomeHemoptysie;

    private Boolean symptomePostAmygdalectomie;

    private Boolean symptomePostAvulsionDentaire;

    private Boolean symptomeChuteCordon;

    private Boolean symptomeAutre;

    private String symptomeAutreDescription;

    private LocalDate datePremiereConsultation;

    private Integer agePremiereConsultationJours;

    private Integer agePremiereConsultationMois;

    private Integer agePremiereConsultationAnnees;

    private Integer ageDiagnosticJours;

    private Integer ageDiagnosticMois;

    private Integer ageDiagnosticAnnees;

    private Double hb;

    private Double ht;

    private Integer plaquettes;

    private Double colEpi;

    private Double colAdp;

    private Double tp;

    private Double tpMPlusT;

    private Double fibrinogene;

    private Double tcaMT;

    private Double tcaMPlusT;

    private Double tcaTemoin;

    private String facteurDeficitaireAutre;

    private Double tauxFacteurDeficitaireAutre;

    private String facteurSupplementaire1;

    private Double tauxSupplementaire1;

    private String facteurSupplementaire2;

    private Double tauxSupplementaire2;

    private String facteurSupplementaire3;

    private Double tauxSupplementaire3;

    private OuiNonNP confirmationMoleculaire;

    private Boolean mutationPresente;

    private String mutationNom;

    private MutationType mutationType;

    private String mutation2Nom;

    private SerologieStatut serologieVHB;

    private SerologieStatut serologieVHC;

    private SerologieStatut serologieVIH;

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

    private Boolean saignementChuteCordon;

    private Integer frequenceSaignementChuteCordon;

    private Boolean retardCicatrisationPlaies;

    private Integer frequenceRetardCicatrisationPlaies;

    private Boolean autresManifestations;

    private Boolean faussesCouchesSpontaneesRecurrence;

    private Integer frequenceFaussesCouches;

    private Boolean accidentsThrombotiques;

    private Boolean thromboseVeineuse;

    private Boolean thromboseArterielle;

    private Integer frequenceAccidentsThrombotiques;

    private Boolean hemoperitoine;

    private Integer frequenceHemoperitoine;

    private Boolean ruptureKystesHemorragiques;

    private Integer frequenceRuptureKystes;

    private Boolean hemorragieGrave;

    private Integer frequenceHemorragieGrave;

    private Boolean graviteDeglobulisation;

    private Boolean graviteEtatDeChoc;

    private Boolean graviteParLocalisation;

    @Size(max = 255)
    private String siegeGraviteLocalisation;

    private OuiNonNP chirurgiePrepare;

    private OuiNonNP chirurgieSuiteHemorragique;

    private OuiNonNP circoncisionPrepare;

    private OuiNonNP circoncisionSuiteHemorragique;

    private Integer ageCirconcision;

    private OuiNonNP traitementPFC;

    private OuiNonNP traitementCryoprecipite;

    private OuiNonNP traitementConcentreFacteurXI;

    private OuiNonNP traitementCulotsPlaquettaires;

    private OuiNonNP traitementDicynone;

    private OuiNonNP traitementExacyl;

    private OuiNonNP traitementMartial;

    private OuiNonNP traitementOestroprogestatif;

    private OuiNonNP transfusionCGR;

    private OuiNonNP alloImmunisation;

    private OuiNonNP inhibiteurNeutralisant;

    private Double titreInhibiteurUB;

    private OuiNonNP inhibiteurNonNeutralisant;

    private OuiNonNP infectionVIH;

    private OuiNonNP infectionVHC;

    private OuiNonNP infectionVHB;

    private Boolean thrombose;

    private Boolean reactionAllergique;

    @Size(max = 255)
    private String autresComplications;

    private OuiNonNP adherenceTherapeutique;

    @Size(max = 255)
    private String causeNonAdherence;

    private StatutSocial statutSocial;

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

    public Boolean getCircHemorragique() {
        return circHemorragique;
    }

    public void setCircHemorragique(Boolean circHemorragique) {
        this.circHemorragique = circHemorragique;
    }

    public String getPrecisionsCircHemorragique() {
        return precisionsCircHemorragique;
    }

    public void setPrecisionsCircHemorragique(String precisionsCircHemorragique) {
        this.precisionsCircHemorragique = precisionsCircHemorragique;
    }

    public Boolean getDecouverteFortuitePreop() {
        return decouverteFortuitePreop;
    }

    public void setDecouverteFortuitePreop(Boolean decouverteFortuitePreop) {
        this.decouverteFortuitePreop = decouverteFortuitePreop;
    }

    public String getPrecisionsDecouverteFortuite() {
        return precisionsDecouverteFortuite;
    }

    public void setPrecisionsDecouverteFortuite(String precisionsDecouverteFortuite) {
        this.precisionsDecouverteFortuite = precisionsDecouverteFortuite;
    }

    public OuiNonNP getJamaisSaignement() {
        return jamaisSaignement;
    }

    public void setJamaisSaignement(OuiNonNP jamaisSaignement) {
        this.jamaisSaignement = jamaisSaignement;
    }

    public Integer getAgePremierSigneJours() {
        return agePremierSigneJours;
    }

    public void setAgePremierSigneJours(Integer agePremierSigneJours) {
        this.agePremierSigneJours = agePremierSigneJours;
    }

    public Integer getAgePremierSigneMois() {
        return agePremierSigneMois;
    }

    public void setAgePremierSigneMois(Integer agePremierSigneMois) {
        this.agePremierSigneMois = agePremierSigneMois;
    }

    public Integer getAgePremierSigneAnnees() {
        return agePremierSigneAnnees;
    }

    public void setAgePremierSigneAnnees(Integer agePremierSigneAnnees) {
        this.agePremierSigneAnnees = agePremierSigneAnnees;
    }

    public Boolean getSymptomeEcchymose() {
        return symptomeEcchymose;
    }

    public void setSymptomeEcchymose(Boolean symptomeEcchymose) {
        this.symptomeEcchymose = symptomeEcchymose;
    }

    public Boolean getSymptomeEpistaxis() {
        return symptomeEpistaxis;
    }

    public void setSymptomeEpistaxis(Boolean symptomeEpistaxis) {
        this.symptomeEpistaxis = symptomeEpistaxis;
    }

    public Boolean getSymptomeGingivorragies() {
        return symptomeGingivorragies;
    }

    public void setSymptomeGingivorragies(Boolean symptomeGingivorragies) {
        this.symptomeGingivorragies = symptomeGingivorragies;
    }

    public Boolean getSymptomeMetrorragies() {
        return symptomeMetrorragies;
    }

    public void setSymptomeMetrorragies(Boolean symptomeMetrorragies) {
        this.symptomeMetrorragies = symptomeMetrorragies;
    }

    public Boolean getSymptomeMelena() {
        return symptomeMelena;
    }

    public void setSymptomeMelena(Boolean symptomeMelena) {
        this.symptomeMelena = symptomeMelena;
    }

    public Boolean getSymptomeHemoptysie() {
        return symptomeHemoptysie;
    }

    public void setSymptomeHemoptysie(Boolean symptomeHemoptysie) {
        this.symptomeHemoptysie = symptomeHemoptysie;
    }

    public Boolean getSymptomePostAmygdalectomie() {
        return symptomePostAmygdalectomie;
    }

    public void setSymptomePostAmygdalectomie(Boolean symptomePostAmygdalectomie) {
        this.symptomePostAmygdalectomie = symptomePostAmygdalectomie;
    }

    public Boolean getSymptomePostAvulsionDentaire() {
        return symptomePostAvulsionDentaire;
    }

    public void setSymptomePostAvulsionDentaire(Boolean symptomePostAvulsionDentaire) {
        this.symptomePostAvulsionDentaire = symptomePostAvulsionDentaire;
    }

    public Boolean getSymptomeChuteCordon() {
        return symptomeChuteCordon;
    }

    public void setSymptomeChuteCordon(Boolean symptomeChuteCordon) {
        this.symptomeChuteCordon = symptomeChuteCordon;
    }

    public Boolean getSymptomeAutre() {
        return symptomeAutre;
    }

    public void setSymptomeAutre(Boolean symptomeAutre) {
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

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Double getTpMPlusT() {
        return tpMPlusT;
    }

    public void setTpMPlusT(Double tpMPlusT) {
        this.tpMPlusT = tpMPlusT;
    }

    public Double getFibrinogene() {
        return fibrinogene;
    }

    public void setFibrinogene(Double fibrinogene) {
        this.fibrinogene = fibrinogene;
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

    public Double getTcaTemoin() {
        return tcaTemoin;
    }

    public void setTcaTemoin(Double tcaTemoin) {
        this.tcaTemoin = tcaTemoin;
    }

    public String getFacteurDeficitaireAutre() {
        return facteurDeficitaireAutre;
    }

    public void setFacteurDeficitaireAutre(String facteurDeficitaireAutre) {
        this.facteurDeficitaireAutre = facteurDeficitaireAutre;
    }

    public Double getTauxFacteurDeficitaireAutre() {
        return tauxFacteurDeficitaireAutre;
    }

    public void setTauxFacteurDeficitaireAutre(Double tauxFacteurDeficitaireAutre) {
        this.tauxFacteurDeficitaireAutre = tauxFacteurDeficitaireAutre;
    }

    public String getFacteurSupplementaire1() {
        return facteurSupplementaire1;
    }

    public void setFacteurSupplementaire1(String facteurSupplementaire1) {
        this.facteurSupplementaire1 = facteurSupplementaire1;
    }

    public Double getTauxSupplementaire1() {
        return tauxSupplementaire1;
    }

    public void setTauxSupplementaire1(Double tauxSupplementaire1) {
        this.tauxSupplementaire1 = tauxSupplementaire1;
    }

    public String getFacteurSupplementaire2() {
        return facteurSupplementaire2;
    }

    public void setFacteurSupplementaire2(String facteurSupplementaire2) {
        this.facteurSupplementaire2 = facteurSupplementaire2;
    }

    public Double getTauxSupplementaire2() {
        return tauxSupplementaire2;
    }

    public void setTauxSupplementaire2(Double tauxSupplementaire2) {
        this.tauxSupplementaire2 = tauxSupplementaire2;
    }

    public String getFacteurSupplementaire3() {
        return facteurSupplementaire3;
    }

    public void setFacteurSupplementaire3(String facteurSupplementaire3) {
        this.facteurSupplementaire3 = facteurSupplementaire3;
    }

    public Double getTauxSupplementaire3() {
        return tauxSupplementaire3;
    }

    public void setTauxSupplementaire3(Double tauxSupplementaire3) {
        this.tauxSupplementaire3 = tauxSupplementaire3;
    }

    public OuiNonNP getConfirmationMoleculaire() {
        return confirmationMoleculaire;
    }

    public void setConfirmationMoleculaire(OuiNonNP confirmationMoleculaire) {
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

    public SerologieStatut getSerologieVHB() {
        return serologieVHB;
    }

    public void setSerologieVHB(SerologieStatut serologieVHB) {
        this.serologieVHB = serologieVHB;
    }

    public SerologieStatut getSerologieVHC() {
        return serologieVHC;
    }

    public void setSerologieVHC(SerologieStatut serologieVHC) {
        this.serologieVHC = serologieVHC;
    }

    public SerologieStatut getSerologieVIH() {
        return serologieVIH;
    }

    public void setSerologieVIH(SerologieStatut serologieVIH) {
        this.serologieVIH = serologieVIH;
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

    public Boolean getSaignementChuteCordon() {
        return saignementChuteCordon;
    }

    public void setSaignementChuteCordon(Boolean saignementChuteCordon) {
        this.saignementChuteCordon = saignementChuteCordon;
    }

    public Integer getFrequenceSaignementChuteCordon() {
        return frequenceSaignementChuteCordon;
    }

    public void setFrequenceSaignementChuteCordon(Integer frequenceSaignementChuteCordon) {
        this.frequenceSaignementChuteCordon = frequenceSaignementChuteCordon;
    }

    public Boolean getRetardCicatrisationPlaies() {
        return retardCicatrisationPlaies;
    }

    public void setRetardCicatrisationPlaies(Boolean retardCicatrisationPlaies) {
        this.retardCicatrisationPlaies = retardCicatrisationPlaies;
    }

    public Integer getFrequenceRetardCicatrisationPlaies() {
        return frequenceRetardCicatrisationPlaies;
    }

    public void setFrequenceRetardCicatrisationPlaies(Integer frequenceRetardCicatrisationPlaies) {
        this.frequenceRetardCicatrisationPlaies = frequenceRetardCicatrisationPlaies;
    }

    public Boolean getAutresManifestations() {
        return autresManifestations;
    }

    public void setAutresManifestations(Boolean autresManifestations) {
        this.autresManifestations = autresManifestations;
    }

    public Boolean getFaussesCouchesSpontaneesRecurrence() {
        return faussesCouchesSpontaneesRecurrence;
    }

    public void setFaussesCouchesSpontaneesRecurrence(Boolean faussesCouchesSpontaneesRecurrence) {
        this.faussesCouchesSpontaneesRecurrence = faussesCouchesSpontaneesRecurrence;
    }

    public Integer getFrequenceFaussesCouches() {
        return frequenceFaussesCouches;
    }

    public void setFrequenceFaussesCouches(Integer frequenceFaussesCouches) {
        this.frequenceFaussesCouches = frequenceFaussesCouches;
    }

    public Boolean getAccidentsThrombotiques() {
        return accidentsThrombotiques;
    }

    public void setAccidentsThrombotiques(Boolean accidentsThrombotiques) {
        this.accidentsThrombotiques = accidentsThrombotiques;
    }

    public Boolean getThromboseVeineuse() {
        return thromboseVeineuse;
    }

    public void setThromboseVeineuse(Boolean thromboseVeineuse) {
        this.thromboseVeineuse = thromboseVeineuse;
    }

    public Boolean getThromboseArterielle() {
        return thromboseArterielle;
    }

    public void setThromboseArterielle(Boolean thromboseArterielle) {
        this.thromboseArterielle = thromboseArterielle;
    }

    public Integer getFrequenceAccidentsThrombotiques() {
        return frequenceAccidentsThrombotiques;
    }

    public void setFrequenceAccidentsThrombotiques(Integer frequenceAccidentsThrombotiques) {
        this.frequenceAccidentsThrombotiques = frequenceAccidentsThrombotiques;
    }

    public Boolean getHemoperitoine() {
        return hemoperitoine;
    }

    public void setHemoperitoine(Boolean hemoperitoine) {
        this.hemoperitoine = hemoperitoine;
    }

    public Integer getFrequenceHemoperitoine() {
        return frequenceHemoperitoine;
    }

    public void setFrequenceHemoperitoine(Integer frequenceHemoperitoine) {
        this.frequenceHemoperitoine = frequenceHemoperitoine;
    }

    public Boolean getRuptureKystesHemorragiques() {
        return ruptureKystesHemorragiques;
    }

    public void setRuptureKystesHemorragiques(Boolean ruptureKystesHemorragiques) {
        this.ruptureKystesHemorragiques = ruptureKystesHemorragiques;
    }

    public Integer getFrequenceRuptureKystes() {
        return frequenceRuptureKystes;
    }

    public void setFrequenceRuptureKystes(Integer frequenceRuptureKystes) {
        this.frequenceRuptureKystes = frequenceRuptureKystes;
    }

    public Boolean getHemorragieGrave() {
        return hemorragieGrave;
    }

    public void setHemorragieGrave(Boolean hemorragieGrave) {
        this.hemorragieGrave = hemorragieGrave;
    }

    public Integer getFrequenceHemorragieGrave() {
        return frequenceHemorragieGrave;
    }

    public void setFrequenceHemorragieGrave(Integer frequenceHemorragieGrave) {
        this.frequenceHemorragieGrave = frequenceHemorragieGrave;
    }

    public Boolean getGraviteDeglobulisation() {
        return graviteDeglobulisation;
    }

    public void setGraviteDeglobulisation(Boolean graviteDeglobulisation) {
        this.graviteDeglobulisation = graviteDeglobulisation;
    }

    public Boolean getGraviteEtatDeChoc() {
        return graviteEtatDeChoc;
    }

    public void setGraviteEtatDeChoc(Boolean graviteEtatDeChoc) {
        this.graviteEtatDeChoc = graviteEtatDeChoc;
    }

    public Boolean getGraviteParLocalisation() {
        return graviteParLocalisation;
    }

    public void setGraviteParLocalisation(Boolean graviteParLocalisation) {
        this.graviteParLocalisation = graviteParLocalisation;
    }

    public String getSiegeGraviteLocalisation() {
        return siegeGraviteLocalisation;
    }

    public void setSiegeGraviteLocalisation(String siegeGraviteLocalisation) {
        this.siegeGraviteLocalisation = siegeGraviteLocalisation;
    }

    public OuiNonNP getChirurgiePrepare() {
        return chirurgiePrepare;
    }

    public void setChirurgiePrepare(OuiNonNP chirurgiePrepare) {
        this.chirurgiePrepare = chirurgiePrepare;
    }

    public OuiNonNP getChirurgieSuiteHemorragique() {
        return chirurgieSuiteHemorragique;
    }

    public void setChirurgieSuiteHemorragique(OuiNonNP chirurgieSuiteHemorragique) {
        this.chirurgieSuiteHemorragique = chirurgieSuiteHemorragique;
    }

    public OuiNonNP getCirconcisionPrepare() {
        return circoncisionPrepare;
    }

    public void setCirconcisionPrepare(OuiNonNP circoncisionPrepare) {
        this.circoncisionPrepare = circoncisionPrepare;
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

    public OuiNonNP getTraitementPFC() {
        return traitementPFC;
    }

    public void setTraitementPFC(OuiNonNP traitementPFC) {
        this.traitementPFC = traitementPFC;
    }

    public OuiNonNP getTraitementCryoprecipite() {
        return traitementCryoprecipite;
    }

    public void setTraitementCryoprecipite(OuiNonNP traitementCryoprecipite) {
        this.traitementCryoprecipite = traitementCryoprecipite;
    }

    public OuiNonNP getTraitementConcentreFacteurXI() {
        return traitementConcentreFacteurXI;
    }

    public void setTraitementConcentreFacteurXI(OuiNonNP traitementConcentreFacteurXI) {
        this.traitementConcentreFacteurXI = traitementConcentreFacteurXI;
    }

    public OuiNonNP getTraitementCulotsPlaquettaires() {
        return traitementCulotsPlaquettaires;
    }

    public void setTraitementCulotsPlaquettaires(OuiNonNP traitementCulotsPlaquettaires) {
        this.traitementCulotsPlaquettaires = traitementCulotsPlaquettaires;
    }

    public OuiNonNP getTraitementDicynone() {
        return traitementDicynone;
    }

    public void setTraitementDicynone(OuiNonNP traitementDicynone) {
        this.traitementDicynone = traitementDicynone;
    }

    public OuiNonNP getTraitementExacyl() {
        return traitementExacyl;
    }

    public void setTraitementExacyl(OuiNonNP traitementExacyl) {
        this.traitementExacyl = traitementExacyl;
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

    public OuiNonNP getAlloImmunisation() {
        return alloImmunisation;
    }

    public void setAlloImmunisation(OuiNonNP alloImmunisation) {
        this.alloImmunisation = alloImmunisation;
    }

    public OuiNonNP getInhibiteurNeutralisant() {
        return inhibiteurNeutralisant;
    }

    public void setInhibiteurNeutralisant(OuiNonNP inhibiteurNeutralisant) {
        this.inhibiteurNeutralisant = inhibiteurNeutralisant;
    }

    public Double getTitreInhibiteurUB() {
        return titreInhibiteurUB;
    }

    public void setTitreInhibiteurUB(Double titreInhibiteurUB) {
        this.titreInhibiteurUB = titreInhibiteurUB;
    }

    public OuiNonNP getInhibiteurNonNeutralisant() {
        return inhibiteurNonNeutralisant;
    }

    public void setInhibiteurNonNeutralisant(OuiNonNP inhibiteurNonNeutralisant) {
        this.inhibiteurNonNeutralisant = inhibiteurNonNeutralisant;
    }

    public OuiNonNP getInfectionVIH() {
        return infectionVIH;
    }

    public void setInfectionVIH(OuiNonNP infectionVIH) {
        this.infectionVIH = infectionVIH;
    }

    public OuiNonNP getInfectionVHC() {
        return infectionVHC;
    }

    public void setInfectionVHC(OuiNonNP infectionVHC) {
        this.infectionVHC = infectionVHC;
    }

    public OuiNonNP getInfectionVHB() {
        return infectionVHB;
    }

    public void setInfectionVHB(OuiNonNP infectionVHB) {
        this.infectionVHB = infectionVHB;
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
        if (!(o instanceof FicheFacteurXVIIIDTO)) {
            return false;
        }

        FicheFacteurXVIIIDTO ficheFacteurXVIIIDTO = (FicheFacteurXVIIIDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ficheFacteurXVIIIDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheFacteurXVIIIDTO{" +
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
            ", circHemorragique='" + getCircHemorragique() + "'" +
            ", precisionsCircHemorragique='" + getPrecisionsCircHemorragique() + "'" +
            ", decouverteFortuitePreop='" + getDecouverteFortuitePreop() + "'" +
            ", precisionsDecouverteFortuite='" + getPrecisionsDecouverteFortuite() + "'" +
            ", jamaisSaignement='" + getJamaisSaignement() + "'" +
            ", agePremierSigneJours=" + getAgePremierSigneJours() +
            ", agePremierSigneMois=" + getAgePremierSigneMois() +
            ", agePremierSigneAnnees=" + getAgePremierSigneAnnees() +
            ", symptomeEcchymose='" + getSymptomeEcchymose() + "'" +
            ", symptomeEpistaxis='" + getSymptomeEpistaxis() + "'" +
            ", symptomeGingivorragies='" + getSymptomeGingivorragies() + "'" +
            ", symptomeMetrorragies='" + getSymptomeMetrorragies() + "'" +
            ", symptomeMelena='" + getSymptomeMelena() + "'" +
            ", symptomeHemoptysie='" + getSymptomeHemoptysie() + "'" +
            ", symptomePostAmygdalectomie='" + getSymptomePostAmygdalectomie() + "'" +
            ", symptomePostAvulsionDentaire='" + getSymptomePostAvulsionDentaire() + "'" +
            ", symptomeChuteCordon='" + getSymptomeChuteCordon() + "'" +
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
            ", ht=" + getHt() +
            ", plaquettes=" + getPlaquettes() +
            ", colEpi=" + getColEpi() +
            ", colAdp=" + getColAdp() +
            ", tp=" + getTp() +
            ", tpMPlusT=" + getTpMPlusT() +
            ", fibrinogene=" + getFibrinogene() +
            ", tcaMT=" + getTcaMT() +
            ", tcaMPlusT=" + getTcaMPlusT() +
            ", tcaTemoin=" + getTcaTemoin() +
            ", facteurDeficitaireAutre='" + getFacteurDeficitaireAutre() + "'" +
            ", tauxFacteurDeficitaireAutre=" + getTauxFacteurDeficitaireAutre() +
            ", facteurSupplementaire1='" + getFacteurSupplementaire1() + "'" +
            ", tauxSupplementaire1=" + getTauxSupplementaire1() +
            ", facteurSupplementaire2='" + getFacteurSupplementaire2() + "'" +
            ", tauxSupplementaire2=" + getTauxSupplementaire2() +
            ", facteurSupplementaire3='" + getFacteurSupplementaire3() + "'" +
            ", tauxSupplementaire3=" + getTauxSupplementaire3() +
            ", confirmationMoleculaire='" + getConfirmationMoleculaire() + "'" +
            ", mutationPresente='" + getMutationPresente() + "'" +
            ", mutationNom='" + getMutationNom() + "'" +
            ", mutationType='" + getMutationType() + "'" +
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

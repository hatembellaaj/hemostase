package tn.adhes.hemostase.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
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
 * A DTO for the {@link tn.adhes.hemostase.domain.FicheFacteurVIII} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheFacteurVIIIDTO implements Serializable {

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

    private FaiteNonFaite enqueteFamiliale;

    private Boolean decouverteFortuite;

    private Boolean enqueteFamilialeBool;

    private Boolean explorationSyndromeHemorragique;

    private ExplorationType typeExplorationSyndromeHemorragique;

    private LocalDate datePremierSigneClinique;

    private Integer ageDiagnosticJour;

    private Integer ageDiagnosticMois;

    private Integer ageDiagnosticAnnee;

    private Float tp;

    private Float tpMT;

    private Float tca;

    private Float tcaMT;

    private Float fibrinogene;

    private FaiteNonFaite ts;

    private NormalAllonge tsFait;

    private NormalAllonge toPfa;

    private Float toPfa200ColEpi;

    private Float toPfa200ColAdp;

    private Float tauxDosageFacteurXIII;

    private Float hb;

    private Float ht;

    private Integer plaquettes;

    private Boolean techniqueChromogenique;

    private Float tauxTechniqueChromogenique;

    private Boolean techniqueSolubiliteCaillot;

    private Float tauxTechniqueSolubiliteCaillot;

    private FaiteNonFaite confirmationMoleculaire;

    private Boolean mutationPresence;

    private MutationType mutationType;

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

    private TypeAccidentsThrombotiques typeAccidentsThrombotiques;

    private Boolean hemorragieGrave;

    private TypeHemorragieGrave typeHemorragieGrave;

    private OuiNonNP chirurgiePrepare;

    private OuiNonNP suiteOperatoireHemorragique;

    private OuiNonNP circoncisionPrepare;

    private OuiNonNP suiteOperatoireCirconcision;

    private Integer ageCirconcision;

    private OuiNonNP facteurVIIIRecombinant;

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

    private Float titreUB;

    private Boolean inhibiteurNonNeutralisant;

    private OuiNonNP infectionsVirales;

    private OuiNonNP thrombose;

    private OuiNonNP reactionAllergique;

    private OuiNonNP alloImmunisation;

    private OuiNonNP adhesionTherapeutique;

    @Size(max = 255)
    private String causeNonAdhesion;

    private VieSocialeType vieSociale;

    private OuiNonNP mariage;

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

    private Boolean hemorragieSnc;

    private Boolean hemorragieAutreQueSnc;

    private Boolean hiv;

    private Boolean autreInfection;

    private Boolean evenementThromboembolique;

    private Boolean cancer;

    private Boolean covid19;

    private Boolean inconnue;

    private Boolean autreEvenement;

    @Size(max = 255)
    private String autreEvenementDetails;

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

    public FaiteNonFaite getEnqueteFamiliale() {
        return enqueteFamiliale;
    }

    public void setEnqueteFamiliale(FaiteNonFaite enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public Boolean getDecouverteFortuite() {
        return decouverteFortuite;
    }

    public void setDecouverteFortuite(Boolean decouverteFortuite) {
        this.decouverteFortuite = decouverteFortuite;
    }

    public Boolean getEnqueteFamilialeBool() {
        return enqueteFamilialeBool;
    }

    public void setEnqueteFamilialeBool(Boolean enqueteFamilialeBool) {
        this.enqueteFamilialeBool = enqueteFamilialeBool;
    }

    public Boolean getExplorationSyndromeHemorragique() {
        return explorationSyndromeHemorragique;
    }

    public void setExplorationSyndromeHemorragique(Boolean explorationSyndromeHemorragique) {
        this.explorationSyndromeHemorragique = explorationSyndromeHemorragique;
    }

    public ExplorationType getTypeExplorationSyndromeHemorragique() {
        return typeExplorationSyndromeHemorragique;
    }

    public void setTypeExplorationSyndromeHemorragique(ExplorationType typeExplorationSyndromeHemorragique) {
        this.typeExplorationSyndromeHemorragique = typeExplorationSyndromeHemorragique;
    }

    public LocalDate getDatePremierSigneClinique() {
        return datePremierSigneClinique;
    }

    public void setDatePremierSigneClinique(LocalDate datePremierSigneClinique) {
        this.datePremierSigneClinique = datePremierSigneClinique;
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

    public Float getTp() {
        return tp;
    }

    public void setTp(Float tp) {
        this.tp = tp;
    }

    public Float getTpMT() {
        return tpMT;
    }

    public void setTpMT(Float tpMT) {
        this.tpMT = tpMT;
    }

    public Float getTca() {
        return tca;
    }

    public void setTca(Float tca) {
        this.tca = tca;
    }

    public Float getTcaMT() {
        return tcaMT;
    }

    public void setTcaMT(Float tcaMT) {
        this.tcaMT = tcaMT;
    }

    public Float getFibrinogene() {
        return fibrinogene;
    }

    public void setFibrinogene(Float fibrinogene) {
        this.fibrinogene = fibrinogene;
    }

    public FaiteNonFaite getTs() {
        return ts;
    }

    public void setTs(FaiteNonFaite ts) {
        this.ts = ts;
    }

    public NormalAllonge getTsFait() {
        return tsFait;
    }

    public void setTsFait(NormalAllonge tsFait) {
        this.tsFait = tsFait;
    }

    public NormalAllonge getToPfa() {
        return toPfa;
    }

    public void setToPfa(NormalAllonge toPfa) {
        this.toPfa = toPfa;
    }

    public Float getToPfa200ColEpi() {
        return toPfa200ColEpi;
    }

    public void setToPfa200ColEpi(Float toPfa200ColEpi) {
        this.toPfa200ColEpi = toPfa200ColEpi;
    }

    public Float getToPfa200ColAdp() {
        return toPfa200ColAdp;
    }

    public void setToPfa200ColAdp(Float toPfa200ColAdp) {
        this.toPfa200ColAdp = toPfa200ColAdp;
    }

    public Float getTauxDosageFacteurXIII() {
        return tauxDosageFacteurXIII;
    }

    public void setTauxDosageFacteurXIII(Float tauxDosageFacteurXIII) {
        this.tauxDosageFacteurXIII = tauxDosageFacteurXIII;
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

    public Boolean getTechniqueChromogenique() {
        return techniqueChromogenique;
    }

    public void setTechniqueChromogenique(Boolean techniqueChromogenique) {
        this.techniqueChromogenique = techniqueChromogenique;
    }

    public Float getTauxTechniqueChromogenique() {
        return tauxTechniqueChromogenique;
    }

    public void setTauxTechniqueChromogenique(Float tauxTechniqueChromogenique) {
        this.tauxTechniqueChromogenique = tauxTechniqueChromogenique;
    }

    public Boolean getTechniqueSolubiliteCaillot() {
        return techniqueSolubiliteCaillot;
    }

    public void setTechniqueSolubiliteCaillot(Boolean techniqueSolubiliteCaillot) {
        this.techniqueSolubiliteCaillot = techniqueSolubiliteCaillot;
    }

    public Float getTauxTechniqueSolubiliteCaillot() {
        return tauxTechniqueSolubiliteCaillot;
    }

    public void setTauxTechniqueSolubiliteCaillot(Float tauxTechniqueSolubiliteCaillot) {
        this.tauxTechniqueSolubiliteCaillot = tauxTechniqueSolubiliteCaillot;
    }

    public FaiteNonFaite getConfirmationMoleculaire() {
        return confirmationMoleculaire;
    }

    public void setConfirmationMoleculaire(FaiteNonFaite confirmationMoleculaire) {
        this.confirmationMoleculaire = confirmationMoleculaire;
    }

    public Boolean getMutationPresence() {
        return mutationPresence;
    }

    public void setMutationPresence(Boolean mutationPresence) {
        this.mutationPresence = mutationPresence;
    }

    public MutationType getMutationType() {
        return mutationType;
    }

    public void setMutationType(MutationType mutationType) {
        this.mutationType = mutationType;
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

    public TypeAccidentsThrombotiques getTypeAccidentsThrombotiques() {
        return typeAccidentsThrombotiques;
    }

    public void setTypeAccidentsThrombotiques(TypeAccidentsThrombotiques typeAccidentsThrombotiques) {
        this.typeAccidentsThrombotiques = typeAccidentsThrombotiques;
    }

    public Boolean getHemorragieGrave() {
        return hemorragieGrave;
    }

    public void setHemorragieGrave(Boolean hemorragieGrave) {
        this.hemorragieGrave = hemorragieGrave;
    }

    public TypeHemorragieGrave getTypeHemorragieGrave() {
        return typeHemorragieGrave;
    }

    public void setTypeHemorragieGrave(TypeHemorragieGrave typeHemorragieGrave) {
        this.typeHemorragieGrave = typeHemorragieGrave;
    }

    public OuiNonNP getChirurgiePrepare() {
        return chirurgiePrepare;
    }

    public void setChirurgiePrepare(OuiNonNP chirurgiePrepare) {
        this.chirurgiePrepare = chirurgiePrepare;
    }

    public OuiNonNP getSuiteOperatoireHemorragique() {
        return suiteOperatoireHemorragique;
    }

    public void setSuiteOperatoireHemorragique(OuiNonNP suiteOperatoireHemorragique) {
        this.suiteOperatoireHemorragique = suiteOperatoireHemorragique;
    }

    public OuiNonNP getCirconcisionPrepare() {
        return circoncisionPrepare;
    }

    public void setCirconcisionPrepare(OuiNonNP circoncisionPrepare) {
        this.circoncisionPrepare = circoncisionPrepare;
    }

    public OuiNonNP getSuiteOperatoireCirconcision() {
        return suiteOperatoireCirconcision;
    }

    public void setSuiteOperatoireCirconcision(OuiNonNP suiteOperatoireCirconcision) {
        this.suiteOperatoireCirconcision = suiteOperatoireCirconcision;
    }

    public Integer getAgeCirconcision() {
        return ageCirconcision;
    }

    public void setAgeCirconcision(Integer ageCirconcision) {
        this.ageCirconcision = ageCirconcision;
    }

    public OuiNonNP getFacteurVIIIRecombinant() {
        return facteurVIIIRecombinant;
    }

    public void setFacteurVIIIRecombinant(OuiNonNP facteurVIIIRecombinant) {
        this.facteurVIIIRecombinant = facteurVIIIRecombinant;
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

    public Float getTitreUB() {
        return titreUB;
    }

    public void setTitreUB(Float titreUB) {
        this.titreUB = titreUB;
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

    public OuiNonNP getThrombose() {
        return thrombose;
    }

    public void setThrombose(OuiNonNP thrombose) {
        this.thrombose = thrombose;
    }

    public OuiNonNP getReactionAllergique() {
        return reactionAllergique;
    }

    public void setReactionAllergique(OuiNonNP reactionAllergique) {
        this.reactionAllergique = reactionAllergique;
    }

    public OuiNonNP getAlloImmunisation() {
        return alloImmunisation;
    }

    public void setAlloImmunisation(OuiNonNP alloImmunisation) {
        this.alloImmunisation = alloImmunisation;
    }

    public OuiNonNP getAdhesionTherapeutique() {
        return adhesionTherapeutique;
    }

    public void setAdhesionTherapeutique(OuiNonNP adhesionTherapeutique) {
        this.adhesionTherapeutique = adhesionTherapeutique;
    }

    public String getCauseNonAdhesion() {
        return causeNonAdhesion;
    }

    public void setCauseNonAdhesion(String causeNonAdhesion) {
        this.causeNonAdhesion = causeNonAdhesion;
    }

    public VieSocialeType getVieSociale() {
        return vieSociale;
    }

    public void setVieSociale(VieSocialeType vieSociale) {
        this.vieSociale = vieSociale;
    }

    public OuiNonNP getMariage() {
        return mariage;
    }

    public void setMariage(OuiNonNP mariage) {
        this.mariage = mariage;
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

    public Boolean getHemorragieSnc() {
        return hemorragieSnc;
    }

    public void setHemorragieSnc(Boolean hemorragieSnc) {
        this.hemorragieSnc = hemorragieSnc;
    }

    public Boolean getHemorragieAutreQueSnc() {
        return hemorragieAutreQueSnc;
    }

    public void setHemorragieAutreQueSnc(Boolean hemorragieAutreQueSnc) {
        this.hemorragieAutreQueSnc = hemorragieAutreQueSnc;
    }

    public Boolean getHiv() {
        return hiv;
    }

    public void setHiv(Boolean hiv) {
        this.hiv = hiv;
    }

    public Boolean getAutreInfection() {
        return autreInfection;
    }

    public void setAutreInfection(Boolean autreInfection) {
        this.autreInfection = autreInfection;
    }

    public Boolean getEvenementThromboembolique() {
        return evenementThromboembolique;
    }

    public void setEvenementThromboembolique(Boolean evenementThromboembolique) {
        this.evenementThromboembolique = evenementThromboembolique;
    }

    public Boolean getCancer() {
        return cancer;
    }

    public void setCancer(Boolean cancer) {
        this.cancer = cancer;
    }

    public Boolean getCovid19() {
        return covid19;
    }

    public void setCovid19(Boolean covid19) {
        this.covid19 = covid19;
    }

    public Boolean getInconnue() {
        return inconnue;
    }

    public void setInconnue(Boolean inconnue) {
        this.inconnue = inconnue;
    }

    public Boolean getAutreEvenement() {
        return autreEvenement;
    }

    public void setAutreEvenement(Boolean autreEvenement) {
        this.autreEvenement = autreEvenement;
    }

    public String getAutreEvenementDetails() {
        return autreEvenementDetails;
    }

    public void setAutreEvenementDetails(String autreEvenementDetails) {
        this.autreEvenementDetails = autreEvenementDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheFacteurVIIIDTO)) {
            return false;
        }

        FicheFacteurVIIIDTO ficheFacteurVIIIDTO = (FicheFacteurVIIIDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ficheFacteurVIIIDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheFacteurVIIIDTO{" +
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

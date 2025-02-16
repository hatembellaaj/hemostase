package tn.adhes.hemostase.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import tn.adhes.hemostase.domain.enumeration.CivilStatus;
import tn.adhes.hemostase.domain.enumeration.CouvertureSociale;
import tn.adhes.hemostase.domain.enumeration.DiagnosticType;
import tn.adhes.hemostase.domain.enumeration.FaiteNonFaite;
import tn.adhes.hemostase.domain.enumeration.Gender;
import tn.adhes.hemostase.domain.enumeration.OuiNonNP;
import tn.adhes.hemostase.domain.enumeration.Region;
import tn.adhes.hemostase.domain.enumeration.ServiceCliniqueType;
import tn.adhes.hemostase.domain.enumeration.TypeManifestation;

/**
 * A DTO for the {@link tn.adhes.hemostase.domain.FicheThrombopathie} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheThrombopathieDTO implements Serializable {

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

    @Size(max = 25)
    private String degreParenteConsanguins;

    private OuiNonNP casSimilaires;

    private Integer nombreCas;

    @Size(max = 25)
    private String degreParenteCasSimilaires;

    private OuiNonNP decesSyndromeHemorragique;

    private Integer nombreDeces;

    @Size(max = 255)
    private String typeHemorragie;

    @Size(max = 255)
    private String lienParenteDeces;

    private FaiteNonFaite enqueteFamiliale;

    @Size(max = 255)
    private String antecedentsMedicaux;

    private OuiNonNP antecedentsChirurgicaux;

    @Size(max = 255)
    private String typeChirurgie;

    private OuiNonNP complicationHemorragique;

    private Integer agePremierSigneHemorragique;

    @Size(max = 255)
    private String typeSyndromeHemorragique;

    private Integer ageDiagnostic;

    private Boolean decouverteFortuite;

    private Boolean enqueteFamilialeBool;

    private Boolean explorationSyndromeHemorragique;

    @Size(max = 255)
    private String typeCirconstanceDiagnostic;

    private OuiNonNP eczema;

    private OuiNonNP syndromeDysmorphique;

    private OuiNonNP albinisme;

    private OuiNonNP arthrogrypose;

    private Integer gb;

    private Float hb;

    private Float vgm;

    private Float tcmh;

    private Integer plaquettes;

    @Size(max = 255)
    private String frottisSanguin;

    private Float ts;

    private Float toPfa;

    private Float toPfa100ColEpi;

    private Float toPfa100ColAdp;

    private Float tp;

    private Float tca;

    private OuiNonNP aggregometriePlaquettaire;

    @Size(max = 255)
    private String resultatAgregometrie;

    private OuiNonNP cmf;

    @Size(max = 255)
    private String resultatCmf;

    private Float dosageFvwAg;

    private Float dosageFvwRco;

    private OuiNonNP etudeGenetique;

    @Size(max = 255)
    private String resultatEtudeGenetique;

    private Boolean thrombasthenieGlanzmann;

    private Boolean maladieBernardSoulier;

    private Boolean autreThrombopathie;

    @Size(max = 255)
    private String autreThrombopathieDetails;

    private OuiNonNP ecchymose;

    private TypeManifestation typeEcchymose;

    private OuiNonNP gingivorragies;

    private TypeManifestation typeGingivorragies;

    private OuiNonNP epistaxis;

    private TypeManifestation typeEpistaxis;

    private OuiNonNP hematome;

    private TypeManifestation typeHematome;

    @Size(max = 255)
    private String siegelHematome;

    private OuiNonNP menoMetrorragies;

    private Integer frequenceMenoMetrorragies;

    @Size(max = 255)
    private String severiteMenoMetrorragies;

    private OuiNonNP explorationMenoMetrorragies;

    @Size(max = 255)
    private String typeLesionSousJacente;

    private OuiNonNP hemorragieVisceres;

    private Boolean hemorragieDigestiveHaute;

    private Boolean hemorragieDigestiveBasse;

    private Boolean hematurie;

    private OuiNonNP explorationHemorragieVisceres;

    @Size(max = 255)
    private String lesionSousJacenteVisceres;

    private OuiNonNP interventionChirurgicale;

    @Size(max = 255)
    private String typeInterventionChirurgicale;

    private Boolean hematomesCerebrales;

    private Boolean edcHemorragique;

    private OuiNonNP transfusionCps;

    private OuiNonNP facteurVIIRecombinant;

    private OuiNonNP dicynone;

    private OuiNonNP exacyl;

    private OuiNonNP traitementOestroprogestatif;

    private OuiNonNP mechage;

    private OuiNonNP transfusionCgr;

    private OuiNonNP traitementMartial;

    private OuiNonNP thrombose;

    private OuiNonNP alloImmunisationHLA;

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

    public String getDegreParenteCasSimilaires() {
        return degreParenteCasSimilaires;
    }

    public void setDegreParenteCasSimilaires(String degreParenteCasSimilaires) {
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

    public String getTypeHemorragie() {
        return typeHemorragie;
    }

    public void setTypeHemorragie(String typeHemorragie) {
        this.typeHemorragie = typeHemorragie;
    }

    public String getLienParenteDeces() {
        return lienParenteDeces;
    }

    public void setLienParenteDeces(String lienParenteDeces) {
        this.lienParenteDeces = lienParenteDeces;
    }

    public FaiteNonFaite getEnqueteFamiliale() {
        return enqueteFamiliale;
    }

    public void setEnqueteFamiliale(FaiteNonFaite enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public String getAntecedentsMedicaux() {
        return antecedentsMedicaux;
    }

    public void setAntecedentsMedicaux(String antecedentsMedicaux) {
        this.antecedentsMedicaux = antecedentsMedicaux;
    }

    public OuiNonNP getAntecedentsChirurgicaux() {
        return antecedentsChirurgicaux;
    }

    public void setAntecedentsChirurgicaux(OuiNonNP antecedentsChirurgicaux) {
        this.antecedentsChirurgicaux = antecedentsChirurgicaux;
    }

    public String getTypeChirurgie() {
        return typeChirurgie;
    }

    public void setTypeChirurgie(String typeChirurgie) {
        this.typeChirurgie = typeChirurgie;
    }

    public OuiNonNP getComplicationHemorragique() {
        return complicationHemorragique;
    }

    public void setComplicationHemorragique(OuiNonNP complicationHemorragique) {
        this.complicationHemorragique = complicationHemorragique;
    }

    public Integer getAgePremierSigneHemorragique() {
        return agePremierSigneHemorragique;
    }

    public void setAgePremierSigneHemorragique(Integer agePremierSigneHemorragique) {
        this.agePremierSigneHemorragique = agePremierSigneHemorragique;
    }

    public String getTypeSyndromeHemorragique() {
        return typeSyndromeHemorragique;
    }

    public void setTypeSyndromeHemorragique(String typeSyndromeHemorragique) {
        this.typeSyndromeHemorragique = typeSyndromeHemorragique;
    }

    public Integer getAgeDiagnostic() {
        return ageDiagnostic;
    }

    public void setAgeDiagnostic(Integer ageDiagnostic) {
        this.ageDiagnostic = ageDiagnostic;
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

    public String getTypeCirconstanceDiagnostic() {
        return typeCirconstanceDiagnostic;
    }

    public void setTypeCirconstanceDiagnostic(String typeCirconstanceDiagnostic) {
        this.typeCirconstanceDiagnostic = typeCirconstanceDiagnostic;
    }

    public OuiNonNP getEczema() {
        return eczema;
    }

    public void setEczema(OuiNonNP eczema) {
        this.eczema = eczema;
    }

    public OuiNonNP getSyndromeDysmorphique() {
        return syndromeDysmorphique;
    }

    public void setSyndromeDysmorphique(OuiNonNP syndromeDysmorphique) {
        this.syndromeDysmorphique = syndromeDysmorphique;
    }

    public OuiNonNP getAlbinisme() {
        return albinisme;
    }

    public void setAlbinisme(OuiNonNP albinisme) {
        this.albinisme = albinisme;
    }

    public OuiNonNP getArthrogrypose() {
        return arthrogrypose;
    }

    public void setArthrogrypose(OuiNonNP arthrogrypose) {
        this.arthrogrypose = arthrogrypose;
    }

    public Integer getGb() {
        return gb;
    }

    public void setGb(Integer gb) {
        this.gb = gb;
    }

    public Float getHb() {
        return hb;
    }

    public void setHb(Float hb) {
        this.hb = hb;
    }

    public Float getVgm() {
        return vgm;
    }

    public void setVgm(Float vgm) {
        this.vgm = vgm;
    }

    public Float getTcmh() {
        return tcmh;
    }

    public void setTcmh(Float tcmh) {
        this.tcmh = tcmh;
    }

    public Integer getPlaquettes() {
        return plaquettes;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public String getFrottisSanguin() {
        return frottisSanguin;
    }

    public void setFrottisSanguin(String frottisSanguin) {
        this.frottisSanguin = frottisSanguin;
    }

    public Float getTs() {
        return ts;
    }

    public void setTs(Float ts) {
        this.ts = ts;
    }

    public Float getToPfa() {
        return toPfa;
    }

    public void setToPfa(Float toPfa) {
        this.toPfa = toPfa;
    }

    public Float getToPfa100ColEpi() {
        return toPfa100ColEpi;
    }

    public void setToPfa100ColEpi(Float toPfa100ColEpi) {
        this.toPfa100ColEpi = toPfa100ColEpi;
    }

    public Float getToPfa100ColAdp() {
        return toPfa100ColAdp;
    }

    public void setToPfa100ColAdp(Float toPfa100ColAdp) {
        this.toPfa100ColAdp = toPfa100ColAdp;
    }

    public Float getTp() {
        return tp;
    }

    public void setTp(Float tp) {
        this.tp = tp;
    }

    public Float getTca() {
        return tca;
    }

    public void setTca(Float tca) {
        this.tca = tca;
    }

    public OuiNonNP getAggregometriePlaquettaire() {
        return aggregometriePlaquettaire;
    }

    public void setAggregometriePlaquettaire(OuiNonNP aggregometriePlaquettaire) {
        this.aggregometriePlaquettaire = aggregometriePlaquettaire;
    }

    public String getResultatAgregometrie() {
        return resultatAgregometrie;
    }

    public void setResultatAgregometrie(String resultatAgregometrie) {
        this.resultatAgregometrie = resultatAgregometrie;
    }

    public OuiNonNP getCmf() {
        return cmf;
    }

    public void setCmf(OuiNonNP cmf) {
        this.cmf = cmf;
    }

    public String getResultatCmf() {
        return resultatCmf;
    }

    public void setResultatCmf(String resultatCmf) {
        this.resultatCmf = resultatCmf;
    }

    public Float getDosageFvwAg() {
        return dosageFvwAg;
    }

    public void setDosageFvwAg(Float dosageFvwAg) {
        this.dosageFvwAg = dosageFvwAg;
    }

    public Float getDosageFvwRco() {
        return dosageFvwRco;
    }

    public void setDosageFvwRco(Float dosageFvwRco) {
        this.dosageFvwRco = dosageFvwRco;
    }

    public OuiNonNP getEtudeGenetique() {
        return etudeGenetique;
    }

    public void setEtudeGenetique(OuiNonNP etudeGenetique) {
        this.etudeGenetique = etudeGenetique;
    }

    public String getResultatEtudeGenetique() {
        return resultatEtudeGenetique;
    }

    public void setResultatEtudeGenetique(String resultatEtudeGenetique) {
        this.resultatEtudeGenetique = resultatEtudeGenetique;
    }

    public Boolean getThrombasthenieGlanzmann() {
        return thrombasthenieGlanzmann;
    }

    public void setThrombasthenieGlanzmann(Boolean thrombasthenieGlanzmann) {
        this.thrombasthenieGlanzmann = thrombasthenieGlanzmann;
    }

    public Boolean getMaladieBernardSoulier() {
        return maladieBernardSoulier;
    }

    public void setMaladieBernardSoulier(Boolean maladieBernardSoulier) {
        this.maladieBernardSoulier = maladieBernardSoulier;
    }

    public Boolean getAutreThrombopathie() {
        return autreThrombopathie;
    }

    public void setAutreThrombopathie(Boolean autreThrombopathie) {
        this.autreThrombopathie = autreThrombopathie;
    }

    public String getAutreThrombopathieDetails() {
        return autreThrombopathieDetails;
    }

    public void setAutreThrombopathieDetails(String autreThrombopathieDetails) {
        this.autreThrombopathieDetails = autreThrombopathieDetails;
    }

    public OuiNonNP getEcchymose() {
        return ecchymose;
    }

    public void setEcchymose(OuiNonNP ecchymose) {
        this.ecchymose = ecchymose;
    }

    public TypeManifestation getTypeEcchymose() {
        return typeEcchymose;
    }

    public void setTypeEcchymose(TypeManifestation typeEcchymose) {
        this.typeEcchymose = typeEcchymose;
    }

    public OuiNonNP getGingivorragies() {
        return gingivorragies;
    }

    public void setGingivorragies(OuiNonNP gingivorragies) {
        this.gingivorragies = gingivorragies;
    }

    public TypeManifestation getTypeGingivorragies() {
        return typeGingivorragies;
    }

    public void setTypeGingivorragies(TypeManifestation typeGingivorragies) {
        this.typeGingivorragies = typeGingivorragies;
    }

    public OuiNonNP getEpistaxis() {
        return epistaxis;
    }

    public void setEpistaxis(OuiNonNP epistaxis) {
        this.epistaxis = epistaxis;
    }

    public TypeManifestation getTypeEpistaxis() {
        return typeEpistaxis;
    }

    public void setTypeEpistaxis(TypeManifestation typeEpistaxis) {
        this.typeEpistaxis = typeEpistaxis;
    }

    public OuiNonNP getHematome() {
        return hematome;
    }

    public void setHematome(OuiNonNP hematome) {
        this.hematome = hematome;
    }

    public TypeManifestation getTypeHematome() {
        return typeHematome;
    }

    public void setTypeHematome(TypeManifestation typeHematome) {
        this.typeHematome = typeHematome;
    }

    public String getSiegelHematome() {
        return siegelHematome;
    }

    public void setSiegelHematome(String siegelHematome) {
        this.siegelHematome = siegelHematome;
    }

    public OuiNonNP getMenoMetrorragies() {
        return menoMetrorragies;
    }

    public void setMenoMetrorragies(OuiNonNP menoMetrorragies) {
        this.menoMetrorragies = menoMetrorragies;
    }

    public Integer getFrequenceMenoMetrorragies() {
        return frequenceMenoMetrorragies;
    }

    public void setFrequenceMenoMetrorragies(Integer frequenceMenoMetrorragies) {
        this.frequenceMenoMetrorragies = frequenceMenoMetrorragies;
    }

    public String getSeveriteMenoMetrorragies() {
        return severiteMenoMetrorragies;
    }

    public void setSeveriteMenoMetrorragies(String severiteMenoMetrorragies) {
        this.severiteMenoMetrorragies = severiteMenoMetrorragies;
    }

    public OuiNonNP getExplorationMenoMetrorragies() {
        return explorationMenoMetrorragies;
    }

    public void setExplorationMenoMetrorragies(OuiNonNP explorationMenoMetrorragies) {
        this.explorationMenoMetrorragies = explorationMenoMetrorragies;
    }

    public String getTypeLesionSousJacente() {
        return typeLesionSousJacente;
    }

    public void setTypeLesionSousJacente(String typeLesionSousJacente) {
        this.typeLesionSousJacente = typeLesionSousJacente;
    }

    public OuiNonNP getHemorragieVisceres() {
        return hemorragieVisceres;
    }

    public void setHemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.hemorragieVisceres = hemorragieVisceres;
    }

    public Boolean getHemorragieDigestiveHaute() {
        return hemorragieDigestiveHaute;
    }

    public void setHemorragieDigestiveHaute(Boolean hemorragieDigestiveHaute) {
        this.hemorragieDigestiveHaute = hemorragieDigestiveHaute;
    }

    public Boolean getHemorragieDigestiveBasse() {
        return hemorragieDigestiveBasse;
    }

    public void setHemorragieDigestiveBasse(Boolean hemorragieDigestiveBasse) {
        this.hemorragieDigestiveBasse = hemorragieDigestiveBasse;
    }

    public Boolean getHematurie() {
        return hematurie;
    }

    public void setHematurie(Boolean hematurie) {
        this.hematurie = hematurie;
    }

    public OuiNonNP getExplorationHemorragieVisceres() {
        return explorationHemorragieVisceres;
    }

    public void setExplorationHemorragieVisceres(OuiNonNP explorationHemorragieVisceres) {
        this.explorationHemorragieVisceres = explorationHemorragieVisceres;
    }

    public String getLesionSousJacenteVisceres() {
        return lesionSousJacenteVisceres;
    }

    public void setLesionSousJacenteVisceres(String lesionSousJacenteVisceres) {
        this.lesionSousJacenteVisceres = lesionSousJacenteVisceres;
    }

    public OuiNonNP getInterventionChirurgicale() {
        return interventionChirurgicale;
    }

    public void setInterventionChirurgicale(OuiNonNP interventionChirurgicale) {
        this.interventionChirurgicale = interventionChirurgicale;
    }

    public String getTypeInterventionChirurgicale() {
        return typeInterventionChirurgicale;
    }

    public void setTypeInterventionChirurgicale(String typeInterventionChirurgicale) {
        this.typeInterventionChirurgicale = typeInterventionChirurgicale;
    }

    public Boolean getHematomesCerebrales() {
        return hematomesCerebrales;
    }

    public void setHematomesCerebrales(Boolean hematomesCerebrales) {
        this.hematomesCerebrales = hematomesCerebrales;
    }

    public Boolean getEdcHemorragique() {
        return edcHemorragique;
    }

    public void setEdcHemorragique(Boolean edcHemorragique) {
        this.edcHemorragique = edcHemorragique;
    }

    public OuiNonNP getTransfusionCps() {
        return transfusionCps;
    }

    public void setTransfusionCps(OuiNonNP transfusionCps) {
        this.transfusionCps = transfusionCps;
    }

    public OuiNonNP getFacteurVIIRecombinant() {
        return facteurVIIRecombinant;
    }

    public void setFacteurVIIRecombinant(OuiNonNP facteurVIIRecombinant) {
        this.facteurVIIRecombinant = facteurVIIRecombinant;
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

    public OuiNonNP getTraitementOestroprogestatif() {
        return traitementOestroprogestatif;
    }

    public void setTraitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.traitementOestroprogestatif = traitementOestroprogestatif;
    }

    public OuiNonNP getMechage() {
        return mechage;
    }

    public void setMechage(OuiNonNP mechage) {
        this.mechage = mechage;
    }

    public OuiNonNP getTransfusionCgr() {
        return transfusionCgr;
    }

    public void setTransfusionCgr(OuiNonNP transfusionCgr) {
        this.transfusionCgr = transfusionCgr;
    }

    public OuiNonNP getTraitementMartial() {
        return traitementMartial;
    }

    public void setTraitementMartial(OuiNonNP traitementMartial) {
        this.traitementMartial = traitementMartial;
    }

    public OuiNonNP getThrombose() {
        return thrombose;
    }

    public void setThrombose(OuiNonNP thrombose) {
        this.thrombose = thrombose;
    }

    public OuiNonNP getAlloImmunisationHLA() {
        return alloImmunisationHLA;
    }

    public void setAlloImmunisationHLA(OuiNonNP alloImmunisationHLA) {
        this.alloImmunisationHLA = alloImmunisationHLA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheThrombopathieDTO)) {
            return false;
        }

        FicheThrombopathieDTO ficheThrombopathieDTO = (FicheThrombopathieDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ficheThrombopathieDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheThrombopathieDTO{" +
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
            ", degreParenteCasSimilaires='" + getDegreParenteCasSimilaires() + "'" +
            ", decesSyndromeHemorragique='" + getDecesSyndromeHemorragique() + "'" +
            ", nombreDeces=" + getNombreDeces() +
            ", typeHemorragie='" + getTypeHemorragie() + "'" +
            ", lienParenteDeces='" + getLienParenteDeces() + "'" +
            ", enqueteFamiliale='" + getEnqueteFamiliale() + "'" +
            ", antecedentsMedicaux='" + getAntecedentsMedicaux() + "'" +
            ", antecedentsChirurgicaux='" + getAntecedentsChirurgicaux() + "'" +
            ", typeChirurgie='" + getTypeChirurgie() + "'" +
            ", complicationHemorragique='" + getComplicationHemorragique() + "'" +
            ", agePremierSigneHemorragique=" + getAgePremierSigneHemorragique() +
            ", typeSyndromeHemorragique='" + getTypeSyndromeHemorragique() + "'" +
            ", ageDiagnostic=" + getAgeDiagnostic() +
            ", decouverteFortuite='" + getDecouverteFortuite() + "'" +
            ", enqueteFamilialeBool='" + getEnqueteFamilialeBool() + "'" +
            ", explorationSyndromeHemorragique='" + getExplorationSyndromeHemorragique() + "'" +
            ", typeCirconstanceDiagnostic='" + getTypeCirconstanceDiagnostic() + "'" +
            ", eczema='" + getEczema() + "'" +
            ", syndromeDysmorphique='" + getSyndromeDysmorphique() + "'" +
            ", albinisme='" + getAlbinisme() + "'" +
            ", arthrogrypose='" + getArthrogrypose() + "'" +
            ", gb=" + getGb() +
            ", hb=" + getHb() +
            ", vgm=" + getVgm() +
            ", tcmh=" + getTcmh() +
            ", plaquettes=" + getPlaquettes() +
            ", frottisSanguin='" + getFrottisSanguin() + "'" +
            ", ts=" + getTs() +
            ", toPfa=" + getToPfa() +
            ", toPfa100ColEpi=" + getToPfa100ColEpi() +
            ", toPfa100ColAdp=" + getToPfa100ColAdp() +
            ", tp=" + getTp() +
            ", tca=" + getTca() +
            ", aggregometriePlaquettaire='" + getAggregometriePlaquettaire() + "'" +
            ", resultatAgregometrie='" + getResultatAgregometrie() + "'" +
            ", cmf='" + getCmf() + "'" +
            ", resultatCmf='" + getResultatCmf() + "'" +
            ", dosageFvwAg=" + getDosageFvwAg() +
            ", dosageFvwRco=" + getDosageFvwRco() +
            ", etudeGenetique='" + getEtudeGenetique() + "'" +
            ", resultatEtudeGenetique='" + getResultatEtudeGenetique() + "'" +
            ", thrombasthenieGlanzmann='" + getThrombasthenieGlanzmann() + "'" +
            ", maladieBernardSoulier='" + getMaladieBernardSoulier() + "'" +
            ", autreThrombopathie='" + getAutreThrombopathie() + "'" +
            ", autreThrombopathieDetails='" + getAutreThrombopathieDetails() + "'" +
            ", ecchymose='" + getEcchymose() + "'" +
            ", typeEcchymose='" + getTypeEcchymose() + "'" +
            ", gingivorragies='" + getGingivorragies() + "'" +
            ", typeGingivorragies='" + getTypeGingivorragies() + "'" +
            ", epistaxis='" + getEpistaxis() + "'" +
            ", typeEpistaxis='" + getTypeEpistaxis() + "'" +
            ", hematome='" + getHematome() + "'" +
            ", typeHematome='" + getTypeHematome() + "'" +
            ", siegelHematome='" + getSiegelHematome() + "'" +
            ", menoMetrorragies='" + getMenoMetrorragies() + "'" +
            ", frequenceMenoMetrorragies=" + getFrequenceMenoMetrorragies() +
            ", severiteMenoMetrorragies='" + getSeveriteMenoMetrorragies() + "'" +
            ", explorationMenoMetrorragies='" + getExplorationMenoMetrorragies() + "'" +
            ", typeLesionSousJacente='" + getTypeLesionSousJacente() + "'" +
            ", hemorragieVisceres='" + getHemorragieVisceres() + "'" +
            ", hemorragieDigestiveHaute='" + getHemorragieDigestiveHaute() + "'" +
            ", hemorragieDigestiveBasse='" + getHemorragieDigestiveBasse() + "'" +
            ", hematurie='" + getHematurie() + "'" +
            ", explorationHemorragieVisceres='" + getExplorationHemorragieVisceres() + "'" +
            ", lesionSousJacenteVisceres='" + getLesionSousJacenteVisceres() + "'" +
            ", interventionChirurgicale='" + getInterventionChirurgicale() + "'" +
            ", typeInterventionChirurgicale='" + getTypeInterventionChirurgicale() + "'" +
            ", hematomesCerebrales='" + getHematomesCerebrales() + "'" +
            ", edcHemorragique='" + getEdcHemorragique() + "'" +
            ", transfusionCps='" + getTransfusionCps() + "'" +
            ", facteurVIIRecombinant='" + getFacteurVIIRecombinant() + "'" +
            ", dicynone='" + getDicynone() + "'" +
            ", exacyl='" + getExacyl() + "'" +
            ", traitementOestroprogestatif='" + getTraitementOestroprogestatif() + "'" +
            ", mechage='" + getMechage() + "'" +
            ", transfusionCgr='" + getTransfusionCgr() + "'" +
            ", traitementMartial='" + getTraitementMartial() + "'" +
            ", thrombose='" + getThrombose() + "'" +
            ", alloImmunisationHLA='" + getAlloImmunisationHLA() + "'" +
            "}";
    }
}

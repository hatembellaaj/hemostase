package org.adhes.hemostase.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import org.adhes.hemostase.domain.enumeration.CivilStatus;
import org.adhes.hemostase.domain.enumeration.CouvertureSociale;
import org.adhes.hemostase.domain.enumeration.DiagnosticType;
import org.adhes.hemostase.domain.enumeration.Gender;
import org.adhes.hemostase.domain.enumeration.OuiNonNP;
import org.adhes.hemostase.domain.enumeration.Region;

/**
 * A DTO for the {@link org.adhes.hemostase.domain.FicheHemophilie} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheHemophilieDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 25)
    private String prenom;

    @NotNull
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

    @NotNull
    @Size(max = 25)
    private String dossierNumber;

    @Size(max = 25)
    private String ordreNumber;

    @Size(max = 25)
    private String indexNumber;

    @Size(max = 25)
    private String degreParenteConsanguins;

    @Size(max = 25)
    private String degreParenteCasSimilaires;

    @Size(max = 25)
    private String typeHemorragiesCutaneoMuqueuses;

    @Size(max = 25)
    private String circonstanceHematomeSuperficiel;

    @Size(max = 25)
    private String siegeHematomeSuperficiel;

    @Size(max = 25)
    private String circonstanceHematomePsoas;

    @Size(max = 25)
    private String circonstanceAccidentsHemorragiquesGraves;

    @Size(max = 25)
    private String typeInterventionChirurgicale;

    @Size(max = 25)
    private String causePriseEnCharge;

    @Size(max = 25)
    private String posologieITI;

    @Size(max = 25)
    private String dureeITI;

    @Size(max = 25)
    private String causeNonObservanceITI;

    @Size(max = 25)
    private String typeHandicap;

    @Size(max = 25)
    private String typeActiviteSportive;

    @Size(max = 25)
    private String causeDateDeces;

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

    public String getDegreParenteConsanguins() {
        return degreParenteConsanguins;
    }

    public void setDegreParenteConsanguins(String degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public String getDegreParenteCasSimilaires() {
        return degreParenteCasSimilaires;
    }

    public void setDegreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.degreParenteCasSimilaires = degreParenteCasSimilaires;
    }

    public String getTypeHemorragiesCutaneoMuqueuses() {
        return typeHemorragiesCutaneoMuqueuses;
    }

    public void setTypeHemorragiesCutaneoMuqueuses(String typeHemorragiesCutaneoMuqueuses) {
        this.typeHemorragiesCutaneoMuqueuses = typeHemorragiesCutaneoMuqueuses;
    }

    public String getCirconstanceHematomeSuperficiel() {
        return circonstanceHematomeSuperficiel;
    }

    public void setCirconstanceHematomeSuperficiel(String circonstanceHematomeSuperficiel) {
        this.circonstanceHematomeSuperficiel = circonstanceHematomeSuperficiel;
    }

    public String getSiegeHematomeSuperficiel() {
        return siegeHematomeSuperficiel;
    }

    public void setSiegeHematomeSuperficiel(String siegeHematomeSuperficiel) {
        this.siegeHematomeSuperficiel = siegeHematomeSuperficiel;
    }

    public String getCirconstanceHematomePsoas() {
        return circonstanceHematomePsoas;
    }

    public void setCirconstanceHematomePsoas(String circonstanceHematomePsoas) {
        this.circonstanceHematomePsoas = circonstanceHematomePsoas;
    }

    public String getCirconstanceAccidentsHemorragiquesGraves() {
        return circonstanceAccidentsHemorragiquesGraves;
    }

    public void setCirconstanceAccidentsHemorragiquesGraves(String circonstanceAccidentsHemorragiquesGraves) {
        this.circonstanceAccidentsHemorragiquesGraves = circonstanceAccidentsHemorragiquesGraves;
    }

    public String getTypeInterventionChirurgicale() {
        return typeInterventionChirurgicale;
    }

    public void setTypeInterventionChirurgicale(String typeInterventionChirurgicale) {
        this.typeInterventionChirurgicale = typeInterventionChirurgicale;
    }

    public String getCausePriseEnCharge() {
        return causePriseEnCharge;
    }

    public void setCausePriseEnCharge(String causePriseEnCharge) {
        this.causePriseEnCharge = causePriseEnCharge;
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

    public String getCauseNonObservanceITI() {
        return causeNonObservanceITI;
    }

    public void setCauseNonObservanceITI(String causeNonObservanceITI) {
        this.causeNonObservanceITI = causeNonObservanceITI;
    }

    public String getTypeHandicap() {
        return typeHandicap;
    }

    public void setTypeHandicap(String typeHandicap) {
        this.typeHandicap = typeHandicap;
    }

    public String getTypeActiviteSportive() {
        return typeActiviteSportive;
    }

    public void setTypeActiviteSportive(String typeActiviteSportive) {
        this.typeActiviteSportive = typeActiviteSportive;
    }

    public String getCauseDateDeces() {
        return causeDateDeces;
    }

    public void setCauseDateDeces(String causeDateDeces) {
        this.causeDateDeces = causeDateDeces;
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
            ", degreParenteConsanguins='" + getDegreParenteConsanguins() + "'" +
            ", degreParenteCasSimilaires='" + getDegreParenteCasSimilaires() + "'" +
            ", typeHemorragiesCutaneoMuqueuses='" + getTypeHemorragiesCutaneoMuqueuses() + "'" +
            ", circonstanceHematomeSuperficiel='" + getCirconstanceHematomeSuperficiel() + "'" +
            ", siegeHematomeSuperficiel='" + getSiegeHematomeSuperficiel() + "'" +
            ", circonstanceHematomePsoas='" + getCirconstanceHematomePsoas() + "'" +
            ", circonstanceAccidentsHemorragiquesGraves='" + getCirconstanceAccidentsHemorragiquesGraves() + "'" +
            ", typeInterventionChirurgicale='" + getTypeInterventionChirurgicale() + "'" +
            ", causePriseEnCharge='" + getCausePriseEnCharge() + "'" +
            ", posologieITI='" + getPosologieITI() + "'" +
            ", dureeITI='" + getDureeITI() + "'" +
            ", causeNonObservanceITI='" + getCauseNonObservanceITI() + "'" +
            ", typeHandicap='" + getTypeHandicap() + "'" +
            ", typeActiviteSportive='" + getTypeActiviteSportive() + "'" +
            ", causeDateDeces='" + getCauseDateDeces() + "'" +
            "}";
    }
}

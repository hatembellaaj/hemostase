package org.adhes.hemostase.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.adhes.hemostase.domain.enumeration.CivilStatus;
import org.adhes.hemostase.domain.enumeration.CouvertureSociale;
import org.adhes.hemostase.domain.enumeration.DiagnosticType;
import org.adhes.hemostase.domain.enumeration.Gender;
import org.adhes.hemostase.domain.enumeration.OuiNonNP;
import org.adhes.hemostase.domain.enumeration.Region;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FicheHemophilie.
 */
@Entity
@Table(name = "fiche_hemophilie")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheHemophilie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 25)
    @Column(name = "prenom", length = 25, nullable = false)
    private String prenom;

    @NotNull
    @Size(max = 25)
    @Column(name = "nom", length = 25, nullable = false)
    private String nom;

    @Size(max = 25)
    @Column(name = "nom_jeune_fille", length = 25)
    private String nomJeuneFille;

    @Size(max = 25)
    @Column(name = "profession", length = 25)
    private String profession;

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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "etat_civil", nullable = false)
    private CivilStatus etatCivil;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "couverture_sociale", nullable = false)
    private CouvertureSociale couvertureSociale;

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

    @NotNull
    @Size(max = 25)
    @Column(name = "dossier_number", length = 25, nullable = false)
    private String dossierNumber;

    @Size(max = 25)
    @Column(name = "ordre_number", length = 25)
    private String ordreNumber;

    @Size(max = 25)
    @Column(name = "index_number", length = 25)
    private String indexNumber;

    @Size(max = 25)
    @Column(name = "degre_parente_consanguins", length = 25)
    private String degreParenteConsanguins;

    @Size(max = 25)
    @Column(name = "degre_parente_cas_similaires", length = 25)
    private String degreParenteCasSimilaires;

    @Size(max = 25)
    @Column(name = "type_hemorragies_cutaneo_muqueuses", length = 25)
    private String typeHemorragiesCutaneoMuqueuses;

    @Size(max = 25)
    @Column(name = "circonstance_hematome_superficiel", length = 25)
    private String circonstanceHematomeSuperficiel;

    @Size(max = 25)
    @Column(name = "siege_hematome_superficiel", length = 25)
    private String siegeHematomeSuperficiel;

    @Size(max = 25)
    @Column(name = "circonstance_hematome_psoas", length = 25)
    private String circonstanceHematomePsoas;

    @Size(max = 25)
    @Column(name = "circonstance_accidents_hemorragiques_graves", length = 25)
    private String circonstanceAccidentsHemorragiquesGraves;

    @Size(max = 25)
    @Column(name = "type_intervention_chirurgicale", length = 25)
    private String typeInterventionChirurgicale;

    @Size(max = 25)
    @Column(name = "cause_prise_en_charge", length = 25)
    private String causePriseEnCharge;

    @Size(max = 25)
    @Column(name = "posologie_iti", length = 25)
    private String posologieITI;

    @Size(max = 25)
    @Column(name = "duree_iti", length = 25)
    private String dureeITI;

    @Size(max = 25)
    @Column(name = "cause_non_observance_iti", length = 25)
    private String causeNonObservanceITI;

    @Size(max = 25)
    @Column(name = "type_handicap", length = 25)
    private String typeHandicap;

    @Size(max = 25)
    @Column(name = "type_activite_sportive", length = 25)
    private String typeActiviteSportive;

    @Size(max = 25)
    @Column(name = "cause_date_deces", length = 25)
    private String causeDateDeces;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FicheHemophilie id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public FicheHemophilie prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public FicheHemophilie nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomJeuneFille() {
        return this.nomJeuneFille;
    }

    public FicheHemophilie nomJeuneFille(String nomJeuneFille) {
        this.setNomJeuneFille(nomJeuneFille);
        return this;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public String getProfession() {
        return this.profession;
    }

    public FicheHemophilie profession(String profession) {
        this.setProfession(profession);
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Gender getSexe() {
        return this.sexe;
    }

    public FicheHemophilie sexe(Gender sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Gender sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public FicheHemophilie dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Integer getAgeActuel() {
        return this.ageActuel;
    }

    public FicheHemophilie ageActuel(Integer ageActuel) {
        this.setAgeActuel(ageActuel);
        return this;
    }

    public void setAgeActuel(Integer ageActuel) {
        this.ageActuel = ageActuel;
    }

    public Region getOrigine() {
        return this.origine;
    }

    public FicheHemophilie origine(Region origine) {
        this.setOrigine(origine);
        return this;
    }

    public void setOrigine(Region origine) {
        this.origine = origine;
    }

    public String getAutreOrigine() {
        return this.autreOrigine;
    }

    public FicheHemophilie autreOrigine(String autreOrigine) {
        this.setAutreOrigine(autreOrigine);
        return this;
    }

    public void setAutreOrigine(String autreOrigine) {
        this.autreOrigine = autreOrigine;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public FicheHemophilie adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public FicheHemophilie telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public CivilStatus getEtatCivil() {
        return this.etatCivil;
    }

    public FicheHemophilie etatCivil(CivilStatus etatCivil) {
        this.setEtatCivil(etatCivil);
        return this;
    }

    public void setEtatCivil(CivilStatus etatCivil) {
        this.etatCivil = etatCivil;
    }

    public CouvertureSociale getCouvertureSociale() {
        return this.couvertureSociale;
    }

    public FicheHemophilie couvertureSociale(CouvertureSociale couvertureSociale) {
        this.setCouvertureSociale(couvertureSociale);
        return this;
    }

    public void setCouvertureSociale(CouvertureSociale couvertureSociale) {
        this.couvertureSociale = couvertureSociale;
    }

    public String getPrenomPere() {
        return this.prenomPere;
    }

    public FicheHemophilie prenomPere(String prenomPere) {
        this.setPrenomPere(prenomPere);
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getProfessionPere() {
        return this.professionPere;
    }

    public FicheHemophilie professionPere(String professionPere) {
        this.setProfessionPere(professionPere);
        return this;
    }

    public void setProfessionPere(String professionPere) {
        this.professionPere = professionPere;
    }

    public String getNomPrenomMere() {
        return this.nomPrenomMere;
    }

    public FicheHemophilie nomPrenomMere(String nomPrenomMere) {
        this.setNomPrenomMere(nomPrenomMere);
        return this;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }

    public String getProfessionMere() {
        return this.professionMere;
    }

    public FicheHemophilie professionMere(String professionMere) {
        this.setProfessionMere(professionMere);
        return this;
    }

    public void setProfessionMere(String professionMere) {
        this.professionMere = professionMere;
    }

    public String getMedecinTraitant() {
        return this.medecinTraitant;
    }

    public FicheHemophilie medecinTraitant(String medecinTraitant) {
        this.setMedecinTraitant(medecinTraitant);
        return this;
    }

    public void setMedecinTraitant(String medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }

    public String getServiceOrigine() {
        return this.serviceOrigine;
    }

    public FicheHemophilie serviceOrigine(String serviceOrigine) {
        this.setServiceOrigine(serviceOrigine);
        return this;
    }

    public void setServiceOrigine(String serviceOrigine) {
        this.serviceOrigine = serviceOrigine;
    }

    public String getBiologisteResponsableDg() {
        return this.biologisteResponsableDg;
    }

    public FicheHemophilie biologisteResponsableDg(String biologisteResponsableDg) {
        this.setBiologisteResponsableDg(biologisteResponsableDg);
        return this;
    }

    public void setBiologisteResponsableDg(String biologisteResponsableDg) {
        this.biologisteResponsableDg = biologisteResponsableDg;
    }

    public DiagnosticType getDiagnostic() {
        return this.diagnostic;
    }

    public FicheHemophilie diagnostic(DiagnosticType diagnostic) {
        this.setDiagnostic(diagnostic);
        return this;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return this.autreDiagnostic;
    }

    public FicheHemophilie autreDiagnostic(String autreDiagnostic) {
        this.setAutreDiagnostic(autreDiagnostic);
        return this;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public Integer getAnneeDiagnostic() {
        return this.anneeDiagnostic;
    }

    public FicheHemophilie anneeDiagnostic(Integer anneeDiagnostic) {
        this.setAnneeDiagnostic(anneeDiagnostic);
        return this;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public OuiNonNP getConsentementRegistre() {
        return this.consentementRegistre;
    }

    public FicheHemophilie consentementRegistre(OuiNonNP consentementRegistre) {
        this.setConsentementRegistre(consentementRegistre);
        return this;
    }

    public void setConsentementRegistre(OuiNonNP consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return this.dateEnregistrementRegistre;
    }

    public FicheHemophilie dateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.setDateEnregistrementRegistre(dateEnregistrementRegistre);
        return this;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
    }

    public String getDossierNumber() {
        return this.dossierNumber;
    }

    public FicheHemophilie dossierNumber(String dossierNumber) {
        this.setDossierNumber(dossierNumber);
        return this;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return this.ordreNumber;
    }

    public FicheHemophilie ordreNumber(String ordreNumber) {
        this.setOrdreNumber(ordreNumber);
        return this;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return this.indexNumber;
    }

    public FicheHemophilie indexNumber(String indexNumber) {
        this.setIndexNumber(indexNumber);
        return this;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getDegreParenteConsanguins() {
        return this.degreParenteConsanguins;
    }

    public FicheHemophilie degreParenteConsanguins(String degreParenteConsanguins) {
        this.setDegreParenteConsanguins(degreParenteConsanguins);
        return this;
    }

    public void setDegreParenteConsanguins(String degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public String getDegreParenteCasSimilaires() {
        return this.degreParenteCasSimilaires;
    }

    public FicheHemophilie degreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.setDegreParenteCasSimilaires(degreParenteCasSimilaires);
        return this;
    }

    public void setDegreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.degreParenteCasSimilaires = degreParenteCasSimilaires;
    }

    public String getTypeHemorragiesCutaneoMuqueuses() {
        return this.typeHemorragiesCutaneoMuqueuses;
    }

    public FicheHemophilie typeHemorragiesCutaneoMuqueuses(String typeHemorragiesCutaneoMuqueuses) {
        this.setTypeHemorragiesCutaneoMuqueuses(typeHemorragiesCutaneoMuqueuses);
        return this;
    }

    public void setTypeHemorragiesCutaneoMuqueuses(String typeHemorragiesCutaneoMuqueuses) {
        this.typeHemorragiesCutaneoMuqueuses = typeHemorragiesCutaneoMuqueuses;
    }

    public String getCirconstanceHematomeSuperficiel() {
        return this.circonstanceHematomeSuperficiel;
    }

    public FicheHemophilie circonstanceHematomeSuperficiel(String circonstanceHematomeSuperficiel) {
        this.setCirconstanceHematomeSuperficiel(circonstanceHematomeSuperficiel);
        return this;
    }

    public void setCirconstanceHematomeSuperficiel(String circonstanceHematomeSuperficiel) {
        this.circonstanceHematomeSuperficiel = circonstanceHematomeSuperficiel;
    }

    public String getSiegeHematomeSuperficiel() {
        return this.siegeHematomeSuperficiel;
    }

    public FicheHemophilie siegeHematomeSuperficiel(String siegeHematomeSuperficiel) {
        this.setSiegeHematomeSuperficiel(siegeHematomeSuperficiel);
        return this;
    }

    public void setSiegeHematomeSuperficiel(String siegeHematomeSuperficiel) {
        this.siegeHematomeSuperficiel = siegeHematomeSuperficiel;
    }

    public String getCirconstanceHematomePsoas() {
        return this.circonstanceHematomePsoas;
    }

    public FicheHemophilie circonstanceHematomePsoas(String circonstanceHematomePsoas) {
        this.setCirconstanceHematomePsoas(circonstanceHematomePsoas);
        return this;
    }

    public void setCirconstanceHematomePsoas(String circonstanceHematomePsoas) {
        this.circonstanceHematomePsoas = circonstanceHematomePsoas;
    }

    public String getCirconstanceAccidentsHemorragiquesGraves() {
        return this.circonstanceAccidentsHemorragiquesGraves;
    }

    public FicheHemophilie circonstanceAccidentsHemorragiquesGraves(String circonstanceAccidentsHemorragiquesGraves) {
        this.setCirconstanceAccidentsHemorragiquesGraves(circonstanceAccidentsHemorragiquesGraves);
        return this;
    }

    public void setCirconstanceAccidentsHemorragiquesGraves(String circonstanceAccidentsHemorragiquesGraves) {
        this.circonstanceAccidentsHemorragiquesGraves = circonstanceAccidentsHemorragiquesGraves;
    }

    public String getTypeInterventionChirurgicale() {
        return this.typeInterventionChirurgicale;
    }

    public FicheHemophilie typeInterventionChirurgicale(String typeInterventionChirurgicale) {
        this.setTypeInterventionChirurgicale(typeInterventionChirurgicale);
        return this;
    }

    public void setTypeInterventionChirurgicale(String typeInterventionChirurgicale) {
        this.typeInterventionChirurgicale = typeInterventionChirurgicale;
    }

    public String getCausePriseEnCharge() {
        return this.causePriseEnCharge;
    }

    public FicheHemophilie causePriseEnCharge(String causePriseEnCharge) {
        this.setCausePriseEnCharge(causePriseEnCharge);
        return this;
    }

    public void setCausePriseEnCharge(String causePriseEnCharge) {
        this.causePriseEnCharge = causePriseEnCharge;
    }

    public String getPosologieITI() {
        return this.posologieITI;
    }

    public FicheHemophilie posologieITI(String posologieITI) {
        this.setPosologieITI(posologieITI);
        return this;
    }

    public void setPosologieITI(String posologieITI) {
        this.posologieITI = posologieITI;
    }

    public String getDureeITI() {
        return this.dureeITI;
    }

    public FicheHemophilie dureeITI(String dureeITI) {
        this.setDureeITI(dureeITI);
        return this;
    }

    public void setDureeITI(String dureeITI) {
        this.dureeITI = dureeITI;
    }

    public String getCauseNonObservanceITI() {
        return this.causeNonObservanceITI;
    }

    public FicheHemophilie causeNonObservanceITI(String causeNonObservanceITI) {
        this.setCauseNonObservanceITI(causeNonObservanceITI);
        return this;
    }

    public void setCauseNonObservanceITI(String causeNonObservanceITI) {
        this.causeNonObservanceITI = causeNonObservanceITI;
    }

    public String getTypeHandicap() {
        return this.typeHandicap;
    }

    public FicheHemophilie typeHandicap(String typeHandicap) {
        this.setTypeHandicap(typeHandicap);
        return this;
    }

    public void setTypeHandicap(String typeHandicap) {
        this.typeHandicap = typeHandicap;
    }

    public String getTypeActiviteSportive() {
        return this.typeActiviteSportive;
    }

    public FicheHemophilie typeActiviteSportive(String typeActiviteSportive) {
        this.setTypeActiviteSportive(typeActiviteSportive);
        return this;
    }

    public void setTypeActiviteSportive(String typeActiviteSportive) {
        this.typeActiviteSportive = typeActiviteSportive;
    }

    public String getCauseDateDeces() {
        return this.causeDateDeces;
    }

    public FicheHemophilie causeDateDeces(String causeDateDeces) {
        this.setCauseDateDeces(causeDateDeces);
        return this;
    }

    public void setCauseDateDeces(String causeDateDeces) {
        this.causeDateDeces = causeDateDeces;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheHemophilie)) {
            return false;
        }
        return getId() != null && getId().equals(((FicheHemophilie) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheHemophilie{" +
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

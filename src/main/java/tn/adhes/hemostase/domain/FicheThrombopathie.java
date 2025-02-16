package tn.adhes.hemostase.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
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
 * A FicheThrombopathie.
 */
@Entity
@Table(name = "fiche_thrombopathie")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheThrombopathie implements Serializable {

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

    @Size(max = 25)
    @Column(name = "degre_parente_consanguins", length = 25)
    private String degreParenteConsanguins;

    @Enumerated(EnumType.STRING)
    @Column(name = "cas_similaires")
    private OuiNonNP casSimilaires;

    @Column(name = "nombre_cas")
    private Integer nombreCas;

    @Size(max = 25)
    @Column(name = "degre_parente_cas_similaires", length = 25)
    private String degreParenteCasSimilaires;

    @Enumerated(EnumType.STRING)
    @Column(name = "deces_syndrome_hemorragique")
    private OuiNonNP decesSyndromeHemorragique;

    @Column(name = "nombre_deces")
    private Integer nombreDeces;

    @Size(max = 255)
    @Column(name = "type_hemorragie", length = 255)
    private String typeHemorragie;

    @Size(max = 255)
    @Column(name = "lien_parente_deces", length = 255)
    private String lienParenteDeces;

    @Enumerated(EnumType.STRING)
    @Column(name = "enquete_familiale")
    private FaiteNonFaite enqueteFamiliale;

    @Size(max = 255)
    @Column(name = "antecedents_medicaux", length = 255)
    private String antecedentsMedicaux;

    @Enumerated(EnumType.STRING)
    @Column(name = "antecedents_chirurgicaux")
    private OuiNonNP antecedentsChirurgicaux;

    @Size(max = 255)
    @Column(name = "type_chirurgie", length = 255)
    private String typeChirurgie;

    @Enumerated(EnumType.STRING)
    @Column(name = "complication_hemorragique")
    private OuiNonNP complicationHemorragique;

    @Column(name = "age_premier_signe_hemorragique")
    private Integer agePremierSigneHemorragique;

    @Size(max = 255)
    @Column(name = "type_syndrome_hemorragique", length = 255)
    private String typeSyndromeHemorragique;

    @Column(name = "age_diagnostic")
    private Integer ageDiagnostic;

    @Column(name = "decouverte_fortuite")
    private Boolean decouverteFortuite;

    @Column(name = "enquete_familiale_bool")
    private Boolean enqueteFamilialeBool;

    @Column(name = "exploration_syndrome_hemorragique")
    private Boolean explorationSyndromeHemorragique;

    @Size(max = 255)
    @Column(name = "type_circonstance_diagnostic", length = 255)
    private String typeCirconstanceDiagnostic;

    @Enumerated(EnumType.STRING)
    @Column(name = "eczema")
    private OuiNonNP eczema;

    @Enumerated(EnumType.STRING)
    @Column(name = "syndrome_dysmorphique")
    private OuiNonNP syndromeDysmorphique;

    @Enumerated(EnumType.STRING)
    @Column(name = "albinisme")
    private OuiNonNP albinisme;

    @Enumerated(EnumType.STRING)
    @Column(name = "arthrogrypose")
    private OuiNonNP arthrogrypose;

    @Column(name = "gb")
    private Integer gb;

    @Column(name = "hb")
    private Float hb;

    @Column(name = "vgm")
    private Float vgm;

    @Column(name = "tcmh")
    private Float tcmh;

    @Column(name = "plaquettes")
    private Integer plaquettes;

    @Size(max = 255)
    @Column(name = "frottis_sanguin", length = 255)
    private String frottisSanguin;

    @Column(name = "ts")
    private Float ts;

    @Column(name = "to_pfa")
    private Float toPfa;

    @Column(name = "to_pfa_100_col_epi")
    private Float toPfa100ColEpi;

    @Column(name = "to_pfa_100_col_adp")
    private Float toPfa100ColAdp;

    @Column(name = "tp")
    private Float tp;

    @Column(name = "tca")
    private Float tca;

    @Enumerated(EnumType.STRING)
    @Column(name = "aggregometrie_plaquettaire")
    private OuiNonNP aggregometriePlaquettaire;

    @Size(max = 255)
    @Column(name = "resultat_agregometrie", length = 255)
    private String resultatAgregometrie;

    @Enumerated(EnumType.STRING)
    @Column(name = "cmf")
    private OuiNonNP cmf;

    @Size(max = 255)
    @Column(name = "resultat_cmf", length = 255)
    private String resultatCmf;

    @Column(name = "dosage_fvw_ag")
    private Float dosageFvwAg;

    @Column(name = "dosage_fvw_rco")
    private Float dosageFvwRco;

    @Enumerated(EnumType.STRING)
    @Column(name = "etude_genetique")
    private OuiNonNP etudeGenetique;

    @Size(max = 255)
    @Column(name = "resultat_etude_genetique", length = 255)
    private String resultatEtudeGenetique;

    @Column(name = "thrombasthenie_glanzmann")
    private Boolean thrombasthenieGlanzmann;

    @Column(name = "maladie_bernard_soulier")
    private Boolean maladieBernardSoulier;

    @Column(name = "autre_thrombopathie")
    private Boolean autreThrombopathie;

    @Size(max = 255)
    @Column(name = "autre_thrombopathie_details", length = 255)
    private String autreThrombopathieDetails;

    @Enumerated(EnumType.STRING)
    @Column(name = "ecchymose")
    private OuiNonNP ecchymose;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_ecchymose")
    private TypeManifestation typeEcchymose;

    @Enumerated(EnumType.STRING)
    @Column(name = "gingivorragies")
    private OuiNonNP gingivorragies;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_gingivorragies")
    private TypeManifestation typeGingivorragies;

    @Enumerated(EnumType.STRING)
    @Column(name = "epistaxis")
    private OuiNonNP epistaxis;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_epistaxis")
    private TypeManifestation typeEpistaxis;

    @Enumerated(EnumType.STRING)
    @Column(name = "hematome")
    private OuiNonNP hematome;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_hematome")
    private TypeManifestation typeHematome;

    @Size(max = 255)
    @Column(name = "siegel_hematome", length = 255)
    private String siegelHematome;

    @Enumerated(EnumType.STRING)
    @Column(name = "meno_metrorragies")
    private OuiNonNP menoMetrorragies;

    @Column(name = "frequence_meno_metrorragies")
    private Integer frequenceMenoMetrorragies;

    @Size(max = 255)
    @Column(name = "severite_meno_metrorragies", length = 255)
    private String severiteMenoMetrorragies;

    @Enumerated(EnumType.STRING)
    @Column(name = "exploration_meno_metrorragies")
    private OuiNonNP explorationMenoMetrorragies;

    @Size(max = 255)
    @Column(name = "type_lesion_sous_jacente", length = 255)
    private String typeLesionSousJacente;

    @Enumerated(EnumType.STRING)
    @Column(name = "hemorragie_visceres")
    private OuiNonNP hemorragieVisceres;

    @Column(name = "hemorragie_digestive_haute")
    private Boolean hemorragieDigestiveHaute;

    @Column(name = "hemorragie_digestive_basse")
    private Boolean hemorragieDigestiveBasse;

    @Column(name = "hematurie")
    private Boolean hematurie;

    @Enumerated(EnumType.STRING)
    @Column(name = "exploration_hemorragie_visceres")
    private OuiNonNP explorationHemorragieVisceres;

    @Size(max = 255)
    @Column(name = "lesion_sous_jacente_visceres", length = 255)
    private String lesionSousJacenteVisceres;

    @Enumerated(EnumType.STRING)
    @Column(name = "intervention_chirurgicale")
    private OuiNonNP interventionChirurgicale;

    @Size(max = 255)
    @Column(name = "type_intervention_chirurgicale", length = 255)
    private String typeInterventionChirurgicale;

    @Column(name = "hematomes_cerebrales")
    private Boolean hematomesCerebrales;

    @Column(name = "edc_hemorragique")
    private Boolean edcHemorragique;

    @Enumerated(EnumType.STRING)
    @Column(name = "transfusion_cps")
    private OuiNonNP transfusionCps;

    @Enumerated(EnumType.STRING)
    @Column(name = "facteur_vii_recombinant")
    private OuiNonNP facteurVIIRecombinant;

    @Enumerated(EnumType.STRING)
    @Column(name = "dicynone")
    private OuiNonNP dicynone;

    @Enumerated(EnumType.STRING)
    @Column(name = "exacyl")
    private OuiNonNP exacyl;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_oestroprogestatif")
    private OuiNonNP traitementOestroprogestatif;

    @Enumerated(EnumType.STRING)
    @Column(name = "mechage")
    private OuiNonNP mechage;

    @Enumerated(EnumType.STRING)
    @Column(name = "transfusion_cgr")
    private OuiNonNP transfusionCgr;

    @Enumerated(EnumType.STRING)
    @Column(name = "traitement_martial")
    private OuiNonNP traitementMartial;

    @Enumerated(EnumType.STRING)
    @Column(name = "thrombose")
    private OuiNonNP thrombose;

    @Enumerated(EnumType.STRING)
    @Column(name = "allo_immunisation_hla")
    private OuiNonNP alloImmunisationHLA;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FicheThrombopathie id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDossierNumber() {
        return this.dossierNumber;
    }

    public FicheThrombopathie dossierNumber(String dossierNumber) {
        this.setDossierNumber(dossierNumber);
        return this;
    }

    public void setDossierNumber(String dossierNumber) {
        this.dossierNumber = dossierNumber;
    }

    public String getOrdreNumber() {
        return this.ordreNumber;
    }

    public FicheThrombopathie ordreNumber(String ordreNumber) {
        this.setOrdreNumber(ordreNumber);
        return this;
    }

    public void setOrdreNumber(String ordreNumber) {
        this.ordreNumber = ordreNumber;
    }

    public String getIndexNumber() {
        return this.indexNumber;
    }

    public FicheThrombopathie indexNumber(String indexNumber) {
        this.setIndexNumber(indexNumber);
        return this;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public FicheThrombopathie prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public FicheThrombopathie nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomJeuneFille() {
        return this.nomJeuneFille;
    }

    public FicheThrombopathie nomJeuneFille(String nomJeuneFille) {
        this.setNomJeuneFille(nomJeuneFille);
        return this;
    }

    public void setNomJeuneFille(String nomJeuneFille) {
        this.nomJeuneFille = nomJeuneFille;
    }

    public String getProfession() {
        return this.profession;
    }

    public FicheThrombopathie profession(String profession) {
        this.setProfession(profession);
        return this;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public CivilStatus getEtatCivil() {
        return this.etatCivil;
    }

    public FicheThrombopathie etatCivil(CivilStatus etatCivil) {
        this.setEtatCivil(etatCivil);
        return this;
    }

    public void setEtatCivil(CivilStatus etatCivil) {
        this.etatCivil = etatCivil;
    }

    public ServiceCliniqueType getServiceClinique() {
        return this.serviceClinique;
    }

    public FicheThrombopathie serviceClinique(ServiceCliniqueType serviceClinique) {
        this.setServiceClinique(serviceClinique);
        return this;
    }

    public void setServiceClinique(ServiceCliniqueType serviceClinique) {
        this.serviceClinique = serviceClinique;
    }

    public String getAutreServiceClinique() {
        return this.autreServiceClinique;
    }

    public FicheThrombopathie autreServiceClinique(String autreServiceClinique) {
        this.setAutreServiceClinique(autreServiceClinique);
        return this;
    }

    public void setAutreServiceClinique(String autreServiceClinique) {
        this.autreServiceClinique = autreServiceClinique;
    }

    public CouvertureSociale getCouvertureSociale() {
        return this.couvertureSociale;
    }

    public FicheThrombopathie couvertureSociale(CouvertureSociale couvertureSociale) {
        this.setCouvertureSociale(couvertureSociale);
        return this;
    }

    public void setCouvertureSociale(CouvertureSociale couvertureSociale) {
        this.couvertureSociale = couvertureSociale;
    }

    public Gender getSexe() {
        return this.sexe;
    }

    public FicheThrombopathie sexe(Gender sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Gender sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public FicheThrombopathie dateNaissance(LocalDate dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Integer getAgeActuel() {
        return this.ageActuel;
    }

    public FicheThrombopathie ageActuel(Integer ageActuel) {
        this.setAgeActuel(ageActuel);
        return this;
    }

    public void setAgeActuel(Integer ageActuel) {
        this.ageActuel = ageActuel;
    }

    public Region getOrigine() {
        return this.origine;
    }

    public FicheThrombopathie origine(Region origine) {
        this.setOrigine(origine);
        return this;
    }

    public void setOrigine(Region origine) {
        this.origine = origine;
    }

    public String getAutreOrigine() {
        return this.autreOrigine;
    }

    public FicheThrombopathie autreOrigine(String autreOrigine) {
        this.setAutreOrigine(autreOrigine);
        return this;
    }

    public void setAutreOrigine(String autreOrigine) {
        this.autreOrigine = autreOrigine;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public FicheThrombopathie adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public FicheThrombopathie telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrenomPere() {
        return this.prenomPere;
    }

    public FicheThrombopathie prenomPere(String prenomPere) {
        this.setPrenomPere(prenomPere);
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getProfessionPere() {
        return this.professionPere;
    }

    public FicheThrombopathie professionPere(String professionPere) {
        this.setProfessionPere(professionPere);
        return this;
    }

    public void setProfessionPere(String professionPere) {
        this.professionPere = professionPere;
    }

    public String getNomPrenomMere() {
        return this.nomPrenomMere;
    }

    public FicheThrombopathie nomPrenomMere(String nomPrenomMere) {
        this.setNomPrenomMere(nomPrenomMere);
        return this;
    }

    public void setNomPrenomMere(String nomPrenomMere) {
        this.nomPrenomMere = nomPrenomMere;
    }

    public String getProfessionMere() {
        return this.professionMere;
    }

    public FicheThrombopathie professionMere(String professionMere) {
        this.setProfessionMere(professionMere);
        return this;
    }

    public void setProfessionMere(String professionMere) {
        this.professionMere = professionMere;
    }

    public String getMedecinTraitant() {
        return this.medecinTraitant;
    }

    public FicheThrombopathie medecinTraitant(String medecinTraitant) {
        this.setMedecinTraitant(medecinTraitant);
        return this;
    }

    public void setMedecinTraitant(String medecinTraitant) {
        this.medecinTraitant = medecinTraitant;
    }

    public String getServiceOrigine() {
        return this.serviceOrigine;
    }

    public FicheThrombopathie serviceOrigine(String serviceOrigine) {
        this.setServiceOrigine(serviceOrigine);
        return this;
    }

    public void setServiceOrigine(String serviceOrigine) {
        this.serviceOrigine = serviceOrigine;
    }

    public String getBiologisteResponsableDg() {
        return this.biologisteResponsableDg;
    }

    public FicheThrombopathie biologisteResponsableDg(String biologisteResponsableDg) {
        this.setBiologisteResponsableDg(biologisteResponsableDg);
        return this;
    }

    public void setBiologisteResponsableDg(String biologisteResponsableDg) {
        this.biologisteResponsableDg = biologisteResponsableDg;
    }

    public DiagnosticType getDiagnostic() {
        return this.diagnostic;
    }

    public FicheThrombopathie diagnostic(DiagnosticType diagnostic) {
        this.setDiagnostic(diagnostic);
        return this;
    }

    public void setDiagnostic(DiagnosticType diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getAutreDiagnostic() {
        return this.autreDiagnostic;
    }

    public FicheThrombopathie autreDiagnostic(String autreDiagnostic) {
        this.setAutreDiagnostic(autreDiagnostic);
        return this;
    }

    public void setAutreDiagnostic(String autreDiagnostic) {
        this.autreDiagnostic = autreDiagnostic;
    }

    public Integer getAnneeDiagnostic() {
        return this.anneeDiagnostic;
    }

    public FicheThrombopathie anneeDiagnostic(Integer anneeDiagnostic) {
        this.setAnneeDiagnostic(anneeDiagnostic);
        return this;
    }

    public void setAnneeDiagnostic(Integer anneeDiagnostic) {
        this.anneeDiagnostic = anneeDiagnostic;
    }

    public OuiNonNP getConsentementRegistre() {
        return this.consentementRegistre;
    }

    public FicheThrombopathie consentementRegistre(OuiNonNP consentementRegistre) {
        this.setConsentementRegistre(consentementRegistre);
        return this;
    }

    public void setConsentementRegistre(OuiNonNP consentementRegistre) {
        this.consentementRegistre = consentementRegistre;
    }

    public LocalDate getDateEnregistrementRegistre() {
        return this.dateEnregistrementRegistre;
    }

    public FicheThrombopathie dateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.setDateEnregistrementRegistre(dateEnregistrementRegistre);
        return this;
    }

    public void setDateEnregistrementRegistre(LocalDate dateEnregistrementRegistre) {
        this.dateEnregistrementRegistre = dateEnregistrementRegistre;
    }

    public OuiNonNP getParentsConsanguins() {
        return this.parentsConsanguins;
    }

    public FicheThrombopathie parentsConsanguins(OuiNonNP parentsConsanguins) {
        this.setParentsConsanguins(parentsConsanguins);
        return this;
    }

    public void setParentsConsanguins(OuiNonNP parentsConsanguins) {
        this.parentsConsanguins = parentsConsanguins;
    }

    public String getDegreParenteConsanguins() {
        return this.degreParenteConsanguins;
    }

    public FicheThrombopathie degreParenteConsanguins(String degreParenteConsanguins) {
        this.setDegreParenteConsanguins(degreParenteConsanguins);
        return this;
    }

    public void setDegreParenteConsanguins(String degreParenteConsanguins) {
        this.degreParenteConsanguins = degreParenteConsanguins;
    }

    public OuiNonNP getCasSimilaires() {
        return this.casSimilaires;
    }

    public FicheThrombopathie casSimilaires(OuiNonNP casSimilaires) {
        this.setCasSimilaires(casSimilaires);
        return this;
    }

    public void setCasSimilaires(OuiNonNP casSimilaires) {
        this.casSimilaires = casSimilaires;
    }

    public Integer getNombreCas() {
        return this.nombreCas;
    }

    public FicheThrombopathie nombreCas(Integer nombreCas) {
        this.setNombreCas(nombreCas);
        return this;
    }

    public void setNombreCas(Integer nombreCas) {
        this.nombreCas = nombreCas;
    }

    public String getDegreParenteCasSimilaires() {
        return this.degreParenteCasSimilaires;
    }

    public FicheThrombopathie degreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.setDegreParenteCasSimilaires(degreParenteCasSimilaires);
        return this;
    }

    public void setDegreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.degreParenteCasSimilaires = degreParenteCasSimilaires;
    }

    public OuiNonNP getDecesSyndromeHemorragique() {
        return this.decesSyndromeHemorragique;
    }

    public FicheThrombopathie decesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.setDecesSyndromeHemorragique(decesSyndromeHemorragique);
        return this;
    }

    public void setDecesSyndromeHemorragique(OuiNonNP decesSyndromeHemorragique) {
        this.decesSyndromeHemorragique = decesSyndromeHemorragique;
    }

    public Integer getNombreDeces() {
        return this.nombreDeces;
    }

    public FicheThrombopathie nombreDeces(Integer nombreDeces) {
        this.setNombreDeces(nombreDeces);
        return this;
    }

    public void setNombreDeces(Integer nombreDeces) {
        this.nombreDeces = nombreDeces;
    }

    public String getTypeHemorragie() {
        return this.typeHemorragie;
    }

    public FicheThrombopathie typeHemorragie(String typeHemorragie) {
        this.setTypeHemorragie(typeHemorragie);
        return this;
    }

    public void setTypeHemorragie(String typeHemorragie) {
        this.typeHemorragie = typeHemorragie;
    }

    public String getLienParenteDeces() {
        return this.lienParenteDeces;
    }

    public FicheThrombopathie lienParenteDeces(String lienParenteDeces) {
        this.setLienParenteDeces(lienParenteDeces);
        return this;
    }

    public void setLienParenteDeces(String lienParenteDeces) {
        this.lienParenteDeces = lienParenteDeces;
    }

    public FaiteNonFaite getEnqueteFamiliale() {
        return this.enqueteFamiliale;
    }

    public FicheThrombopathie enqueteFamiliale(FaiteNonFaite enqueteFamiliale) {
        this.setEnqueteFamiliale(enqueteFamiliale);
        return this;
    }

    public void setEnqueteFamiliale(FaiteNonFaite enqueteFamiliale) {
        this.enqueteFamiliale = enqueteFamiliale;
    }

    public String getAntecedentsMedicaux() {
        return this.antecedentsMedicaux;
    }

    public FicheThrombopathie antecedentsMedicaux(String antecedentsMedicaux) {
        this.setAntecedentsMedicaux(antecedentsMedicaux);
        return this;
    }

    public void setAntecedentsMedicaux(String antecedentsMedicaux) {
        this.antecedentsMedicaux = antecedentsMedicaux;
    }

    public OuiNonNP getAntecedentsChirurgicaux() {
        return this.antecedentsChirurgicaux;
    }

    public FicheThrombopathie antecedentsChirurgicaux(OuiNonNP antecedentsChirurgicaux) {
        this.setAntecedentsChirurgicaux(antecedentsChirurgicaux);
        return this;
    }

    public void setAntecedentsChirurgicaux(OuiNonNP antecedentsChirurgicaux) {
        this.antecedentsChirurgicaux = antecedentsChirurgicaux;
    }

    public String getTypeChirurgie() {
        return this.typeChirurgie;
    }

    public FicheThrombopathie typeChirurgie(String typeChirurgie) {
        this.setTypeChirurgie(typeChirurgie);
        return this;
    }

    public void setTypeChirurgie(String typeChirurgie) {
        this.typeChirurgie = typeChirurgie;
    }

    public OuiNonNP getComplicationHemorragique() {
        return this.complicationHemorragique;
    }

    public FicheThrombopathie complicationHemorragique(OuiNonNP complicationHemorragique) {
        this.setComplicationHemorragique(complicationHemorragique);
        return this;
    }

    public void setComplicationHemorragique(OuiNonNP complicationHemorragique) {
        this.complicationHemorragique = complicationHemorragique;
    }

    public Integer getAgePremierSigneHemorragique() {
        return this.agePremierSigneHemorragique;
    }

    public FicheThrombopathie agePremierSigneHemorragique(Integer agePremierSigneHemorragique) {
        this.setAgePremierSigneHemorragique(agePremierSigneHemorragique);
        return this;
    }

    public void setAgePremierSigneHemorragique(Integer agePremierSigneHemorragique) {
        this.agePremierSigneHemorragique = agePremierSigneHemorragique;
    }

    public String getTypeSyndromeHemorragique() {
        return this.typeSyndromeHemorragique;
    }

    public FicheThrombopathie typeSyndromeHemorragique(String typeSyndromeHemorragique) {
        this.setTypeSyndromeHemorragique(typeSyndromeHemorragique);
        return this;
    }

    public void setTypeSyndromeHemorragique(String typeSyndromeHemorragique) {
        this.typeSyndromeHemorragique = typeSyndromeHemorragique;
    }

    public Integer getAgeDiagnostic() {
        return this.ageDiagnostic;
    }

    public FicheThrombopathie ageDiagnostic(Integer ageDiagnostic) {
        this.setAgeDiagnostic(ageDiagnostic);
        return this;
    }

    public void setAgeDiagnostic(Integer ageDiagnostic) {
        this.ageDiagnostic = ageDiagnostic;
    }

    public Boolean getDecouverteFortuite() {
        return this.decouverteFortuite;
    }

    public FicheThrombopathie decouverteFortuite(Boolean decouverteFortuite) {
        this.setDecouverteFortuite(decouverteFortuite);
        return this;
    }

    public void setDecouverteFortuite(Boolean decouverteFortuite) {
        this.decouverteFortuite = decouverteFortuite;
    }

    public Boolean getEnqueteFamilialeBool() {
        return this.enqueteFamilialeBool;
    }

    public FicheThrombopathie enqueteFamilialeBool(Boolean enqueteFamilialeBool) {
        this.setEnqueteFamilialeBool(enqueteFamilialeBool);
        return this;
    }

    public void setEnqueteFamilialeBool(Boolean enqueteFamilialeBool) {
        this.enqueteFamilialeBool = enqueteFamilialeBool;
    }

    public Boolean getExplorationSyndromeHemorragique() {
        return this.explorationSyndromeHemorragique;
    }

    public FicheThrombopathie explorationSyndromeHemorragique(Boolean explorationSyndromeHemorragique) {
        this.setExplorationSyndromeHemorragique(explorationSyndromeHemorragique);
        return this;
    }

    public void setExplorationSyndromeHemorragique(Boolean explorationSyndromeHemorragique) {
        this.explorationSyndromeHemorragique = explorationSyndromeHemorragique;
    }

    public String getTypeCirconstanceDiagnostic() {
        return this.typeCirconstanceDiagnostic;
    }

    public FicheThrombopathie typeCirconstanceDiagnostic(String typeCirconstanceDiagnostic) {
        this.setTypeCirconstanceDiagnostic(typeCirconstanceDiagnostic);
        return this;
    }

    public void setTypeCirconstanceDiagnostic(String typeCirconstanceDiagnostic) {
        this.typeCirconstanceDiagnostic = typeCirconstanceDiagnostic;
    }

    public OuiNonNP getEczema() {
        return this.eczema;
    }

    public FicheThrombopathie eczema(OuiNonNP eczema) {
        this.setEczema(eczema);
        return this;
    }

    public void setEczema(OuiNonNP eczema) {
        this.eczema = eczema;
    }

    public OuiNonNP getSyndromeDysmorphique() {
        return this.syndromeDysmorphique;
    }

    public FicheThrombopathie syndromeDysmorphique(OuiNonNP syndromeDysmorphique) {
        this.setSyndromeDysmorphique(syndromeDysmorphique);
        return this;
    }

    public void setSyndromeDysmorphique(OuiNonNP syndromeDysmorphique) {
        this.syndromeDysmorphique = syndromeDysmorphique;
    }

    public OuiNonNP getAlbinisme() {
        return this.albinisme;
    }

    public FicheThrombopathie albinisme(OuiNonNP albinisme) {
        this.setAlbinisme(albinisme);
        return this;
    }

    public void setAlbinisme(OuiNonNP albinisme) {
        this.albinisme = albinisme;
    }

    public OuiNonNP getArthrogrypose() {
        return this.arthrogrypose;
    }

    public FicheThrombopathie arthrogrypose(OuiNonNP arthrogrypose) {
        this.setArthrogrypose(arthrogrypose);
        return this;
    }

    public void setArthrogrypose(OuiNonNP arthrogrypose) {
        this.arthrogrypose = arthrogrypose;
    }

    public Integer getGb() {
        return this.gb;
    }

    public FicheThrombopathie gb(Integer gb) {
        this.setGb(gb);
        return this;
    }

    public void setGb(Integer gb) {
        this.gb = gb;
    }

    public Float getHb() {
        return this.hb;
    }

    public FicheThrombopathie hb(Float hb) {
        this.setHb(hb);
        return this;
    }

    public void setHb(Float hb) {
        this.hb = hb;
    }

    public Float getVgm() {
        return this.vgm;
    }

    public FicheThrombopathie vgm(Float vgm) {
        this.setVgm(vgm);
        return this;
    }

    public void setVgm(Float vgm) {
        this.vgm = vgm;
    }

    public Float getTcmh() {
        return this.tcmh;
    }

    public FicheThrombopathie tcmh(Float tcmh) {
        this.setTcmh(tcmh);
        return this;
    }

    public void setTcmh(Float tcmh) {
        this.tcmh = tcmh;
    }

    public Integer getPlaquettes() {
        return this.plaquettes;
    }

    public FicheThrombopathie plaquettes(Integer plaquettes) {
        this.setPlaquettes(plaquettes);
        return this;
    }

    public void setPlaquettes(Integer plaquettes) {
        this.plaquettes = plaquettes;
    }

    public String getFrottisSanguin() {
        return this.frottisSanguin;
    }

    public FicheThrombopathie frottisSanguin(String frottisSanguin) {
        this.setFrottisSanguin(frottisSanguin);
        return this;
    }

    public void setFrottisSanguin(String frottisSanguin) {
        this.frottisSanguin = frottisSanguin;
    }

    public Float getTs() {
        return this.ts;
    }

    public FicheThrombopathie ts(Float ts) {
        this.setTs(ts);
        return this;
    }

    public void setTs(Float ts) {
        this.ts = ts;
    }

    public Float getToPfa() {
        return this.toPfa;
    }

    public FicheThrombopathie toPfa(Float toPfa) {
        this.setToPfa(toPfa);
        return this;
    }

    public void setToPfa(Float toPfa) {
        this.toPfa = toPfa;
    }

    public Float getToPfa100ColEpi() {
        return this.toPfa100ColEpi;
    }

    public FicheThrombopathie toPfa100ColEpi(Float toPfa100ColEpi) {
        this.setToPfa100ColEpi(toPfa100ColEpi);
        return this;
    }

    public void setToPfa100ColEpi(Float toPfa100ColEpi) {
        this.toPfa100ColEpi = toPfa100ColEpi;
    }

    public Float getToPfa100ColAdp() {
        return this.toPfa100ColAdp;
    }

    public FicheThrombopathie toPfa100ColAdp(Float toPfa100ColAdp) {
        this.setToPfa100ColAdp(toPfa100ColAdp);
        return this;
    }

    public void setToPfa100ColAdp(Float toPfa100ColAdp) {
        this.toPfa100ColAdp = toPfa100ColAdp;
    }

    public Float getTp() {
        return this.tp;
    }

    public FicheThrombopathie tp(Float tp) {
        this.setTp(tp);
        return this;
    }

    public void setTp(Float tp) {
        this.tp = tp;
    }

    public Float getTca() {
        return this.tca;
    }

    public FicheThrombopathie tca(Float tca) {
        this.setTca(tca);
        return this;
    }

    public void setTca(Float tca) {
        this.tca = tca;
    }

    public OuiNonNP getAggregometriePlaquettaire() {
        return this.aggregometriePlaquettaire;
    }

    public FicheThrombopathie aggregometriePlaquettaire(OuiNonNP aggregometriePlaquettaire) {
        this.setAggregometriePlaquettaire(aggregometriePlaquettaire);
        return this;
    }

    public void setAggregometriePlaquettaire(OuiNonNP aggregometriePlaquettaire) {
        this.aggregometriePlaquettaire = aggregometriePlaquettaire;
    }

    public String getResultatAgregometrie() {
        return this.resultatAgregometrie;
    }

    public FicheThrombopathie resultatAgregometrie(String resultatAgregometrie) {
        this.setResultatAgregometrie(resultatAgregometrie);
        return this;
    }

    public void setResultatAgregometrie(String resultatAgregometrie) {
        this.resultatAgregometrie = resultatAgregometrie;
    }

    public OuiNonNP getCmf() {
        return this.cmf;
    }

    public FicheThrombopathie cmf(OuiNonNP cmf) {
        this.setCmf(cmf);
        return this;
    }

    public void setCmf(OuiNonNP cmf) {
        this.cmf = cmf;
    }

    public String getResultatCmf() {
        return this.resultatCmf;
    }

    public FicheThrombopathie resultatCmf(String resultatCmf) {
        this.setResultatCmf(resultatCmf);
        return this;
    }

    public void setResultatCmf(String resultatCmf) {
        this.resultatCmf = resultatCmf;
    }

    public Float getDosageFvwAg() {
        return this.dosageFvwAg;
    }

    public FicheThrombopathie dosageFvwAg(Float dosageFvwAg) {
        this.setDosageFvwAg(dosageFvwAg);
        return this;
    }

    public void setDosageFvwAg(Float dosageFvwAg) {
        this.dosageFvwAg = dosageFvwAg;
    }

    public Float getDosageFvwRco() {
        return this.dosageFvwRco;
    }

    public FicheThrombopathie dosageFvwRco(Float dosageFvwRco) {
        this.setDosageFvwRco(dosageFvwRco);
        return this;
    }

    public void setDosageFvwRco(Float dosageFvwRco) {
        this.dosageFvwRco = dosageFvwRco;
    }

    public OuiNonNP getEtudeGenetique() {
        return this.etudeGenetique;
    }

    public FicheThrombopathie etudeGenetique(OuiNonNP etudeGenetique) {
        this.setEtudeGenetique(etudeGenetique);
        return this;
    }

    public void setEtudeGenetique(OuiNonNP etudeGenetique) {
        this.etudeGenetique = etudeGenetique;
    }

    public String getResultatEtudeGenetique() {
        return this.resultatEtudeGenetique;
    }

    public FicheThrombopathie resultatEtudeGenetique(String resultatEtudeGenetique) {
        this.setResultatEtudeGenetique(resultatEtudeGenetique);
        return this;
    }

    public void setResultatEtudeGenetique(String resultatEtudeGenetique) {
        this.resultatEtudeGenetique = resultatEtudeGenetique;
    }

    public Boolean getThrombasthenieGlanzmann() {
        return this.thrombasthenieGlanzmann;
    }

    public FicheThrombopathie thrombasthenieGlanzmann(Boolean thrombasthenieGlanzmann) {
        this.setThrombasthenieGlanzmann(thrombasthenieGlanzmann);
        return this;
    }

    public void setThrombasthenieGlanzmann(Boolean thrombasthenieGlanzmann) {
        this.thrombasthenieGlanzmann = thrombasthenieGlanzmann;
    }

    public Boolean getMaladieBernardSoulier() {
        return this.maladieBernardSoulier;
    }

    public FicheThrombopathie maladieBernardSoulier(Boolean maladieBernardSoulier) {
        this.setMaladieBernardSoulier(maladieBernardSoulier);
        return this;
    }

    public void setMaladieBernardSoulier(Boolean maladieBernardSoulier) {
        this.maladieBernardSoulier = maladieBernardSoulier;
    }

    public Boolean getAutreThrombopathie() {
        return this.autreThrombopathie;
    }

    public FicheThrombopathie autreThrombopathie(Boolean autreThrombopathie) {
        this.setAutreThrombopathie(autreThrombopathie);
        return this;
    }

    public void setAutreThrombopathie(Boolean autreThrombopathie) {
        this.autreThrombopathie = autreThrombopathie;
    }

    public String getAutreThrombopathieDetails() {
        return this.autreThrombopathieDetails;
    }

    public FicheThrombopathie autreThrombopathieDetails(String autreThrombopathieDetails) {
        this.setAutreThrombopathieDetails(autreThrombopathieDetails);
        return this;
    }

    public void setAutreThrombopathieDetails(String autreThrombopathieDetails) {
        this.autreThrombopathieDetails = autreThrombopathieDetails;
    }

    public OuiNonNP getEcchymose() {
        return this.ecchymose;
    }

    public FicheThrombopathie ecchymose(OuiNonNP ecchymose) {
        this.setEcchymose(ecchymose);
        return this;
    }

    public void setEcchymose(OuiNonNP ecchymose) {
        this.ecchymose = ecchymose;
    }

    public TypeManifestation getTypeEcchymose() {
        return this.typeEcchymose;
    }

    public FicheThrombopathie typeEcchymose(TypeManifestation typeEcchymose) {
        this.setTypeEcchymose(typeEcchymose);
        return this;
    }

    public void setTypeEcchymose(TypeManifestation typeEcchymose) {
        this.typeEcchymose = typeEcchymose;
    }

    public OuiNonNP getGingivorragies() {
        return this.gingivorragies;
    }

    public FicheThrombopathie gingivorragies(OuiNonNP gingivorragies) {
        this.setGingivorragies(gingivorragies);
        return this;
    }

    public void setGingivorragies(OuiNonNP gingivorragies) {
        this.gingivorragies = gingivorragies;
    }

    public TypeManifestation getTypeGingivorragies() {
        return this.typeGingivorragies;
    }

    public FicheThrombopathie typeGingivorragies(TypeManifestation typeGingivorragies) {
        this.setTypeGingivorragies(typeGingivorragies);
        return this;
    }

    public void setTypeGingivorragies(TypeManifestation typeGingivorragies) {
        this.typeGingivorragies = typeGingivorragies;
    }

    public OuiNonNP getEpistaxis() {
        return this.epistaxis;
    }

    public FicheThrombopathie epistaxis(OuiNonNP epistaxis) {
        this.setEpistaxis(epistaxis);
        return this;
    }

    public void setEpistaxis(OuiNonNP epistaxis) {
        this.epistaxis = epistaxis;
    }

    public TypeManifestation getTypeEpistaxis() {
        return this.typeEpistaxis;
    }

    public FicheThrombopathie typeEpistaxis(TypeManifestation typeEpistaxis) {
        this.setTypeEpistaxis(typeEpistaxis);
        return this;
    }

    public void setTypeEpistaxis(TypeManifestation typeEpistaxis) {
        this.typeEpistaxis = typeEpistaxis;
    }

    public OuiNonNP getHematome() {
        return this.hematome;
    }

    public FicheThrombopathie hematome(OuiNonNP hematome) {
        this.setHematome(hematome);
        return this;
    }

    public void setHematome(OuiNonNP hematome) {
        this.hematome = hematome;
    }

    public TypeManifestation getTypeHematome() {
        return this.typeHematome;
    }

    public FicheThrombopathie typeHematome(TypeManifestation typeHematome) {
        this.setTypeHematome(typeHematome);
        return this;
    }

    public void setTypeHematome(TypeManifestation typeHematome) {
        this.typeHematome = typeHematome;
    }

    public String getSiegelHematome() {
        return this.siegelHematome;
    }

    public FicheThrombopathie siegelHematome(String siegelHematome) {
        this.setSiegelHematome(siegelHematome);
        return this;
    }

    public void setSiegelHematome(String siegelHematome) {
        this.siegelHematome = siegelHematome;
    }

    public OuiNonNP getMenoMetrorragies() {
        return this.menoMetrorragies;
    }

    public FicheThrombopathie menoMetrorragies(OuiNonNP menoMetrorragies) {
        this.setMenoMetrorragies(menoMetrorragies);
        return this;
    }

    public void setMenoMetrorragies(OuiNonNP menoMetrorragies) {
        this.menoMetrorragies = menoMetrorragies;
    }

    public Integer getFrequenceMenoMetrorragies() {
        return this.frequenceMenoMetrorragies;
    }

    public FicheThrombopathie frequenceMenoMetrorragies(Integer frequenceMenoMetrorragies) {
        this.setFrequenceMenoMetrorragies(frequenceMenoMetrorragies);
        return this;
    }

    public void setFrequenceMenoMetrorragies(Integer frequenceMenoMetrorragies) {
        this.frequenceMenoMetrorragies = frequenceMenoMetrorragies;
    }

    public String getSeveriteMenoMetrorragies() {
        return this.severiteMenoMetrorragies;
    }

    public FicheThrombopathie severiteMenoMetrorragies(String severiteMenoMetrorragies) {
        this.setSeveriteMenoMetrorragies(severiteMenoMetrorragies);
        return this;
    }

    public void setSeveriteMenoMetrorragies(String severiteMenoMetrorragies) {
        this.severiteMenoMetrorragies = severiteMenoMetrorragies;
    }

    public OuiNonNP getExplorationMenoMetrorragies() {
        return this.explorationMenoMetrorragies;
    }

    public FicheThrombopathie explorationMenoMetrorragies(OuiNonNP explorationMenoMetrorragies) {
        this.setExplorationMenoMetrorragies(explorationMenoMetrorragies);
        return this;
    }

    public void setExplorationMenoMetrorragies(OuiNonNP explorationMenoMetrorragies) {
        this.explorationMenoMetrorragies = explorationMenoMetrorragies;
    }

    public String getTypeLesionSousJacente() {
        return this.typeLesionSousJacente;
    }

    public FicheThrombopathie typeLesionSousJacente(String typeLesionSousJacente) {
        this.setTypeLesionSousJacente(typeLesionSousJacente);
        return this;
    }

    public void setTypeLesionSousJacente(String typeLesionSousJacente) {
        this.typeLesionSousJacente = typeLesionSousJacente;
    }

    public OuiNonNP getHemorragieVisceres() {
        return this.hemorragieVisceres;
    }

    public FicheThrombopathie hemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.setHemorragieVisceres(hemorragieVisceres);
        return this;
    }

    public void setHemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.hemorragieVisceres = hemorragieVisceres;
    }

    public Boolean getHemorragieDigestiveHaute() {
        return this.hemorragieDigestiveHaute;
    }

    public FicheThrombopathie hemorragieDigestiveHaute(Boolean hemorragieDigestiveHaute) {
        this.setHemorragieDigestiveHaute(hemorragieDigestiveHaute);
        return this;
    }

    public void setHemorragieDigestiveHaute(Boolean hemorragieDigestiveHaute) {
        this.hemorragieDigestiveHaute = hemorragieDigestiveHaute;
    }

    public Boolean getHemorragieDigestiveBasse() {
        return this.hemorragieDigestiveBasse;
    }

    public FicheThrombopathie hemorragieDigestiveBasse(Boolean hemorragieDigestiveBasse) {
        this.setHemorragieDigestiveBasse(hemorragieDigestiveBasse);
        return this;
    }

    public void setHemorragieDigestiveBasse(Boolean hemorragieDigestiveBasse) {
        this.hemorragieDigestiveBasse = hemorragieDigestiveBasse;
    }

    public Boolean getHematurie() {
        return this.hematurie;
    }

    public FicheThrombopathie hematurie(Boolean hematurie) {
        this.setHematurie(hematurie);
        return this;
    }

    public void setHematurie(Boolean hematurie) {
        this.hematurie = hematurie;
    }

    public OuiNonNP getExplorationHemorragieVisceres() {
        return this.explorationHemorragieVisceres;
    }

    public FicheThrombopathie explorationHemorragieVisceres(OuiNonNP explorationHemorragieVisceres) {
        this.setExplorationHemorragieVisceres(explorationHemorragieVisceres);
        return this;
    }

    public void setExplorationHemorragieVisceres(OuiNonNP explorationHemorragieVisceres) {
        this.explorationHemorragieVisceres = explorationHemorragieVisceres;
    }

    public String getLesionSousJacenteVisceres() {
        return this.lesionSousJacenteVisceres;
    }

    public FicheThrombopathie lesionSousJacenteVisceres(String lesionSousJacenteVisceres) {
        this.setLesionSousJacenteVisceres(lesionSousJacenteVisceres);
        return this;
    }

    public void setLesionSousJacenteVisceres(String lesionSousJacenteVisceres) {
        this.lesionSousJacenteVisceres = lesionSousJacenteVisceres;
    }

    public OuiNonNP getInterventionChirurgicale() {
        return this.interventionChirurgicale;
    }

    public FicheThrombopathie interventionChirurgicale(OuiNonNP interventionChirurgicale) {
        this.setInterventionChirurgicale(interventionChirurgicale);
        return this;
    }

    public void setInterventionChirurgicale(OuiNonNP interventionChirurgicale) {
        this.interventionChirurgicale = interventionChirurgicale;
    }

    public String getTypeInterventionChirurgicale() {
        return this.typeInterventionChirurgicale;
    }

    public FicheThrombopathie typeInterventionChirurgicale(String typeInterventionChirurgicale) {
        this.setTypeInterventionChirurgicale(typeInterventionChirurgicale);
        return this;
    }

    public void setTypeInterventionChirurgicale(String typeInterventionChirurgicale) {
        this.typeInterventionChirurgicale = typeInterventionChirurgicale;
    }

    public Boolean getHematomesCerebrales() {
        return this.hematomesCerebrales;
    }

    public FicheThrombopathie hematomesCerebrales(Boolean hematomesCerebrales) {
        this.setHematomesCerebrales(hematomesCerebrales);
        return this;
    }

    public void setHematomesCerebrales(Boolean hematomesCerebrales) {
        this.hematomesCerebrales = hematomesCerebrales;
    }

    public Boolean getEdcHemorragique() {
        return this.edcHemorragique;
    }

    public FicheThrombopathie edcHemorragique(Boolean edcHemorragique) {
        this.setEdcHemorragique(edcHemorragique);
        return this;
    }

    public void setEdcHemorragique(Boolean edcHemorragique) {
        this.edcHemorragique = edcHemorragique;
    }

    public OuiNonNP getTransfusionCps() {
        return this.transfusionCps;
    }

    public FicheThrombopathie transfusionCps(OuiNonNP transfusionCps) {
        this.setTransfusionCps(transfusionCps);
        return this;
    }

    public void setTransfusionCps(OuiNonNP transfusionCps) {
        this.transfusionCps = transfusionCps;
    }

    public OuiNonNP getFacteurVIIRecombinant() {
        return this.facteurVIIRecombinant;
    }

    public FicheThrombopathie facteurVIIRecombinant(OuiNonNP facteurVIIRecombinant) {
        this.setFacteurVIIRecombinant(facteurVIIRecombinant);
        return this;
    }

    public void setFacteurVIIRecombinant(OuiNonNP facteurVIIRecombinant) {
        this.facteurVIIRecombinant = facteurVIIRecombinant;
    }

    public OuiNonNP getDicynone() {
        return this.dicynone;
    }

    public FicheThrombopathie dicynone(OuiNonNP dicynone) {
        this.setDicynone(dicynone);
        return this;
    }

    public void setDicynone(OuiNonNP dicynone) {
        this.dicynone = dicynone;
    }

    public OuiNonNP getExacyl() {
        return this.exacyl;
    }

    public FicheThrombopathie exacyl(OuiNonNP exacyl) {
        this.setExacyl(exacyl);
        return this;
    }

    public void setExacyl(OuiNonNP exacyl) {
        this.exacyl = exacyl;
    }

    public OuiNonNP getTraitementOestroprogestatif() {
        return this.traitementOestroprogestatif;
    }

    public FicheThrombopathie traitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.setTraitementOestroprogestatif(traitementOestroprogestatif);
        return this;
    }

    public void setTraitementOestroprogestatif(OuiNonNP traitementOestroprogestatif) {
        this.traitementOestroprogestatif = traitementOestroprogestatif;
    }

    public OuiNonNP getMechage() {
        return this.mechage;
    }

    public FicheThrombopathie mechage(OuiNonNP mechage) {
        this.setMechage(mechage);
        return this;
    }

    public void setMechage(OuiNonNP mechage) {
        this.mechage = mechage;
    }

    public OuiNonNP getTransfusionCgr() {
        return this.transfusionCgr;
    }

    public FicheThrombopathie transfusionCgr(OuiNonNP transfusionCgr) {
        this.setTransfusionCgr(transfusionCgr);
        return this;
    }

    public void setTransfusionCgr(OuiNonNP transfusionCgr) {
        this.transfusionCgr = transfusionCgr;
    }

    public OuiNonNP getTraitementMartial() {
        return this.traitementMartial;
    }

    public FicheThrombopathie traitementMartial(OuiNonNP traitementMartial) {
        this.setTraitementMartial(traitementMartial);
        return this;
    }

    public void setTraitementMartial(OuiNonNP traitementMartial) {
        this.traitementMartial = traitementMartial;
    }

    public OuiNonNP getThrombose() {
        return this.thrombose;
    }

    public FicheThrombopathie thrombose(OuiNonNP thrombose) {
        this.setThrombose(thrombose);
        return this;
    }

    public void setThrombose(OuiNonNP thrombose) {
        this.thrombose = thrombose;
    }

    public OuiNonNP getAlloImmunisationHLA() {
        return this.alloImmunisationHLA;
    }

    public FicheThrombopathie alloImmunisationHLA(OuiNonNP alloImmunisationHLA) {
        this.setAlloImmunisationHLA(alloImmunisationHLA);
        return this;
    }

    public void setAlloImmunisationHLA(OuiNonNP alloImmunisationHLA) {
        this.alloImmunisationHLA = alloImmunisationHLA;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FicheThrombopathie)) {
            return false;
        }
        return getId() != null && getId().equals(((FicheThrombopathie) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FicheThrombopathie{" +
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

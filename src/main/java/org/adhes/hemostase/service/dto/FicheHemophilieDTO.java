package org.adhes.hemostase.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import org.adhes.hemostase.domain.enumeration.AccidentsGravesType;
import org.adhes.hemostase.domain.enumeration.ArticulationCible;
import org.adhes.hemostase.domain.enumeration.CATTherapeutique;
import org.adhes.hemostase.domain.enumeration.EvolutionInhibiteur;
import org.adhes.hemostase.domain.enumeration.EvolutionSNC;
import org.adhes.hemostase.domain.enumeration.EvolutionVHC;
import org.adhes.hemostase.domain.enumeration.FormeHemophilie;
import org.adhes.hemostase.domain.enumeration.InjectionType;
import org.adhes.hemostase.domain.enumeration.Observance;
import org.adhes.hemostase.domain.enumeration.OuiNonNP;
import org.adhes.hemostase.domain.enumeration.PECTherapeutique;
import org.adhes.hemostase.domain.enumeration.PriseEnChargeType;
import org.adhes.hemostase.domain.enumeration.RechercheACC;
import org.adhes.hemostase.domain.enumeration.SchemaTherapeutique;
import org.adhes.hemostase.domain.enumeration.Serologie;
import org.adhes.hemostase.domain.enumeration.SuiteOperatoire;
import org.adhes.hemostase.domain.enumeration.TauxInhibiteur;
import org.adhes.hemostase.domain.enumeration.TauxRecuperation;
import org.adhes.hemostase.domain.enumeration.TestStatus;
import org.adhes.hemostase.domain.enumeration.TraitementType;
import org.adhes.hemostase.domain.enumeration.TypeComplicationsOrthopediques;
import org.adhes.hemostase.domain.enumeration.TypeHemorragie;
import org.adhes.hemostase.domain.enumeration.TypeHemorragieVisceres;
import org.adhes.hemostase.domain.enumeration.TypeManifestation;
import org.adhes.hemostase.domain.enumeration.TypePreparation;

/**
 * A DTO for the {@link org.adhes.hemostase.domain.FicheHemophilie} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FicheHemophilieDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String dossierNumber;

    @Size(max = 50)
    private String ordreNumber;

    @Size(max = 50)
    private String indexNumber;

    private OuiNonNP parentsConsanguins;

    @Size(max = 50)
    private String degreParenteConsanguins;

    private OuiNonNP casSimilairesFamille;

    private Integer nbreCasSimilaires;

    @Size(max = 50)
    private String degreParenteCasSimilaires;

    private OuiNonNP casDecesSyndromeHemorragique;

    private Integer nbreCasDeces;

    private FormeHemophilie formeHemophilie;

    private Integer nbreFreres;

    private Integer nbreSoeurs;

    private LocalDate dateTestConfirmation;

    private Double hemogrammeHb;

    private Double hemogrammeHt;

    private Integer plaquettes;

    private Double tp;

    private Double fibrinogene;

    private Double tcaMT;

    private Double tcaMT2h;

    private Double tcaTemoin2h;

    private TestStatus ts;

    private OuiNonNP hemarthrose;

    private ArticulationCible siegeHemarthrose;

    private TypeManifestation circonstanceHemarthrose;

    private Integer frequenceHemarthrose;

    private OuiNonNP hematomeSuperficiel;

    private TypeManifestation circonstanceHematomeSuperficiel;

    @Size(max = 50)
    private String siegeHematomeSuperficiel;

    private OuiNonNP hematomePsoas;

    private TypeManifestation circonstanceHematomePsoas;

    private OuiNonNP recidiveHematomePsoas;

    private OuiNonNP hemorragiesCutaneoMuqueuses;

    private TypeHemorragie typeHemorragiesCutaneoMuqueuses;

    private Integer frequenceHemorragiesCutaneoMuqueuses;

    private OuiNonNP hemorragieVisceres;

    private TypeHemorragieVisceres typeHemorragieVisceres;

    private OuiNonNP explorationHemorragieVisceres;

    @Size(max = 50)
    private String examenLesionHemorragieVisceres;

    private OuiNonNP saignementSNC;

    private EvolutionSNC evolutionSNC;

    private OuiNonNP circoncision;

    private TypePreparation typePreparationCirconcision;

    private SuiteOperatoire suiteOperatoireCirconcision;

    private Integer ageCirconcision;

    private OuiNonNP avulsionDentaire;

    private TypePreparation typePreparationAvulsionDentaire;

    private OuiNonNP interventionChirurgicale;

    @Size(max = 50)
    private String typeInterventionChirurgicale;

    private OuiNonNP accidentsHemorragiquesGraves;

    private AccidentsGravesType circonstanceAccidentsHemorragiquesGraves;

    @NotNull
    private PriseEnChargeType priseEnCharge;

    @Size(max = 50)
    private String causePriseEnCharge;

    @Size(max = 20)
    private String doseProphylaxie;

    private Integer frequenceProphylaxie;

    private LocalDate debutProphylaxie;

    private InjectionType modaliteInjection;

    private TraitementType typeTraitementSubstitutif;

    private Integer age1ereSubstitution;

    private OuiNonNP antecedentsPSL;

    private OuiNonNP plasmaFraisCongele;

    private OuiNonNP cryoprecipite;

    private OuiNonNP complicationsOrthopediques;

    private TypeComplicationsOrthopediques typeComplicationsOrthopediques;

    private ArticulationCible articulationCible;

    private PECTherapeutique pecTherapeutique;

    private OuiNonNP complicationsInhibiteurs;

    private TauxInhibiteur tauxInhibiteur;

    private TestStatus testRecuperationFAH;

    private LocalDate dateRecuperationFAH;

    private TauxRecuperation tauxRecuperation;

    private OuiNonNP antiFAHNonNeutralisant;

    private RechercheACC rechercheACC;

    private CATTherapeutique catTherapeutique;

    private OuiNonNP iti;

    @Size(max = 50)
    private String posologieITI;

    @Size(max = 50)
    private String dureeITI;

    private Observance observanceITI;

    @Size(max = 50)
    private String causeNonObservanceITI;

    private EvolutionInhibiteur evolutionInhibiteur;

    private OuiNonNP prophylaxieHemlibra;

    private LocalDate dateDebutHemlibra;

    private SchemaTherapeutique schemaTherapeutique;

    private Integer efficaciteHemlibra;

    private Serologie serologieHBC;

    private Serologie serologieHVC;

    private LocalDate dateDecouverteVHC;

    @Size(max = 50)
    private String chargeViraleVHC;

    private OuiNonNP traitementVHC;

    private EvolutionVHC evolutionVHC;

    private Serologie serologieHIV;

    private PatientDTO patient;

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

    public OuiNonNP getCasSimilairesFamille() {
        return casSimilairesFamille;
    }

    public void setCasSimilairesFamille(OuiNonNP casSimilairesFamille) {
        this.casSimilairesFamille = casSimilairesFamille;
    }

    public Integer getNbreCasSimilaires() {
        return nbreCasSimilaires;
    }

    public void setNbreCasSimilaires(Integer nbreCasSimilaires) {
        this.nbreCasSimilaires = nbreCasSimilaires;
    }

    public String getDegreParenteCasSimilaires() {
        return degreParenteCasSimilaires;
    }

    public void setDegreParenteCasSimilaires(String degreParenteCasSimilaires) {
        this.degreParenteCasSimilaires = degreParenteCasSimilaires;
    }

    public OuiNonNP getCasDecesSyndromeHemorragique() {
        return casDecesSyndromeHemorragique;
    }

    public void setCasDecesSyndromeHemorragique(OuiNonNP casDecesSyndromeHemorragique) {
        this.casDecesSyndromeHemorragique = casDecesSyndromeHemorragique;
    }

    public Integer getNbreCasDeces() {
        return nbreCasDeces;
    }

    public void setNbreCasDeces(Integer nbreCasDeces) {
        this.nbreCasDeces = nbreCasDeces;
    }

    public FormeHemophilie getFormeHemophilie() {
        return formeHemophilie;
    }

    public void setFormeHemophilie(FormeHemophilie formeHemophilie) {
        this.formeHemophilie = formeHemophilie;
    }

    public Integer getNbreFreres() {
        return nbreFreres;
    }

    public void setNbreFreres(Integer nbreFreres) {
        this.nbreFreres = nbreFreres;
    }

    public Integer getNbreSoeurs() {
        return nbreSoeurs;
    }

    public void setNbreSoeurs(Integer nbreSoeurs) {
        this.nbreSoeurs = nbreSoeurs;
    }

    public LocalDate getDateTestConfirmation() {
        return dateTestConfirmation;
    }

    public void setDateTestConfirmation(LocalDate dateTestConfirmation) {
        this.dateTestConfirmation = dateTestConfirmation;
    }

    public Double getHemogrammeHb() {
        return hemogrammeHb;
    }

    public void setHemogrammeHb(Double hemogrammeHb) {
        this.hemogrammeHb = hemogrammeHb;
    }

    public Double getHemogrammeHt() {
        return hemogrammeHt;
    }

    public void setHemogrammeHt(Double hemogrammeHt) {
        this.hemogrammeHt = hemogrammeHt;
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

    public Double getTcaMT2h() {
        return tcaMT2h;
    }

    public void setTcaMT2h(Double tcaMT2h) {
        this.tcaMT2h = tcaMT2h;
    }

    public Double getTcaTemoin2h() {
        return tcaTemoin2h;
    }

    public void setTcaTemoin2h(Double tcaTemoin2h) {
        this.tcaTemoin2h = tcaTemoin2h;
    }

    public TestStatus getTs() {
        return ts;
    }

    public void setTs(TestStatus ts) {
        this.ts = ts;
    }

    public OuiNonNP getHemarthrose() {
        return hemarthrose;
    }

    public void setHemarthrose(OuiNonNP hemarthrose) {
        this.hemarthrose = hemarthrose;
    }

    public ArticulationCible getSiegeHemarthrose() {
        return siegeHemarthrose;
    }

    public void setSiegeHemarthrose(ArticulationCible siegeHemarthrose) {
        this.siegeHemarthrose = siegeHemarthrose;
    }

    public TypeManifestation getCirconstanceHemarthrose() {
        return circonstanceHemarthrose;
    }

    public void setCirconstanceHemarthrose(TypeManifestation circonstanceHemarthrose) {
        this.circonstanceHemarthrose = circonstanceHemarthrose;
    }

    public Integer getFrequenceHemarthrose() {
        return frequenceHemarthrose;
    }

    public void setFrequenceHemarthrose(Integer frequenceHemarthrose) {
        this.frequenceHemarthrose = frequenceHemarthrose;
    }

    public OuiNonNP getHematomeSuperficiel() {
        return hematomeSuperficiel;
    }

    public void setHematomeSuperficiel(OuiNonNP hematomeSuperficiel) {
        this.hematomeSuperficiel = hematomeSuperficiel;
    }

    public TypeManifestation getCirconstanceHematomeSuperficiel() {
        return circonstanceHematomeSuperficiel;
    }

    public void setCirconstanceHematomeSuperficiel(TypeManifestation circonstanceHematomeSuperficiel) {
        this.circonstanceHematomeSuperficiel = circonstanceHematomeSuperficiel;
    }

    public String getSiegeHematomeSuperficiel() {
        return siegeHematomeSuperficiel;
    }

    public void setSiegeHematomeSuperficiel(String siegeHematomeSuperficiel) {
        this.siegeHematomeSuperficiel = siegeHematomeSuperficiel;
    }

    public OuiNonNP getHematomePsoas() {
        return hematomePsoas;
    }

    public void setHematomePsoas(OuiNonNP hematomePsoas) {
        this.hematomePsoas = hematomePsoas;
    }

    public TypeManifestation getCirconstanceHematomePsoas() {
        return circonstanceHematomePsoas;
    }

    public void setCirconstanceHematomePsoas(TypeManifestation circonstanceHematomePsoas) {
        this.circonstanceHematomePsoas = circonstanceHematomePsoas;
    }

    public OuiNonNP getRecidiveHematomePsoas() {
        return recidiveHematomePsoas;
    }

    public void setRecidiveHematomePsoas(OuiNonNP recidiveHematomePsoas) {
        this.recidiveHematomePsoas = recidiveHematomePsoas;
    }

    public OuiNonNP getHemorragiesCutaneoMuqueuses() {
        return hemorragiesCutaneoMuqueuses;
    }

    public void setHemorragiesCutaneoMuqueuses(OuiNonNP hemorragiesCutaneoMuqueuses) {
        this.hemorragiesCutaneoMuqueuses = hemorragiesCutaneoMuqueuses;
    }

    public TypeHemorragie getTypeHemorragiesCutaneoMuqueuses() {
        return typeHemorragiesCutaneoMuqueuses;
    }

    public void setTypeHemorragiesCutaneoMuqueuses(TypeHemorragie typeHemorragiesCutaneoMuqueuses) {
        this.typeHemorragiesCutaneoMuqueuses = typeHemorragiesCutaneoMuqueuses;
    }

    public Integer getFrequenceHemorragiesCutaneoMuqueuses() {
        return frequenceHemorragiesCutaneoMuqueuses;
    }

    public void setFrequenceHemorragiesCutaneoMuqueuses(Integer frequenceHemorragiesCutaneoMuqueuses) {
        this.frequenceHemorragiesCutaneoMuqueuses = frequenceHemorragiesCutaneoMuqueuses;
    }

    public OuiNonNP getHemorragieVisceres() {
        return hemorragieVisceres;
    }

    public void setHemorragieVisceres(OuiNonNP hemorragieVisceres) {
        this.hemorragieVisceres = hemorragieVisceres;
    }

    public TypeHemorragieVisceres getTypeHemorragieVisceres() {
        return typeHemorragieVisceres;
    }

    public void setTypeHemorragieVisceres(TypeHemorragieVisceres typeHemorragieVisceres) {
        this.typeHemorragieVisceres = typeHemorragieVisceres;
    }

    public OuiNonNP getExplorationHemorragieVisceres() {
        return explorationHemorragieVisceres;
    }

    public void setExplorationHemorragieVisceres(OuiNonNP explorationHemorragieVisceres) {
        this.explorationHemorragieVisceres = explorationHemorragieVisceres;
    }

    public String getExamenLesionHemorragieVisceres() {
        return examenLesionHemorragieVisceres;
    }

    public void setExamenLesionHemorragieVisceres(String examenLesionHemorragieVisceres) {
        this.examenLesionHemorragieVisceres = examenLesionHemorragieVisceres;
    }

    public OuiNonNP getSaignementSNC() {
        return saignementSNC;
    }

    public void setSaignementSNC(OuiNonNP saignementSNC) {
        this.saignementSNC = saignementSNC;
    }

    public EvolutionSNC getEvolutionSNC() {
        return evolutionSNC;
    }

    public void setEvolutionSNC(EvolutionSNC evolutionSNC) {
        this.evolutionSNC = evolutionSNC;
    }

    public OuiNonNP getCirconcision() {
        return circoncision;
    }

    public void setCirconcision(OuiNonNP circoncision) {
        this.circoncision = circoncision;
    }

    public TypePreparation getTypePreparationCirconcision() {
        return typePreparationCirconcision;
    }

    public void setTypePreparationCirconcision(TypePreparation typePreparationCirconcision) {
        this.typePreparationCirconcision = typePreparationCirconcision;
    }

    public SuiteOperatoire getSuiteOperatoireCirconcision() {
        return suiteOperatoireCirconcision;
    }

    public void setSuiteOperatoireCirconcision(SuiteOperatoire suiteOperatoireCirconcision) {
        this.suiteOperatoireCirconcision = suiteOperatoireCirconcision;
    }

    public Integer getAgeCirconcision() {
        return ageCirconcision;
    }

    public void setAgeCirconcision(Integer ageCirconcision) {
        this.ageCirconcision = ageCirconcision;
    }

    public OuiNonNP getAvulsionDentaire() {
        return avulsionDentaire;
    }

    public void setAvulsionDentaire(OuiNonNP avulsionDentaire) {
        this.avulsionDentaire = avulsionDentaire;
    }

    public TypePreparation getTypePreparationAvulsionDentaire() {
        return typePreparationAvulsionDentaire;
    }

    public void setTypePreparationAvulsionDentaire(TypePreparation typePreparationAvulsionDentaire) {
        this.typePreparationAvulsionDentaire = typePreparationAvulsionDentaire;
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

    public OuiNonNP getAccidentsHemorragiquesGraves() {
        return accidentsHemorragiquesGraves;
    }

    public void setAccidentsHemorragiquesGraves(OuiNonNP accidentsHemorragiquesGraves) {
        this.accidentsHemorragiquesGraves = accidentsHemorragiquesGraves;
    }

    public AccidentsGravesType getCirconstanceAccidentsHemorragiquesGraves() {
        return circonstanceAccidentsHemorragiquesGraves;
    }

    public void setCirconstanceAccidentsHemorragiquesGraves(AccidentsGravesType circonstanceAccidentsHemorragiquesGraves) {
        this.circonstanceAccidentsHemorragiquesGraves = circonstanceAccidentsHemorragiquesGraves;
    }

    public PriseEnChargeType getPriseEnCharge() {
        return priseEnCharge;
    }

    public void setPriseEnCharge(PriseEnChargeType priseEnCharge) {
        this.priseEnCharge = priseEnCharge;
    }

    public String getCausePriseEnCharge() {
        return causePriseEnCharge;
    }

    public void setCausePriseEnCharge(String causePriseEnCharge) {
        this.causePriseEnCharge = causePriseEnCharge;
    }

    public String getDoseProphylaxie() {
        return doseProphylaxie;
    }

    public void setDoseProphylaxie(String doseProphylaxie) {
        this.doseProphylaxie = doseProphylaxie;
    }

    public Integer getFrequenceProphylaxie() {
        return frequenceProphylaxie;
    }

    public void setFrequenceProphylaxie(Integer frequenceProphylaxie) {
        this.frequenceProphylaxie = frequenceProphylaxie;
    }

    public LocalDate getDebutProphylaxie() {
        return debutProphylaxie;
    }

    public void setDebutProphylaxie(LocalDate debutProphylaxie) {
        this.debutProphylaxie = debutProphylaxie;
    }

    public InjectionType getModaliteInjection() {
        return modaliteInjection;
    }

    public void setModaliteInjection(InjectionType modaliteInjection) {
        this.modaliteInjection = modaliteInjection;
    }

    public TraitementType getTypeTraitementSubstitutif() {
        return typeTraitementSubstitutif;
    }

    public void setTypeTraitementSubstitutif(TraitementType typeTraitementSubstitutif) {
        this.typeTraitementSubstitutif = typeTraitementSubstitutif;
    }

    public Integer getAge1ereSubstitution() {
        return age1ereSubstitution;
    }

    public void setAge1ereSubstitution(Integer age1ereSubstitution) {
        this.age1ereSubstitution = age1ereSubstitution;
    }

    public OuiNonNP getAntecedentsPSL() {
        return antecedentsPSL;
    }

    public void setAntecedentsPSL(OuiNonNP antecedentsPSL) {
        this.antecedentsPSL = antecedentsPSL;
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

    public OuiNonNP getComplicationsOrthopediques() {
        return complicationsOrthopediques;
    }

    public void setComplicationsOrthopediques(OuiNonNP complicationsOrthopediques) {
        this.complicationsOrthopediques = complicationsOrthopediques;
    }

    public TypeComplicationsOrthopediques getTypeComplicationsOrthopediques() {
        return typeComplicationsOrthopediques;
    }

    public void setTypeComplicationsOrthopediques(TypeComplicationsOrthopediques typeComplicationsOrthopediques) {
        this.typeComplicationsOrthopediques = typeComplicationsOrthopediques;
    }

    public ArticulationCible getArticulationCible() {
        return articulationCible;
    }

    public void setArticulationCible(ArticulationCible articulationCible) {
        this.articulationCible = articulationCible;
    }

    public PECTherapeutique getPecTherapeutique() {
        return pecTherapeutique;
    }

    public void setPecTherapeutique(PECTherapeutique pecTherapeutique) {
        this.pecTherapeutique = pecTherapeutique;
    }

    public OuiNonNP getComplicationsInhibiteurs() {
        return complicationsInhibiteurs;
    }

    public void setComplicationsInhibiteurs(OuiNonNP complicationsInhibiteurs) {
        this.complicationsInhibiteurs = complicationsInhibiteurs;
    }

    public TauxInhibiteur getTauxInhibiteur() {
        return tauxInhibiteur;
    }

    public void setTauxInhibiteur(TauxInhibiteur tauxInhibiteur) {
        this.tauxInhibiteur = tauxInhibiteur;
    }

    public TestStatus getTestRecuperationFAH() {
        return testRecuperationFAH;
    }

    public void setTestRecuperationFAH(TestStatus testRecuperationFAH) {
        this.testRecuperationFAH = testRecuperationFAH;
    }

    public LocalDate getDateRecuperationFAH() {
        return dateRecuperationFAH;
    }

    public void setDateRecuperationFAH(LocalDate dateRecuperationFAH) {
        this.dateRecuperationFAH = dateRecuperationFAH;
    }

    public TauxRecuperation getTauxRecuperation() {
        return tauxRecuperation;
    }

    public void setTauxRecuperation(TauxRecuperation tauxRecuperation) {
        this.tauxRecuperation = tauxRecuperation;
    }

    public OuiNonNP getAntiFAHNonNeutralisant() {
        return antiFAHNonNeutralisant;
    }

    public void setAntiFAHNonNeutralisant(OuiNonNP antiFAHNonNeutralisant) {
        this.antiFAHNonNeutralisant = antiFAHNonNeutralisant;
    }

    public RechercheACC getRechercheACC() {
        return rechercheACC;
    }

    public void setRechercheACC(RechercheACC rechercheACC) {
        this.rechercheACC = rechercheACC;
    }

    public CATTherapeutique getCatTherapeutique() {
        return catTherapeutique;
    }

    public void setCatTherapeutique(CATTherapeutique catTherapeutique) {
        this.catTherapeutique = catTherapeutique;
    }

    public OuiNonNP getIti() {
        return iti;
    }

    public void setIti(OuiNonNP iti) {
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

    public Observance getObservanceITI() {
        return observanceITI;
    }

    public void setObservanceITI(Observance observanceITI) {
        this.observanceITI = observanceITI;
    }

    public String getCauseNonObservanceITI() {
        return causeNonObservanceITI;
    }

    public void setCauseNonObservanceITI(String causeNonObservanceITI) {
        this.causeNonObservanceITI = causeNonObservanceITI;
    }

    public EvolutionInhibiteur getEvolutionInhibiteur() {
        return evolutionInhibiteur;
    }

    public void setEvolutionInhibiteur(EvolutionInhibiteur evolutionInhibiteur) {
        this.evolutionInhibiteur = evolutionInhibiteur;
    }

    public OuiNonNP getProphylaxieHemlibra() {
        return prophylaxieHemlibra;
    }

    public void setProphylaxieHemlibra(OuiNonNP prophylaxieHemlibra) {
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

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
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
            ", dossierNumber='" + getDossierNumber() + "'" +
            ", ordreNumber='" + getOrdreNumber() + "'" +
            ", indexNumber='" + getIndexNumber() + "'" +
            ", parentsConsanguins='" + getParentsConsanguins() + "'" +
            ", degreParenteConsanguins='" + getDegreParenteConsanguins() + "'" +
            ", casSimilairesFamille='" + getCasSimilairesFamille() + "'" +
            ", nbreCasSimilaires=" + getNbreCasSimilaires() +
            ", degreParenteCasSimilaires='" + getDegreParenteCasSimilaires() + "'" +
            ", casDecesSyndromeHemorragique='" + getCasDecesSyndromeHemorragique() + "'" +
            ", nbreCasDeces=" + getNbreCasDeces() +
            ", formeHemophilie='" + getFormeHemophilie() + "'" +
            ", nbreFreres=" + getNbreFreres() +
            ", nbreSoeurs=" + getNbreSoeurs() +
            ", dateTestConfirmation='" + getDateTestConfirmation() + "'" +
            ", hemogrammeHb=" + getHemogrammeHb() +
            ", hemogrammeHt=" + getHemogrammeHt() +
            ", plaquettes=" + getPlaquettes() +
            ", tp=" + getTp() +
            ", fibrinogene=" + getFibrinogene() +
            ", tcaMT=" + getTcaMT() +
            ", tcaMT2h=" + getTcaMT2h() +
            ", tcaTemoin2h=" + getTcaTemoin2h() +
            ", ts='" + getTs() + "'" +
            ", hemarthrose='" + getHemarthrose() + "'" +
            ", siegeHemarthrose='" + getSiegeHemarthrose() + "'" +
            ", circonstanceHemarthrose='" + getCirconstanceHemarthrose() + "'" +
            ", frequenceHemarthrose=" + getFrequenceHemarthrose() +
            ", hematomeSuperficiel='" + getHematomeSuperficiel() + "'" +
            ", circonstanceHematomeSuperficiel='" + getCirconstanceHematomeSuperficiel() + "'" +
            ", siegeHematomeSuperficiel='" + getSiegeHematomeSuperficiel() + "'" +
            ", hematomePsoas='" + getHematomePsoas() + "'" +
            ", circonstanceHematomePsoas='" + getCirconstanceHematomePsoas() + "'" +
            ", recidiveHematomePsoas='" + getRecidiveHematomePsoas() + "'" +
            ", hemorragiesCutaneoMuqueuses='" + getHemorragiesCutaneoMuqueuses() + "'" +
            ", typeHemorragiesCutaneoMuqueuses='" + getTypeHemorragiesCutaneoMuqueuses() + "'" +
            ", frequenceHemorragiesCutaneoMuqueuses=" + getFrequenceHemorragiesCutaneoMuqueuses() +
            ", hemorragieVisceres='" + getHemorragieVisceres() + "'" +
            ", typeHemorragieVisceres='" + getTypeHemorragieVisceres() + "'" +
            ", explorationHemorragieVisceres='" + getExplorationHemorragieVisceres() + "'" +
            ", examenLesionHemorragieVisceres='" + getExamenLesionHemorragieVisceres() + "'" +
            ", saignementSNC='" + getSaignementSNC() + "'" +
            ", evolutionSNC='" + getEvolutionSNC() + "'" +
            ", circoncision='" + getCirconcision() + "'" +
            ", typePreparationCirconcision='" + getTypePreparationCirconcision() + "'" +
            ", suiteOperatoireCirconcision='" + getSuiteOperatoireCirconcision() + "'" +
            ", ageCirconcision=" + getAgeCirconcision() +
            ", avulsionDentaire='" + getAvulsionDentaire() + "'" +
            ", typePreparationAvulsionDentaire='" + getTypePreparationAvulsionDentaire() + "'" +
            ", interventionChirurgicale='" + getInterventionChirurgicale() + "'" +
            ", typeInterventionChirurgicale='" + getTypeInterventionChirurgicale() + "'" +
            ", accidentsHemorragiquesGraves='" + getAccidentsHemorragiquesGraves() + "'" +
            ", circonstanceAccidentsHemorragiquesGraves='" + getCirconstanceAccidentsHemorragiquesGraves() + "'" +
            ", priseEnCharge='" + getPriseEnCharge() + "'" +
            ", causePriseEnCharge='" + getCausePriseEnCharge() + "'" +
            ", doseProphylaxie='" + getDoseProphylaxie() + "'" +
            ", frequenceProphylaxie=" + getFrequenceProphylaxie() +
            ", debutProphylaxie='" + getDebutProphylaxie() + "'" +
            ", modaliteInjection='" + getModaliteInjection() + "'" +
            ", typeTraitementSubstitutif='" + getTypeTraitementSubstitutif() + "'" +
            ", age1ereSubstitution=" + getAge1ereSubstitution() +
            ", antecedentsPSL='" + getAntecedentsPSL() + "'" +
            ", plasmaFraisCongele='" + getPlasmaFraisCongele() + "'" +
            ", cryoprecipite='" + getCryoprecipite() + "'" +
            ", complicationsOrthopediques='" + getComplicationsOrthopediques() + "'" +
            ", typeComplicationsOrthopediques='" + getTypeComplicationsOrthopediques() + "'" +
            ", articulationCible='" + getArticulationCible() + "'" +
            ", pecTherapeutique='" + getPecTherapeutique() + "'" +
            ", complicationsInhibiteurs='" + getComplicationsInhibiteurs() + "'" +
            ", tauxInhibiteur='" + getTauxInhibiteur() + "'" +
            ", testRecuperationFAH='" + getTestRecuperationFAH() + "'" +
            ", dateRecuperationFAH='" + getDateRecuperationFAH() + "'" +
            ", tauxRecuperation='" + getTauxRecuperation() + "'" +
            ", antiFAHNonNeutralisant='" + getAntiFAHNonNeutralisant() + "'" +
            ", rechercheACC='" + getRechercheACC() + "'" +
            ", catTherapeutique='" + getCatTherapeutique() + "'" +
            ", iti='" + getIti() + "'" +
            ", posologieITI='" + getPosologieITI() + "'" +
            ", dureeITI='" + getDureeITI() + "'" +
            ", observanceITI='" + getObservanceITI() + "'" +
            ", causeNonObservanceITI='" + getCauseNonObservanceITI() + "'" +
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
            ", patient=" + getPatient() +
            "}";
    }
}

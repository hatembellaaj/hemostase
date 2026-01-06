package org.adhes.hemostase.cli;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import org.adhes.hemostase.config.FicheImportProperties;
import org.adhes.hemostase.domain.enumeration.CivilStatus;
import org.adhes.hemostase.domain.enumeration.CouvertureSociale;
import org.adhes.hemostase.domain.enumeration.DiagnosticType;
import org.adhes.hemostase.domain.enumeration.FormeHemophilie;
import org.adhes.hemostase.domain.enumeration.Gender;
import org.adhes.hemostase.domain.enumeration.OuiNonNP;
import org.adhes.hemostase.domain.enumeration.PriseEnChargeType;
import org.adhes.hemostase.domain.enumeration.Region;
import org.adhes.hemostase.domain.enumeration.Serologie;
import org.adhes.hemostase.domain.enumeration.TraitementType;
import org.adhes.hemostase.service.dto.FicheHemophilieDTO;
import org.adhes.hemostase.service.dto.PatientDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

@Component
@Profile("fiche-import")
public class FicheHemostaseCsvImporter implements org.springframework.boot.CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(FicheHemostaseCsvImporter.class);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private final FicheImportProperties properties;
    private final RestClient.Builder restClientBuilder;

    public FicheHemostaseCsvImporter(RestClient.Builder restClientBuilder, FicheImportProperties properties) {
        this.properties = properties;
        this.restClientBuilder = restClientBuilder;
    }

    @Override
    public void run(String... args) throws Exception {
        validateConfiguration();
        RestClient restClient = restClientBuilder
            .baseUrl(properties.getApiBase())
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + properties.getToken())
            .build();
        LOG.info("Starting fiche hémostase CSV import from {}", properties.getCsvPath());
        try (
            Reader reader = Files.newBufferedReader(properties.getCsvPath());
            CSVParser parser = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build().parse(reader)
        ) {
            for (CSVRecord record : parser) {
                importRecord(restClient, record);
            }
        }
    }

    private void validateConfiguration() {
        Assert.notNull(properties.getCsvPath(), "hemostase.import.csv-path est requis");
        Assert.hasText(properties.getApiBase(), "hemostase.import.api-base est requis");
        Assert.hasText(properties.getToken(), "hemostase.import.token est requis");
        Assert.hasText(properties.getSalt(), "hemostase.import.salt est requis");
    }

    private void importRecord(RestClient restClient, CSVRecord record) throws IOException {
        PatientDTO patientDTO = buildPatient(record);
        patientDTO = restClient.post().uri("/api/patients").body(patientDTO).retrieve().body(PatientDTO.class);

        FicheHemophilieDTO ficheHemophilieDTO = buildFiche(record);
        ficheHemophilieDTO.setPatient(patientDTO);

        restClient.post().uri("/api/fiche-hemophilies").body(ficheHemophilieDTO).retrieve().toBodilessEntity();

        LOG.info("Import OK pour le dossier {} (patient id={})", ficheHemophilieDTO.getDossierNumber(), patientDTO.getId());
    }

    private PatientDTO buildPatient(CSVRecord record) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setPrenom(hashValue(required(record, "patient_prenom")));
        patientDTO.setNom(hashValue(required(record, "patient_nom")));
        patientDTO.setNomJeuneFille(hashValue(record.get("patient_nom_jeune_fille")));
        patientDTO.setProfession(trimToNull(record.get("patient_profession")));
        patientDTO.setSexe(enumValue(record, "patient_sexe", Gender.class, true));
        patientDTO.setOrigine(enumValue(record, "patient_origine", Region.class, false));
        patientDTO.setAutreOrigine(trimToNull(record.get("patient_autre_origine")));
        patientDTO.setAdresse(trimToNull(record.get("patient_adresse")));
        patientDTO.setTelephone(trimToNull(record.get("patient_telephone")));
        patientDTO.setEtatCivil(enumValue(record, "patient_etat_civil", CivilStatus.class, true));
        patientDTO.setCouvertureSociale(enumValue(record, "patient_couverture_sociale", CouvertureSociale.class, true));
        patientDTO.setPrenomPere(hashValue(record.get("patient_prenom_pere")));
        patientDTO.setProfessionPere(trimToNull(record.get("patient_profession_pere")));
        patientDTO.setNomPrenomMere(hashValue(record.get("patient_nom_prenom_mere")));
        patientDTO.setProfessionMere(trimToNull(record.get("patient_profession_mere")));
        patientDTO.setDiagnostic(enumValue(record, "patient_diagnostic", DiagnosticType.class, true));
        patientDTO.setAnneeDiagnostic(integerValue(record.get("patient_annee_diagnostic")));
        patientDTO.setConsentementRegistre(enumValue(record, "patient_consentement_registre", OuiNonNP.class, false));
        return patientDTO;
    }

    private FicheHemophilieDTO buildFiche(CSVRecord record) {
        FicheHemophilieDTO ficheHemophilieDTO = new FicheHemophilieDTO();
        ficheHemophilieDTO.setDossierNumber(required(record, "fiche_dossier_number"));
        ficheHemophilieDTO.setOrdreNumber(trimToNull(record.get("fiche_ordre_number")));
        ficheHemophilieDTO.setIndexNumber(trimToNull(record.get("fiche_index_number")));
        ficheHemophilieDTO.setPriseEnCharge(enumValue(record, "fiche_prise_en_charge", PriseEnChargeType.class, true));
        ficheHemophilieDTO.setFormeHemophilie(enumValue(record, "fiche_forme_hemophilie", FormeHemophilie.class, false));
        ficheHemophilieDTO.setDateTestConfirmation(dateValue(record.get("fiche_date_test_confirmation")));
        ficheHemophilieDTO.setNbreFreres(integerValue(record.get("fiche_nbre_freres")));
        ficheHemophilieDTO.setNbreSoeurs(integerValue(record.get("fiche_nbre_soeurs")));
        ficheHemophilieDTO.setDebutProphylaxie(dateValue(record.get("fiche_debut_prophylaxie")));
        ficheHemophilieDTO.setTypeTraitementSubstitutif(
            enumValue(record, "fiche_type_traitement_substitutif", TraitementType.class, false)
        );
        ficheHemophilieDTO.setSerologieHBC(enumValue(record, "fiche_serologie_hbc", Serologie.class, false));
        ficheHemophilieDTO.setSerologieHVC(enumValue(record, "fiche_serologie_hvc", Serologie.class, false));
        ficheHemophilieDTO.setSerologieHIV(enumValue(record, "fiche_serologie_hiv", Serologie.class, false));
        return ficheHemophilieDTO;
    }

    private String required(CSVRecord record, String header) {
        String value = trimToNull(record.get(header));
        if (value == null) {
            throw new IllegalArgumentException("La colonne obligatoire " + header + " est vide");
        }
        return value;
    }

    private String trimToNull(String value) {
        return StringUtils.isBlank(value) ? null : value.trim();
    }

    private Integer integerValue(String raw) {
        String value = trimToNull(raw);
        if (value == null) {
            return null;
        }
        return Integer.valueOf(value);
    }

    private LocalDate dateValue(String raw) {
        String value = trimToNull(raw);
        if (value == null) {
            return null;
        }
        try {
            return LocalDate.parse(value, DATE_FORMATTER);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Date invalide : " + value, ex);
        }
    }

    private <E extends Enum<E>> E enumValue(CSVRecord record, String header, Class<E> enumClass, boolean required) {
        String value = trimToNull(record.get(header));
        if (value == null) {
            if (required) {
                throw new IllegalArgumentException("La colonne obligatoire " + header + " est vide");
            }
            return null;
        }
        try {
            return Enum.valueOf(enumClass, value.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Valeur inconnue pour " + header + " : " + value, ex);
        }
    }

    private String hashValue(String raw) {
        String value = trimToNull(raw);
        if (value == null) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest((value + properties.getSalt()).getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(hashed.length * 2);
            for (byte b : hashed) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Algorithme SHA-256 indisponible", e);
        }
    }
}

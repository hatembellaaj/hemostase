package tn.med.adhes.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.med.adhes.domain.FicheHemophilieTestSamples.*;
import static tn.med.adhes.domain.PatientTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.med.adhes.web.rest.TestUtil;

class FicheHemophilieTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheHemophilie.class);
        FicheHemophilie ficheHemophilie1 = getFicheHemophilieSample1();
        FicheHemophilie ficheHemophilie2 = new FicheHemophilie();
        assertThat(ficheHemophilie1).isNotEqualTo(ficheHemophilie2);

        ficheHemophilie2.setId(ficheHemophilie1.getId());
        assertThat(ficheHemophilie1).isEqualTo(ficheHemophilie2);

        ficheHemophilie2 = getFicheHemophilieSample2();
        assertThat(ficheHemophilie1).isNotEqualTo(ficheHemophilie2);
    }

    @Test
    void patientTest() {
        FicheHemophilie ficheHemophilie = getFicheHemophilieRandomSampleGenerator();
        Patient patientBack = getPatientRandomSampleGenerator();

        ficheHemophilie.setPatient(patientBack);
        assertThat(ficheHemophilie.getPatient()).isEqualTo(patientBack);

        ficheHemophilie.patient(null);
        assertThat(ficheHemophilie.getPatient()).isNull();
    }
}

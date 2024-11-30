package tn.med.adhes.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.med.adhes.domain.PatientTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.med.adhes.web.rest.TestUtil;

class PatientTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Patient.class);
        Patient patient1 = getPatientSample1();
        Patient patient2 = new Patient();
        assertThat(patient1).isNotEqualTo(patient2);

        patient2.setId(patient1.getId());
        assertThat(patient1).isEqualTo(patient2);

        patient2 = getPatientSample2();
        assertThat(patient1).isNotEqualTo(patient2);
    }
}

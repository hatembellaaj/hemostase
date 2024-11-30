package tn.med.adhes.service.mapper;

import static tn.med.adhes.domain.PatientAsserts.*;
import static tn.med.adhes.domain.PatientTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatientMapperTest {

    private PatientMapper patientMapper;

    @BeforeEach
    void setUp() {
        patientMapper = new PatientMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPatientSample1();
        var actual = patientMapper.toEntity(patientMapper.toDto(expected));
        assertPatientAllPropertiesEquals(expected, actual);
    }
}

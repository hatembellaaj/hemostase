package tn.adhes.hemostase.service.mapper;

import static tn.adhes.hemostase.domain.FicheThrombopathieAsserts.*;
import static tn.adhes.hemostase.domain.FicheThrombopathieTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FicheThrombopathieMapperTest {

    private FicheThrombopathieMapper ficheThrombopathieMapper;

    @BeforeEach
    void setUp() {
        ficheThrombopathieMapper = new FicheThrombopathieMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFicheThrombopathieSample1();
        var actual = ficheThrombopathieMapper.toEntity(ficheThrombopathieMapper.toDto(expected));
        assertFicheThrombopathieAllPropertiesEquals(expected, actual);
    }
}

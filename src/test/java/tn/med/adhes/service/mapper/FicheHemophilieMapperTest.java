package tn.med.adhes.service.mapper;

import static tn.med.adhes.domain.FicheHemophilieAsserts.*;
import static tn.med.adhes.domain.FicheHemophilieTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FicheHemophilieMapperTest {

    private FicheHemophilieMapper ficheHemophilieMapper;

    @BeforeEach
    void setUp() {
        ficheHemophilieMapper = new FicheHemophilieMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFicheHemophilieSample1();
        var actual = ficheHemophilieMapper.toEntity(ficheHemophilieMapper.toDto(expected));
        assertFicheHemophilieAllPropertiesEquals(expected, actual);
    }
}

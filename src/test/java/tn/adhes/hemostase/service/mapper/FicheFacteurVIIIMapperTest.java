package tn.adhes.hemostase.service.mapper;

import static tn.adhes.hemostase.domain.FicheFacteurVIIIAsserts.*;
import static tn.adhes.hemostase.domain.FicheFacteurVIIITestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FicheFacteurVIIIMapperTest {

    private FicheFacteurVIIIMapper ficheFacteurVIIIMapper;

    @BeforeEach
    void setUp() {
        ficheFacteurVIIIMapper = new FicheFacteurVIIIMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFicheFacteurVIIISample1();
        var actual = ficheFacteurVIIIMapper.toEntity(ficheFacteurVIIIMapper.toDto(expected));
        assertFicheFacteurVIIIAllPropertiesEquals(expected, actual);
    }
}

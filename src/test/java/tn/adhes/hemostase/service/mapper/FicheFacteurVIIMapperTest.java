package tn.adhes.hemostase.service.mapper;

import static tn.adhes.hemostase.domain.FicheFacteurVIIAsserts.*;
import static tn.adhes.hemostase.domain.FicheFacteurVIITestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FicheFacteurVIIMapperTest {

    private FicheFacteurVIIMapper ficheFacteurVIIMapper;

    @BeforeEach
    void setUp() {
        ficheFacteurVIIMapper = new FicheFacteurVIIMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFicheFacteurVIISample1();
        var actual = ficheFacteurVIIMapper.toEntity(ficheFacteurVIIMapper.toDto(expected));
        assertFicheFacteurVIIAllPropertiesEquals(expected, actual);
    }
}

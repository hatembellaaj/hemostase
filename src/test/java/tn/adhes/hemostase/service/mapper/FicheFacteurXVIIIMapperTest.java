package tn.adhes.hemostase.service.mapper;

import static tn.adhes.hemostase.domain.FicheFacteurXVIIIAsserts.*;
import static tn.adhes.hemostase.domain.FicheFacteurXVIIITestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FicheFacteurXVIIIMapperTest {

    private FicheFacteurXVIIIMapper ficheFacteurXVIIIMapper;

    @BeforeEach
    void setUp() {
        ficheFacteurXVIIIMapper = new FicheFacteurXVIIIMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFicheFacteurXVIIISample1();
        var actual = ficheFacteurXVIIIMapper.toEntity(ficheFacteurXVIIIMapper.toDto(expected));
        assertFicheFacteurXVIIIAllPropertiesEquals(expected, actual);
    }
}

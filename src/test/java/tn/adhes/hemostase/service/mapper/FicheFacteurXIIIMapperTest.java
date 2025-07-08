package tn.adhes.hemostase.service.mapper;

import static tn.adhes.hemostase.domain.FicheFacteurXIIIAsserts.*;
import static tn.adhes.hemostase.domain.FicheFacteurXIIITestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FicheFacteurXIIIMapperTest {

    private FicheFacteurXIIIMapper ficheFacteurXIIIMapper;

    @BeforeEach
    void setUp() {
        ficheFacteurXIIIMapper = new FicheFacteurXIIIMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFicheFacteurXIIISample1();
        var actual = ficheFacteurXIIIMapper.toEntity(ficheFacteurXIIIMapper.toDto(expected));
        assertFicheFacteurXIIIAllPropertiesEquals(expected, actual);
    }
}

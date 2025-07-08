package tn.adhes.hemostase.service.mapper;

import static tn.adhes.hemostase.domain.FicheFacteurXIAsserts.*;
import static tn.adhes.hemostase.domain.FicheFacteurXITestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FicheFacteurXIMapperTest {

    private FicheFacteurXIMapper ficheFacteurXIMapper;

    @BeforeEach
    void setUp() {
        ficheFacteurXIMapper = new FicheFacteurXIMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFicheFacteurXISample1();
        var actual = ficheFacteurXIMapper.toEntity(ficheFacteurXIMapper.toDto(expected));
        assertFicheFacteurXIAllPropertiesEquals(expected, actual);
    }
}

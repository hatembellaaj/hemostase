package tn.adhes.hemostase.service.mapper;

import static tn.adhes.hemostase.domain.FicheVonWillebrandAsserts.*;
import static tn.adhes.hemostase.domain.FicheVonWillebrandTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FicheVonWillebrandMapperTest {

    private FicheVonWillebrandMapper ficheVonWillebrandMapper;

    @BeforeEach
    void setUp() {
        ficheVonWillebrandMapper = new FicheVonWillebrandMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getFicheVonWillebrandSample1();
        var actual = ficheVonWillebrandMapper.toEntity(ficheVonWillebrandMapper.toDto(expected));
        assertFicheVonWillebrandAllPropertiesEquals(expected, actual);
    }
}

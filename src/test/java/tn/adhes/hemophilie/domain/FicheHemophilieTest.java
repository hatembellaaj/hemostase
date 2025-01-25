package tn.adhes.hemophilie.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.adhes.hemophilie.domain.FicheHemophilieTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.adhes.hemophilie.web.rest.TestUtil;

class FicheHemophilieTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheHemophilie.class);
        FicheHemophilie ficheHemophilie1 = getFicheHemophilieSample1();
        FicheHemophilie ficheHemophilie2 = new FicheHemophilie();
        assertThat(ficheHemophilie1).isNotEqualTo(ficheHemophilie2);

        ficheHemophilie2.setId(ficheHemophilie1.getId());
        assertThat(ficheHemophilie1).isEqualTo(ficheHemophilie2);

        ficheHemophilie2 = getFicheHemophilieSample2();
        assertThat(ficheHemophilie1).isNotEqualTo(ficheHemophilie2);
    }
}

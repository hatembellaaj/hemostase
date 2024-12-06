package org.adhes.hemostase.domain;

import static org.adhes.hemostase.domain.FicheHemophilieTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.adhes.hemostase.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

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

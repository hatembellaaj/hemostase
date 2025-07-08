package tn.adhes.hemostase.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.adhes.hemostase.domain.FicheVonWillebrandTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheVonWillebrandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheVonWillebrand.class);
        FicheVonWillebrand ficheVonWillebrand1 = getFicheVonWillebrandSample1();
        FicheVonWillebrand ficheVonWillebrand2 = new FicheVonWillebrand();
        assertThat(ficheVonWillebrand1).isNotEqualTo(ficheVonWillebrand2);

        ficheVonWillebrand2.setId(ficheVonWillebrand1.getId());
        assertThat(ficheVonWillebrand1).isEqualTo(ficheVonWillebrand2);

        ficheVonWillebrand2 = getFicheVonWillebrandSample2();
        assertThat(ficheVonWillebrand1).isNotEqualTo(ficheVonWillebrand2);
    }
}

package tn.adhes.hemostase.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.adhes.hemostase.domain.FicheFacteurXITestSamples.*;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurXITest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurXI.class);
        FicheFacteurXI ficheFacteurXI1 = getFicheFacteurXISample1();
        FicheFacteurXI ficheFacteurXI2 = new FicheFacteurXI();
        assertThat(ficheFacteurXI1).isNotEqualTo(ficheFacteurXI2);

        ficheFacteurXI2.setId(ficheFacteurXI1.getId());
        assertThat(ficheFacteurXI1).isEqualTo(ficheFacteurXI2);

        ficheFacteurXI2 = getFicheFacteurXISample2();
        assertThat(ficheFacteurXI1).isNotEqualTo(ficheFacteurXI2);
    }
}

package tn.adhes.hemostase.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.adhes.hemostase.domain.FicheFacteurVIITestSamples.*;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurVIITest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurVII.class);
        FicheFacteurVII ficheFacteurVII1 = getFicheFacteurVIISample1();
        FicheFacteurVII ficheFacteurVII2 = new FicheFacteurVII();
        assertThat(ficheFacteurVII1).isNotEqualTo(ficheFacteurVII2);

        ficheFacteurVII2.setId(ficheFacteurVII1.getId());
        assertThat(ficheFacteurVII1).isEqualTo(ficheFacteurVII2);

        ficheFacteurVII2 = getFicheFacteurVIISample2();
        assertThat(ficheFacteurVII1).isNotEqualTo(ficheFacteurVII2);
    }
}

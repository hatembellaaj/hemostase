package tn.adhes.hemostase.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.adhes.hemostase.domain.FicheFacteurVIIITestSamples.*;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurVIIITest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurVIII.class);
        FicheFacteurVIII ficheFacteurVIII1 = getFicheFacteurVIIISample1();
        FicheFacteurVIII ficheFacteurVIII2 = new FicheFacteurVIII();
        assertThat(ficheFacteurVIII1).isNotEqualTo(ficheFacteurVIII2);

        ficheFacteurVIII2.setId(ficheFacteurVIII1.getId());
        assertThat(ficheFacteurVIII1).isEqualTo(ficheFacteurVIII2);

        ficheFacteurVIII2 = getFicheFacteurVIIISample2();
        assertThat(ficheFacteurVIII1).isNotEqualTo(ficheFacteurVIII2);
    }
}

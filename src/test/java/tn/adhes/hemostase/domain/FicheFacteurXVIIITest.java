package tn.adhes.hemostase.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.adhes.hemostase.domain.FicheFacteurXVIIITestSamples.*;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurXVIIITest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurXVIII.class);
        FicheFacteurXVIII ficheFacteurXVIII1 = getFicheFacteurXVIIISample1();
        FicheFacteurXVIII ficheFacteurXVIII2 = new FicheFacteurXVIII();
        assertThat(ficheFacteurXVIII1).isNotEqualTo(ficheFacteurXVIII2);

        ficheFacteurXVIII2.setId(ficheFacteurXVIII1.getId());
        assertThat(ficheFacteurXVIII1).isEqualTo(ficheFacteurXVIII2);

        ficheFacteurXVIII2 = getFicheFacteurXVIIISample2();
        assertThat(ficheFacteurXVIII1).isNotEqualTo(ficheFacteurXVIII2);
    }
}

package tn.adhes.hemostase.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.adhes.hemostase.domain.FicheFacteurXIIITestSamples.*;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurXIIITest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurXIII.class);
        FicheFacteurXIII ficheFacteurXIII1 = getFicheFacteurXIIISample1();
        FicheFacteurXIII ficheFacteurXIII2 = new FicheFacteurXIII();
        assertThat(ficheFacteurXIII1).isNotEqualTo(ficheFacteurXIII2);

        ficheFacteurXIII2.setId(ficheFacteurXIII1.getId());
        assertThat(ficheFacteurXIII1).isEqualTo(ficheFacteurXIII2);

        ficheFacteurXIII2 = getFicheFacteurXIIISample2();
        assertThat(ficheFacteurXIII1).isNotEqualTo(ficheFacteurXIII2);
    }
}

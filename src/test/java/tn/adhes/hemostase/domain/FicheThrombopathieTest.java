package tn.adhes.hemostase.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.adhes.hemostase.domain.FicheThrombopathieTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheThrombopathieTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheThrombopathie.class);
        FicheThrombopathie ficheThrombopathie1 = getFicheThrombopathieSample1();
        FicheThrombopathie ficheThrombopathie2 = new FicheThrombopathie();
        assertThat(ficheThrombopathie1).isNotEqualTo(ficheThrombopathie2);

        ficheThrombopathie2.setId(ficheThrombopathie1.getId());
        assertThat(ficheThrombopathie1).isEqualTo(ficheThrombopathie2);

        ficheThrombopathie2 = getFicheThrombopathieSample2();
        assertThat(ficheThrombopathie1).isNotEqualTo(ficheThrombopathie2);
    }
}

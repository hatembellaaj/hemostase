package tn.adhes.hemostase.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheThrombopathieDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheThrombopathieDTO.class);
        FicheThrombopathieDTO ficheThrombopathieDTO1 = new FicheThrombopathieDTO();
        ficheThrombopathieDTO1.setId(1L);
        FicheThrombopathieDTO ficheThrombopathieDTO2 = new FicheThrombopathieDTO();
        assertThat(ficheThrombopathieDTO1).isNotEqualTo(ficheThrombopathieDTO2);
        ficheThrombopathieDTO2.setId(ficheThrombopathieDTO1.getId());
        assertThat(ficheThrombopathieDTO1).isEqualTo(ficheThrombopathieDTO2);
        ficheThrombopathieDTO2.setId(2L);
        assertThat(ficheThrombopathieDTO1).isNotEqualTo(ficheThrombopathieDTO2);
        ficheThrombopathieDTO1.setId(null);
        assertThat(ficheThrombopathieDTO1).isNotEqualTo(ficheThrombopathieDTO2);
    }
}

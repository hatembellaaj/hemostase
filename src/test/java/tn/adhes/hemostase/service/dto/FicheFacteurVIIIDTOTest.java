package tn.adhes.hemostase.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurVIIIDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurVIIIDTO.class);
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO1 = new FicheFacteurVIIIDTO();
        ficheFacteurVIIIDTO1.setId(1L);
        FicheFacteurVIIIDTO ficheFacteurVIIIDTO2 = new FicheFacteurVIIIDTO();
        assertThat(ficheFacteurVIIIDTO1).isNotEqualTo(ficheFacteurVIIIDTO2);
        ficheFacteurVIIIDTO2.setId(ficheFacteurVIIIDTO1.getId());
        assertThat(ficheFacteurVIIIDTO1).isEqualTo(ficheFacteurVIIIDTO2);
        ficheFacteurVIIIDTO2.setId(2L);
        assertThat(ficheFacteurVIIIDTO1).isNotEqualTo(ficheFacteurVIIIDTO2);
        ficheFacteurVIIIDTO1.setId(null);
        assertThat(ficheFacteurVIIIDTO1).isNotEqualTo(ficheFacteurVIIIDTO2);
    }
}

package tn.adhes.hemostase.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurVIIDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurVIIDTO.class);
        FicheFacteurVIIDTO ficheFacteurVIIDTO1 = new FicheFacteurVIIDTO();
        ficheFacteurVIIDTO1.setId(1L);
        FicheFacteurVIIDTO ficheFacteurVIIDTO2 = new FicheFacteurVIIDTO();
        assertThat(ficheFacteurVIIDTO1).isNotEqualTo(ficheFacteurVIIDTO2);
        ficheFacteurVIIDTO2.setId(ficheFacteurVIIDTO1.getId());
        assertThat(ficheFacteurVIIDTO1).isEqualTo(ficheFacteurVIIDTO2);
        ficheFacteurVIIDTO2.setId(2L);
        assertThat(ficheFacteurVIIDTO1).isNotEqualTo(ficheFacteurVIIDTO2);
        ficheFacteurVIIDTO1.setId(null);
        assertThat(ficheFacteurVIIDTO1).isNotEqualTo(ficheFacteurVIIDTO2);
    }
}

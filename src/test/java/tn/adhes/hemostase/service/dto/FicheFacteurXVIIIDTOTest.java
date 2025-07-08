package tn.adhes.hemostase.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurXVIIIDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurXVIIIDTO.class);
        FicheFacteurXVIIIDTO ficheFacteurXVIIIDTO1 = new FicheFacteurXVIIIDTO();
        ficheFacteurXVIIIDTO1.setId(1L);
        FicheFacteurXVIIIDTO ficheFacteurXVIIIDTO2 = new FicheFacteurXVIIIDTO();
        assertThat(ficheFacteurXVIIIDTO1).isNotEqualTo(ficheFacteurXVIIIDTO2);
        ficheFacteurXVIIIDTO2.setId(ficheFacteurXVIIIDTO1.getId());
        assertThat(ficheFacteurXVIIIDTO1).isEqualTo(ficheFacteurXVIIIDTO2);
        ficheFacteurXVIIIDTO2.setId(2L);
        assertThat(ficheFacteurXVIIIDTO1).isNotEqualTo(ficheFacteurXVIIIDTO2);
        ficheFacteurXVIIIDTO1.setId(null);
        assertThat(ficheFacteurXVIIIDTO1).isNotEqualTo(ficheFacteurXVIIIDTO2);
    }
}

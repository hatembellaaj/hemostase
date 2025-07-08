package tn.adhes.hemostase.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurXIIIDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurXIIIDTO.class);
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO1 = new FicheFacteurXIIIDTO();
        ficheFacteurXIIIDTO1.setId(1L);
        FicheFacteurXIIIDTO ficheFacteurXIIIDTO2 = new FicheFacteurXIIIDTO();
        assertThat(ficheFacteurXIIIDTO1).isNotEqualTo(ficheFacteurXIIIDTO2);
        ficheFacteurXIIIDTO2.setId(ficheFacteurXIIIDTO1.getId());
        assertThat(ficheFacteurXIIIDTO1).isEqualTo(ficheFacteurXIIIDTO2);
        ficheFacteurXIIIDTO2.setId(2L);
        assertThat(ficheFacteurXIIIDTO1).isNotEqualTo(ficheFacteurXIIIDTO2);
        ficheFacteurXIIIDTO1.setId(null);
        assertThat(ficheFacteurXIIIDTO1).isNotEqualTo(ficheFacteurXIIIDTO2);
    }
}

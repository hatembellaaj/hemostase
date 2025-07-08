package tn.adhes.hemostase.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheFacteurXIDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheFacteurXIDTO.class);
        FicheFacteurXIDTO ficheFacteurXIDTO1 = new FicheFacteurXIDTO();
        ficheFacteurXIDTO1.setId(1L);
        FicheFacteurXIDTO ficheFacteurXIDTO2 = new FicheFacteurXIDTO();
        assertThat(ficheFacteurXIDTO1).isNotEqualTo(ficheFacteurXIDTO2);
        ficheFacteurXIDTO2.setId(ficheFacteurXIDTO1.getId());
        assertThat(ficheFacteurXIDTO1).isEqualTo(ficheFacteurXIDTO2);
        ficheFacteurXIDTO2.setId(2L);
        assertThat(ficheFacteurXIDTO1).isNotEqualTo(ficheFacteurXIDTO2);
        ficheFacteurXIDTO1.setId(null);
        assertThat(ficheFacteurXIDTO1).isNotEqualTo(ficheFacteurXIDTO2);
    }
}

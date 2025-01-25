package tn.adhes.hemophilie.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.adhes.hemophilie.web.rest.TestUtil;

class FicheHemophilieDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheHemophilieDTO.class);
        FicheHemophilieDTO ficheHemophilieDTO1 = new FicheHemophilieDTO();
        ficheHemophilieDTO1.setId(1L);
        FicheHemophilieDTO ficheHemophilieDTO2 = new FicheHemophilieDTO();
        assertThat(ficheHemophilieDTO1).isNotEqualTo(ficheHemophilieDTO2);
        ficheHemophilieDTO2.setId(ficheHemophilieDTO1.getId());
        assertThat(ficheHemophilieDTO1).isEqualTo(ficheHemophilieDTO2);
        ficheHemophilieDTO2.setId(2L);
        assertThat(ficheHemophilieDTO1).isNotEqualTo(ficheHemophilieDTO2);
        ficheHemophilieDTO1.setId(null);
        assertThat(ficheHemophilieDTO1).isNotEqualTo(ficheHemophilieDTO2);
    }
}

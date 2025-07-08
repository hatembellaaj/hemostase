package tn.adhes.hemostase.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import tn.adhes.hemostase.web.rest.TestUtil;

class FicheVonWillebrandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FicheVonWillebrandDTO.class);
        FicheVonWillebrandDTO ficheVonWillebrandDTO1 = new FicheVonWillebrandDTO();
        ficheVonWillebrandDTO1.setId(1L);
        FicheVonWillebrandDTO ficheVonWillebrandDTO2 = new FicheVonWillebrandDTO();
        assertThat(ficheVonWillebrandDTO1).isNotEqualTo(ficheVonWillebrandDTO2);
        ficheVonWillebrandDTO2.setId(ficheVonWillebrandDTO1.getId());
        assertThat(ficheVonWillebrandDTO1).isEqualTo(ficheVonWillebrandDTO2);
        ficheVonWillebrandDTO2.setId(2L);
        assertThat(ficheVonWillebrandDTO1).isNotEqualTo(ficheVonWillebrandDTO2);
        ficheVonWillebrandDTO1.setId(null);
        assertThat(ficheVonWillebrandDTO1).isNotEqualTo(ficheVonWillebrandDTO2);
    }
}

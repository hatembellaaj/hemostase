package org.adhes.hemostase.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FicheHemophilieTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static FicheHemophilie getFicheHemophilieSample1() {
        return new FicheHemophilie()
            .id(1L)
            .dossierNumber("dossierNumber1")
            .ordreNumber("ordreNumber1")
            .indexNumber("indexNumber1")
            .degreParenteConsanguins("degreParenteConsanguins1")
            .nbreCasSimilaires(1)
            .degreParenteCasSimilaires("degreParenteCasSimilaires1")
            .nbreCasDeces(1)
            .nbreFreres(1)
            .nbreSoeurs(1)
            .plaquettes(1)
            .frequenceHemarthrose(1)
            .siegeHematomeSuperficiel("siegeHematomeSuperficiel1")
            .frequenceHemorragiesCutaneoMuqueuses(1)
            .examenLesionHemorragieVisceres("examenLesionHemorragieVisceres1")
            .ageCirconcision(1)
            .typeInterventionChirurgicale("typeInterventionChirurgicale1")
            .causePriseEnCharge("causePriseEnCharge1")
            .doseProphylaxie("doseProphylaxie1")
            .frequenceProphylaxie(1)
            .age1ereSubstitution(1)
            .posologieITI("posologieITI1")
            .dureeITI("dureeITI1")
            .causeNonObservanceITI("causeNonObservanceITI1")
            .efficaciteHemlibra(1)
            .chargeViraleVHC("chargeViraleVHC1");
    }

    public static FicheHemophilie getFicheHemophilieSample2() {
        return new FicheHemophilie()
            .id(2L)
            .dossierNumber("dossierNumber2")
            .ordreNumber("ordreNumber2")
            .indexNumber("indexNumber2")
            .degreParenteConsanguins("degreParenteConsanguins2")
            .nbreCasSimilaires(2)
            .degreParenteCasSimilaires("degreParenteCasSimilaires2")
            .nbreCasDeces(2)
            .nbreFreres(2)
            .nbreSoeurs(2)
            .plaquettes(2)
            .frequenceHemarthrose(2)
            .siegeHematomeSuperficiel("siegeHematomeSuperficiel2")
            .frequenceHemorragiesCutaneoMuqueuses(2)
            .examenLesionHemorragieVisceres("examenLesionHemorragieVisceres2")
            .ageCirconcision(2)
            .typeInterventionChirurgicale("typeInterventionChirurgicale2")
            .causePriseEnCharge("causePriseEnCharge2")
            .doseProphylaxie("doseProphylaxie2")
            .frequenceProphylaxie(2)
            .age1ereSubstitution(2)
            .posologieITI("posologieITI2")
            .dureeITI("dureeITI2")
            .causeNonObservanceITI("causeNonObservanceITI2")
            .efficaciteHemlibra(2)
            .chargeViraleVHC("chargeViraleVHC2");
    }

    public static FicheHemophilie getFicheHemophilieRandomSampleGenerator() {
        return new FicheHemophilie()
            .id(longCount.incrementAndGet())
            .dossierNumber(UUID.randomUUID().toString())
            .ordreNumber(UUID.randomUUID().toString())
            .indexNumber(UUID.randomUUID().toString())
            .degreParenteConsanguins(UUID.randomUUID().toString())
            .nbreCasSimilaires(intCount.incrementAndGet())
            .degreParenteCasSimilaires(UUID.randomUUID().toString())
            .nbreCasDeces(intCount.incrementAndGet())
            .nbreFreres(intCount.incrementAndGet())
            .nbreSoeurs(intCount.incrementAndGet())
            .plaquettes(intCount.incrementAndGet())
            .frequenceHemarthrose(intCount.incrementAndGet())
            .siegeHematomeSuperficiel(UUID.randomUUID().toString())
            .frequenceHemorragiesCutaneoMuqueuses(intCount.incrementAndGet())
            .examenLesionHemorragieVisceres(UUID.randomUUID().toString())
            .ageCirconcision(intCount.incrementAndGet())
            .typeInterventionChirurgicale(UUID.randomUUID().toString())
            .causePriseEnCharge(UUID.randomUUID().toString())
            .doseProphylaxie(UUID.randomUUID().toString())
            .frequenceProphylaxie(intCount.incrementAndGet())
            .age1ereSubstitution(intCount.incrementAndGet())
            .posologieITI(UUID.randomUUID().toString())
            .dureeITI(UUID.randomUUID().toString())
            .causeNonObservanceITI(UUID.randomUUID().toString())
            .efficaciteHemlibra(intCount.incrementAndGet())
            .chargeViraleVHC(UUID.randomUUID().toString());
    }
}

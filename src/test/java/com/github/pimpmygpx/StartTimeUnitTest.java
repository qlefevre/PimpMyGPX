package com.github.pimpmygpx;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Junit pour tester l'option --start-time : changer l'heure de début
 */
public class StartTimeUnitTest extends AbstractUnitTest{

    @Test
    public void testOptionStartTime() throws IOException {

        testGpx(GPX_LANDASIENNE, gpx -> {
            // start time 2023-10-15T07:50:39Z fichier 9h50 (GMT+2)

            // On change l'heure à 12h12
            LocalTime localTime = LocalTime.parse("12:12", DateTimeFormatter.ISO_LOCAL_TIME);
            gpx = GpxUtils.changeStartTime(gpx,localTime);

            // Mise à jour du temps au niveau des metadata
            gpx = GpxUtils.updateMetadata(gpx);

            // Tests
            assertEquals("2023-10-15T10:12:39Z", gpx.getMetadata().get().getTime().get().toString(),
                    "Metadata - L'heure de début n'est pas correcte.");
            assertEquals("2023-10-15T10:12:39Z", gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getTime().get().toString(),
                    "Metadata - L'heure de début n'est pas correcte.");
        });
    }

    @Test
    public void testMainOptionStartTime() throws Exception {
        testMain(GPX_LANDASIENNE, new String[]{"-s", "14:14"}, gpx -> {
            // start time 2023-10-15T07:50:39Z fichier 9h50 (GMT+2)
            // On change l'heure de fin à 15h15

            // Tests
            assertEquals("2023-10-15T12:14:39Z", gpx.getMetadata().get().getTime().get().toString(),
                    "Metadata - L'heure de début n'est pas correcte.");
            assertEquals("2023-10-15T12:14:39Z", gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getTime().get().toString(),
                    "Metadata - L'heure de début n'est pas correcte.");
        });
    }

}

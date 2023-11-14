package com.github.pimpmygpx;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Junit pour tester l'option --date : changer la date
 */
public class DateUnitTest extends AbstractUnitTest{



    @Test
    public void testOptionDate() throws IOException {
        testGpx(GPX_LANDASIENNE, gpx -> {

            // start time 2023-10-15T07:50:39Z fichier 9h50 (GMT+2)

            // On change la date au 24/12/2023
            LocalDate localDate = LocalDate.parse("2023-12-24", DateTimeFormatter.ISO_LOCAL_DATE);
            gpx = GpxUtils.changeDate(gpx,localDate);

            // Mise à jour du temps au niveau des metadata
            gpx = GpxUtils.updateMetadata(gpx);

            // Tests
            assertEquals("2023-12-24T07:50:39Z", gpx.getMetadata().get().getTime().get().toString(),
                    "Metadata - La date n'est pas correcte.");
            assertEquals("2023-12-24T07:50:39Z", gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getTime().get().toString(),
                    "Metadata - La date n'est pas correcte.");
        });
    }

    @Test
    public void testMainOptionDate() throws Exception {
        testMain(GPX_LANDASIENNE, new String[]{"-d", "2023-12-24"}, gpx -> {
            // start time 2023-10-15T07:50:39Z fichier 9h50 (GMT+2)
            // changement au 24/12/2023
            // Tests
            assertEquals("2023-12-24T07:50:39Z", gpx.getMetadata().get().getTime().get().toString(),
                    "Metadata - La date n'est pas correcte.");
            assertEquals("2023-12-24T07:50:39Z", gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getTime().get().toString(),
                    "Metadata - La date n'est pas correcte.");
        });
    }


}

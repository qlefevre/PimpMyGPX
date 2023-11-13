package com.github.pimpmygpx;

import io.jenetics.jpx.GPX;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Junit pour tester l'option --start-time : changer l'heure de début
 */
public class StartTimeUnitTest {

    public static final String FILE = "/La_Landasienne_2023_5Km_20231015.gpx";

    @Test
    public void testOptionStartTime() throws IOException {

        try(InputStream is = getClass().getResourceAsStream(FILE)) {
            GPX gpx = GPX.Reader.DEFAULT.read(is);

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
        }
    }

}

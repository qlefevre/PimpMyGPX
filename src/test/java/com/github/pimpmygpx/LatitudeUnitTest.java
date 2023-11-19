package com.github.pimpmygpx;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Junit pour tester l'option --lat : modifier la latitude d'un fichier GPX
 */
public class LatitudeUnitTest extends AbstractUnitTest {

    @Test
    public void testOptionLatitude() throws IOException {
        testGpx(GPX_LANDASIENNE, gpx -> {

            // <trkpt lat="50.4737550" lon="3.3012980">
            // <trkpt lat="50.4737590" lon="3.3012820">

            // On change la latitude de 0.0011
            gpx = GpxUtils.moveWayPoints(gpx,0.0011,0);

            // Tests
            assertEquals(50.4748550, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getLatitude().doubleValue(),
                    "La latitude n'est pas correcte.");
            assertEquals(50.4748590, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(1).getLatitude().doubleValue(),
                    "La latitude n'est pas correcte.");
        });
    }

    @Test
    public void testMainOptionLatitude() throws Exception {
        testMain(GPX_LANDASIENNE, new String[]{"-lat", "0.0011"}, gpx -> {
            // <trkpt lat="50.4737550" lon="3.3012980">
            // <trkpt lat="50.4737590" lon="3.3012820">

            // Tests
            assertEquals(50.4748550, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getLatitude().doubleValue(),
                    "La latitude n'est pas correcte.");
            assertEquals(50.4748590, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(1).getLatitude().doubleValue(),
                    "La latitude n'est pas correcte.");
        });
    }

}


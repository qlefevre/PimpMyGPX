package com.github.pimpmygpx;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.WayPoint;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Junit pour tester l'option --finish-time : changer l'heure de fin
 */
public class FinishTimeUnitTest extends AbstractUnitTest{

    public static final String FILE = "/La_Landasienne_2023_5Km_20231015.gpx";

    @Test
    public void testOptionFinishTime() throws IOException {
        testGpx(GPX_LANDASIENNE, gpx -> {

            // finish time 2023-10-15T08:18:40Z fichier 10h18 (GMT+2)

            // On change l'heure de fin à 15h15
            LocalTime localTime = LocalTime.parse("15:15", DateTimeFormatter.ISO_LOCAL_TIME);
            gpx = GpxUtils.changeFinishTime(gpx,localTime);

            // Tests;
            List<WayPoint> points = gpx.getTracks().get(0).getSegments().get(0).getPoints();
            assertEquals("2023-10-15T13:15:40Z", points.get(points.size()-1).getTime().get().toString(),
                    "Metadata - L'heure de fin n'est pas correcte.");
        });
    }

    @Test
    public void testMainOptionFinishTime() throws Exception {
        testMain(GPX_LANDASIENNE, new String[]{"-f", "15:15"}, gpx -> {
            // start time 2023-10-15T07:50:39Z fichier 9h50 (GMT+2)
            // On change l'heure de fin à 15h15

            // Tests
            List<WayPoint> points = gpx.getTracks().get(0).getSegments().get(0).getPoints();
            assertEquals("2023-10-15T13:15:40Z", points.get(points.size()-1).getTime().get().toString(),
                    "Metadata - L'heure de fin n'est pas correcte.");
        });
    }

}

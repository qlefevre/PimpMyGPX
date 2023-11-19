package com.github.pimpmygpx;

import io.jenetics.jpx.WayPoint;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Junit pour tester l'option --output : changer le nom du fichier de sortie
 */
public class OutputUnitTest extends AbstractUnitTest{

    @Test
    public void testMainOptionOutput() throws Exception {;
        testMain(GPX_LANDASIENNE,"test_output_file.gpx", new String[]{"-f", "15:15","-o","test_output_file.gpx"}, gpx -> {
            // Tests
            List<WayPoint> points = gpx.getTracks().get(0).getSegments().get(0).getPoints();
            assertEquals("2023-10-15T13:15:40Z", points.get(points.size()-1).getTime().get().toString(),
                    "Metadata - L'heure de fin n'est pas correcte.");
        });
    }

}

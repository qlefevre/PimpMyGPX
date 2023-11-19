package com.github.pimpmygpx;

import io.jenetics.jpx.GPX;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Junit pour tester l'option --info : afficher les infos d'un gpx
 */
public class InfoUnitTest extends AbstractUnitTest {

    private static final String EXPECTED_INFO = "Date: 2023-10-15\t\t\tDurée: 0:28\n" +
            "Heure de début: 09:50:39\tHeure de fin: 10:18:40\n" +
            "Distance: 4.81 km\t\t\tAllure: 5:49 /km";

    @Test
    public void testOptionInfo() throws IOException {
        testGpx(GPX_LANDASIENNE, gpx -> {

            // start time 2023-10-15T07:50:39Z fichier 9h50 (GMT+2)

            // On charge les infos du fichier
            String info = GpxUtils.info(gpx);

            assertEquals(EXPECTED_INFO, info,"Le message d'info n'est pas correct.");
        });
    }

    @Test
    public void testMainOptionStartTime() throws Exception {
        // Set the standard output to use newConsole.
        ByteArrayOutputStream newConsole = new ByteArrayOutputStream();
        System.setOut(new PrintStream(newConsole));

        testMain(GPX_LANDASIENNE, new String[]{"-i"}, gpx -> {
            // start time 2023-10-15T07:50:39Z fichier 9h50 (GMT+2)
            assertEquals(EXPECTED_INFO, newConsole.toString().trim(),"Le message d'info n'est pas correct.");
        });
    }

}


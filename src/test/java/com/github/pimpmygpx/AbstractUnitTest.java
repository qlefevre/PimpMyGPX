package com.github.pimpmygpx;

import io.jenetics.jpx.GPX;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.util.TimeZone;
import java.util.function.Consumer;

public class AbstractUnitTest {

    public static final String GPX_LANDASIENNE = "/La_Landasienne_2023_5Km_20231015.gpx";

    public void testGpx(String ressourceFilename, Consumer<GPX> function) throws IOException {
        try(InputStream is = getClass().getResourceAsStream(ressourceFilename)) {
            GPX gpx = GPX.Reader.DEFAULT.read(is);
           function.accept(gpx);
        }
    }

    @BeforeAll
    public static void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Paris"));
    }

}

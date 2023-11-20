package com.github.pimpmygpx;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  Junit pour tester l'option --lon : modifier la longitude d'un fichier GPX
 */
public class LongitudeUnitTest extends AbstractUnitTest {

    @Test
    public void testOptionLongitude() throws IOException {
        testGpx(GPX_LANDASIENNE, gpx -> {

            // <trkpt lat="50.4737550" lon="3.3012980">
            // <trkpt lat="50.4737590" lon="3.3012820">

            // On change la latitude de 0.0011
            gpx = GpxUtils.moveWayPoints(gpx,0,0.0011);

            // Tests
            assertEquals(3.3023980, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getLongitude().doubleValue(),
                    "La longitude n'est pas correcte.");
            assertEquals(3.3023820, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(1).getLongitude().doubleValue(),
                    "La longitude n'est pas correcte.");
        });
    }

    @Test
    public void testMainOptionLongitude() throws Exception {
        testMain(GPX_LANDASIENNE, new String[]{"-lon", "0.0011"}, gpx -> {
            // <trkpt lat="50.4737550" lon="3.3012980">
            // <trkpt lat="50.4737590" lon="3.3012820">

            // Tests
            assertEquals(3.3023980, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getLongitude().doubleValue(),
                    "La longitude n'est pas correcte.");
            assertEquals(3.3023820, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(1).getLongitude().doubleValue(),
                    "La longitude n'est pas correcte.");
        });
    }

    @Test
    public void testMainOptionLongitudeDouai() throws Exception {
        testMain(GPX_DOUAI, new String[]{"-lon", "0.011"}, gpx -> {
            // <trkpt lat="50.3797900" lon="3.0748300">
            // <trkpt lat="50.3798120" lon="3.0747730">

            // Tests
            assertEquals(3.0858300, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getLongitude().doubleValue(),
                    "La longitude n'est pas correcte.");
            assertEquals(3.0857730, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(1).getLongitude().doubleValue(),
                    "La longitude n'est pas correcte.");
        });
    }

    @Test
    public void testMainOptionLongitudeLaBassee() throws Exception {
        testMain(GPX_LABASSEE, new String[]{"-lon", "0.00078"}, gpx -> {
            // <trkpt lat="50.53101348876953" lon="2.8016600608825684">
            // <trkpt lat="50.53101348876953" lon="2.801661729812622">

            // Tests
            assertEquals(2.8024401, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(0).getLongitude().doubleValue(),
                    "La longitude n'est pas correcte.");
            assertEquals(2.8024417, gpx.getTracks().get(0).getSegments().get(0).getPoints().get(1).getLongitude().doubleValue(),
                    "La longitude n'est pas correcte.");
        });
    }

}


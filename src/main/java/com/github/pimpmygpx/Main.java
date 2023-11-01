package com.github.pimpmygpx;

import io.jenetics.jpx.GPX;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        // Press Alt+Entrée with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Hello and welcome!");

        GPX gpx = GPX.read(Paths.get(args[0]));


    /*
        Optional<Instant> max = streamWayPointTimes(gpx).max(Instant::compareTo);
        System.out.println("max "+max);

        gpx = GpxUtils.changeFinishTime(gpx,13,49);
        Optional<Instant> max2 = streamWayPointTimes(gpx).max(Instant::compareTo);
        System.out.println("max "+max2);

        */

        /*


        Optional<Instant> min = streamWayPointTimes(gpx).min(Instant::compareTo);
        System.out.println("min "+min);

        gpx = GpxUtils.changeStartTime(gpx,18,07);
        Optional<Instant> min2 = streamWayPointTimes(gpx).min(Instant::compareTo);
        System.out.println("min "+min2);


         */

        gpx = GpxUtils.changeDate(gpx, LocalDate.parse("2023-02-06"));
        GPX.write(gpx, Paths.get(args[0] + ".out.gpx"));


    }
}
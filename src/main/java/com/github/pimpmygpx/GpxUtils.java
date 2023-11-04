package com.github.pimpmygpx;

import io.jenetics.jpx.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Locale.ENGLISH;

public class GpxUtils {

    public static Stream<Instant> streamWayPointTimes(GPX gpx) {
        return gpx.tracks()
                .flatMap(Track::segments)
                .flatMap(TrackSegment::points)
                .map(WayPoint::getTime)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public static GPX changeFinishTime(GPX gpx, LocalTime localTime) {
        int hours = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
        int minutes = localTime.get(ChronoField.MINUTE_OF_HOUR);
        Optional<Instant> max = streamWayPointTimes(gpx).max(Instant::compareTo);
        if (max.isPresent()) {
            Instant currentFinishTimeInstant = max.get();
            LocalDateTime currentFinishTimeLdt = LocalDateTime.ofInstant(currentFinishTimeInstant, ZoneId.systemDefault());
            int currentFinishMinutes = 60 * currentFinishTimeLdt.getHour()
                    + currentFinishTimeLdt.getMinute();
            int finalFinishMinutes = 60 * hours + minutes;
            int deltaMinutes = finalFinishMinutes - currentFinishMinutes;
            return addXMinutesToAllWayPoints(gpx, deltaMinutes);
        }
        return null;
    }

    public static GPX changeStartTime(GPX gpx, LocalTime localTime) {
        int hours = localTime.get(ChronoField.CLOCK_HOUR_OF_DAY);
        int minutes = localTime.get(ChronoField.MINUTE_OF_HOUR);
        Optional<Instant> min = streamWayPointTimes(gpx).min(Instant::compareTo);
        if (min.isPresent()) {
            Instant currentStartTimeInstant = min.get();
            LocalDateTime currentStartTimeLdt = LocalDateTime.ofInstant(currentStartTimeInstant, ZoneId.systemDefault());
            int currentFinishMinutes = 60 * currentStartTimeLdt.getHour()
                    + currentStartTimeLdt.getMinute();
            int finalStartMinutes = 60 * hours + minutes;
            int deltaMinutes = finalStartMinutes - currentFinishMinutes;
            return addXMinutesToAllWayPoints(gpx, deltaMinutes);
        }
        return null;
    }

    public static GPX changeDate(GPX gpx, LocalDate localdate) {
        Optional<Instant> min = streamWayPointTimes(gpx).min(Instant::compareTo);
        if (min.isPresent()) {
            Instant currentStartTimeInstant = min.get();
            LocalDateTime currentStartTimeLdt = LocalDateTime.ofInstant(currentStartTimeInstant, ZoneId.systemDefault());
            int currentDayOfyear = currentStartTimeLdt.getDayOfYear();
            int finalDayOfYear = localdate.getDayOfYear();
            int deltaMinutes = (finalDayOfYear - currentDayOfyear) * 1440;
            return addXMinutesToAllWayPoints(gpx, deltaMinutes);
        }
        return null;
    }

    public static GPX moveWayPoints(GPX gpx, double deltaLatitude, double deltaLongitude) {
        // Modifier latitude longitude
        return applyToAllWayPoints(gpx, point ->
            point.toBuilder()
                .lat(point.getLatitude().doubleValue() + deltaLatitude)
                .lon(point.getLongitude().doubleValue() + deltaLongitude)
            .build());
    }

    private static GPX applyToAllWayPoints(GPX gpx, Function<WayPoint,WayPoint> function) {
        List<Track> tracks = gpx.tracks().map(track -> {
            List<TrackSegment> segments = track.segments().map(segment -> {
                List<WayPoint> points = segment.points().map(function).collect(Collectors.toList());
                return segment.toBuilder().points(points).build();
            }).collect(Collectors.toList());
            return track.toBuilder().segments(segments).build();
        }).collect(Collectors.toList());
        return gpx.toBuilder().tracks(tracks).build();
    }

    private static GPX addXMinutesToAllWayPoints(GPX gpx, int plusMinutes) {
        // Calculer différence
        return applyToAllWayPoints(gpx, point ->
                point.toBuilder().time(point.getTime().get()
                        .plus(plusMinutes, ChronoUnit.MINUTES)).build());
    }

    public static GPX removeElevations(GPX gpx) {
        boolean allElevationsEqualZero = gpx.tracks()
                .flatMap(Track::segments)
                .flatMap(TrackSegment::points)
                .map(WayPoint::getElevation)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .allMatch(ele -> ele.intValue() == 0);

        // Si tous les éléments sont égals à zéro
        if(allElevationsEqualZero) {
            GPX rGPX = applyToAllWayPoints(gpx, point ->
                point.toBuilder().ele(null).build());
            return rGPX;
        }else{
            return gpx;
        }
    }



}

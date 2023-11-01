package com.github.pimpmygpx;

import io.jenetics.jpx.GPX;
import io.jenetics.jpx.Track;
import io.jenetics.jpx.TrackSegment;
import io.jenetics.jpx.WayPoint;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GpxUtils {

    public static Stream<Instant> streamWayPointTimes(GPX gpx) {
        return gpx.tracks()
                .flatMap(Track::segments)
                .flatMap(TrackSegment::points)
                .map(WayPoint::getTime)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    public static GPX changeFinishTime(GPX gpx, int hours, int minutes) {
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

    public static GPX changeStartTime(GPX gpx, int hours, int minutes) {
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
            int deltaMinutes = (finalDayOfYear - currentDayOfyear)*1440;
            return addXMinutesToAllWayPoints(gpx, deltaMinutes);
        }
        return null;
    }

    public static GPX moveWayPoints(GPX gpx, double deltaLatitude, double deltaLongitude) {
        // Calculer différence
        List<Track> tracks =
                gpx.tracks().map(track -> {
                    List<TrackSegment> segments = track.segments().map(segment -> {
                        List<WayPoint> points = segment.points().map(point ->
                                        point.toBuilder()
                                                .lat(point.getLatitude().doubleValue()
                                                        + deltaLatitude)
                                                .lon(point.getLongitude().doubleValue() + deltaLongitude)
                                                .build())
                                .collect(Collectors.toList());
                        return segment.toBuilder().points(points).build();
                    }).collect(Collectors.toList());
                    return track.toBuilder().segments(segments).build();
                }).collect(Collectors.toList());
        return gpx.toBuilder().tracks(tracks).build();
    }

    private static GPX addXMinutesToAllWayPoints(GPX gpx, int plusMinutes) {
        // Calculer différence
        List<Track> tracks =
                gpx.tracks().map(track -> {
                    List<TrackSegment> segments = track.segments().map(segment -> {
                        List<WayPoint> points = segment.points().map(point ->
                                        point.toBuilder().time(point.getTime().get()
                                                .plus(plusMinutes, ChronoUnit.MINUTES)).build())
                                .collect(Collectors.toList());
                        return segment.toBuilder().points(points).build();
                    }).collect(Collectors.toList());
                    return track.toBuilder().segments(segments).build();
                }).collect(Collectors.toList());
        return gpx.toBuilder().tracks(tracks).build();
    }


}

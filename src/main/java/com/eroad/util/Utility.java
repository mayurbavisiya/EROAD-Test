package com.eroad.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.TimeZone;

import com.google.maps.GeoApiContext;
import com.google.maps.TimeZoneApi;
import com.google.maps.model.LatLng;

/**
 *
 * @author Mayur
 */
public class Utility {

    private static final String API_KEY = Constants.getString("API_KEY");
    private static GeoApiContext context = new GeoApiContext().setApiKey(API_KEY);

    /**
     *
     * @param latLng
     * @return
     */
    public Optional<TimeZone> getTimeZoneFromGoogle(LatLng latLng) {
        Optional<TimeZone> timezone = Optional.empty();
        try {
            timezone = Optional.of(TimeZoneApi.getTimeZone(context, latLng).await());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timezone;
    }

    /**
     *
     * @return
     */
    public DateTimeFormatter getDateTimeFormat() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneOffset.UTC);
    }

    /**
     *
     * @param dateTime
     * @return
     */
    public LocalDateTime getLocalDate(String dateTime) {
        final DateTimeFormatter UTCTimeFormat = getDateTimeFormat();
        final ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime, UTCTimeFormat);
        return LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneId.of("Pacific/Auckland"));
    }
}

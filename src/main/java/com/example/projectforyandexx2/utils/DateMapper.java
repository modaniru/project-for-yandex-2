package com.example.projectforyandexx2.utils;

import com.example.projectforyandexx2.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateMapper {
    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

    public Long toMilli(String date) {
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter);
            return zonedDateTime.toInstant().toEpochMilli();
        } catch (Exception e) {
            throw new RestException("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    public String toString(Long milli) {
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(
                    Instant.ofEpochMilli(milli), ZoneId.of("UTC"));
            return zonedDateTime.format(formatter);
        } catch (Exception e) {
            throw new RestException("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    public Long toMilliMinusDay(String date) {
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter).minusDays(1);
            return zonedDateTime.toInstant().toEpochMilli();
        } catch (Exception e) {
            throw new RestException("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }
}

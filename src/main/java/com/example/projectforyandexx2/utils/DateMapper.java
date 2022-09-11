package com.example.projectforyandexx2.utils;

import com.example.projectforyandexx2.exception.RestException;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Mapper
public class DateMapper {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

    @Named("stringDateToLong")
    public Long stringDateToLong(String date) {
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.parse(date, formatter);
            return zonedDateTime.toInstant().toEpochMilli();
        } catch (Exception e) {
            throw new RestException("Validation Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @Named("longToStringDate")
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

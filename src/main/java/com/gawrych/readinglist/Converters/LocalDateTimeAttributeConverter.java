package com.gawrych.readinglist.Converters;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp date) {
        return date==null ? null : date.toLocalDateTime();
    }
}

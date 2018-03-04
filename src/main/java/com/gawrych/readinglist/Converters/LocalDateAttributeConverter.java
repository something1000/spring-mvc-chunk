package com.gawrych.readinglist.Converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.sql.Date;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate,Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        if(localDate != null){
            return Date.valueOf(localDate);
        }
        return null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        if(date != null){
            return date.toLocalDate();
        }
        return null;
    }
}

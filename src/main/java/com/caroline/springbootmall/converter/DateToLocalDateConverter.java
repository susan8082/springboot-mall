package com.caroline.springbootmall.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Converter(autoApply = true)
public class DateToLocalDateConverter implements AttributeConverter<LocalDateTime, Date> {

        @Override
        public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
            return java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault())
                            .toInstant());
        }

        @Override
        public LocalDateTime convertToEntityAttribute(Date date) {
            return date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        }

}

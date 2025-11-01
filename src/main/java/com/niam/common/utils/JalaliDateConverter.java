package com.niam.common.utils;

import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.calendar.PersianCalendar;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JalaliDateConverter {

    private JalaliDateConverter() {
    }

    public static String convertDate(LocalDateTime dateTime) {
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        Moment moment = Moment.from(zonedDateTime.toInstant());
        PlainDate gregorian = moment.toLocalTimestamp().toDate();
        PersianCalendar persianDate = gregorian.transform(PersianCalendar.axis());

        return String.format("%04d/%02d/%02d",
                persianDate.getYear(),
                persianDate.getMonth().getValue(),
                persianDate.getDayOfMonth());
    }
}

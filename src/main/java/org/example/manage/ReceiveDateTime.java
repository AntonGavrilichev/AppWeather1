package org.example.manage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

//Класс для получения даты и времени
public class ReceiveDateTime {

    //Геттер на форматированную дату
    public static String getCurrentDate() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy\nEEEE", Locale.getDefault());
        return date.format(formatter);
    }

    //Геттер на форматированное время
    public static String getCurrentTime()  {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
        return time.format(formatter);
    }

    //Геттер на форматированную Датуи время захода солнца в Севастополе
    public  static String getSunEventSvs(long sunTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault());
        String formattedDtm = Instant.ofEpochSecond(sunTime) //Метка времени для захода солнца
                .atZone(ZoneId.of("GMT+3"))         //в зоне времени +3 часа по гринвичу
                .format(formatter);                     //в определнном формате
        return formattedDtm;
    }

    //Метод для настройки формата отображения даты и времени
    public static String formattingDateTime(String oldDateTime) {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //Обычный формат даты и времени
        try {
            //Перевод в новый формат даты и времени
            Date date = dt.parse(oldDateTime);
            SimpleDateFormat newDateTime = new SimpleDateFormat("dd.MM.yyyy в HH:mm");
            oldDateTime = newDateTime.format(date);
        } catch (ParseException e) {    //обработка исключения по парсингу даты
            throw new RuntimeException(e);
        }
        return oldDateTime;
    }

}

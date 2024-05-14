package org.example.receive_json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

//Класс для получения, обработки и возврата URl
public class UrlContent {

    public static String getUrlContent(String urlAdress) {

        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlAdress);  //Создание объекта класса URL с переданным параметром
            URLConnection urlConnection = url.openConnection(); //Создаем объект класса URLConnection и
                                                                // возвращаем объект, который дает соединение с
                                                                //удаленным объектом, на который указывает URL-адрес.

            //Считываем текст из потока ввода с URL
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            //Редактируем формат строки
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();

        } catch (Exception ignore) {

        }

        return content.toString();
    }

}



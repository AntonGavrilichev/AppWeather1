package org.example.manage;

import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//Класс настроек конфигурации
@Data
public class ConfigSettings {
    public static final String FILE_NAME = "config.properties"; //константа имени файла, где лежат API и URl
    private static ConfigSettings instance = new ConfigSettings(); //Объект текущего класса
    private Properties properties;  //объект класса Properties (будущие характеристики)
    private String api;     //Переменная API
    private String urlSvs;  //Переменная URL Севастополя
    private String urlCurrent;  //Переменная URL прогноза для введенного пользователем города
    private String urlForecast; //Переменная URL проноза для введенного пользователем города на 5 дней

    //Геттер объекта текущего класса
    public static ConfigSettings getInstance() {
         return instance;
    }


    //Блок инициализации с обработкой исключений RuntimeException или IOException e
    {
        try {
            properties = new Properties(); //новый объект характеристики

            //Вернем список свойст в потоке из файла FILE_NAME = config.properties
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
                properties.load(inputStream); //Считываем список свойств (пары ключей и элементов) из входного потока байтов.
            } catch (IOException e) { //обработка искл. Ввода/Вывода
                throw new IOException(String.format("Error loading properties file '%s'", FILE_NAME));
            }
            //Присвоим переменной api значение из списка свойств по ключу "api"
            api = properties.getProperty("api");
            if (api == null) {
                throw new RuntimeException("Api value is null");
            }
            //Присвоим переменной urlSvs значение из списка свойств по ключу "urlSvs"
            urlSvs = properties.getProperty("urlSvs");
            if (urlSvs == null) {
                throw new RuntimeException("Url value is null");
            }
            //Присвоим переменной urlCurrent значение из списка свойств по ключу "urlCurrent"
            urlCurrent = properties.getProperty("urlCurrent");
            if (urlCurrent == null) {
                throw new RuntimeException("Url value is null");
            }
            //Присвоим переменной urlForecast значение из списка свойств по ключу "urlForecast"
            urlForecast = properties.getProperty("urlForecast");
            if (urlForecast == null) {
                throw new RuntimeException("Url value is null");
            }

        //Обработка общих исключений
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("App initialisation error: " + e.getMessage());
        }
    }
    
}

package org.example.receive_json.extendsJsonData;

import org.example.manage.ConfigSettings;
import org.example.manage.ReceiveDateTime;
import org.example.manage.WindDirection;
import org.example.receive_json.GettingJson;
import org.example.receive_json.JsonData;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.example.manage.ConstParam.*;

//Класс для обработки запросов JSON для прогноза погоды на 5 дней. Унаследован от JsonData
public class JsonForecast extends JsonData {

    private final static ConfigSettings settings = ConfigSettings.getInstance(); //Объект класса ConfigSettings
    private final String city;  //Название города
    private String dateTime;    //Дата
    private String feels;   //"Как чувствуется"
    private String desc;    //Описание
    private String wind;    //Ветер

    //Конструктор класса с парматером названия города
    public JsonForecast(String city) {
        this.city = city;
    }

    //метод вохврата прогноза погоды
    @Override
    public String getWeatherJson() {

        //Создание  Json объекта с параметром запроса
        JSONObject json = GettingJson.receiveJson(settings.getUrlForecast()
                + city + "&appid=" + settings.getApi() + "&units=metric&lang=ru");

        //Создания списка данных json
        JSONArray list = (JSONArray) json.get("list");

        StringBuilder data = new StringBuilder();

        //Для каждого объекта их списка list
          for (Object o : list) {

              //Присваеваем переменной forecast значение объекта o
            JSONObject forecast = (JSONObject) o;

            //Задаем переменной значения даты в определнном формате
            this.dateTime = ReceiveDateTime.formattingDateTime(forecast.get("dt_txt").toString()) + ": \n";

            //Прописываем условие для какого времени показывать погоду:
            if (this.dateTime.contains("09:00") || this.dateTime.contains("18:00")) {

                //запрос json по ключу "main"
                JSONObject main = (JSONObject) forecast.get("main");
                this.feels = main.getInt("feels_like") + DEGREE + ", ";

                //Присваивание переменной desc результата json запроса по ключу "weather"
                JSONObject desk = forecast.getJSONArray("weather").getJSONObject(0);
                this.desc = desk.get("description") + ", ";

                //Присваивание переменной wind результата json запроса по ключу "wind", "speed", "deg"
                JSONObject wind = forecast.getJSONObject("wind");
                this.wind = wind.get("speed").toString() + SPEED + " "
                        + WindDirection.directionSymb(wind.getDouble("deg"));

                //Обновляем значения с указанием запрошенных json
                data.append(this.dateTime);
                data.append(this.feels);
                data.append(this.desc);
                data.append(this.wind);
                data.append("\n");

            }
        }
        return data.toString();
    }
}

package org.example.receive_json.extendsJsonData;

import lombok.Getter;
import org.example.manage.ConfigSettings;
import org.example.manage.ReceiveDateTime;
import org.example.manage.WindDirection;
import org.example.receive_json.GettingJson;
import org.example.receive_json.JsonData;
import org.json.JSONObject;
import static org.example.manage.ConstParam.*;

//Класс для обработки запросов JSON для прогноза погоды для Севастополя. Унаследован от JsonData
@Getter
public class JsonCurrentSvs extends JsonData {

    private final static ConfigSettings settings = ConfigSettings.getInstance(); //Объект класса ConfigSettings
    private String temp;     //Переменная для определения температуры "Как чувствуется"
    private String desc;    //Описание
    private String wind;       //Ветер
    private String sunrise;     //Восход
    private String sunset;  //Закат

    //Метод возврата прогноза погоды
    @Override
    public String getWeatherJson() {

        //Создание  Json объекта с параметром запроса
        JSONObject json = GettingJson.receiveJson(settings.getUrlSvs() +
                settings.getApi() + "&units=metric&lang=ru");

        //запрос json по ключу "main"
        JSONObject jsonSpecific = json.getJSONObject("main");

        //Создание объекта форматированной строки
        StringBuilder data = new StringBuilder();

        //Присваивание переменной temp результата json запроса по ключу "feels_like"
        this.temp = jsonSpecific.getInt("feels_like") + DEGREE;

        //Присваивание переменной desc результата json запроса по ключу "weather" в формате .toString()
        jsonSpecific = json.getJSONArray("weather").getJSONObject(0);
        this.desc = jsonSpecific.get("description").toString();

        //Присваивание переменной wind результата json запроса по ключу "wind", "speed", "deg" в формате .toString()
        jsonSpecific = json.getJSONObject("wind");
        this.wind = jsonSpecific.get("speed").toString() + SPEED + ", " +
                WindDirection.directionSymb(jsonSpecific.getDouble("deg"));

        //Присваивание переменной sunrise результата json запроса по ключу "sunrise"
        jsonSpecific = json.getJSONObject("sys");
        this.sunrise = ReceiveDateTime.getSunEventSvs(jsonSpecific.getLong("sunrise")) ;

        //Присваивание переменной sunrise результата json запроса по ключу "sunset"
        jsonSpecific = json.getJSONObject("sys");
        this.sunset = ReceiveDateTime.getSunEventSvs(jsonSpecific.getLong("sunset"));

        //Обновляем значения с указанием запрошенных json
        data.append(this.temp).append("\n");
        data.append(this.desc).append("\n");
        data.append(this.wind).append("\n");
        data.append("Восход: ").append(this.sunrise).append("\n");
        data.append("Закат: ").append(this.sunset);

        return data.toString();

    }

}

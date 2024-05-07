package org.example.receive_json.extendsJsonData;

import lombok.Getter;
import org.example.manage.ConfigSettings;
import org.example.manage.ReceiveDateTime;
import org.example.manage.WindDirection;
import org.example.receive_json.GettingJson;
import org.example.receive_json.JsonData;
import org.json.JSONObject;
import static org.example.manage.ConstParam.*;


@Getter
public class JsonCurrentSvs extends JsonData {

    private final static ConfigSettings settings = ConfigSettings.getInstance();
    private String temp;
    private String desc;
    private String wind;
    private String sunrise;
    private String sunset;

    @Override
    public String getWeatherJson() {

        JSONObject json = GettingJson.receiveJson(settings.getUrlSvs() +
                settings.getApi() + "&units=metric&lang=ru");

        JSONObject jsonSpecific = json.getJSONObject("main");

        StringBuilder data = new StringBuilder();

        this.temp = jsonSpecific.getInt("feels_like") + DEGREE;

        jsonSpecific = json.getJSONArray("weather").getJSONObject(0);
        this.desc = jsonSpecific.get("description").toString();

        jsonSpecific = json.getJSONObject("wind");
        this.wind = jsonSpecific.get("speed").toString() + SPEED + ", " +
                WindDirection.directionSymb(jsonSpecific.getDouble("deg"));

        jsonSpecific = json.getJSONObject("sys");
        this.sunrise = ReceiveDateTime.getSunEventSvs(jsonSpecific.getLong("sunrise")) ;

        jsonSpecific = json.getJSONObject("sys");
        this.sunset = ReceiveDateTime.getSunEventSvs(jsonSpecific.getLong("sunset"));

        data.append(this.temp).append("\n");
        data.append(this.desc).append("\n");
        data.append(this.wind).append("\n");
        data.append("Восход: ").append(this.sunrise).append("\n");
        data.append("Закат: ").append(this.sunset);

        return data.toString();

    }

}

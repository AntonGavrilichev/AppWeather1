package org.example.receive_json;

//Класс-родитель для других классов, обрабатывающих json по разным направлениям
public abstract class JsonData {
    private String city;
    public abstract String getWeatherJson();
    public String getCity() {return city;}
}

package org.example.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.example.manage.OpenScene;
import org.example.manage.ReceiveDateTime;
import org.example.receive_json.extendsJsonData.JsonCurrentSvs;

import java.net.URL;
import java.util.ResourceBundle;

//Контроллер прогноза погоды для Севастополя
public class ControllerCurrentSvs {

    @FXML // аннотация @FXML связывает код Java и соответствующий объект нашего макета
    private ResourceBundle resources;  //ресурсы
    @FXML
    private URL location;               //Местоположение
    @FXML
    private Text cityName;             //Название города
    @FXML
    private Text dataTime;              //Дата и Время
    @FXML
    private Text weatherInfo;           //Инфо по погоде
    @FXML
    private Button toReturn;            //Кнопка для возврата в меню

    //Создание объекта класса JsonCurrentSvs
    JsonCurrentSvs jsonConnect = new JsonCurrentSvs();

    //Инициализация значений
    @FXML
    void initialize() {
        //Задаем название города
        cityName.setText("Севастополь");

        //Задаём дату и время
        dataTime.setText(ReceiveDateTime.getCurrentDate() +
                ", " + ReceiveDateTime.getCurrentTime());


        //Задаем погоду в формате определенном в методе getWeatherJson()
        weatherInfo.setText(jsonConnect.getWeatherJson());


        //Задаем параметры исполнения команды кнопки toReturn.
        //Используем стартовый графический сценраий
        toReturn.setOnAction(e -> {
            toReturn.getScene().getWindow().hide();
            OpenScene.openScene("/fxml/weather_start.fxml");
        });
    }

}

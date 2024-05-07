package org.example.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.example.manage.OpenScene;
import org.example.manage.ReceiveDateTime;
import org.example.receive_json.extendsJsonData.JsonCurrentSvs;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCurrentSvs {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Text cityName;
    @FXML
    private Text dataTime;
    @FXML
    private Text weatherInfo;
    @FXML
    private Button toReturn;

    JsonCurrentSvs jsonConnect = new JsonCurrentSvs();

    @FXML
    void initialize() {
        cityName.setText("Севастополь");

        dataTime.setText(ReceiveDateTime.getCurrentDate() +
                ", " + ReceiveDateTime.getCurrentTime());

        weatherInfo.setText(jsonConnect.getWeatherJson());

        toReturn.setOnAction(e -> {
            toReturn.getScene().getWindow().hide();
            OpenScene.openScene("/fxml/weather_start.fxml");
        });
    }

}

package org.example.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.manage.OpenScene;
import org.example.manage.ReceiveDateTime;

import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ControllerStart {
    @FXML
    private ResourceBundle resources;   //ресурсы
    @FXML
    private URL location;       //URl местности
    @FXML
    private Text currentDate;   //Переменная даты
    @FXML
    private Text currentTime;   //Переменная времени
    @FXML
    private Button getWorld;    //Кнопка "В мире"
    @FXML
    private Button getSvs;      //Кнопка в Севастополе
    @FXML
    private Button getForecast; //Кнопка "Прогноз на 5 дней

    //Метод задания значений
    @FXML
    void initialize() {

        // Задаем дату и время
        currentDate.setText(ReceiveDateTime.getCurrentDate());
        getRunningTime();

        //Задаем действие по нажатию кнопки "В Севастополе"
        getSvs.setOnAction(e -> {
            getSvs.getScene().getWindow().hide();//Скроем (hide()) выбранные элементы (getWindow())
            OpenScene.openScene("/fxml/weather_current_svs.fxml"); //Вернем страницу с прогнозом Севастополя
        });

        //Задаем действие по нажатию кнопки "В Мире"
        getWorld.setOnAction(e -> {
            getWorld.getScene().getWindow().hide();//Скроем (hide()) выбранные элементы (getWindow())
            OpenScene.openScene("/fxml/weather_current_search.fxml");//Вернем страницу с прогнозом в мире
        });
        //Задаем действие по нажатию кнопки "Прогноз на 5 дней"
        getForecast.setOnAction(e -> {
            getWorld.getScene().getWindow().hide();//Скроем (hide()) выбранные элементы (getWindow())
            OpenScene.openScene("/fxml/weather_forecast.fxml");//Вернем страницу с прогнозом на 5 дней
        });
    }

    //метод инициализации даты и времени
    public void getRunningTime() {

        //Задаем формат даты
        final DateFormat format = DateFormat.getTimeInstance();
        //Задаем формат времени с обновлением в 1 секунду
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>() {    //Абстрактный класс обработчика событий в качестве параметра
                    @Override
                    public void handle(ActionEvent event) {     //переопределение метода handle
                        final Calendar cal = Calendar.getInstance();    //создание объекта класса Calendar и
                                                                        // присваивание локальной даты
                        currentTime.setText(format.format(cal.getTime()));  //Задаем форматированный вывод времени
                    }
                }
        ));

        timeline.setCycleCount(Animation.INDEFINITE); //Устанавливаем анимацию в цикл
        timeline.play(); //запуск
    }

}

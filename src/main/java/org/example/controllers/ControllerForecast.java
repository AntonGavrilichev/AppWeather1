package org.example.controllers;

import org.example.manage.OpenScene;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.receive_json.extendsJsonData.JsonForecast;

import static org.example.manage.ConstParam.*;

//Контроллер Прогноза на 5 дней
public class ControllerForecast {

    JsonForecast jsonConnect; //Создание объекта класса JsonCurrent
    String citySet;   //форматированное название города
    @FXML
    public Button toReturn;  //Кнопка для возврата в меню
    @FXML
    public Button help; //Кнопка справки
    @FXML
    public Button getWeather; //Кнопка "Узнать" о погоде
    @FXML
    public Button clear;    //Кнопка "Очистить"
    @FXML
    public TextField cityName;  //Название города
    @FXML
    public Text exceptionField; //Текст ошибки. Переменная Text отображает отфоррматированный текст
    @FXML
    public Text forecast;   //Текст проноза погоды

    //Задаем значения в этом методе
    @FXML
    void initialize() {

        //Определяем метод, который будет вызван после ввода пользователем названия города
        cityName.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                setWeatherInfo();
            }
        });

        //Настраиваем кнопу "помощь".
        help.setOnAction(e -> {
            showHelp();
        });

        //Определяем метод, который будет вызван после нажатия кнопки "Узнать"
        getWeather.setOnAction(e -> {
            setWeatherInfo();
        });

        //Определяем метод, который будет вызван после нажатия кнопки "Меню"
        toReturn.setOnAction(e -> {
            toReturn.getScene().getWindow().hide();
            OpenScene.openScene("/fxml/weather_start.fxml");
        });

        //Определяем метод, который будет вызван после нажатия кнопки "Очистить"
        clear.setOnAction(e -> {
            reset();
        });

    }

    //Метод, который выводит на экран окно справки.
    public void showHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Справка");
        alert.setHeaderText(null);
        alert.setContentText(HELP);
        alert.showAndWait();
    }

    //Метод вывода ошибок исполнения программы
    private void showErrors(String message) {
        //Задаем текст ошибки с переданным параметром message
        exceptionField.setText(message);
        //Выводим текст ошибки с эффектом меделенного появления (new FadeTransition()
        // за 1 секунду (Duration.seconds(1))
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), exceptionField);
        fadeIn.setToValue(1);
        fadeIn.setFromValue(0);
        fadeIn.play();

        //Скрываем текст ошибки с эффектом затухания
        fadeIn.setOnFinished(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.play();
            pause.setOnFinished(event2 -> {
                FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), exceptionField);
                fadeOut.setToValue(0);
                fadeOut.setFromValue(1);
                fadeOut.play();
            });
        });
    }

    //Метод вывода информации о погоде
    private void setWeatherInfo() {
        //Если поле ввода города пустое, то вызовем метод вывода ошибок (showErrors)
        //с параметром ENTER_CITY
        if (cityName.getText().equals("")) {
            showErrors(ENTER_CITY);
        } else {
            try {                                           //Обрабатываем исключение
                exceptionField.setText("");                 //текст ошибки задаем пустой
                this.citySet = cityName.getText().trim();   //Задаем название городо в строковый тип \
                                                            // с удалением лишних пробелов
                jsonConnect = new JsonForecast(citySet);    //Передаем в название города в качестве параметра в
                //созданный объект прогноза погоды (JsonCurrent)

                //Запрашиваем погоду
                forecast.setText(jsonConnect.getWeatherJson());
            } catch (Exception e) {
                showErrors(NOT_FOUND);  //В случае ошибки, выводим надпись "Город с таким названием не найден"
            }
        }
    }
    //Метод очистки полей
    private void reset() {
        cityName.setText("");   //Стирает название города
        forecast.setText("");   //Стирает прогноз погоды
    }
}

package org.example.manage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

//Класс для загрузки граф. интерфейса
public class OpenScene {

    //Метод загрузки объекта Scene c парметром адреса файла окна fxml
    public static void openScene(String window) {
        FXMLLoader loader = new FXMLLoader(); //загрузчик fxml
        loader.setLocation(OpenScene.class.getResource(window)); //Загрузим из адреса window характеристики
        try {
            loader.load();      //обработка ошибки загрузки
        }catch (IOException e) {
            e.printStackTrace();
        }

        Parent root  = loader.getRoot();    //Объект класса обработки операций со Scene
        Stage stage = new Stage();      //Создание главного объекта для работы с граф.

        stage.setTitle("Weather");      //Задание название окна

        //Загрузка картинки программы
        InputStream iconStream = OpenScene.class.getResourceAsStream("/img/weather_icon.png");
        assert iconStream != null; //метка на ошибку
        Image image = new Image(iconStream);
        stage.getIcons().add(image);    //добавление картинки в stage

        stage.setScene(new Scene(root));    //задаем в stage новый Scene с
                                            // параметром root(Объект класса обработки операций со Scene)
        stage.show();
    }
}

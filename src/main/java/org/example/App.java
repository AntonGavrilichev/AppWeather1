package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

//Класс запуска программы. Унаследован от абстрактного класса Application
public class App extends Application {
    //входная точка прогрммы. Метод launch() создается объект класса App и вызывается метод App.start()
    public static void main(String[] args) {launch();}

    //переопределение метода start(), где параметр - объект класса Stage (основной объект Stage создается средой JavaFX)
    //метод start(Stage stage): определяет графический интерфейс.
    @Override
    public void start(Stage stage) throws IOException { // исключение при выполнении операции ввода/вывода

        //Создание объекта класса FXMLLoader, который загрузит иерархию объектов из указанного пути.
        //Исключение проброшено выше
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/weather_start.fxml"));

        //Создадим контейнер графики Scene и укажем параметры (объекты из weather_start.fxml)
        Scene scene = new Scene(fxmlLoader.load(), 406, 552);

        //зададим название приложения на окне Windows
        stage.setTitle("Weather");

        //откроем входной поток байтов iconStream и вернем иконку программы
        //исключение проброшено выше
        InputStream iconStream = getClass().getResourceAsStream("/img/weather_icon.png");
        //создание объекта image c параметром iconStream
        Image image = new Image(iconStream);
        //установка ярлыка программы
        stage.getIcons().add(image);

        //зададим основному объекту stage "графический сценарий" (scene), прописанный выше
        stage.setScene(scene);
        //запрещаем изменять размер окна
        stage.setResizable(false);
        //отображение окна
        stage.show();
    }
}
package com.example.project_game_java;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 713, 388);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        scene.setOnKeyPressed( e -> {
            if (e.getCode() == KeyCode.SPACE && !Controller.jump)//нажимаем на кнопку space -прыгаем
                Controller.jump = true;

            if (e.getCode() == KeyCode.A)//нажимаем на кнопку А -перемещаемся влево
                Controller.left = true;

            if (e.getCode() == KeyCode.D)//нажимаем на кнопку D -перемещаемся вправо
                Controller.right = true;

        });

        scene.setOnKeyReleased( e -> {

            if (e.getCode() == KeyCode.A)
                Controller.left = false;

            if (e.getCode() == KeyCode.D)
                Controller.right = false;

            if (e.getCode() == KeyCode.ESCAPE )//нажимаем на кнопку escape -пауза
            Controller.isPause = !Controller.isPause;

        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
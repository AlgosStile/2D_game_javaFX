module com.example.project_game_java {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_game_java to javafx.fxml;
    exports com.example.project_game_java;
}
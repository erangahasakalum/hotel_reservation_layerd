package lk.ijse.hotel_management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.net.URL;

public class Appinitilizer extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootNode = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}


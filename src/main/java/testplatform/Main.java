package testplatform;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    MainController controller;

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("fxml/main.fxml"));
        loader.setRoot(controller);
        loader.setController(controller);
        controller = new MainController(primaryStage);
        primaryStage.setScene(new Scene(controller));
        primaryStage.getScene().getWindow().setWidth(1000);
        primaryStage.getScene().getWindow().setHeight(1000);
        primaryStage.show();
    }
}

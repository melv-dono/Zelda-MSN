import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Vue/Vue.fxml"));
        Scene scene=new Scene(root, 600, 550);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zelda");
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}

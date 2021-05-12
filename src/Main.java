import Controleur.ArrowGestion;
import Vue.MapReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("Vue/Vue.fxml"));
        Scene scene=new Scene(root, 1280, 736);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zelda");
        primaryStage.show();


    }
    public static void main(String[] args) {
        launch(args);
    }
}

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
       launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PantallaPrincipal.fxml"));
       Scene scene = new Scene(loader.load());
       stage.setTitle("Empire Invaders");
       stage.setScene(scene);
       stage.show();

    }
}

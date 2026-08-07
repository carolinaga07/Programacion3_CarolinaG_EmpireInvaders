package Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

   public static Stage stagePrincipal;
    public static void main(String[] args) throws Exception {
       launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
      stagePrincipal = stage;

       FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PantallaPrincipal.fxml"));
       Parent root = loader.load();
       Scene scene = new Scene(root);
       stage.setTitle("Empire Invaders");
       stage.setScene(scene);
       stage.show();

    }
}

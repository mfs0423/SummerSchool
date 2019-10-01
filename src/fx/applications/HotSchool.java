package fx.applications;

import java.io.FileInputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Meng
 */
public class HotSchool extends Application {
    public static Stage mystage = new Stage();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new FXMLLoader().load(new FileInputStream("src/fx/FXML/WelcomePage.fxml"));
        Scene scene = new Scene(root);
        mystage.setTitle("Hot School System");
        mystage.setScene(scene);
        mystage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

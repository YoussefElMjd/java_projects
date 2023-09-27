package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View {
    private ControllerFx controllerFx;
    private Stage stage;

    public View(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        controllerFx = fxmlLoader.getController();
        stage.setTitle("Stib!");
        stage.setScene(scene);
        stage.show();
    }
    public void setMousse(Presenter presenter){
        controllerFx.setOnMousseClicked(presenter);
    }
    public ControllerFx getControllerFx() {
        return controllerFx;
    }
}

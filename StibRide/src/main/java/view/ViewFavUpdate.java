package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFavUpdate {
    private ControllerFavUpdateFx controllerFavUpdateFx;
    private Stage stage;

    public ViewFavUpdate(Stage stage) {
        this.stage = stage;
    }

    public void initialize(String language) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("viewFavUpdate.fxml"));
        controllerFavUpdateFx = new ControllerFavUpdateFx(language);
        fxmlLoader.setController(controllerFavUpdateFx);
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setTitle("update fav");
        stage.setScene(scene);
        stage.show();
    }

    public void show() throws IOException {
        stage.showAndWait();
    }

    public void setMousse(Presenter presenter) {
        controllerFavUpdateFx.setOnMousseClicked(presenter);
    }

    public ControllerFavUpdateFx getControllerFx() {
        return controllerFavUpdateFx;
    }

    public Stage getStage() {
        return stage;
    }
}

package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFav {

    private ControllerFavFx controllerFavFx;
    private Stage stage;

    public ViewFav(Stage stage) {
        this.stage = stage;
    }

    public void initialize(String langage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("viewFav.fxml"));
        controllerFavFx = new ControllerFavFx(langage);
        fxmlLoader.setController(controllerFavFx);
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("new fav");
        stage.setScene(scene);
        stage.show();
    }

    public void setMousse(Presenter presenter, String language) {
        controllerFavFx.setOnMousseClicked(presenter);
    }

    public ControllerFavFx getControllerFx() {
        return controllerFavFx;
    }

    public void show() throws IOException {
        stage.showAndWait();
    }

    public Stage getStage() {
        return stage;
    }
}

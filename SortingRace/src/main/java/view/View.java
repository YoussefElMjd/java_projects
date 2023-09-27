package view;

import com.example.thread.MainApplication;
import dto.SimulationDto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Observable;

import java.io.IOException;
import java.util.List;

public class View implements Observer{
    FxmlController fx;

    public void initialize(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("sort.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.fx = fxmlLoader.getController();
        stage.setTitle("Sort!");
        stage.setScene(scene);
        stage.show();
    }

    public FxmlController getFx() {
        return fx;
    }

    @Override
    public synchronized void update(Observable observable) {
        fx.update(observable);
    }
}

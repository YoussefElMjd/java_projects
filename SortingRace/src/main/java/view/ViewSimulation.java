package view;

import com.example.thread.MainApplication;
import dto.SimulationDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Observable;

import java.io.IOException;
import java.util.List;

public class ViewSimulation {
    SimulationFxController fx;
    private Stage stage;

    public ViewSimulation(Stage stage) {
        this.stage = stage;
    }

    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("simulation.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.fx = fxmlLoader.getController();
        stage.setTitle("Simulations!");
        stage.setScene(scene);
        stage.show();
    }

    public SimulationFxController getFx() {
        return fx;
    }

    public SimulationFxController getControllerFx() {
        return fx;
    }

    public void show(List<SimulationDto> allSimulations) {
        fx.update(allSimulations);
    }

    public void add(SimulationDto simulationDto) {
        fx.add(simulationDto);
    }
}

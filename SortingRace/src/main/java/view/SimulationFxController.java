package view;

import dto.SimulationDto;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Observable;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class SimulationFxController implements Initializable {
    @FXML
    private TableColumn<SimulationDto, Long> maxSize;

    @FXML
    private TableColumn<SimulationDto, String> sortType;

    @FXML
    private TableView<SimulationDto> table;

    @FXML
    private TableColumn<SimulationDto, LocalDateTime> timestamp;

    private ObservableList<SimulationDto> allSimulation;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allSimulation = FXCollections.observableArrayList();
        table.setItems(allSimulation);
        maxSize.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().getMax_size()));
        timestamp.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().getDate()));
        sortType.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().getSortType()));
    }

    public void update(List<SimulationDto> allSimulations) {
        for (int i = 0; i < allSimulations.size(); i++) {
            table.getItems().add(allSimulations.get(i));
        }
    }
    public void add(SimulationDto simulation){
        table.getItems().add(simulation);
    }
}

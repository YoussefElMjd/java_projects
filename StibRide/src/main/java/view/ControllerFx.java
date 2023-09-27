package view;

import dto.FavTravelDto;
import dto.StationsDto;
import dto.StopsDto;
import javafx.application.Platform;
import javafx.scene.control.*;
import model.Node;
import repisotory.FavTravelRepository;
import repisotory.StationsNlRepository;
import repisotory.StationsRepository;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import myException.RepositoryException;
import org.controlsfx.control.SearchableComboBox;


public class ControllerFx implements Initializable {
    @FXML
    private SearchableComboBox<StationsDto> destination;

    @FXML
    private TableColumn<StopsDto, String> lignes;

    @FXML
    private SearchableComboBox<StationsDto> origine;

    @FXML
    private TableColumn<StopsDto, String> stations;

    @FXML
    private TableView<StopsDto> table;

    @FXML
    private Button search;

    @FXML
    private Button updateFav;

    @FXML
    private Button newFav;

    @FXML
    private SearchableComboBox<FavTravelDto> allFavMenu;

    @FXML
    private Button searchByFav;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu edit;

    @FXML
    private MenuItem fr;

    @FXML
    private MenuItem nl;

    private String language = "FR";
    private ObservableList<StopsDto> tableValue;
    private ObservableList<FavTravelDto> favValue;
    private ObservableList<StationsDto> destinationValue;
    private ObservableList<StationsDto> origineValue;

    private int lastOrigine;
    private int lastDestination;


    public void setOnMousseClicked(Presenter presenter) {
        search.setOnMouseClicked(e -> {
            this.lastOrigine = origine.getValue().getKey();
            this.lastDestination = destination.getValue().getKey();
            presenter.start(lastOrigine, lastDestination);
        });
        searchByFav.setOnMouseClicked(e -> {
            if (allFavMenu.getValue() != null) {
                this.lastOrigine = allFavMenu.getValue().getOrigin();
                this.lastDestination = allFavMenu.getValue().getDest();
                presenter.start(lastOrigine, lastDestination);
            }
        });
        newFav.setOnMouseClicked(e -> {
            presenter.newFav(language);
        });

        updateFav.setOnMouseClicked(e -> {
            presenter.openFavWindow(language);
        });

        fr.setOnAction(e -> {
            this.language = "FR";
            this.changeLanguage("FR");
            presenter.changeLanguage("FR");
            if (lastOrigine != 0 && lastDestination != 0)
                presenter.start(lastOrigine, lastDestination);
        });

        nl.setOnAction(e -> {
            this.language = "NL";
            this.changeLanguage("NL");
            presenter.changeLanguage("NL");
            if (lastOrigine != 0 && lastDestination != 0)
                presenter.start(lastOrigine, lastDestination);
        });

    }

    private void fillSearchableComboBox(List<StationsDto> stations) {
        destinationValue = FXCollections.observableArrayList();
        destination.setItems(destinationValue);
        origineValue = FXCollections.observableArrayList();
        origine.setItems(origineValue);
        for (int i = 0; i < stations.size(); i++) {
            destination.getItems().add(stations.get(i));
            origine.getItems().add(stations.get(i));
        }
    }

    private void changeLanguage(String language) {
        if (language.equals("FR")) {
            initializeFR();
        } else {
            initializeNL();
        }
    }
    public void initializeFR() {
        StationsRepository stationsRepository = null;
        try {
            stationsRepository = new StationsRepository();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        List<StationsDto> stations = null;
        try {
            stations = stationsRepository.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        fillSearchableComboBox(stations);
    }

    public void initializeNL() {
        StationsNlRepository stationsRepository = null;
        try {
            stationsRepository = new StationsNlRepository();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        List<StationsDto> stations = null;
        try {
            stations = stationsRepository.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        fillSearchableComboBox(stations);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableValue = FXCollections.observableArrayList();
        table.setItems(tableValue);
        stations.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().getId_station().getName()));
        lignes.setCellValueFactory(element -> new ReadOnlyObjectWrapper<>(element.getValue().getId_line().getKey().toString()));
        destinationValue = FXCollections.observableArrayList();
        destination.setItems(destinationValue);
        origineValue = FXCollections.observableArrayList();
        origine.setItems(origineValue);
        initializeFR();
        FavTravelRepository favRepository = null;
        List<FavTravelDto> favTravels = null;
        favValue = FXCollections.observableArrayList();
        allFavMenu.setItems(favValue);
        try {
            favRepository = new FavTravelRepository();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        try {
            favTravels = favRepository.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < favTravels.size(); i++) {
            allFavMenu.getItems().add(favTravels.get(i));
        }
    }

    public void updateFav() {
        favValue = FXCollections.observableArrayList();
        allFavMenu.setItems(favValue);
        FavTravelRepository favRepository = null;
        List<FavTravelDto> favTravels = null;
        try {
            favRepository = new FavTravelRepository();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        try {
            favTravels = favRepository.getAll();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < favTravels.size(); i++) {
            allFavMenu.getItems().add(favTravels.get(i));
        }
    }

    public void update(Node dest) {
        Platform.runLater(() -> {
            List<Node> bestTravel = dest.getShortestPath();
            table.getItems().clear();
            for (int i = 0; i < bestTravel.size(); i++) {
                table.getItems().add(bestTravel.get(i).getStopsStation());
            }
            table.getItems().add(dest.getStopsStation());
        });
    }
}


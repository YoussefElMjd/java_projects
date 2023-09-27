package view;

import dto.FavTravelDto;
import dto.StationsDto;
import javafx.collections.FXCollections;
import repisotory.FavTravelRepository;
import repisotory.Repository;
import repisotory.StationsNlRepository;
import repisotory.StationsRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import myException.RepositoryException;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.textfield.CustomTextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerFavUpdateFx implements Initializable {

    @FXML
    private SearchableComboBox<FavTravelDto> allFavUpdate;

    @FXML
    private SearchableComboBox<StationsDto> destFavUpdate;

    @FXML
    private CustomTextField nameFavUpdate;

    @FXML
    private SearchableComboBox<StationsDto> originFavUpdate;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    private String language = "FR";

    public ControllerFavUpdateFx(String language) {
        this.language = language;
    }

    public void setOnMousseClicked(Presenter presenter) {
        update.setOnMouseClicked(e -> {
            presenter.updateFav(allFavUpdate.getValue().getName(), nameFavUpdate.getText(), originFavUpdate.getValue().getKey(), destFavUpdate.getValue().getKey());
        });

        delete.setOnMouseClicked(e -> {
            presenter.deleteFav(allFavUpdate.getValue().getName());
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        if (language.equals("FR")) {
            initializeFR();
        } else {
            initializeNL();
        }

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
            allFavUpdate.getItems().add(favTravels.get(i));
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
        for (int i = 0; i < stations.size(); i++) {
            destFavUpdate.getItems().add(stations.get(i));
            originFavUpdate.getItems().add(stations.get(i));
        }
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
        for (int i = 0; i < stations.size(); i++) {
            destFavUpdate.getItems().add(stations.get(i));
            originFavUpdate.getItems().add(stations.get(i));
        }
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}

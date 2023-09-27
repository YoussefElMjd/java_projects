package view;

import dto.StationsDto;
import repisotory.StationsNlRepository;
import repisotory.StationsRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import myException.RepositoryException;
import org.controlsfx.control.SearchableComboBox;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerFavFx implements Initializable {
    @FXML
    private Button confirm;

    @FXML
    private SearchableComboBox<StationsDto> destFav;

    @FXML
    private CustomTextField nameFav;

    @FXML
    private SearchableComboBox<StationsDto> originFav;

    private String language = "FR";

    public ControllerFavFx(String langage) {
        this.language = langage;
    }

    public void setOnMousseClicked(Presenter presenter) {
        confirm.setOnMouseClicked(e -> {
            presenter.addToFav(nameFav.getText(), originFav.getValue().getKey(), destFav.getValue().getKey());
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (language.equals("FR")) {
            initializeFR();
        } else {
            initializeNL();
        }
    }


    public void initializeFR(){
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
            destFav.getItems().add(stations.get(i));
            originFav.getItems().add(stations.get(i));
        }
    }
    public void initializeNL(){
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
            destFav.getItems().add(stations.get(i));
            originFav.getItems().add(stations.get(i));
        }
    }
    public void setLanguage(String language) {
        this.language = language;
    }
}

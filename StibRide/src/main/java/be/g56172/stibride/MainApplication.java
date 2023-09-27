package be.g56172.stibride;

import model.Model;
import view.*;
import view.Presenter;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainApplication extends Application {


    public void execute(String[] args) {
        launch(args);
    }
    public MainApplication(){

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Model model = new Model();
        View view = new View(primaryStage);
        ViewFav viewFav = new ViewFav(new Stage());
        ViewFavUpdate viewFavUpdate = new ViewFavUpdate(new Stage());
        Presenter presenter = new Presenter(model, view,viewFav,viewFavUpdate);
        view.initialize();
        view.setMousse(presenter);
        model.addObserver(presenter);

    }
}
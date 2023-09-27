package com.example.thread;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view.View;
import view.ViewSimulation;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        View view = new View();
        view.initialize(stage);
        ViewSimulation viewSimulation = new ViewSimulation(new Stage());
        Controller controller = new Controller(view,viewSimulation);
        view.getFx().setMousseClicked(controller);
    }

    public static void main(String[] args) {
        launch();
    }
}
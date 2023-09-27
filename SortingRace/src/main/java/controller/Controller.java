package controller;

import dto.SimulationDto;
import model.Configuration;
import model.Model;
import view.View;
import view.ViewSimulation;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Controller {
    View view;
    ViewSimulation viewSimulation;
    Model model;

    public Controller(View view,ViewSimulation viewSimulation){
        model = new Model();
        this.view = view;
        this.viewSimulation = viewSimulation;
    }

    public void start(LocalDateTime begin, Integer nbThread, String choice, Configuration configuration) {
        model.initialize(begin,nbThread,choice,configuration,view);
        model.start(nbThread);
        if(viewSimulation.getControllerFx() != null)
        viewSimulation.add(new SimulationDto(begin,choice,configuration.getValue()));
    }

    public void openSimulations() {
        try {
            List<SimulationDto> allSimulations = model.getAllSimulations();
            viewSimulation.initialize();
            viewSimulation.show(allSimulations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

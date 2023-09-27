package view;

import dto.SimulationDto;
import model.Observable;

import java.util.List;

public interface Observer {
    /**
     * Method for updating the view scene according to a state.
     * We have two states GAME_OVER and WIN.
     * an update at the time of a game over and an update of the view at the time of a win
     */
    void update(Observable observable);
}

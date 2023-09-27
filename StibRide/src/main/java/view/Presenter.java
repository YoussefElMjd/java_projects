package view;

import dto.FavTravelDto;
import model.Model;
import model.Node;
import observer.Observable;
import observer.Observer;

public class Presenter implements Observer {

    private Model model;
    private View view;
    private ViewFav viewFav;
    private ViewFavUpdate viewFavUpdate;

    public Presenter(Model model, View view, ViewFav viewFav, ViewFavUpdate viewFavUpdate) {
        this.model = model;
        this.view = view;
        this.viewFav = viewFav;
        this.viewFavUpdate = viewFavUpdate;
    }

    public void start(int origin, int destination) {
        if (origin != 0 && destination != 0)
            model.calculateShortestPath(origin, destination, this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        view.getControllerFx().update((Node) arg);
    }

    public void addToFav(String nameFav, int origin, int dest) {
        model.addToFav(nameFav, origin, dest);
        view.getControllerFx().updateFav();
        viewFav.getStage().close();
    }

    public void newFav(String language) {
        viewFav.initialize(language);
        viewFav.setMousse(this, language);
    }

    public void updateFav(String name, String nameFav, int origin, int dest) {
        model.updateFav(name, nameFav, origin, dest);
        view.getControllerFx().updateFav();
        viewFavUpdate.getStage().close();
    }

    public void openFavWindow(String language) {
        viewFavUpdate.initialize(language);
        viewFavUpdate.setMousse(this);
    }

    public void deleteFav(String name) {
        model.deleteFav(name);
        view.getControllerFx().updateFav();
        viewFavUpdate.getStage().close();
    }

    public void changeLanguage(String language) {
        model.changeLanguage(language);
        model.setLanguage(language);
        if (viewFav.getControllerFx() != null)
            viewFav.getControllerFx().setLanguage(language);
        if (viewFavUpdate.getControllerFx() != null)
            viewFavUpdate.getControllerFx().setLanguage(language);
    }
}

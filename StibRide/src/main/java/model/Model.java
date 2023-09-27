package model;

import dto.FavTravelDto;
import dto.StationsDto;
import dto.StopsDto;
import javafx.util.Pair;
import repisotory.FavTravelRepository;
import repisotory.StationsNlRepository;
import repisotory.StationsRepository;
import repisotory.StopsRepository;
import config.ConfigManager;
import myException.RepositoryException;
import observer.Observable;
import observer.Observer;
import view.Presenter;

import java.io.IOException;
import java.util.*;

public class Model extends Observable {
    private Set<Node> adjencyList = new HashSet<>();
    private Graph graph;
    private List<List<StopsDto>> stops = new LinkedList<>();
    private FavTravelRepository favRepository = null;
    private StopsRepository repository = null;
    private String language = "FR";

    public Model() {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dbUrl = ConfigManager.getInstance().getProperties("db.url");
        System.out.println("Base de données stockée = " + dbUrl);
        StopsRepository repository = null;
        try {
            repository = new StopsRepository();
            this.stops = repository.constructGraph("FR");
            favRepository = new FavTravelRepository();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        this.graph = new Graph(stops, adjencyList);
    }

    public void calculateShortestPath(int source, int dest, Presenter presenter) {
        Search search = new Search(source, dest, graph);
        search.addObserver(presenter);
        Thread thread = new Thread(search);
        thread.start();

    }

    public void addToFav(String nameFav, int origin, int dest) {
        FavTravelDto favValue = new FavTravelDto(nameFav,origin, dest);
        try {
            favRepository.insert(favValue);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
    public void updateFav(String name, String nameFav, int origin, int dest) {
        FavTravelDto favValue = new FavTravelDto(nameFav,origin, dest);
        try {
            favRepository.update(name, favValue);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    public void deleteFav(String name) {
        try {
            favRepository.delete(name);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguage(String language) {
        this.language = language;
        try {
            repository = new StopsRepository();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        this.stops.clear();
        if (language.equals("FR")) {
            try {
                this.stops = repository.constructGraph("FR");
            } catch (RepositoryException e) {
                e.printStackTrace();
            }

        } else {
            try {
                this.stops = repository.constructGraph("NL");
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        }
        this.adjencyList = new HashSet<>();
        this.graph = new Graph(stops, adjencyList);
    }

    @Override
    public void addObserver(Observer observer) {
        super.addObserver(observer); //To change body of generated methods, choose Tools | Templates.
    }

    public Graph getGraph() {
        return graph;
    }

    public List<List<StopsDto>> getStops() {
        return stops;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

package model;

import dto.StationsDto;
import myException.RepositoryException;
import observer.Observable;
import repisotory.StationsNlRepository;
import repisotory.StationsRepository;

public class Search extends Observable implements Runnable {
    private int source;
    private int dest;
    private Graph graph;

    public Search(int source, int dest, Graph graph) {
        this.source = source;
        this.dest = dest;
        this.graph = graph;
    }

    public void calculateShortestPath() {
        this.graph.resetNode();
        this.graph.calculateShortestPath(this.graph.findNodeByKey(source));
        Node destination = this.graph.findNodeByKey(dest);
        notifyObservers(destination);
    }

    @Override
    public void run() {
        calculateShortestPath();
    }
}

package model;

import dto.StopsDto;

import java.util.*;

public class Node {
    private StopsDto stopsStation;
    @Override
    public int hashCode() {
        return Objects.hash(getStopsStation());
    }

    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    Map<Node,Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination){
        if(destination != null && !containsStations(destination.getStopsStation().getId_station().getKey())){
            adjacentNodes.put(destination,1);
        }
    }

    public boolean containsStations(int id_stations){
        for(Map.Entry<Node,Integer> node : adjacentNodes.entrySet()){
            if(node.getKey().getStopsStation().getId_station().getKey() == id_stations)
                return true;
        }
        return false;
    }


    public Node(StopsDto id){
        this.stopsStation = id;
    }


    public StopsDto getStopsStation() {
        return stopsStation;
    }

    public void setStopsStation(StopsDto stopsStation) {
        this.stopsStation = stopsStation;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

}

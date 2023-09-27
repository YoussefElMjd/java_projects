package model;

import dto.StopsDto;

import java.util.*;

public class Graph {

    private Set<Node> nodes;
    private List<List<StopsDto>> stops;

    public Graph() {
        stops = new ArrayList<>();
        nodes = new HashSet<>();
    }

    public Graph(List<List<StopsDto>> stops, Set<Node> nodes) {
        this.stops = stops;
        this.nodes = nodes;
    }

    public void addNode(Node node) {
        this.nodes.add(node);
    }

    public void printSet() {
        for (Node node : nodes) {
            System.out.println("le chemin le plus cours pour aller à " + node.getStopsStation().getId_station().getName());
            for (int i = 0; i < node.getShortestPath().size(); i++) {
                System.out.print(node.getShortestPath().get(i).getStopsStation().getId_station().getName() + "     Distance: " + node.getShortestPath().get(i).getDistance());
                System.out.println();
            }
        }
    }

    public void calculateShortestPath(Node source) {
        source.setDistance(0);
        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);
        while (!unsettledNodes.isEmpty()) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacencyNode = adjacencyPair.getKey();
                Integer distance = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacencyNode)) {
                    calcluateMinimumDistance(adjacencyNode, distance, currentNode);
                    unsettledNodes.add(adjacencyNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    private void calcluateMinimumDistance(Node ajdencyNode, Integer distance, Node currentNode) {
        Integer sourceDistance = currentNode.getDistance();
        if (sourceDistance + distance < ajdencyNode.getDistance()) {
            ajdencyNode.setDistance(sourceDistance + distance);
            LinkedList<Node> shortestPath = new LinkedList<>(currentNode.getShortestPath());
            shortestPath.add(currentNode);
            ajdencyNode.setShortestPath(shortestPath);
        }
    }

    private Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    private void constructAdjencyListNode() {
        for (int i = 0; i < stops.size(); i++) {
            Node node = findNode(stops.get(i).get(0));
            if (node == null) {
                node = new Node(stops.get(i).get(0));
                addNode(node);
            }
            for (int j = 0; j < stops.get(i).size(); j++) {
                Node nodeTmp = new Node(stops.get(i).get(j));
                if (nodeTmp.getStopsStation().getStopsDest() != null) {
                    Node destNode = null;
                    destNode = findNode(nodeTmp.getStopsStation().getStopsDest());
                    if (destNode == null) {
                        destNode = new Node(nodeTmp.getStopsStation().getStopsDest());
                        addNode(destNode);
                    }
                    node.addDestination(destNode);
                    destNode.addDestination(node);
                }
            }
        }
    }

    public void resetNode() {
        this.nodes.clear();
        constructAdjencyListNode();
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Graph(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Node findNode(StopsDto stopsDto) {
        for (Node node : nodes) {
            if (node.getStopsStation().getId_station().getKey().intValue() == stopsDto.getId_station().getKey().intValue()) {
                return node;
            }
        }
        return null;
    }

    public Node findNodeByKey(int keyStation) {
        for (Node node : nodes) {
            if (node.getStopsStation().getId_station().getKey() == keyStation) {
                return node;
            }
        }
        return null;
    }
}

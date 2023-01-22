package Graph;

import java.security.interfaces.EdECKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyMapGraph {
    private boolean isDirected;
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public AdjacencyMapGraph(boolean directed) {
        isDirected = directed;
    }

    public int numVertices() {
        return vertices.size();
    }

    public Iterable<Vertex> vertices() {
        return vertices;
    }

    public int numEdge() {
        return edges.size();
    }

    public Iterable<Edge> edges() {
        return edges;
    }

    public int outDegree(Vertex v) {
        return v.getOutgoing().size();
    }

    public Iterable<Edge> outgoing(Vertex v) {
        return v.getOutgoing().values();
    }

    public int inDegree(Vertex v) {
        return v.getIncoming().size();
    }

    public Iterable<Edge> incomingEdges(Vertex v) {
        return v.getIncoming().values();
    }

    public Edge getEdge(Vertex u, Vertex v) {
        return u.getOutgoing().get(v);
    }

    public Vertex getVertex(int id) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getPerson().getId() == id) {
                return vertices.get(i);
            }
        }
        return null;
    }

    public Vertex[] endVertices(Edge e) {
        return e.getEndpoint();
    }

    public Vertex opposite(Vertex v, Edge e) {
        Vertex[] endpoints = e.getEndpoint();
        if (endpoints[0] == v)
            return endpoints[1];
        else if (endpoints[1] == v)
            return endpoints[0];
        else throw new IllegalArgumentException("v is not incident to this edges.");

    }

    public Vertex insertVertex(Person element) {
        Vertex v = new Vertex(element, isDirected);
        vertices.add(v);
        return v;
    }

    public Edge insertEdge(Vertex u, Vertex v, Integer element) {
        if (getEdge(u, v) == null) {
            Edge e = new Edge(u, v, element);
            edges.add(e);
            u.getOutgoing().put(v, e);
            v.getIncoming().put(u, e);
            return e;
        } else
            return null;
    }

    public void removeVertex(Vertex v) {
        for (Edge e : v.getOutgoing().values())
            removeEdges(e);
        for (Edge e : v.getIncoming().values())
            removeEdges(e);
        vertices.remove(v);
    }

    public void removeEdges(Edge e) {

        e.getEndpoint()[0].getOutgoing().remove(e.getEndpoint()[1]);
        e.getEndpoint()[1].getIncoming().remove(e.getEndpoint()[0]);

        e.getEndpoint()[0] = null;
        e.getEndpoint()[1] = null;
        edges.remove(e);
    }

    public void BFS(Vertex s, ArrayList<Vertex> known, Map<Vertex, Integer> forest) {
        int levelIndex = 0;
        List<Vertex> level = new ArrayList<>();
        //known.add(s);
        level.add(s);
        while (!level.isEmpty() || levelIndex <= 5) {
            int pointDegree = pointOfDegree(levelIndex);
            List<Vertex> nextLevel = new ArrayList<>();
            for (Vertex u : level)
                for (Edge e : this.outgoing(u)) {
                    Vertex v = this.opposite(u, e);

                    if (!contains(known,v)) {
                        known.add(v);
                        forest.put(v, pointDegree);//key of forest show point of degree
                        nextLevel.add(v);
                    }
                }

            if (levelIndex == 0) known.clear();
            level = nextLevel;
            levelIndex++;
        }
        known.remove(s);
        forest.remove(s);
    }

    private int pointOfDegree(int LevelIndex) {
        int point = 0;
        switch (LevelIndex) {
            case 1:
                point = 5;
                break;
            case 2:
                point = 4;
                break;
            case 3:
                point = 3;
                break;
            case 4:
                point = 2;
                break;
            case 5:
                point = 1;
                break;
        }
        return point;
    }

    private boolean contains(ArrayList<Vertex> known, Vertex v) {
        boolean answer = false;
        for (int i = 0; i < known.size(); i++) {
            if (known.get(i).equals(v)) {
                answer = true;
                break;
            }
        }
        return answer;
    }


}

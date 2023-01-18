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

    public AdjacencyMapGraph(boolean directed){isDirected=directed;}

    public int numVertices(){return vertices.size();}

    public Iterable<Vertex> vertices(){return vertices;}

    public int numEdge(){return edges.size();}

    public Iterable<Edge> edges(){return edges;}

    public int outDegree(Vertex v){
        return v.getOutgoing().size();
    }

    public Iterable<Edge> outgoing(Vertex v){
        return v.getOutgoing().values();
    }

    public int inDegree(Vertex v){
        return v.getIncoming().size();
    }

    public Iterable<Edge> incomingEdges(Vertex v){
        return  v.getIncoming().values();
    }

    public Edge getEdge(Vertex u,Vertex v){
        return u.getOutgoing().get(v);
    }

    public Vertex[] endVertices(Edge e){
        return e.getEndpoint();
    }

    public Vertex opposite(Vertex v,Edge e){
        Vertex[] endpoints =e.getEndpoint();
        if (endpoints[0]==v)
            return endpoints[1];
        else if (endpoints[1]==v)
            return endpoints[0];
        else throw new IllegalArgumentException("v is not incident to this edges.");

    }

    public Vertex insertVertex(Integer element){
        Vertex v = new Vertex(element,isDirected);
        vertices.add(v);
        return v;
    }

    public Edge insertEdge(Vertex u,Vertex v, Integer element){
        if (getEdge(u,v)==null){
            Edge e = new Edge(u,v,element);
            edges.add(e);
            u.getOutgoing().put(v,e);
            v.getIncoming().put(u,e);
            return e;
        }else
            throw new IllegalArgumentException("Edge from u to v exists ");
    }

    public void removeVertex(Vertex v){
        for (Edge e :v.getOutgoing().values())
            removeEdges(e);
        for (Edge e :v.getIncoming().values())
            removeEdges(e);
        vertices.remove(v);
    }

    public void removeEdges(Edge e){

        e.getEndpoint()[0].getOutgoing().remove(e.getEndpoint()[1]);
        e.getEndpoint()[1].getIncoming().remove(e.getEndpoint()[0]);

        e.getEndpoint()[0]=null;
        e.getEndpoint()[1]=null;
        edges.remove(e);
    }

    void BFS (Vertex s, Set<Vertex> known, Map<Vertex,Edge> forest){
        List<Vertex> level = new ArrayList<>();
        known.add(s);
        level.add(s);
        while (!level.isEmpty()){
            List<Vertex> nextLevel = new ArrayList<>();
            for (Vertex u:level)
                for (Edge e:this.outgoing(u)){
                    Vertex v = this.opposite(u,e);
                    if (!known.contains(v)){
                        known.add(v);
                        forest.put(v,e);
                        nextLevel.add(v);
                    }
                }
            level=nextLevel;
        }
    }




}

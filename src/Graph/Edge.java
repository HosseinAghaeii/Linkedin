package Graph;

 public class Edge {
  private Integer element;

  private Vertex[] endpoint;
  public Edge(Vertex u, Vertex v,Integer elem){
   element=elem;
   endpoint =  new Vertex[]{u,v};
  }

  public Integer getElement() {
   return element;
  }

  public Vertex[] getEndpoint() {
   return endpoint;
  }

 }

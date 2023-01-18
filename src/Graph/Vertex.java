package Graph;

import java.util.HashMap;
import java.util.Map;

public class Vertex {
     private Integer element;

     private Map<Vertex,Edge> outgoing,incoming;
     public Vertex(Integer elem,boolean graphIsDirection){
          element = elem;
          outgoing = new HashMap<>();
          if (graphIsDirection)
               incoming = new HashMap<>();
          else
               incoming=outgoing;
     }

     public Integer getElement() {
          return element;
     }



     public Map<Vertex, Edge> getOutgoing() {
          return outgoing;
     }

     public Map<Vertex, Edge> getIncoming() {
          return incoming;
     }


}

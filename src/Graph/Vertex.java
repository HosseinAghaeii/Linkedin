package Graph;

import Model.Person;

import java.util.HashMap;
import java.util.Map;


public class Vertex {
     private Person person;

     private Map<Vertex,Edge> outgoing,incoming;
     public Vertex(Person elem,boolean graphIsDirection){
          person = elem;
          outgoing = new HashMap<>();
          if (graphIsDirection)
               incoming = new HashMap<>();
          else
               incoming=outgoing;
     }

     public Person getPerson() {
          return person;
     }



     public Map<Vertex, Edge> getOutgoing() {
          return outgoing;
     }

     public Map<Vertex, Edge> getIncoming() {
          return incoming;
     }


}

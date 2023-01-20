package Controller;

import Graph.AdjacencyMapGraph;
import Graph.Person;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildGraph {
   private AdjacencyMapGraph graph = new AdjacencyMapGraph(false);

    public Person[] getPeople() {
        return people;
    }

    public AdjacencyMapGraph getGraph() {
        return graph;
    }

    private Person[] people = new Person[2000];

    private void read() throws IOException {

        BufferedReader reader=new BufferedReader(new FileReader("users.json"));
        ObjectMapper mapper=new ObjectMapper();
        JsonNode node=mapper.readTree(reader);

        for (int i = 0; i < node.size(); i++) {
            people[i]=new Person();
            people[i].setId(node.get(i).path("id").asInt());
            people[i].setName(node.get(i).path("name").asText());
            people[i].setDateOfBirth(node.get(i).path("dateOfBirth").asText());
            people[i].setUniversityLocation(node.get(i).path("universityLocation").asText());
            people[i].setField(node.get(i).path("field").asText());
            people[i].setWorkPlace(node.get(i).path("workPlace").asText());

            ArrayList<String> list = new ArrayList<>();
            for (int j = 0; j < node.get(i).path("specialties").size(); j++) {
                list.add(node.get(i).path("specialties").get(j).asText());
            }
            people[i].setSpecialties(list);

            ArrayList<String> list2 = new ArrayList<>();
            for (int j = 0; j < node.get(i).path("connectionId").size(); j++) {
                list2.add(node.get(i).path("connectionId").get(j).asText());
            }
            people[i].setConnectionId(list2);

            people[i].setEmail(node.get(i).path("email").asText());
        }
    }

    public void build () throws IOException {
        read();
        for (int i = 0; i < people.length; i++) {
            graph.insertVertex(people[i]);
        }
        for (int i = 0; i < graph.numVertices(); i++) {
            int id = people[i].getId();
            ArrayList<String> connectionsId = (ArrayList<String>) graph.getVertex(id).getPerson().getConnectionId();
            for (int j = 0; j < connectionsId.size(); j++) {
                graph.insertEdge(graph.getVertex(id),graph.getVertex(Integer.parseInt(connectionsId.get(j))),1);
            }
        }
    }


}

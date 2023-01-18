import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadJsonFile {
    Person[] people = new Person[999];
    public void read() throws IOException {
        
        BufferedReader reader=new BufferedReader(new FileReader("users2.json"));
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
            List<String> list = new ArrayList<>();
            for (int j = 0; j < node.get(i).path("specialties").size(); j++) {
                list.add(node.get(i).path("specialties").get(j).asText());
            }
            people[i].setSpecialties(list);
            List<String> list2 = new ArrayList<>();
            for (int j = 0; j < node.get(i).path("connectionId").size(); j++) {
                list2.add(node.get(i).path("connectionId").get(j).asText());
            }
            people[i].setConnectionId(list2);
        }
    }
}

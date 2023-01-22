package View;

import Controller.GraphAndJsonFileController;
import Graph.Edge;
import Model.Person;
import Graph.Vertex;

import java.io.IOException;
import java.util.*;

public class UserInterface {
    //int currentID;
    private Person currentPerson;
    private Vertex curVertex;
    GraphAndJsonFileController graphBuilder = new GraphAndJsonFileController();
    Scanner input = new Scanner(System.in);
    int specialityPoint = 13;
    int universityPoint = 6;
    int workPlacePoint = 9;
    int fieldPoint = 10;

    public void menu() throws IOException {
        graphBuilder.build();
        System.out.println("Welcome to Linkedin! :)");
        int continuer;
        do {

            System.out.println("what do you want to do?\tpress a number:");
            System.out.println("1-Login\n2-See list of all user\n3-Search and find an user");
            int chooser = input.nextInt();
            switch (chooser) {
                case 1:
                    login();
                    break;
                case 2:
                    SeeAllUser();
                    break;
                case 3:
                    search();
                    break;
            }
            System.out.println("Do you want to continue? Yes(1) No(0)");
            continuer = input.nextInt();
        } while (continuer == 1);

        System.out.println("GOOD BYE!");
    }

    private void login() {
        System.out.println("please login in your account(inter your ID):");
        int id = input.nextInt();
        if (id < 1 || id > 2000) {
            System.out.println("invalid Id!");
            System.out.println("Do you want to login again? Yes(1)  No(0)");
            int chooser = input.nextInt();
            if (chooser == 1)
                login();
            else return;
        }
        currentPerson = graphBuilder.getGraph().getVertex(id).getPerson();
        curVertex = graphBuilder.getGraph().getVertex(currentPerson.getId());
        System.out.println("Welcome in your account dear " + currentPerson.getName());
        int continuer;
        do {
            System.out.println("What do you want to do?\tpress a number");
            System.out.println("1-see your information's\n2-connect to another user\n3-show 20 or less user suggestion to you");
            int chooser = input.nextInt();
            switch (chooser) {
                case 1:
                    showInformation(currentPerson);
                    break;
                case 2:
                    connectToAnother();
                    break;
                case 3:
                    showSuggestions();
                    break;
            }
            System.out.println("Do you want stay in your account? Yes(1) No(0)");
            continuer = input.nextInt();
        } while (continuer == 1);
    }

    private void showSuggestions() {
        System.out.println("Do you want people similar to you to be suggested based on the logic of the program?\t(press 1)");
        System.out.println("Or you want to determine the effective factors\t(press 2)");
        int chooser = input.nextInt();
        switch (chooser) {
            case 1:
                suggestion(specialityPoint, universityPoint, workPlacePoint, fieldPoint);
                break;
            case 2:
                orderSuggestion();
                break;
        }
    }

    private void orderSuggestion() {
        int continuee;
        int zSpeciality = 1;
        int zUniversity = 1;
        int zWorkPlace = 1;
        int zField = 1;
        do {
            System.out.println("Which factor do you want to be more effective? press a number");
            System.out.println("1-specialities\n2-university location\n3-work place\n4-field");

            int chooser = input.nextInt();
            switch (chooser) {
                case 1:
                    zSpeciality = 2;
                    break;
                case 2:
                    zUniversity = 2;
                    break;
                case 3:
                    zWorkPlace = 2;
                    break;
                case 4:
                    zField = 2;
                    break;
            }
            System.out.println("Do you want to choose a factor again? Yes(1) NO(0)");
            continuee = input.nextInt();
        } while (continuee == 1);
        suggestion(specialityPoint * zSpeciality, universityPoint * zUniversity, workPlacePoint * zWorkPlace, fieldPoint * zField);
    }

    private void showInformation(Person person) {
        System.out.println("Id: " + person.getId());
        System.out.println("name: " + person.getName());
        System.out.println("Date of birth: " + person.getDateOfBirth());
        System.out.println("Email: " + person.getEmail());
        System.out.println("University Location: " + person.getUniversityLocation());
        System.out.println("field: " + person.getField());
        System.out.println("work place: " + person.getWorkPlace());
        System.out.println("specialties: ");
        for (int i = 0; i < person.getSpecialties().size(); i++) {
            System.out.println("    " + "*" + person.getSpecialties().get(i));
        }
        System.out.println("id of people who follow: ");
        for (int i = 0; i < person.getConnectionId().size(); i++) {
            System.out.print(person.getConnectionId().get(i) + ", ");
        }
        System.out.println();

    }

    private void SeeAllUser() {
        for (int i = 0; i < graphBuilder.getPeople().length; i++) {
            showInformation(graphBuilder.getPeople()[i]);
            System.out.println("**************************************************************************");
            System.out.println("**************************************************************************");
        }
    }

    private void search() {
        System.out.println("On what basis do you want to sort?\tpress a number");
        System.out.println("1-Id\t2-Name");
        int chooser = input.nextInt();
        switch (chooser) {
            case 1: {
                System.out.println("inter Id: ");
                int id = input.nextInt();
                if (!checkId(id)) {
                    System.out.println("invalid Id");
                    return;
                }
                Person person = searchById(id);
                if (person == null)
                    System.out.println("We can not find user with this Id :(");
                else showInformation(person);
            }
            break;
            case 2: {
                System.out.println("inter name: ");
                input.nextLine();
                String name = input.nextLine();
                Person person = searchByName(name);
                if (person == null)
                    System.out.println("We can not find user with this name :(");
                else showInformation(person);
            }
            break;
        }
    }

    private boolean checkId(int id) {
        boolean check = true;
        if (id < 1 || id > 2000) {
            check = false;
        }
        return check;
    }

    private Person searchById(int id) {
        Person person = null;
        for (int i = 0; i < graphBuilder.getPeople().length; i++) {
            if (graphBuilder.getPeople()[i].getId() == id) {
                person = graphBuilder.getPeople()[i];
                break;
            }
        }
        return person;
    }

    private Person searchByName(String name) {
        Person person2 = null;
        for (int i = 0; i < graphBuilder.getPeople().length; i++) {
            if (graphBuilder.getPeople()[i].getName().equals(name)) {
                person2 = graphBuilder.getPeople()[i];
                break;
            }
        }
        return person2;
    }

    private void connectToAnother() {
        System.out.println("How do you want your suggestions to be based on? ID(1) or name(2)?");
        int chooser = input.nextInt();
        switch (chooser) {
            case 1: {
                System.out.println("inter Id: ");
                int id = input.nextInt();
                connectWithId(id);
            }
            break;
            case 2: {
                System.out.println("inter Name: ");
                input.nextLine();
                String name = input.nextLine();
                connectWithName(name);
            }
        }
    }

    private void connectWithId(int id) {
        if (!checkId(id)) {
            System.out.println("invalid id");
            return;
        }
        Vertex v = graphBuilder.getGraph().getVertex(id);
        Vertex curV = graphBuilder.getGraph().getVertex(currentPerson.getId());
        Edge e = graphBuilder.getGraph().insertEdge(curV, v, 1);
        if (e == null) System.out.println("Sorry! they have connected before");
        else {
            curV.getPerson().getConnectionId().add(String.valueOf(id));
            v.getPerson().getConnectionId().add(String.valueOf(curV.getPerson().getId()));
            System.out.println("Congratulations!\tyou follow " + v.getPerson().getName());
            System.out.println("Now, see list of users that you are follow");
            for (int i = 0; i < currentPerson.getConnectionId().size(); i++) {
                System.out.println(currentPerson.getConnectionId().get(i));
            }
        }

    }

    private void connectWithName(String name) {
        Person person = null;
        for (int i = 0; i < graphBuilder.getPeople().length; i++) {
            if (graphBuilder.getPeople()[i].getName().equals(name)) {
                person = graphBuilder.getPeople()[i];
                break;
            }
        }
        if (person == null) {
            System.out.println("Sorry! we can not find this user!");
        } else {
            Vertex v = graphBuilder.getGraph().getVertex(person.getId());
            Vertex curV = graphBuilder.getGraph().getVertex(currentPerson.getId());
            Edge e = graphBuilder.getGraph().insertEdge(curV, v, 1);
            if (e == null) System.out.println("Sorry! they have connected before");
            else {
                currentPerson.getConnectionId().add(String.valueOf(person.getId()));
                v.getPerson().getConnectionId().add(String.valueOf(curV.getPerson().getId()));
                System.out.println("Congratulations!\tyou follow " + v.getPerson().getName());
                System.out.println("Now, see list of users that you are follow");
                for (int i = 0; i < currentPerson.getConnectionId().size(); i++) {
                    System.out.println(currentPerson.getConnectionId().get(i));
                }
            }
        }
    }

    private void suggestion(int specialityPoint, int universityPoint, int workPlacePoint, int fieldPoint) {
        ArrayList<Vertex> known = new ArrayList<>();
        Map<Vertex, Integer> forest = new HashMap<>();//value is point degree

        Map<Vertex,Integer> suggestion = new HashMap<>();//key is final point
        graphBuilder.getGraph().BFS(curVertex, known, forest);

         for (int i = 0; i < forest.size(); i++) {
            Vertex v = known.get(i);
            suggestion.put(v,computePoint(forest, v, specialityPoint, universityPoint, workPlacePoint, fieldPoint));
        }
        ArrayList<Vertex> top20 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            Map.Entry<Vertex,Integer> entryWithMaxKey = null;
            for (Map.Entry<Vertex,Integer> temp : suggestion.entrySet()) {
                if (entryWithMaxKey == null || temp.getValue().compareTo(entryWithMaxKey.getValue()) > 0)
                    entryWithMaxKey = temp;
            }
            if (entryWithMaxKey != null) {
                top20.add(entryWithMaxKey.getKey());
                suggestion.remove(entryWithMaxKey.getKey(), entryWithMaxKey.getValue());
            }
        }
        printTop20(top20);

    }

    private int computePoint(Map<Vertex, Integer> forest, Vertex v, int specialityPoint, int universityPoint, int workPlacePoint, int fieldPoint) {
        int finalPoint = 0;
        ArrayList<String> list = new ArrayList<>();
        for (String temp : v.getPerson().getSpecialties()) {
            if (currentPerson.getSpecialties().contains(temp)) {
                list.add(temp);
            }
        }
        finalPoint += specialityPoint * list.size();
        finalPoint += forest.get(v);
        if (currentPerson.getUniversityLocation().equals(v.getPerson().getUniversityLocation()))
            finalPoint += universityPoint;
        if (currentPerson.getField().equals(v.getPerson().getField()))
            finalPoint += fieldPoint;
        if (currentPerson.getWorkPlace().equals(v.getPerson().getWorkPlace()))
            finalPoint += workPlacePoint;
        return finalPoint;
    }

    private void printTop20(ArrayList<Vertex> top20) {
        for (int i = 0; i < top20.size(); i++) {
            System.out.print(i + 1 + "-");
            showInformation(top20.get(i).getPerson());
            System.out.println("***************************************************************************");
            System.out.println("***************************************************************************");
        }
    }

}

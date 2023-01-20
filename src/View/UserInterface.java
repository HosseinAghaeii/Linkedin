package View;

import Controller.BuildGraph;
import Graph.Person;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    int currentID;
    private Person currentPerson;
    BuildGraph graphBuilder = new BuildGraph();
    Scanner input = new Scanner(System.in);

    public void menu() throws IOException {
        graphBuilder.build();
        System.out.println("Welcome to Linkedin! :)");
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
    }

    private void login() {
        System.out.println("please login in your account(inter your ID):");
        int id = input.nextInt();
        if (id < 1 || id > 999) {
            System.out.println("invalid Id!");
            System.out.println("Do you want to login again? Yes(1)  No(0)");
            int chooser = input.nextInt();
            if (chooser == 1)
                login();
            else return;
        }
        currentPerson = graphBuilder.getGraph().getVertex(id).getPerson();
        System.out.println("Do you want see your information? Yes(1)  NO(0)");
        int chooser = input.nextInt();
        if (chooser == 1) {
            showInformation(currentPerson);
        }
        //todo
    }

    private void showInformation(Person person) {
        System.out.println("Id: " + person.getId());
        System.out.println("name: " + person.getName());
        System.out.println("Date of birth: " + person.getDateOfBirth());
        System.out.println("University Location: " + person.getUniversityLocation());
        System.out.println("field: " + person.getField());
        System.out.println("work spase: " + person.getWorkPlace());
        System.out.println("specialties: ");
        for (int i = 0; i < person.getSpecialties().size(); i++) {
            System.out.println("    " + "*" + person.getSpecialties().get(i));
        }
        System.out.println("id of people who connect you: ");
        for (int i = 0; i < person.getConnectionId().size(); i++) {
            System.out.print(person.getConnectionId().get(i) + ", ");
        }

    }

    private void SeeAllUser() {
        for (int i = 0; i < graphBuilder.getPeople().length; i++) {
            showInformation(graphBuilder.getPeople()[i]);
            System.out.println("**************************");
            System.out.println();
            System.out.println("**************************");
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
                Person person = sortById(id);
                if (person == null)
                    System.out.println("We can not find user with this Id :(");
                else showInformation(person);
            }
            break;
            case 2: {
                System.out.println("inter name: ");
                String name = input.next();
                Person person = sortByName(name);
                if (person == null)
                    System.out.println("We can not find user with this name :(");
                else showInformation(person);
            }
            break;
        }
    }

    private boolean checkId(int id) {
        boolean check = true;
        if (id < 1 || id > 999) {
            check = false;
        }
        return check;
    }

    private Person sortById(int id) {
        Person person = null;
        for (int i = 0; i < graphBuilder.getPeople().length; i++) {
            if (graphBuilder.getPeople()[i].getId() == id) {
                person = graphBuilder.getPeople()[i];
                break;
            }
        }
        return person;
    }

    private Person sortByName(String name) {
        Person person2 = null;
        for (int i = 0; i < graphBuilder.getPeople().length; i++) {
            if (graphBuilder.getPeople()[i].getName().equals( name)) {
                person2 = graphBuilder.getPeople()[i];
                break;
            }
        }
        return person2;
    }


}

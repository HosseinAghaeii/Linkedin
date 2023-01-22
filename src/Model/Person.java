package Model;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int id;
    private String name;
    private String dateOfBirth;
    private String universityLocation;
    private String field;
    private String workPlace;
    private ArrayList<String> specialties;
    private ArrayList<String> connectionId;
    private String email;

    public Person(int id, String name, String dateOfBirth, String universityLocation, String field, String workPlace, ArrayList<String> specialties, ArrayList<String> connectionId) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.universityLocation = universityLocation;
        this.field = field;
        this.workPlace = workPlace;
        this.specialties = specialties;
        this.connectionId = connectionId;
    }

    public Person(){}

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setUniversityLocation(String universityLocation) {
        this.universityLocation = universityLocation;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public void setSpecialties(ArrayList<String> specialties) {
        this.specialties = specialties;
    }

    public void setConnectionId(ArrayList<String> connectionId) {
        this.connectionId = connectionId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUniversityLocation() {
        return universityLocation;
    }

    public String getField() {
        return field;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public List<String> getSpecialties() {
        return specialties;
    }

    public List<String> getConnectionId() {
        return connectionId;
    }
}

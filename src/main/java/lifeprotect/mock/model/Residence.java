package lifeprotect.mock.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Residence extends PersistableElement {

    private String adress;

    private String phone;

    private String email;

    private Timestamp creationdate;

    private List<Person> people;

    public Residence(String adress, String phone, String email, Timestamp creationdate) {
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.creationdate = creationdate;
        this.people = new ArrayList<>();
    }

    public Residence() {
        this.people = new ArrayList<>();
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Timestamp creationdate) {
        this.creationdate = creationdate;
    }

    public void addPerson(Person p){
        if(!people.contains(p)){
            people.add(p);
            p.addResidence(this);
        }
    }

    public void removePerson(Person p){
        if(people.contains(p)){
            people.remove(p);
            p.removeResidence(this);
        }
    }

    @Override
    public String toString() {
        return "Residence{" +
                "adress='" + adress + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", creationdate=" + creationdate +
                ", people=" + people +
                '}';
    }
}

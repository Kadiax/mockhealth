package lifeprotect.mock.model;

import javax.persistence.*;
import java.util.List;

public class Person extends PersistableElement{

    private String firstName;

    private String lastName;

    private String birthdate;

    private String email;

    private String phone;

    private String handicap;

    private String averageincome;

    private String login;

    private String password;

    private String deseas;

    private String ismobile;

    private Long userevaluation;

    private PersonStatus userrole;

    private Residence residence;

    private Strap strap;

    public Person(){}

    public Person(String firstName, String lastName, String birthdate, String email, String phone, String handicap, String averageincome, String login, String password, String deseas, String ismobile, Long userevaluation, PersonStatus userrole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.handicap = handicap;
        this.averageincome = averageincome;
        this.login = login;
        this.password = password;
        this.deseas = deseas;
        this.ismobile = ismobile;
        this.userevaluation = userevaluation;
        this.userrole = userrole;

    }

    public String getDeseas() {
        return deseas;
    }

    public void setDeseas(String deseas) {
        this.deseas = deseas;
    }

    public String getIsmobile() {
        return ismobile;
    }

    public void setIsmobile(String ismobile) {
        this.ismobile = ismobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserevaluation() {
        return userevaluation;
    }

    public void setUserevaluation(Long userevaluation) {
        this.userevaluation = userevaluation;
    }

    public PersonStatus getUserrole() {
        return userrole;
    }

    public void setUserrole(PersonStatus userrole) {
        this.userrole = userrole;
    }

    public Strap getStrap() {
        return strap;
    }

    public void setStrap(Strap strap) {
        this.strap = strap;
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHandicap() {
        return handicap;
    }

    public void setHandicap(String handicap) {
        this.handicap = handicap;
    }

    public String getAverageincome() {
        return averageincome;
    }

    public void setAverageincome(String averageincome) {
        this.averageincome = averageincome;
    }

    @Override
    public String toString() {
        return "Person{" +super.getId()+
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", handicap='" + handicap + '\'' +
                ", averageincome='" + averageincome + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", deseas='" + deseas + '\'' +
                ", ismobile='" + ismobile + '\'' +
                ", userevaluation=" + userevaluation +
                ", userrole=" + userrole +
                '}';
    }

    public void addResidence(Residence residence) {
        this.residence = residence;
    }

    public void removeResidence(Residence residence) {
        this.residence= null;
    }

}

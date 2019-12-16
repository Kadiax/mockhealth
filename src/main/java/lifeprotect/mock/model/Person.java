package lifeprotect.mock.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "person")
public class Person extends PersistableElement{

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="birthdate")
    private String birthdate;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="handicap")
    private String handicap;

    @Column(name="averageincome")
    private String averageincome;

    @Column(name="login")
    private String login;

    @Column(name="password")
    private String password;

    @Column(name="deseas")
    private String deseas;

    @Column(name="ismobile")
    private String ismobile;

    @Column(name="userevaluation")
    private Long userevaluation;

    @Enumerated(EnumType.STRING)
    private PersonStatus userrole;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Light> lights;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Shutter> shutters;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Heater> heaters;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Clock> clocks;

    @ManyToOne(cascade = CascadeType.ALL)
    private Residence residence;

    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
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

    public List<Light> getLights() {
        return lights;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
    }

    public List<Shutter> getShutters() {
        return shutters;
    }

    public void setShutters(List<Shutter> shutters) {
        this.shutters = shutters;
    }

    public Strap getStrap() {
        return strap;
    }

    public void setStrap(Strap strap) {
        this.strap = strap;
    }

    public List<Heater> getHeaters() {
        return heaters;
    }

    public void setHeaters(List<Heater> heaters) {
        this.heaters = heaters;
    }

    public List<Clock> getClocks() {
        return clocks;
    }

    public void setClocks(List<Clock> clocks) {
        this.clocks = clocks;
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
        return "Person{" +
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
                ", lights=" + lights +
                ", strap=" + strap +
                '}';
    }

    public void addResidence(Residence residence) {
        this.residence = residence;
    }

    public void removeResidence(Residence residence) {
        this.residence= null;
    }

}

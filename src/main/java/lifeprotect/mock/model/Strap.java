package lifeprotect.mock.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name="strap")
public class Strap extends IOT{


    @Column(name="minsysto")
    private String minsysto;

    @Column(name="maxsysto")
    private String maxsysto;

    @Column(name="maxdiasto")
    private String maxdiasto;

    @Column(name="minglyc")
    private String minglyc;

    @Column(name="maxglyc")
    private String maxglyc;

    @Column(name="minsteps")
    private String minsteps;

    @OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "strap")
    private List<HealthHistoric> healthhistorics;

    public Strap(String status, String state, String ipadress, Timestamp startdate, String minvalueref, String maxvalueref, String suspect, Timestamp activityduration, String minsysto, String maxsysto, String maxdiasto, String minglyc, String maxglyc, String minsteps, Person person) {
        super(status, state, ipadress, startdate, minvalueref, maxvalueref, suspect, activityduration);
        this.minsysto = minsysto;
        this.maxsysto = maxsysto;
        this.maxdiasto = maxdiasto;
        this.minglyc = minglyc;
        this.maxglyc = maxglyc;
        this.minsteps = minsteps;
        this.person = person;
        healthhistorics = new ArrayList<>();
    }

    public Strap() {
    }

    public String getMinsysto() {
        return minsysto;
    }

    public void setMinsysto(String minsysto) {
        this.minsysto = minsysto;
    }

    public String getMaxsysto() {
        return maxsysto;
    }

    public void setMaxsysto(String maxsysto) {
        this.maxsysto = maxsysto;
    }

    public String getMaxdiasto() {
        return maxdiasto;
    }

    public void setMaxdiasto(String maxdiasto) {
        this.maxdiasto = maxdiasto;
    }

    public String getMinglyc() {
        return minglyc;
    }

    public void setMinglyc(String minglyc) {
        this.minglyc = minglyc;
    }

    public String getMaxglyc() {
        return maxglyc;
    }

    public void setMaxglyc(String maxglyc) {
        this.maxglyc = maxglyc;
    }

    public String getMinsteps() {
        return minsteps;
    }

    public void setMinsteps(String minsteps) {
        this.minsteps = minsteps;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        person.setStrap(this);
        this.person = person;
    }

    @Override
    public String toString() {
        return "Strap{" +
                "minsysto='" + minsysto + '\'' +
                "minsysto='" + minsysto + '\'' +
                ", maxsysto='" + maxsysto + '\'' +
                ", maxdiasto='" + maxdiasto + '\'' +
                ", minglyc='" + minglyc + '\'' +
                ", maxglyc='" + maxglyc + '\'' +
                ", minsteps='" + minsteps + '\'' +
                ", person=" + person +
                '}';
    }

    public List<HealthHistoric> getHealthhistorics() {
        return healthhistorics;
    }

    public void setHealthhistorics(List<HealthHistoric> healthhistorics) {
        this.healthhistorics = healthhistorics;
    }

    public void addHealthHistocic(HealthHistoric historic){
        if(!healthhistorics.contains(historic)){
            healthhistorics.add(historic);
            historic.setStrap(this);
        }
    }

    public void removeHealthHistocic(HealthHistoric historic){
        if(healthhistorics.contains(historic)){
            healthhistorics.remove(historic);
            historic.removeStrap();
        }
    }
}

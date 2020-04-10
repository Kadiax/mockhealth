package lifeprotect.mock.model;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity(name="strap")
public class Strap extends IOT{

    @Column(name="minsysto")
    private String minsysto;

    @Column(name="maxsysto")
    private String maxsysto;

    @Column(name="mindiasto")
    private String mindiasto;

    @Column(name="maxdiasto")
    private String maxdiasto;

    @Column(name="minglyc")
    private String minglyc;

    @Column(name="maxglyc")
    private String maxglyc;

    @Column(name="minsteps")
    private String minsteps;

    @Column(name="healthstate")
    private String healthstate;

    @OneToOne(cascade = CascadeType.ALL, fetch= FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    public Strap(Double price, StatusEnum breakdownstatus, StateEnum state, String ipadress, Timestamp startdate, String minvalueref, String maxvalueref, String suspect, Timestamp activityduration, String minsysto, String maxsysto, String mindiasto,String maxdiasto, String minglyc, String maxglyc, String minsteps, Person person) {
        super(price, breakdownstatus, state, ipadress, startdate, minvalueref, maxvalueref, suspect, activityduration);
        this.minsysto = minsysto;
        this.maxsysto = maxsysto;
        this.mindiasto = mindiasto;
        this.maxdiasto = maxdiasto;
        this.minglyc = minglyc;
        this.maxglyc = maxglyc;
        this.minsteps = minsteps;
        this.person = person;
    }

    public Strap() {}

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

    public String getMindiasto() {
        return mindiasto;
    }

    public void setMindiasto(String mindiasto) {
        this.mindiasto = mindiasto;
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
        this.person = person;
    }

    public String getHealthstate() {
        return healthstate;
    }

    public void setHealthstate(String healthstate) {
        this.healthstate = healthstate;
    }

    @Override
    public String toString() {
        return "Strap{" +
                "minsysto='" + minsysto + '\'' +
                ", maxsysto='" + maxsysto + '\'' +
                ", mindiasto='" + mindiasto + '\'' +
                ", maxdiasto='" + maxdiasto + '\'' +
                ", minglyc='" + minglyc + '\'' +
                ", maxglyc='" + maxglyc + '\'' +
                ", minsteps='" + minsteps + '\'' +
                ", healthstate='" + healthstate + '\'' +
                ", person=" + person +
                '}';
    }
}

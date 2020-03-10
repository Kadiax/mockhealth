package lifeprotect.mock.model;

import javax.persistence.*;
import java.sql.Timestamp;


public class Strap extends IOT{

    private String minsysto;

    private String maxsysto;

    private String mindiasto;

    private String maxdiasto;

    private String minglyc;

    private String maxglyc;

    private String minsteps;

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

    @Override
    public String toString() {
        return "Strap{" +
                "minsysto='" + minsysto + '\'' +
                ", maxsysto='" + maxsysto + '\'' +
                ", maxdiasto='" + maxdiasto + '\'' +
                ", minglyc='" + minglyc + '\'' +
                ", maxglyc='" + maxglyc + '\'' +
                ", minsteps='" + minsteps + '\'' +
                ", person=" + person +
                '}';
    }
}

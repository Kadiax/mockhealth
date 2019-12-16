package lifeprotect.mock.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity(name="light")
public class Light extends IOT{

    @Column(name="color")
    private String color;

    @Column(name = "percentage")
    private String percentage;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

    public Light(String status, String state, String ipadress, Timestamp startdate, String minvalueref, String maxvalueref, String suspect, Timestamp activityduration, String color, String percentage, Person person) {
        super(status, state, ipadress, startdate, minvalueref, maxvalueref, suspect, activityduration);
        this.color = color;
        this.percentage = percentage;
        this.person = person;
    }

    public Light() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}

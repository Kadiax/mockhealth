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

    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;

    public Light(Double price, StatusEnum breakdownstatus, StateEnum state, String ipadress, Timestamp startdate, String minvalueref, String maxvalueref, String suspect, Timestamp activityduration, String color, String percentage, Person person, Room room) {
        super(price, breakdownstatus, state, ipadress, startdate, minvalueref, maxvalueref, suspect, activityduration);
        this.color = color;
        this.percentage = percentage;
        this.person = person;
        this.room = room;
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

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }
}

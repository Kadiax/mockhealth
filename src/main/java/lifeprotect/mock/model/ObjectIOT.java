package lifeprotect.mock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "objectiot")
public class ObjectIOT extends PersistableElement {
    @Column(name="name")
    private String name;
    @Column(name="status")
    private String status;
    @Column(name="date")
    private Date date;
    @Column(name="minValue")
    private int minValue;
    @Column(name="maxValue")
    private int maxValue;
    @Column(name="actualValue")
    private int actualValue;

    public ObjectIOT(){}

    public ObjectIOT(String name, String status, Date date, int minValue, int maxValue, int actualValue) {
        this.name = name;
        this.status = status;
        this.date =date;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.actualValue=actualValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getActualValue() {
        return actualValue;
    }

    public void setActualValue(int actualValue) {
        this.actualValue = actualValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

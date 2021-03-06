package lifeprotect.mock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

public class AlertHealth extends PersistableElement{
    private String message;

    private Timestamp startdate;

    private String criticity;

    private Long strapId;

    public AlertHealth(String message, Timestamp startdate, String criticity, Long strap) {
        this.message = message;
        this.startdate = startdate;
        this.criticity = criticity;
        this.strapId = strap;
    }

    public AlertHealth(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Long getStrapId() {
        return strapId;
    }

    public void setStrapId(Long strapId) {
        this.strapId = strapId;
    }

    public String getCriticity() {
        return criticity;
    }

    public void setCriticity(String criticity) {
        this.criticity = criticity;
    }
}

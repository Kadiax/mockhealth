package lifeprotect.mock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity(name="alerthealth")
public class AlertHealth extends PersistableElement{
    @Column(name="message")
    private String message;

    @Column(name="startdate")
    private Timestamp startdate;

    @Column(name="criticity")
    private String criticity;

    @Column(name="status")
    private String status;

    @Column(name="strapid")
    private Long strap;

    @Column(name="doctorid")
    private Long doctor;

    public AlertHealth(String message, Timestamp startdate, String criticity, String status, Long strap, Long doctor) {
        this.message = message;
        this.startdate = startdate;
        this.criticity = criticity;
        this.status = status;
        this.strap = strap;
        this.doctor = doctor;
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

    public String getCriticity() {
        return criticity;
    }

    public void setCriticity(String criticity) {
        this.criticity = criticity;
    }

    public Long getStrap() {
        return strap;
    }

    public void setStrap(Long strap) {
        this.strap = strap;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "AlertHealth{" +
                "message='" + message + '\'' +
                ", startdate=" + startdate +
                ", criticity='" + criticity + '\'' +
                ", status='" + status + '\'' +
                ", strap=" + strap +
                ", doctor=" + doctor +
                '}';
    }
}

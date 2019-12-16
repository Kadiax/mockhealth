package lifeprotect.mock.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public class IOT implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Version
    @Column
    protected Timestamp optlock;

    @Column(name="status")
    private String status;

    @Column(name="state")
    private String state;

    @Column(name="ipadress")
    private String ipadress;

    @Column(name="startdate")
    private Timestamp startdate;

    @Column(name="minvalueref")
    private String minvalueref;

    @Column(name="maxvalueref")
    private String maxvalueref;

    @Column(name="suspect")
    private String suspect;

    @Column(name="activityduration")
    private Timestamp activityduration;


    public IOT(String status, String state, String ipadress, Timestamp startdate, String minvalueref, String maxvalueref, String suspect, Timestamp activityduration) {
        this.status = status;
        this.state = state;
        this.ipadress = ipadress;
        this.startdate = startdate;
        this.minvalueref = minvalueref;
        this.maxvalueref = maxvalueref;
        this.suspect = suspect;
        this.activityduration = activityduration;
    }

    public IOT() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public String getMinvalueref() {
        return minvalueref;
    }

    public void setMinvalueref(String minvalueref) {
        this.minvalueref = minvalueref;
    }

    public String getMaxvalueref() {
        return maxvalueref;
    }

    public void setMaxvalueref(String maxvalueref) {
        this.maxvalueref = maxvalueref;
    }

    public Timestamp getActivityduration() {
        return activityduration;
    }

    public void setActivityduration(Timestamp activityduration) {
        this.activityduration = activityduration;
    }

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public String getIpadress() {
        return ipadress;
    }

    public void setIpadress(String ipadress) {
        this.ipadress = ipadress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getOptlock() {
        return optlock;
    }

    public void setOptlock(Timestamp optlock) {
        this.optlock = optlock;
    }
}

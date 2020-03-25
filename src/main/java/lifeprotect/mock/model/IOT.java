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

    @Enumerated(EnumType.STRING)
    private StatusEnum breakdownstatus;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @Column(name="price")
    private Double price;

    @Column(name="ipadress")
    private String ipadress;

    @Column(name="minvalueref")
    private String minvalueref;

    @Column(name="maxvalueref")
    private String maxvalueref;

    @Column(name="suspect")
    private String suspect;

    public IOT(Double price, StatusEnum breakdownstatus, StateEnum state, String ipadress, Timestamp startdate, String minvalueref, String maxvalueref, String suspect, Timestamp activityduration) {
        this.breakdownstatus = breakdownstatus;
        this.state = state;
        this.ipadress = ipadress;
        this.minvalueref = minvalueref;
        this.maxvalueref = maxvalueref;
        this.suspect = suspect;
        this.price = price;
    }

    public IOT() {
    }

    public StatusEnum getBreakdownstatus() {
        return breakdownstatus;
    }

    public void setBreakdownstatus(StatusEnum breakdownstatus) {
        this.breakdownstatus = breakdownstatus;
    }

    public StateEnum getState() {
        return state;
    }

    public void setState(StateEnum state) {
        this.state = state;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

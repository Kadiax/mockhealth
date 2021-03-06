package lifeprotect.mock.model;
import javax.persistence.*;
import java.sql.Timestamp;

public class HealthHistoric extends PersistableElement{

    private String hearthrate;

    private String systolic;

    private String diastolic;

    private String sugarlevel;

    private String stepcounter;

    private Timestamp startdate;

    private Long strapid;

    public HealthHistoric(String hearthrate, String systolic, String diastolic, String sugarlevel, String stepcounter, Timestamp startdate, Long strap) {
        this.hearthrate = hearthrate;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.sugarlevel = sugarlevel;
        this.stepcounter = stepcounter;
        this.startdate = startdate;
        this.strapid = strap;
    }

    public HealthHistoric(){}


    public String getHearthrate() {
        return hearthrate;
    }

    public void setHearthrate(String hearthrate) {
        this.hearthrate = hearthrate;
    }

    public String getSystolic() {
        return systolic;
    }

    public void setSystolic(String systolic) {
        this.systolic = systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(String diastolic) {
        this.diastolic = diastolic;
    }

    public String getSugarlevel() {
        return sugarlevel;
    }

    public void setSugarlevel(String sugarlevel) {
        this.sugarlevel = sugarlevel;
    }

    public String getStepcounter() {
        return stepcounter;
    }

    public void setStepcounter(String stepcounter) {
        this.stepcounter = stepcounter;
    }



    public Long getStrapid() {
        return strapid;
    }

    public void setStrapid(Long strapid) {
        this.strapid = strapid;
    }


    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    @Override
    public String toString() {
        return "HealthHistoric{" +
                "hearthrate='" + hearthrate + '\'' +
                ", systolic='" + systolic + '\'' +
                ", diastolic='" + diastolic + '\'' +
                ", sugarlevel='" + sugarlevel + '\'' +
                ", stepcounter='" + stepcounter + '\'' +
                ", startdate=" + startdate +
                '}';
    }

    public String toMessage(){
        return "hearthrate=" + hearthrate +
                ",systolic=" + systolic +
                ",diastolic=" + diastolic +
                ",sugarlevel=" + sugarlevel +
                ",stepcounter=" + stepcounter +
                ",strapid=" + strapid +
                ",startdate=" + startdate;

    }
}

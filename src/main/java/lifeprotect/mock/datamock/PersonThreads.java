package lifeprotect.mock.datamock;

import lifeprotect.mock.dao.HealthHistoricDAO;
import lifeprotect.mock.dao.ResidenceDAO;
import lifeprotect.mock.dao.StrapDAO;
import lifeprotect.mock.model.HealthHistoric;
import lifeprotect.mock.model.Person;
import lifeprotect.mock.model.Residence;
import lifeprotect.mock.model.Strap;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonThreads implements Runnable{
    private Person p;

    private HealthHistoricDAO healthHistoricDAO;
    private List<HealthHistoric> historics;
    private StrapDAO strapDAO;
    private  ResidenceDAO residenceDAO;
    private static volatile Residence residence;

    public PersonThreads(Person p, HealthHistoricDAO healthHistoricDAO, StrapDAO strapDAO, ResidenceDAO residenceDAO){
        this.p=p;
        this.healthHistoricDAO = healthHistoricDAO;
        historics = new ArrayList<>();
        this.strapDAO = strapDAO;
        this.residenceDAO = residenceDAO;
    }


    @Override
    public void run() {
        generateHistoric();
    }


    private void generateHistoric() {

        //Create Historic
        HealthHistoric h = new HealthHistoric("100","150", "80", "0.45", "100", new Timestamp(new Date().getTime()), p.getStrap() );
        HealthHistoric h2 = new HealthHistoric("220","180", "90", "0.44", "200", new Timestamp(new Date().getTime()), p.getStrap() );
        //Save Historic in strap
        Strap s = p.getStrap();
        s.addHealthHistocic(h);
        s.addHealthHistocic(h2);
        //System.out.println(p.toString());

    }

    public static Residence getResidence() {
        return residence;
    }

    public static void setResidence(Residence residence) {
        PersonThreads.residence = residence;
    }
}

package lifeprotect.mock.datamock;

import lifeprotect.mock.dao.HealthHistoricDAO;
import lifeprotect.mock.dao.StrapDAO;
import lifeprotect.mock.model.Person;
import lifeprotect.mock.model.Residence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MockHealthData {
    private Residence residence;
    private List<PersonThreads> personThreads;
    private HealthHistoricDAO healthHistoricDAO;


    public MockHealthData(Residence residence, HealthHistoricDAO healthHistoricDAO)  {
        this.residence = residence;
        personThreads = new ArrayList<>();
        generatePersonsThreads();
        startMockHealthData();
    }

    public void generatePersonsThreads()  {
        for (Person p : residence.getPeople()){
            personThreads.add(new PersonThreads(p, healthHistoricDAO));
        }
    }

    public void startMockHealthData(){
        for (PersonThreads pth: personThreads){
            pth.run();
        }
    }

}

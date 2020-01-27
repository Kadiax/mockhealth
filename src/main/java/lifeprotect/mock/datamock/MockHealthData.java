package lifeprotect.mock.datamock;

import lifeprotect.mock.dao.HealthHistoricDAO;
import lifeprotect.mock.dao.PersonDAO;
import lifeprotect.mock.dao.ResidenceDAO;
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
    private StrapDAO strapDAO;
    private ResidenceDAO residenceDAO;
    private PersonDAO personDAO;

    public MockHealthData(Residence residence, HealthHistoricDAO healthHistoricDAO, StrapDAO strapDAO, ResidenceDAO residenceDAO, PersonDAO pdao)  {
        this.residence = residence;
        personThreads = new ArrayList<>();
        this.strapDAO = strapDAO;
        this.healthHistoricDAO = healthHistoricDAO;
        this.residenceDAO = residenceDAO;
        this.personDAO = pdao;

        //Start simulation
        generatePersonsThreads();
        startMockHealthData();

    }

    public void generatePersonsThreads()  {
        //PersonThreads.setResidence(residence);
        for (Person p : residence.getPeople()){
            PersonThreads pth = new PersonThreads(p, healthHistoricDAO, strapDAO, residenceDAO);
            //PersonThreads.setResidence(residence);
            personThreads.add(pth);
        }
    }

    public void startMockHealthData(){
        for (PersonThreads pth: personThreads){
            pth.run();
        }
    }

}

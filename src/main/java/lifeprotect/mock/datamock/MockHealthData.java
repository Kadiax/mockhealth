package lifeprotect.mock.datamock;

import lifeprotect.mock.model.Person;
import lifeprotect.mock.model.Residence;

import java.util.ArrayList;
import java.util.List;

public class MockHealthData {
    private Residence residence;
    private MockServer mockserver;
    private List<PersonThreads> personThreads;

    public MockHealthData(Residence residence) {
        this.residence = residence;
        personThreads = new ArrayList<>();
    }

    public void generatePersonsThreads(){
        for (Person p : residence.getPeople()){
            personThreads.add(new PersonThreads(p));
        }
    }

    public void generateMockServer(){

    }

}

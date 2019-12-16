package lifeprotect.mock.datamock;

import lifeprotect.mock.dao.HealthHistoricDAO;
import lifeprotect.mock.model.HealthHistoric;
import lifeprotect.mock.model.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonThreads implements Runnable{
    private Person p;

    private HealthHistoricDAO healthHistoricDAO;
    private List<HealthHistoric> historics;


    public PersonThreads(Person p, HealthHistoricDAO healthHistoricDAO){
        this.p=p;
        this.healthHistoricDAO = healthHistoricDAO;
        historics = new ArrayList<>();
    }
    

    @Override
    public void run() {
        generateHistoric();
    }

    private void generateHistoric() {

        //Create Historic
        HealthHistoric h = new HealthHistoric("100","150", "80", "0.45", "100",p.getStrap() );
        //healthHistoricDAO.save(h);
        //System.out.println(h+"___________");
        //Save Historic
       // historics.add(healthHistoricDAO.save(h));
    }

}

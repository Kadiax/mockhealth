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
import java.util.Random;

public class PersonThreads implements Runnable{
    private Person p;

    private static HealthHistoricDAO healthHistoricDAO;
    private List<HealthHistoric> historics;
    private StrapDAO strapDAO;
    private  ResidenceDAO residenceDAO;
    private Random rd;
    private static Residence residence;
    private static boolean changeThreads=true;



    public PersonThreads(Person p, StrapDAO strapDAO, ResidenceDAO residenceDAO){
        this.p=p;

        historics = new ArrayList<>();
        this.strapDAO = strapDAO;
        this.residenceDAO = residenceDAO;
    }
    public static HealthHistoricDAO getHealthHistoricDAO() {
        return healthHistoricDAO;
    }

    public static void setHealthHistoricDAO(HealthHistoricDAO healthHistoricDAO) {
        PersonThreads.healthHistoricDAO = healthHistoricDAO;
    }

    @Override
    public void run() {
        generateHistoric();
    }

    private void generateHistoric() {
        int i = 0;
        rd = new Random();

        double hearthrate=rdHearthRate(p.getStrap());
        int systolic=rdSysto(p.getStrap()), diastolic=rdDiasto(p.getStrap()) ,stepcounter=0;
        double sugarLevel= rdSugarLevel(p.getStrap());

        while(i<4){
            //increment values
            int min = 1, max=2;
            int choice = rd.nextInt(max + 1 - min) + min;
            if(choice==1){
                hearthrate+=2; systolic+=10; diastolic+=5; sugarLevel+=0.2; stepcounter=stepcounter+3;
               }
            else{
                hearthrate-=2; systolic-=5; diastolic-=2; sugarLevel-=0.1; stepcounter=stepcounter+2;
            }

            HealthHistoric h = new HealthHistoric(String.valueOf(hearthrate),String.valueOf(systolic), String.valueOf(diastolic), String.valueOf(sugarLevel), String.valueOf(stepcounter), new Timestamp(new Date().getTime()), p.getStrap().getId() );

            //synchronized (healthHistoricDAO) {
                System.out.println(healthHistoricDAO.saveAndFlush(h));

                //wait
                try {
                    Thread.currentThread().sleep(1000);
                    //while(changeThreads) {
                        //changeThreads=false;
                       // healthHistoricDAO.wait();
                   // }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //changeThreads=true;
                //healthHistoricDAO.notifyAll();
            i++;
            }

        }

    //}

    private double rdHearthRate(Strap s) {
         float min = Float.parseFloat(s.getMinvalueref());
        float max= Float.parseFloat(s.getMaxvalueref());
        return   min + Math.random() * (max - min);
    }

    private int rdSysto(Strap s) {
        int min= Math.abs(Integer.parseInt(s.getMinsysto()));
        int max= Math.abs(Integer.parseInt(s.getMaxsysto()));

        return rd.nextInt(max + 1 - min) + min;
    }

    private int rdDiasto(Strap s) {
        int min= Math.abs(40);
        int max= Math.abs(Integer.parseInt(s.getMaxdiasto()));

        return rd.nextInt(max + 1 - min) + min;
    }

    private double rdSugarLevel(Strap s) {
        float min = Float.parseFloat(s.getMinglyc());
        float max= Float.parseFloat(s.getMaxglyc());
        return   min + Math.random() * (max - min);
    }

}
//Synchronize

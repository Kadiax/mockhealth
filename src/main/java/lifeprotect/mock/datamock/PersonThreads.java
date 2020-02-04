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
            /*3 Choices : 1)increment all values, 2)decrement values or lower the stepscounter, 3)generate alerts*/
            int min = 1, max=3;
            int choice = rd.nextInt(max + 1 - min) + min;
            //increment values
            if(choice==1){
                hearthrate+=2; systolic+=10; diastolic+=5; sugarLevel+=0.2; stepcounter=stepcounter+3;
            }
            //decrement values
            else if(choice==2){
                hearthrate-=2; systolic-=5; diastolic-=2; sugarLevel-=0.1; stepcounter=stepcounter+2;
            }//create alerts
            else{
                //while alert variable is false we stay in the alert generator
                boolean alert=false;
                while(!alert) {
                    int mini = 1, maxi = 2;
                    int alertchoice = rd.nextInt(maxi + 1 - mini) + mini;
                    //increment
                    if (alertchoice == 1) {
                        hearthrate += 20;
                        systolic += 20;
                        diastolic += 10;
                        sugarLevel += 0.6;
                        stepcounter = stepcounter - 0;
                    } else {//decrement
                        hearthrate -= 20;
                        systolic -= 20;
                        diastolic -= 10;
                        sugarLevel -= 0.6;
                        stepcounter = stepcounter - 0;
                    }
                    HealthHistoric h = new HealthHistoric(String.valueOf(hearthrate), String.valueOf(systolic), String.valueOf(diastolic), String.valueOf(sugarLevel), String.valueOf(stepcounter), new Timestamp(new Date().getTime()), p.getStrap().getId());
                    System.err.println(healthHistoricDAO.saveAndFlush(h));

                    //TODO isAlert
                    if (isAlert(h)) {
                        alert = true;
                    }
                }
                break;
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

    private boolean isAlert(HealthHistoric h) {
        Strap s = p.getStrap();
        boolean isAlert = false;
        //test //HIGH HEARTH RATE
        if (Integer.parseInt(h.getHearthrate())>Integer.parseInt(s.getMaxvalueref())){
            //TODO AlertDAO
            isAlert=true;
        }
        //test //LOW HEARTH RATE
        if (Integer.parseInt(h.getHearthrate())< Integer.parseInt(s.getMinvalueref())){
            isAlert=true;
        }
        //test LOW BLOOD PRESSURE
        if (Integer.parseInt(h.getSystolic())< Integer.parseInt(s.getMinsysto())){
            isAlert=true;
        }

        //test HIGH BLOOD PRESSURE
        if (Integer.parseInt(h.getSystolic())>Integer.parseInt(s.getMaxsysto()) &&
                Integer.parseInt(h.getDiastolic())>Integer.parseInt(s.getMaxdiasto())){
            isAlert=true;
        }

        //test DIABETIC
        //HYPERGLYCEMIA
        if (Integer.parseInt(h.getSugarlevel())>Integer.parseInt(s.getMaxglyc())) {
            isAlert=true;
        }
        //HYPOGLICEMIA
        if (Integer.parseInt(h.getSugarlevel())< Integer.parseInt(s.getMinglyc())) {
            isAlert=true;
        }

        return isAlert;
    }


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


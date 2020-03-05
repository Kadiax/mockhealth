package lifeprotect.mock.datamock;

import lifeprotect.mock.model.*;
import lifeprotect.mock.services.MockService;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Date;
import java.util.Random;

public class PersonThreads implements Runnable{
    private Person p;
    private Random rd;
    private  DecimalFormat df;
    private MockService service;

    private static long HEARTHRATE_INTERVALLE =1000;
    private static long BLOODPRESSURE_INTERVALLE=2000;
    private static long DIABETIC_INTERVALLE=5000;
    private static long STEPS_INTERVALLE =5000;


    public PersonThreads(Person p){
        this.p=p;
        rd = new Random();
        //arrondir
        df = new DecimalFormat() ;
        df.setMaximumFractionDigits(2);
        DecimalFormatSymbols ds = new DecimalFormatSymbols();
        ds.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(ds);

        this.service = new MockService();
    }


    @Override
    public void run() {
        System.out.println("START :"+p.toString());
        int i = 0;
        //health variables
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
            }
            //create alerts
            else{
                if(p.getDeseas()!=null){//if the resident is not sick we don't create alerts
                    //while alert variable is false we stay in the alert generator
                    boolean alert=false;
                    while(!alert) {
                        int mini = 1, maxi = 2;
                        int alertchoice = rd.nextInt(maxi + 1 - mini) + mini;
                        //increment
                        if (alertchoice == 1) {
                            hearthrate += 10;
                            systolic += 10;
                            diastolic += 10;
                            sugarLevel += 0.3;
                        } else {//decrement
                            hearthrate -= 3;
                            systolic -= 5;
                            diastolic -= 5;
                            sugarLevel -= 0.2;
                        }
                        HealthHistoric h = createHistoric(hearthrate, systolic, diastolic, sugarLevel, stepcounter);
                        //System.err.println(healthHistoricDAO.saveAndFlush(h));
                        if (isAlert(h)) {
                            alert = true;
                        }
                    }
                }
            }

            HealthHistoric h = createHistoric(hearthrate, systolic, diastolic, sugarLevel, stepcounter);

            System.out.println(service.sendMessage(h.toMessage()));

            //wait
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }


    private HealthHistoric createHistoric(double hearthrate, int systolic, int diastolic, double sugarLevel, int stepcounter) {
        return new HealthHistoric(String.valueOf(Math.abs((int)hearthrate)),
                String.valueOf(Math.abs(systolic)),
                String.valueOf(Math.abs(diastolic)),
                String.valueOf(df.format(Math.abs(sugarLevel))),
                String.valueOf(Math.abs(stepcounter)),
                new Timestamp(new Date().getTime()),
                p.getStrap().getId() );
    }

    private boolean isAlert(HealthHistoric h) {
        Strap s = p.getStrap();
        boolean isAlert = false;
        String message = "";
        int criticity = 0;

        //test //HIGH HEARTH RATE
        if (Float.parseFloat(h.getHearthrate())>Float.parseFloat(s.getMaxvalueref())){
            isAlert=true;
            criticity++;
            message+="HIGH HEARTH RATE : "+h.getHearthrate()+" bpm > "+s.getMaxvalueref()+" bpm";
        }
        //test //LOW HEARTH RATE
        if (Float.parseFloat(h.getHearthrate())< Float.parseFloat(s.getMinvalueref())){
            isAlert=true;
            criticity++;
            message+="---LOW HEARTH RATE : "+h.getHearthrate()+" bpm < "+s.getMinvalueref()+" bpm";
        }
        //test LOW BLOOD PRESSURE
        if (Integer.parseInt(h.getSystolic())< Integer.parseInt(s.getMinsysto())){
            isAlert=true;
            criticity++;
            message+="----LOW BLOOD PRESSURE : "+h.getSystolic()+"/"+h.getDiastolic()+" mmHg < "+s.getMinsysto()+"/"+s.getMaxdiasto()+" mmHg";
        }

        //test HIGH BLOOD PRESSURE
        if (Integer.parseInt(h.getSystolic())>Integer.parseInt(s.getMaxsysto()) &&
                Integer.parseInt(h.getDiastolic())>Integer.parseInt(s.getMaxdiasto())){
            isAlert=true;
            criticity++;
            message+="----HIGH BLOOD PRESSURE : "+h.getSystolic()+"/"+h.getDiastolic()+" mmHg > "+s.getMaxsysto()+"/"+s.getMaxdiasto()+" mmHg";;
        }

        //test DIABETIC
        //HYPERGLYCEMIA
        if (Float.parseFloat(h.getSugarlevel())>Float.parseFloat(s.getMaxglyc())) {
            isAlert=true;
            criticity++;
            message+="----HYPERGLYCEMIA : "+h.getSugarlevel()+" g/l > "+s.getMaxglyc()+" g/l";
        }
        //HYPOGLYCEMIA
        if (Float.parseFloat(h.getSugarlevel())< Float.parseFloat(s.getMinglyc())) {
            isAlert=true;
            criticity++;
            message+="----HYPOGLYCEMIA : "+h.getSugarlevel()+" g/l < "+s.getMinglyc()+" g/l";
        }


        if (isAlert) {
            AlertHealth a = new AlertHealth(message, new Timestamp(new Date().getTime()), String.valueOf(criticity), s.getId());
           //alertDAO.saveAndFlush(a);
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

    //private Thread HearthRate = new Thread()

}


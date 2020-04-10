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
        if (p.getDeseas() != null) {
            if(p.getDeseas().equals("HEARTPROBLEM")) {
                anormalhearthrate();
            }
        }
        else{
            normalHearthRate();
        }
    }

    private void anormalhearthrate() {
        //health variables
        Double hearthrate=100.00;
        /*3 Choices : 1)increment all values, 2)decrement values or lower the stepscounter, 3)generate alerts*/
        int min = 1, max=4;
        HealthHistoric h = null;
        while(true){

            int choice = rd.nextInt(max + 1 - min) + min;
            //increment values
            if(choice==1) {
                h = createHistoric(hearthrate += 1, null, null, null, null);
            }
            //decrement values
            if(choice==2) {
                h = createHistoric(hearthrate -= 6, null, null, null, null);
            }
            else {//Alert
                h = createHistoric(hearthrate += 4, null, null, null, null);
            }

           /* if(Integer.parseInt(h.getHearthrate())>(Integer.parseInt(p.getStrap().getMaxvalueref())+15))
                h.setHearthrate("0");//MAX*/
            if(Integer.parseInt(h.getHearthrate())<0)
                h.setHearthrate("0");

            System.out.println(p.getFirstName()+" "+p.getLastName()+": "+h.toMessage() + ": server " + service.sendMessage(h.toMessage()));

            try {
                Thread.currentThread().sleep(this.HEARTHRATE_INTERVALLE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    private Double createAlertHearth(Double hearthrate) {
        Double hearthRateMaxRef = Double.parseDouble(p.getStrap().getMaxvalueref());
        //Double res = hearthRateMaxRef - hearthrate;
        while(hearthrate<hearthRateMaxRef+10) {
            HealthHistoric h = createHistoric(hearthrate+=10, null, null, null, null);
            System.err.println(p.getFirstName() + " " + p.getLastName() + ": " + h.toMessage() + ": server " + service.sendMessage(h.toMessage()));
        }
        return hearthrate;
    }

    public void normalHearthRate(){
        //health variables
        Double hearthrate=rdHearthRate(p.getStrap());
        int i =0;
        while(true){
            /*3 Choices : 1)increment all values, 2)decrement values or lower the stepscounter, 3)generate alerts*/
            int min = 1, max=2;
            int choice = rd.nextInt(max + 1 - min) + min;
            //increment values
            if(choice==1)
                hearthrate+=1;
                //decrement values
            else
                hearthrate-=1;

            HealthHistoric h = createHistoric(hearthrate, null, null, null, null);
            try {
                System.out.println(p.getFirstName()+" "+p.getLastName()+": "+h.toMessage() + ": server " + service.sendMessage(h.toMessage()));
                i++;
                Thread.currentThread().sleep(this.HEARTHRATE_INTERVALLE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void normalTension(){
        System.out.println("START MOCK:"+p.getFirstName()+" "+p.getLastName());
        //health variables
        Double hearthrate=rdHearthRate(p.getStrap());
        Integer systolic=rdSysto(p.getStrap()), diastolic=rdDiasto(p.getStrap()) ,stepcounter=0;
        Double sugarLevel= rdSugarLevel(p.getStrap());
        int i =0;
        while(true){
            /*3 Choices : 1)increment all values, 2)decrement values or lower the stepscounter, 3)generate alerts*/
            int min = 1, max=2;
            int choice = rd.nextInt(max + 1 - min) + min;
            //increment values
            if(choice==1){
                hearthrate+=2; systolic+=10; diastolic+=5; sugarLevel+=0.2; stepcounter=stepcounter+3;
            }
            //decrement values
            else{
                hearthrate-=2; systolic-=5; diastolic-=2; sugarLevel-=0.1; stepcounter=stepcounter+2;
            }

            HealthHistoric h = createHistoric(hearthrate, null, null, null, null);
            try {
                System.out.println(h.toMessage() + ": server " + service.sendMessage(h.toMessage()));
                i++;
                Thread.currentThread().sleep(this.HEARTHRATE_INTERVALLE);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private HealthHistoric createHistoric(Double hearthrate, Integer systolic, Integer diastolic, Double sugarLevel, Integer stepcounter) {
        HealthHistoric h =  new HealthHistoric();
        if (hearthrate != null)
            h.setHearthrate(String.valueOf((int)Math.abs(hearthrate)));
        if (systolic != null)
            h.setHearthrate(String.valueOf(Math.abs(systolic)));
        if (diastolic != null)
            h.setHearthrate(String.valueOf(Math.abs(diastolic)));
        if (sugarLevel != null)
            h.setHearthrate(String.valueOf(df.format(Math.abs(sugarLevel))));
        if (stepcounter != null)
            h.setHearthrate(String.valueOf(Math.abs(stepcounter)));

        h.setStartdate(new Timestamp(new Date().getTime()));
        h.setStrap(p.getStrap().getId() );

        return h;
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
            //AlertHealth a = new AlertHealth(message, new Timestamp(new Date().getTime()), String.valueOf(criticity), s.getId());
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


package lifeprotect.mock.controller;

import lifeprotect.mock.dao.*;
import lifeprotect.mock.datamock.PersonThreads;
import lifeprotect.mock.model.*;

import java.io.IOException;
import java.util.*;

public class MockGeneratorController {
    private MockResidentDAO mockDAO;
    private List<Person> persons;
    private List<PersonThreads> personThreads;

    //HEARTH RATE
    private final static int MIN_HEARTH_RATE = 50;
    private final static int MIN_HEARTH_RATE_FOR_ATHLETE = 30;
    private final static int MIN_STEPS_NUMBER_BY_DAY = 7000;
    //LOW BLOOD PRESSURE
    private final static int MIN_SYSTOLIC = 90;
    //HIGH BLOOD PRESSURE
    private final static int MAX_SYSTOLIC = 130;
    private final static int MAX_DIASTOLIC = 85;
    //DIABETIC
    private final static double MIN_GLYC = 0.45;
    private final static double MAX_GLYC = 1.26;



    public MockGeneratorController(){
       //opening of csv file and creation of persons
        mockDAO = new MockResidentDAO();
    }

    public void  getPersonsMockFromOpenData(){
        try {
            //Creation of persons from csv file
            this.persons = mockDAO.createPersons();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Start simulation
        for (Person p : persons){
            PersonThreads pth = new PersonThreads(p);
            //personThreads.add(pth);
            pth.run();
        }
    }

    //calcul of thresholds based on persons
    public void generateStraps(){
        int i =0;

        while(persons.size()>i){
            Strap s = new Strap();

            //Creation of a strap
            s.setIpadress(generateIpAddress());

            // min threshold for heartRate is not the same for athletic people
            if (Boolean.parseBoolean(persons.get(i).getIsmobile())) {
                s.setMinvalueref(""+MIN_HEARTH_RATE);
            }else{
                s.setMinvalueref(""+MIN_HEARTH_RATE_FOR_ATHLETE);
            }

            //fill the steps number
            s.setMinsteps(""+MIN_STEPS_NUMBER_BY_DAY);

            //calcul of max threshold for heartRate (FC max = 207 – 0,7 x age)
            double fcMax = 207-0.7*ageCalculator(persons.get(i));
            s.setMaxvalueref(String.valueOf((int)fcMax));

            //BLOOD PRESSURE
            //LOW BLOOD PRESSURE: min systolic 90 mmHg
            s.setMinsysto(""+MIN_SYSTOLIC);
            //HIGH BLOOD PRESSURE : max systo 130, max diasto 85
            s.setMaxsysto(""+MAX_SYSTOLIC);
            s.setMaxdiasto(""+MAX_DIASTOLIC);

            //DIABETIC
            //hypoglycemia 0,45
            s.setMinglyc(""+MIN_GLYC);
            //hyperglycemia 1,26
            s.setMaxglyc(""+MAX_GLYC);
            s.setState(StateEnum.ON);
            s.setPerson(persons.get(i));

            i++;
        }

    }

    public int ageCalculator(Person p){
        String[] tabGetDate = p.getBirthdate().split(" ");
        String[] tabGetYMD = tabGetDate[0].split("-");
        Calendar cal = new GregorianCalendar(Integer.parseInt(tabGetYMD[0]), Integer.parseInt(tabGetYMD[1]), Integer.parseInt(tabGetYMD[2]));
        Calendar now = new GregorianCalendar();

        return now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
    }

    public String generateIpAddress(){
        Random r = new Random();
        String ip = ""+r.nextInt(255)+".";
        for (int i = 0; i<2; i++) {
            ip += r.nextInt(255) + ".";
        }
        ip += r.nextInt(255);

        return ip;
    }

}

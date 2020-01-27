package lifeprotect.mock.controller;

import lifeprotect.mock.dao.*;
import lifeprotect.mock.datamock.MockHealthData;
import lifeprotect.mock.forms.MockForm;
import lifeprotect.mock.model.IOT;
import lifeprotect.mock.model.Person;
import lifeprotect.mock.model.Residence;
import lifeprotect.mock.model.Strap;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

public class MockGeneratorController {
    private MockResidentDAO mockDAO;
    private PersonDAO personDAO;
    private ResidenceDAO residenceDAO;

    private List<Person> persons;
    private Residence residenceSaved= null;
    private List<IOT> straps= new ArrayList<>();
    private MockForm mf;
    private StrapDAO strapDAO;
    private MockHealthData mckd;
    private HealthHistoricDAO healthHistoricDAO;

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


    public MockGeneratorController(MockForm mf, PersonDAO pdao, ResidenceDAO rDAO, StrapDAO strapDAO, HealthHistoricDAO healthHistoricDAO){
       this.mf = mf;
       //openning of csv file and creation of persons
        mockDAO = new MockResidentDAO(this.mf);
        personDAO = pdao;
        residenceDAO = rDAO;
        this.strapDAO = strapDAO;
        this.healthHistoricDAO = healthHistoricDAO;
        persons= new ArrayList<Person>();
    }

    public void  getPersonsMockFromOpenData(){
        try {
            //Creation of persons from csv file
            List<Person> personsMock = mockDAO.createPersons();

         // create only one residence
            if (residenceSaved==null) {
                Residence residence = new Residence();
                residence.setAdress("71 Rue Saint-Simon, 94000 Créteil");
                residence.setCreationdate(new Timestamp(new Date().getTime()));
                residence.setEmail("lifeprotect@gmail.com");
                residence.setPhone("0889897387746");
                residenceSaved = residenceDAO.saveAndFlush(residence);
            }

        //insert persons into residence
        for (Person p : personsMock) {
            residenceSaved.addPerson(p);
            residenceSaved = residenceDAO.saveAndFlush(residenceSaved);
        }

        persons = residenceSaved.getPeople();

        //Display persons
            for (Person p : persons) {
                System.out.println(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Start simulation
        generateStraps();
        generateMockData();
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
            s.setMaxvalueref(String.valueOf(fcMax));

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

            s.setPerson(persons.get(i));

            i++;
        }

        //update persons
        residenceSaved = residenceDAO.saveAndFlush(residenceSaved);
        //update persons array
        persons = residenceSaved.getPeople();

        //Save straps into array
        for (Person p : persons) {
            IOT s = p.getStrap();
            straps.add(s);
            //System.out.println(straps);
        }
    }

    private void generateMockData() {

            mckd =new MockHealthData(residenceSaved, healthHistoricDAO, strapDAO, residenceDAO);

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

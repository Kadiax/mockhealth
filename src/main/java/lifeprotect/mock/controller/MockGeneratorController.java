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

    private List<Person> persons= new ArrayList<Person>();
    private Residence residenceSaved= null;
    private List<IOT> straps= new ArrayList<>();
    private MockForm mf;
    private StrapDAO strapDAO;
    private MockHealthData mckd;
    private HealthHistoricDAO healthHistoricDAO;

    public MockGeneratorController(MockForm mf, PersonDAO pdao, ResidenceDAO rDAO, StrapDAO strapDAO, HealthHistoricDAO healthHistoricDAO){
       this.mf = mf;
        mockDAO = new MockResidentDAO(this.mf);
        personDAO = pdao;
        residenceDAO = rDAO;
        this.strapDAO = strapDAO;
        this.healthHistoricDAO = healthHistoricDAO;
    }

    public void  getPersonsMockFromOpenData(){
        try {
            List<Person> personsMock = mockDAO.findPersons();

         // create only one residence
            if (residenceSaved==null) {
                Residence residence = new Residence();
                residence.setAdress("71 Rue Saint-Simon, 94000 Créteil");
                residence.setCreationdate(new Timestamp(new Date().getTime()));
                residence.setEmail("lifeprotect@gmail.com");
                residence.setPhone("0889897387746");
                residenceSaved = residenceDAO.saveAndFlush(residence);
            }

        //insert resident to in DB
        for (Person p : personsMock) {
            residenceSaved.addPerson(p);
            residenceSaved = residenceDAO.saveAndFlush(residenceSaved);
        }

        persons = residenceSaved.getPeople();
            for (Person p : persons) {
                System.out.println(p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Sart simulation
        generateStraps();
        generateMockData();
    }

    private void generateMockData() {

            mckd =new MockHealthData(residenceSaved, healthHistoricDAO, strapDAO, residenceDAO);

    }

    //calcule of threshold for straps based on persons
    public void generateStraps(){
        int i =0;

        while(persons.size()>i){
            Strap s = new Strap();

            //Create an IOT
            s.setStartdate(new Timestamp(new Date().getTime()));
            s.setIpadress(generateIpAddress());
            //s.setStatus("");
            //s.setState("");

            // min threshold for heartRate is not the same for athletic people
            if (Boolean.parseBoolean(persons.get(i).getIsmobile())) {
                s.setMinvalueref("30");
                s.setMinsteps("7000");
            }else{
                s.setMinvalueref("50");
            }

            //calcul of max threshold for heartRate (FC max = 207 – 0,7 x age)
            double fcmax = 207-0.7*ageCalculator(persons.get(i));
            s.setMaxvalueref(String.valueOf(fcmax));
            //System.out.println(fcmax+"------------------------");

            //BLOOD PRESSURE
            //LOW BLOOD PRESSURE: min systolic 90 mmHg
            s.setMinsysto("90");
            //HIGH BLOOD PRESSURE : max systo 130, max diasto 85
            s.setMaxsysto("130");
            s.setMaxdiasto("85");

            //DIABETIC
            //hypoglycemia 0,45
            s.setMinglyc("0.45");
            //hyperglycemia 1,26
            s.setMaxglyc("1.26");


            s.setPerson(persons.get(i));
            //System.out.println(s.toString());
            i++;
        }

        //update persons
        residenceSaved = residenceDAO.saveAndFlush(residenceSaved);
        //update persons array
        persons = residenceSaved.getPeople();

        //Display straps
        for (Person p : persons) {
            IOT s = p.getStrap();
            straps.add(s);
            //System.out.println(straps);
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

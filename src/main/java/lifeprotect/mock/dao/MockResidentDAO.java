package lifeprotect.mock.dao;

import com.opencsv.CSVReader;
import lifeprotect.mock.forms.MockForm;
import lifeprotect.mock.model.Diseas;
import lifeprotect.mock.model.Person;
import lifeprotect.mock.model.PersonStatus;

import java.io.*;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockResidentDAO {
   private final static String RESOURCES_PATH = "src/main/resources/";
    private final static String FILE_NAME = "mock.csv";
    private final static char SEPARATOR = ',';

    private MockForm mf;

    public MockResidentDAO(MockForm mf){
        this.mf = mf;
    }

    public List<Person> findPersons() throws IOException {

        Set<String> setOfPersons = new HashSet<String>();

        ClassPathResource cl = new ClassPathResource("mock.csv");
        URL url = cl.getURL();
         BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

            Stream<String> stream = br.lines();
            setOfPersons  = stream.collect(Collectors.toSet());
            //System.out.println(setOfPersons );

        //ReadData
        List<String[] > data = new ArrayList<String[] >();

        Iterator<String> it = setOfPersons.iterator();
        while (it.hasNext()) {
            String[] nextLine = it.next().split(",");
            int size = nextLine.length;

            // ligne vide
            if (size == 0) {
                continue;
            }
            String debut = nextLine[0].trim();
            if (debut.length() == 0 && size == 1) {
                continue;
            }

            // ligne de commentaire
            if (debut.startsWith("#")) {
                continue;
            }
            data.add(nextLine);
        }

        //Create data
        List<Person> persons= new ArrayList<Person>();

        DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        for (String[] oneData : data) {
            String firstName = oneData[0];
            String lastName = oneData[1];
            String birthdate = oneData[2];
            String email = oneData[3];
            String phone = oneData[4];
            String handicap = oneData[5];
            String averageincome = oneData[6];
            String login = oneData[7];
            String password = oneData[8];
            String deseas = oneData[9];
            String ismobile = oneData[10];
            //String userevaluation = oneData[11];
            //String userRole = oneData[12];
            Long userevaluation = null;
            PersonStatus userRole = PersonStatus.RESIDENT;

           Person p = new Person(firstName, lastName, birthdate, email, phone, handicap, averageincome, login, password, deseas, ismobile, userevaluation, userRole);

           //repect the number of bloodPressure
            if (mf.getBloodPressurepPb()>0){
                p.setDeseas(Diseas.BLOODPRESSURE.toString());
                mf.setBloodPressurepPb(mf.getBloodPressurepPb()-1);
            }

            //repect the number of Diabetic Problem
            if (mf.getDiabeticPb()>0){
                p.setDeseas(p.getDeseas()+"-"+Diseas.DIABETIC.toString());
                mf.setDiabeticPb(mf.getDiabeticPb()-1);
            }


            //repect the number of Inactivity Problem
            if (mf.getStepsPb()>0){
                p.setDeseas(p.getDeseas()+"-"+Diseas.INACTIVITY.toString());
                p.setIsmobile("true");
                mf.setStepsPb(mf.getStepsPb()-1);
            }

            //repect the number of Athletic (the last person of the list)
            if (mf.getNbAthletic()>0 && mf.getNbresident()>= mf.getNbAthletic()) {
                p.setIsmobile("true");
                mf.setNbAthletic(mf.getNbAthletic()-1);
            }

            persons.add(p);

           //respect the number of resident
           mf.setNbresident(mf.getNbresident()-1);
           if (mf.getNbresident()==0) break;
        }

        return persons;
    }
}

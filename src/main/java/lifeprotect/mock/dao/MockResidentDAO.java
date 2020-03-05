package lifeprotect.mock.dao;

import com.opencsv.CSVReader;
import lifeprotect.mock.model.Diseas;
import lifeprotect.mock.model.Person;
import lifeprotect.mock.model.PersonStatus;

import java.io.*;

import lifeprotect.mock.model.Strap;
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

    public MockResidentDAO(){}

    public List<Person> createPersons() throws IOException {

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
            String id = oneData[0];
            String firstName = oneData[1];
            String lastName = oneData[2];
            String minvalueref = oneData[3];
            String maxvalueref= oneData[4];
            String minsysto= oneData[5];
            String maxsysto= oneData[6];
            String maxdiasto= oneData[7];
            String minsteps= oneData[8];
            String minglyc= oneData[9];
            String maxglyc = oneData[10];

           Person p = new Person();
           p.setId(Long.parseLong(id));
           p.setFirstName(firstName);
           p.setLastName(lastName);
           Strap s = new Strap();
           s.setMinvalueref(minvalueref);
           s.setMaxvalueref(maxvalueref);
           s.setMinsysto(minsysto);
           s.setMaxsysto(maxsysto);
           s.setMaxdiasto(maxdiasto);
           s.setMinglyc(minglyc);
           s.setMaxglyc(maxglyc);
           s.setMinsteps(minsteps);
           p.setStrap(s);


           persons.add(p);
        }

        return persons;
    }
}

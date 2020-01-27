package lifeprotect.mock;

import lifeprotect.mock.controller.MockGeneratorController;
import lifeprotect.mock.dao.HealthHistoricDAO;
import lifeprotect.mock.dao.PersonDAO;
import lifeprotect.mock.dao.ResidenceDAO;
import lifeprotect.mock.dao.StrapDAO;
import lifeprotect.mock.forms.MockForm;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;


@SpringBootApplication
public class MockApplication  implements CommandLineRunner {

    private Scanner scanner;
    private MockGeneratorController mgc;
    private PersonDAO pdao;
    private ResidenceDAO residenceDAO;
    private StrapDAO strapDAO;
    private HealthHistoricDAO healthHistoricDAO;

    public MockApplication(PersonDAO pdao, ResidenceDAO rDAO, StrapDAO strapDAO, HealthHistoricDAO healthHistoricDAO){
        scanner = new Scanner(System.in);
        this.pdao = pdao;
        residenceDAO = rDAO;
        this.strapDAO = strapDAO;
        this.healthHistoricDAO = healthHistoricDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        int choice = 0;
        while (true){
            System.out.println("******************MENU*******************");
            System.out.println("-1- Start Mock");
            System.out.println("-2- delete mock data");
            System.out.println("-3- exit");

            try{
                choice = Integer.parseInt(scanner.nextLine());
            }catch (Exception ex){System.out.println("Invalid number.");}

            switch (choice) {
                case 1:
                    fillMockForm();
                    break;
                case 2:
                    dropData();
                    System.exit(0);
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                System.out.println("Invalid choice");
                    break;
            }
        }
    }

    //Form to mock resident
    private void fillMockForm() {
        MockForm mf = new MockForm();

        System.out.println("-------------------Mock------------------");
        try {
            //Resident number
            System.out.println("How many resident ? (max 150)");
            int nbr = Integer.parseInt(scanner.nextLine());

            if(nbr>150){
                System.err.println("Error number of resident must be lower than 150.");
                throw new Exception();
            }else{
                mf.setNbresident(nbr);
            }

            //bloodPressure Number
            System.out.println("How many residents with blood pressure problem ?");
            nbr = Integer.parseInt(scanner.nextLine());

            if(nbr>mf.getNbresident()){
                System.err.println("Error number of resident with diseas must be lower than the number of resident.");
                throw new Exception();
            }else {
                mf.setBloodPressurepPb(nbr);
            }

            //Diabetic number
            System.out.println("How many residents with diabetic problem ?");
            nbr = Integer.parseInt(scanner.nextLine());

            if(nbr>mf.getNbresident()){
                System.err.println("Error number of resident with diseas must be lower than the number of resident.");
                throw new Exception();
            }else{
                mf.setDiabeticPb(nbr);
            }


            //Inactivity problem Number
            System.out.println("How many residents with inactivity problem ?");
            nbr = Integer.parseInt(scanner.nextLine());

            if(nbr>mf.getNbresident()){
                System.err.println("Error number of resident with diseas must be lower than the number of resident.");
                throw new Exception();
            }else{
                mf.setStepsPb(nbr);
            }

            //Athletic Number
            System.out.println("How many residents athletic ?");
            nbr = Integer.parseInt(scanner.nextLine());

            if(nbr>mf.getNbresident()){
                System.err.println("Error number of athletic must be lower than resident number.");
                throw new Exception();
            }else{
                mf.setNbAthletic(nbr);
            }

        }catch (Exception e){System.out.println("Invalid character: "+e.getMessage());}

        //generate resident mock
        mgc = new MockGeneratorController(mf, pdao, residenceDAO, strapDAO, healthHistoricDAO);
        mgc.getPersonsMockFromOpenData();
    }

    private void dropData() {
        if (mgc==null)
            System.out.println("no data on database");
        else
            mgc.dropMockData();
    }


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MockApplication.class);
        // disable spring banner
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}

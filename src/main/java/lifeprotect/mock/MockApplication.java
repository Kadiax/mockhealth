package lifeprotect.mock;

import lifeprotect.mock.controller.MockGeneratorController;
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
    StrapDAO strapDAO;

    public MockApplication(PersonDAO pdao, ResidenceDAO rDAO, StrapDAO strapDAO){
        scanner = new Scanner(System.in);
        this.pdao = pdao;
        residenceDAO = rDAO;
        this.strapDAO = strapDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        int choice = 0;
        while (true){
            System.out.println("******************MENU*******************");
            System.out.println("-1- Start Mock");
            System.out.println("-2- exit");

            try{
                choice = Integer.parseInt(scanner.nextLine());
            }catch (Exception ex){System.out.println("Invalid number.");}

            switch (choice) {
                case 1:
                    mockform();
                    break;
                case 2:
                    scanner.close();
                    dropdata();
                    System.exit(0);
                    break;
                default:
                System.out.println("Invalid choice");
                    break;
            }
        }
    }

    //Form to mock resident
    private void mockform() {
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
        mgc = new MockGeneratorController(mf, pdao, residenceDAO, strapDAO);
        mgc.getPersonsMockFromOpenData();
    }

    private void dropdata() {
    }


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MockApplication.class);
        // disable spring banner
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}

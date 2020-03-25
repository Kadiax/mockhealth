package lifeprotect.mock;

import lifeprotect.mock.controller.MockGeneratorController;
import lifeprotect.mock.dao.PersonDAO;
import lifeprotect.mock.services.MockService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class MockApplication  implements CommandLineRunner {

    private MockGeneratorController mgc;
    private PersonDAO pdao;

    public MockApplication(PersonDAO pdao){
       this.pdao= pdao;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("-------------------Mock------------------");

        //generate resident mock
        mgc = new MockGeneratorController(pdao);
        mgc.getPersonsMockFromOpenData();


    }



    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MockApplication.class);
        // disable spring banner
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }
}

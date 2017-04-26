package database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /** 
     * @Title: run  
     * @param args
     * @throws Exception   
     * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])   
     */
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

    }
}

package gov.ssa.functionalfitness;

import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import gov.ssa.functionalfitness.api.ConnectToWgerAPI;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class })
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
//		ArrayList<String> test= new ArrayList<>();
//		Scanner sc=new Scanner(System.in);
//		String userinput=sc.nextLine();
//		test.add(ConnectToWgerAPI.connectingToWgerAPI(userinput).toString());
//		
//		System.out.println(test);
//		
		
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}

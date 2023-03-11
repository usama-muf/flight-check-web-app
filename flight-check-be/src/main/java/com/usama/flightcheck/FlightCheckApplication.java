package com.usama.flightcheck;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.usama.flightcheck.entity.Availability;
import com.usama.flightcheck.entity.FlightDetails;
import com.usama.flightcheck.repository.FlightDetailsRepo;

@SpringBootApplication
public class FlightCheckApplication implements CommandLineRunner{
	

	@Autowired
	private FlightDetailsRepo flightDetailsRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(FlightCheckApplication.class, args);
	} 
	
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	@Bean
	public ModelMapper ModelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public AuthenticationManager AuthenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	} 
	
	@Configuration
	@EnableWebMvc
	public class CorsConfiguration implements WebMvcConfigurer {
	  @Override
	  public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/api/**")
	            .allowedOrigins("http://localhost:3000")
//	            .allowedMethods("GET", "POST", "PUT", "DELETE")
	            .allowedMethods("*")
	            .allowedHeaders("*")
	            .allowCredentials(true)
	            .exposedHeaders("Authorization");
	  }
	}

//	@Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//            	registry.addMapping("/api/**")
////                .allowedOrigins("*")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .exposedHeaders("Authorization")
//                .allowCredentials(true)
//                .maxAge(3600);
//            }
//        };
//    }
	
	
	@Override
	public void run(String... args) throws Exception {

//		Availability a1 = new Availability(null, LocalDate.of(2022, 12, 12), 10, 20042);
//		Availability a2 = new Availability(null, LocalDate.of(2022, 1, 11), 4, 10042);
//		Availability a3 = new Availability(null, LocalDate.of(2022, 11, 2), 20, 11200);
//		
//		List<Availability> al = new ArrayList<Availability>(Arrays.asList(a1, a2, a3));
//		FlightDetails fd = new FlightDetails(null, "AI0292", "LKO", LocalTime.of(5 , 12), "BOM", LocalTime.of(13, 15),"AirIndia" , al);
//		flightDetailsRepo.save(fd);

		
	}
}

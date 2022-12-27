package com.sigecap.sigecapexamenbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class SigecapexamenbackendApplication {

	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("America/Lima"));   // It will set UTC timezone
		System.out.println("Spring boot application running in UTC timezone :"+new Date());   // It will print UTC timezone
		System.out.println("Spring boot application LOCALDATIME running in UTC timezone :"+ LocalDateTime.now());   // It will print UTC timezone

	}
	public static void main(String[] args) {
		SpringApplication.run(SigecapexamenbackendApplication.class, args);
	}

}

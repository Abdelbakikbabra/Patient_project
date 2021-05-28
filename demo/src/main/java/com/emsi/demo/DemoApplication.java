package com.emsi.demo;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
 
import com.emsi.demo.entities.Patient;
import com.emsi.demo.repositories.PatientRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	@Autowired
	private PatientRepository patientRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		patientRepository.save(new Patient(null,"abdo",new Date(),false,10));
		patientRepository.save(new Patient(null,"abdelbaki",new Date(),true,9));
		patientRepository.save(new Patient(null,"kbabra",new Date(),true,20));
		patientRepository.save(new Patient(null,"abdel",new Date(),false,90));
		patientRepository.save(new Patient(null,"kbabaabdelbaki",new Date(),true,71));
		patientRepository.findAll().forEach(p->{
			System.out.println(p.getNom());
		});;
	}

}

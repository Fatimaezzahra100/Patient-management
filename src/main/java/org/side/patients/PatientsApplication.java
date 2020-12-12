package org.side.patients;

import java.util.Date;

import org.side.patients.dao.PatientRepository;
import org.side.patients.entites.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientsApplication implements CommandLineRunner{
	
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		 SpringApplication.run(PatientsApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient("Ali", new Date(), true));
		patientRepository.save(new Patient("Karim", new Date(), false));
		patientRepository.save(new Patient("Laila", new Date(), false));
		patientRepository.save(new Patient("Titouan", new Date(), true));
		patientRepository.save(new Patient("Fatima", new Date(), true));
		patientRepository.save(new Patient("Mona", new Date(), false));
		patientRepository.save(new Patient("Mouhssin", new Date(), false));
		patientRepository.save(new Patient("Tito", new Date(), true));
		
	}

}

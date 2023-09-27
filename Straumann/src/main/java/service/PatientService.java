package service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import exception.PatientNotFoundException;
import model.Patient;
import repository.PatientRepository;

@Service
public class PatientService {
	
	@Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.cache.type}")
    private String cacheType;
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Cacheable("patients")
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));
    }
    
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient updatedPatient) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        existingPatient.setName(updatedPatient.getName());
        existingPatient.setAge(updatedPatient.getAge());

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException(id));

        patientRepository.delete(existingPatient);
    }
}

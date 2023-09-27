import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import model.Patient;
import repository.PatientRepository;
import service.PatientService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {
    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void testGetPatientById() {
      
    	Long patientId = 1L;
        Patient mockPatient = new Patient();
        mockPatient.setId(patientId);
        
        // Mock the findById method to return an Optional of mockPatient
        when(patientRepository.findById(patientId)).thenReturn(Optional.of(mockPatient));

        // Act
        Patient patient = patientService.getPatientById(patientId);

        // Assert
        assertEquals(patientId, patient.getId());
    }

    @Test
    public void testCreatePatient() {
        // Arrange
        Patient newPatient = new Patient();
        newPatient.setName("John");
        newPatient.setAge(30);

        // Act
        Patient createdPatient = patientService.createPatient(newPatient);

        // Assert
        assertEquals("John", createdPatient.getName());
        assertEquals(30, createdPatient.getAge());
    }
}

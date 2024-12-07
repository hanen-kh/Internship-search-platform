package where.ServicesTest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import where.Entities.Candidate;
import where.Entities.Role;
import where.Entities.TypeRole;
import where.Repositories.CandidateRepository;
import where.Repositories.RoleRepository;
import where.Services.CandidateService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CandidateServerTest {
    @InjectMocks
    private CandidateService candidateService;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CandidateRepository candidateRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

   @Test
    void testSaveCandidate() {
       Candidate candidate = new Candidate();
       candidate.setFirstname("John");
       candidate.setLastname("Doe");
       candidate.setEmail("john.doe@example.com");
       candidate.setPassword("securePassword");

       Role role = new Role();
       role.setLibelle(TypeRole.CANDIDATE);
       roleRepository.save(role);

       // Mocks
       Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");
       Mockito.when(roleRepository.findByLibelle(TypeRole.CANDIDATE)).thenReturn(Optional.of(role));
       Mockito.when(candidateRepository.save(Mockito.any(Candidate.class))).thenReturn(candidate);

       // Act
       Candidate result = candidateService.saveCandidate(candidate);

       // Assert
       assertNotNull(result);
       assertEquals("encodedPassword", result.getPassword());
       assertEquals(TypeRole.CANDIDATE, result.getRole().getLibelle());

       verify(candidateRepository, times(1)).save(Mockito.any(Candidate.class));
   }

    @Test
    void updateCandidate_ShouldUpdateSuccessfully_WhenCandidateExists() {
        // Arrange
        Long candidateId = 1L;

        Candidate existingCandidate = new Candidate();
        existingCandidate.setId(candidateId);
        existingCandidate.setFirstname("John");
        existingCandidate.setLastname("Doe");
        existingCandidate.setEmail("john.doe@example.com");

        Candidate updatedDetails = new Candidate();
        updatedDetails.setFirstname("Jane");
        updatedDetails.setLastname("Smith");
        updatedDetails.setEmail("jane.smith@example.com");
        updatedDetails.setPassword("newPassword");
        updatedDetails.setDateOfBirth(java.sql.Date.valueOf("1995-05-10"));
        updatedDetails.setUniversity("University of Example");

        // Mock the repository behavior
        Mockito.when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(existingCandidate));
        Mockito.when(candidateRepository.save(existingCandidate)).thenReturn(existingCandidate);

        // Act
        Candidate result = candidateService.updateCandidate(candidateId, updatedDetails);

        // Assert
        assertNotNull(result);
        assertEquals("Jane", result.getFirstname());
        assertEquals("Smith", result.getLastname());
        assertEquals("jane.smith@example.com", result.getEmail());
        assertEquals("newPassword", result.getPassword());
        assertEquals("1995-05-10", result.getDateOfBirth().toString());
        assertEquals("University of Example", result.getUniversity());

        verify(candidateRepository, times(1)).findById(candidateId);
        verify(candidateRepository, times(1)).save(existingCandidate);
    }

    @Test
    void updateCandidate_ShouldThrowException_WhenCandidateDoesNotExist() {
        // Arrange
        Long candidateId = 99L;
        Candidate updatedDetails = new Candidate();

        // Mock the repository behavior
        Mockito.when(candidateRepository.findById(candidateId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            candidateService.updateCandidate(candidateId, updatedDetails);
        });

        assertEquals("Candidate not found with id 99", exception.getMessage());
        verify(candidateRepository, times(1)).findById(candidateId);
        verify(candidateRepository, times(0)).save(any(Candidate.class));
    }

}

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
import where.Entities.Validation;
import where.Repositories.CandidateRepository;
import where.Repositories.RoleRepository;
import where.Repositories.ValidationRepository;
import where.Services.CandidateService;
import where.Services.EmailService;
import where.Services.UserService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CandidateServerTest {
    @InjectMocks
    private CandidateService candidateService;
    @Mock
    ValidationRepository validationRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;
    @Mock
    private CandidateRepository candidateRepository;
    @Mock
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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

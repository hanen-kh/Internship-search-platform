package where.ServicesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import where.Entities.Company;
import where.Entities.Recruiter;
import where.Entities.Role;
import where.Entities.TypeRole;
import where.Repositories.CompanyRepository;
import where.Repositories.RecruiterRepository;
import where.Repositories.RoleRepository;
import where.Services.RecruiterService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RecruiterServiceTest {
    @InjectMocks
    private RecruiterService recruiterService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    RecruiterRepository recruiterRepository;
    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createRecruiter_ShouldCreateRecruiter_WhenValidDataProvided() {
        // Arrange
        Recruiter recruiter = new Recruiter();
        recruiter.setFirstname("John");
        recruiter.setLastname("Doe");
        recruiter.setEmail("john.doe@example.com");
        recruiter.setPassword("password123");

        Company company = new Company();
        company.setName("TechCorp");

        Company savedCompany = new Company();
        savedCompany.setId(1L);
        savedCompany.setName("TechCorp");

        Role recruiterRole = new Role();
        recruiterRole.setId(1L);
        recruiterRole.setLibelle(TypeRole.RECRUITER);

        // Mock les comportements des repositories
        when(companyRepository.findByName("TechCorp")).thenReturn(Optional.empty());
        when(companyRepository.save(any(Company.class))).thenReturn(savedCompany);
        when(roleRepository.findByLibelle(TypeRole.RECRUITER)).thenReturn(Optional.of(recruiterRole));
        when(recruiterRepository.save(any(Recruiter.class))).thenReturn(recruiter);

        // Act
        Recruiter result = recruiterService.createRecruiter(recruiter, company);

        // Assert
        assertNotNull(result);
        assertEquals(savedCompany, result.getCompany());
        assertEquals(recruiterRole, result.getRole());
        assertNotEquals("password123", result.getPassword()); // Le mot de passe doit être encodé

        verify(companyRepository, times(1)).findByName("TechCorp");
        verify(companyRepository, times(1)).save(any(Company.class));
        verify(roleRepository, times(1)).findByLibelle(TypeRole.RECRUITER);
        verify(recruiterRepository, times(1)).save(any(Recruiter.class));
    }

    @Test
    void createRecruiter_ShouldAssignExistingCompany_WhenCompanyAlreadyExists() {
        // Arrange
        Recruiter recruiter = new Recruiter();
        recruiter.setFirstname("Jane");
        recruiter.setLastname("Smith");
        recruiter.setEmail("jane.smith@example.com");
        recruiter.setPassword("securePassword");

        Company existingCompany = new Company();
        existingCompany.setId(1L);
        existingCompany.setName("ExistingCorp");

        Role recruiterRole = new Role();
        recruiterRole.setId(2L);
        recruiterRole.setLibelle(TypeRole.RECRUITER);

        // Mock les comportements des repositories
        when(companyRepository.findByName("ExistingCorp")).thenReturn(Optional.of(existingCompany));
        when(roleRepository.findByLibelle(TypeRole.RECRUITER)).thenReturn(Optional.of(recruiterRole));
        when(recruiterRepository.save(any(Recruiter.class))).thenReturn(recruiter);

        // Act
        recruiterService.createRecruiter(recruiter, existingCompany);

        // Assert
        assertEquals(existingCompany, recruiter.getCompany());
        verify(companyRepository, times(1)).findByName("ExistingCorp");
        verify(companyRepository, times(0)).save(any(Company.class));
        verify(recruiterRepository, times(1)).save(any(Recruiter.class));
    }

    @Test
    void createRecruiter_ShouldThrowException_WhenRoleNotFound() {
        // Arrange
        Recruiter recruiter = new Recruiter();
        recruiter.setFirstname("Mark");
        recruiter.setLastname("Brown");
        recruiter.setEmail("mark.brown@example.com");
        recruiter.setPassword("anotherPassword");

        Company company = new Company();
        company.setName("NoRoleCorp");

        // Mock les comportements des repositories
        when(companyRepository.findByName("NoRoleCorp")).thenReturn(Optional.empty());
        when(roleRepository.findByLibelle(TypeRole.RECRUITER)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            recruiterService.createRecruiter(recruiter, company);
        });

        assertEquals("Role RECRUITER doesn't exist", exception.getMessage());
        verify(roleRepository, times(1)).findByLibelle(TypeRole.RECRUITER);
        verify(recruiterRepository, times(0)).save(any(Recruiter.class));
    }
}

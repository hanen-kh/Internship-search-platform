package where.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import where.Entities.Company;
import where.Entities.Recruiter;
import where.Services.RecruiterService;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/recruiter")
public class RecruiterController {
    @Autowired
    private RecruiterService recruiterService;

    @PostMapping("/register")
    public ResponseEntity<String> registerRecruiter(
            @RequestBody Recruiter recruiter,
            @RequestBody Company company) {

        recruiterService.createRecruiter(recruiter, company);
        return ResponseEntity.status(HttpStatus.CREATED).body("Recruiter registered successfully!");
    }
}

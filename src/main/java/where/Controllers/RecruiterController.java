package where.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import where.Entities.Candidate;
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

    @PostMapping("/register-temporary")
    public ResponseEntity<String> registerTemporary(@RequestBody Recruiter recruiter, @RequestBody Company company) {
        try {
            String response = recruiterService.saveRecruiterTemporary(recruiter, company);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateAndRegister(@RequestParam String email,
                                                      @RequestParam String code,
                                                      @RequestParam String password) {
        try {
            String response = recruiterService.validateRecruiter(email, code, password);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

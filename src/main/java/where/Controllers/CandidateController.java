package where.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import where.Entities.Candidate;
import where.Services.CandidateService;
@AllArgsConstructor
@NoArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/candidate")
public class CandidateController {
    @Autowired
    CandidateService candidateService;




    @PostMapping("/register")
    public ResponseEntity<Candidate> registerCandidate(@RequestBody Candidate candidate) {
        Candidate savedCandidate = candidateService.saveCandidate(candidate);
        return ResponseEntity.ok(savedCandidate);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidateDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

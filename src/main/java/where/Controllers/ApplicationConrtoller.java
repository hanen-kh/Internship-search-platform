package where.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import where.Entities.Application;
import where.Services.ApplicationService;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/application")
public class ApplicationConrtoller {
    @Autowired
    ApplicationService applicationService;

    @PostMapping("/offer/{offerId}/candidate/{candidateId}")
    public ResponseEntity<String> submitApplication(
            @PathVariable Long offerId,
            @PathVariable Long candidateId,
            @ModelAttribute Application application,
            @RequestParam("file") MultipartFile cvFile) {
        try {
            String result = applicationService.saveApplication(offerId, candidateId, application, cvFile);
            return ResponseEntity.ok(result);
        } catch (RuntimeException | IOException e) {
            return ResponseEntity.badRequest().body("Erreur : " + e.getMessage());
        }
    }

}

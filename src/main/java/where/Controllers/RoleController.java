package where.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import where.Entities.Role;
import where.Entities.TypeRole;
import where.Repositories.RoleRepository;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody Role role) {
        TypeRole typeRole = role.getLibelle();
        Optional<Role> existingRole = roleRepository.findByLibelle(typeRole);

        // Check if the role already exists
        if (existingRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Role with libelle " + typeRole + " already exists.");
        }

        // Save the new role
        Role savedRole = roleRepository.save(role);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
    }

}

package mintic.ciclo4a.security.controllers;

import lombok.AllArgsConstructor;
import mintic.ciclo4a.security.models.Role;
import mintic.ciclo4a.security.repositories.RoleRepository;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleRepository roleRepository;

    @PostMapping("")
    Role createrole(@RequestBody Role role) {
        return roleRepository.save(role);
    }

}

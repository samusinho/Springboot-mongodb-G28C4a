package mintic.ciclo4a.security.controllers;

import lombok.AllArgsConstructor;
import mintic.ciclo4a.security.models.User;
import mintic.ciclo4a.security.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    // PostMapping -> Método POST
    // "" -> "/users"
    @PostMapping("")
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // GetMapping -> Método GET
    // "" -> "/users"
    @GetMapping("")
    List<User> getUsers(@RequestParam("nick") Optional<String> nick) {
        if (nick.isPresent()) {
            return userRepository.findByRegexpNick(nick.get());
        }
        return userRepository.findAll();
    }
}

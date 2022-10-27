package mintic.ciclo4a.security.controllers;

import lombok.AllArgsConstructor;
import mintic.ciclo4a.security.models.User;
import mintic.ciclo4a.security.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    ResponseEntity<List<User>> getUsers(@RequestParam("nick") Optional<String> nick) {
        if (nick.isPresent()) {
            return ResponseEntity.status(200).body(userRepository.findByRegexpNick(nick.get()));
            // return userRepository.findByRegexpNick(nick.get());
        }

        return ResponseEntity.status(200).body(userRepository.findAll());
    }

    // @PathVariable("userId") -> /users/{userId}
    @GetMapping("{userId}")
    User getUserById(@PathVariable("userId") String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe"));
    }

    @DeleteMapping("{userId}")
    ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe"));
        userRepository.delete(user);
        return ResponseEntity.ok().body("El usuario " + user.getNick() + " Fue eliminado");
    }

    @PutMapping("{userId}")
    User updateUser(@PathVariable String userId, @RequestBody User userinfo) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe"));
        user.setNick(userinfo.getNick());
        user.setEmail(userinfo.getEmail());
        return userRepository.save(user);
    }
}

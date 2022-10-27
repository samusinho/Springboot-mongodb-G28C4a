package mintic.ciclo4a.security.repositories;

import mintic.ciclo4a.security.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository
        extends MongoRepository<User, String> {
    Optional<User> findUserByEmail(String email);

    @Query(value = "{'nick': {'$regex': ?0 }}", fields = "{ 'password': 0 }")
    List<User> findByRegexpNick(String nick);
}

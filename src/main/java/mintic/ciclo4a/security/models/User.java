package mintic.ciclo4a.security.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {
    @Id
    private String id;
    private String nick;
    @Indexed(unique = true)
    private String email;
    private String password;

    public User(
            String nick,
            String email,
            String password
    ) {
        this.nick = nick;
        this.email = email;
        this.password = password;
    }
}

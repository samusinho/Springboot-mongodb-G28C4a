package mintic.ciclo4a.security.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PermisssionRole {
    @Id
    private String id;
    @DBRef
    private Role role;
    @DBRef
    private Permission permission;

}

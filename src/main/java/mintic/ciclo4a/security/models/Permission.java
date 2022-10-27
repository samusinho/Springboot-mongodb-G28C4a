package mintic.ciclo4a.security.models;

import org.springframework.data.annotation.Id;

public class Permission {
    @Id
    private String id;
    private String url; // url -> "/users"
    private String method; // method -> "post"

    public Permission(String url, String method) {
        this.url = url;
        this.method = method;
    }
}

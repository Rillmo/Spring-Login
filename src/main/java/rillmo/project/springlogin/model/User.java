package rillmo.project.springlogin.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;

    public User update(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        return this;
    }
}

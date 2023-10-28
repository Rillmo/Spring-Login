package rillmo.project.springlogin.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class User {
    @Id @UuidGenerator
    @Column(name = "USER_ID")
    private String id;
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

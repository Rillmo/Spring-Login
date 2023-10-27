package rillmo.project.springlogin.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import rillmo.project.springlogin.model.User;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreateUserDTO {
    private String name;
    private String email;
    private String password;

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .build();
    }
}

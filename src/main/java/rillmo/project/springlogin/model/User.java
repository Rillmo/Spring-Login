package rillmo.project.springlogin.model;


import com.google.type.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class User {
    private String id;
    private String name;

    private Date create_dt;
    private Date update_dt;
}

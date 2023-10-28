package rillmo.project.springlogin.dto.account;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import rillmo.project.springlogin.model.Account;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CreateAccountDTO {
    private String nickname;

    public Account toEntity() {
        return Account.builder()
                .nickname(nickname)
                .build();
    }
}

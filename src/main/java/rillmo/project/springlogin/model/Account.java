package rillmo.project.springlogin.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
public class Account {
    @Id @UuidGenerator
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
    private String nickname;

    public Account setUser(User user) {
        this.user = user;
        return this;
    }

    public Account update(String nickname) {
        this.nickname = nickname;
        return this;
    }
}

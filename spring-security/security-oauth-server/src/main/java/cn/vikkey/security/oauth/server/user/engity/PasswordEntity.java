package cn.vikkey.security.oauth.server.user.engity;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "oauth_server_password")
public class PasswordEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity account;

    private String value;

    private ZonedDateTime createTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

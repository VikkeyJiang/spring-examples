package cn.vikkey.security.oauth.server.user.engity;

import org.springframework.security.core.userdetails.UserDetailsPasswordService;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "oauth_server_account")
public class UserEntity {

    @Id
    private String id;
    private String name;
    private boolean expired;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private List<PasswordEntity> passwordEntities;

    private String locked;

    private ZonedDateTime lastLoginTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

package cn.vikkey.data.jpa.examples.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.ZonedDateTime;
import java.util.List;

@Entity(name = "data_jpa_account")
public class AccountEntity {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "account")
    private List<PasswordEntity> passwordEntities;
    private boolean expired;

    private String locked;

    private ZonedDateTime lastLoginTime;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PasswordEntity> getPasswords() {
        return passwordEntities;
    }

    public void setPasswords(List<PasswordEntity> passwordEntities) {
        this.passwordEntities = passwordEntities;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public ZonedDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(ZonedDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}

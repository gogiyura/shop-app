package com.example.model.bean;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Account implements Serializable {

    private Long id;
    private String login;
    private String password;
    private Role role;
    private Boolean blocked;
    private Timestamp createDate;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean getBlocked() {return blocked;}
    public void setBlocked(boolean blocked) {this.blocked = blocked;}
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}
    public Date getCreateDate() {return createDate;}
    public void setCreateDate(Timestamp createDate) {this.createDate = createDate;}
    public void switchIt() {blocked = !blocked;}

    public Account() {}
    public Account(long id, String login, String password, boolean blocked, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.blocked = blocked;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", blocked=" + blocked +
                ", role=" + role +
                '}';
    }
}

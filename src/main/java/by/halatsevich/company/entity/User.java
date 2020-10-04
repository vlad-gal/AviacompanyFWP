package by.halatsevich.company.entity;

import java.util.Arrays;

public class User extends Entity {
    private String email;
    private String login;
    private Role role;
    private Status status;
    private UserData userData;

    public enum Role {
        ADMIN, OPERATOR, DISPATCHER, PILOT, RADIOMAN, NAVIGATOR, STEWARDESS, DEFAULT
    }

    public enum Status {
        ACTIVE, INACTIVE
    }

    public User() {
    }

    public User(String email, String login, Role role, Status status, UserData userData) {
        this.email = email;
        this.login = login;
        this.role = role;
        this.status = status;
        this.userData = userData;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (!email.equals(user.email)) return false;
        if (!login.equals(user.login)) return false;
        if (role != user.role) return false;
        if (status != user.status) return false;
        return userData != null ? userData.equals(user.userData) : user.userData == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (userData != null ? userData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", role=").append(role);
        sb.append(", status=").append(status);
        sb.append(", userData=").append(userData);
        sb.append('}');
        return sb.toString();
    }
}

package by.halatsevich.company.entity;

public class AuthorizationData{
    private String login;
    private String password;

    public AuthorizationData() {
    }

    public AuthorizationData(String login, String password) {
        this.login = login;
        this.password = password;
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
}

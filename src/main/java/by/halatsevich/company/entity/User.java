package by.halatsevich.company.entity;

/**
 * The class represents user entity.
 *
 * @author Vladislav Halatsevich
 * @version 1.0
 */
public class User extends Entity {
    private String email;
    private String login;
    private String firstName;
    private String lastName;
    private long telephoneNumber;
    private Role role;
    private Status status;

    /**
     * The enum Role.
     */
    public enum Role {
        /**
         * Admin role.
         */
        ADMIN("Admin"),
        /**
         * Operator role.
         */
        OPERATOR("Operator"),
        /**
         * Dispatcher role.
         */
        DISPATCHER("Dispatcher"),
        /**
         * Pilot role.
         */
        PILOT("Pilot"),
        /**
         * Radioman role.
         */
        RADIOMAN("Radioman"),
        /**
         * Navigator role.
         */
        NAVIGATOR("Navigator"),
        /**
         * Stewardess role.
         */
        STEWARDESS("Stewardess");

        private String roleName;

        Role(String roleName) {
            this.roleName = roleName;
        }

        /**
         * Gets role name.
         *
         * @return the role name
         */
        public String getRoleName() {
            return roleName;
        }
    }

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param email           the email
     * @param login           the login
     * @param firstName       the first name
     * @param lastName        the last name
     * @param telephoneNumber the telephone number
     * @param role            the role
     * @param status          the status
     */
    public User(String email, String login, String firstName, String lastName, long telephoneNumber,
                Role role, Status status) {
        this.email = email;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.role = role;
        this.status = status;
    }

    /**
     * Instantiates a new User.
     *
     * @param id              the id
     * @param email           the email
     * @param login           the login
     * @param firstName       the first name
     * @param lastName        the last name
     * @param telephoneNumber the telephone number
     * @param role            the role
     * @param status          the status
     */
    public User(int id, String email, String login, String firstName, String lastName, long telephoneNumber,
                Role role, Status status) {
        super(id);
        this.email = email;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.role = role;
        this.status = status;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets telephone number.
     *
     * @return the telephone number
     */
    public long getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets telephone number.
     *
     * @param telephoneNumber the telephone number
     */
    public void setTelephoneNumber(long telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (telephoneNumber != user.telephoneNumber) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (role != user.role) return false;
        return status == user.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (int) (telephoneNumber ^ (telephoneNumber >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("email='").append(email).append('\'');
        sb.append(", login='").append(login).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", telephoneNumber=").append(telephoneNumber);
        sb.append(", role=").append(role);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}

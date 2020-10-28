//package by.halatsevich.company.model.entity;
//
//public class UserData extends Entity {
//    private String firstName;
//    private String lastName;
//    private long telephoneNumber;
//
//    public UserData() {
//    }
//
//    public UserData(int id, String firstName, String lastName, long telephoneNumber) {
//        super(id);
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.telephoneNumber = telephoneNumber;
//    }
//
//    public UserData(String firstName, String lastName, long telephoneNumber) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.telephoneNumber = telephoneNumber;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public long getTelephoneNumber() {
//        return telephoneNumber;
//    }
//
//    public void setTelephoneNumber(long telephoneNumber) {
//        this.telephoneNumber = telephoneNumber;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//
//        UserData userData = (UserData) o;
//
//        if (telephoneNumber != userData.telephoneNumber) return false;
//        if (!firstName.equals(userData.firstName)) return false;
//        return lastName.equals(userData.lastName);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = super.hashCode();
//        result = 31 * result + firstName.hashCode();
//        result = 31 * result + lastName.hashCode();
//        result = 31 * result + (int) (telephoneNumber ^ (telephoneNumber >>> 32));
//        return result;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("UserData{");
//        sb.append("firstName='").append(firstName).append('\'');
//        sb.append(", lastName='").append(lastName).append('\'');
//        sb.append(", telephoneNumber=").append(telephoneNumber);
//        sb.append('}');
//        return sb.toString();
//    }
//}

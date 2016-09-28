package info.androidhive.recyclerview;

public class PersonsName {

    private String firstname, lastname;

    public PersonsName() {
    }

    public PersonsName(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String fname) {
        this.firstname = fname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lname) {
        this.lastname = lname;
    }
}

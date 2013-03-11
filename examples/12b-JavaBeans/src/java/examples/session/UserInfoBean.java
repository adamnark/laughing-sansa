package examples.session;

/**
 *
 * @author blecherl
 */
public class UserInfoBean {

    private String firstName;
    private String lastName;

    public UserInfoBean() {
        firstName = lastName = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String string) {
        firstName = string;
    }

    public void setLastName(String string) {
        lastName = string;
    }
}
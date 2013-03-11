package chat.logic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author blecherl
 */
public class UserManager {
    private Set<String> usersSet;
    private int version;

    public UserManager() {
        usersSet = new HashSet<String>();
        version = 0;
    }

    public void addUser (String username) {
        usersSet.add(username);
        updateVersion();
    }

    public void removeUser (String username) {
        usersSet.remove(username);
        updateVersion();
    }

    public Set<String> getUsers() {
        return Collections.unmodifiableSet(usersSet);
    }

    public boolean isUserExists (String username) {
        return usersSet.contains(username);
    }

    public int getVersion() {
        return version;
    }

    private void updateVersion() {
        ++version;
    }
}
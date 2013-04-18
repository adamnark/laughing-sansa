package example.engine.model;

import java.util.Objects;

public class Player {

    private String name;

    public Player(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 47 * hash + Objects.hashCode(this.name);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Player other = (Player) obj;
	if (!Objects.equals(this.name, other.name)) {
	    return false;
	}
	return true;
    }
}

package engine.cards;

import java.util.Objects;

/**
 *
 * @author adamnark
 */
public final class Series {

    private String name;

    @Override
    public String toString() {
        return this.name;
    }

    public Series() {
    }

    public Series(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean isEqual;
        final Series other = (Series) obj;
        if (obj == null) {
            isEqual = false;
        } else if (getClass() != obj.getClass()) {
            isEqual = false;
        } else if (!Objects.equals(this.name, other.name)) {
            isEqual = false;
        } else {
            isEqual = true;
        }
        return isEqual;
    }
}

package examples.object.equals;

/**
 *
 * @author blecherl
 */
public class Robot {
    private int serialNumber;

    public Robot(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Override
    public String toString() {
        return "Robot{serial=" + this.serialNumber + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Robot other = (Robot) obj;
        if (this.serialNumber != other.serialNumber) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.serialNumber;
        return hash;
    }
}

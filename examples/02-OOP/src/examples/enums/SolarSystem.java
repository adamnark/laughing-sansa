package examples.enums;

/**
 *
 * @author blecherl
 */
public enum SolarSystem {

    MERCURY("Mercury", 1, 1, 1),
    VENUS("Venus", 2, 2, 2),
    EARTH("Earth", 3, 100452, 100),
    MARS("Me'adim", 4, 480452, 500);

    private String name;
    private int ordinalPosition;
    int avgDistanceFromSun;
    int mass;

    private SolarSystem(String name, int ordinalPosition, int avgDistanceFromSun, int mass) {
        this.name = name;
        this.ordinalPosition = ordinalPosition;
        this.avgDistanceFromSun = avgDistanceFromSun;
        this.mass = mass;
    }
// You can do with or without the special toString
//	@Override
//	public String toString() {
//		return name + " mass = " + mass;
//	}

    @Override
    public String toString() {
        return name;//"SolarSystem{" + "name=" + name + '}';
    }



    public int getAvgDistanceFromSun() {
        return avgDistanceFromSun;
    }

    public int getMass() {
        return mass;
    }

    public String getName() {
        return name;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setName (String name){
        this.name = name;
    }

}

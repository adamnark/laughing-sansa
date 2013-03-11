package examples.interfaces.housecleaning.robots;

/**
 *
 * @author blecherl
 */
public class Robot {
    protected String manufacturer;
    protected String serialNumber;

    protected Robot(String manufacturer, String serialNumber) {
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
    }
}
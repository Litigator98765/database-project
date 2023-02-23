/**
 * Put a short phrase describing the program here.
 *
 * @author Max Miller
 *
 */
public class Warehouse {
    private String city;
    private String address;
    private String droneCap;
    private String equipCap;
    private String manager;
    private String phone;

    public Warehouse(String city, String address, String droneCap,
            String equipCap, String manager, String phone) {
        this.city = city;
        this.address = address;
        this.droneCap = droneCap;
        this.equipCap = equipCap;
        this.manager = manager;
        this.phone = phone;
    }

    public String getCity() {
        return this.city;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDroneCap() {
        return this.droneCap;
    }

    public String getEquipCap() {
        return this.equipCap;
    }

    public String getManager() {
        return this.manager;
    }

    public String getPhone() {
        return this.phone;
    }

}

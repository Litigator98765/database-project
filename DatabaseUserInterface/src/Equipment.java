/**
 * Put a short phrase describing the program here.
 *
 * @author Max Miller
 *
 */
public class Equipment {
    private String invID;
    private String size;
    private String type;
    private String arrivalDate;
    private String weight;

    public Equipment(String invID, String size, String type, String arrivalDate,
            String weight) {
        this.invID = invID;
        this.size = size;
        this.type = type;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
    }

    public String getID() {
        return this.invID;
    }

    public String getSize() {
        return this.size;
    }

    public String getType() {
        return this.type;
    }

    public String getArrival() {
        return this.arrivalDate;
    }

    public String getWeight() {
        return this.weight;
    }

}

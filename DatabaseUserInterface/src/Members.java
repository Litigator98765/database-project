/**
 * Put a short phrase describing the program here.
 *
 * @author Max Miller
 *
 */
public class Members {
    private String name;
    private String phone;
    private String addy;
    private String email;
    private String startDate;

    public Members(String name, String phone, String addy, String email,
            String startDate) {
        this.name = name;
        this.phone = phone;
        this.addy = addy;
        this.email = email;
        this.startDate = startDate;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getAddy() {
        return this.addy;
    }

    public String getEmail() {
        return this.email;
    }

    public String getStartDate() {
        return this.startDate;
    }

}

import java.util.TreeMap;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Max Miller
 *
 */
public final class DatabaseInterface {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private DatabaseInterface() {
    }

    private static void newMember(SimpleReader in,
            TreeMap<String, Members> map) {
        System.out.print("Enter your name: ");
        String name = in.nextLine();
        System.out
                .print("Enter phone # in the following format (6147362535): ");
        String phone = in.nextLine();
        System.out.print("Enter your address: ");
        String addy = in.nextLine();
        System.out.print("Enter your email: ");
        String email = in.nextLine();
        System.out.print("Enter todays date: ");
        String startDate = in.nextLine();

        //Create new member object
        Members member = new Members(name, phone, addy, email, startDate);

        //Put member object into treeMap
        map.put(email, member);

        System.out.println("Member successfully added.\n");

    }

    private static void memberLookup(SimpleWriter out, SimpleReader in,
            TreeMap<String, Members> map) {

        System.out.print("Enter email: ");
        String email = in.nextLine();

        Members member = map.get(email);

        System.out.println("MEMBER INFORMATION:");
        System.out.println("-----------------------------------------------");
        System.out.println("Email: " + email);
        System.out.println("Name: " + member.getName());
        System.out.println("Phone #: " + member.getPhone());
        System.out.println("Address: " + member.getAddy());
        System.out.println("Membership Start Date: " + member.getStartDate());
        System.out.println("-----------------------------------------------\n");
    }

    private static void deleteMember(SimpleWriter out, SimpleReader in,
            TreeMap<String, Members> map) {

        System.out.print("Enter member email to be deleted: ");
        String email = in.nextLine();

        map.remove(email);

        System.out.println("Member successfully removed.\n");
    }

    private static void newEquip(SimpleReader in,
            TreeMap<String, Equipment> map) {
        System.out.print("Enter Inventory ID: ");
        String invID = in.nextLine();
        System.out.print("Enter size: ");
        String size = in.nextLine();
        System.out.print("Enter type: ");
        String type = in.nextLine();
        System.out.print("Enter arrival date: ");
        String arrival = in.nextLine();
        System.out.print("Enter weight: ");
        String weight = in.nextLine();

        //Create new equipment object
        Equipment equip = new Equipment(invID, size, type, arrival, weight);

        //Enter object into treeMap
        map.put(invID, equip);

        System.out.println("Equipment successfully added.\n");
    }

    private static void equipLookup(SimpleWriter out, SimpleReader in,
            TreeMap<String, Equipment> map) {

        System.out.print("Enter invID: ");
        String invID = in.nextLine();
        Equipment equip = map.get(invID);

        System.out.println("EQUIPMENT INFORMATION:");
        System.out.println("-----------------------------------------------");
        System.out.println("invID: " + invID);
        System.out.println("Size: " + equip.getSize());
        System.out.println("Type: " + equip.getType());
        System.out.println("Arrival Date: " + equip.getArrival());
        System.out.println("Weight: " + equip.getWeight());
        System.out.println("-----------------------------------------------\n");
    }

    private static void deleteEquip(SimpleWriter out, SimpleReader in,
            TreeMap<String, Equipment> map) {

        System.out.print("Enter equipment invID to be deleted: ");
        String invID = in.nextLine();

        map.remove(invID);

        System.out.println("Equipment successfully removed.\n");
    }

    private static void newWarehouse(SimpleReader in,
            TreeMap<String, Warehouse> map) {
        System.out.print("Enter City: ");
        String city = in.nextLine();
        System.out.print("Enter Address: ");
        String addy = in.nextLine();
        System.out.print("Enter Drone Capacity: ");
        String droneCap = in.nextLine();
        System.out.print("Enter Equipment Capacity: ");
        String equipCap = in.nextLine();
        System.out.print("Enter manager: ");
        String manager = in.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = in.nextLine();

        //Create new equipment object
        Warehouse warehouse = new Warehouse(city, addy, droneCap, equipCap,
                manager, phone);

        //Enter object into treeMap
        map.put(city + "/" + addy, warehouse);

        System.out.println("Warehouse successfully added.\n");
    }

    private static void warehouseLookup(SimpleWriter out, SimpleReader in,
            TreeMap<String, Warehouse> map) {

        System.out.print("Enter Warehouse city and address (City/Addr): ");
        String cityAndAddy = in.nextLine();

        Warehouse warehouse = map.get(cityAndAddy);

        System.out.println("WAREHOUSE INFORMATION:");
        System.out.println("-----------------------------------------------");
        System.out.println("City: " + warehouse.getCity());
        System.out.println("Address: " + warehouse.getAddress());
        System.out.println("Drone Capacity: " + warehouse.getDroneCap());
        System.out.println("Equipment Capacity: " + warehouse.getEquipCap());
        System.out.println("Phone Number: " + warehouse.getPhone());
        System.out.println("Manager: " + warehouse.getManager());
        System.out.println("-----------------------------------------------\n");
    }

    private static void deleteWarehouse(SimpleWriter out, SimpleReader in,
            TreeMap<String, Warehouse> map) {
        System.out.print(
                "Enter member warehosue city and address to be deleted(city/addr): ");
        String cityAndAddr = in.nextLine();

        map.remove(cityAndAddr);

        System.out.println("Warehouse successfully removed.\n");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        TreeMap<String, Members> memberMap = new TreeMap<String, Members>();
        TreeMap<String, Equipment> equipMap = new TreeMap<String, Equipment>();
        TreeMap<String, Warehouse> warehouseMap = new TreeMap<String, Warehouse>();

        int input;
        do {
            System.out.println("Welcome to Local Equipment Rental Inc.!");
            System.out.println("---------------------------------------");
            System.out.println(
                    "[0] to add a member, [1] to lookup a member, or [2] to delete a member");
            System.out.println(
                    "[3] to add equipment, [4] to lookup equipment, [5] to delete equipment");
            System.out.println(
                    "[6] to add a warehouse, [7] to lookup a warehouse, [8] to delete a warehouse");
            System.out.println("Enter [9] to stop");
            System.out.print("Selection: ");
            input = in.nextInteger();
            System.out.println();

            if (input == 0) {
                newMember(in, memberMap);
            } else if (input == 1) {
                memberLookup(out, in, memberMap);
            } else if (input == 2) {
                deleteMember(out, in, memberMap);
            } else if (input == 3) {
                newEquip(in, equipMap);
            } else if (input == 4) {
                equipLookup(out, in, equipMap);
            } else if (input == 5) {
                deleteEquip(out, in, equipMap);
            } else if (input == 6) {
                newWarehouse(in, warehouseMap);
            } else if (input == 7) {
                warehouseLookup(out, in, warehouseMap);
            } else if (input == 8) {
                deleteWarehouse(out, in, warehouseMap);
            }
        } while (input != 9);

        in.close();
        out.close();
    }

}

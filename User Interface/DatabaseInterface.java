import java.sql.Connection; 
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public final class DatabaseInterface {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private DatabaseInterface() {
    }

    private static String DATABASE = "Project_DB (3).db";

    /**
     * Queries the database and prints the results.
     *
     * @param conn
     *            a connection object
     * @param sql
     *            a SQL statement that returns rows This query is written with
     *            the Statement class, tipically used for static SQL SELECT
     *            statements
     */
    public static void sqlQuery(Connection conn, String sql) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String value = rsmd.getColumnName(i);
                System.out.print(value);
                if (i < columnCount) {
                    System.out.print(",  ");
                }
            }
            System.out.print("\n");
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                    if (i < columnCount) {
                        System.out.print(",  ");
                    }
                }
                System.out.print("\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection initializeDB(String databaseFileName) {
        /**
         * The "Connection String" or "Connection URL".
         *
         * "jdbc:sqlite:" is the "subprotocol". (If this were a SQL Server
         * database it would be "jdbc:sqlserver:".)
         */
        String url = "jdbc:sqlite:" + databaseFileName;
        Connection conn = null; // If you create this variable inside the Try block it will be out of scope
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                // Provides some positive assurance the connection and/or creation was successful.
                DatabaseMetaData meta = conn.getMetaData();
                System.out
                        .println("The driver name is " + meta.getDriverName());
                System.out.println(
                        "The connection to the database was successful.");
            } else {
                // Provides some feedback in case the connection failed but did not throw an exception.
                System.out.println("Null Connection");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out
                    .println("There was a problem connecting to the database.");
        }
        return conn;
    }

    //prints the result set of the sql query
    public static void printResultSet(ResultSet rs) {
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String value = rsmd.getColumnName(i);
                System.out.print(value);
                if (i < columnCount) {
                    System.out.print(",  ");
                }
            }
            System.out.print("\n");
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                    if (i < columnCount) {
                        System.out.print(",  ");
                    }
                }
                System.out.print("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // inserts a tuple into the MEMBER table; User provides the attribute values
    public static int insertMember(Connection conn, String user_Id,
            String fname, String lname, String addr, String phone, String email,
            String start_Date, String credit_Card) {
        String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?)";
        int success = 0;

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, user_Id);
            psmt.setString(2, fname);
            psmt.setString(3, lname);
            psmt.setString(4, addr);
            psmt.setString(5, phone);
            psmt.setString(6, email);
            psmt.setString(7, start_Date);
            psmt.setString(8, credit_Card);
            success = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // inserts a tuple into the DRONE table; User provides the attribute values
    public static int insertDrone(Connection conn, String fleet_Id,
            String model_Num, String serial_Num, String year, String desc,
            String warr_Exp, int weight_Cap, int volume_Cap, String dist_Auth,
            int max_Speed, int status, String addr, String city,
            String supp_Id) {
        String sql = "INSERT INTO DRONE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int success = 0;

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, fleet_Id);
            psmt.setString(2, model_Num);
            psmt.setString(3, serial_Num);
            psmt.setString(4, year);
            psmt.setString(5, desc);
            psmt.setString(6, warr_Exp);
            psmt.setInt(7, weight_Cap);
            psmt.setInt(8, volume_Cap);
            psmt.setString(9, dist_Auth);
            psmt.setInt(10, max_Speed);
            psmt.setInt(11, status);
            psmt.setString(12, addr);
            psmt.setString(13, city);
            psmt.setString(14, supp_Id);
            success = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // inserts a tuple into the EQUIPMENT table; User provides the attribute values
    public static int insertEquipment(Connection conn, String inv_Id,
            String model_Num, String serial_Num, String year, String desc,
            String warr_Exp, String type, int weight, String arrival_Date,
            String size, String addr, String city, String supp_Id) {
        String sql = "INSERT INTO EQUIPMENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int success = 0;

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);

            psmt.setString(1, inv_Id);
            psmt.setString(2, model_Num);
            psmt.setString(3, serial_Num);
            psmt.setString(4, year);
            psmt.setString(5, desc);
            psmt.setString(6, warr_Exp);
            psmt.setString(7, type);
            psmt.setInt(8, weight);
            psmt.setString(9, arrival_Date);
            psmt.setString(10, size);
            psmt.setString(11, addr);
            psmt.setString(12, city);
            psmt.setString(13, supp_Id);
            success = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // inserts a tuple into the CUST_ORDER table; User provides the attribute values
    public static int insertCustOrder(Connection conn, String order_Num,
            int value, String order_Date, String return_Date, int return_Status,
            String inv_Id, String fleet_Id, String user_Id) {
        String sql = "INSERT INTO CUST_ORDER VALUES (?,?,?,?,?,?,?,?)";
        int success = 0;

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, order_Num);
            psmt.setInt(2, value);
            psmt.setString(3, order_Date);
            psmt.setString(4, return_Date);
            psmt.setInt(5, return_Status);
            psmt.setString(6, inv_Id);
            psmt.setString(7, fleet_Id);
            psmt.setString(8, user_Id);
            success = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // inserts a tuple into the EMPLOYEE table; User provides the attribute values
    public static int insertEmployee(Connection conn, String ssn, String fName,
            String lName, String addr, String phone, String email, int salary) {
        String sql = "INSERT INTO EMPLOYEE VALUES (?,?,?,?,?,?, ?)";
        int success = 0;
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, ssn);
            psmt.setString(2, fName);
            psmt.setString(3, lName);
            psmt.setString(4, addr);
            psmt.setString(5, phone);
            psmt.setString(6, email);
            psmt.setInt(7, salary);
            success = psmt.executeUpdate();
        }

        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // inserts a tuple into the INV_ORDER and FOR_DRONE tables; User provides the attribute values
    public static int insertDrone_Order(Connection conn, String order_Num,
            int value, String order_Date, String est_DOA, int num_Items,
            String type, String ssn, String fleet_Id) {
        String sql = "INSERT INTO INV_ORDER VALUES (?,?,?,?,?,?,?)";
        String sql1 = "INSERT INTO FOR_DRONE VALUES (?,?)";
        PreparedStatement psmt = null;
        PreparedStatement psmt1 = null;
        int success = 0;
        try {
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, order_Num);
            psmt.setInt(2, value);
            psmt.setString(3, order_Date);
            psmt.setString(4, est_DOA);
            psmt.setInt(5, num_Items);
            psmt.setString(6, type);
            psmt.setString(7, ssn);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            psmt1 = conn.prepareStatement(sql1);
            psmt1.setString(1, order_Num);
            psmt1.setString(2, fleet_Id);
            success += psmt1.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // inserts a tuple into the INV_ORDER table; User provides the attribute values
    public static int insertInv_Order(Connection conn, String order_Num,
            int value, String order_Date, String est_DOA, int num_Items,
            String type, String ssn) {
        String sql = "INSERT INTO INV_ORDER VALUES (?,?,?,?,?,?,?)";
        int success = 0;
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, order_Num);
            psmt.setInt(2, value);
            psmt.setString(3, order_Date);
            psmt.setString(4, est_DOA);
            psmt.setInt(5, num_Items);
            psmt.setString(6, type);
            psmt.setString(7, ssn);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // inserts a tuple into the FOR_DRONE table; User provides the attribute values
    public static int insertFor_Drone(Connection conn, String order_Num,
            String fleet_Id) {
        int success = 0;
        String sql = "INSERT INTO FOR_DRONE VALUES (?,?)";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, order_Num);
            psmt.setString(2, fleet_Id);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return success;
    }

    // inserts a tuple into the FOR_DRONE table; User provides the attribute values
    public static int insertFor_Equip(Connection conn, String order_Num,
            String inv_Id) {
        int success = 0;
        String sql = "INSERT INTO FOR_EQUIP VALUES (?,?)";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, order_Num);
            psmt.setString(2, inv_Id);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return success;
    }

    // inserts a tuple into the REVIEW table; User provides the attribute values
    public static int insertReview(Connection conn, String review_Id,
            int rating, String text, String user_Id, String inv_Id) {
        String sql = "INSERT INTO REVIEW VALUES (?,?,?,?,?)";
        int success = 0;
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, review_Id);
            psmt.setInt(2, rating);
            psmt.setString(3, text);
            psmt.setString(4, user_Id);
            psmt.setString(5, inv_Id);
            success += psmt.executeUpdate();
            return success;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // inserts a tuple into the REVIEW table; User provides the attribute values
    public static int insertSupplier(Connection conn, String supp_Id,
            String name, String addr, String city) {
        String sql = "INSERT INTO SUPPLIER VALUES (?,?,?,?)";
        int success = 0;

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, supp_Id);
            psmt.setString(2, name);
            psmt.setString(3, addr);
            psmt.setString(4, city);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // inserts a tuple into the WAREHOUSE table; User provides the attribute values
    public static int insertWarehouse(Connection conn, String addr, String city,
            String mgrFname, String mgrLname, String phone, int equip_cap,
            int drone_cap) {

        String sql = "INSERT INTO WAREHOUSE VALUES (?,?,?,?,?,?,?)";
        int success = 0;

        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, addr);
            psmt.setString(2, city);
            psmt.setString(3, mgrFname);
            psmt.setString(4, mgrLname);
            psmt.setString(5, phone);
            psmt.setInt(6, equip_cap);
            psmt.setInt(7, drone_cap);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // deletes a tuple from the CUST_ORDER table, given a valid Order_Num
    public static int deleteFromCUST_ORDER(Connection conn, String key) {

        int result = 0;
        String sql = "DELETE FROM CUST_ORDER WHERE Order_Num = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, key);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // deletes a tuple from the DRONE table, given a valid Fleet_Id
    public static int deleteFromDRONE(Connection conn, String key) {

        int result = 0;
        String sql = "DELETE FROM DRONE WHERE Fleet_Id = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, key);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // deletes a tuple from the EMPLOYEE table, given a valid Ssn
    public static int deleteFromEMPLOYEE(Connection conn, String key) {

        int result = 0;
        String sql = "DELETE FROM EMPLOYEE WHERE Ssn = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, key);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // deletes a tuple from the EQUIPMENT table, given a valid Inv_Id
    public static int deleteFromEQUIPMENT(Connection conn, String key) {

        int result = 0;
        String sql = "DELETE FROM EQUIPMENT WHERE Inv_Id = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, key);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // deletes a tuple from the INV_ORDER table, given a valid Order_Num
    public static int deleteFromINV_ORDER(Connection conn, String key) {

        int result = 0;
        String sql = "DELETE FROM INV_ORDER WHERE Order_Num = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, key);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // deletes a tuple from the MEMBER table, given a valid User_Id
    public static int deleteFromMEMBER(Connection conn, String key) {

        int result = 0;
        String sql = "DELETE FROM MEMBER WHERE User_Id = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, key);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // deletes a tuple from the REVIEW table, given a valid Review_Id
    public static int deleteFromREVIEW(Connection conn, String key) {

        int result = 0;
        String sql = "DELETE FROM REVIEW WHERE Review_Id = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, key);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // deletes a tuple from the SUPPLIER table, given a valid Supp_Id
    public static int deleteFromSUPPLIER(Connection conn, String key) {

        int result = 0;
        String sql = "DELETE FROM SUPPLIER WHERE Supp_Id = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, key);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // deletes a tuple from the INV_ORDER table, given a Addr and City
    public static int deleteFromWAREHOUSE(Connection conn, String addr,
            String city) {

        int result = 0;
        String sql = "DELETE FROM WAREHOUSE WHERE Addr = ? AND City = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, addr);
            psmt.setString(2, city);
            result = psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    // prints a list of the column names
    public static void printCols(Connection conn, String tableName) {
        try {
            String sql = "SELECT * FROM " + tableName + " LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            System.out.print("|");
            for (int i = 1; i <= columnCount; i++) {
                String value = rsmd.getColumnName(i);
                System.out.print(value + "|");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    // updates a single column of a specified table, given the primary key is one value
    public static int update(Connection conn, String tableName, String attrName,
            String searchName, String searchVal, String newVal) {
        int success = 0;
        try {
            String sql = "UPDATE " + tableName + " SET " + attrName + "=?"
                    + " WHERE " + searchName + "=?";
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, newVal);
            psmt.setString(2, searchVal);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // updates a single integer column of a specified table, given the primary key is one value
    public static int update(Connection conn, String tableName, String attrName,
            String searchName, String searchVal, int newVal) {
        int success = 0;
        try {
            String sql = "UPDATE " + tableName + " SET " + attrName + "=?"
                    + " WHERE " + searchName + "=?";

            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, newVal);
            psmt.setString(2, searchVal);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    // updates a single column of the WAREHOUSE table
    public static int updateWarehouse(Connection conn, String attrName,
            String addr, String city, String newVal) {
        int success = 0;
        try {
            String sql = "UPDATE WAREHOUSE SET " + attrName + "=?"
                    + " WHERE Addr=? AND City=?";

            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, newVal);
            psmt.setString(2, addr);
            psmt.setString(3, city);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    //updates a single integer column of the WAREHOUSE table
    public static int updateWarehouse(Connection conn, String attrName,
            String addr, String city, int newVal) {
        int success = 0;
        try {
            String sql = "UPDATE WAREHOUSE SET " + attrName + "=?"
                    + " WHERE Addr=? AND City=?";

            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, newVal);
            psmt.setString(2, addr);
            psmt.setString(3, city);
            success += psmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);
        Connection conn = initializeDB(DATABASE);
        String input = "";
        String table = "";
        conn.createStatement().execute("PRAGMA foreign_keys = ON");

        while (true) {

            System.out.println("What would you like to do: ");
            System.out.println("[ENTER DATA]");
            System.out.println("[EDIT]");
            System.out.println("[DELETE ENTRY]");
            System.out.println("[SEARCH]");
            System.out.println("[USEFUL REPORTS]");
            System.out.println("[QUIT]");
            input = in.nextLine();

            if (input.equals("QUIT")) {
                break;
            }

            if (!input.equals("ENTER DATA") && !input.equals("EDIT")
                    && !input.equals("DELETE ENTRY") && !input.equals("SEARCH")
                    && !input.equals("USEFUL REPORTS")) {
                System.out.println("ERROR: INVALID INPUT.\n");
                continue;
            }

            if (!input.equals("USEFUL REPORTS")) {
                System.out.println("What table do you want to " + input + ":");
                System.out.println(
                        "CUST_ORDER|DRONE|EMPLOYEE|EQUIPMENT|FOR_DRONE");
                System.out
                        .println("FOR_EQUIP|INV_ORDER|MEMBER|REVIEW|SUPPLIER|");
                System.out.println("WAREHOUSE");
                table = in.nextLine();
            }

            if (input.equals("ENTER DATA")) {
                System.out.println(
                        "Enter Each Element of Data Corresponding to the Given List on a New Line.");
                int eSuccess;
                if (table.equals("CUST_ORDER")) {
                    printCols(conn, "CUST_ORDER");
                    String orderNum = in.nextLine();
                    int value = Integer.parseInt(in.nextLine());
                    String orderDate = in.nextLine();
                    String returnDate = in.nextLine();
                    int returnStatus = Integer.parseInt(in.nextLine());
                    String inv_Id = in.nextLine();
                    String fleet_Id = in.nextLine();
                    String user_Id = in.nextLine();
                    eSuccess = insertCustOrder(conn, orderNum, value, orderDate,
                            returnDate, returnStatus, inv_Id, fleet_Id,
                            user_Id);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("DRONE")) {
                    printCols(conn, "DRONE");
                    String fleetId = in.nextLine();
                    String modelNum = in.nextLine();
                    String serialNum = in.nextLine();
                    String year = in.nextLine();
                    String desc = in.nextLine();
                    String warrExp = in.nextLine();
                    int weightCap = Integer.parseInt(in.nextLine());
                    int volCap = Integer.parseInt(in.nextLine());
                    String distAuth = in.nextLine();
                    int maxSpd = Integer.parseInt(in.nextLine());
                    int status = Integer.parseInt(in.nextLine());
                    String dAddr = in.nextLine();
                    String city = in.nextLine();
                    String suppId = in.nextLine();
                    eSuccess = insertDrone(conn, fleetId, modelNum, serialNum,
                            year, desc, warrExp, weightCap, volCap, distAuth,
                            maxSpd, status, dAddr, city, suppId);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("EMPLOYEE")) {
                    printCols(conn, "EMPLOYEE");
                    String ssn = in.nextLine();
                    String fName = in.nextLine();
                    String lName = in.nextLine();
                    String eAddr = in.nextLine();
                    String ePhone = in.nextLine();
                    String eEmail = in.nextLine();
                    int salary = Integer.parseInt(in.nextLine());
                    eSuccess = insertEmployee(conn, ssn, fName, lName, eAddr,
                            ePhone, eEmail, salary);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion. \n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("EQUIPMENT")) {
                    printCols(conn, "EQUIPMENT");
                    String eqInvId = in.nextLine();
                    String eqModelNum = in.nextLine();
                    String eqSerialNum = in.nextLine();
                    String eqYear = in.nextLine();
                    String eqDesc = in.nextLine();
                    String eqWarrExp = in.nextLine();
                    String eqType = in.nextLine();
                    int eqWeight = Integer.parseInt(in.nextLine());
                    String eqArrDate = in.nextLine();
                    String eqSize = in.nextLine();
                    String eqAddr = in.nextLine();
                    String eqCity = in.nextLine();
                    String eqSuppId = in.nextLine();
                    eSuccess = insertEquipment(conn, eqInvId, eqModelNum,
                            eqSerialNum, eqYear, eqDesc, eqWarrExp, eqType,
                            eqWeight, eqArrDate, eqSize, eqAddr, eqCity,
                            eqSuppId);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("INV_ORDER")) {
                    printCols(conn, "INV_ORDER");
                    String eoOrderNum = in.nextLine();
                    int eoValue = Integer.parseInt(in.nextLine());
                    String eoOrderDate = in.nextLine();
                    String eoEstDoa = in.nextLine();
                    int eoNumItems = Integer.parseInt(in.nextLine());
                    String eoType = in.nextLine();
                    String eoSsn = in.nextLine();
                    eSuccess = insertInv_Order(conn, eoOrderNum, eoValue,
                            eoOrderDate, eoEstDoa, eoNumItems, eoType, eoSsn);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("FOR_DRONE")) {
                    printCols(conn, "FOR_DRONE");
                    String fdOrderNum = in.nextLine();
                    String fdFleetId = in.nextLine();
                    eSuccess = insertFor_Drone(conn, fdOrderNum, fdFleetId);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("FOR_EQUIP")) {
                    printCols(conn, "FOR_EQUIP");
                    String feOrderNum = in.nextLine();
                    String feInvId = in.nextLine();
                    eSuccess = insertFor_Equip(conn, feOrderNum, feInvId);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("MEMBER")) {
                    printCols(conn, "MEMBER");
                    String mUserId = in.nextLine();
                    String mFname = in.nextLine();
                    String mLname = in.nextLine();
                    String mAddr = in.nextLine();
                    String mPhone = in.nextLine();
                    String mEmail = in.nextLine();
                    String mStartDate = in.nextLine();
                    String mCreditCard = in.nextLine();
                    eSuccess = insertMember(conn, mUserId, mFname, mLname,
                            mAddr, mPhone, mEmail, mStartDate, mCreditCard);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("REVIEW")) {
                    printCols(conn, "REVIEW");
                    String reviewId = in.nextLine();
                    int rating = Integer.parseInt(in.nextLine());
                    String text = in.nextLine();
                    String rUserId = in.nextLine();
                    String rInvId = in.nextLine();
                    eSuccess = insertReview(conn, reviewId, rating, text,
                            rUserId, rInvId);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("SUPPLIER")) {
                    printCols(conn, "SUPPLIER");
                    String sSuppId = in.nextLine();
                    String sName = in.nextLine();
                    String sAddr = in.nextLine();
                    String sCity = in.nextLine();
                    eSuccess = insertSupplier(conn, sSuppId, sName, sAddr,
                            sCity);
                    if (eSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("WAREHOUSE")) {
                    printCols(conn, "WAREHOUSE");
                    String wAddr = in.nextLine();
                    String wCity = in.nextLine();
                    String wMgrFname = in.nextLine();
                    String wMgrLname = in.nextLine();
                    String wPhone = in.nextLine();
                    int wEquipCap = Integer.parseInt(in.nextLine());
                    int wDroneCap = Integer.parseInt(in.nextLine());
                    int wSuccess = insertWarehouse(conn, wAddr, wCity,
                            wMgrFname, wMgrLname, wPhone, wEquipCap, wDroneCap);
                    if (wSuccess == 1) {
                        System.out.println("Successful Insertion.\n");
                    }

                } else {
                    System.out.println("ERROR: INVALID INPUT.\n");
                }
            } else if (input.equals("EDIT")) {
                String tableName;
                String attName;
                String idName;
                String key;
                String newVal;
                int intNewVal;
                int success = 0;
                if (table.equals("CUST_ORDER")) {
                    tableName = "CUST_ORDER";
                    System.out.println(
                            "Enter the Name of the Attribute you Want to Update from the List Below: ");
                    printCols(conn, tableName);
                    attName = in.nextLine();
                    System.out.println(
                            "Enter the Order Number of the Customer Order you Want to Update: ");
                    key = in.nextLine();
                    idName = "Order_Num";
                    System.out.println(
                            "Enter the New Value of the Attribute you Selected: ");
                    if (attName.equals("Value")
                            || attName.equals("Return_Status")) {
                        intNewVal = Integer.parseInt(in.nextLine());
                        success += update(conn, tableName, attName, idName, key,
                                intNewVal);
                    } else {
                        newVal = in.nextLine();
                        success += update(conn, tableName, attName, idName, key,
                                newVal);
                    }
                    if (success == 1) {
                        System.out.println("Successful Update.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("DRONE")) {
                    tableName = "DRONE";
                    System.out.println(
                            "Enter the Name of the Attribute you Want to Update from the List Below: ");
                    printCols(conn, tableName);
                    attName = in.nextLine();
                    System.out.println(
                            "Enter the Fleet ID of the Drone you Want to Update: ");
                    key = in.nextLine();
                    idName = "Fleet_Id";
                    System.out.println(
                            "Enter the New Value of the Attribute you Selected: ");
                    if (attName.equals("Weight_Cap")
                            || attName.equals("Volume_Cap")
                            || attName.equals("Max_Speed")
                            || attName.equals("Status")) {
                        intNewVal = Integer.parseInt(in.nextLine());
                        success += update(conn, tableName, attName, idName, key,
                                intNewVal);
                    } else {
                        newVal = in.nextLine();
                        success += update(conn, tableName, attName, idName, key,
                                newVal);
                    }
                    if (success == 1) {
                        System.out.println("Successful Update.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("EMPLOYEE")) {
                    tableName = "EMPLOYEE";
                    System.out.println(
                            "Enter the Name of the Attribute you Want to Update from the List Below: ");
                    printCols(conn, tableName);
                    attName = in.nextLine();
                    System.out.println(
                            "Enter the Ssn of the Employee you Want to Update: ");
                    key = in.nextLine();
                    idName = "Ssn";
                    System.out.println(
                            "Enter the New Value of the Attribute you Selected: ");
                    if (attName.equals("Salary")) {
                        intNewVal = Integer.parseInt(in.nextLine());
                        success += update(conn, tableName, attName, idName, key,
                                intNewVal);
                    } else {
                        newVal = in.nextLine();
                        success += update(conn, tableName, attName, idName, key,
                                newVal);
                    }
                    if (success == 1) {
                        System.out.println("Successful Update.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("EQUIPMENT")) {
                    tableName = "EQUIPMENT";
                    System.out.println(
                            "Enter the Name of the Attribute you Want to Update from the List Below: ");
                    printCols(conn, tableName);
                    attName = in.nextLine();
                    System.out.println(
                            "Enter the Inventory Id of the Equipment you Want to Update: ");
                    key = in.nextLine();
                    idName = "Inv_Id";
                    System.out.println(
                            "Enter the New Value of the Attribute you Selected: ");
                    if (attName.equals("Weight")) {
                        intNewVal = Integer.parseInt(in.nextLine());
                        success += update(conn, tableName, attName, idName, key,
                                intNewVal);
                    } else {
                        newVal = in.nextLine();
                        success += update(conn, tableName, attName, idName, key,
                                newVal);
                    }
                    if (success == 1) {
                        System.out.println("Successful Update.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("FOR_DRONE")) {
                    System.out.println("Cannot Edit Data in this Table.\n");
                } else if (table.equals("FOR_EQUIP")) {
                    System.out.println("Cannot Edit Data in this Table.\n");
                } else if (table.equals("INV_ORDER")) {
                    tableName = "INV_ORDER";
                    System.out.println(
                            "Enter the Name of the Attribute you Want to Update from the List Below: ");
                    printCols(conn, tableName);
                    attName = in.nextLine();
                    System.out.println(
                            "Enter the Order Number of the Inventory Order you Want to Update: ");
                    key = in.nextLine();
                    idName = "Order_Num";
                    System.out.println(
                            "Enter the New Value of the Attribute you Selected: ");
                    if (attName.equals("Value")
                            || attName.equals("Num_Items")) {
                        intNewVal = Integer.parseInt(in.nextLine());
                        success += update(conn, tableName, attName, idName, key,
                                intNewVal);
                    } else {
                        newVal = in.nextLine();
                        success += update(conn, tableName, attName, idName, key,
                                newVal);
                    }
                    if (success == 1) {
                        System.out.println("Successful Update.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("MEMBER")) {
                    tableName = "MEMBER";
                    System.out.println(
                            "Enter the Name of the Attribute you Want to Update from the List Below: ");
                    printCols(conn, tableName);
                    attName = in.nextLine();
                    System.out.println(
                            "Enter the User Id of the Member you Want to Update: ");
                    key = in.nextLine();
                    idName = "User_Id";
                    System.out.println(
                            "Enter the New Value of the Attribute you Selected: ");
                    newVal = in.nextLine();
                    success += update(conn, tableName, attName, idName, key,
                            newVal);
                    if (success == 1) {
                        System.out.println("Successful Update.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("REVIEW")) {
                    tableName = "REVIEW";
                    System.out.println(
                            "Enter the Name of the Attribute you Want to Update from the List Below: ");
                    printCols(conn, tableName);
                    attName = in.nextLine();
                    System.out.println(
                            "Enter the Review Id of the Review you Want to Update: ");
                    key = in.nextLine();
                    idName = "Review_Id";
                    System.out.println(
                            "Enter the New Value of the Attribute you Selected: ");
                    newVal = in.nextLine();
                    success += update(conn, tableName, attName, idName, key,
                            newVal);
                    if (success == 1) {
                        System.out.println("Successful Update.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("SUPPLIER")) {
                    tableName = "SUPPLIER";
                    System.out.println(
                            "Enter the Name of the Attribute you Want to Update from the List Below: ");
                    printCols(conn, tableName);
                    attName = in.nextLine();
                    System.out.println(
                            "Enter the Supplier Id of the Supplier you Want to Update: ");
                    key = in.nextLine();
                    idName = "Supp_Id";
                    System.out.println(
                            "Enter the New Value of the Attribute you Selected: ");
                    newVal = in.nextLine();
                    success += update(conn, tableName, attName, idName, key,
                            newVal);
                    if (success == 1) {
                        System.out.println("Successful Update.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("WAREHOUSE")) {
                    tableName = "WAREHOUSE";
                    System.out.println(
                            "Enter the Name of the Attribute you Want to Update from the List Below: ");
                    printCols(conn, tableName);
                    attName = in.nextLine();
                    System.out.println(
                            "Enter the Address, then City on a New Line of the Warehouse you Want to Update: ");
                    key = in.nextLine();
                    String city = in.nextLine();
                    System.out.println(
                            "Enter the New Value of the Attribute you Selected: ");
                    if (attName.equals("Equip_Cap")
                            || attName.equals("Drone_Cap")) {
                        intNewVal = Integer.parseInt(in.nextLine());
                        success += updateWarehouse(conn, attName, key, city,
                                intNewVal);
                    } else {
                        newVal = in.nextLine();
                        success += updateWarehouse(conn, attName, key, city,
                                newVal);
                    }
                    if (success == 1) {
                        System.out.println("Successful Update.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else {
                    System.out.println("ERROR: INVALID INPUT.\n");
                }
            } else if (input.equals("DELETE ENTRY")) {
                int result;
                if (table.equals("CUST_ORDER")) {
                    System.out.println("Enter Order_Num: ");
                    String Order_Num = in.nextLine();
                    result = deleteFromCUST_ORDER(conn, Order_Num);
                    if (result == 1) {
                        System.out.println("Successful Deletion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("DRONE")) {
                    System.out.println("Enter Fleet_Id: ");
                    String Fleet_Id = in.nextLine();
                    result = deleteFromDRONE(conn, Fleet_Id);
                    if (result == 1) {
                        System.out.println("Successful Deletion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("EMPLOYEE")) {
                    System.out.println("Enter Ssn: ");
                    String ssn = in.nextLine();
                    result = deleteFromEMPLOYEE(conn, ssn);
                    if (result == 1) {
                        System.out.println("Successful Deletion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("EQUIPMENT")) {
                    System.out.println("Enter Inv_Id: ");
                    String Inv_Id = in.nextLine();
                    result = deleteFromEQUIPMENT(conn, Inv_Id);
                    if (result == 1) {
                        System.out.println("Successful Deletion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("FOR_DRONE")) {
                    System.out.println("Cannot Delete From this Table.\n");
                } else if (table.equals("FOR_EQUIP")) {
                    System.out.println("Cannot Delete From this Table.\n");
                } else if (table.equals("INV_ORDER")) {
                    System.out.println("Enter Order_Num: ");
                    String Order_NumI = in.nextLine();
                    result = deleteFromINV_ORDER(conn, Order_NumI);
                    if (result == 1) {
                        System.out.println("Successful Deletion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("MEMBER")) {
                    System.out.println("Enter User_Id: ");
                    String User_Id = in.nextLine();
                    result = deleteFromMEMBER(conn, User_Id);
                    if (result == 1) {
                        System.out.println("Successful Deletion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("REVIEW")) {
                    System.out.println("Enter Review_Id: ");
                    String Review_Id = in.nextLine();
                    result = deleteFromREVIEW(conn, Review_Id);
                    if (result == 1) {
                        System.out.println("Successful Deletion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("SUPPLIER")) {
                    System.out.println("Enter Supp_Id: ");
                    String Supp_Id = in.nextLine();
                    result = deleteFromSUPPLIER(conn, Supp_Id);
                    if (result == 1) {
                        System.out.println("Successful Deletion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else if (table.equals("WAREHOUSE")) {
                    System.out.println("Enter Address: ");
                    String addr = in.nextLine();
                    System.out.println("Enter City: ");
                    String city = in.nextLine();
                    result = deleteFromWAREHOUSE(conn, addr, city);
                    if (result == 1) {
                        System.out.println("Successful Deletion.\n");
                    } else {
                        System.out.println("ERROR: INVALID INPUT.\n");
                    }
                } else {
                    System.out.println("ERROR: INVALID INPUT.\n");
                }
            } else if (input.equals("SEARCH")) {
                String query = "";
                String pK = "";
                if (table.equals("CUST_ORDER")) {
                    System.out.println("Enter Order_Num:");
                    pK = in.nextLine();
                    query = "SELECT * FROM CUST_ORDER WHERE Order_Num=?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                } else if (table.equals("DRONE")) {
                    System.out.println("Enter Fleet_Id:");
                    pK = in.nextLine();
                    query = "SELECT * FROM DRONE WHERE Fleet_Id=?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                } else if (table.equals("EMPLOYEE")) {
                    System.out.println("Enter Ssn:");
                    pK = in.nextLine();
                    query = "SELECT * FROM EMPLOYEE WHERE Ssn=?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                } else if (table.equals("EQUIPMENT")) {
                    System.out.println("Enter Inv_Id:");
                    pK = in.nextLine();
                    query = "SELECT * FROM EQUIPMENT WHERE Inv_Id=?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                }
                // returns all of the fleet ids corresponding to an order number
                else if (table.equals("FOR_DRONE")) {
                    System.out.println("Enter Order_Num:");
                    pK = in.nextLine();
                    query = "SELECT * FROM FOR_DRONE WHERE Order_Num=?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                }
                // returns all of the inventory ids corresponding to an order number
                else if (table.equals("FOR_EQUIP")) {
                    System.out.println("Enter Order_Num:");
                    pK = in.nextLine();
                    query = "SELECT * FROM FOR_EQUIP WHERE Order_Num = ?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                } else if (table.equals("INV_ORDER")) {
                    System.out.println("Enter Order_Num:");
                    pK = in.nextLine();
                    query = "SELECT * FROM EQUIPMENT WHERE Order_Num = ?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                } else if (table.equals("MEMBER")) {
                    System.out.println("Enter User_Id:");
                    pK = in.nextLine();
                    query = "SELECT * FROM MEMBER WHERE User_Id = ?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                } else if (table.equals("REVIEW")) {
                    System.out.println("Enter Review_Id:");
                    pK = in.nextLine();
                    query = "SELECT * FROM REVIEW WHERE Review_Id = ?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                } else if (table.equals("SUPPLIER")) {
                    System.out.println("Enter Supp_Id:");
                    pK = in.nextLine();
                    query = "SELECT * FROM SUPPLIER WHERE Supp_Id = ?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                } else if (table.equals("WAREHOUSE")) {
                    System.out.println("Enter Addr:");
                    pK = in.nextLine();
                    System.out.println("Enter City:");
                    String pkW = in.nextLine();
                    query = "SELECT * FROM WAREHOUSE WHERE Addr = ? AND City = ?";
                    try {
                        PreparedStatement stmt = conn.prepareStatement(query);
                        stmt.setString(1, pK);
                        stmt.setString(2, pkW);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.out.println();
                } else {
                    System.out.println("ERROR: INVALID INPUT.\n");
                }
            } else if (input.equals("USEFUL REPORTS")) {
                String report;
                System.out.println("Enter the report you want to see:");
                System.out.println("REPORTS:");
                System.out.println("[1]: All equipment rented by a member");
                System.out.println("[2]: Most popular item");
                System.out.println("[3]: Most popular Manufacturer");
                System.out.println("[4]: Drone(s) with most orders");
                System.out.println("[5]: Top user");
                System.out.println("[6]: Find equipment by type before year");
                report = in.nextLine();
                if (report.equals("1")) {
                    try {
                        System.out.println("Enter User_Id");
                        String uid = in.nextLine();
                        PreparedStatement stmt = conn.prepareStatement(
                                "SELECT User_Id AS User, COUNT(*) AS NumOrders FROM CUST_ORDER WHERE User_Id = ? GROUP BY User_Id;");
                        stmt.setString(1, uid);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                } else if (report.equals("2")) {
                    try {
                        PreparedStatement stmt = conn.prepareStatement(
                                "SELECT Inv_Id, SUM((julianday(Return_Date) - julianday(Order_Date))) AS Time_Rented_Days "
                                        + "FROM CUST_ORDER "
                                        + "GROUP BY CUST_ORDER.Inv_Id "
                                        + "ORDER BY Time_Rented_Days DESC LIMIT 1;");
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                } else if (report.equals("3")) {
                    try {
                        PreparedStatement stmt = conn.prepareStatement(
                                "SELECT EQUIPMENT.Supp_Id, Name, COUNT(*) AS Num_Orders "
                                        + "FROM EQUIPMENT "
                                        + "JOIN CUST_ORDER ON EQUIPMENT.Inv_Id = CUST_ORDER.Inv_Id "
                                        + "JOIN SUPPLIER ON SUPPLIER.Supp_Id = EQUIPMENT.Supp_Id "
                                        + "GROUP BY SUPPLIER.Supp_Id "
                                        + "ORDER BY 3 DESC " + "LIMIT 1;");
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                } else if (report.equals("4")) {
                    try {
                        PreparedStatement stmt = conn.prepareStatement(
                                "SELECT CUST_ORDER.Fleet_Id, COUNT(*) AS NumOrders "
                                        + "FROM CUST_ORDER "
                                        + "INNER JOIN DRONE ON CUST_ORDER.Fleet_Id = DRONE.Fleet_Id "
                                        + "GROUP BY CUST_ORDER.Fleet_Id "
                                        + "HAVING COUNT(*) = ("
                                        + "SELECT MAX(NumOrders) " + "FROM ("
                                        + "SELECT COUNT(*) AS NumOrders "
                                        + "FROM CUST_ORDER "
                                        + "INNER JOIN DRONE ON CUST_ORDER.Fleet_Id = DRONE.Fleet_Id "
                                        + "GROUP BY CUST_ORDER.Fleet_Id "
                                        + "    ) AS OrderCounts " + ")");
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                } else if (report.equals("5")) {
                    try {
                        PreparedStatement stmt = conn.prepareStatement(
                                "SELECT User_Id AS User, COUNT(*) AS NumOrders "
                                        + "FROM CUST_ORDER "
                                        + "GROUP BY User_Id "
                                        + "ORDER BY COUNT(*) DESC "
                                        + "LIMIT 1;");
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                } else if (report.equals("6")) {
                    try {
                        System.out.println("Enter the type: ");
                        String type = in.nextLine();
                        System.out.println("Enter the year: ");
                        String year = in.nextLine();
                        PreparedStatement stmt = conn.prepareStatement(
                                "SELECT Desc AS EquipmentSelected "
                                        + "FROM EQUIPMENT "
                                        + "WHERE Year < ? AND Type = ?;");
                        stmt.setString(1, year);
                        stmt.setString(2, type);
                        ResultSet rs = stmt.executeQuery();
                        printResultSet(rs);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println();
                } else {
                    System.out.println("ERROR: INVALID INPUT.\n");
                }

            }

        }
        in.close();
    }
}

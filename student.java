import java.sql.*;
import java.util.Scanner;

public class EmployeeCRUD {

    static final String URL =
            "jdbc:mysql://localhost:3306/trainingdb";
    static final String USER = "root";
    static final String PASSWORD = "admin";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(
                            URL,
                            USER,
                            PASSWORD
                    );

            while(true) {

                System.out.println("\n===== EMPLOYEE MANAGEMENT =====");
                System.out.println("1. Insert Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");

                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();

                switch(choice) {

                    case 1:

                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Salary: ");
                        double salary = sc.nextDouble();

                        String insertSql =
                                "INSERT INTO employee VALUES(?,?,?)";

                        PreparedStatement ps1 =
                                con.prepareStatement(insertSql);

                        ps1.setInt(1,id);
                        ps1.setString(2,name);
                        ps1.setDouble(3,salary);

                        int rows =
                                ps1.executeUpdate();

                        System.out.println(
                                rows +
                                " Employee Inserted Successfully");

                        break;

                    case 2:

                        String selectSql =
                                "SELECT * FROM employee";

                        PreparedStatement ps2 =
                                con.prepareStatement(selectSql);

                        ResultSet rs =
                                ps2.executeQuery();

                        System.out.println(
                                "\nID\tNAME\tSALARY");

                        while(rs.next()) {

                            System.out.println(
                                    rs.getInt("id") + "\t" +
                                    rs.getString("name") + "\t" +
                                    rs.getDouble("salary")
                            );
                        }

                        break;

                    case 3:

                        System.out.print(
                                "Enter Employee ID: ");

                        int updateId =
                                sc.nextInt();

                        System.out.print(
                                "Enter New Salary: ");

                        double newSalary =
                                sc.nextDouble();

                        String updateSql =
                                "UPDATE employee SET salary=? WHERE id=?";

                        PreparedStatement ps3 =
                                con.prepareStatement(updateSql);

                        ps3.setDouble(1,newSalary);
                        ps3.setInt(2,updateId);

                        int updated =
                                ps3.executeUpdate();

                        System.out.println(
                                updated +
                                " Employee Updated");

                        break;

                    case 4:

                        System.out.print(
                                "Enter Employee ID: ");

                        int deleteId =
                                sc.nextInt();

                        String deleteSql =
                                "DELETE FROM employee WHERE id=?";

                        PreparedStatement ps4 =
                                con.prepareStatement(deleteSql);

                        ps4.setInt(1,deleteId);

                        int deleted =
                                ps4.executeUpdate();

                        System.out.println(
                                deleted +
                                " Employee Deleted");

                        break;

                    case 5:

                        con.close();

                        System.out.println(
                                "Application Closed");

                        System.exit(0);

                    default:

                        System.out.println(
                                "Invalid Choice");
                }
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}

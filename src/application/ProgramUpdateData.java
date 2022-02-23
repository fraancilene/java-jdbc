package application;

import db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgramUpdateData {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;

        try{
            conn = DB.getConnection();
            pst = conn.prepareStatement(
                    "UPDATE tb_seller SET BaseSalary = BaseSalary + ? WHERE (DepartmentId = ?)");

            pst.setDouble(1, 200.0);
            pst.setInt(2, 2);

            int rowsAffected = pst.executeUpdate();
            System.out.println("Done! Rows Affected: " + rowsAffected);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}

package application;

import db.DB;
import db.DbIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProgramDeleteData {

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pst = null;

        try{
            conn = DB.getConnection();
            pst = conn.prepareStatement("DELETE FROM tb_department WHERE id = ?");
            //pst.setInt(1, 5);
            pst.setInt(1, 2);
            int rowsAffected = pst.executeUpdate();
            System.out.println("Done! Rows Affected: " + rowsAffected);

        } catch (SQLException e) {
           throw new DbIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }
    }
}

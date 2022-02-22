package application;

import db.DB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from tb_department");

            // percorrendo os dados e capturando
            while (rs.next()){
                System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            // fechando os recursos
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }


    }

}

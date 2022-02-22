package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ProgramInsertData {
    public static void main(String[] args) {

        // inseção simples de um novo vendedor com preparedStatement
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            conn = DB.getConnection();
            pst = conn.prepareStatement("INSERT INTO tb_seller(Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                    "values (?, ?, ?, ?, ?)", // ? - PLACEHOLDER - lugar onde vou colocar o valor depois
                    Statement.RETURN_GENERATED_KEYS); // recuperando id

            // trocando a interrogações por valores
            pst.setString(1, "Carl Purple");
            pst.setString(2, "carl@gmail.com");
            pst.setDate(3, new Date(sdf.parse("22/02/1985").getTime())); // java.sql.date
            pst.setDouble(4, 3000);
            pst.setInt(5, 4);

            // quando estiver fazendo uma alteração chama o executeUpdate, retorna um inteiro
            int rowsAffected = pst.executeUpdate();

            // pegando o código do registro inserido
            if (rowsAffected > 0){
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }

            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(pst);
            DB.closeConnection();
        }

        //inserindo mais de um dado ao mesmo tempo
        try {
            conn = DB.getConnection();
            pst = conn.prepareStatement("insert into tb_department (name) values ('D1'),('D2')",
                    Statement.RETURN_GENERATED_KEYS);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = pst.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }

            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

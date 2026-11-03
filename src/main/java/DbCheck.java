import db.Database;

import java.sql.Connection;
import java.sql.SQLException;

public class DbCheck {

    public static void main(String[] args) {
        try(Connection conn = Database.getConnection()) {
            System.out.println("😊 " + conn.getMetaData());
        }catch(SQLException e) {
            System.out.println("❌ Ошибка: " + e.getMessage());
        }
    }
}

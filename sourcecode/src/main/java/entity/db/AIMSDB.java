package entity.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import java.sql.Connection;
import utils.*;

// Temporal cohesion: main() chỉ liên quan đến việc kết nối database bởi việc App chạy sẽ gọi đến getConnection() của AIMSDB
// Design Pattern: Singleton - Dễ dàng quản lí, debug, tạo ra tính nhất quán trong việc kết nối database, tối ưu tài nguyên
public class AIMSDB {

    private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
    private static Connection connect;

    // TODO: refactor Utils -> limit connections
    public static Connection getConnection() {
        if (connect != null)
            return connect;
        try {
            // Clean code: nên sử dụng các hằng số cho các giá trị cố định
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:src/main/resources/assets/db/aims.db";
            connect = DriverManager.getConnection(url);
            LOGGER.info("Connect database successfully");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
        return connect;
    }

    public static void main(String[] args) {
        AIMSDB.getConnection();
    }
}

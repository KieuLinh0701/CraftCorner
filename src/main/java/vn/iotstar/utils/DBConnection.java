package vn.iotstar.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final String serverName = "DESKTOP-DJ03I8E"; // Tên server
    private final String instanceName = "MSSQLSERVER02"; // Tên instance
    private final String databaseName = "CraftCornerDB"; // Tên cơ sở dữ liệu   C_H_Noi_That
    private final String portNumber = "1435"; // Cổng mặc định SQL Server
    private final String username = "sa"; // Tên người dùng
    private final String password = "1"; // Mật khẩu

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // Chuỗi kết nối tới SQL Server
    	String url = "jdbc:sqlserver://" + serverName + "\\" + instanceName + ":" 
                + portNumber + ";databaseName=" + databaseName 
                + ";encrypt=false;trustServerCertificate=true";

        // Đảm bảo JDBC Driver đã được load
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Trả về đối tượng Connection
        return DriverManager.getConnection(url, username, password);
    }
}

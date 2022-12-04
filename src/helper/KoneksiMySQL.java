package helper;
import java.sql.Connection;
import java.sql.DriverManager;

public class KoneksiMySQL {
    public static Connection getConnection(){
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/db_bimbingan";
        String user = "root";
        String pass = "";
        Connection conn = null;

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Berhasil Koneksi Database");

        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}

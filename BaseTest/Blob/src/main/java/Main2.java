import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

/**
 * Created by aharon on 3/11/15.
 */
public class Main2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException, IOException {
        Class.forName("com.j_spaces.jdbc.driver.GDriver").newInstance();
        String url = "jdbc:gigaspaces:url:jini://*/*/mySpace?groups=aharon";
        Connection conn = DriverManager.getConnection(url);

        Statement st1 = conn.createStatement();
        String createTable = "CREATE TABLE MY_DATA (ID INTEGER INDEX,BLOB_COL BLOB)";
        st1.executeUpdate(createTable);
        st1.close();

        File imgfile = new File("/tmp/largeFile.pdf");

        for (int i = 1; i < 5; i++) {
            FileInputStream fin = new FileInputStream(imgfile);
            PreparedStatement pre = conn.prepareStatement("insert into MY_DATA values(?,?)");
            pre.setInt(1, i);
            pre.setBinaryStream(2, fin, (int) imgfile.length());
            pre.executeUpdate();

            System.out.println("We have " + i + " files in the space");

            pre.close();
            fin.close();
        }

        for (int i = 1; i < 5; i++) {
            PreparedStatement pre = conn.prepareStatement("select ID,BLOB_COL from MY_DATA where ID = ?");
            pre.setInt(1, i);
            ResultSet result = pre.executeQuery();
            while (result.next()) {
                int ID = result.getInt(1);
                Blob b = result.getBlob(2);
                System.out.println("ID=" + ID + " read " + b.length() + " bytes");
            }
            pre.close();
        }
    }
}

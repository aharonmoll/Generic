import java.sql.*;

/**
 * Created by aharon on 3/22/15.
 */
public class JdbcClient {
    public static void main(String args[]) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        Connection conn;
        Class.forName("com.j_spaces.jdbc.driver.GDriver").newInstance();
        String url = "jdbc:gigaspaces:url:jini://*/*/mySpace?groups=aharon";
        conn = DriverManager.getConnection(url);
        Statement st = conn.createStatement();

        String createTable = "CREATE TABLE COFFEES (COF_NAME VARCHAR(32) INDEX,SUP_ID INTEGER INDEX, " +
                "PRICE FLOAT INDEX,SALES INTEGER INDEX,TOTAL LONG)";
        st.executeUpdate(createTable);

        st= conn.createStatement();
        String insertRows = "INSERT INTO COFFEES " +
                            "VALUES ('NessCoffee',16,17,18, 19)";
        st.execute(insertRows);


        String query = "SELECT COF_NAME, PRICE , TOTAL FROM COFFEES";
        st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String s = rs.getString("COF_NAME");
            float n = rs.getFloat("PRICE");
            long l = rs.getLong("TOTAL");
            System.out.println(s + "   " + n + "    "+l);
        }
    }
}

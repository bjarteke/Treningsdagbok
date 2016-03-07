import java.util.Properties;
import java.sql.*;

public abstract class connectDB {
    protected Connection conn;
    
    public connectDB () {
    }
   
    public void connect() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties p = new Properties();
            p.put("user", "bjarteke_bruker");
            p.put("password", "pass1234");           
            conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no/bjarteke_trening?autoReconnect=true&useSSL=false",p);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }
}


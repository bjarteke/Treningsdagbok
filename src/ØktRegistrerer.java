import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class ØktRegistrerer extends connectDB{
	
	private void registerØkt(String tidspunkt, String varighet, String kommentar, String prestasjon, String aktivitet){
		try {
			String s = "INSERT INTO økt(øktid,dato,varighet,kommentar,prestasjon,aktivitet) VALUES (4,'2016-07-03',0.5,'hdjkash',10, 'ute');";
			java.sql.PreparedStatement pstmt = conn.prepareStatement(s);
			pstmt.execute();
			
			/* pstmt.setString(1, "3");
			 pstmt.setString(2, tidspunkt);
			 pstmt.setString(3, varighet);
			 pstmt.setString(4, kommentar);
			 pstmt.setString(5, prestasjon);
			 pstmt.setString(6, aktivitet);*/
			 
        } catch (Exception e) {
            System.out.println("db error during insert"+e);
        }
	}
	
	public void registerUteØkt(String tidspunkt, String varighet, String kommentar, String prestasjon, String aktivitet, String temperatur, String vær){
		 try {
             Statement statement = conn.createStatement();

             registerØkt(tidspunkt, varighet, kommentar, prestasjon, aktivitet);             
             statement.executeUpdate("INSERT INTO utendørsaktivitet(øktid, aktivitet,temperatur,vær) VALUES (4,ute,skey,bra)");

         } catch (Exception e) {
             System.out.println("db error during insert"+e);
         }
		
	}
	
	public void registrerInneØkt(String dato, String varighet, String kommentar, String prestasjon, String aktivitet, String luftkvalitet, String tilskuere) {
		registerØkt(dato, varighet, kommentar, prestasjon,aktivitet);
	}
	
	public static String getNextØktId(Statement stmt) throws SQLException{
		String query = "select * from økt";
		ResultSet rs = stmt.executeQuery(query);
		rs.previous();		
        return rs.getString("øktid");	
	}
	
}


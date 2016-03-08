import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class ØktRegistrerer extends connectDB{
	
	private void registerØkt(String ID, String tidspunkt, String varighet, String kommentar, String prestasjon, String aktivitet){
		try {
			String s = "INSERT INTO økt(øktid,dato,varighet,kommentar,prestasjon,aktivitet) VALUES "
					+ "(" + ID + "," + addFnutts(tidspunkt) +","+ varighet +","+ addFnutts(kommentar) +","+ prestasjon +","+ addFnutts(aktivitet)+");";
			java.sql.PreparedStatement pstmt = conn.prepareStatement(s);
			pstmt.execute();
			 
        } catch (Exception e) {
            System.out.println("db error during insert"+e);
        }
	}
	
	private String addFnutts(String s){
		return "'" + s + "'";
	}
	
	public void registerUteØkt(String tidspunkt, String varighet, String kommentar, String prestasjon, String aktivitet, String temperatur, String vær){
		 try {
			 String newID = getNextØktId();
			 registerØkt(newID, tidspunkt, varighet, kommentar, prestasjon, aktivitet);             
			 
			 String s = "INSERT INTO utendørsaktivitet(øktid, aktivitet,temperatur,vær) VALUES"+ "(" + newID + "," + addFnutts(aktivitet) +","+ temperatur +","+ addFnutts(vær)+");";
			 java.sql.PreparedStatement pstmt = conn.prepareStatement(s);
			 pstmt.execute();

         } catch (Exception e) {
             System.out.println("db error during insert"+e);
         }
		
	}
	
	/*public void registrerInneØkt(String dato, String varighet, String kommentar, String prestasjon, String aktivitet, String luftkvalitet, String tilskuere) {
		registerØkt(dato, varighet, kommentar, prestasjon,aktivitet);
	}*/
	
	public String getNextØktId() throws SQLException{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) from økt");
		System.out.println(rs.next());
		return rs.getString(1);
        	
	}
	
}


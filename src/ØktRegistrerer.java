import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public void registerUteØkt(String tidspunkt, String varighet, String kommentar, String prestasjon, String temperatur, String vær){
		 try {
			 String newID = getNextØktId();
			 registerØkt(newID, tidspunkt, varighet, kommentar, prestasjon, "ute");             
			 
			 String s = "INSERT INTO utendørsaktivitet(øktid, aktivitet,temperatur,vær) VALUES"+ "(" + newID + "," + "'ute'" +","+ temperatur +","+ addFnutts(vær)+");";
			 java.sql.PreparedStatement pstmt = conn.prepareStatement(s);
			 pstmt.execute();

         } catch (Exception e) {
             System.out.println("db error during insert"+e);
         }
		
	}
	
	public void registerInneØkt(String tidspunkt, String varighet, String kommentar, String prestasjon, String luftkvalitet, String tilskuere){
		try {
			 String newID = getNextØktId();
			 registerØkt(newID, tidspunkt, varighet, kommentar, prestasjon, "inne");             
			 
			 String s = "INSERT INTO innendørsaktivitet(øktid, aktivitet,luftkvalitet,tilskuere) VALUES"+ "(" + newID + "," + "'inne'" +","+ addFnutts(luftkvalitet) +","+ tilskuere+");";
			 java.sql.PreparedStatement pstmt = conn.prepareStatement(s);
			 pstmt.execute();

        } catch (Exception e) {
            System.out.println("db error during insert"+e);
        }
	}
	
	public String getNextØktId() throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) from økt");
		rs.next();
		return rs.getString(1);      	
	}
	
	public int getNumberOfØvelser() throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) from øvelse");
		rs.next();
		return Integer.parseInt(rs.getString(1))-1;
	}
	
	
	public void connectØktToØvelse(int ØktId, int ØvelseId, String resultat) throws NumberFormatException, SQLException {
		if (ØktId < Integer.parseInt(getNextØktId()) && ØvelseId <= getNumberOfØvelser() ){
			try {   
				 String s = "INSERT INTO øktøvelse(øktid, øvelseid,resultat) VALUES"+ "(" + Integer.toString(ØktId) + "," + Integer.toString(ØvelseId) +","+ addFnutts(resultat) +");";
				 java.sql.PreparedStatement pstmt = conn.prepareStatement(s);
				 pstmt.execute();

	        } catch (Exception e) {
	            System.out.println("db error during insert"+e);
	        }
		}
		else {
			System.out.println("Can't connect the selected ØktId to the selected ØvelseId");
		}
	}
}


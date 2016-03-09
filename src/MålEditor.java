import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MålEditor extends connectDB{
	
	public void addMål (String øvelseid, String beskrivelse, String oppnådd){
		try {
			 String newID = getNextMålId();
	          
			 String s = "INSERT INTO innendørsaktivitet(målid, øvelseid, beskrivelse, oppnådd) VALUES"+ "(" + newID + "," + ØktRegistrerer.addFnutts(øvelseid) +","+ ØktRegistrerer.addFnutts(beskrivelse) +","+ ØktRegistrerer.addFnutts(oppnådd)+");";
			 java.sql.PreparedStatement pstmt = conn.prepareStatement(s);
			 pstmt.execute();

       } catch (Exception e) {
           System.out.println("db error during insert"+e);
       }
	}
	
	public boolean reachedRunningGoal (String øvelseid,String resultat) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * from mål WHERE øvelseid="+øvelseid+"AND oppnådd=false;");
		rs.next();
		String time = rs.getString("beskrivelse");
		String[] goalTimeSplit = time.split(":");
		String[] resultTimeSplit = resultat.split(":");
		
		for (int i = 0; i < resultTimeSplit.length; i++) {
			if (Integer.parseInt(resultTimeSplit[i]) < Integer.parseInt(goalTimeSplit[i])) {
				return true;
			}
			else if (Integer.parseInt(resultTimeSplit[i]) == Integer.parseInt(goalTimeSplit[i])){
				continue;
			}
			else {
				return false;
			}
		}
		return false;
		
	}
	
	public String getNextMålId () throws SQLException{
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) from mål");
		rs.next();
		return rs.getString(1);  
	}
	
	
}

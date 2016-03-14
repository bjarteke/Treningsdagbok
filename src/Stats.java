import java.sql.*;

public class Stats extends connectDB {
	
	public double getAntallKm() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("SELECT SUM(distanse) FROM kondisjonsøvelse;");
			results.next();
			double result = results.getFloat(1);
			return (result/1000);
		} catch (SQLException e) {
			e.printStackTrace();
		} return 0.0;
		
	}
	
	public String getBestResult(int øvelseID) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "SELECT MIN(resultat) FROM øktøvelse WHERE øvelseid=" + øvelseID +";";
		ResultSet results = stmt.executeQuery(query);
		results.next();
		String result = results.getString(1);
		
		return result;
	}
	
	public String getAverageResult(int øvelseID) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "SELECT AVG(resultat) FROM øktøvelse WHERE øvelseid=" + øvelseID +";";
		ResultSet results = stmt.executeQuery(query);
		results.next();
		String result = results.getString(1);
		
		return result;
	}	
	
}
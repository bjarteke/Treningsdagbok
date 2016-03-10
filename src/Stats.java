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
	
	public String getBestResult(String øvelse) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "SELECT MIN(resultat) FROM øvelse inner join øktøvelse on øktøvelse.øvelseid WHERE navn=" + øvelse +",";
		ResultSet results = stmt.executeQuery(query);
		results.next();
		String result = results.getString(1);
		
		return result;
	}

	
	public static void main(String[] args) {
		Stats stat = new Stats();
		try{
        	stat.connect();
        	
        }catch(RuntimeException exception){
        	System.out.println("Error. Not connected to database...");
        	return;
        }
		
		System.out.println(stat.getAntallKm());
		
		
	}
	
	
}
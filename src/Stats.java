import java.sql.*;

public class Stats extends connectDB {
	
	public double getAntallKm() {
		try {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("SELECT SUM(distanse) FROM kondisjonsøvelse;");
			double result = results.getFloat(1);
			return (result/1000);
		} catch (SQLException e) {
			e.printStackTrace();
		} return 0.0;
		
	}
	
	public static void main(String[] args) {
		Stats stat = new Stats();
		stat.getAntallKm();
	}
	
	
}
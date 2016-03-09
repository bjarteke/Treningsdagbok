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
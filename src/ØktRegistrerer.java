import java.sql.Statement;

public class ØktRegistrerer extends connectDB{
	
	public static int øktId = 1;
	
	public void registerØkt(String tidspunkt, String varighet, String kommentar, String prestasjon, String aktivitet){
		try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("INSERT INTO økt " + "VALUES (" + Integer.toString(øktId) + "," + tidspunkt + "," + varighet + "," + kommentar + "," + prestasjon+ "," + aktivitet+")");
        } catch (Exception e) {
            System.out.println("db error during insert");
        }
	}
	
	public void registerUteØkt(String tidspunkt, String varighet, String kommentar, String prestasjon, String aktivitet, String temperatur, String vær){
		 try {
             Statement statement = conn.createStatement();
             statement.executeUpdate("INSERT INTO utendørsaktivitet " + "VALUES (" + Integer.toString(øktId) + "," + "ute" + "," + temperatur + "," + vær + ")");
             registerØkt(tidspunkt, varighet, kommentar, prestasjon, aktivitet);
             øktId++;
         } catch (Exception e) {
             System.out.println("db error during insert");
         }
		
	}
	
	public void registrerInneØkt(String dato, String varighet, String kommentar, String prestasjon, String aktivitet, String luftkvalitet, String tilskuere) {
		registerØkt(dato, varighet, kommentar, prestasjon,aktivitet);
	}
	
}


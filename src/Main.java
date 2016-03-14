import java.util.Scanner;
import java.sql.SQLException;

public class Main extends connectDB {
	
	private static void inp(){
		System.out.print("-> ");
	}

    public static void main(String[] args) throws SQLException {        
        ØktRegistrerer su = new ØktRegistrerer();
        
        try{
        	su.connect();
        	
        }catch(RuntimeException exception){
        	System.out.println("Error. Not connected to database...");
        	return;
        }
               
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Hva vil du gjøre?");
        System.out.println();
        System.out.println("Opprette økt:\t\t\t'inneøkt' eller 'uteøkt'");
        System.out.println("Legge til øvelse:\t\t'øvelse'");
        System.out.println("Statistikk:\t\t\t'stat'");
        System.out.println("Lese logg:\t\t\t'logg'");
        System.out.println("Se alle økter:\t\t\t'se'");
        System.out.println("Beste resultat for øvelse: \t'bestØvelse'");
        System.out.println("Avslutte:\t\t\t'avslutt'");

        System.out.println();
        
        String input = "";
        while (!input.equals("avslutt")){
        		inp();
        		input = scanner.nextLine();
        		String beskrivelse;
        		String[] liste;
        		
        		switch(input.split(" ")[0]){
        		case "uteøkt":
        			System.out.println("Skriv på format: dato(yyyy-mm-dd), varighet, kommentar, prestasjon(1-10), temperatur, vær");
        			inp();
        			beskrivelse = scanner.nextLine();
        			liste = beskrivelse.split(", ");
        			su.registerUteØkt(liste[0], liste[1], liste[2], liste[3], liste[4], liste[5]);
        			break;
        			
        		case "inneøkt":
        			System.out.println("Skriv på format: dato(yyyy-mm-dd), varighet, kommentar, prestasjon(1-10), luftkvalitet, tilskuere");
        			inp();
        			beskrivelse = scanner.nextLine();
        			liste = beskrivelse.split(", ");
        			su.registerInneØkt(liste[0], liste[1], liste[2], liste[3], liste[4], liste[5]);
        			break;
        			
        		case "øvelse":
        			System.out.println("ØvelsesIDer:");
        			int i = 1;
        			for (String øvelse : su.getØvelser()){
        				
        				System.out.println(i + ". " + øvelse);
        				i++;
        			}
        			System.out.println();
        			System.out.println("Skriv på format: øvelsesID, øktID, resultat");

        			inp();
        			beskrivelse = scanner.nextLine();
        			liste = beskrivelse.split(", ");
        			su.connectØktToØvelse(Integer.parseInt(liste[1]), Integer.parseInt(liste[0]), liste[2]);
        			break;
        			
        			
        		case "stat":
        			Stats stat = new Stats();
        			stat.connect();
        			System.out.println("Totalt antall km: " + stat.getAntallKm());
        			System.out.println();
        			System.out.println("Øvelse        Beste resultat    Gjennomsnittlig resultat");
        			System.out.println("--------------------------------------------------------");
        			for (int j = 1; j <= su.getNumberOfØvelser(); j++ ){
        				
        				System.out.format("%-14s%-18s%-16s", su.getØvelseFromID(j), stat.getBestResult(j), stat.getAverageResult(j));
        				System.out.println();

        			}
        			break;

        		case "se":
        			if (input.split(" ").length == 1){
        				su.printØkter();
        				break;        				
        			}else{
        				int øktID = Integer.parseInt(input.split(" ")[1]);
        				su.printØvelserForØkt(øktID);
        				break;
        			}
        			
        		case "bestØvelse":
        			System.out.println("ØvelseID: \tNavn:");
        			int j = 1;
					for (String øvelse : su.getØvelser()){
        				System.out.println(j + ". \t\t" + øvelse);
        				j++;
        			}
					System.out.println("Skriv inn ønsket ØvelseID");
        			inp();
        			while(true){
        				String øvelseID = scanner.nextLine();        				
        				if(Integer.parseInt(øvelseID)<=su.getNumberOfØvelser()){
        					String øvelse = su.getØvelseFromID(Integer.parseInt(øvelseID));
        					System.out.println("Beste resultat på " + øvelse +": " + su.getBestResultFromØvelseID(Integer.parseInt(øvelseID)));
        					break;
        				}
        				else {
        					System.out.println("Ingen øvelse med angitt øvelseID. Prøv igjen...");
        					inp();
        				}
        			
        			}
        			break;
        			
        		case "avslutt":
        			break;
        		
        		default:
        			System.out.println("Ugyldig kommando. Prøv igjen...");
        		}
        		
        }
        scanner.close();
    }

}
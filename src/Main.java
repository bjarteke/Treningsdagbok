import java.util.Scanner;

public class Main {

    public static void main(String[] args) {        
        ØktRegistrerer su = new ØktRegistrerer();
        
        
        su.connect();
  
        
        Scanner scanner = new Scanner(System.in);
        //Alternativer til til å gjøre, går i loop.
        //Legg til økt --> koble økt til øvelse(r)
        	//Når du har lagt til resultat, sjekk om dette er oppnådd
        //Se ti beste resultater fra gitt øvelse
        //Se antall kilometer løpt
        
        
        
        
        su.registerUteØkt("2016-03-07", "0.5", "Bra økt", "5", "akt", "24", "Skyet");
    }

}
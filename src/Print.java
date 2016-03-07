import java.sql.*;

public class Print extends connectDB {
    
    public void printTreningsøkt () {

       try {
           Statement stmt = conn.createStatement();
           String query = "select * from økt";
           
           ResultSet rs = stmt.executeQuery(query);
           rs.next();
           
           while(rs.next()){        	   
    		   System.out.println(String.format("Dato: %s \nKommentar: %s \nVarighet: %s",rs.getString("dato"),rs.getString("kommentar"),rs.getString("varighet")));
    		   System.out.println("");        		   
           }
       }
            
     	catch (Exception e) {
            System.out.println("db error during select of loper = "+e);
    	}

    }
    
    public static void main(String[] args) {
		Print p = new Print();
		p.printTreningsøkt();
	}
  
}

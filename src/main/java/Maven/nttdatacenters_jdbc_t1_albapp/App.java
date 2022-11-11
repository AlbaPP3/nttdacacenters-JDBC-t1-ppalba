package Maven.nttdatacenters_jdbc_t1_albapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Main con metodo de conexion a BBDD
 *
 *@author Alba
 */
public class App {
	
	
    public static void main( String[] args ) throws SQLException {
    	
    	stablishMDBConnection();
    
}
    
    private static void stablishMDBConnection() throws SQLException {
    	Connection dbConnection = null;
		Statement sentence = null;
		ResultSet queryResult = null;
		StringBuilder playerInfo = new StringBuilder();
		
		
		
		try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nttdata_jdbc_ex", "root", "manolo");
			
			sentence = dbConnection.createStatement();
			

			/*Realización de la consulta.*/
			
			final String query = "SELECT sp.name AS playerName, st.name AS teamName, sp.first_rol AS rol1, sp.second_rol AS rol2, sp.birth_date AS birthD FROM nttdata_mysql_soccer_player sp JOIN nttdata_mysql_soccer_team st ON sp.id_soccer_team = st.id_soccer_team";
			queryResult = sentence.executeQuery(query);
			
			/*Recorrido y busqueda de resultados.*/
			
			while (queryResult.next()) {

				playerInfo.append("Nombre: ");
				playerInfo.append(queryResult.getString("playerName"));

				playerInfo.append(" | Equipo: ");
				playerInfo.append(queryResult.getString("teamName"));

				playerInfo.append(" | Demarcación: ");
				playerInfo.append(queryResult.getString("rol1"));

				playerInfo.append(" | Demarcación alternativa: ");
				playerInfo.append(queryResult.getString("rol2"));

				playerInfo.append(" | Fecha nacimiento: ");
				playerInfo.append(queryResult.getDate("birthD"));

				playerInfo.append("\n");
			}

			System.out.println(playerInfo.toString());

		}finally {
			
				/*Cierre BBDD*/
				
				dbConnection.close();
		}
    	
    	
    }
    

 }




	


    	
    	
       
    


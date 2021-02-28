package model3;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudCajas extends ConexionBD{
	
	//METODO QUE CREA TABLAS MYSQL
		public String createTable(String nomBD) {
			
			String mensaje="";
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE Cajas"
						+ "(numReferencia CHAR(5) PRIMARY KEY,"
						+ "contenido VARCHAR(100),"
						+ "valor INT,"
						+ "almacen_FK INT,"
						+"\nFOREIGN KEY (almacen_FK)"+
						"\nREFERENCES Almacenes(codigo)"+
						"\nON DELETE CASCADE"+
						"\nON UPDATE CASCADE) Engine=InnoDB;";
				
				Statement st= getConexion().createStatement();
				st.executeUpdate(Query);
				
				mensaje="Tabla 'Cajas' creada con exito!";
				
			}catch (SQLException ex) {
				mensaje="Error crenado tabla 'Cajas' "+ex.getMessage();
			}
			return mensaje;
		}
		
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public String insertData(String nomBD,String numReferencia, String contenido,int valor,int almacen_fk) {
			String mensaje="";
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO Cajas VALUE(" 
						+ "\"" +numReferencia+ "\","
						+ "\"" +contenido+ "\","
						+"\""+valor+"\","
						+"\""+almacen_fk+"\");"; 
				
				Statement st =getConexion().createStatement();
				st.executeUpdate(Query);
				
				mensaje="Datos almacenados correctamente";
				
			} catch (SQLException ex ) {
				
				mensaje="Error en el almacenamiento "+ex.getMessage();
			}
			
			return mensaje;
						
		}
		
		//METODO QUE OBTIENE VALORES MYSQL
		public  String getValues(String nomBD) {
			String mensaje="";
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "SELECT * FROM Cajas";
				Statement st = getConexion().createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					mensaje+="\nNº Referencia: " +  resultSet.getString("numReferencia")
							+"\nContenido: " +  resultSet.getString("contenido")
							+"\nValor: "+resultSet.getString("valor")
							+"\nCódigo Almacen: "+resultSet.getString("almacen_FK");
				}
			} catch (SQLException ex) {
				
				mensaje="Error en la adquisicion de datos  "+ex.getMessage();
			}
			
			return mensaje;
		
		}
		 
		//METODO QUE LIMPIA TABLAS MYSQL
		public  String deleteRecord(String nomBD,String codigo) {
			String mensaje="";
			
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "DELETE FROM Cajas WHERE numReferencia = \"" + codigo + "\"";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);
				
				mensaje="Registros de tabla ELIMINADOS con exito!";
							
			} catch (SQLException ex) {
				mensaje="Error borrando el registro especificado "+ex.getMessage();
			}
			
			return mensaje;
		}	

		
		//METODO QUE ELIMINA TABLAS MYSQL
		public String deleteTabla(String nomBD) {
			
			String mensaje="";
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "DROP TABLE Cajas;";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);
				
				mensaje="TABLA 'Cajas' ELIMINADA con exito!";
							
			} catch (SQLException ex) {
				mensaje="Error borrando la tabla";
			}
			return mensaje;
		}	
}

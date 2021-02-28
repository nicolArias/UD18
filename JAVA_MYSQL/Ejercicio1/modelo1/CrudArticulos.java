package modelo1;



import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import clase.ConexionBD;

/*Autor: Nicol Dayana Arias Lebro
 * 
 * Fecha: 25/02/2021*/
public class CrudArticulos extends ConexionBD {
	
	
	
	
	//METODO QUE CREA TABLAS MYSQL
		public String createTable(String nomBD) {
			
			String mensaje="";
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE Articulos"
						+ "(codigo INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(100),"+
						"precio INT, fabricante_FK INT,"+
						"\nFOREIGN KEY (fabricante_FK)"+
						"\nREFERENCES Fabricantes(codigo)"+
						"\nON DELETE CASCADE"+
						"\nON UPDATE CASCADE) Engine=InnoDB;";
				Statement st= getConexion().createStatement();
				st.executeUpdate(Query);
				mensaje="Tabla 'Articulos' creada con exito!";
				
			}catch (SQLException ex){
				
				mensaje="Error crenado tabla 'Articulos' "+ex.getMessage();
				
			}
			return mensaje;
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public String insertData(String nomBD,String nombre, int precio,int idFabricante) {
			String mensaje="";
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO Articulos (nombre,precio,fabricante_FK) VALUE(" 
						+ "\"" + nombre + "\","
						+"\""+precio+"\","
						+"\""+idFabricante+"\");"; 
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
							
				String Query = "SELECT * FROM Articulos";
				Statement st = getConexion().createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					mensaje+="\nCodigo: " +  resultSet.getString("codigo")
							+"\nNombre: " +  resultSet.getString("nombre");
				}
			} catch (SQLException ex) {
				
				mensaje="Error en la adquisicion de datos  "+ex.getMessage();
			}
			
			return mensaje;
		
		}
		 
		//METODO QUE LIMPIA TABLAS MYSQL
		public  String deleteRecord(String nomBD,int codigo) {
			String mensaje="";
			
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "DELETE FROM Articulos WHERE codigo = \"" + codigo + "\"";
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
							
				String Query = "DROP TABLE Articulos;";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);
				
				mensaje="TABLA 'Articulos' ELIMINADA con exito!";
							
			} catch (SQLException ex) {
				mensaje="Error borrando la tabla";
			}
			return mensaje;
		}	
}

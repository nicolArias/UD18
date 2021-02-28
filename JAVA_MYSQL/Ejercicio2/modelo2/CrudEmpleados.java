package modelo2;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudEmpleados extends ConexionBD {

	
	
	//METODO QUE CREA TABLAS MYSQL
		public String createTable(String nomBD) {
			
			String mensaje="";
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
				
				String Query = "CREATE TABLE Empleados"
						+ "(DNI VARCHAR(8) PRIMARY KEY,"
						+ " nombre VARCHAR(100),"
						+"apellidos VARCHAR(100),"
						+ "departamento_FK INT,"+
						"\nFOREIGN KEY (departamento_FK)"+
						"\nREFERENCES Departamentos(codigo)"+
						"\nON DELETE CASCADE"+
						"\nON UPDATE CASCADE) Engine=InnoDB;";
				Statement st= getConexion().createStatement();
				st.executeUpdate(Query);
				mensaje="Tabla 'Empleados' creada con exito!";
				
			}catch (SQLException ex){
				
				mensaje="Error creando tabla 'Empleados' "+ex.getMessage();
				
			}
			return mensaje;
		}
		
		//METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public String insertData(String nomBD,String DNI,String nombre,String apellidos,int dep_fk) {
			String mensaje="";
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "INSERT INTO Empleados VALUE(" 
						+ "\"" + DNI + "\","
						+ "\"" + nombre + "\","
						+"\""+apellidos+"\","
						+"\""+dep_fk+"\");"; 
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
							
				String Query = "SELECT * FROM Empleados";
				Statement st = getConexion().createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);
				
				while (resultSet.next()) {
					mensaje+="\nDNI: " +  resultSet.getString("DNI")
							+"\nNombre: " +  resultSet.getString("nombre")
							+"\nApellidos: "+resultSet.getString("apellidos"
							+"\nCodigo Departamento: "+resultSet.getString("departamento_FK"));
				}
			} catch (SQLException ex) {
				
				mensaje="Error en la adquisicion de datos  "+ex.getMessage();
			}
			
			return mensaje;
		
		}
		 
		//METODO QUE LIMPIA TABLAS MYSQL
		public  String deleteRecord(String nomBD,String DNI) {
			String mensaje="";
			
			try {
				String Querydb = "USE "+nomBD+";";
				Statement stdb= getConexion().createStatement();
				stdb.executeUpdate(Querydb);
							
				String Query = "DELETE FROM Empleados WHERE DNI = \"" + DNI + "\"";
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
							
				String Query = "DROP TABLE Empleados;";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);
				
				mensaje="TABLA 'Empleados' ELIMINADA con exito!";
							
			} catch (SQLException ex) {
				mensaje="Error borrando la tabla 'Empleados' "+ex.getMessage();
			}
			return mensaje;
		}	
}

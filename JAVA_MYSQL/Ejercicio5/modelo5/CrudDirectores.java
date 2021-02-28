package modelo5;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudDirectores extends ConexionBD{

	//METODO QUE CREA TABLAS MYSQL
		public String createTable(String nomBD) {
			String mensaje = "";

			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "CREATE TABLE Directores" 
						+ "(DNI VARCHAR(8) PRIMARY KEY,"
						+ "nomApels VARCHAR(255),"
						+ "DNIJefe_FK VARCHAR(8),"
						+ "despacho_FK INT,"
						+ "FOREIGN KEY (DNIJefe_FK)"
						+ "REFERENCES Directores(DNI)"
						+ "ON DELET CASCADE"
						+ "ON UPDATE CASCADE,"
						+ "FOREIGN KEY (despacho_FK)"
						+ "REFERENCES Despachos(numero)"
						+ "ON DELET CASCADE"
						+ "ON UPDATE CASCADE)Engine=InnoDB;";
				
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Tabla 'Directores' creada con exito";

			} catch (SQLException ex) {
				mensaje = "Error creando tabla 'Directores'";
			}
			return mensaje;
		}

		// METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public String insertData(String nomBD, String DNI, String nomApels,String DNI_FK,int despacho_FK) {

			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "INSERT INTO Directores VALUE("
								+ "\"" + DNI + "\"," 
								+ "\"" + nomApels + "\","
								+ "\"" + DNI_FK + "\","
								+ "\"" +despacho_FK+ "\"); ";
				
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Datos almacenados correctamente";

			} catch (SQLException ex) {
				mensaje = "Error en el almacenamiento \n Exception: " + ex;

			}

			return mensaje;

		}

		// METODO QUE OBTIENE VALORES MYSQL
		public String getValues(String nomBD) {
			String consulta = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "SELECT * FROM Directores";
				Statement st = getConexion().createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);

				while (resultSet.next()) {

					consulta += "\nDNI: " + resultSet.getString("DNI")
								+"\nNombre y Apellidos: "+resultSet.getString("nomApels")
								+"\nDNI Jefe: "+resultSet.getString("DNIJefe_FK")
								+"\nNº Despacho: "+resultSet.getString("despacho_FK");
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
			return consulta;
		}

		// METODO QUE LIMPIA TABLAS MYSQL
		public String deleteRecord(String nomBD, String DNI) {
			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "DELETE FROM Directores WHERE DNI = \"" + DNI + "\"";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Registro de tabla 'Directores' ELIMINADO con exito!";

			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				mensaje = "Error borrando el registro especificado  " + ex.getMessage();
			}
			return mensaje;
		}

		// METODO QUE ELIMINA TABLAS MYSQL
		public String deleteTabla(String nomBD) {
			String mensaje = "";

			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "DROP TABLE Directores;";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "TABLA 'Directores' ELIMINADA con exito!";

			} catch (SQLException ex) {

				mensaje = "Error borrando la tabla " + ex.getMessage();
			}
			return mensaje;
		}
}

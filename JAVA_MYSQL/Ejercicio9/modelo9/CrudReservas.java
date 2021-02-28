package modelo9;

import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import clase.ConexionBD;

public class CrudReservas extends ConexionBD{
	//METODO QUE CREA TABLAS MYSQL
		public String createTable(String nomBD) {
			String mensaje = "";

			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "CREATE TABLE Reservas" 
						+ "(DNI VARCHAR(8),"
						+ "numSerie CHAR(4),"
						+ "comienzo DATETIME,"
						+ "fin DATETIME,"
						+ "PRIMARY KEY(DNI,numSerie),"
						+ "FOREIGN KEY (DNI),"
						+ "REFERENCES Investigadores(DNI)"
						+ "ON DELETE CASCADE"
						+ "ON UPDATE CASCADE,"
						+ "FOREIGN KEY (numSerie),"
						+ "REFERENCES Equipos"
						+ "ON DELETE CASCADE"
						+ "ON UPDATE CASCADE) Engine=InnoDB;";
				
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Tabla 'Reservas' creada con exito";

			} catch (SQLException ex) {
				mensaje = "Error creando tabla 'Reservas'";
			}
			return mensaje;
		}

		// METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public String insertData(String nomBD,String DNI,String numSerie,LocalDateTime comienzo,LocalDateTime fin) {

			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "INSERT INTO Reservas VALUE("
								+ "\"" +DNI+ "\","
								+ "\""+numSerie+"\","
								+ "\""+comienzo+"\""
								+ "\""+fin+"\"); ";
				
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

				String Query = "SELECT * FROM Reservas";
				Statement st = getConexion().createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);

				while (resultSet.next()) {

					consulta += "\nDNI: " + resultSet.getString("DNI")
								+"\nNombre y Apellidos: "+resultSet.getString("nomApels")
								+"\nFacultad: "+resultSet.getString("facultad_fk");
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
			return consulta;
		}

		// METODO QUE LIMPIA TABLAS MYSQL
		public String deleteRecordxDNI(String nomBD, String DNI) {
			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "DELETE FROM Reservas WHERE DNI = \"" + DNI + "\"";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Registro de tabla 'Investigadores' ELIMINADO con exito!";

			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				mensaje = "Error borrando el registro especificado  " + ex.getMessage();
			}
			return mensaje;
		}
		
		public String deleteRecordxNumSerie(String nomBD, String numSerie) {
			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "DELETE FROM Reservas WHERE numSerie = \"" + numSerie + "\"";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Registro de tabla 'Reservas' ELIMINADO con exito!";

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

				String Query = "DROP TABLE Reservas;";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "TABLA 'Reservas ELIMINADA con exito!";

			} catch (SQLException ex) {

				mensaje = "Error borrando la tabla " + ex.getMessage();
			}
			return mensaje;
		}
}

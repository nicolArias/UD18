package modelo7;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 25/02/2021*/
public class CrudCientificos extends ConexionBD {

	//METODO QUE CREA TABLAS MYSQL
		public String createTable(String nomBD) {
			String mensaje = "";

			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "CREATE TABLE Cientificos" 
						+ "(DNI VARCHAR(8) PRIMARY KEY,"
						+ "nomApels VARCHAR(255) Engine=InnoDB;";
				
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Tabla 'Cientificos' creada con exito";

			} catch (SQLException ex) {
				mensaje = "Error creando tabla 'Cientificos'";
			}
			return mensaje;
		}

		// METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public String insertData(String nomBD,String DNI,String nombre) {

			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "INSERT INTO Cientificos VALUE("
								+ "\"" +DNI+ "\","
								+ "\"" +nombre+ "\"); ";
				
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

				String Query = "SELECT * FROM Cientificos";
				Statement st = getConexion().createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);

				while (resultSet.next()) {

					consulta += "\nDNI: " + resultSet.getString("DNI")
								+"\nNombre y Apellidos: "+resultSet.getString("nomApels");
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

				String Query = "DELETE FROM Cientificos WHERE DNI = \"" + DNI + "\"";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Registro de tabla 'Cientificos' ELIMINADO con exito!";

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

				String Query = "DROP TABLE Cientificos;";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "TABLA 'Cientificos' ELIMINADA con exito!";

			} catch (SQLException ex) {

				mensaje = "Error borrando la tabla " + ex.getMessage();
			}
			return mensaje;
		}
}

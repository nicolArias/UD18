package modelo5;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 25/02/2021*/
public class CrudDespachos extends ConexionBD {

	//METODO QUE CREA TABLAS MYSQL
		public String createTable(String nomBD) {
			String mensaje = "";

			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "CREATE TABLE Despachos" 
						+ "(numero INT PRIMARY KEY,"
						+ "capacidad INT) Engine=InnoDB;";
				
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Tabla 'Despachos' creada con exito";

			} catch (SQLException ex) {
				mensaje = "Error creando tabla 'Despachos'";
			}
			return mensaje;
		}

		// METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public String insertData(String nomBD,int numero, int capacidad) {

			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "INSERT INTO Despachos VALUE("
								+ "\"" + numero + "\"," 
								+ "\"" + capacidad+ "\"); ";
				
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

				String Query = "SELECT * FROM Despachos";
				Statement st = getConexion().createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);

				while (resultSet.next()) {

					consulta += "\nNúmero: " + resultSet.getString("numero")
								+"\nCapacidad: "+resultSet.getString("capacidad");
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
			return consulta;
		}

		// METODO QUE LIMPIA TABLAS MYSQL
		public String deleteRecord(String nomBD, int numero) {
			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "DELETE FROM Despachos WHERE numero = \"" + numero + "\"";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Registro de tabla 'Despachos' ELIMINADO con exito!";

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

				String Query = "DROP TABLE Despachos;";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "TABLA 'Despachos' ELIMINADA con exito!";

			} catch (SQLException ex) {

				mensaje = "Error borrando la tabla " + ex.getMessage();
			}
			return mensaje;
		}
}

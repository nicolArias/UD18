package modelo6;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudProveedores extends ConexionBD{

	//METODO QUE CREA TABLAS MYSQL
		public String createTable(String nomBD) {
			String mensaje = "";

			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "CREATE TABLE Proveedores" 
						+ "(id VARCHAR(4) PRIMARY KEY,"
						+ "nombre VARCHAR(100))Engine=InnoDB;";
				
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Tabla 'Proveedores' creada con exito";

			} catch (SQLException ex) {
				mensaje = "Error creando tabla 'Directores'";
			}
			return mensaje;
		}

		// METODO QUE INSERTA DATOS EN TABLAS MYSQL
		public String insertData(String nomBD, String id, String nombre) {

			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "INSERT INTO Directores VALUE("
								+ "\"" + id + "\"," 
								+ "\"" + nombre + "\"); ";
				
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

				String Query = "SELECT * FROM Proveedores";
				Statement st = getConexion().createStatement();
				java.sql.ResultSet resultSet;
				resultSet = st.executeQuery(Query);

				while (resultSet.next()) {

					consulta += "\nId: " + resultSet.getString("id")
								+"\nNombre: "+resultSet.getString("nombre");
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error en la adquisicion de datos");
			}
			return consulta;
		}

		// METODO QUE LIMPIA TABLAS MYSQL
		public String deleteRecord(String nomBD, String id) {
			String mensaje = "";
			try {
				String Querydb = "USE " + nomBD + ";";
				Statement stdb = getConexion().createStatement();
				stdb.executeUpdate(Querydb);

				String Query = "DELETE FROM Proveedores WHERE id = \"" + id + "\"";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "Registro de tabla 'Proveedores' ELIMINADO con exito!";

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

				String Query = "DROP TABLE Proveedores;";
				Statement st = getConexion().createStatement();
				st.executeUpdate(Query);

				mensaje = "TABLA 'Proveedores' ELIMINADA con exito!";

			} catch (SQLException ex) {

				mensaje = "Error borrando la tabla " + ex.getMessage();
			}
			return mensaje;
		}
}

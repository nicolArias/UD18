package modelo1;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

/*Autor: Nicol Dayana Arias Lebro
 * 
 * Fecha: 25/02/2021*/
public class CrudFabricantes extends ConexionBD {

	// METODO QUE CREA TABLAS MYSQL
	public String createTable(String nomBD) {
		String mensaje = "";

		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE Fabricantes"
					+ "(codigo INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(100))Engine=InnoDB;";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "Tabla 'Fabricantes' creada con exito";

		} catch (SQLException ex) {
			System.out.println(ex.getMessage() + ex);
			System.out.println("Clase fabricante: " + getConexion());
			// System.out.println("Error crando tabla.");

			mensaje = "Error creando tabla 'Fabricantes'";
		}
		return mensaje;
	}

	// METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public String insertData(String nomBD, String nombre) {

		String mensaje = "";
		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO Fabricantes (nombre) VALUE(" + "\"" + nombre + "\"); ";
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

			String Query = "SELECT * FROM Fabricantes";
			Statement st = getConexion().createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {

				consulta += "\nID: " + resultSet.getString("codigo") + " " + "\nNombre: "
						+ resultSet.getString("nombre") + " \n";
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			System.out.println("Error en la adquisicion de datos");
		}

		return consulta;

	}

	// METODO QUE LIMPIA TABLAS MYSQL
	public String deleteRecord(String nomBD, int codigo) {
		String mensaje = "";
		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "DELETE FROM Fabricantes WHERE codigo = \"" + codigo + "\"";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "Registros de tabla 'Fabricantes' ELIMINADOS con exito!";

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

			String Query = "DROP TABLE Fabricantes;";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "TABLA 'Fabricantes' ELIMINADA con exito!";

		} catch (SQLException ex) {

			mensaje = "Error borrando la tabla " + ex.getMessage();
		}
		return mensaje;
	}

}

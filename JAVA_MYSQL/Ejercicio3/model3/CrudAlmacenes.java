package model3;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudAlmacenes extends ConexionBD{
	
	//METODO QUE CREA TABLAS MYSQL
	public String createTable(String nomBD) {
		String mensaje = "";

		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE Almacenes" + "(codigo INT PRIMARY KEY AUTO_INCREMENT," + "lugar VARCHAR(100),"
					+ "capacidad INT)Engine=InnoDB;";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "Tabla 'Almacenes' creada con exito";

		} catch (SQLException ex) {
			mensaje = "Error creando tabla 'Almacenes'";
		}
		return mensaje;
	}

	// METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public String insertData(String nomBD, String lugar, int capacidad) {

		String mensaje = "";
		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO Almacenes (lugar,capacidad) VALUE("+"\"" + lugar + "\"," + "\"" + capacidad+"\");";
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

			String Query = "SELECT * FROM Almacenes";
			Statement st = getConexion().createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {

				consulta += "\nCódigo: " + resultSet.getString("codigo")
							+"\nNombre: "+resultSet.getString("lugar")
							+"\nCapacidad: "+resultSet.getString("capacidad");
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

			String Query = "DELETE FROM Almacenes WHERE codigo = \"" + codigo + "\"";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "Registros de tabla 'Almacenes' ELIMINADOS con exito!";

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

			String Query = "DROP TABLE Almacenes;";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "TABLA 'Almacenes' ELIMINADA con exito!";

		} catch (SQLException ex) {

			mensaje = "Error borrando la tabla " + ex.getMessage();
		}
		return mensaje;
	}

}

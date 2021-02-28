package modelo9;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudInvestigadores extends ConexionBD{
	//METODO QUE CREA TABLAS MYSQL
	public String createTable(String nomBD) {
		String mensaje = "";

		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE Investigadores" 
					+ "(DNI VARCHAR(8) PRIMARY KEY,"
					+ "nomApels VARCHAR(255),"
					+ "facultad_fk INT,"
					+ "FOREIGN KEY (facultad),"
					+ "REFERENCES Facultades(codigo)"
					+ "ON DELETE CASCADE"
					+ "ON UPDATE CASCADE) Engine=InnoDB;";
			
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "Tabla 'Investigadores' creada con exito";

		} catch (SQLException ex) {
			mensaje = "Error creando tabla 'Investigadores'";
		}
		return mensaje;
	}

	// METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public String insertData(String nomBD,String DNI,String nomApels,int codFacultad_fk) {

		String mensaje = "";
		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO Investigadores VALUE("
							+ "\"" +DNI+ "\","
							+ "\""+nomApels+"\","
							+ "\""+codFacultad_fk+"\"); ";
			
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

			String Query = "SELECT * FROM Investigadores";
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
	public String deleteRecord(String nomBD, String DNI) {
		String mensaje = "";
		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "DELETE FROM Investigadores WHERE DNI = \"" + DNI + "\"";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "Registro de tabla 'Investigadores' ELIMINADO con exito!";

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

			String Query = "DROP TABLE Investigadores;";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "TABLA 'Investigadores' ELIMINADA con exito!";

		} catch (SQLException ex) {

			mensaje = "Error borrando la tabla " + ex.getMessage();
		}
		return mensaje;
	}
}

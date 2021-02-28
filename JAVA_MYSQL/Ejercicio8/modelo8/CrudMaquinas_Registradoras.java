package modelo8;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudMaquinas_Registradoras extends ConexionBD {
	//METODO QUE CREA TABLAS MYSQL
	public String createTable(String nomBD) {
		String mensaje = "";

		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "CREATE TABLE Maquinas_Registradoras" 
					+ "(codigo INT PRIMARY KEY AUTO_INCREMENT,"
					+ "piso INT) Engine=InnoDB;";
			
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "Tabla 'Maquinas_Registradoras' creada con exito";

		} catch (SQLException ex) {
			mensaje = "Error creando tabla 'Maquinas_Registradoras'";
		}
		return mensaje;
	}

	// METODO QUE INSERTA DATOS EN TABLAS MYSQL
	public String insertData(String nomBD,int piso) {

		String mensaje = "";
		try {
			String Querydb = "USE " + nomBD + ";";
			Statement stdb = getConexion().createStatement();
			stdb.executeUpdate(Querydb);

			String Query = "INSERT INTO Cientificos VALUE("
							+ "\"" +piso+ "\"); ";
			
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

			String Query = "SELECT * FROM Maquinas_Registradoras";
			Statement st = getConexion().createStatement();
			java.sql.ResultSet resultSet;
			resultSet = st.executeQuery(Query);

			while (resultSet.next()) {

				consulta += "\nCodigo: " + resultSet.getString("codigo")
							+"\nPiso: "+resultSet.getString("piso");
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

			String Query = "DELETE FROM Maquinas_Registradoras WHERE codigo = \"" + codigo + "\"";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "Registro de tabla 'Maquinas_Registradoras' ELIMINADO con exito!";

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

			String Query = "DROP TABLE Maquinas_Registradoras;";
			Statement st = getConexion().createStatement();
			st.executeUpdate(Query);

			mensaje = "TABLA 'Maquinas_Registradoras' ELIMINADA con exito!";

		} catch (SQLException ex) {

			mensaje = "Error borrando la tabla " + ex.getMessage();
		}
		return mensaje;
	}
}

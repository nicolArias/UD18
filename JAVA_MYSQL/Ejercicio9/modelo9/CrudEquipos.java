package modelo9;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudEquipos extends ConexionBD{
	//METODO QUE CREA TABLAS MYSQL
			public String createTable(String nomBD) {
				String mensaje = "";

				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "CREATE TABLE Equipos" 
							+ "(numSerie CHAR(4) PRIMARY KEY,"
							+ "nombre VARCHAR(100),"
							+ "facultad_fk INT,"
							+ "FOREIGN KEY (facultad),"
							+ "REFERENCES Facultades(codigo)"
							+ "ON DELETE CASCADE"
							+ "ON UPDATE CASCADE) Engine=InnoDB;";
					
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Tabla 'Equipos' creada con exito";

				} catch (SQLException ex) {
					mensaje = "Error creando tabla 'Equipos'";
				}
				return mensaje;
			}

			// METODO QUE INSERTA DATOS EN TABLAS MYSQL
			public String insertData(String nomBD,String numSerie,String nombre,int codFacultad_fk) {

				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "INSERT INTO Equipos VALUE("
									+ "\"" +numSerie+ "\","
									+ "\""+nombre+"\","
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

					String Query = "SELECT * FROM Equipos";
					Statement st = getConexion().createStatement();
					java.sql.ResultSet resultSet;
					resultSet = st.executeQuery(Query);

					while (resultSet.next()) {

						consulta += "\nNº Serie: " + resultSet.getString("numSerie")
									+"\nNombre: "+resultSet.getString("nombre")
									+"\nFacultad: "+resultSet.getString("facultad_fk");
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					System.out.println("Error en la adquisicion de datos");
				}
				return consulta;
			}

			// METODO QUE LIMPIA TABLAS MYSQL
			public String deleteRecord(String nomBD, String nSerie) {
				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "DELETE FROM Equipos WHERE numSerie = \"" + nSerie + "\"";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Registro de tabla 'Equipos' ELIMINADO con exito!";

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

					String Query = "DROP TABLE Equipos;";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "TABLA 'Equipos' ELIMINADA con exito!";

				} catch (SQLException ex) {

					mensaje = "Error borrando la tabla " + ex.getMessage();
				}
				return mensaje;
			}
}

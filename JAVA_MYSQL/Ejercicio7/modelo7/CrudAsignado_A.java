package modelo7;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudAsignado_A extends ConexionBD{
	
	
	//METODO QUE CREA TABLAS MYSQL
			public String createTable(String nomBD) {
				String mensaje = "";

				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "CREATE TABLE Asignado_A" 
							+ "(codCientifico VARCHAR(8),"
							+ "idProyecto VARCHAR(4),"
							+ "PRIMARY KEY(codCientifico,idProyecto),"
							+ "FOREIGN KEY (codCientifico)"
							+ "REFERENCES Cientificos(ID)"
							+ "ON DELETE CASCADE"
							+ "ON UPDATE CASCADE,"
							+ "FOREIGN KEY (idProyecto)"
							+ "REFERENCES Proyectos(id)"
							+ "ON DELETE CASCADE"
							+ "ON UPDATE CASCADE)Engine=InnoDB;";
					
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Tabla creada con exito";

				} catch (SQLException ex) {
					mensaje = "Error creando tabla ";
				}
				return mensaje;
			}

			// METODO QUE INSERTA DATOS EN TABLAS MYSQL
			public String insertData(String nomBD, String DNI_FK, String idProyecto_FK) {

				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "INSERT INTO Asignado_A VALUE("
									+ "\"" + DNI_FK + "\"," 
									+ "\"" + idProyecto_FK + "\"); ";
					
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

					String Query = "SELECT * FROM Asignado_A";
					Statement st = getConexion().createStatement();
					java.sql.ResultSet resultSet;
					resultSet = st.executeQuery(Query);

					while (resultSet.next()) {

						consulta += "\nCodigo Cientifico: " + resultSet.getString("codCientifico")
									+"\nId Proyecto: "+resultSet.getString("idProyecto");
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					System.out.println("Error en la adquisicion de datos");
				}
				return consulta;
			}

			// METODO QUE LIMPIA TABLAS MYSQL
			public String deleteRecord(String nomBD, String codigo) {
				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "DELETE FROM Asignado_A WHERE codCientifico = \"" + codigo + "\"";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Registro de tabla 'Asignado_A' ELIMINADO con exito!";

				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					mensaje = "Error borrando el registro especificado  " + ex.getMessage();
				}
				return mensaje;
			}
			
			// METODO QUE LIMPIA TABLAS MYSQL
			public String deleteRecordxID(String nomBD, String id) {
				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "DELETE FROM Asignado_A WHERE idProyecto = \"" + id + "\"";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Registro de tabla 'Asignado_A' ELIMINADO con exito!";

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

					String Query = "DROP TABLE Asignado_A;";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "TABLA 'Asignado_A' ELIMINADA con exito!";

				} catch (SQLException ex) {

					mensaje = "Error borrando la tabla " + ex.getMessage();
				}
				return mensaje;
			}
}

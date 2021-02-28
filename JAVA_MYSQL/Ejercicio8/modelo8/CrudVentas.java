package modelo8;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudVentas extends ConexionBD{
	
	
	//METODO QUE CREA TABLAS MYSQL
			public String createTable(String nomBD) {
				String mensaje = "";

				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "CREATE TABLE Venta" 
							+ "(codCajero INT),"
							+ "codMaquina INT,"
							+ "codProducto INT"
							+ "PRIMARY KEY(codCajero,codMaquina,codProducto),"
							+ "FOREIGN KEY (codCajero)"
							+ "REFERENCES Cajeros(codigo)"
							+ "ON DELETE CASCADE"
							+ "ON UPDATE CASCADE,"
							+ "FOREIGN KEY (codMaquina)"
							+ "REFERENCES Maquinas_Registradoras(codigo)"
							+ "ON DELETE CASCADE"
							+ "ON UPDATE CASCADE,"
							+ "FOREIGN KEY (codProducto)"
							+ "REFERENCES Productos(codigo)"
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
			public String insertData(String nomBD, int codCajero, int codMaquina,int codProducto ) {

				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "INSERT INTO Ventas VALUE("
									+ "\"" + codCajero + "\"," 
									+ "\"" + codMaquina + "\","
									+ "\""+codProducto+"\"); ";
					
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

					String Query = "SELECT * FROM Ventas";
					Statement st = getConexion().createStatement();
					java.sql.ResultSet resultSet;
					resultSet = st.executeQuery(Query);

					while (resultSet.next()) {

						consulta += "\nCodigo Cajero: " + resultSet.getString("codCajero")
									+"\nCodigo Maquina: "+resultSet.getString("codMaquina")
									+"\nCodigo Producto: "+resultSet.getString("codProducto");
					}
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					System.out.println("Error en la adquisicion de datos");
				}
				return consulta;
			}

			// METODO QUE LIMPIA TABLAS MYSQL
			public String deleteRecordxCodCajero(String nomBD,int codigo) {
				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "DELETE FROM Ventas WHERE codCajero = \"" + codigo + "\"";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Registro de tabla 'Ventas' ELIMINADO con exito!";

				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					mensaje = "Error borrando el registro especificado  " + ex.getMessage();
				}
				return mensaje;
			}
			
			// METODO QUE LIMPIA REGISTROS DE LA TABLA MYSQL 
			public String deleteRecordxCodMaquina(String nomBD,int codigo) {
				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "DELETE FROM Ventas WHERE codMaquina = \"" + codigo + "\"";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Registro de tabla 'Ventas' ELIMINADO con exito!";

				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					mensaje = "Error borrando el registro especificado  " + ex.getMessage();
				}
				return mensaje;
			}
			
			// METODO QUE LIMPIA TABLAS MYSQL
			public String deleteRecordxCodProducto(String nomBD,int codigo) {
				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "DELETE FROM Ventas WHERE codProducto = \"" + codigo + "\"";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Registro de tabla 'Ventas' ELIMINADO con exito!";

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

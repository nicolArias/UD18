package modelo6;

import java.sql.SQLException;
import java.sql.Statement;

import clase.ConexionBD;

public class CrudPiezas_Proveedores extends ConexionBD{
	
	
	//METODO QUE CREA TABLAS MYSQL
			public String createTable(String nomBD) {
				String mensaje = "";

				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "CREATE TABLE Piezas_Proveedores" 
							+ "(codPieza INT,"
							+ "idProveedor VARCHAR(4),"
							+ "precio INT,"
							+ "PRIMARY KEY(codPieza,idProveedor),"
							+ "FOREIGN KEY (codPieza)"
							+ "REFERENCES Piezas(codigo)"
							+ "ON DELET CASCADE"
							+ "ON UPDATE CASCADE,"
							+ "FOREIGN KEY (idProveedor)"
							+ "REFERENCES Proveedores(id)"
							+ "ON DELET CASCADE"
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
			public String insertData(String nomBD, int codPieza, String idProveedor,int precio) {

				String mensaje = "";
				try {
					String Querydb = "USE " + nomBD + ";";
					Statement stdb = getConexion().createStatement();
					stdb.executeUpdate(Querydb);

					String Query = "INSERT INTO Piezas_Proveedores VALUE("
									+ "\"" + codPieza + "\"," 
									+ "\"" + idProveedor + "\","
									+ "\" "+ precio + "\"); ";
					
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

					String Query = "SELECT * FROM Piezas_Proveedores";
					Statement st = getConexion().createStatement();
					java.sql.ResultSet resultSet;
					resultSet = st.executeQuery(Query);

					while (resultSet.next()) {

						consulta += "\nCodigo Pieza: " + resultSet.getString("codPieza")
									+"\nId Proveedor: "+resultSet.getString("idProveedor")
									+"\nPrecio: "+resultSet.getString("precio");
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

					String Query = "DELETE FROM Piezas_Proveedores WHERE codPieza = \"" + codigo + "\"";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Registro de tabla 'Piezas_Proveedores' ELIMINADO con exito!";

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

					String Query = "DELETE FROM Piezas_Proveedores WHERE idProveedor = \"" + id + "\"";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "Registro de tabla 'Piezas_Proveedores' ELIMINADO con exito!";

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

					String Query = "DROP TABLE Piezas_Proveedores;";
					Statement st = getConexion().createStatement();
					st.executeUpdate(Query);

					mensaje = "TABLA 'Piezas_Proveedores' ELIMINADA con exito!";

				} catch (SQLException ex) {

					mensaje = "Error borrando la tabla " + ex.getMessage();
				}
				return mensaje;
			}
}

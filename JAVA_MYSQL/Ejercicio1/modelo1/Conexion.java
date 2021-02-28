package modelo1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

/*Autor: Nicol Dayana Arias Lebro
 * 
 * Fecha: 25/02/2021*/

public class Conexion {
	private Connection conexion;

	private final String url="jdbc:mysql://192.168.1.136:3306?useTimezone=true&serverTimezone=UTC";

	public Conexion() {
		this.conexion=openConnection();
	}
	
	//METODO QUE ABRE LA CONEXION CON SERVER MYSQL
	public Connection openConnection() {
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				this.conexion=DriverManager.getConnection(url,"remote","12345*");//credenciales temporales
				System.out.print("Server Connected");
				System.out.println("Conexion: "+conexion);
				
			}catch(SQLException | ClassNotFoundException ex  ){
				System.out.println(ex.getMessage());
				System.out.println("No se ha podido conectar con mi base de datos "+fecha());
			}
			
	return conexion; 
	}
	

		
	//METODO QUE CIERRA LA CONEXION CON SERVER MYSQL
	public  String closeConnection() {
		String mensaje="";
		
			try {
				conexion.close();
				mensaje="Server Disconnected "+fecha();
				
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
				mensaje="Error cerrando conexion "+fecha()+"\n"+ex.getMessage();
			}
			
			return mensaje;
	}
		
	//METODO QUE CREA LA BASE DE DATOS
	public String createDB(String baseD) {
		
		String mensaje="";
			try {
				String Query="CREATE DATABASE "+baseD;
				Statement st= conexion.createStatement();
				st.executeUpdate(Query);
				System.out.println("DB creada con exito!");
				mensaje="DB creada con exito!";
			JOptionPane.showMessageDialog(null, "Se ha creado la DB " +baseD+ "de forma exitosa.");
			}catch(SQLException ex) {
				System.out.println(ex.getMessage());
				System.out.println("Error creando la DB.");
				mensaje="Error creando la DB";
			}	
			
			return mensaje;
	}

	
	//METODO QUE MUESTRA FECHA
	public String  fecha() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		String stFecha=" - " + hourdateFormat.format(date);
		
		return stFecha;
	}


	public Connection getConexion() {
		System.out.println("Enviando conexion... "+conexion);
		return conexion;
	}


	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}




	public String getUrl() {
		return url;
	}

	





}

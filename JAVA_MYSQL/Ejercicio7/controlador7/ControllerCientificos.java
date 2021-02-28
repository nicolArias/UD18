package controlador7;

import clase.ConexionBD;
import modelo7.CrudAsignado_A;
import modelo7.CrudCientificos;
import modelo7.CrudProyectos;
import vista7.CientificosView;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 28/02/2021*/
public class ControllerCientificos {
	private ConexionBD conexion;
	private CrudCientificos cdCientificos;
	private CrudProyectos cdProyectos;
	private CrudAsignado_A cdAsignado_A;
	private CientificosView cVista;

	private String nombreB;
	
	public ControllerCientificos(CrudCientificos cdCientificos,CrudProyectos cdProyectos,CrudAsignado_A cdAsignado_A,CientificosView cVista) {
		
		this.conexion=new ConexionBD();//Aqui ya conecta a la base de datos
		this.cdCientificos=cdCientificos;
		this.cdProyectos=cdProyectos;
		this.cdAsignado_A=cdAsignado_A;
		this.cVista=cVista;
		
		//Aqui guardamos el valor que nos devuelve el metodo 'nombreBase' que se encuentra en la clase vista
		this.nombreB=cVista.nombreBase();
		
		//Método de create
		CrearBD();
		tableCientificos();
		tableProyectos();
		tableAsignado_A();
		opcionMenu();
		conexion.closeConnection();
	}
	
	//Crear base de datos
	public void CrearBD() {
		String crearBDM=conexion.createDB(nombreB);
		cVista.mostrarMensaje(crearBDM);
	}
	
	//TABLAS
	public void tableCientificos() {
		String crearTablaC=cdCientificos.createTable(nombreB);
		cVista.mostrarMensaje(crearTablaC);
	}
	
	public void tableProyectos() {
		String crearTablaPro=cdProyectos.createTable(nombreB);
		cVista.mostrarMensaje(crearTablaPro);
	}
	
	public void tableAsignado_A() {
		String crearTablaAA=cdAsignado_A.createTable(nombreB);
		cVista.mostrarMensaje(crearTablaAA);
	}
	
	

	public void opcionMenu() {
		String respMenu;
		String respSMenu;
		 
		try {
			respMenu=cVista.menu();
			if(respMenu.equalsIgnoreCase("Cientificos")) {
				respSMenu=cVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertCientifico();
					break;
				case("2")://Consultar
					selectCientificos();
					break;
				case("3")://Eliminar registros
					deleteCientifico();
					break;
				case("4")://Eliminar tabla
					dlTablaCientificos();
					break;
				}

			}else if(respMenu.equalsIgnoreCase("Proyectos")) {
				respSMenu=cVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertProyecto();
					break;
				case("2")://Consultar
					selectProyectos();
					break;
				case("3")://Eliminar registros
					deleteProyecto();
					break;
				case("4")://Eliminar tabla
					dlTablaProyectos();
					break;
				}
			}else if(respMenu.equalsIgnoreCase("Asignado_A")) {
				respSMenu=cVista.subMenuAsignado_A();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertAsignado();
					break;
				case("2")://Consultar
					selectAsignado();
					break;
				case("3")://Eliminar registros por codigo de pieza (int)
					deleteAsignadoxDNI();
					break;
				case("4")://Eliminar registros por id proveedor (String)
					deleteAsignadoxID();
					break;
				case("5")://Eliminar tabla
					dlTablaAsignado();
					break;
				}
				
			}else if(respMenu.equalsIgnoreCase("Cerrar")) {
				cerrarConexion();
			}
		}catch(NullPointerException e) {
			cVista.mostrarMensaje("Dato incorrecto");
		}
	}
	
	public void cerrarConexion() {
		String close=conexion.closeConnection();
		cVista.mostrarMensaje(close);

	}
	
	/*INFORMACION SOBRE LOS METODOS DEL CRUD QUE HACEN UN LLAMADO DE LOS RESPECTIVOS MÉTODOS DE CLASE
	 * todos los métodos que llaman retornan un String con el mensaje que puede ser si se realizo la
	 * operacion solicitada o en el caso de la consulta retorna lo que se va a mostrar al usuario, para
	 * ello se llama a los metodos de la clase que contiene los JOptionPane que vera el usuario*/
	
	public void insertCientifico() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=cVista.cientifico().split("/");
			String dni=cadena[0];
			String nombre=cadena[1];
			
			String s_insertC=cdCientificos.insertData(nombreB,dni,nombre);
		}
		opcionMenu();
	}
	

	public void selectCientificos() {
		String consulta=cdCientificos.getValues(nombreB);
		cVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deleteCientifico() {
		try {
			String cod=cVista.getCodigo();
			String deletP=cdCientificos.deleteRecord(nombreB, cod);
			cVista.mostrarMensaje(deletP);
			
		}catch(NullPointerException | NumberFormatException e) {
			cVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaCientificos() {
		String dlTabla=cdCientificos.deleteTabla(nombreB);
		cVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertProyecto() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=cVista.proyecto().split("/");
			String id=cadena[0];
			String nombre=cadena[1];
			int horas=Integer.parseInt(cadena[2]);
		
			String s_insertP=cdProyectos.insertData(nombreB,id,nombre,horas);
			cVista.mostrarMensaje(s_insertP);
		}
		opcionMenu();
	}
	
	//Método que consultara en el metodo de la clase los datos de la tabla
	public void selectProyectos() {
		/*El método al que llama devuelve un String con los datos que se deben imprimir
		los guardamos en la variable tipo String para luego enviarlos a la vista y mostrarlos en JOptionPane.*/
		String consulta=cdProyectos.getValues(nombreB);
		
		cVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	//Llama al método para eliminar un registro de la tabla, debemos enviar el valor de la llave primaria
	public void deleteProyecto() {
		try {
			String id=cVista.getCodigo();
			String deletP=cdProyectos.deleteRecord(nombreB,id);
			cVista.mostrarMensaje(deletP);
			
		}catch(NullPointerException | NumberFormatException e) {
			cVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaProyectos() {
		String dlTabla=cdProyectos.deleteTabla(nombreB);
		cVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertAsignado() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=cVista.asignado_A().split("/");
			String codigoCientifico =cadena[0];
			String idProyecto=cadena[1];
			
			String s_insertAA=cdAsignado_A.insertData(nombreB,codigoCientifico,idProyecto);
			cVista.mostrarMensaje(s_insertAA);
		}
		
		opcionMenu();
	}
	
	//Método que consultara en el metodo de la clase los datos de la tabla
	public void selectAsignado() {
		/*El método al que llama devuelve un String con los datos que se deben imprimir
		los guardamos en la variable tipo String para luego enviarlos a la vista y mostrarlos en JOptionPane.*/
		String consulta=cdAsignado_A.getValues(nombreB);
		
		cVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	//Llama al método para eliminar un registro de la tabla, debemos enviar el valor de la llave primaria
	public void deleteAsignadoxDNI() {
		try {
			String DNI=cVista.getCodigo();
			String deletPP=cdAsignado_A.deleteRecord(nombreB,DNI);
			cVista.mostrarMensaje(deletPP);
			
		}catch(NullPointerException | NumberFormatException e) {
			cVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
	}
	
	public void deleteAsignadoxID() {
		try {
			String id=cVista.getCodigo();
			String deletPP=cdAsignado_A.deleteRecordxID(nombreB,id);
			cVista.mostrarMensaje(deletPP);
			
		}catch(NullPointerException | NumberFormatException e) {
			cVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
	}
	
	public void dlTablaAsignado() {
		String dlTabla=cdAsignado_A.deleteTabla(nombreB);
		cVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
}

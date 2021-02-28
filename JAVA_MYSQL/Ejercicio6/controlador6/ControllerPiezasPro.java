package controlador6;

import clase.ConexionBD;
import modelo6.CrudPiezas;
import modelo6.CrudPiezas_Proveedores;
import modelo6.CrudProveedores;
import vista6.PiezasProView;



public class ControllerPiezasPro {
	private ConexionBD conexion;
	private CrudPiezas cdPiezas;
	private CrudProveedores cdProveedores;
	private CrudPiezas_Proveedores cdPiezas_Pro;
	private PiezasProView pVista;

	private String nombreB;
	
	public ControllerPiezasPro(CrudPiezas cdPiezas,CrudProveedores cdProveedores,CrudPiezas_Proveedores cdPiezas_P,PiezasProView pView) {
		
		this.conexion=new ConexionBD();//Aqui ya conecta a la base de datos
		this.cdPiezas=cdPiezas;
		this.cdProveedores=cdProveedores;
		this.cdPiezas_Pro=cdPiezas_Pro;
		this.pVista=pView;
		
		//Aqui guardamos el valor que nos devuelve el metodo 'nombreBase' que se encuentra en la clase vista
		this.nombreB=pVista.nombreBase();
		
		//Método de create
		CrearBD();
		tablePiezas();
		tableProveedores();
		tablePiezas_Pro();
		opcionMenu();
		conexion.closeConnection();
	}
	
	//Crear base de datos
	public void CrearBD() {
		String crearBDM=conexion.createDB(nombreB);
		pVista.mostrarMensaje(crearBDM);
	}
	
	//TABLAS
	public void tablePiezas() {
		String crearTablaP=cdPiezas.createTable(nombreB);
		pVista.mostrarMensaje(crearTablaP);
	}
	
	public void tableProveedores() {
		String crearTablaPro=cdProveedores.createTable(nombreB);
		pVista.mostrarMensaje(crearTablaPro);
	}
	
	public void tablePiezas_Pro() {
		String crearTablaPP=cdPiezas_Pro.createTable(nombreB);
		pVista.mostrarMensaje(crearTablaPP);
	}
	
	

	public void opcionMenu() {
		String respMenu;
		String respSMenu;
		 
		try {
			respMenu=pVista.menu();
			if(respMenu.equalsIgnoreCase("Piezas")) {
				respSMenu=pVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertPieza();
					break;
				case("2")://Consultar
					selectPiezas();
					break;
				case("3")://Eliminar registros
					deletePieza();
					break;
				case("4")://Eliminar tabla
					dlTablaPiezas();
					break;
				}

			}else if(respMenu.equalsIgnoreCase("Proveedores")) {
				respSMenu=pVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertProveedor();
					break;
				case("2")://Consultar
					selectProveedores();
					break;
				case("3")://Eliminar registros
					deleteProveedor();
					break;
				case("4")://Eliminar tabla
					dlTablaProveedores();
					break;
				}
			}else if(respMenu.equalsIgnoreCase("Piezas_Proveedores")) {
				respSMenu=pVista.subMenuPP();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertPiezaPro();
					break;
				case("2")://Consultar
					selectPiezaPro();
					break;
				case("3")://Eliminar registros por codigo de pieza (int)
					deletePiezaProxCod();
					break;
				case("4")://Eliminar registros por id proveedor (String)
					deletePiezaProxId();
					break;
				case("5")://Eliminar tabla
					dlTablaPiezaPro();
					break;
				}
				
			}else if(respMenu.equalsIgnoreCase("Cerrar")) {
				cerrarConexion();
			}
		}catch(NullPointerException e) {
			pVista.mostrarMensaje("Dato incorrecto");
		}
	}
	
	public void cerrarConexion() {
		String close=conexion.closeConnection();
		pVista.mostrarMensaje(close);

	}
	
	/*INFORMACION SOBRE LOS METODOS DEL CRUD QUE HACEN UN LLAMADO DE LOS RESPECTIVOS MÉTODOS DE CLASE
	 * todos los métodos que llaman retornan un String con el mensaje que puede ser si se realizo la
	 * operacion solicitada o en el caso de la consulta retorna lo que se va a mostrar al usuario, para
	 * ello se llama a los metodos de la clase que contiene los JOptionPane que vera el usuario*/
	
	public void insertPieza() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String cadena=pVista.pieza();
			String nombre=cadena;
			
			String s_insertP=cdPiezas.insertData(nombreB,nombre);
		}
		opcionMenu();
	}
	

	public void selectPiezas() {
		String consulta=cdPiezas.getValues(nombreB);
		pVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deletePieza() {
		try {
			int cod=pVista.getCodigo();
			String deletP=cdPiezas.deleteRecord(nombreB, cod);
			pVista.mostrarMensaje(deletP);
			
		}catch(NullPointerException | NumberFormatException e) {
			pVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaPiezas() {
		String dlTabla=cdPiezas.deleteTabla(nombreB);
		pVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertProveedor() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=pVista.proveedores().split("/");
			String id=cadena[0];
			String nombre=cadena[1];
		
			String s_insertD=cdProveedores.insertData(nombreB,id,nombre);
			pVista.mostrarMensaje(s_insertD);
		}
		
		opcionMenu();
	}
	
	//Método que consultara en el metodo de la clase los datos de la tabla
	public void selectProveedores() {
		/*El método al que llama devuelve un String con los datos que se deben imprimir
		los guardamos en la variable tipo String para luego enviarlos a la vista y mostrarlos en JOptionPane.*/
		String consulta=cdProveedores.getValues(nombreB);
		
		pVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	//Llama al método para eliminar un registro de la tabla, debemos enviar el valor de la llave primaria
	public void deleteProveedor() {
		try {
			String id=pVista.getID();
			String deletP=cdProveedores.deleteRecord(nombreB,id);
			pVista.mostrarMensaje(deletP);
			
		}catch(NullPointerException | NumberFormatException e) {
			pVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaProveedores() {
		String dlTabla=cdProveedores.deleteTabla(nombreB);
		pVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertPiezaPro() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=pVista.piezaPro().split("/");
			int codPieza=Integer.parseInt(cadena[0]);
			String idProveedor=cadena[1];
			int precio=Integer.parseInt(cadena[2]);
			
			String s_insertPP=cdPiezas_Pro.insertData(nombreB,codPieza,idProveedor,precio);
			pVista.mostrarMensaje(s_insertPP);
		}
		
		opcionMenu();
	}
	
	//Método que consultara en el metodo de la clase los datos de la tabla
	public void selectPiezaPro() {
		/*El método al que llama devuelve un String con los datos que se deben imprimir
		los guardamos en la variable tipo String para luego enviarlos a la vista y mostrarlos en JOptionPane.*/
		String consulta=cdPiezas_Pro.getValues(nombreB);
		
		pVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	//Llama al método para eliminar un registro de la tabla, debemos enviar el valor de la llave primaria
	public void deletePiezaProxCod() {
		try {
			int cod=pVista.getCodigo();
			String deletPP=cdPiezas_Pro.deleteRecord(nombreB,cod);
			pVista.mostrarMensaje(deletPP);
			
		}catch(NullPointerException | NumberFormatException e) {
			pVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
	}
	
	public void deletePiezaProxId() {
		try {
			String id=pVista.getID();
			String deletPP=cdPiezas_Pro.deleteRecordxID(nombreB,id);
			pVista.mostrarMensaje(deletPP);
			
		}catch(NullPointerException | NumberFormatException e) {
			pVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
	}
	
	public void dlTablaPiezaPro() {
		String dlTabla=cdPiezas_Pro.deleteTabla(nombreB);
		pVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
}

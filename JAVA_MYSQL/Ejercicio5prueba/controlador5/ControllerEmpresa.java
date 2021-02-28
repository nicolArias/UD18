package controlador5;

import clase.ConexionBD;
import modelo5.CrudDespachos;
import modelo5.CrudDirectores;
import vista5.EmpresaView;



public class ControllerEmpresa {
	private ConexionBD conexion;
	private CrudDespachos cdDespachos;
	private CrudDirectores cdDirectores;
	private EmpresaView eVista;

	private String nombreB;
	
	public ControllerEmpresa(CrudDespachos cdDespachos,CrudDirectores cdDirectores,EmpresaView eVista) {
		
		this.conexion=new ConexionBD();//Aqui ya conecta a la base de datos
		this.cdDespachos=cdDespachos;
		this.cdDirectores=cdDirectores;
		this.eVista=eVista;
		
		//Aqui guardamos el valor que nos devuelve el metodo 'nombreBase' que se encuentra en la clase vista
		this.nombreB=eVista.nombreBase();
		
		//Método de create
		CrearBD();
		tableDespachos();
		tableDirectores();
		opcionMenu();
		conexion.closeConnection();
	}
	
	//Crear base de datos
	public void CrearBD() {
		String crearBDM=conexion.createDB(nombreB);
		eVista.mostrarMensaje(crearBDM);
	}
	
	//TABLAS
	public void tableDespachos() {
		String crearTablaD=cdDespachos.createTable(nombreB);
		eVista.mostrarMensaje(crearTablaD);
	}
	
	public void tableDirectores() {
		String crearTablaDir=cdDirectores.createTable(nombreB);
		eVista.mostrarMensaje(crearTablaDir);
	}
	
	
	
	
	public void opcionMenu() {
		String respMenu;
		String respSMenu;
		 
		try {
			respMenu=eVista.menu();
			if(respMenu.equalsIgnoreCase("Despachos")) {
				respSMenu=eVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertDespacho();
					break;
				case("2")://Consultar
					selectDespachos();
					break;
				case("3")://Eliminar registros
					deleteDespacho();
					break;
				case("4")://Eliminar tabla
					dlTablaDespachos();
					break;
				}

			}else if(respMenu.equalsIgnoreCase("Directores")) {
				respSMenu=eVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertDirectores();
					break;
				case("2")://Consultar
					selectDirectores();
					break;
				case("3")://Eliminar registros
					deleteDirectores();
					break;
				case("4")://Eliminar tabla
					dlTablaDirectores();
					break;
				}
			}else if(respMenu.equalsIgnoreCase("Cerrar")) {
				cerrarConexion();
			}
		}catch(NullPointerException e) {
			eVista.mostrarMensaje("Dato incorrecto");
		}
	}
	
	public void cerrarConexion() {
		String close=conexion.closeConnection();
		eVista.mostrarMensaje(close);

	}
	
	/*INFORMACION SOBRE LOS METODOS DEL CRUD QUE HACEN UN LLAMADO DE LOS RESPECTIVOS MÉTODOS DE CLASE
	 * todos los métodos que llaman retornan un String con el mensaje que puede ser si se realizo la
	 * operacion solicitada o en el caso de la consulta retorna lo que se va a mostrar al usuario, para
	 * ello se llama a los metodos de la clase que contiene los JOptionPane que vera el usuario*/
	
	public void insertDespacho() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=eVista.despacho().split("/");
			int numero=Integer.parseInt(cadena[0]);
			int capacidad=Integer.parseInt(cadena[1]);
			String s_insertD=cdDespachos.insertData(nombreB,numero,capacidad);
		}
		opcionMenu();
	}
	

	public void selectDespachos() {
		String consulta=cdDespachos.getValues(nombreB);
		eVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deleteDespacho() {
		try {
			int cod=eVista.getCodigo();
			String deletD=cdDespachos.deleteRecord(nombreB, cod);
			eVista.mostrarMensaje(deletD);
			
		}catch(NullPointerException | NumberFormatException e) {
			eVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaDespachos() {
		String dlTabla=cdDespachos.deleteTabla(nombreB);
		eVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertDirectores() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=eVista.directores().split("/");
			String DNI=cadena[0];
			String nomApels=cadena[1];
			String DNIJefe=cadena[2];
			int despacho_fk=Integer.parseInt(cadena[3]);
			
			String s_insertD=cdDirectores.insertData(nombreB,DNI,nomApels,DNIJefe,despacho_fk);
			eVista.mostrarMensaje(s_insertD);
		}
		
		opcionMenu();
	}
	
	//Método que consultara en el metodo de la clase los datos de la tabla
	public void selectDirectores() {
		/*El método al que llama devuelve un String con los datos que se deben imprimir
		los guardamos en la variable tipo String para luego enviarlos a la vista y mostrarlos en JOptionPane.*/
		String consulta=cdDirectores.getValues(nombreB);
		
		eVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	//Llama al método para eliminar un registro de la tabla, debemos enviar el valor de la llave primaria
	public void deleteDirectores() {
		try {
			String dni=eVista.getDNI();
			String deletS=cdDirectores.deleteRecord(nombreB, dni);
			eVista.mostrarMensaje(deletS);
			
		}catch(NullPointerException | NumberFormatException e) {
			eVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaDirectores() {
		String dlTabla=cdDirectores.deleteTabla(nombreB);
		eVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
}

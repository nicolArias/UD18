package controlador3;

import javax.swing.JOptionPane;

import clase.ConexionBD;
import model3.CrudAlmacenes;
import model3.CrudCajas;
import vista3.AlmacenesView;

/*Autor: Nicol Dayana Arias Lebro
 *Fecha: 25/02/2021*/

public class ControllerAlmacen {
	private ConexionBD conexion;
	private CrudCajas cdCaja;
	private CrudAlmacenes cdAlmacen;
	private AlmacenesView aVista;

	private String nombreB;
	
	public ControllerAlmacen(CrudAlmacenes cdAlmacen,CrudCajas cdCaja,AlmacenesView aVista) {
		
		this.conexion=new ConexionBD();//Aqui ya conecta a la base de datos
		this.cdAlmacen=cdAlmacen;
		this.cdCaja=cdCaja;
		this.aVista=aVista;
		
		//Aqui guardamos el valor que nos devuelve el metodo 'nombreBase' que se encuentra en la clase vista
		this.nombreB=aVista.nombreBase();
		
		//Método de create
		CrearBD();
		tableAlmacenes();
		tableCajas();
		opcionMenu();
		conexion.closeConnection();
	}
	
	//Crear base de datos
	public void CrearBD() {
		String crearBDM=conexion.createDB(nombreB);
		aVista.mostrarMensaje(crearBDM);
	}
	
	//TABLAS
	public void tableAlmacenes() {
		String crearTablaA=cdAlmacen.createTable(nombreB);
		aVista.mostrarMensaje(crearTablaA);
	}
	
	public void tableCajas() {
		String crearTablaC=cdCaja.createTable(nombreB);
		aVista.mostrarMensaje(crearTablaC);
	}
	
	
	
	
	public void opcionMenu() {
		String respMenu;
		String respSMenu;
		 
		try {
			respMenu=aVista.menu();
			if(respMenu.equalsIgnoreCase("Almacenes")) {
				respSMenu=aVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertAlmacen();
					break;
				case("2")://Consultar
					selectAlmacenes();
					break;
				case("3")://Eliminar registros
					deleteAlmacen();
					break;
				case("4")://Eliminar tabla
					dlTablaAlmacenes();
					break;
				}

			}else if(respMenu.equalsIgnoreCase("Cajas")) {
				respSMenu=aVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertCaja();
					break;
				case("2")://Consultar
					selectCajas();
					break;
				case("3")://Eliminar registros
					deleteCaja();
					break;
				case("4")://Eliminar tabla
					dlTablaCajas();
					break;
				}
			}else if(respMenu.equalsIgnoreCase("Cerrar")) {
				cerrarConexion();
			}
		}catch(NullPointerException e) {
			aVista.mostrarMensaje("Dato incorrecto");
		}
	}
	
	public void cerrarConexion() {
		String close=conexion.closeConnection();
		aVista.mostrarMensaje(close);

	}
	
	public void insertAlmacen() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=aVista.almacenes().split("/");
			String lugar=cadena[0];
			int capacidad=Integer.parseInt(cadena[1]);
			String s_insertA=cdAlmacen.insertData(nombreB,lugar,capacidad);
		}
		
		opcionMenu();

	}
	

	public void selectAlmacenes() {
		
		String consulta=cdAlmacen.getValues(nombreB);
		aVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deleteAlmacen() {
		try {
			int codS=aVista.getCodigo();
			String deletA=cdAlmacen.deleteRecord(nombreB, codS);
			aVista.mostrarMensaje(deletA);
			
		}catch(NullPointerException | NumberFormatException e) {
			aVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaAlmacenes() {
		String dlTabla=cdAlmacen.deleteTabla(nombreB);
		aVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertCaja() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadenaC=aVista.cajas().split("/");
			String numR=cadenaC[0];
			String contenido=cadenaC[1];
			int valor=Integer.parseInt(cadenaC[2]);
			int almacen_fk=Integer.parseInt(cadenaC[3]);
			
			String s_insertC=cdCaja.insertData(nombreB,numR,contenido,valor,almacen_fk);
			aVista.mostrarMensaje(s_insertC);
		}
		
		opcionMenu();
	}
	

	public void selectCajas() {
		String consulta=cdCaja.getValues(nombreB);
		aVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deleteCaja() {
		try {
			String codS=aVista.getNumReferencia();
			String deletC=cdCaja.deleteRecord(nombreB, codS);
			aVista.mostrarMensaje(deletC);
			
		}catch(NullPointerException | NumberFormatException e) {
			aVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaCajas() {
		String dlTabla=cdCaja.deleteTabla(nombreB);
		aVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
}

package controlador1;


import clase.ConexionBD;
import modelo1.CrudArticulos;
import modelo1.CrudFabricantes;
import vista1.TiendaView;

/*Autor: Nicol Dayana Arias Lebro
 *Fecha: 25/02/2021*/

public class Controller {
	private ConexionBD conexion;
	private CrudArticulos cdArticulo;
	private CrudFabricantes cdFabricante;
	private TiendaView tVista;

	private String nombreB;
	
	public Controller(CrudFabricantes cdFabricante,CrudArticulos cdArticulos,TiendaView tVista) {
		
		this.conexion=new ConexionBD();//Aqui ya conecta a la base de datos
		this.cdFabricante=cdFabricante;
		this.cdArticulo=cdArticulos;
		this.tVista=tVista;
		
		//Aqui guardamos el valor que nos devuelve el metodo 'nombreBase' que se encuentra en la clase vista
		this.nombreB=tVista.nombreBase();
		
		//Método de create
		CrearBD();
		tableFabricantes();
		tableArticulos();
		opcionMenu();
		conexion.closeConnection();
	}
	
	//Crear base de datos
	public void CrearBD() {
		String crearBDM=conexion.createDB(nombreB);
		tVista.mostrarMensaje(crearBDM);
	}
	
	//TABLAS
	public void tableFabricantes() {
		String crearTablaF=cdFabricante.createTable(nombreB);
		tVista.mostrarMensaje(crearTablaF);
	}
	
	public void tableArticulos() {
		String crearTablaA=cdArticulo.createTable(nombreB);
		tVista.mostrarMensaje(crearTablaA);
	}
	
	
	
	
	public void opcionMenu() {
		String respMenu;
		String respSMenu;
		 
		try {
			respMenu=tVista.menu();
			if(respMenu.equalsIgnoreCase("Fabricantes")) {
				respSMenu=tVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertFabricante();
					break;
				case("2")://Consultar
					selectFabricante();
					break;
				case("3")://Eliminar registros
					deleteFabricante();
					break;
				case("4")://Eliminar tabla
					dlTablaFabricantes();
					break;
				}

			}else if(respMenu.equalsIgnoreCase("Articulos")) {
				respSMenu=tVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertArticulo();
					break;
				case("2")://Consultar
					selectArticulo();
					break;
				case("3")://Eliminar registros
					deleteArticulo();
					break;
				case("4")://Eliminar tabla
					dlTablaArticulos();
					break;
				}
			}else if(respMenu.equalsIgnoreCase("Cerrar")) {
				cerrarConexion();
			}
		}catch(NullPointerException e) {
			tVista.mostrarMensaje("Dato incorrecto");
		}
	}
	
	public void cerrarConexion() {
		String close=conexion.closeConnection();
		tVista.mostrarMensaje(close);

	}
	
	public void insertFabricante() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String nombre=tVista.fabricantes();
			String s_insertF=cdFabricante.insertData(nombreB,nombre);
		}
		
		opcionMenu();

	}
	

	public void selectFabricante() {
		
		String consulta=cdFabricante.getValues(nombreB);
		tVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deleteFabricante() {
		try {
			int codS=tVista.getCodigo();
			String deletF=cdFabricante.deleteRecord(nombreB, codS);
			tVista.mostrarMensaje(deletF);
			
		}catch(NullPointerException | NumberFormatException e) {
			tVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaFabricantes() {
		String dlTabla=cdFabricante.deleteTabla(nombreB);
		tVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertArticulo() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadenaA=tVista.articulos().split("/");
			String nombre=cadenaA[0];
			int precio=Integer.parseInt(cadenaA[1]);
			int fabricante_fk=Integer.parseInt(cadenaA[2]);
			
			String s_insertA=cdArticulo.insertData(nombreB,nombre,precio,fabricante_fk);
			tVista.mostrarMensaje(s_insertA);
		}
		
		opcionMenu();
	}
	

	public void selectArticulo() {
		
		String consulta=cdArticulo.getValues(nombreB);
		tVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deleteArticulo() {
		try {
			int codS=tVista.getCodigo();
			String deletA=cdArticulo.deleteRecord(nombreB, codS);
			tVista.mostrarMensaje(deletA);
			
		}catch(NullPointerException | NumberFormatException e) {
			tVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaArticulos() {
		String dlTabla=cdArticulo.deleteTabla(nombreB);
		tVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
}

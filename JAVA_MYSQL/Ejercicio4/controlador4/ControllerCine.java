package controlador4;

import clase.ConexionBD;
import modelo4.CrudPeliculas;
import modelo4.CrudSalas;
import vista4.CineView;


public class ControllerCine {
	private ConexionBD conexion;
	private CrudPeliculas cdPelicula;
	private CrudSalas cdSala;
	private CineView cVista;

	private String nombreB;
	
	public ControllerCine(CrudPeliculas cdPelicula,CrudSalas cdSala,CineView cVista) {
		
		this.conexion=new ConexionBD();//Aqui ya conecta a la base de datos
		this.cdPelicula=cdPelicula;
		this.cdSala=cdSala;
		this.cVista=cVista;
		//Aqui guardamos el valor que nos devuelve el metodo 'nombreBase' que se encuentra en la clase vista
		this.nombreB=cVista.nombreBase();
		
		//Método de create
		CrearBD();
		tablePeliculas();
		tableSalas();
		opcionMenu();
		conexion.closeConnection();
	}
	
	//Crear base de datos
	public void CrearBD() {
		String crearBDM=conexion.createDB(nombreB);
		cVista.mostrarMensaje(crearBDM);
	}
	
	//TABLAS
	public void tablePeliculas() {
		String crearTablaP=cdPelicula.createTable(nombreB);
		cVista.mostrarMensaje(crearTablaP);
	}
	
	public void tableSalas() {
		String crearTablaS=cdSala.createTable(nombreB);
		cVista.mostrarMensaje(crearTablaS);
	}
	
	
	
	
	public void opcionMenu() {
		String respMenu;
		String respSMenu;
		 
		try {
			respMenu=cVista.menu();
			if(respMenu.equalsIgnoreCase("Peliculas")) {
				respSMenu=cVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertPelicula();
					break;
				case("2")://Consultar
					selectPeliculas();
					break;
				case("3")://Eliminar registros
					deletePelicula();
					break;
				case("4")://Eliminar tabla
					dlTablaPeliculas();
					break;
				}

			}else if(respMenu.equalsIgnoreCase("Salas")) {
				respSMenu=cVista.subMenu();
				
				switch(respSMenu) {
				case("1")://Insertar registro nuevo
					insertSala();
					break;
				case("2")://Consultar
					selectSalas();
					break;
				case("3")://Eliminar registros
					deleteSala();
					break;
				case("4")://Eliminar tabla
					dlTablaSalas();
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
	
	public void insertPelicula() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=cVista.peliculas().split("/");
			String nombre=cadena[0];
			int calificacionE=Integer.parseInt(cadena[1]);
			String s_insertA=cdPelicula.insertData(nombreB,nombre,calificacionE);
			cVista.mostrarMensaje(s_insertA);
		}
		opcionMenu();
	}
	

	public void selectPeliculas() {
		String consulta=cdPelicula.getValues(nombreB);
		cVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deletePelicula() {
		try {
			int cod=cVista.getCodigo();
			String deletP=cdPelicula.deleteRecord(nombreB, cod);
			cVista.mostrarMensaje(deletP);
			
		}catch(NullPointerException | NumberFormatException e) {
			cVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaPeliculas() {
		String dlTabla=cdPelicula.deleteTabla(nombreB);
		cVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertSala() {
		//Aqui se usa el ciclo para almacenar 5 registros
		for(int i=0;i<5;i++) {
			String[] cadena=cVista.salas().split("/");
			String nombre=cadena[0];
			int pelicula_fk=Integer.parseInt(cadena[1]);
			
			String s_insertS=cdSala.insertData(nombreB,nombre,pelicula_fk);
			cVista.mostrarMensaje(s_insertS);
		}
		
		opcionMenu();
	}
	

	public void selectSalas() {
		String consulta=cdSala.getValues(nombreB);
		cVista.mostrarMensaje(consulta);
		opcionMenu();
	}
	
	public void deleteSala() {
		try {
			int cod=cVista.getCodigo();
			String deletS=cdSala.deleteRecord(nombreB, cod);
			cVista.mostrarMensaje(deletS);
			
		}catch(NullPointerException | NumberFormatException e) {
			cVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
		
	}
	
	public void dlTablaSalas() {
		String dlTabla=cdSala.deleteTabla(nombreB);
		cVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
}

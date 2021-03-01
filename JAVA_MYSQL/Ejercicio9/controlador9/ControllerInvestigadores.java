package controlador9;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import clase.ConexionBD;
import modelo9.CrudEquipos;
import modelo9.CrudFacultades;
import modelo9.CrudInvestigadores;
import modelo9.CrudReservas;
import vista9.InvestigadoresView;


public class ControllerInvestigadores {
	private ConexionBD conexion;
	private CrudFacultades cdFacultades;
	private CrudEquipos cdEquipos;
	private CrudInvestigadores cdInvestigadores;
	private CrudReservas cdReservas;
	private InvestigadoresView iVista;

	private String nombreB;

	public ControllerInvestigadores(CrudFacultades cdFacultades,CrudEquipos cdEquipos,CrudInvestigadores cdInvestigadores,InvestigadoresView iView) {

		this.conexion = new ConexionBD();// Aqui ya conecta a la base de datos
		this.cdFacultades=cdFacultades;
		this.cdEquipos=cdEquipos;
		this.cdInvestigadores=cdInvestigadores;
		this.cdReservas=cdReservas;
		this.iVista=iView;

		// Aqui guardamos el valor que nos devuelve el metodo 'nombreBase' que se
		// encuentra en la clase vista
		this.nombreB = iVista.nombreBase();

		// Método de create
		CrearBD();
		tableFacultades();
		tableEquipos();
		tableInvestigadores();
		tableReservas();
		opcionMenu();
		conexion.closeConnection();
	}

	// Crear base de datos
	public void CrearBD() {
		String crearBDM = conexion.createDB(nombreB);
		iVista.mostrarMensaje(crearBDM);
	}

	// TABLAS
	public void tableFacultades() {
		String crearTablaF = cdFacultades.createTable(nombreB);
		iVista.mostrarMensaje(crearTablaF);
	}

	public void tableEquipos() {
		String crearTablaE = cdEquipos.createTable(nombreB);
		iVista.mostrarMensaje(crearTablaE);
	}

	public void tableInvestigadores() {
		String crearTablaI = cdInvestigadores.createTable(nombreB);
		iVista.mostrarMensaje(crearTablaI);
	}
	
	public void tableReservas() {
		String crearTablaR = cdReservas.createTable(nombreB);
		iVista.mostrarMensaje(crearTablaR);
	}


	public void opcionMenu() {
		String respMenu;
		String respSMenu;

		try {
			respMenu = iVista.menu();
			if (respMenu.equalsIgnoreCase("Facultades")) {
				respSMenu = iVista.subMenu();

				switch (respSMenu) {
				case ("1"):// Insertar registro nuevo
					insertFacultad();
					break;
				case ("2"):// Consultar
					selectFacultades();
					break;
				case ("3"):// Eliminar registros
					deleteFacultad();
					break;
				case ("4"):// Eliminar tabla
					dlTablaFacultades();
					break;
				}

			} else if (respMenu.equalsIgnoreCase("Equipos")) {
				respSMenu = iVista.subMenu();

				switch (respSMenu) {
				case ("1"):// Insertar registro nuevo
					insertEquipo();
					break;
				case ("2"):// Consultar
					selectEquipos();
					break;
				case ("3"):// Eliminar registros
					deleteEquipo();
					break;
				case ("4"):// Eliminar tabla
					dlTablaEquipos();
					break;
				}
			} else if (respMenu.equalsIgnoreCase("Investigadores")) {
				respSMenu = iVista.subMenu();

				switch (respSMenu) {
				case ("1"):// Insertar registro nuevo
					insertInvestigador();
					break;
				case ("2"):// Consultar
					selectInvestigadores();
					break;
				case ("3"):
					deleteInvestigador();
					break;
				case ("4"):// Eliminar tabla
					dlTablaInvestigadores();
					break;
				}
			} else if (respMenu.equalsIgnoreCase("Reservas")) {
				respSMenu = iVista.subMenuReserva();

				switch (respSMenu) {
				case ("1"):// Insertar registro nuevo
					insertReserva();
					break;
				case ("2"):// Consultar
					selectReservas();
					break;
				case ("3")://Eliminar venta por codigo Cajero
					deleteReserva(3);
					break;
				case ("4")://Eliminar venta por codigo Maquina
					deleteReserva(4);
					break;

				case ("5"):// Eliminar tabla
					dlTablaReservas();
					break;
				}

			} else if (respMenu.equalsIgnoreCase("Cerrar")) {
				cerrarConexion();
			}
		} catch (NullPointerException e) {
			iVista.mostrarMensaje("Dato incorrecto");
		}
	}

	public void cerrarConexion() {
		String close = conexion.closeConnection();
		iVista.mostrarMensaje(close);

	}

	/*
	 * INFORMACION SOBRE LOS METODOS DEL CRUD QUE HACEN UN LLAMADO DE LOS
	 * RESPECTIVOS MÉTODOS DE CLASE todos los métodos que llaman retornan un String
	 * con el mensaje que puede ser si se realizo la operacion solicitada o en el
	 * caso de la consulta retorna lo que se va a mostrar al usuario, para ello se
	 * llama a los metodos de la clase que contiene los JOptionPane que vera el
	 * usuario
	 */

	public void insertFacultad() {
		// Aqui se usa el ciclo para almacenar 5 registros
		for (int i = 0; i < 5; i++) {
			String[] cadena = iVista.facultad().split("/");
			int codigo=Integer.parseInt(cadena[0]);
			String nombre=cadena[1];
			String s_insertF = cdFacultades.insertData(nombreB,codigo,nombre);
		}
		opcionMenu();
	}

	public void selectFacultades() {
		String consulta = cdFacultades.getValues(nombreB);
		iVista.mostrarMensaje(consulta);
		opcionMenu();
	}

	public void deleteFacultad() {
		try {
			int cod = iVista.getCodigo();
			String deletF = cdFacultades.deleteRecord(nombreB, cod);
			iVista.mostrarMensaje(deletF);

		} catch (NullPointerException | NumberFormatException e) {
			iVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();

	}

	public void dlTablaFacultades() {
		String dlTabla = cdFacultades.deleteTabla(nombreB);
		iVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}

	public void insertEquipo() {
		// Aqui se usa el ciclo para almacenar 5 registros
		for (int i = 0; i < 5; i++) {
			String[] cadena = iVista.equipo().split("/");
			String numSerie=cadena[0];
			String nombre=cadena[1];
			int facultad_fk=Integer.parseInt(cadena[2]);
			
			String s_insertE = cdEquipos.insertData(nombreB,numSerie,nombre,facultad_fk);
			iVista.mostrarMensaje(s_insertE);
		}
		opcionMenu();
	}

	// Método que consultara en el metodo de la clase los datos de la tabla
	public void selectEquipos() {
		/*
		 * El método al que llama devuelve un String con los datos que se deben imprimir
		 * los guardamos en la variable tipo String para luego enviarlos a la vista y
		 * mostrarlos en JOptionPane.
		 */
		String consulta = cdEquipos.getValues(nombreB);

		iVista.mostrarMensaje(consulta);
		opcionMenu();
	}

	// Llama al método para eliminar un registro de la tabla, debemos enviar el
	// valor de la llave primaria
	public void deleteEquipo() {
		try {
			String cod = iVista.getCodigoString();
			String deletE = cdEquipos.deleteRecord(nombreB, cod);
			iVista.mostrarMensaje(deletE);

		} catch (NullPointerException | NumberFormatException e) {
			iVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();

	}

	public void dlTablaEquipos() {
		String dlTabla = cdEquipos.deleteTabla(nombreB);
		iVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}

	public void insertInvestigador() {
		// Aqui se usa el ciclo para almacenar 5 registros
		for (int i = 0; i < 5; i++) {
			String[] cadena = iVista.investigador().split("/");
			String DNI = cadena[0];
			String nomApels=cadena[1];
			int facultad_fk=Integer.parseInt(cadena[2]);
	

			String s_insertI = cdInvestigadores.insertData(nombreB,DNI,nomApels,facultad_fk);
			iVista.mostrarMensaje(s_insertI);
		}

		opcionMenu();
	}

	// Método que consultara en el metodo de la clase los datos de la tabla
	public void selectInvestigadores() {
		/*
		 * El método al que llama devuelve un String con los datos que se deben imprimir
		 * los guardamos en la variable tipo String para luego enviarlos a la vista y
		 * mostrarlos en JOptionPane.
		 */
		String consulta = cdInvestigadores.getValues(nombreB);

		iVista.mostrarMensaje(consulta);
		opcionMenu();
	}

	// Llama al método para eliminar un registro de la tabla, debemos enviar el
	// valor de la llave primaria
	public void deleteInvestigador() {
		try {
			String codigo= iVista.getCodigoString();
			String deletP = cdInvestigadores.deleteRecord(nombreB, codigo);
			iVista.mostrarMensaje(deletP);

		} catch (NullPointerException | NumberFormatException e) {
			iVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
	}

	public void dlTablaInvestigadores() {
		String dlTabla = cdInvestigadores.deleteTabla(nombreB);
		iVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertReserva() {
		// Aqui se usa el ciclo para almacenar 5 registros
		for (int i = 0; i < 5; i++) {
			String[] cadena = iVista.reserva().split("/");
			String DNI_FK = cadena[0];
			String numSerie_FK = cadena[1];
			String comienzo = cadena[2];
			String fin=cadena[3];


			String s_insertR = cdReservas.insertData(nombreB,DNI_FK,numSerie_FK,comienzo,fin);
			iVista.mostrarMensaje(s_insertR);
		}

		opcionMenu();
	}

	// Método que consultara en el metodo de la clase los datos de la tabla
	public void selectReservas() {
		String consulta = cdReservas.getValues(nombreB);

		iVista.mostrarMensaje(consulta);
		opcionMenu();
	}

	// Llama al método para eliminar un registro de la tabla, debemos enviar el
	// valor de la llave primaria
	public void deleteReserva(int num) {
		
		String deletV;
		try {
			String codigo = iVista.getCodigoString();
			
			if(num==3) {//Eliminar venta por codigo cajero
				deletV = cdReservas.deleteRecordxDNI(nombreB, codigo);
				iVista.mostrarMensaje(deletV);
			}else if(num==4) {//Eliminar  venta por codigo maquina
				deletV =cdReservas.deleteRecordxNumSerie(nombreB, codigo);
				iVista.mostrarMensaje(deletV);
			}
			

		} catch (NullPointerException | NumberFormatException e) {
			iVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
	}

	public void dlTablaReservas() {
		String dlTabla = cdReservas.deleteTabla(nombreB);
		iVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
}

package controlador8;

import clase.ConexionBD;

import modelo8.CrudCajeros;
import modelo8.CrudMaquinas_Registradoras;
import modelo8.CrudProductos;
import modelo8.CrudVentas;
import vista8.VentasView;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha: 28/02/2021*/
public class ControllerGrandesAlmacenes {
	private ConexionBD conexion;
	private CrudCajeros cdCajeros;
	private CrudProductos cdProductos;
	private CrudMaquinas_Registradoras cdMaquinasR;
	private CrudVentas cdVentas;
	private VentasView vVista;

	private String nombreB;

	public ControllerGrandesAlmacenes(CrudCajeros cdCajeros,CrudMaquinas_Registradoras cdMaquinasR,CrudProductos cdProductos, CrudVentas cdVentas, VentasView vVista) {

		this.conexion = new ConexionBD();// Aqui ya conecta a la base de datos
		this.cdCajeros = cdCajeros;
		this.cdProductos = cdProductos;
		this.cdMaquinasR = cdMaquinasR;
		this.cdVentas = cdVentas;
		this.vVista = vVista;

		// Aqui guardamos el valor que nos devuelve el metodo 'nombreBase' que se
		// encuentra en la clase vista
		this.nombreB = vVista.nombreBase();

		// Método de create
		CrearBD();
		tableCajeros();
		tableMaquinasR();
		tableProductos();
		tableVentas();
		opcionMenu();
		conexion.closeConnection();
	}

	// Crear base de datos
	public void CrearBD() {
		String crearBDM = conexion.createDB(nombreB);
		vVista.mostrarMensaje(crearBDM);
	}

	// TABLAS
	public void tableCajeros() {
		String crearTablaC = cdCajeros.createTable(nombreB);
		vVista.mostrarMensaje(crearTablaC);
	}

	public void tableProductos() {
		String crearTablaP = cdProductos.createTable(nombreB);
		vVista.mostrarMensaje(crearTablaP);
	}

	public void tableMaquinasR() {
		String crearTablaMR = cdMaquinasR.createTable(nombreB);
		vVista.mostrarMensaje(crearTablaMR);
	}

	public void opcionMenu() {
		String respMenu;
		String respSMenu;

		try {
			respMenu = vVista.menu();
			if (respMenu.equalsIgnoreCase("Cajeros")) {
				respSMenu = vVista.subMenu();

				switch (respSMenu) {
				case ("1"):// Insertar registro nuevo
					insertCajero();
					break;
				case ("2"):// Consultar
					selectCajeros();
					break;
				case ("3"):// Eliminar registros
					deleteCajero();
					break;
				case ("4"):// Eliminar tabla
					dlTablaCajeros();
					break;
				}

			} else if (respMenu.equalsIgnoreCase("Productos")) {
				respSMenu = vVista.subMenu();

				switch (respSMenu) {
				case ("1"):// Insertar registro nuevo
					insertProducto();
					break;
				case ("2"):// Consultar
					selectProductos();
					break;
				case ("3"):// Eliminar registros
					deleteProducto();
					break;
				case ("4"):// Eliminar tabla
					dlTablaProductos();
					break;
				}
			} else if (respMenu.equalsIgnoreCase("Maquinas")) {
				respSMenu = vVista.subMenu();

				switch (respSMenu) {
				case ("1"):// Insertar registro nuevo
					insertMaquina();
					break;
				case ("2"):// Consultar
					selectMaquinas();
					break;
				case ("3"):
					deleteMaquinas();
					break;
				case ("4"):// Eliminar tabla
					dlTablaMaquinas();
					break;
				}
			} else if (respMenu.equalsIgnoreCase("Ventas")) {
				respSMenu = vVista.subMenuVentas();

				switch (respSMenu) {
				case ("1"):// Insertar registro nuevo
					insertVenta();
					break;
				case ("2"):// Consultar
					selectVentas();
					break;
				case ("3")://Eliminar venta por codigo Cajero
					deleteVenta(3);
					break;
				case ("4")://Eliminar venta por codigo Maquina
					deleteVenta(4);
					break;
				case ("5")://Eliminar venta por codigo Producto
					deleteVenta(5);
					break;
				case ("6"):// Eliminar tabla
					dlTablaVentas();
					break;
				}

			} else if (respMenu.equalsIgnoreCase("Cerrar")) {
				cerrarConexion();
			}
		} catch (NullPointerException e) {
			vVista.mostrarMensaje("Dato incorrecto");
		}
	}

	public void cerrarConexion() {
		String close = conexion.closeConnection();
		vVista.mostrarMensaje(close);

	}

	/*
	 * INFORMACION SOBRE LOS METODOS DEL CRUD QUE HACEN UN LLAMADO DE LOS
	 * RESPECTIVOS MÉTODOS DE CLASE todos los métodos que llaman retornan un String
	 * con el mensaje que puede ser si se realizo la operacion solicitada o en el
	 * caso de la consulta retorna lo que se va a mostrar al usuario, para ello se
	 * llama a los metodos de la clase que contiene los JOptionPane que vera el
	 * usuario
	 */

	public void insertCajero() {
		// Aqui se usa el ciclo para almacenar 5 registros
		for (int i = 0; i < 5; i++) {
			String nombre = vVista.cajero();
		
			String s_insertC = cdCajeros.insertData(nombreB, nombre);
		}
		opcionMenu();
	}

	public void selectCajeros() {
		String consulta = cdCajeros.getValues(nombreB);
		vVista.mostrarMensaje(consulta);
		opcionMenu();
	}

	public void deleteCajero() {
		try {
			int cod = vVista.getCodigo();
			String deletC = cdCajeros.deleteRecord(nombreB, cod);
			vVista.mostrarMensaje(deletC);

		} catch (NullPointerException | NumberFormatException e) {
			vVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();

	}

	public void dlTablaCajeros() {
		String dlTabla = cdCajeros.deleteTabla(nombreB);
		vVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}

	public void insertMaquina() {
		// Aqui se usa el ciclo para almacenar 5 registros
		for (int i = 0; i < 5; i++) {
			int piso = vVista.maquinaR();
			String s_insertM = cdMaquinasR.insertData(nombreB,piso);
			vVista.mostrarMensaje(s_insertM);
		}
		opcionMenu();
	}

	// Método que consultara en el metodo de la clase los datos de la tabla
	public void selectMaquinas() {
		/*
		 * El método al que llama devuelve un String con los datos que se deben imprimir
		 * los guardamos en la variable tipo String para luego enviarlos a la vista y
		 * mostrarlos en JOptionPane.
		 */
		String consulta = cdMaquinasR.getValues(nombreB);

		vVista.mostrarMensaje(consulta);
		opcionMenu();
	}

	// Llama al método para eliminar un registro de la tabla, debemos enviar el
	// valor de la llave primaria
	public void deleteMaquinas() {
		try {
			int cod = vVista.getCodigo();
			String deletM = cdMaquinasR.deleteRecord(nombreB, cod);
			vVista.mostrarMensaje(deletM);

		} catch (NullPointerException | NumberFormatException e) {
			vVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();

	}

	public void dlTablaMaquinas() {
		String dlTabla = cdMaquinasR.deleteTabla(nombreB);
		vVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}

	public void insertProducto() {
		// Aqui se usa el ciclo para almacenar 5 registros
		for (int i = 0; i < 5; i++) {
			String[] cadena = vVista.producto().split("/");
			String nombre = cadena[0];
			int precio =Integer.parseInt(cadena[1]);

			String s_insertP = cdProductos.insertData(nombreB,nombre,precio);
			vVista.mostrarMensaje(s_insertP);
		}

		opcionMenu();
	}

	// Método que consultara en el metodo de la clase los datos de la tabla
	public void selectProductos() {
		/*
		 * El método al que llama devuelve un String con los datos que se deben imprimir
		 * los guardamos en la variable tipo String para luego enviarlos a la vista y
		 * mostrarlos en JOptionPane.
		 */
		String consulta = cdProductos.getValues(nombreB);

		vVista.mostrarMensaje(consulta);
		opcionMenu();
	}

	// Llama al método para eliminar un registro de la tabla, debemos enviar el
	// valor de la llave primaria
	public void deleteProducto() {
		try {
			int  codigo= vVista.getCodigo();
			String deletP = cdProductos.deleteRecord(nombreB, codigo);
			vVista.mostrarMensaje(deletP);

		} catch (NullPointerException | NumberFormatException e) {
			vVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
	}

	public void dlTablaProductos() {
		String dlTabla = cdProductos.deleteTabla(nombreB);
		vVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
	
	
	public void insertVenta() {
		// Aqui se usa el ciclo para almacenar 5 registros
		for (int i = 0; i < 5; i++) {
			String[] cadena = vVista.venta().split("/");
			int codCajero = Integer.parseInt(cadena[0]);
			int codMaquina = Integer.parseInt(cadena[0]);
			int codProducto = Integer.parseInt(cadena[0]);


			String s_insertP = cdVentas.insertData(nombreB,codCajero,codMaquina,codProducto);
			vVista.mostrarMensaje(s_insertP);
		}

		opcionMenu();
	}

	// Método que consultara en el metodo de la clase los datos de la tabla
	public void selectVentas() {
		String consulta = cdVentas.getValues(nombreB);

		vVista.mostrarMensaje(consulta);
		opcionMenu();
	}

	// Llama al método para eliminar un registro de la tabla, debemos enviar el
	// valor de la llave primaria
	public void deleteVenta(int num) {
		
		String deletV;
		try {
			int codigo = vVista.getCodigo();
			
			if(num==3) {//Eliminar venta por codigo cajero
				deletV = cdVentas.deleteRecordxCodCajero(nombreB, codigo);
				vVista.mostrarMensaje(deletV);
			}else if(num==3) {//Eliminar  venta por codigo maquina
				deletV = cdVentas.deleteRecordxCodMaquina(nombreB, codigo);
				vVista.mostrarMensaje(deletV);
			}else if(num==4) {//Eliminar venta por codigo producto
				deletV = cdVentas.deleteRecordxCodProducto(nombreB, codigo);
				vVista.mostrarMensaje(deletV);
			}
			

		} catch (NullPointerException | NumberFormatException e) {
			vVista.mostrarMensaje("Dato incorrecto");
		}
		opcionMenu();
	}

	public void dlTablaVentas() {
		String dlTabla = cdVentas.deleteTabla(nombreB);
		vVista.mostrarMensaje(dlTabla);
		opcionMenu();
	}
}

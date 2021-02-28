package vista6;

import javax.swing.JOptionPane;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha:25/02/2021*/
public class PiezasProView {
	// CONSTRUCTOR POR DEFECTO
	public PiezasProView() {

	}

	// Método para solicitar el nombre de la base de datos
	public String nombreBase() {
		String baseDatos = JOptionPane.showInputDialog("Ingrese nombre de la base de datos a crear:");

		return baseDatos;
	}

	// Menú de opciones para escoger tabla
	public String menu() {
		String menu = JOptionPane
				.showInputDialog("MENÚ:" + "\n- Piezas" + "\n- Proveedores" + "\n- Piezas_Proveedores" + "\n- Cerrar");
		return menu;
	}

	// Menú de opciones para realizar el crud
	public String subMenu() {
		String subMenu = JOptionPane.showInputDialog(
				"MENÚ:" + "\n1. Insertar " + "\n2. Consultar" + "\n3. Eliminar Registros" + "\n4. Eliminar tabla");
		return subMenu;
	}

	// SubMenu de opciones para tabla de muchos a muchos
	public String subMenuPP() {
		String subMenu = JOptionPane
				.showInputDialog("MENÚ:" + "\n1. Insertar " + "\n2. Consultar" + "\n3. Eliminar Registro x codigoPieza"
						+ "\n4. Eliminar Registro x ID Proveedor" + "\n5. Eliminar tabla");
		return subMenu;
	}

	// Datos de entrada para la tabla 'Piezas'
	public String pieza() {
		String nombre = JOptionPane.showInputDialog("Nombre: ");

		return nombre;
	}

	// En este metodo el usuario escribira el codigo del registro que desea eliminar
	public Integer getCodigo() {
		int numero = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Borrar registros: \n" + "" + "Ingrese el codigo del registro que desea eliminar:"));
		return numero;
	}

	public String getID() {
		String id = JOptionPane.showInputDialog(null,
				"Borrar registros: \n" + "" + "Ingrese el ID del registro que desea eliminar:");
		return id;
	}

	/* Datos de entrada para la tabla 'Proveedores' */
	public String proveedores() {
		String id = JOptionPane.showInputDialog("ID:");
		String nombre = JOptionPane.showInputDialog("Nombre:");

		return id + "/" + nombre;
	}

	/* Datos de entrada para la tabla 'Proveedores' */
	public String piezaPro() {
		String codPieza_fk = JOptionPane.showInputDialog("Codigo Pieza:");
		String idPro_fk = JOptionPane.showInputDialog("Id Proveedor:");
		String precio = JOptionPane.showInputDialog("Precio:");

		return codPieza_fk + "/" + idPro_fk + "/" + precio;
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
}

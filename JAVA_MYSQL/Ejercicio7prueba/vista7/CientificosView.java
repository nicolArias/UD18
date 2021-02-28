package vista7;

import javax.swing.JOptionPane;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha:25/02/2021*/
public class CientificosView {
	// CONSTRUCTOR POR DEFECTO
	public CientificosView() {

	}

	// Método para solicitar el nombre de la base de datos
	public String nombreBase() {
		String baseDatos = JOptionPane.showInputDialog("Ingrese nombre de la base de datos a crear:");

		return baseDatos;
	}

	// Menú de opciones para escoger tabla
	public String menu() {
		String menu = JOptionPane
				.showInputDialog("MENÚ:" + "\n- Cientificos" + "\n- Proyectos" + "\n- Asignado_A" + "\n- Cerrar");
		return menu;
	}

	// Menú de opciones para realizar el crud
	public String subMenu() {
		String subMenu = JOptionPane.showInputDialog(
				"MENÚ:" + "\n1. Insertar " + "\n2. Consultar" + "\n3. Eliminar Registros" + "\n4. Eliminar tabla");
		return subMenu;
	}

	// SubMenu de opciones para tabla de muchos a muchos
	public String subMenuAsignado_A() {
		String subMenu = JOptionPane
				.showInputDialog("MENÚ:" + "\n1. Insertar " + "\n2. Consultar" + "\n3. Eliminar Registro x codigoCientificos"
						+ "\n4. Eliminar Registro x ID Proyecto" + "\n5. Eliminar tabla");
		return subMenu;
	}

	// Datos de entrada para la tabla 'Cientificos'
	public String cientifico() {
		String ID = JOptionPane.showInputDialog("ID: ");
		String nombre = JOptionPane.showInputDialog("Nombre y Apellidos: ");

		return ID+"/"+nombre;
	}

	// En este metodo el usuario escribira el codigo del registro que desea eliminar
	public String getCodigo() {
		String codigo= JOptionPane.showInputDialog(null,
				"Borrar registros: \n" + "" + "Ingrese el codigo del registro que desea eliminar:");
		return codigo;
	}

	/* Datos de entrada para la tabla 'Proyectos' */
	public String proyecto() {
		String id = JOptionPane.showInputDialog("ID:");
		String nombre = JOptionPane.showInputDialog("Nombre:");
		String horas = JOptionPane.showInputDialog("Horas:");

		return id + "/" + nombre+"/"+horas;
	}

	/* Datos de entrada para la tabla 'Asignado_A' */
	public String asignado_A() {
		String codC_fk = JOptionPane.showInputDialog("Codigo Cientifico:");
		String idPro_fk = JOptionPane.showInputDialog("Id Proyecto:");

		return codC_fk + "/" + idPro_fk;
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
}

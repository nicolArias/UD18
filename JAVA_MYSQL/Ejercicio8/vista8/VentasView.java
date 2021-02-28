package vista8;

import javax.swing.JOptionPane;

/*Autor: Nicol Dayana Arias Lebro
 * Fecha:25/02/2021*/
public class VentasView {
	// CONSTRUCTOR POR DEFECTO
	public VentasView() {

	}

	// Método para solicitar el nombre de la base de datos
	public String nombreBase() {
		String baseDatos = JOptionPane.showInputDialog("Ingrese nombre de la base de datos a crear:");

		return baseDatos;
	}

	// Menú de opciones para escoger tabla
	public String menu() {
		String menu = JOptionPane
				.showInputDialog("MENÚ:" + "\n- Cajeros" + "\n- Maquinas" + "\n- Productos" +"\n- Ventas"+ "\n- Cerrar");
		return menu;
	}

	// Menú de opciones para realizar el crud
	public String subMenu() {
		String subMenu = JOptionPane.showInputDialog(
				"MENÚ:" + "\n1. Insertar " + "\n2. Consultar" + "\n3. Eliminar Registros" + "\n4. Eliminar tabla");
		return subMenu;
	}

	// SubMenu de opciones para tabla de muchos a muchos
	public String subMenuVentas() {
		String subMenu = JOptionPane
				.showInputDialog("MENÚ:" + "\n1. Insertar " + "\n2. Consultar" + "\n3. Eliminar Registro x codigoCajero"
						+ "\n4. Eliminar Registro x codigoProducto" +"\n5. Eliminar registro x codigo Maquina" +"\n6. Eliminar tabla");
		return subMenu;
	}

	// Datos de entrada para la tabla 'Cajeros'
	public String cajero() {
		
		String nombre = JOptionPane.showInputDialog("Nombre y Apellidos: ");

		return nombre;
	}

	// En este metodo el usuario escribira el codigo del registro que desea eliminar
	public int getCodigo() {
		int codigo= Integer.parseInt(JOptionPane.showInputDialog(null,
				"Borrar registros: \n" + "" + "Ingrese el codigo del registro que desea eliminar:"));
		return codigo;
	}

	/* Datos de entrada para la tabla 'Productos' */
	public String producto() {
		
		String nombre = JOptionPane.showInputDialog("Nombre:");
		String precio = JOptionPane.showInputDialog("precio:");

		return nombre+"/"+precio;
	}

	/* Datos de entrada para la tabla 'Maquinas Registradoras' */
	public int maquinaR() {
		int piso =Integer.parseInt(JOptionPane.showInputDialog("Piso:"));

		return piso;
	}
	
	/* Datos de entrada para la tabla 'Ventas' */
	public String venta() {
		String codCajero_FK = JOptionPane.showInputDialog("Codigo cajero:");
		String codMaquina_FK = JOptionPane.showInputDialog("Codigo maquina registradora:");
		String codProducto_FK = JOptionPane.showInputDialog("Codigo maquina producto:");
		
		return codCajero_FK + "/" + codMaquina_FK+"/"+codProducto_FK;
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
}

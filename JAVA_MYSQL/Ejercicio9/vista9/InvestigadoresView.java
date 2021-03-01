package vista9;

import javax.swing.JOptionPane;

public class InvestigadoresView {
	// CONSTRUCTOR POR DEFECTO
		public InvestigadoresView() {

		}

		// Método para solicitar el nombre de la base de datos
		public String nombreBase() {
			String baseDatos = JOptionPane.showInputDialog("Ingrese nombre de la base de datos a crear:");

			return baseDatos;
		}

		// Menú de opciones para escoger tabla
		public String menu() {
			String menu = JOptionPane
					.showInputDialog("MENÚ:" + "\n- Facultades (principal)" + "\n- Equipos " + "\n- Investigadores" +"\n- Reserva"+ "\n- Cerrar");
			return menu;
		}

		// Menú de opciones para realizar el crud
		public String subMenu() {
			String subMenu = JOptionPane.showInputDialog(
					"MENÚ:" + "\n1. Insertar " + "\n2. Consultar" + "\n3. Eliminar Registros" + "\n4. Eliminar tabla");
			return subMenu;
		}

		// SubMenu de opciones para tabla de muchos a muchos
		public String subMenuReserva() {
			String subMenu = JOptionPane
					.showInputDialog("MENÚ:" + "\n1. Insertar " + "\n2. Consultar" + "\n3. Eliminar Registro x DNI"
							+ "\n4. Eliminar Registro x numero Serie" +"\n5. Eliminar tabla");
			return subMenu;
		}

		// Datos de entrada para la tabla 'Facultad'
		public String facultad() {
			
			String nombre = JOptionPane.showInputDialog("Nombre: ");

			return nombre;
		}

		// En este metodo el usuario escribira el codigo del registro que desea eliminar
		public int getCodigo() {
			int codigo= Integer.parseInt(JOptionPane.showInputDialog(null,
					"Borrar registros: \n" + "" + "Ingrese el codigo del registro que desea eliminar:"));
			return codigo;
		}
		
		public String getCodigoString() {
			String codigo= JOptionPane.showInputDialog(null,
					"Borrar registros: \n" + "" + "Ingrese el codigo del registro que desea eliminar:");
			return codigo;
		}


		/* Datos de entrada para la tabla 'Equipos' */
		public String equipo() {
			
			String nombre = JOptionPane.showInputDialog("Nombre:");
			String facultad_fk = JOptionPane.showInputDialog("Codigo de la facultad:");

			return nombre+"/"+facultad_fk;
		}

		/* Datos de entrada para la tabla 'Investigadores' */
		public String investigador() {
			String nomApels =JOptionPane.showInputDialog("Nombre y apellidos:");
			String facultad_fk=JOptionPane.showInputDialog("Codigo de la facultad:");
			
			return nomApels+"/"+facultad_fk;
		}
		
		/* Datos de entrada para la tabla 'Reserva' */
		public String reserva() {
			String dni_FK = JOptionPane.showInputDialog("DNI Investigador:");
			String numSerie_FK = JOptionPane.showInputDialog("Numero Serie Equipos:");
			String comienzo = JOptionPane.showInputDialog("Fecha inicio (aaaa-mm-dd hh:mm:ss):");
			String fin = JOptionPane.showInputDialog("Fecha fin (aaaa-mm-dd hh:mm:ss):");
			
			return dni_FK+"/"+numSerie_FK+"/"+comienzo+"/"+fin;
		}

		public void mostrarMensaje(String mensaje) {
			JOptionPane.showMessageDialog(null, mensaje);
		}
}
